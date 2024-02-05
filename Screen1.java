import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Screen1 extends JFrame implements ActionListener {

    JLabel ImageLabel = new JLabel();

    ImageIcon Apple2 = new ImageIcon(("src/image/Apple2.png"));
    JButton b1 = new JButton("로그인");
    JButton b2 = new JButton("회원가입");
    JLabel l1 = new JLabel("아이디 : ");
    JLabel l2 = new JLabel("비밀번호 : ");
    JLabel l3 = new JLabel("가입 완료");
    JTextField f1 = new JTextField("아이디를 입력해주세요");
    JTextField f2 = new JTextField("비밀번호는 4자리입니다.");
    String ID = " ";
    String PW = " ";

    public Screen1() {
        setLayout(null);
        setVisible(true);
        setTitle("사과톡");
        setSize(350, 500);

        add(b1);
        b1.addActionListener(this);
        add(b2);
        b2.addActionListener(this);
        add(l1);
        add(l2);
        add(f1);
        add(f2);

        b1.setBounds(120, 380, 100, 30);
        b2.setBounds(120, 410, 100, 30);
        l1.setBounds(70, 250, 150, 30);
        l2.setBounds(60, 300, 150, 30);
        f1.setBounds(120, 250, 150, 30);
        f2.setBounds(120, 300, 150, 30);

        ImageLabel.setIcon(Apple2);
        ImageLabel.setVisible(true);
        ImageLabel.setBounds(55, 15, 300, 300);
        getContentPane().add(ImageLabel);
    }

    public static void main(String[] args) {
        new Screen1();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            // 로그인 버튼 클릭 시 DB와 연동하여 로그인 확인
            String username = f1.getText();
            String password = f2.getText();

            if (checkLogin(username, password)) {
                // 로그인 성공 시 Screen2를 열 수 있도록 수정
                new Screen2(username);
            } else {
                // 로그인 실패 시 메시지 출력 또는 다른 처리
                JOptionPane.showMessageDialog(this, "로그인 실패. 다시 시도하세요.");
            }
        } else if (e.getSource() == b2) {
            // 회원가입 버튼 클릭 시 DB에 회원 정보 저장
            String username = f1.getText();
            String password = f2.getText();

            if (registerUser(username, password)) {
                JOptionPane.showMessageDialog(this, "회원가입이 완료되었습니다.");
            } else {
                JOptionPane.showMessageDialog(this, "회원가입에 실패하였습니다. 다시 시도하세요.");
            }
        }
    }

    private boolean checkLogin(String username, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // JDBC 연결 정보
            String jdbcUrl = "jdbc:mysql://localhost:3306/m_userdb";
            String dbUser = "yeonchan";
            String dbPassword = "loli24pop79!";

            // JDBC 드라이버 로딩
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 데이터베이스 연결
            conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
            
            
            

            // 사용자 정보 조회
            String sql = "SELECT * FROM userslst WHERE username = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            return rs.next(); // 결과가 존재하면 true, 아니면 false 반환

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            // 리소스 해제
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean registerUser(String username, String password) {
        Connection conn = null;
         PreparedStatement pstmtCheck = null;
        PreparedStatement pstmtInsert = null;
        ResultSet rs = null;


        try {
            // JDBC 연결 정보
            String jdbcUrl = "jdbc:mysql://localhost:3306/m_userdb";
            String dbUser = "yeonchan";
            String dbPassword = "loli24pop79!";

            // JDBC 드라이버 로딩
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 데이터베이스 연결
            conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

         // 중복 체크
            String checkSql = "SELECT * FROM userslst WHERE username = ?";
            pstmtCheck = conn.prepareStatement(checkSql);
            pstmtCheck.setString(1, username);
            rs = pstmtCheck.executeQuery();

            if (rs.next()) {
                // 이미 존재하는 사용자명이면 회원가입 실패
                JOptionPane.showMessageDialog(this, "이미 존재하는 사용자명입니다. 다른 사용자명을 선택하세요.");
                return false;
            }

            // 사용자 정보 등록
            String insertSql = "INSERT INTO userslst (username, password) VALUES (?, ?)";
            pstmtInsert = conn.prepareStatement(insertSql);
            pstmtInsert.setString(1, username);
            pstmtInsert.setString(2, password);
            int result = pstmtInsert.executeUpdate();
            return result > 0; // 삽입된 행이 있으면 true, 없으면 false 반환

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
        	// 리소스 해제
            try {
                if (rs != null) rs.close();
                if (pstmtCheck != null) pstmtCheck.close();
                if (pstmtInsert != null) pstmtInsert.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
    }
 
}
    }
}  