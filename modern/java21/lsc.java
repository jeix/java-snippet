package modern.java21;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class lsc {

	private static final byte CR = 13;
	private static final byte NL = 10;
	private static final byte[] NEW_LINE_DOS = "\r\n".getBytes(StandardCharsets.UTF_8);
	private static final byte[] NEW_LINE_UNIX = "\n".getBytes(StandardCharsets.UTF_8);

	public static void convert(String file, String mode) throws IOException {
		var path = Path.of(file);
		var source = Files.readAllBytes(path);
		if (source.length == 0) return;

		var converted = switch (mode) {
			case "d2u" -> crnl2nl(source);
			case "u2d" -> nl2crnl(source);
			default -> throw new IllegalArgumentException("Unknown mode: " + mode);
		};
		Files.write(path, converted);
	}

	private static byte[] crnl2nl(byte[] source) {
		var output = new ByteArrayOutputStream(source.length);
		for (int i = 0; i < source.length; i++) {
			if (source[i] == CR && i + 1 < source.length && source[i + 1] == NL) {
				output.writeBytes(NEW_LINE_UNIX);
				i++;
			} else {
				output.write(source[i]);
			}
		}
		return output.toByteArray();
	}

	private static byte[] nl2crnl(byte[] source) {
		var output = new ByteArrayOutputStream(source.length);
		for (int i = 0; i < source.length; i++) {
			if (source[i] == NL && (i == 0 || source[i - 1] != CR)) {
				output.writeBytes(NEW_LINE_DOS);
			} else {
				output.write(source[i]);
			}
		}
		return output.toByteArray();
	}

	public static void main(String[] args) throws IOException {
		if (args.length != 2 || !("d2u".equals(args[0]) || "u2d".equals(args[0]))) {
			System.out.println("java lsc d2u file");
			System.out.println("java lsc u2d file");
			return;
		}
		lsc.convert(args[1], args[0]);
	}
}
