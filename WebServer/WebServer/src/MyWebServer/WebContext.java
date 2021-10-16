package MyWebServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 转成hashmap更好找
 * 先写url_parttern---->servlet name---->class
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
		//传值初始化
		this.entities = entities;
		this.mappings = mappings;
		
		//将entity转成对应的map
		for(Entity entity:entities) {
			entitiesMap.put(entity.getName(),entity.getCls());
		}
		//将mapping转成对应的map
		//一个servlet可能对应多个urlParttern
		//现在化成了一对一
		for(ServletMapping mapping:mappings) {
			for(String pattern:mapping.getPatterns()) {
				mappingMap.put(pattern, mapping.getName());
			}
		}
	}
	
	//url_parttern---->servlet name---->class
	//得到class之后 然后再通过反射
	public String getCls(String parttern) {
		String name = mappingMap.get(parttern);
		return entitiesMap.get(name);
	}
}
