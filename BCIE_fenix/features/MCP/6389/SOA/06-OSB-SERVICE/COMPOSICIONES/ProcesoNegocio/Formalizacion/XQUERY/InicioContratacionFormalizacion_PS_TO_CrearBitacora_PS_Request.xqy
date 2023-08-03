xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/bpmn/bpmnProcess/FormalizacionProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC05/FormalizacionProcess.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare variable $InicioContratacionFormalizacion as element() (:: schema-element(ns2:InicioContratacionFormalizacion) ::) external;

declare function local:func($InicioContratacionFormalizacion as element() (:: schema-element(ns2:InicioContratacionFormalizacion) ::)) as element() (:: schema-element(ns1:requestCrearBitacoraProcesoMessage) ::) {
    <ns1:requestCrearBitacoraProcesoMessage>
        <ns1:BitacoraInput>
            <cre:idOperacion>{fn:data($InicioContratacionFormalizacion/ns2:Header/ns3:Operacion/ns4:CodigoOperacion)}</cre:idOperacion>
            <cre:nombreOperacion>{fn:data($InicioContratacionFormalizacion/ns2:Header/ns3:Operacion/ns4:NombreOperacion)}</cre:nombreOperacion>
            <cre:idProceso>5</cre:idProceso>
            <cre:nombreProceso>PC05</cre:nombreProceso>
            <cre:esProceso>{fn:true()}</cre:esProceso>
            <cre:usuario>{fn:data($InicioContratacionFormalizacion/ns2:Header/ns3:Operacion/ns4:ResponsableOperacion)}</cre:usuario>
            <cre:nombreUsuario></cre:nombreUsuario>
            <cre:esFinActividad>{fn:false()}</cre:esFinActividad>
            <cre:fechaRegistro>{fn:current-dateTime()}</cre:fechaRegistro>
            <cre:finalizado>{fn:false()}</cre:finalizado>
            {
            if(count($InicioContratacionFormalizacion/ns2:Header/ns5:ParameterType)>0)then
            <cre:string01>{fn:concat($InicioContratacionFormalizacion/ns2:Header/ns5:ParameterType[1]/ns5:parameterName,'-', $InicioContratacionFormalizacion/ns2:Header/ns5:ParameterType[1]/ns5:parameterValue[1])}</cre:string01>
            else()}
            {
            if(count($InicioContratacionFormalizacion/ns2:Header/ns5:ParameterType)>1)then
            <cre:string02>{fn:concat($InicioContratacionFormalizacion/ns2:Header/ns5:ParameterType[2]/ns5:parameterName,'-', $InicioContratacionFormalizacion/ns2:Header/ns5:ParameterType[2]/ns5:parameterValue)}</cre:string02>
            else()}
            {
            if(count($InicioContratacionFormalizacion/ns2:Header/ns5:ParameterType)>2)then
            <cre:string03>{fn:concat($InicioContratacionFormalizacion/ns2:Header/ns5:ParameterType[3]/ns5:parameterName,'-', $InicioContratacionFormalizacion/ns2:Header/ns5:ParameterType[3]/ns5:parameterValue)}</cre:string03>
            else()}
        </ns1:BitacoraInput>
    </ns1:requestCrearBitacoraProcesoMessage>
};

local:func($InicioContratacionFormalizacion)
