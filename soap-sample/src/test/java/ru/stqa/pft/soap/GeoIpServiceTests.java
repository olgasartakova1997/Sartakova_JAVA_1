package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp(){
    String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("194.28.29.152");
    assertEquals(geoIp, "<GeoIP><Country>RU</Country><State>47</State></GeoIP>");
  }

  @Test
  public void testInvalidIp(){
    String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("194.28.29.xxx");
    assertEquals(geoIp, "<GeoIP><Country>RU</Country><State>47</State></GeoIP>");
  }
}