package com.youssef.flutterProjectBackend.controller;

import com.youssef.flutterProjectBackend.enties.Tool;
import com.youssef.flutterProjectBackend.models.ResponseModel;
import com.youssef.flutterProjectBackend.service.IToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tool")
public class ToolController {
        @Autowired
        private IToolService iToolService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseModel<List<Tool>> getAllTools() {
        return new ResponseModel<>(HttpStatus.OK.value(), "all the Tools",iToolService.findAllTools());
    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('Provider') or hasAuthority('ADMIN')")
    @GetMapping("/category/{categoryTitle}")
    public ResponseModel<List<Tool>> getAllToolsByCategory(@PathVariable String categoryTitle){
        return new ResponseModel<>(HttpStatus.OK.value(), "all tools by "+categoryTitle,iToolService.findAllByCategoryTitle(categoryTitle));
    }
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("{id}")
    public ResponseModel<Tool> getToolById(@PathVariable int id){
        return new ResponseModel<>(HttpStatus.OK.value(), "the tool with id "+id,iToolService.findToolById(id));
    }
    @PreAuthorize("hasAuthority('Provider')")
    @PostMapping
    public ResponseModel<Tool> addNewTool(@RequestBody Tool tool){
        return new ResponseModel<>(HttpStatus.OK.value(), "the new tool is added successfully",iToolService.createTool(tool));
    }
    @PreAuthorize("hasAuthority('Provider')")
    @PutMapping
    public ResponseModel<Tool> updateTool(@RequestBody Tool tool){
        Tool updateTool=iToolService.findToolById(tool.getId());
        updateTool.setTitle(tool.getTitle());
        updateTool.setCategory(tool.getCategory());
        updateTool.setStatus(tool.getStatus());
        updateTool.setImageUrl(tool.getImageUrl());
        updateTool.setSmallDescription(tool.getSmallDescription());
        updateTool.setRentPriceParHour(tool.getRentPriceParHour());
       // updateTool.setUsers(tool.getUsers());
        return new ResponseModel<>(HttpStatus.OK.value(), "updated the tool data",iToolService.updateTool(tool));
    }
    @PreAuthorize("hasAuthority('Provider') or hasAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseModel<Tool> deleteTool(@PathVariable int id){
        Tool tool=iToolService.findToolById(id);
        iToolService.deleteTool(tool);
        return new ResponseModel<>(HttpStatus.OK.value(), "deleted tool successfully",tool);
    }
    @PreAuthorize("hasAuthority('Provider')")
    @PostMapping("/out-of-service/{id}")
    public ResponseModel<Tool> outOfServiceTool(@PathVariable int id){
        return new ResponseModel<>(HttpStatus.OK.value(), "change the status of tool to out of service",iToolService.outOfServiceTool(id));
    }
    @PreAuthorize("hasAuthority('Provider')")
    @PostMapping("/available/{id}")
    public ResponseModel<Tool> available(@PathVariable int id){
        return new ResponseModel<>(HttpStatus.OK.value(), "change the status of tool to out of service",iToolService.outOfServiceTool(id));
    }
    @PreAuthorize("hasAuthority('Provider')")
    @GetMapping("/tools-by-email/{email}")
    public ResponseModel<List<Tool>> getToolsByUserEmail(@PathVariable String email){
        return new ResponseModel<>(HttpStatus.OK.value(),"get all the tools by user_email",iToolService.findAllByUserEmail(email));
    }
}
