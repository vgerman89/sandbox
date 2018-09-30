package svz.sandbox.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("109.173.111.50");
    System.out.println(ipLocation);
    assertEquals(ipLocation, "<GeoIP><Country>RU</Country><State>48</State></GeoIP>");
  }

  @Test
  public void testInvalidIp() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("109.173.111.xxx");
    System.out.println(ipLocation);
    assertEquals(ipLocation, "<GeoIP><Country>RU</Country><State>48</State></GeoIP>");
  }
}
