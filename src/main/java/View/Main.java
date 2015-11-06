package View;

import Controller.Controller;

import java.applet.Applet;

/**
 * Created by Gregoire on 26/10/2015.
 */
public class Main   {

    /**
     * Méthode main qui lance l'interface
     */
    public static void main (String[] args){
        launchInterface();
    }

    /**
     * Méthode pour lancer l'interface
     * Differents status : doorMoving - doorOpen - goUp - doorMovingUp - up
     */
    public static void launchInterface()
    {
        Interface.launch(Interface.class);
    }


}
