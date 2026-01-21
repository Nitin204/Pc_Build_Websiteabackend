package com.pcbuild.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pcbuild.model.PreBuilt;
import com.pcbuild.service.PreBuiltService;

@CrossOrigin(origins = "https://pc-build-websiteadmin.vercel.app")
@RestController
@RequestMapping("/api/prebuilts")
public class PreBuiltController {

    @Autowired
    private PreBuiltService service;

    @GetMapping
    public List<PreBuilt> getAll() {
        return service.getAll();
    }

    @GetMapping("/category/{category}")
    public List<PreBuilt> getByCategory(@PathVariable String category) {
        return service.getByCategory(category);
    }

    @PostMapping
    public PreBuilt create(@RequestBody PreBuilt preBuilt) {
        return service.create(preBuilt);
    }

    @PutMapping("/{id}")
    public PreBuilt update(@PathVariable String id, @RequestBody PreBuilt preBuilt) {
        return service.update(id, preBuilt);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        boolean deleted = service.delete(id);
        return deleted ? "Deleted Successfully" : "Build Not Found";
    }
}
