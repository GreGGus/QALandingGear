package Model;

import java.util.List;
import Model.Gear.Status;
import java.lang.Thread;

/**
 * Created by Grégoire on 21/10/2015.
 */
public class GearSet {


    private Gear gearOne;
    private Gear gearTwo;
    private Gear gearThree;
    private List<Gear> GearList;
    private Status GearSetStatus;

    public GearSet(){
        gearOne = new Gear();
        gearTwo = new Gear();
        gearThree=new Gear();

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

    public void startAllGearThread(){

        // On crée les Thread de chaque Gear
        Thread startOne = new Thread(new ThreadGear(gearOne));
        Thread startTwo = new Thread(new ThreadGear(gearTwo));
        Thread startThree = new Thread(new ThreadGear(gearThree));

        // Check si on peux les démarer
        if(getGearSetStatus()==Status.up)
        {
            // Appelle la fonction activé Thread qui lance la fonction goUP ou goDown
            startOne.start();
            startTwo.start();
            startThree.start();
        }
    }



}
