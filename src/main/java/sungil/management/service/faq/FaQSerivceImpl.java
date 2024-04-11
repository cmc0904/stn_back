package sungil.management.service.faq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sungil.management.domain.FaQ;
import sungil.management.domain.Result;
import sungil.management.repository.faq.FaQRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FaQSerivceImpl implements FaQService{
    private final FaQRepository faQRepository;

    @Autowired
    public FaQSerivceImpl(FaQRepository faQRepository) {
        this.faQRepository = faQRepository;
    }

    @Override
    public Result addFaQ(FaQ faQ) {
        try {
            faQRepository.insertFaQ(faQ);
            return new Result("ADD_FAQ_COMPLETE");
        } catch (Exception e) {
            return new Result("FAILED_ADD_FAQ");
        }


    }

    @Override
    public Result updateFaQ(FaQ faQ) {
        try {
            faQRepository.updateFaQ(faQ);
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
    public List<FaQ> getAllFaQ() {
        return faQRepository.getAllFaQ();
    }

    @Override
    public FaQ getFaQByIdx(int idx) {
        return faQRepository.getFaQByIdx(idx);
    }
}
