package kyuspring.shop.domain.member;

import jakarta.persistence.Entity;
import kyuspring.shop.domain.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

import static org.springframework.util.Assert.isTrue;

@Entity
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class MemberDetail extends AbstractEntity {
    private LocalDateTime registeredAt;

    private LocalDateTime activatedAt;

    private LocalDateTime deactivatedAt;

    static MemberDetail create() {
        MemberDetail memberDetail = new MemberDetail();
        memberDetail.registeredAt = LocalDateTime.now();
        return memberDetail;
    }

    void activate() {
        isTrue(activatedAt == null, "이미 활성화 되었습니다.");
        this.activatedAt = LocalDateTime.now();
    }

    void deactivate() {
        isTrue(deactivatedAt == null, "이미 비활성화 되었습니다.");
        this.deactivatedAt = LocalDateTime.now();
    }
}
