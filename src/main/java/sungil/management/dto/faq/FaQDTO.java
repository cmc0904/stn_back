package sungil.management.dto.faq;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FaQDTO {
    private Integer idx;
    @NotBlank
    @Size(min = 5, max = 50)
    private String question;
    @NotBlank
    @Size(min = 5, max = 100)
    private String answer;
}
