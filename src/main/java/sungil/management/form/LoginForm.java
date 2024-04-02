package sungil.management.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
public class LoginForm {
    @NotBlank(message = "userId는 공백일 수 없습니다.")
    private String userId;

    @NotBlank(message = "password는 공백일 수 없습니다.")
    private String password;

}
