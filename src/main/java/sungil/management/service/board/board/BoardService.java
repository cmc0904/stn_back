package sungil.management.service.board.board;

import sungil.management.dto.board.BoardDTO;
import sungil.management.execption.CreateFailedExecption;
import sungil.management.execption.DeleteFailedExecption;
import sungil.management.execption.UpdateFailedExecption;
import sungil.management.vo.board.BoardVO;
import sungil.management.vo.board.PostingDayForChartData;
import sungil.management.vo.etc.PageVO;
import sungil.management.vo.etc.Result;

import java.util.List;
import java.util.Map;

public interface BoardService {
    List<BoardVO> getBoardBy(String type, String content, int currentPage);
    int getBoardCount(String type, String content);
    BoardVO getBoardByBoardIdx(Integer boardIdx);
    List<BoardVO> getBoardByWriterId(String writerId);
    Result updateBoard(BoardDTO board) throws UpdateFailedExecption;
    Result postBoard(BoardDTO boardDTO) throws CreateFailedExecption;
    Result deleteBoard(int boardIdx) throws DeleteFailedExecption;
    List<BoardVO> getBoardByTitle(String content);
    List<BoardVO> getBoardByDate(String date);
    List<String> getAllFileNameByBoardIdx(int boardIdx);
    Result changePrivate(int idx, int priv) throws UpdateFailedExecption;
    List<BoardVO> getMyBoard(String userId);
    Result read(String reader, Integer boardIdx) throws CreateFailedExecption;
    PageVO<PostingDayForChartData> getDataForChart(Integer currentPage);

}