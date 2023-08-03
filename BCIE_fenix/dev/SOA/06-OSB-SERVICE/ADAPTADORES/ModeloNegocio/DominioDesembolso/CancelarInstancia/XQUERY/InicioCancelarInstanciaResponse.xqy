xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $respuestaIn as xs:string external;

declare function local:func($respuestaIn as xs:string) as element() (:: schema-element(ns1:InicioCancelarInstanciaResponse) ::) {
    <ns1:InicioCancelarInstanciaResponse>
        <ns1:resultado>
            <res:result>OK</res:result>
            <res:message>Operación realizada exitosamente</res:message>
        </ns1:resultado>
    </ns1:InicioCancelarInstanciaResponse>
};

local:func($respuestaIn)
