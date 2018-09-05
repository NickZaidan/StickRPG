public class Handler{
    private Game game;
    private World world;
    public Handler(Game game){
        this.game = game;
    }


    //Getters
    public Game getGame(){
      return game;
    }

    public World getWorld(){
      return world;
    }

    public int getWidth(){
      return game.getWidth();
    }

    public int getHeight(){
      return game.getHeight();
    }

    public KeyManager getKeyManager(){
      return game.getKeyManager();
    }

    public GameCamera getGameCamera(){
      return game.getGameCamera();
    }

    public MouseManager getMouseManager(){
      return game.getMouseManager();
    }

    public WorldManager getWorldManager(){
      return game.getWorldManager();
    }
    //Setters

    public void setGame(Game game){
      this.game = game;
    }

    public void setWorld(World world){
      this.world = world;

    }
}
