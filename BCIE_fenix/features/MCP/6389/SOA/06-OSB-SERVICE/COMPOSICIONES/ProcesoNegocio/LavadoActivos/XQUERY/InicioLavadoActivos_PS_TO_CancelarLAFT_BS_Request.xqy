xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/LavadoActivosProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA01/LavadoActivosProcess.xsd" ::)

declare namespace ns2 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $InicioLAFT as element() (:: schema-element(ns1:InicioLAFT) ::) external;

declare function local:func($InicioLAFT as element() (:: schema-element(ns1:InicioLAFT) ::)) as element() (:: schema-element(ns1:CancelarLAFT) ::) {
    <ns1:CancelarLAFT>
        <ns1:Header>
            <ns2:Operacion>
                <ns3:CodigoOperacion>{fn:data($InicioLAFT/ns1:Header/ns2:Operacion/ns3:CodigoOperacion)}</ns3:CodigoOperacion>
                <ns3:NombreOperacion>{fn:data($InicioLAFT/ns1:Header/ns2:Operacion/ns3:NombreOperacion)}</ns3:NombreOperacion>
                {
                    if ($InicioLAFT/ns1:Header/ns2:Operacion/ns3:ResponsableOperacion)
                    then <ns3:ResponsableOperacion>{fn:data($InicioLAFT/ns1:Header/ns2:Operacion/ns3:ResponsableOperacion)}</ns3:ResponsableOperacion>
                    else ()
                }
                <ns3:CodigoCliente>{fn:data($InicioLAFT/ns1:Header/ns2:Operacion/ns3:CodigoCliente)}</ns3:CodigoCliente>
                <ns3:NombreCliente>{fn:data($InicioLAFT/ns1:Header/ns2:Operacion/ns3:NombreCliente)}</ns3:NombreCliente>
                {
                    if ($InicioLAFT/ns1:Header/ns2:Operacion/ns3:CodigoProducto)
                    then <ns3:CodigoProducto>{fn:data($InicioLAFT/ns1:Header/ns2:Operacion/ns3:CodigoProducto)}</ns3:CodigoProducto>
                    else ()
                }
                <ns3:NombreProducto>{fn:data($InicioLAFT/ns1:Header/ns2:Operacion/ns3:NombreProducto)}</ns3:NombreProducto>
                {
                    if ($InicioLAFT/ns1:Header/ns2:Operacion/ns3:MontoSolicitado)
                    then <ns3:MontoSolicitado>{fn:data($InicioLAFT/ns1:Header/ns2:Operacion/ns3:MontoSolicitado)}</ns3:MontoSolicitado>
                    else ()
                }
                <ns3:Pais>{fn:data($InicioLAFT/ns1:Header/ns2:Operacion/ns3:Pais)}</ns3:Pais>
            </ns2:Operacion>
            {
                if ($InicioLAFT/ns1:Header/ns2:Tarea)
                then 
                    <ns2:Tarea>
                        <ns4:CodigoTarea>{fn:data($InicioLAFT/ns1:Header/ns2:Tarea/ns4:CodigoTarea)}</ns4:CodigoTarea>
                        <ns4:NombreTarea>{fn:data($InicioLAFT/ns1:Header/ns2:Tarea/ns4:NombreTarea)}</ns4:NombreTarea>
                        {
                            if ($InicioLAFT/ns1:Header/ns2:Tarea/ns4:CodigoRol)
                            then <ns4:CodigoRol>{fn:data($InicioLAFT/ns1:Header/ns2:Tarea/ns4:CodigoRol)}</ns4:CodigoRol>
                            else ()
                        }
                        {
                            if ($InicioLAFT/ns1:Header/ns2:Tarea/ns4:NombreRol)
                            then <ns4:NombreRol>{fn:data($InicioLAFT/ns1:Header/ns2:Tarea/ns4:NombreRol)}</ns4:NombreRol>
                            else ()
                        }
                        {
                            if ($InicioLAFT/ns1:Header/ns2:Tarea/ns4:CodigoProceso)
                            then <ns4:CodigoProceso>{fn:data($InicioLAFT/ns1:Header/ns2:Tarea/ns4:CodigoProceso)}</ns4:CodigoProceso>
                            else ()
                        }
                        {
                            if ($InicioLAFT/ns1:Header/ns2:Tarea/ns4:NombreProceso)
                            then <ns4:NombreProceso>{fn:data($InicioLAFT/ns1:Header/ns2:Tarea/ns4:NombreProceso)}</ns4:NombreProceso>
                            else ()
                        }
                    </ns2:Tarea>
                else ()
            }
            {
                for $ParameterType in $InicioLAFT/ns1:Header/ns5:ParameterType
                return 
                <ns5:ParameterType>
                    <ns5:parameterName>{fn:data($ParameterType/ns5:parameterName)}</ns5:parameterName>
                    <ns5:parameterValue>{fn:data($ParameterType/ns5:parameterValue)}</ns5:parameterValue>
                </ns5:ParameterType>
            }
        </ns1:Header>
    </ns1:CancelarLAFT>
};

local:func($InicioLAFT)
