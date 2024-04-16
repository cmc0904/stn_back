package sungil.management.dto.faq;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FaQDTO {
    private Integer idx;
    private String question;
    private String answer;
}
