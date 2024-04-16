package sungil.management.service.faq;

import sungil.management.vo.etc.Result;
import sungil.management.dto.faq.FaQDTO;
import sungil.management.vo.faq.FaQVO;

import java.util.List;

public interface FaQService {
    Result addFaQ(FaQDTO faQDTO);
    Result updateFaQ(FaQDTO faQDTO);
    Result deleteFaQByIdx(int idx);
    List<FaQVO> getAllFaQ();
    FaQVO getFaQByIdx(int idx);

}
