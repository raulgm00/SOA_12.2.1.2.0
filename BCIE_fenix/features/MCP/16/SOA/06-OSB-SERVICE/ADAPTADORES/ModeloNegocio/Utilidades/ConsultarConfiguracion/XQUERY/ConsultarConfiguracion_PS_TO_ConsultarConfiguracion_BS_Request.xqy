xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ConsultarConfiguracion";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarConfiguracion/V1/Schema/ConsultarConfiguracionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarConfiguracion";
(:: import schema at "../XSD/ConsultarConfiguracion.xsd" ::)

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $ConsultarConfiguracionRequest as element() (:: schema-element(ns1:ConsultarConfiguracionRequest) ::) external;

declare function local:func($ConsultarConfiguracionRequest as element() (:: schema-element(ns1:ConsultarConfiguracionRequest) ::)) as element() (:: schema-element(ns2:ConsultarConfiguracionInput) ::) {
    <ns2:ConsultarConfiguracionInput>
      {if(fn:count($ConsultarConfiguracionRequest/ns1:Parametro) = 1)then
        <ns2:llave>{fn:data($ConsultarConfiguracionRequest/ns1:Parametro/com:name)}</ns2:llave>
       else
        <ns2:llave></ns2:llave>
      }  
    </ns2:ConsultarConfiguracionInput>
};

local:func($ConsultarConfiguracionRequest)
