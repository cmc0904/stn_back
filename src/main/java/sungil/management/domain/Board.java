package sungil.management.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@NoArgsConstructor
public class Board {
    private Long boardIdx;
    @Size(min = 5, max = 20)
    @NotBlank
    private String boardTitle;
    @NotBlank
    @Size(min = 5, max = 100)
    private String boardDetail;
    @Size(min = 4, max = 10)
    private String writerId;
    private Date createAt;
    private Integer isPrivate;
    private MultipartFile[] files;

    public Board(String boardTitle, String boardDetail, String writerId, int isPrivate, MultipartFile[] files) {
        this.boardTitle = boardTitle;
        this.boardDetail = boardDetail;
        this.writerId = writerId;
        this.isPrivate = isPrivate;
        this.files = files;
    }

}
