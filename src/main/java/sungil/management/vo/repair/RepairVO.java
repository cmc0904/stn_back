package sungil.management.vo.repair;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class RepairVO {
    private Integer idx;
    private Integer rIdx;
    private String customerUserId;
    private String problemTitle;
    private String problemComment;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createAt;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String gender;
    private int finished;
    private String adminId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime visitDate;
}
