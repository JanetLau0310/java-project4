package jrails;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.*;

public class HtmlTest {

    private Html html;

    @Before
    public void setUp() {
        html = new Html();
    }

    @Test
    public void empty() throws Exception {
        assertThat(html.empty().toString(), isEmptyString());
    }
}