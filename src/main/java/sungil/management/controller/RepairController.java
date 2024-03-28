package sungil.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    private final JwtTokenValidator jwtTokenValidator;
    private final RepairService repairService;

    @Autowired
    public RepairController(JwtTokenValidator jwtTokenValidator, RepairService repairService) {
        this.jwtTokenValidator = jwtTokenValidator;
        this.repairService = repairService;
    }


    @PostMapping("/registrationrepair")
    public ResponseEntity<Map<String, String>> registration(@RequestHeader("Authorization") String authorizationHeader, @RequestBody RepairRegistration repairRegistration) {
        repairRegistration.setCustomerUserId(jwtTokenValidator.getUserIdFromToken(jwtTokenValidator.extractJwtToken(authorizationHeader)));
        return ResponseEntity.ok(repairService.registrationRepair(repairRegistration));
    }

    @GetMapping("/getRepairStatus")
    public ResponseEntity<List<RepairView>> getRepairStatus() {
        return ResponseEntity.ok(repairService.getAllRepairStatus());
    }

    @GetMapping("/getRepairFiltering") // 대기
    public ResponseEntity<?> getRepairWaitStatus(String type) {


        if(type.equals("allData")) {
            return ResponseEntity.ok(repairService.getAllRepairStatus());
        } else if (type.equals("waiting")) {
            return ResponseEntity.ok(repairService.getWaitRepairStatus());
        }else if (type.equals("willVisit")) {
            return ResponseEntity.ok(repairService.getExpectedRepairStatus());
        }else if (type.equals("finished")) {
            return ResponseEntity.ok(repairService.getEndedRepairStatus());
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("result", "CHECK_TYPE");
            return ResponseEntity.ok(map);
        }

    }


    @GetMapping("getRepairExpectedStatus") // 예정
    public ResponseEntity<List<RepairView>> getRepairExpectedStatus() {
        return ResponseEntity.ok(repairService.getExpectedRepairStatus());
    }


    @PostMapping("/processrepair")
    public ResponseEntity<Map<String, String>> proccessRegistration(@RequestBody RepairResult repairResult) {
        return ResponseEntity.ok(repairService.processRegistration(repairResult));
    }

    @GetMapping("/getRepairStatusByUserId")
    public ResponseEntity<List<RepairView>> getRepairStatusBy(@RequestHeader("Authorization") String authorizationHeader, String userId) {
        return ResponseEntity.ok(repairService.getRepairStatusByUserId(userId == null ? jwtTokenValidator.getUserIdFromToken(jwtTokenValidator.extractJwtToken(authorizationHeader)) : userId));
    }
    @PostMapping("/completeRepair")
    public ResponseEntity<Map<String, String>> proccessRegistration(@RequestBody int idx) {
        return ResponseEntity.ok(repairService.complete(idx));
    }

    @PutMapping("/editAdminIdVisitDate")
    public ResponseEntity<Map<String, String>> editAdminIdAndVisitDate(@RequestBody RepairResult repairResult) {
        return ResponseEntity.ok(repairService.editRegistration(repairResult));
    }

    @GetMapping("/searchRepair")
    public ResponseEntity<List<RepairView>> searchRepair(String userId, String type){
        System.out.println(type);
        if (type.equals("allData")){
            return ResponseEntity.ok(repairService.allSearchRepair(userId));
        } else if (type.equals("waiting")) {
            return ResponseEntity.ok(repairService.waitSearchRepair(userId));
        } else if (type.equals("willVisit")) {
            return ResponseEntity.ok(repairService.expectedSearchRepair(userId));
        } else if (type.equals("finished")) {
            return ResponseEntity.ok(repairService.endedSearchRepair(userId));
        }
        return ResponseEntity.ok(new ArrayList<>());
    }
}
