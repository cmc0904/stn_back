package sungil.management.service.repair;

import sungil.management.execption.CreateFailedExecption;
import sungil.management.execption.UpdateFailedExecption;
import sungil.management.vo.etc.PageVO;
import sungil.management.vo.etc.Result;
import sungil.management.dto.repair.RepairRequestDTO;
import sungil.management.dto.repair.RepairResponseDTO;
import sungil.management.dto.repair.UpdateRepairResponseDTO;
import sungil.management.vo.repair.RepairDayForChartData;
import sungil.management.vo.repair.RepairVO;
import sungil.management.vo.repair.SelectedAdminForChart;

import java.util.List;

public interface RepairService {

    Result registrationRepair(RepairRequestDTO repairRequestDTO) throws CreateFailedExecption;
    List<RepairVO> getRepairStatusByUserId(String userId);
    Result processRegistration(RepairResponseDTO repairResponseDTO) throws CreateFailedExecption;
    Result complete(int idx) throws UpdateFailedExecption;
    Result editRegistration(UpdateRepairResponseDTO updateRepairResponseDTO) throws UpdateFailedExecption;
    List<RepairVO> getDataByType(String type);
    List<RepairVO> searchRepairLogsByUserIdAndMode(String type, String userId);

    PageVO<SelectedAdminForChart> getSelectedAdminForChartData(Integer currentPage);

    PageVO<RepairDayForChartData> getRepairDataForChart(Integer currentPage);


}
