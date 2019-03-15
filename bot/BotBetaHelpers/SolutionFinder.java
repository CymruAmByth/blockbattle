package bot.BotBetaHelpers;

import field.*;
import field.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolutionFinder {

    public static List<Solution> findSolutions(Field field, ShapeType shapeType){
        List result = new ArrayList();

        int fieldHeight = field.getHeight();
        int fieldWidth = field.getWidth();

        for(int i=-4; i<fieldHeight; i++){
            for (int j=-4; j<fieldWidth; j++){
                Shape shape = new Shape(shapeType, field, new Point(j,i));
                for (int k=0; k<4; k++){
                    boolean skip = false;
                    switch(shape.getType()){
                        case Z: if(k > 1){
                            skip = true;
                        }
                            break;
                        case S:if(k > 1) {
                                skip = true;
                        }
                            break;
                        case O: if(k != 0){
                                skip = true;
                        }
                            break;
                        case I:if(k > 1) {
                            skip = true;
                        }
                            break;
                    }



                    if(!skip /*&& i==16*/) {
                        boolean down = false;
                        boolean collides = false;
                        for (Cell c : shape.getBlocks()) {
                            //get the cell underneath current cell
                            Cell under = field.getCell(c.getLocation().x, c.getLocation().y+1);
                            if(c.getLocation().y+1>=fieldHeight)
                            {
                                down = true;
                            } else if(under != null){
                                if (under.isSolid() || under.isBlock()) {
                                    down = true;
                                }
                            }
                            Cell current = field.getCell(c.getLocation().x, c.getLocation().y);
                            if (current == null || current.isSolid() || current.isBlock()) {
                                collides = true;
                            }

                        }

                        if (down && !collides) {
                            result.add(new Solution(i, j, k, field, shape.getType()));
                        }

                        shape.turnRight();
                    }
                }
            }
        }

        Collections.sort(result);

        return result;
    }

}
