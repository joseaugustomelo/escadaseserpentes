package escadaseserpentes.counter;

import escadaseserpentes.board.Board;
import escadaseserpentes.board.Space;
import escadaseserpentes.core.Dice;

public class Counter {

	private String name;
	private static Space currentSpace;

	public Counter(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Space getCurrentSpace() {
		return currentSpace;
	}

	public static void goTo(Space space) {
		currentSpace = space;

	}

	public void play(Board board) {
		Dice dice = Dice.get();
		int diceNumber = dice.roll();

		System.out.format("Jogador '%s' jogou o dado e o resultado foi %d\n", name, diceNumber);

		board.move(this, diceNumber);

	}

}
