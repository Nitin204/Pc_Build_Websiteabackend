package com.pcbuild.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcbuild.model.PCBuild;
import com.pcbuild.service.PCBuildService;

@RestController
@CrossOrigin(origins = "http://localhost:5174")
@RequestMapping("/api/pcbuilds")
public class PCBuildController {

    private final PCBuildService service;

    public PCBuildController(PCBuildService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public PCBuild create(@RequestBody PCBuild pcBuild) {
        return service.save(pcBuild);
    }

    // UPDATE
    @PutMapping("/{id}")
    public PCBuild update(@PathVariable String id, @RequestBody PCBuild pcBuild) {
        return service.update(id, pcBuild);
    }

    // GET ALL
    @GetMapping
    public List<PCBuild> getAll() {
        return service.getAll();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}