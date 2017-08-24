package org.detoeuf.hexagonal.webserver;

import org.detoeuf.hexagonal.domain.Blog;
import org.detoeuf.hexagonal.domain.BlogPost;
import org.detoeuf.hexagonal.domain.BlogServer;

import static spark.Spark.get;
import static spark.Spark.port;

public class WebServer implements BlogServer {

    public static final int PORT = 5050;

    public WebServer() {
        System.out.println("Started webserver on port " + PORT);
    }

    public void start() {
        port(PORT);

        Blog blog = new Blog();

        get("/post/:title/:text", (req, res) -> {
            String title = req.params(":title");
            String text = req.params(":text");
            BlogPost blogPost = new BlogPost(title, text);
            blog.post(blogPost);
            return "Posted blog " + blogPost.toString() + "!";
        });

        get("/article/:title", (req, res) -> {
            String title = req.params(":title");
            return blog.getPostWithTitle(title)
                    .map(p -> "Blog post : " + p.toString() + "!")
                    .orElse("Blog post not found !");
        });

        get("/share/:title/:media", (req, res) -> {
            String title = req.params(":title");
            String media = req.params(":media");
            blog.share(title, media);
            return "OK";
        });

    }
}
