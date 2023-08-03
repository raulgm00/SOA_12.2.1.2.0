xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ActualizarTreLineaCredito_DB";
(:: import schema at "../XSD/ActualizarTreLineaCredito_DB.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare variable $ActualizarTreLineaCreditoRequest as element() (:: schema-element(ns1:ActualizarTreLineaCreditoRequest) ::) external;

declare function local:func($ActualizarTreLineaCreditoRequest as element() (:: schema-element(ns1:ActualizarTreLineaCreditoRequest) ::)) as element() (:: schema-element(ns2:ActualizarTreLineaCredito_DBInput) ::) {
    <ns2:TreLineaCreditoTccCollection>
        <ns2:ID_TERMINO>{fn:data($ActualizarTreLineaCreditoRequest/ns1:Contrato/con:LineaCredito/lin:Termino/ter:idTermino)}</ns2:ID_TERMINO>
        <ns2:ID_COMISION>{fn:data($ActualizarTreLineaCreditoRequest/ns1:Contrato/con:LineaCredito/lin:Comision/com:idComision)}</ns2:ID_COMISION>
        <ns2:ID_LINEA_CREDITO>{fn:data($ActualizarTreLineaCreditoRequest/ns1:Contrato/con:LineaCredito/lin:idLineaCredito)}</ns2:ID_LINEA_CREDITO>
    </ns2:TreLineaCreditoTccCollection>
};

local:func($ActualizarTreLineaCreditoRequest)
