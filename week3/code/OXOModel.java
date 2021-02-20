import java.util.*;

public class OXOModel
{
    //private OXOPlayer cells[][];
    private ArrayList<ArrayList<OXOPlayer>> cells;
    private ArrayList<OXOPlayer> players;
    private OXOPlayer currentPlayer;
    private OXOPlayer winner;
    private boolean gameDrawn;
    private int winThreshold;
    private int movements;


    public OXOModel(int numberOfRows, int numberOfColumns, int winThresh)
    {
        winThreshold = winThresh;
        cells = new ArrayList<ArrayList<OXOPlayer>>(numberOfRows);
        for(int i = 0; i < numberOfRows; i++) {
            ArrayList<OXOPlayer> row = new ArrayList<OXOPlayer>(numberOfColumns);
            for(int j=0; j<numberOfColumns; j++){
                row.add(j, new OXOPlayer('\0'));
            }
            cells.add(i, row);
        }
        players = new ArrayList<>();
        movements = 0;
    }

    public int getNumberOfPlayers()
    {
        return players.size();
    }

    public void addPlayer(OXOPlayer player)
    {
        try{
            checkDuplication(player);
            players.add(player);
        }catch(DuplicatePlayersException e){
            System.out.println(e);
        }

//        for(int i=0; i<players.length; i++) {
//            if(players[i] == null) {
//                players[i] = player;
//                return;
//            }
//        }
    }
    void checkDuplication(OXOPlayer player) throws DuplicatePlayersException{
        for(int i=0; i<players.size(); i++){
            if(players.get(i).getPlayingLetter()==player.getPlayingLetter()){
                throw new DuplicatePlayersException(player);
            }
        }
        return;
    }

    public OXOPlayer getPlayerByNumber(int number)
    {
        return players.get(number);
    }

    public OXOPlayer getWinner()
    {
        return winner;
    }

    public void setWinner(OXOPlayer player)
    {
        winner = player;
    }

    public OXOPlayer getCurrentPlayer()
    {
        int index = movements%(getNumberOfPlayers());
        currentPlayer = players.get(index);
        return currentPlayer;
    }

    public void setCurrentPlayer(OXOPlayer player)
    {
        currentPlayer = player;
    }

    public void addAMovement()
    {
        movements++;
    }

    public int getNumberOfRows()
    {
        return cells.size();
    }

    public int getNumberOfColumns()
    {
        return cells.get(0).size();
    }

    public OXOPlayer getCellOwner(int rowNumber, int colNumber)
    {
        return cells.get(rowNumber).get(colNumber);
    }

    public void setCellOwner(int rowNumber, int colNumber, OXOPlayer player)
    {
        cells.get(rowNumber).set(colNumber, player);
    }

    public void setWinThreshold(int winThresh)
    {
        winThreshold = winThresh;
    }

    public int getWinThreshold()
    {
        return winThreshold;
    }

    public void setGameDrawn()
    {
        gameDrawn = true;
    }

    public boolean isGameDrawn()
    {
        if (movements==getNumberOfColumns()*getNumberOfRows()){
            gameDrawn=true;
        }
        return gameDrawn;
    }

}

class DuplicatePlayersException extends Exception{
    OXOPlayer player;
    public DuplicatePlayersException(OXOPlayer player){
        this.player = player;
    }
    public String toString(){
        return "player "+ player.getPlayingLetter()+ " already exists.";
    }
}
