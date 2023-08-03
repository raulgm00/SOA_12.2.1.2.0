xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";


declare function local:func() as element() (:: schema-element(ns1:NotificarEliminacionEvidenciaResponse) ::) {
    <ns1:NotificarEliminacionEvidenciaResponse>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Notificación Exitosa</res:message>
        </ns1:Resultado>
    </ns1:NotificarEliminacionEvidenciaResponse>
};

local:func()
