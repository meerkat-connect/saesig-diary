package saesigDiary.faq;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class FaqController {
    private final FaqService faqService;

    @GetMapping("/admin/faqs/view")
    public String faqView() {
        return "faq/view";
    }

    @GetMapping("/admin/faqs/")
    @ResponseBody
    public List<FaqResponseDto> findAll() {
        return faqService.findAll();
    }

}
