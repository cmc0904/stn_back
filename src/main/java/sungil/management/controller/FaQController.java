package sungil.management.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sungil.management.domain.FaQ;
import sungil.management.domain.Result;
import sungil.management.service.faq.FaQService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/faq")
public class FaQController {
    private final FaQService faQService;

    @Autowired
    public FaQController(FaQService faQService) {
        this.faQService = faQService;
    }

    @GetMapping("/getAllFaQ")
    public ResponseEntity<List<FaQ>> getAllFaQ() {
        return ResponseEntity.ok(faQService.getAllFaQ());
    }

    @GetMapping("/getFaQByIdx")
    public ResponseEntity<FaQ> getFaQByIdx(int idx) {
        return ResponseEntity.ok(faQService.getFaQByIdx(idx));
    }

    @PostMapping("/addFaQ")
    public ResponseEntity<Result> addFaQ(@RequestBody @Validated FaQ faQ) {
        return ResponseEntity.ok(faQService.addFaQ(faQ));
    }

    @PutMapping("/updateFaQ")
    public ResponseEntity<Result> updateFaQ(@RequestBody @Validated FaQ faQ) {
        return ResponseEntity.ok(faQService.updateFaQ(faQ));
    }

    @DeleteMapping("/deleteFaQ")
    public ResponseEntity<Result> deleteFaQ(int idx) {
        return ResponseEntity.ok(faQService.deleteFaQByIdx(idx));
    }
}
