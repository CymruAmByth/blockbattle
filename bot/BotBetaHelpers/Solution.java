package bot.BotBetaHelpers;

import field.Cell;
import field.Field;
import field.Shape;
import field.ShapeType;

import java.awt.*;

public class Solution implements Comparable<Solution>{
    private int y;
    private int x;
    private int orientation;
    private Field field;
    private Shape shape;
    private int filledLines;
    private int highestCell;
    private int blockedCells=0;
    private int totalY=0;

    @Override
    public String toString() {
        return "Solution{" +
                "y=" + y +
                ", x=" + x +
                ", orientation=" + orientation +
                ", linesfilled=" + filledLines +
                ", highestcell=" + highestCell +
                ", blockedcells=" + blockedCells +
                '}';
    }



    public Solution(int y, int x, int orientation, Field f, ShapeType s) {
        this.y = y;
        this.x = x;
        this.orientation = orientation;
        this.shape = new Shape(s, f, new Point(x,y));
        this.filledLines = 0;
        this.highestCell =f.getHeight();
        for(int i=0; i<orientation; i++){
            shape.turnRight();
        }
        this.field = f.clone();

        for(Cell c : shape.getBlocks()){
            this.field.getCell(c.getLocation().x, c.getLocation().y).setShape();
            totalY += c.getLocation().y;
        }

        for(int i=0; i<this.field.getHeight(); i++){
            boolean linefilled = true;
            for(int j=0; j<this.field.getWidth(); j++){
                Cell c = this.field.getCell(j,i);
                if(c.isEmpty() && linefilled){
                    linefilled = false;
                }
                if(!c.isEmpty() && i < highestCell){
                    highestCell = i;
                }
            }
            if(linefilled){
                this.filledLines++;
            }
        }

        for(int i=0; i<this.field.getWidth(); i++) {
            int topline=20;
            for (int j = 0; j < this.field.getHeight(); j++) {
                if((!this.field.getCell(i,j).isEmpty()) && j< topline){
                    topline = j;
                } else if(this.field.getCell(i,j).isEmpty() && j> topline){
                    blockedCells++;
                }
            }
        }
    }


    @Override
    public int compareTo(Solution solution) {
        if(this.filledLines != solution.filledLines){
            return (this.filledLines - solution.filledLines) * -1;
        } else if (this.highestCell != solution.highestCell){
            return (this.highestCell - solution.highestCell) * -1;
        } else if (this.blockedCells != solution.blockedCells){
            return this.blockedCells - solution.blockedCells;
        } else if (this.totalY != solution.totalY){
            return (this.totalY - solution.totalY) * -1;
        } else {
            return this.y - solution.y;
        }
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getOrientation() {
        return orientation;
    }
}
