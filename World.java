import java.awt.Graphics;


public class World{
    private int width, height;
    private int spawnX, spawnY;
    private int map;
    private int[][] tiles;
    private Handler handler;
    private EntityManager entityManager;
    private ItemManager itemManager;
    private Spawner spawner;


    public World(Handler handler, String path, int map){
      this.handler = handler;
      entityManager = new EntityManager(handler, new Player(handler, 100, 100));
      this.map = map;

      itemManager = new ItemManager(handler);

      spawner = new Spawner(handler, entityManager, this);


      loadWorld(path);
      entityManager.getPlayer().setX(spawnX);
      entityManager.getPlayer().setY(spawnY);
      createEntityMap(map);

    }

    public void tick(){
      itemManager.tick();
      entityManager.tick();
      spawner.tick();
    }





    public void render(Graphics g){
      int xStart = (int) Math.max(0, handler.getGameCamera().getXOffSet()/Tile.TILEWIDTH);
      int xEnd = (int) Math.min(width, (handler.getGameCamera().getXOffSet() + handler.getWidth()) / Tile.TILEWIDTH + 1);
      int yStart = (int) Math.max(0, handler.getGameCamera().getYOffSet()/Tile.TILEHEIGHT);
      int yEnd = (int) Math.min(height, (handler.getGameCamera().getYOffSet() + handler.getHeight()) / Tile.TILEHEIGHT + 1);


      for(int y = yStart; y < yEnd; y++){
        for(int x = xStart; x < xEnd; x++){
          getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getXOffSet()),(int)(y * Tile.TILEHEIGHT - handler.getGameCamera().getYOffSet()));
        }
      }

      //Items
      itemManager.render(g);

      //Entities
      entityManager.render(g);

    }

    public Tile getTile(int x, int y){
        if(x < 0 || y < 0 || x >= width || y >= height){
          return Tile.grassTile;
        }

        Tile t = Tile.tiles[tiles[x][y]];

        if(t == null){
          return Tile.grassTile;
        }

        return t;

    }

    private void loadWorld(String path){
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        System.out.println(tokens.length);
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for(int y = 0; y < height; y++){
          for(int x = 0; x < width; x++){
            tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
          }
        }
    }


    //Getters
    public int getWidth(){
      return width;
    }

    public int getHeight(){
      return height;
    }

    public EntityManager getEntityManager(){
      return entityManager;
    }
    public ItemManager getItemManager(){
      return itemManager;
    }

    public Handler getHandler(){
      return handler;
    }

    public int[][] getTiles(){
      return tiles;
    }


    //Dumb Method

    private void createEntityMap(int map){
        entityManager.addEntity(new Tree(handler, 128, 325));
        entityManager.addEntity(new Tree(handler, 192, 325));
        entityManager.addEntity(new Tree(handler, 256, 325));
        entityManager.addEntity(new BigTree(handler, 400, 400));
        entityManager.addEntity(new Tree(handler, 256, 525));

    }

}
