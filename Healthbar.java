import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;

public class Healthbar{

    private int totalHealth;
    private int currentHealth;
    private float healthPercentage;
    private Handler handler;
    private int width;
    private int x, y;
    private EntityManager entities;

    public Healthbar(Handler handler, int totalHealth, int width){
      this.totalHealth = totalHealth;
      this.handler = handler;
      this.currentHealth = totalHealth;
      healthPercentage = currentHealth / totalHealth * 100;
      this.entities = handler.getWorld().getEntityManager();
    }

    public void init(int width, int healthAmount){
      setWidth(width);
      setTotalHealth(healthAmount);
      setCurrentHealth(healthAmount);
    }

    private void takeDamge(int amnt){
      currentHealth -= amnt;

    }

    public void tick(){

    }

    public void render(Graphics g){

    }

    //Getter
    public int getWidth(){
      return width;
    }

    public int getTotalHealth(){
      return totalHealth;
    }

    public int getCurrentHealth(){
      return currentHealth;
    }

    //Setter
    public void setWidth(int width){
      this.width = width;
    }

    public void setTotalHealth(int totalHealth){
      this.totalHealth = totalHealth;
    }

    public void setCurrentHealth(int currentHealth){
      this.currentHealth = currentHealth;
    }
}
