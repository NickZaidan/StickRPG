import java.awt.Graphics;
import java.awt.Rectangle;


public abstract class Entity{
    protected float x, y;
    protected int width, height;
    protected Handler handler;
    protected Rectangle bounds;
    protected int health;
    protected boolean active = true;
    protected boolean isPlayer = false;
    public static final int DEFAULT_HEALTH = 10;


    public Entity(Handler handler, float x, float y, int width, int height){
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.handler = handler;
      health = DEFAULT_HEALTH;

      bounds = new Rectangle(0, 0, width, height);

    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void die();


    public void hurt(int amt){
      health -= amt;
      if(health <= 0){
        active = false;
        die();
      }
    }

    public boolean checkEntityCollision(float xOffSet, float yOffSet){
      for(Entity e: handler.getWorld().getEntityManager().getEntities()){
        if(e.equals(this)){
          continue;
        }
        if(e.getCollisionsBounds(0f, 0f).intersects(getCollisionsBounds(xOffSet, yOffSet))){
          return true;
        }
      }
      return false;
    }

    public Rectangle getCollisionsBounds(float xOffSet, float yOffSet){
      return new Rectangle((int) (x + bounds.x + xOffSet), (int) (y + bounds.y + yOffSet), bounds.width, bounds.height);
    }

    //Getters
    public float getX(){
      return x;
    }

    public float getY(){
      return y;
    }

    public int getWidth(){
      return width;
    }

    public int getHeight(){
      return height;
    }

    public boolean getActive(){
      return active;
    }

    public int getHealth(){
      return health;
    }

    public Entity getEntity(){
      return this;
    }

    public boolean getIsPlayer(){
      return isPlayer;
    }

    //Setters
    public void setX(float x){
      this.x = x;
    }

    public void setY(float y){
      this.y = y;
    }

    public void setWidth(int width){
      this.width = width;
    }

    public void setHeight(int height){
      this.height = height;
    }

    public void setActive(boolean active){
      this.active = active;
    }

    public void setHealth(int health){
      this.health = health;
    }

    public void setIsPlayer(boolean isPlayer){
      this.isPlayer = isPlayer;
    }
}
