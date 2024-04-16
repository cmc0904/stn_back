package sungil.management.service.board.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sungil.management.dto.board.BoardDTO;
import sungil.management.repository.board.board.BoardRepository;

import sungil.management.repository.file.FileRepository;
import sungil.management.vo.board.BoardVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;

    // 임시
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
        System.out.println( boardRepository.getBoardByWriterId(writerId));
        return boardRepository.getBoardByWriterId(writerId);
    }

    @Override
    public Map<String, String> updateBoard(BoardDTO board) {
        Map<String, String> map = new HashMap<>();

        try {
            boardRepository.updateBoard(board);

            map.put("result", "UPDATE_BOARD_COMPLETE");
        } catch (Exception e) {
            map.put("result", "FAILED_UPDATE_BOARD");
        }

        return map;
    }

    @Override
    @Transactional
    public Map<String, String> postBoard(BoardDTO boardDTO) {
        Map<String, String> map = new HashMap<>();

        try {
            boardRepository.insertBoard(boardDTO);
            if(boardDTO.getFiles() != null) {
                List<String> fileNames = fileRepository.save(boardDTO.getFiles());

                for (String fileName : fileNames) {
                    boardRepository.saveFileName(boardDTO.getBoardIdx(), fileName);
                }
            }

            map.put("result", "ADD_BOARD_COMPLETE");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", "FAILED_ADD_BOARD");
        }

        return map;
    }

    @Override
    public Map<String, String> deleteBoard(int boardIdx) {
        Map<String, String> map = new HashMap<>();

        try {
            boardRepository.deleteBoard(boardIdx);
            map.put("result", "DELETE_BOARD_COMPLETE");
        } catch (Exception e) {
            map.put("result", "FAILED_DELETE_BOARD");
        }

        return map;
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
    public Map<String, String> changePrivate(int idx, int priv) {
        Map<String, String> map = new HashMap<>();

        try {
            System.out.println(idx);
            System.out.println(priv);
            boardRepository.setPrivate(idx, priv);
            map.put("result", "EDIT_COMPLETE");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", "FAILED_EDIT");
        }

        return map;
    }

    @Override
    public List<BoardVO> getMyBoard(String userId) {
        return boardRepository.getMyBoards(userId);
    }

    @Override
    public Map<String, String> read(String reader, Integer boardIdx) {
        Map<String, String> map = new HashMap<>();
        try {
            boardRepository.readBoard(boardIdx, reader);
            map.put("result", "GREAT");

        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", "Failed");
        }
        return map;
    }
}
