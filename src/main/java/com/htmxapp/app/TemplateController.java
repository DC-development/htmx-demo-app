package com.htmxapp.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.lang.model.element.ModuleElement;

import static org.thymeleaf.util.StringUtils.split;

@Controller
public class TemplateController {

  @GetMapping("/")
  private String getTemplate(Model model) {
    String[] factorsOf24 = {"1", "2", "3", "4", "6", "12", "24", "25"};
    model.addAttribute("items", factorsOf24);
    model.addAttribute("title", "Checking out HTMX");
    model.addAttribute("template", "/components/list/list");
    return "master";
  }

  @GetMapping("/list")
  private String getList(Model model) {
    String[] factorsOf24 = {"Lisa", "Bine", "Dummy", "Puma", "Pumukkel", "Dada", "Gugu"};

    ListItem myitem1 = new ListItem("my title", 1);
    ListItem myitem2 = new ListItem("my title", 2);
    ListItem myitem3 = new ListItem("my title", 3);
    ListItem myitem4 = new ListItem("my title", 4);

    ListItem[] arrayOfItems = {myitem1, myitem2, myitem3, myitem4};

    model.addAttribute("items", factorsOf24);
    model.addAttribute("arrayOfItems", arrayOfItems);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "/components/list/list";
  }

  @PostMapping("/items")
  private String postItems(Model model, @RequestBody String list) {
    String[] itemStrings = split(list, "&");
    model.addAttribute("items", itemStrings);
    return "/components/list/list";
  }
}
