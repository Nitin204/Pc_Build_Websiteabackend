package com.pcbuild.service;


import org.springframework.stereotype.Service;

import com.pcbuild.model.Lead;
import com.pcbuild.repository.LeadRepository;

import java.util.List;

@Service
public class LeadService {

    private final LeadRepository repository;

    public LeadService(LeadRepository repository) {
        this.repository = repository;
    }

    public List<Lead> getAllLeads() {
        return repository.findAll();
    }

    public Lead saveLead(Lead lead) {
        lead.setStatus("PENDING");
        return repository.save(lead);
    }

    public Lead markContacted(String id) {
        Lead lead = repository.findById(id).orElseThrow();
        lead.setStatus("CONTACTED");
        return repository.save(lead);
    }
    public void deleteLead(String id) {
        repository.deleteById(id);
    }

}
