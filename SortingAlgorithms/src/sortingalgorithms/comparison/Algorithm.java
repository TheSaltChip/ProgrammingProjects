package sortingalgorithms.comparison;

import sortingalgorithms.adt.SortingAlgorithmADT;
import sortingalgorithms.adt.MengdeADT;
import sortingalgorithms.arraylist.TabellMengde;

import java.util.Iterator;

public class Algorithm {
	private String name;
	private int numOfReadings;
	private double avgTime;
	private SortingAlgorithmADT algorithm;
	private MengdeADT<Double> times;
	private boolean sortedStatus;
	private String bigO;
	private int lengthOfArr;

	public Algorithm(String name, String bigO, SortingAlgorithmADT algorithm, int lengthOfArr) {
		this.name = name;
		numOfReadings = 0;
		avgTime = 0;
		times = new TabellMengde<Double>();
		sortedStatus = true;
		this.bigO = bigO;
		this.algorithm = algorithm;
		this.lengthOfArr = lengthOfArr;
	}

	private void incrementReadings() {
		numOfReadings++;
	}

	public void addTime(double time) {
		times.leggTil(time);
		incrementReadings();
	}
	
	public void addSortedStatus(boolean isSorted) {
		if(sortedStatus && !isSorted) {
			sortedStatus = false;
		}
	}

	public void setNumOfReadings(int numOfReadings) {
		this.numOfReadings = numOfReadings;
	}

	public void setavgTime(int avgTime) {
		this.avgTime = avgTime;
	}

	public void setBigO(String bigO) {
		this.bigO = bigO;
	}

	public void setAlgorithm(SortingAlgorithmADT algorithm) {
		this.algorithm = algorithm;
	}

	public void setLengthOfArr(int lengthOfArr) {
		this.lengthOfArr = lengthOfArr;
	}

	public int getNumOfReadings() {
		return numOfReadings;
	}

	public double getAvgTime() {
		double sumTime = 0;

		Iterator<Double> it = times.oppramser();

		while (it.hasNext()) {
			sumTime += it.next();
		}
		
		avgTime = sumTime / numOfReadings;
		
		return avgTime;
	}

	public String getName() {
		return name;
	}

	public String getBigO() {
		return bigO;
	}

	public SortingAlgorithmADT getAlgorithm() {
		return algorithm;
	}

	public int getLengthOfArr() {
		return lengthOfArr;
	}
	
	public boolean isAllSorted(){
		return sortedStatus;
	}
}
