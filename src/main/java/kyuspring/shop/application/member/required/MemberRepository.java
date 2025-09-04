package kyuspring.shop.application.member.required;

import kyuspring.shop.domain.member.Email;
import kyuspring.shop.domain.member.Member;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface MemberRepository extends Repository<Member, Long> {
    Member save(Member member);

    Optional<Member> findByEmail(Email email);

    Optional<Member> findById(Long memberId);
}
