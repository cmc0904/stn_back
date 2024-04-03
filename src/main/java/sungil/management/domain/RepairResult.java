package sungil.management.domain;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RepairResult {
    private Long idx;
    @NotNull
    private Long repairIdx;

    @NotBlank
    private String adminId;

    @Future
    private LocalDateTime visitDate;

    public RepairResult(Long repairIdx, String adminId, LocalDateTime visitDate) {
        this.repairIdx = repairIdx;
        this.adminId = adminId;
        this.visitDate = visitDate;
    }
}
