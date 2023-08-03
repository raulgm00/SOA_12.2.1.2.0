xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarDeclaracionByIdOperacion";
(:: import schema at "../XSD/ConsultarDeclaracionJuradaByIdOperacion.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/SP_Insertar_Declaracion";
(:: import schema at "../XSD/SP_Insertar_Declaracion_sp.xsd" ::)

declare variable $ConsultarDeclaracionByIdOperacionOutput as element() (:: schema-element(ns1:ConsultarDeclaracionByIdOperacionOutputCollection) ::) external;
declare variable $index as xs:int external;

declare function local:func($ConsultarDeclaracionByIdOperacionOutput as element() (:: schema-element(ns1:ConsultarDeclaracionByIdOperacionOutputCollection) ::),
                            $index as xs:int) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_ID_OPERACION>{fn:data($ConsultarDeclaracionByIdOperacionOutput/ns1:ConsultarDeclaracionByIdOperacionOutput[$index]/ns1:CODIGO_REFERENCIA)}</ns2:P_ID_OPERACION>
        <ns2:P_ID_DECLARACION>{fn:data($ConsultarDeclaracionByIdOperacionOutput/ns1:ConsultarDeclaracionByIdOperacionOutput[$index]/ns1:CODIGO_DECLARACION)}</ns2:P_ID_DECLARACION>
        <ns2:P_FECHA_REGISTRO>{fn:current-date()}</ns2:P_FECHA_REGISTRO></ns2:InputParameters>
};

local:func($ConsultarDeclaracionByIdOperacionOutput, $index)
