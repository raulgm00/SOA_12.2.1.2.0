xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/IniciarAprobacionLotusMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/IniciarAprobacionLOTUS/V1/Schema/IniciarAprobacionLOTUSMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $CREAR as element() external;

declare function local:func($CREAR as element()) as element() (:: schema-element(ns1:IniciarAprobacionLotusResponse) ::) {
    <ns1:IniciarAprobacionLotusResponse>
        <ns1:codigoAprobacion>{fn:data($CREAR)}</ns1:codigoAprobacion>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Aprobacion iniciada correctamente</res:message>
        </ns1:Resultado>
    </ns1:IniciarAprobacionLotusResponse>
};

local:func($CREAR)
