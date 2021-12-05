
package org.selenide.examples;


import com.codeborne.selenide.Configuration;
import org.junit.Test;
import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.Select;
import com.codeborne.selenide.commands.SelectOptionByValue;

import static com.codeborne.selenide.Selenide.*;


//Tests made using Google Chrome
public class LHVTest {
    @Test
    public void Calculator() {
        //Open calculator, fill values and open "Maksegraafik".
        open("https://www.lhv.ee/et/liising#kalkulaator");

        //If I want to start all my tests at the same time, then Google Chrome tests are made in same
        // instance so I accept cookies one time and other tests wont need to accept cookies anymore
        // so thats why I use if statement to check if cookies are displayed. But I added cookies
        //accepting to every test case, if they are runned separately.
        if ($(By.cssSelector("#acceptPirukas")).isDisplayed()) {
            $(By.cssSelector("#acceptPirukas")).click();
        };

        $(By.id("price")).setValue("15000");
        $(By.id("initial_percentage")).setValue("10");
        $(By.id("interest_rate")).setValue("4");
        $(By.id("reminder_percentage")).setValue("11");

        $(By.linkText("Maksegraafik")).click();

        //Added closeWebDriver to every test case to prevent test failing when multiple tests are started at the same time.
        closeWebDriver();

    }
    @Test
    public void CalculatorWithWrongValues() {
        //open calculator and fill with wrong values (according to conditions) and try to apply for a lease from the bank
        open("https://www.lhv.ee/et/liising#kalkulaator");

        if ($(By.cssSelector("#acceptPirukas")).isDisplayed()) {
            $(By.cssSelector("#acceptPirukas")).click();
        };
        $(By.id("price")).setValue("5000");
        $(By.id("initial_percentage")).setValue("11");
        $(By.id("interest_rate")).setValue("2");
        $(By.id("reminder_percentage")).setValue("1");
        $(By.linkText("Taotle")).click();

        closeWebDriver();
    }

    @Test
    public void LeasingAsPrivateIndividual() throws InterruptedException {
        //open calculator and fill with letters to test
        open("https://www.lhv.ee/et/liising#kalkulaator");

        if ($(By.cssSelector("#acceptPirukas")).isDisplayed()) {
            $(By.cssSelector("#acceptPirukas")).click();
        };

        $(By.id("price")).setValue("12500");
        $(By.id("initial_percentage")).setValue("15");
        $(By.id("interest_rate")).setValue("4");
        $(By.id("reminder_percentage")).setValue("10");
        $(By.linkText("Taotle")).click();

        closeWebDriver();
    }

    @Test
    public void MaxPaymentMinimumNetoAcceptance() {
        //open calculator and fill with letters to test
        open("https://www.lhv.ee/et/liising#max-payment");

        if ($(By.cssSelector("#acceptPirukas")).isDisplayed()) {
            $(By.cssSelector("#acceptPirukas")).click();
        };

        $(By.id("monthly-income")).setValue("799");
        $(By.linkText("Maksimaalse kuumakse arvutamiseks on netosissetulek liiga väike.")).isDisplayed();

        closeWebDriver();
    }

    @Test
    public void MaxNumberAddingToCalculator() {
        open("https://www.lhv.ee/et/liising#kalkulaator");
        if ($(By.cssSelector("#acceptPirukas")).isDisplayed()) {
            $(By.cssSelector("#acceptPirukas")).click();
        };
        //Adding way too big numbers to test if calculator crash or not
        $(By.id("price")).setValue("999999");
        $(By.id("initial_percentage")).setValue("99999");
        $(By.id("interest_rate")).setValue("9999");
        $(By.id("reminder_percentage")).setValue("9999");
        $(By.linkText("Taotle")).click();

        closeWebDriver();
    }

    @Test
    public void AddingTextToCalculatorNumberFields() {
        //open calculator and fill number fields with letters to see if calculator has number value control or not
        open("https://www.lhv.ee/et/liising#kalkulaator");
        if ($(By.cssSelector("#acceptPirukas")).isDisplayed()) {
            $(By.cssSelector("#acceptPirukas")).click();
        };

        $(By.id("price")).setValue("TEST");
        $(By.id("initial_percentage")).setValue("TEST");
        $(By.id("interest_rate")).setValue("TEST");
        $(By.id("reminder_percentage")).setValue("TEST");
        $(By.linkText("Taotle")).click();

        closeWebDriver();
    }
    @Test
    public void NegativeNumbersInCalculator() {
        open("https://www.lhv.ee/et/liising#kalkulaator");

        if ($(By.cssSelector("#acceptPirukas")).isDisplayed()) {
            $(By.cssSelector("#acceptPirukas")).click();
        };
        //Inserting negative numbers to see if calculator accepts them or tells error message
        $(By.id("price")).setValue("-999");
        $(By.id("initial_percentage")).setValue("-999");
        $(By.id("interest_rate")).setValue("-9999");
        $(By.id("reminder_percentage")).setValue("-999");
        $(By.linkText("Taotle")).click();

        closeWebDriver();
    }

    @Test
    public void OpenCalculatorMultipleTimes() {
        // This test will open and close calculator 10 times to see if calculator still opens fine.
        for (int i = 0; i < 10; i++) {
            open("https://www.lhv.ee/et/liising#kalkulaator");
            closeWebDriver();
        }
    }

}
