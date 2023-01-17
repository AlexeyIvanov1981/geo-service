import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class TestMessSendWithMockitoSpy {

    final String IP_RUS = "172.";

    @Test
    public void sendTestSpy() {
        GeoServiceImpl geoService = Mockito.spy(GeoServiceImpl.class);
        LocalizationServiceImpl localizationService = Mockito.spy(LocalizationServiceImpl.class);
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> mapRUS = new HashMap<>();
        mapRUS.put(MessageSenderImpl.IP_ADDRESS_HEADER, IP_RUS);

        String result = messageSender.send(mapRUS);
        Assertions.assertEquals("Добро пожаловать", result);
    }
}


