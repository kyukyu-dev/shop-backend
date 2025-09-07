package kyuspring.shop.adapter.webapi;

import jakarta.validation.Valid;
import kyuspring.shop.adapter.webapi.dto.MemberRegisterResponse;
import kyuspring.shop.application.member.provided.MemberRegister;
import kyuspring.shop.domain.member.Member;
import kyuspring.shop.domain.member.MemberRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApi {
    private final MemberRegister memberRegister;

    @PostMapping("/api/members")
    public MemberRegisterResponse register(@RequestBody @Valid MemberRegisterRequest request) {
        Member member = memberRegister.register(request);

        return MemberRegisterResponse.of(member);
    }
}
