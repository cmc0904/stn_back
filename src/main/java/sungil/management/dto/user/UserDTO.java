package sungil.management.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sungil.management.domain.Gender;

@Getter
@AllArgsConstructor
public class UserDTO {
    private String userId;
    private String password;
    private String name;
    private String email;
    private String address;
    private String phone;
    private Gender gender;
}
