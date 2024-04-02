package sungil.management.domain;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
public class User {
    @Size(min = 4, max = 10, message = "최소 4자 이상 10자 이하여야 합니다.")
    @NotBlank(message = "userId는 공백일 수 없습니다.")
    private String userId;

    @Size(min = 3, max = 10, message = "최소 4자 이상 10자 이하여야 합니다.")
    @NotBlank(message = "userPassword는 공백일 수 없습니다.")
    private String userPassword;

    @Size(min = 2, max = 8, message = "최소 2자 이상 8자 이하여야 합니다.")
    private String userName;

    @Size(min = 8, max = 20, message = "최소 8자 이상 20자 이하여야 합니다.")
    @Email(message = "유효한 이메일 주소여야 합니다.")
    private String userEmail;

    @Size(min = 8, max = 20, message = "최소 8자 이상 20자 이하여야 합니다.")
    private String userAddress;

    @Size(min = 11, max = 11, message = "휴대폰 번호는 11자리여야 합니다.")
    private String userPhone;

    @NotNull(message = "성별을 선택해주세요.")
    private Gender userGender;

    @NotNull(message = "생성일자를 입력해주세요.")
    private Date createAt;
}
