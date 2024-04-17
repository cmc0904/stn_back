package sungil.management.dto.board;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReadBoard {
    @NotNull
    private Integer boardIdx;
    private String reader;
}
