package Controller;

import Model.Gear;
import Model.GearSet;
import View.view1;

/**
 * Created by Grégoire on 21/10/2015.
 */
public class Controller {

    private GearSet gearSet;
    private Gear gearTest;
    private view1 glc;

public Controller(){

    gearTest=new Gear(Gear.Status.down);
    glc = new view1(this);
    gearSet=new GearSet();
}

    public GearSet getGear()
    {
        return this.gearSet;
    }

    public Gear getGearTest()
    {
        return this.gearTest;
    }

    public GearSet getGearSet(){
        return this.gearSet;
    }

}
