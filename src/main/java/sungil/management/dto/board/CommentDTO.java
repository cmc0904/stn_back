package sungil.management.dto.board;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import sungil.management.dto.LoginedUser;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentDTO extends LoginedUser {
    @NotNull
    private Integer boardIdx;


    @NotBlank
    @Size(min = 5, max = 50)
    private String comment;
    private final String writerId = super.userId;
}
