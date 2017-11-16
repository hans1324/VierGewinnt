public class Square {
    private int x;
    private int y;
    private PieceType content;
    public int x() { return x; }
    public int y() { return y; }
    //Quadruplet[][] qlist;


    public Square(int setx, int sety) {
        x = setx;
        y = sety;
        content = PieceType.none; 
    }


    public void setContent(PieceType t) { content = t; }


    public PieceType getContent() { return content; }

}
