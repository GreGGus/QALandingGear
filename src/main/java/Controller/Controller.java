package Controller;

import Model.Gear;
import Model.GearSet;
import View.*;
import javafx.application.Application;

/**
 * Created by Gr�goire on 21/10/2015.
 */
public class Controller {

    private GearSet gearSet;
    private Gear gearTest;
    private view1 glc;
    private Interface viewTest;

public Controller(){

    gearTest=new Gear(Gear.Status.down);
    gearSet=new GearSet();
   // launchInterface();
}

    public void launchInterface()
    {
        Interface.launch(Interface.class);
    }

    public Gear getGearTest()
    {
        return this.gearTest;
    }

    public GearSet getGearSet(){
        return this.gearSet;
    }

}
