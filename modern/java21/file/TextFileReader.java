package modern.java21.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextFileReader implements AutoCloseable {

	private BufferedReader in;

	public void open(String dir_path, String file_name) throws IOException {
		close();
		in = Files.newBufferedReader(Path.of(dir_path, file_name), StandardCharsets.UTF_8);
	}

	public String read_line() throws IOException {
		if (in == null) throw new IllegalStateException("Reader is not open");
		return in.readLine();
	}

	@Override
	public void close() throws IOException {
		close(false);
	}

	public void close(boolean quiet) throws IOException {
		if (in == null) return;

		try {
			in.close();
		} catch (IOException exception) {
			if (!quiet) throw exception;
		} finally {
			in = null;
		}
	}

	public static void main(String[] args) throws IOException {
		try (var reader = new TextFileReader()) {
			reader.open(".", "file.txt");
			String line;
			while ((line = reader.read_line()) != null) {
				System.out.println("[" + line + "]");
			}
		}
	}
}
