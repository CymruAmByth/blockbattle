package bot;

import moves.Move;

public interface Bot {

    public Move getMove(BotState state);

}
