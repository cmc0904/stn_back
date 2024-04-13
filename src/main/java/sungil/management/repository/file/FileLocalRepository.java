package sungil.management.repository.file;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Repository
public class FileLocalRepository implements FileRepository {

    private static final String PATH = "C:\\Users\\user\\Desktop\\img\\";
    @Override
    public List<String> save(MultipartFile[] files) throws IOException {
        List<String> fileNames = new ArrayList<>();
        for(MultipartFile file : files) {
            String fileName = String.format("%s=%s", UUID.randomUUID().toString(), file.getOriginalFilename());
            fileNames.add(fileName);
            file.transferTo(new File(PATH + fileName));
        }

        return fileNames;
    }

    @Override
    public byte[] getFileByFileName(String fileName) throws IOException {
        Path filePath = Paths.get("\\home\\stninfo\\imgs\\"+fileName);

        return Files.readAllBytes(filePath);
    }
}
