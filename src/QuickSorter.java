import java.util.ArrayList;

/************************************************************************************************
 * Project 5:
 * The task of this project is to implement in Java several variations of the in-place QuickSort 
 * algorithm, each with a different choice of pivot. You should note the impact on execution time 
 * of different pivot selection strategies.
 * 
 * @author nate
 * extends java.lang.Comparable<E>
 ************************************************************************************************/

public class QuickSorter<E>{
	
	public enum PivotStategy{
		FIRST_ELEMENT,
		RANDOM_ELEMENT,
		MEDIAN_OF_THREE_RANDOM_ELEMENTS,
		MEDIAN_OF_THREE
	}
	
	public static <E extends java.lang.Comparable<E>> java.time.Duration timedQuickSort(ArrayList<E> list, PivotStategy pivotStrategy) {
		java.time.Duration test;
		return null;
		
		
	}
	
}
