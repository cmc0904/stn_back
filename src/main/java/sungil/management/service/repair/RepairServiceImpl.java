package sungil.management.service.repair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sungil.management.domain.RepairRegistration;
import sungil.management.domain.RepairResult;
import sungil.management.domain.RepairView;
import sungil.management.domain.Result;
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
    public Result registrationRepair(RepairRegistration repairRegistration) {

        try {
            repaireRepository.insertRepaire(repairRegistration);
            return new Result("정상적으로 처리되었습니다.");
        } catch (Exception e) {
            return new Result("Failed");
        }

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
    public Result processRegistration(RepairResult repairResult) {

        try {
            repaireRepository.insertRepairResult(repairResult);
            return new Result("정상적으로 처리되었습니다.");
        } catch (Exception e) {
            return new Result("Failed");
        }


    }



    @Override
    public Result complete(int idx) {

        try {
            repaireRepository.updateRepairFlagToOne(idx);
            return new Result("COMPLETE");
        } catch (Exception e) {
            return new Result("FAILED");
        }


    }

    @Override
    public Result editRegistration(RepairResult repairResult) {

        try {
            repaireRepository.updateAdminIdAndVisitTime(repairResult);
            return new Result("COMPLETE");
        } catch (Exception e) {
            return new Result("FAILED");
        }


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


    @Override
    public List<RepairView> getDataByType(String type) {
        return repaireRepository.getRepairDataType(type);
    }


    @Override
    public List<RepairView> searchRepairLogsByUserIdAndMode(String type, String userId) {
        return repaireRepository.searchRepair(type, userId);
    }

}
