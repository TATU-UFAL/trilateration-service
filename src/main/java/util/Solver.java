package util;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import vo.Beacon;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import vo.Trilateration;

import java.awt.geom.Point2D;

public class Solver {

    public static Point2D.Double linearTrilateration(Trilateration trilateration) {
        return linearTrilateration(trilateration.getBeaconA(), trilateration.getBeaconB(),
                trilateration.getBeaconC(), trilateration.getDistanceFromA(), trilateration.getDistanceFromB(),
                trilateration.getDistanceFromC());
    }

    public static Point2D.Double nonLinearTrilateration(Trilateration trilateration) {
        return nonLinearTrilateration(trilateration.getBeaconA(), trilateration.getBeaconB(),
                trilateration.getBeaconC(), trilateration.getDistanceFromA(), trilateration.getDistanceFromB(),
                trilateration.getDistanceFromC());
    }

    public static Point2D.Double nonLinearTrilateration(Beacon a, Beacon b, Beacon c, Double distanceFromA, Double distanceFromB, Double distanceFromC){
        double[][] positions = new double[][] { { a.getX(), b.getX() }, { b.getX(), b.getY() }, { c.getX(), c.getY() } };
        double[] distances = new double[] { distanceFromA, distanceFromB, distanceFromC };

        NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(new TrilaterationFunction(positions, distances), new LevenbergMarquardtOptimizer());
        LeastSquaresOptimizer.Optimum optimum = solver.solve();

        double[] centroid = optimum.getPoint().toArray();

        return new Point2D.Double(centroid[0], centroid[1]);
    }

    //from stackoverflow: https://stackoverflow.com/questions/20332856/triangulate-example-for-ibeacons
    public static Point2D.Double linearTrilateration(Beacon a, Beacon b, Beacon c, Double dA, Double dB, Double dC){
        Double W, Z, x, y, y2;
        W = dA*dA - dB*dB - a.getX()*a.getX() - a.getY()*a.getY() + b.getX()*b.getX() + b.getY()*b.getY();
        Z = dB*dB - dC*dC - b.getX()*b.getX() - b.getY()*b.getY() + c.getX()*c.getX() + c.getY()*c.getY();

        x = (W*(c.getY()-b.getY()) - Z*(b.getY()-a.getY())) / (2 * ((b.getX()-a.getX())*(c.getY()-b.getY()) - (c.getX()-b.getX())*(b.getY()-a.getY())));
        y = (W - 2* x *(b.getX()-a.getX())) / (2*(b.getY()-a.getY()));
        //y2 is a second measure of y to mitigate errors
        y2 = (Z - 2*x*(c.getX()-b.getX())) / (2*(c.getY()-b.getY()));

        y = (y + y2) / 2;

        return new Point2D.Double(x, y);
    }

}
