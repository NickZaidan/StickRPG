import java.awt.Graphics;

public class GameState extends State{
    private WorldManager worldManager;


    public GameState(Handler handler){
        super(handler);
        worldManager = new WorldManager(handler);
        worldManager.addWorld(new World(handler,"res/worlds/world1.txt", 0));
        worldManager.addWorld(new World(handler, "res/worlds/world2.txt", 1));
        handler.setWorld(worldManager.getWorld(0));
    }

    public void tick(){
      handler.getWorld().tick();

    }

    public void render(Graphics g){
      handler.getWorld().render(g);

    }




}
