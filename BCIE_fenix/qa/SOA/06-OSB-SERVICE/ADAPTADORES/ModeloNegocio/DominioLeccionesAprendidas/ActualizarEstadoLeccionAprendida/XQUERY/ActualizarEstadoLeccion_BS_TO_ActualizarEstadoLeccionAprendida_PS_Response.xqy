xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LeccionAprendidaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLeccionesAprendidas/LeccionAprendida/V1/Schema/LeccionAprendidaMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";


declare function local:func() as element() (:: schema-element(ns1:ActualizarEstadoLeccionAprendidaResponse) ::) {
    <ns1:ActualizarEstadoLeccionAprendidaResponse>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Estado de Leccion Aprendida Actualizado</res:message>
        </ns1:Resultado>
    </ns1:ActualizarEstadoLeccionAprendidaResponse>
};

local:func()
