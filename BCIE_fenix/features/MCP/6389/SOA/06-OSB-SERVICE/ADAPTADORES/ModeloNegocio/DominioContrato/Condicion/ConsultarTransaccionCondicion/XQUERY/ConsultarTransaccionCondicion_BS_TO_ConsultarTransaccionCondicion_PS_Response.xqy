xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTransaccionCondicion";
(:: import schema at "../XSD/ConsultarTransaccionCondicion.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarTransaccionCondicionOutputCollection as element() (:: schema-element(ns1:ConsultarTransaccionCondicionOutputCollection) ::) external;

declare function local:func($ConsultarTransaccionCondicionOutputCollection as element() (:: schema-element(ns1:ConsultarTransaccionCondicionOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarTransaccionCondicionResponse) ::) {
    <ns2:ConsultarTransaccionCondicionResponse>
      {for $transaccion in $ConsultarTransaccionCondicionOutputCollection/ns1:ConsultarTransaccionCondicionOutput
        return
        <ns2:TransaccionCondicion>
            <con:IdTransaccion>{fn:data($transaccion/ns1:ID_TRANSACCION)}</con:IdTransaccion>
            <con:Operacion>
                <ope:idOperacion>{fn:data($transaccion/ns1:ID_OPERACION)}</ope:idOperacion>
            </con:Operacion>
            <con:Solicitud>
                <des:idDesembolso>{fn:data($transaccion/ns1:ID_SOLICITUD)}</des:idDesembolso>
            </con:Solicitud>
            <con:ContratoDesembolso>
                <des:idDesembolso>{fn:data($transaccion/ns1:ID_CONTRATO_DESEMBOLSO)}</des:idDesembolso>
            </con:ContratoDesembolso>
            <con:Condicion>
                <con:idCondicion>{fn:data($transaccion/ns1:ID_CONDICION)}</con:idCondicion>
            </con:Condicion>
            <con:EstadoTCC>
                <cat:Id>{fn:data($transaccion/ns1:ID_TCA_ESTADO_TCC)}</cat:Id>
            </con:EstadoTCC>
            <con:estatus>{fn:data($transaccion/ns1:BAN_ESTATUS)}</con:estatus>
            <con:enProceso>{
            if($transaccion/ns1:EN_PROCESO = 1)then fn:true() else fn:false()
            }</con:enProceso>
            <con:agrupador>{fn:data($transaccion/ns1:AGRUPADOR)}</con:agrupador>
            <con:EventoCondicion>
                <cat:Id>{fn:data($transaccion/ns1:ID_TCA_EVENTO_CONDICION)}</cat:Id>
            </con:EventoCondicion>
            <con:subEstadoTCC>
                <cat:Id>{fn:data($transaccion/ns1:ID_TCA_SUB_ESTADO_TCC)}</cat:Id>
            </con:subEstadoTCC>
        </ns2:TransaccionCondicion>
       }
        <ns2:Resultado>
            <res:result>OK</res:result>
            {if(fn:count($ConsultarTransaccionCondicionOutputCollection/ns1:ConsultarTransaccionCondicionOutput) > 0)then
              <res:message>Consulta exitosa</res:message>
            else
              <res:message>Consulta sin resultados</res:message>
            }  
        </ns2:Resultado>
    </ns2:ConsultarTransaccionCondicionResponse>
};

local:func($ConsultarTransaccionCondicionOutputCollection)
