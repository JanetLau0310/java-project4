package jrails;

public class Html {
    //a data structure represent HTML
    //an Html object has as instance methods
    // all the same tag methods as View, which are used to sequence HTML
    public String final_html;
    public Html(){}
    public Html(String s) {
        this.final_html = s;
    }

    public String toString() {
        return final_html;
        //return a string contains the corresponding HTML
    }

    public Html empty() throws Exception {
        //html.empty().toString() => ""
        return View.empty();
    }

    public Html seq(Html h) {
        String combine = this.final_html + h.final_html;
        return new Html(combine);
        //combine this and h
    }

    public Html br() {
        return View.br();
    }

    public Html t(Object o) {
        return View.t(o);
        // Use o.toString() to get the text for this
    }

    public Html p(Html child) {
        //return a new object
       return View.p(child);
    }

    public Html div(Html child) {
        return View.div(child);
    }

    public Html strong(Html child) {
        return View.strong(child);
    }

    public Html h1(Html child) {
        return View.h1(child);
    }

    public Html tr(Html child) {
        return View.tr(child);
    }

    public Html th(Html child) {
        return View.th(child);
    }

    public Html td(Html child) {
        return View.td(child);
    }

    public Html table(Html child) {
        return View.table(child);
    }

    public Html thead(Html child) {
        return View.thead(child);
    }

    public Html tbody(Html child) {
        return View.tbody(child);
    }

    public Html textarea(String name, Html child) {
        return View.textarea(name,child);
    }

    public Html link_to(String text, String url) {
        return View.link_to(text, url);
    }

    public Html form(String action, Html child) {
        return View.form(action, child);
    }

    public Html submit(String value) {
        return View.submit(value);
    }

}