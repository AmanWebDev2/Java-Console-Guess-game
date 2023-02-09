import java.util.Scanner;

class Guesser {
    int guessNum;

    int guessNumber() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Guesser,Please guess any number");
        guessNum = scan.nextInt();    
        return guessNum;
    }
}

class Player {
    int guessNum;
    String name;
    boolean rightGuess = false;
    int score = 0;

    void setScore(int num) {
        score += num;
    }

    int getScore() {
        return score;
    }

    int guessNumber() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Player,"+name+" Please guess a number");
        guessNum = scan.nextInt();
        return guessNum;
    }

    void resetScore() {
        score = 0;
    }
}

class Umpire {

    int numFromGuesser;
    int numberOfPlayers = 0;
    String name;
    int gameNumType = -1;
    // store player type in a array
    Player[] players; 

    void getNumFromGuesser() {
        Guesser guessPlayer = new Guesser();
        numFromGuesser = guessPlayer.guessNumber();
    }   

    void getNumFromPlayers () {
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i].guessNumber();
        }
    }

    void compare() {
        for (int i = 0; i < numberOfPlayers; i++) {
            if(players[i].guessNum == numFromGuesser) {
                players[i].score += 1;
                players[i].rightGuess = true;
            }else {
                players[i].rightGuess = false;
                if(players[i].score > 0) {
                    players[i].score -= 1;
                }
                System.err.println("wrong guess");
            }
        }
    }

    void getDetails(){
        System.out.println("How many of you are playing");
        Scanner scan = new Scanner(System.in);
        numberOfPlayers = scan.nextInt();
        players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new Player();
            System.err.println("player "+(i+1)+" Enter your name");
            name = scan.next();
            players[i].name = name;
        }

    }

    void getResult() {
        for (int i = 0; i < numberOfPlayers; i++) {
            if(players[i].rightGuess) {
                System.err.println(players[i].name + " Guessed the right number and has "+ players[i].score +" score");
            }else {
                System.err.println(players[i].name + " Guessed the wrong number and has "+ players[i].score +" score");
            }
        }
        System.out.println("Press 1 to play again or Press 0 to exit or Press 2 to reset the score and play again");
        Scanner scan = new Scanner(System.in);
        gameNumType = scan.nextInt();
        start();
    }

    void resetPlayersScore() {
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i].resetScore();
        }
    }

    void start() {
        if(gameNumType < 0) {

            getDetails();
            getNumFromGuesser();
            getNumFromPlayers();
            compare();
            getResult();
        }else {
            switch (gameNumType) {
                case 1:
                    getNumFromGuesser();
                    getNumFromPlayers();
                    compare();
                    getResult();
                    break;
                case 0:
                // exit
                System.exit(1);
                case 2:
                    resetPlayersScore();
                    getNumFromGuesser();
                    getNumFromPlayers();
                    compare();
                    getResult();
                break;
    
                default:
                    break;
            }
        }

    }
}

class LaunchGame {


 

    void start() {
        
        
        Umpire umpire = new Umpire();
        umpire.start();
    }
}

class GuessGame {
    public static void main(String []args) {
        LaunchGame launch = new LaunchGame();
        launch.start();
    }
}