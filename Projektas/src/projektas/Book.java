/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektas;

//import edu.ktu.ds.lab2.utils.Ks;
//import edu.ktu.ds.lab2.utils.Parsable;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Matas
 */
public class Book implements Parsable<Book> {
    private String title;
    private String publisher;
    private int year;
    private int pageCount;
    private double price;
    private static final String idCode = "N";   //  ***** nauja
    private static int serNr = 101;               //  ***** nauja
    private final String RegNr;
    
    public Book() {
        RegNr = idCode + (serNr++);    // suteikiamas originalus RegNr
    }

    public Book(String title, String publisher,
            int year, int pageCount, double price) {
        RegNr = idCode + (serNr++);
        this.title = title;
        this.publisher = publisher;
        this.year = year;
        this.pageCount = pageCount;
        this.price = price;
    }
    
    public Book(String data) {
        RegNr = idCode + (serNr++);
        parse(data);
    }
    
    public Book(Builder builder) {
        RegNr = idCode + (serNr++);
        this.title = builder.title;
        this.publisher = builder.publisher;
        this.year = builder.year;
        this.pageCount = builder.pageCount;
        this.price = builder.price;
    }
    
    public void parse(String data){
        try {   // ed - tai elementarūs duomenys, atskirti tarpais
            Scanner ed = new Scanner(data);
            title = ed.next();
            publisher = ed.next();
            year = ed.nextInt();
            pageCount = ed.nextInt();
            price = ed.nextDouble();
           // setPrice(ed.nextDouble());
        } catch (InputMismatchException e) {
            Ks.ern("Blogas duomenų formatas apie knygas -> " + data);
        } catch (NoSuchElementException e) {
            Ks.ern("Trūksta duomenų apie knygas -> " + data);
        }
    }

//    public String validate() {
//        String error = "";
//        int currentYear = LocalDate.now().getYear();
//        if (year < minYear || year > currentYear) {
//            error = "Netinkami gamybos metai, turi būti ["
//                    + minYear + ":" + currentYear + "]";
//        }
//        if (price < minPrice || price > maxPrice) {
//            error += " Kaina už leistinų ribų [" + minPrice
//                    + ":" + maxPrice + "]";
//        }
//        return error;
//   }

    @Override
    public String toString() {  // surenkama visa reikalinga informacija
//        return String.format("%-4s %-20s %-11s %4d %4d %8.1f "/*%s*/,
//                RegNr, title, publisher, year, pageCount, price /*validate()*/);
          return String.format("%-4s", RegNr);
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getYear() {
        return year;
    }

    public int getPageCount() {
        return pageCount;
    }

    public double getPrice() {
        return price;
    }

    // keisti bus galima tik kainą - kiti parametrai pastovūs
    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getRegNr() {  //** nauja.
        return RegNr;
    }

//    @Override
//    public int compareTo(Book otherBook) {
//        // lyginame pagal svarbiausią požymį - kainą
//        double otherPrice = otherBook.getPrice();
//        if (price < otherPrice) {
//            return -1;
//        }
//        if (price > otherPrice) {
//            return +1;
//        }
//        return 0;
//    }
    
    @Override
    public int compareTo(Book otherBook) {
        if(this == null){
            if(otherBook == null){
                return 0;
            }
            return -1;
        }else if(otherBook == null) return 1;
        int i = getRegNr().compareTo(otherBook.getRegNr());
        return i;
    }
    
    
    
    @Override
    public boolean equals(Object obj){
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;       
        final Book other = (Book)obj;
        return this.title.equals(other.title) && this.publisher.equals(other.publisher) &&
                this.year == other.year && this.pageCount == other.pageCount &&
                this.price == other.price;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.title);
        hash = 37 * hash + Objects.hashCode(this.publisher);
        hash = 37 * hash + this.year;
        hash = 37 * hash + this.pageCount;
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        return hash;
    }
    
    public static class Builder {

        private final static Random RANDOM = new Random(1949);  // Atsitiktinių generatorius
        private final static String[][] TITLES = { // galimų automobilių markių ir jų modelių masyvas
            {"Return of ", "the king", "Bob", "the emperor", "some guy"},
            {"Death of ", "the author", "a kitty", "Bob", "John", "some guy"},
            {"Adventures of ", "Winnie the Pooh", "Tom Sawyer", "Mr Bean", "some guy"},
            {"The making of ", "cars", "planes", "trains", "ships"},
            {"How to ", "capitalism", "make cookies", "find love", "get smart"},
            {"The ", "wreckoning", "stranger", "room", "partner"},
            {"The great ", "escape", "game m8 8/8", "experience", "business strategy", "wall of china", "title"}
        };
        private final static String[] PUBLISHERS = {"Publisherrrr", "Publishman", "Good publisher", "blublibsher", "Bookz", "good books"};

        private String title = "";
        private String publisher = "";
        private int year = -1;
        private int pageCount = -1;
        private double price = -1.0;

        public Book build() {
            return new Book(this);
        }

        public Book buildRandom() {
            int ma = RANDOM.nextInt(TITLES.length);        // markės indeksas  0..
            int mo = RANDOM.nextInt(TITLES[ma].length - 1) + 1;// modelio indeksas 1..
            int me = RANDOM.nextInt(PUBLISHERS.length);
            return new Book(TITLES[ma][0] + TITLES[ma][mo],
                    PUBLISHERS[me],
                    1000 + RANDOM.nextInt(1000),// metai tarp 1990 ir 2009
                    100 + RANDOM.nextInt(900),// rida tarp 6000 ir 228000
                    10 + RANDOM.nextDouble() * 90);// kaina tarp 800 ir 88800
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder publisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder pageCount(int pageCount) {
            this.pageCount = pageCount;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }
    }
    
}
