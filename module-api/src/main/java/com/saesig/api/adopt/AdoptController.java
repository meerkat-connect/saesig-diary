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
    @ResponseBody
    public Long insertAdopt(@RequestBody AdoptDto adoptDto, @LoginMember SessionMember Member) throws Exception{
        adoptDto.setMember(Member);
        return adoptService.insertAdopt(adoptDto);
    }

    @Operation(summary="분양글 검색", description = "입력한 정보를 이용하여 분양목록을 반환")
    @GetMapping("/selectAdoptList")
    public List<AdoptDto> getAdoptList(AdoptDto adoptDto, @LoginMember SessionMember member) throws Exception{
        return adoptService.selectAdoptList(adoptDto);
    }

    @Operation(summary="분양글 수정", description = "입력한 정보를 이용하여 등록된 분양내용을 수정")
    @PostMapping("/updateAdopt")
    @ResponseBody
    public Long updateAdopt(@RequestBody AdoptDto adoptDto, @LoginMember SessionMember member) throws Exception{
        adoptDto.setMember(member);
        return adoptService.updateAdopt(adoptDto);
    }

    @Operation(summary="분양글 삭제", description = "분양글 삭제")
    @DeleteMapping("/deleteAdopt")
    @ResponseBody
    public Long deleteAdopt(@RequestParam Long id, @LoginMember SessionMember member) throws Exception{
        AdoptDto adoptDto = new AdoptDto();
        adoptDto.setId(id);
        adoptDto.setMember(member);
        return adoptService.deleteAdopt(adoptDto);
    }

    @Operation(summary="분양글 좋아요", description = "분양글 좋아요 수정 정보")
    @PostMapping("/changLikeInfo")
    @ResponseBody
    public Long changeLikeInfo(@RequestBody AdoptDto adoptDto, @LoginMember SessionMember member) throws Exception{
        adoptDto.setMember(member);
        return adoptService.changeLikeInfo(adoptDto);
    }

    @Operation(summary="분양글 신고", description = "분양글 신고")
    @PostMapping("/reportAdoptPost")
    @ResponseBody
    public Long reportAdoptPost(@RequestBody AdoptReportDto adoptReportDto, @LoginMember SessionMember member) throws Exception{
        adoptReportDto.setMember(member);
        return adoptService.reportAdoptPost(adoptReportDto);
    }

}
