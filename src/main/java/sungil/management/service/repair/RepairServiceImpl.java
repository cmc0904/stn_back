package sungil.management.service.repair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sungil.management.domain.RepairRegistration;
import sungil.management.domain.RepairResult;
import sungil.management.domain.RepairView;
import sungil.management.repository.repair.RepaireRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RepairServiceImpl implements RepairService {

    private final RepaireRepository repaireRepository;

    @Autowired
    public RepairServiceImpl(RepaireRepository repaireRepository) {
        this.repaireRepository = repaireRepository;
    }

    @Override
    public Map<String, String> registrationRepair(RepairRegistration repairRegistration) {
        Map<String, String> map = new HashMap<>();

        try {
            repaireRepository.insertRepaire(repairRegistration);
            map.put("results", "정상적으로 처리되었습니다.");
        } catch (Exception e) {
            map.put("results", "Failed");
        }


        return map;
    }

    @Override
    public List<RepairView> getAllRepairStatus() {
       return repaireRepository.getAllRepairStatus();
    }

    @Override
    public List<RepairView> getWaitRepairStatus() {
        return repaireRepository.getWaitRepairStatus();
    }

    @Override
    public List<RepairView> getExpectedRepairStatus() {
        return repaireRepository.getExpectedRepairStatus();
    }

    @Override
    public List<RepairView> getEndedRepairStatus() {
        return repaireRepository.getEndedRepairStatus();
    }
    @Override
    public List<RepairView> getRepairStatusByUserId(String userId) {
        return repaireRepository.getRepairStatusByUserId(userId);
    }

    @Override
    public Map<String, String> processRegistration(RepairResult repairResult) {
        Map<String, String> map = new HashMap<>();

        try {
            repaireRepository.insertRepairResult(repairResult);
            map.put("results", "정상적으로 처리되었습니다.");
        } catch (Exception e) {
            map.put("results", "Failed");
        }


        return map;
    }



    @Override
    public Map<String, String> complete(int idx) {
        Map<String, String> map = new HashMap<>();
        try {
            repaireRepository.updateRepairFlagToOne(idx);
            map.put("result", "COMPLETE");
        } catch (Exception e) {
            map.put("result", "FAILED");
        }

        return map;
    }

    @Override
    public Map<String, String> editRegistration(RepairResult repairResult) {
        Map<String, String> map = new HashMap<>();
        try {
            repaireRepository.updateAdminIdAndVisitTime(repairResult);
            map.put("result", "COMPLETE");
        } catch (Exception e) {
            map.put("result", "FAILED");
        }

        return map;
    }

    @Override
    public List<RepairView> allSearchRepair(String userId) {
        return repaireRepository.allSearchRepair(userId);
    }

    @Override
    public List<RepairView> endedSearchRepair(String userId) {
        return repaireRepository.endedSearchRepair(userId);
    }

    @Override
    public List<RepairView> expectedSearchRepair(String userId) {
        return repaireRepository.expectedSearchRepair(userId);
    }

    @Override
    public List<RepairView> waitSearchRepair(String userId) {
        return repaireRepository.waitSearchRepair(userId);
    }
}
