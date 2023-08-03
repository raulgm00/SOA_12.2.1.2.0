xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CorreoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)

declare namespace ns1="http://www.w3.org/2005/xquery-local-functions";

declare namespace cor = "http://www.bcie.org/CorreoBO";

declare variable $EnviarCorreoElectronico as element() (:: schema-element(ns2:EnviarCorreoElectronicoBPELRequest) ::) external;
declare variable $crearBitacoraCorreoElectronico as element() (:: schema-element(ns2:crearBitacoraCorreoElectronicoResponse) ::) external;

declare function ns1:func($EnviarCorreoElectronico as element() (:: schema-element(ns2:EnviarCorreoElectronicoBPELRequest) ::), 
                          $crearBitacoraCorreoElectronico as element() (:: schema-element(ns2:crearBitacoraCorreoElectronicoResponse) ::)) 
                          as element() (:: schema-element(ns2:ConstruirCorreoElectronicoRequest) ::) {
    <ns2:ConstruirCorreoElectronicoRequest>
        <ns2:CorreoElectronico>
            {
                if ($EnviarCorreoElectronico/ns2:correoElectronico/cor:idOperacion)
                then <cor:idOperacion>{fn:data($EnviarCorreoElectronico/ns2:correoElectronico/cor:idOperacion)}</cor:idOperacion>
                else ()
            }
            {
                if ($EnviarCorreoElectronico/ns2:correoElectronico/cor:idClienteFenix)
                then <cor:idClienteFenix>{fn:data($EnviarCorreoElectronico/ns2:correoElectronico/cor:idClienteFenix)}</cor:idClienteFenix>
                else ()
            }
            <cor:id_plantilla>92</cor:id_plantilla>
            
            <cor:parametros>
                <cor:tag>ASUNTO</cor:tag>
                {
                    if ($EnviarCorreoElectronico/ns2:correoElectronico/cor:id_plantilla)
                    then <cor:valor>{fn:data($EnviarCorreoElectronico/ns2:correoElectronico/cor:id_plantilla)}</cor:valor>
                    else <cor:valor>92</cor:valor>
                }
            </cor:parametros>
          
          {
            if(string($EnviarCorreoElectronico/ns2:correoElectronico/cor:idOperacion) !='')
            then
               <cor:parametros>
                 <cor:tag>TIPO_NOTIFICACION</cor:tag>
                <cor:valor>1</cor:valor>  
              </cor:parametros>
              
            else
             <cor:parametros>
                 <cor:tag>TIPO_NOTIFICACION</cor:tag>
                <cor:valor>0</cor:valor>  
              </cor:parametros>
            }
          
           
            <cor:parametros>
                 <cor:tag>ERROR_ENVIO_CORREO</cor:tag>
                <cor:valor>{fn:data($crearBitacoraCorreoElectronico/ns2:BitacoraId)}</cor:valor>  
              </cor:parametros>
           <cor:parametros>
                 <cor:tag>ID_ENVIO_CORREO</cor:tag>
                <cor:valor>{fn:data($crearBitacoraCorreoElectronico/ns2:BitacoraId)}</cor:valor>  
              </cor:parametros>
            
           {
            if(string($EnviarCorreoElectronico/ns2:correoElectronico/cor:idOperacion) !='')
            then
               <cor:parametros>
                 <cor:tag>TIPO_ETIQUETA_1</cor:tag>
                <cor:valor>1</cor:valor>  
              </cor:parametros>
              
            else
             <cor:parametros>
                 <cor:tag>TIPO_ETIQUETA_1</cor:tag>
                <cor:valor>0</cor:valor>  
              </cor:parametros>
            }    
            {
            if(string($EnviarCorreoElectronico/ns2:correoElectronico/cor:idOperacion) !='')
            then
               <cor:parametros>
                 <cor:tag>TIPO_ETIQUETA_2</cor:tag>
                <cor:valor>1</cor:valor>  
              </cor:parametros>
              
            else
             <cor:parametros>
                 <cor:tag>TIPO_ETIQUETA_2</cor:tag>
                <cor:valor>0</cor:valor>  
              </cor:parametros>
            }
            
            
              <cor:attachment>
               
            </cor:attachment>
        </ns2:CorreoElectronico>
    </ns2:ConstruirCorreoElectronicoRequest>
};

ns1:func($EnviarCorreoElectronico, $crearBitacoraCorreoElectronico)
