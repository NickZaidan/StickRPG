import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Color;

public class Inventory{

    private Handler handler;
    private boolean active = false;
    private ArrayList<Item> inventoryItems;

    private int invX = 100, invY = 100, invWidth = 800, invHeight = 400;
    private int invListCentreX = invX + 350, invListCentreY = invY + 180;
    private int invListSpace = 60;

    private int invImageX = invX + 722, invImageY = 200, invImageWidth = 90, invImageHeight = 85;

    private int selectedItem = 0;

  public Inventory(Handler handler){
      this.handler = handler;
      inventoryItems = new ArrayList<Item>();
      addItem(Item.woodItem.createNew(5));
      addItem(Item.woodItem.createNew(7));
      addItem(Item.woodItem.createNew(9));
      addItem(Item.woodItem.createNew(2));
      addItem(Item.woodItem.createNew(4));

      addItem(Item.woodItem.createNew(7));
      addItem(Item.woodItem.createNew(9));
      addItem(Item.woodItem.createNew(2));
      addItem(Item.woodItem.createNew(4));

      addItem(Item.woodItem.createNew(7));
      addItem(Item.woodItem.createNew(9));
      addItem(Item.woodItem.createNew(2));
      addItem(Item.woodItem.createNew(4));
  }

  public void tick(){
      if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)){
        active = !active;
      }

      if(!active){
        return;
      }

      if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)){
        selectedItem--;
      }
      if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)){
        selectedItem++;
      }

      if(selectedItem < 0){
        selectedItem = inventoryItems.size() - 1;
      }

      else if(selectedItem >= inventoryItems.size()){
        selectedItem = 0;
      }
  }

  public void render(Graphics g){
    if(!active){
      return;
    }
    g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);
    int len = inventoryItems.size();

    if(len == 0){
      return;
    }

    for(int i = -2; i < 4; i++){
      if(selectedItem + i < 0 || selectedItem + i >= len){
        continue;
      }
      if(i == 0){
        Text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName() + " <" , invListCentreX, invListCentreY + i * invListSpace, true, Color.YELLOW, Assets.font28);
      }
      else{
        Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCentreX, invListCentreY + i * invListSpace, true, Color.WHITE, Assets.font28);
      }

    }
      Item item = inventoryItems.get(selectedItem);
      //Code for when I implement images into the inventory
      /*
      g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
      */

      Text.drawString(g, Integer.toString(item.getCount()),invImageX, invImageY, true, Color.WHITE, Assets.font28);
  }

  //Inventory Methods
  public void addItem(Item item){

    
    for(Item i: inventoryItems){
      if(i.getId() == item.getId()){
        i.setCount(i.getCount() + item.getCount());
        return;
      }
    }

    inventoryItems.add(item);
  }


  //Getters

  public Handler getHandler(){
    return handler;
  }

  public boolean isActive(){
    return active;
  }
}
