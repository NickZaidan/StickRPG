import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Graphics;

public class ItemManager{

    private Handler handler;
    private ArrayList<Item> items;


    public ItemManager(Handler handler){
        this.handler = handler;
        items = new ArrayList<Item>();
    }

    public void tick(){
        Iterator<Item> it = items.iterator();
        while(it.hasNext()){
          Item i = it.next();
          i.tick();
          if(i.isPickedUp()){
              it.remove();
          }
        }
    }

    public void render(Graphics g){
        for(Item i: items){
            i.render(g);
        }
    }

    public void addItems(Item i){
      i.setHandler(handler);
      items.add(i);
    }

    //Getter

    public Handler getHandler(){
      return handler;
    }

    //Setter

    public void setHandler(Handler handler){
      this.handler = handler;
    }
}
