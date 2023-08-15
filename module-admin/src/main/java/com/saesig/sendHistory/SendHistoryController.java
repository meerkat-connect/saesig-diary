package com.saesig.sendHistory;

import com.saesig.global.enumCode.EnumMapperFactory;
import com.saesig.templateManage.TemplateManageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class SendHistoryController {

    private final SendHistoryService sendHistoryService;

    private final EnumMapperFactory enumMapperFactory;

    @GetMapping({"/sendHistory","/sendHistory/sendHistoryList.html"})
    public String sendHistoryList(TemplateManageDto tmd, Model model) throws Exception {

        return "/sendHistory/sendHistoryList";
    }

    @GetMapping("/sendHistory/selectSendHistoryList.do")
    @ResponseBody
    public Map<String, Object> selectSendHistoryList(SendHistoryDto shd) throws Exception {
        Map<String, Object> result = new HashMap<>();

        List<SendHistoryDto> list = sendHistoryService.selectSendHistoryList(shd);
        result.put("data", list);

        return result;
    }
}
