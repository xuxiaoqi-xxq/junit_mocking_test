package parking;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {ParkingLot.class})
public class VipParkingStrategyPowerMockTest {

    @Test
    public void testCalculateHourlyPrice_givenSunday_thenPriceIsDoubleOfSundayPrice(){

        /* Exercise 6: Write test case for VipParkingStrategy calculateHourlyPrice
        * by using PowerMock to mock static method */
        PowerMockito.mockStatic(ParkingLot.class);
        when(ParkingLot.getBasicHourlyPrice()).thenReturn(25);
        VipParkingStrategy vipParkingStrategy = new VipParkingStrategy();
        Integer result = vipParkingStrategy.calculateHourlyPrice();
        Assert.assertEquals(Integer.valueOf(50),result);
    }

    @Test
    public void testCalculateHourlyPrice_givenNotSunday_thenPriceIsDoubleOfNonSundayPrice(){

        /* Exercise 6: Write test case for VipParkingStrategy calculateHourlyPrice
         * by using PowerMock to mock static method */
        PowerMockito.mockStatic(ParkingLot.class);
        when(ParkingLot.getBasicHourlyPrice()).thenReturn(20);
        VipParkingStrategy vipParkingStrategy = new VipParkingStrategy();
        Integer result = vipParkingStrategy.calculateHourlyPrice();
        Assert.assertEquals(Integer.valueOf(40),result);

    }
}
