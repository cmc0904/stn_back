package sungil.management.domain;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RepairResult {
    private int idx;
    @NotNull
    private Integer repairIdx;

    @NotBlank
    private String adminId;

    @Future
    private LocalDateTime visitDate;

    public RepairResult(int repairIdx, String adminId, LocalDateTime visitDate) {
        this.repairIdx = repairIdx;
        this.adminId = adminId;
        this.visitDate = visitDate;
    }
}
