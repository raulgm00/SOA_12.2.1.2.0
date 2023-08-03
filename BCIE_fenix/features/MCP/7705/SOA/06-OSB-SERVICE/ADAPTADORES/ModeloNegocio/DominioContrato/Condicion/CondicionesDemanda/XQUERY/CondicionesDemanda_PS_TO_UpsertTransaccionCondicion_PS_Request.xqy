xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

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

declare variable $CondicionesDemandaRequest as element() (:: schema-element(ns1:CondicionesDemandaRequest) ::) external;

declare function local:func($CondicionesDemandaRequest as element() (:: schema-element(ns1:CondicionesDemandaRequest) ::)) as element() (:: schema-element(ns1:UpsertTransaccionCondicionRequest) ::) {
    <ns1:UpsertTransaccionCondicionRequest>
        <ns1:transaccionCondicion>
            <con:Operacion>
                {
                    if ($CondicionesDemandaRequest/ns1:CondicionesDemanda/con:Operacion/ope:idOperacion)
                    then <ope:idOperacion>{fn:data($CondicionesDemandaRequest/ns1:CondicionesDemanda/con:Operacion/ope:idOperacion)}</ope:idOperacion>
                    else ()
                }
            </con:Operacion>
            <con:Solicitud>
                <des:idDesembolso>{fn:data($CondicionesDemandaRequest/ns1:CondicionesDemanda/con:Solicitud/des:idDesembolso)}</des:idDesembolso>
            </con:Solicitud>
            <con:ContratoDesembolso>
                <des:idDesembolso>{fn:data($CondicionesDemandaRequest/ns1:CondicionesDemanda/con:ContratoDesembolso/des:idDesembolso)}</des:idDesembolso>
            </con:ContratoDesembolso>
            {for $condicion in $CondicionesDemandaRequest/ns1:CondicionesDemanda/con:Condicion
            return
            <con:Condicion>
                <con:idCondicion>{fn:data($condicion/con:idCondicion)}</con:idCondicion>
            </con:Condicion>
            }
            <con:EstadoTCC>
              <cat:Id>14</cat:Id>
            </con:EstadoTCC>
            <con:estatus>{fn:data($CondicionesDemandaRequest/ns1:CondicionesDemanda/con:estatus)}</con:estatus>
            <con:agrupador>{fn:data($CondicionesDemandaRequest/ns1:CondicionesDemanda/con:agrupador)}</con:agrupador>
            <con:EventoCondicion>
                {
                    if ($CondicionesDemandaRequest/ns1:CondicionesDemanda/con:EventoCondicion/cat:Id)
                    then <cat:Id>{fn:data($CondicionesDemandaRequest/ns1:CondicionesDemanda/con:EventoCondicion/cat:Id)}</cat:Id>
                    else ()
                }
            </con:EventoCondicion>
        </ns1:transaccionCondicion>
    </ns1:UpsertTransaccionCondicionRequest>
};

local:func($CondicionesDemandaRequest)
