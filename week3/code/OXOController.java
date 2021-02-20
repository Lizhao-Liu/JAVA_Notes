import OXOExceptions.*;

class OXOController
{
    OXOModel gameModel;

    public OXOController(OXOModel model)
    {
        gameModel = model;
    }

    public void handleIncomingCommand(String command) throws OXOMoveException
    {
        int row = command.charAt(1) - '1';
        int col = command.toLowerCase().charAt(0) - 'a';
        if( command.length()>2){
            throw new InvalidIdentifierLengthException(command.length());
        }
        if(row<0||row>9){
            throw new InvalidIdentifierCharacterException(command.charAt(1), RowOrColumn.ROW);
        }
        if(col<0||col>9){
            throw new InvalidIdentifierCharacterException(command.charAt(0), RowOrColumn.COLUMN);
        }
        if(row>=gameModel.getNumberOfRows()){
            throw new OutsideCellRangeException(row, RowOrColumn.ROW);
        }
        if(col>=gameModel.getNumberOfColumns()){
            throw new OutsideCellRangeException(col, RowOrColumn.COLUMN);
        }
        if(gameModel.getCellOwner(row, col)!=null){
            throw new CellAlreadyTakenException(row, col);
        }
        gameModel.setCellOwner(row, col, gameModel.getCurrentPlayer());
        if(WinDetected(row, col, gameModel.getCurrentPlayer())){
            gameModel.setWinner(gameModel.getCurrentPlayer());
        }
        gameModel.addAMovement();
        gameModel.setCurrentPlayer(gameModel.getNextPlayer());
    }

    boolean WinDetected(int row, int col, OXOPlayer currentPlayer){
        char curr = gameModel.getCellOwner(row, col).getPlayingLetter();
        int count = 0;
        int i ,j;

        // check for wins in vertical direction
        i = row;
        while(i>=0 && gameModel.getCellOwner(i, col).getPlayingLetter()==curr){
            i--;
            count++;
        }
        i = row+1;
        while(row<gameModel.getNumberOfRows() && gameModel.getCellOwner(i, col).getPlayingLetter()==curr){
            i++;
            count++;
        }
        if(count == gameModel.getWinThreshold()){
            return true;
        }

        // check for wins in horizontal direction
        count = 0;
        i=col;
        while(i>=0 && gameModel.getCellOwner(row, i).getPlayingLetter()==curr){
            i--;
            count++;
        }
        i = col+1;
        while(col<gameModel.getNumberOfColumns() && gameModel.getCellOwner(row, i).getPlayingLetter()==curr){
            i++;
            count++;
        }
        if(count == gameModel.getWinThreshold()){
            return true;
        }

        //check win condition diagonally
        count = 0;
        i=row; j=col;
        while(i>=0&&j>=0&&gameModel.getCellOwner(i, j).getPlayingLetter()==curr){
            i--;
            j--;
            count++;
        }
        i=row+1; j=col+1;
        while(i<gameModel.getNumberOfRows()&&j<gameModel.getNumberOfColumns()&&gameModel.getCellOwner(i, j).getPlayingLetter()==curr){
            i++;
            j++;
            count++;
        }
        if(count == gameModel.getWinThreshold()){
            return true;
        }

        //check win condition anti-diagonally
        count = 0;
        i=row; j=col;
        while(i>=0&&j<gameModel.getNumberOfColumns()&&gameModel.getCellOwner(i, j).getPlayingLetter()==curr){
            i--;
            j++;
            count++;
        }
        i = row+1; j=col-1;
        while(i<gameModel.getNumberOfRows()&&j>=0&&gameModel.getCellOwner(i, j).getPlayingLetter()==curr){
            i++;
            j--;
            count++;
        }
        if(count == gameModel.getWinThreshold()){
            return true;
        }
        return false;
    }
}


class CellAlreadyTakenException extends OXOMoveException
{
    public CellAlreadyTakenException(int row, int column)
    {
        super(row, column);
    }
    public String toString() {
        return "The cell at row: " + super.getRow() + " column: " + super.getColumn() + " is already taken";
    }
}

class CellDoesNotExistException extends OXOMoveException
{

}

class OutsideCellRangeException extends CellDoesNotExistException
{
    int position;
    RowOrColumn type;

    public OutsideCellRangeException(int position, RowOrColumn type)
    {
        this.position = position;
        this.type = type;
    }

    public String toString() {
        return "The cell at " + type + position + " does not exist";
    }
}

class InvalidIdentifierException extends CellDoesNotExistException
{

}

class InvalidIdentifierLengthException extends InvalidIdentifierException{
    int length;
    public InvalidIdentifierLengthException(int length)
    {
        this.length = length;
    }

    public String toString() {
        return "Invalid Identifier " + this.getMessage() + ": expected 2 characters, get " +length +" characters";
    }
}

class InvalidIdentifierCharacterException extends InvalidIdentifierException{
    char character;
    RowOrColumn type;
    public InvalidIdentifierCharacterException(char character, RowOrColumn type){
        this.character = character;
        this.type = type;
    }
    public String toString() {
        return "The " + type +" character "+ character + " is not valid";
    }
}


