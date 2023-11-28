package com.saesig.api.adopt;

import com.saesig.api.util.Constants;
import com.saesig.config.auth.LoginMember;
import com.saesig.config.auth.SessionMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Adopt Controller", description = "분양관리 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping(Constants.CONTEXT_PATH+"/adopt")
public class AdoptController {
    private final AdoptService adoptService;

    @Operation(summary="분양글 등록", description = "입력한 정보를 이용하여 분양등록을 진행")
    @PostMapping("/insertAdopt")
    public Long insertAdopt(AdoptDto adoptDto, @LoginMember SessionMember member) throws Exception{
        adoptDto.setMember(member);
        return adoptService.insertAdopt(adoptDto);
    }

    @Operation(summary="분양글 검색", description = "입력한 정보를 이용하여 분양목록을 반환")
    @GetMapping("/selectAdoptList")
    public List<AdoptDto> getAdoptList(AdoptDto adoptDto) throws Exception{
        return adoptService.selectAdoptList(adoptDto);
    }

    @Operation(summary="분양글 수정", description = "입력한 정보를 이용하여 등록된 분양내용을 수정")
    @PostMapping("/updateAdopt")
    public Long updateAdopt(AdoptDto adoptDto, @LoginMember SessionMember member) throws Exception{
        adoptDto.setMember(member);
        return adoptService.updateAdopt(adoptDto);
    }

    @Operation(summary="분양글 삭제", description = "분양글 삭제")
    @DeleteMapping("/deleteAdopt")
    public Long deleteAdopt(AdoptDto adoptDto, @LoginMember SessionMember member) throws Exception{
        adoptDto.setMember(member);
        return adoptService.deleteAdopt(adoptDto);
    }

}
