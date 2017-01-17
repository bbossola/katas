package me.katas.arabic2roman;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class ArabicToRoman {

    static Map<Integer, String> symbols = new TreeMap<>(Collections.reverseOrder());
    static {
        symbols.put(1, "I");
        symbols.put(4, "IV");
        symbols.put(5, "V");
        symbols.put(9, "IX");
        symbols.put(10, "X");
        symbols.put(40, "XL");
        symbols.put(50, "L");
        symbols.put(90, "XC");
        symbols.put(100, "C");
        symbols.put(400, "CD");
        symbols.put(500, "D");
        symbols.put(900, "CM");
        symbols.put(1000, "M");
        symbols.put(4000, "MV̅");
        symbols.put(5000, "V̅");
        symbols.put(9000, "MX̅");
        symbols.put(10000, "X̅");
    }
   
    public static String convert(int arabic) {
        StringBuilder result = new StringBuilder();
        
        for (int value : symbols.keySet()) {
            while (arabic >= value) {
                arabic = arabic - value;
                result.append(symbols.get(value));
            }
        }
        
        return result.toString();
    }
}
