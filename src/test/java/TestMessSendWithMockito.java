import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class TestMessSendWithMockito {

    Location location;
    GeoServiceImpl geoService;
    LocalizationServiceImpl localizationService;
    MessageSenderImpl messageSender;
    final String IP = "144.";
    final String IP_RUS = "172.";
    final String IP_ENG = "96.";

    @BeforeEach
    void init() {
        geoService = Mockito.mock(GeoServiceImpl.class);
        localizationService = Mockito.mock(LocalizationServiceImpl.class);
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @Test
    public void sendMessage_null() {
        HashMap map = new HashMap<>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, IP);
        Mockito.when(geoService.byIp(IP)).thenReturn(new Location(null, null, null, 0));
        Mockito.when(localizationService.locale(null)).thenReturn(null);

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        String result = messageSender.send(map);
        Assertions.assertEquals(null, result);
    }

    @Test
    public void sendMessage_RUS() {
        Map<String, String> map = new HashMap<>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, IP_RUS);

        Mockito.when(geoService.byIp(IP_RUS))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        String result = messageSender.send(map);
        Assertions.assertEquals("Добро пожаловать", result);
    }

    @Test
    public void sendMessage_USA() {
        Map<String, String> map = new HashMap<>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, IP_ENG);

        Mockito.when(geoService.byIp(IP_ENG)).
                thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        Mockito.when(localizationService.locale(Country.USA)).
                thenReturn("welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        String result = messageSender.send(map);
        Assertions.assertEquals("welcome", result);
    }
}


