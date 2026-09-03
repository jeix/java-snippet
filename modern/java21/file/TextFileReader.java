package modern.java21.file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.stream.Stream;

public class TextFileReader {

	private Stream<String> lines;
	private Iterator<String> it;

	public void open(String dir_path, String file_name) throws IOException {
		Path path = Path.of(dir_path, file_name);
		try {
			lines = Files.lines(path, StandardCharsets.UTF_8);
			it = lines.iterator();
		} catch (IOException e) {
			close(true);
			throw e;
		}
	}

	public String read_line() {
		if (it != null && it.hasNext()) {
			return it.next();
		}
		return null;
	}

	public void close() throws IOException {
		close(false);
	}

	public void close(boolean quiet) throws IOException {
		if (lines != null) {
			lines.close();
			lines = null;
			it = null;
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
