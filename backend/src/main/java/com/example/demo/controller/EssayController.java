package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.AIrequest;
import com.example.demo.dto.AIresponse;
import com.example.demo.service.EssayService;

import com.example.demo.entity.Essay;
import com.example.demo.repository.EssayRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/essay")
@RequiredArgsConstructor
public class EssayController {
    private final EssayRepository essayRepository;
    private final EssayService essayService;
    @PostMapping("/create")
    public ResponseEntity<?> addEssay(@RequestBody Essay essay) {
        essayRepository.createEssay(
                essay.getTitle(),
                essay.getContent(),
                essay.getScore(),
                essay.getFeedback());
        return ResponseEntity.ok("Essay created successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> editEssay(@PathVariable long id, @RequestBody Essay essay) {
        essayRepository.updateEssay(
                id,
                essay.getTitle(),
                essay.getContent(),
                essay.getScore(),
                essay.getFeedback());
        return ResponseEntity.ok("Essay updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delEssay(@PathVariable long id) {
        essayRepository.deleteEssay(id);
        return ResponseEntity.ok("Essay deleted successfully");
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(essayRepository.getAllEssay());

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return ResponseEntity.ok(essayRepository.getEssayById(id));
    }

    @PostMapping("/score/{id}")
    public ResponseEntity<?> scoreEssay(@PathVariable Long id) {
        Essay scoredEssay = essayService.scoreEssay(id);
        return ResponseEntity.ok(scoredEssay);
    }   
}
