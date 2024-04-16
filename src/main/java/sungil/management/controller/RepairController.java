package sungil.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sungil.management.vo.etc.Result;
import sungil.management.dto.repair.RepairRequestDTO;
import sungil.management.dto.repair.RepairResponseDTO;
import sungil.management.dto.repair.UpdateRepairResponseDTO;
import sungil.management.service.repair.RepairService;
import sungil.management.vo.repair.RepairVO;

import java.util.List;

@RestController
@RequestMapping("/api/repair")
public class RepairController {

    private final RepairService repairService;

    @Autowired
    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }


    @PostMapping("/registrationrepair")
    public ResponseEntity<Result> registration(@RequestBody @Validated RepairRequestDTO repairRequestDTO) {
        return ResponseEntity.ok(repairService.registrationRepair(repairRequestDTO));
    }

    @GetMapping("/getRepairFiltering") // 대기
    public ResponseEntity<List<RepairVO>> getRepairWaitStatus(String type) {
        System.out.println(repairService.getDataByType(type));
        return ResponseEntity.ok(repairService.getDataByType(type));

    }

    @PostMapping("/processrepair")
    public ResponseEntity<Result> proccessRegistration(@RequestBody @Validated RepairResponseDTO repairResponseDTO) {
        return ResponseEntity.ok(repairService.processRegistration(repairResponseDTO));
    }

    @GetMapping("/getRepairStatusByUserId")
    public ResponseEntity<List<RepairVO>> getRepairStatusBy(Authentication authentication, String userId) {
        return ResponseEntity.ok(repairService.getRepairStatusByUserId(userId == null ? authentication.getName() : userId));
    }
    @PostMapping("/completeRepair")
    public ResponseEntity<Result> proccessRegistration(@RequestBody int idx) {
        return ResponseEntity.ok(repairService.complete(idx));
    }


    @PutMapping("/editAdminIdVisitDate")
    public ResponseEntity<Result> editAdminIdAndVisitDate(@RequestBody @Validated UpdateRepairResponseDTO updateRepairResponseDTO) {
        return ResponseEntity.ok(repairService.editRegistration(updateRepairResponseDTO));
    }

    @GetMapping("/searchRepair")
    public ResponseEntity<List<RepairVO>> searchRepair(String userId, String type){
        return ResponseEntity.ok(repairService.searchRepairLogsByUserIdAndMode(type, userId));
    }
}
