package sungil.management.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
public class LoginForm {
    @NotBlank
    private String userId;
    @NotBlank
    private String password;

}
