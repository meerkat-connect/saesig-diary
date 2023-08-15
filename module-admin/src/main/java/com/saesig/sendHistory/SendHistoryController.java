package com.saesig.sendHistory;

import com.saesig.global.enumCode.EnumMapperFactory;
import com.saesig.templateManage.TemplateManageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class SendHistoryController {

    private final SendHistoryService sendHistoryService;

    private final EnumMapperFactory enumMapperFactory;

    @GetMapping({"/sendHistory","/sendHistory/sendHistoryList.html"})
    public String sendHistoryList(TemplateManageDto tmd, Model model) throws Exception {

        return "/sendHistory/sendHistoryList";
    }
}
