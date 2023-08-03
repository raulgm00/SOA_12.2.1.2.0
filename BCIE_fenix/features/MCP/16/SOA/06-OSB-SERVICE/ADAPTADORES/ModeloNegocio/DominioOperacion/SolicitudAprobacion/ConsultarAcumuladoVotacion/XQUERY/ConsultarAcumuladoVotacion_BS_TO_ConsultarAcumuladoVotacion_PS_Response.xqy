xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarVotacion_BD";
(:: import schema at "../XSD/ConsultarAcumuladoVotacion_BD.xsd" ::)

declare namespace sol = "http://www.bcie.org/SolicitudAprobacionBO";

declare variable $ConsultarVotacion_BDOutputCollection as element() (:: schema-element(ns1:ConsultarVotacion_BDOutputCollection) ::) external;

declare function local:func($ConsultarVotacion_BDOutputCollection as element() (:: schema-element(ns1:ConsultarVotacion_BDOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarAcumuladoVotacionResponse) ::) {
    <ns2:ConsultarAcumuladoVotacionResponse>
        {
            for $ConsultarVotacion_BDOutput in $ConsultarVotacion_BDOutputCollection/ns1:ConsultarVotacion_BDOutput
            return 
            <ns2:acumuladoVotacion>
                <sol:cantidadVotos>{fn:data($ConsultarVotacion_BDOutput/ns1:CANTIDAD_VOTOS)}</sol:cantidadVotos>
                <sol:descripcionDecision>{fn:data($ConsultarVotacion_BDOutput/ns1:DESCRIPCION)}</sol:descripcionDecision>
                <sol:tipoDecision>{xs:long($ConsultarVotacion_BDOutput/ns1:TIPO_DECISION)}</sol:tipoDecision></ns2:acumuladoVotacion>
        }
    </ns2:ConsultarAcumuladoVotacionResponse>
};

local:func($ConsultarVotacion_BDOutputCollection)
