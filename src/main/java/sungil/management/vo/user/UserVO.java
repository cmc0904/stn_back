package sungil.management.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import sungil.management.dto.user.Gender;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserVO {
    private String userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userAddress;
    private String userPhone;
    private Gender userGender;
    private List<String> roles;
    private LocalDateTime createAt;
}
