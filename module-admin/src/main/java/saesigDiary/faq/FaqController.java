package saesigDiary.faq;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import saesigDiary.global.enumCode.EnumMapperFactory;
import saesigDiary.role.DataTablesResponseDto;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class FaqController {
    private final FaqService faqService;
    private final EnumMapperFactory enumFactory;


    @GetMapping("/admin/faqs/view")
    public String faqView() {
        return "faq/view";
    }

    @GetMapping("/admin/faqs/")
    @ResponseBody
    public Map<String, Object> findAll(HttpServletRequest request) {
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length = Integer.valueOf(request.getParameter("length"));
        Integer pageNum = start / length;

        PageRequest of = PageRequest.of(pageNum, length);
        DataTablesResponseDto dataTablesResponseDto = faqService.findAll(of);

        Map<String, Object> result = new HashMap<>();
        result.put("data", dataTablesResponseDto.getList());
        result.put("recordsTotal", dataTablesResponseDto.getRecordsTotal());
        result.put("recordsFiltered", dataTablesResponseDto.getRecordsFiltered());

        return result;
    }

    @GetMapping("/admin/faqs/{id}")
    @ResponseBody
    public FaqResponseDto findById(@PathVariable Long id) {
        return faqService.findById(id);
    }

    @GetMapping("/admin/faqs/{id}/form")
    public String findByIdView(@PathVariable(required = false) Optional<Long> id, Model model) {
        if(id.isPresent()) {
            FaqResponseDto byId = faqService.findById(id.get());
            model.addAttribute("faq", byId);
        } else {
            FaqResponseDto byId = new FaqResponseDto();
            model.addAttribute("faq", byId);
        }

        model.addAttribute("faqCategories", enumFactory.get("faqCategory"));
        return "faq/form";
    }
}
