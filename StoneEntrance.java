public class StoneEntrance extends Tile{

    public StoneEntrance (int id){
        super(Assets.stoneEntrance, id);
    }

    @Override
    public boolean isSolid(){
      return false;
    }
}
