xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/AprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Aprobacion/V1/Schema/AprobacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarAprobacionById";
(:: import schema at "../XSD/ConsultarAprobacionById.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace apr = "http://www.bcie.org/AprobacionBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarAprobacionByIdOutputCollection as element() (:: schema-element(ns1:ConsultarAprobacionByIdOutputCollection) ::) external;

declare function local:func($ConsultarAprobacionByIdOutputCollection as element() (:: schema-element(ns1:ConsultarAprobacionByIdOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarAprobacionByIdResponse) ::) {
    <ns2:ConsultarAprobacionByIdResponse>
        <ns2:Aprobacion>
            <apr:idAprobacion>{fn:data($ConsultarAprobacionByIdOutputCollection/ns1:ConsultarAprobacionByIdOutput/ns1:ID)}</apr:idAprobacion>
            <apr:idReunionAprobacion>{fn:data($ConsultarAprobacionByIdOutputCollection/ns1:ConsultarAprobacionByIdOutput/ns1:ID_DECISION_REUNION)}</apr:idReunionAprobacion>
            <apr:fechaAprobacion>{fn:data($ConsultarAprobacionByIdOutputCollection/ns1:ConsultarAprobacionByIdOutput/ns1:FECHA_APROBACION)}</apr:fechaAprobacion>
            <apr:numeroResolucion>{fn:data($ConsultarAprobacionByIdOutputCollection/ns1:ConsultarAprobacionByIdOutput/ns1:NUMERO_RESOLUCION)}</apr:numeroResolucion>
            <apr:resumen>{fn:data($ConsultarAprobacionByIdOutputCollection/ns1:ConsultarAprobacionByIdOutput/ns1:RESUMEN)}</apr:resumen>
            <apr:idMontoAprobacion>{fn:data($ConsultarAprobacionByIdOutputCollection/ns1:ConsultarAprobacionByIdOutput/ns1:ID_MONTO)}</apr:idMontoAprobacion>
            <apr:MontoAprobacion>{fn:data($ConsultarAprobacionByIdOutputCollection/ns1:ConsultarAprobacionByIdOutput/ns1:MONTO)}</apr:MontoAprobacion>
            <apr:loginUsuario>
                <apr:usuario></apr:usuario>
                <apr:nombreUsuario>{fn:data($ConsultarAprobacionByIdOutputCollection/ns1:ConsultarAprobacionByIdOutput/ns1:LOGIN_USUARIO)}</apr:nombreUsuario>
            </apr:loginUsuario>
        </ns2:Aprobacion>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>La consulta se ha realizado correctamente</res:message>
        </ns2:Resultado>
    </ns2:ConsultarAprobacionByIdResponse>
};

local:func($ConsultarAprobacionByIdOutputCollection)
