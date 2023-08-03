xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ActualizarDetalleEnmienda_DB";
(:: import schema at "../XSD/ActualizarDetalleEnmienda_DB.xsd" ::)

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare variable $ActualizarDetalleEnmiendaRequest as element() (:: schema-element(ns1:ActualizarDetalleEnmiendaRequest) ::) external;

declare function local:func($ActualizarDetalleEnmiendaRequest as element() (:: schema-element(ns1:ActualizarDetalleEnmiendaRequest) ::)) as element() (:: schema-element(ns2:ActualizarDetalleEnmienda_DBInput) ::) {
    <ns2:ActualizarDetalleEnmienda_DBInput>
        <ns2:P_ACCION>{fn:data($ActualizarDetalleEnmiendaRequest/ns1:detalleEnmienda/mat:accion)}</ns2:P_ACCION>
        <ns2:P_ID_TCC>{fn:data($ActualizarDetalleEnmiendaRequest/ns1:detalleEnmienda/mat:idTCC)}</ns2:P_ID_TCC>
        <ns2:P_TIPO_TCC>{fn:data($ActualizarDetalleEnmiendaRequest/ns1:detalleEnmienda/mat:tipoTCC)}</ns2:P_TIPO_TCC>
    </ns2:ActualizarDetalleEnmienda_DBInput>
};

local:func($ActualizarDetalleEnmiendaRequest)
