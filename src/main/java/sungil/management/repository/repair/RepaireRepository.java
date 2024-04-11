package sungil.management.repository.repair;

import org.apache.ibatis.annotations.Mapper;
import sungil.management.domain.RepairRegistration;
import sungil.management.domain.RepairResult;
import sungil.management.domain.RepairView;

import java.util.List;

@Mapper
public interface RepaireRepository {

    // 접수
    void insertRepaire(RepairRegistration repairRegistration);

    // 현황 확인
    List<RepairView> getAllRepairStatus();

    // 접수 대기
    List<RepairView> getWaitRepairStatus();

    // 방문 예정
    List<RepairView> getExpectedRepairStatus();

    //처리완료
    List<RepairView> getEndedRepairStatus();
    // 현황 확인
    List<RepairView> getRepairStatusByUserId(String userId);

    // 접수 처리
    void insertRepairResult(RepairResult repairResult);

    // 처리 완료
    void updateRepairFlagToOne(int idx);
    
    // 담당 관리자, 방문 시간대 변경
    void updateAdminIdAndVisitTime(RepairResult repairResult);

    List<RepairView> allSearchRepair(String userId);
    List<RepairView> endedSearchRepair(String userId);

    List<RepairView> expectedSearchRepair(String userId);

    List<RepairView> waitSearchRepair(String userId);


    // 필터링
    List<RepairView> getRepairDataType(String type);



    // 필터링 + 유저 아이디로 검색
    List<RepairView> searchRepair(String type, String userId);
}
