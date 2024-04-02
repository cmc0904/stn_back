package sungil.management.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class Board {
    private Integer boardIdx;

    @Size(min = 1, max = 20)
    @NotBlank
    private String boardTitle;

    @NotBlank
    @Size(min = 1, max = 100)
    private String boardDetail;

    @Size(min = 4, max = 10)
    private String writerId;

    private Date createAt;

    @Min(0)
    @Max(1)
    private int isPrivate;
}
