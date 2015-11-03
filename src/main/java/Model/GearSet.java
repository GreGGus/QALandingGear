package Model;

import java.util.ArrayList;
import java.util.List;
import Model.Gear.Status;
import View.Interface;

import java.lang.Thread;
import java.util.Observable;

/**
 * Created by Gr�goire on 21/10/2015.
 */
public class GearSet extends Observable {


    private Gear gearOne;
    private Gear gearTwo;
    private Gear gearThree;
    private List<Gear> GearList;
    private Status GearSetStatus;

    public GearSet(){
        gearOne = new Gear(Status.up);
        gearTwo = new Gear(Status.up);
        gearThree=new Gear(Status.up);
        GearList = new ArrayList<Gear>();
        GearList.add(gearOne);
        GearList.add(gearTwo);
        GearList.add(gearThree);
    }

    // get Plane Status
    public Status getGearSetStatus(){
        if(GearList.get(0).getStatus() == Status.up &&
                GearList.get(1).getStatus() == Status.up &&
                GearList.get(2).getStatus() == Status.up )
            return Status.up;
        else if (GearList.get(0).getStatus() == Status.down &&
                GearList.get(1).getStatus() == Status.down &&
                GearList.get(2).getStatus() == Status.down)
            return Status.down;
        else if (GearList.get(0).getStatus() == Status.goDown &&
                GearList.get(1).getStatus() == Status.goDown &&
                GearList.get(2).getStatus() == Status.goDown)
            return Status.goDown;
        else if (GearList.get(0).getStatus() == Status.goUp &&
                GearList.get(1).getStatus() == Status.goUp &&
                GearList.get(2).getStatus() == Status.goUp)
            return Status.goUp;
        else return Status.blocked;
    }

    public Gear getGearOne() {
        return gearOne;
    }

    public Gear getGearTwo() {
        return gearTwo;
    }

    public Gear getGearThree() {
        return gearThree;
    }

    public void startAllGearThread(){

        // On cr�e les Thread de chaque Gear
        Thread startOne = new Thread(new ThreadGear(gearOne));
        Thread startTwo = new Thread(new ThreadGear(gearTwo));
        Thread startThree = new Thread(new ThreadGear(gearThree));

        // Check si on peux les d�marer
        if(getGearSetStatus()==Status.up)
        {
            // Appelle la fonction activ� Thread qui lance la fonction goUP ou goDown
            startOne.start();
            startTwo.start();
            startThree.start();
        }
        if(getGearSetStatus()==Status.down)
        {
            startOne.start();
            startTwo.start();
            startThree.start();
        }

        setGearSetStatus(getGearSetStatus());
        setChanged();
        notifyObservers();

    }

    public void setGearSetStatus(Status status){
        this.GearSetStatus = status;
    }




}
