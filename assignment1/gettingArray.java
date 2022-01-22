public class gettingArray {
    private int[][] players;
    private int[][] tieArray;
    private boolean threePlayerTie = false ;
    private boolean twoplayerTie = false;
    /** 
     * It is the constructor which would take the values for the no of player and then roll the dice for them get 
     * a sorted array for the game .It will First produce a 2d array in which the 0 index is given to the player and
     * 1 index is given to dice number .It will roll dice and if two or three players get same values then it will give them
     * 0 index and would make  the whole array in ascending which means that the players who tied would get the last 
     * position and the one who didnt tied would be placed in asceding order .It will repeat process untill a sorted array is
     * not obtained
     *
     */
    gettingArray(int noOfPlayers) {
        players = new int[noOfPlayers][2];
    }
    
    /** 
     * It is used to get random int values 
     * @return int
     */
    public int flip(){
        int x ;
        x = (int)(1+Math.random()*6);
        return x;
    }
    // This function starts the program to get a sorted array
    public void play(){
        System.out.println("Game is Played by "+players.length);
        System.out.println("Now deciding which player will start playing");
        getOrderCorrectforPlay();
    }
    
    /** 
     * @return int[][]
     */
    public int[][] getPlayers() {
        return players;
    }
    // 
    public void getOrderCorrectforPlay(){
        rollDice();
        if(allElementSame(players) == true || twoSameElement(players) == true){
            while(allElementSame(players)|| twoSameElement(players)){
                System.out.println("Tie between All The players");
                rollDice();
                break;
            }
            
        }
        if(tie(players) == true){
            getOrderCorrect();
        }
        else{
            players = asscendingOrder(players);
        }
        System.out.print("Reached final decision on order of playing: ");
        for(int i =0;i<players.length;i++){
            System.out.print("player"+players[(players.length-1)-i][0]+" ");
        }
        System.out.println();

    }
    
    /** 
     * @param player
     * @return boolean
     */
    public boolean twoSameElement(int[][] player){
        int z = 0;
        boolean a = false;
        for(int i = 0;i<player.length-1;i++){
            for(int c =i+1;c<player.length;c++){
                if(player[i][1] == player[c][1]){
                    z += 1;
                }
                
            }    
        }
        if(player.length == 4 && z == 2){
            a = true;
        }
        else{
            a = false;
        }
        return a;

    }
    
    /** 
     * @param array
     * @return boolean
     */
    public boolean allElementSame(int[][] array){
        boolean same = false;
        for(int i = 0; i<array.length-1;i++){
            if(array[i][1] == array[i+1][1]){
                same = true;
            }
            else{
                same = false;
                break;
            }
            
        }
        return same;
    }
    // Rolls dice for the players
    public void rollDice(){
        for(int i =0;i<players.length;i++){
            int z;
            players[i][0]=i+1;
            z =flip();
            players[i][1] = z;
            System.out.println("players "+players[i][0]+ "got a dice value of "+players[i][1]);
        }
    }
    
    /** 
     * Check for the tie
     * @param player
     * @return boolean
     */
    public boolean tie(int[][] player){
        
        int temp =0;
        boolean draw = false;
        for(int i = 0;i<player.length-1;i++){
            for(int z =i+1;z<player.length;z++){
                if(player[i][1] == player[z][1]){
                    twoplayerTie = true;
                    temp =player[i][1];
                    placeZeros(i,player);
                    placeZeros(z, player);
                    for(int c = 0;c<player.length;c++){
                        if(player[c][1] == temp){
                            threePlayerTie = true;
                            placeZeros(c, player);
                            tieArray = new int[3][2];
                            tieArray[0] = player[i];
                            tieArray[1] = player[z];
                            tieArray[2] = player[c];
                            break;
                        } 
                        
                    }
                    if(threePlayerTie == false && twoplayerTie == true && tieArray == null){
                        tieArray = new int[2][2];
                        tieArray[0] = player[i];
                        tieArray[1] = player[z];
                        
                    }
                    draw =true;
                    
                }
            }
        }
        return draw;
    }

    public void getOrderCorrect(){
        asscendingOrder(players);
        if(twoplayerTie == true ){
            if(twoplayerTie == true && threePlayerTie == false ){
                while(sameValues(tieArray)){
                    System.out.println("A tie was achieved between "+tieArray[0][0]+" and "+tieArray[1][0]+". Attempting to break the tie");
                        tieArray[0][1] = flip();
                        tieArray[1][1] = flip();
                        System.out.println("players "+tieArray[0][0]+" got a dice value of "+tieArray[0][1]);
                        System.out.println("players "+tieArray[1][0]+" got a dice value of "+tieArray[1][1]);
                        asscendingOrder(tieArray);

            }
                players[1] = tieArray[1];
                players[0] = tieArray[0];
                
            }
            if(twoplayerTie == true && threePlayerTie == true ){
                System.out.println("start");
                while(samethreeValues(tieArray)){
                    System.out.println("A tie was achieved between "+tieArray[0][0]+","+tieArray[1][0]+" and "+tieArray[2][0]+". Attempting to break the tie");
                    for(int i =0;i<tieArray.length;i++){
                        tieArray[i][1] = flip();
                        System.out.println("players "+tieArray[i][0]+" got a dice value of "+tieArray[i][1]);
                    }
                    if(confirmTwoSameNumber(tieArray) && samethreeValues(tieArray) == false){
                        int[][] array = extractArray(tieArray);
                        asscendingOrder(tieArray);
                        while(sameValues(array)){
                            System.out.println("A tie was achieved between "+array[0][0]+" and "+array[1][0]+". Attempting to break the tie");
                            for(int i =0;i<array.length;i++){
                                array[i][1] = flip();
                                System.out.println("players "+array[i][0]+" got a dice value of "+array[i][1]);
                                
                            }
                            asscendingOrder(array);

        
                        }
                        tieArray[0] = array[0];
                        tieArray[1] = array[1];
                       
                    
                    }
                    else{
                        asscendingOrder(tieArray);

                    }
                    
                    players[2] = tieArray[2];
                    players[1] = tieArray[1];
                    players[0] = tieArray[0];
            
                }

            }
        }
        
    }
    
    /** 
     * check for two same values in the array
     * @param tieArray
     * @return boolean
     */
    public boolean sameValues(int[][] tieArray){
        boolean same = false;
        for(int i = 0;i<tieArray.length-1;i++){
            if(tieArray[i][1] == tieArray[i+1][1]){
                same = true;
            }
            else{
                same = false;
            }
        }
        return same;
    }
    

    
    /** 
     * Check for same three values in the array
     * @param tieArray
     * @return boolean
     */
    public boolean samethreeValues(int[][] tieArray){
        boolean samevlaues = false;
        for(int z =0;z<1;z++){
            if(tieArray[z][1] == tieArray[z+1][1] && tieArray[z+1][1] == tieArray[z+2][1]){
                samevlaues = true;
            }
        }
        return samevlaues;
    }
    
    /** 
     * Check whether there are two values same in the array
     * @param tieArray
     * @return boolean
     */
    public boolean confirmTwoSameNumber(int[][] tieArray){
        boolean sameTwoValuess = false;
        for(int i = 0;i<1;i++){
            if(tieArray[i][1] == tieArray[i+1][1] &&tieArray[i+1][1] == tieArray[i+2][1]){
                sameTwoValuess = false;
            }
            if(tieArray[i][1] == tieArray[i+1][1] || tieArray[i+1][1] == tieArray[i+2][1] || tieArray[i][1] == tieArray[i+2][1] ){
                sameTwoValuess = true;
            }

        }

        return sameTwoValuess;
    }
    
    /** 
     * Extracts a draw array from the main array of two lenght
     * @param tieArray
     * @return int[][]
     */
    public int[][] extractArray(int[][] tieArray){
        int[][] extractArray = new int[2][2];
        for(int i=0;i<tieArray.length-1;i++){
            for(int z =i+1;z<tieArray.length;z++){
                if(tieArray[i][1] == tieArray[z][1]){
                    tieArray[i][1] =0;
                    tieArray[z][1] =0;
                    extractArray[0] = tieArray[i];
                    extractArray[1] = tieArray[z];
                }
            }
        }
        return extractArray;

    }
    
    
    /** 
     * Used to place zeros on specified index number
     * @param i
     * @param player
     */
    public void placeZeros(int i,int[][] player){
        player[i][1] =0; 

    }
    
    /** 
     * Used to get a array in ascending order
     * @param player
     * @return int[][]
     */
    public int[][] asscendingOrder(int[][] player) {
        int[][] temp  = new int[1][2];
        
        for(int i =0;i<player.length-1;i++){
            for(int z =i+1;z<player.length;z++){
                if(player[i][1] >player[z][1]){
                    temp[0] = player[i];
                    player[i] = player[z];
                    player[z] = temp[0];
                }
            }
        }
        return player;
        
    }
    
    
}
