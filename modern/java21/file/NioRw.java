package modern.java21.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class NioRw {

    public static byte[] nio_read(String file_name) throws IOException {
        Path path = Path.of(file_name);
        return Files.readAllBytes(path);
    }

    public static String nio_read_text(String file_name) throws IOException {
        Path path = Path.of(file_name);
        return Files.readString(path);
    }

    public static String nio_read_text(String file_name, String charset) throws IOException {
        Path path = Path.of(file_name);
        return Files.readString(path, java.nio.charset.Charset.forName(charset));
    }

    public static void nio_write(String file_name, byte[] buf) throws IOException {
        Path path = Path.of(file_name);
        Files.write(path, buf, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
    }

    public static void nio_write(String file_name, String s) throws IOException {
        Path path = Path.of(file_name);
        Files.writeString(path, s, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
    }

    public static void nio_write(String file_name, String s, String charset) throws IOException {
        Path path = Path.of(file_name);
        Files.writeString(path, s, java.nio.charset.Charset.forName(charset), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
    }

    public static byte[] nio_mapped_read(String file_name) throws IOException {
        Path path = Path.of(file_name);
        return Files.readAllBytes(path);
    }

    public static String nio_mapped_read_text(String file_name) throws IOException {
        Path path = Path.of(file_name);
        return Files.readString(path);
    }

    public static String nio_mapped_read_text(String file_name, String charset) throws IOException {
        Path path = Path.of(file_name);
        return Files.readString(path, java.nio.charset.Charset.forName(charset));
    }

    public static void nio_mapped_write(String file_name, byte[] buf) throws IOException {
        Path path = Path.of(file_name);
        Files.write(path, buf, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
    }

    public static void nio_mapped_write(String file_name, String s) throws IOException {
        Path path = Path.of(file_name);
        Files.writeString(path, s, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
    }

    public static void nio_mapped_write(String file_name, String s, String charset) throws IOException {
        Path path = Path.of(file_name);
        Files.writeString(path, s, java.nio.charset.Charset.forName(charset), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
    }

    public static void main(String[] args) throws Exception {
        if (3 == args.length) {
            if ("b".equals(args[0])) {
                NioRw.nio_write(args[2], NioRw.nio_read(args[1]));
            } else if ("bm".equals(args[0])) {
                NioRw.nio_mapped_write(args[2], NioRw.nio_mapped_read(args[1]));
            } else if ("t".equals(args[0])) {
                NioRw.nio_write(args[2], NioRw.nio_read_text(args[1]));
            } else if ("tm".equals(args[0])) {
                NioRw.nio_mapped_write(args[2], NioRw.nio_mapped_read_text(args[1]));
            } else if ("tu".equals(args[0])) {
                NioRw.nio_write(args[2], NioRw.nio_read_text(args[1], "UTF-8"), "UTF-8");
            } else if ("tum".equals(args[0])) {
                NioRw.nio_mapped_write(args[2], NioRw.nio_mapped_read_text(args[1], "UTF-8"), "UTF-8");
            }
        } else {
            System.out.println("java NioRw mode infile outfile");
            System.out.println("  - mode");
            System.out.println("    b   binary");
            System.out.println("    bm  binary, mapped");
            System.out.println("    t   text");
            System.out.println("    tm  text, mapped");
            System.out.println("    tu  text, utf-8");
            System.out.println("    tum text, utf-8, mapped");
        }
    }
}