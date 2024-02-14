package com.saesig.api.adopt;

import com.saesig.api.util.Constants;
import com.saesig.config.auth.LoginMember;
import com.saesig.config.auth.SessionMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    public Long insertAdopt(@RequestBody AdoptRequestDto adoptRequestDto, @LoginMember SessionMember Member, HttpSession session) throws Exception{
        adoptRequestDto.setMember(Member);
        return adoptService.insertAdopt(adoptRequestDto);
    }

    @Operation(summary="분양글 검색", description = "입력한 정보를 이용하여 분양목록을 반환")
    @GetMapping("/selectAdoptList")
    public List<AdoptListResponseDto> getAdoptList(AdoptRequestDto adoptRequestDto, @LoginMember SessionMember member, HttpSession session) throws Exception{
        adoptRequestDto.setMember(member);
        return adoptService.selectAdoptList(adoptRequestDto);
    }

    @Operation(summary="분양글 상세", description = "분양 상세 페이지 데이터 전달")
    @GetMapping("/selectAdoptView")
    public AdoptViewResponseDto selectAdoptView(AdoptRequestDto adoptRequestDto, @LoginMember SessionMember member, HttpSession session) throws Exception{
        adoptRequestDto.setMember(member);
        return adoptService.selectAdoptView(adoptRequestDto);
    }

    @Operation(summary="분양글 수정", description = "입력한 정보를 이용하여 등록된 분양내용을 수정")
    @PostMapping("/updateAdopt")
    @ResponseBody
    public Long updateAdopt(@RequestBody AdoptRequestDto adoptRequestDto, @LoginMember SessionMember member) throws Exception{
        adoptRequestDto.setMember(member);
        return adoptService.updateAdopt(adoptRequestDto);
    }

    @Operation(summary="분양글 삭제", description = "분양글 삭제")
    @DeleteMapping("/deleteAdopt")
    @ResponseBody
    public Long deleteAdopt(@RequestParam Long id, @LoginMember SessionMember member) throws Exception{
        AdoptRequestDto adoptRequestDto = new AdoptRequestDto();
        adoptRequestDto.setId(id);
        adoptRequestDto.setMember(member);
        return adoptService.deleteAdopt(adoptRequestDto);
    }

    @Operation(summary="분양글 좋아요", description = "분양글 좋아요 수정 정보")
    @PostMapping("/changLikeInfo")
    @ResponseBody
    public Long changeLikeInfo(@RequestBody AdoptRequestDto adoptRequestDto, @LoginMember SessionMember member) throws Exception{
        adoptRequestDto.setMember(member);
        return adoptService.changeLikeInfo(adoptRequestDto);
    }

    @Operation(summary="분양글 신고", description = "분양글 신고")
    @PostMapping("/reportAdoptPost")
    @ResponseBody
    public Long reportAdoptPost(@RequestBody AdoptReportDto adoptReportDto, @LoginMember SessionMember member) throws Exception{
        adoptReportDto.setMember(member);
        return adoptService.reportAdoptPost(adoptReportDto);
    }

    @Operation(summary="품종 리스트 제공", description = "품종 리스트 제공")
    @GetMapping("/getBreedOptions")
    @ResponseBody
    public List<AdoptBreedListDto> getBreedOptions(AdoptBreedListDto adoptBreedListDto) throws Exception {
        List<AdoptBreedListDto> breedOptions = adoptService.getBreedOptions(adoptBreedListDto);
        return breedOptions;
    }

    @Operation(summary="백신 리스트 제공", description = "백신 리스트 제공")
    @GetMapping("/getVaccineList")
    @ResponseBody
    public List<AnimalVaccineListDto> getVaccineList(AnimalVaccineListDto animalVaccineListDto) throws Exception {
        List<AnimalVaccineListDto> VaccineList = adoptService.getVaccineList(animalVaccineListDto);
        return VaccineList;
    }

}
