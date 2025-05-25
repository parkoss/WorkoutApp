import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start implements ActionListener {
    JFrame startFrame = new JFrame();
    JLabel startLabel;
    JButton startButton;


    Start(){
        startButton=new JButton();
        startButton = new JButton();
        startButton.setBounds(155, 530, 225, 115);
        startButton.setContentAreaFilled(false);
        startButton.setOpaque(false);
        startButton.setBorder(null);
        startButton.addActionListener(this);
        startFrame.add(startButton);


        ImageIcon backgroundImage= new ImageIcon("startFrame.png");
        startLabel = new JLabel(backgroundImage);
        startLabel.setSize(1000,1000);
        startFrame.add(startLabel);

        startFrame.setTitle("WorkoutApp");
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setSize(1000,1000);
        startFrame.setResizable(false);
        startFrame.setLocationRelativeTo(null);

        ImageIcon icon=new ImageIcon("WorkoutAppIcon.png");
        startFrame.setIconImage(icon.getImage());


        startFrame.setVisible(true);



         


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==startButton){
            startFrame.dispose();
            new LoginScreen();

        }
    }
}
