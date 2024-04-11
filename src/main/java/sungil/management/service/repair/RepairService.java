package sungil.management.service.repair;

import sungil.management.domain.RepairRegistration;
import sungil.management.domain.RepairResult;
import sungil.management.domain.RepairView;
import sungil.management.domain.Result;

import java.util.List;
import java.util.Map;

public interface RepairService {

    Result registrationRepair(RepairRegistration repairRegistration);

    List<RepairView> getAllRepairStatus();

    List<RepairView> getWaitRepairStatus();

    List<RepairView> getExpectedRepairStatus();

    List<RepairView> getEndedRepairStatus();

    List<RepairView> getRepairStatusByUserId(String userId);
    Result processRegistration(RepairResult repairResult);
    Result complete(int idx);
    Result editRegistration(RepairResult repairResult);

    List<RepairView> allSearchRepair(String userId);
    List<RepairView> endedSearchRepair(String userId);

    List<RepairView> expectedSearchRepair(String userId);

    List<RepairView> waitSearchRepair(String userId);


}
