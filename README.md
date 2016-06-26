nse-fetch
====

NSE-FETCH is a web-scraping library for Java that provides an API to fetch stock/share/Nifty50 data from the nseindia website.

It can get data of an individual stock,show the top gainers/losers in NIFTY50 ,and also list the NIFTY50 stocks with open=high and open=low.

Supported Java Versions
-----------------------

Oracle Java 8.


Licensing
---------

NSE-FETCH is released under the terms of the MIT License (MIT).

You are free to use Ui4j or any of its constituent parts in any other project (even commercial projects) so long as its copyright headers are left intact.

Stability
---------

This library is currently in beta.

If you have found a defect or you want to request a feature enhancement an issue report is the way to bring it to my attention.


Usage Examples
--------------

Here is a very basic sample program that uses Ui4j to display a web page with a "hello, world!" message. See the [ui4j-sample](https://github.com/ui4j/ui4j/tree/master/ui4j-sample/src/main/java/com/ui4j/sample) project for more sample code snippets.

```java
package com.ui4j.sample;

import com.ui4j.api.browser.BrowserEngine;
import com.ui4j.api.browser.BrowserFactory;
import com.ui4j.api.browser.Page;

public class HelloWorld {

    public static void main(String[] args) {
        // get the instance of the webkit
        BrowserEngine browser = BrowserFactory.getWebKit();

        // navigate to blank page
        Page page = browser.navigate("about:blank");

        // show the browser page
        page.show();

        // append html header to the document body
        page.getDocument().getBody().append("<h1>Hello, World!</h1>");
    }
}
```

Here is another sampe code that list all front page news from Hacker News.

```java
package com.ui4j.sample;

import static com.ui4j.api.browser.BrowserFactory.getWebKit;

import com.ui4j.api.browser.Page;

public class HackerNews {

    public static void main(String[] args) {

        try (Page page = getWebKit().navigate("https://news.ycombinator.com")) {
            page
                .getDocument()
                .queryAll(".title a")
                .forEach(e -> {
                    System.out.println(e.getText().get());
                });
        }
    }
}
```

Building Ui4j
-------------
mvn install

Getting help and getting involved
---------------------------------

Please do not hesitate to submit pull requests.
We are happy to accept documentation and sample code contributions.

FAQ
---

#### How can i set the user agent string?

See [UserAgent.java](https://github.com/ui4j/ui4j/blob/master/ui4j-sample/src/main/java/com/ui4j/sample/UserAgent.java) sample.

#### How can i execute javascript?

See [JavaScriptExecution.java](https://github.com/ui4j/ui4j/blob/master/ui4j-sample/src/main/java/com/ui4j/sample/JavaScriptExecution.java) sample.

#### How can i handle browser login, prompt or confirmation dialog?

See [DialogTest.java](https://github.com/ui4j/ui4j/blob/master/ui4j-webkit/src/test/java/com/ui4j/test/DialogTest.java) for custom handlers or
use default handlers from [Dialogs.java](https://github.com/ui4j/ui4j/blob/master/ui4j-api/src/main/java/com/ui4j/api/dialog/Dialogs.java).

#### What is the easiest way clear all input elements?

Use [clear](https://github.com/ui4j/ui4j/blob/master/ui4j-api/src/main/java/com/ui4j/api/dom/Form.java#L13) method of the Form class.

Donations
---------
Donations are accepted via Amazon.

[Ui4j Amazon Wishlist](https://www.amazon.com/registry/wishlist/1IPPMT8VAXN2F)



