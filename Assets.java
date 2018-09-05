import java.awt.image.BufferedImage;
import java.awt.Font;

public class Assets{

  public static BufferedImage grass, stone, water, treeStumpFlower, treeStump, cactus, wood, stoneEntrance;
  public static BufferedImage[] player_down, player_up, player_left, player_right;
  public static BufferedImage[] enemy_down, enemy_up, enemy_left, enemy_right;
  public static BufferedImage[] btn_start;
  public static BufferedImage[] bigTree;
  public static BufferedImage inventoryScreen;
  public static Font font28;


  private static final int width = 32, height = 32;
  private static final int enemyWidth = 90, enemyHeight = 90;



  public static void init(){

    //Spritesheet loaders
    font28 = FontLoader.loadFont("res/fonts/MyFont.ttf", 28);
    SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/res/textures/spritesheetmarginlessEdited.png"));
    SpriteSheet start = new SpriteSheet(ImageLoader.loadImage("/res/textures/startYes.png"));
    SpriteSheet startC = new SpriteSheet(ImageLoader.loadImage("/res/textures/startNo.png"));
    SpriteSheet enemy = new SpriteSheet(ImageLoader.loadImage("/res/textures/characters.png"));

    inventoryScreen = ImageLoader.loadImage("/res/textures/Inventory.png");

    //Player Animations

    //Animation right player frames
    player_down = new BufferedImage[3];
    player_down[0] = sheet.crop(width * 18, height * 25, width, height);
    player_down[1] = sheet.crop(width * 19, height * 25, width, height);
    player_down[2] = sheet.crop(width * 20, height * 25, width, height);

    //Animation left player frames
    player_left = new BufferedImage[3];
    player_left[0] = sheet.crop(width * 18, height * 26, width, height);
    player_left[1] = sheet.crop(width * 19, height * 26, width, height);
    player_left[2] = sheet.crop(width * 20, height * 26, width, height);

    //Animation right player frames
    player_right = new BufferedImage[3];
    player_right[0] = sheet.crop(width * 18, height * 27, width, height);
    player_right[1] = sheet.crop(width * 19, height * 27, width, height);
    player_right[2] = sheet.crop(width * 20, height * 27, width, height);

    //Animation up player frames
    player_up = new BufferedImage[3];
    player_up[0] = sheet.crop(width * 18, height * 28, width, height);
    player_up[1] = sheet.crop(width * 19, height * 28, width, height);
    player_up[2] = sheet.crop(width * 20, height * 28, width, height);

    //Enemy Animations

    //Animation right enemy frames
    enemy_down = new BufferedImage[3];
    enemy_down[0] = sheet.crop(width * 18, height * 25, width, height);
    enemy_down[1] = sheet.crop(width * 19, height * 25, width, height);
    enemy_down[2] = sheet.crop(width * 20, height * 25, width, height);

    //Animation left enemy frames
    enemy_left = new BufferedImage[3];
    enemy_left[0] = sheet.crop(width * 18, height * 26, width, height);
    enemy_left[1] = sheet.crop(width * 19, height * 26, width, height);
    enemy_left[2] = sheet.crop(width * 20, height * 26, width, height);

    //Animation right enemy frames
    enemy_right = new BufferedImage[3];
    enemy_right[0] = sheet.crop(width * 18, height * 27, width, height);
    enemy_right[1] = sheet.crop(width * 19, height * 27, width, height);
    enemy_right[2] = sheet.crop(width * 20, height * 27, width, height);

    //Animation up enemy frames
    enemy_up = new BufferedImage[3];
    enemy_up[0] = sheet.crop(width * 18, height * 28, width, height);
    enemy_up[1] = sheet.crop(width * 19, height * 28, width, height);
    enemy_up[2] = sheet.crop(width * 20, height * 28, width, height);

    //Start button
    btn_start = new BufferedImage[2];
    btn_start[0] = startC.crop(0, 0, 128,64);
    btn_start[1] = start.crop(0, 0, 128, 64);


    //Tree
    bigTree = new BufferedImage[2];
    bigTree[0] = sheet.crop(width * 2, height * 12, width * 2, height * 2);
    bigTree[1] = sheet.crop(0 , height * 12, width * 2, height * 2);



    //Average entities
    grass = sheet.crop(0, height, width, height);
    stone = sheet.crop(width*3, height, width, height);
    water = sheet.crop(0, height*16, width, height);
    stoneEntrance = sheet.crop(width * 5, height * 1, width, height);
    treeStumpFlower = sheet.crop(0, height * 6, width, height);
    treeStump = sheet.crop(width, height * 7, width, height);
    cactus = sheet.crop(0, height * 11, width, height);
    wood = sheet.crop(width * 8, height, width, height);


  }
}
