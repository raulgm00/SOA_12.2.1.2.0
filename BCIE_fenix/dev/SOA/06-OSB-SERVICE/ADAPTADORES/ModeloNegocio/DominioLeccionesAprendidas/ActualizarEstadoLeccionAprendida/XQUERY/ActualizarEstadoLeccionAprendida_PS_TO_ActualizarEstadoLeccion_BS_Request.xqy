xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LeccionAprendidaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLeccionesAprendidas/LeccionAprendida/V1/Schema/LeccionAprendidaMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ActualizarEstadoLeccion";
(:: import schema at "../XSD/ActualizarEstadoLeccion_table.xsd" ::)

declare variable $ActualizarEstadoLeccionAprendidaRequest as element() (:: schema-element(ns1:ActualizarEstadoLeccionAprendidaRequest) ::) external;

declare function local:func($ActualizarEstadoLeccionAprendidaRequest as element() (:: schema-element(ns1:ActualizarEstadoLeccionAprendidaRequest) ::)) as element() (:: schema-element(ns2:LeccionCollection) ::) {
    <ns2:LeccionCollection>
        <ns2:Leccion>
            <ns2:idLeccion>{fn:data($ActualizarEstadoLeccionAprendidaRequest/ns1:idLeccionAprendida)}</ns2:idLeccion>
            <ns2:idTcaEstadoLeccion>{fn:data($ActualizarEstadoLeccionAprendidaRequest/ns1:idEstado)}</ns2:idTcaEstadoLeccion>
        </ns2:Leccion>
    </ns2:LeccionCollection>
};

local:func($ActualizarEstadoLeccionAprendidaRequest)
