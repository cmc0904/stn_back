package sungil.management.dto.repair;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RepairRequestDTO {
    private String userId;
    private String title;
    private String comment;
}
