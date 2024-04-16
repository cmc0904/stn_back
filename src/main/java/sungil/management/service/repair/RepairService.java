package sungil.management.service.repair;

import sungil.management.vo.etc.Result;
import sungil.management.dto.repair.RepairRequestDTO;
import sungil.management.dto.repair.RepairResponseDTO;
import sungil.management.dto.repair.UpdateRepairResponseDTO;
import sungil.management.vo.repair.RepairVO;

import java.util.List;

public interface RepairService {

    Result registrationRepair(RepairRequestDTO repairRequestDTO);
    List<RepairVO> getRepairStatusByUserId(String userId);
    Result processRegistration(RepairResponseDTO repairResponseDTO);
    Result complete(int idx);
    Result editRegistration(UpdateRepairResponseDTO updateRepairResponseDTO);
    List<RepairVO> getDataByType(String type);
    List<RepairVO> searchRepairLogsByUserIdAndMode(String type, String userId);

}
