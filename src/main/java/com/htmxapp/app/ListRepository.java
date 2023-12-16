package com.htmxapp.app;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<ListItem, Long> {

}