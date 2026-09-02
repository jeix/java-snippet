package modern.java21.file;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

public class PropertiesTest {

    private Properties props;

    private PropertiesTest(Properties props) {
        this.props = props;
    }

    public static PropertiesTest load(String file_name) {
        Path path = Path.of(file_name);
        try (InputStream is = Files.newInputStream(path, StandardOpenOption.READ)) {
            Properties props = new Properties();
            props.load(is);
            return new PropertiesTest(props);
        } catch (IOException ioe) {
            if (ioe instanceof java.nio.file.NoSuchFileException) {
                if (touch_new_prop_file(file_name)) {
                    return load(file_name);
                }
            }
            ioe.printStackTrace();
            return null;
        }
    }

    public static PropertiesTest load_from_xml(String file_name) {
        Path path = Path.of(file_name);
        try (InputStream is = Files.newInputStream(path, StandardOpenOption.READ)) {
            Properties props = new Properties();
            props.loadFromXML(is);
            return new PropertiesTest(props);
        } catch (IOException ioe) {
            if (ioe instanceof java.nio.file.NoSuchFileException) {
                if (touch_new_prop_file(file_name)) {
                    return load_from_xml(file_name);
                }
            }
            ioe.printStackTrace();
            return null;
        }
    }

    private static boolean touch_new_prop_file(String file_name) {
        Path path = Path.of(file_name);
        try {
            Files.createFile(path);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
        if (file_name.endsWith(".xml")) {
            try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8,
                    StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                writer.newLine();
                writer.write("<!DOCTYPE properties SYSTEM \"http://java.sun.com/dtd/properties.dtd\">");
                writer.newLine();
                writer.write("<properties version=\"1.0\"></properties>");
                writer.newLine();
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
        try (OutputStream os = Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
            props.store(os, comment);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void store_to_xml(String file_name) {
        store_to_xml(file_name, null);
    }

    public void store_to_xml(String file_name, String comment) {
        Path path = Path.of(file_name);
        try (OutputStream os = Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
            props.storeToXML(os, comment);
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