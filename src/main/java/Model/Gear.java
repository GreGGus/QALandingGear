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
    public  enum Status{blocked,down,goDown,doorClose,up,goUp, doorOpen,doorMoving,doorMovingDown,doorMovingUp,doorBlock}
    private Status status;



    public Gear(Status status){
        door = new Door();
        door.setOpen(false);
        this.setStatus(status);
    }

    public Gear(){
        door=new Door();
    }



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




    public void UpGear(){
        // Les portes s'ouvrent
        setStatus(Status.doorMoving);
        Timer timer3 = new Timer();
        timer3.schedule(new TimerTask(){
            public void run()
            {   // Les portes sont ouvertes
                setStatus(Status.doorOpen);
                setDoorOpen(true);
                Timer timer= new Timer();
                timer.schedule(new TimerTask(){
                    public void run()
                    {
                        // Les gear sont en train de monter
                        setStatus(Status.goUp);
                        Timer timer2 = new Timer();
                        timer2.schedule( new TimerTask(){
                            public void run()
                            {
                                //Les portent se referment
                                setStatus(Status.doorMovingUp);
                                Timer timer4 = new Timer();
                                timer4.schedule(new TimerTask(){
                                    public void run()
                                    {

                                       //  random pour raté la fermeture de la porte
                                        Random rand = new Random();
                                       int random= rand.nextInt(5);
                                        if(random==3) {
                                            setDoorOpen(true);
                                        }else{
                                            setDoorOpen(false);
                                       }
                                        // Les gears sont rentrés et portes fermé.
                                       if(door.isOpen()==false) {

                                            setStatus(Status.up);
                                        }else{setStatus(Status.blocked);}

                                    }
                                },1000);
                            }
                        },2500);

                    }
                },1000);

            }
        },1000);



    }

    public void setDoorOpen(boolean val){
        this.door.setOpen(val);
    }

    public void DownGear(){

        Timer timer3 = new Timer();
        // Les portes s'ouvrent
        setStatus(Status.doorMoving);
        timer3.schedule(new TimerTask(){
            public void run(){
                // Les portes sont ouvertent
                setDoorOpen(true);
                setStatus(Status.doorOpen);
                Timer timer = new Timer();
                timer.schedule(new TimerTask(){
                    public void run() {
                        // Le gear goDown
                        setStatus(Status.goDown);
                        Timer timer2 = new Timer();
                        timer2.schedule(new TimerTask() {
                            public void run() {
                                // On remonte les portes pour les fermer
                                setStatus(Status.doorMovingDown);
                                Timer timer4 = new Timer();
                                timer4.schedule(new TimerTask() {
                                    public void run() {
                                     //    Random rand = new Random();
                                     //   int random= rand.nextInt(10);
                                     //   if(random==3) {
                                     //       setDoorOpen(true);
                                      //  }else{
                                            setDoorOpen(false);
                                       // }
                                        // Les gears sont sorties
                                    //    if(door.isOpen()==false) {
                                            setStatus(Status.down);
                                      //  }else{setStatus(Status.blocked);}
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
