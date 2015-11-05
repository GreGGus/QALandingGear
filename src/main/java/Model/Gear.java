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



    /**
     * Constructeur Gear
     * @param status   Status initial du gear
     *
     */
    public Gear(Status status){
        door = new Door();
        door.setOpen(false);
        this.setStatus(status);
    }

    public Gear(){
        door=new Door();
    }


    /**
     * Set le status d'un gear. Notifie l'interface lors d'un changement d'état
     * @param status   Status à modifier
     */
    public void setStatus(Status status){
        this.status=status;
        setChanged();
        notifyObservers();
    }
    /**
     * Recupération du status d'un gear
     * @return status Renvoie le status du gear
     */
    public Status getStatus(){
        return this.status;
    }

    /**
     * Récupération d'une porte d'un gear
     * @return door Renvoie la porte d'un gear
     */
    public Door getDoor(){
        return this.door;
    }

    public void setDoor(Door door){
        this.door=door;
    }

    /**
     * Lance le déplacement du gear. Si le status est UP, on va down. Si le status est DOWN, on va UP.
     */
    public void startThreadGear(){
        //Test sur le status, puis appelle de la fonction UP ou DOWN.
        if(this.getStatus()==Status.up)
            this.DownGear();
        if(this.getStatus()==Status.down)
            this.UpGear();

    }



    /**
     * Méthode pour monter le gear ( rétracter ).
     * Differents status : doorMoving - doorOpen - goUp - doorMovingUp - up
     */
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
                                        setDoorOpen(false);
                                        //  Une chance sur 6 que la porte ne se referme pas.
                                        //  getRandomError();
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

    /**
     * Création d'une erreur d'une chance sur six que la porte ne se referme pas apres mouvement du gear
     */
    public void getRandomError(){
        Random rand = new Random();
        int random = rand.nextInt(6);
        if (random==1)
        {
            // On laisse la porte ouverte
            door.setOpen(true);

        }else
        {
            // On ferme la porte
            door.setOpen(false);
        }
    }

    /**
     * Set le boolean pour s'avoir si la porte est ouverte ou pas.
     */
    public void setDoorOpen(boolean val){
        this.door.setOpen(val);
    }

    /**
     * Méthode pour descendre le gear
     * Differents status : doorMoving - doorOpen - goDown - doorMovingDown - down
     */
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

                                            setDoorOpen(false);
                                            // Erreur de fermeture de porte.
                                            // getRandomError();
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
