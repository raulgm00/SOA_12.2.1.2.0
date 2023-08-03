xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $InicioCancelarInstanciaRequest as element() (:: schema-element(ns1:InicioCancelarInstanciaRequest) ::) external;

declare function local:func($InicioCancelarInstanciaRequest as element() (:: schema-element(ns1:InicioCancelarInstanciaRequest) ::)) as element() (:: schema-element(ns2:ConsultarAgrupadorByCondicionRequest) ::) {
    <ns2:ConsultarAgrupadorByCondicionRequest>
        <ns2:idContrato>{fn:data($InicioCancelarInstanciaRequest/ns1:Header/ns3:ParameterType[ns3:parameterName='ID_CONTRATO_DESEMBOLSO']/ns3:parameterValue)}</ns2:idContrato>
    </ns2:ConsultarAgrupadorByCondicionRequest>
};

local:func($InicioCancelarInstanciaRequest)
