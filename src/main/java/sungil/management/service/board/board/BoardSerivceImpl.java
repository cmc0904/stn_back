package sungil.management.service.board.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sungil.management.domain.Board;
import sungil.management.domain.ResponseDto;
import sungil.management.domain.User;
import sungil.management.repository.board.board.BoardRepository;
import sungil.management.utils.PageNationUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardSerivceImpl implements BoardSerivce {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardSerivceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
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
            boardRepository.insertBoard(board);
            map.put("result", "ADD_BOARD_COMPLETE");
        } catch (Exception e) {
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
}
