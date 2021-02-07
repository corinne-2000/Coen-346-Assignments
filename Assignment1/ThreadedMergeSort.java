package assignment1;

import java.util.ArrayList;
import java.util.List;

public class ThreadedMergeSort implements Runnable{
	
	static int num = 0;
	private List<Integer> list;
	private static List<String> outputList = new ArrayList<>();
	
	public ThreadedMergeSort(List<Integer> inputList) {
		list = inputList;
	}
	
	@Override
	public void run() {			
		System.out.println(Thread.currentThread().getName() + " started");
		outputList.add(Thread.currentThread().getName() + " started");
		list = mergeSort(list);		
	}

	public List<Integer> mergeSort(List<Integer> inputList) {
	
		List<Integer> tempOutputList = new ArrayList<>();
		if(inputList.size() == 1) {
			System.out.println(Thread.currentThread().getName() + " finished: " + inputList.toString());
			outputList.add(Thread.currentThread().getName() + " finished: " + inputList.toString());
			return inputList;
		}
		
		List<Integer> leftList = new ArrayList<Integer>();
		List<Integer> rightList = new ArrayList<Integer>();
		
		int midpoint = inputList.size()/2;
		
		for(int i = 0; i < midpoint; i++) {
			leftList.add(inputList.get(i));
		}
		
		for(int i = midpoint; i < inputList.size(); i++) {
			rightList.add(inputList.get(i));
		}
		
		ThreadedMergeSort msLeft = new ThreadedMergeSort(leftList);
		ThreadedMergeSort msRight = new ThreadedMergeSort(rightList);
		
		Thread lThread = new Thread(msLeft);
		Thread rThread = new Thread(msRight);
	
		lThread.start();
		rThread.start();		
		
		try {
			lThread.join();
			rThread.join();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		leftList = msLeft.getList();
		rightList = msRight.getList();
		
		tempOutputList = merge(leftList, rightList);
		System.out.println(Thread.currentThread().getName() + " finished: " + tempOutputList.toString());
		outputList.add(Thread.currentThread().getName() + " finished: " + tempOutputList.toString());
		
		return tempOutputList;
	}

	private static List<Integer> merge(List<Integer> leftList, List<Integer> rightList) {
		
		List<Integer> tempList = new ArrayList<>();		
		int i = 0;
		
		while(leftList.size() > 0 && rightList.size() > 0) {
			
			if(leftList.get(i) > rightList.get(i)) {
				tempList.add(rightList.get(i));
				rightList.remove(i);
			}
			else {
				tempList.add(leftList.get(i));
				leftList.remove(i);
			}
		}
		
		while(leftList.size() > 0) {
			tempList.add(leftList.get(i));
			leftList.remove(i);
		}
		
		while(rightList.size() > 0) {
			tempList.add(rightList.get(i));
			rightList.remove(i);
		}
		
		return tempList;		
	}
	
	public List<String> getOutputList(){
		return outputList;
	}
	
	public List<Integer> getList(){
		return list;
	}

}
	