package sungil.management.advice;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sungil.management.execption.*;
import sungil.management.vo.etc.Result;

@RestControllerAdvice
public class ControllerAdvice {
    // 수정 예외처리
    @ExceptionHandler(UpdateFailedExecption.class)
    public ResponseEntity<Result> updateFailedException() {
        return ResponseEntity.ok(new Result("UPDATE_FAILED"));
    }

    // 삽입 예외처리
    @ExceptionHandler(CreateFailedExecption.class)
    public ResponseEntity<Result> createFailedException() {
        return ResponseEntity.ok(new Result("CREATE_FAILED"));
    }

    // 삭제 예외처리
    @ExceptionHandler(DeleteFailedExecption.class)
    public ResponseEntity<Result> deleteFailedException() {
        return ResponseEntity.ok(new Result("DELETE_FAILED"));
    }

    // 중복 유저
    @ExceptionHandler(DuplicateUserExecption.class)
    public ResponseEntity<Result> duplicateUserException() {
        return ResponseEntity.ok(new Result("DUPLICATE_USER"));
    }

    // 삭제 예외처리
    @ExceptionHandler(NotFoundUserExecption.class)
    public ResponseEntity<Result> notFoundUserException() {
        return ResponseEntity.ok(new Result("NOT_FOUND_USER"));
    }

}
