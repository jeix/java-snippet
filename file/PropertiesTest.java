package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTest {
	
	private Properties props;
	
	private PropertiesTest(Properties props) {
		this.props = props;
	}
	
	public static PropertiesTest load(String file_name) {
		FileInputStream fis = open_input_stream(file_name);
		if (fis == null) return null;
		Properties props = new Properties();
		try {
			props.load(fis);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		}
		try {
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		}
		return new PropertiesTest(props);
	}
	
	public static PropertiesTest load_from_xml(String file_name) {
		FileInputStream fis = open_input_stream(file_name);
		if (fis == null) return null;
		Properties props = new Properties();
		try {
			props.loadFromXML(fis);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		}
		try {
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		}
		return new PropertiesTest(props);
	}
	
	private static FileInputStream open_input_stream(String file_name) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file_name);
		} catch (FileNotFoundException fnfe) {
			if (touch_new_prop_file(file_name)) {
				try {
					fis = new FileInputStream(file_name);
				} catch (FileNotFoundException fnfe2) {
					fnfe2.printStackTrace();
					return null;
				}
			} else {
				return null;
			}
		}
		return fis;
	}
	
	private static boolean touch_new_prop_file(String file_name) {
		File f = new File(file_name);
		try {
			f.createNewFile();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
		if (file_name.endsWith(".xml")) {
			try {
				FileWriter writer = new FileWriter(f);
				writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				writer.write("<!DOCTYPE properties SYSTEM \"http://java.sun.com/dtd/properties.dtd\">");
				writer.write("<properties version=\"1.0\"></properties>");
				writer.close();
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
		FileOutputStream fos = open_output_stream(file_name);
		if (fos == null) return;
		try {
			props.store(fos, comment);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return;
		}
		try {
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return;
		}
	}
	
	public void store_to_xml(String file_name) {
		store_to_xml(file_name, null);
	}
	public void store_to_xml(String file_name, String comment) {
		FileOutputStream fos = open_output_stream(file_name);
		if (fos == null) return;
		try {
			props.storeToXML(fos, comment);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return;
		}
		try {
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return;
		}
	}
	
	private static FileOutputStream open_output_stream(String file_name) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file_name);
		} catch (FileNotFoundException fnfe) {
			if (touch_new_prop_file(file_name)) {
				try {
					fos = new FileOutputStream(file_name);
				} catch (FileNotFoundException fnfe2) {
					fnfe2.printStackTrace();
					return null;
				}
			} else {
				return null;
			}
		}
		return fos;
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
