package escadaseserpentes.board;

import java.util.Arrays;

import escadaseserpentes.board.Space.Type;
import escadaseserpentes.counter.Counter;
import escadaseserpentes.infratructure.Printable;

public class Board implements Printable {

	private Space[] spaces;
	private Space SpaceHome;
	private Space spaceStarHere;
	private Counter winnerCounter;

	public Board(int numSpaces) {
		spaces = new Space[numSpaces + 2];

		for (int i = 0; i < spaces.length; i++) {
			if (i == 0) {
				spaces[i] = new Space(0, Type.START_HERE);
				spaceStarHere = spaces[i];

			} else if (i == spaces.length - 1) {
				spaces[i] = new Space(i, Type.HOME);
				SpaceHome = spaces[i];

			} else {
				spaces[i] = new Space(i, Type.REGULAR);
			}
		}
	}

	@Override
	public String toString() {
		return "Board [space=" + Arrays.toString(spaces) + "]";
	}

	@Override
	public void print() {
		System.out.println("TABULEIRO:");
		for (Space space : spaces) {
			System.out.print(space + " ");
		}
		System.out.println();
	}

	public void setupCounters(Counter[] counters) {
		for (Counter counter : counters) {
			Counter.goTo(spaceStarHere);

		}
	}

	public void move(Counter counter, int diceNumber) {
		Space space = counter.getCurrentSpace();
		int newSpaceNumber = space.getNumber() + diceNumber;

		Space newSpace;

		if (newSpaceNumber >= SpaceHome.getNumber()) {
			newSpace = SpaceHome;
			winnerCounter = counter;
		} else {
			newSpace = spaces[newSpaceNumber];
		}

		counter.goTo(newSpace);
		System.out.format("Jogador '%s' foi para a casa %s\n", counter.getName(), newSpace);

		Transition transition = newSpace.getTransition();

		if (transition != null) {
			System.out.format("Jogador '%s' encontrou uma' %s' na casa %s\n", counter.getName(), transition.getType(),
					newSpace);
			counter.goTo(transition.getSpaceTo());
			System.out.format("Jogador '%s' foi para a casa %s\n", counter.getName(), transition.getSpaceTo());
		}
	}

	public Counter getWinnerCounter() {
		return winnerCounter;
	}

	public boolean gameFinished() {
		return winnerCounter != null;
	}

	public void addTransition(int from, int to) {
		Space spaceFrom = spaces[from];
		Space spaceTo = spaces[to];

		Transition transition = new Transition(spaceFrom, spaceTo);
		spaceFrom.setTransition(transition);
	}
}
