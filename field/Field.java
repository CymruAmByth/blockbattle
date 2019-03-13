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

package field;

import field.Cell;

/**
 * Field class
 * 
 * Represents the playing field for one player.
 * Has some basic methods already implemented.
 * 
 * @author Jim van Eeden <jim@riddles.io>
 */

public class Field {
	
	private int width;
	private int height;
	private Cell grid[][];

	public Field(int width, int height, String input) {
	    this.width = width;
	    this.height = height;

	    parseFromString(input);
    }
	
	/**
	 * Parses the input string to get a grid with Cell objects
	 * @param input input string
	 */
	private void parseFromString(String input) {
		this.grid = new Cell[this.width][this.height];
		int x = 0;
		int y = 0;

		for (String cellString : input.split(",|;")) {
		    int cellCode = Integer.parseInt(cellString.trim());
		    this.grid[x][y] = new Cell(x, y, CellType.values()[cellCode]);

		    if (++x == this.width) {
		        x = 0;
		        y++;
            }
        }
	}
	
	public Cell getCell(int x, int y) {
		if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
			return null;
		}

		return this.grid[x][y];
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}
}
