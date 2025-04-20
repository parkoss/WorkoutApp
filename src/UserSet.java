import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class UserSet implements ActionListener {
    JFrame userSetFrame=new JFrame();
    JLabel userSetLabel;
    JLabel writeName;
    JLabel writeHeight;
    JLabel writeWeight;

    private JTextField nameField;
    private JTextField heightField;
    private JTextField weightField;
    private JButton saveButton;
    User user;
    String username;


    public UserSet(String username) {
        this.username=username;
        user = new User();
        loadUserFromFile();

        saveButton = new JButton("Save");
        saveButton.setBounds(330, 300, 100, 30);
        saveButton.addActionListener(this);
        userSetFrame.add(saveButton);

        ImageIcon backgroundImage= new ImageIcon("SetYourAccount.png");
        userSetLabel = new JLabel(backgroundImage);
        userSetLabel.setSize(1000,1000);
        userSetFrame.add(userSetLabel);

        userSetFrame.setTitle("Set your Account");
        userSetFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userSetFrame.setSize(1000,1000);
        userSetFrame.setResizable(false);
        userSetFrame.setLocationRelativeTo(null);

        writeName=new JLabel("Whats your name?");
        writeName.setBounds(230,150,200,30);
        userSetLabel.add(writeName);

        writeHeight=new JLabel("How tall are you?");
        writeHeight.setBounds(230,200,200,30);
        userSetLabel.add(writeHeight);

        writeWeight=new JLabel("Whats your weight?");
        writeWeight.setBounds(230,250,200,30);
        userSetLabel.add(writeWeight);



        nameField = new JTextField(user.getName());
        nameField.setBounds(350, 150, 200, 30);
        userSetLabel.add(nameField);

        heightField = new JTextField(user.getHeight() > 0 ? String.valueOf(user.getHeight()) : "");
        heightField.setBounds(350, 200, 200, 30);
        userSetLabel.add(heightField);

        weightField = new JTextField(user.getWeight() > 0 ? String.valueOf(user.getWeight()) : "");
        weightField.setBounds(350, 250, 200, 30);
        userSetLabel.add(weightField);

        ImageIcon icon=new ImageIcon("WorkoutAppIcon.png");
        userSetFrame.setIconImage(icon.getImage());

        userSetFrame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==saveButton){

            try {
                String name = nameField.getText();
                int height = Integer.parseInt(heightField.getText());
                double weight = Double.parseDouble(weightField.getText());

                user.setName(name);
                user.setHeight(height);
                user.setWeight(weight);

                saveUserToFile();

                JOptionPane.showMessageDialog(userSetFrame, "Saved:\nName: " + name + "\nHeight: " + height + "\nWeight: " + weight);
                userSetFrame.dispose();
                new Menu();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(userSetFrame, "Write correct form","Error", JOptionPane.ERROR_MESSAGE);
            }


        }
    }

    private void saveUserToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user_"+username+".txt"))) {
            writer.write(user.getName() + "\n");
            writer.write(user.getHeight() + "\n");
            writer.write(user.getWeight() + "\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(userSetFrame, "Error in saving files", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadUserFromFile() {
        File file = new File("user_" + username + ".txt");
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String name = reader.readLine();
            int height = Integer.parseInt(reader.readLine());
            double weight = Double.parseDouble(reader.readLine());

            user.setName(name);
            user.setHeight(height);
            user.setWeight(weight);
        } catch (IOException | NumberFormatException e) {
            System.out.println("User cannot be loaded: " + e.getMessage());
        }
    }
}
