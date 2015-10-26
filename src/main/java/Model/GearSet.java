package Model;

import java.util.List;
import Model.Gear.Status;

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



}
