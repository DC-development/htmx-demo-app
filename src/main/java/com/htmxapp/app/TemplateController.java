package com.htmxapp.app;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.apache.tomcat.util.buf.Utf8Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.lang.model.element.ModuleElement;

import static org.thymeleaf.util.StringUtils.split;

@Controller
public class TemplateController {
  @Autowired
  private ListService listService;

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
    model.addAttribute("items", factorsOf24);

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
  @PostMapping("/item/new")
  public String createItem(Model model,@RequestBody ListItem item) {
    listService.createListItem(item);
    model.addAttribute("item", item);
    return "/components/list/item/item";
  }
}
