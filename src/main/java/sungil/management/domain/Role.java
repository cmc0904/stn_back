package sungil.management.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Role {
    @NotBlank
    private String userId;
    @NotBlank
    private String role;
}
