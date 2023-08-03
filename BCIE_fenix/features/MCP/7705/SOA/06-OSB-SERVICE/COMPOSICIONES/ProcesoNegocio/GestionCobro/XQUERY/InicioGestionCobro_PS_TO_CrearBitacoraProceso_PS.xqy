xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearBitacoraProcesoClienteMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearBitacoraProcesoCliente/V1/Schema/CrearBitacoraProcesoClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/GestionCobroProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC07/GestionCobroProcess.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoClienteBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare variable $InicioGestionCobro as element() (:: schema-element(ns1:InicioGestionCobro) ::) external;

declare function local:func($InicioGestionCobro as element() (:: schema-element(ns1:InicioGestionCobro) ::)) as element() (:: schema-element(ns2:requestCrearBitacoraProcesoClienteMessage) ::) {
    <ns2:requestCrearBitacoraProcesoClienteMessage>
        <ns2:BitacoraInput>
            <cre:id></cre:id>
            <cre:idCliente>{fn:data($InicioGestionCobro/ns1:Header/ns3:Cliente/ns4:IdCliente)}</cre:idCliente>
            <cre:razonSocial>{fn:data($InicioGestionCobro/ns1:Header/ns3:Cliente/ns4:RazonSocial)}</cre:razonSocial>
            <cre:idProceso>18</cre:idProceso>
            <cre:nombreProceso>PC07</cre:nombreProceso>
            <cre:idProcesoSiguiente></cre:idProcesoSiguiente>
            <cre:esProceso>{fn:true()}</cre:esProceso>
            <cre:idTarea></cre:idTarea>
            <cre:nombreTarea></cre:nombreTarea>
            <cre:instanciaTarea></cre:instanciaTarea>
            <cre:usuario>{fn:data($InicioGestionCobro/ns1:Header/ns3:Cliente/ns4:ResponsableCliente)}</cre:usuario>
            <cre:nombreUsuario></cre:nombreUsuario>
            <cre:esFinActividad>{fn:false()}</cre:esFinActividad>
            <cre:fechaRegistro>{fn:current-dateTime()}</cre:fechaRegistro>
            <cre:tiempoProceso></cre:tiempoProceso>
            <cre:tiempoTarea></cre:tiempoTarea>
            <cre:scr></cre:scr>
            {
            if(fn:count($InicioGestionCobro/ns1:Header/ns5:ParameterType) > 0)then
              <cre:string01>{fn:concat($InicioGestionCobro/ns1:Header/ns5:ParameterType[1]/ns5:parameterName,'-',$InicioGestionCobro/ns1:Header/ns5:ParameterType[1]/ns5:parameterValue[1])}</cre:string01>
            else()
            }
            {
            if(fn:count($InicioGestionCobro/ns1:Header/ns5:ParameterType) > 1)then
            <cre:string02>{fn:concat($InicioGestionCobro/ns1:Header/ns5:ParameterType[2]/ns5:parameterName,'-',$InicioGestionCobro/ns1:Header/ns5:ParameterType[2]/ns5:parameterValue)}</cre:string02>
            else()
            }
            {
            if(fn:count($InicioGestionCobro/ns1:Header/ns5:ParameterType) > 2)then
            <cre:string03>{fn:concat($InicioGestionCobro/ns1:Header/ns5:ParameterType[3]/ns5:parameterName,'-',$InicioGestionCobro/ns1:Header/ns5:ParameterType[3]/ns5:parameterValue)}</cre:string03>
            else()
            }
            <cre:finalizado>{fn:false()}</cre:finalizado>
            <cre:idInicio></cre:idInicio>
        </ns2:BitacoraInput>
    </ns2:requestCrearBitacoraProcesoClienteMessage>
};

local:func($InicioGestionCobro)
