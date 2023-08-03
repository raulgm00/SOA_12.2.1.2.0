xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ObtenerFactibilidadRequest as element() (:: schema-element(ns1:ObtenerFactibilidadRequest) ::) external;

declare function local:func($ObtenerFactibilidadRequest as element() (:: schema-element(ns1:ObtenerFactibilidadRequest) ::)) as element() (:: schema-element(ns1:ObtenerFactibilidadResponse) ::) {
    <ns1:ObtenerFactibilidadResponse>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>OperacionExitosa</res:message>
        </ns1:Resultado>
    </ns1:ObtenerFactibilidadResponse>
};

local:func($ObtenerFactibilidadRequest)