package MyWebServer;

import java.util.logging.Handler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/*
 * �������xml
 * ����ͨ��������url��������Ӧ��servlet
 */
public class WebApp {
	static WebHandle handler;
	static WebContext wc;
	static {
		//SAX����
		try {
			//SAX����
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			handler = new WebHandle();
			parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream(
					"WebContent/WEB-INF/web.xml"), handler);
			wc = new WebContext(handler.getEntitys(), handler.getMappings());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static MyServlet getServletFromUrl(String url) {
		String className = wc.getCls("/"+url);
		Class cls;
		try {
			cls = Class.forName(className);
			MyServlet servlet = (MyServlet)cls.getConstructor().newInstance();
			System.out.println(servlet);
			return servlet;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
