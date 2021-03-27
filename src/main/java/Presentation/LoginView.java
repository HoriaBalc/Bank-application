package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
public class LoginView {


    private LoginController loginController;
    public JFrame frame;
    private JTextField username;
    private JPasswordField password;

    public JButton btnLogin = new JButton("Login");
    public JLabel lblUsername = new JLabel("Username");
    public JLabel lblPassword = new JLabel("Password");
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public LoginView() {
        frame = new JFrame("Bank - Login");
        loginController=new LoginController(this);
        initialize();
        frame.setVisible(true);
    }


    private void initialize() {


        username = new JTextField(10);

        password = new JPasswordField(10);

        JLabel lblNewLabel = new JLabel("Enter your login credentials");


        JPanel panel = new JPanel(new GridLayout(4,1));
        JPanel panel1 = new JPanel(new FlowLayout());
        JPanel panel2 = new JPanel(new FlowLayout());
        JPanel panel3 = new JPanel(new FlowLayout());
        JPanel panel4 = new JPanel(new GridLayout());

        panel1.add(lblNewLabel);
        panel2.add(lblUsername);
        panel2.add(username);
        panel3.add(lblPassword);
        panel3.add(password);

        panel4.add(btnLogin);
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        frame.add(panel);

        frame.setSize(350,350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

    }

    public JTextField getUsername() {
        return username;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public void setUsername(JTextField username) {
        this.username = username;
    }

    public void setPassword(JPasswordField password) {
        this.password = password;
    }

    public void addLoginListener(ActionListener login) {
        btnLogin.addActionListener(login);
    }
}



