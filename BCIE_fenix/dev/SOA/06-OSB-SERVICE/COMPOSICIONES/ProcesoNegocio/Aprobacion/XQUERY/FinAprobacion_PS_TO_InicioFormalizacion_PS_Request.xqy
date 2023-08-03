xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/AprobacionProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC04/AprobacionProcess.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/bpmn/bpmnProcess/FormalizacionProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC05/FormalizacionProcess.xsd" ::)

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $FinAprobacion as element() (:: schema-element(ns1:FinAprobacion) ::) external;

declare function local:func($FinAprobacion as element() (:: schema-element(ns1:FinAprobacion) ::)) as element() (:: schema-element(ns2:InicioFormalizacion) ::) {
    <ns2:InicioFormalizacion>
        <ns2:Header>
            <ns3:Operacion>
                <ns4:CodigoOperacion>{fn:data($FinAprobacion/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</ns4:CodigoOperacion>
                <ns4:NombreOperacion>{fn:data($FinAprobacion/ns1:Header/ns3:Operacion/ns4:NombreOperacion)}</ns4:NombreOperacion>
                {
                    if ($FinAprobacion/ns1:Header/ns3:Operacion/ns4:ResponsableOperacion)
                    then <ns4:ResponsableOperacion>{fn:data($FinAprobacion/ns1:Header/ns3:Operacion/ns4:ResponsableOperacion)}</ns4:ResponsableOperacion>
                    else ()
                }
                <ns4:CodigoCliente>{fn:data($FinAprobacion/ns1:Header/ns3:Operacion/ns4:CodigoCliente)}</ns4:CodigoCliente>
                <ns4:NombreCliente>{fn:data($FinAprobacion/ns1:Header/ns3:Operacion/ns4:NombreCliente)}</ns4:NombreCliente>
                {
                    if ($FinAprobacion/ns1:Header/ns3:Operacion/ns4:CodigoProducto)
                    then <ns4:CodigoProducto>{fn:data($FinAprobacion/ns1:Header/ns3:Operacion/ns4:CodigoProducto)}</ns4:CodigoProducto>
                    else ()
                }
                <ns4:NombreProducto>{fn:data($FinAprobacion/ns1:Header/ns3:Operacion/ns4:NombreProducto)}</ns4:NombreProducto>
                {
                    if ($FinAprobacion/ns1:Header/ns3:Operacion/ns4:MontoSolicitado)
                    then <ns4:MontoSolicitado>{fn:data($FinAprobacion/ns1:Header/ns3:Operacion/ns4:MontoSolicitado)}</ns4:MontoSolicitado>
                    else ()
                }
                <ns4:Pais>{fn:data($FinAprobacion/ns1:Header/ns3:Operacion/ns4:Pais)}</ns4:Pais>
            </ns3:Operacion>
            {
                if ($FinAprobacion/ns1:Header/ns3:Tarea)
                then 
                    <ns3:Tarea>
                        <ns5:CodigoTarea>{fn:data($FinAprobacion/ns1:Header/ns3:Tarea/ns5:CodigoTarea)}</ns5:CodigoTarea>
                        <ns5:NombreTarea>{fn:data($FinAprobacion/ns1:Header/ns3:Tarea/ns5:NombreTarea)}</ns5:NombreTarea>
                        {
                            if ($FinAprobacion/ns1:Header/ns3:Tarea/ns5:CodigoRol)
                            then <ns5:CodigoRol>{fn:data($FinAprobacion/ns1:Header/ns3:Tarea/ns5:CodigoRol)}</ns5:CodigoRol>
                            else ()
                        }
                        {
                            if ($FinAprobacion/ns1:Header/ns3:Tarea/ns5:NombreRol)
                            then <ns5:NombreRol>{fn:data($FinAprobacion/ns1:Header/ns3:Tarea/ns5:NombreRol)}</ns5:NombreRol>
                            else ()
                        }
                        <ns5:CodigoProceso>5</ns5:CodigoProceso>
                        <ns5:NombreProceso>PC05</ns5:NombreProceso>
                    </ns3:Tarea>
                else ()
            }
            {
                for $ParameterType in $FinAprobacion/ns1:Header/ns6:ParameterType
                return 
                <ns6:ParameterType>
                    <ns6:parameterName>{fn:data($ParameterType/ns6:parameterName)}</ns6:parameterName>
                    <ns6:parameterValue>{fn:data($ParameterType/ns6:parameterValue)}</ns6:parameterValue>
                </ns6:ParameterType>
            }
        </ns2:Header>
    </ns2:InicioFormalizacion>
};

local:func($FinAprobacion)
