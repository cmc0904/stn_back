package sungil.management.vo.board;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
@Data
public class BoardVO {
    private Integer boardIdx;
    @Size(min = 5, max = 20)
    @NotBlank
    private String boardTitle;
    private String boardDetail;
    private String writerId;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private LocalDateTime createAt;
    private Integer isPrivate;
    private String[] fileNames;
    private int views;
}
