xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ContratoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioContrato/Contrato/V1/Schema/ContratoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/ImplPCTProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA11/ImpPCTProcess.xsd" ::)

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns7 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare namespace ns8 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace con = "http://www.bcie.org/ContratoBO";

declare variable $InicioImplementacionPCT as element() (:: schema-element(ns1:InicioImplementacionPCT) ::) external;
declare variable $CrearContratoResponse as element() (:: schema-element(ns2:CrearContratoResponse) ::) external;

declare function local:func($InicioImplementacionPCT as element() (:: schema-element(ns1:InicioImplementacionPCT) ::), 
                            $CrearContratoResponse as element() (:: schema-element(ns2:CrearContratoResponse) ::)) 
                            as element() (:: schema-element(ns1:InicioImplementacionPCT) ::) {
    <ns1:InicioImplementacionPCT>
        <ns1:Header> 
                    <ns3:Operacion>
                        <ns4:CodigoOperacion>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</ns4:CodigoOperacion>
                        <ns4:NombreOperacion>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Operacion/ns4:NombreOperacion)}</ns4:NombreOperacion>
                        {
                            if ($InicioImplementacionPCT/ns1:Header/ns3:Operacion/ns4:ResponsableOperacion)
                            then <ns4:ResponsableOperacion>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Operacion/ns4:ResponsableOperacion)}</ns4:ResponsableOperacion>
                            else ()
                        }
                        <ns4:CodigoCliente>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Operacion/ns4:CodigoCliente)}</ns4:CodigoCliente>
                        <ns4:NombreCliente>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Operacion/ns4:NombreCliente)}</ns4:NombreCliente>
                        {
                            if ($InicioImplementacionPCT/ns1:Header/ns3:Operacion/ns4:CodigoProducto)
                            then <ns4:CodigoProducto>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Operacion/ns4:CodigoProducto)}</ns4:CodigoProducto>
                            else ()
                        }
                        <ns4:NombreProducto>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Operacion/ns4:NombreProducto)}</ns4:NombreProducto>
                        {
                            if ($InicioImplementacionPCT/ns1:Header/ns3:Operacion/ns4:MontoSolicitado)
                            then <ns4:MontoSolicitado>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Operacion/ns4:MontoSolicitado)}</ns4:MontoSolicitado>
                            else ()
                        }
                        <ns4:Pais>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Operacion/ns4:Pais)}</ns4:Pais>
                    </ns3:Operacion>
            
            {
                if ($InicioImplementacionPCT/ns1:Header/ns3:Tarea)
                then 
                    <ns3:Tarea>
                        <ns6:CodigoTarea>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Tarea/ns6:CodigoTarea)}</ns6:CodigoTarea>
                        <ns6:NombreTarea>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Tarea/ns6:NombreTarea)}</ns6:NombreTarea>
                        {
                            if ($InicioImplementacionPCT/ns1:Header/ns3:Tarea/ns6:CodigoRol)
                            then <ns6:CodigoRol>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Tarea/ns6:CodigoRol)}</ns6:CodigoRol>
                            else ()
                        }
                        {
                            if ($InicioImplementacionPCT/ns1:Header/ns3:Tarea/ns6:NombreRol)
                            then <ns6:NombreRol>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Tarea/ns6:NombreRol)}</ns6:NombreRol>
                            else ()
                        }
                        {
                            if ($InicioImplementacionPCT/ns1:Header/ns3:Tarea/ns6:CodigoProceso)
                            then <ns6:CodigoProceso>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Tarea/ns6:CodigoProceso)}</ns6:CodigoProceso>
                            else ()
                        }
                        {
                            if ($InicioImplementacionPCT/ns1:Header/ns3:Tarea/ns6:NombreProceso)
                            then <ns6:NombreProceso>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Tarea/ns6:NombreProceso)}</ns6:NombreProceso>
                            else ()
                        }
                    </ns3:Tarea>
                else ()
            }
            {
                if ($InicioImplementacionPCT/ns1:Header/ns3:Proceso)
                then 
                    <ns3:Proceso>
                        <ns7:IdProceso>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Proceso/ns7:IdProceso)}</ns7:IdProceso>
                        {
                            if ($InicioImplementacionPCT/ns1:Header/ns3:Proceso/ns7:CodigoProceso)
                            then <ns7:CodigoProceso>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Proceso/ns7:CodigoProceso)}</ns7:CodigoProceso>
                            else ()
                        }
                        {
                            if ($InicioImplementacionPCT/ns1:Header/ns3:Proceso/ns7:NombreProceso)
                            then <ns7:NombreProceso>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Proceso/ns7:NombreProceso)}</ns7:NombreProceso>
                            else ()
                        }
                        {
                            if ($InicioImplementacionPCT/ns1:Header/ns3:Proceso/ns7:EsProcesoCore)
                            then <ns7:EsProcesoCore>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Proceso/ns7:EsProcesoCore)}</ns7:EsProcesoCore>
                            else ()
                        }
                        {
                            if ($InicioImplementacionPCT/ns1:Header/ns3:Proceso/ns7:RolIniciaProceso)
                            then <ns7:RolIniciaProceso>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Proceso/ns7:RolIniciaProceso)}</ns7:RolIniciaProceso>
                            else ()
                        }
                        {
                            if ($InicioImplementacionPCT/ns1:Header/ns3:Proceso/ns7:UsuarioIniciaProceso)
                            then <ns7:UsuarioIniciaProceso>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Proceso/ns7:UsuarioIniciaProceso)}</ns7:UsuarioIniciaProceso>
                            else ()
                        }
                        {
                            if ($InicioImplementacionPCT/ns1:Header/ns3:Proceso/ns7:InstanciaProceso)
                            then <ns7:InstanciaProceso>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Proceso/ns7:InstanciaProceso)}</ns7:InstanciaProceso>
                            else ()
                        }
                        {
                            if ($InicioImplementacionPCT/ns1:Header/ns3:Proceso/ns7:IdFlujo)
                            then <ns7:IdFlujo>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Proceso/ns7:IdFlujo)}</ns7:IdFlujo>
                            else ()
                        }
                    </ns3:Proceso>
                else ()
            }
            {
                for $ParameterType in $InicioImplementacionPCT/ns1:Header/ns8:ParameterType
                return 
                <ns8:ParameterType>
                    <ns8:parameterName>{fn:data($ParameterType/ns8:parameterName)}</ns8:parameterName>
                    <ns8:parameterValue>{fn:data($ParameterType/ns8:parameterValue)}</ns8:parameterValue>
                </ns8:ParameterType>
            }
               <ns8:ParameterType>
                    <ns8:parameterName>ID_CONTRATO</ns8:parameterName>
                    <ns8:parameterValue>{fn:data($CrearContratoResponse/ns2:Contrato/con:idContrato)}</ns8:parameterValue>
                </ns8:ParameterType>
        </ns1:Header>
    </ns1:InicioImplementacionPCT>
};

local:func($InicioImplementacionPCT, $CrearContratoResponse)
