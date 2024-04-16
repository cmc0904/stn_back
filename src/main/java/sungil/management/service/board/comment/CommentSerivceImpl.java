package sungil.management.service.board.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sungil.management.dto.board.CommentDTO;
import sungil.management.execption.CreateFailedExecption;
import sungil.management.repository.board.comment.CommentRepository;
import sungil.management.vo.board.CommentVO;
import sungil.management.vo.etc.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentSerivceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Autowired
    public CommentSerivceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Result addComment(CommentDTO commentDTO) throws CreateFailedExecption {
        try {
            commentRepository.insertComment(commentDTO);
            return new Result("ADD_COMMENT_COMPLETE");
        } catch (Exception e) {
            throw new CreateFailedExecption();
        }

    }

    @Override
    public List<CommentVO> getCommentByBoardIdx(int boardIdx) {
        return commentRepository.getCommnetByBoardIdx(boardIdx);
    }
}
