package com.htmxapp.app;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.tools.jconsole.JConsolePlugin;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.apache.tomcat.util.buf.Utf8Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.lang.model.element.ModuleElement;

import java.util.List;
import java.util.Map;

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

    JSONObject jsonObject = (JSONObject) JSONValue.parse(list);
    JSONArray data = (JSONArray) jsonObject.get("item");
    //String[] items = (String) data.get("item");
    System.out.println("items= " + data);
    model.addAttribute("items", data);
    return "/components/sortable-list/sortable-list";
  }
  @PostMapping("/item/new")
  public String createItem(Model model,@RequestBody ListItem item) {
    listService.createListItem(item);
    model.addAttribute("item", item);
    return "/components/item/item-form";
  }
}

