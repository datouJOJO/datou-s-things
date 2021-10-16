package PresentTier;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ChoosePage {
	public JFrame chooseP;
	public JButton userimfo;
	public JButton animalimfo;
	public JButton shelterImfo;
	public JButton healthImfo;
	public JButton VaccinesImfo;
	public JLabel BigWord;
	
	public ChoosePage() {
		// TODO Auto-generated constructor stub
		setUp();
	}
	
	public void setUp() {
		chooseP = new JFrame();
		chooseP.setLayout(null);
		chooseP.setTitle("选择查询种类");
		chooseP.setSize(600, 300);
		BigWord = new JLabel("请选择想要操作的信息种类");
		BigWord.setFont(new Font(null,Font.BOLD,30));
		BigWord.setBounds(80, 0,400, 150);
		chooseP.add(BigWord);
		
		userimfo = new JButton("用户");
		animalimfo = new JButton("动物");
		shelterImfo = new JButton("收容所");
		healthImfo = new JButton("动物健康");
		VaccinesImfo = new JButton("有关疫苗");
		
		userimfo.setBounds(0, 130, 100, 50);
		animalimfo.setBounds(120, 130, 100, 50);
		shelterImfo.setBounds(240, 130, 100, 50);
		healthImfo.setBounds(360, 130, 100, 50);
		VaccinesImfo.setBounds(480, 130, 100, 50);
		
		chooseP.add(userimfo);
		chooseP.add(animalimfo);
		chooseP.add(shelterImfo);
		chooseP.add(healthImfo);
		chooseP.add(VaccinesImfo);
		
		chooseP.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension testSize = chooseP.getSize(); 
		chooseP.setLocation((screenSize.width-testSize.width)/2,(screenSize.height-testSize.height)/2);
//		choosePage.setVisible(true);
	}
//	public static void main(String[] args) {
//		ChoosePage choosePage = new ChoosePage();
//	}
}
