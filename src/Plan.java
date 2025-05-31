import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Plan implements ActionListener {

    JFrame planFrame = new JFrame();
    JLabel planLabel;

    JFrame newPlanFrame;
    JPanel newPlanPanel;
    JLabel planNameLabel;
    JTextField planNameInput;
    JLabel dateLabel;
    JTextField dateInput;
    JLabel timeLabel;
    JTextField timeInput;
    JLabel noteLabel;
    JTextField noteInput;
    JButton submitButton;

    JFrame notificationFrame;
    JPanel notificationPanel;
    JButton backButton;
    JButton showPlanHistoryButton;

    JButton newPlanButton;
    JButton notificationButton;
    JButton closeButton;

    ArrayList<WorkoutPlan> userPlans = new ArrayList<>();

    String userName;


    public Plan(String userName) {
        this.userName = userName;
        planFrame.setTitle("Planning board");
        planFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        planFrame.setSize(1000, 1000);
        planFrame.setResizable(false);
        planFrame.setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("WorkoutAppIcon.png");
        planFrame.setIconImage(icon.getImage());

        newPlanButton = new JButton();
        newPlanButton.setBounds(420, 675, 190, 190);
        newPlanButton.setContentAreaFilled(false);
        newPlanButton.setOpaque(false);
        newPlanButton.setBorder(null);
        newPlanButton.addActionListener(this);
        planFrame.add(newPlanButton);

        notificationButton = new JButton();
        notificationButton.setBounds(428, 353, 115, 115);
        notificationButton.setContentAreaFilled(false);
        notificationButton.setOpaque(false);
        notificationButton.setBorder(null);
        notificationButton.addActionListener(this);
        planFrame.add(notificationButton);

        closeButton = new JButton("Back");
        closeButton.setBounds(800, 800, 100, 50);
        closeButton.setFont(new Font("Roboto", Font.BOLD, 14));
        closeButton.setFocusPainted(false);
        closeButton.setBackground(new Color(255, 99, 71));
        closeButton.setForeground(Color.WHITE);
        closeButton.addActionListener(this);
        planFrame.add(closeButton);


        ImageIcon backgroundImage = new ImageIcon("planFrame.png");
        planLabel = new JLabel(backgroundImage);
        planLabel.setSize(1000, 1000);
        planLabel.setLayout(null);
        planLabel.add(newPlanButton);
        planLabel.add(notificationButton);
        planLabel.add(closeButton);

        planFrame.setContentPane(planLabel);
        planFrame.setVisible(true);
    }

    /**
     * Opens a new frame to create a new plan.
     * It sets up the input fields for plan name, date, time, and notes,
     * and adds an action listener for the submit button.
     */

    private void newPlan() {
        newPlanFrame = new JFrame("New Plan");
        newPlanFrame.setSize(400, 300);
        newPlanFrame.setLocationRelativeTo(planFrame);

        newPlanPanel = new JPanel();
        newPlanPanel.setLayout(new BoxLayout(newPlanPanel, BoxLayout.Y_AXIS));
        newPlanPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Font labelFont = new Font("Roboto", Font.BOLD, 14);
        Font inputFont = new Font("Roboto", Font.PLAIN, 14);

        planNameLabel = new JLabel("Enter the name of plan");
        planNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        planNameLabel.setFont(labelFont);

        planNameInput = new JTextField();
        planNameInput.setMaximumSize(new Dimension(300, 30));
        planNameInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        planNameInput.setFont(inputFont);

        dateLabel = new JLabel("Enter the date (YYYY-MM-DD)");
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dateLabel.setFont(labelFont);

        dateInput = new JTextField();
        dateInput.setMaximumSize(new Dimension(300, 30));
        dateInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        dateInput.setFont(inputFont);

        timeLabel = new JLabel("Enter the time (HH:MM)");
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        timeLabel.setFont(labelFont);

        timeInput = new JTextField();
        timeInput.setMaximumSize(new Dimension(300, 30));
        timeInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        timeInput.setFont(inputFont);

        noteLabel = new JLabel("Enter note");
        noteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        noteLabel.setFont(labelFont);

        noteInput = new JTextField();
        noteInput.setMaximumSize(new Dimension(300, 30));
        noteInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        noteInput.setFont(inputFont);

        submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setBackground(new Color(70, 130, 180));
        submitButton.setFont(labelFont);
        submitButton.setFocusPainted(false);
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(this);

        newPlanPanel.add(planNameLabel);
        newPlanPanel.add(planNameInput);
        newPlanPanel.add(Box.createVerticalStrut(10));
        newPlanPanel.add(dateLabel);
        newPlanPanel.add(dateInput);
        newPlanPanel.add(Box.createVerticalStrut(10));
        newPlanPanel.add(timeLabel);
        newPlanPanel.add(timeInput);
        newPlanPanel.add(Box.createVerticalStrut(10));
        newPlanPanel.add(noteLabel);
        newPlanPanel.add(noteInput);
        newPlanPanel.add(Box.createVerticalStrut(10));
        newPlanPanel.add(submitButton);

        newPlanFrame.add(newPlanPanel);
        newPlanFrame.setVisible(true);
    }

    /**
     * Sets up the notification frame to display today's plans.
     * It retrieves today's plans and displays them in a text area.
     */

    private void notificationSet() {
        notificationFrame = new JFrame("Notification Board");
        notificationFrame.setSize(400, 400);
        notificationFrame.setLocationRelativeTo(planFrame);
        notificationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Font labelFont = new Font("Roboto", Font.BOLD, 14);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        String todayPlansText = getTodayPlansText();

        JTextArea todayPlansArea = new JTextArea(todayPlansText);
        todayPlansArea.setEditable(false);
        todayPlansArea.setLineWrap(true);
        todayPlansArea.setWrapStyleWord(true);
        todayPlansArea.setFont(new Font("Roboto", Font.PLAIN, 13));
        todayPlansArea.setBackground(new Color(245, 245, 245));

        JScrollPane scrollPane = new JScrollPane(todayPlansArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(300, 200));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);

        showPlanHistoryButton = new JButton("Show plan history");
        showPlanHistoryButton.setFont(labelFont);
        showPlanHistoryButton.setBackground(new Color(155, 89, 182));
        showPlanHistoryButton.setFocusPainted(false);
        showPlanHistoryButton.setForeground(Color.WHITE);
        showPlanHistoryButton.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(showPlanHistoryButton, gbc);

        backButton = new JButton("Back");
        backButton.setFont(labelFont);
        backButton.setBackground(new Color(255, 99, 71));
        backButton.setFocusPainted(false);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(backButton, gbc);

        notificationFrame.add(panel);
        planFrame.setVisible(true);
        notificationFrame.setVisible(true);
    }


    /**
     * Saves the specified plan details to a file.
     *
     * @param planName the name of the plan
     * @param date     the date of the plan
     * @param time     the time of the plan
     * @param note     additional notes for the plan
     */

    public void savePlanToFile(String planName, String date, String time, String note) {
        String fileName = "plan_" + userName + ".txt";
        String dateNow = java.time.LocalDate.now().toString();
        String line = dateNow + ";" + planName + ";" + date + ";" + time + ";" + note;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(planFrame, "error", "error", JOptionPane.ERROR_MESSAGE);
        }
    }


    /**
     * Displays the history of plans saved for the user.
     * It reads from the plan file and shows the details in a dialog.
     */

    private void showPlanHistory() {
        String fileName = "plan_" + userName + ".txt";
        File file = new File(fileName);
        if (!file.exists()) {
            JOptionPane.showMessageDialog(planFrame, "no history", "history", JOptionPane.INFORMATION_MESSAGE);
        }
        String historyText = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    historyText = historyText + "Date: " + parts[0]
                            + ", name: " + parts[1]
                            + ", date: " + parts[2]
                            + ", time: " + parts[3]
                            + ", note:" + parts[4] + "\n";
                }

            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(planFrame, "Error", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JTextArea textArea = new JTextArea(historyText.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(planFrame, scrollPane, "Workout History", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Retrieves today's plans from the user's plan file.
     *
     * @return a string representation of today's plans or a message if there are none
     */

    public String getTodayPlansText() {
        String fileName = "plan_" + userName + ".txt";
        File file = new File(fileName);
        if (!file.exists()) return "No plans today.";

        String today = java.time.LocalDate.now().toString();
        StringBuilder todayPlans = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 5 && parts[2].equals(today)) {
                    todayPlans.append("• ").append(parts[1])
                            .append(" in ").append(parts[3])
                            .append(" – ").append(parts[4]).append("\n");
                }
            }
        } catch (IOException e) {
            return "Error";
        }

        return todayPlans.length() > 0 ? todayPlans.toString() : "No plans today.";
    }


    /**
     * Handles action events for various buttons in the plan frame.
     * It responds to new plan creation, notifications, and submission of plan details.
     *
     * @param e the action event triggered by button presses
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newPlanButton) {
            newPlan();
        } else if (e.getSource() == notificationButton) {
            notificationSet();
        } else if (e.getSource() == submitButton) {
            String name = planNameInput.getText().trim();
            String date = dateInput.getText().trim();
            String time = timeInput.getText().trim();
            String note = noteInput.getText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(newPlanFrame, "Enter name of the plan", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                JOptionPane.showMessageDialog(newPlanFrame, "Enter date in correct form YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!time.matches("\\d{2}:\\d{2}")) {
                JOptionPane.showMessageDialog(newPlanFrame, "Čas musí být ve formátu HH:MM.", "Chyba", JOptionPane.ERROR_MESSAGE);
                return;
            }
            userPlans.add(new WorkoutPlan(name, date, time, note));
            savePlanToFile(name, date, time, note);
            System.out.println("Plan saved: " + name + ", " + date + " " + time + ", note: " + note);
            newPlanFrame.dispose();
        } else if (e.getSource() == backButton) {
            notificationFrame.dispose();
            planFrame.setVisible(true);
        } else if (e.getSource() == showPlanHistoryButton) {
            showPlanHistory();
        } else if (e.getSource() == closeButton) {
            planFrame.dispose();
            new Menu(userName);
        }
    }


}