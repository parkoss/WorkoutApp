import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen implements ActionListener {
    JFrame loginFrame;
    JTextField usernameField;
    JButton continueButton;

    public LoginScreen() {
        loginFrame = new JFrame("User login");
        loginFrame.setSize(400, 200);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(null);
        loginFrame.setLocationRelativeTo(null);

        JLabel label = new JLabel("Enter your name:");
        label.setBounds(50, 40, 150, 30);
        loginFrame.add(label);

        usernameField = new JTextField();
        usernameField.setBounds(170, 40, 150, 30);
        loginFrame.add(usernameField);

        continueButton = new JButton("Continue");
        continueButton.setBounds(130, 100, 120, 30);
        continueButton.setBackground(new Color(70, 130, 180));
        continueButton.setFont(new Font("Roboto",Font.BOLD,14));
        continueButton.setForeground(Color.WHITE);
        continueButton.setFocusPainted(false);
        continueButton.addActionListener(this);
        loginFrame.add(continueButton);

        ImageIcon icon=new ImageIcon("WorkoutAppIcon.png");
        loginFrame.setIconImage(icon.getImage());

        loginFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText().trim();
        if (!username.isEmpty()) {
            loginFrame.dispose();
            new UserSet(username);
        } else {
            JOptionPane.showMessageDialog(loginFrame, "Enter your name.");
        }
    }
}
