xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/AdquisicionProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA09/AdquisicionProcess.xsd" ::)

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns7 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace ns2 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare variable $request as element() (:: schema-element(ns1:InicioAdquisicion) ::) external;

declare function local:func($request as element() (:: schema-element(ns1:InicioAdquisicion) ::)) as element() (:: schema-element(ns1:InicioReasignarRO) ::) {
    <ns1:InicioReasignarRO>
        <ns1:Header>
                 
                    <ns2:Operacion>
                        <ns3:CodigoOperacion>{fn:data($request/ns1:Header/ns2:Operacion/ns3:CodigoOperacion)}</ns3:CodigoOperacion>
                        <ns3:NombreOperacion>{fn:data($request/ns1:Header/ns2:Operacion/ns3:NombreOperacion)}</ns3:NombreOperacion>
                        {
                            if ($request/ns1:Header/ns2:Operacion/ns3:ResponsableOperacion)
                            then <ns3:ResponsableOperacion>{fn:data($request/ns1:Header/ns2:Operacion/ns3:ResponsableOperacion)}</ns3:ResponsableOperacion>
                            else ()
                        }
                        <ns3:CodigoCliente>{fn:data($request/ns1:Header/ns2:Operacion/ns3:CodigoCliente)}</ns3:CodigoCliente>
                        <ns3:NombreCliente>{fn:data($request/ns1:Header/ns2:Operacion/ns3:NombreCliente)}</ns3:NombreCliente>
                        {
                            if ($request/ns1:Header/ns2:Operacion/ns3:CodigoProducto)
                            then <ns3:CodigoProducto>{fn:data($request/ns1:Header/ns2:Operacion/ns3:CodigoProducto)}</ns3:CodigoProducto>
                            else ()
                        }
                        <ns3:NombreProducto>{fn:data($request/ns1:Header/ns2:Operacion/ns3:NombreProducto)}</ns3:NombreProducto>
                        {
                            if ($request/ns1:Header/ns2:Operacion/ns3:MontoSolicitado)
                            then <ns3:MontoSolicitado>{fn:data($request/ns1:Header/ns2:Operacion/ns3:MontoSolicitado)}</ns3:MontoSolicitado>
                            else ()
                        }
                        <ns3:Pais>{fn:data($request/ns1:Header/ns2:Operacion/ns3:Pais)}</ns3:Pais>
                    </ns2:Operacion>
             
            {
                if ($request/ns1:Header/ns2:Tarea)
                then 
                    <ns2:Tarea>
                        <ns5:CodigoTarea>{fn:data($request/ns1:Header/ns2:Tarea/ns5:CodigoTarea)}</ns5:CodigoTarea>
                        <ns5:NombreTarea>{fn:data($request/ns1:Header/ns2:Tarea/ns5:NombreTarea)}</ns5:NombreTarea>
                        {
                            if ($request/ns1:Header/ns2:Tarea/ns5:CodigoRol)
                            then <ns5:CodigoRol>{fn:data($request/ns1:Header/ns2:Tarea/ns5:CodigoRol)}</ns5:CodigoRol>
                            else ()
                        }
                        {
                            if ($request/ns1:Header/ns2:Tarea/ns5:NombreRol)
                            then <ns5:NombreRol>{fn:data($request/ns1:Header/ns2:Tarea/ns5:NombreRol)}</ns5:NombreRol>
                            else ()
                        }
                        {
                            if ($request/ns1:Header/ns2:Tarea/ns5:CodigoProceso)
                            then <ns5:CodigoProceso>{fn:data($request/ns1:Header/ns2:Tarea/ns5:CodigoProceso)}</ns5:CodigoProceso>
                            else ()
                        }
                        {
                            if ($request/ns1:Header/ns2:Tarea/ns5:NombreProceso)
                            then <ns5:NombreProceso>{fn:data($request/ns1:Header/ns2:Tarea/ns5:NombreProceso)}</ns5:NombreProceso>
                            else ()
                        }
                    </ns2:Tarea>
                else ()
            }
            {
                if ($request/ns1:Header/ns2:Proceso)
                then 
                    <ns2:Proceso>
                        <ns6:IdProceso>{fn:data($request/ns1:Header/ns2:Proceso/ns6:IdProceso)}</ns6:IdProceso>
                        {
                            if ($request/ns1:Header/ns2:Proceso/ns6:CodigoProceso)
                            then <ns6:CodigoProceso>{fn:data($request/ns1:Header/ns2:Proceso/ns6:CodigoProceso)}</ns6:CodigoProceso>
                            else ()
                        }
                        {
                            if ($request/ns1:Header/ns2:Proceso/ns6:NombreProceso)
                            then <ns6:NombreProceso>{fn:data($request/ns1:Header/ns2:Proceso/ns6:NombreProceso)}</ns6:NombreProceso>
                            else ()
                        }
                        {
                            if ($request/ns1:Header/ns2:Proceso/ns6:EsProcesoCore)
                            then <ns6:EsProcesoCore>{fn:data($request/ns1:Header/ns2:Proceso/ns6:EsProcesoCore)}</ns6:EsProcesoCore>
                            else ()
                        }
                        {
                            if ($request/ns1:Header/ns2:Proceso/ns6:RolIniciaProceso)
                            then <ns6:RolIniciaProceso>{fn:data($request/ns1:Header/ns2:Proceso/ns6:RolIniciaProceso)}</ns6:RolIniciaProceso>
                            else ()
                        }
                        {
                            if ($request/ns1:Header/ns2:Proceso/ns6:UsuarioIniciaProceso)
                            then <ns6:UsuarioIniciaProceso>{fn:data($request/ns1:Header/ns2:Proceso/ns6:UsuarioIniciaProceso)}</ns6:UsuarioIniciaProceso>
                            else ()
                        }
                        {
                            if ($request/ns1:Header/ns2:Proceso/ns6:InstanciaProceso)
                            then <ns6:InstanciaProceso>{fn:data($request/ns1:Header/ns2:Proceso/ns6:InstanciaProceso)}</ns6:InstanciaProceso>
                            else ()
                        }
                        {
                            if ($request/ns1:Header/ns2:Proceso/ns6:IdFlujo)
                            then <ns6:IdFlujo>{fn:data($request/ns1:Header/ns2:Proceso/ns6:IdFlujo)}</ns6:IdFlujo>
                            else ()
                        }
                    </ns2:Proceso>
                else ()
            }
            {
                for $ParameterType in $request/ns1:Header/ns7:ParameterType
                return 
                <ns7:ParameterType>
                    <ns7:parameterName>{fn:data($ParameterType/ns7:parameterName)}</ns7:parameterName>
                    <ns7:parameterValue>{fn:data($ParameterType/ns7:parameterValue)}</ns7:parameterValue>
                </ns7:ParameterType>
            }
        </ns1:Header>
    </ns1:InicioReasignarRO>
};

local:func($request)
