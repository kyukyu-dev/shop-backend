package kyuspring.shop.application.member.provided;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import kyuspring.shop.ShopTestConfiguration;
import kyuspring.shop.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static kyuspring.shop.domain.member.MemberFixture.createMemberRegisterRequest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Import(ShopTestConfiguration.class)
record MemberFinderTest(EntityManager entityManager, MemberFinder memberFinder, MemberRegister memberRegister) {
    @Test
    void find() {
        Member member = memberRegister.register(createMemberRegisterRequest());
        entityManager.flush();
        entityManager.clear();

        Member found = memberFinder.find(member.getId());

        assertThat(found.getId()).isEqualTo(member.getId());
    }
}