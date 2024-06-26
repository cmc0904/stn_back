package sungil.management.dto.repair;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import sungil.management.dto.LoginedUser;
import sungil.management.dto.user.LoginDTO;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RepairRequestDTO extends LoginedUser {
    private final String customerUserId = super.userId;
    @NotBlank
    @Size(min = 5, max = 100)
    private String problemTitle;
    @NotBlank
    @Size(min = 5, max = 100)
    private String problemComment;
}
