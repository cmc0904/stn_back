package sungil.management.dto.etc;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class IdxOnlyDTO {
    @NotNull
    private Integer idx;
}
