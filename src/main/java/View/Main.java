package View;

import Controller.Controller;

import java.applet.Applet;

/**
 * Created by Gr�goire on 26/10/2015.
 */
public class Main   {


    public static void main (String[] args){
        //Interface.launch(Interface.class);
        //Interface.launch(args);
       // Controller controller = new Controller();
        launchInterface();
    }

    public static void launchInterface()
    {
        Interface.launch(Interface.class);
    }


}
