import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.files.DownloadActions.click;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderingACardSelenide {

    @Test
    public void shouldOrderingACard(){
        open("http://localhost:9999");
        SelenideElement form = $("[data-test-id=callback-form]");
        form.$("[data-test-id = name] input").setValue("Евгений");
        form.$("[data-test-id = phone] input").setValue("+79221956008");
        form.$("[data-test-id = agreement]").click();
        form.$("[type = button]").click();
        $("[.order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время"));
    }
}
