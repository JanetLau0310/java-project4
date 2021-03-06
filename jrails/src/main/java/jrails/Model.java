package jrails;

import javax.print.attribute.standard.NumberUpSupported;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.*;

public class Model {
    private int unique_id;
    public int id() {
        // to get an id
        return unique_id;
    }
    public void setID(int id){
        this.unique_id = id;
    }

    public static void check_int(int x){
        if(x<0){
            return;
        }
    }
    public void save() {
        //this is an instance of current model
        Model model = this;
        String tmpFile = "temp.csv";
        String filepath = "db.csv";
        File f = new File(filepath);
        File tmp = new File(tmpFile);
        InputStreamReader reader = null;
        BufferedReader layout = null;
        OutputStreamWriter writer = null;
        try{
            writer = new OutputStreamWriter(new FileOutputStream(tmp,true));
            List<Object> list = new ArrayList<Object>();
            // id = 0 before save in file
            list.add(0);
            for(Field field : model.getClass().getFields()){
                Column column = field.getAnnotation(Column.class);
                if(column != null) {
                    if (field.getType() == null) {
                        list.add("Null");
                    } else {
                        if (field.getType().equals(String.class)) {
                            Object value = field.get(model);
                            if (value.toString().isEmpty()) { list.add("Empty");
                            } else {//save in csv, make sure commas is unique
                                value = ((String) value).replaceAll(",", "#");
                                list.add(value);
                            }
                        } else if (field.getType().equals(int.class)){
                            check_int(Integer.parseInt(field.get(model).toString()));
                            list.add(field.get(model));
                        }else if(field.getType().equals(boolean.class)) {
                            list.add(field.get(model));
                        } else { throw new UnsupportedClassVersionError(); }
                    }
                }else{
                    Object value = field.get(model);
                    list.add(value);
                }
            }
            if(f.length()==0){
                this.unique_id = 1;
                list.set(0, this.unique_id);
                for(Object data : list){
                    String rowStr = data + ",";
                    writer.write(rowStr);
                }
                writer.write("\r\n");
                writer.close();
            }else {
                reader = new InputStreamReader(new FileInputStream(f));
                layout = new BufferedReader(reader);
                String line = null;
                StringBuilder update = new StringBuilder();
                String id = String.valueOf(this.unique_id);
                int mark = 0;
                int size = 1;

                while (((line = layout.readLine())) != null){
                    String[] cells = line.split(",");
                    if(id.equals(cells[0])){
                        // System.out.println("in db");
                        mark = 1;
                        list.set(0, this.unique_id);
                        for(Object data : list){
                            update.append(data);
                            update.append(",");
                        }
                        line = line.replace(line,update.toString());
                    }
                    writer.write(line+"\r\n");
                    size = size+1;
                }
                if(mark == 0){ // not in db
                    if(this.unique_id != 0 ){
                        throw new UnsupportedOperationException("not in db");
                    }
                    this.unique_id = size;
                    list.set(0, this.unique_id);
                    for(Object data : list){
                        String rowStr = data + ",";
                        writer.write(rowStr);
                    }
                    writer.write("\r\n");
                }
                layout.close();
                reader.close();
                writer.close();
            }
        }catch (IOException | IllegalAccessException e){
            e.printStackTrace();
        }finally {
            try{
                if (layout!=null){ layout.close(); }
            }catch (Exception e) { e.printStackTrace();}
            try{
                if (reader!=null){ reader.close(); }
            }catch (Exception e) { e.printStackTrace();}
            try{
                if (writer!=null){ writer.close(); }
            }catch (Exception e) { e.printStackTrace();}
            try {
                if(!f.delete()){ f.delete(); }
            }catch (Exception e){
                e.printStackTrace();
            }
            File dump = new File(filepath);
            tmp.renameTo(dump);
        }
    }
    //check the db for given id, if not,return null;
    public static <T extends Model> T find(Class<T> c, int id) {
        File f = new File("db.csv");
        T t = null;
        if(!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        InputStreamReader reader = null;
        BufferedReader layout = null;
        try{
            Field[] fields = c.getDeclaredFields();
            reader = new InputStreamReader(new FileInputStream(f));
            layout = new BufferedReader(reader);
            String line = null;
            int mark = 0;
            while (((line = layout.readLine())) != null){
                String[] cells = line.split(",");
                if(cells[0].equals(String.valueOf(id))) {
                    Object obj = c.getDeclaredConstructor().newInstance();
                    t = (T) obj;
                    t.setID(id);
                    mark = 1;
                    for(int k=0 ; k<fields.length; k++){
                        if(fields[k].getType().equals(int.class)){
                            check_int(Integer.parseInt(cells[k+1]));
                            fields[k].set(t,Integer.parseInt(cells[k+1]));
                        }else if (fields[k].getType().equals(String.class)){
                            String tmp = cells[k+1].replaceAll("#", ",");
                            tmp = tmp.replaceAll("Empty","");
                            tmp = tmp.replaceAll("Null",null);
                            fields[k].set(t,tmp);
                        }else if (fields[k].getType().equals(boolean.class)){
                            fields[k].set(t,Boolean.parseBoolean(cells[k+1]));
                        }
                    }
                }
            }
            if( mark == 0 ){ return null; }
            layout.close();
            reader.close();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if (layout!=null){ layout.close(); }
            }catch (Exception e) { e.printStackTrace();}

            try{
                if (reader!=null){ reader.close(); }
            }catch (Exception e) { e.printStackTrace();}
        }
        return t;
    }

    //similar to find, return all instances of class
    public static <T extends Model> List<T> all(Class<T> c) {
        // Returns a List<element type>
        List<T> list = new ArrayList<T>();
        Field[] fields = c.getDeclaredFields();
        File f = new File("db.csv");
        InputStreamReader reader = null;
        BufferedReader layout = null;
        try{
            reader = new InputStreamReader(new FileInputStream(f));
            layout = new BufferedReader(reader);
            String line = null;

            Constructor<?> cons = c.getDeclaredConstructor();

            while (((line = layout.readLine())) != null){
                String[] cells = line.split(",");
                Object obj = cons.newInstance();
                T t = (T) obj;
                t.setID(Integer.parseInt(cells[0]));
                for(int k=0 ; k<fields.length; k++){
                    if(fields[k].getType().equals(int.class)){
                        check_int(Integer.parseInt(cells[k+1]));
                        fields[k].set(t,Integer.parseInt(cells[k+1]));
                    }else if (fields[k].getType().equals(String.class)){
                        String tmp = cells[k+1].replaceAll("#", ",");
                        tmp = tmp.replaceAll("Empty","");
                        tmp = tmp.replaceAll("Null",null);
                        fields[k].set(t,tmp);
                    }else if (fields[k].getType().equals(boolean.class)){
                        fields[k].set(t,Boolean.parseBoolean(cells[k+1]));
                    }
                }
                list.add(t);
            }
            layout.close();
            reader.close();
        }catch (Exception e) { e.printStackTrace();
        } finally {
            try{ if (layout!=null){ layout.close(); }
            }catch (Exception e) { e.printStackTrace();}
            try{ if (reader!=null){ reader.close(); }
            }catch (Exception e) { e.printStackTrace();}
        }
        return list;
    }

    //remove the receiver from db
    //if receiver not save to db before, raise exception
    public void destroy() {
        //if not save to db before
        if (this.unique_id == 0 || find(this.getClass(),this.id()) == null) {
            throw new UnsupportedOperationException();
        }
        try {
            Model model = find(this.getClass(), this.unique_id);
            if(model != null){
                //this model can be found in db file
                //update the model
                String tmpFile = "temp.csv";
                String filepath = "db.csv";
                File oldFile = new File(filepath);
                File newFile = new File(tmpFile);
                OutputStreamWriter fw = null;
                InputStreamReader reader = null;
                BufferedReader layout = null;
                try{
                    reader = new InputStreamReader(new FileInputStream(oldFile));
                    layout = new BufferedReader(reader);
                    fw = new OutputStreamWriter(new FileOutputStream(newFile,true));

                    String line = null;
                    while (((line = layout.readLine())) != null){
                        String[] cells = line.split(",");
                        if(!(cells[0].equals(String.valueOf(this.unique_id)))) {
                            fw.write(line+"\r\n");
                        }
                    }
                    fw.flush();
                    fw.close();
                    layout.close();
                    reader.close();
                }catch (Exception e) { e.printStackTrace();
                }finally {
                    try{
                        if (layout!=null){ layout.close(); }
                    }catch (Exception e) { e.printStackTrace();}
                    try{
                        if (reader!=null){ reader.close(); }
                    }catch (Exception e) { e.printStackTrace();}
                    try{
                        if (fw!=null){ fw.close(); }
                    }catch (Exception e) { e.printStackTrace();}
                    oldFile.delete();
                    File dump = new File(filepath);
                    newFile.renameTo(dump);
                }
            }else{ throw new UnsupportedOperationException(); }
        }catch(Exception e){e.printStackTrace();}
    }

    //remove all rows from db
    public static void reset() {
        try{
            File f = new File("db.csv");
            if(!f.delete()){
                System.gc();
                f.delete();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}