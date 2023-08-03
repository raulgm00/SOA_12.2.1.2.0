xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ProcesarEnmienda";
(:: import schema at "../XSD/ProcesarEnmienda_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ProcesarEnmiendaResponse) ::) {
    <ns2:ProcesarEnmiendaResponse>
        <mat:PESTADOSP>{fn:data($OutputParameters/ns1:PESTADOSP)}</mat:PESTADOSP>
        <mat:PMENSAJESP>{fn:data($OutputParameters/ns1:PMENSAJESP)}</mat:PMENSAJESP>
    </ns2:ProcesarEnmiendaResponse>
};

local:func($OutputParameters)
