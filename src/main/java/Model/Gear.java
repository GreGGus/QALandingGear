package Model;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Grégoire on 21/10/2015.
 */
public class Gear extends Observable {

    private Door door;
    public  enum Status{blocked,down,goDown,open,up,goUp, doorOpen, doorClose}
    private Status status;



    public Gear(Status status){
        door = new Door();
        this.setStatus(status);
    }

    public Gear(){
        door=new Door();
    }
    //
    public void setStatus(Status status){
        this.status=status;
        // setChanged();
        // notifyObservers();
    }

    public Status getStatus(){
        return this.status;
    }

    public Door getDoor(){
        return this.door;
    }

    public void setDoor(Door door){
        this.door=door;
    }

    public void startThreadGear(){
        //Test sur le status, puis appelle de la fonction UP ou DOWN.
        if(this.getStatus()==Status.up)
            this.DownGear();
        if(this.getStatus()==Status.down)
            this.UpGear();

    }

    public Status stickAction(Status status){
        if(status == Status.up)
            setStatus(Status.goUp);
        else if (status == Status.down)
            setStatus(Status.goDown);
        return status;

    }

    public void UpGear(){
        // Timer
        Timer timer= new Timer();
        // Move stick to top, so the status is "goUp"
        //stickAction(Status.up);
        setStatus(Status.goUp);
        // new task timer .
        timer.schedule(new TimerTask(){
            public void run()
            {
                // Door open after few times
                door.setOpen(true);
                // general status to doorOpen
                setStatus(Status.doorOpen);
                // new timer for the gear
                Timer timer2 = new Timer();
                timer2.schedule( new TimerTask(){
                    public void run()
                    {
                        //Change general status to UP
                        setStatus(Status.up);
                    }
                },250);

            }
        },100);

        // Run la manivelle (status door open)
        // Timer encore
        // change status : UP pour le gear
        // -> On appuie levier -> Is going UP
        // -> On ouvre les portes
        // -> Les roues rentrent et c'est bon.
        // On ferme les portes.

        //TODO Attention la porte n'est pas fermé.
        //close gate
        this.door.setOpen(false);

    }

    public void DownGear(){

        // Ouverture des portes
        this.door.setOpen(true);
        setStatus(Status.doorOpen);
        // Timer
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            public void run(){
                // We're going down
                //stickAction(Status.down);
                setStatus(Status.goDown);

                Timer timer2 = new Timer();
                timer2.schedule(new TimerTask(){
                    public void run() {
                        // Set general status to down
                        setStatus(Status.down);
                    }
                },200);

            }

        },250);

    }

}
