package sungil.management.service.faq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public Result addFaQ(FaQDTO faQDTO) {
        try {
            faQRepository.insertFaQ(faQDTO);
            return new Result("ADD_FAQ_COMPLETE");
        } catch (Exception e) {
            return new Result("FAILED_ADD_FAQ");
        }


    }

    @Override
    public Result updateFaQ(FaQDTO faQDTO) {
        try {
            faQRepository.updateFaQ(faQDTO);
            return new Result("UPDATE_FAQ_COMPLETE");

        } catch (Exception e) {
            return new Result("FAILED_UPDATE_FAQ");
        }
    }

    @Override
    public Result deleteFaQByIdx(int idx) {

        try {
            faQRepository.deleteFaQ(idx);
            return new Result("DELETE_FAQ_COMPLETE");

        } catch (Exception e) {
            return new Result("FAILED_DELETE_FAQ");
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
