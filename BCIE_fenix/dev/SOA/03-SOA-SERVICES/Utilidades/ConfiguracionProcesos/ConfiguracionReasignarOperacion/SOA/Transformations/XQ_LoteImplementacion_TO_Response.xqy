xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace imp="http://www.bcie.org/ImplementacionPctMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/implementacionPCT/V1/Schema/ImplementacionPctMO.xsd" ::)

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace imp1 = "http://www.bcie.org/ImplementacionPctBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $outConsultarImplementacionLote.response as element() (:: schema-element(imp:ConsultarImplementacionLoteResponse) ::) external;

declare function local:funcConsultarloteimplementacion_to_listalotes($outConsultarImplementacionLote.response as element() (:: schema-element(imp:ConsultarImplementacionLoteResponse) ::)) as element() (:: schema-element(imp:ConsultarImplementacionLoteResponse) ::) {
    <imp:ConsultarImplementacionLoteResponse>
         <imp:ImplementacionLote>
             {
               for $ImplementacionLote in $outConsultarImplementacionLote.response/imp:ImplementacionLote
               return 
           
                  
                    for $idLoteImpementacion in distinct-values($ImplementacionLote/imp1:loteImplementacion/imp1:idLote)
                    let $loteImpementacion := $ImplementacionLote/imp1:loteImplementacion[imp1:idLote = $idLoteImpementacion][1]
      
                    return 
                    <imp1:loteImplementacion>
                        <imp1:idLote>{fn:data($loteImpementacion/imp1:idLote)}</imp1:idLote>
                        {
                            if ($loteImpementacion/imp1:nombre)
                            then <imp1:nombre>{fn:data($loteImpementacion/imp1:nombre)}</imp1:nombre>
                            else ()
                        }
                        {
                            if ($loteImpementacion/imp1:monto)
                            then 
                                <imp1:monto>
                                    <com:tipo>
                                        {
                                            if ($loteImpementacion/imp1:monto/com:tipo/cat:Id)
                                            then <cat:Id>{fn:data($loteImpementacion/imp1:monto/com:tipo/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($loteImpementacion/imp1:monto/com:tipo/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($loteImpementacion/imp1:monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($loteImpementacion/imp1:monto/com:tipo/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($loteImpementacion/imp1:monto/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($loteImpementacion/imp1:monto/com:tipo/cat:estatus)
                                            then <cat:estatus>{fn:data($loteImpementacion/imp1:monto/com:tipo/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($loteImpementacion/imp1:monto/com:tipo/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($loteImpementacion/imp1:monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </com:tipo>
                                    {
                                        if ($loteImpementacion/imp1:monto/com:importe)
                                        then <com:importe>{fn:data($loteImpementacion/imp1:monto/com:importe)}</com:importe>
                                        else ()
                                    }
                                    {
                                        if ($loteImpementacion/imp1:monto/com:moneda)
                                        then 
                                            <com:moneda>
                                                {
                                                    if ($loteImpementacion/imp1:monto/com:moneda/cat:Id)
                                                    then <cat:Id>{fn:data($loteImpementacion/imp1:monto/com:moneda/cat:Id)}</cat:Id>
                                                    else ()
                                                }
                                                {
                                                    if ($loteImpementacion/imp1:monto/com:moneda/cat:Descripcion)
                                                    then <cat:Descripcion>{fn:data($loteImpementacion/imp1:monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                                    else ()
                                                }
                                                {
                                                    if ($loteImpementacion/imp1:monto/com:moneda/cat:DescripcionCorta)
                                                    then <cat:DescripcionCorta>{fn:data($loteImpementacion/imp1:monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                    else ()
                                                }
                                                {
                                                    if ($loteImpementacion/imp1:monto/com:moneda/cat:estatus)
                                                    then <cat:estatus>{fn:data($loteImpementacion/imp1:monto/com:moneda/cat:estatus)}</cat:estatus>
                                                    else ()
                                                }
                                                {
                                                    if ($loteImpementacion/imp1:monto/com:moneda/cat:codigoExterno)
                                                    then <cat:codigoExterno>{fn:data($loteImpementacion/imp1:monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                                    else ()
                                                }
                                            </com:moneda>
                                        else ()
                                    }
                                </imp1:monto>
                            else ()
                        }
                        {
                            if ($loteImpementacion/imp1:estatus)
                            then <imp1:estatus>{fn:data($loteImpementacion/imp1:estatus)}</imp1:estatus>
                            else ()
                        }
                        {
                            if ($loteImpementacion/imp1:enProceso)
                            then <imp1:enProceso>{fn:data($loteImpementacion/imp1:enProceso)}</imp1:enProceso>
                            else ()
                        }
                        
                    </imp1:loteImplementacion>
                }
            </imp:ImplementacionLote>
    </imp:ConsultarImplementacionLoteResponse>
};

local:funcConsultarloteimplementacion_to_listalotes($outConsultarImplementacionLote.response)
