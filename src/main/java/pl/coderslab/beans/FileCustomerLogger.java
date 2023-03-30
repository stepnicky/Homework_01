package pl.coderslab.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Component
@Qualifier("fileLogger")
public class FileCustomerLogger implements CustomerLogger {

    private String fileName;

    @Override
    public void log() {
        try(FileWriter writer = new FileWriter(fileName, true)) {
            writer.append(String.valueOf(LocalDateTime.now()))
                    .append(": Customer operation\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
