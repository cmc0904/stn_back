package sungil.management.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
@NoArgsConstructor
public class Board {
    private Integer boardIdx;
    private String boardTitle;
    private String boardDetail;
    private String writerId;
    private Date createAt;
    private int isPrivate;
    private MultipartFile[] files;

    public Board(String boardTitle, String boardDetail, String writerId, int isPrivate, MultipartFile[] files) {
        this.boardTitle = boardTitle;
        this.boardDetail = boardDetail;
        this.writerId = writerId;
        this.isPrivate = isPrivate;
        this.files = files;
    }

}
