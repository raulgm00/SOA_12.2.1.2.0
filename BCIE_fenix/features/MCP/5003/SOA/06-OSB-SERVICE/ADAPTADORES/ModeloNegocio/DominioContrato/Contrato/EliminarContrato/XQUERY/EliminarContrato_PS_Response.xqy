xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ContratoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Contrato/V1/Schema/ContratoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";


declare function local:func() as element() (:: schema-element(ns1:EliminarContratoResponse) ::) {
    <ns1:EliminarContratoResponse>
     <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>La eliminación se ha realizado correctamente</res:message>
        </ns1:Resultado>
    </ns1:EliminarContratoResponse>
};

local:func()
