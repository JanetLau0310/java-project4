package jrails;

import books.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JRouterTest {

    private JRouter jRouter;

    @Before
    public void setUp() throws Exception {
        jRouter = new JRouter();
    }

    @Test
    public void addRoute() {
        jRouter.addRoute("GET", "/",  BookController.class, "index");
        jRouter.addRoute("GET","/show", BookController.class,"show");
    }
    @Test
    public void getjRouter() {
        assertThat(jRouter.getRoute("GET", "/show"),is("BookController#show"));
    }
    @Test
    public void router() throws Exception {
        HashMap<String,String> test = new HashMap<>();
        test.put("id","42");
        jRouter.addRoute("GET", "/",  BookController.class, "index");
        jRouter.addRoute("GET","/show", BookController.class,"show");
        //jRouter.route("GET","/show",test);
    }
}