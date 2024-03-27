package sungil.management.repository.board.board;

import org.apache.ibatis.annotations.*;
import sungil.management.domain.Board;

import java.util.List;

@Mapper
public interface MyBatisBoardRepository extends BoardRepository {

    @Select("SELECT idx as boardIdx, title as boardTitle, detail as boardDetail, writerId, createAt FROM stn_board order by createAt desc")
    List<Board> getAllBoard();
    @Select("SELECT idx as boardIdx, title as boardTitle, detail as boardDetail, writerId, createAt FROM stn_board where idx = #{boardIdx}")
    Board getBoardByBoardIdx(Integer boardIdx);

    @Select("Select idx as boardIdx, title as boardTitle, detail as boardDetail, writerId, createAt from stn_board where writerId = #{writerId}")
    List<Board> getBoardByWriterId(String writerId);

    @Update("update stn_board set title = #{boardTitle}, detail = #{boardDetail} where idx = #{boardIdx}")
    void updateBoard(Board board);

    @Insert("insert into stn_board (title, detail, writerId, createAt) values (#{boardTitle}, #{boardDetail}, #{writerId}, NOW())")
    void insertBoard(Board board);

    @Delete("delete from stn_board where idx = #{boardIdx}")
    void deleteBoard(int boardIdx);

    @Select("SELECT idx as boardIdx, title as boardTitle, detail as boardDetail, writerId, createAt FROM stn_board order by createAt desc limit #{limit}")
    List<Board> getBoardListByPageNum(int limit);

    @Select("SELECT idx as boardIdx, title as boardTitle, detail as boardDetail, writerId, createAt FROM stn_board WHERE title LIKE CONCAT(#{findContent}, '%') order by createAt desc")
    List<Board> findByTitleLIKE(String findContent);
    @Select("SELECT idx as boardIdx, title as boardTitle, detail as boardDetail, writerId, createAt FROM stn_board WHERE DATE_FORMAT(createAt, '%Y-%m-%d') = #{date} order by createAt desc")
    List<Board> findByCreatAt(String date);
}
