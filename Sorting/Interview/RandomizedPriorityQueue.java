package Interview;
//Describe how to add the methods sample() and delRandom() to our binary heap implementation.
//The two methods return a key that is chosen uniformly at random among the remaining keys, with the latter method also removing that key.
//The sample() method should take constant time; the delRandom() method should take logarithmic time.
//Do not worry about resizing the underlying array.

/*
generate random number from 0 - N, sample() just return that number
when delete random, exchange with last, delete last, 
do a swim and a sink
It's an easy question, can reference to the changeKey in IndexMinPQ
*/
public class RandomizedPriorityQueue {

}
