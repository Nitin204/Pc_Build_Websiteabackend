package com.pcbuild.service;

import com.pcbuild.model.CareerApplication;
import com.pcbuild.repository.CareerRepository;
import org.springframework.stereotype.Service;

@Service
public class CareerService {

    private final CareerRepository careerRepository;

    public CareerService(CareerRepository careerRepository) {
        this.careerRepository = careerRepository;
    }

    public CareerApplication save(CareerApplication application) {
        return careerRepository.save(application);
    }
}
