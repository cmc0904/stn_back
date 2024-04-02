package sungil.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sungil.management.domain.Board;
import sungil.management.domain.Comment;
import sungil.management.jwt.JwtTokenValidator;
import sungil.management.service.board.board.BoardService;
import sungil.management.service.board.comment.CommentService;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;
    @Autowired
    public BoardController(BoardService boardService, CommentService commentService) {
        this.boardService = boardService;
        this.commentService = commentService;
    }

    @PostMapping("/postBoard")
    public ResponseEntity<?> postBoard(Authentication authentication, @RequestBody @Validated Board board) {
        board.setWriterId(authentication.getName());
        System.out.println(board);
        return ResponseEntity.ok(boardService.postBoard(board));
    }

    @GetMapping("/getAllBoard")
    public ResponseEntity<?> getAllBoard() {

        return ResponseEntity.ok(boardService.getAllBoard());
    }

    @GetMapping("/getBoardByUserIdx")
    public ResponseEntity<?> getBoardByWriterId(Authentication authentication, String userId) {
        return ResponseEntity.ok(boardService.getBoardByWriterId(userId == null ? authentication.getName() : userId));
    }

    @GetMapping("/getBoardByTitle")
    public ResponseEntity<?> getBoardByTitle(String content) {
        List<Board> finedBoard = boardService.getBoardByTitle(content);

        return ResponseEntity.ok(finedBoard);
    }


    @GetMapping("/getBoardByDate")
    public List<Board> getBoardByDate(String date) {
        return boardService.getBoardByDate(date);
    }

    @GetMapping("/getBoardByIdx")
    public ResponseEntity<Board> getBoardByBoardIdx(Integer boardIdx) {
        return ResponseEntity.ok(boardService.getBoardByBoardIdx(boardIdx));
    }

    @PutMapping("/updateBoard")
    public ResponseEntity<Map<String, String>> updateBoard(@RequestBody @Validated Board board) {
        return ResponseEntity.ok(boardService.updateBoard(board));
    }

    @DeleteMapping("/deleteBoard")
    public ResponseEntity<Map<String, String>> deleteBoard(Integer boardIdx) {
        return ResponseEntity.ok(boardService.deleteBoard(boardIdx));
    }


    @GetMapping("/getComment")
    public ResponseEntity<List<Comment>> getCommentByBoardIdx(Integer boardIdx) {
        return ResponseEntity.ok(commentService.getCommentByBoardIdx(boardIdx));
    }

    @PostMapping("/addComment")
    public ResponseEntity<Map<String, String>> addComment(Authentication authentication, @RequestBody @Validated Comment comment) {
        comment.setWriterId(authentication.getName());
        return ResponseEntity.ok(commentService.addComment(comment));
    }

    @GetMapping("/pageNumbers")
    public List<Integer> getBoardListPageNumbers() {
        return boardService.getPageNumbers();
    }

    @GetMapping("/getBoardByPageNumber")
    public List<Board> getUsersByPage(int page) {
        return boardService.getBoardListByPageNum(page);
    }



}
