package sungil.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sungil.management.domain.RepairRegistration;
import sungil.management.domain.RepairResult;
import sungil.management.domain.RepairView;
import sungil.management.domain.Result;
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
    public ResponseEntity<Result> registration(Authentication authentication, @RequestBody @Validated RepairRegistration repairRegistration) {
        repairRegistration.setCustomerUserId(authentication.getName());
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
    public ResponseEntity<Result> proccessRegistration(@RequestBody @Validated RepairResult repairResult) {
        return ResponseEntity.ok(repairService.processRegistration(repairResult));
    }

    @GetMapping("/getRepairStatusByUserId")
    public ResponseEntity<List<RepairView>> getRepairStatusBy(Authentication authentication, String userId) {
        return ResponseEntity.ok(repairService.getRepairStatusByUserId(userId == null ? authentication.getName() : userId));
    }
    @PostMapping("/completeRepair")
    public ResponseEntity<Result> proccessRegistration(@RequestBody int idx) {
        return ResponseEntity.ok(repairService.complete(idx));
    }

    @PutMapping("/editAdminIdVisitDate")
    public ResponseEntity<Result> editAdminIdAndVisitDate(@RequestBody @Validated RepairResult repairResult) {
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
