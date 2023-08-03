xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)


declare namespace ns2="http://www.bcie.org/CrearBitacoraProcesoClienteMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearBitacoraProcesoCliente/V1/Schema/CrearBitacoraProcesoClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/AdministrarClienteProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA06/AdministrarClienteProcess.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraClienteBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace cre1 = "http://www.bcie.org/CrearBitacoraProcesoClienteBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $InicioAdministrarCliente as element() (:: schema-element(ns1:InicioAdministrarCliente) ::) external;

declare function local:func($InicioAdministrarCliente as element() (:: schema-element(ns1:InicioAdministrarCliente) ::)) as element() (:: schema-element(ns2:requestCrearBitacoraProcesoClienteMessage) ::) {
     <ns2:requestCrearBitacoraProcesoClienteMessage>
     
         <ns2:BitacoraInput>
             <cre1:idCliente>{fn:data($InicioAdministrarCliente/ns1:Cliente/ns3:IdCliente)}</cre1:idCliente>
             <cre1:razonSocial>{fn:data($InicioAdministrarCliente/ns1:Cliente/ns3:RazonSocial)}</cre1:razonSocial>
             <cre1:idProceso>12</cre1:idProceso>
             <cre1:nombreProceso>PA06</cre1:nombreProceso>
             <cre1:esProceso>{fn:true()}</cre1:esProceso>
             <cre1:usuario>{fn:data($InicioAdministrarCliente/ns1:Cliente/ns3:ResponsableCliente)}</cre1:usuario>
             
             <cre1:esFinActividad>{fn:false()}</cre1:esFinActividad>
             <cre1:fechaRegistro>{fn:current-dateTime()}</cre1:fechaRegistro>
             
            {if (count($InicioAdministrarCliente/ns4:ParameterType)>0)then
             <cre1:string01>{fn:concat($InicioAdministrarCliente/ns4:ParameterType[1]/ns4:parameterName,' - ',$InicioAdministrarCliente/ns4:ParameterType[1]/ns4:parameterValue)}</cre1:string01>
             else()
             }
            {if (count($InicioAdministrarCliente/ns4:ParameterType)>1)then
              <cre1:string02>{fn:concat($InicioAdministrarCliente/ns4:ParameterType[2]/ns4:parameterName,' - ',$InicioAdministrarCliente/ns4:ParameterType[2]/ns4:parameterValue)}</cre1:string02>
             else ()
            }
            {if (count($InicioAdministrarCliente/ns4:ParameterType)>2)then
             <cre1:string03>{fn:concat($InicioAdministrarCliente/ns4:ParameterType[3]/ns4:parameterName,' - ',$InicioAdministrarCliente/ns4:ParameterType[3]/ns4:parameterValue)}</cre1:string03>
             else()
            }
             <cre1:finalizado>{fn:false()}</cre1:finalizado>
             <cre1:idInicio></cre1:idInicio>
         </ns2:BitacoraInput>
     </ns2:requestCrearBitacoraProcesoClienteMessage>
};

local:func($InicioAdministrarCliente)
