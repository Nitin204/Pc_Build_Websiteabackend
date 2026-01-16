package com.pcbuild.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pcbuild.model.PCBuild;
import com.pcbuild.repository.PCBuildRepository;



@Service
public class PCBuildService {

    private final PCBuildRepository repo;

    public PCBuildService(PCBuildRepository repo) {
        this.repo = repo;
    }

    public PCBuild save(PCBuild pcBuild) {
        return repo.save(pcBuild);
    }

    public PCBuild update(String id, PCBuild pcBuild) {
        pcBuild.setId(id);
        return repo.save(pcBuild);
    }

    public List<PCBuild> getAll() {
        return repo.findAll();
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
