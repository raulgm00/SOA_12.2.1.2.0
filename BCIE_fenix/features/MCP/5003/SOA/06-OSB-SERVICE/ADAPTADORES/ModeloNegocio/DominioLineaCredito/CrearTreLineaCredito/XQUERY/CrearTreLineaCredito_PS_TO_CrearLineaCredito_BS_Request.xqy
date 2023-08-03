xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearTreLineaCredito";
(:: import schema at "../XSD/CrearTreLineaCredito_table.xsd" ::)

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare variable $CrearLineaCreditoRequest as element() (:: schema-element(ns1:CrearTreLineaCreditoRequest) ::) external;

declare function local:func($CrearLineaCreditoRequest as element() (:: schema-element(ns1:CrearTreLineaCreditoRequest) ::)) as element() (:: schema-element(ns2:TreLineaCreditoTccCollection) ::) {
    <ns2:LineaCreditoCollection>
        <ns2:TreLineaCreditoTcc>
            {
                if ($CrearLineaCreditoRequest/ns1:LineaCredito/lin:idLineaCredito)
                then <ns2:idLineaCredito>{fn:data($CrearLineaCreditoRequest/ns1:LineaCredito/lin:idLineaCredito)}</ns2:idLineaCredito>
                else ()
            }
            <ns2:idTermino>{fn:data($CrearLineaCreditoRequest/ns1:LineaCredito/lin:Termino/ter:idTermino)}</ns2:idTermino>
            <ns2:idComision>{fn:data($CrearLineaCreditoRequest/ns1:LineaCredito/lin:Comision/com:idComision)}</ns2:idComision>
            <ns2:idCondicion>{fn:data($CrearLineaCreditoRequest/ns1:LineaCredito/lin:Condicion/con1:idCondicion)}</ns2:idCondicion>
            <ns2:fechaRegistro>{fn:current-dateTime()}</ns2:fechaRegistro>
            <ns2:banEstatus>1</ns2:banEstatus>
        </ns2:TreLineaCreditoTcc>
    </ns2:LineaCreditoCollection>
};

local:func($CrearLineaCreditoRequest)