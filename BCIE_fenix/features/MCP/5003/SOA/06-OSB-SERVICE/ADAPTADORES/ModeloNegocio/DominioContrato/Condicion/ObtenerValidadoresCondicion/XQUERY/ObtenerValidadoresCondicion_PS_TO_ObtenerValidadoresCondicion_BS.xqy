xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ObtenerValidadoresCondicion_BS";
(:: import schema at "../XSD/ObtenerValidadoresCondicion_BS_sp.xsd" ::)

declare variable $ObtenerValidadoresCondicionRequest as element() (:: schema-element(ns1:ObtenerValidadoresActivosRequest) ::) external;

declare function local:func($ObtenerValidadoresCondicionRequest as element() (:: schema-element(ns1:ObtenerValidadoresActivosRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_AGRUPADOR>{fn:data($ObtenerValidadoresCondicionRequest/ns1:idAgrupador)}</ns2:P_AGRUPADOR>
        <ns2:P_OPERACION>{fn:data(xs:decimal($ObtenerValidadoresCondicionRequest/ns1:idOperacion))}</ns2:P_OPERACION>
    </ns2:InputParameters>
};

local:func($ObtenerValidadoresCondicionRequest)
