xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://xmlns.oracle.com/bpmn/bpmnProcess/ElegibilidadProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC01/ElegibilidadProcess.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/PreparacionProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC02/PreparacionProcess.xsd" ::)

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $FinPreparacion as element() (:: schema-element(ns1:FinPreparacion) ::) external;

declare function local:func($FinPreparacion as element() (:: schema-element(ns1:FinPreparacion) ::)) as element() (:: schema-element(ns2:InicioElegibilidad) ::) {
    <ns2:InicioElegibilidad>
        <ns2:Header>
            <ns3:Operacion>
                <ns4:CodigoOperacion>{fn:data($FinPreparacion/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</ns4:CodigoOperacion>
                <ns4:NombreOperacion>{fn:data($FinPreparacion/ns1:Header/ns3:Operacion/ns4:NombreOperacion)}</ns4:NombreOperacion>
                {
                    if ($FinPreparacion/ns1:Header/ns3:Operacion/ns4:ResponsableOperacion)
                    then <ns4:ResponsableOperacion>{fn:data($FinPreparacion/ns1:Header/ns3:Operacion/ns4:ResponsableOperacion)}</ns4:ResponsableOperacion>
                    else ()
                }
                <ns4:CodigoCliente>{fn:data($FinPreparacion/ns1:Header/ns3:Operacion/ns4:CodigoCliente)}</ns4:CodigoCliente>
                <ns4:NombreCliente>{fn:data($FinPreparacion/ns1:Header/ns3:Operacion/ns4:NombreCliente)}</ns4:NombreCliente>
                {
                    if ($FinPreparacion/ns1:Header/ns3:Operacion/ns4:CodigoProducto)
                    then <ns4:CodigoProducto>{fn:data($FinPreparacion/ns1:Header/ns3:Operacion/ns4:CodigoProducto)}</ns4:CodigoProducto>
                    else ()
                }
                <ns4:NombreProducto>{fn:data($FinPreparacion/ns1:Header/ns3:Operacion/ns4:NombreProducto)}</ns4:NombreProducto>
                {
                    if ($FinPreparacion/ns1:Header/ns3:Operacion/ns4:MontoSolicitado)
                    then <ns4:MontoSolicitado>{fn:data($FinPreparacion/ns1:Header/ns3:Operacion/ns4:MontoSolicitado)}</ns4:MontoSolicitado>
                    else ()
                }
                <ns4:Pais>{fn:data($FinPreparacion/ns1:Header/ns3:Operacion/ns4:Pais)}</ns4:Pais>
            </ns3:Operacion>
            <ns3:Tarea>
                <ns5:CodigoTarea>{fn:data($FinPreparacion/ns1:Header/ns3:Tarea/ns5:CodigoTarea)}</ns5:CodigoTarea>
                <ns5:NombreTarea>{fn:data($FinPreparacion/ns1:Header/ns3:Tarea/ns5:NombreTarea)}</ns5:NombreTarea>
                {
                    if ($FinPreparacion/ns1:Header/ns3:Tarea/ns5:CodigoRol)
                    then <ns5:CodigoRol>{fn:data($FinPreparacion/ns1:Header/ns3:Tarea/ns5:CodigoRol)}</ns5:CodigoRol>
                    else ()
                }
                {
                    if ($FinPreparacion/ns1:Header/ns3:Tarea/ns5:NombreRol)
                    then <ns5:NombreRol>{fn:data($FinPreparacion/ns1:Header/ns3:Tarea/ns5:NombreRol)}</ns5:NombreRol>
                    else ()
                }
                {
                    if ($FinPreparacion/ns1:Header/ns3:Tarea/ns5:CodigoProceso)
                    then <ns5:CodigoProceso>{fn:data($FinPreparacion/ns1:Header/ns3:Tarea/ns5:CodigoProceso)}</ns5:CodigoProceso>
                    else ()
                }
                {
                    if ($FinPreparacion/ns1:Header/ns3:Tarea/ns5:NombreProceso)
                    then <ns5:NombreProceso>{fn:data($FinPreparacion/ns1:Header/ns3:Tarea/ns5:NombreProceso)}</ns5:NombreProceso>
                    else ()
                }
            </ns3:Tarea>
            <ns6:ParameterType>
                <ns6:parameterName>{fn:data($FinPreparacion/ns1:Header/ns6:ParameterType/ns6:parameterName)}</ns6:parameterName>
                <ns6:parameterValue>{fn:data($FinPreparacion/ns1:Header/ns6:ParameterType/ns6:parameterValue)}</ns6:parameterValue>
            </ns6:ParameterType>
        </ns2:Header>
    </ns2:InicioElegibilidad>
};

local:func($FinPreparacion)
