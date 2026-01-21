package com.pcbuild.controller;

import com.pcbuild.model.CareerApplication;
import com.pcbuild.repository.CareerRepository;

import jakarta.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/careers")
@CrossOrigin(origins = "https://pc-build-website.vercel.app")
public class CareersController {

    private final CareerRepository repository;

    private final String UPLOAD_DIR = "uploads/resumes/";

    public CareersController(CareerRepository repository) {
        this.repository = repository;
        new File(UPLOAD_DIR).mkdirs(); // auto create folder
    }

    // ✅ GET ALL
    @GetMapping
    public List<CareerApplication> getAllCareers() {
        return repository.findAll();
    }

    // ✅ POST with resume upload
    @PostMapping(consumes = "multipart/form-data")
    public CareerApplication createCareer(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String description,
            @RequestParam MultipartFile resume
    ) throws IOException {

        String fileName = System.currentTimeMillis() + "_" + resume.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        Files.copy(resume.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        CareerApplication app = new CareerApplication();
        app.setName(name);
        app.setEmail(email);
        app.setPhone(phone);
        app.setDescription(description);
        app.setStatus("NEW");
        app.setAppliedOn(LocalDate.now().toString());
        app.setResumeData(resume.getBytes());
        app.setResumeName(resume.getOriginalFilename());
        app.setResumeType(resume.getContentType());

        return repository.save(app);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCareer(@PathVariable String id) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204
    }

    // ✅ DOWNLOAD RESUME
    @GetMapping("/{id}/resume")
    public ResponseEntity<byte[]> downloadResume(@PathVariable String id) {

        CareerApplication app = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        if (app.getResumeData() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header("Content-Disposition",
                        "attachment; filename=\"" + app.getResumeName() + "\"")
                .header("Content-Type", app.getResumeType())
                .body(app.getResumeData());
    }

}
