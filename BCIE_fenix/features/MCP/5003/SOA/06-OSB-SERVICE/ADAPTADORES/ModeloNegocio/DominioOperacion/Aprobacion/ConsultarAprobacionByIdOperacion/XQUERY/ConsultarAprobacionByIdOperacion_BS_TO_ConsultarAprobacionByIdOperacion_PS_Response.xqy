xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/AprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Aprobacion/V1/Schema/AprobacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultaraprobacionByIdOperacion_DB";
(:: import schema at "../XSD/ConsultaraprobacionByIdOperacion_DB.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace apr = "http://www.bcie.org/AprobacionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultaraprobacionByIdOperacion_DBOutputCollection as element() (:: schema-element(ns1:ConsultaraprobacionByIdOperacion_DBOutputCollection) ::) external;

declare function local:func($ConsultaraprobacionByIdOperacion_DBOutputCollection as element() (:: schema-element(ns1:ConsultaraprobacionByIdOperacion_DBOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarAprobacionByIdOperacionResponse) ::) {
    <ns2:ConsultarAprobacionByIdOperacionResponse>
        <ns2:Aprobacion>
            <apr:idAprobacion>{fn:data($ConsultaraprobacionByIdOperacion_DBOutputCollection/ns1:ConsultaraprobacionByIdOperacion_DBOutput/ns1:ID)}</apr:idAprobacion>
            <apr:idOperacion></apr:idOperacion>
            <apr:idReunionAprobacion>{fn:data($ConsultaraprobacionByIdOperacion_DBOutputCollection/ns1:ConsultaraprobacionByIdOperacion_DBOutput/ns1:ID_DECISION_REUNION)}</apr:idReunionAprobacion>
            <apr:fechaAprobacion>{fn:data($ConsultaraprobacionByIdOperacion_DBOutputCollection/ns1:ConsultaraprobacionByIdOperacion_DBOutput/ns1:FECHA_APROBACION)}</apr:fechaAprobacion>
            <apr:numeroResolucion>{fn:data($ConsultaraprobacionByIdOperacion_DBOutputCollection/ns1:ConsultaraprobacionByIdOperacion_DBOutput/ns1:NUMERO_RESOLUCION)}</apr:numeroResolucion>
            <apr:resumen>{fn:data($ConsultaraprobacionByIdOperacion_DBOutputCollection/ns1:ConsultaraprobacionByIdOperacion_DBOutput/ns1:RESUMEN)}</apr:resumen>
            <apr:idMontoAprobacion>{fn:data($ConsultaraprobacionByIdOperacion_DBOutputCollection/ns1:ConsultaraprobacionByIdOperacion_DBOutput/ns1:ID_MONTO_APROBADO)}</apr:idMontoAprobacion>
            <apr:loginUsuario>
                <apr:usuario>{fn:data($ConsultaraprobacionByIdOperacion_DBOutputCollection/ns1:ConsultaraprobacionByIdOperacion_DBOutput/ns1:LOGIN_USUARIO)}</apr:usuario>
            </apr:loginUsuario>
            <apr:fechaRegistro>{fn:data($ConsultaraprobacionByIdOperacion_DBOutputCollection/ns1:ConsultaraprobacionByIdOperacion_DBOutput/ns1:FECHA_REGISTRO)}</apr:fechaRegistro>
        </ns2:Aprobacion>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>La consulta se ha realizado exitosamente</res:message>
        </ns2:Resultado>
    </ns2:ConsultarAprobacionByIdOperacionResponse>
};

local:func($ConsultaraprobacionByIdOperacion_DBOutputCollection)
