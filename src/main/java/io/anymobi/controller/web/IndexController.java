package io.anymobi.controller.web;

import java.security.Principal;

import io.anymobi.common.CommonLogger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController extends CommonLogger<IndexController> {

    @GetMapping("/")
    public String index(Model model, Principal principal) {

        //model.addAttribute("message", "You are logged in as onjsdnjs" + principal.getName());

        model.addAttribute("message", "You are logged in as onjsdnjs");
        logger.info("Hello World!!");

        return "index";
    }
}
