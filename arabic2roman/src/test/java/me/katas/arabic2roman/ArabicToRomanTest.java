package me.katas.arabic2roman;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
    
@RunWith(Parameterized.class)
public class ArabicToRomanTest {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {     
                 {     0, ""    }, 
                 {     1, "I"   }, 
                 {     2, "II"  }, 
                 {     3, "III" },
                 {     4, "IV"  },
                 {     5, "V"   },
                 {     6, "VI"  },
                 {     9, "IX"  },
                 {    10, "X"   },
                 {    12, "XII" },
                 {    14, "XIV"  },
                 {    19, "XIX"  },
                 {    24, "XXIV"  },
                 {    48, "XLVIII" }, 
                 {    99, "XCIX" }, 
                 {   111, "CXI" }, 
                 {  1984, "MCMLXXXIV" },
                 {  3999, "MMMCMXCIX" },
                 {  4000, "MV̅" },
                 {  9999, "MX̅CMXCIX" },
           });
    }
    
    private int arabic;
    private String roman;

    public ArabicToRomanTest(int arabic, String roman) {
        this.arabic = arabic;
        this.roman = roman;
    }

    @Test
    public void test() {
        assertEquals(roman, ArabicToRoman.convert(arabic));
    }
}    


//C̅
//M̅
//L̅
//D̅
