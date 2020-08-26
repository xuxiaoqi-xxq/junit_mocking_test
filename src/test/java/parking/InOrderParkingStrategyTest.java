package parking;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class InOrderParkingStrategyTest {

	@Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
	    * With using Mockito to mock the input parameter */

        ParkingLot parkingLotMock = Mockito.mock(ParkingLot.class);
        Car carMock = Mockito.mock(Car.class);
        when(parkingLotMock.getName()).thenReturn("parkingLot name");
        when(carMock.getName()).thenReturn("car name");
        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        Receipt receipt = inOrderParkingStrategy.createReceipt(parkingLotMock, carMock);
        assertNotNull(receipt);
    }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */
        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        Car carMock = Mockito.mock(Car.class);
        when(carMock.getName()).thenReturn("car name");
        Receipt receipt = inOrderParkingStrategy.createNoSpaceReceipt(carMock);
        assertNotNull(receipt);
    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt(){

	    /* Exercise 2: Test park() method.
	    Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */
        Car carMock = Mockito.mock(Car.class);
        when(carMock.getName()).thenReturn("car name");
        InOrderParkingStrategy inOrderParkingStrategy = Mockito.spy(new InOrderParkingStrategy());
        Receipt receipt = inOrderParkingStrategy.park(Collections.emptyList(), carMock);
        Mockito.verify(inOrderParkingStrategy).createNoSpaceReceipt(any());
        assertNotNull(receipt);
    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt(){

        /* Exercise 2: Test park() method.
        Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */
        ParkingLot parkingLotMock = Mockito.mock(ParkingLot.class);
        Car carMock = Mockito.mock(Car.class);
        when(parkingLotMock.getName()).thenReturn("parkingLot name");
        when(parkingLotMock.isFull()).thenReturn(false);
        when(parkingLotMock.getParkedCars()).thenReturn(new ArrayList<Car>());
        when(carMock.getName()).thenReturn("car name");
        InOrderParkingStrategy inOrderParkingStrategy = Mockito.spy(new InOrderParkingStrategy());
        Receipt receipt = inOrderParkingStrategy.park(Collections.singletonList(parkingLotMock), carMock);
        Mockito.verify(inOrderParkingStrategy).createReceipt(any(),any());
        assertNotNull(receipt);
    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt(){

        /* Exercise 2: Test park() method.
        Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */
        ParkingLot parkingLotMock = Mockito.mock(ParkingLot.class);
        Car carMock = Mockito.mock(Car.class);
        when(parkingLotMock.getName()).thenReturn("parkingLot name");
        when(parkingLotMock.isFull()).thenReturn(true);
        when(parkingLotMock.getParkedCars()).thenReturn(new ArrayList<Car>());
        when(carMock.getName()).thenReturn("car name");
        InOrderParkingStrategy inOrderParkingStrategy = Mockito.spy(new InOrderParkingStrategy());
        Receipt receipt = inOrderParkingStrategy.park(Collections.singletonList(parkingLotMock), carMock);
        Mockito.verify(inOrderParkingStrategy).createNoSpaceReceipt(any());
        assertNotNull(receipt);
    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot(){

        /* Exercise 3: Test park() method.
         Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */
        ParkingLot parkingLotMock1 = Mockito.mock(ParkingLot.class);
        ParkingLot parkingLotMock2 = Mockito.mock(ParkingLot.class);
        Car carMock = Mockito.mock(Car.class);
        when(parkingLotMock1.getName()).thenReturn("parkingLot name");
        when(parkingLotMock1.isFull()).thenReturn(true);
        when(parkingLotMock1.getParkedCars()).thenReturn(new ArrayList<Car>());
        when(parkingLotMock2.getName()).thenReturn("parkingLot name");
        when(parkingLotMock2.isFull()).thenReturn(false);
        when(parkingLotMock2.getParkedCars()).thenReturn(new ArrayList<Car>());
        when(carMock.getName()).thenReturn("car name");
        InOrderParkingStrategy inOrderParkingStrategy = Mockito.spy(new InOrderParkingStrategy());
        Receipt receipt = inOrderParkingStrategy.park(Arrays.asList(parkingLotMock1, parkingLotMock2), carMock);
        Mockito.verify(inOrderParkingStrategy).createReceipt(any(), any());
        Mockito.verify(inOrderParkingStrategy, times(0)).createNoSpaceReceipt(any());
        assertNotNull(receipt);
    }


}
