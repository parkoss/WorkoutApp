import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Workout implements ActionListener {
    JFrame workoutFrame;
    JPanel mainPanel;
    CardLayout cardLayout;

    JPanel inputPanel;
    JTextField exerciseInput;
    JButton startButton;
    JButton backButton;

    JPanel timerPanel;
    JLabel timerLabel;
    JButton stopButton;
    Timer stopwatch;
    Timer pauseTimer;
    long startTime;

    JPanel resultPanel;
    JTextField repsInput;
    JTextField weightInput;
    JComboBox<String> pauseSelector;
    JButton pauseStartButton;
    JButton stopPauseButton;

    JPanel choicePanel;
    JButton sameExerciseButton;
    JButton newExerciseButton;

    String currentExercise="";
    String userName;
    JLabel pauseTimerLabel;
    JButton historyButton=new JButton();

    public Workout(String userName) {
        this.userName=userName;
        workoutFrame=new JFrame("Workout");
        workoutFrame.setSize(500, 300);
        workoutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        workoutFrame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        setupInputPanel();
        setupTimerPanel();
        setupPauseTimerPanel();
        setupResultPanel();
        setupChoicePanel();

        workoutFrame.add(mainPanel);
        workoutFrame.setVisible(true);
    }

    private void setupInputPanel(){
        inputPanel=new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(60,50,30,50));

        Font labelFont = new Font("Roboto", Font.BOLD, 14);
        Font inputFont = new Font("Roboto", Font.PLAIN, 14);

        JLabel exerciseName=new JLabel("Enter name of the exercise:");
        exerciseName.setAlignmentX(Component.CENTER_ALIGNMENT);
        exerciseName.setFont(labelFont);

        exerciseInput=new JTextField();
        exerciseInput.setMaximumSize(new Dimension(300,30));
        exerciseInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        exerciseName.setFont(inputFont);

        startButton=new JButton("Start");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setFont(labelFont);
        startButton.setFocusPainted(false);
        startButton.setBackground(new Color(178, 224, 178));
        startButton.addActionListener(this);

        backButton=new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(labelFont);
        backButton.setFocusPainted(false);
        backButton.setBackground(new Color(255, 99, 71));
        backButton.addActionListener(this);

        inputPanel.add(exerciseName);
        inputPanel.add(Box.createVerticalStrut(5));
        inputPanel.add(exerciseInput);
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(startButton);
        inputPanel.add(Box.createVerticalStrut(15));
        inputPanel.add(backButton);
        inputPanel.add(Box.createVerticalStrut(15));

        mainPanel.add(inputPanel,"input");

    }

    private void setupTimerPanel(){
        timerPanel = new JPanel();
        timerLabel = new JLabel("Stopwatch: 0 s");
        stopButton = new JButton("Stop");

        stopButton.addActionListener(this);

        timerPanel.add(timerLabel);
        timerPanel.add(stopButton);

        mainPanel.add(timerPanel, "Timer");
    }

    private void setupPauseTimerPanel(){
        JPanel pausePanel = new JPanel();
        pauseTimerLabel = new JLabel("Stopwatch: 0 s");
        stopPauseButton=new JButton("Stop");

        stopPauseButton.addActionListener(this);

        pausePanel.add(pauseTimerLabel);
        pausePanel.add(stopPauseButton);

        mainPanel.add(pausePanel, "PauseTimer");
    }

    private void setupResultPanel(){
        resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        Font labelFont = new Font("Roboto", Font.BOLD, 14);
        Font inputFont = new Font("Roboto", Font.PLAIN, 14);

        JLabel reps = new JLabel("How many reps did you do:");
        reps.setAlignmentX(Component.CENTER_ALIGNMENT);
        reps.setFont(labelFont);

        repsInput = new JTextField();
        repsInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        repsInput.setMaximumSize(new Dimension(200, 30));
        repsInput.setFont(inputFont);

        JLabel weight=new JLabel("Enter Weight of the set:");
        weight.setAlignmentX(Component.CENTER_ALIGNMENT);
        weight.setFont(labelFont);

        weightInput=new JTextField();
        weightInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        weightInput.setMaximumSize(new Dimension(100,30));
        weightInput.setFont(inputFont);

        JLabel pauseLabel = new JLabel("Choose pause length:");
        pauseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pauseLabel.setFont(labelFont);

        String[] pauses = {"No pauses", "1 minute", "3 minutes", "5 minutes"};
        pauseSelector = new JComboBox<>(pauses);
        pauseSelector.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        pauseSelector.setAlignmentX(Component.CENTER_ALIGNMENT);
        pauseSelector.setFont(inputFont);

        pauseStartButton = new JButton("Start Pause");
        pauseStartButton.setFont(labelFont);
        pauseStartButton.setFocusPainted(false);
        pauseStartButton.setBackground(new Color(70,130,180));
        pauseStartButton.setForeground(Color.WHITE);
        pauseStartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        pauseStartButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        pauseStartButton.addActionListener(this);

        resultPanel.add(reps);
        resultPanel.add(Box.createVerticalStrut(5));
        resultPanel.add(repsInput);
        resultPanel.add(Box.createVerticalStrut(10));
        resultPanel.add(weight);
        resultPanel.add(Box.createVerticalStrut(5));
        resultPanel.add(weightInput);
        resultPanel.add(Box.createVerticalStrut(10));
        resultPanel.add(pauseLabel);
        resultPanel.add(Box.createVerticalStrut(5));
        resultPanel.add(pauseSelector);
        resultPanel.add(Box.createVerticalStrut(10));
        resultPanel.add(pauseStartButton);

        mainPanel.add(resultPanel, "Results");

    }

    private void setupChoicePanel(){
        choicePanel=new JPanel();
        choicePanel.setLayout(new BoxLayout(choicePanel, BoxLayout.Y_AXIS));
        choicePanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        Font buttonFont = new Font("Roboto", Font.BOLD, 14);

        sameExerciseButton = new JButton("Continue in same exercise");
        sameExerciseButton.setFont(buttonFont);
        sameExerciseButton.setFocusPainted(false);
        sameExerciseButton.setBackground(new Color(46, 204, 113));
        sameExerciseButton.setForeground(Color.WHITE);
        sameExerciseButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        sameExerciseButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        newExerciseButton = new JButton("Change exercise");
        newExerciseButton.setFont(buttonFont);
        newExerciseButton.setFocusPainted(false);
        newExerciseButton.setBackground(new Color(52, 152, 219));
        newExerciseButton.setForeground(Color.WHITE);
        newExerciseButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        newExerciseButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        historyButton= new JButton("Show Workout History");
        historyButton.setFont(buttonFont);
        historyButton.setFocusPainted(false);
        historyButton.setBackground(new Color(155, 89, 182));
        historyButton.setForeground(Color.WHITE);
        historyButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        historyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        historyButton.addActionListener(this);

        sameExerciseButton.addActionListener(this);
        newExerciseButton.addActionListener(this);

        choicePanel.add(sameExerciseButton);
        choicePanel.add(Box.createVerticalStrut(15));
        choicePanel.add(newExerciseButton);
        choicePanel.add(Box.createVerticalStrut(15));
        choicePanel.add(historyButton);


        mainPanel.add(choicePanel, "choice");


    }


    private void switchPanel(String name){
        cardLayout.show(mainPanel, name);
    }



    private void startStopWatch(){
        startTime = System.currentTimeMillis();
        stopwatch=new Timer(1000,e -> {
            long start=(System.currentTimeMillis()-startTime)/1000;
            timerLabel.setText("Stopwatch: " + start + " s");
        });
        stopwatch.start();

    }

    private void startPause(int seconds){
        pauseTimerLabel.setText("Pause is running: "+seconds+ "s");
        switchPanel("PauseTimer");

        Timer pauseTimer = new Timer(1000, new ActionListener() {
            int timeLeft = seconds;

            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                pauseTimerLabel.setText("Pause left : " + timeLeft + " s");

                if (timeLeft <= 0) {
                    ((Timer) e.getSource()).stop();
                    switchPanel("choice");
                }
            }
        });
        pauseTimer.start();
    }

    private void saveWorkoutToFile(String exercise, int reps, double weight) {
        String fileName = "plan_" + userName + ".txt";
        String date = java.time.LocalDate.now().toString();
        String line = date + ";" + exercise + ";" + reps + ";" + weight;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(workoutFrame, "error","error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showWorkoutHistory() {
        String fileName = "plan_" + userName + ".txt";
        File file = new File(fileName);
        if (!file.exists()) {
            JOptionPane.showMessageDialog(workoutFrame, "No history", "History", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String historyText="";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    historyText = historyText + "Date: " + parts[0]
                            + ", Exercise: " + parts[1]
                            + ", Reps: " + parts[2]
                            + ", Weight: " + parts[3] + " kg\n";
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(workoutFrame, "Error", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTextArea textArea = new JTextArea(historyText.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(workoutFrame, scrollPane, "Workout History", JOptionPane.INFORMATION_MESSAGE);
    }





    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==startButton){
            currentExercise=exerciseInput.getText();
            switchPanel("Timer");
            startStopWatch();
        } else if (e.getSource()==stopButton) {
            stopwatch.stop();
            switchPanel("Results");


        } else if (e.getSource()==backButton) {
            workoutFrame.dispose();
            new Menu(userName);

        } else if (e.getSource()==pauseStartButton) {
            try {
                int reps = Integer.parseInt(repsInput.getText());
                double weight = Double.parseDouble(weightInput.getText());
                saveWorkoutToFile(currentExercise, reps, weight);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(workoutFrame, "Enter correct form", "error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int pauseMinutes=switch (pauseSelector.getSelectedIndex()){
                case 0->0;
                case 1->1;
                case 2->3;
                case 3->5;
                default -> 1;
            };
            startPause(pauseMinutes*60);

        } else if (e.getSource()==sameExerciseButton) {
            switchPanel("Timer");
            startStopWatch();

        } else if (e.getSource()==newExerciseButton) {
            exerciseInput.setText("");
            switchPanel("input");
        } else if (e.getSource()==stopPauseButton) {
            stopwatch.stop();
            switchPanel("choice");
        }else if (e.getSource()==historyButton) {
            showWorkoutHistory();
        }

    }
}
