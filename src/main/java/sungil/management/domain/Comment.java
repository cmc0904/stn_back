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
    private Integer commentIdx;
    @NotNull
    private Integer boardIdx;
    @NotBlank
    @Size(min = 1, max = 50)
    private String comment;

    @NotBlank
    @Size(min = 4, max = 10, message = "최소 4자 이상 10자 이하여야 합니다.")
    private String writerId;
    private Date createAt;
}
