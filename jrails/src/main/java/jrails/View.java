package jrails;

public class View {
    private static Html final_html=new Html();
    //superclass provide methods for constructing HTML
    public static Html empty() {
        return final_html.empty();
    }
    public static Html br() {
        return final_html.br();
        //render with <br/>
    }

    public static Html t(Object o) {
        return final_html.t(o);
        // Use o.toString() to get the text for this
    }

    public static Html p(Html child) {
        return final_html.p(child);
    }

    public static Html div(Html child) {
        return final_html.div(child);
    }

    public static Html strong(Html child) {
        return final_html.strong(child);
    }

    public static Html h1(Html child) {
        return final_html.h1(child);
    }

    public static Html tr(Html child) {
        return final_html.tr(child);
    }

    public static Html th(Html child) {
        return final_html.th(child);
    }

    public static Html td(Html child) {
        return final_html.td(child);
    }

    public static Html table(Html child) {
        return final_html.table(child);
    }

    public static Html thead(Html child) {
        return final_html.thead(child);
    }

    public static Html tbody(Html child) {
        return final_html.tbody(child);
    }

    public static Html textarea(String name, Html child) {
        return final_html.textarea(name,child);
    }

    public static Html link_to(String text, String url) {
        return final_html.link_to(text,url);
    }

    public static Html form(String action, Html child) {
        return final_html.form(action,child);
    }

    public static Html submit(String value) {
        //return html submit button with given value
        //e.g., submit("Save") should produce <input type="submit" value="Save"/>.
        return final_html.submit(value);
    }
}