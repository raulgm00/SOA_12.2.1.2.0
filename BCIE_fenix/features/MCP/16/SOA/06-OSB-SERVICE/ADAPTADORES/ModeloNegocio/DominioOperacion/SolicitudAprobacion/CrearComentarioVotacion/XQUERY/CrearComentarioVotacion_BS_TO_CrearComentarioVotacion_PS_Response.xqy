xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $idComentarioVotacion as xs:long external;

declare function local:func($idComentarioVotacion as xs:long) as element() (:: schema-element(ns1:CrearComentarioVotacionResponse) ::) {
    <ns1:CrearComentarioVotacionResponse>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>{concat('El comentario se insertó correctamente. Id. del Comentario: ', $idComentarioVotacion)}</res:message>
        </ns1:Resultado>
    </ns1:CrearComentarioVotacionResponse>
};

local:func($idComentarioVotacion)
