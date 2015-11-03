package View;

import Model.Gear;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import Controller.Controller;
import java.util.Observable;
import java.util.Observer;
import Model.*;

public class Interface extends Application implements Observer {
    private Image EMPTY_LIGHT = new Image("emptyLight.jpg", 175, 0, true, true);
    private Image RED_LIGHT = new Image("redLight.jpg", 175, 0, true, true);
    private Image ORANGE_LIGHT = new Image("orangeLight.jpg", 175, 0, true, true);
    private Image GREEN_LIGHT = new Image("greenLight.jpg", 175, 0, true, true);
    private Image CLOSED_DOOR = new Image("doorClosed.jpg", 175, 0, true, true);
    private Image MOVING_DOOR = new Image("doorMoving.jpg", 175, 0, true, true);
    private Image OPEN_DOOR = new Image("doorOpened.jpg", 175, 0, true, true);
    private Image RETRACTED_GEAR = new Image("gearRetracted.jpg", 175, 0, true, true);
    private Image MOVING_GEAR = new Image("gearMoving.jpg", 175, 0, true, true);
    private Image EXTRACTED_GEAR = new Image("gearExtracted.jpg", 175, 0, true, true);
    private ImageView LIGHT_VIEW1 = new ImageView();
    private ImageView LIGHT_VIEW2 = new ImageView();
    private ImageView LIGHT_VIEW3 = new ImageView();
    private ImageView DOOR_VIEW1 = new ImageView();
    private ImageView DOOR_VIEW2 = new ImageView();
    private ImageView DOOR_VIEW3 = new ImageView();
    private ImageView GEAR_VIEW1 = new ImageView();
    private ImageView GEAR_VIEW2 = new ImageView();
    private ImageView GEAR_VIEW3 = new ImageView();
    private Controller controller;


    @Override
    public void start(Stage primaryStage) {
        Scene scene = null;
        GridPane gridPane = createGridPane();
        controller = new Controller();
        controller.getGearSet().getGearOne().addObserver(this);
        controller.getGearSet().getGearTwo().addObserver(this);
        controller.getGearSet().getGearThree().addObserver(this);


        try {
            scene = new Scene(gridPane, 1280, 960);
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

        if (scene != null) {
//            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    /**
     * createGridPane creates the elements in the JavaFX interface.
     *
     * @return a JavaFX GridPane which contains the interface.
     */

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();

        Text title = new Text("Landing Gear System");
        title.setFont(new Font(32));
        GridPane.setColumnSpan(title, 3);

        // Initialize the lights with images of empty lights.

        LIGHT_VIEW1.setImage(EMPTY_LIGHT);
        LIGHT_VIEW2.setImage(EMPTY_LIGHT);
        LIGHT_VIEW3.setImage(EMPTY_LIGHT);

        // Initialize the doors with images of closed doors.

        DOOR_VIEW1.setImage(CLOSED_DOOR);
        DOOR_VIEW2.setImage(CLOSED_DOOR);
        DOOR_VIEW3.setImage(CLOSED_DOOR);

        // Initialize the gears with images of retracted gears.

        GEAR_VIEW1.setImage(RETRACTED_GEAR);
        GEAR_VIEW2.setImage(RETRACTED_GEAR);
        GEAR_VIEW3.setImage(RETRACTED_GEAR);

        final Slider gearSlider = new Slider(0, 1, 1);
        gearSlider.setOrientation(Orientation.VERTICAL);
        GridPane.setRowSpan(gearSlider, 5);
        gearSlider.setSnapToTicks(true);
        gearSlider.setMajorTickUnit(1);
        gearSlider.setMinorTickCount(0);
        gearSlider.setMaxHeight(100);
        gearSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasChanging, Boolean changing) {

                if (!changing) {

                    if ((int) Math.round(gearSlider.getValue()) == 1) {
                        retractGear(LIGHT_VIEW1, DOOR_VIEW1, GEAR_VIEW1);
                        retractGear(LIGHT_VIEW2, DOOR_VIEW2, GEAR_VIEW2);
                        retractGear(LIGHT_VIEW3, DOOR_VIEW3, GEAR_VIEW3);
                    } else if ((int) Math.round(gearSlider.getValue()) == 0) {
                        extractGear(LIGHT_VIEW1, DOOR_VIEW1, GEAR_VIEW1);
                        extractGear(LIGHT_VIEW2, DOOR_VIEW2, GEAR_VIEW2);
                        extractGear(LIGHT_VIEW3, DOOR_VIEW3, GEAR_VIEW3);
                    } else {
                        System.out.println("Error!");
                    }
                }
            }
        });

        gridPane.addRow(0, gearSlider);
        gridPane.addRow(1, title);
        gridPane.addRow(2, LIGHT_VIEW1, LIGHT_VIEW2, LIGHT_VIEW3);
        gridPane.addRow(3, DOOR_VIEW1, DOOR_VIEW2, DOOR_VIEW3);
        gridPane.addRow(4, GEAR_VIEW1, GEAR_VIEW2, GEAR_VIEW3);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        return gridPane;
    }

    /**
     * extractGear completes the process of extracting a gear, changing a light, door, and gear.
     *
     * @param lightView is the view of the corresponding light.
     * @param doorView  is the view of the corresponding door.
     * @param gearView  is the view of the corresponding gear.
     */

    private void extractGear(ImageView lightView, ImageView doorView, ImageView gearView) {
        // DOWN
        if (controller.getGearSet().getGearSetStatus() == Gear.Status.up) {
            controller.getGearSet().startAllGearThread();
            // controller.getGearTest().startThreadGear();
        } else {
            System.out.println("fail");
        }
       /*
        setOrangeLight(lightView);
        setMovingDoor(doorView);
        setOpenDoor(doorView);
        setMovingGear(gearView);
        setExtractedGear(gearView);
        //setMovingDoor(doorView);
        setClosedDoor(doorView);
        //setGreenLight(lightView);
        setEmptyLight(lightView);
    */


    }

    /**
     * retractGear completes the process of extracting a gear, changing a light, door, and gear.
     *
     * @param lightView is the view of the corresponding light.
     * @param doorView  is the view of the corresponding door.
     * @param gearView  is the view of the corresponding gear.
     */

    private void retractGear(ImageView lightView, ImageView doorView, ImageView gearView) {
        int i = 0;
        //  System.out.println(controller.getGearSet().getGearSetStatus());
        // System.out.println(controller.getGearSet().getGearOne().getStatus());

        //UP

        if (controller.getGearSet().getGearSetStatus() == Gear.Status.down) {
            controller.getGearSet().startAllGearThread();
            // controller.getGearTest().startThreadGear();
        } else {
            System.out.println("fail");
        }

       /* setOrangeLight(lightView);
        setMovingDoor(doorView);
        setOpenDoor(doorView);
        setMovingGear(gearView);
        setRetractedGear(gearView);
        setMovingDoor(doorView);
        setClosedDoor(doorView);
        setEmptyLight(lightView);
    */
    }

    private void setEmptyLight(ImageView lightView) {
        lightView.setImage(EMPTY_LIGHT);
    }

    private void setRedLight(ImageView lightView) {
        lightView.setImage(RED_LIGHT);
    }

    private void setOrangeLight(ImageView lightView) {
        lightView.setImage(ORANGE_LIGHT);
    }

    private void setGreenLight(ImageView lightView) {
        lightView.setImage(GREEN_LIGHT);
    }

    private void setClosedDoor(ImageView doorView) {
        doorView.setImage(CLOSED_DOOR);
    }

    private void setMovingDoor(ImageView doorView) {
        doorView.setImage(MOVING_DOOR);
    }

    private void setOpenDoor(ImageView doorView) {
        doorView.setImage(OPEN_DOOR);
    }

    private void setRetractedGear(ImageView gearView) {
        gearView.setImage(RETRACTED_GEAR);
    }

    private void setMovingGear(ImageView gearView) {
        gearView.setImage(MOVING_GEAR);
    }

    private void setExtractedGear(ImageView gearView) {
        gearView.setImage(EXTRACTED_GEAR);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        if (arg0 == controller.getGearSet().getGearOne()) {

            System.out.println("Gear One :" + controller.getGearSet().getGearOne().getStatus());
            System.out.println("Gear One Door :"+controller.getGearSet().getGearOne().getDoor().isOpen());

            // Test si le status du gearOne est UP et si la porte est fermé
            if(controller.getGearSet().getGearOne().getStatus()== Gear.Status.up && controller.getGearSet().getGearOne().getDoor().isOpen()==false)
            {
                // Set images
                LIGHT_VIEW1.setImage(EMPTY_LIGHT);
                DOOR_VIEW1.setImage(CLOSED_DOOR);
                GEAR_VIEW1.setImage(RETRACTED_GEAR);
            }
            if(controller.getGearSet().getGearOne().getStatus()== Gear.Status.goDown && controller.getGearSet().getGearOne().getDoor().isOpen()==true)
            {
                LIGHT_VIEW1.setImage(ORANGE_LIGHT);
                GEAR_VIEW1.setImage(MOVING_GEAR);

            }
            if(controller.getGearSet().getGearOne().getStatus()== Gear.Status.doorMoving && controller.getGearSet().getGearOne().getDoor().isOpen()==false)
            {
                LIGHT_VIEW1.setImage(ORANGE_LIGHT);
                DOOR_VIEW1.setImage(MOVING_DOOR);

            }
            if(controller.getGearSet().getGearOne().getStatus()== Gear.Status.goUp && controller.getGearSet().getGearOne().getDoor().isOpen()==true)
            {
                LIGHT_VIEW1.setImage(ORANGE_LIGHT);
                GEAR_VIEW1.setImage(MOVING_GEAR);

            }if(controller.getGearSet().getGearOne().getStatus()== Gear.Status.doorMovingDown  && controller.getGearSet().getGearOne().getDoor().isOpen()==true)
            {
                LIGHT_VIEW1.setImage(ORANGE_LIGHT);
                DOOR_VIEW1.setImage(MOVING_DOOR);
                GEAR_VIEW1.setImage(EXTRACTED_GEAR);

            }
            if(controller.getGearSet().getGearOne().getStatus()== Gear.Status.doorMovingUp  && controller.getGearSet().getGearOne().getDoor().isOpen()==true)
            {
                LIGHT_VIEW1.setImage(ORANGE_LIGHT);
                DOOR_VIEW1.setImage(MOVING_DOOR);
                GEAR_VIEW1.setImage(RETRACTED_GEAR);

            }
            if(controller.getGearSet().getGearOne().getStatus()== Gear.Status.down  && controller.getGearSet().getGearOne().getDoor().isOpen()==false)
            {
                LIGHT_VIEW1.setImage(GREEN_LIGHT);
                DOOR_VIEW1.setImage(CLOSED_DOOR);
                GEAR_VIEW1.setImage(EXTRACTED_GEAR);


            }
            if(controller.getGearSet().getGearOne().getStatus()==Gear.Status.doorOpen)
            {
                DOOR_VIEW1.setImage(OPEN_DOOR);

            }


        }
        if (arg0 == controller.getGearSet().getGearTwo()) {
            System.out.println("Gear Two :" + controller.getGearSet().getGearOne().getStatus());

            if(controller.getGearSet().getGearTwo().getStatus()== Gear.Status.up)
            {
                LIGHT_VIEW2.setImage(EMPTY_LIGHT);
            }
            if(controller.getGearSet().getGearTwo().getStatus()== Gear.Status.goDown)
            {
                LIGHT_VIEW2.setImage(ORANGE_LIGHT);
            }
            if(controller.getGearSet().getGearTwo().getStatus()== Gear.Status.goUp)
            {
                LIGHT_VIEW2.setImage(ORANGE_LIGHT);
            }
            if(controller.getGearSet().getGearTwo().getStatus()== Gear.Status.down)
            {
                LIGHT_VIEW2.setImage(GREEN_LIGHT);
            }
        }
        if (arg0 == controller.getGearSet().getGearThree()) {
            System.out.println("Gear Three :" + controller.getGearSet().getGearOne().getStatus());

            if (controller.getGearSet().getGearThree().getStatus() == Gear.Status.up) {
                LIGHT_VIEW3.setImage(EMPTY_LIGHT);
            }
            if (controller.getGearSet().getGearThree().getStatus() == Gear.Status.goDown) {
                LIGHT_VIEW3.setImage(ORANGE_LIGHT);
            }
            if (controller.getGearSet().getGearThree().getStatus() == Gear.Status.goUp) {
                LIGHT_VIEW3.setImage(ORANGE_LIGHT);
            }
            if (controller.getGearSet().getGearThree().getStatus() == Gear.Status.down) {
                LIGHT_VIEW3.setImage(GREEN_LIGHT);
            }

        }

    }

}