package sungil.management.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RepairView {
    private Integer idx;
    private Integer rIdx;
    private String customerUserId;
    private String problemTitle;
    private String problemComment;
    private LocalDateTime createAt;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String gender;
    private int finished;
    private String adminId;
    private LocalDateTime visitDate;
}
