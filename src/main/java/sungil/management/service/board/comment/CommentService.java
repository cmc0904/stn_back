package sungil.management.service.board.comment;


import sungil.management.dto.board.CommentDTO;
import sungil.management.execption.CreateFailedExecption;
import sungil.management.vo.board.CommentVO;
import sungil.management.vo.etc.Result;

import java.util.List;
import java.util.Map;

public interface CommentService {

    Result addComment(CommentDTO commentDTO) throws CreateFailedExecption;

    List<CommentVO> getCommentByBoardIdx(int boardIdx);

}
