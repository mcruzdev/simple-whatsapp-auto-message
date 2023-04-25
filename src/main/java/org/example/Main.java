package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        int tenSeconds = 10000;
        final WebWhatsApp webWhatsApp = new WebWhatsApp(webDriver, tenSeconds, "Olá, boa semana");
        webWhatsApp.init();
    }
}