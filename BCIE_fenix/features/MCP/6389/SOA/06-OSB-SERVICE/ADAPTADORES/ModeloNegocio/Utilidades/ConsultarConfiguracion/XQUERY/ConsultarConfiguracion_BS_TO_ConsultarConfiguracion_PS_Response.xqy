xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConsultarConfiguracion";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarConfiguracion/V1/Schema/ConsultarConfiguracionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarConfiguracion";
(:: import schema at "../XSD/ConsultarConfiguracion.xsd" ::)

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarConfiguracionOutputCollection as element() (:: schema-element(ns1:ConsultarConfiguracionOutputCollection) ::) external;
declare variable $ConsultarConfiguracionRequest as element() (:: schema-element(ns2:ConsultarConfiguracionRequest) ::) external;

declare function local:func($ConsultarConfiguracionOutputCollection as element() (:: schema-element(ns1:ConsultarConfiguracionOutputCollection) ::), 
                            $ConsultarConfiguracionRequest as element() (:: schema-element(ns2:ConsultarConfiguracionRequest) ::)) 
                            as element() (:: schema-element(ns2:ConsultarConfiguracionResponse) ::) {
    <ns2:ConsultarConfiguracionResponse>
    {if($ConsultarConfiguracionRequest/ns2:Parametro[1]/com:name != '')then
      for $parametros at $posicion in $ConsultarConfiguracionOutputCollection/ns1:ConsultarConfiguracionOutput
      where $parametros/ns1:LLAVE = $ConsultarConfiguracionRequest/ns2:Parametro/com:name
      return
        <ns2:Parametro>
            <com:name>{fn:data($parametros/ns1:LLAVE)}</com:name>
            <com:value>{fn:data($parametros/ns1:VALOR)}</com:value>
        </ns2:Parametro>
     else
      for $parametros at $posicion in $ConsultarConfiguracionOutputCollection/ns1:ConsultarConfiguracionOutput
      return
        <ns2:Parametro>
            <com:name>{fn:data($parametros/ns1:LLAVE)}</com:name>
            <com:value>{fn:data($parametros/ns1:VALOR)}</com:value>
        </ns2:Parametro>
    }
    <ns2:Resultado>
        <res:result>OK</res:result>
        <res:message>Consulta exitosa</res:message>
    </ns2:Resultado>    
    </ns2:ConsultarConfiguracionResponse>
};

local:func($ConsultarConfiguracionOutputCollection, $ConsultarConfiguracionRequest)
