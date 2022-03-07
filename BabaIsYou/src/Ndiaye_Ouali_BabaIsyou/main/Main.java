package Ndiaye_Ouali_BabaIsyou.main;
import java.awt.Color;

import fr.umlv.zen5.Application;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.KeyboardKey;
import fr.umlv.zen5.ScreenInfo;
import fr.umlv.zen5.Event.Action;
import ndiaye_ouali_BabaIsYou.Window;
/**
 * 
 * @author ouali_fares moussa_ndiaye
 *
 */

public class Main {
	
		static int levelNumber = 0;
		
		/**
		 * main
		 * @param args -array
		 */
		public static void main(String[] args) {
			var window = new Window();
			
				Application.run(Color.BLACK, context -> {
				ScreenInfo screenInfo = context.getScreenInfo();
				float width = screenInfo.getWidth();
				float height = screenInfo.getHeight();
				if(args.length!=0 ) {
					if(args[0].equals("--levels")) {
						if(args[1].equals("levels"))
							levelNumber = 0;
						else {
							throw new IllegalArgumentException("file not found");
						}
					}
					
					if(args[0].equals("--level"))
						if(args[1]!=null)
							levelNumber = window.nbrOfLevel(args[1]);
						else {
							throw new IllegalArgumentException("level not found");
						}
					
					var area  = window.initWindow (window.ChooseLevel(levelNumber ));
					for (;;) {
						Event event = context.pollOrWaitEvent(10);
						
						if (event == null) {
							continue;
						}
						Action action = event.getAction();
						if (action == Action.KEY_PRESSED) {
							if (event.getKey() == KeyboardKey.Q) {
								System.out.println("abort abort !");
								context.exit(0);
								return;
							}
							area.applyProps(event.getKey());
							area.rulesOfGames();
							area.draw(context,height,width);
							area.applyPropsLoose(context, width,height);
							if(area.win() && args[0].equals("--levels")) {
								levelNumber+=1;
								area.remove();
								window.initWindow (window.ChooseLevel(levelNumber));
							}
							if(area.win() && args[0].equals("--level")) {
								context.exit(0);
							}
						}	
					}	
				}	
				else {
					var area  = window.initWindow (window.ChooseLevel(levelNumber));
					for (;;) {
						Event event = context.pollOrWaitEvent(10);
						
						if (event == null) {
							continue;
						}
						Action action = event.getAction();
						if (action == Action.KEY_PRESSED) {
							if (event.getKey() == KeyboardKey.Q) {
								System.out.println("abort abort !");
								context.exit(0);
								return;
							}
							area.applyProps(event.getKey());
							area.rulesOfGames();
							area.draw(context,height,width);
							area.applyPropsLoose(context, width,height);
							if(area.win()) {
								levelNumber+=1 ;
								area.remove();
								window.initWindow (window.ChooseLevel(levelNumber));
							}
						}
					}
				}
			});
		}	

}
