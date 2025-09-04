package kyuspring.shop.application.member.provided;

import kyuspring.shop.domain.member.Member;

/**
 * 회원을 조회한다
 */
public interface MemberFinder {
    Member find(Long memberId);
}
