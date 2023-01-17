import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class TestLocalisationWithJUnit {
    @Test
    void test_locale_RUS() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String result = localizationService.locale(Country.RUSSIA);
        Assertions.assertEquals("Добро пожаловать", result);
    }

    @Test
    void test_locale_ENG() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String result = localizationService.locale(Country.USA);
        Assertions.assertEquals("welcome", result);
    }
}
