package file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TextFileWriter {

	private BufferedWriter out;

	public void open(String dir_path, String file_name) throws IOException {
		File f = new File(dir_path, file_name);
		try {
			out = new BufferedWriter(new FileWriter(f, false));
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