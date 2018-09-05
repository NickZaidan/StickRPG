import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Enemy extends Creature{


  //Player location booleans
  private boolean isDown = false, isUp = false, isLeft = false, isRight = false;
  //Animations
  private Animation animationDown, animationUp, animationLeft, animationRight;

  //Attack timer
  private long lastAttackTimer, attackCooldown = 100, attackTimer = attackCooldown;

  private float healthAsPercentage;

  private int totalHealth;

  public Enemy(Handler handler, float x, float y){
      super(handler,x,y, 42, 42);
      bounds.x = 5;
      bounds.y = 20;
      bounds.width = 32;
      bounds.height = 22;

      this.totalHealth = health;
      //Animations
      animationDown = new Animation(175, Assets.enemy_down);
      animationUp = new Animation(175, Assets.enemy_up);
      animationLeft = new Animation(175, Assets.enemy_left);
      animationRight = new Animation(175, Assets.enemy_right);
    }

    public void tick(){
      //Animation

      animationDown.tick();
      animationUp.tick();
      animationLeft.tick();
      animationRight.tick();


      //Movement
      moveDirections();

      //Attack
      checkAttacks();


      move();
      updateHealthPercentage();

    }

    private void updateHealthPercentage(){
      healthAsPercentage = health * 100 / totalHealth;
    }

    public void moveDirections(){
      xMove = 0;
      yMove = 0;
      float playerX = handler.getWorld().getEntityManager().getPlayer().getX();
      float playerY = handler.getWorld().getEntityManager().getPlayer().getY();

      if(handler.getWorld().getEntityManager().getPlayer().getInventory().isActive()){
        return;
      }


      //If player is above enemy
      if(y > playerY){
         yMove = -speed;
         isUp = true;
      }


      //If player is below enemy
      if(y < playerY){
         yMove = speed;
         isDown = true;
      }

      //If player is left of enemy
      if(x > playerX){
         xMove = -speed;
         isLeft = true;
      }

      //if player is right of enemy
      if(x < playerX){
         xMove = speed;
         isRight = true;
      }
    }

    private void checkAttacks(){
      attackTimer += System.currentTimeMillis() - lastAttackTimer;
      lastAttackTimer = System.currentTimeMillis();

      if(attackTimer < attackCooldown){
        return;
      }

      if(handler.getWorld().getEntityManager().getPlayer().getInventory().isActive()){
        return;
      }

      Rectangle cb = getCollisionsBounds(0,0);
      Rectangle ar = new Rectangle();
      int arSize = 20;
      ar.width = arSize;
      ar.height = arSize;

      if(isUp == true){
        ar.x = cb.x + cb.width/2 - arSize/2;
        ar.y = cb.y - arSize;
        isUp = false;
      }

      if(isDown == true){
        ar.x = cb.x + cb.width/2 - arSize/2;
        ar.y = cb.y + cb.height;
        isDown = false;
      }

      if(isLeft == true){
        ar.x = cb.x - arSize;
        ar.y = cb.y + cb.height / 2 - arSize / 2;
        isLeft = false;
      }

      if(isRight == true){
        ar.x = cb.x + cb.width;
        ar.y = cb.y + cb.height / 2 - arSize / 2;
        isRight = false;
      }

      attackTimer = 0;

      for(Entity e: handler.getWorld().getEntityManager().getEntities()){
        if(e.equals(this)){
          continue;
        }
        if(e.getCollisionsBounds(0,0).intersects(ar)){
          if(e.getIsPlayer() == true){
            e.hurt(3);
            return;
          }
          return;
        }
      }
    }

    public void render(Graphics g){
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! change Assets
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getXOffSet()), (int) (y - handler.getGameCamera().getYOffSet()), width, height, null);
        g.setColor(Color.RED);
        g.fillRect((int) (x - handler.getGameCamera().getXOffSet()), (int) (y - handler.getGameCamera().getYOffSet()), width, 5);
        g.setColor(Color.GREEN);
        g.fillRect((int) (x - handler.getGameCamera().getXOffSet()), (int) (y - handler.getGameCamera().getYOffSet()), (int) (width  * (healthAsPercentage / 100)), 5);

        /*
        g.setColor(Color.red);
        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getXOffSet()),
        (int) (y + bounds.y - handler.getGameCamera().getYOffSet()),
        bounds.width, bounds.height);
        */

    }

    private BufferedImage getCurrentAnimationFrame(){
      if(xMove < 0){ //Moving left
          return animationLeft.getCurrentFrame();
      }

      else if(xMove > 0){ //Moving right
          return animationRight.getCurrentFrame();
      }

      else if(yMove < 0){ //Moving up
        return animationUp.getCurrentFrame();
      }

      else if(yMove > 0) { //Moving right
        return animationDown.getCurrentFrame();
      }
      else {
        return Assets.player_down[0];
      }
    }

    public void die(){
      handler.getWorld().getItemManager().addItems(Item.woodItem.createNew((int) x, (int) y));
      //handler.getWorld().getEntityManager().getPlayer().setX(handler.getWorld().getEntityManager().getPlayer().getX() + 100);
      //handler.getWorld().getEntityManager().getPlayer().setY(handler.getWorld().getEntityManager().getPlayer().getY() + 30);
    }

}
