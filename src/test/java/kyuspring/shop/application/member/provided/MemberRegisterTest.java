package kyuspring.shop.application.member.provided;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import kyuspring.shop.ShopTestConfiguration;
import kyuspring.shop.domain.member.DuplicateEmailException;
import kyuspring.shop.domain.member.Member;
import kyuspring.shop.domain.member.MemberRegisterRequest;
import kyuspring.shop.domain.member.MemberStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static kyuspring.shop.domain.member.MemberFixture.createMemberRegisterRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
@Import(ShopTestConfiguration.class)
record MemberRegisterTest(EntityManager entityManager, MemberRegister memberRegister) {
    @Test
    void register() {
        Member member = memberRegister.register(createMemberRegisterRequest());

        assertThat(member.getId()).isNotNull();
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    void duplicateEmailFail() {
        memberRegister.register(createMemberRegisterRequest());
        
        assertThatThrownBy(() -> memberRegister.register(createMemberRegisterRequest()))
            .isInstanceOf(DuplicateEmailException.class);
    }

    @Test
    void memberRegisterRequestValidationFail() {
        final String shortString = "a";
        final String longString = "a".repeat(200);

        checkValidation(new MemberRegisterRequest("invalid email", "nickname", "password"));

        checkValidation(new MemberRegisterRequest("test@email.com", shortString, "password"));
        checkValidation(new MemberRegisterRequest("test@email.com", longString, "password"));

        checkValidation(new MemberRegisterRequest("test@email.com", "nickname", shortString));
        checkValidation(new MemberRegisterRequest("test@email.com", "nickname", longString));
    }

    private void checkValidation(MemberRegisterRequest invalidRequest) {
        assertThatThrownBy(() -> memberRegister.register(invalidRequest))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    void activate() {
        Member member = memberRegister.register(createMemberRegisterRequest());
        entityManager.flush();
        entityManager.clear();

        member = memberRegister.activate(member.getId());
        entityManager.flush();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
        assertThat(member.getDetail().getActivatedAt()).isNotNull();
    }

    @Test
    void deactivate() {
        Member member = memberRegister.register(createMemberRegisterRequest());
        entityManager.flush();
        entityManager.clear();

        memberRegister.activate(member.getId());
        entityManager.flush();
        entityManager.clear();

        member = memberRegister.deactivate(member.getId());
        entityManager.flush();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATED);
        assertThat(member.getDetail().getDeactivatedAt()).isNotNull();
    }
}
