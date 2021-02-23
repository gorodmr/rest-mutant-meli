package co.com.mercado.libre.quasar.util;

import co.com.mercado.libre.quasar.exception.UndeterminedLocationException;
import co.com.mercado.libre.quasar.exception.QuasarOperationErrors;
import co.com.mercado.libre.quasar.model.Position;
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import lombok.AllArgsConstructor;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;

/**
 * @author Diego Muñoz
 */
@AllArgsConstructor
public class MultiPointTrilaterationUtil {

	private double[][] satellitePositions;

	/**
	 * Determines the ship location using the trilateration method
	 * 
	 * @param distances Array with distances between satellites and ship
	 * @return double array with positions x,y
	 */
	public Position getLocation(double[] distances) {
		if(satellitePositions.length != distances.length) 
			throw new UndeterminedLocationException(QuasarOperationErrors.DISTANCE_NOT_FOUND);
		NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
				new TrilaterationFunction(satellitePositions, distances), new LevenbergMarquardtOptimizer());
		LeastSquaresOptimizer.Optimum optimum = solver.solve();

		double[] location = optimum.getPoint().toArray();
		return new Position(location[0], location[1]);
	}
}
