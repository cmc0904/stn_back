package sungil.management.service.repair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sungil.management.vo.etc.Result;
import sungil.management.dto.repair.RepairRequestDTO;
import sungil.management.dto.repair.RepairResponseDTO;
import sungil.management.dto.repair.UpdateRepairResponseDTO;
import sungil.management.repository.repair.RepaireRepository;
import sungil.management.vo.repair.RepairVO;

import java.util.List;


@Service
public class RepairServiceImpl implements RepairService {

    private final RepaireRepository repaireRepository;

    @Autowired
    public RepairServiceImpl(RepaireRepository repaireRepository) {
        this.repaireRepository = repaireRepository;
    }

    @Override
    public Result registrationRepair(RepairRequestDTO repairRequestDTO) {

        try {
            repaireRepository.insertRepaire(repairRequestDTO);
            return new Result("정상적으로 처리되었습니다.");
        } catch (Exception e) {
            return new Result("Failed");
        }

    }

    @Override
    public List<RepairVO> getRepairStatusByUserId(String userId) {
        return repaireRepository.getRepairStatusByUserId(userId);
    }

    @Override
    public Result processRegistration(RepairResponseDTO repairResponseDTO) {

        try {
            repaireRepository.insertRepairResult(repairResponseDTO);
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
    public Result editRegistration(UpdateRepairResponseDTO updateRepairResponseDTO) {

        try {
            repaireRepository.updateAdminIdAndVisitTime(updateRepairResponseDTO);
            return new Result("COMPLETE");
        } catch (Exception e) {
            return new Result("FAILED");
        }


    }
    @Override
    public List<RepairVO> getDataByType(String type) {
        return repaireRepository.getRepairDataType(type);
    }


    @Override
    public List<RepairVO> searchRepairLogsByUserIdAndMode(String type, String userId) {
        return repaireRepository.searchRepair(type, userId);
    }

}
