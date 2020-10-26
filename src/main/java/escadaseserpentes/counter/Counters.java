package escadaseserpentes.counter;

import escadaseserpentes.board.Board;
import escadaseserpentes.infratructure.Printable;

public class Counters implements Printable {

	private Counter[] counters;
	private int currentCounterIndex = -1;

	public Counters(Board board, int numPlayers) {
		counters = new Counter[numPlayers];
		char currentName = 'A';

		for (int i = 0; i < counters.length; i++) {
			counters[i] = new Counter(String.valueOf(currentName));
			currentName++;
		}

		board.setupCounters(counters);
	}

	public Counter next() {
		currentCounterIndex = (currentCounterIndex + 1) % counters.length;
		return counters[currentCounterIndex];
	}

	@Override
	public void print() {
		for (Counter counter : counters) {
			System.out.format("Jogador '%s' est� na posi��o %s\n", counter.getName(), counter.getCurrentSpace());
		}
	}

}
