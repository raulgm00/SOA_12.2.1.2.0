xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/Validar_Condiciones_Desembolso";
(:: import schema at "../XSD/Validar_Condiciones_Desembolso_sp.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $ValidarCondicionesDesembolsoRequest as element() (:: schema-element(ns1:ValidarCondicionesDesembolsoRequest) ::) external;

declare function local:func($ValidarCondicionesDesembolsoRequest as element() (:: schema-element(ns1:ValidarCondicionesDesembolsoRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:PNID_CONTRATO_DESEMBOLSO>{fn:data($ValidarCondicionesDesembolsoRequest/ns1:IdContratoDesembolso)}</ns2:PNID_CONTRATO_DESEMBOLSO> 
    </ns2:InputParameters>
};

local:func($ValidarCondicionesDesembolsoRequest)
