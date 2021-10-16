package emaildemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import emaildemo.sendemail;


public class mainUI {
	public static void UI() {
		JFrame frame = new JFrame();		
		frame.setTitle("发送邮件");		
		frame.setSize(300,420);		
		frame.setLocationRelativeTo(null);		
		frame.setLayout(new FlowLayout()); 
		JLabel jl1=new JLabel("收件人: ");
		JTextField input_area1 = new JTextField(18);
		JLabel jl2=new JLabel("主    题: ");
		JTextField input_area2 = new JTextField(18);
		JTextArea input_area3 = new JTextArea(15,23);
		JButton a1 = new JButton("发送");	
		JButton a2 = new JButton("邮箱认证");	
		a1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){	
				boolean sended = sendemail.sendEmail(input_area1.getText(),input_area2.getText(),input_area3.getText());
				if(sended) {
					JOptionPane.showMessageDialog(null, "发送成功", "success", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "发送失败", "failure", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		a2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){	
				sucUI();
			}
		});
		frame.add(jl1);
		frame.add(input_area1);
		frame.add(jl2);
		frame.add(input_area2);
		frame.add(input_area3);
		frame.add(a1);
		frame.add(a2);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
	public static void sucUI() {
		JFrame frame = new JFrame();		
		frame.setTitle("确认收件地址");		
		frame.setSize(320,130);		
		frame.setLocationRelativeTo(null);		
		frame.setLayout(new FlowLayout()); 
		
		JLabel jl=new JLabel("收件人: ");
		JTextField input_area = new JTextField(18);
		JButton a = new JButton("提交认证");	
		
		a.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				boolean check = sendemail.validateEmailAddress(input_area.getText());
				if(check) {
					JOptionPane.showMessageDialog(null, "邮箱有效", "success", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "邮箱无效", "failure", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		frame.add(jl);
		frame.add(input_area);
		frame.add(a);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
	}
	public static void main(String[] atrs) {
		UI();
    }
}