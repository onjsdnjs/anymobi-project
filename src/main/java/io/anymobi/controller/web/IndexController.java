package io.anymobi.controller.web;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        //model.addAttribute("message", "You are logged in as onjsdnjs" + principal.getName());
        model.addAttribute("message", "You are logged in as onjsdnjs");
        logger.debug("test1");
        logger.error("test2");
        logger.warn("test3");
        return "index";
    }
}
