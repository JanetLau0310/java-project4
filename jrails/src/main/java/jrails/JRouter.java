package jrails;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JRouter {
    public HashMap<String,Object[]> RouterMap = new HashMap<>();

    public void addRoute(String verb, String path, Class clazz, String method) {
        //add a route from HTTP verb for path to the clazz class's method.
        if(!verb.equals("GET")&&
                !verb.equals("POST")){
            throw new UnsupportedOperationException("This is not a HTTP verb");
        }
        String request = verb + path;
        Object[] Clazz_method = new Object[2];
        Clazz_method[0] = (Object)clazz.getSimpleName();
        Clazz_method[1] = (Object)method;
        RouterMap.put(request,Clazz_method);
        // Table mapping work
    }
    // Returns "clazz#method" corresponding to verb+URN
    // Null if no such route
    public String getRoute(String verb, String path) {
        String request = verb + path;
        if(RouterMap.isEmpty() || !RouterMap.containsKey(request)){
            return null;
        }
        Object[] Clazz_method = RouterMap.get(request);
        String result = (String)Clazz_method[0] + "#" + (String)Clazz_method[1];

        return result;
    }
    // Call the appropriate controller method and
    // return the result
    public Html route(String verb, String path, Map<String, String> params) throws Exception {
        String request = verb + path;
        if(RouterMap.isEmpty() || !RouterMap.containsKey(request)){
            throw new UnsupportedOperationException();
        }
        String[] cls_method = getRoute(verb, path).split("#");
        //System.out.println(cls_method[0]);
        Class<?> cls = Class.forName(cls_method[0]);
        Method[] methods = cls.getMethods();
        //System.out.println(Arrays.toString(methods));
        for(Method k : methods){
            if(k.getName().equals(cls_method[1])){
                return (Html) k.invoke(params);
            }
        }
        throw new UnsupportedOperationException();
        //use reflection to call index method
    }
}
