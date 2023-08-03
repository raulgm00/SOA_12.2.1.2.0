xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ActualizarTBILineaCredito";
(:: import schema at "../XSD/TBILineaCredito.xsd" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare variable $TBILineaCreditoRequest as element() (:: schema-element(ns1:TBILineaCreditoActualizarRequest) ::) external;

declare function local:func($TBILineaCreditoRequest as element() (:: schema-element(ns1:TBILineaCreditoActualizarRequest) ::)) as element() (:: element(*, ns2:TBLineaCreditoType) ::) {
    <ns2:TBLineaCreditoType>
        <ns2:id>{fn:data($TBILineaCreditoRequest/ns1:TbiLineaCredito/lin:id)}</ns2:id>
        {
            if ($TBILineaCreditoRequest/ns1:TbiLineaCredito/lin:idContrato)
            then <ns2:idContrato>{fn:data($TBILineaCreditoRequest/ns1:TbiLineaCredito/lin:idContrato)}</ns2:idContrato>
            else ()
        }
        {
            if ($TBILineaCreditoRequest/ns1:TbiLineaCredito/lin:idLineaCredito)
            then <ns2:idLineaCredito>{fn:data($TBILineaCreditoRequest/ns1:TbiLineaCredito/lin:idLineaCredito)}</ns2:idLineaCredito>
            else ()
        }
        {
            if ($TBILineaCreditoRequest/ns1:TbiLineaCredito/lin:idTareaBpm)
            then <ns2:idTareaBpm>{fn:data($TBILineaCreditoRequest/ns1:TbiLineaCredito/lin:idTareaBpm)}</ns2:idTareaBpm>
            else ()
        }
        {
            if ($TBILineaCreditoRequest/ns1:TbiLineaCredito/lin:instanciaProceso)
            then <ns2:instanciaProceso>{fn:data($TBILineaCreditoRequest/ns1:TbiLineaCredito/lin:instanciaProceso)}</ns2:instanciaProceso>
            else ()
        }
        {
            if ($TBILineaCreditoRequest/ns1:TbiLineaCredito/lin:estadoLineaCredito)
            then <ns2:estadoLineaCredito>{fn:data($TBILineaCreditoRequest/ns1:TbiLineaCredito/lin:estadoLineaCredito)}</ns2:estadoLineaCredito>
            else ()
        }
        {
            if ($TBILineaCreditoRequest/ns1:TbiLineaCredito/lin:usuario)
            then <ns2:usuario>{fn:data($TBILineaCreditoRequest/ns1:TbiLineaCredito/lin:usuario)}</ns2:usuario>
            else ()
        }
        {
            if ($TBILineaCreditoRequest/ns1:TbiLineaCredito/lin:fechaRegistro)
            then <ns2:fechaRegistro>{fn:data($TBILineaCreditoRequest/ns1:TbiLineaCredito/lin:fechaRegistro)}</ns2:fechaRegistro>
            else ()
        }
        {
            if ($TBILineaCreditoRequest/ns1:TbiLineaCredito/lin:banEstatus)
            then <ns2:banEstatus>{fn:data($TBILineaCreditoRequest/ns1:TbiLineaCredito/lin:banEstatus)}</ns2:banEstatus>
            else ()
        }
        {
            if ($TBILineaCreditoRequest/ns1:TbiLineaCredito/lin:numeroLineaCredito)
            then <ns2:numeroLineaCredito>{fn:data($TBILineaCreditoRequest/ns1:TbiLineaCredito/lin:numeroLineaCredito)}</ns2:numeroLineaCredito>
            else ()
        }
    </ns2:TBLineaCreditoType>
};

local:func($TBILineaCreditoRequest)