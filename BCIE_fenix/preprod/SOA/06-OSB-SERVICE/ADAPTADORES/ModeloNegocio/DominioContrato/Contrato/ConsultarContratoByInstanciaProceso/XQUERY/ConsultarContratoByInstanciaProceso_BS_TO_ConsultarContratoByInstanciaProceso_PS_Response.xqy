xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ContratoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Contrato/V1/Schema/ContratoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/ConsultarContratoByIntanciaProceso";
(:: import schema at "../XSD/ConsultarContratoByIntanciaProceso_table.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ContratoCollection as element() (:: schema-element(ns1:ContratoCollection) ::) external;

declare function local:func($ContratoCollection as element() (:: schema-element(ns1:ContratoCollection) ::)) as element() (:: schema-element(ns2:ConsultarContratoByInstanciaProcesoResponse) ::) {
    <ns2:ConsultarContratoByInstanciaProcesoResponse>
        <ns2:Contrato>
            <con:idContrato>{fn:data($ContratoCollection/ns1:Contrato/ns1:id)}</con:idContrato>
            <con:idOperacion>{fn:data($ContratoCollection/ns1:Contrato/ns1:idOperacion)}</con:idOperacion>
            <con:fechaFirma>{fn:data($ContratoCollection/ns1:Contrato/ns1:fechaFirma)}</con:fechaFirma>
            <con:fechaVigencia>{fn:data($ContratoCollection/ns1:Contrato/ns1:fechaVigencia)}</con:fechaVigencia>
            <con:MontoEscriturado>{fn:data($ContratoCollection/ns1:Contrato/ns1:idMontoEscriturado)}</con:MontoEscriturado>
            <con:cuentaCliente>
                <con:cuentaCliente>{fn:data($ContratoCollection/ns1:Contrato/ns1:cuentaCliente)}</con:cuentaCliente>
            </con:cuentaCliente>
            <con:instanciaProceso>{fn:data($ContratoCollection/ns1:Contrato/ns1:instanciaProceso)}</con:instanciaProceso>
        </ns2:Contrato>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>La consulta se ha realizado exitosamente</res:message>
        </ns2:Resultado>
    </ns2:ConsultarContratoByInstanciaProcesoResponse>
};

local:func($ContratoCollection)
