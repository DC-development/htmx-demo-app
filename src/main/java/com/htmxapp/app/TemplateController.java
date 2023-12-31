package com.htmxapp.app;

import jakarta.servlet.http.HttpServletResponse;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
public class TemplateController {
  @Autowired
  private ListService listService;

  @GetMapping("/")
  private String getTemplate(Model model) {
    List<ListItem> arrayOfItems = this.listService.getAllListItems();
    model.addAttribute("items", arrayOfItems);
    model.addAttribute("title", "HTMX-App");
    model.addAttribute("template", "/components/sortable-list/sortable-list");
    return "master";
  }
  @GetMapping("/sortablelist")
  private String getSortableList(Model model) {
    List<ListItem> arrayOfItems = this.listService.getAllListItems();
    model.addAttribute("items", arrayOfItems);
    return "/components/sortable-list/sortable-list";
  }
  @GetMapping("/list")
  private String getList(Model model) {
    List<ListItem> arrayOfItems = this.listService.getAllListItems();
    model.addAttribute("items", arrayOfItems);
    return "/components/sortable-list/list";
  }
  @GetMapping("/items")
  private String getItems(Model model) {
    List<ListItem> arrayOfItems = this.listService.getAllListItems();
    model.addAttribute("items", arrayOfItems);
    return "/components/sortable-list/sortable-list";
  }
  @PostMapping("/items")
  private String postItems(Model model, @RequestBody String list) {

    JSONArray arrayOfIds = this.extractArray("item", list);

    for(int i = 0; i < arrayOfIds.size(); i++) {
      Optional<ListItem> fetchedItem = this.listService.getListItemById(Long.valueOf((String) arrayOfIds.get(i)));
      ListItem updatedItem = new ListItem();
      updatedItem.setOrdinary(i);
      updatedItem.setName(fetchedItem.get().getName());
      updatedItem.setId(fetchedItem.get().getId());
      updatedItem.setEmail(fetchedItem.get().getEmail());
      this.listService.updateListItem(fetchedItem.get().getId(), updatedItem);
    }
    List<ListItem> arrayOfItems = this.listService.getAllListItems();
    model.addAttribute("items", arrayOfItems);

    return "/components/sortable-list/sortable-list";
  }
  @PostMapping("/item/new")
  public String createItem(Model model,@RequestBody ListItem item, HttpServletResponse response) {
    model.addAttribute("item", item);
    listService.createListItem(item);
    response.setHeader("HX-Trigger", "new-item-trigger");

    return "/components/item/item-form";
  }
  @PostMapping("/item/update/{id}")
  public ResponseEntity<Void> updateItem(Model model, @RequestBody String list, @PathVariable int id) {
    JSONObject jsonObject = (JSONObject) JSONValue.parse(list);
    String newName = (String) jsonObject.get("name-"+id);
    String newEmail = (String) jsonObject.get("email-"+id);

    ListItem updateItem = new ListItem();
    updateItem.setName(newName);
    updateItem.setEmail(newEmail);
    this.listService.updateListItem((long) id, updateItem);

    updateItem.setId((long) id);
    model.addAttribute("item", updateItem);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  @DeleteMapping("/item/delete/{id}")
  public String deleteItem(HttpServletResponse response, @PathVariable int id, Model model, @RequestBody String list) {

    listService.deleteListItem((long) id);
    JSONObject jsonObject = (JSONObject) JSONValue.parse(list);
    String newName = (String) jsonObject.get("name-"+id);
    String newEmail = (String) jsonObject.get("email-"+id);
    ListItem listItem = new ListItem();
    listItem.setId((long) id);
    listItem.setEmail(newEmail);
    listItem.setName(newName);
    model.addAttribute("item", listItem);
    response.setHeader("HX-Trigger", "item-removed-trigger");
    return "components/item/item";
  }
  private JSONArray extractArray(String jsonKeyString, String jsonStr) {
    JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonStr);
    return (JSONArray) jsonObject.get(jsonKeyString);
  }
}

