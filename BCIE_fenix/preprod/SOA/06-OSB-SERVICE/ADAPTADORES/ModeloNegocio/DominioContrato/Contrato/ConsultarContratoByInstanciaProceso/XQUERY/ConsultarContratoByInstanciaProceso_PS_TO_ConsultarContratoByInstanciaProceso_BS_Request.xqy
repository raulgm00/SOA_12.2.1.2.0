xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ContratoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Contrato/V1/Schema/ContratoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ConsultarContratoByIntanciaProceso";
(:: import schema at "../XSD/ConsultarContratoByIntanciaProceso_table.xsd" ::)

declare variable $ConsultarContratoByInstanciaProcesoRequest as element() (:: schema-element(ns1:ConsultarContratoByInstanciaProcesoRequest) ::) external;

declare function local:func($ConsultarContratoByInstanciaProcesoRequest as element() (:: schema-element(ns1:ConsultarContratoByInstanciaProcesoRequest) ::)) as element() (:: schema-element(ns2:ConsultarContratoByIntanciaProcesoSelect_instanciaProcesoInputParameters) ::) {
    <ns2:ConsultarContratoByIntanciaProcesoSelect_instanciaProcesoInputParameters>
        <ns2:instanciaProceso>{fn:data($ConsultarContratoByInstanciaProcesoRequest/ns1:Instancia_proceso)}</ns2:instanciaProceso>
    </ns2:ConsultarContratoByIntanciaProcesoSelect_instanciaProcesoInputParameters>
};

local:func($ConsultarContratoByInstanciaProcesoRequest)
