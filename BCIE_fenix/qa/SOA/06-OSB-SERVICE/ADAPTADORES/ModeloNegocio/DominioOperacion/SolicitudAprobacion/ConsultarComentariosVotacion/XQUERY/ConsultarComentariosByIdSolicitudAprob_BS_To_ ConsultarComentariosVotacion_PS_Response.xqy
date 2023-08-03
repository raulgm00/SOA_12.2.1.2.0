xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarComentariosDB";
(:: import schema at "../XSD/ConsultarComentariosByIdSolicitudAproDB.xsd" ::)

declare namespace sol = "http://www.bcie.org/SolicitudAprobacionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarComentariosDBOutputCollection as element() (:: schema-element(ns1:ConsultarComentariosDBOutputCollection) ::) external;

declare function local:func($ConsultarComentariosDBOutputCollection as element() (:: schema-element(ns1:ConsultarComentariosDBOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarComentariosVotacionResponse) ::) {
    <ns2:ConsultarComentariosVotacionResponse>
    {
    for $i in $ConsultarComentariosDBOutputCollection/ns1:ConsultarComentariosDBOutput
    return
        <ns2:ListaComentariosVotacion>
            <sol:idSolicitudAprobacion>{fn:data($i/ns1:ID_SOLICITUD_APROBACION)}</sol:idSolicitudAprobacion>
            <sol:idOperacion>{fn:data($i/ns1:ID_OPERACION)}</sol:idOperacion>
            <sol:fechaHora>{fn:data($i/ns1:FECHA_HORA)}</sol:fechaHora>
            <sol:idComentario>{fn:data($i/ns1:ID_COMENTARIO)}</sol:idComentario>
            <sol:comentario>{fn:data($i/ns1:COMENTARIO)}</sol:comentario>
            <sol:nombreUsuario>{fn:data($i/ns1:NOMBRE_USUARIO)}</sol:nombreUsuario>
        </ns2:ListaComentariosVotacion>
        }
    <ns2:Resultado>
        <res:result>OK</res:result>
        {if (count($ConsultarComentariosDBOutputCollection/ns1:ConsultarComentariosDBOutput)> 0)then
        <res:message>Operacion Exitosa</res:message>
        else
        <res:message>No existen registros</res:message>
        }
        <res:error>
            <err:errorCode></err:errorCode>
            <err:errorDescription></err:errorDescription>
            <err:errorType></err:errorType>
        </res:error>
    </ns2:Resultado>
    </ns2:ConsultarComentariosVotacionResponse>
};

local:func($ConsultarComentariosDBOutputCollection)
