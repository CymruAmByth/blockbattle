package bot.BotBetaHelpers;

import field.Field;
import field.Shape;
import moves.MoveType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PathFinder {
    public static List<MoveType> getFirstPath(List<Solution> solutions, Field field, Shape startShape){
        boolean pathFound = false;
        List<MoveType> moves = new ArrayList<>();

        for(Solution sol: solutions){
            Shape shape = new Shape(startShape.getType(), field.clone(), new Point(startShape.getLocation().x, startShape.getLocation().y));
            if(pathFound){
                break;
            }
            boolean collision = false;

            moves = new ArrayList<>();
            int horizontal =  sol.getX() - shape.getLocation().x;
            int vertical = sol.getY() - shape.getLocation().y;

            System.err.println(sol);
            System.err.println(horizontal);
            System.err.println(vertical);
            for(int i=0; i< sol.getOrientation(); i++) {
                //Twist the shape in place
                moves.add(MoveType.TURNRIGHT);
                shape.turnRight();
                if(shape.hasCollision(field)) {
                    System.err.println("Collision on turn");
                    collision = true;
                }
            }
            //stop cycle if collission happens
            if(collision){
                continue;
            }

            //Move shape left or right
            for(int i = 0; i< Math.abs(horizontal); i++){
                if(horizontal > 0){
                    moves.add(MoveType.RIGHT);
                    shape.oneRight();
                    if(shape.hasCollision(field)) {
                        System.err.println("Collision on right");
                        collision = true;
                    }
                } else {
                    moves.add(MoveType.LEFT);
                    shape.oneLeft();
                    if(shape.hasCollision(field)) {
                        System.err.println("Collision on left");
                        collision = true;
                    }
                }
            }

            //stop cycle if collission happens
            if(collision){
                continue;
            }

            //Move shape left or right
            for(int i = 0; i< vertical; i++){
                shape.oneDown();
                if(shape.hasCollision(field)) {
                    System.err.println("Collision on down");
                    collision = true;
                }
            }
            //stop cycle if collission happens
            if(collision){
                continue;
            }

            moves.add(MoveType.DROP);
            System.err.println(" ");
            //if we reach this point, a path has been found which we can return
            return moves;


        }


        return moves;

    }

}
