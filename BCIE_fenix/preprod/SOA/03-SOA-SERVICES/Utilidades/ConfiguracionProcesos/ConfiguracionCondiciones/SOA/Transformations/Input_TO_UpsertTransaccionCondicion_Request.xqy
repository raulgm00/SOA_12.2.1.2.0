xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/ConfiguracionProcesosMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/Schema/ConfiguracionProcesosMO.xsd" ::)

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

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $configuracionCondicionesRequest as element() (:: schema-element(ns1:configuracionCondicionesRequest) ::) external;

declare function local:func($configuracionCondicionesRequest as element() (:: schema-element(ns1:configuracionCondicionesRequest) ::)) as element() (:: schema-element(ns2:UpsertTransaccionCondicionRequest) ::) {
    <ns2:UpsertTransaccionCondicionRequest>
        <ns2:transaccionCondicion>
            <con:Operacion>
                <ope:idOperacion>{fn:data($configuracionCondicionesRequest/ns1:idOperacion)}</ope:idOperacion>
            </con:Operacion>
            <con:Solicitud>
                <des:idDesembolso>{fn:data($configuracionCondicionesRequest/ns1:idSolicitud)}</des:idDesembolso>
            </con:Solicitud>
            <con:ContratoDesembolso>
                <des:idDesembolso>{fn:data($configuracionCondicionesRequest/ns1:idContratoDesembolso)}</des:idDesembolso>
            </con:ContratoDesembolso>
            {for $condicion in $configuracionCondicionesRequest/ns1:listaCondicion
            return
              <con:Condicion>
                  <con:idCondicion>{fn:data($condicion/con:idCondicion)}</con:idCondicion>
              </con:Condicion>
            }
            <con:EstadoTCC>23</con:EstadoTCC>
            <con:enProceso>{fn:true()}</con:enProceso>
            <con:EventoCondicion>
                {
                    if ($configuracionCondicionesRequest/ns1:idEventoCondicion)
                    then <cat:Id>{fn:data($configuracionCondicionesRequest/ns1:idEventoCondicion)}</cat:Id>
                    else ()
                }
            </con:EventoCondicion>
            <con:subEstadoTCC>
                <cat:Id>29</cat:Id>
            </con:subEstadoTCC>
        </ns2:transaccionCondicion>
    </ns2:UpsertTransaccionCondicionRequest>
};

local:func($configuracionCondicionesRequest)
