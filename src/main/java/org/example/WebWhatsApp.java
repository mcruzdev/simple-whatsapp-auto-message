package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebWhatsApp {
    private final WebDriver webDriver;
    private final int time;
    private final String automaticMessage;

    private static final String WEB_WHATSAPP_URL = "https://web.whatsapp.com";
    private static final By CONVERSATIONS_XPATH = By.xpath("//div[@class='lhggkp7q ln8gz9je rx9719la']");
    private static final By NOT_READ_XPATH = By.xpath("//span[@data-testid='icon-unread-count']");
    private static final By TEXT_AREA_XPATH = By.xpath("//div[@class='to2l77zo gfz4du6o ag5g9lrv bze30y65 kao4egtt']");

    public WebWhatsApp(WebDriver webDriver, int loopTime, String automaticMessage) {
        this.webDriver = webDriver;
        this.time = loopTime;
        this.automaticMessage = automaticMessage;
    }

    public void init() throws InterruptedException {
        this.webDriver.get(WEB_WHATSAPP_URL);
        long i = 1;
        while (true) {
            Thread.sleep(this.time);

            System.out.printf("Execution number: %d%n", i);

            final List<WebElement> elements = webDriver.findElements(CONVERSATIONS_XPATH);
            elements.stream()
                    .filter(webElement -> !webElement.findElements(NOT_READ_XPATH).isEmpty())
                    .forEach( webElement -> this.sendMessage(webElement, automaticMessage));
            i++;
        }
    }

    private void sendMessage(WebElement webElement, String automaticMessage) {
        webElement.click();
        final WebElement element = webDriver.findElement(TEXT_AREA_XPATH);
        element.sendKeys(automaticMessage);
        element.sendKeys(Keys.ENTER);
    }

}
