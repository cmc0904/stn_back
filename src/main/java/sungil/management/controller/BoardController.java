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
import sungil.management.execption.CreateFailedExecption;
import sungil.management.execption.DeleteFailedExecption;
import sungil.management.execption.UpdateFailedExecption;
import sungil.management.repository.file.FileRepository;
import sungil.management.service.board.board.BoardService;
import sungil.management.service.board.comment.CommentService;
import sungil.management.vo.board.BoardVO;
import sungil.management.vo.board.CommentVO;
import sungil.management.vo.etc.PageVO;
import sungil.management.vo.etc.Result;


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
    public ResponseEntity<Result> postBoard(
            @Valid  @RequestParam(value = "flL", required = false) MultipartFile[] files
            , @Valid @RequestParam(value = "title") String title
            , @Valid @RequestParam(value = "content") String content
            , @Valid @RequestParam(value = "isPrivate") Integer isPrivate
    ) throws CreateFailedExecption {

        BoardDTO boardDTO = new BoardDTO(title, content, isPrivate, files);

        return ResponseEntity.ok(boardService.postBoard(boardDTO));
    }

    @GetMapping("/getAllBoard")
    public ResponseEntity<PageVO<BoardVO>> getAllBoard(String type, String content, Integer currentPage) {
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
    public ResponseEntity<Result> updateBoard(@RequestBody BoardDTO board) throws UpdateFailedExecption {
        return ResponseEntity.ok(boardService.updateBoard(board));
    }

    @DeleteMapping("/deleteBoard")
    public ResponseEntity<Result> deleteBoard(Integer boardIdx) throws DeleteFailedExecption {
        return ResponseEntity.ok(boardService.deleteBoard(boardIdx));
    }


    @GetMapping("/getComment")
    public ResponseEntity<List<CommentVO>> getCommentByBoardIdx(Integer boardIdx) {
        return ResponseEntity.ok(commentService.getCommentByBoardIdx(boardIdx));
    }

    @PostMapping("/addComment")
    public ResponseEntity<Result> addComment(@RequestBody CommentDTO commentDTO) throws CreateFailedExecption {
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
        return ResponseEntity.ok(boardService.getAllFileNameByBoardIdx(boardIdx));
    }

    @PutMapping("/changePrivate")
    public ResponseEntity<Result> getFileNameByBoardIdx(@RequestBody Map<String, Integer> requestData) throws UpdateFailedExecption {
        return ResponseEntity.ok(boardService.changePrivate(requestData.get("idx"), requestData.get("pri")));
    }

    @GetMapping("/getMyBoards")
    public ResponseEntity<List<BoardVO>> getMyBoards(Authentication authentication) {
        return ResponseEntity.ok(boardService.getMyBoard(authentication.getName()));
    }

    @PutMapping("/readBoard")
    public ResponseEntity<Result> updateUser(@RequestBody ReadBoard readBoard, Authentication authentication) throws CreateFailedExecption {
        return ResponseEntity.ok(boardService.read(authentication.getName(), readBoard.getBoardIdx()));
    }


}
