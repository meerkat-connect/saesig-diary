package com.saesig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExampleController {
    @GetMapping({"/example", "/example/index.html"})
    public String example() {
        return "example";
    }

    @GetMapping("/tabExample")
    public String tabExample() {
        return "tabExample";
    }

    @GetMapping("/oldTabExample")
    public String oldTabExample() {
        return "oldTabExample";
    }

}
