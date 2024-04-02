package sungil.management.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairRegistration {

    private Integer repairRegistrationIdx;

    @NotBlank
    @Size(min = 4, max = 10)
    private String customerUserId;

    @NotBlank
    @Size(min = 5, max = 10)
    private String problemTitle;

    @NotBlank
    @Size(min = 5, max = 50)
    private String problemComment;

    private Date createAt;
}
