import java.util.ArrayList;

public class QuadrupletManager {
    //meant to be instanciated once only. 
    //Implements common operations for quadruplets, especially initiating and searching them
    
    private int numberofquads;
    ArrayList<Quadruplet> quads = new ArrayList<Quadruplet>();

    public QuadrupletManager(Board gameboard) {
    //Constructor. Makes a certain number of quadruplets, depending on the gameboard size (usually at 7x6: 69 quads). 
    //System.out.println("Debug: Started QuadrupletManager constructor. ");
        //Load size of the game board...
        int width = gameboard.width;
        int height = gameboard.height;
        //Search for all quadruplets fitting into the gameboard...
        for( int x = 0; x < width; x++) {
            for( int y = 0; y < height; y++) {
                tryToMakeQuadAt(gameboard, Quadruplet.Alignment.horizontal,    x, y);
                tryToMakeQuadAt(gameboard, Quadruplet.Alignment.vertical,      x, y);
                tryToMakeQuadAt(gameboard, Quadruplet.Alignment.majorDiagonal, x, y);
                tryToMakeQuadAt(gameboard, Quadruplet.Alignment.minorDiagonal, x, y);
            } // end of for(y)
        } // end of for(x)
        numberofquads = quads.size();

        //Update for the first time, no one should have won yet...
        if( updateAll() != PieceType.none ) { System.out.println("Something went wrong. Seems like we have a winner before the first move. "); }

        System.out.print("Made ");
        System.out.print(numberofquads);
        System.out.print(" quadruplets. ");
        System.out.print(System.getProperty("line.separator"));
    }

    private boolean tryToMakeQuadAt(Board gameboard, Quadruplet.Alignment orient, int x, int y) {
        boolean success = false;
        int x_edge = x + Quadruplet.getDeltaX(orient);
        int y_edge = y + Quadruplet.getDeltaY(orient);
        if(x_edge < gameboard.width && y_edge >= 0 && y_edge < gameboard.height) {
            quads.add(new Quadruplet(gameboard, orient, x, y));
            success = true;
        }
        return success;
    }
    
    public PieceType updateAll() {
        PieceType winner = PieceType.none;
        for( Quadruplet item : quads ) {
            winner = item.update();
            if( winner != PieceType.none ) { return winner; }
        }
        return PieceType.none;
    }
}

