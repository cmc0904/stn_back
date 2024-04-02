package sungil.management.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class FaQ {
    private Integer idx;
    @NotBlank
    @Size(min = 5, max = 50)
    private String question;

    @NotBlank
    @Size(min = 5, max = 100)
    private String answer;
}
