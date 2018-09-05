import java.awt.Graphics;

public class MenuState extends State{

  private UIManager uiManager;

  public MenuState(Handler handler){
      super(handler);
      uiManager = new UIManager(handler);
      handler.getMouseManager().setUIManager(uiManager);
      uiManager.addObject(new UIImageButton(436, 268, 128, 64, Assets.btn_start, new ClickListener(){
          public void onClick(){
            handler.getMouseManager().setUIManager(null);
            State.setState(handler.getGame().gameState);
          }
      }));
  }

  public void tick(){
      uiManager.tick();
  }

  public void render(Graphics g){
      uiManager.render(g);

  }


}
