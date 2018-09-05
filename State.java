import java.awt.Graphics;

public abstract class State{

    //Class variables
    private static State currentState = null;
    protected Handler handler;

    //Constructor
    public State(Handler handler){
      this.handler = handler;
    }

    //Getters
    public static State getState(){
      return currentState;
    }

    //Setters
    public static void setState(State state){
      currentState = state;
    }


    //Class
    public abstract void tick();
    public abstract void render(Graphics g);



}
