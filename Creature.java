public abstract class Creature extends Entity{
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64,
                            DEFAULT_CREATURE_HEIGHT = 64;

    protected float speed;
    protected float xMove, yMove;


    public Creature(Handler handler, float x, float y, int width, int height){
      super(handler,x,y,width,height);
      speed = DEFAULT_SPEED;
      xMove = 0;
      yMove = 0;
    }

    public void move(){
      if(!checkEntityCollision(xMove, 0f)){
        moveX();
      }

      if(!checkEntityCollision(0f, yMove)){
        moveY();
      }
    }

    public void moveEnemy(){
      if(!checkEntityCollision(xMove, 0f)){
        moveXEnemy();
      }

      if(!checkEntityCollision(0f, yMove)){
        moveYEnemy();
      }
    }

    public void moveXEnemy(){
      if(xMove > 0){ //Moving right
        int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
        if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
          x += xMove;
        }

        else{
          x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
        }

      }
      else if(xMove < 0){ //Moving left
        int tx = (int) (x + xMove + bounds.x) / handler.getWorld().getEntityManager().getPlayer().getWidth();

        if(!collisionWithTile(tx, (int) (y + bounds.y) / handler.getWorld().getEntityManager().getPlayer().getHeight()) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / handler.getWorld().getEntityManager().getPlayer().getHeight())){
          x += xMove;
        }

        else {
          x = tx * handler.getWorld().getEntityManager().getPlayer().getWidth() + handler.getWorld().getEntityManager().getPlayer().getHeight() - bounds.x;
        }
      }

    }

    public void moveYEnemy(){
      if(yMove < 0){ //Up
        int ty = (int) (y + yMove + bounds.y) / handler.getWorld().getEntityManager().getPlayer().getHeight();
        if(!collisionWithTile((int)(x + bounds.x) / handler.getWorld().getEntityManager().getPlayer().getWidth(), ty) && !collisionWithTile((int)(x + bounds.x + bounds.width) / handler.getWorld().getEntityManager().getPlayer().getWidth(), ty)){
          y += yMove;
        }
        else{
          y = ty * handler.getWorld().getEntityManager().getPlayer().getHeight() + handler.getWorld().getEntityManager().getPlayer().getHeight() - bounds.y;
        }
      }

      else if (yMove > 0){ //Down
        int ty = (int) (y + yMove + bounds.y + bounds.height) / handler.getWorld().getEntityManager().getPlayer().getHeight();
        if(!collisionWithTile((int)(x + bounds.x) / handler.getWorld().getEntityManager().getPlayer().getWidth(), ty) && !collisionWithTile((int)(x + bounds.x + bounds.width) / handler.getWorld().getEntityManager().getPlayer().getWidth(), ty)){
          y += yMove;
        }

        else {
          y = ty * handler.getWorld().getEntityManager().getPlayer().getHeight() - bounds.y - bounds.height - 1;
        }
      }
    }


    public void moveX(){
      if(xMove > 0){ //Moving right
        int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
        if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
          x += xMove;
        }

        else{
          x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
        }

      }
      else if(xMove < 0){ //Moving left
        int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

        if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
          x += xMove;
        }

        else {
          x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
        }
      }

    }

    public void moveY(){
      if(yMove < 0){ //Up
        int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
        if(!collisionWithTile((int)(x + bounds.x) / Tile.TILEWIDTH, ty) && !collisionWithTile((int)(x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
          y += yMove;
        }
        else{
          y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
        }
      }

      else if (yMove > 0){ //Down
        int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
        if(!collisionWithTile((int)(x + bounds.x) / Tile.TILEWIDTH, ty) && !collisionWithTile((int)(x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
          y += yMove;
        }

        else {
          y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
        }
      }
    }

    protected boolean collisionWithTile(int x, int y){
      return handler.getWorld().getTile(x, y).isSolid();
    }
    //Getters

    public int getHealth(){
      return health;
    }

    public float getSpeed(){
      return speed;
    }

    public float getXMove(){
      return xMove;
    }

    public float getYMove(){
      return yMove;
    }

    //Setters

    public void setHealth(int health){
      this.health = health;
    }

    public void setSpeed(float speed){
      this.speed = speed;
    }

    public void setXMove(float xMove){
      this.xMove = xMove;
    }

    public void setYMove(float yMove){
      this.yMove = yMove;
    }

}
