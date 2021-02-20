import OXOExceptions.OXOMoveException;

public class OXOGameTester {
    public static void main(String[] args){
        OXOModel model = new OXOModel(3,3,3);
        model.addPlayer(new OXOPlayer('X'));
        model.addPlayer(new OXOPlayer('O'));
        OXOController controller = new OXOController(model);
        System.out.println(model.getCurrentPlayer().getPlayingLetter());
        try{
            controller.handleIncomingCommand("a1");
            controller.handleIncomingCommand("a2");
            controller.handleIncomingCommand("a3");
        }catch(OXOMoveException e){
            System.out.println(e);
        }
        model.addPlayer(new OXOPlayer('X'));
        model.addPlayer(new OXOPlayer('Z'));
        System.out.println(model.getNumberOfPlayers());

    }
}
