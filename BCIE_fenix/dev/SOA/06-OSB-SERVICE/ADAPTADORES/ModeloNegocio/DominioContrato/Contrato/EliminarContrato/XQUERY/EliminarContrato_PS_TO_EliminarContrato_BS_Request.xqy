xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ContratoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Contrato/V1/Schema/ContratoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/EliminarContrato";
(:: import schema at "../XSD/EliminarContrato_table.xsd" ::)

declare variable $EliminarContratoRequest as element() (:: schema-element(ns1:EliminarContratoRequest) ::) external;

declare function local:func($EliminarContratoRequest as element() (:: schema-element(ns1:EliminarContratoRequest) ::)) as element() (:: schema-element(ns2:ContratoCollection) ::) {
    <ns2:ContratoCollection>
        <ns2:Contrato>
            <ns2:id>{fn:data($EliminarContratoRequest/ns1:idContrato)}</ns2:id>
            <ns2:idOperacion></ns2:idOperacion>
            <ns2:instanciaProceso></ns2:instanciaProceso>
            <ns2:fechaFirma></ns2:fechaFirma>
            <ns2:fechaVigencia></ns2:fechaVigencia>
            <ns2:idMontoEscriturado></ns2:idMontoEscriturado>
            <ns2:numeroCustodia></ns2:numeroCustodia>
            <ns2:cuentaCliente></ns2:cuentaCliente>
        </ns2:Contrato>
    </ns2:ContratoCollection>
};

local:func($EliminarContratoRequest)
