package sungil.management.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sungil.management.domain.Board;
import sungil.management.domain.Comment;
import sungil.management.jwt.JwtTokenValidator;
import sungil.management.repository.file.FileRepository;
import sungil.management.service.board.board.BoardService;
import sungil.management.service.board.comment.CommentService;


import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;

    private final FileRepository fileRepository;


    @Autowired
    public BoardController(BoardService boardService, CommentService commentService, FileRepository fileRepository) {
        this.boardService = boardService;
        this.commentService = commentService;
        this.fileRepository = fileRepository;
    }

    @PostMapping(path = "/postBoard")
    public ResponseEntity<?> postBoard(
            @Valid  @RequestParam(value = "flL", required = false) MultipartFile[] files
            , @Valid @RequestParam(value = "title") String title
            , @Valid @RequestParam(value = "content") String content
            , @Valid @RequestParam(value = "isPrivate") String isPrivate
            , Authentication authentication
    ) {

        Board board = new Board(
                title
                , content
                , authentication.getName()
                , Integer.parseInt(isPrivate)
                , files
        );

        return ResponseEntity.ok(boardService.postBoard(board));
    }

    @GetMapping("/getAllBoard")
    public ResponseEntity<?> getAllBoard() {
        return ResponseEntity.ok(boardService.getAllBoard());
    }

    @GetMapping("/getBoardByUserIdx")
    public ResponseEntity<?> getBoardByWriterId(Authentication authentication, String userId) {
        String writerId = userId == null ? authentication.getName() : userId;
        return ResponseEntity.ok(boardService.getBoardByWriterId(writerId));
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
    public ResponseEntity<Map<String, String>> updateBoard(@RequestBody Board board) {
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
    public ResponseEntity<Map<String, String>> addComment(Authentication authentication, @RequestBody Comment comment) {
        comment.setWriterId(authentication.getName());
        return ResponseEntity.ok(commentService.addComment(comment));
    }

    @GetMapping("/pageNumbers")
    public List<Integer> getBoardListPageNumbers() {
        return boardService.getPageNumbers();
    }

    @GetMapping("/getBoardByPageNumber")
    public ResponseEntity<List<Board>> getUsersByPage(int page) {
        return ResponseEntity.ok(boardService.getBoardListByPageNum(page));
    }

    @GetMapping("/image/download")
    public ResponseEntity<byte[]> downloadImage(String fileName) {

        try {
            byte[] fileBytes = fileRepository.getFileByFileName(fileName);

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
                    .body(fileBytes);
        } catch (IOException e) {

            return ResponseEntity.status(HttpStatus.OK).body(null);

        }

    }

    @GetMapping("/getFileNames")
    public ResponseEntity<List<String>> getFileNameByBoardIdx(int boardIdx) {
        System.out.println(boardService.getAllFileNameByBoardIdx(boardIdx));
        return ResponseEntity.ok(boardService.getAllFileNameByBoardIdx(boardIdx));
    }

    @PutMapping("/changePrivate")
    public ResponseEntity<Map<String, String>> getFileNameByBoardIdx(@RequestBody Map<String, Integer> requestData) {
        int idx = requestData.get("idx");
        int pri = requestData.get("pri");
        return ResponseEntity.ok(boardService.changePrivate(idx, pri));
    }




}
