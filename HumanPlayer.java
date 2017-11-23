import java.io.Console;

class HumanPlayer extends Player {

    public HumanPlayer(String setName) {
        this.name = setName;
    }

    
    int choose(Board gameboard) {
//        @Override
        int x = 0;
        System.out.println(this.name);
        System.out.println("Your number?");
        Console c = System.console();
        do { do {
                x = Integer.parseInt(c.readLine());
            } while( x < 0 || x >= gameboard.width);
        } while(gameboard.getElement(x, 0).getContent() != PieceType.none);
        return x;
    }
    
}
