package modern.java21.file;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextFileWriter implements AutoCloseable {

	private BufferedWriter out;

	public void open(String dir_path, String file_name) throws IOException {
		close();
		out = Files.newBufferedWriter(Path.of(dir_path, file_name), StandardCharsets.UTF_8);
	}

	public void write_line(String line) throws IOException {
		if (out == null) throw new IllegalStateException("Writer is not open");
		out.write(line);
		out.newLine();
	}

	@Override
	public void close() throws IOException {
		close(false);
	}

	public void close(boolean quiet) throws IOException {
		if (out == null) return;

		try {
			out.close();
		} catch (IOException exception) {
			if (!quiet) throw exception;
		} finally {
			out = null;
		}
	}

	public static void main(String[] args) throws IOException {
		try (var writer = new TextFileWriter()) {
			writer.open(".", "file.txt");
			writer.write_line("고구마");
			writer.write_line("고등어");
			writer.write_line("고사리");
		}
	}
}
