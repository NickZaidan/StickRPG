import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class Item{

    //Handler

    public static Item[] items = new Item[256];
    public static Item woodItem = new Item(Assets.wood, "Wood", 0);

    //Class
    public static final int ITEMWIDTH = 20, ITEMHEIGHT = 20;
    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int id;
    protected Rectangle bounds;
    protected boolean pickedUp = false;
    protected int x, y, count;

    public Item(BufferedImage texture, String name, int id){
      this.texture = texture;
      this.name = name;
      this.id = id;
      count = randomCount();
      bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);
      items[id] = this;

    }
    private int randomCount (){
      Random rand = new Random();
      int x = rand.nextInt(3) + 0;
      return x;
    }
    
    public void tick(){
        if(handler.getWorld().getEntityManager().getPlayer().getCollisionsBounds(0f, 0f).intersects(bounds)){
          pickedUp = true;
          handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
        }
    }

    public void render(Graphics g){
        if(handler == null){
          return;
        }
        render(g, (int) (x - handler.getGameCamera().getXOffSet()), (int) (y - handler.getGameCamera().getYOffSet()));
    }

    public void render(Graphics g, int x, int y){
      if(count == 0){
        return;
      }
      g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
    }

    public Item createNew(int count){
      if(count == 0){
        return null;
      }
      Item i = new Item(texture, name, id);
      i.setPickedUp(true);
      i.setCount(count);
      return i;
    }

    public Item createNew(int x, int y){
      Item i = new Item(texture, name, id);
      i.setPosition(x, y);
      return i;
    }

    public void setPosition(int x, int y){
      this.x = x;
      this.y = y;
      bounds.x = x;
      bounds.y = y;
    }

    //Getters
    public int getCount(){
      return count;
    }

    public int getX(){
      return x;
    }

    public int getY(){
      return y;
    }

    public int getId(){
      return id;
    }

    public Handler getHandler(){
      return handler;
    }

    public String getName(){
      return name;
    }

    public BufferedImage getTexture(){
      return texture;
    }

    public boolean isPickedUp(){
      return pickedUp;
    }

    //Setters

    public void setCount(int count){
      this.count = count;
    }

    public void setX(int x){
      this.x = x;
    }

    public void setY(int y){
      this.y = y;
    }

    public void setPickedUp(boolean pickedUp){
      this.pickedUp = pickedUp;
    }


    public void setHandler(Handler handler){
      this.handler = handler;
    }

    public void setName(String name){
      this.name = name;
    }

    public void setTexture(BufferedImage texture){
      this.texture = texture;
    }
}
