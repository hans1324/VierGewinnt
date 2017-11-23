public class Quadruplet {
    
    
    public static enum Alignment { vertical, horizontal, minorDiagonal, majorDiagonal};
    private Alignment orientation;
    private final int x0;
    private final int y0;
    private final int x3;
    private final int y3;
    Square[] square = new Square[4];
    private boolean iswinnable;
    private int numberofpiecespl1;
    private int numberofpiecespl2;
    
    public boolean isWinnable() { return this.iswinnable; }
    
    public static int getDeltaX(Alignment orient) {
    // These functions implement the orientations
        int deltax=0;
        switch(orient) {
            case horizontal:
            case majorDiagonal:
            case minorDiagonal:
                deltax = 3;
                break;
            case vertical:
            default:
                deltax = 0;
                break;
        }
        return deltax;
    }

    public static int getDeltaY(Alignment orient) {
        int deltay=0;
        switch(orient) {
            case vertical:
            case majorDiagonal:
                deltay = 3;
                break;
            case minorDiagonal:
                deltay = -3;
                break;
            case horizontal:
            default:
                deltay = 0;
                break;
        }
        return deltay;
    }
    
    
    public Quadruplet(Board gameboard, Alignment setOrientation, int setleftx, int setleftupy) {
// initiate one possibly win-leading quadruplet of squares in a line, idetified first by the smallest x and second by the smallest y coordinates
        orientation = setOrientation;
        x0 = setleftx;
        y0 = setleftupy;
        //First calculate missing edges...
        x3 = x0 + getDeltaX(orientation);
        y3 = y0 + getDeltaY(orientation);
        //Now connect to the 4 explicit squares...
        for(int i = 0; i < 4; i++) {
            int x = ( i*x0 + (3-i)*x3 ) / 3;
            int y = ( i*y0 + (3-i)*y3 ) / 3;
            square[i] = gameboard.getElement(x, y);
        }
    }

    public int numberOfPieces(PieceType player) {
        int number = 0;
        switch(player) {
            case pl1:
                number = numberofpiecespl1;
            case pl2:
                number = numberofpiecespl2;
            case none:
                number = 4 - numberofpiecespl1 - numberofpiecespl2;
        }
        return number;
    }
    
    

    public PieceType update() { 
        // Estimate information about each player's winning opportunities in this quadruplet.
        // In case anyone has won, the corresponding type of gaming piece (pl1/pl2) is returned. 
        int numberpl1 = 0;
        int numberpl2 = 0;
        PieceType winner = PieceType.none;
        for(int i = 0; i < 4; i++) {
            if( square[i].getContent() == PieceType.pl1) { numberpl1++; }
            if( square[i].getContent() == PieceType.pl2) { numberpl2++; }
        }
        // Look for winner...
        if( numberpl1 == 0 || numberpl2 == 0 ) {
            this.iswinnable = true;
            if( numberpl1 == 4 ) { winner = PieceType.pl1; }
            if( numberpl2 == 4 ) { winner = PieceType.pl2; }
        } else {
            this.iswinnable = false;
        }
        return winner;
    }
    
    public void print() {
        if( y0 == 0 ) {System.out.println("Debug: Upper (smaller number) y equals zero. ");}
        System.out.print("quadruplet from (");
        System.out.print(x0);
        System.out.print(", ");
        System.out.print(y0);
        System.out.print(" ) to (");
        System.out.print(x3);
        System.out.print(", ");
        System.out.print(y3);
        System.out.print(" ). ");
        System.out.print(System.getProperty("line.separator"));
    }
    
    
} //end of class Quadruplet
