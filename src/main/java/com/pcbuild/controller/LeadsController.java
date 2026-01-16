package com.pcbuild.controller;


import org.springframework.web.bind.annotation.*;

import com.pcbuild.model.Lead;
import com.pcbuild.service.LeadService;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
@CrossOrigin(origins = "http://localhost:5173")
public class LeadsController {

    private final LeadService service;

    public LeadsController(LeadService service) {
        this.service = service;
    }

    @GetMapping
    public List<Lead> getAllLeads() {
        return service.getAllLeads();
    }

    @PostMapping
    public Lead createLead(@RequestBody Lead lead) {
        return service.saveLead(lead);
    }

    @PutMapping("/{id}/contacted")
    public Lead markContacted(@PathVariable String id) {
        return service.markContacted(id);
    }
    @DeleteMapping("/{id}")
    public void deleteLead(@PathVariable String id) {
        service.deleteLead(id);
    }

    
}
