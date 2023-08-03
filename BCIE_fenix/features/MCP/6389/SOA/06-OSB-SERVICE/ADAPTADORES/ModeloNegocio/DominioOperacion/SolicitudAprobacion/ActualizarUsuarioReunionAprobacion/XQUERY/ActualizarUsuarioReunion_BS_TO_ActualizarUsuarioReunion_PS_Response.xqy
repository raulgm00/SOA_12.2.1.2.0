xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ActualizarUsuarioReunionAprob";
(:: import schema at "../XSD/ActualizarUsuarioReunionAprob.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ActualizarUsuarioReunionAprobInpu as element() (:: schema-element(ns1:ActualizarUsuarioReunionAprobInput) ::) external;

declare function local:func($ActualizarUsuarioReunionAprobInpu as element() (:: schema-element(ns1:ActualizarUsuarioReunionAprobInput) ::)) as element() (:: schema-element(ns2:ActualizarUsuarioReunionResponse) ::) {
    <ns2:ActualizarUsuarioReunionResponse>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Actualización  realizada exitosamente</res:message>
            
        </ns2:Resultado>
    </ns2:ActualizarUsuarioReunionResponse>
};

local:func($ActualizarUsuarioReunionAprobInpu)
