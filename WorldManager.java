import java.util.ArrayList;

public class WorldManager{
    ArrayList<World> worldList = new ArrayList<World>();
    Handler handler;

    public WorldManager(Handler handler){
        this.handler = handler;
    }


    public void addWorld(World w){
      worldList.add(w);
    }

    public World getWorld(int index){
      return worldList.get(index);
    }
}
