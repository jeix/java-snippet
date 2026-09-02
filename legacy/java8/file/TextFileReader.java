package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TextFileReader {

	private BufferedReader in;

	public void open(String dir_path, String file_name) throws IOException {
		File f = new File(dir_path, file_name);
		try {
			in = new BufferedReader(new FileReader(f));
			//out.close(); // TODO DeleteMe
		} catch (IOException e) {
			close(true);
			throw e;
		}
	}

	public String read_line() throws IOException {
		try {
			if (in.ready()) {
				return in.readLine();
			}
		} catch (IOException e) {
			close(true);
			throw e;
		}
		return null;
	}

	public void close() throws IOException {
		close(false);
	}

	public void close(boolean quiet) throws IOException {
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				if (! quiet) throw e;
			} finally {
				in = null;
			}
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