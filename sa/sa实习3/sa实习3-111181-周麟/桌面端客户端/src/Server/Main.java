package Server;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class Main {
	EmailSyst emailSyst;
	
	public Main() {
		// TODO Auto-generated constructor stub
		emailSyst = new EmailSyst();
		emailSyst.open(); 
	}
	public static void main(String[] args) {
		Main main = new Main();
	}
}
