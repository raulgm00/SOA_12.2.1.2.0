xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/AdquisicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Adquisicion/V1/Schema/AdquisicionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";


declare function local:func() as element() (:: schema-element(ns1:CrearActualizarNoObjecionResponse) ::) {
    <ns1:CrearActualizarNoObjecionResponse>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Operación Exitosa</res:message>
        </ns1:Resultado>
    </ns1:CrearActualizarNoObjecionResponse>
};

local:func()
