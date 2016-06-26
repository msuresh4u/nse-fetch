package com.rishabhk.nsefetch;

import com.ui4j.api.browser.BrowserEngine;
import com.ui4j.api.browser.BrowserFactory;
import com.ui4j.api.browser.Page;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;

/**
 * Created by RisH on 25-06-2016.
 */
public class Nse {

    private BrowserEngine browser;

    private static final String BASE_URL = "https://nseindia.com/";
    private static final String NSE_50_URL = "live_market/dynaContent/live_watch/equities_stock_watch.htm";
    private static final String STOCK_QUOTE = "live_market/dynaContent/live_watch/get_quote/GetQuote.jsp?symbol=";
    private static final String TOP_GAINERS_LOSERS = "live_market/dynaContent/live_analysis/top_gainers_losers.htm";

    private static final String HTML_TAG_ROW = "tr";
    private static final String HTML_TAG_COLUMN = "td";
    private static final String HTML_TAG_LINK = "a";

    private static final String TOP_GAINERS_TABLE_ID = "topGainers";
    private static final String TOP_LOSERS_TABLE_ID = "topLosers";
    private static final String NUMBER_CLASS = "number";
    private static final String LOSERS_BUTTON_ID = "tab8";

    public static final String LAST_TRADED_PRICE = "lastPrice";
    public static final String PREVIOUS_CLOSE = "previousClose";
    public static final String OPEN = "open";
    public static final String HIGH = "dayHigh";
    public static final String LOW = "dayLow";
    public static final String CLOSE = "closePrice";

    public static final String PERCENTAGE_CHANGE = "%change";
    public static final String TRADED_QUANTITY = "tradedqty";

    public Nse() {
        browser = BrowserFactory.getWebKit();
    }

    public static void main(String[] args) {
        Nse nse = new Nse();
        HashMap<String,String> stockDetails = nse.getStockDetails("WIPRO");
        printHashMap(stockDetails);
        //nse.showOpenEqualsHighLow();
        nse.showAndGetTopLosersNifty50();
        nse.close();
    }

    public HashMap<String, String>[] showAndGetTopGainersNifty50() {
        HashMap<String, String>[] topGainers = new HashMap[11];

        Document doc = Jsoup.parse(getPageAsString(BASE_URL + TOP_GAINERS_LOSERS));
        Element table = doc.getElementById(TOP_GAINERS_TABLE_ID);
        Elements rows = table.getElementsByTag(HTML_TAG_ROW);

        int n = 0;
        for (Element row : rows) {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            Elements name = row.getElementsByTag(HTML_TAG_LINK);
            Element firstName = name.first();
            if (firstName != null) {
                //System.out.println(firstName.html());
                hashMap.put("symbol", firstName.html());
                Elements columns = row.getElementsByTag(HTML_TAG_COLUMN);
                int c = 1;
                for (Element column : columns) {
                    if (column.hasClass(NUMBER_CLASS)) {
                        //System.out.println(column.html());
                        switch (c) {
                            case 1:
                                hashMap.put(LAST_TRADED_PRICE, column.html());
                                break;
                            case 2:
                                hashMap.put(PERCENTAGE_CHANGE, column.html());
                                break;
                            case 3:
                                hashMap.put(TRADED_QUANTITY, column.html());
                                break;
                            case 4:
                                break;
                            case 5:
                                hashMap.put(OPEN, column.html());
                                break;
                            case 6:
                                hashMap.put(HIGH, column.html());
                                break;
                            case 7:
                                hashMap.put(LOW, column.html());
                                break;
                            case 8:
                                hashMap.put(PREVIOUS_CLOSE, column.html());
                                break;
                            default:
                                break;
                        }
                        c++;
                    }

                }

            }
            topGainers[n++] = hashMap;
        }
        for (int i = 0; i < 11; i++) {
            printHashMap(topGainers[i]);
            System.out.println("------------------------------------------------");

        }
        //browser.shutdown();
        return topGainers;

    }

    public void close(){
        browser.shutdown();
    }


    public HashMap<String, String>[] showAndGetTopLosersNifty50() {
        HashMap<String, String>[] topLosers = new HashMap[11];
        Page p = browser.navigate(BASE_URL + TOP_GAINERS_LOSERS);
        p.executeScript("document.getElementById('"+LOSERS_BUTTON_ID+"').click()");
        p.show();
        Document doc = Jsoup.parse(p.getDocument().getBody().getInnerHTML());
        p.hide();
        Element table = doc.getElementById(TOP_LOSERS_TABLE_ID);
        Elements rows = table.getElementsByTag(HTML_TAG_ROW);

        int n = 0;
        for (Element row : rows) {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            Elements name = row.getElementsByTag(HTML_TAG_LINK);
            Element firstName = name.first();
            if (firstName != null) {
                //System.out.println(firstName.html());
                hashMap.put("symbol", firstName.html());
                Elements columns = row.getElementsByTag(HTML_TAG_COLUMN);
                int c = 1;
                for (Element column : columns) {
                    if (column.hasClass(NUMBER_CLASS)) {
                        //System.out.println(column.html());
                        switch (c) {
                            case 1:
                                hashMap.put(LAST_TRADED_PRICE, column.html());
                                break;
                            case 2:
                                hashMap.put(PERCENTAGE_CHANGE, column.html());
                                break;
                            case 3:
                                hashMap.put(TRADED_QUANTITY, column.html());
                                break;
                            case 4:
                                break;
                            case 5:
                                hashMap.put(OPEN, column.html());
                                break;
                            case 6:
                                hashMap.put(HIGH, column.html());
                                break;
                            case 7:
                                hashMap.put(LOW, column.html());
                                break;
                            case 8:
                                hashMap.put(PREVIOUS_CLOSE, column.html());
                                break;
                            default:
                                break;
                        }
                        c++;
                    }

                }

            }
            topLosers[n++] = hashMap;
        }
        for (int i = 0; i < 11; i++) {
            printHashMap(topLosers[i]);
            System.out.println("------------------------------------------------");

        }
        return topLosers;

    }


    public HashMap<String, String> getStockDetails(String symbol) {
        HashMap<String, String> stockDetails = new HashMap<String, String>();
        Document doc = Jsoup.parse(getPageAsString(BASE_URL + STOCK_QUOTE + symbol));
        Element lastPriceSpan = doc.getElementById(LAST_TRADED_PRICE);
        Element previousCloseDiv = doc.getElementById(PREVIOUS_CLOSE);
        Element openDiv = doc.getElementById(OPEN);
        Element highDiv = doc.getElementById(HIGH);
        Element lowDiv = doc.getElementById(LOW);
        Element closeDiv = doc.getElementById(CLOSE);

        stockDetails.put(LAST_TRADED_PRICE, lastPriceSpan.html());
        stockDetails.put(PREVIOUS_CLOSE, previousCloseDiv.html());
        stockDetails.put(OPEN, openDiv.html());
        stockDetails.put(HIGH, highDiv.html());
        stockDetails.put(LOW, lowDiv.html());
        stockDetails.put(CLOSE, closeDiv.html());

        return stockDetails;
    }

    private static void printHashMap(HashMap<String, String> hashMap) {
        for (String name : hashMap.keySet()) {
            String key = name.toString();
            String value = hashMap.get(name).toString();
            System.out.println(key + " " + value);
        }
    }

    public void showOpenEqualsHighLow() {
        Document doc = Jsoup.parse(getPageAsString(BASE_URL + NSE_50_URL));
        Elements rows = doc.getElementsByTag(HTML_TAG_ROW);
        String[] names = new String[52];
        String[] open = new String[52];
        String[] high = new String[52];
        String[] low = new String[52];

        int c = 0;
        for (Element row : rows) {
            int n = 0;
            Elements name = row.getElementsByTag(HTML_TAG_LINK);
            Element firstName = name.first();

            if (firstName != null) {
                names[c] = firstName.html();
            }

            Elements columns = row.getElementsByTag(HTML_TAG_COLUMN);
            for (Element column : columns) {
                if (column.hasClass(NUMBER_CLASS)) {
                    if (n == 0)
                        open[c] = column.html();
                    else if (n == 1)
                        high[c] = column.html();
                    else if (n == 2)
                        low[c++] = column.html();
                    n++;
                }
            }

        }
        //browser.shutdown();

        System.out.println("Open=High");
        for (int i = 1; i <= 51; i++) {
            if (open[i].equals(high[i])) {
                System.out.println(names[i]);
            }
        }

        System.out.println("Open=Low");
        for (int i = 1; i <= 51; i++) {
            if (open[i].equals(low[i])) {
                System.out.println(names[i]);
            }
        }
    }


    public String getPageAsString(String url) {
        Page page = browser.navigate(url);
        return (String) page.executeScript("document.documentElement.innerHTML");
    }


}
