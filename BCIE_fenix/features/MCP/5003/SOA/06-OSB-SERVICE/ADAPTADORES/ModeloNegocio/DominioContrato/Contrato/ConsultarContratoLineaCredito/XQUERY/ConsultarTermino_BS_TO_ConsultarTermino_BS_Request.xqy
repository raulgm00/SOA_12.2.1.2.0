xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)


declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarContrato";
(:: import schema at "../XSD/ConsultarContrato.xsd" ::)

declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTcaTermino";
(:: import schema at "../../../Termino/ConsultarTerminoByContrato/XSD/ConsultarTcaTermino.xsd" ::)

declare variable $responselineacredito as element() (:: schema-element(ns1:ConsultarContratoOutputCollection) ::) external;
declare variable $index as xs:int external;

declare function local:func($responselineacredito as element() (:: schema-element(ns1:ConsultarContratoOutputCollection) ::),$index as xs:int) 
as element() (:: schema-element(ns2:ConsultarTcaTerminoInput) ::) {
    <ns2:ConsultarTcaTerminoInput>
        <ns2:P_IDCONTRATO>{fn:data($responselineacredito/ns1:ConsultarContratoOutput[$index]/ns1:ID)}</ns2:P_IDCONTRATO>

   
    </ns2:ConsultarTcaTerminoInput>
};

local:func($responselineacredito, $index)