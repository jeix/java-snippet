package modern.java21.file;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class TextFileWriter {

	private BufferedWriter out;

	public void open(String dir_path, String file_name) throws IOException {
		Path path = Path.of(dir_path, file_name);
		try {
			out = Files.newBufferedWriter(path, StandardCharsets.UTF_8,
					StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			close(true);
			throw e;
		}
	}

	public void write_line(String line) throws IOException {
		try {
			out.write(line);
			out.newLine();
		} catch (IOException e) {
			close(true);
			throw e;
		}
	}

	public void close() throws IOException {
		try {
			out.flush();
		} catch (IOException e) {
			close(true);
			throw e;
		}
		close(false);
	}

	public void close(boolean quiet) throws IOException {
		if (out != null) {
			try {
				out.close();
			} catch (IOException e) {
				if (! quiet) throw e;
			} finally {
				out = null;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		TextFileWriter writer = new TextFileWriter();
		writer.open(".", "file.txt");
		writer.write_line("고구마");
		writer.write_line("고등어");
		writer.write_line("고사리");
		writer.close();
	}
}
