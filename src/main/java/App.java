import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("artists", Artist.all());
      model.put("albums", Album.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/artist/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Artist artist = Artist.find(Integer.parseInt(request.params(":id")));
      model.put("artist", artist);
      model.put("albums", Album.all());
      model.put("template", "templates/artist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/album/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Album album = Album.find(Integer.parseInt(request.params(":id")));
      Artist artist = Artist.find(album.getArtistId());
      model.put("album", album);
      model.put("artist", artist);
      model.put("template", "templates/album.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/album/:id/add-review", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Album album = Album.find(Integer.parseInt(request.params(":id")));
      Artist artist = Artist.find(album.getArtistId());
      model.put("album", album);
      model.put("artist", artist);
      model.put("template", "templates/add-review-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/album/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Album album = Album.find(Integer.parseInt(request.params(":id")));
      Artist artist = Artist.find(album.getArtistId());
      Review review = Review.find(review.getAlbumId());
      model.put("album", album);
      model.put("artist", artist);
      model.put("review", review);
      model.put("template", "templates/album.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
    })

  }
}
