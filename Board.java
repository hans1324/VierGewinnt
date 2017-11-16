public class Board {
// implements the game board

    private static final int xmax = 7;
    private static final int ymax = 6;
    private Square[][] element = new Square[xmax][ymax];


    public Board() {
        System.out.println("Initializing board... ");
        for(int y = 0; y < ymax; y++) {
            for(int x = 0; x < xmax; x++) {
                element[x][y] = new Square(x, y);
            }//end of for(x)
        }//end of for(y)
    }


    public void show() {
        for(int y = 0; y < ymax; y++) {
            StringBuffer line = new StringBuffer("|");
            for(int x = 0; x < xmax; x++) {
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
        for(y = ymax-1; y >= 0; y--) {
            // Search from bottom to top for the first empty cell
            if(element[x][y].getContent() == PieceType.none) { break; }
        }
        element[x][y].setContent(player);
        return true;
    }
}
