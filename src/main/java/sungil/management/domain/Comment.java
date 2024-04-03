package sungil.management.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Comment {
    private Long commentIdx;
    @NotNull
    private Long boardIdx;

    @NotBlank
    @Size(min = 5, max = 50)
    private String comment;
    private String writerId;
    private Date createAt;
}
