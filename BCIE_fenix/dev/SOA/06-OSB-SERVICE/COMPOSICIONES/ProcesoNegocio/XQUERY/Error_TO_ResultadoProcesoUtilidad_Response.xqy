xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/ProcesosUtilidad";
(:: import schema at "../../../MDS/Resources/BPM/Wsdl/ProcesosUtilidad.wsdl" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $descripcion as xs:string external;
declare variable $codigo as xs:string external;
declare variable $mensaje as xs:string external;

declare function local:func($descripcion as xs:string, 
                            $codigo as xs:string,
                            $mensaje as xs:string) 
                            as element() (:: schema-element(ns1:ResultadoProceso) ::) {
    <ns1:ResultadoProceso>
        <ns1:resultado>
            <res:result>ERROR</res:result>
            <res:message>{fn:data($mensaje)}</res:message>
            <res:error>
                <err:errorCode>{fn:data($codigo)}</err:errorCode>
                <err:errorDescription>{fn:data($descripcion)}</err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns1:resultado>
    </ns1:ResultadoProceso>
};

local:func($descripcion, $codigo, $mensaje)
