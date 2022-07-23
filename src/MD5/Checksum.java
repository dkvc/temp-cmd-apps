package MD5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Checksum {
    static String checksum(MessageDigest md, File file) throws IOException {
        // File Input Stream for reading file content
        FileInputStream fis = new FileInputStream(file);

        // Byte array for reading data in chunks
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        // Read file data
        while ((bytesCount = fis.read(byteArray)) != -1) {
            md.update(byteArray, 0, bytesCount);
        }

        // Close file input stream
        fis.close();

        // Store bytes
        byte[] bytes = md.digest();

        // Array of bytes are currently in decimal format
        // Conversion into hexadecimal format
        StringBuilder sb = new StringBuilder();

        for (byte b : bytes) {
            sb.append(Integer.toString((b & 0xff) + 0x100, 16)
                             .substring(1));
        }

        return sb.toString();
    }

    static String read(File file) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return Checksum.checksum(md, file);
    }
}