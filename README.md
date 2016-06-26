nse-fetch
====

nse-fetch is a web-scraping library for Java that provides an API to fetch stock/share/Nifty50 data from the nseindia website.

It can get data of an individual stock,show the top gainers/losers in NIFTY50 ,and also list the NIFTY50 stocks with open=high and open=low.

Supported Java Versions
-----------------------

Oracle Java 8.


Licensing
---------

nse-fetch is released under the terms of the MIT License (MIT).

You are free to use nse-fetch or any of its constituent parts in any other project (even commercial projects) so long as its copyright headers are left intact.

Stability
---------

This library is currently in beta.

If you have found a defect or you want to request a feature enhancement an issue report is the way to bring it to my attention.


Download
---------
The jar can be downloaded from the following link :-

https://sourceforge.net/projects/nse-fetch/files/latest/download

Add it as project dependecy/library to your project to use.


Usage Examples
--------------

Here is a sample program that uses nse-fetch to obtain details of Infosys(INFY).

The getStockDetails(String symbol) method returns a HashMap, and the various data entries can be accessed as follows.

Note:- All values returned are Strings,use Double.parseDouble(String value) to convert.


```java
import com.rishabhk.nsefetch.Nse;
import java.util.HashMap;

public class Example1 {

    public static void main(String[] args){
        Nse nse = new Nse();
        
        HashMap<String,String> hashMap = nse.getStockDetails("INFY");

        System.out.println(hashMap.get(Nse.LAST_TRADED_PRICE));
        System.out.println(hashMap.get(Nse.PREVIOUS_CLOSE));
        System.out.println(hashMap.get(Nse.OPEN));
        System.out.println(hashMap.get(Nse.HIGH));
        System.out.println(hashMap.get(Nse.LOW));
        System.out.println(hashMap.get(Nse.CLOSE));
        
        nse.close();
    }
}
```

Here is another sampe code that displays and returns an array of hash map containing top 10 gainers of NIFTY50.

Use showAndGetTopLosersNifty50() for top 10 losers of NIFTY50.

```java
import com.rishabhk.nsefetch.Nse;
import java.util.HashMap;

public class Example1 {

    public static void main(String[] args){
        Nse nse = new Nse();
        
        HashMap<String,String>[] topGainers = nse.showAndGetTopGainersNifty50();
        
        System.out.println(topGainers[1].get("symbol"));//Get symbol of the biggest gainer.
        System.out.println(topGainers[2].get(Nse.LAST_TRADED_PRICE));//Get LTP of the 2nd biggest gainer.
        
        nse.close();
    }
}
```

Another method that shows the stocks with Open=Low and Open=High for the day.

```java
import com.rishabhk.nsefetch.Nse;
import java.util.HashMap;

public class Example1 {

    public static void main(String[] args){
        Nse nse = new Nse();
        
        nse.showOpenEqualsHighLow();
        
        nse.close();
    }
}
```

Libraries Used
---------
[ui4j](https://github.com/ui4j/ui4j)
[jsoup](https://jsoup.org/)



