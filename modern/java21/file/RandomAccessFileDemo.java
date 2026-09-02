package modern.java21.file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class RandomAccessFileDemo {

    private void test_java_io_RandomAccessFile() {
        Path path = Path.of("raf_test.txt");
        try {
            Files.deleteIfExists(path);
            try (SeekableByteChannel channel = Files.newByteChannel(
                    path,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.READ,
                    StandardOpenOption.WRITE)) {
                channel.write(ByteBuffer.wrap("123\n456\n789".getBytes()));
                print_file_pointer_offset(channel); // -> 11
                channel.position(0L);
                print_file_pointer_offset(channel); // -> 0
                channel.write(ByteBuffer.wrap("276".getBytes()));
                print_file_pointer_offset(channel); // -> 3
                channel.position(channel.size() - 3);
                print_file_pointer_offset(channel); // -> 8
                channel.write(ByteBuffer.wrap("438".getBytes()));
                print_file_pointer_offset(channel); // -> 11
                channel.position(4L);
                print_file_pointer_offset(channel); // -> 4
                channel.write(ByteBuffer.wrap("951".getBytes()));
                print_file_pointer_offset(channel); // -> 7
                channel.position(0L);
                print_file_pointer_offset(channel); // -> 0
                ByteBuffer buffer = ByteBuffer.allocate((int) channel.size());
                channel.read(buffer);
                print_file_pointer_offset(channel); // -> 11
                System.out.write(buffer.array(), 0, buffer.limit()); // -> 276 951 438
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void print_file_pointer_offset(SeekableByteChannel channel) throws IOException {
        System.out.println("fp @ " + channel.position());
    }

    private void test_nothing() {
        System.out.println(":wq");
    }

    public void test() {
        test_java_io_RandomAccessFile();
        test_nothing();
    }

    public static void main(String[] args) {
        RandomAccessFileDemo worker = new RandomAccessFileDemo();
        worker.test();
    }
}