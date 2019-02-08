package com.app;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.apache.commons.text.WordUtils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class App 
{
    public static void main( String[] args )
    {
        // ===============================================================================
        // zliczanie ilosci wystapien wzorcow - sposob 1
        var expression = "hala mala na niej ala";
        var pattern = "ala";
        System.out.println("SPOSOB 1 -> " + StringUtils.countMatches(expression, pattern));

        // ===============================================================================
        // inne metody StringUtils
        System.out.println("\n----- capitalize ------");
        System.out.println(StringUtils.capitalize("ala ma kota"));

        System.out.println("\n----- contains only ------");
        System.out.println(StringUtils.containsOnly("alam", 'a', 'l')); // false
        System.out.println(StringUtils.containsOnly("ala", 'a', 'l')); // true

        System.out.println("\n----- default if blank ------");
        System.out.println(StringUtils.defaultIfBlank("   ", "xxx"));
        System.out.println(StringUtils.defaultIfBlank("        ", "xxx2"));

        System.out.println("\n----- default if empty ------");
        System.out.println(StringUtils.defaultIfEmpty("", "yyy"));

        System.out.println("\n----- delete whitespaces ------");
        System.out.println(StringUtils.deleteWhitespace("   ala    ma    kota"));

        System.out.println("\n----- ends with any ------");
        System.out.println(StringUtils.endsWithAny("ala ma kota", "psa", "koguta", "kota")); // true
        System.out.println(StringUtils.endsWithAny("ala ma kota", "psa", "koguta")); // false

        System.out.println("\n----- first non empty ------");
        System.out.println(StringUtils.firstNonEmpty("", "", "ala", "ma"));

        System.out.println("\n----- first non blank ------");
        System.out.println(StringUtils.firstNonEmpty("      ", "", "ala", "ma"));

        System.out.println("\n----- get digits ------");
        System.out.println(StringUtils.getDigits("al1 ma23 ko4ta"));

        // ===============================================================================
        // inne metody WordUtils
        System.out.println("\n----- initials ------");
        System.out.println(WordUtils.initials("Ala Kowalska"));

        System.out.println("\n----- wrap ------");
        System.out.println(WordUtils.wrap("Dzisiaj ala ma kota jutro ola ma psa", 20));

        // ===============================================================================
        // ilosc zmian, ktore nalezy dokonac zeby przejsc z jednego napisu do drugiego
        System.out.println("\n----- levenshtein ------");
        LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
        System.out.println("ILOSC ZMIAN = " + levenshteinDistance.apply("ala", "agata"));
        System.out.println("ILOSC ZMIAN = " + levenshteinDistance.apply("ala", "ala"));

        // NAPISY JAVA 11
        System.out.println("\n\n----- java11 ------");
        var str1 = "ADAM";
        System.out.println("a. " + str1.repeat(3));
        System.out.println("b. " + str1.repeat(0).equals(""));
        System.out.println("c. " + "".repeat(100).equals(""));
        System.out.println("d. " + "".isBlank());
        System.out.println("e. " + "    ala    ma    kota    ".stripLeading());
        System.out.println("f. " + "    ala    ma    kota    ".stripTrailing() + "_");
        System.out.println("f. " + "    ala    ma    kota    ".strip()); // trim()?
        System.out.println("g. " + "ala\nma\nkota".lines().collect(Collectors.joining("|")));

        // ROZBIJANIE
        System.out.println("\n\n----- rozbijanie ------");
        var str = "ala ma kota oraz psa ala ala    ";

        var tab1 = str.split(" ", 1); // limit > 0 - rozbijanie po wzorcu nastapi co najwyzej limit - 1 razy
        var tab2 = str.split(" ", 2);
        var tab3 = str.split(" ", 3);
        var tab4 = str.split(" ", 4);
        var tab5 = str.split(" "); // limit = 0 - puste napisy z konca tablicy usuniete
        var tab6 = str.split(" ", -1); //limit < 0, puste elementy z konca tablicy zostaja
        System.out.println(Arrays.toString(tab1));
        System.out.println(Arrays.toString(tab2));
        System.out.println(Arrays.toString(tab3));
        System.out.println(Arrays.toString(tab4));
        System.out.println(Arrays.toString(tab5));
        System.out.println(Arrays.toString(tab6));
        System.out.println("--------------------------");

        // zliczanie ilosci wystapien wzorcow - sposob 2
        var expression2 = "hala mala na niej ala";
        var pattern2 = "ala";

        // a. rozbijasz napis wedlug podanego wzorca
        // b. -1 oznacza ze rozbijamy kolejne rozbite napisy az do skutku
        // c. zliczamy ile mamy elementow, pomiedzy nimi jest nasz wzorzec i zawsze bedzie
        //    w ilosci o 1 mniej niz ilosc elementow tablicy
        var arr1 = expression2.split(pattern2, -1);
        System.out.println("SPOSOB 2 -> " + Arrays.toString(arr1));
        System.out.println("SPOSOB 2 -> " + ( arr1.length - 1 ));


        // zliczanie ilosci wystapien wzorcow - sposob 3
        var expression3 = "hala mala na niej ala";
        var pattern3 = "ala";

        int counter = 0;
        boolean found = false;
        Pattern ptr = Pattern.compile(pattern3);
        Matcher matcher = ptr.matcher(expression3);

        while (matcher.find()) {
            ++counter;
            System.out.print("GROUP: " + matcher.group());
            System.out.print("\tSTART: " + matcher.start());
            System.out.println("\tEND: " + matcher.end());
        }
        System.out.println("SPOSOB 3 -> " + counter);
    }
}
