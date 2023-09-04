package com.saesig.managerBoard;

import com.saesig.common.mybatis.DataTablesDto;
import com.saesig.config.auth.LoginMember;
import com.saesig.config.auth.SessionMember;
import com.saesig.domain.common.Constant;
import com.saesig.global.enumCode.EnumMapperFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/managerBoard")
public class ManagerBoardController {

    private final ManagerBoardService managerBoardService;

    private final EnumMapperFactory enumMapperFactory;


    @GetMapping({"view"})
    public String managerNoticeBoardList(Model model) throws Exception {
        model.addAttribute("searchSendMethod", enumMapperFactory.get("sendMethod"));
        model.addAttribute("searchSendCategory", enumMapperFactory.get("sendCategory"));

        return "/managerBoard/view";
    }

    @GetMapping("selectManagerBoardList.do")
    @ResponseBody
    public DataTablesDto selectManagerNoticeBoardList(ManagerBoardDto mnbd) throws Exception {
        DataTablesDto dtd = new DataTablesDto();

        List<ManagerBoardDto> list = managerBoardService.selectManagerBoardList(mnbd);

        dtd.setDraw(mnbd.getDraw());
        dtd.setData(list);
        if(list.size() == 0) {
            dtd.setRecordsFiltered(0);
            dtd.setRecordsTotal(0);
        }else {
            dtd.setRecordsFiltered(list.get(0).getRecordsTotal());
            dtd.setRecordsTotal(list.get(0).getRecordsTotal());
        }

        return dtd;
    }

    @GetMapping("form")
    public String managerNoticeBoardForm(Long id, Model model) throws Exception {
        model.addAttribute("searchSendMethod", enumMapperFactory.get("sendMethod"));
        model.addAttribute("searchSendCategory", enumMapperFactory.get("sendCategory"));

        if (!Objects.isNull(id)){
            ManagerBoardDto managerNoticeBoard = managerBoardService.selectManagerBoard(id);
            model.addAttribute("managerNoticeBoard", managerNoticeBoard);

            return "/managerBoard/form";
        }else {
            ManagerBoardDto managerNoticeBoard = new ManagerBoardDto();
            model.addAttribute("managerNoticeBoard", managerNoticeBoard);

            return "/managerBoard/form";
        }
    }

    @PostMapping("insertForm.do")
    @ResponseBody
    public Map<String, Object> insertForm(@LoginMember SessionMember member, ManagerBoardDto mnbd) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        mnbd.setMember(member);
        int retVal = 0;
        retVal = managerBoardService.insertForm(mnbd);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "저장에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패했습니다.");
        }

        return resultMap;
    }

    @PostMapping("updateForm.do")
    @ResponseBody
    public Map<String, Object> updateForm(@LoginMember SessionMember member, ManagerBoardDto mnbd) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        mnbd.setMember(member);
        int retVal = 0;
        retVal = managerBoardService.updateForm(mnbd);
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }

        return resultMap;
    }

    @DeleteMapping("deleteItems.do")
    @ResponseBody
    public Map<String, Object> deleteItems(@RequestParam Long[] ids) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        int retVal = 0;
        retVal = managerBoardService.deleteItems(ids);
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "삭제에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "삭제에 실패했습니다.");
        }

        return resultMap;
    }
}
