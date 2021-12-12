package org.selenide.examples;

import com.codeborne.selenide.Configuration;
import org.junit.Test;
import org.openqa.selenium.By;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.Select;
import com.codeborne.selenide.commands.SelectOptionByValue;

import static com.codeborne.selenide.Selenide.*;

//Tests made in Safari
public class LHVTestInSafari {
    private static final String URL= "https://www.lhv.ee/et/liising#kalkulaator";

    @Test
    public void Calculator() {
        Configuration.browser = "safari";
        //Open calculator, fill values and open "Maksegraafik".
        open(URL);

        //If I want to start all my tests at the same time, then safari tests are made in same
        // instance so I accept cookies one time and other tests wont need to accept cookies anymore
        // so thats why I use if statement to check if cookies are displayed. But I added cookies
        //accepting to every test case, if they are runned separately.

        if ($("#acceptPirukas").isDisplayed()) {
            $("#acceptPirukas").click();
        }

        $("#price").val("15000");
        $("#initial_percentage").val("10");
        $("#interest_rate").val("4");
        $("#reminder_percentage").val("11");

        $(By.linkText("Maksegraafik")).click();

        //Added closeWebDriver to every test case to prevent test failing when multiple tests are started at the same time.
        closeWebDriver();

    }
    @Test
    public void CalculatorWithWrongValues() {
        Configuration.browser = "safari";
        //open calculator and fill with wrong values and try to apply for a lease from the bank
        open(URL);

        if ($(By.cssSelector("#acceptPirukas")).isDisplayed()) {
            $(By.cssSelector("#acceptPirukas")).click();
        }
        $(By.id("price")).val("5000");
        $(By.id("initial_percentage")).val("11");
        $(By.id("interest_rate")).val("2");
        $(By.id("reminder_percentage")).val("1");
        $(By.linkText("Taotle")).click();

        closeWebDriver();

    }

    @Test
    public void LeasingAsPrivateIndividual() {
        Configuration.browser = "safari";
        //open calculator and fill with letters to test
        open(URL);

        if ($(By.cssSelector("#acceptPirukas")).isDisplayed()) {
            $(By.cssSelector("#acceptPirukas")).click();
        }
        //By default private individual and capital lease is selected

        $(By.id("price")).val("12500");
        $(By.id("initial_percentage")).val("15");
        $(By.id("interest_rate")).val("4");
        $(By.id("reminder_percentage")).val("10");
        $(By.linkText("Taotle")).click();

        closeWebDriver();
    }

    @Test
    public void MaxPaymentMinimumNetoAcceptance() {
        Configuration.browser = "safari";
        open(URL);
        if ($(By.cssSelector("#acceptPirukas")).isDisplayed()) {
            $(By.cssSelector("#acceptPirukas")).click();
        }

        $(By.id("monthly-income")).val("799");
        $(By.linkText("Maksimaalse kuumakse arvutamiseks on netosissetulek liiga väike.")).isDisplayed();

        closeWebDriver();
    }

    @Test
    public void MaxNumberAddingToCalculator() {
        Configuration.browser = "safari";
        open(URL);
        if ($(By.cssSelector("#acceptPirukas")).isDisplayed()) {
            $(By.cssSelector("#acceptPirukas")).click();
        }

        $(By.id("price")).val("999999");
        $(By.id("initial_percentage")).val("99999");
        $(By.id("interest_rate")).val("9999");
        $(By.id("reminder_percentage")).val("9999");
        $(By.linkText("Taotle")).click();

        closeWebDriver();
    }

    @Test
    public void AddingTextToCalculatorNumberFields() {
        Configuration.browser = "safari";
        open(URL);
        if ($(By.cssSelector("#acceptPirukas")).isDisplayed()) {
            $(By.cssSelector("#acceptPirukas")).click();
        }

        $(By.id("price")).val("TEST");
        $(By.id("initial_percentage")).val("TEST");
        $(By.id("interest_rate")).val("TEST");
        $(By.id("reminder_percentage")).val("TEST");
        $(By.linkText("Taotle")).click();

        closeWebDriver();
    }

    @Test
    public void NegativeNumbersInCalculator() {
        Configuration.browser = "safari";
        open(URL);
        if ($(By.cssSelector("#acceptPirukas")).isDisplayed()) {
            $(By.cssSelector("#acceptPirukas")).click();
        }
        //Inserting negative numbers to see if calculator accepts them or tells error message
        $(By.id("price")).val("-999");
        $(By.id("initial_percentage")).val("-999");
        $(By.id("interest_rate")).val("-9999");
        $(By.id("reminder_percentage")).val("-999");
        $(By.linkText("Taotle")).click();

        closeWebDriver();
    }

    @Test
    public void OpenCalculatorMultipleTimes() {
        Configuration.browser = "safari";
        open(URL);
        // This test will open and close calculator 10 times to see if calculator still opens fine.
        for (int i = 0; i < 10; i++) {
            open("https://www.lhv.ee/et/liising#kalkulaator");
            closeWebDriver();
        }
    }
}
