package sungil.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sungil.management.domain.RepairRegistration;
import sungil.management.domain.RepairResult;
import sungil.management.domain.RepairView;
import sungil.management.jwt.JwtTokenValidator;
import sungil.management.service.repair.RepairService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/repair")
public class RepairController {

    private final RepairService repairService;

    @Autowired
    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }


    @PostMapping("/registrationrepair")
    public ResponseEntity<Map<String, String>> registration(Authentication authentication, @RequestBody @Validated RepairRegistration repairRegistration) {
        repairRegistration.setCustomerUserId(authentication.getName());
        return ResponseEntity.ok(repairService.registrationRepair(repairRegistration));
    }

    @GetMapping("/getRepairStatus")
    public ResponseEntity<List<RepairView>> getRepairStatus() {
        return ResponseEntity.ok(repairService.getAllRepairStatus());
    }

    @GetMapping("/getRepairFiltering") // 대기
    public ResponseEntity<List<RepairView>> getRepairWaitStatus(String type) {
        System.out.println(repairService.getDataByType(type));
        return ResponseEntity.ok(repairService.getDataByType(type));

    }


    @GetMapping("getRepairExpectedStatus") // 예정
    public ResponseEntity<List<RepairView>> getRepairExpectedStatus() {
        return ResponseEntity.ok(repairService.getExpectedRepairStatus());
    }


    @PostMapping("/processrepair")
    public ResponseEntity<Map<String, String>> proccessRegistration(@RequestBody @Validated RepairResult repairResult) {
        return ResponseEntity.ok(repairService.processRegistration(repairResult));
    }

    @GetMapping("/getRepairStatusByUserId")
    public ResponseEntity<List<RepairView>> getRepairStatusBy(Authentication authentication, String userId) {
        return ResponseEntity.ok(repairService.getRepairStatusByUserId(userId == null ? authentication.getName() : userId));
    }
    @PostMapping("/completeRepair")
    public ResponseEntity<Map<String, String>> proccessRegistration(@RequestBody int idx) {
        return ResponseEntity.ok(repairService.complete(idx));
    }


    @PutMapping("/editAdminIdVisitDate")
    public ResponseEntity<Map<String, String>> editAdminIdAndVisitDate(@RequestBody @Validated RepairResult repairResult) {
        return ResponseEntity.ok(repairService.editRegistration(repairResult));
    }

    @GetMapping("/searchRepair")
    public ResponseEntity<List<RepairView>> searchRepair(String userId, String type){
        System.out.println(userId);
        System.out.println(type);
        System.out.println(repairService.searchRepairLogsByUserIdAndMode(type, userId));
        return ResponseEntity.ok(repairService.searchRepairLogsByUserIdAndMode(type, userId));
    }
}
