xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";


declare function local:func() as element() (:: schema-element(ns1:EliminarTreTerminoResponse) ::) {
    <ns1:EliminarTreTerminoResponse>        
        <ns1:Rsultado>
            <res:result>OK</res:result>
            <res:message>La eliminacion se ha realizado correctamente</res:message>
        </ns1:Rsultado>
    </ns1:EliminarTreTerminoResponse>
};

local:func()
