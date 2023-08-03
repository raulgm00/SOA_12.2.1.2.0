xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearBitacoraProcesoClienteMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearBitacoraProcesoCliente/V1/Schema/CrearBitacoraProcesoClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/SegCrediticioProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA07/SegCrediticioProcess.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoClienteBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare namespace ns7 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $InicioSeguimientoCrediticio as element() (:: schema-element(ns1:InicioSeguimientoCrediticio) ::) external;

declare function local:func($InicioSeguimientoCrediticio as element() (:: schema-element(ns1:InicioSeguimientoCrediticio) ::)) as element() (:: schema-element(ns2:requestCrearBitacoraProcesoClienteMessage) ::) {
    <ns2:requestCrearBitacoraProcesoClienteMessage>
        <ns2:BitacoraInput>
            <cre:id></cre:id>
            <cre:idCliente>{fn:data($InicioSeguimientoCrediticio/ns1:Header/ns3:Cliente/ns4:IdCliente)}</cre:idCliente>
            <cre:razonSocial>{fn:data($InicioSeguimientoCrediticio/ns1:Header/ns3:Cliente/ns4:RazonSocial)}</cre:razonSocial>
            <cre:idProceso>20</cre:idProceso>
            <cre:nombreProceso>PA07</cre:nombreProceso>
            <cre:idProcesoSiguiente></cre:idProcesoSiguiente>
            <cre:esProceso>{fn:true()}</cre:esProceso>
            <cre:idTarea></cre:idTarea>
            <cre:nombreTarea></cre:nombreTarea>
            <cre:instanciaTarea></cre:instanciaTarea>
            <cre:usuario>{fn:data($InicioSeguimientoCrediticio/ns1:Header/ns3:Cliente/ns4:ResponsableCliente)}</cre:usuario>
            <cre:nombreUsuario></cre:nombreUsuario>
            <cre:esFinActividad>{fn:false()}</cre:esFinActividad>
            <cre:fechaRegistro>{fn:current-dateTime()}</cre:fechaRegistro>
            <cre:tiempoProceso></cre:tiempoProceso>
            <cre:tiempoTarea></cre:tiempoTarea>
            <cre:scr></cre:scr>
            {if (count($InicioSeguimientoCrediticio/ns1:Header/ns7:ParameterType)>0)then
            <cre:string01>{fn:concat($InicioSeguimientoCrediticio/ns1:Header/ns7:ParameterType[1]/ns7:parameterName,'-',$InicioSeguimientoCrediticio/ns1:Header/ns7:ParameterType[1]/ns7:parameterValue)}</cre:string01>
            else()}
            {if (count($InicioSeguimientoCrediticio/ns1:Header/ns7:ParameterType)>1)then
            <cre:string02>{fn:concat($InicioSeguimientoCrediticio/ns1:Header/ns7:ParameterType[2]/ns7:parameterName,'-',$InicioSeguimientoCrediticio/ns1:Header/ns7:ParameterType[2]/ns7:parameterValue)}</cre:string02>
            else()}
            {if (count($InicioSeguimientoCrediticio/ns1:Header/ns7:ParameterType)>1)then
            <cre:string03>{fn:concat($InicioSeguimientoCrediticio/ns1:Header/ns7:ParameterType[3]/ns7:parameterName,'-',$InicioSeguimientoCrediticio/ns1:Header/ns7:ParameterType[3]/ns7:parameterValue)}</cre:string03>
            else()}
            <cre:finalizado>{fn:false()}</cre:finalizado>
            <cre:idInicio></cre:idInicio>
        </ns2:BitacoraInput>
    </ns2:requestCrearBitacoraProcesoClienteMessage>
};

local:func($InicioSeguimientoCrediticio)
