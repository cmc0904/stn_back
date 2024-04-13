package sungil.management.service.faq;

import sungil.management.domain.FaQ;
import sungil.management.domain.Result;

import java.util.List;
import java.util.Map;

public interface FaQService {
    Result addFaQ(FaQ faQ);
    Result updateFaQ(FaQ faQ);
    Result deleteFaQByIdx(int idx);
    List<FaQ> getAllFaQ();
    FaQ getFaQByIdx(int idx);

}
