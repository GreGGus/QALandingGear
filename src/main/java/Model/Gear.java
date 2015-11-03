package Model;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Observable;
import java.util.Random;

/**
 * Created by Gr�goire on 21/10/2015.
 */
public class Gear extends Observable {

    private Door door;
    public  enum Status{blocked,down,goDown,doorClose,up,goUp, doorOpen,doorMoving,doorMovingDown,doorMovingUp}
    private Status status;



    public Gear(Status status){
        door = new Door();
        door.setOpen(false);
        this.setStatus(status);
    }

    public Gear(){
        door=new Door();
    }


    //
    public void setStatus(Status status){
        this.status=status;
        setChanged();
        notifyObservers();
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

    // blocked,down,goDown,open,up,goUp, doorOpen, doorClose}
    // doorOpen -> goUP

    public void UpGear(){
        // Timer
        // Move stick to top, so the status is "goUp"
        //stickAction(Status.up);
        // random ici
        //
        setStatus(Status.doorMoving);
        // new task timer .
        Timer timer3 = new Timer();
        timer3.schedule(new TimerTask(){
            public void run()
            {
                setStatus(Status.doorOpen);
                setDoorOpen(true);
                Timer timer= new Timer();
                timer.schedule(new TimerTask(){
                    public void run()
                    {
                        // Door open after few times
                        // general status to doorOpen
                        setStatus(Status.goUp);
                        // new timer for the gear
                        Timer timer2 = new Timer();
                        timer2.schedule( new TimerTask(){
                            public void run()
                            {
                                //Change general status to UP
                                setStatus(Status.doorMovingUp);
                                Timer timer4 = new Timer();
                                timer4.schedule(new TimerTask(){
                                    public void run()
                                    {

                                        // random pour raté la fermeture de la porte
                                        Random rand = new Random();
                                        int random= rand.nextInt(6);
                                        if(random==3) {
                                            setDoorOpen(true);
                                        }else{
                                            setDoorOpen(false);
                                        }
                                        setStatus(Status.up);
                                    }
                                },1000);
                            }
                        },2500);

                    }
                },1000);

            }
        },1000);


        // Run la manivelle (status door open)
        // Timer encore
        // change status : UP pour le gear
        // -> On appuie levier -> Is going UP
        // -> On ouvre les portes
        // -> Les roues rentrent et c'est bon.
        // On ferme les portes.

        //TODO Attention la porte n'est pas ferm�.
        //close gate


    }

    public void setDoorOpen(boolean val){
        this.door.setOpen(val);
    }

    public void DownGear(){

        // Ouverture des portes
        Timer timer3 = new Timer();
        setStatus(Status.doorMoving);
        timer3.schedule(new TimerTask(){
            public void run(){
                setDoorOpen(true);
                setStatus(Status.doorOpen);
                // Timer
                Timer timer = new Timer();
                timer.schedule(new TimerTask(){
                    public void run() {
                        // We're going down
                        //stickAction(Status.down);
                        setStatus(Status.goDown);

                        Timer timer2 = new Timer();
                        timer2.schedule(new TimerTask() {
                            public void run() {
                                // Set general status to down
                                setStatus(Status.doorMovingDown);
                                Timer timer4 = new Timer();
                                timer4.schedule(new TimerTask() {
                                    public void run() {
                                        Random rand = new Random();
                                        int random= rand.nextInt(6);
                                        if(random==3) {
                                            setDoorOpen(true);
                                        }else{
                                            setDoorOpen(false);
                                        }
                                        setStatus(Status.down);

                                    }
                                }, 1000);
                            }
                        }, 2000);

                    }

                },2500);
            }
        },1000);


    }

}
