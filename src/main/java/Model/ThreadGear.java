package Model;

import java.util.Random;

/**
 * Created by Gr�goire on 26/10/2015.
 */
public class ThreadGear implements Runnable {

    private Gear gear;

    public ThreadGear(Gear gear){
        this.gear=gear;
    }

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
