package kyuspring.shop.domain.member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;
import org.springframework.util.Assert;

import java.util.Objects;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String email;

    private String nickname;

    private String passwordHash;

    private MemberStatus status;

    public static Member register(String email, String nickname, String passwordHash) {
        Member member = new Member();

        member.email = Objects.requireNonNull(email);
        member.nickname = Objects.requireNonNull(nickname);
        member.passwordHash = Objects.requireNonNull(passwordHash);

        member.status = MemberStatus.ACTIVE;

        return member;
    }

    public void deactivate() {
        Assert.state(this.status == MemberStatus.ACTIVE, "ACTIVE 상태가 아닙니다");

        this.status = MemberStatus.DEACTIVATED;
    }
}
