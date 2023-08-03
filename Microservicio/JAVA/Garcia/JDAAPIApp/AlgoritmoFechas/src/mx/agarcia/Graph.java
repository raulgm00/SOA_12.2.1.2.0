package mx.agarcia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;

public class Graph {
    static HashMap<String, HashMap> globalDefinitions = new HashMap();
    
    /**
     KEY                        VALUE
     INTERFACE_DEF_CODE         interfaceInstanceStats
     */
    
    static HashMap<String, HashMap> instances;
    static HashMap<String, HashMap> statusCountMap;
    
    
    public static void main(String [] args){
        List<InterfaInstance> ejemplos = dataSample ();
        for(InterfaInstance instance : ejemplos){
            if( globalDefinitions.containsKey( instance.getCodeDef() ) ){
                instances = globalDefinitions.get( instance.getCodeDef() );
            } else {
                instances = new HashMap<String, HashMap> ();
                globalDefinitions.put( instance.getCodeDef() , instances);
            }
            
            if( instances.containsKey( instance.getStatusCode() == "OK") ){
                if( instances.containsKey( instance.getStatusCode() + "OK" ) ){
                    statusCountMap = instances.get( instance.getStatusCode() + "OK" );
                } else{
                    statusCountMap =  new HashMap<String, HashMap> ();
                }
                
            }
            
            
            
            
            
        }
        
        
    
    
    }
    
    
    private static List<InterfaInstance> dataSample (){
        List<InterfaInstance> sample = new ArrayList<InterfaInstance>();
        for(int i = 0; i <= 100; i++){
            sample.add(  new InterfaInstance());                         
        }
        return sample;
    }


 
}

//OK
/*

{
"01/06/2019" : 2,
"02/06/2019" : 4,
"05/06/2019" : 9

}

//Eror


{
"01/06/2019" : 2,
"02/06/2019" : 4,
"05/06/2019" : 12

}


//Total


{
"01/06/2019" : 2,
"02/06/2019" : 4,
"05/06/2019" : 21

}
*/
class InterfaInstance {
    
    
    
    private Date startDate;
    private String codeDef;
    private String statusCode;

    public InterfaInstance(){
        startDate = new Date();
        codeDef = "CRIT";
        statusCode = "OK";
    }


    void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    Date getStartDate() {
        return startDate;
    }

    void setCodeDef(String codeDef) {
        this.codeDef = codeDef;
    }

    String getCodeDef() {
        return codeDef;
    }

    void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    String getStatusCode() {
        return statusCode;
    }
}
