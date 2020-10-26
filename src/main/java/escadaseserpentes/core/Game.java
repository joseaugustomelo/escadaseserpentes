package escadaseserpentes.core;

import escadaseserpentes.board.Board;
import escadaseserpentes.counter.Counter;
import escadaseserpentes.counter.Counters;

public class Game {

	private static final int NUM_SPACES = 30;
	private static final int NUM_PLAYERS = 2;

	public void play() {
		Board board = new Board(NUM_SPACES);
		addTransitions(board);

		board.print();

		Counters counter = new Counters(board, NUM_PLAYERS);
		counter.print();

		while (!board.gameFinished()) {
			Counter currentCounter = counter.next();
			currentCounter.play(board);

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Counter winnerCounter = board.getWinnerCounter();
		System.out.format("Jogado '%s' GANHOU! \n", winnerCounter.getName());

	}

	private void addTransitions(Board board) {
		board.addTransition(4, 12);
		board.addTransition(7, 9);
		board.addTransition(11, 25);
		board.addTransition(14, 2);
		board.addTransition(22, 5);
		board.addTransition(28, 8);
	}

}
