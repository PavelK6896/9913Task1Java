package app.web.pavelk.task1.model.init;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class UploadFile {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private UploadFile() {
        throw new IllegalStateException("Utility class");
    }

    public static LoadJson getFile() throws IOException {
        return objectMapper.readValue(new File("json.json"), LoadJson.class);
    }
}
