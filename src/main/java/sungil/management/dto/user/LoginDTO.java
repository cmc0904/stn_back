package sungil.management.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginDTO {
    @Size(min = 4, max = 10, message = "최소 4자 이상 10자 이하여야 합니다.")
    @NotBlank(message = "userId는 공백일 수 없습니다.")
    private String userId;
    @Size(min = 3, max = 10, message = "최소 4자 이상 10자 이하여야 합니다.")
    @NotBlank(message = "userPassword는 공백일 수 없습니다.")
    private String password;
}
