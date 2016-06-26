Ui4j

Download Maven License Build Status Coverage Status
Talk with us and ask questions via Gitter

Gitter

Ui4j is a web-automation library for Java. It is a thin wrapper library around the JavaFx WebKit Engine, and can be used for automating the use of web pages and for testing web pages.
Supported Java Versions

Oracle Java 8.

Both the JRE and the JDK are suitable for use with this library.
Licensing

Ui4j is released under the terms of the MIT License (MIT).

You are free to use Ui4j or any of its constituent parts in any other project (even commercial projects) so long as its copyright headers are left intact.
Support

JProfiler

Ui4j is optimized using JProfiler.

Thanks so much to ej-technologies to let us to use the JProfiler with an open source project license.
Stability

This library is suitable for use in production systems.

If you have found a defect or you want to request a feature enhancement an issue report is the way to bring the attention to the Ui4j Community.
Integration with Maven

To use the official release of Ui4j, please use the following snippet in your pom.xml

    <dependency>
        <groupId>com.ui4j</groupId>
        <artifactId>ui4j-all</artifactId>
        <version>2.1.0</version>
    </dependency>

Using Ui4j without Maven

If you use Ui4j without Maven, download pre built jar file.
Supported Platforms

Ui4j has been tested under Windows 8.1 and Ubuntu 14.04, but should work on any platform where a Java 8 JRE or JDK is available.
Headless Mode

Ui4j can be run in "headless" mode using Xfvb or with using Monocle.

Headless Mode with Xfvb

Sample configuration for ubuntu running with headless mode:

    First install these packages: sudo apt-get install xvfb x11-xkb-utils libxrender-dev libxtst-dev libgtk2.0-0

    Then start the xvfb nohup xvfb :99 -ac &

    Then export the DISPLAY variable export DISPLAY=:99.0

Headless Mode with Monocle

    Download or add the maven dependency of latest openjfx-monocle
    Add -Dui4j.headless Java system parameter from command line or with using api System.setProperty("ui4j.headless", "true");

monocle dependency:

<dependency>
    <groupId>org.jfxtras</groupId>
    <artifactId>openjfx-monocle</artifactId>
    <version>1.8.0_20</version>
    <scope>test</scope>
</dependency>

Logging

Both simple logger for java (SLF4J) and Java utility logger (JUL) is supported. If slf4j is available on classpath com.ui4j.api.util.LoggerFactory use slf4j else java utility logger is used.
CSS Selector Engine

Ui4j use W3C selector engine which is default selector engine of WebKit. Alternatively Sizzle selector engine might be used. Sizzle is the css selector engine of JQuery and it supports extra selectors like :has(div), :text, containts(text) etc. Check the Sizzle.java for using sizzle with Ui4j.
Usage Examples

Here is a very basic sample program that uses Ui4j to display a web page with a "hello, world!" message. See the ui4j-sample project for more sample code snippets.

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

Here is another sampe code that list all front page news from Hacker News.

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

Building Ui4j

mvn install
Getting help and getting involved

Please do not hesitate to submit pull requests. We are happy to accept documentation and sample code contributions.
FAQ
How can i set the user agent string?

See UserAgent.java sample.
How can i execute javascript?

See JavaScriptExecution.java sample.
How can i handle browser login, prompt or confirmation dialog?

See DialogTest.java for custom handlers or use default handlers from Dialogs.java.
What is the easiest way clear all input elements?

Use clear method of the Form class.
Donations

Donations are accepted via Amazon.

Ui4j Amazon Wishlist