package MyWebServer;

import java.util.HashSet;
import java.util.Set;

public class FilterMapping {
	private String name;
	private Set<String> fPatterns;
	public FilterMapping() {
		super();
		fPatterns = new HashSet<String>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<String> getfPatterns() {
		return fPatterns;
	}
	public void setfPatterns(Set<String> fPatterns) {
		this.fPatterns = fPatterns;
	}

	public void addPattern(String pattern) {
		this.fPatterns.add(pattern);
	}
}
