package com.saesig.sendHistory;

import com.saesig.common.mybatis.DataTablesDto;
import com.saesig.global.enumCode.EnumMapperFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/sendHistory")
public class SendHistoryController {

    private final SendHistoryService sendHistoryService;

    private final EnumMapperFactory enumMapperFactory;

    @GetMapping("view")
    public String sendHistoryList(Model model) throws Exception {
        model.addAttribute("searchSendMethod", enumMapperFactory.get("sendMethod"));
        model.addAttribute("searchSendCategory", enumMapperFactory.get("sendCategory"));

        return "sendHistory/view";
    }

    @GetMapping("selectSendHistoryList.do")
    @ResponseBody
    public DataTablesDto selectSendHistoryList(SendHistoryDto shd) throws Exception {
        DataTablesDto dtd = new DataTablesDto();

        List<SendHistoryDto> list = sendHistoryService.selectSendHistoryList(shd);

        dtd.setDraw(shd.getDraw());
        dtd.setData(list);
        if(list.isEmpty()) {
            dtd.setRecordsFiltered(0);
            dtd.setRecordsTotal(0);
        }else {
            dtd.setRecordsFiltered(list.get(0).getRecordsTotal());
            dtd.setRecordsTotal(list.get(0).getRecordsTotal());
        }

        return dtd;
    }

    @GetMapping("sendHistoryForm.html")
    public String templateManageForm(Long id, Model model) throws Exception {
        model.addAttribute("searchSendMethod", enumMapperFactory.get("sendMethod"));
        model.addAttribute("searchSendCategory", enumMapperFactory.get("sendCategory"));

        SendHistoryDto sendHistory = sendHistoryService.selectSendHistory(id);
        model.addAttribute("sendHistory", sendHistory);

        return "sendHistory/form";
    }
}
