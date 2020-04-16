package jrails;

import books.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class ModelTest {

    private Model model;

    @Before
    public void setUp() throws Exception {
        model = new Model(){};
    }
    @Test
    public void myTest_b(){
        Book b1 = new Book();
        Book b2 = new Book();
        Book b3 = new Book();

        b1.title = "Beyond Good and Evil";
        b1.author = "Nietzsche";
        b1.num_copies = 100;
        b1.save();

        b2.title = "";
        b2.author = "";
        b2.num_copies = 10;
        b2.save();

        b3.title = "12 Rules for Life";
        b3.author = "Jordan B. Peterson";
        b3.num_copies = 10;
        b3.save();

        Model.find(Book.class,2);
        assert(b1.id()!=b2.id());
       // b2.destroy();
        assert(Model.find(Book.class,0) == null);
        //b2.destroy();
        assert(Model.all(Book.class).size() == 3);
    }

    @Test
    public void id() {
        assertThat(model.id(), notNullValue());
    }


    @After
    public void tearDown() throws Exception {
        Model.reset();
    }
}