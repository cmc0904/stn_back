package sungil.management.service.faq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sungil.management.execption.CreateFailedExecption;
import sungil.management.execption.DeleteFailedExecption;
import sungil.management.execption.UpdateFailedExecption;
import sungil.management.vo.etc.Result;
import sungil.management.dto.faq.FaQDTO;
import sungil.management.repository.faq.FaQRepository;
import sungil.management.vo.faq.FaQVO;

import java.util.List;

@Service
public class FaQSerivceImpl implements FaQService{
    private final FaQRepository faQRepository;

    @Autowired
    public FaQSerivceImpl(FaQRepository faQRepository) {
        this.faQRepository = faQRepository;
    }

    @Override
    public Result addFaQ(FaQDTO faQDTO) throws CreateFailedExecption {
        try {
            faQRepository.insertFaQ(faQDTO);
            return new Result("ADD_FAQ_COMPLETE");
        } catch (Exception e) {
            throw new CreateFailedExecption();
        }


    }

    @Override
    public Result updateFaQ(FaQDTO faQDTO) throws UpdateFailedExecption {
        try {
            faQRepository.updateFaQ(faQDTO);
            return new Result("UPDATE_FAQ_COMPLETE");
        } catch (Exception e) {
            throw new UpdateFailedExecption();
        }
    }

    @Override
    public Result deleteFaQByIdx(int idx) throws DeleteFailedExecption {

        try {
            faQRepository.deleteFaQ(idx);
            return new Result("DELETE_FAQ_COMPLETE");
        } catch (Exception e) {
            throw new DeleteFailedExecption();
        }

    }

    @Override
    public List<FaQVO> getAllFaQ() {
        return faQRepository.getAllFaQ();
    }

    @Override
    public FaQVO getFaQByIdx(int idx) {
        return faQRepository.getFaQByIdx(idx);
    }
}
