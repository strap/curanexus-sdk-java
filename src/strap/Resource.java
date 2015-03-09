package strap;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author marcellebonterre
 */
public class Resource {

    public String Name;
    public String Token;
    public String Method;
    public String URI;
    public String Description;
    public ArrayList<String> Required;
    public ArrayList<String> Optional;

    public Resource(){
        
    }
    
//    TODO: fifure out return type, and type of params
    public String Call(Map<String,String> params){
//        verify method is not empty
//        pull apart request pieces
        
        
        String body = "";
        return body;
    }
}
