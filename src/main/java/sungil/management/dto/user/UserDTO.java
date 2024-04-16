package sungil.management.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sungil.management.utils.PasswordUtils;

@Getter
@RequiredArgsConstructor
public class UserDTO {
    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;
    private String userAddress;
    private String userPhone;
    private Gender userGender;

    public void encodePassword() {
        userPassword = PasswordUtils.passwordEncoder().encode(userPassword);
    }

}
