package sungil.management.service.repair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sungil.management.execption.CreateFailedExecption;
import sungil.management.execption.UpdateFailedExecption;
import sungil.management.vo.etc.PageVO;
import sungil.management.vo.etc.Result;
import sungil.management.dto.repair.RepairRequestDTO;
import sungil.management.dto.repair.RepairResponseDTO;
import sungil.management.dto.repair.UpdateRepairResponseDTO;
import sungil.management.repository.repair.RepaireRepository;
import sungil.management.vo.repair.RepairVO;
import sungil.management.vo.repair.SelectedAdminForChart;

import java.util.List;


@Service
public class RepairServiceImpl implements RepairService {

    private final RepaireRepository repaireRepository;

    @Autowired
    public RepairServiceImpl(RepaireRepository repaireRepository) {
        this.repaireRepository = repaireRepository;
    }

    @Override
    public Result registrationRepair(RepairRequestDTO repairRequestDTO) throws CreateFailedExecption {

        try {
            repaireRepository.insertRepaire(repairRequestDTO);
            return new Result("정상적으로 처리되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new CreateFailedExecption();
        }

    }

    @Override
    public List<RepairVO> getRepairStatusByUserId(String userId) {
        return repaireRepository.getRepairStatusByUserId(userId);
    }

    @Override
    public Result processRegistration(RepairResponseDTO repairResponseDTO) throws CreateFailedExecption {

        try {
            repaireRepository.insertRepairResult(repairResponseDTO);
            return new Result("정상적으로 처리되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new CreateFailedExecption();
        }


    }

    @Override
    public Result complete(int idx) throws UpdateFailedExecption {

        try {
            repaireRepository.updateRepairFlagToOne(idx);
            return new Result("COMPLETE");
        } catch (Exception e) {
            throw new UpdateFailedExecption();
        }


    }

    @Override
    public Result editRegistration(UpdateRepairResponseDTO updateRepairResponseDTO) throws UpdateFailedExecption {

        try {
            repaireRepository.updateAdminIdAndVisitTime(updateRepairResponseDTO);
            return new Result("COMPLETE");
        } catch (Exception e) {
            throw new UpdateFailedExecption();
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

    @Override
    public PageVO<SelectedAdminForChart> getSelectedAdminForChartData(Integer currentPage) {
        return new PageVO<SelectedAdminForChart> (repaireRepository.getSelectedAdminForChartDataLength(), repaireRepository.getSelectedAdminForChartData(currentPage != null ? (currentPage - 1) * 5 : null));
    }


}
