package jrails;

public class Html {
    //a data structure represent HTML
    //an Html object has as instance methods
    // all the same tag methods as View, which are used to sequence HTML
    public String html;
    public Html(String s){
        this.html = s;
    }
    public Html(){}
    public String toString() {
        return this.html;
        //return a string contains the corresponding HTML
    }

    public Html empty() {
        //html.empty().toString() => ""
        return seq(new Html(""));
    }

    public Html seq(Html h) {
        if(this.html == null)
            return new Html(h.toString());
        return new Html(this.html + h.toString());
        //combine this and h
    }

    public Html br() {
        return seq(new Html("<br/>"));
    }

    public Html t(Object o) {
       // System.out.println(o.toString());
        String text = o.toString();
        return seq(new Html(text));
        // Use o.toString() to get the text for this
    }

    public Html p(Html child) {
        //return a new object
       return seq(new Html("<p>"+child.toString()+"</p>"));
    }

    public Html div(Html child) {
        return seq(new Html("<div>"+child.toString()+"</div>"));
    }

    public Html strong(Html child) {
        return seq(new Html("<strong>"+child.toString()+"</strong>"));
    }

    public Html h1(Html child) {
        return seq(new Html("<h1>"+child.toString()+"</h1>"));
    }

    public Html tr(Html child) {
        return seq(new Html("<tr>"+child.toString()+"</tr>"));
    }

    public Html th(Html child) {
        return seq(new Html("<th>"+child.toString()+"</th>"));
    }

    public Html td(Html child) {
        return seq(new Html("<td>"+child.toString()+"</td>"));
    }

    public Html table(Html child) {
        return seq(new Html("<table>"+child.toString()+"</table>"));
    }

    public Html thead(Html child) {
        return seq(new Html("<thead>"+child.toString()+"</thead>"));
    }

    public Html tbody(Html child) {
        return seq(new Html("<tbody>"+child.toString()+"</tbody>"));
    }

    public Html textarea(String name, Html child) {
        return seq(new Html("<textarea name=\""+name+"\">"+child.toString()+"</textarea>"));
    }

    public Html link_to(String text, String url) {
        return seq(new Html("<a href=\"" + url + "\">" + text + "</a>"));
    }

    public Html form(String action, Html child) {
        String s1 = "<form action=\"" + action + "\" accept-charset=\"UTF-8\" method=\"post\">";
        String s2 = "</form>";
        return seq(new Html(s1 + child.toString() + s2));
    }

    public Html submit(String value) {
        String button = " <input type=\"submit\" " + "value=\"" + value + "\""+"/>";
        return seq(new Html(button));
    }

}