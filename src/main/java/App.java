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

    get("artist/:artistId/album/:albumId", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Album album = Album.find(Integer.parseInt(request.params(":albumId")));
      Artist artist = Artist.find(album.getArtistId());
      model.put("album", album);
      model.put("artist", artist);
      model.put("template", "templates/album.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("artist/:artistId/album/:albumId/add-review", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Album album = Album.find(Integer.parseInt(request.params(":albumId")));
      Artist artist = Artist.find(album.getArtistId());
      model.put("album", album);
      model.put("artist", artist);
      model.put("template", "templates/add-review-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("artist/:artistId/album/:albumId", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Album album = Album.find(Integer.parseInt(request.params(":albumId")));
      Artist artist = Artist.find(album.getArtistId());
      String reviewName = request.queryParams("name");
      String reviewComment = request.queryParams("comment");
      int reviewRating = Integer.parseInt(request.queryParams("rating"));
      Review review = new Review(reviewName, reviewComment, reviewRating, album.getId());
      review.save();
      model.put("album", album);
      model.put("artist", artist);
      model.put("reviews", Review.findAlbumId(album.getId()));
      model.put("template", "templates/album.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/new-review", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/new-review-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/success", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      //create Artist object
      String userArtist = request.queryParams("user-artist");
      Artist artist = new Artist(userArtist);
      artist.save();
      //create Album object
      String userAlbumName = request.queryParams("user-album-name");
      int userAlbumYear = Integer.parseInt(request.queryParams("user-album-year"));
      String userAlbumGenre = request.queryParams("user-album-genre");
      Album album = new Album(userAlbumName, userAlbumYear, userAlbumGenre, artist.getId());
      album.save();
      //create Review object
      String reviewName = request.queryParams("name");
      String reviewComment = request.queryParams("comment");
      int reviewRating = Integer.parseInt(request.queryParams("rating"));
      Review newReview = new Review(reviewName, reviewComment, reviewRating, album.getId());
      newReview.save();
      model.put("artist", artist);
      model.put("album", album);
      model.put("reviews", Review.findAlbumId(album.getId()));
      model.put("template", "templates/album.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
