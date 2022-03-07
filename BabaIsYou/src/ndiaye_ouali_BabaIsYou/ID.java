package ndiaye_ouali_BabaIsYou;
/**
 * 
 * @author ouali_fares ndiaye_moussa
 *
 */
public enum ID {
	/**
	 *  name with Categories.Block
	 */
	Baba(Categories.Block),
	/**
	 *  name with Categories.Block
	 */
	Wall(Categories.Block), 
	/**
	 *  name with Categories.Block
	 */
	Rock(Categories.Block),
	/**
	 *  name with Categories.Block
	 */
	Flag(Categories.Block), 
	/**
	 *  name with Categories.Block
	 */
	Lava(Categories.Block),
	/**
	 *  name with Categories.Block
	 */
	Skull(Categories.Block),
	/**
	 *  name with Categories.Block
	 */
	Water(Categories.Block),
	
	/**
	 *  name with Categories.Noun
	 */
	BABA(Categories.Noun),
	/**
	 *  name with Categories.Noun
	 */
	WALL(Categories.Noun),
	/**
	 *  name with Categories.Noun
	 */
	ROCK(Categories.Noun), 
	/**
	 *  name with Categories.Noun
	 */
	FLAG(Categories.Noun), 
	/**
	 *  name with Categories.Noun
	 */
	LAVA(Categories.Noun),
	/**
	 *  name with Categories.Noun
	 */
	WATER(Categories.Noun),
	/**
	 *  name with Categories.Noun
	 */
	SKULL(Categories.Noun),
	/**
	 *  operators
	 */
	Is(Categories.operators), 
	/**
	 *  name with Categories.Property
	 */
	You(Categories.Property), 
	/**
	 *  name with Categories.Property
	 */
	Stop(Categories.Property), 
	/**
	 *  name with Categories.Property
	 */
	Push(Categories.Property),
	/**
	 *  name with Categories.Property
	 */
	Win(Categories.Property), 
	/**
	 *  name with Categories.Property
	 */
	Melt(Categories.Property),
	/**
	 *  name with Categories.Property
	 */
	Defeat(Categories.Property),
	/**
	 *  name with Categories.Property
	 */
	Sink(Categories.Property),
	/**
	 *  name with Categories.Property
	 */
	Hot(Categories.Property);
	
	private Categories categories;
	/**
	 * Constructor for Block
	 * @param categorie -Categories
	 */
	private ID (Categories categorie) {
		this.categories =categorie;
	}
	/**
	 * Getter for the categories field
	 * @return categories
	 */
	public Categories getCategorie() {
		return categories;
	}
	
	/**
	 * search the name of the file according to its category
	 * @return -String return the name of the file according to it's category
	 */
	private String dir() {
		return switch (categories) {
		case Block -> "./Name/";
		case Noun -> "./Name/";
		case operators -> "./Ope/";
		case Property -> "./Prop/";
		default -> throw new IllegalArgumentException("Unexpected value: " + categories);
		};
	}
	/**
	 * search the file according to its categories
	 * @return -String return the name of the file according to its categories
	 */
	private String imageName() {
		return switch (categories) {
		case Block -> toString().toUpperCase() + "_0.gif";
		case Noun -> "Text_" + toString() + "_0.gif";
		case operators -> "Text_" + toString().toUpperCase() + "_0.gif";
		case Property -> "Text_" + toString().toUpperCase() + "_0.gif";
		default -> throw new IllegalArgumentException("Unexpected value: " + categories);
		};
	}
	/**
	 * search the path to display an image
	 * @return -String return the path to display an image
	 */
	public String file() {
		return dir() +  imageName();
	}
	/**
	 * changes the last three letters to lower case
	 * @param name -String
	 * @return -String return the first original letter 
	 * and the last three lower case letters
	 */
	public String switchName(String name) {
		return name.substring(0,1)+name.substring(1,name.length()).toLowerCase();
	}
	/**
	 * change the id of a block according to it's category
	 * @return -ID
	 */
	public ID switchBlockNoun() {
		return switch (categories) {
		case Block -> ID.valueOf(toString().toUpperCase());
		case Noun -> ID.valueOf(switchName(toString()));
		default -> this;
		};
	}
	

}
