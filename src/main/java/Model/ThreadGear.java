package Model;

/**
 * Created by Grégoire on 26/10/2015.
 */
public class ThreadGear implements Runnable {

    private Gear gear;

    public ThreadGear(Gear gear){
        this.gear=gear;
    }

    public void run(){
        // On lance les threads.
        this.gear.startThreadGear();

    }
}
