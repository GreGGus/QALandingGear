package View;

import javax.swing.*;
import Controller.Controller;
/**
 * Created by Grégoire on 25/10/2015.
 */
public class view1  extends JFrame{

    private ControlsPanel pan;

public view1(Controller controller){


    this.setTitle("Gear Landing Control Panel");
    this.setSize(600,400);
    this.pan=new ControlsPanel(controller);
    this.getContentPane().add(pan);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);

}


}
