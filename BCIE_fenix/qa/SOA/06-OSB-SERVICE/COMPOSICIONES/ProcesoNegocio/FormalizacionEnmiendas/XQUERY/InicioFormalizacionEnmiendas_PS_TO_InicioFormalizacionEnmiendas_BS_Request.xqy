xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)
declare namespace ns11="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/FormalizacionEnmiendasProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA15/FormalizacionEnmiendasProcess.xsd" ::)

declare namespace ns2 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $InicioFormalizacionEnmiendas as element() (:: schema-element(ns1:InicioFormalizacionEnmiendas) ::) external;
declare variable $CrearEnmiendaTCCResponse as element() (:: schema-element(ns11:CrearEnmiendaTCCResponse) ::) external;

declare function local:func($InicioFormalizacionEnmiendas as element() (:: schema-element(ns1:InicioFormalizacionEnmiendas) ::),  $CrearEnmiendaTCCResponse as element() (:: schema-element(ns11:CrearEnmiendaTCCResponse) ::)) as element() (:: schema-element(ns1:InicioFormalizacionEnmiendas) ::) {
    <ns1:InicioFormalizacionEnmiendas>
        <ns1:Header>
            <ns2:Operacion>
                <ns3:CodigoOperacion>{fn:data($InicioFormalizacionEnmiendas/ns1:Header/ns2:Operacion/ns3:CodigoOperacion)}</ns3:CodigoOperacion>
                <ns3:NombreOperacion>{fn:data($InicioFormalizacionEnmiendas/ns1:Header/ns2:Operacion/ns3:NombreOperacion)}</ns3:NombreOperacion>
                {
                    if ($InicioFormalizacionEnmiendas/ns1:Header/ns2:Operacion/ns3:ResponsableOperacion)
                    then <ns3:ResponsableOperacion>{fn:data($InicioFormalizacionEnmiendas/ns1:Header/ns2:Operacion/ns3:ResponsableOperacion)}</ns3:ResponsableOperacion>
                    else ()
                }
                <ns3:CodigoCliente>{fn:data($InicioFormalizacionEnmiendas/ns1:Header/ns2:Operacion/ns3:CodigoCliente)}</ns3:CodigoCliente>
                <ns3:NombreCliente>{fn:data($InicioFormalizacionEnmiendas/ns1:Header/ns2:Operacion/ns3:NombreCliente)}</ns3:NombreCliente>
                {
                    if ($InicioFormalizacionEnmiendas/ns1:Header/ns2:Operacion/ns3:CodigoProducto)
                    then <ns3:CodigoProducto>{fn:data($InicioFormalizacionEnmiendas/ns1:Header/ns2:Operacion/ns3:CodigoProducto)}</ns3:CodigoProducto>
                    else ()
                }
                <ns3:NombreProducto>{fn:data($InicioFormalizacionEnmiendas/ns1:Header/ns2:Operacion/ns3:NombreProducto)}</ns3:NombreProducto>
                {
                    if ($InicioFormalizacionEnmiendas/ns1:Header/ns2:Operacion/ns3:MontoSolicitado)
                    then <ns3:MontoSolicitado>{fn:data($InicioFormalizacionEnmiendas/ns1:Header/ns2:Operacion/ns3:MontoSolicitado)}</ns3:MontoSolicitado>
                    else ()
                }
                <ns3:Pais>{fn:data($InicioFormalizacionEnmiendas/ns1:Header/ns2:Operacion/ns3:Pais)}</ns3:Pais>
            </ns2:Operacion>
            <ns2:Tarea>
                <ns4:CodigoTarea>{fn:data($InicioFormalizacionEnmiendas/ns1:Header/ns2:Tarea/ns4:CodigoTarea)}</ns4:CodigoTarea>
                <ns4:NombreTarea>{fn:data($InicioFormalizacionEnmiendas/ns1:Header/ns2:Tarea/ns4:NombreTarea)}</ns4:NombreTarea>
                {
                    if ($InicioFormalizacionEnmiendas/ns1:Header/ns2:Tarea/ns4:CodigoRol)
                    then <ns4:CodigoRol>{fn:data($InicioFormalizacionEnmiendas/ns1:Header/ns2:Tarea/ns4:CodigoRol)}</ns4:CodigoRol>
                    else ()
                }
                {
                    if ($InicioFormalizacionEnmiendas/ns1:Header/ns2:Tarea/ns4:NombreRol)
                    then <ns4:NombreRol>{fn:data($InicioFormalizacionEnmiendas/ns1:Header/ns2:Tarea/ns4:NombreRol)}</ns4:NombreRol>
                    else ()
                }
                {
                    if ($InicioFormalizacionEnmiendas/ns1:Header/ns2:Tarea/ns4:CodigoProceso)
                    then <ns4:CodigoProceso>{fn:data($InicioFormalizacionEnmiendas/ns1:Header/ns2:Tarea/ns4:CodigoProceso)}</ns4:CodigoProceso>
                    else ()
                }
                {
                    if ($InicioFormalizacionEnmiendas/ns1:Header/ns2:Tarea/ns4:NombreProceso)
                    then <ns4:NombreProceso>{fn:data($InicioFormalizacionEnmiendas/ns1:Header/ns2:Tarea/ns4:NombreProceso)}</ns4:NombreProceso>
                    else ()
                }
            </ns2:Tarea>
            {
                for $ParameterType in $InicioFormalizacionEnmiendas/ns1:Header/ns5:ParameterType
                return 
                <ns5:ParameterType>
                    <ns5:parameterName>{fn:data($ParameterType/ns5:parameterName)}</ns5:parameterName>
                    <ns5:parameterValue>{fn:data($ParameterType/ns5:parameterValue)}</ns5:parameterValue>
                </ns5:ParameterType>
            }
                   <ns5:ParameterType>
                   {if  (string($CrearEnmiendaTCCResponse/ns11:idEnmienda)='' ) then () else
                  
                    <ns5:parameterName>IDENMIENDA</ns5:parameterName>
                    }
                     {if  (string($CrearEnmiendaTCCResponse/ns11:idEnmienda)='' ) then () else
                  
                    <ns5:parameterValue>{fn:data($CrearEnmiendaTCCResponse/ns11:idEnmienda)}</ns5:parameterValue>
                    }
                </ns5:ParameterType>
                 <ns5:ParameterType>
                   {if  (string($InicioFormalizacionEnmiendas/ns1:OrigenFormalizacionEnmiendas)='' ) then () else
                  
                    <ns5:parameterName>IDORIGENFORMALIZACION</ns5:parameterName>
                    }
                     {if  (string($InicioFormalizacionEnmiendas/ns1:OrigenFormalizacionEnmiendas)='' ) then () else
                  
                    <ns5:parameterValue>{fn:data($InicioFormalizacionEnmiendas/ns1:OrigenFormalizacionEnmiendas)}</ns5:parameterValue>
                    }
                </ns5:ParameterType>
        </ns1:Header>
        {
            if ($InicioFormalizacionEnmiendas/ns1:OrigenFormalizacionEnmiendas)
            then <ns1:OrigenFormalizacionEnmiendas>{fn:data($InicioFormalizacionEnmiendas/ns1:OrigenFormalizacionEnmiendas)}</ns1:OrigenFormalizacionEnmiendas>
            else ()
        }
        {
            if ($InicioFormalizacionEnmiendas/ns1:idEnmienda)
                then <ns1:idEnmienda>{fn:data($InicioFormalizacionEnmiendas/ns1:idEnmienda)}</ns1:idEnmienda>
                else ()
        }
    </ns1:InicioFormalizacionEnmiendas>
};

local:func($InicioFormalizacionEnmiendas, $CrearEnmiendaTCCResponse)