package P1;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class AnimalList implements Iterable<Animal>, Serializable{
    // ATTRIBUTES
    private int size=0;
    private AnimalNode<Animal> head = null, tail = null;
    //METHODS:
// isEmpty
    public boolean isEmpty(){return (size==0);}
    // size
    public int size(){return size;}
    //ADDING
// addFirst (add AnimalNode then increment size)
    public void addFirst(Animal element){
        AnimalNode<Animal> n = new AnimalNode<Animal>(element);
        if(isEmpty())
            head = tail = n;
        else{
            n.next = head;
            head = n;
        }
        size++;
    }
    // addLast (add AnimalNode then increment size)
    public void addLast(Animal element){
        AnimalNode<Animal> n = new AnimalNode<Animal>(element);
        if(isEmpty())
            head = tail = n;
        else{
            tail.next = n;
            tail = n; //tail = tail.next;
        }
        size++;
    }
    // add(index,e)
    public void add(int index, Animal element){
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        else if(index == 0)
            addFirst(element);
        else if(index == size)
            addLast(element);
        else{
            AnimalNode<Animal> AnimalNode = new AnimalNode<Animal>(element);
//get a reference to element at index-1
            AnimalNode<Animal> current = head;
            for (int i = 0; i < index-1; i++)
                current = current.next;
            AnimalNode.next = current.next;
            current.next = AnimalNode;
        }
        size++;
    }
    // add(e)
    public void add(Animal element){
        addLast(element);
    }
    //REMOVING
// removeFirst and decrement size
    public Animal removeFirst(){
        if(isEmpty())
            return null;
//OR throw new NoSuchElementException();
        else{
            AnimalNode<Animal> temp = head;
            head = head.next;
            if(head == null) tail = null;
            size--;
            return temp.element;
        }
    }
    // remove last and decrement size
    public Animal removeLast(){
        if(isEmpty())
            return null;
//OR throw new NoSuchElementException();
        else if(size == 1)
            return removeFirst();
        else{ //more than one element
//temp save last AnimalNode in order to return it
            AnimalNode<Animal> temp = tail;
//get a reference to AnimalNode at size-2
            AnimalNode<Animal> current = head;
            for (int i = 0; i < size-2; i++)
                current = current.next;
//move tail to current
            tail = current;
            tail.next = null;
            size--;
//return last AnimalNode
            return temp.element;
        }
    }
    // remove by index
    public Animal remove(int index){
        if(index < 0 || index > size-1)
            throw new IndexOutOfBoundsException();
        else if(index == 0)
            return removeFirst();
        else if (index == size-1)
            return removeLast();
        else{
            AnimalNode<Animal> prev = head;
            for (int i = 0; i < index-1; i++)
                prev = prev.next;
            AnimalNode<Animal> current = prev.next;
            prev.next = current.next;
            size--;
            return current.element;
        }
    }
    //GET/SET
// getFirst/getLast
    public Animal getFirst(){
        if(isEmpty())
            return null;
        else
            return head.element;
    }
    public Animal getLast(){
        if(isEmpty())
            return null;
        else
            return tail.element;
    }
    public Animal get(int index){
        //checked validity.
        if (index<0 || index>= size) return null;
        else if (index == 0) return getFirst();
        else if (index ==  size-1) return getLast();
        else {
            AnimalNode<Animal> currnt =head;
            for (int i = 0; i < index; i++)
                currnt = currnt.next;
            return currnt.element;
        }
    }

    //ITERATOR
//iterator
    public Iterator<Animal> iterator(){
        return new MyIterator();
    }

    public Animal set(int index, Animal element) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();
        AnimalNode<Animal> animalNode = head;
        for(int i = 0; i < index; i++)
            animalNode = animalNode.next;
        Animal temp = animalNode.element;
        animalNode.element = element;
        return temp;
    }

    // Iterator class
    class MyIterator implements Iterator<Animal>{
        private AnimalNode<Animal> current = head;
        public boolean hasNext() {
            return (current != null);
        }
        public Animal next() {
            Animal tmp = current.element;
            current = current.next;
            return tmp;
        }
    }

    private class AnimalNode<Animal> implements Serializable{
        Animal element;
        AnimalNode<Animal> next;

        public AnimalNode(Animal animal) {
            this.element = animal;
        }
    }
    public String toString(){
        return super.toString();
    }
    public AnimalList getHungryAnimals(){
        //creatd AnimalList here. And added the animals whose energy is lower than 50.
        AnimalList animals = new AnimalList();
        AnimalNode<Animal> animalAnimalNode = head;
        while (animalAnimalNode != null){
            Animal animal = animalAnimalNode.element;
            if (animal.getEnergy() < 50) animals.add(animal);
            animalAnimalNode = animalAnimalNode.next;
        }
        if (animals.size() > 0) return animals;
        else return null;
    }
    public AnimalList getStarvingAnimals(){
        //created AnimalList here. And added the animals whose energy is lower than 17.
        AnimalList animals = new AnimalList();
        AnimalNode<Animal> animalAnimalNode = head;
        while (animalAnimalNode != null){
            Animal animal = animalAnimalNode.element;
            if (animal.getEnergy() < 17) animals.add(animal);
            animalAnimalNode = animalAnimalNode.next;
        }
        if (animals.size() > 0) return animals;
        else return null;
    }
    public AnimalList getAnimalsInBarn() {
        //created AnimalList here. And added the animals whose coordinates are within the rectangle.
        AnimalList animals = new AnimalList();
        int x1 = 450;
        int x2 = 550;
        int y1 = 50;
        int y2 = 150;
        AnimalNode<Animal> animalAnimalNode = head;
        while (animalAnimalNode != null) {
            Animal animal = animalAnimalNode.element;
            if (animal.getX() > x1 && animal.getX() < x2 &&
                    animal.getY() > y1 && animal.getY() < y2) {
                animals.add(animal);}
            animalAnimalNode = animalAnimalNode.next;
            }
        if (animals.size() > 0) return animals;
        else return null;
    }
    public double getRequiredFood(){
        //created AnimalList here. And added the animal's needed energy to the sum which is double type.

        AnimalList animals = new AnimalList();
        double requiredAmount = 0;
        for (int i = 0; i < size; i++){
            requiredAmount += (100 - get(i).getEnergy());
        }
        return requiredAmount;
    }

}
