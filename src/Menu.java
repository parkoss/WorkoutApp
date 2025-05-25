import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener {

    JFrame menuFrame = new JFrame();
    JLabel menuLabel;
    JButton goWorkoutButton;
    JButton planButton;
    Workout workout;
    String userName;

    public Menu(String userName) {
        this.userName=userName;
        goWorkoutButton = new JButton();
        goWorkoutButton.setBounds(220, 283, 220, 120);
        goWorkoutButton.setContentAreaFilled(false);
        goWorkoutButton.setOpaque(false);
        goWorkoutButton.setBorder(null);
        goWorkoutButton.addActionListener(this);
        menuFrame.add(goWorkoutButton);

        planButton=new JButton();
        planButton.setBounds(215,615,240,195);
        planButton.setContentAreaFilled(false);
        planButton.setOpaque(false);
        planButton.setBorder(null);
        planButton.addActionListener(this);
        menuFrame.add(planButton);



        
        ImageIcon backgroundImage= new ImageIcon("menuFrameRemastered.png");
        menuLabel = new JLabel(backgroundImage);
        menuLabel.setSize(1000,1000);
        menuFrame.add(menuLabel);

        menuFrame.setTitle("WorkoutApp");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(1000,1000);
        menuFrame.setResizable(false);
        menuFrame.setLocationRelativeTo(null);

        ImageIcon icon=new ImageIcon("WorkoutAppIcon.png");
        menuFrame.setIconImage(icon.getImage());


        menuFrame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==goWorkoutButton){
            menuFrame.dispose();
            new Workout(userName);
        } else if (e.getSource()==planButton) {
            menuFrame.dispose();
            new Plan();
            
        }

    }
}
