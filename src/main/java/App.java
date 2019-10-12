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

        options("/*", (req, res) -> {
            String accessControlRequestHeaders = req.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                res.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = req.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                res.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((req, res) -> {
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Allow-Headers", "*");
            res.type("application/json");
        });

        post("/trilateration", (req, res) -> {
            Trilateration trilateration = new Gson().fromJson(req.body(), Trilateration.class);

            Point2D.Double point = Solver.nonLinearTrilateration(trilateration);

            return new Gson().toJson(point);
        });
    }

}
