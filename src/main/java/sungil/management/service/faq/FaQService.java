package sungil.management.service.faq;

import sungil.management.execption.CreateFailedExecption;
import sungil.management.execption.DeleteFailedExecption;
import sungil.management.execption.UpdateFailedExecption;
import sungil.management.vo.etc.Result;
import sungil.management.dto.faq.FaQDTO;
import sungil.management.vo.faq.FaQVO;

import java.util.List;

public interface FaQService {
    Result addFaQ(FaQDTO faQDTO) throws CreateFailedExecption;
    Result updateFaQ(FaQDTO faQDTO) throws UpdateFailedExecption;
    Result deleteFaQByIdx(int idx) throws DeleteFailedExecption;
    List<FaQVO> getAllFaQ();
    FaQVO getFaQByIdx(int idx);

}
