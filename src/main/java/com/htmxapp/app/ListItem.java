package com.htmxapp.app;

import jakarta.persistence.*;

@Entity
@Table(name = "listItems")
public class ListItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String email;
  private int ordinary = -1;

  // Constructors, Getters, and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getOrdinary() {
    return ordinary;
  }

  public void setOrdinary(int ordinary) {
    this.ordinary = ordinary;
  }
}

