/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektas;

import java.util.Random;

/**
 *
 * @author Matas
 * @param <E>
 */
public class SkipList<E extends Comparable<E>> implements Set<E> {
    
    public static final int DEFAULT_NUMBER_OF_ELEMENTS = 40;
    public static final float DEFAULT_PROBABILITY = 0.5f;
    public static int LIST_HEIGHT;
    public static float PROBABILITY;
    public static int size;
    private Node<E> last = null;
    private Node<E> first = null;
    public Random rnd = new Random();
    
    public SkipList(){
        this(DEFAULT_NUMBER_OF_ELEMENTS, DEFAULT_PROBABILITY);   
    }
    
    public SkipList(int ExpectedNumberOfElements){
        this(ExpectedNumberOfElements, DEFAULT_PROBABILITY);   
    }
    
    public SkipList(int ExpectedNumberOfElements, float probability){
        size = 0;
        LIST_HEIGHT = (int) Math.round(Math.log(ExpectedNumberOfElements)/Math.log(1/probability));
        this.first = new Node(null, last);
        for (int i = 0; i < LIST_HEIGHT; i++) {
            first.links[i] = last;
        }  
    }
    
    @Override
    public boolean contains(E e) {
        if (e == null) {
            throw new IllegalArgumentException("Element is null in contains(E element)");
        }
        return get(e) != null;
    }
    
    public E get(E e){
        if (e == null) {
            throw new IllegalArgumentException("Element is null in get(E element)");
        }
        int i = LIST_HEIGHT-1;
        Node<E> n = first;
        while(i >= 0){
            while(n.links[i] != null && n.links[i].element.compareTo(e) < 0)
                n = n.links[i];
            i--;
        } 
        if(n.links[0] == null && n.element.equals(e) || n.links[0] != null && n.links[0].element.equals(e))
            return n.element;
        else return null;
    }
    
    public void add(E e){
         if (e == null) {
            throw new IllegalArgumentException("Element is null in add(E element)");
        }
        int height = LIST_HEIGHT-1;
        Node<E> n = first;
        Node<E>[] linksToFix = new Node[LIST_HEIGHT];
        while(height >= 0){
            while(n.links[height] != null && n.links[height].element.compareTo(e) < 0)
                n = n.links[height];
            linksToFix[height] = n;
            height--;
        }
        if (n.links[0] == null || !n.links[0].element.equals(e)){
            Node<E> added = new Node(e, n.links[0]);
            n.links[0] = added; 
            size++;
            int newNodeHeight = randomizeHeight();
            for (int j = 1; j < linksToFix.length; j++) {
                if(j <= newNodeHeight){
                    added.links[j] = linksToFix[j].links[j];
                    linksToFix[j].links[j] = added;
                }
            }
        }
    }
    
    public void remove(E e){
         if (e == null) {
            throw new IllegalArgumentException("Element is null in remove(E element)");
        }
        int height = LIST_HEIGHT-1;
        Node<E> n = first;
        Node[] linksToFix = new Node[LIST_HEIGHT];
        while(height >= 0){
            while(n.links[height] != null && n.links[height].element.compareTo(e) < 0)
                n = n.links[height];
            linksToFix[height] = n;
            height--;
        }
        if (n.links[0] == null && n.element.equals(e) || n.links[0] != null && n.links[0].element.equals(e)){
            Node<E> elementToRemove = n.links[0];
            for (int j = 0; j < linksToFix.length; j++) {
                if(elementToRemove.links[j] != null){
                    linksToFix[j].links[j] = elementToRemove.links[j];
                }
            }
            size--;
        }
    }
    
    private int randomizeHeight(){
        int height = 0;
        while (height < LIST_HEIGHT-1){
            int nr = rnd.nextInt(Math.round(1/DEFAULT_PROBABILITY));
            if(nr == 1)
                height++;
            else
                return height;
        }
        return height;
    }
    
    
    @Override
    public String toVisualizedString(){
        if(first.links[0] == null)
            return null;
        Node<E> n;
        StringBuilder result = new StringBuilder();
        for (int i = LIST_HEIGHT-1; i>=0; i--) {
            n = first.links[i];
            Node<E> baseNode = first.links[0];
            while(baseNode != null){
                if(n != null && baseNode.element.equals(n.element)){
                        result.append(String.format("%6s", n.element.toString()));
                    n = n.links[i];
                }
                else
                    result.append(String.format("%6s", "---"));
                baseNode = baseNode.links[0];
            }
            result.append(System.lineSeparator());
        }       
        return result.toString();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        for (int i = 0; i < LIST_HEIGHT-1 ; i++) {
            first.links[i] = last;
        }
    }
        
    
    private class Node<E>{
        public E element;
        public Node<E>[] links = new Node[LIST_HEIGHT];
        
        public Node(){
        }
        
        public Node(E e, Node n){
            this.element = e;
            this.links[0] = n;
        }
    }
}


//kokie kintamieji?
//kaip gui padaryt?
//kaip su random?
