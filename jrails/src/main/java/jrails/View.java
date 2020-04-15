package jrails;

public class View {
    //superclass provide methods for constructing HTML
    public static Html empty() {
        Html html = new Html();
        html.final_html = "";
        return html;
    }
    public static Html br() {
        Html html = new Html();
        String br = "<br/>";
        if(html.final_html != null){
            html.final_html = html.final_html + br;
        }else {
            html.final_html = br;
        }
        return html;
        //render with <br/>
    }

    public static Html t(Object o) {
        Html html = new Html();
        String text = o.toString();
        if(html.final_html != null){
            html.final_html = html.final_html + text;
        }else {
            html.final_html = text;
        }
        return html;
        // Use o.toString() to get the text for this
    }

    public static Html p(Html child) {
        Html html = new Html();
        String s1 = "<p>";
        String s2 = "</p>";
        if(child == null){
            html.final_html = s1 + "" + s2;
        }else{
            html.final_html = s1 + child.toString() + s2;
        }
        return html;
    }

    public static Html div(Html child) {
        Html html = new Html();
        String s1 = "<div>";
        String s2 = "</div>";
        if(child == null){
            html.final_html = s1 + "" + s2;
        }else{
            html.final_html = s1 + child.toString() + s2;
        }
        return html;
    }

    public static Html strong(Html child) {
        Html html = new Html();
        String s1 = "<strong>";
        String s2 = "</strong>";
        if(child == null){
            html.final_html = s1 + "" + s2;
        }else{
            html.final_html = s1 + child.toString() + s2;
        }
        return html;
    }

    public static Html h1(Html child) {
        Html html = new Html();
        String s1 = "<h1>";
        String s2 = "</h1>";
        if(child == null){
            html.final_html = s1 + "" + s2;
        }else{
            html.final_html = s1 + child.toString() + s2;
        }
        return html;
    }

    public static Html tr(Html child) {
        Html html = new Html();
        String s1 = "<tr>";
        String s2 = "</tr>";
        if(child == null){
            html.final_html = s1 + "" + s2;
        }else{
            html.final_html = s1 + child.toString() + s2;
        }
        return html;
    }

    public static Html th(Html child) {
        Html html = new Html();
        String s1 = "<th>";
        String s2 = "</th>";
        if(child == null){
            html.final_html = s1 + "" + s2;
        }else{
            html.final_html = s1 + child.toString() + s2;
        }
        return html;
    }

    public static Html td(Html child) {
        Html html = new Html();
        String s1 = "<td>";
        String s2 = "</td>";
        if(child == null){
            html.final_html = s1 + "" + s2;
        }else{
            html.final_html = s1 + child.toString() + s2;
        }
        return html;
    }

    public static Html table(Html child) {
        Html html = new Html();
        String s1 = "<table>";
        String s2 = "</table>";
        if(child == null){
            html.final_html = s1 + "" + s2;
        }else{
            html.final_html = s1 + child.toString() + s2;
        }
        return html;
    }

    public static Html thead(Html child) {
        Html html = new Html();
        String s1 = "<thead>";
        String s2 = "</thead>";
        if(child == null){
            html.final_html = s1 + "" + s2;
        }else{
            html.final_html = s1 + child.toString() + s2;
        }
        return html;
    }

    public static Html tbody(Html child) {
        Html html = new Html();
        String s1 = "<tbody>";
        String s2 = "</tbody>";
        if(child == null){
            html.final_html = s1 + "" + s2;
        }else{
            html.final_html = s1 + child.toString() + s2;
        }
        return html;
    }

    public static Html textarea(String name, Html child) {
        Html html = new Html();
        //<textarea name="title">
        String s1 = "<textarea name=\"" + name + "\">";
        String s2 = "</textarea>";
        if(child == null){
            html.final_html = s1 + "" + s2;
        }else{
            html.final_html = s1 + child.toString() + s2;
        }
        return html;
    }

    public static Html link_to(String text, String url) {
        Html html = new Html();
        String s1 = "<a href=\""+url+"\">";
        String s2 = "</a>";
        String link = s1 + text  + s2;
        if(html.final_html != null){
            html.final_html = html.final_html + link;
        }else {
            html.final_html = link;
        }
        return html;
    }

    public static Html form(String action, Html child) {
        Html html = new Html();
        String s1 = "<form action=\"" + action + "\" accept-charset=\"UTF-8\" method=\"post\">";
        String s2 = "</form>";
        if(child == null){
            html.final_html = s1 + "" + s2;
        }else{
            html.final_html = s1 + child.toString() + s2;
        }
        return html;
    }

    public static Html submit(String value) {
        //return html submit button with given value
        //e.g., submit("Save") should produce <input type="submit" value="Save"/>.
        Html html = new Html();
        String button = " <input type=\"submit\" " + "value=\"" + value + "\"/>";
        if(html.final_html != null){
            html.final_html = html.final_html + button;
        }else {
            html.final_html = button;
        }
        return html;
    }

/*    public static void main(String[] args) {
        Html html = new Html();
        System.out.println(View.empty().br().toString());
    }*/
}