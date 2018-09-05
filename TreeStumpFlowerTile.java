public class TreeStumpFlowerTile extends Tile {
      public TreeStumpFlowerTile(int id){
          super(Assets.treeStumpFlower, id);
      }


      @Override
      public boolean isSolid(){
        return true;
      }
}
