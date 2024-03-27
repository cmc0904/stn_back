package sungil.management.repository.board.board;

import sungil.management.domain.Board;

import java.util.List;

public interface BoardRepository {

    List<Board> getAllBoard();

    Board getBoardByBoardIdx(Integer boardIdx);

    List<Board> getBoardByWriterId(String writerId);

    void updateBoard(Board board);

    void insertBoard(Board board);

    void deleteBoard(int boardIdx);
    List<Board> getBoardListByPageNum(int limit);
    List<Board> findByTitleLIKE(String findContent);

    List<Board> findByCreatAt(String date);


}
