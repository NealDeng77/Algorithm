/*
 * An animal shelter, which holds only dogs and cats, operates on a strictly "first in, first
 * out" basis. People must adopt either the "oldest" (based on arrival time) of all animals at the shelter,
 * or they can select whether they would prefer a dog or a cat (and will receive the oldest animal of
 * that type). They cannot select which specific animal they would like. Create the data structures to
 * maintain this system and implement operations such as enqueue, dequeueAny, dequeueDog,
 * and dequeueCat.You may use the built-in linkedlist data structure.
 */
public class AnimalShelter {
	private int order;      //arrival time of the animals
	
	/*
	 * Use Animal Class to store animal information
	 */
	abstract class Animal {
		private int order;
		private String name;
		public Animal(String name) {
			this.name = name;
		}
		
		/*
		 * return the order(timestamp)
		 */
		public int getOrder() {
			return order;
		}
		
		public void setOrder(int order) {
			this.order = order;
		}
		
		/*
		 * Check if the animal arrived earlier than the other one
		 */
		public boolean isOrderThan(Animal a) {
			return this.order < a.getOrder();
		}
	}
	
	private class Cat extends Animal{
		public Cat(String name) {
			super(name);
		}
	}
	
	private class Dog extends Animal{
		public Dog(String name) {
			super(name);
		}
	}
	
	/*
	 * Store dogs and cats information
	 */
	LinkedQueueOfItems<Dog> dogs = new LinkedQueueOfItems<Dog>();
	LinkedQueueOfItems<Cat> cats = new LinkedQueueOfItems<Cat>();
	
	/*
	 * add animal
	 */
	public void enqueue(Animal a) {
		//add order
		order++;
		a.setOrder(order);
		
		//add animal
		if(a.getClass() == Dog.class) {
			dogs.enqueue((Dog)a);
		}else if(a.getClass() == Cat.class) {
			cats.enqueue((Cat)a);
		}
	}
	
	/*
	 * dequeue from dogs
	 */
	public Dog dequeueDog() {
		return dogs.dequeue();
	}
	
	/*
	 * dequeue from cats
	 */
	public Cat dequeueCat() {
		return cats.dequeue();
	}
	
	/*
	 * dequeue any 
	 */
	public Animal dequeueAny() {
		if(dogs.isEmpty()) {
			return dequeueCat();
		}else if(cats.isEmpty()) {
			return dequeueDog();
		}
		Animal tempCat = cats.peek();
		Animal tempDog = dogs.peek();
		if(tempCat.isOrderThan(tempDog)) {
			return tempCat;
		}else {
			return tempDog;
		}
	}
	
	
}
