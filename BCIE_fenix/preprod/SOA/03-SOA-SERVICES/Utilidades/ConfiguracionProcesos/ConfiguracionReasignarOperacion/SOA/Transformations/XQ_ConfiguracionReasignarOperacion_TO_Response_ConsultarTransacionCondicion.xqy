xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)


declare namespace confMO="http://www.bcie.org/ConfiguracionProcesosMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/Schema/ConfiguracionProcesosMO.xsd"::)
declare namespace con="http://www.bcie.org/CondicionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace con3 = "http://www.bcie.org/ContratoBO";

declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoBO";

declare namespace con2 = "http://www.bcie.org/ConfiguracionProcesosBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace adq = "http://www.bcie.org/AdquisicionBO";

declare namespace imp = "http://www.bcie.org/ImplementacionPctBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare variable $outConsultarTransaccionCondicion.response as element() (:: schema-element(con:ConsultarTransaccionCondicionResponse) ::) external;

declare function local:funcConfiguracionreasignaroperacion_to_response_consultartransacioncondicion($outConsultarTransaccionCondicion.response as element() (:: schema-element(con:ConsultarTransaccionCondicionResponse) ::)) as element() (:: schema-element(confMO:configuracionReasignarOperacionResponse) ::) {
    <con:configuracionReasignarOperacionResponse>
     
        <confMO:configuracionReasignarOperacion>
            <con2:Condiciones>
             {
       for $agrupador in distinct-values( $outConsultarTransaccionCondicion.response/con:TransaccionCondicion/con1:agrupador)
       let $transacion := $outConsultarTransaccionCondicion.response/con:TransaccionCondicion[con1:agrupador=$agrupador][1]
      
       return
       
           <con1:TransaccionCondicion>
            {
                if ($transacion/con1:IdTransaccion)
                then <con1:IdTransaccion>{fn:data($transacion/con1:IdTransaccion)}</con1:IdTransaccion>
                else ()
            }
            <con1:Operacion>
                {
                    if ($transacion/con1:Operacion/ope:idOperacion)
                    then <ope:idOperacion>{fn:data($transacion/con1:Operacion/ope:idOperacion)}</ope:idOperacion>
                    else ()
                }
            </con1:Operacion>
            <con1:Condicion>
               <con1:idCondicion>{fn:data($transacion/con1:Condicion/con1:idCondicion)}</con1:idCondicion>
            </con1:Condicion>
            <con1:EstadoTCC>
                {
                    if ($transacion/con1:Condicion/con1:estadoTCC/atr:id)
                    then <cat:Id>{fn:data($transacion/con1:Condicion/con1:estadoTCC/atr:id)}</cat:Id>
                    else ()
                }
            </con1:EstadoTCC>
            <con1:estatus>{fn:data($transacion/con1:estatus)}</con1:estatus>
            <con1:enProceso>{fn:data($transacion/con1:enProceso)}</con1:enProceso>
            {
                if ($transacion/con1:agrupador)
                then <con1:agrupador>{fn:data($transacion/con1:agrupador)}</con1:agrupador>
                else ()
            }
             <con1:EventoCondicion>
                {
                    if ($transacion/con1:EventoCondicion/cat:Id)
                    then <cat:Id>{fn:data($transacion/con1:EventoCondicion/cat:Id)}</cat:Id>
                    else ()
                }
            </con1:EventoCondicion>
            </con1:TransaccionCondicion>
            }
            </con2:Condiciones>
        </confMO:configuracionReasignarOperacion>
    </con:configuracionReasignarOperacionResponse>
};

local:funcConfiguracionreasignaroperacion_to_response_consultartransacioncondicion($outConsultarTransaccionCondicion.response)
