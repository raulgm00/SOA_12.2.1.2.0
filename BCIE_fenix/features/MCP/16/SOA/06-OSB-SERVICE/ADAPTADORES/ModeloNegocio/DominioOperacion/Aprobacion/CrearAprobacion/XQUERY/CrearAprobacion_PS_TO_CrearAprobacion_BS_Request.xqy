xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/AprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Aprobacion/V1/Schema/AprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearAprobacion";
(:: import schema at "../XSD/CrearAprobacion_table.xsd" ::)

declare namespace apr = "http://www.bcie.org/AprobacionBO";

declare variable $CrearAprobacionRequest as element() (:: schema-element(ns1:CrearAprobacionRequest) ::) external;

declare function local:func($CrearAprobacionRequest as element() (:: schema-element(ns1:CrearAprobacionRequest) ::)) as element() (:: schema-element(ns2:AprobacionCollection) ::) {
    <ns2:AprobacionCollection>
        
        <ns2:Aprobacion>
            <ns2:id></ns2:id>
            <ns2:idDecisionReunion>{fn:data($CrearAprobacionRequest/ns1:Aprobacion/apr:idReunionAprobacion)}</ns2:idDecisionReunion>
            {
                if ($CrearAprobacionRequest/ns1:Aprobacion/apr:fechaAprobacion)
                then <ns2:fechaAprobacion>{fn:data($CrearAprobacionRequest/ns1:Aprobacion/apr:fechaAprobacion)}</ns2:fechaAprobacion>
                else ()
            }
            {
                if ($CrearAprobacionRequest/ns1:Aprobacion/apr:numeroResolucion)
                then <ns2:numeroResolucion>{fn:data($CrearAprobacionRequest/ns1:Aprobacion/apr:numeroResolucion)}</ns2:numeroResolucion>
                else ()
            }
            {
                if ($CrearAprobacionRequest/ns1:Aprobacion/apr:resumen)
                then <ns2:resumen>{fn:data($CrearAprobacionRequest/ns1:Aprobacion/apr:resumen)}</ns2:resumen>
                else ()
            }
            <ns2:loginUsuario>{fn:data($CrearAprobacionRequest/ns1:Aprobacion/apr:loginUsuario/apr:usuario)}</ns2:loginUsuario>
            <ns2:fechaRegistro>{fn:current-dateTime()}</ns2:fechaRegistro>
            {
                if ($CrearAprobacionRequest/ns1:Aprobacion/apr:idMontoAprobacion)
                then <ns2:idMontoAprobado>{fn:data($CrearAprobacionRequest/ns1:Aprobacion/apr:idMontoAprobacion)}</ns2:idMontoAprobado>
                else ()
            }
        </ns2:Aprobacion>
    </ns2:AprobacionCollection>
};

local:func($CrearAprobacionRequest)