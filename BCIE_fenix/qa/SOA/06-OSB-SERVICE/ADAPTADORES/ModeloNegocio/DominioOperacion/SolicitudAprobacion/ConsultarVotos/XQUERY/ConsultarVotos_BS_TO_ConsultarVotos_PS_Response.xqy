xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarVotosEmitidos_BD";
(:: import schema at "../XSD/ConsultarVotosEmitidos_BD.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace sol = "http://www.bcie.org/SolicitudAprobacionBO";

declare variable $ConsultarVotosEmitidos_BDOutputCollection as element() (:: schema-element(ns1:ConsultarVotosEmitidos_BDOutputCollection) ::) external;

declare function local:func($ConsultarVotosEmitidos_BDOutputCollection as element() (:: schema-element(ns1:ConsultarVotosEmitidos_BDOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarVotosResponse) ::) {
    <ns2:ConsultarVotosResponse>
    {
    for $ConsultarVotos in $ConsultarVotosEmitidos_BDOutputCollection/ns1:ConsultarVotosEmitidos_BDOutput
      return
        <ns2:ListaVotosEmitidos>
            <sol:tipo_decision>{fn:data($ConsultarVotos/ns1:TIPO_DECISION)}</sol:tipo_decision>
            <sol:descripcion>{fn:data($ConsultarVotos/ns1:DESCRIPCION)}</sol:descripcion>
            <sol:usuario>{fn:data($ConsultarVotos/ns1:USUARIO)}</sol:usuario>
            <sol:fecha_hora>{fn:data($ConsultarVotos/ns1:FECHA_HORA)}</sol:fecha_hora>
            <sol:comentario>{fn:data($ConsultarVotos/ns1:COMENTARIO)}</sol:comentario>
            <sol:nombreUsuario>{fn:data($ConsultarVotos/ns1:NOMBRE_USUARIO)}</sol:nombreUsuario>
        </ns2:ListaVotosEmitidos>
    }
        <ns2:Resultado>
            <res:result>OK</res:result>
            {
            if (count($ConsultarVotosEmitidos_BDOutputCollection/ns1:ConsultarVotosEmitidos_BDOutput) > 0) then
            <res:message>Operacion Exitosa</res:message>
            else
             <res:message>No existen Registros</res:message>
             }
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:ConsultarVotosResponse>
};

local:func($ConsultarVotosEmitidos_BDOutputCollection)
