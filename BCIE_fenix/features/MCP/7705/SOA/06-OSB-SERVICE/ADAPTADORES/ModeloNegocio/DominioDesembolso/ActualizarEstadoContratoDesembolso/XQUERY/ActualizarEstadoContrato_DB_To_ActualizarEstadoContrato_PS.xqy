xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ActualizarEstadoContratoDesembolso_DB";
(:: import schema at "../XSD/ActualizarEstadoContratoDesembolso_DB_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ActualizarEstadoContratoDesembolsoResponse) ::) {
    <ns2:ActualizarEstadoContratoDesembolsoResponse>
        <ns2:resultado>
            <res:result>OK</res:result>
            <res:message>Actualizacion Satisfactoria</res:message>
        </ns2:resultado>
        <ns2:respuestaDB>{fn:data($OutputParameters/ns1:P_RESULTADO)}</ns2:respuestaDB>
    </ns2:ActualizarEstadoContratoDesembolsoResponse>
};

local:func($OutputParameters)
