package icet.edu.util;

import org.springframework.stereotype.Service;
import java.sql.Blob;
import java.sql.SQLException;

@Service
public class FileDataExtractor {

    public CommonFileSavedSimpleDataDTO toStringDataObject(CommonFileSavedBinaryDataDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Input DTO cannot be null");
        }

        String fileName = blobToString(dto.getFileName());
        String hash = blobToString(dto.getHash());
        String resourceUrl = blobToString(dto.getResourceUrl());
        String directory = dto.getDirectory();

        return new CommonFileSavedSimpleDataDTO(hash, directory, fileName, resourceUrl);
    }

    private String blobToString(Blob blob) {
        if (blob == null) return null;
        try {
            byte[] bytes = blob.getBytes(1, (int) blob.length());
            return new String(bytes);
        } catch (SQLException e) {
            throw new RuntimeException("Error while converting Blob to String", e);
        }
    }
}
