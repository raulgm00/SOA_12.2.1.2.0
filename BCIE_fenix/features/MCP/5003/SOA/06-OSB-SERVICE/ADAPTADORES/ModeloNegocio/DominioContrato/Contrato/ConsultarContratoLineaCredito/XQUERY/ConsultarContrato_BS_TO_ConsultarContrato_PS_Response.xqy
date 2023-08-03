xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ContratoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Contrato/V1/Schema/ContratoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarContrato";
(:: import schema at "../XSD/ConsultarContrato.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare variable $responseContrato as element() (:: schema-element(ns1:ConsultarContratoOutputCollection) ::) external;

declare function local:func($responseContrato as element() (:: schema-element(ns1:ConsultarContratoOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarContratoResponse) ::) {
    <ns2:ConsultarContratoResponse>
        <ns2:clienteContrato>
            <con:idContrato>{fn:data($responseContrato/ns1:ConsultarContratoOutput/ns1:ID)}</con:idContrato>
            <con:idOperacion>{fn:data($responseContrato/ns1:ConsultarContratoOutput/ns1:ID_OPERACION)}</con:idOperacion>
            <con:fechaFirma>{fn:data($responseContrato/ns1:ConsultarContratoOutput/ns1:FECHA_FIRMA)}</con:fechaFirma>
            <con:fechaVigencia>{fn:data($responseContrato/ns1:ConsultarContratoOutput/ns1:FECHA_VIGENCIA)}</con:fechaVigencia>
            <con:idTipoMonedaMontoEscriturado>{fn:data($responseContrato/ns1:ConsultarContratoOutput/ns1:ID_TCA_TIPO_MONEDA)}</con:idTipoMonedaMontoEscriturado>
            <con:MontoEscriturado>{fn:data($responseContrato/ns1:ConsultarContratoOutput/ns1:MONTO)}</con:MontoEscriturado>
            <con:instanciaProceso>{fn:data($responseContrato/ns1:ConsultarContratoOutput/ns1:INSTANCIA_PROCESO)}</con:instanciaProceso>
            <con:fechaValor>{fn:data($responseContrato/ns1:ConsultarContratoOutput/ns1:FECHA_VALOR)}</con:fechaValor>
            </ns2:clienteContrato>
    </ns2:ConsultarContratoResponse>
};

local:func($responseContrato)