package com.youssef.flutterProjectBackend.service;

import com.youssef.flutterProjectBackend.enties.Tool;

import java.util.List;

public interface IToolService {

    public Tool createTool(Tool tool);
    public Tool findToolById(int id);
    public Tool updateTool(Tool tool);
    public List<Tool> findAllTools();
    public void deleteTool(Tool tool);
    public List<Tool> findAllByCategoryTitle(String categoryTitle);

    public Tool outOfServiceTool(int id);

    public Tool availableTool(int id);

    public List<Tool> findAllByUserEmail(String userEmail);

}
