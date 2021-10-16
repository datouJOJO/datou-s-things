package MyWebServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * ת��hashmap������
 * ��дurl_parttern---->servlet name---->class
 */
public class WebContext {
	private List<Entity>entities;
	private List<ServletMapping>mappings;
	
	//url_parttern---->servlet name
	private Map<String,String>entitiesMap = new HashMap<String,String>();
	//servlet name---->class
	private Map<String,String>mappingMap = new HashMap<String,String>();
	public WebContext(List<Entity> entities, List<ServletMapping> mappings) {
		super();
		//��ֵ��ʼ��
		this.entities = entities;
		this.mappings = mappings;
		
		//��entityת�ɶ�Ӧ��map
		for(Entity entity:entities) {
			entitiesMap.put(entity.getName(),entity.getCls());
		}
		//��mappingת�ɶ�Ӧ��map
		//һ��servlet���ܶ�Ӧ���urlParttern
		//���ڻ�����һ��һ
		for(ServletMapping mapping:mappings) {
			for(String pattern:mapping.getPatterns()) {
				mappingMap.put(pattern, mapping.getName());
			}
		}
	}
	
	//url_parttern---->servlet name---->class
	//�õ�class֮�� Ȼ����ͨ������
	public String getCls(String parttern) {
		String name = mappingMap.get(parttern);
		return entitiesMap.get(name);
	}
}
