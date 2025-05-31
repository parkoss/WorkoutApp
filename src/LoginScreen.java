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


    /**
     * Handles the action event when the continue button is pressed.
     * If the username field is not empty, it disposes of the login frame
     * and creates a new UserSet instance with the entered username.
     * Otherwise, it shows a message dialog prompting the user to enter their name.
     *
     * @param e the action event triggered by the button press
     */

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
