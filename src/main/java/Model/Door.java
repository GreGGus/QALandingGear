package Model;

/**
 * Created by Gregoireon 21/10/2015.
 */
public class Door {

    private boolean open;

    public Door(){
        setOpen(true);
    }

    public boolean isOpen()
    {
        return open;
    }

    public void setOpen(boolean open)
    {
        this.open=open;
    }
}
