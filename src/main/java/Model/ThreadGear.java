package Model;

import java.util.Random;

/**
 * Created by Gregoire on 26/10/2015.
 */
public class ThreadGear implements Runnable {

    private Gear gear;

    /**
     * Constructeur ThreadGear
     * @param gear
     */
    public ThreadGear(Gear gear){
        this.gear=gear;
    }

    /**
     * Fonction qui run les threads. Random sur le temps de départs des thread pour ne pas les bouger tous en même temps.
     */
    public void run(){
        Random rnd = new Random();
        // On lance les threads.
        int sleep= rnd.nextInt(2000);
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.gear.startThreadGear();

    }
}
