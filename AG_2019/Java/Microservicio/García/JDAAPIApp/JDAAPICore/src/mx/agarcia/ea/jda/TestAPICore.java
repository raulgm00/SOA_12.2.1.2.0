package mx.agarcia.ea.jda;

import java.text.SimpleDateFormat;

public class TestAPICore {
    
        static SimpleDateFormat format = new SimpleDateFormat("ddMMyy");
        static SimpleDateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws Exception {
        
        String convertedFecIni = "", convertedFecFin = "";
            convertedFecIni = format.format( formatInput.parse( "2019-08-06" ) ) ;

        System.out.println( "convertedFec" + convertedFecIni  );
       // config();
        /*
        //saveFile( "PRUEBA" );
        String fileContent = readFileContent( testFilePath );
        log( "Archivo "+testFilePath+" leido.." );
        log("\n");
        String data = parseXmlContent( fileContent );
        //savePriceChangeFile( "0", data );
        */
    }

    
}
