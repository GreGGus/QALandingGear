package Controller;

import Model.Gear;
import Model.GearSet;
import View.*;
import javafx.application.Application;

/**
 * Created by Gregoire on 21/10/2015.
 */
public class Controller {

    private GearSet gearSet;

    /**
     * Constructeur controler
     *
     */
public Controller(){

    gearSet=new GearSet();
}

    /**
     * Obtention du gearset
     * @return gearSet
     */
    public GearSet getGearSet(){
        return this.gearSet;
    }

}
