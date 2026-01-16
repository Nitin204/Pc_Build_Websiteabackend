package com.pcbuild.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pcbuild.model.Address;
import com.pcbuild.repository.AddressRepository;

@Service
public class AddressService {

    private final AddressRepository repo;

    public AddressService(AddressRepository repo) {
        this.repo = repo;
    }

    public Address save(Address address) {
        return repo.save(address);
    }

    public List<Address> getUserAddresses(String userId) {
        return repo.findByUserId(userId);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
