xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/EquipoTrabajoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearEquipoTrabajo/V1/Schema/CrearEquipoTrabajoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";


declare function local:func() as element() (:: schema-element(ns1:responseCrearEquipoTrabajoMessage) ::) {
    <ns1:responseCrearEquipoTrabajoMessage>
        <ns1:Result>
            <res:result>OK</res:result>
            <res:message>Inserción realizada correctamente</res:message>
        </ns1:Result>
    </ns1:responseCrearEquipoTrabajoMessage>
};

local:func()
