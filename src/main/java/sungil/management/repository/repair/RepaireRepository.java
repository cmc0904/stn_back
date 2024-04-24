package sungil.management.repository.repair;

import org.apache.ibatis.annotations.Mapper;

import sungil.management.dto.repair.RepairRequestDTO;
import sungil.management.dto.repair.RepairResponseDTO;
import sungil.management.dto.repair.UpdateRepairResponseDTO;
import sungil.management.vo.repair.RepairDayForChartData;
import sungil.management.vo.repair.RepairVO;
import sungil.management.vo.repair.SelectedAdminForChart;

import java.util.List;

@Mapper
public interface RepaireRepository {
    // 접수
    void insertRepaire(RepairRequestDTO repairRegistration);
    // 현황 확인
    List<RepairVO> getRepairStatusByUserId(String userId);
    // 접수 처리
    void insertRepairResult(RepairResponseDTO repairResponseDTO);
    // 처리 완료
    void updateRepairFlagToOne(int idx);
    // 담당 관리자, 방문 시간대 변경
    void updateAdminIdAndVisitTime(UpdateRepairResponseDTO updateRepairResponseDTO);
    // 필터링
    List<RepairVO> getRepairDataType(String type);
    // 필터링 + 유저 아이디로 검색
    List<RepairVO> searchRepair(String type, String userId);

    // 각 배정된 관리자 숫자
    List<SelectedAdminForChart> getSelectedAdminForChartData(Integer offset);

    int getSelectedAdminForChartDataLength();

    List<RepairDayForChartData> getRepairDataForChartData(Integer offset);

    int getRepairDataForChartDataLength();

}
