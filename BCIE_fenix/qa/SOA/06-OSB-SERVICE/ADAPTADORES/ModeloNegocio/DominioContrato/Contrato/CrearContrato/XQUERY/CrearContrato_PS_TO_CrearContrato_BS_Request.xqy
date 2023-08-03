xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ContratoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Contrato/V1/Schema/ContratoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearContrato";
(:: import schema at "../XSD/CrearContrato_table.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare variable $CrearContratoRequest as element() (:: schema-element(ns1:CrearContratoRequest) ::) external;

declare function local:func($CrearContratoRequest as element() (:: schema-element(ns1:CrearContratoRequest) ::)) as element() (:: schema-element(ns2:ContratoCollection) ::) {
    <ns2:ContratoCollection>
        <ns2:Contrato>
            <ns2:idOperacion>{fn:data($CrearContratoRequest/ns1:Contrato/con:idOperacion)}</ns2:idOperacion>
            <ns2:instanciaProceso>{fn:data($CrearContratoRequest/ns1:Contrato/con:instanciaProceso)}</ns2:instanciaProceso>
            <ns2:fechaFirma>{fn:data($CrearContratoRequest/ns1:Contrato/con:fechaFirma)}</ns2:fechaFirma>
            <ns2:fechaVigencia>{fn:data($CrearContratoRequest/ns1:Contrato/con:fechaVigencia)}</ns2:fechaVigencia>
            <ns2:idMontoEscriturado>{fn:data($CrearContratoRequest/ns1:Contrato/con:MontoEscriturado)}</ns2:idMontoEscriturado>
            <ns2:cuentaCliente>{fn:data($CrearContratoRequest/ns1:Contrato/con:cuentaCliente/con:cuentaCliente)}</ns2:cuentaCliente></ns2:Contrato>
    </ns2:ContratoCollection>
};

local:func($CrearContratoRequest)
