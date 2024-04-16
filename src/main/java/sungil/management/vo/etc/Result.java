package sungil.management.vo.etc;

import lombok.Data;

@Data
public class Result {
    private String results;

    public Result(String results) {
        this.results = results;
    }
}
