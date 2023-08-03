xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/FormalizacionProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC05/FormalizacionProcess.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $FinFormalizacion as element() (:: schema-element(ns1:FinFormalizacion) ::) external;

declare function local:func($FinFormalizacion as element() (:: schema-element(ns1:FinFormalizacion) ::)) as element() (:: schema-element(ns2:ActualizarOperacionRequest) ::) {
    <ns2:ActualizarOperacionRequest>
        <ns2:Operacion>
            <ope:idOperacion>{fn:data($FinFormalizacion/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</ope:idOperacion>
            <ope:etapa>5</ope:etapa>
        </ns2:Operacion>
    </ns2:ActualizarOperacionRequest>
};

local:func($FinFormalizacion)
