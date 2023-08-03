xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearBitacoraProcesoClienteMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearBitacoraProcesoCliente/V1/Schema/CrearBitacoraProcesoClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/ReasignacionRCProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PU02/ReasignacionRCProcess.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoClienteBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $FinReasignacionRC as element() (:: schema-element(ns1:FinReasignacionRC) ::) external;

declare function local:func($FinReasignacionRC as element() (:: schema-element(ns1:FinReasignacionRC) ::)) as element() (:: schema-element(ns2:requestCrearBitacoraProcesoClienteMessage) ::) {
    <ns2:requestCrearBitacoraProcesoClienteMessage>
        <ns2:BitacoraInput>
            <cre:id></cre:id>
            <cre:idCliente>{fn:data($FinReasignacionRC/ns1:Header/ns3:Cliente/ns4:IdCliente)}</cre:idCliente>
            <cre:razonSocial>{fn:data($FinReasignacionRC/ns1:Header/ns3:Cliente/ns4:RazonSocial)}</cre:razonSocial>
            <cre:idProceso>34</cre:idProceso>
            <cre:nombreProceso>PU02</cre:nombreProceso>
            <cre:instanciaProceso></cre:instanciaProceso>
            <cre:idProcesoSiguiente></cre:idProcesoSiguiente>
            <cre:esProceso>{fn:true()}</cre:esProceso>
            <cre:idTarea></cre:idTarea>
            <cre:nombreTarea></cre:nombreTarea>
            <cre:instanciaTarea></cre:instanciaTarea>
            <cre:usuario>{fn:data($FinReasignacionRC/ns1:Header/ns3:Cliente/ns4:ResponsableCliente)}</cre:usuario>
             <cre:nombreUsuario></cre:nombreUsuario>
            <cre:esFinActividad>{fn:true()}</cre:esFinActividad>
            <cre:fechaRegistro>{fn:current-date()}</cre:fechaRegistro>
            <cre:tiempoProceso></cre:tiempoProceso>
            <cre:tiempoTarea></cre:tiempoTarea>
            <cre:scr></cre:scr>
            <cre:string01></cre:string01>
            <cre:string02></cre:string02>
            <cre:string03></cre:string03>
            <cre:finalizado>{fn:true()}</cre:finalizado>
            <cre:idInicio></cre:idInicio>
        </ns2:BitacoraInput>
    </ns2:requestCrearBitacoraProcesoClienteMessage>
};

local:func($FinReasignacionRC)
