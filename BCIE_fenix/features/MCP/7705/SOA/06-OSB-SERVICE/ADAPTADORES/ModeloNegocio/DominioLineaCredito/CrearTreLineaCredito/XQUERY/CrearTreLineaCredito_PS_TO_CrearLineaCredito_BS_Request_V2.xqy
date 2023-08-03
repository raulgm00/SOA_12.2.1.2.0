xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/CrearTreLineaCredito";
(:: import schema at "../V2/XSD/CrearTreLineaCredito.xsd" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare variable $CrearLineaCreditoRequest as element() (:: schema-element(ns1:CrearTreLineaCreditoRequest) ::) external;

declare function local:func($CrearLineaCreditoRequest as element() (:: schema-element(ns1:CrearTreLineaCreditoRequest) ::)) as element() (:: schema-element(ns2:CrearTreLineaCreditoInput) ::) {
    <ns2:CrearTreLineaCreditoInput>
        <ns2:idLineaCredito>{fn:data($CrearLineaCreditoRequest/ns1:LineaCredito/lin:idLineaCredito)}</ns2:idLineaCredito>
        <ns2:idTermino>{fn:data($CrearLineaCreditoRequest/ns1:LineaCredito/lin:Termino/ter:idTermino)}</ns2:idTermino>
        <ns2:idComision>{fn:data($CrearLineaCreditoRequest/ns1:LineaCredito/lin:Comision/com:idComision)}</ns2:idComision>
        <ns2:idCondicion>{fn:data($CrearLineaCreditoRequest/ns1:LineaCredito/lin:Condicion/con:idCondicion)}</ns2:idCondicion>
    </ns2:CrearTreLineaCreditoInput>
};

local:func($CrearLineaCreditoRequest)