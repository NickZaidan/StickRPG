public class GameCamera{

  private float xOffSet, yOffSet;
  private Handler handler;
  public GameCamera(Handler handler, float xOffSet, float yOffSet){
      this.xOffSet = xOffSet;
      this.yOffSet = yOffSet;
      this.handler = handler;
  }

  public void checkBlankSpace(){
    if(xOffSet < 0){
      xOffSet = 0;
    }

    else if (xOffSet > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()){
      xOffSet =  handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
    }

    if(yOffSet < 0){
      yOffSet = 0;
    }

    else if (yOffSet > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight()){
      yOffSet = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
    }

  }
  public void centreOnEntity(Entity e){
    xOffSet = e.getX() - handler.getWidth()/2 + e.getWidth()/2;
    yOffSet = e.getY() - handler.getHeight()/2 + e.getHeight()/2;
    checkBlankSpace();
  }
  public void move(float xAmt, float yAmt){
    xOffSet += xAmt;
    yOffSet += yAmt;
    checkBlankSpace();
  }

  //Getters
  public float getXOffSet(){
    return xOffSet;
  }

  public float getYOffSet(){
    return yOffSet;
  }

  //Setters

  public void setXOffSet(float xOffSet){
    this.xOffSet = xOffSet;
  }

  public void setYOffSet(float yOffSet){
    this.yOffSet = yOffSet;
  }
}
