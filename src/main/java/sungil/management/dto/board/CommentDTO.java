package sungil.management.dto.board;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentDTO {
    private Integer boardIdx;
    private String comment;
    private String writerId;
}
