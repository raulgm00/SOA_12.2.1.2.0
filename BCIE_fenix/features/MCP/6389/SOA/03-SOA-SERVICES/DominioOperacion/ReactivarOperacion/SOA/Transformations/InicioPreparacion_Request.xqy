xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace con="http://www.bcie.org/ConsultarParametrosEnBitacoraProcesoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConsultarParametrosEnBitacoraProceso/V1/Schema/ConsultarParametrosEnBitacoraProcesoMO.xsd" ::)
declare namespace ope="http://www.bcie.org/OperacionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ele="http://xmlns.oracle.com/bpmn/bpmnProcess/ElegibilidadProcess";
(:: import schema at "oramds:/apps/Resources/BPM/Schema/PC01/ElegibilidadProcess.xsd" ::)

declare namespace ns2 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns1 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ope1 = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace con1 = "http://www.bcie.org/ConsultarParametrosEnBitacoraProcesoBO";

declare variable $OutConsultar.response as element() (:: schema-element(ope:ConsultarOperacionResponse) ::) external;
declare variable $outConsultarParametrosEnBitacora.response as element() (:: schema-element(con:responseConsultarParametrosEnBitacoraProcesoMessage) ::) external;

declare function local:funcIniciopreparacion_request($OutConsultar.response as element() (:: schema-element(ope:ConsultarOperacionResponse) ::), 
                                                     $outConsultarParametrosEnBitacora.response as element() (:: schema-element(con:responseConsultarParametrosEnBitacoraProcesoMessage) ::)) 
                                                     as element() (:: schema-element(ele:InicioElegibilidad) ::) {
    <ele:InicioElegibilidad>
        <ele:Header>
            <ns1:Operacion>
                <ns2:CodigoOperacion>{fn:data($OutConsultar.response/ope:Operacion/ope1:idOperacion)}</ns2:CodigoOperacion>
                <ns2:NombreOperacion>{fn:data($OutConsultar.response/ope:Operacion/ope1:nombre)}</ns2:NombreOperacion>
                {
                    if ($OutConsultar.response/ope:Operacion/ope1:responsable)
                    then <ns2:ResponsableOperacion>{fn:data($OutConsultar.response/ope:Operacion/ope1:responsable)}</ns2:ResponsableOperacion>
                    else ()
                }
                <ns2:CodigoCliente>{fn:data($OutConsultar.response/ope:Operacion/ope1:cliente/cli:idCliente)}</ns2:CodigoCliente>
                <ns2:NombreCliente>{fn:data($OutConsultar.response/ope:Operacion/ope1:cliente/cli:razonSocial)}</ns2:NombreCliente>
                <ns2:CodigoProducto>{fn:data($OutConsultar.response/ope:Operacion/ope1:producto/pro:idProducto)}</ns2:CodigoProducto>
                <ns2:NombreProducto>{fn:data($OutConsultar.response/ope:Operacion/ope1:producto/pro:descripcion)}</ns2:NombreProducto>
                <ns2:MontoSolicitado></ns2:MontoSolicitado>
                <ns2:Pais>{fn:data($OutConsultar.response/ope:Operacion/ope1:cliente/cli:pais/cat:Descripcion)}</ns2:Pais>
            </ns1:Operacion>
            <ns1:Tarea>
                <ns3:CodigoTarea></ns3:CodigoTarea>
                <ns3:NombreTarea></ns3:NombreTarea>
                <ns3:CodigoRol></ns3:CodigoRol>
                <ns3:NombreRol></ns3:NombreRol>
                <ns3:CodigoProceso>2</ns3:CodigoProceso>
                <ns3:NombreProceso>PC02</ns3:NombreProceso>
            </ns1:Tarea>
            {
            if (fn:string-length($outConsultarParametrosEnBitacora.response/con:Parametros/con1:parameter1)> 0)
            then(
            <ns4:ParameterType>
                <ns4:parameterName>{fn:substring-before(fn:data($outConsultarParametrosEnBitacora.response/con:Parametros/con1:parameter1),'-')}</ns4:parameterName>
                <ns4:parameterValue>{fn:substring-after(fn:data($outConsultarParametrosEnBitacora.response/con:Parametros/con1:parameter1),'-')}</ns4:parameterValue>
            </ns4:ParameterType>)
            else()}
            {
            if (fn:string-length($outConsultarParametrosEnBitacora.response/con:Parametros/con1:parameter2)> 0)
            then(
            <ns4:ParameterType>
                <ns4:parameterName>{fn:substring-before(fn:data($outConsultarParametrosEnBitacora.response/con:Parametros/con1:parameter2),'-')}</ns4:parameterName>
                <ns4:parameterValue>{fn:substring-after(fn:data($outConsultarParametrosEnBitacora.response/con:Parametros/con1:parameter2),'-')}</ns4:parameterValue>
            </ns4:ParameterType>)
            else()}
            {
            if (fn:string-length($outConsultarParametrosEnBitacora.response/con:Parametros/con1:parameter3)> 0)
            then(
            <ns4:ParameterType>
                <ns4:parameterName>{fn:substring-before(fn:data($outConsultarParametrosEnBitacora.response/con:Parametros/con1:parameter3),'-')}</ns4:parameterName>
                <ns4:parameterValue>{fn:substring-after(fn:data($outConsultarParametrosEnBitacora.response/con:Parametros/con1:parameter3),'-')}</ns4:parameterValue>
            </ns4:ParameterType>)
            else()}
        </ele:Header>
    </ele:InicioElegibilidad>
};

local:funcIniciopreparacion_request($OutConsultar.response, $outConsultarParametrosEnBitacora.response)
