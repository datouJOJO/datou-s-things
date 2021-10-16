package MyWebServer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.parser.Parser;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlParse {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		//SAX����
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		WebHandle handler = new WebHandle();
		parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream(
				"WebContent/WEB-INF/web.xml"), handler);
		
		}
	}
//		List<FilterConfig>fConfigs = handler.getFilterConfigs();
//		List<FilterMapping>filterMappings = handler.getFilterMappings();
//		List<Entity>entities = handler.getEntitys();
//		List<ServletMapping>mappings = handler.getMappings();
		
//		WebContext wc = new WebContext(entities,mappings);
//		String className = wc.getCls("/Login");
//		Class clz = Class.forName(className);
//		//����ӿڱ��
//		Myserlvet servlet =(Myserlvet)clz.getConstructor().newInstance();
//		servlet.service();
		
		
//		for(FilterMapping temp:filterMappings) {
//			System.out.println("name---->"+temp.getName()+"  patterns----->"+temp.getfPatterns());
//		}
//		for(FilterConfig temp:fConfigs) {
//			System.out.println("name---->"+temp.getName()+"  class----->"+temp.getCls());
//		}
//		for(Entity temp:entities) {
//			System.out.println("name------>"+temp.getName()+"  class----->"+temp.getCls());
//		}
//		for(ServletMapping temp:mappings) {
//			System.out.println("name----->"+temp.getName()+"  patterns----->"+temp.getPatterns());
//		}
//	}
//}

//class WebHandle extends DefaultHandler{
//	private List<Entity> Entitys;				//��servlet name--->class
//	private List<ServletMapping>mappings;		//��url parttern --->servlet name
//	private List<FilterConfig>filterConfigs;	//�� filter name ---->class
//	private List<FilterMapping>filterMappings;	//�� filter url parttern ---->filter name
//	
//	//serlet��filter
//	//�м����
//	private Entity entity;				
//	private ServletMapping mapping;
//	private FilterConfig filterConfig;
//	private FilterMapping filterMapping;
//	private String tag;
//	private boolean isMapping = false;		//�жϵ�ǰ�Ƿ���mapping
//	private boolean isFMapping = false;		//�жϵ�ǰ�Ƿ���filter��mapping
//	private boolean isFiter = false;
//	private boolean isSerlvet = false;
//	
//	@Override
//	public void startDocument() throws SAXException {
//		// TODO Auto-generated method stub
//		//��ʼ��
//		Entitys = new ArrayList<Entity>();
//		mappings = new ArrayList<ServletMapping>();
//		filterConfigs = new ArrayList<FilterConfig>();
//		filterMappings = new ArrayList<FilterMapping>();
//	}
//	//ȡ��element
//	@Override
//	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//		// TODO Auto-generated method stub
//		if(null!=qName) {
//			tag = qName;
//			if(tag.equals("servlet")) {
//				entity = new Entity();
//				isSerlvet = true;
//				isMapping = false;
//				isFiter = false;
//				isFMapping = false;
//			}else if(tag.equals("servlet-mapping")){
//				mapping = new ServletMapping();
//				isMapping = true;
//				isSerlvet = false;
//				isFiter = false;
//				isFMapping = false;
//			}else if(tag.equals("filter")){
//				filterConfig = new FilterConfig();
//				isFiter = true;
//				isSerlvet = false;
//				isMapping = false;
//				isFMapping = false;
//			}else if(tag.equals("filter-mapping")){
//				filterMapping = new FilterMapping();
//				isFMapping = true;
//				isSerlvet = false;
//				isMapping = false;
//				isFiter = false;
//			}
//		}
//	}
//	//ȡ������
//	@Override
//	public void characters(char[] ch, int start, int length) throws SAXException {
//		// TODO Auto-generated method stub
//		String content = new String(ch,start,length).trim();
//		if(null!=tag&&content.length()>0) {
//			if(isFMapping) {
//				if(tag.equals("filter-name")) {
//					
//					filterMapping.setName(content);
//				}
//				if(tag.equals("url-pattern")) {
//					filterMapping.addPattern(content);
//					filterMappings.add(filterMapping);
//				}
//			}else if(isMapping){
//				if(tag.equals("servlet-name")) {
//					mapping.setName(content);
//				}
//				if(tag.equals("url-pattern")) {
//					mapping.addPattern(content);
//					mappings.add(mapping);
//				}
//			}else if(isSerlvet){
//				if(tag.equals("servlet-name")) {
//					entity.setName(content);
//				}
//				if(tag.equals("servlet-class")) {
//					entity.setCls(content);
//					Entitys.add(entity);
//				}
//			}else if(isFiter) {
//				if(tag.equals("filter-name")) {
//					filterConfig.setName(content);
//				}
//				if(tag.equals("filter-class")) {
//					filterConfig.setCls(content);
//					filterConfigs.add(filterConfig);
//				}
//			}
//		}
//		
//	}
//	
//	@Override
//	public void endDocument() throws SAXException {
//		// TODO Auto-generated method stub
//		System.out.println("��������");
//	}
//
//	/*
//	 * ȡ����
//	 */
//	public List<Entity> getEntitys() {
//		return Entitys;
//	}
//	
//	public List<ServletMapping> getMappings() {
//		return mappings;
//	}
//	
//	public List<FilterConfig> getFilterConfigs() {
//		return filterConfigs;
//	}
//
//	public List<FilterMapping> getFilterMappings() {
//		return filterMappings;
//	}
//	
//}