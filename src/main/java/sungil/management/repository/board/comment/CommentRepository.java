package sungil.management.repository.board.comment;

import org.apache.ibatis.annotations.Mapper;
import sungil.management.domain.Comment;

import java.util.List;

@Mapper
public interface CommentRepository {

    List<Comment> getCommnetByBoardIdx(Integer idx);

    void insertComment(Comment comment);
}

