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

import java.util.List;

import static org.thymeleaf.util.StringUtils.split;

@Controller
public class TemplateController {
  @Autowired
  private ListService listService;

  @GetMapping("/")
  private String getTemplate(Model model) {

    List<ListItem> arrayOfItems = this.listService.getAllListItems();
    model.addAttribute("items", arrayOfItems);
    model.addAttribute("title", "Checking out HTMX");
    model.addAttribute("template", "/components/sortable-list/sortable-list");
    return "master";
  }

  @GetMapping("/list")
  private String getList(Model model) {
    List<ListItem> arrayOfItems = this.listService.getAllListItems();
    model.addAttribute("items", arrayOfItems);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "/components/sortable-list/sortable-list";
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
