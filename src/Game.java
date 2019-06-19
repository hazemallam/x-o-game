

import java.util.*;
import java.util.Random;

public class Game {
 
    public static void main(String[] args) {
        Board b = new Board();
        Random rand = new Random();
 
        Scanner input= new Scanner(System.in);
 
        b.displayBoard();
 
        System.out.println("Who plays first? (1) Computer (2) User? \n(Enter 1 or 2): ");
        int choice = b.scan.nextInt();
        if(choice == 1){
            Point p = new Point(rand.nextInt(3), rand.nextInt(3));  //plays at a random box
            b.placeAMove(p, 1);     // 1 is computer and computer plays as X
            b.displayBoard();
        }
 
        while (!b.isGameOver()) {
            System.out.println("Your move: ");
            int x=input.nextInt();
            int y=input.nextInt();
            while (b.board[x][y] == 1 || b.board[x][y] == 2) {
                System.out.println("Invalid Move. Enter other position");
                x=input.nextInt();
                y=input.nextInt();
            }
            Point userMove = new Point(x, y);
 
            b.placeAMove(userMove, 2); //2 for O and O is the user
            b.displayBoard();
            if (b.isGameOver()) break;
 
            b.minimax(0, 1);
 
            b.placeAMove(b.computersMove, 1);
            b.displayBoard();
        }
        if (b.hasXWon())
            System.out.println("Unfortunately, you lost!");
        else if (b.hasOWon())
            System.out.println("You win!"); //Will never happen ;)
        else
            System.out.println("It's a draw!");
    }
}
