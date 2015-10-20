package de.lezleoh.mathgame.term;

import java.util.ArrayList;

public class TermQueue {
	ArrayList<TermInt> terms;
	int counter;

	public TermQueue() {
		super();
		terms = new ArrayList<TermInt>();
		counter = 0;
	}

	public void addTerm(TermInt term) {
		terms.add(term);
	}

	public void removeTerm() {
		if (!terms.isEmpty()) {
			terms.remove(0);
		}
	}

	public TermInt getNextTerm() {
		counter++;
		if (counter >= terms.size()) {
			counter = 0;
		}
		if (!terms.isEmpty()) {
			return terms.get(counter);
		} else {
			return new Number(new Integer(0));
		}
	}

	public static TermInt randomTerm(int depth) {
		//TODO construct random Term instead of fix term
		Number summand11 = new Number(11);
		Number summand12 = new Number(12);
		Number summand13 = new Number(13);
		Number summand21 = new Number(21);
		Number summand22 = new Number(22);
		Sum sum1 = new Sum();
		Sum sum2 = new Sum();
		sum1.addOperand(summand11);
		sum1.addOperand(summand12);
		sum1.addOperand(sum2);
		sum1.addOperand(summand13);
		sum2.addOperand(summand21);
		sum2.addOperand(summand22);
		return sum1;
	}
}
