package sungil.management.dto.board;


import lombok.AllArgsConstructor;
import lombok.Getter;
import sungil.management.dto.LoginedUser;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentDTO extends LoginedUser {
    private Integer boardIdx;
    private String comment;
    private final String writerId = super.userId;
}
