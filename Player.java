import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Player extends Creature{

  //Animations
  private Animation animationDown, animationUp, animationLeft, animationRight;

  //Attack timer
  private long lastAttackTimer, attackCooldown = 300, attackTimer = attackCooldown;

  //Last moved
  BufferedImage lastMoved = Assets.player_down[0];

  //Inventory
  private Inventory inventory;

  //Healthbar
  private float healthAsPercentage;

  //Stats
  private int level, strength, healthAmount, moveSpeed, attackSpeed;

  public Player(Handler handler, float x, float y){
      //super(handler,x,y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
      super(handler,x,y, 42, 42);
      bounds.x = 5;
      bounds.y = 20;
      bounds.width = 32;
      bounds.height = 22;

      //Set Player entity
      setIsPlayer(true);

      //Health
      setHealthAmount(50000);
      setHealth(healthAmount);

      //Animations
      animationDown = new Animation(175, Assets.player_down);
      animationUp = new Animation(175, Assets.player_up);
      animationLeft = new Animation(175, Assets.player_left);
      animationRight = new Animation(175, Assets.player_right);

      inventory = new Inventory(handler);
  }

  public void tick(){
    //Animation
    animationDown.tick();
    animationUp.tick();
    animationLeft.tick();
    animationRight.tick();

    //Movement
    getInput();
    move();
    handler.getGameCamera().centreOnEntity(this);

    //Attack
    checkAttacks();

    //Inventory
    inventory.tick();

    updateHealthPercentage();
  }

  private void updateHealthPercentage(){
    healthAsPercentage = health * 100 / healthAmount;
  }

  private void checkAttacks(){
    attackTimer += System.currentTimeMillis() - lastAttackTimer;
    lastAttackTimer = System.currentTimeMillis();
    if(attackTimer < attackCooldown){
      return;
    }

    if(inventory.isActive()){
      return;
    }

    Rectangle cb = getCollisionsBounds(0,0);
    Rectangle ar = new Rectangle();
    int arSize = 20;
    ar.width = arSize;
    ar.height = arSize;

    if(handler.getKeyManager().aUp){
      ar.x = cb.x + cb.width/2 - arSize/2;
      ar.y = cb.y - arSize;
    }

    else if(handler.getKeyManager().aDown){
      ar.x = cb.x + cb.width/2 - arSize/2;
      ar.y = cb.y + cb.height;
    }

    else if(handler.getKeyManager().aLeft){
      ar.x = cb.x - arSize;
      ar.y = cb.y + cb.height / 2 - arSize / 2;
    }

    else if(handler.getKeyManager().aRight){
      ar.x = cb.x + cb.width;
      ar.y = cb.y + cb.height / 2 - arSize / 2;
    }

    else{
      return;
    }

    attackTimer = 0;

    for(Entity e: handler.getWorld().getEntityManager().getEntities()){
      if(e.equals(this)){
        continue;
      }
      if(e.getCollisionsBounds(0,0).intersects(ar)){
        e.hurt(3);
        return;
      }
    }
  }

  public void die(){
    System.out.println("You have been slain");
    System.exit(0);
  }


  private void getInput(){
    xMove = 0;
    yMove = 0;

    if(inventory.isActive()){
      return;
    }

    if(handler.getKeyManager().up){
       yMove = -speed;
    }
    if(handler.getKeyManager().down){
       yMove = speed;
    }
    if(handler.getKeyManager().left){
       xMove = -speed;
    }
    if(handler.getKeyManager().right){
       xMove = speed;
    }
  }

  public void render(Graphics g){
      g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getXOffSet()), (int) (y - handler.getGameCamera().getYOffSet()), width, height, null);
      g.setColor(Color.RED);
      g.fillRect((int) (handler.getWorld().getEntityManager().getPlayer().getX() -  handler.getGameCamera().getXOffSet()), (int) (handler.getWorld().getEntityManager().getPlayer().getY() -  handler.getGameCamera().getYOffSet() - 10), width, 5);
      g.setColor(Color.GREEN);
      g.fillRect((int) (handler.getWorld().getEntityManager().getPlayer().getX() -  handler.getGameCamera().getXOffSet()), (int) (handler.getWorld().getEntityManager().getPlayer().getY() -  handler.getGameCamera().getYOffSet() - 10), (int) (width  * (healthAsPercentage / 100)), 5);

      /*
      g.setColor(Color.red);
      g.fillRect((int) (x + bounds.x - handler.getGameCamera().getXOffSet()),
      (int) (y + bounds.y - handler.getGameCamera().getYOffSet()),
      bounds.width, bounds.height);
      */

  }


  public void postRender(Graphics g){
      inventory.render(g);
  }

  private BufferedImage getCurrentAnimationFrame(){
    if(xMove < 0){ //Moving left
        lastMoved = Assets.player_left[0];
        return animationLeft.getCurrentFrame();
    }

    else if(xMove > 0){ //Moving right
        lastMoved = Assets.player_right[0];
        return animationRight.getCurrentFrame();
    }

    else if(yMove < 0){ //Moving up
      lastMoved = Assets.player_up[0];
      return animationUp.getCurrentFrame();
    }

    else if(yMove > 0) { //Moving down
      lastMoved = Assets.player_down[0];
      return animationDown.getCurrentFrame();
    }
    else {
      return lastMoved;
    }
  }

  //Getter

  public Inventory getInventory(){
    return inventory;
  }

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

  public int getLevel(){
    return level;
  }

  public int getStrength(){
    return strength;
  }

  public int getHealthAmount(){
    return healthAmount;
  }

  public int getMoveSpeed(){
    return moveSpeed;
  }

  public int getAttackSpeed(){
    return attackSpeed;
  }


  //Setter

  public void setInventory(Inventory inventory){
    this.inventory = inventory;
  }


  public void setLevel(int level){
    this.level = level;
  }

  public void setStrength(int strength){
    this.strength = strength;
  }

  public void setHealthAmount(int healthAmount){
    this.healthAmount = healthAmount;
  }

  public void setMoveSpeed(int moveSpeed){
    this.moveSpeed = moveSpeed;
  }

  public void setAttackSpeed(int attackSpeed){
    this.attackSpeed = attackSpeed;
  }


}
