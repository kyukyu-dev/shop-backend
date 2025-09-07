package kyuspring.shop.domain.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static kyuspring.shop.domain.member.MemberFixture.createMemberRegisterRequest;
import static kyuspring.shop.domain.member.MemberFixture.createPasswordEncoder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberTest {
    Member member;
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        passwordEncoder = createPasswordEncoder();

        member = Member.register(
                createMemberRegisterRequest(),
                passwordEncoder
        );
    }

    @Test
    void registerMember() {
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
        assertThat(member.getDetail().getRegisteredAt()).isNotNull();
    }

    @Test
    void activateMember() {
        assertThat(member.isActive()).isFalse();
        assertThat(member.getDetail().getActivatedAt()).isNull();

        member.activate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
        assertThat(member.isActive()).isTrue();
        assertThat(member.getDetail().getActivatedAt()).isNotNull();
    }

    @Test
    void activateMemberFail() {
        member.activate();

        assertThatThrownBy(() -> member.activate())
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void deactivateMember() {
        assertThat(member.isActive()).isFalse();

        member.activate();

        assertThat(member.isActive()).isTrue();
        assertThat(member.getDetail().getDeactivatedAt()).isNull();

        member.deactivate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATED);
        assertThat(member.isActive()).isFalse();
        assertThat(member.getDetail().getDeactivatedAt()).isNotNull();
    }

    @Test
    void deactivateMemberFail() {
        member.activate();

        member.deactivate();

        assertThatThrownBy(() -> member.deactivate())
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void verifyPassword() {
        assertThat(member.verifyPassword("password", passwordEncoder)).isTrue();
        assertThat(member.verifyPassword("wrongPassword", passwordEncoder)).isFalse();
    }

    @Test
    void changeNickname() {
        assertThat(member.getNickname()).isEqualTo("nickname");

        member.changeNickname("newNickname");

        assertThat(member.getNickname()).isEqualTo("newNickname");
    }

    @Test
    void changePassword() {
        assertThat(member.verifyPassword("password", passwordEncoder)).isTrue();

        member.changePassword("newPassword", passwordEncoder);

        assertThat(member.verifyPassword("newPassword", passwordEncoder)).isTrue();
    }

    @Test
    void invalidEmail() {
        assertThatThrownBy(() ->
                Member.register(
                        createMemberRegisterRequest("invalid email"),
                        passwordEncoder
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }
}