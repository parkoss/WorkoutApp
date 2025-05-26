import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    JLabel noteLabel;
    JTextField noteInput;
    JButton submitButton;

    JButton newPlanButton;
    JButton notificationButton;



    public Plan() {
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

        ImageIcon backgroundImage = new ImageIcon("planFrame.png");
        planLabel = new JLabel(backgroundImage);
        planLabel.setSize(1000, 1000);
        planLabel.setLayout(null);
        planLabel.add(newPlanButton);
        planLabel.add(notificationButton);

        planFrame.setContentPane(planLabel);
        planFrame.setVisible(true);
    }

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

        noteLabel = new JLabel("Enter note");
        noteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        noteLabel.setFont(labelFont);

        noteInput = new JTextField();
        noteInput.setMaximumSize(new Dimension(300, 30));
        noteInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        noteInput.setFont(inputFont);

        submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setBackground(new Color(70,130,180));
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
        newPlanPanel.add(noteLabel);
        newPlanPanel.add(noteInput);
        newPlanPanel.add(Box.createVerticalStrut(20));
        newPlanPanel.add(submitButton);

        newPlanFrame.add(newPlanPanel);
        newPlanFrame.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newPlanButton) {
            newPlan();
        } else if (e.getSource() == notificationButton) {
            //doplnit
        } else if (e.getSource()==submitButton) {
            String name = planNameInput.getText().trim();
            String date = dateInput.getText().trim();
            String note = noteInput.getText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(newPlanFrame, "Enter name of the plan", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                JOptionPane.showMessageDialog(newPlanFrame, "Enter date in correct form YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //userPlans.add(new WorkoutPlan(name, date, note));
            newPlanFrame.dispose();




        }
    }


}
