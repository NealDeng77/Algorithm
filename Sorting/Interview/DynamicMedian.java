package Interview;
//idea: two heaps, one maxheap to store the smaller part, one min heap to store the larger part, keep the 
//heap balanced.
//reference: https://leetcode.com/problems/find-median-from-data-stream/solution/
//https://leetcode.com/problems/find-median-from-data-stream/discuss/74047/JavaPython-two-heap-solution-O(log-n)-add-O(1)-find


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
