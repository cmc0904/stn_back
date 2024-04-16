package sungil.management.dto.board;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardDTO {
    private String title;
    private String content;
    private String writerId;
    private Integer isPrivate;
}
