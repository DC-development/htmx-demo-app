package com.htmxapp.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.TimerTask;

@Controller
public class TemplateController {

    @GetMapping("/")
    private String getTemplate(Model model) {
        String[] factorsOf24 = { "1", "2", "3", "4", "6", "12", "24", "25" };
        model.addAttribute("items", factorsOf24);
        model.addAttribute("title", "Checking out layouts");
        model.addAttribute("template", "/components/test/test");
        return "master";
    }
    @GetMapping("/list")
    private String getList(Model model) {
        String[] factorsOf24 = { "Lisa", "Bine", "Dummy", "Puma", "Pumukkel", "Dada", "Gugu" };
        model.addAttribute("items", factorsOf24);
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
        }
        return "/components/test/test";
    }
}
