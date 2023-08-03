xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/ImplPCTProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA11/ImpPCTProcess.xsd" ::)

declare namespace ns2 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare namespace ns7 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $InicioEnviarGasto as element() (:: schema-element(ns1:InicioEnviarGasto) ::) external;

declare function local:func($InicioEnviarGasto as element() (:: schema-element(ns1:InicioEnviarGasto) ::)) as element() (:: schema-element(ns1:InicioReasignarImplementacionPCT) ::) {
    <ns1:InicioReasignarImplementacionPCT>
        <ns1:Header>
            <ns2:Operacion>
                <ns3:CodigoOperacion>{fn:data($InicioEnviarGasto/ns1:Header/ns2:Operacion/ns3:CodigoOperacion)}</ns3:CodigoOperacion>
                <ns3:NombreOperacion>{fn:data($InicioEnviarGasto/ns1:Header/ns2:Operacion/ns3:NombreOperacion)}</ns3:NombreOperacion>
                {
                    if ($InicioEnviarGasto/ns1:Header/ns2:Operacion/ns3:ResponsableOperacion)
                    then <ns3:ResponsableOperacion>{fn:data($InicioEnviarGasto/ns1:Header/ns2:Operacion/ns3:ResponsableOperacion)}</ns3:ResponsableOperacion>
                    else ()
                }
                <ns3:CodigoCliente>{fn:data($InicioEnviarGasto/ns1:Header/ns2:Operacion/ns3:CodigoCliente)}</ns3:CodigoCliente>
                <ns3:NombreCliente>{fn:data($InicioEnviarGasto/ns1:Header/ns2:Operacion/ns3:NombreCliente)}</ns3:NombreCliente>
                {
                    if ($InicioEnviarGasto/ns1:Header/ns2:Operacion/ns3:CodigoProducto)
                    then <ns3:CodigoProducto>{fn:data($InicioEnviarGasto/ns1:Header/ns2:Operacion/ns3:CodigoProducto)}</ns3:CodigoProducto>
                    else ()
                }
                <ns3:NombreProducto>{fn:data($InicioEnviarGasto/ns1:Header/ns2:Operacion/ns3:NombreProducto)}</ns3:NombreProducto>
                {
                    if ($InicioEnviarGasto/ns1:Header/ns2:Operacion/ns3:MontoSolicitado)
                    then <ns3:MontoSolicitado>{fn:data($InicioEnviarGasto/ns1:Header/ns2:Operacion/ns3:MontoSolicitado)}</ns3:MontoSolicitado>
                    else ()
                }
                <ns3:Pais>{fn:data($InicioEnviarGasto/ns1:Header/ns2:Operacion/ns3:Pais)}</ns3:Pais>
            </ns2:Operacion>
              
            {
                if ($InicioEnviarGasto/ns1:Header/ns2:Tarea)
                then 
                    <ns2:Tarea>
                        <ns5:CodigoTarea>{fn:data($InicioEnviarGasto/ns1:Header/ns2:Tarea/ns5:CodigoTarea)}</ns5:CodigoTarea>
                        <ns5:NombreTarea>{fn:data($InicioEnviarGasto/ns1:Header/ns2:Tarea/ns5:NombreTarea)}</ns5:NombreTarea>
                        {
                            if ($InicioEnviarGasto/ns1:Header/ns2:Tarea/ns5:CodigoRol)
                            then <ns5:CodigoRol>{fn:data($InicioEnviarGasto/ns1:Header/ns2:Tarea/ns5:CodigoRol)}</ns5:CodigoRol>
                            else ()
                        }
                        {
                            if ($InicioEnviarGasto/ns1:Header/ns2:Tarea/ns5:NombreRol)
                            then <ns5:NombreRol>{fn:data($InicioEnviarGasto/ns1:Header/ns2:Tarea/ns5:NombreRol)}</ns5:NombreRol>
                            else ()
                        }
                        {
                            if ($InicioEnviarGasto/ns1:Header/ns2:Tarea/ns5:CodigoProceso)
                            then <ns5:CodigoProceso>{fn:data($InicioEnviarGasto/ns1:Header/ns2:Tarea/ns5:CodigoProceso)}</ns5:CodigoProceso>
                            else ()
                        }
                        {
                            if ($InicioEnviarGasto/ns1:Header/ns2:Tarea/ns5:NombreProceso)
                            then <ns5:NombreProceso>{fn:data($InicioEnviarGasto/ns1:Header/ns2:Tarea/ns5:NombreProceso)}</ns5:NombreProceso>
                            else ()
                        }
                    </ns2:Tarea>
                else ()
            }
            {
                if ($InicioEnviarGasto/ns1:Header/ns2:Proceso)
                then 
                    <ns2:Proceso>
                        <ns6:IdProceso>{fn:data($InicioEnviarGasto/ns1:Header/ns2:Proceso/ns6:IdProceso)}</ns6:IdProceso>
                        {
                            if ($InicioEnviarGasto/ns1:Header/ns2:Proceso/ns6:CodigoProceso)
                            then <ns6:CodigoProceso>{fn:data($InicioEnviarGasto/ns1:Header/ns2:Proceso/ns6:CodigoProceso)}</ns6:CodigoProceso>
                            else ()
                        }
                        {
                            if ($InicioEnviarGasto/ns1:Header/ns2:Proceso/ns6:NombreProceso)
                            then <ns6:NombreProceso>{fn:data($InicioEnviarGasto/ns1:Header/ns2:Proceso/ns6:NombreProceso)}</ns6:NombreProceso>
                            else ()
                        }
                        {
                            if ($InicioEnviarGasto/ns1:Header/ns2:Proceso/ns6:EsProcesoCore)
                            then <ns6:EsProcesoCore>{fn:data($InicioEnviarGasto/ns1:Header/ns2:Proceso/ns6:EsProcesoCore)}</ns6:EsProcesoCore>
                            else ()
                        }
                        {
                            if ($InicioEnviarGasto/ns1:Header/ns2:Proceso/ns6:RolIniciaProceso)
                            then <ns6:RolIniciaProceso>{fn:data($InicioEnviarGasto/ns1:Header/ns2:Proceso/ns6:RolIniciaProceso)}</ns6:RolIniciaProceso>
                            else ()
                        }
                        {
                            if ($InicioEnviarGasto/ns1:Header/ns2:Proceso/ns6:UsuarioIniciaProceso)
                            then <ns6:UsuarioIniciaProceso>{fn:data($InicioEnviarGasto/ns1:Header/ns2:Proceso/ns6:UsuarioIniciaProceso)}</ns6:UsuarioIniciaProceso>
                            else ()
                        }
                        {
                            if ($InicioEnviarGasto/ns1:Header/ns2:Proceso/ns6:InstanciaProceso)
                            then <ns6:InstanciaProceso>{fn:data($InicioEnviarGasto/ns1:Header/ns2:Proceso/ns6:InstanciaProceso)}</ns6:InstanciaProceso>
                            else ()
                        }
                        {
                            if ($InicioEnviarGasto/ns1:Header/ns2:Proceso/ns6:IdFlujo)
                            then <ns6:IdFlujo>{fn:data($InicioEnviarGasto/ns1:Header/ns2:Proceso/ns6:IdFlujo)}</ns6:IdFlujo>
                            else ()
                        }
                    </ns2:Proceso>
                else ()
            }
            {
                for $ParameterType in $InicioEnviarGasto/ns1:Header/ns7:ParameterType
                return 
                <ns7:ParameterType>
                    <ns7:parameterName>{fn:data($ParameterType/ns7:parameterName)}</ns7:parameterName>
                    <ns7:parameterValue>{fn:data($ParameterType/ns7:parameterValue)}</ns7:parameterValue>
                </ns7:ParameterType>
            }
        </ns1:Header>
    </ns1:InicioReasignarImplementacionPCT>
};

local:func($InicioEnviarGasto)
