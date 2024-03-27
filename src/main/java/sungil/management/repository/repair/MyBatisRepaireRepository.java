package sungil.management.repository.repair;

import org.apache.ibatis.annotations.*;
import sungil.management.domain.RepairRegistration;
import sungil.management.domain.RepairResult;
import sungil.management.domain.RepairView;

import java.util.List;

@Mapper
public interface MyBatisRepaireRepository extends RepaireRepository{

    @Insert("insert into stn_repair(customerUserId, problemTitle, problemComment, createAt) values (#{customerUserId}, #{problemTitle}, #{problemComment}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "repairRegistrationIdx", keyColumn = "idx")
    void insertRepaire(RepairRegistration repairRegistration);

    @Select("SELECT r.idx, r.customerUserId, r.problemTitle, r.problemComment, r.createAt, u.name, u.address, u.email, u.phone, u.gender, rr.adminId, rr.finished, rr.visitDate, rr.idx as rIdx FROM stn_repair r JOIN stn_users u ON u.userId = r.customerUserId LEFT JOIN stn_repair_result rr ON rr.repairIdx = r.idx")
    List<RepairView> getAllRepairStatus();
    @Insert("insert into stn_repair_result(repairIdx, adminId, visitDate) values (#{repairIdx}, #{adminId}, #{visitDate})")
    void insertRepairResult(RepairResult repairResult);

    @Select("SELECT r.idx, r.customerUserId, r.problemTitle, r.problemComment, r.createAt, u.name, u.address, u.email, u.phone, u.gender, rr.adminId, rr.visitDate FROM stn_repair r JOIN stn_users u ON u.userId = r.customerUserId LEFT JOIN stn_repair_result rr ON rr.repairIdx = r.idx WHERE u.userId = #{userId}")
    List<RepairView> getRepairStatusByUserId(String userId);

    @Update("update stn_repair_result set finished = 1 where repairIdx = #{idx}")
    void updateRepairFlagToOne(int idx);

    @Update("update stn_repair_result set adminId = #{adminId}, visitDate = #{visitDate} where idx = #{idx}")
    void updateAdminIdAndVisitTime(RepairResult repairResult);
}
