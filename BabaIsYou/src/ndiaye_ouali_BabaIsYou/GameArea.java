package ndiaye_ouali_BabaIsYou;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors; 
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.KeyboardKey;
/**
 * 
 * @author ouali_fares ndiaye_moussa
 *
 */
public class GameArea {
	
	private final int nbcaseH ;
	private final int nbcaseW ;
	
	private ArrayList<Block> blocks;
	private final HashMap<ID, ArrayList<ID>> proprety;
	/**
	 * Constructor for GameArea
	 */
	public GameArea(){
		nbcaseH = 25;
		nbcaseW = 25 ;
		blocks= new ArrayList<>();
		proprety = new HashMap<>();
		for(var id : ID.values()) {
			proprety.put(id, new ArrayList<>());
		}
	}
	
	/**
	 * add a block to the arrayList blocks
	 * @param block -Block
	 */
	public void addBlock(Block block) {
		Objects.requireNonNull(block);
		blocks.add(block);
	}
	/**
	 * add property props to ID name
	 * @param name -ID
	 * @param prop	-the property added to the name
	 */
	public void addProprety(ID name , ID prop) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(prop);
		if(!prop.getCategorie().equals(Categories.Property)) {
			throw new IllegalArgumentException("prop is not a Property");

		}
		else {
			proprety.get(name).add(prop);
		}
	}
	/**
	 * check if a block contains a property or not
	 * @param block -Block
	 * @param prop -Property
	 * @return returns true if the block contains the property prop 
	 * else returns false
	 */
	public boolean checkProp(Block block, ID prop) {
		Objects.requireNonNull(block);
		Objects.requireNonNull(prop);
		if (!prop.getCategorie().equals(Categories.Property)) {
			throw new IllegalArgumentException("prop is not a Property");
			
		}
		else {
			return proprety.get(block.getId()).contains(prop);
		}	
	}
	/**
	 * check if there is a collision between 
	 * blockToMove and another block 
	 * that has the stop property
	 * @param blockToMove -the block that is in movement
	 * @param direction -KeyboardKey
	 * @return returns true if there is a collision else returns false 
	 */
	public boolean stop (Block blockToMove,KeyboardKey direction) {
		for (var block : blocks) {
			if(blockToMove.collideOneSide(block,direction) && checkProp(block, ID.Stop) ) {
				return true;
			}
		}
		return false;
	}
	/**
	 * check if there is a collision between 
	 * blockToMove and another block 
	 * that has the hot property
	 * @param blockToMove -the block that is in movement
	 * @return returns true if there is a collision else returns false 
	 */
	public boolean Hot (Block blockToMove) {
		for (var block : blocks) {
			if(blockToMove.intersected(block) && checkProp(block, ID.Hot) ) {
				return true;
			}
		}
		return false;
	}
	/**
	 * check if there is an intersection between 
	 * blockToMove and another block 
	 * that has the defeat property
	 * @param blockToMove -the block that is in movement
	 * @return returns true if there is a collision else returns false 
	 */
	public boolean defeat (Block blockToMove) {
		for (var block : blocks) {
			if(blockToMove.intersected(block) && checkProp(block, ID.Defeat) ) {
				return true;
			}
		}
		return false;
	}
	/**
	 * check if there is an intersection between 
	 * blockToMove and another block 
	 * that has the sink property
	 * @param blockTomove -the block that is in movement
	 * @return returns true if there is a collision else returns false 
	 */
	public boolean collideSink(Block blockTomove) {
		for (var block : blocks){
			if(blockTomove.intersected(block) && checkProp(block, ID.Sink)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * check if there is a collision between block and block with sink property 
	 */
	 public void sink() {
		 for(var block : blocks) {
			 if(checkProp(block, ID.You) || checkProp(block, ID.Push)) {
				if(collideSink(block)) {
					blocks.remove(block);
				}
			 }
		 }
	 }
	/**
	 * push a block who have the Push property 
	 * @param direction -KeyboardKey
	 * @param block -Block
	 * @return true if the block have successfully moved
	 */
	public boolean push(KeyboardKey direction, Block block) {
		if (stop(block, direction)) {
			return false;
		}
		for (var blockToPush : blocks) {
			if (checkProp(blockToPush, ID.Push) &&
					block.collideOneSide(blockToPush,direction)) {
				if (this.push(direction, blockToPush)) {
					return blockToPush.move( direction, nbcaseH,nbcaseW);
				}
				return false;
			}
		}
		return true;
	}
	/**
	 * displays a game over message if a block with the property you
	 * touch a block with the property you defeat also a block 
	 * with the property melt touch a block with the property hot
	 * @param context -ApplicationContext
	 * @param width -window width
	 * @param height -window height
	 */
	public void applyPropsLoose(ApplicationContext context , float width,float height) {
		
		
		for(var block : blocks) {
			if(checkProp(block,ID.You)){
				if(defeat (block)) {
					PrintGameover(context, width , height );
				}
				if(checkProp(block,ID.Melt)) {
					if(Hot(block)) {
						PrintGameover(context, width , height );
					}
				}
			}
		}
	}
	/**
	 * check if there is an intersection between 
	 * blockToMove and another block that has the win property
	 * @param blockTomove -the block that is in movement
	 * @return returns true if there is an intersection else returns false 
	 */
	public boolean collideWithWin(Block blockTomove) {
		for (var block : blocks){
			if(blockTomove.intersected(block) && checkProp(block, ID.Win)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * check if a block with 
	 * the true property intersects with a block of the win property
	 * @return returns true if the block with the true property 
	 * intersects with a block of the win property
	 */
	public boolean win(){
		for (var block : blocks) {
			if (checkProp(block,ID.You)) {
				if(collideWithWin(block)) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * applies the necessary propriety to move the block
	 * @param direction -KeyboardKey
	 */
	public void applyProps(KeyboardKey direction) {
		for (var block : blocks) {
			if (checkProp(block,ID.You)) {
				if (!stop( block,direction)) {
					if (push(direction, block)) {
							block.move(direction, nbcaseH,nbcaseW);
					}
				}
			}
		}
	}
	/**
	 * clear all block in the arraList blocks
	 */
	public void remove() {
		blocks= new ArrayList<>();
	}
	/**
	 * Get a List of all blocks who have categories Noun
	 * @return the list of blocks
	 */
	public List<Block> getNoun(){
		return blocks.stream()
		.filter(b -> b.getId().getCategorie().equals(Categories.Noun))
		.collect(Collectors.toList());
	}
	
	/**
	 * Get a List of all blocks who have categories Property
	 * @return the list of blocks
	 */
	public List<Block> getProprety(){
		return blocks.stream()
		.filter(b -> b.getId().getCategorie().equals(Categories.Property))
		.collect(Collectors.toList());
	}
	/**
	 * Get a List of all blocks who have categories operators
	 * @return the list of blocks
	 */
	public List<Block> getOperator(){
		return blocks.stream()
		.filter(b -> b.getId().getCategorie().equals(Categories.operators))
		.collect(Collectors.toList());
	}
	/**
	 * Get a List of all blocks who have the ID specified
	 * @param block -ID
	 * @return the list of blocks
	 */
	public List<Block> getBlock(ID block) {
		return blocks.stream()
		.filter(bl -> bl.getId().equals(block))
		.collect(Collectors.toList());
	}
	/**
	 * adds the push function to all the blocks that are basic movable
	 */
	public void allpush() {
		var nounsText = getNoun();
		var props = getProprety();
		var ops = getOperator();
		for (var noun : nounsText) {
			this.addProprety(noun.getId(), ID.Push);
		}
		for (var prop : props) {
			this.addProprety(prop.getId(), ID.Push);
		}
		for (var op : ops) {
			this.addProprety(op.getId(), ID.Push);
		}
	}
	/**
	 * discard all propriety of the area
	 */
	public void discard() {
		for (var block : proprety.keySet()) {
			proprety.replace(block, new ArrayList<ID>());
		}
	}
	/**
	 * display game over in the screen
	 * @param context -ApplicationContext
	 * @param width -window width
	 * @param height -window height
	 */
	public void PrintGameover(ApplicationContext context, float width, float height){
        context.renderFrame(graphics -> {
            graphics.setColor(new Color(0f,0f,0f,0.5f));
            graphics.fill(new  Rectangle2D.Float(0, 0, width, height));
            graphics.setColor(Color.WHITE);
            graphics.drawString("GAMEOVER", width/2, height/4);
        });
    }
	
	/**
	 * displays all the blocks that are in the block list
	 * @param context -ApplicationContext
	 * @param height -window height
	 * @param width -window width
	 */
	public void draw(ApplicationContext context,float height,float width) {
		var rec = new Rectangle2D.Float(0,0, width, height);
			context.renderFrame(graphics ->{
			graphics.setColor(Color.BLACK);
			graphics.fill(rec);
			for(var elm:blocks) {
				elm.draw(graphics,height,width);
			 }
		});
	}
	/**
	 * activates the rulers in the right direction
	 * @param noun  -Block
	 * @param opr -block that has the Property
	 */
	public void activeRulesSideRight(Block noun , Block opr) {
		var nounsText = getNoun();
		var props = getProprety();
		if (noun.collideOneSide(opr, KeyboardKey.RIGHT)) {
			for (var prop : props) {
				if (opr.collideOneSide(prop, KeyboardKey.RIGHT)) {
					this.addProprety(noun.getId().switchBlockNoun(), prop.getId());
				}
			}
			for (var noun2 : nounsText) {
				if (opr.collideOneSide(noun2, KeyboardKey.RIGHT)) {
					blocks.forEach(b -> {
						if (b.getId().equals(noun.getId().switchBlockNoun())) {
							b.changeBlock(noun2.getId().switchBlockNoun());
						}
					});
				}
			}
		}
	}
	/**
	 * activates the rulers in the down direction
	 * @param noun -block which represents a noun
	 * @param opr -block which represents a operator
	 */
	public void activeRulesSideDown (Block noun ,Block opr) {
		var nounsText = getNoun();
		var props = getProprety();
		if (noun.collideOneSide(opr,KeyboardKey.DOWN)) {
			for (var prop : props) {
				if (opr.collideOneSide(prop, KeyboardKey.DOWN)) {
					this.addProprety(noun.getId().switchBlockNoun(),prop.getId());
				}
			}
			for (var noun2 : nounsText) {
				if (opr.collideOneSide(noun2, KeyboardKey.DOWN)) {
					blocks.forEach(b -> {
						if (b.getId().equals(noun.getId().switchBlockNoun())) {
							b.changeBlock(noun2.getId().switchBlockNoun());
						}
					});
				}
			}
		}
	}
	/**
	 * active all rules of game
	 */
	public void rulesOfGames() {
		var nounsText = getNoun();
		var operator = getOperator();
		this.discard();
		for (var noun : nounsText) {
			for (var opr : operator) {
				activeRulesSideDown(noun,opr);
				activeRulesSideRight(noun ,opr);
			}
		}
		allpush();
	}

}

