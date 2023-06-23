package saesigDiary.templateManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TemplateManageController {

    @Autowired
    private TemplateManageService templateManageService;

    @GetMapping({"/templateManage","/templateManage/templateManageList.html"})
    public String templateManageList() {
        return "/templateManage/templateManageList";
    }

    @GetMapping({"/templateManage/templateManageForm.html"})
    public String templateManageForm() {
        return "/templateManage/templateManageForm";
    }

    @PutMapping({"/templateManage/insertTemplate.do"})
    @ResponseBody
    public Map<String, Object> insertTemplate(TemplateManageDto tmd) throws Exception {
        // TODO: 2023-06-23 : need validation check, need user object
        Map<String, Object> resultMap = new HashMap<>();

        int retVal = 0;

//        retVal = templateManageService.insertTemplate(tmd);

        if (retVal > 0) {
//            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다.");
        } else {
//            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
        }

        return resultMap;
    }
}
