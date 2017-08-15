package org.detoeuf.hexagonal.webserver;

import org.detoeuf.hexagonal.domain.Blog;
import org.detoeuf.hexagonal.domain.BlogPost;
import org.detoeuf.hexagonal.domain.BlogServer;
import ratpack.server.RatpackServer;

public class WebServer implements BlogServer {

    public WebServer() {
        System.out.println("Démarrage webserver");
    }

    public void start() {
        Blog blog = new Blog();
        try {
            RatpackServer.start(server -> server
                    .handlers(chain -> chain
                            .get("post/:title/:text", ctx -> {
                                BlogPost blogPost = new BlogPost(ctx.getPathTokens().get("title"), ctx.getPathTokens().get("text"));
                                blog.post(blogPost);
                                ctx.render("Posted blog " + blogPost.toString() + "!");
                            })
                            .get("article/:title", ctx -> {
                                ctx.render(blog.getPostWithTitle(ctx.getPathTokens().get("title"))
                                        .map(p -> "Blog post : " + p.toString() + "!")
                                        .orElse("Blog post not found !")
                                );
                            })
                            .get("share/:title/:media", ctx -> {
                                blog.share(ctx.getPathTokens().get("title"),ctx.getPathTokens().get("media"));
                                ctx.render("OK");
                            })
                    )
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
