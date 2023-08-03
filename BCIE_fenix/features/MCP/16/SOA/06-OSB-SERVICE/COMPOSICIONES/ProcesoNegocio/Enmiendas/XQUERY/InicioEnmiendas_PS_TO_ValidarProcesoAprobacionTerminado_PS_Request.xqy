xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/AprobacionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Aprobacion/V1/Schema/AprobacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/EnmiendasProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA04/EnmiendasProcess.xsd" ::)

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $InicioEnmiendas as element() (:: schema-element(ns1:InicioEnmiendas) ::) external;

declare function local:func($InicioEnmiendas as element() (:: schema-element(ns1:InicioEnmiendas) ::)) as element() (:: schema-element(ns2:ValidarProcesoAprobacionFinalizadoRequest) ::) {
    <ns2:ValidarProcesoAprobacionFinalizadoRequest>
        <ns2:idOperacion>{fn:data($InicioEnmiendas/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</ns2:idOperacion>
    </ns2:ValidarProcesoAprobacionFinalizadoRequest>
};

local:func($InicioEnmiendas)
