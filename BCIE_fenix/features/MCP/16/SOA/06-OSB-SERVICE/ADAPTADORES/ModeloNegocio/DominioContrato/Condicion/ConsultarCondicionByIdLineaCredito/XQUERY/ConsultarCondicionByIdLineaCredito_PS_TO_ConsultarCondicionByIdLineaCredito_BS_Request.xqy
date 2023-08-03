xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarCondicionByIdLineaCredito";
(:: import schema at "../XSD/ConsultarCondicionByIdLineaCredito.xsd" ::)

declare variable $ConsultarCondicionByIdLineaCreditoRequest as element() (:: schema-element(ns1:ConsultarCondicionByIdLineaCreditoRequest) ::) external;

declare function local:func($ConsultarCondicionByIdLineaCreditoRequest as element() (:: schema-element(ns1:ConsultarCondicionByIdLineaCreditoRequest) ::)) as element() (:: schema-element(ns2:ConsultarCondicionByIdLineaCreditoInput) ::) {
    <ns2:ConsultarCondicionByIdLineaCreditoInput>
        <ns2:IDLINEACREDITO>{fn:data($ConsultarCondicionByIdLineaCreditoRequest/ns1:idLineaCredito)}</ns2:IDLINEACREDITO>
    </ns2:ConsultarCondicionByIdLineaCreditoInput>
};

local:func($ConsultarCondicionByIdLineaCreditoRequest)
