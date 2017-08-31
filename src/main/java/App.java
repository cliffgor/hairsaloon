import java.util.ArrayList;
import java.util.Map;

import java.util.HashMap;

import spark.ModelAndView;

import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;


public class App{

    private static ArrayList<Hair> hair;

	public static void main(String[] args){

        staticFileLocation("/public");

        String layout ="templates/layout.vtl";

        // a route that renders the home page

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("hairs", request.session().attribute("hairs"));
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());



        post("/hairs", (request, response) -> {
            
                Map<String, Object> model = new HashMap<String, Object>();
            
                ArrayList<Hair> hairs = request.session().attribute("hair");
            
                if (hairs == null) {
            
                  hairs = new ArrayList<Hair>();
            
                  request.session().attribute("hairs", hairs);
            
                }
            
    
                String description = request.queryParams("description");
            
                Hair newHair = new Hair(description);
            
                hair.add(newHair);
            
                model.put("template", "templates/success.vtl");
            
                return new ModelAndView(model, layout);
            
               }, new VelocityTemplateEngine());

    }
}
