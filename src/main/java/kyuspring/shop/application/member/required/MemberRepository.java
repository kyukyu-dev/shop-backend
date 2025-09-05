package kyuspring.shop.application.member.required;

import kyuspring.shop.domain.member.Member;
import kyuspring.shop.domain.shared.Email;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface MemberRepository extends Repository<Member, Long> {
    Member save(Member member);

    Optional<Member> findByEmail(Email email);

    Optional<Member> findById(Long memberId);
}
