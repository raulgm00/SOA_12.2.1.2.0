xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/CondicionesProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA03/CondicionesProcess.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare variable $inInicioCondiciones as element() (:: schema-element(ns1:InicioCondiciones) ::) external;

declare function local:func($inInicioCondiciones as element() (:: schema-element(ns1:InicioCondiciones) ::)) as element() (:: schema-element(ns2:requestCrearBitacoraProcesoMessage) ::) {
    <ns2:requestCrearBitacoraProcesoMessage>
        <ns2:BitacoraInput>
            <cre:id></cre:id>
            <cre:idOperacion>{fn:data($inInicioCondiciones/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</cre:idOperacion>
            <cre:nombreOperacion>{fn:data($inInicioCondiciones/ns1:Header/ns3:Operacion/ns4:NombreOperacion)}</cre:nombreOperacion>
            <cre:idProceso>9</cre:idProceso>
            <cre:nombreProceso>PA03</cre:nombreProceso>
            <cre:esProceso>{true()}</cre:esProceso>
            <cre:usuario>{fn:data($inInicioCondiciones/ns1:Header/ns3:Operacion/ns4:ResponsableOperacion)}</cre:usuario>
            <cre:nombreUsuario></cre:nombreUsuario>
            <cre:esFinActividad>{false()}</cre:esFinActividad>
            <cre:fechaRegistro>{fn:current-dateTime()}</cre:fechaRegistro>
            {
            if(count($inInicioCondiciones/ns1:Header/ns5:ParameterType)>0)then
            <cre:string01>{fn:concat($inInicioCondiciones/ns1:Header/ns5:ParameterType[1]/ns5:parameterName,'-', $inInicioCondiciones/ns1:Header/ns5:ParameterType[1]/ns5:parameterValue[1])}</cre:string01>
            else()}
            {
            if(count($inInicioCondiciones/ns1:Header/ns5:ParameterType)>1)then
            <cre:string02>{fn:concat($inInicioCondiciones/ns1:Header/ns5:ParameterType[2]/ns5:parameterName,'-', $inInicioCondiciones/ns1:Header/ns5:ParameterType[2]/ns5:parameterValue)}</cre:string02>
            else()}
            {
            if(count($inInicioCondiciones/ns1:Header/ns5:ParameterType)>2)then
            <cre:string03>{fn:concat($inInicioCondiciones/ns1:Header/ns5:ParameterType[3]/ns5:parameterName,'-', $inInicioCondiciones/ns1:Header/ns5:ParameterType[3]/ns5:parameterValue)}</cre:string03>
            else()}
        </ns2:BitacoraInput>
    </ns2:requestCrearBitacoraProcesoMessage>
};

local:func($inInicioCondiciones)