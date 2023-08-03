xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/FormalizacionProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC05/FormalizacionProcess.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/bpmn/bpmnProcess/ImplPCTProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA11/ImpPCTProcess.xsd" ::)

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns8 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace ns7 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $request as element() (:: schema-element(ns1:FinContratacionFormalizacion) ::) external;

declare function local:func($request as element() (:: schema-element(ns1:FinContratacionFormalizacion) ::)) as element() (:: schema-element(ns2:InicioImplementacionPCT) ::) {
    <ns2:InicioImplementacionPCT>
        <ns2:Header>
               <ns3:Operacion>
                        <ns4:CodigoOperacion>{fn:data($request/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</ns4:CodigoOperacion>
                        <ns4:NombreOperacion>{fn:data($request/ns1:Header/ns3:Operacion/ns4:NombreOperacion)}</ns4:NombreOperacion>
                        {
                            if ($request/ns1:Header/ns3:Operacion/ns4:ResponsableOperacion)
                            then <ns4:ResponsableOperacion>{fn:data($request/ns1:Header/ns3:Operacion/ns4:ResponsableOperacion)}</ns4:ResponsableOperacion>
                            else ()
                        }
                        <ns4:CodigoCliente>{fn:data($request/ns1:Header/ns3:Operacion/ns4:CodigoCliente)}</ns4:CodigoCliente>
                        <ns4:NombreCliente>{fn:data($request/ns1:Header/ns3:Operacion/ns4:NombreCliente)}</ns4:NombreCliente>
                        {
                            if ($request/ns1:Header/ns3:Operacion/ns4:CodigoProducto)
                            then <ns4:CodigoProducto>{fn:data($request/ns1:Header/ns3:Operacion/ns4:CodigoProducto)}</ns4:CodigoProducto>
                            else ()
                        }
                        <ns4:NombreProducto>{fn:data($request/ns1:Header/ns3:Operacion/ns4:NombreProducto)}</ns4:NombreProducto>
                        {
                            if ($request/ns1:Header/ns3:Operacion/ns4:MontoSolicitado)
                            then <ns4:MontoSolicitado>{fn:data($request/ns1:Header/ns3:Operacion/ns4:MontoSolicitado)}</ns4:MontoSolicitado>
                            else ()
                        }
                        <ns4:Pais>{fn:data($request/ns1:Header/ns3:Operacion/ns4:Pais)}</ns4:Pais>
                    </ns3:Operacion>
              
            {
                if ($request/ns1:Header/ns3:Tarea)
                then 
                    <ns3:Tarea>
                        <ns6:CodigoTarea>{fn:data($request/ns1:Header/ns3:Tarea/ns6:CodigoTarea)}</ns6:CodigoTarea>
                        <ns6:NombreTarea>{fn:data($request/ns1:Header/ns3:Tarea/ns6:NombreTarea)}</ns6:NombreTarea>
                        {
                            if ($request/ns1:Header/ns3:Tarea/ns6:CodigoRol)
                            then <ns6:CodigoRol>{fn:data($request/ns1:Header/ns3:Tarea/ns6:CodigoRol)}</ns6:CodigoRol>
                            else ()
                        }
                        {
                            if ($request/ns1:Header/ns3:Tarea/ns6:NombreRol)
                            then <ns6:NombreRol>{fn:data($request/ns1:Header/ns3:Tarea/ns6:NombreRol)}</ns6:NombreRol>
                            else ()
                        }
                        {
                            if ($request/ns1:Header/ns3:Tarea/ns6:CodigoProceso)
                            then <ns6:CodigoProceso>{fn:data($request/ns1:Header/ns3:Tarea/ns6:CodigoProceso)}</ns6:CodigoProceso>
                            else ()
                        }
                        {
                            if ($request/ns1:Header/ns3:Tarea/ns6:NombreProceso)
                            then <ns6:NombreProceso>{fn:data($request/ns1:Header/ns3:Tarea/ns6:NombreProceso)}</ns6:NombreProceso>
                            else ()
                        }
                    </ns3:Tarea>
                else ()
            }
            {
                if ($request/ns1:Header/ns3:Proceso)
                then 
                    <ns3:Proceso>
                        <ns7:IdProceso>{fn:data($request/ns1:Header/ns3:Proceso/ns7:IdProceso)}</ns7:IdProceso>
                        {
                            if ($request/ns1:Header/ns3:Proceso/ns7:CodigoProceso)
                            then <ns7:CodigoProceso>{fn:data($request/ns1:Header/ns3:Proceso/ns7:CodigoProceso)}</ns7:CodigoProceso>
                            else ()
                        }
                        {
                            if ($request/ns1:Header/ns3:Proceso/ns7:NombreProceso)
                            then <ns7:NombreProceso>{fn:data($request/ns1:Header/ns3:Proceso/ns7:NombreProceso)}</ns7:NombreProceso>
                            else ()
                        }
                        {
                            if ($request/ns1:Header/ns3:Proceso/ns7:EsProcesoCore)
                            then <ns7:EsProcesoCore>{fn:data($request/ns1:Header/ns3:Proceso/ns7:EsProcesoCore)}</ns7:EsProcesoCore>
                            else ()
                        }
                        {
                            if ($request/ns1:Header/ns3:Proceso/ns7:RolIniciaProceso)
                            then <ns7:RolIniciaProceso>{fn:data($request/ns1:Header/ns3:Proceso/ns7:RolIniciaProceso)}</ns7:RolIniciaProceso>
                            else ()
                        }
                        {
                            if ($request/ns1:Header/ns3:Proceso/ns7:UsuarioIniciaProceso)
                            then <ns7:UsuarioIniciaProceso>{fn:data($request/ns1:Header/ns3:Proceso/ns7:UsuarioIniciaProceso)}</ns7:UsuarioIniciaProceso>
                            else ()
                        }
                        {
                            if ($request/ns1:Header/ns3:Proceso/ns7:InstanciaProceso)
                            then <ns7:InstanciaProceso>{fn:data($request/ns1:Header/ns3:Proceso/ns7:InstanciaProceso)}</ns7:InstanciaProceso>
                            else ()
                        }
                        {
                            if ($request/ns1:Header/ns3:Proceso/ns7:IdFlujo)
                            then <ns7:IdFlujo>{fn:data($request/ns1:Header/ns3:Proceso/ns7:IdFlujo)}</ns7:IdFlujo>
                            else ()
                        }
                    </ns3:Proceso>
                else ()
            }
            {
                for $ParameterType in $request/ns1:Header/ns8:ParameterType
                return 
                <ns8:ParameterType>
                    <ns8:parameterName>{fn:data($ParameterType/ns8:parameterName)}</ns8:parameterName>
                    <ns8:parameterValue>{fn:data($ParameterType/ns8:parameterValue)}</ns8:parameterValue>
                </ns8:ParameterType>
            }
        </ns2:Header>
    </ns2:InicioImplementacionPCT>
};

local:func($request)
