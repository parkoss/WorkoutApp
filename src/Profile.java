import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Profile implements ActionListener {

    JFrame profileFrame;
    CardLayout cardLayout;

    User user;
    String userName;

    JPanel profilePanel;
    JButton backButton;
    JButton logOutButton;

    JLabel nameLabel;
    JLabel heightLabel;
    JLabel weightLabel;
    JLabel exercisesLabel;
    JLabel workoutLabel;
    JLabel plansLabel;

    JTextField nameField;
    JTextField heightField;
    JTextField weightField;
    JTextField exercisesField;
    JTextField workoutField;
    JTextField plansField;


    public Profile(String userName) {
        this.userName = userName;
        profileFrame = new JFrame("Profile");
        profileFrame.setSize(500, 500);
        profileFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        profileFrame.setLocationRelativeTo(null);

        loadUserFromFile();
        cardLayout = new CardLayout();


        setupProfilepage();
        profileFrame.setVisible(true);
    }


    private void setupProfilepage() {
        profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        Font labelFont = new Font("Roboto", Font.BOLD, 14);
        Font inputFont = new Font("Roboto", Font.PLAIN, 14);

        nameLabel = new JLabel("Name: ");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setFont(labelFont);
        profilePanel.add(nameLabel);

        nameField = new JTextField(user.getName());
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameField.setFont(inputFont);
        nameField.setEditable(false);
        profilePanel.add(nameField);

        heightLabel = new JLabel("Height: ");
        heightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        heightLabel.setFont(labelFont);
        profilePanel.add(heightLabel);

        heightField = new JTextField(String.valueOf(user.getHeight()));
        heightField.setAlignmentX(Component.CENTER_ALIGNMENT);
        heightField.setFont(inputFont);
        heightField.setEditable(false);
        profilePanel.add(heightField);

        weightLabel = new JLabel("Weight: ");
        weightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        weightLabel.setFont(labelFont);
        profilePanel.add(weightLabel);

        weightField = new JTextField(String.valueOf(user.getWeight()));
        weightField.setAlignmentX(Component.CENTER_ALIGNMENT);
        weightField.setFont(inputFont);
        weightField.setEditable(false);
        profilePanel.add(weightField);

        exercisesLabel = new JLabel("Exercises: ");
        exercisesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        exercisesLabel.setFont(labelFont);
        profilePanel.add(exercisesLabel);

        exercisesField = new JTextField(String.valueOf(getExercisesCountFromFile()));
        exercisesField.setAlignmentX(Component.CENTER_ALIGNMENT);
        exercisesField.setEditable(false);
        exercisesField.setFont(inputFont);
        profilePanel.add(exercisesField);

        plansLabel = new JLabel("Plans assigned: ");
        plansLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        plansLabel.setFont(labelFont);
        profilePanel.add(plansLabel);

        plansField = new JTextField(String.valueOf(getPlansCountFromFile()));
        plansField.setAlignmentX(Component.CENTER_ALIGNMENT);
        plansField.setEditable(false);
        plansField.setFont(inputFont);
        profilePanel.add(plansField);

        workoutLabel = new JLabel("Workouts completed: ");
        workoutLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        workoutLabel.setFont(labelFont);
        profilePanel.add(workoutLabel);

        workoutField = new JTextField(String.valueOf(getWorkoutCount()));
        workoutField.setAlignmentX(Component.CENTER_ALIGNMENT);
        workoutField.setEditable(false);
        workoutField.setFont(inputFont);
        profilePanel.add(workoutField);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        backButton = new JButton("Back");
        backButton.setBackground(new Color(255, 99, 71));
        backButton.setFont(labelFont);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(this);
        buttonPanel.add(backButton, BorderLayout.WEST);

        logOutButton = new JButton("Log out");
        logOutButton.setBackground(new Color(155, 89, 182));
        logOutButton.setFont(labelFont);
        logOutButton.setForeground(Color.WHITE);
        logOutButton.setFocusPainted(false);
        logOutButton.addActionListener(this);
        buttonPanel.add(logOutButton, BorderLayout.EAST);

        profilePanel.add(buttonPanel);
        profileFrame.add(profilePanel);
    }


    private void loadUserFromFile() {
        user = new User();
        File file = new File("user_" + userName + ".txt");
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

    private int getExercisesCountFromFile() {
        File file = new File("workout_" + userName + ".txt");
        if (!file.exists()) return 0;

        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            System.out.println("Error reading workouts file: " + e.getMessage());
        }
        return count;
    }

    private int getPlansCountFromFile() {
        File file = new File("plan_" + userName + ".txt");
        if (!file.exists()) return 0;

        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            System.out.println("Error reading workouts file: " + e.getMessage());
        }
        return count;
    }

    private int getWorkoutCount() {
        File file = new File("user_" + userName + "_workouts.txt");
        int count = 0;

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = reader.readLine();
                if (line != null) {
                    count = Integer.parseInt(line.trim());
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error reading workouts count: " + e.getMessage());
            }
        }

        return count;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==backButton){
            profileFrame.dispose();
            new Menu(userName);
        }else if(e.getSource()==logOutButton){
            profileFrame.dispose();
            new Start();
        }

    }
}
