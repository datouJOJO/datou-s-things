package MyWebServer;

import java.util.HashSet;
import java.util.Set;
/*
 * һ�Զ��
 */
public class ServletMapping {
	private String name;		//url
	private Set<String> patterns;		//��ͬ�ı���
	
	public ServletMapping() {
		// TODO Auto-generated constructor stub
		patterns = new HashSet<String>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getPatterns() {
		return patterns;
	}

	public void setPatterns(Set<String> patterns) {
		this.patterns = patterns;
	}
	//��hashset���Ԫ��
	public void addPattern(String pattern) {
		this.patterns.add(pattern);
	}
}
