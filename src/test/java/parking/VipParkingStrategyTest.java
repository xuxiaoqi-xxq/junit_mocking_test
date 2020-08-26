package parking;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class VipParkingStrategyTest {

	@Test
    public void testPark_givenAVipCarAndAFullParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() throws Exception {

	    /* Exercise 4, Write a test case on VipParkingStrategy.park()
	    * With using Mockito spy, verify and doReturn */
        ParkingLot parkingLotMock = Mockito.mock(ParkingLot.class);
        Car carMock = Mockito.mock(Car.class);
        when(parkingLotMock.getName()).thenReturn("parkingLot name");
        when(parkingLotMock.isFull()).thenReturn(true);
        when(parkingLotMock.getParkedCars()).thenReturn(new ArrayList<Car>());
        when(carMock.getName()).thenReturn("A vip car");
        VipParkingStrategy vipParkingStrategy = Mockito.spy(new VipParkingStrategy());
        doReturn(true).when(vipParkingStrategy).isAllowOverPark(any());
        Receipt receipt = vipParkingStrategy.park(Collections.singletonList(parkingLotMock), carMock);
        Mockito.verify(vipParkingStrategy).createReceipt(any(ParkingLot.class),any(Car.class));
        assertNotNull(receipt);
    }

    @Test
    public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() {

        /* Exercise 4, Write a test case on VipParkingStrategy.park()
         * With using Mockito spy, verify and doReturn */
        ParkingLot parkingLotMock = Mockito.mock(ParkingLot.class);
        Car carMock = Mockito.mock(Car.class);
        when(parkingLotMock.getName()).thenReturn("parkingLot name");
        when(parkingLotMock.isFull()).thenReturn(true);
        when(parkingLotMock.getParkedCars()).thenReturn(new ArrayList<Car>());
        when(carMock.getName()).thenReturn("vip car");
        VipParkingStrategy vipParkingStrategy = Mockito.spy(new VipParkingStrategy());
        doReturn(false).when(vipParkingStrategy).isAllowOverPark(any());
        Receipt receipt = vipParkingStrategy.park(Collections.singletonList(parkingLotMock), carMock);
        Mockito.verify(vipParkingStrategy).createNoSpaceReceipt(any(Car.class));
        assertNotNull(receipt);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        ParkingLot parkingLotMock = Mockito.mock(ParkingLot.class);
        when(parkingLotMock.getName()).thenReturn("parkingLot name");
        when(parkingLotMock.isFull()).thenReturn(true);
        when(parkingLotMock.getParkedCars()).thenReturn(new ArrayList<Car>());
        Car carMock = Mockito.mock(Car.class);
        when(carMock.getName()).thenReturn("vip car A");
        CarDao carDao = mock(CarDao.class);
        when(carDao.isVip(any())).thenReturn(true);
        VipParkingStrategy vipParkingStrategy = new VipParkingStrategy(carDao);
        boolean result = vipParkingStrategy.isAllowOverPark(carMock);
        assertTrue(result);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        ParkingLot parkingLotMock = Mockito.mock(ParkingLot.class);
        when(parkingLotMock.getName()).thenReturn("parkingLot name");
        when(parkingLotMock.isFull()).thenReturn(true);
        when(parkingLotMock.getParkedCars()).thenReturn(new ArrayList<Car>());
        Car carMock = Mockito.mock(Car.class);
        when(carMock.getName()).thenReturn("vip car");
        CarDao carDao = mock(CarDao.class);
        when(carDao.isVip(any())).thenReturn(true);
        VipParkingStrategy vipParkingStrategy = new VipParkingStrategy(carDao);
        boolean result = vipParkingStrategy.isAllowOverPark(carMock);
        assertFalse(result);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse(){
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        ParkingLot parkingLotMock = Mockito.mock(ParkingLot.class);
        when(parkingLotMock.getName()).thenReturn("parkingLot name");
        when(parkingLotMock.isFull()).thenReturn(true);
        when(parkingLotMock.getParkedCars()).thenReturn(new ArrayList<Car>());
        Car carMock = Mockito.mock(Car.class);
        when(carMock.getName()).thenReturn("vip car A");
        CarDao carDao = mock(CarDao.class);
        when(carDao.isVip(any())).thenReturn(false);
        VipParkingStrategy vipParkingStrategy = new VipParkingStrategy(carDao);
        boolean result = vipParkingStrategy.isAllowOverPark(carMock);
        assertFalse(result);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        ParkingLot parkingLotMock = Mockito.mock(ParkingLot.class);
        when(parkingLotMock.getName()).thenReturn("parkingLot name");
        when(parkingLotMock.isFull()).thenReturn(true);
        when(parkingLotMock.getParkedCars()).thenReturn(new ArrayList<Car>());
        Car carMock = Mockito.mock(Car.class);
        when(carMock.getName()).thenReturn("vip car");
        CarDao carDao = mock(CarDao.class);
        when(carDao.isVip(any())).thenReturn(false);
        VipParkingStrategy vipParkingStrategy = new VipParkingStrategy(carDao);
        boolean result = vipParkingStrategy.isAllowOverPark(carMock);
        assertFalse(result);
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
