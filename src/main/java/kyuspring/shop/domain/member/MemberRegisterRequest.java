package kyuspring.shop.domain.member;

public record MemberRegisterRequest(
        String email,
        String nickname,
        String password
) {
}
