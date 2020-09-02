package sortingalgorithms.comparison;

import sortingalgorithms.adt.MengdeADT;

import java.util.Iterator;

public class FormatOutput {
	private Comparison comparison;
	private MengdeADT<Algorithm> algorithms;
	
	private Algorithm titleAlgorithm;
	private Algorithm algorithm;
	private Algorithm findingCAlgorithm;
	
	private String horizontalLine = "";
	private String fancyLine = "";
	private String[] cString;

	private final String MAIN_STRING_NAMES = "| %-18s | %-18s | %-18s | %-18s | %-18s | %-18s |";
	private final String C_STRING_NAMES = "| %-18s | %-18s | %-18s | %-18s |";
	private final String C_STRING_VALUES = "| %-18s | %-18s | %s | %s |";

	public FormatOutput(MengdeADT<Algorithm> algorithms, Comparison comparison) {
		this.comparison = comparison;
		this.algorithms = algorithms;
		getCAlgorithm();
		
		makeLines();
		
		cString = buildCString(findingCAlgorithm.getLengthOfArr(), findingCAlgorithm);
	}

	private void makeLines() {
		for (int i = 0; i < 20; i++) {
			horizontalLine += "-";

			if (i % 2 == 0) {
				fancyLine += "/\\";
			}
		}
	}

	private void completeLineForSort(String string) {
		for (int i = 0; i < 6; i++)
			System.out.format("+%s", string);

		System.out.format("+%n");
	}
	
	private void completeLineForCBox(String string) {
		for (int i = 0; i < 4; i++)
			System.out.format("+%s", string);

		System.out.format("+%n");
	}
	
	private String formatSingleEntryToString(Algorithm algorithm, double cValue) {
		String leftAlign = "| %-18d | %-18d | %-18f | %-18f | %-18g | %-18b |";

		String out = String.format(leftAlign, algorithm.getLengthOfArr(), algorithm.getNumOfReadings(),
				(algorithm.getAvgTime()), comparison.computeTheoreticalTime(cValue, algorithm), cValue,
				algorithm.isAllSorted());

		return out;
	}
	
	private void getCAlgorithm() {
		Iterator<Algorithm> it = algorithms.oppramser();
		findingCAlgorithm = algorithms.fjern(it.next());
	}
	

	private String[] buildCString(int size, Algorithm algorithm) {

		double c = findC();

		String avgTime = String.format("%-18g", algorithm.getAvgTime());
		String cValue = String.format("%-18g", c);

		String[] findCString = { size + "", algorithm.getNumOfReadings() + "", avgTime, cValue, algorithm.getBigO() };

		return findCString;

	}
	

	private double findC() {
		double c, n = (double) findingCAlgorithm.getLengthOfArr();

		String fn = findingCAlgorithm.getBigO();

		if (fn.equals("n^2")) {
			c = findingCAlgorithm.getAvgTime() / Math.pow(n, 2);
		} else if (fn.equals("nlog2n")) {
			double nlog2n = n * (Math.log(n) / Math.log(2));
			c = findingCAlgorithm.getAvgTime() / nlog2n;

		} else {
			c = findingCAlgorithm.getAvgTime() / n;
		}

		return c;
	}

	private void makeHeader() {
		titleAlgorithm = algorithms.oppramser().next();
		
		System.out.format("+%-18s+%n", horizontalLine);
		System.out.format("| %-18s |%n", titleAlgorithm.getName());

		completeLineForSort(horizontalLine);

		System.out.format(MAIN_STRING_NAMES + "%n", "n", "Readings", "Average Time", "Theoretical Time", "c",
				"All arryas sorted");

		System.out.format(MAIN_STRING_NAMES + "%n", "", "", "in ms", "in ms (c*" + titleAlgorithm.getBigO() + ")", "", "");

		completeLineForSort(horizontalLine);

	}
	
	private void makeBody(double cValue) {
		Iterator<Algorithm> it = algorithms.oppramser();
		
		while (it.hasNext()) {
			algorithm = it.next();
			System.out.format(formatSingleEntryToString(algorithm, cValue) + "%n");
		}
		
		completeLineForSort(horizontalLine);
		
	}
	
	private void makeCBox() {
		for (int i = 0; i < 4; i++) 
			System.out.format("|%s", fancyLine);
		
		System.out.format("|%n");
		
		completeLineForCBox(horizontalLine);

		System.out.format(C_STRING_NAMES + "%n", "n", "Readings", "Average Time", "c");
		System.out.format(C_STRING_NAMES + "%n", "", "", "in ms", "AvgTime/" + cString[4]);

		
		completeLineForCBox(horizontalLine);
		
		System.out.format(C_STRING_VALUES + "%n", cString[0], cString[1], cString[2], cString[3]);
	
		completeLineForCBox(horizontalLine);
		
		System.out.println();
	}
	
	public void run() {
		makeHeader();
		makeBody(findC());
		makeCBox();
	}
}
