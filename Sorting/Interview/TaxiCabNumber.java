package Interview;

import java.util.PriorityQueue;
import java.util.Queue;

//Taxicab numbers. Find the smallest integers that can be expressed as the sum of 
//cubes of integers in two different ways (1,729), three different ways (87,539,319), 
//four different ways (6,963,472,309,248), five different ways (48,988,659,276,962,496), 
//and six different ways (24,153,319,581,254,312,065,344). 
//Such integers are named Taxicab numbers after the famous Ramanujan story. 
//The smallest integers that can be expressed as the sum of cubes of integers in seven 
//different ways is currently unknown. 

//Write a program Taxicab.java that reads in a command line parameter N and prints out 
//all nontrivial solutions of a3 + b3 = c3 + d3. such that a, b, c, and d, are less than or equal to N.

//Time efficiency: N^2Log(N^2) = 2 * N ^2 * LogN  = O(N^2 LogN)  because we keep adding pairs to the pq 
//until all pairs are added. We also keep deleting the min, each causes Log(N^2)
//Space efficiency: O(N) because of using a priority queue

//We can also solve this problem by adding all sum of pairs to an array, such as 1^3 + 2^3, 1^3 + 3^3...
// 1^3 + N^3.  Then sort the array, then we go through the sorted array, when see a duplicate, means we 
//find the number.
//This way we can solve the problem using N^2Log(N^2) = 2 * N ^2 * LogN  = O(N^2 LogN) to sort the array
// Using another O(N^2) to iterate the array.
//Space efficiency: O(N^2)

//Reference: https://algs4.cs.princeton.edu/24pq/Taxicab.java.html
//https://stackoverflow.com/questions/12243571/how-to-find-all-taxicab-numbers-less-than-n
public class TaxiCabNumber implements Comparable<TaxiCabNumber>{
	private int i;
	private int j;
	private int sum;
	public  TaxiCabNumber(int i, int j) {
		this.i = i;
		this.j = j;
		sum = i * i * i + j * j * j;
	}
	
	public int compareTo(TaxiCabNumber that) {
		if(this.sum < that.sum) return -1;
		else if(this.sum > that.sum) return 1;
		else if(this.i < that.i) return -1;
		else if(this.i > that.i) return 1;
		else return 0;
	}
	
	public String toString() {
		return i + "^3 + " + j + "^3";
	}
	
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		Queue<TaxiCabNumber> pq = new PriorityQueue<TaxiCabNumber>();
		for(int i = 1; i <= N; i++) {
			pq.add(new TaxiCabNumber(i, i));
		}
		TaxiCabNumber prev = new TaxiCabNumber(0, 0);
		int run = 1;
		TaxiCabNumber curr;
		while(!pq.isEmpty()) {
			curr = pq.poll();
			if(curr.sum == prev.sum) {
				run++;
				if(run == 2) {
					System.out.println(curr.sum + "=" + prev + "=" + curr);
				}
			}
			else {
				run = 1;
			}
			if(curr.j < N) pq.add(new TaxiCabNumber(curr.i, curr.j + 1));
			prev = curr;
		}
	}
}
