public class LadderAndSnake {
    private int noOfPlayers;
    private int[][] orderedList;
    private int totalBoardNumber =100;
    private int[][] ladder ={{1,38},{4,14},{9,31},{21,42},{28,84},{36,44},{51,67},{71,91},{80,100}};
    private int[][] snake={{16,6},{48,30},{64,60},{79,19},{93,68},{95,24},{98,78}}; 
    LadderAndSnake(int noOfPlayers) {
        this.noOfPlayers = noOfPlayers;
    }

    
    /** 
     * It will flip the dice and produce an integer value
     * @return int
     */
    public int flip(){
        int x ;
        x = (int)(1+Math.random()*6);
        return x;
    }
    // It will get the array from the gettingArray class and would give 0 to the 1 index of the 2d array
    public void play(){
        int flipped =0;
        int a;
        gettingArray gettingArray = new gettingArray(noOfPlayers);
        gettingArray.play();
        orderedList = gettingArray.getPlayers();
        for(int i =0 ; i<orderedList.length;i++){
            orderedList[i][1] = 0;
        }
        // It will run until gameWin is not true at each roll it will update the array and check for ladder and snake
        while(!gameWin(orderedList)){
            for(int i = orderedList.length-1;i>=0;i--){
                a =0;
                flipped = flip();
                if((orderedList[i][1] + flipped) >100 ){
                    a =((orderedList[i][1] +flipped)-100);

                }
                boolean goup = goUp(orderedList[i][1]+flipped - (2*a)) != (orderedList[i][1]+flipped- (2*a));
                boolean godown = goDown(orderedList[i][1]+flipped- (2*a)) != (orderedList[i][1]+flipped- (2*a));

                if(goup == true){
                    System.out.println("Player "+orderedList[i][0]+" got dice value of "+flipped+"; gone to square "+ (orderedList[i][1] +flipped - (2*a)) +" then up to square "+goUp(orderedList[i][1]+flipped- (2*a)));
                    orderedList[i][1] = goUp(orderedList[i][1]+flipped - (2*a));
                    if(gameWin(orderedList) == true){
                        System.out.println("Game Over Player "+orderedList[i][0]+" won");
                        break;
                    }
                }
                else if(godown == true){
                    System.out.println("Player "+orderedList[i][0]+" got dice value of "+flipped+"; gone to square "+ (orderedList[i][1] +flipped - (2*a)) +" then down to square "+goDown(orderedList[i][1]+flipped- (2*a)));
                    orderedList[i][1] = goDown(orderedList[i][1]+flipped - (2*a));
                    if(gameWin(orderedList) == true){
                        System.out.println("Game Over Player "+orderedList[i][0]+" won");
                        break;
                    }
                }
                else{
                    System.out.println("Player "+orderedList[i][0]+" got dice value of "+flipped+"; now in square "+(orderedList[i][1]+flipped- (2*a)));
                    orderedList[i][1] = orderedList[i][1] +flipped - (2*a);
                    if(gameWin(orderedList) == true){
                        System.out.println("Game Over Player "+orderedList[i][0]+" won");
                        break;
                    }

                }
                
                
                
            }
            if(!gameWin(orderedList)){
                System.out.println("Game not over; flipping again");
            }
            
            
        }
        
    }
    
    /** 
     * @param a
     * @return int
     */
    public int goUp(int a) {
        int b =a;
        for(int i =0; i<ladder.length;i++){
            if(ladder[i][0] == a){
                b = ladder[i][1];
            }
        }
        return b;

    }
    
    /** 
     * It will check for the snake on the board
     * @param a
     * @return int
     */
    public int goDown(int a){
        int b =a;
        for(int i =0; i<snake.length;i++){
            if(snake[i][0] == a){
                b = snake[i][1];
            }
        }
        return b;

    }

    
    /** 
     * Would check for the winner
     * @param gettingArray
     * @return boolean
     */
    public boolean gameWin(int[][] gettingArray) {
        boolean gameWon = false;
        for (int i = 0; i < gettingArray.length; i++) {
            if (gettingArray[i][1] ==totalBoardNumber) {
                gameWon = true;
            }
        }
        return gameWon;
    }


    
}
