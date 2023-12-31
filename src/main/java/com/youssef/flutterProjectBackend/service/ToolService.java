package com.youssef.flutterProjectBackend.service;

import com.youssef.flutterProjectBackend.enties.Tool;
import com.youssef.flutterProjectBackend.enties.User;
import com.youssef.flutterProjectBackend.handler.ReservationApiException;
import com.youssef.flutterProjectBackend.models.Status;
import com.youssef.flutterProjectBackend.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ToolService implements IToolService{

    @Autowired
    private ToolRepository toolRepository;
    @Override
    public Tool createTool(Tool tool) {
        return toolRepository.save(tool);
    }

    @Override
    public Tool findToolById(int id) {
        return toolRepository.findById(id).get();
    }

    @Override
    public Tool updateTool(Tool tool) {
        return toolRepository.save(tool);
    }

    @Override
    public List<Tool> findAllTools() {
        return toolRepository.findAll();
    }

    @Override
    public void deleteTool(Tool tool) {
           toolRepository.delete(tool);
    }

    @Override
    public List<Tool> findAllByCategoryTitle(String categoryTitle) {
        return toolRepository.findAllByCategoryTitle(categoryTitle).orElseThrow(()->new ReservationApiException(HttpStatus.NOT_FOUND,"there is not tools with this category"));
    }

    @Override
    public Tool outOfServiceTool(int id) {
        Tool tool=toolRepository.findById(id).get();
        tool.setStatus(Status.OUT_OF_SERVICE);
        return tool;
    }

    @Override
    public Tool availableTool(int id) {
        Tool tool=toolRepository.findById(id).get();
        tool.setStatus(Status.AVAILABLE);
        return tool;
    }

    @Override
    public List<Tool> findAllByUserEmail(String userEmail) {

        return toolRepository.findAllByUserEmail(userEmail).orElseThrow(()->new ReservationApiException(HttpStatus.NOT_FOUND,"no tools for this provider"));
    }


}
