import java.util.Random;

public class Spawner{

    //Spawner timer
    private long lastSpawnTimer, spawnCooldown = 4000, spawnTimer = spawnCooldown;

    //Max spawn
    int currentSpawn = 0;
    int maxSpawn = 0;
    //Handler
    Handler handler;

    //Entity Manager
    EntityManager entityManager;

    //World
    World world;

    public Spawner(Handler handler, EntityManager entityManager, World world){
        this.handler = handler;
        this.entityManager = entityManager;
        this.world = world;
        maxSpawn = entityManager.getEntities().size() + 10;
    }


    public void tick(){
      currentSpawn = entityManager.getEntities().size();

      if(currentSpawn < maxSpawn){
        spawnTimer += System.currentTimeMillis() - lastSpawnTimer;
        lastSpawnTimer = System.currentTimeMillis();

        if(spawnTimer < spawnCooldown){
          return;
        }

        if(entityManager.getPlayer().getInventory().isActive()){
          return;
        }
        int x;
        int y;
        while(true){
          x = getRandomX();
          y = getRandomY();
          if(world.getTiles()[x][y] == 0){
            break;
          }
        }
        x = x * Tile.TILEWIDTH;
        y = y * Tile.TILEHEIGHT;

        entityManager.addEntity(new Enemy(handler, x, y));
        spawnTimer = 0;
        currentSpawn++;
      }


    }

    private int getRandomX(){
        Random random  = new Random();
        int xSpawn =  (random.nextInt(world.getWidth() - 1) + 1);
        return xSpawn;
    }

    private int getRandomY(){
      Random random  = new Random();
      int ySpawn =  (random.nextInt(world.getHeight() - 1) + 1);
      return ySpawn;
    }
}
