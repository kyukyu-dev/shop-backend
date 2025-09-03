package kyuspring.shop.domain.member;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberTest {
    @Test
    void registerMember() {
        Member member = Member.register("test@email.com", "nickname", "hashedPassword");

        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
    }

    @Test
    void registerMemberNullCheck() {
        assertThatThrownBy(() -> Member.register(null, "nickname", "hashedPassword"))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    void deactivateMember() {
        Member member = Member.register("test@email.com", "nickname", "hashedPassword");

        member.deactivate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATED);
    }

    @Test
    void deactivateMemberFail() {
        Member member = Member.register("test@email.com", "nickname", "hashedPassword");

        member.deactivate();

        assertThatThrownBy(() -> member.deactivate())
            .isInstanceOf(IllegalStateException.class);
    }
}