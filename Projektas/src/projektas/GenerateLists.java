/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektas;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Matas
 */
public class GenerateLists {
    
    static Random rnd = new Random();
    
    public void generateSkipList(SkipList list, int listSize){
        for (int i = 0; i < listSize; i++) {
            list.add(rnd.nextInt(1000000));
        }
    }
    
    public void generateLinkedList(LinkedList list, int listSize){
        for (int i = 0; i < listSize; i++) {
            list.add(rnd.nextInt(1000000));
        }
    }
    
    static void generateIndexes(int[] indexes, int listSize) {
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = rnd.nextInt(listSize);
        }
    }
    
    static void generateValues(int[] values, int listSize) {
        for (int i = 0; i < values.length; i++) {
            values[i] = rnd.nextInt(1000000);
        }
    }
}
