package sungil.management.repository.file;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Mapper
public interface FileRepository {

    List<String> save(MultipartFile[] multipartFiles) throws IOException;

    byte[] getFileByFileName(String fileName) throws IOException;

}
