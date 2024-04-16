package sungil.management.dto.repair;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UpdateRepairResponseDTO {
    private Integer repairResponseIdx;
    private String adminId;
    private LocalDateTime visitDate;
}
