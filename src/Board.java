


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Board {
 
    List<Point> availablePoints;
    Scanner scan = new Scanner(System.in);
    int[][] board = new int[3][3];
    public static final int X = 1;
    public static final int Y = 2;
    public static final int NO_PLAYER = 0;
    
    public boolean isGameOver() {
        //Game is over is someone has won, or board is full (draw)
        return (hasXWon() || hasOWon() || getAvailableStates().isEmpty());
    }
 
    
    
    public boolean hasXWon() {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == X) ||
            (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == X)) {
            //System.out.println("X Diagonal Win");
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if (((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == X)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == X))) {
                // System.out.println("X Row or Column win");
                return true;
            }
        }
        return false;
    }
 
    
    
    
    public boolean hasOWon() {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == Y) || 
           (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == Y)) {
            // System.out.println("O Diagonal Win");
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if ((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == Y)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == Y)) {
                //  System.out.println("O Row or Column win");
                return true;
            }
        }
 
        return false;
    }
 
    
    
    
    public List<Point> getAvailableStates() {
        availablePoints = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board[i][j] == NO_PLAYER) {
                    availablePoints.add(new Point(i, j));
                }
            }
        }
        return availablePoints;
    }
 
    
    
    
    
    public void placeAMove(Point point, int player) {
        board[point.x][point.y] = player;   //player = 1 for X, 2 for O
    }
 
    
    
    
    // O play  = 2 
    public void takeHumanInput() {
        System.out.println("Your move: ");
        int x = scan.nextInt();
        int y = scan.nextInt();
        Point point = new Point(x, y);
        placeAMove(point, Y);
    }
 
    
    
    
    
    
    public void displayBoard() {
        System.out.println();
 
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if(board[i][j] == X){
                    System.out.print("X ");
                }else if(board[i][j] == Y){
                    System.out.print("O ");
                }else{
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }
 
    
    
    
    
    Point computersMove;
 
    public int minimax(int depth, int turn) {
        if (hasXWon()) 
            return +10;
        
        if (hasOWon()) 
            return -10;
 
        List<Point> pointsAvailable = getAvailableStates();
        if (pointsAvailable.isEmpty())
            return 0;
 
        int max = Integer.MAX_VALUE, 
            min = Integer.MIN_VALUE; 
 
        for (int i = 0; i < pointsAvailable.size(); ++i) {
            Point point = pointsAvailable.get(i);
            
            if (turn == X) {
                placeAMove(point, X);
                int currentScore = minimax(depth + 1, Y);
                max = Math.max(currentScore, max);
 
                if(currentScore >= 0){ 
                    if(depth == 0)
                        computersMove = point;
                }
                
                if(currentScore == 1){
                    board[point.x][point.y] = 0;
                    break;
                }
                
                if(i == pointsAvailable.size()-1 && max < 0){
                    if(depth == 0)
                        computersMove = point;
                }
                
            } else if (turn == 2) {
                placeAMove(point, 2);
                int currentScore = minimax(depth + 1, 1);
                min = Math.min(currentScore, min);
                if(min == -1){
                    board[point.x][point.y] = 0;
                    break;
                }
            }
            board[point.x][point.y] = 0; //Reset this point
        }
        return turn == Y?
                max:min;
    }
}
 
