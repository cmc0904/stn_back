package sungil.management.repository.faq;

import org.apache.ibatis.annotations.Mapper;

import sungil.management.dto.faq.FaQDTO;
import sungil.management.vo.faq.FaQVO;

import java.util.List;
import java.util.Optional;

@Mapper
public interface FaQRepository {
    List<FaQVO> getAllFaQ();
    void insertFaQ(FaQDTO faQ);
    void deleteFaQ(int idx);
    void updateFaQ(FaQDTO faQ);
    FaQVO getFaQByIdx(int idx);
}
