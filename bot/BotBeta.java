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

import bot.BotBetaHelpers.PathFinder;
import bot.BotBetaHelpers.Solution;
import bot.BotBetaHelpers.SolutionFinder;
import field.Cell;
import field.Field;
import field.Shape;
import field.ShapeType;
import moves.Move;
import moves.MoveType;
import player.Player;

import java.util.*;

/**
 * BotAlpha class
 *
 * This class is where the main logic should be. Implement getMove() to
 * return something better than random moves.
 *
 * @author Jim van Eeden <jim@riddles.io>
 */

public class BotBeta implements Bot{

	private Random random;

	public BotBeta() {
		this.random = new Random();
	}

	/**
	 * Returns a random amount of random moves
	 * @param state current state of the bot
	 * @return a list of moves to execute
	 */
	public Move getMove(BotState state) {
		Player player = state.getPlayers().get(state.getMyName());
		Field field = player.getField();
		ShapeType shape = state.getCurrentShape();

		System.err.println("Round: "+state.getRoundNumber());
		List<Solution> solutions = SolutionFinder.findSolutions(field, shape);

		return new Move(PathFinder.getFirstPath(solutions, field, new Shape(shape, field, state.getShapeLocation())));
	}

	public static void main(String[] args) {
		BotParser parser = new BotParser(new BotBeta());
		parser.run();
	}
}
