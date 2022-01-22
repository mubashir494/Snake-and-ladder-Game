import java.util.Scanner;

public class mainMethod {
    
    /** 
     * This is the driver class for the program.In the main function the program will take the input from the user
     * for the no of players in the game will call the constructor of ladder and Snake
     * @param args
     */
    public static void main(String[] args) {
        int a =0;
        Scanner sc = new Scanner(System.in);
        LadderAndSnake ladderAndSnake ;
        System.out.println("Enter the # of players for your game – Number must be between 2 and 4 inclusively:");
        a = sc.nextInt();
        if(a>=2 && a<=4){
            ladderAndSnake = new LadderAndSnake(a);
            ladderAndSnake.play();
            
        }
        else{
            for(int i =1;i<3;i++){
                System.out.println("Bad Attempt "+i+" - Invalid # of players. Please enter a # between 2 and 4 inclusively:");
                a = sc.nextInt();
                if(a>=2 && a<=4){
                    ladderAndSnake = new LadderAndSnake(a);
                    ladderAndSnake.play();
                    break;
                }
                if(i == 2){
                    System.out.println("Bad Attempt 3 - Invalid # of players. Please enter a # between 2 and 4 inclusively. This is your last attempt:");
                    a = sc.nextInt();
                    if(a>=2 && a<=4){
                        ladderAndSnake = new LadderAndSnake(a);
                        ladderAndSnake.play();
                        break;
                    }
                    System.out.println("Bad Attempt 4! You have exhausted all your chances. Program will terminate!");
                }
            }
            sc.close();
        }
    }
    
}
