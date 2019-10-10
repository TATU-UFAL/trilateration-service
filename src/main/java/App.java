import com.google.gson.Gson;
import spark.Filter;
import util.Solver;
import vo.Trilateration;

import java.awt.*;
import java.awt.geom.Point2D;
import java.nio.charset.Charset;

import static spark.Spark.*;

public class App {

    public static void main(String args[]){
        port(4000);

        after((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET");
        });

        post("/trilateration", (req, res) -> {
            Trilateration trilateration = new Gson().fromJson(req.body(), Trilateration.class);

            Point2D.Double point = Solver.nonLinearTrilateration(trilateration);

            return new Gson().toJson(point);
        });
    }

}
