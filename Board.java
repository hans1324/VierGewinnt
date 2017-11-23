public class Board {
// implements the game board

    public final int width = 7;
    public final int height = 6;
    private Square[][] element = new Square[width][height];
    public QuadrupletManager qm;


    public Board() {
    // Instanciate a certain number (width*hight) of gaming squares.
        System.out.println("Initializing board... ");
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                element[x][y] = new Square(x, y);
            }//end of for(x)
        }//end of for(y)
        this.qm = new QuadrupletManager(this);
        this.show();
    }
    
    public Square getElement(int x, int y) { return element[x][y]; }


    public void show() {
        for(int y = 0; y < height; y++) {
            StringBuffer line = new StringBuffer("|");
            for(int x = 0; x < width; x++) {
                switch(element[x][y].getContent()) {
                    case pl1: line.append(" x |");
                         break;
                    case pl2: line.append(" o |");
                         break;
                    case none:
                    default: line.append("   |");
                         break;
                }//end of switch
            }//end of for(x)
            System.out.println(line);
        }//end of for(y)
    System.out.println("  0   1   2   3   4   5   6");
    }//end of show()


    public boolean insertPiece(PieceType player, int position) {  // insertPiece(player, pos) Read color from player
        int x = position;
        if(element[x][0].getContent() != PieceType.none) { return false; }
        int y;
        for(y = height-1; y >= 0; y--) {
            // Search from bottom to top for the first empty cell
            if(element[x][y].getContent() == PieceType.none) { break; }
        }
        element[x][y].setContent(player);
        return true;
    }

    public void play(Player player1, Player player2) {
        boolean finished = false;
        int x = 0;
        PieceType winner = PieceType.none;
        while( !finished ) {
            x = player1.choose(this);
            this.insertPiece(PieceType.pl1, x);
            winner = this.qm.updateAll();
            this.show();

            if( winner != PieceType.pl1 ) {
                x = player2.choose(this);
                this.insertPiece(PieceType.pl2, x);
                winner = this.qm.updateAll();
                this.show();
            }
            if( winner != PieceType.none || this.full() ) { finished = true; }
        }
        if( winner == PieceType.none ) 
        { 
            System.out.println("No winner.");
        } else {
            System.out.println("The winner is");
            if(winner == PieceType.pl1) {
                System.out.println(player1.name);
            } else {
                System.out.println(player2.name);
            }   
        }//ifEnd
    }

    public boolean full() {
        boolean f = true;
        for(int i = 0; i < this.width; i++ ) { 
            if(this.getElement(i, 0).getContent() == PieceType.none) { 
                f = false; 
            } 
        }
        return f;
    }
}
