import java.util.Objects;
import java.util.Scanner;

public class ConnectFourGame{
    // variables
   String [][] board;
   boolean winner;
   boolean draw;
   int winningPlayer;
   int playerTurn;
     // constructer
   public ConnectFourGame(){
     winningPlayer = 0;
     draw = false;
     playerTurn = 1;
     board = new String[6][7];
     newBoard();
     displayBoard();

   }
    // bulds a better looking board in the console
   private void newBoard(){
    for(int i = 0; i<6; i++){
       for(int j = 0 ; j<7; j++){
        board[i][j] = " - ";
       }
    }
   }
     // display board
   private void displayBoard(){
    System.out.println(" ");
    System.out.println("  *** ConnectFourGame *** ");
    System.out.println(" ");
    for(int i = 0 ; i < 6 ; i++){
        for(int j = 0 ; j <7 ; j++){
            System.out.print(board[i][j]);
        }
        System.out.println();
    }
    System.out.println();
   }
       // valid input from user if true it is valid otherwise false
   private boolean validInput(String input){
       return ((Objects.equals(input,"1") ||
                Objects.equals(input,"2") ||
                Objects.equals(input,"3") ||
                Objects.equals(input,"4") ||
                Objects.equals(input,"5") ||
                Objects.equals(input,"6") ||
                Objects.equals(input,"7"))); 
   }
    // if coumn is full then true otherwise false
   private boolean isColumnFull(int column){
    return (board[0][column-1] == " x " || board[0][column-1] == " 0 ");
   }
     // get next available slot
   private int getNextAvailableSlot(int column){
    int position = 5;
    boolean found = false;
    while(position >= 0 && !found){
        if(!Objects.equals(board[position][column], "x") && !Objects.equals(board[position][column], "0")){
            found = true;
        }else{
            position--;
        }
    }
    return position;
   }
   
   // swap turn for current player
   private void swapPlayerTurn(){
    if(playerTurn == 1){
        playerTurn = 2;
    }else{
        playerTurn = 1;
    }
   }

     // place piece onto the board in the chosen and availabe possition

   private void placePiece(){
    Scanner sc = new Scanner(System.in);
    System.out.println("player " + playerTurn + " - please select which column to place your piece(1-7): ");
    String input = sc.nextLine();

    while(!validInput(input) || isColumnFull(Integer.parseInt(input))){
        while(!validInput(input)){
            System.out.println("invalid input - please select a column from 1-7 ");
            input = sc.nextLine();
        }
        while (isColumnFull(Integer.parseInt(input))) {
            System.out.println("column full, choose another column ");
            input = sc.nextLine();
            if(!validInput(input)){
                break;
            }
        }
    }
    int columnChoise = Integer.parseInt(input) - 1;  // player column choise
        // get available row
    System.out.println("Next available row in column: " + getNextAvailableSlot(columnChoise));   
    String pieceToPlace;
    if(playerTurn == 1){
        pieceToPlace = "x";
    }else{
        pieceToPlace = "0";
    }
    board[getNextAvailableSlot(columnChoise)][columnChoise] = pieceToPlace;
     displayBoard();
     swapPlayerTurn();
   }

      // return true if board is full otherwise false

   private boolean isBoardFull(){
    boolean full = true;
    for(int i = 0; i<1;i++){
        for(int j = 0;j<7;j++){
            if(board[i][j] != " x " && board[i][j] != "0"){
                full = false;
            }
        }
    }
    return full;
   }

      // check vertical winner Symbol of the winner(x or 0)

   private String checkVerticalWinner(){
    String symbol = null;
    for(int i = 0;i<3;i++){
        for(int j = 0;j<7;j++){
            if((board[i][j] == board[i+1][j]) && (board[i][j] == board[i+2][j]) && (board[i][j] == board[i+3][j])){
                if(board[i][j] == "x" || board[i][j] == " 0 "){
                    symbol = board[i][j];
                }
            }
        }
    }
    return symbol;
   }
 
         // check Horizontal winner

   private String checkHorizontalWinner(){
    String symbol = null;
    for(int i = 0;i<6;i++){
        for(int j = 0;j<4;j++){
            if((board[i][j] == board[i][j+1]) && (board[i][j] == board[i][j+2]) && (board[i][j] == board[i][j+3])){
                if(board[i][j] == "x" || board[i][j] == " 0 "){
                    symbol = board[i][j];
                }
            }
        }
    }
    return symbol;
   }

     // check Left diagonal winner

   private String checkLeftDiagonalWinner(){
    String symbol = null;
    for(int i = 0;i<3;i++){
        for(int j = 0;j<4;j++){
            if((board[i][j] == board[i+1][j+1]) && (board[i][j] == board[i+2][j+2]) && (board[i][j] == board[i+3][j+3])){
                if(board[i][j] == "x" || board[i][j] == " 0 "){
                    symbol = board[i][j];
                }
            }
        }
    }
    return symbol;
   }

   
     // check Left diagonal winner

   private String checkRightDiagonalWinner(){
    String symbol = null;
    for(int i = 0;i<3;i++){
        for(int j = 3;j<7;j++){
            if((board[i][j] == board[i+1][j-1]) && (board[i][j] == board[i+2][j-2]) && (board[i][j] == board[i+3][j-3])){
                if(board[i][j] == "x" || board[i][j] == " 0 "){
                    symbol = board[i][j];
                }
            }
        }
    }
    return symbol;
   }

     // check for winning player

   private int checkforWinner(){
    int winner = 0;
    String symbol = " ";
    if(checkVerticalWinner() == "x" || checkVerticalWinner() == "0"){
        symbol = checkVerticalWinner();
    }else if(checkHorizontalWinner() == "x" || checkHorizontalWinner() == "0"){
        symbol = checkHorizontalWinner();
    }else if(checkLeftDiagonalWinner() == "x" || checkLeftDiagonalWinner() == "0"){
        symbol = checkLeftDiagonalWinner();
    }else if(checkRightDiagonalWinner() == "x" || checkRightDiagonalWinner() == "0"){
        symbol = checkRightDiagonalWinner();
    }
    if(symbol == "x"){
        winner = 1;
    }else if(symbol == "0"){
        winner = 2;
    }
    return winner;
   }

     // check for draw true if game is draw otherwise false

   private boolean checkforDraw(){
     return(isBoardFull() == true && (checkforWinner() != 1 && checkforWinner() != 2));
   }

   // Display winner grphics in console

   private void showWinner(){
    System.out.println(" ");
    System.out.println(" ******************************** ");
    System.out.println("                                    ");
    System.out.println("          player " +  winningPlayer  +  " WINS !!!");
    System.out.println("   *                      *      ");
    System.out.println("         *     \\0/    *       ");
    System.out.println("  *      *      |    *      *  ");
    System.out.println("      *         / \\      *    ");
    System.out.println(" *********************************   ");
    System.out.println(" *********************************");
   }

   public void playGame(){
    while (winningPlayer == 0) {
        winningPlayer = checkforWinner();
        draw = checkforDraw();
        if(winningPlayer == 1){
            showWinner();
            break;
        }else if(winningPlayer == 2){
            showWinner();
            break;
        }else if(draw == true){
            System.out.println(" Match draw ");
            break;
        }
        placePiece();
    }
   }
   public static void main(String [] args){
    ConnectFourGame c4 = new ConnectFourGame();
    c4.playGame();
   }
} 