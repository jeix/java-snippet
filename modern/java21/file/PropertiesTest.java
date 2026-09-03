package modern.java21.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class PropertiesTest {

	private final Properties props;

	private PropertiesTest(Properties props) {
		this.props = props;
	}

	public static PropertiesTest load(String file_name) throws IOException {
		var path = prepare_file(file_name, false);
		var props = new Properties();
		try (var input = Files.newInputStream(path)) {
			props.load(input);
		}
		return new PropertiesTest(props);
	}

	public static PropertiesTest load_from_xml(String file_name) throws IOException {
		var path = prepare_file(file_name, true);
		var props = new Properties();
		try (var input = Files.newInputStream(path)) {
			props.loadFromXML(input);
		}
		return new PropertiesTest(props);
	}

	private static Path prepare_file(String file_name, boolean xml) throws IOException {
		var path = Path.of(file_name);
		if (Files.notExists(path)) {
			if (xml) {
				try (var output = Files.newOutputStream(path)) {
					new Properties().storeToXML(output, null);
				}
			} else {
				Files.createFile(path);
			}
		}
		return path;
	}

	public void store(String file_name) throws IOException {
		store(file_name, null);
	}

	public void store(String file_name, String comment) throws IOException {
		try (var output = Files.newOutputStream(Path.of(file_name))) {
			props.store(output, comment);
		}
	}

	public void store_to_xml(String file_name) throws IOException {
		store_to_xml(file_name, null);
	}

	public void store_to_xml(String file_name, String comment) throws IOException {
		try (var output = Files.newOutputStream(Path.of(file_name))) {
			props.storeToXML(output, comment);
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

	public static void main(String[] args) throws IOException {
		var file_name = "property.cfg";
		var prop = PropertiesTest.load(file_name);
		if (prop.get("a") == null) prop.put("a", "42");
		if (prop.get("b") == null) prop.put("b", "simple");
		if (prop.get("c") == null) prop.put("c", "<고구마>");
		prop.store(file_name, "as an identifying comment");

		file_name = "property.xml";
		prop = PropertiesTest.load_from_xml(file_name);
		if (prop.get("a") == null) prop.put("a", "42");
		if (prop.get("b") == null) prop.put("b", "simple");
		if (prop.get("c") == null) prop.put("c", "<고구마>");
		prop.store_to_xml(file_name, "설명");
	}
}
