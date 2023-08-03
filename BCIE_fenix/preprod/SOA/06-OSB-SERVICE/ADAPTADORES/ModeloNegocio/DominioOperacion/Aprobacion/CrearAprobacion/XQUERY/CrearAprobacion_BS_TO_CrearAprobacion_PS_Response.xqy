xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/AprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Aprobacion/V1/Schema/AprobacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearAprobacion";
(:: import schema at "../XSD/CrearAprobacion_table.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace apr = "http://www.bcie.org/AprobacionBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare variable $AprobacionCollection as element() (:: schema-element(ns1:AprobacionCollection) ::) external;

declare function local:func($AprobacionCollection as element() (:: schema-element(ns1:AprobacionCollection) ::)) as element() (:: schema-element(ns2:CrearAprobacionResponse) ::) {
    <ns2:CrearAprobacionResponse>
        <ns2:Aprobacion>
            <apr:idAprobacion>{fn:data($AprobacionCollection/ns1:Aprobacion/ns1:id)}</apr:idAprobacion>
            <apr:idOperacion></apr:idOperacion>
            <apr:idReunionAprobacion>{fn:data($AprobacionCollection/ns1:Aprobacion/ns1:idDecisionReunion)}</apr:idReunionAprobacion>
            <apr:fechaAprobacion>{fn:data($AprobacionCollection/ns1:Aprobacion/ns1:fechaAprobacion)}</apr:fechaAprobacion>
            <apr:numeroResolucion>{fn:data($AprobacionCollection/ns1:Aprobacion/ns1:numeroResolucion)}</apr:numeroResolucion>
            <apr:resumen>{fn:data($AprobacionCollection/ns1:Aprobacion/ns1:resumen)}</apr:resumen>
            <apr:idMontoAprobacion>{fn:data($AprobacionCollection/ns1:Aprobacion/ns1:idMontoAprobado)}</apr:idMontoAprobacion>
            <apr:loginUsuario>
                <apr:nombreUsuario>{fn:data($AprobacionCollection/ns1:Aprobacion/ns1:loginUsuario)}</apr:nombreUsuario>
            </apr:loginUsuario>
            <apr:fechaRegistro>{fn:data($AprobacionCollection/ns1:Aprobacion/ns1:fechaRegistro)}</apr:fechaRegistro>
        </ns2:Aprobacion>
          <ns1:Resultado>
            <res:result >{fn:data($AprobacionCollection/ns1:Aprobacion/ns1:idMontoAprobado)}</res:result>
            <res:message>La inserción se ha realizado correctamente.</res:message>
        </ns1:Resultado>
    </ns2:CrearAprobacionResponse>
};

local:func($AprobacionCollection)
