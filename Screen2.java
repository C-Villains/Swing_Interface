import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen2 extends JFrame implements ActionListener {
    //Screen1 에서 입력받은 아이디를 출력하기 위한 작업
    JLabel l1 = new JLabel("아이디 : ");
    JButton b1 = new JButton("조회");
    JTextField f1 = new JTextField();

    // 생성자 추가
    public Screen2(String username) {
        setLayout(null);
        setVisible(true);
        setTitle("로그인 성공");
        setSize(350, 500);
        JFrame frm = new JFrame();

        // 아이디 값을 JTextField에 설정
        f1.setText(username);

        add(l1);
        add(b1);
        add(f1);
        l1.setBounds(60, 220, 75, 30);
        b1.setBounds(105, 425, 100, 30);
        b1.addActionListener(this);
        f1.setBounds(105, 200, 120, 50);
    }

    public static void main(String[] args) {
        new Screen2("username"); // 테스트용 아이디를 전달
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            new Screen3();
        }
    }
}
