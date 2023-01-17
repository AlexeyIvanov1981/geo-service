import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class TestMessSendWithMockitoSpy {

    @Test
    public void sendTestSpy() {
        final String IP_RUS = "172.";

        GeoServiceImpl geoService = Mockito.spy(GeoServiceImpl.class);
        LocalizationServiceImpl localizationService = Mockito.spy(LocalizationServiceImpl.class);
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);


        Map<String, String> mapRUS = new HashMap<>();
        mapRUS.put(MessageSenderImpl.IP_ADDRESS_HEADER, IP_RUS);

        String rusText = messageSender.send(mapRUS);
        Assertions.assertEquals("Добро пожаловать", rusText);
    }
}


