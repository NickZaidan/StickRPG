import java.awt.Graphics;
import java.awt.Color;

public class Tree extends StaticEntity{
    boolean collisionBox = false;


    public Tree(Handler handler, float x, float y){
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width;
        bounds.height = height;
    }

    public void tick(){


    }

    public void die(){
      handler.getWorld().getItemManager().addItems(Item.woodItem.createNew((int) x, (int) y));
    }

    public void render(Graphics g){
        g.drawImage(Assets.treeStumpFlower, (int) (x - handler.getGameCamera().getXOffSet()), (int) (y - handler.getGameCamera().getYOffSet()), width, height, null);

        if(collisionBox == true){
          g.setColor(Color.red);
          g.fillRect((int) (x + bounds.x - handler.getGameCamera().getXOffSet()),
          (int) (y + bounds.y - handler.getGameCamera().getYOffSet()),
          bounds.width, bounds.height);
        }

    }

}
