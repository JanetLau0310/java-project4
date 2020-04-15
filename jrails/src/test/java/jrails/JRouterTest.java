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
    public void addRoute() throws Exception {
        jRouter.addRoute("GET", "/",  BookController.class, "index");
        jRouter.addRoute("GET","/show", BookController.class,"show");
        HashMap<String,String> test = new HashMap<>();
        test.put("id","42");
        jRouter.route("GET","/show",test);
    }
}