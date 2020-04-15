package jrails;

import books.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.*;

public class ViewTest {

    private Book b;

    @Before
    public void setBook(){
        b = new Book();
        b.title = "Programming Languages: Build, Prove, and Compare";
        b.author = "Norman Ramsey";
        b.num_copies = 999;
    }
    @Test
    public void empty() {
        assertThat(View.empty().toString(), isEmptyString());
    }

    @Test
    public void p() {
        assertThat(View.p(View.t("abc")).toString(), is("<p>abc</p>"));
    }

    @Test
    public void showBook(){
        assertThat(View.p(View.strong(View.t("Title:")).t(b.title)).
                p(View.strong(View.t("Author:")).t(b.author)).
                p(View.strong(View.t("Copies:")).t(b.num_copies)).
                t(View.link_to("Edit", "/edit")).t(" | ").
                t(View.link_to("Back", "/")).toString(), is(
                        "<p><strong>Title:</strong>Programming Languages: Build, Prove, and Compare</p>" +
                                "<p><strong>Author:</strong>Norman Ramsey</p>" +
                                "<p><strong>Copies:</strong>999</p>" +
                                "<a href=\"/edit\">Edit</a> | <a href=\"/\">Back</a>")
                );
    }
}