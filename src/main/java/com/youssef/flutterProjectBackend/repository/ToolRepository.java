package com.youssef.flutterProjectBackend.repository;

import com.youssef.flutterProjectBackend.enties.Tool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ToolRepository extends JpaRepository<Tool,Integer> {
    public Optional<List<Tool>> findAllByCategoryTitle(String categoryTitle);
    public Optional<List<Tool>> findAllByUserEmail(String userEmail);
}
