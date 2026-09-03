package modern.java21.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class PropertiesTest {

	private final Properties props;

	private PropertiesTest(Properties props) {
		this.props = props;
	}

	public static PropertiesTest load(String file_name) {
		Path path = Path.of(file_name);
		if (! Files.exists(path) && ! touch_new_prop_file(path)) return null;
		Properties props = new Properties();
		try (InputStream in = Files.newInputStream(path)) {
			props.load(in);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		}
		return new PropertiesTest(props);
	}

	public static PropertiesTest load_from_xml(String file_name) {
		Path path = Path.of(file_name);
		if (! Files.exists(path) && ! touch_new_prop_file(path)) return null;
		Properties props = new Properties();
		try (InputStream in = Files.newInputStream(path)) {
			props.loadFromXML(in);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		}
		return new PropertiesTest(props);
	}

	private static boolean touch_new_prop_file(Path path) {
		try {
			Files.createFile(path);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
		if (path.toString().endsWith(".xml")) {
			try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
				writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				writer.write("<!DOCTYPE properties SYSTEM \"http://java.sun.com/dtd/properties.dtd\">");
				writer.write("<properties version=\"1.0\"></properties>");
			} catch (IOException ioe) {
				ioe.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public void store(String file_name) {
		store(file_name, null);
	}
	public void store(String file_name, String comment) {
		Path path = Path.of(file_name);
		if (! Files.exists(path) && ! touch_new_prop_file(path)) return;
		try (OutputStream out = Files.newOutputStream(path)) {
			props.store(out, comment);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void store_to_xml(String file_name) {
		store_to_xml(file_name, null);
	}
	public void store_to_xml(String file_name, String comment) {
		Path path = Path.of(file_name);
		if (! Files.exists(path) && ! touch_new_prop_file(path)) return;
		try (OutputStream out = Files.newOutputStream(path)) {
			props.storeToXML(out, comment);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public String get(String key) {
		return props.getProperty(key);
	}
	public String get(String key, String dflt_val) {
		return props.getProperty(key, dflt_val);
	}
	public String put(String key, String val) {
		return (String) props.setProperty(key, val);
	}

	public static void main(String[] args) {
		PropertiesTest prop = null;
		String file_name = null;

		file_name = "property.cfg";
		prop = PropertiesTest.load(file_name);
		if (prop != null) {
			if (prop.get("a") == null) prop.put("a", "42");
			if (prop.get("b") == null) prop.put("b", "simple");
			if (prop.get("c") == null) prop.put("c", "<고구마>");
			prop.store(file_name, "as an identifying comment");
		}

		file_name = "property.xml";
		prop = PropertiesTest.load_from_xml(file_name);
		if (prop != null) {
			if (prop.get("a") == null) prop.put("a", "42");
			if (prop.get("b") == null) prop.put("b", "simple");
			if (prop.get("c") == null) prop.put("c", "<고구마>");
			prop.store_to_xml(file_name, "설명");
		}
	}
}
