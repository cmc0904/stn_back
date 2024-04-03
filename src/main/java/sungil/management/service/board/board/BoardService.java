package sungil.management.service.board.board;

import sungil.management.domain.Board;

import java.util.List;
import java.util.Map;

public interface BoardService {
    List<Board> getAllBoard();
    Board getBoardByBoardIdx(Integer boardIdx);
    List<Board> getBoardByWriterId(String writerId);
    Map<String, String> updateBoard(Board board);
    Map<String, String> postBoard(Board board);
    Map<String, String> deleteBoard(int boardIdx);
    List<Board> getBoardListByPageNum(int pageNumber);
    List<Integer> getPageNumbers();
    List<Board> getBoardByTitle(String content);
    List<Board> getBoardByDate(String date);

    List<String> getAllFileNameByBoardIdx(int boardIdx);

    Map<String, String> changePrivate(int idx, int priv);

    List<Board> getMyBoard(String userId);

}