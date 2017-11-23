//import GameRules


public class VierGewinnt {

	public static void main(String[] args) {
		//System.out.println("Hallo Welt!");
                Board game = new Board();
                HumanPlayer player1 = new HumanPlayer("Luise");
                HumanPlayer player2 = new HumanPlayer("Hans");
                game.play(player1, player2);
	}


}
