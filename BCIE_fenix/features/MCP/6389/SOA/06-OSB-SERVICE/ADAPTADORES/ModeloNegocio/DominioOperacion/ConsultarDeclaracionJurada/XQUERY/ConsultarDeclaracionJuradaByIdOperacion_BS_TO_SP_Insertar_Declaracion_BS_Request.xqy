xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/SP_Insertar_Declaracion";
(:: import schema at "../XSD/SP_Insertar_Declaracion_sp.xsd" ::)

declare variable $idOperacion as element() external;
declare variable $idDeclaracion as element() external;

declare function local:func($idOperacion as element(), 
                            $idDeclaracion as element()) 
                            as element() (:: schema-element(ns1:InputParameters) ::) {
    <ns1:InputParameters>
        <ns1:P_ID_OPERACION>{fn:data($idOperacion)}</ns1:P_ID_OPERACION>
        <ns1:P_ID_DECLARACION>{fn:data($idDeclaracion)}</ns1:P_ID_DECLARACION>
        <ns1:P_FECHA_REGISTRO>{fn:current-date()}</ns1:P_FECHA_REGISTRO>
    </ns1:InputParameters>
};

local:func($idOperacion, $idDeclaracion)
