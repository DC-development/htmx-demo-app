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
public class ListItemController {
  @Autowired
  private ListService listService;

  @GetMapping("/items")
  private String getItems(Model model) {
    List<ListItem> arrayOfItems = this.listService.getAllListItems();
    model.addAttribute("items", arrayOfItems);
    return "/components/sortable-list/sortable-list";
  }
  @PostMapping("/items/reorder")
  private String reorderListItems(Model model, @RequestBody String list) {

    JSONArray arrayOfIds = this.extractArray("item", list);

    if(arrayOfIds != null) {
      for(int i = 0; i < arrayOfIds.size(); i++) {
        Optional<ListItem> fetchedItem = this.listService.getListItemById(Long.valueOf((String) arrayOfIds.get(i)));
        ListItem updatedItem = new ListItem();
        updatedItem.setOrdinary(i);
        updatedItem.setName(fetchedItem.get().getName());
        updatedItem.setId(fetchedItem.get().getId());
        updatedItem.setEmail(fetchedItem.get().getEmail());
        this.listService.updateListItem(fetchedItem.get().getId(), updatedItem);
      }
    }

    List<ListItem> arrayOfItems = this.listService.getAllListItems();
    model.addAttribute("items", arrayOfItems);

    return "/components/sortable-list/sortable-list";
  }
  @PutMapping("/item/new")
  public String createItem(
      Model model,
      @RequestBody ListItem item,
      HttpServletResponse response
  ) {

    ListItem highestOrderItem = !this.listService.getAllListItems().isEmpty() ? this.listService.getAllListItems().getLast() : new ListItem();

    item.setOrdinary(highestOrderItem.getOrdinary()+1);
    ListItem newItem = listService.createListItem(item);
    response.setHeader("HX-Trigger", "new-item-trigger");

    model.addAttribute("item", newItem);
    model.addAttribute("isClone", false);
    return "/components/item/item";
  }
  @PostMapping("/item/update/{id}")
  public ResponseEntity<Void> updateItem(
      Model model,
      @RequestBody String list,
      @PathVariable int id
  ) {
    JSONObject jsonObject = (JSONObject) JSONValue.parse(list);
    String newName = (String) jsonObject.get("name-"+id);
    String newEmail = (String) jsonObject.get("email-"+id);
    Optional<ListItem> oldItem = this.listService.getListItemById((long) id);
    ListItem updateItem = new ListItem();
    updateItem.setName(newName);
    updateItem.setEmail(newEmail);
    updateItem.setOrdinary(oldItem.get().getOrdinary());
    this.listService.updateListItem((long) id, updateItem);

    updateItem.setId((long) id);
    model.addAttribute("item", updateItem);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  @DeleteMapping("/item/delete/{id}")
  public String deleteItem(
      HttpServletResponse response,
      @PathVariable int id,
      Model model,
      @RequestBody String list
  ) {
    listService.deleteListItem((long) id);
    JSONObject jsonObject = (JSONObject) JSONValue.parse(list);
    String newName = (String) jsonObject.get("name-"+id);
    String newEmail = (String) jsonObject.get("email-"+id);
    ListItem listItem = new ListItem();
    listItem.setId((long) id);
    listItem.setEmail(newEmail);
    listItem.setName(newName);
    model.addAttribute("item", listItem);
    model.addAttribute("dropped", true);
    model.addAttribute("isClone", false);// TODO: have default fragment-values
    response.setHeader("HX-Trigger", "item-removed-trigger");
    return "components/item/item";
  }

  private JSONArray extractArray(String jsonKeyString, String jsonStr) {
    JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonStr);
    return (JSONArray) jsonObject.get(jsonKeyString);
  }
}
