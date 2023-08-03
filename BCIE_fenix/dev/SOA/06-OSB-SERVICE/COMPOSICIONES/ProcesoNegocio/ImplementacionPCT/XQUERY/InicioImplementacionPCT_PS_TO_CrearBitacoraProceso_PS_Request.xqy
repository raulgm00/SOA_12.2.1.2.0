xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/ImplPCTProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA11/ImpPCTProcess.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare variable $InicioImplementacionPCT as element() (:: schema-element(ns1:InicioImplementacionPCT) ::) external;

declare function local:func($InicioImplementacionPCT as element() (:: schema-element(ns1:InicioImplementacionPCT) ::)) as element() (:: schema-element(ns2:requestCrearBitacoraProcesoMessage) ::) {
    <ns2:requestCrearBitacoraProcesoMessage>
        <ns2:BitacoraInput>
            <cre:id></cre:id>
            <cre:idOperacion>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</cre:idOperacion>
            <cre:nombreOperacion>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Operacion/ns4:NombreOperacion)}</cre:nombreOperacion>
            <cre:idProceso>25</cre:idProceso>
            <cre:nombreProceso>PA11</cre:nombreProceso>
            <cre:instanciaProceso>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Proceso/ns6:InstanciaProceso)}</cre:instanciaProceso>
            <cre:esProceso>{true()}</cre:esProceso>
            <cre:usuario>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Operacion/ns4:ResponsableOperacion)}</cre:usuario>
            <cre:nombreUsuario></cre:nombreUsuario>
            <cre:esFinActividad>{false()}</cre:esFinActividad>
            <cre:fechaRegistro>{fn:current-dateTime()}</cre:fechaRegistro>
            <cre:finalizado>{false()}</cre:finalizado>
            {
            if (count($InicioImplementacionPCT/ns1:Header/ns5:ParameterType)>0)then
            <cre:string01>{fn:concat($InicioImplementacionPCT/ns1:Header/ns5:ParameterType[1]/ns5:parameterName,"-",$InicioImplementacionPCT/ns1:Header/ns5:ParameterType[1]/ns5:parameterValue)}</cre:string01>
            else()
            }
            {
            if (count($InicioImplementacionPCT/ns1:Header/ns5:ParameterType)>1)then
            <cre:string02>{fn:concat($InicioImplementacionPCT/ns1:Header/ns5:ParameterType[2]/ns5:parameterName,"-",$InicioImplementacionPCT/ns1:Header/ns5:ParameterType[2]/ns5:parameterValue)}</cre:string02>
            else()
            }
            {
            if (count($InicioImplementacionPCT/ns1:Header/ns5:ParameterType)>3)then
            <cre:string03>{fn:concat($InicioImplementacionPCT/ns1:Header/ns5:ParameterType[3]/ns5:parameterName,"-",$InicioImplementacionPCT/ns1:Header/ns5:ParameterType[3]/ns5:parameterValue)}</cre:string03>
            else()
            }
        </ns2:BitacoraInput>
    </ns2:requestCrearBitacoraProcesoMessage>
};

local:func($InicioImplementacionPCT)
