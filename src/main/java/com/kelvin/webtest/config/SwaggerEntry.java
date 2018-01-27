package com.kelvin.webtest.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Kelvin范显
 * @createDate 2018年01月27日
 */
@Controller
public class SwaggerEntry {

    @GetMapping("/")
    public String index() {
//        return "redirect:swagger-ui.html";
        return "redirect:swagger-ui.html";
    }
}
