package sungil.management.repository.board.board;

import org.apache.ibatis.annotations.*;
import sungil.management.domain.Board;

import java.util.List;

@Mapper
public interface MyBatisBoardRepository extends BoardRepository {

    @Select("SELECT idx as boardIdx, title as boardTitle, detail as boardDetail, writerId, createAt FROM stn_board where private = 0 order by createAt desc")
    List<Board> getAllBoard();
    @Select("SELECT idx as boardIdx, title as boardTitle, detail as boardDetail, writerId, createAt FROM stn_board where idx = #{boardIdx} and private = 0")
    Board getBoardByBoardIdx(Integer boardIdx);

    @Select("Select idx as boardIdx, title as boardTitle, detail as boardDetail, writerId, createAt, `private` as isPrivate from stn_board where writerId = #{writerId} and private = 0")
    List<Board> getBoardByWriterId(String writerId);


    @Update("update stn_board set title = #{boardTitle}, detail = #{boardDetail} where idx = #{boardIdx} and private = 0")
    void updateBoard(Board board);

    @Insert("insert into stn_board (title, detail, writerId, createAt, private) values (#{boardTitle}, #{boardDetail}, #{writerId}, NOW(), #{isPrivate})")
    @Options(useGeneratedKeys = true, keyProperty = "boardIdx", keyColumn = "idx")
    void insertBoard(Board board);

    @Delete("delete from stn_board where idx = #{boardIdx}")
    void deleteBoard(int boardIdx);

    @Select("SELECT idx as boardIdx, title as boardTitle, detail as boardDetail, writerId, createAt FROM stn_board where private = 0 order by createAt desc limit #{limit}")
    List<Board> getBoardListByPageNum(int limit);

    @Select("SELECT idx as boardIdx, title as boardTitle, detail as boardDetail, writerId, createAt FROM stn_board WHERE title LIKE CONCAT('%', #{findContent}, '%') and private = 0 order by createAt desc")
    List<Board> findByTitleLIKE(String findContent);
    @Select("SELECT idx as boardIdx, title as boardTitle, detail as boardDetail, writerId, createAt FROM stn_board WHERE DATE_FORMAT(createAt, '%Y-%m-%d') = #{date} and private = 0 order by createAt desc")
    List<Board> findByCreatAt(String date);
    @Insert("insert into stn_board_files (boardIdx, fileName) values (#{boardIdx}, #{fileName})")
    void saveFileName(int boardIdx, String fileName);

    @Select("select fileName from stn_board_files where boardIdx = #{boardIdx}")
    List<String> getAllFileNameByBoardIdx(int boardIdx);

    @Update("update stn_board set private = #{isPrivate} where idx = #{boardIdx}")
    void setPrivate(int boardIdx, int isPrivate);


    @Select("Select idx as boardIdx, title as boardTitle, detail as boardDetail, writerId, createAt, `private` as isPrivate from stn_board where writerId = #{writerId}")
    List<Board> getMyBoards(String userId);
}
