package sungil.management.repository.board.board;

import org.apache.ibatis.annotations.Mapper;
import sungil.management.dto.board.BoardDTO;
import sungil.management.vo.board.BoardVO;
import sungil.management.vo.board.PostingDayForChartData;

import java.util.List;

@Mapper
public interface BoardRepository {

    List<BoardVO> getAllBoard();

    List<BoardVO> getBoardBy(String type, String content, int offset);

    int count(String type, String content);

    BoardVO getBoardByBoardIdx(Integer boardIdx);

    List<BoardVO> getBoardByWriterId(String writerId);

    void updateBoard(BoardDTO board);

    void insertBoard(BoardDTO boardDTO);

    void deleteBoard(int boardIdx);

    List<BoardVO> findByTitleLIKE(String findContent);

    List<BoardVO> findByCreatAt(String date);

    void saveFileName(int boardIdx, String fileName);
    List<String> getAllFileNameByBoardIdx(int boardIdx);

    void setPrivate(int boardIdx, int isPrivate);

    List<BoardVO> getMyBoards(String userId);

    void readBoard(int boardIdx, String reader);

    List<PostingDayForChartData> getPostingDataForChartData(Integer offset);

    int getPostingDataForChartDataLength();

}
