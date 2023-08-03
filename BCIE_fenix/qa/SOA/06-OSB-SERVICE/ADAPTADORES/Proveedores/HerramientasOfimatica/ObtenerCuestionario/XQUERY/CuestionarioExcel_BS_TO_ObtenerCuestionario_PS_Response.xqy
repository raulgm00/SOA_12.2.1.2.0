xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.com/Cuestionario/ObtenerCuestionario";
(:: import schema at "../XSD/CuestionarioExcel.xsd" ::)
declare namespace ns2="http://xmlns.bcie.com/CuestionarioMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Cuestionario/V1/Schema/CuestionarioMO.xsd" ::)

declare variable $CuestionarioResponse as element() (:: schema-element(ns1:crearExcelResponse) ::) external;

declare function local:func($CuestionarioResponse as element() (:: schema-element(ns1:crearExcelResponse) ::)) as element() (:: schema-element(ns2:ObtenerCuestionarioResponse) ::) {
    <ns2:ObtenerCuestionarioResponse>
        <ns2:Cuestionario>{fn:data($CuestionarioResponse/return)}</ns2:Cuestionario>
    </ns2:ObtenerCuestionarioResponse>
};

local:func($CuestionarioResponse)