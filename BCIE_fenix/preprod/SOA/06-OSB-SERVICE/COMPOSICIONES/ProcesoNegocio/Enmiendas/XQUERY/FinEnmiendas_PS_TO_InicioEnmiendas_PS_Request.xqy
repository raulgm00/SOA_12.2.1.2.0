xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/EnmiendasProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA04/EnmiendasProcess.xsd" ::)

declare namespace ns2 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $FinEnmiendas as element() (:: schema-element(ns1:FinEnmiendas) ::) external;

declare function local:func($FinEnmiendas as element() (:: schema-element(ns1:FinEnmiendas) ::)) as element() (:: schema-element(ns1:InicioEnmiendas) ::) {
    <ns1:InicioEnmiendas>
        <ns1:Header>
            <ns2:Operacion>
                <ns3:CodigoOperacion>{fn:data($FinEnmiendas/ns1:Header/ns2:Operacion/ns3:CodigoOperacion)}</ns3:CodigoOperacion>
                <ns3:NombreOperacion>{fn:data($FinEnmiendas/ns1:Header/ns2:Operacion/ns3:NombreOperacion)}</ns3:NombreOperacion>
                {
                    if ($FinEnmiendas/ns1:Header/ns2:Operacion/ns3:ResponsableOperacion)
                    then <ns3:ResponsableOperacion>{fn:data($FinEnmiendas/ns1:Header/ns2:Operacion/ns3:ResponsableOperacion)}</ns3:ResponsableOperacion>
                    else ()
                }
                <ns3:CodigoCliente>{fn:data($FinEnmiendas/ns1:Header/ns2:Operacion/ns3:CodigoCliente)}</ns3:CodigoCliente>
                <ns3:NombreCliente>{fn:data($FinEnmiendas/ns1:Header/ns2:Operacion/ns3:NombreCliente)}</ns3:NombreCliente>
                {
                    if ($FinEnmiendas/ns1:Header/ns2:Operacion/ns3:CodigoProducto)
                    then <ns3:CodigoProducto>{fn:data($FinEnmiendas/ns1:Header/ns2:Operacion/ns3:CodigoProducto)}</ns3:CodigoProducto>
                    else ()
                }
                <ns3:NombreProducto>{fn:data($FinEnmiendas/ns1:Header/ns2:Operacion/ns3:NombreProducto)}</ns3:NombreProducto>
                {
                    if ($FinEnmiendas/ns1:Header/ns2:Operacion/ns3:MontoSolicitado)
                    then <ns3:MontoSolicitado>{fn:data($FinEnmiendas/ns1:Header/ns2:Operacion/ns3:MontoSolicitado)}</ns3:MontoSolicitado>
                    else ()
                }
                <ns3:Pais>{fn:data($FinEnmiendas/ns1:Header/ns2:Operacion/ns3:Pais)}</ns3:Pais>
            </ns2:Operacion>
            {
                if ($FinEnmiendas/ns1:Header/ns2:Tarea)
                then 
                    <ns2:Tarea>
                        <ns4:CodigoTarea>{fn:data($FinEnmiendas/ns1:Header/ns2:Tarea/ns4:CodigoTarea)}</ns4:CodigoTarea>
                        <ns4:NombreTarea>{fn:data($FinEnmiendas/ns1:Header/ns2:Tarea/ns4:NombreTarea)}</ns4:NombreTarea>
                        {
                            if ($FinEnmiendas/ns1:Header/ns2:Tarea/ns4:CodigoRol)
                            then <ns4:CodigoRol>{fn:data($FinEnmiendas/ns1:Header/ns2:Tarea/ns4:CodigoRol)}</ns4:CodigoRol>
                            else ()
                        }
                        {
                            if ($FinEnmiendas/ns1:Header/ns2:Tarea/ns4:NombreRol)
                            then <ns4:NombreRol>{fn:data($FinEnmiendas/ns1:Header/ns2:Tarea/ns4:NombreRol)}</ns4:NombreRol>
                            else ()
                        }
                        {
                            if ($FinEnmiendas/ns1:Header/ns2:Tarea/ns4:CodigoProceso)
                            then <ns4:CodigoProceso>{fn:data($FinEnmiendas/ns1:Header/ns2:Tarea/ns4:CodigoProceso)}</ns4:CodigoProceso>
                            else ()
                        }
                        {
                            if ($FinEnmiendas/ns1:Header/ns2:Tarea/ns4:NombreProceso)
                            then <ns4:NombreProceso>{fn:data($FinEnmiendas/ns1:Header/ns2:Tarea/ns4:NombreProceso)}</ns4:NombreProceso>
                            else ()
                        }
                    </ns2:Tarea>
                else ()
            }
            {
                for $ParameterType in $FinEnmiendas/ns1:Header/ns5:ParameterType
                return 
                <ns5:ParameterType>
                    <ns5:parameterName>{fn:data($ParameterType/ns5:parameterName)}</ns5:parameterName>
                    <ns5:parameterValue>{fn:data($ParameterType/ns5:parameterValue)}</ns5:parameterValue>
                </ns5:ParameterType>
            }
        </ns1:Header>
    </ns1:InicioEnmiendas>
};

local:func($FinEnmiendas)
