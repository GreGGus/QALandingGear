package Model;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Grégoire on 21/10/2015.
 */
public class Gear extends Observable {

    private Door door;
    public  enum Status{blocked,down,goDown,open,up,goUp}
    private Status status;



    public Gear(Status status){
        door = new Door();
        this.setStatus(status);
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
    }

    public void UpGear(){
        // Timer
        // Manoeuvre ( is GoingUp and isGoingDown )
        // new task timer pour le temps.

        // Run la manivelle (status door open)
        // Timer encore
        // change status : UP pour le gear
        // -> On appuie levier -> Is going UP
        // -> On ouvre les portes
        // -> Les roues rentrent et c'est bon.
        // On ferme les portes.

    }

    public void DownGear(){
        // Timer
        // Open gate ( setDoor(open))

        //manoeuvre(isGoingDown)
        //timer

        //set status DOWN
        // close gate
    }

}
