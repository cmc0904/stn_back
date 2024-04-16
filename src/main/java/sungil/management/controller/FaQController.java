package sungil.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sungil.management.vo.etc.Result;
import sungil.management.dto.faq.FaQDTO;
import sungil.management.service.faq.FaQService;
import sungil.management.vo.faq.FaQVO;

import java.util.List;

@RestController
@RequestMapping("/api/faq")
public class FaQController {
    private final FaQService faQService;

    @Autowired
    public FaQController(FaQService faQService) {
        this.faQService = faQService;
    }

    @GetMapping("/getAllFaQ")
    public ResponseEntity<List<FaQVO>> getAllFaQ() {
        return ResponseEntity.ok(faQService.getAllFaQ());
    }

    @GetMapping("/getFaQByIdx")
    public ResponseEntity<FaQVO> getFaQByIdx(int idx) {
        return ResponseEntity.ok(faQService.getFaQByIdx(idx));
    }

    @PostMapping("/addFaQ")
    public ResponseEntity<Result> addFaQ(@RequestBody @Validated FaQDTO faQDTO) {
        return ResponseEntity.ok(faQService.addFaQ(faQDTO));
    }

    @PutMapping("/updateFaQ")
    public ResponseEntity<Result> updateFaQ(@RequestBody @Validated FaQDTO faQ) {
        return ResponseEntity.ok(faQService.updateFaQ(faQ));
    }

    @DeleteMapping("/deleteFaQ")
    public ResponseEntity<Result> deleteFaQ(int idx) {
        return ResponseEntity.ok(faQService.deleteFaQByIdx(idx));
    }
}
