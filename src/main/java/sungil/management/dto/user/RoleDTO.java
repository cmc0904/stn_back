package sungil.management.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RoleDTO {
    private String userId;
    private String role;
}
