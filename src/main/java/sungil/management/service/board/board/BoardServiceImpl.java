package sungil.management.service.board.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sungil.management.dto.board.BoardDTO;
import sungil.management.execption.CreateFailedExecption;
import sungil.management.execption.DeleteFailedExecption;
import sungil.management.execption.UpdateFailedExecption;
import sungil.management.repository.board.board.BoardRepository;

import sungil.management.repository.file.FileRepository;
import sungil.management.vo.board.BoardVO;
import sungil.management.vo.etc.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;


    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository, FileRepository fileRepository) {
        this.boardRepository = boardRepository;
        this.fileRepository = fileRepository;
    }

    @Override
    public List<BoardVO> getBoardBy(String type, String content, int currentPage) {
        return boardRepository.getBoardBy(type, content, (currentPage - 1) * 5);
    }

    @Override
    public int getBoardCount(String type, String content) {
        return boardRepository.count(type, content);
    }

    @Override
    public BoardVO getBoardByBoardIdx(Integer boardIdx) {
        return boardRepository.getBoardByBoardIdx(boardIdx);
    }

    @Override
    public List<BoardVO> getBoardByWriterId(String writerId) {
        return boardRepository.getBoardByWriterId(writerId);
    }

    @Override
    public Result updateBoard(BoardDTO board) throws UpdateFailedExecption {

        try {
            boardRepository.updateBoard(board);

            return new Result("UPDATE_BOARD_COMPLETE");
        } catch (Exception e) {
            throw new UpdateFailedExecption();
        }

    }

    @Override
    @Transactional
    public Result postBoard(BoardDTO boardDTO) throws CreateFailedExecption {
        try {
            boardRepository.insertBoard(boardDTO);
            if(boardDTO.getFiles() != null) {
                List<String> fileNames = fileRepository.save(boardDTO.getFiles());

                for (String fileName : fileNames) {
                    boardRepository.saveFileName(boardDTO.getBoardIdx(), fileName);
                }
            }

            return new Result("ADD_BOARD_COMPLETE");
        } catch (Exception e) {
            throw new CreateFailedExecption();
        }

    }

    @Override
    public Result deleteBoard(int boardIdx) throws DeleteFailedExecption {
        try {
            boardRepository.deleteBoard(boardIdx);
            return new Result("DELETE_BOARD_COMPLETE");
        } catch (Exception e) {
            throw new DeleteFailedExecption();
        }

    }



    @Override
    public List<BoardVO> getBoardByTitle(String content) {
        if(content.isBlank()) {
            return new ArrayList<>();
        }
        return boardRepository.findByTitleLIKE(content);
    }

    @Override
    public List<BoardVO> getBoardByDate(String date) {
        return boardRepository.findByCreatAt(date);
    }

    @Override
    public List<String> getAllFileNameByBoardIdx(int boardIdx) {
        System.out.println(boardIdx);
        return boardRepository.getAllFileNameByBoardIdx(boardIdx);
    }

    @Override
    public Result changePrivate(int idx, int priv) throws UpdateFailedExecption {

        try {
            boardRepository.setPrivate(idx, priv);
            return new Result("EDIT_COMPLETE");
        } catch (Exception e) {
            throw new UpdateFailedExecption();
        }

    }

    @Override
    public List<BoardVO> getMyBoard(String userId) {
        return boardRepository.getMyBoards(userId);
    }

    @Override
    public Result read(String reader, Integer boardIdx) throws CreateFailedExecption {
        try {
            boardRepository.readBoard(boardIdx, reader);
            return new Result("READ_COMPLETE");
        } catch (Exception e) {
            throw new CreateFailedExecption();
        }
    }
}
