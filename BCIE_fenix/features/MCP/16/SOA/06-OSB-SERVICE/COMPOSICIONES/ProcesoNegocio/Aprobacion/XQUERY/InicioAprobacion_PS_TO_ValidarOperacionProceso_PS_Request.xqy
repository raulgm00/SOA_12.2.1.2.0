xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/AprobacionProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC04/AprobacionProcess.xsd" ::)

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $InicioAprobacion as element() (:: schema-element(ns1:InicioAprobacion) ::) external;

declare function local:func($InicioAprobacion as element() (:: schema-element(ns1:InicioAprobacion) ::)) as element() (:: schema-element(ns2:consultarBitacoraProcesoRequest) ::) {
    <ns2:consultarBitacoraProcesoRequest>
        <ns2:idOperacion>{fn:data($InicioAprobacion/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</ns2:idOperacion>
        <ns2:idProceso>4</ns2:idProceso>
        <ns2:idTipo>{if (string ($InicioAprobacion/ns1:Header/ns5:ParameterType[1]/ns5:parameterName)='')then () else
        
        fn:concat($InicioAprobacion/ns1:Header/ns5:ParameterType[1]/ns5:parameterName,'-', $InicioAprobacion/ns1:Header/ns5:ParameterType[1]/ns5:parameterValue[1])}</ns2:idTipo>
        
        <ns2:Tipo>{if (string ($InicioAprobacion/ns1:Header/ns5:ParameterType[2]/ns5:parameterName)='')then () else
        
        fn:concat($InicioAprobacion/ns1:Header/ns5:ParameterType[2]/ns5:parameterName,'-', $InicioAprobacion/ns1:Header/ns5:ParameterType[2]/ns5:parameterValue)}</ns2:Tipo>
      
        
    </ns2:consultarBitacoraProcesoRequest>
};

local:func($InicioAprobacion)
