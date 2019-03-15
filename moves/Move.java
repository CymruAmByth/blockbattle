package moves;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * moves.Move - Created on 19-4-17
 *
 * [description]
 *
 * @author Jim van Eeden - jim@riddles.io
 */
public class Move {

    private List<MoveType> moves;

    public Move(List<MoveType> moves) {
        this.moves = moves;
    }

    public String toString() {
        return this.moves.stream().map(Object::toString).collect(Collectors.joining(","));
    }
}
