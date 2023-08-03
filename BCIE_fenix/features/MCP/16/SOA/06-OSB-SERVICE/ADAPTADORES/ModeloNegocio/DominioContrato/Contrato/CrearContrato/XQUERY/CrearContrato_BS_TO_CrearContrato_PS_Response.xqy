xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ContratoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Contrato/V1/Schema/ContratoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearContrato";
(:: import schema at "../XSD/CrearContrato_table.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare variable $ContratoCollection as element() (:: schema-element(ns1:ContratoCollection) ::) external;

declare function local:func($ContratoCollection as element() (:: schema-element(ns1:ContratoCollection) ::)) as element() (:: schema-element(ns2:CrearContratoResponse) ::) {
    <ns2:CrearContratoResponse>
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
           <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>La inserción se ha realizado correctamente.</res:message>
        </ns1:Resultado>
    </ns2:CrearContratoResponse>
};

local:func($ContratoCollection)
