package maps;

import org.openqa.selenium.By;

public class ContactUsMap {

    public final By lblGetInTouch =
            By.xpath("//h2[normalize-space()='Get In Touch']");

    public final By textContactName =
            By.xpath("//input[@data-qa='name']");

    public final By textContactEmail =
            By.xpath("//input[@name='email']");

    public final By contactSubject =
            By.xpath("//input[@name='subject']");


    public final By contactMessage =
            By.xpath("//textarea[@id='message']");

    public final By uploadFileForContact =
            By.xpath("//input[@name='upload_file']");

    public final By contactSubmitBtn =
            By.cssSelector("input[data-qa='submit-button']");
    //alerts appears and then:

    public final By messageSentSuccessfully =
            By.xpath("//div[@class='status alert alert-success']");

    public final By btnBackHome =
            By.cssSelector("a[href='/']");

}
