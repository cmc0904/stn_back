package sungil.management.dto.board;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import sungil.management.dto.LoginedUser;
import sungil.management.dto.user.LoginDTO;

@Getter
public class BoardDTO extends LoginedUser {


    private Integer boardIdx;
    @Size(min = 5, max = 20)
    @NotBlank
    private final String boardTitle;
    @Size(min = 5, max = 100)
    @NotBlank
    private final String boardDetail;
    @Size(min = 4, max = 10)
    private final String writerId;
    private final Integer isPrivate;
    private final MultipartFile[] files;

    public BoardDTO(String boardTitle, String boardDetail, Integer isPrivate, MultipartFile[] files) {
        this.boardTitle = boardTitle;
        this.boardDetail = boardDetail;
        this.writerId = super.userId;
        this.isPrivate = isPrivate;
        this.files = files;
    }
}
