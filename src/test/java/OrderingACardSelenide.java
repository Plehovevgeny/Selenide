import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.files.DownloadActions.click;

public class OrderingACardSelenide {

    @Test
    public void shouldOrderingACardPositive(){
        open("http://localhost:9999/");
        $("[data-test-id = name] input").setValue("Евгений");
        $("[data-test-id = phone] input").setValue("+79221956008");
        $("[data-test-id = agreement]").click();
        $("[type = button]").click();
        $("[data-test-id = order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldOrderingACardWithEnglishWordsInName(){
        open("http://localhost:9999/");
        $("[data-test-id = name] input").setValue("John");
        $("[data-test-id = phone] input").setValue("+79221956008");
        $("[data-test-id = agreement]").click();
        $("[type = button]").click();
        $("[data-test-id = name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldOrderingACardWithNoName(){
        open("http://localhost:9999/");
        $("[data-test-id = name] input").setValue("");
        $("[data-test-id = phone] input").setValue("+79221956008");
        $("[data-test-id = agreement]").click();
        $("[type = button]").click();
        $("[data-test-id = name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldOrderingACardNoPhone(){
        open("http://localhost:9999/");
        $("[data-test-id = name] input").setValue("Евгений");
        $("[data-test-id = phone] input").setValue("");
        $("[data-test-id = agreement]").click();
        $("[type = button]").click();
        $("[data-test-id = phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldOrderingACardBadPhone(){
        open("http://localhost:9999/");
        $("[data-test-id = name] input").setValue("Евгений");
        $("[data-test-id = phone] input").setValue("79221956008");
        $("[data-test-id = agreement]").click();
        $("[type = button]").click();
        $("[data-test-id = phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldOrderingACardNoCheckbox(){
        open("http://localhost:9999/");
        $("[data-test-id = name] input").setValue("Евгений");
        $("[data-test-id = phone] input").setValue("+79221956008");
        $("[type = button]").click();
        $("[data-test-id = agreement].input_invalid").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}