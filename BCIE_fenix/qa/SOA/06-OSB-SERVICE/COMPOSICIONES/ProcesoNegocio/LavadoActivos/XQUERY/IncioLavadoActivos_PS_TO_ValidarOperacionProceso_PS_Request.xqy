xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/LavadoActivosProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA01/LavadoActivosProcess.xsd" ::)
declare namespace ns2="http://www.bcie.org/ValidarOperacionProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/ValidarOperacionProceso/V1/Schema/ValidarOperacionProcesoMO.xsd" ::)

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace val = "http://www.bcie.org/ValidarOperacionProcesoBO";

declare variable $inicioLAFT as element() (:: schema-element(ns1:InicioLAFT) ::) external;

declare function local:func($inicioLAFT as element() (:: schema-element(ns1:InicioLAFT) ::)) as element() (:: schema-element(ns2:requestValidarOperacionProceso) ::) {
    <ns2:requestValidarOperacionProceso>
        <ns2:ValidarProcesoOperacionInput>
            <val:idOperacion>{fn:data($inicioLAFT/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</val:idOperacion>
            <val:idProceso>7</val:idProceso>
        </ns2:ValidarProcesoOperacionInput>
    </ns2:requestValidarOperacionProceso>
};

local:func($inicioLAFT)
