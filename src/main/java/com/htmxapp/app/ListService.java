package com.htmxapp.app;

import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListService {
  @Autowired
  private ListRepository ListRepository;

  // Create a new item
  public ListItem createListItem(ListItem item) {
    return ListRepository.save(item);
  }

  // Get all items
  public List<ListItem> getAllListItems() {
    return ListRepository.findAll(Sort.by("ordinary"));
  }

  // Get item by ID
  public Optional<ListItem> getListItemById(Long id) {
    return ListRepository.findById(id);
  }

  // Update item
  public ListItem updateListItem(Long id, ListItem itemDetails) {
    Optional<ListItem> item = ListRepository.findById(id);
    if (item.isPresent()) {
      ListItem existingListItem = item.get();
      existingListItem.setName(itemDetails.getName());
      existingListItem.setEmail(itemDetails.getEmail());
      existingListItem.setOrdinary(itemDetails.getOrdinary());
      return ListRepository.save(existingListItem);
    }
    return null;
  }

  // Delete all items
  public void deleteAllListItems() {
    ListRepository.deleteAll();
  }

  // Delete item
  public void deleteListItem(Long id) {
    ListRepository.deleteById(id);
  }

  // Other business logic related to items
}
