package sungil.management.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RoleDTO {
    @NotBlank
    private String userId;
    @NotBlank
    private String role;
}
