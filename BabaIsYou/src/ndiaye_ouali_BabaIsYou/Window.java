package ndiaye_ouali_BabaIsYou;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
/**
 * 
 * @author ouali_fares ndiaye_moussa
 *
 */
public class Window {
	
	private GameArea area;
	private FileReader file; 
	/**
	 * Constructor for window
	 */
	public Window()  {
		area = new GameArea();		
	}
	/**
	 * open and read the file, add blocks to the area 
	 * according to the character of the file.
	 * @param filename -String
	 * @return - Game area -Game area
	 */
	public GameArea initWindow (String filename) {
		try {
			file = new FileReader(filename);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		var myReader = new Scanner(file);  
		for(var x = 0; myReader.hasNextLine(); x++) {
			var curline = myReader.nextLine();
			for(var y = 0; y < curline.length();y++) {
				switch(curline.charAt(y)) {
					case 'b'->{
						area.addBlock(new Block(y, x, ID.Baba));
					}
					case 'B'->{
						area.addBlock(new Block(y, x, ID.BABA));
					}
					case 'w'->{
						area.addBlock(new Block(y, x, ID.Wall));
					}
					case 'W'->{
						area.addBlock(new Block(y, x, ID.WALL));
					}
					case 'r'->{
						area.addBlock(new Block(y, x, ID.Rock));
					}
					case 'R'->{
						area.addBlock(new Block(y, x, ID.ROCK));
					}
					case 'f'->{
						area.addBlock(new Block(y, x, ID.Flag));
					}
					case 'F'->{
						area.addBlock(new Block(y, x, ID.FLAG));
					}
					case 'I'->{
						area.addBlock(new Block(y, x, ID.Is));
					}
					case 'Y'->{
						area.addBlock(new Block(y, x, ID.You));
					}
					case 'P'->{
						area.addBlock(new Block(y, x, ID.Push));
					}
					case 'S'->{
						area.addBlock(new Block(y, x, ID.Stop));
					}
					case '!'->{
						area.addBlock(new Block(y, x, ID.Win));
					}
					case 'e'->{
						var water = new Block(y, x, ID.Water);
						area.addBlock(water);
					}
					case 'E'->{
						area.addBlock(new Block(y, x, ID.WATER));
					}
					case 'C'->{
						area.addBlock(new Block(y, x, ID.Sink));
					}
					case 't'->{
						area.addBlock(new Block(y, x, ID.Skull));
					}
					case 'T'->{
						area.addBlock(new Block(y, x, ID.SKULL));
					}
					case 'D'->{
						area.addBlock(new Block(y, x, ID.Defeat));
					}
					case 'M'->{
						area.addBlock(new Block(y, x, ID.Melt));
					}
					case 'H'->{
						area.addBlock(new Block(y, x, ID.Hot));
					}
					case 'l'->{
						area.addBlock(new Block(y, x, ID.Lava));
					}
					case 'L'->{
						area.addBlock(new Block(y, x, ID.LAVA));
					}
				}
			}
		}
		myReader.close();
		return area;
	}
	
	/**
	 * search the path of the level file
	 * @param nbroflevels -Integer
	 * @return -String returns the path of the level file
	 */
	public String ChooseLevel(int nbroflevels) {
		return switch (nbroflevels){
			case 0 -> "src/levels/level0.txt";
			case 1 -> "src/levels/level1.txt";
			case 2 -> "src/levels/level2.txt";
			case 3 -> "src/levels/level3.txt";
			case 4 -> "src/levels/level4.txt";
			case 5 -> "src/levels/level5.txt";
			case 6 -> "src/levels/level6.txt";
		default -> "no levels for this number";
		};
	}
	/**
	 * 
	 * @param nbroflevels -Integer
	 * @return -Integer returns a number according to the level number
	 */
	public int nbrOfLevel(String nbroflevels) {
		return switch (nbroflevels){
			case "level0" -> 0;
			case "level1" -> 1;
			case "level2" -> 2;
			case "level3" -> 3;
			case "level4" -> 4;
			case "level5" -> 5;
			case "level6" -> 6;
		default -> 10;
		};
	}
}