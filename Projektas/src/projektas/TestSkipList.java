/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektas;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Matas
 */
public class TestSkipList {
    public static Random rnd = new Random();
    
    public static void main(String[] args) {
        
//        SkipList<Integer> list = new SkipList<>();
//        for (int i = 0; i < 40; i++) {
//            list.add(rnd.nextInt(200));
//        }
//        list.add(100);
//        Ks.ounn(list.toVisualizedString());
//        list.remove(100);
//        Ks.ounn(list.toVisualizedString());

        SkipList<Book> list = new SkipList<>();
        Book b1 = new Book("Lord of the Rings", "Publishman", 1997, 555, 21.6);
        Book b2 = new Book("Harry Potter", "BestBooks", 2001, 321, 17.2);
        Book b3 = new Book("Catcher in the Rye", "GoodPublisher", 2001, 400, 20);
        Book b4 = new Book("Faust BestBooks 1234 780 30,1");
        Book b5 = new Book("1984 Booooooooks 1946 510 24");
        Book b6 = new Book("Bible   Jesus  0  1000 5,2");
        Book b7 = new Book("The Stranger", "BestBooks", 1941, 100, 5.5);
        Book b8 = new Book("Animal Farm", "Publishman", 1939, 110, 7.1);
        Book b9 = new Book("Moby Dick", "BestBooks", 1941, 202, 13.3);
        Book b10 = new Book("Hamlet", "GoodPublisher", 1501, 301, 10.2); 
        Book[] booksArray = {b1,b2,b3,b4,b5,b6,b7,b8,b9,b10};
        Collections.shuffle(Arrays.asList(booksArray));
        for (Book b : booksArray) {
            list.add(b);
        }
        Ks.ounn(list.toVisualizedString());
        Book b11 = new Book("There's no book here", "nobook", 1111, 111, 11);
        Book b12 = new Book("The book is a lie", "boOoOoOoOoK", 1111, 111, 11);
        Book b13 = new Book("Not contained", "book", 1234, 123, 1.2);
        Ks.oun(list.get(b2));
        Ks.oun(list.get(b7));
        Ks.oun(list.get(b11));
        list.add(b12);
        list.add(b4);
        Ks.ounn(list.toVisualizedString());
        list.remove(b9);
        list.remove(b5);
        list.remove(b13);
        Ks.ounn(list.toVisualizedString());
    }
    
}
