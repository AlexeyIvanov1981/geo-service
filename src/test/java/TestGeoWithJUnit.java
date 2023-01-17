import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

public class TestGeoWithJUnit {
    GeoServiceImpl geoService = new GeoServiceImpl();
    final String IP_RUS = "172.";
    final String IP_ENG = "96.";

    @Test
    void testEngLoc() {
        Location location = new Location("New York", Country.USA, " 10th Avenue", 32);
        Location result = geoService.byIp(IP_ENG);
        Assertions.assertEquals(result.getCountry(), location.getCountry());
    }

    @Test
    void testRusLoc() {
        Location location = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        Location result = geoService.byIp(IP_RUS);
        Assertions.assertEquals(result.getCountry(), location.getCountry());
    }

}
