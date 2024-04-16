package sungil.management.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import sungil.management.dto.board.ReadBoard;
import sungil.management.dto.board.BoardDTO;
import sungil.management.dto.board.CommentDTO;
import sungil.management.repository.file.FileRepository;
import sungil.management.service.board.board.BoardService;
import sungil.management.service.board.comment.CommentService;
import sungil.management.vo.board.BoardVO;
import sungil.management.vo.board.CommentVO;
import sungil.management.vo.etc.PageVO;


import java.io.*;
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
            , @Valid @RequestParam(value = "isPrivate") Integer isPrivate
    ) {

        BoardDTO boardDTO = new BoardDTO(title, content, isPrivate, files);

        return ResponseEntity.ok(boardService.postBoard(boardDTO));
    }

    @GetMapping("/getAllBoard")
    public ResponseEntity<PageVO<BoardVO>> getAllBoard(String type, String content, Integer currentPage) {
        System.out.println(new PageVO<BoardVO>(boardService.getBoardCount(type, content), boardService.getBoardBy(type, content, currentPage)));
        return ResponseEntity.ok(new PageVO<BoardVO>(boardService.getBoardCount(type, content), boardService.getBoardBy(type, content, currentPage)));
    }

    @GetMapping("/getBoardByUserIdx")
    public ResponseEntity<?> getBoardByWriterId(Authentication authentication, String userId) {
        String writerId = userId == null ? authentication.getName() : userId;
        return ResponseEntity.ok(boardService.getBoardByWriterId(writerId));
    }

    @GetMapping("/getBoardByTitle")
    public ResponseEntity<?> getBoardByTitle(String content) {
        List<BoardVO> finedBoard = boardService.getBoardByTitle(content);

        return ResponseEntity.ok(finedBoard);
    }


    @GetMapping("/getBoardByDate")
    public List<BoardVO> getBoardByDate(String date) {
        return boardService.getBoardByDate(date);
    }

    @GetMapping("/getBoardByIdx")
    public ResponseEntity<BoardVO> getBoardByBoardIdx(Integer boardIdx) {
        return ResponseEntity.ok(boardService.getBoardByBoardIdx(boardIdx));
    }

    @PutMapping("/updateBoard")
    public ResponseEntity<Map<String, String>> updateBoard(@RequestBody BoardDTO board) {
        return ResponseEntity.ok(boardService.updateBoard(board));
    }

    @DeleteMapping("/deleteBoard")
    public ResponseEntity<Map<String, String>> deleteBoard(Integer boardIdx) {
        return ResponseEntity.ok(boardService.deleteBoard(boardIdx));
    }


    @GetMapping("/getComment")
    public ResponseEntity<List<CommentVO>> getCommentByBoardIdx(Integer boardIdx) {
        return ResponseEntity.ok(commentService.getCommentByBoardIdx(boardIdx));
    }

    @PostMapping("/addComment")
    public ResponseEntity<Map<String, String>> addComment(@RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.addComment(commentDTO));
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
        System.out.println(requestData);
        return ResponseEntity.ok(boardService.changePrivate(idx, pri));
    }

    @GetMapping("/getMyBoards")
    public ResponseEntity<List<BoardVO>> getMyBoards(Authentication authentication) {
        return ResponseEntity.ok(boardService.getMyBoard(authentication.getName()));
    }

    @PutMapping("/readBoard")
    public ResponseEntity<Map<String, String>> updateUser(@RequestBody ReadBoard readBoard, Authentication authentication){
        return ResponseEntity.ok(boardService.read(authentication.getName(), readBoard.getBoardIdx()));
    }


}
