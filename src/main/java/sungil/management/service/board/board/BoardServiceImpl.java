package sungil.management.service.board.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sungil.management.domain.Board;
import sungil.management.repository.board.board.BoardRepository;

import sungil.management.repository.file.FileRepository;
import sungil.management.utils.PageNationUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;

    // 임시
    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository, FileRepository fileRepository) {
        this.boardRepository = boardRepository;
        this.fileRepository = fileRepository;
    }

    @Override
    public List<Board> getAllBoard() {
        return boardRepository.getAllBoard();
    }

    @Override
    public Board getBoardByBoardIdx(Integer boardIdx) {
        return boardRepository.getBoardByBoardIdx(boardIdx);
    }

    @Override
    public List<Board> getBoardByWriterId(String writerId) {
        return boardRepository.getBoardByWriterId(writerId);
    }

    @Override
    public Map<String, String> updateBoard(Board board) {
        Map<String, String> map = new HashMap<>();

        try {
            boardRepository.updateBoard(board);

            map.put("result", "UPDATE_BOARD_COMPLETE");
        } catch (Exception e) {
            map.put("result", "FAILED_UPDATE_BOARD");
        }

        return map;
    }

    @Override
    public Map<String, String> postBoard(Board board) {
        Map<String, String> map = new HashMap<>();

        try {
            String content = board.getBoardDetail();
            board.setBoardDetail(content.replaceAll("\n", "<br>"));
            boardRepository.insertBoard(board);
            System.out.println(board.getBoardIdx());
            if(board.getFiles() != null) {
                List<String> fileNames = fileRepository.save(board.getFiles());

                for (String fileName : fileNames) {
                    boardRepository.saveFileName(board.getBoardIdx(), fileName);
                }
            }

            map.put("result", "ADD_BOARD_COMPLETE");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", "FAILED_ADD_BOARD");
        }

        return map;
    }

    @Override
    public Map<String, String> deleteBoard(int boardIdx) {
        Map<String, String> map = new HashMap<>();

        try {
            boardRepository.deleteBoard(boardIdx);
            map.put("result", "DELETE_BOARD_COMPLETE");
        } catch (Exception e) {
            map.put("result", "FAILED_DELETE_BOARD");
        }

        return map;
    }

    @Override
    public List<Board> getBoardListByPageNum(int pageNumber) {
        List<Board> limitData = boardRepository.getBoardListByPageNum(pageNumber * 5);
        return limitData.subList(pageNumber * 5 - 5, limitData.size());
    }

    @Override
    public List<Integer> getPageNumbers() {
        return PageNationUtil.getPageNationNumbers(boardRepository.getAllBoard(), 5);
    }

    @Override
    public List<Board> getBoardByTitle(String content) {
        if(content.isBlank()) {
            return new ArrayList<>();
        }
        return boardRepository.findByTitleLIKE(content);
    }

    @Override
    public List<Board> getBoardByDate(String date) {
        return boardRepository.findByCreatAt(date);
    }

    @Override
    public List<String> getAllFileNameByBoardIdx(int boardIdx) {
        System.out.println(boardIdx);
        return boardRepository.getAllFileNameByBoardIdx(boardIdx);
    }
}
