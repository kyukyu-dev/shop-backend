package kyuspring.shop.application.member.provided;

import jakarta.validation.Valid;
import kyuspring.shop.domain.member.Member;
import kyuspring.shop.domain.member.MemberRegisterRequest;

/**
 * 회원 등록과 관련된 기능 제공
 */
public interface MemberRegister {
    Member register(@Valid MemberRegisterRequest registerRequest);

    Member activate(Long memberId);

    Member deactivate(Long memberId);
}
