xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/UpsertTransaccionCondicion";
(:: import schema at "../XSD/UpsertTransaccionCondicion_sp.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $UpsertTransaccionCondicionRequest as element() (:: schema-element(ns1:UpsertTransaccionCondicionRequest) ::) external;

declare function local:func($UpsertTransaccionCondicionRequest as element() (:: schema-element(ns1:UpsertTransaccionCondicionRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        {
            if ($UpsertTransaccionCondicionRequest/ns1:transaccionCondicion/con:Operacion/ope:idOperacion)
            then <ns2:P_ID_OPERACION>{fn:data($UpsertTransaccionCondicionRequest/ns1:transaccionCondicion/con:Operacion/ope:idOperacion)}</ns2:P_ID_OPERACION>
            else ()
        }
        <ns2:P_ID_SOLICITUD>{fn:data($UpsertTransaccionCondicionRequest/ns1:transaccionCondicion/con:Solicitud/des:idDesembolso)}</ns2:P_ID_SOLICITUD>
        <ns2:P_ID_CONTRATO_DESEMBOLSO>{fn:data($UpsertTransaccionCondicionRequest/ns1:transaccionCondicion/con:ContratoDesembolso/des:idDesembolso)}</ns2:P_ID_CONTRATO_DESEMBOLSO>
        <ns2:P_ID_CONDICION>
          {for $condicion in $UpsertTransaccionCondicionRequest/ns1:transaccionCondicion/con:Condicion
          return
            <ns2:P_ID_CONDICION_ITEM>{fn:data($condicion/con:idCondicion)}</ns2:P_ID_CONDICION_ITEM>
          }  
        </ns2:P_ID_CONDICION>
        {
            if ($UpsertTransaccionCondicionRequest/ns1:transaccionCondicion/con:EstadoTCC/cat:Id)
            then <ns2:P_ID_TCA_ESTADO_TCC>{fn:data($UpsertTransaccionCondicionRequest/ns1:transaccionCondicion/con:EstadoTCC/cat:Id)}</ns2:P_ID_TCA_ESTADO_TCC>
            else ()
        }
        {
            if ($UpsertTransaccionCondicionRequest/ns1:transaccionCondicion/con:EventoCondicion/cat:Id)
            then <ns2:P_ID_TCA_EVENTO_CONDICION>{fn:data($UpsertTransaccionCondicionRequest/ns1:transaccionCondicion/con:EventoCondicion/cat:Id)}</ns2:P_ID_TCA_EVENTO_CONDICION>
            else ()
        }
        <ns2:P_AGRUPADOR>{fn:data($UpsertTransaccionCondicionRequest/ns1:transaccionCondicion/con:agrupador)}</ns2:P_AGRUPADOR>
        <ns2:P_BAN_ESTATUS>{fn:data($UpsertTransaccionCondicionRequest/ns1:transaccionCondicion/con:estatus)}</ns2:P_BAN_ESTATUS>
        {
            if ($UpsertTransaccionCondicionRequest/ns1:transaccionCondicion/con:subEstadoTCC/cat:Id)
            then <ns2:P_ID_TCA_SUB_ESTADO_TCC>{fn:data($UpsertTransaccionCondicionRequest/ns1:transaccionCondicion/con:subEstadoTCC/cat:Id)}</ns2:P_ID_TCA_SUB_ESTADO_TCC>
            else ()
        }
        <ns2:P_EN_PROCESO>
        {if($UpsertTransaccionCondicionRequest/ns1:transaccionCondicion/con:enProceso = fn:true())then 1 else 0}
        </ns2:P_EN_PROCESO>
    </ns2:InputParameters>
};

local:func($UpsertTransaccionCondicionRequest)
