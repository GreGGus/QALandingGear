package Controller;

import Model.GearSet;
import View.view1;

/**
 * Created by Grégoire on 21/10/2015.
 */
public class Controller {
    private GearSet gearSet;
  //  private Sensors sensorSet;
    private view1 glc;

public Controller(){
    gearSet = new GearSet();
    glc = new view1();
}
}
