package View;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by Grégoire on 26/10/2015.
 */
public class ControlsPanel extends JPanel {
    private JSlider slider1;
    private JLabel label1;
    private JLabel label2;

    public ControlsPanel()
    {
        JLabel label1= new JLabel("test");
        JLabel label2= new JLabel("caca");
        final JSlider slider1 = new JSlider();
        add(label1);
        add(label2);
        add(slider1);


        this.setVisible(true);

        slider1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                System.out.println(slider1.getValue());
                if(slider1.getValue()<50)

                    // Lancement UP
                    System.out.println("ON UP");
                else{

                    // Lancement DOWN
                    System.out.println("ON BAISSE");
                }


            }
        });
    }
}
