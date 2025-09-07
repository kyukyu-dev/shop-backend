package kyuspring.shop.application.member;

import jakarta.transaction.Transactional;
import kyuspring.shop.application.member.provided.MemberFinder;
import kyuspring.shop.application.member.required.MemberRepository;
import kyuspring.shop.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberQueryService implements MemberFinder {
    private final MemberRepository memberRepository;

    @Override
    public Member find(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow();
    }
}
