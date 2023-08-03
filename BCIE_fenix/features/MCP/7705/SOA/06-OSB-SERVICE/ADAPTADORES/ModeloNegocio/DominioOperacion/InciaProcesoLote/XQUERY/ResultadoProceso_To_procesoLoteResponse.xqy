xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ImplementacionPctMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/implementacionPCT/V1/Schema/ImplementacionPctMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/ProcesosAlternos";
(:: import schema at "../../../../../MDS/Resources/BPM/Wsdl/ProcesosAlternos.wsdl" ::)

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns7 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare namespace ns8 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace imp = "http://www.bcie.org/ImplementacionPctBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace err = "http://www.bcie.org/ErrorBO";


declare variable $ImplementacionPctRequest as element() (:: schema-element(ns2:procesoLoteRequest) ::) external;
declare variable $counter as xs:int external;
declare variable $ResultadoProceso as element() (:: schema-element(ns1:ResultadoProceso) ::) external;

declare function local:func($ResultadoProceso as element() (:: schema-element(ns1:ResultadoProceso) ::),$ImplementacionPctRequest as element() (:: schema-element(ns2:procesoLoteRequest) ::), $counter as xs:int) as element() (:: schema-element(ns2:procesoLoteResponse) ::) {
    <ns2:procesoLoteResponse>
            <ns2:resultadoLotes>
                <imp:Lotes>{$ImplementacionPctRequest/ns2:implementacionPCT/imp:loteImplementacion[$counter]}</imp:Lotes>
                <imp:ResultadoProceso>
                    <res:result>{fn:data($ResultadoProceso/ns1:resultado/res:result)}</res:result>
                    <res:message>{fn:data($ResultadoProceso/ns1:resultado/res:message)}</res:message>
                    {
                        if ($ResultadoProceso/ns1:resultado/res:error)
                        then 
                            <res:error>
                                <err:errorCode>{fn:data($ResultadoProceso/ns1:resultado/res:error/err:errorCode)}</err:errorCode>
                                <err:errorDescription>{fn:data($ResultadoProceso/ns1:resultado/res:error/err:errorDescription)}</err:errorDescription>
                                <err:errorType>{fn:data($ResultadoProceso/ns1:resultado/res:error/err:errorType)}</err:errorType>
                            </res:error>
                        else ()
                    }
                </imp:ResultadoProceso>
            </ns2:resultadoLotes>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>servicio ejecutado satisfctoriamente</res:message>
        </ns2:Resultado>
    </ns2:procesoLoteResponse>
};

local:func($ResultadoProceso,$ImplementacionPctRequest,$counter)
