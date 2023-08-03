xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";


declare function local:func() as element() (:: schema-element(ns1:EliminarTreContrapartesTerminoResponse) ::) {
    <ns1:EliminarTreContrapartesTerminoResponse>
        <ns1:Respuesta>
            <res:result>OK</res:result>
            <res:message>Se ha realizado la eliminación</res:message>
        </ns1:Respuesta>
    </ns1:EliminarTreContrapartesTerminoResponse>
};

local:func()
