package sungil.management.repository.board.board;

import org.apache.ibatis.annotations.Mapper;
import sungil.management.domain.Board;

import java.util.List;

@Mapper
public interface BoardRepository {

    List<Board> getAllBoard();

    List<Board> getBoardBy(String type, String content, int offset);

    int count(String type, String content);

    Board getBoardByBoardIdx(Integer boardIdx);

    List<Board> getBoardByWriterId(String writerId);

    void updateBoard(Board board);

    void insertBoard(Board board);

    void deleteBoard(int boardIdx);
    List<Board> getBoardListByPageNum(int limit);
    List<Board> findByTitleLIKE(String findContent);

    List<Board> findByCreatAt(String date);

    void saveFileName(int boardIdx, String fileName);
    List<String> getAllFileNameByBoardIdx(int boardIdx);

    void setPrivate(int boardIdx, int isPrivate);

    List<Board> getMyBoards(String userId);

    void readBoard(int boardIdx, String reader);
}
