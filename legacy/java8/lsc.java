
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class lsc {
	
	public static void convert(String file, String mode) {
		byte[] bytes = read(file);
		if (bytes.length > 0) {
			if ("d2u".equals(mode)) {
				write(file, crnl2nl(bytes));
			} else if ("u2d".equals(mode)) {
				write(file, nl2crnl(bytes));
			}
		}
	}
	
	private static final byte CR = (byte) 13;
	private static final byte NL = (byte) 10;
	private static final String NEW_LINE_DOS = "\r\n";
	private static final String NEW_LINE_UNX = "\n";
	
	private static byte[] append(byte[] dst, byte[] src, int src_offset, int src_length) {
		byte[] buf = (byte[]) java.lang.reflect.Array.newInstance(byte.class, dst.length + src_length);
		System.arraycopy(dst, 0, buf, 0, dst.length);
		System.arraycopy(src, src_offset, buf, dst.length, src_length);
		return buf;
	}
	
	private static byte[] crnl2nl(byte[] src) {
		byte[] dst = {};
		int offset = 0;
		int length = 0;
		for (int i = 1; i < src.length; i++) {
			if (src[i] == NL && src[i-1] == CR) {
				length = i - 1 - offset;
				dst = append(dst, src, offset, length);
				dst = append(dst, NEW_LINE_UNX.getBytes(), 0, NEW_LINE_UNX.length());
				offset = i + 1;
			}
		}
		if (offset < src.length) {
			dst = append(dst, src, offset, src.length - offset);
		}
		return dst;
	}
	
	private static byte[] nl2crnl(byte[] src) {
		byte[] dst = {};
		int offset = 0;
		if (src[0] == NL) {
			dst = append(dst, NEW_LINE_DOS.getBytes(), 0, NEW_LINE_DOS.length());
			offset = 1;
		}
		int length = 0;
		for (int i = 1; i < src.length; i++) {
			if (src[i] == NL && src[i-1] != CR) {
				length = i - offset;
				dst = append(dst, src, offset, length);
				dst = append(dst, NEW_LINE_DOS.getBytes(), 0, NEW_LINE_DOS.length());
				offset = i + 1;
			}
		}
		if (offset < src.length) {
			dst = append(dst, src, offset, src.length - offset);
		}
		return dst;
	}
	
	private static byte[] read(String fname) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fname);
		} catch (FileNotFoundException fnfe) {
		}
		BufferedInputStream in = new BufferedInputStream(fis);
		byte[] dst = {};
		byte[] buf = new byte[256];
		int read_size = 0;
		try {
			while ((read_size = in.read(buf)) != -1) {
				dst = append(dst, buf, 0, read_size);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return new byte[] {};
		} finally {
			try {
				in.close();
			} catch (IOException ioe) {
			}
		}
		return dst;
	}
	
	private static void write(String fname, byte[] txt) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fname, false);
		} catch (FileNotFoundException fnfe) {
		}
		BufferedOutputStream out = new BufferedOutputStream(fos);
		try {
			out.write(txt, 0, txt.length);
			out.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException ioe) {
			}
		}
	}
	
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("java lsc d2u file");
			System.out.println("java lsc u2d file");
			return;
		}
		String mode = args[0];
		String file = args[1];
		if (file.length() > 0) {
			if ("d2u,u2d".contains(mode)) {
				lsc.convert(file, mode);
			}
		}
	}
}