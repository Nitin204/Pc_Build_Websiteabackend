package com.pcbuild.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcbuild.model.PreBuilt;
import com.pcbuild.repository.PreBuiltRepository;

@Service
public class PreBuiltService {

    @Autowired
    private PreBuiltRepository repository;

    public List<PreBuilt> getAll() {
        return repository.findAll();
    }

    public List<PreBuilt> getByCategory(String category) {
        return repository.findByCategory(category);
    }

    public PreBuilt create(PreBuilt preBuilt) {
        return repository.save(preBuilt);
    }

    public PreBuilt update(String id, PreBuilt preBuilt) {
        Optional<PreBuilt> existing = repository.findById(id);
        if(existing.isPresent()) {
            preBuilt.setId(id);
            return repository.save(preBuilt);
        }
        return null;
    }

    public boolean delete(String id) {
        Optional<PreBuilt> existing = repository.findById(id);
        if(existing.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
