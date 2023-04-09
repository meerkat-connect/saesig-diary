package saesigDiary;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExampleController {
    @GetMapping({"/example", "/example/index.html"})
    public String example() {
        return "example";
    }
}
