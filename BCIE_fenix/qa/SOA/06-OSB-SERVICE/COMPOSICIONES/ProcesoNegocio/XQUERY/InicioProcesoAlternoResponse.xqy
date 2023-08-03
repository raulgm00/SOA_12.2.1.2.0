xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/ProcesosAlternos";
(:: import schema at "../../../MDS/Resources/BPM/Wsdl/ProcesosAlternos.wsdl" ::)

declare variable $result as xs:string external;
declare variable $mensaje as xs:string external;

declare function local:func($result as xs:string, $mensaje as xs:string) as element() (:: schema-element(ns1:ResultadoProceso) ::) {
    <ns1:ResultadoProceso>
        <ns1:resultado>
            <res:result>{fn:data($result)}</res:result>
            <res:message>{fn:data($mensaje)}</res:message>
        </ns1:resultado>
    </ns1:ResultadoProceso>
};

local:func($result, $mensaje)
