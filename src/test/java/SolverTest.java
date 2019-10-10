import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Solver;
import vo.Beacon;
import vo.Trilateration;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    private Trilateration trilateration;

    @BeforeEach
    void setUp(){
        this.trilateration = new Trilateration(new Beacon(381, 42), new Beacon(32, 309),
                new Beacon(18, 48),1.93, 1.29, 0.28);
    }

    @Test
    void testLinearTrilaterationShouldReturnXAndY(){
        assertEquals(Solver.linearTrilateration(trilateration), new Point2D.Double(201.545012229112, 169.02711486127373));
    }

    @Test
    void testNonTrilaterationShouldReturnXAndY(){
        assertEquals(Solver.nonLinearTrilateration(trilateration), new Point2D.Double(84.4796978667526, 97.79328690944635));
    }


}
