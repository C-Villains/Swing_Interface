import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Screen3 extends JFrame  {

	JLabel ImageLabel = new JLabel();
	ImageIcon K_ICON = new ImageIcon(("src/image/프로필.jpg"));
	JLabel l1 = new JLabel("아이디 : ");
	JLabel l2 = new JLabel("비밀번호 : ");
	JLabel l3 = new JLabel("환영합니다!");
	Screen3()
	{
		setSize(700,700);
		setTitle("프로필");
		setVisible(true);
		setLayout(null);
		add(l1);
		add(l2);
		add(l3);
		ImageLabel.setIcon(K_ICON);
		ImageLabel.setVisible(true);
		ImageLabel.setBounds(100, 100, 200, 200);
		l1.setBounds(300, 100, 100, 50);
		l2.setBounds(300, 200, 100, 50);
		l3.setBounds(100, 400, 300, 50);
		
		getContentPane().add(ImageLabel);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Screen3();
	}
}