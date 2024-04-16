package sungil.management.vo.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class CommentVO {
    private Integer commentIdx;
    private Integer boardIdx;
    private String comment;
    private String writerId;
    private Date createAt;
}
