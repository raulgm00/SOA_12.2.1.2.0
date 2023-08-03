xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/AprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Aprobacion/V1/Schema/AprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ActualizarAprobacion_DB";
(:: import schema at "../XSD/ActualizarAprobacion_DB_table.xsd" ::)

declare namespace apr = "http://www.bcie.org/AprobacionBO";

declare variable $ActualizarAprobacionRequest as element() (:: schema-element(ns1:ActualizarAprobacionRequest) ::) external;

declare function local:func($ActualizarAprobacionRequest as element() (:: schema-element(ns1:ActualizarAprobacionRequest) ::)) as element() (:: schema-element(ns2:AprobacionCollection) ::) {
    <ns2:AprobacionCollection>
        <ns2:Aprobacion>
            <ns2:id>{fn:data($ActualizarAprobacionRequest/ns1:Aprobacion/apr:idAprobacion)}</ns2:id>
            <ns2:idDecisionReunion>{fn:data($ActualizarAprobacionRequest/ns1:Aprobacion/apr:idReunionAprobacion)}</ns2:idDecisionReunion>
            {
                if ($ActualizarAprobacionRequest/ns1:Aprobacion/apr:fechaAprobacion)
                then <ns2:fechaAprobacion>{fn:data($ActualizarAprobacionRequest/ns1:Aprobacion/apr:fechaAprobacion)}</ns2:fechaAprobacion>
                else ()
            }
            {
                if ($ActualizarAprobacionRequest/ns1:Aprobacion/apr:numeroResolucion)
                then <ns2:numeroResolucion>{fn:data($ActualizarAprobacionRequest/ns1:Aprobacion/apr:numeroResolucion)}</ns2:numeroResolucion>
                else ()
            }
            {
                if ($ActualizarAprobacionRequest/ns1:Aprobacion/apr:resumen)
                then <ns2:resumen>{fn:data($ActualizarAprobacionRequest/ns1:Aprobacion/apr:resumen)}</ns2:resumen>
                else ()
            }
            <ns2:loginUsuario>{fn:data($ActualizarAprobacionRequest/ns1:Aprobacion/apr:loginUsuario/apr:usuario)}</ns2:loginUsuario>
            {
                if ($ActualizarAprobacionRequest/ns1:Aprobacion/apr:idMontoAprobacion)
                then <ns2:idMontoAprobado>{fn:data($ActualizarAprobacionRequest/ns1:Aprobacion/apr:idMontoAprobacion)}</ns2:idMontoAprobado>
                else ()
            }
            
            
        </ns2:Aprobacion>
    </ns2:AprobacionCollection>
};

local:func($ActualizarAprobacionRequest)
