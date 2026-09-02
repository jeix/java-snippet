package modern.java21.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextFileReader {

    private BufferedReader in;

    public void open(String dir_path, String file_name) throws IOException {
        Path path = Path.of(dir_path, file_name);
        in = Files.newBufferedReader(path);
    }

    public String read_line() throws IOException {
        if (in == null) {
            throw new IllegalStateException("File not opened. Call open() first.");
        }
        return in.readLine();
    }

    public void close() throws IOException {
        if (in != null) {
            in.close();
            in = null;
        }
    }

    public static void main(String[] args) throws Exception {
        TextFileReader reader = new TextFileReader();
        reader.open(".", "file.txt");
        String line = null;
        while ((line = reader.read_line()) != null) {
            System.out.println("[" + line + "]");
        }
        reader.close();
    }
}