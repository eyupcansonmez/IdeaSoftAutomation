package com.ideasoft.page;

import com.ideasoft.base.BaseTest;
import com.thoughtworks.gauge.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;


public class FrontendSteps extends BaseTest {


    public FrontendSteps() throws IOException {

    }


    WebElement findElement(String key) {

        By by = getElementInfoBy(findElementInfoByKey(key));
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})", element);
        return element;
    }


    private void clickTo(WebElement element) {
        element.click();
    }


    public void javaScriptClickTo(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);

    }

    @Step("<key> li input alanını bul, <text> değerini yaz ve ardından Enter'a bas")
    public void findInputElementAndSendKeysThenPressEnter(String key, String text) {
        WebElement element = findElementWithWait(key);
        //element.clear();
        element.sendKeys(text);
        element.sendKeys(Keys.ENTER);
        logger.info(key + " li input alanı bulundu, " + text + " değeri yazıldı ve Enter tuşuna basıldı.");
    }


    @Step("<key> li input alanını bul ve <text> değerini yaz")
    public void findInputElementAndSendKeys(String key, String text) {
        WebElement element = findElementWithWait(key);
        element.clear();
        element.sendKeys(text);
        logger.info(key + " li input alanı bulundu ve " + text + " değeri yazıldı.");
    }

    @Step("Elementine tıkla <key>")
    public void clickElement(String key) {
        WebElement element = findElementWithWait(key);
        clickTo(element);
        logger.info(key + " elementine tıklandı.");
    }

    public WebElement findElementWithWait(String key) {
        WebDriverWait wait = new WebDriverWait(driver, 20); // Bekleme süresini örneğin 30 saniyeye çıkarın.
        By by = getElementInfoBy(findElementInfoByKey(key));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by)); // Elementin mevcut olmasını bekleyin.
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

    @Step("<int> saniye bekle")
    public void waitSecond(int seconds) throws InterruptedException {
        try {
            logger.info(seconds + " saniye bekleniyor");
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("<key> elementinin gorunur olduğunu kotrol et")
    public void checkDisabled(String key) {
        WebElement element = findElement(key);
        Assertions.assertTrue(element.isDisplayed(), " Element disabled değil");
        logger.info(key + " elementi disabled");
    }


    @Step("<key> elementi <expectedText> değerini içeriyor mu kontrol et")
    public void checkElementEqualsText(String key, String expectedText) {

        String actualText = findElement(key).getText();
        logger.info("Element str:" + actualText);
        logger.info("Expected str:" + expectedText);
        Assertions.assertEquals(actualText, expectedText, "Beklenen metni içermiyor " + key);
        logger.info(key + " elementi " + expectedText + " degerine eşittir.");
    }

    @Step("<key> elementinin metnini kontrol et <expectedText>")
    public void checkElementText(String key, String expectedText) {
        WebElement element = findElement(key);
        String actualText = element.getText();
        Assertions.assertEquals(expectedText, actualText, key + " elementinin metni beklenen ile uyuşmuyor.");
        logger.info(key + " elementinin metni beklenen ile uyuşuyor: " + expectedText);
    }

    @Step("JavaScript ile <key> elementine tıkla")
    public void clickElementWithJavaScript(String key) {
        WebElement element = findElementWithWait(key);
        javaScriptClickTo(element);
        logger.info("JavaScript ile " + key + " elementine tıklandı.");
    }


    @Step("<key> li input alanını bul ve value değerinin <expectedValue> olduğunu kontrol et")
    public void verifyInputValue(String key, String expectedValue) {
        WebDriverWait wait = new WebDriverWait(driver, 10); // 10 saniye bekleme süresi
        By by = getElementInfoBy(findElementInfoByKey(key));
        WebElement inputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        String actualValue = inputElement.getAttribute("value");
        Assertions.assertEquals(expectedValue, actualValue, "Beklenen değerle uyuşmuyor: " + expectedValue);
        System.out.println("Input öğesinin value değeri doğru: " + actualValue);
    }
}



