package org;

import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Random;

public class StepImp extends BaseClass {

    public Actions action = new Actions(webDriver);
    static String gelenText;

    @Step("<url> sitesi acilir.")
    public void UrlGo(String url) {

        webDriver.get(url);

    }

    @Step("<int> saniye bekle")
    public void WaitSecond(int second) {

        Methods.WaitBySecond(second);

    }

    @Step("<xpath> Xpath'li Elementin textini al.")
    public void XpathElementGetText(String xpath){

        gelenText = webDriver.findElement(By.xpath(xpath)).getText();
        System.out.println("Gelen text => " + gelenText);
    }

    @Step("<xpath> Xpath'li elementin texti ile gelenText'i karsilastir.")
    public void XpathTextKarsilastir(String xpath) {
        System.out.println("Son Text =>" + webDriver.findElement(By.xpath(xpath)).getText());
        Assert.assertEquals("Değerler Eşleşmedi!!", webDriver.findElement(By.xpath(xpath)).getText(), gelenText);
    }

    @Step("<id> Id'li elemente <text> i yazdir")
    public void IdSendKey(String id,String text){
        WebElement element = webDriver.findElement(By.id(id));
        element.sendKeys(text);
    }
    @Step("<xpath> Xpath'li elemente <text> i yazdir")
    public void XpathSendKey(String xpath,String text){
        WebElement element = webDriver.findElement(By.xpath(xpath));
        element.sendKeys(text);
    }

    @Step("<xpath> Xpath'li elemente tıkla.")
    public void ButtonXpathClick(String xpath) throws InterruptedException {

        Methods.ButtonClickXpath(xpath);
    }

    @Step("<id> Id'li elemente tıkla.")
    public void ButtonIdClick(String id) throws InterruptedException {
        Methods.ButtonClickId(id);
    }

    @Step("<xpath> li element var mı ? <text>")
    public void Control(String xpath,String text){

        WebElement element = webDriver.findElement(By.xpath(xpath));

        Assert.assertEquals("Giris Yapılamadı!!",element.getText(),text);
    }

    @Step("<xpath> li elementlerden random urun sec ve tikla")
    public void RndClick(String xpath) {

        List<WebElement> elementList = webDriver.findElements(By.xpath(xpath));

        int size = elementList.size();
        Random rnd = new Random();
        int randomUrun = rnd.nextInt(size);

        WebElement selectElement = webDriver.findElement(By.xpath("//ul[@class='catalog-view clearfix products-container']/li["+randomUrun+"]"));

        action.moveToElement(selectElement).build().perform();
        selectElement.click();
    }

    @Step("<xpath> Xpath'li Elemente git")
    public void ElementHover(String xpath){
        Actions action = new Actions(webDriver);
        WebElement we = webDriver.findElement(By.xpath(xpath));
        action.moveToElement(we).build().perform();
    }

}
