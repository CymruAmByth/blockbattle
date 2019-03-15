/*
 *  Copyright 2016 riddles.io (developers@riddles.io)
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 *
 *      For the full copyright and license information, please view the LICENSE
 *      file that was distributed with this source code.
 */

package bot;

import field.Cell;
import field.Field;
import moves.Move;
import moves.MoveType;
import player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * BotAlpha class
 *
 * This class is where the main logic should be. Implement getMove() to
 * return something better than random moves.
 *
 * @author Jim van Eeden <jim@riddles.io>
 */

public class BotAlpha implements Bot {


	/**
	 * Returns a random amount of random moves
	 * @param state current state of the bot
	 * @return a list of moves to execute
	 */
	public Move getMove(BotState state) {
		List<MoveType> allMoves = Collections.unmodifiableList(Arrays.asList(MoveType.values()));
		MoveType move = null;



		Player player = state.getPlayers().get(state.getMyName());
		Field field = player.getField();
		int fieldWith = field.getWidth();
		int fieldHeight = field.getHeight();

		int[] topline = new int[fieldWith];
		int[] toplineDelta = new int[fieldWith-1];

		for(int i =0; i<fieldWith; i++){
			topline[i] = 0;
			for(int j = (fieldHeight-1); j>0; j--){
				Cell c = field.getCell(i,j);
				if(c.isBlock() || c.isSolid()){
					topline[i] = fieldHeight-j;
				}
			}
			if(i>0){
				toplineDelta[i-1] = topline[i]-topline[i-1];
			}
		}


		int widthH = 1;
		int widthV = 4;
		int[] profileV;

		switch(state.getCurrentShape()){
			case I:
				widthH = 1;
				widthV = 4;
				profileV = new int[] {0,0,0};
				break;
			case J:
				widthH = 2;
				widthV = 3;
				profileV = new int[] {0,0};
				break;
			case L:
				widthH = 2;
				widthV = 3;
				profileV = new int[] {0,0};
				break;
			case O:
				widthH = 2;
				widthV = 2;
				profileV = new int[] {0};
				break;
			case S:
				widthH = 2;
				widthV = 3;
				profileV = new int[] {0,1};
				break;
			case T:
				widthH = 2;
				widthV = 3;
				profileV = new int[] {0,0};
				break;
			case Z:
				widthH = 2;
				widthV = 3;
				profileV = new int[] {-1,0};
				break;
			default:
				widthH = 4;
				widthV = 4;
				profileV = new int[] {0,0,0};
		}

		int bestIndex = -1;
		int backupIndex = 0;
		int backupHighestRow = fieldHeight;




		for(int i=0; i<=fieldWith-widthV; i++){
			int highestCell = topline[i];
			boolean fit = true;
			for(int j=0; j<widthV; j++){
				//backupindex check
				if(highestCell < topline[i+j]) {
					highestCell = topline[i+j];
				}

				//best index check
				if(j<widthV-1){
					if(toplineDelta[i+j] != profileV[j]){
						fit = false;
					}
				}
			}

			//set backupindex if applicable
			if(backupHighestRow > highestCell){
				backupIndex = i;
				backupHighestRow = highestCell;
			}

			//set bestIndex if applicable
			if(fit && (bestIndex == -1 || topline[bestIndex] > topline[i])){
				bestIndex = i;
			}
		}


		System.err.println("Round: " + state.getRoundNumber());
		System.err.println("Topline: " + Arrays.toString(topline));
		System.err.println("Delta: " + Arrays.toString(toplineDelta));
		System.err.println("Best: "+bestIndex);
		System.err.println("Backup: "+backupIndex);
		System.err.println("Backup highest row: " + backupHighestRow);


		int currentIndex = state.getShapeLocation().x;
		int targetIndex;
		if(bestIndex == -1){
			targetIndex = backupIndex;
		} else {
			targetIndex = bestIndex;
		}

		System.err.println("Target: "+targetIndex);
		System.err.println("Current: " + currentIndex);
		System.err.println(" ");

		ArrayList<MoveType> moves = new ArrayList<>();
		for(int i=0; i< Math.abs(currentIndex-targetIndex); i++){
			if(targetIndex > currentIndex){
				moves.add(MoveType.RIGHT);
			} else {
				moves.add(MoveType.LEFT);
			}
		}
		moves.add(MoveType.DROP);

		return new Move(moves);
	}

	public static void main(String[] args) {
		BotParser parser = new BotParser(new BotAlpha());
		parser.run();
	}
}