package modern.java21;

// legacy/java8/lsc.java 를 대체해요.
// 이름 변경 이유: 소문자로 시작하는 클래스명(lsc)을 관례에 맞게 고쳤다.

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class LineSeparatorConverter {

	private static final String NEW_LINE_DOS = "\r\n";
	private static final String NEW_LINE_UNIX = "\n";

	public static void convert(String file, String mode) {
		Path path = Path.of(file);
		// ISO-8859-1(Latin-1)은 바이트 하나가 코드 포인트 하나에 그대로 대응돼서, 원본
		// 인코딩이 무엇이든 디코딩 후 다시 인코딩해도 바이트가 그대로 보존된다.
		String text;
		try {
			text = new String(Files.readAllBytes(path), StandardCharsets.ISO_8859_1);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		if (text.isEmpty()) return;

		String converted = switch (mode) {
			// 이미 DOS 개행인 자리를 건너뛰고 UNIX 개행만 바꾸도록, 먼저 전부 UNIX로
			// 통일한 다음에 DOS로 바꾼다 - 그래야 이미 "\r\n"인 자리가 "\r\r\n"으로
			// 겹치지 않는다.
			case "d2u" -> text.replace(NEW_LINE_DOS, NEW_LINE_UNIX);
			case "u2d" -> text.replace(NEW_LINE_DOS, NEW_LINE_UNIX).replace(NEW_LINE_UNIX, NEW_LINE_DOS);
			default -> text;
		};

		try {
			Files.write(path, converted.getBytes(StandardCharsets.ISO_8859_1));
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("java LineSeparatorConverter d2u file");
			System.out.println("java LineSeparatorConverter u2d file");
			return;
		}
		String mode = args[0];
		String file = args[1];
		if (! file.isEmpty() && ("d2u".equals(mode) || "u2d".equals(mode))) {
			LineSeparatorConverter.convert(file, mode);
		}
	}
}
