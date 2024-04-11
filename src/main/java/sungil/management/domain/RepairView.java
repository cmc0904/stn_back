package sungil.management.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RepairView {
    private Integer idx;
    private Integer rIdx;
    private String customerUserId;
    private String problemTitle;
    private String problemComment;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private LocalDateTime createAt;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String gender;
    private int finished;
    private String adminId;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private LocalDateTime visitDate;
}
