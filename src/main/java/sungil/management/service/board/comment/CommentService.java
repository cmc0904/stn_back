package sungil.management.service.board.comment;


import sungil.management.dto.board.CommentDTO;
import sungil.management.vo.board.CommentVO;

import java.util.List;
import java.util.Map;

public interface CommentService {

    Map<String, String> addComment(CommentDTO commentDTO);

    List<CommentVO> getCommentByBoardIdx(int boardIdx);

}
