package sungil.management.service.board.board;

import sungil.management.dto.board.BoardDTO;
import sungil.management.vo.board.BoardVO;

import java.util.List;
import java.util.Map;

public interface BoardService {
    List<BoardVO> getBoardBy(String type, String content, int currentPage);
    int getBoardCount(String type, String content);

    BoardVO getBoardByBoardIdx(Integer boardIdx);
    List<BoardVO> getBoardByWriterId(String writerId);
    Map<String, String> updateBoard(BoardDTO board);
    Map<String, String> postBoard(BoardDTO boardDTO);
    Map<String, String> deleteBoard(int boardIdx);


    List<BoardVO> getBoardByTitle(String content);
    List<BoardVO> getBoardByDate(String date);

    List<String> getAllFileNameByBoardIdx(int boardIdx);

    Map<String, String> changePrivate(int idx, int priv);

    List<BoardVO> getMyBoard(String userId);

    Map<String, String> read(String reader, Integer boardIdx);

}