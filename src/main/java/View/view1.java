package View;

import javax.swing.*;

/**
 * Created by Grégoire on 25/10/2015.
 */
public class view1  extends JFrame{

    private ControlsPanel pan;
    private JSlider slider1;
    private JLabel label1;
    private JLabel label2;

public view1(){


    this.setTitle("Gear Landing Control Panel");
    this.setSize(600,400);
    this.pan=new ControlsPanel();
    this.getContentPane().add(pan);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);

}


}
