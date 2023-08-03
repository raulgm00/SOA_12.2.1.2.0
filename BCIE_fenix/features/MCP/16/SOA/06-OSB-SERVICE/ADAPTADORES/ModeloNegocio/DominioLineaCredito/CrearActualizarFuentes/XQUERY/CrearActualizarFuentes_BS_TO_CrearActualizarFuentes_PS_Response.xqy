xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CrearActualizarFuentes_BD";
(:: import schema at "../XSD/CrearActualizarFuentes_BD_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:CrearActualizarFuentesResponse) ::) {
    <ns2:CrearActualizarFuentesResponse>
        <ns2:Resultado>
            <res:result>{
            if ($OutputParameters/ns1:P_CODIGO_RES = 0)
            then 'OK'
            else 'ERROR'
            }</res:result>
            <res:message>{fn:data($OutputParameters/ns1:P_MENSAJE)}</res:message>
        </ns2:Resultado>
    </ns2:CrearActualizarFuentesResponse>
};

local:func($OutputParameters)
