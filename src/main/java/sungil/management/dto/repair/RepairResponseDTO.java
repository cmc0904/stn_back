package sungil.management.dto.repair;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RepairResponseDTO {

    @NotNull
    private Integer repairIdx;
    @NotBlank
    private String adminId;
    @Future
    private LocalDateTime visitDate;
}
