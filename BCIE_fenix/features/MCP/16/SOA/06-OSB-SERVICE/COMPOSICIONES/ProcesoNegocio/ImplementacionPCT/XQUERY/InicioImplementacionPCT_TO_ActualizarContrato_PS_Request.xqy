xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ActualizarContrato_DB";
(:: import schema at "../../../../ADAPTADORES/ModeloNegocio/DominioContrato/Contrato/ActualizarContrato/XSD/ActualizarContrato_DB_table.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare variable $outConsultarLineaCreditoContrato as element() (:: schema-element(ns1:ConsultarLineaCreditoFormalizacionResponse) ::) external;

declare function local:func($outConsultarLineaCreditoContrato as element() (:: schema-element(ns1:ConsultarLineaCreditoFormalizacionResponse) ::)) as element() (:: schema-element(ns2:ContratoCollection) ::) {
    <ns2:ContratoCollection>
        {
            for $clienteContrato in $outConsultarLineaCreditoContrato/ns1:clienteContrato
            return 
            <ns2:Contrato>
                <ns2:id>{fn:data($clienteContrato/con:idContrato)}</ns2:id>
                <ns2:idOperacion>{fn:data($outConsultarLineaCreditoContrato/ns1:clienteContrato[fn:count($outConsultarLineaCreditoContrato/ns1:clienteContrato)]/con:idOperacion)}</ns2:idOperacion>
                <ns2:instanciaProceso>{fn:data($outConsultarLineaCreditoContrato/ns1:clienteContrato[fn:count($outConsultarLineaCreditoContrato/ns1:clienteContrato)]/con:instanciaProceso)}</ns2:instanciaProceso>
                <ns2:fechaFirma>{fn:data($outConsultarLineaCreditoContrato/ns1:clienteContrato[fn:count($outConsultarLineaCreditoContrato/ns1:clienteContrato)]/con:fechaFirma)}</ns2:fechaFirma>
                <ns2:fechaVigencia>{fn:data($outConsultarLineaCreditoContrato/ns1:clienteContrato[fn:count($outConsultarLineaCreditoContrato/ns1:clienteContrato)]/con:fechaVigencia)}</ns2:fechaVigencia>
                <ns2:idMontoEscriturado>{fn:data($outConsultarLineaCreditoContrato/ns1:clienteContrato[fn:count($outConsultarLineaCreditoContrato/ns1:clienteContrato)]/con:MontoEscriturado)}</ns2:idMontoEscriturado>
            </ns2:Contrato>
        }
    </ns2:ContratoCollection>
};

local:func($outConsultarLineaCreditoContrato)
