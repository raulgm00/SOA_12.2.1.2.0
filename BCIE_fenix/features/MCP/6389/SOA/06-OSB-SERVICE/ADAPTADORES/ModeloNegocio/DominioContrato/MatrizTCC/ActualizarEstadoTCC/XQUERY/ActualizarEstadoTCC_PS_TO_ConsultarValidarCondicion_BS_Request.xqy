xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarValidacionCondicionByIdCondicion";
(:: import schema at "../../../Condicion/ConsultarValidacionCondicionByIdCondicion/XSD/ConsultarValidacionCondicionByIdCondicion.xsd" ::)

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare variable $ActualizarEstadoTCCRequest as element() (:: schema-element(ns1:ActualizarEstadoTCCRequest) ::) external;
declare variable $tccIndex as xs:int external;

declare function local:func($ActualizarEstadoTCCRequest as element() (:: schema-element(ns1:ActualizarEstadoTCCRequest) ::),$tccIndex as xs:int) as element() (:: schema-element(ns2:ConsultarValidacionCondicionByIdCondicionInput) ::) {
    <ns2:ConsultarValidacionCondicionByIdCondicionInput>
        <ns2:idCondicion>{fn:data($ActualizarEstadoTCCRequest/ns1:ListaTCC/mat:TCC[$tccIndex]/mat:id)}</ns2:idCondicion>
    </ns2:ConsultarValidacionCondicionByIdCondicionInput>
};

local:func($ActualizarEstadoTCCRequest,$tccIndex)
