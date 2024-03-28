package sungil.management.repository.board.comment;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import sungil.management.domain.Comment;

import java.util.Date;
import java.util.List;

@Mapper
public interface MyBatisCommentRepository extends CommentRepository{

    @Select("Select bc.idx, bc.boardIdx, bc.comment, u.name as writerId, bc.createAt as createAt from stn_board_comment bc, stn_users u where bc.boardIdx = #{boardIdx} and bc.writerId = u.userId")
    List<Comment> getCommnetByBoardIdx(Integer boardIdx);
    @Insert("insert into stn_board_comment(boardIdx, comment, writerId, createAt) values(#{boardIdx}, #{comment}, #{writerId}, NOW())")
    void insertComment(Comment comment);
}
