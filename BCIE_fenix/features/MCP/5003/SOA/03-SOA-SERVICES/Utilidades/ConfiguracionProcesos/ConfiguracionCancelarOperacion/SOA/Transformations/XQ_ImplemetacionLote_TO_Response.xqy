xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace con="http://www.bcie.org/ConfiguracionProcesosMO";
                 (:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/Schema/ConfiguracionProcesosMO.xsd" ::)
declare namespace imp="http://www.bcie.org/ImplementacionPctMO";
                 (:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/implementacionPCT/V1/Schema/ImplementacionPctMO.xsd" ::)

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/ConfiguracionProcesosBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace adq = "http://www.bcie.org/AdquisicionBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace imp1 = "http://www.bcie.org/ImplementacionPctBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace con2 = "http://www.bcie.org/ContratoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace con3 = "http://www.bcie.org/CondicionBO";

declare variable $outConsultarImplementacionLote.response as element()
                 (:: schema-element(imp:ConsultarImplementacionLoteResponse) ::) external;
 
declare function local:funcXq_implemetacionlote_to_response($outConsultarImplementacionLote.response as element() (:: schema-element(imp:ConsultarImplementacionLoteResponse) ::)) as element() (:: schema-element(con:configuracionCancelarOperacionResponse) ::) {
    <con:configuracionCancelarOperacionResponse>
        <con:configuracionCancelarOperacion>
            <con1:ImplementacionPCT>
            {
            for $ImplementacionLote in $outConsultarImplementacionLote.response/imp:ImplementacionLote
            return
            for $idLoteImpementacion in distinct-values($ImplementacionLote/imp1:loteImplementacion/imp1:idLote)
            let $loteImpementacion := $ImplementacionLote/imp1:loteImplementacion[imp1:idLote = $idLoteImpementacion][1]
            return 
               
                for $loteImplementacion in $loteImpementacion
                return 
                <imp1:loteImplementacion>
                    <imp1:idLote>{fn:data($loteImpementacion/imp1:idLote)}</imp1:idLote>
                    {
                        if ($loteImplementacion/imp1:nombre)
                        then 
                            if ($loteImpementacion/imp1:nombre)
                            then <imp1:nombre>{fn:data($loteImpementacion/imp1:nombre)}</imp1:nombre>
                            else ()
                        else ()
                    }
                    {
                        if ($loteImplementacion/imp1:monto)
                        then 
                            <imp1:monto>
                                <com:tipo>
                                    {
                                        if ($loteImplementacion/imp1:monto/com:tipo/cat:Id)
                                        then <cat:Id>{fn:data($loteImplementacion/imp1:monto/com:tipo/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($loteImplementacion/imp1:monto/com:tipo/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($loteImplementacion/imp1:monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($loteImplementacion/imp1:monto/com:tipo/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($loteImplementacion/imp1:monto/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($loteImplementacion/imp1:monto/com:tipo/cat:estatus)
                                        then <cat:estatus>{fn:data($loteImplementacion/imp1:monto/com:tipo/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($loteImplementacion/imp1:monto/com:tipo/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($loteImplementacion/imp1:monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </com:tipo>
                                {
                                    if ($loteImplementacion/imp1:monto/com:importe)
                                    then <com:importe>{fn:data($loteImplementacion/imp1:monto/com:importe)}</com:importe>
                                    else ()
                                }
                                {
                                    if ($loteImplementacion/imp1:monto/com:moneda)
                                    then 
                                        <com:moneda>
                                            {
                                                if ($loteImplementacion/imp1:monto/com:moneda/cat:Id)
                                                then <cat:Id>{fn:data($loteImplementacion/imp1:monto/com:moneda/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($loteImplementacion/imp1:monto/com:moneda/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($loteImplementacion/imp1:monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($loteImplementacion/imp1:monto/com:moneda/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($loteImplementacion/imp1:monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($loteImplementacion/imp1:monto/com:moneda/cat:estatus)
                                                then <cat:estatus>{fn:data($loteImplementacion/imp1:monto/com:moneda/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($loteImplementacion/imp1:monto/com:moneda/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($loteImplementacion/imp1:monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </com:moneda>
                                    else ()
                                }
                            </imp1:monto>
                        else ()
                    }
                    {
                        if ($loteImplementacion/imp1:estatus)
                        then <imp1:estatus>{fn:data($loteImplementacion/imp1:estatus)}</imp1:estatus>
                        else ()
                    }
                    {
                        if ($loteImplementacion/imp1:enProceso)
                        then <imp1:enProceso>{fn:data($loteImplementacion/imp1:enProceso)}</imp1:enProceso>
                        else ()
                    }
                </imp1:loteImplementacion>
                }
            </con1:ImplementacionPCT>
            </con:configuracionCancelarOperacion>
    </con:configuracionCancelarOperacionResponse>
};

local:funcXq_implemetacionlote_to_response($outConsultarImplementacionLote.response)
