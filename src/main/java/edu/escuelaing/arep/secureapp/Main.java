package edu.escuelaing.arep.secureapp;

import edu.escuelaing.arep.secureapp.components.persistence.ImplPersistence;
import edu.escuelaing.arep.secureapp.components.services.ImplServices;
import org.json.JSONObject;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        port(433);
        ImplServices implServices = new ImplServices();
        secure("ecikeystore.p12","prueba","myTrustStore","prueba");
        implServices.ssl();
        get("/",(req,res)->{
            return inputDataPage();
        });
        post("/login",(req,res)->{
            String[] body = req.body().split("&");
            String username = body[0].split("=")[1];
            String password = body[1].split("=")[1];
            ImplPersistence implPersistence = new ImplPersistence();
            if(implPersistence.login(username,password)){
                req.session(true);
                req.session().attribute("logged",true);
            } else {
                halt(403,loginFailed());
            }


            return trigoForm();
        });
        post("/results",(req,res)->{
            String[] body = req.body().split("&");
            double value = Double.parseDouble(body[0].split("=")[1]);
            String operation = body[1].split("=")[1];

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("operation",operation);
            try {
                jsonObject.put("value", implServices.makeOperation(operation, value));
            } catch (Exception e){
                e.printStackTrace();
            }

            return outputDataPage(jsonObject);
        });
        get("/calculator",((req, res) -> {
            try {
                if (!Boolean.parseBoolean(req.session().attribute("logged").toString())) {
                    halt(403, loginFailed());
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            return trigoForm();
        }));
    }

    /**
     * This function returns the HTML structure to present the input data
     * @return A String that have a template of a HTML
     */
    private static String inputDataPage() {
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<head><meta charset=\"ISO-8859-1\"> </head>"
                + "<body>"
                + "<h2>Login</h2>"
                + "<form action=\"/login\" method=\"POST\">"
                + "  Username:<br/>"
                + "  <input type=\"text\" name=\"username\" value=\"\" required>"
                + "  <br/>Password:<br/>"
                + "  <input type=\"password\" name=\"password\" value=\"\" required>"
                + "<br/>"
                + "<input type=\"submit\" value=\"Submit\">"
                + "</form>"
                + "</body>"
                + "</html>";


        return pageContent;
    }

    /**
     * This function returns the HTML structure to present the input data
     * @return A String that have a template of a HTML
     */
    private static String trigoForm() {
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<body>"
                + "<h2>Trigonometry Calculator</h2>"
                + "<form action=\"/results\" method=\"POST\">"
                + "  Number:<br>"
                + "  <input type=\"text\" name=\"number\" value=\"\" required>"
                + "  <br><br>"
                + "<label for=\"method\">Choose a Operation:</label>"
                + "<select name=\"operation\" id=\"operation\" required>"
                + "<option value=\"sin\">Sin</option>"
                + "<option value=\"cos\">Cos</option>"
                + "<option value=\"tan\">Tan</option>"
                + "</select>"
                + "<br/>"
                + "<input type=\"submit\" value=\"Submit\">"
                + "</form>"
                + "</body>"
                + "</html>";
        return pageContent;
    }

    /**
     * This function returns the HTML structure to present the input data
     * @return A String that have a template of a HTML
     */
    private static String loginFailed() {
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<head><meta charset=\"ISO-8859-1\"> </head>"
                + "<body>"
                + "<h2>Login</h2>"
                + "<h3>The username or password is incorrect</h3>"
                + "<form action=\"/login\" method=\"POST\">"
                + "  Username:<br/>"
                + "  <input type=\"text\" name=\"username\" value=\"\" required>"
                + "  <br/>Password:<br/>"
                + "  <input type=\"password\" name=\"password\" value=\"\" required>"
                + "<br/>"
                + "<input type=\"submit\" value=\"Submit\">"
                + "</form>"
                + "</body>"
                + "</html>";


        return pageContent;
    }

    /**
     * This function returns the HTML structure to present the output data
     * @param json A JSON with the structure operation and number
     * @return A String that have a template of a HTML
     */
    private static String outputDataPage(JSONObject json){
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<body>"
                + "<h2>Trigonometry Calculator</h2>"
                + "<h4>Operation</h4>"
                + json.get("operation")
                + "<h4>Value</h4>"
                + json.get("value")
                + "<br/>"
                + "<button onclick=\"window.location.href='/calculator'\">New Calculation</button>"
                + "</body>"
                + "</html>";
        return pageContent;
    }
}
