package sungil.management.service.board.board;

import sungil.management.domain.Board;
import sungil.management.domain.User;

import java.util.List;
import java.util.Map;

public interface BoardSerivce {
    List<Board> getAllBoard();
    Board getBoardByBoardIdx(Integer boardIdx);
    List<Board> getBoardByWriterId(String writerId);
    Map<String, String> updateBoard(Board board);
    Map<String, String> postBoard(Board board);
    Map<String, String> deleteBoard(int boardIdx);
    List<Board> getBoardListByPageNum(int pageNumber);
    List<Integer> getPageNumbers();


}