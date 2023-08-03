xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ContratoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Contrato/V1/Schema/ContratoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ActualizarContrato_DB";
(:: import schema at "../XSD/ActualizarContrato_DB_table.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare variable $inActualizarContrato as element() (:: schema-element(ns1:ActualizarContratoRequest) ::) external;

declare function local:func($inActualizarContrato as element() (:: schema-element(ns1:ActualizarContratoRequest) ::)) as element() (:: schema-element(ns2:ContratoCollection) ::) {
    <ns2:ContratoCollection>
        <ns2:Contrato>
            <ns2:id>{fn:data($inActualizarContrato/ns1:Contrato/con:idContrato)}</ns2:id>
            <ns2:idOperacion>{fn:data($inActualizarContrato/ns1:Contrato/con:idOperacion)}</ns2:idOperacion>
            <ns2:instanciaProceso>{fn:data($inActualizarContrato/ns1:Contrato/con:instanciaProceso)}</ns2:instanciaProceso>
            <ns2:fechaFirma>{fn:data($inActualizarContrato/ns1:Contrato/con:fechaFirma)}</ns2:fechaFirma>
            <ns2:fechaVigencia>{fn:data($inActualizarContrato/ns1:Contrato/con:fechaVigencia)}</ns2:fechaVigencia>
            <ns2:idMontoEscriturado>{fn:data($inActualizarContrato/ns1:Contrato/con:MontoEscriturado)}</ns2:idMontoEscriturado>
            <ns2:numeroCustodia></ns2:numeroCustodia>
            <ns2:cuentaCliente>{fn:data($inActualizarContrato/ns1:Contrato/con:cuentaCliente/con:cuentaCliente)}</ns2:cuentaCliente>
             <ns2:fechaValor>{fn:data($inActualizarContrato/ns1:Contrato/con:fechaValor)}</ns2:fechaValor>
        </ns2:Contrato>
    </ns2:ContratoCollection>
};

local:func($inActualizarContrato)