package Interview;
//idea: two heaps, one maxheap to store the smaller part, one min heap to store the larger part, keep the 
//heap balanced.
//reference: https://leetcode.com/problems/find-median-from-data-stream/solution/
//https://leetcode.com/problems/find-median-from-data-stream/discuss/343662/java-heap-solution-2-follow-ups
//https://leetcode.com/problems/find-median-from-data-stream/discuss/74047/JavaPython-two-heap-solution-O(log-n)-add-O(1)-find
//Follow up question: https://leetcode.com/problems/find-median-from-data-stream/discuss/275207/Solutions-to-follow-ups
//1. If all integer numbers from the stream are between 0 and 100, how would you optimize it?
// We can maintain an integer array of length 100 to store the count of each number along with a total count. 
//Then, we can iterate over the array to find the middle value to get our median.
//Time and space complexity would be O(100) = O(1).
//2. If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
//https://leetcode.com/problems/find-median-from-data-stream/discuss/343662/java-heap-solution-2-follow-ups
//The reason not to use a hashmap:
//Why do you use a hashmap instead of an array? Do you want to save some space for duplicate numbers? 
//A hashmap makes the time complexity worse, because you have to sort each time to find the median, 
//which takes O(nlogn) per find operation. With a sorted array, it takes O(n) to insert a number 
//and keep it sorted, and takes O(1) to find the median.
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

class DynamicMedian {
    private Queue<Integer> small;  //maxheap to store the smaller part
    private Queue<Integer> large;  //min heap to store the larger part
    /** initialize your data structure here. */
    public DynamicMedian() {
        
        small = new PriorityQueue<>(Collections.reverseOrder());
        large = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        small.add(num);
        large.add(small.poll());
        if(large.size() > small.size()) {
            small.add(large.poll());
        }
    }
    
    public double findMedian() {
        return small.size() > large.size() ? small.peek() : (double)(small.peek() / 2.0 + large.peek() / 2.0);
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
