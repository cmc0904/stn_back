package sungil.management.repository.board.comment;

import org.apache.ibatis.annotations.Mapper;

import sungil.management.dto.board.CommentDTO;
import sungil.management.vo.board.CommentVO;

import java.util.List;

@Mapper
public interface CommentRepository {

    List<CommentVO> getCommnetByBoardIdx(Integer idx);

    void insertComment(CommentDTO commentDTO);
}

