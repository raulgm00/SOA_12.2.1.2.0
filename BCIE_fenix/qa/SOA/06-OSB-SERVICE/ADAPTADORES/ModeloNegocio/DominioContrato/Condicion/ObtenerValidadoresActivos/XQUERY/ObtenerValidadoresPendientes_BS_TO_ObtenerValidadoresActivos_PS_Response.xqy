xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ObtenerValidadoresActivos";
(:: import schema at "../XSD/ObtenerValidadoresActivos.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ObtenerValidadoresResponse as element() (:: schema-element(ns1:ObtenerValidadoresActivosOutputCollection) ::) external;

declare function local:func($ObtenerValidadoresResponse as element() (:: schema-element(ns1:ObtenerValidadoresActivosOutputCollection) ::)) as element() (:: schema-element(ns2:ObtenerValidadoresActivosResponse) ::) {
    <ns2:ObtenerValidadoresActivosResponse>
        <ns2:numeroValidadores>{fn:data($ObtenerValidadoresResponse/ns1:ObtenerValidadoresActivosOutput[1]/ns1:VALIDADORES_PENDIENTES)}</ns2:numeroValidadores>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Resultado Exitoso</res:message>
        </ns2:Resultado>
    </ns2:ObtenerValidadoresActivosResponse>
};

local:func($ObtenerValidadoresResponse)