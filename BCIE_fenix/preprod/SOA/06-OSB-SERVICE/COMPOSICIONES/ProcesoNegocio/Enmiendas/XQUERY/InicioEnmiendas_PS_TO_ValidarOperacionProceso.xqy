xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/EnmiendasProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA04/EnmiendasProcess.xsd" ::)

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $InicioEnmiendas as element() (:: schema-element(ns1:InicioEnmiendas) ::) external;

declare function local:func($InicioEnmiendas as element() (:: schema-element(ns1:InicioEnmiendas) ::)) as element() (:: schema-element(ns2:consultarBitacoraProcesoRequest) ::) {
    <ns2:consultarBitacoraProcesoRequest>
        <ns2:idOperacion>{fn:data($InicioEnmiendas/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</ns2:idOperacion>
        <ns2:idProceso>10</ns2:idProceso>
        <ns2:idTipo>{fn:concat($InicioEnmiendas/ns1:Header/ns5:ParameterType[1]/ns5:parameterName, '-', $InicioEnmiendas/ns1:Header/ns5:ParameterType[1]/ns5:parameterValue[1])}</ns2:idTipo>
        <ns2:Tipo>{fn:concat($InicioEnmiendas/ns1:Header/ns5:ParameterType[2]/ns5:parameterName, '-', $InicioEnmiendas/ns1:Header/ns5:ParameterType[2]/ns5:parameterValue)}</ns2:Tipo>
    </ns2:consultarBitacoraProcesoRequest>
};

local:func($InicioEnmiendas)
