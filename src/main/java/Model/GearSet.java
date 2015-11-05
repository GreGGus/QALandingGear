package Model;

import java.util.*;

import Model.Gear.Status;
import View.Interface;
import java.util.Timer;


import java.lang.Thread;

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
        this.setGearSetStatus(Status.up);
        gearOne = new Gear(Status.up);
        gearTwo = new Gear(Status.up);
        gearThree=new Gear(Status.up);
        GearList = new ArrayList<Gear>();
        GearList.add(gearOne);
        GearList.add(gearTwo);
        GearList.add(gearThree);
    }

    // get Plane Status UP DOWN OR BLOCKED
    public Status getGearSetStatus(){
        if(GearList.get(0).getStatus() == Status.up &&
                GearList.get(1).getStatus() == Status.up &&
                GearList.get(2).getStatus() == Status.up )
            return Status.up;
        else if (GearList.get(0).getStatus() == Status.down &&
                GearList.get(1).getStatus() == Status.down &&
                GearList.get(2).getStatus() == Status.down)
            return Status.down;
        else if (GearList.get(0).getStatus() == Status.doorMoving || GearList.get(0).getStatus() == Status.doorOpen &&
                 GearList.get(1).getStatus() == Status.doorMoving || GearList.get(1).getStatus() == Status.doorOpen &&
                GearList.get(2).getStatus() == Status.doorMoving || GearList.get(2).getStatus() == Status.doorOpen )
            return Status.doorMoving;
        else if (GearList.get(0).getStatus() == Status.doorMoving || GearList.get(0).getStatus() == Status.up &&
                GearList.get(1).getStatus() == Status.doorMoving || GearList.get(1).getStatus() == Status.up &&
                GearList.get(2).getStatus() == Status.doorMoving || GearList.get(2).getStatus() == Status.up )
            return Status.doorMoving;
        else if (GearList.get(0).getStatus() == Status.doorMovingUp || GearList.get(0).getStatus() == Status.down &&
                GearList.get(1).getStatus() == Status.doorMovingUp || GearList.get(1).getStatus() == Status.down &&
                GearList.get(2).getStatus() == Status.doorMovingUp || GearList.get(2).getStatus() == Status.down )
            return Status.doorMoving;
        else if (GearList.get(0).getStatus() == Status.doorMovingUp || GearList.get(0).getStatus() == Status.up &&
                GearList.get(1).getStatus() == Status.doorMovingUp || GearList.get(1).getStatus() == Status.up &&
                GearList.get(2).getStatus() == Status.doorMovingUp || GearList.get(2).getStatus() == Status.up )
            return Status.doorMoving;
        else if (GearList.get(0).getStatus() == Status.doorMovingDown || GearList.get(0).getStatus() == Status.up &&
                GearList.get(1).getStatus() == Status.doorMovingDown || GearList.get(1).getStatus() == Status.up &&
                GearList.get(2).getStatus() == Status.doorMovingDown || GearList.get(2).getStatus() == Status.up )
            return Status.doorMoving;
      /*  else if (GearList.get(0).getStatus() == Status.blocked || GearList.get(0).getStatus() == Status.blocked &&
                GearList.get(1).getStatus() == Status.up || GearList.get(1).getStatus() == Status.down &&
                GearList.get(2).getStatus() == Status.up || GearList.get(2).getStatus() == Status.down )
            return Status.blocked;
        else if (GearList.get(0).getStatus() == Status.up || GearList.get(0).getStatus() == Status.down &&
                GearList.get(1).getStatus() == Status.up || GearList.get(1).getStatus() == Status.down &&
                GearList.get(2).getStatus() == Status.blocked || GearList.get(2).getStatus() == Status.blocked )
            return Status.blocked;
        else if (GearList.get(0).getStatus() == Status.up || GearList.get(0).getStatus() == Status.down &&
                GearList.get(1).getStatus() == Status.blocked || GearList.get(1).getStatus() == Status.blocked &&
                GearList.get(2).getStatus() == Status.up || GearList.get(2).getStatus() == Status.down )
            return Status.blocked; */
        else if (GearList.get(0).getStatus()==Status.blocked || GearList.get(1).getStatus()==Status.blocked || GearList.get(2).getStatus()==Status.blocked)
            return Status.blocked;
        else return Status.doorMoving;



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
