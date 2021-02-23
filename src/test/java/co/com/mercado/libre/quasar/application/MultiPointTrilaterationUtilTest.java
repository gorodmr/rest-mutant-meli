package co.com.mercado.libre.quasar.application;

import co.com.mercado.libre.quasar.model.Position;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.mercado.libre.quasar.exception.UndeterminedLocationException;
import co.com.mercado.libre.quasar.util.MultiPointTrilaterationUtil;

@RunWith(SpringRunner.class)
public class MultiPointTrilaterationUtilTest {
	
	private MultiPointTrilaterationUtil multiPointTrilaterationUtil;
	private double[][] satellitePositions;
	
	
	@Before
	public void setup() {
		satellitePositions = new double[][] {{-500, -200}, {100, -100}, {500, 100}};
		multiPointTrilaterationUtil = new MultiPointTrilaterationUtil(satellitePositions);
	}
	
	@Test
	public void givenShipDistances_whenDistancesAreOk_thenReturnShipCoordinates() {
		double[] distances = new double[] {80, 100, 90};
		Position location = multiPointTrilaterationUtil.getLocation(distances);
		Assert.assertEquals(-19.34738427993777, location.getX(), 0.0);
		Assert.assertEquals(-56.46456225968159, location.getY(), 0.0);
	}
	
	@Test(expected = UndeterminedLocationException.class)
	public void givenShipDistances_whenDistancesAreInsufficient_thenReturnException() {
		double[] distances = new double[] {80, 100};
		multiPointTrilaterationUtil.getLocation(distances);
	}
}
