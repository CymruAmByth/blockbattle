package bot.testMains;

import bot.BotBetaHelpers.PathFinder;
import bot.BotBetaHelpers.Solution;
import bot.BotBetaHelpers.SolutionFinder;
import field.Field;
import field.Shape;
import field.ShapeType;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class solutionFinderTester {
    public static void main (String[] args) {
        Field f = new Field(10, 20, "0,0,0,1,1,1,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,2,2,2,0,2,0,0,0,0;0,2,2,2,2,2,2,0,2,0;0,0,2,2,2,2,2,2,2,0;0,2,2,2,0,2,2,2,2,0;2,2,0,2,2,2,2,2,2,2;3,3,3,3,3,3,3,3,3,3");

        Shape s = new Shape(ShapeType.T, f, new Point(3,-1));

        List<Solution> solutions = SolutionFinder.findSolutions(f, s.getType());

        System.out.println(solutions);

        System.out.println(PathFinder.getFirstPath(solutions, f, s));

    }
}
