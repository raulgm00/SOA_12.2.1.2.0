xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/AdquisicionProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA09/AdquisicionProcess.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare variable $InicioAdquisicion as element() (:: schema-element(ns1:InicioAdquisicion) ::) external;

declare function local:func($InicioAdquisicion as element() (:: schema-element(ns1:InicioAdquisicion) ::)) as element() (:: schema-element(ns2:requestCrearBitacoraProcesoMessage) ::) {
    <ns2:requestCrearBitacoraProcesoMessage>
        <ns2:BitacoraInput>
            <cre:id></cre:id>
            <cre:idOperacion>{fn:data($InicioAdquisicion/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</cre:idOperacion>
            <cre:nombreOperacion>{fn:data($InicioAdquisicion/ns1:Header/ns3:Operacion/ns4:NombreOperacion)}</cre:nombreOperacion>
            <cre:idProceso>24</cre:idProceso>
            <cre:nombreProceso>PA09</cre:nombreProceso>
            <cre:esProceso>{fn:true()}</cre:esProceso>
            <cre:usuario>{fn:data($InicioAdquisicion/ns1:Header/ns3:Operacion/ns4:ResponsableOperacion)}</cre:usuario>
            <cre:nombreUsuario></cre:nombreUsuario>
            <cre:esFinActividad>{fn:false()}</cre:esFinActividad>
            <cre:fechaRegistro>{fn:current-date()}</cre:fechaRegistro>
            {
            if(count($InicioAdquisicion/ns1:Header/ns5:ParameterType/ns5:parameterName)>0)then
            <cre:string01>{fn:concat($InicioAdquisicion/ns1:Header/ns5:ParameterType[1]/ns5:parameterName,'-', $InicioAdquisicion/ns1:Header/ns5:ParameterType[1]/ns5:parameterValue[1])}</cre:string01>
            else()}
            {
            if(count($InicioAdquisicion/ns1:Header/ns5:ParameterType/ns5:parameterName)>1)then
            <cre:string02>{fn:concat($InicioAdquisicion/ns1:Header/ns5:ParameterType[2]/ns5:parameterName,'-', $InicioAdquisicion/ns1:Header/ns5:ParameterType[2]/ns5:parameterValue)}</cre:string02>
            else()}
            {
            if(count($InicioAdquisicion/ns1:Header/ns5:ParameterType/ns5:parameterName)>2)then
            <cre:string03>{fn:concat($InicioAdquisicion/ns1:Header/ns5:ParameterType[3]/ns5:parameterName,'-', $InicioAdquisicion/ns1:Header/ns5:ParameterType[3]/ns5:parameterValue)}</cre:string03>
            else()}     
            <cre:string01>{fn:data($InicioAdquisicion/ns1:Header/ns5:ParameterType/ns5:parameterName)}</cre:string01>
            <cre:string02>{fn:data($InicioAdquisicion/ns1:Header/ns5:ParameterType/ns5:parameterValue)}</cre:string02>
            <cre:string03></cre:string03>
        </ns2:BitacoraInput>
    </ns2:requestCrearBitacoraProcesoMessage>
};

local:func($InicioAdquisicion)