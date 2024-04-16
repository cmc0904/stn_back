package sungil.management.dto.faq;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UploadFaQDTO {
    private Integer faqIdx;
    private String question;
    private String answer;
}
