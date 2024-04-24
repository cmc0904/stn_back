package sungil.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sungil.management.dto.etc.IdxOnlyDTO;
import sungil.management.execption.CreateFailedExecption;
import sungil.management.execption.UpdateFailedExecption;
import sungil.management.vo.etc.PageVO;
import sungil.management.vo.etc.Result;
import sungil.management.dto.repair.RepairRequestDTO;
import sungil.management.dto.repair.RepairResponseDTO;
import sungil.management.dto.repair.UpdateRepairResponseDTO;
import sungil.management.service.repair.RepairService;
import sungil.management.vo.repair.RepairVO;
import sungil.management.vo.repair.SelectedAdminForChart;

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
    public ResponseEntity<Result> registration(@RequestBody @Validated RepairRequestDTO repairRequestDTO) throws CreateFailedExecption {
        return ResponseEntity.ok(repairService.registrationRepair(repairRequestDTO));
    }

    @GetMapping("/getRepairFiltering") // 대기
    public ResponseEntity<List<RepairVO>> getRepairWaitStatus(String type) {
        return ResponseEntity.ok(repairService.getDataByType(type));

    }

    @PostMapping("/processrepair")
    public ResponseEntity<Result> proccessRegistration(@RequestBody @Validated RepairResponseDTO repairResponseDTO) throws CreateFailedExecption {
        return ResponseEntity.ok(repairService.processRegistration(repairResponseDTO));
    }

    @GetMapping("/getRepairStatusByUserId")
    public ResponseEntity<List<RepairVO>> getRepairStatusBy(Authentication authentication, String userId) {
        return ResponseEntity.ok(repairService.getRepairStatusByUserId(userId == null ? authentication.getName() : userId));
    }
    @PostMapping("/completeRepair")
    public ResponseEntity<Result> proccessRegistration(@RequestBody IdxOnlyDTO idx) throws UpdateFailedExecption {
        System.out.println(idx.getIdx());
        return ResponseEntity.ok(repairService.complete(idx.getIdx()));
    }


    @PutMapping("/editAdminIdVisitDate")
    public ResponseEntity<Result> editAdminIdAndVisitDate(@RequestBody @Validated UpdateRepairResponseDTO updateRepairResponseDTO) throws UpdateFailedExecption {
        return ResponseEntity.ok(repairService.editRegistration(updateRepairResponseDTO));
    }

    @GetMapping("/searchRepair")
    public ResponseEntity<List<RepairVO>> searchRepair(String userId, String type){
        return ResponseEntity.ok(repairService.searchRepairLogsByUserIdAndMode(type, userId));
    }


    @GetMapping("/getSelectedAdminsCountForChart")
    public ResponseEntity<PageVO<SelectedAdminForChart>> getSelectedAdminsForChart(Integer currentPage){
        System.out.println(currentPage);
        System.out.println(repairService.getSelectedAdminForChartData(currentPage));
        return ResponseEntity.ok(repairService.getSelectedAdminForChartData(currentPage));
    }

    @GetMapping("/getRepairDataForChart")
    public ResponseEntity<PageVO> getMyRepairs(Integer currentPage){
        return ResponseEntity.ok(repairService.getRepairDataForChart(currentPage));
    }
}
