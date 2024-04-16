package sungil.management.dto.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReadBoard {
    private Integer boardIdx;
    private String reader;
}
