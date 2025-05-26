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

    //String userNamePlan;
    String userName;



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

        closeButton=new JButton("Back");
        closeButton.setBounds(800,800,100,50);
        closeButton.setFont(new Font("Roboto",Font.BOLD,14));
        closeButton.setFocusPainted(false);
        closeButton.setBackground(new Color(255,99,71));
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

    private void notificationSet() {
        notificationFrame = new JFrame("Notification Board");
        notificationFrame.setSize(400, 400);
        notificationFrame.setLocationRelativeTo(planFrame);

        notificationPanel = new JPanel();
        notificationPanel.setLayout(new BoxLayout(notificationPanel, BoxLayout.Y_AXIS));
        notificationPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Font labelFont = new Font("Roboto", Font.BOLD, 14);

        showPlanHistoryButton=new JButton("Show plan history");
        showPlanHistoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        showPlanHistoryButton.setBackground(new Color(255, 99, 71));
        showPlanHistoryButton.setFocusPainted(false);
        showPlanHistoryButton.setForeground(Color.WHITE);
        showPlanHistoryButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setBounds(350,350,100,50);
        backButton.setBackground(new Color(255, 99, 71));
        backButton.setFocusPainted(false);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);


        notificationPanel.add(showPlanHistoryButton);
        notificationPanel.add(Box.createVerticalStrut(20));
        notificationPanel.add(backButton);
        notificationPanel.add(Box.createVerticalStrut(20));

        notificationFrame.add(notificationPanel);
        planFrame.setVisible(false);
        notificationFrame.setVisible(true);
    }

    /*private void savePlanToFile(String planName,String date,String time,String note){
        String fileName="plan_"+userNamePlan+".txt";
        String dateNow=java.time.LocalDate.now().toString();
        String line=dateNow+";"+planName+";"+date+";"+time+";"+note+";";

        try(BufferedWriter writer=new BufferedWriter(new FileWriter(fileName,true))){
            writer.write(line);
            writer.newLine();
        }catch (IOException e){
            JOptionPane.showMessageDialog(planFrame,"error","error",JOptionPane.ERROR_MESSAGE);
        }
    }

     */

    /*private void showPlanHistory(){
        String fileName="plan_"+userNamePlan+".txt";
        File file = new File(fileName);
        if (!file.exists()){
            JOptionPane.showMessageDialog(planFrame,"no history","history",JOptionPane.INFORMATION_MESSAGE);
        }
        String historyText="";
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while((line=reader.readLine())!=null){
                String[]parts=line.split(";");
                if (parts.length==5){
                    historyText=historyText+"Date: "+parts[ 0]
                            +", name: "+ parts[1]
                            +", date: "+ parts[2]
                            +", time: "+parts[3]
                            +", note:"+parts[4]+"\n";
                }

            }
        }catch (IOException e){
            JOptionPane.showMessageDialog(planFrame, "Error", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JTextArea textArea = new JTextArea(historyText.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(planFrame, scrollPane, "Workout History", JOptionPane.INFORMATION_MESSAGE);
    }

     */





    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newPlanButton) {
            newPlan();
        } else if (e.getSource() == notificationButton) {
            notificationSet();
        } else if (e.getSource()==submitButton) {
            String name = planNameInput.getText().trim();
            String date = dateInput.getText().trim();
            String time=timeInput.getText().trim();
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
            /*userPlans.add(new WorkoutPlan(name, date, time, note));
            savePlanToFile(name,date,time,note);

            System.out.println("Plan saved: " + name + ", " + date + " " + time + ", note: " + note);
             */
            newPlanFrame.dispose();
        } else if (e.getSource()==backButton) {
            notificationFrame.dispose();
            planFrame.setVisible(true);
        } else if (e.getSource()==showPlanHistoryButton) {
            //showPlanHistory();
        } else if (e.getSource()==closeButton) {
            //planFrame.dispose();
            //new Menu(userName);                                                                                       soubor se uklada do stejneho souboru jako workout, zatim nefunkcni, kvuli String userName ig

        }
    }


}
