package ndiaye_ouali_BabaIsYou;
import java.awt.Graphics2D;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import fr.umlv.zen5.KeyboardKey;
/**
 * 
 * @author ouali_fares ndiaye_moussa
 *
 */
public final class Block{
	
	private int x;
	private int y;
	private  ID Id;
	Image image ;
	/**
	 * Constructor for Block
	 * @param x the abscissa of the northwest corner of the block
	 * @param y ordinate of the northwest corner of the block
	 * @param Id -ID
	 */
	public Block (int x, int y, ID Id )  {
		this.x = x;
		this.y = y;
		this.Id=Id;	
		
		try {
			image = ImageIO.read(new File(Id.file()));
		}catch (IOException e) {
			System.out.println("no image found for " + Id);
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * moves a block in one direction
	 * @param direction -KeyboardKey
	 * @param nbcaseH -number of cells in height of the area
	 * @param nbcaseW -number of cells in width of the area	
	 * @return false is block can't move
	 */
	public boolean move(KeyboardKey direction,int nbcaseH,int nbcaseW) {
		switch (direction) {
		
			case DOWN:
				if (y < nbcaseH - 1) {
					y++;
					return true;
				}
				break;
			case UP:
				if (y > 0) {
					y--;
					return true;
				}
				break;
			
			case LEFT:
				if (x > 0) {
					x--;
					return true;
				}
				break;
			case RIGHT:
				if (x < nbcaseW - 1) {
					x++;
					return true;
				}
				break;
			default:
				return false;
			}
				return false;
		}
	/**
	 * check if there is a collision between two blocks
	 * @param other -the adjacent block
	 * @param direction -KeyboardKey
	 * @return true if there is a collision in only one side else false
	 */
	public boolean collideOneSide(Block other, KeyboardKey direction) {
		Objects.requireNonNull(other);
		Objects.requireNonNull(direction);
		return switch (direction) {
		case UP -> this.x == other.x && this.y - 1 == other.y;
		case LEFT -> this.x-1 == other.x && this.y == other.y;
		case RIGHT -> this.x+1 == other.x && this.y == other.y;
		case DOWN -> this.x == other.x && this.y + 1 == other.y;
		default -> false;
		};
	}
	/**
	 * check if there is an intersection between two blocks
	 * @param block -the adjacent block
	 * @return true if there is an intersection between two blocks else false
	 */
	public boolean intersected(Block block) {
		Objects.requireNonNull(block);
		return this.x == block.x && this.y == block.y ;
	}

	/**
	 * draws the image according to these coordinates x and y 
	 * @param g -Graphics2D
	 * @param hight -window height
	 * @param width -window width
	 */
	public void draw(Graphics2D g,float hight,float width) {
		
    	var h =(int)hight;
    	var w = (int)width;
    	var hight_image = h/25;
    	var width_image = w/25;
    	
    	g.drawImage(image,x*width_image,y*hight_image,width_image,hight_image, null); 
	}
	
	/**
	 * change a block into another block
	 * @param name -ID
	 */
	public void changeBlock(ID name) {
		this.Id = name;
		try {
			this.image = ImageIO.read(new File(name.file()));
		} catch (IOException e) {
			System.out.println("no image found for " + name);
			e.printStackTrace();
			System.exit(1);
		}
	}
	/**
	 * Getter for the x field
	 * @return the x field
	 */
	public int getX() {
		return x;
	}
	/**
	 * Getter for the y field
	 * @return the y field
	 */
	public int getY() {
		return y;
	}
	/**
	 * Getter for the Id field
	 * @return the Id field
	 */
	public ID getId() {
		return Id;
	}

}