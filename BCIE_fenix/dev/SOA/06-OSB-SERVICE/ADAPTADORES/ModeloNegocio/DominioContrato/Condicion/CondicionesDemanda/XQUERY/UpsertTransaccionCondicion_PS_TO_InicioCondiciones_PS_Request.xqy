xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/bpmn/bpmnProcess/CondicionesProcess";
(:: import schema at "../../../../../../MDS/Resources/BPM/Schema/PA03/CondicionesProcess.xsd" ::)

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns7 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare namespace ns8 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare variable $CondicionesDemandaRequest as element() (:: schema-element(ns1:CondicionesDemandaRequest) ::) external;
declare variable $UpsertTransaccionCondicionResponse as element() (:: schema-element(ns1:UpsertTransaccionCondicionResponse) ::) external;

declare function local:func($CondicionesDemandaRequest as element() (:: schema-element(ns1:CondicionesDemandaRequest) ::), 
                            $UpsertTransaccionCondicionResponse as element() (:: schema-element(ns1:UpsertTransaccionCondicionResponse) ::)) 
                            as element() (:: schema-element(ns2:InicioCondiciones) ::) {
    <ns2:InicioCondiciones>
        <ns2:Header>
            <ns3:Operacion>
                <ns4:CodigoOperacion>{fn:data($CondicionesDemandaRequest/ns1:CondicionesDemanda/con:Operacion/ope:idOperacion)}</ns4:CodigoOperacion>
                <ns4:NombreOperacion>{fn:data($CondicionesDemandaRequest/ns1:CondicionesDemanda/con:Operacion/ope:nombre)}</ns4:NombreOperacion>
                {
                    if ($CondicionesDemandaRequest/ns1:CondicionesDemanda/con:Operacion/ope:responsable)
                    then <ns4:ResponsableOperacion>{fn:data($CondicionesDemandaRequest/ns1:CondicionesDemanda/con:Operacion/ope:responsable)}</ns4:ResponsableOperacion>
                    else ()
                }
            </ns3:Operacion>

            {
            if(fn:string(fn:data($UpsertTransaccionCondicionResponse/ns1:idAgrupador))!= '')then
            <ns8:ParameterType>
                <ns8:parameterName>AGRUPADOR</ns8:parameterName>
                <ns8:parameterValue>{fn:data($UpsertTransaccionCondicionResponse/ns1:idAgrupador)}</ns8:parameterValue>
            </ns8:ParameterType>
            else()
            }
            {
            if(fn:string(fn:data($CondicionesDemandaRequest/ns1:CondicionesDemanda/con:Solicitud/des:idDesembolso))!='')then
            <ns8:ParameterType>
                <ns8:parameterName>ID_SOLICITUD</ns8:parameterName>
                <ns8:parameterValue>{fn:data($CondicionesDemandaRequest/ns1:CondicionesDemanda/con:Solicitud/des:idDesembolso)}</ns8:parameterValue>
            </ns8:ParameterType>
            else()
            }
            {
            if(fn:string(fn:data($CondicionesDemandaRequest/ns1:CondicionesDemanda/con:ContratoDesembolso/des:idDesembolso))!='')then
            <ns8:ParameterType>
                <ns8:parameterName>ID_DESEMBOLSO</ns8:parameterName>
                <ns8:parameterValue>{fn:data($CondicionesDemandaRequest/ns1:CondicionesDemanda/con:ContratoDesembolso/des:idDesembolso)}</ns8:parameterValue>
            </ns8:ParameterType>
            else()
            }
            {
            if(fn:string(fn:data($CondicionesDemandaRequest/ns1:CondicionesDemanda/con:ContratoDesembolso/des:idNumeroContrato))!='')then
            <ns8:ParameterType>
                <ns8:parameterName>NUM_CONTRATO</ns8:parameterName>
                <ns8:parameterValue>{fn:data($CondicionesDemandaRequest/ns1:CondicionesDemanda/con:ContratoDesembolso/des:idNumeroContrato)}</ns8:parameterValue>
            </ns8:ParameterType>
            else()
            }
            {if(string(fn:data($CondicionesDemandaRequest/ns1:CondicionesDemanda/con:EventoCondicion/cat:Id))!= '')then
            <ns8:ParameterType>
                <ns8:parameterName>EVENTO</ns8:parameterName>
                <ns8:parameterValue>
                {
                if ($CondicionesDemandaRequest/ns1:CondicionesDemanda/con:EventoCondicion/cat:Id = 2) 
                then 
                  'Primer Desembolso'
                else if ($CondicionesDemandaRequest/ns1:CondicionesDemanda/con:EventoCondicion/cat:Id = 3) 
                  then 
                    'Cualquier Desembolso'
                else 
                  fn:data($CondicionesDemandaRequest/ns1:CondicionesDemanda/con:EventoCondicion/cat:DescripcionCorta)
                }
                </ns8:parameterValue>
            </ns8:ParameterType>
            else()}
        </ns2:Header>
    </ns2:InicioCondiciones>
};

local:func($CondicionesDemandaRequest, $UpsertTransaccionCondicionResponse)
