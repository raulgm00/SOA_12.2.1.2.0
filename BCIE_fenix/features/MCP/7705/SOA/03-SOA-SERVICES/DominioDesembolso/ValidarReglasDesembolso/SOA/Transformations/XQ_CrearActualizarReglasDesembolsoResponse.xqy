xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace reg1 = "http://www.bcie.org/ReglaTareaBO";

declare variable $consultarReglasDesembolsoAux.response as element() (:: schema-element(des:ConsultarReglasDesembolsoResponse) ::) external;
declare variable $outCrearActualizarReglasDesembolso.response as element() (:: schema-element(des:CrearActualizarReglasDesembolsoResponse) ::) external;

declare function local:funcXq_crearactualizarreglasdesembolsoresponse($consultarReglasDesembolsoAux.response as element() (:: schema-element(des:ConsultarReglasDesembolsoResponse) ::), 
                                                                      $outCrearActualizarReglasDesembolso.response as element() (:: schema-element(des:CrearActualizarReglasDesembolsoResponse) ::)) 
                                                                      as element() (:: schema-element(des:ConsultarReglasDesembolsoResponse) ::) {
    <des:ConsultarReglasDesembolsoResponse>
        {
            for $ReglasEvaluacion in $consultarReglasDesembolsoAux.response/des:ReglasEvaluacion
            return 
            <des:ReglasEvaluacion>
                <reg:Id>{fn:data($ReglasEvaluacion/reg:Id)}</reg:Id>
                {
                    if ($ReglasEvaluacion/reg:Descripcion)
                    then <reg:Descripcion>{fn:data($ReglasEvaluacion/reg:Descripcion)}</reg:Descripcion>
                    else ()
                }
                {
                    if ($ReglasEvaluacion/reg:Transaccion)
                    then <reg:Transaccion>{fn:data($ReglasEvaluacion/reg:Transaccion)}</reg:Transaccion>
                    else ()
                }
                {
                    if ($ReglasEvaluacion/reg:Tipo)
                    then 
                        <reg:Tipo>
                            {
                                if ($ReglasEvaluacion/reg:Tipo/cat:Id)
                                then <cat:Id>{fn:data($ReglasEvaluacion/reg:Tipo/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($ReglasEvaluacion/reg:Tipo/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($ReglasEvaluacion/reg:Tipo/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($ReglasEvaluacion/reg:Tipo/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($ReglasEvaluacion/reg:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($ReglasEvaluacion/reg:Tipo/cat:estatus)
                                then <cat:estatus>{fn:data($ReglasEvaluacion/reg:Tipo/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($ReglasEvaluacion/reg:Tipo/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($ReglasEvaluacion/reg:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </reg:Tipo>
                    else ()
                }
                {
                    if ($ReglasEvaluacion/reg:PosibleExceptuar)
                    then <reg:PosibleExceptuar>{fn:data($ReglasEvaluacion/reg:PosibleExceptuar)}</reg:PosibleExceptuar>
                    else ()
                }
                {
                    if ($ReglasEvaluacion/reg:Exceptuado)
                    then 
                        <reg:Exceptuado>
                            <cat:Id>{fn:data($outCrearActualizarReglasDesembolso.response/des:ReglasDesembolso[reg:Id = $ReglasEvaluacion/reg:Id]/reg:Exceptuado/cat:Id)}</cat:Id>
                            <cat:Descripcion>{fn:data($outCrearActualizarReglasDesembolso.response/des:ReglasDesembolso/reg:Id)}</cat:Descripcion>
                            {
                                if ($ReglasEvaluacion/reg:Exceptuado/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($ReglasEvaluacion/reg:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($ReglasEvaluacion/reg:Exceptuado/cat:estatus)
                                then <cat:estatus>{fn:data($ReglasEvaluacion/reg:Exceptuado/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($ReglasEvaluacion/reg:Exceptuado/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($ReglasEvaluacion/reg:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </reg:Exceptuado>
                    else ()
                }
                {
                    if ($ReglasEvaluacion/reg:UsuarioExceptua)
                    then <reg:UsuarioExceptua>{fn:data($ReglasEvaluacion/reg:UsuarioExceptua)}</reg:UsuarioExceptua>
                    else ()
                }
                {
                    if ($ReglasEvaluacion/reg:FechaExceptua)
                    then <reg:FechaExceptua>{fn:data($ReglasEvaluacion/reg:FechaExceptua)}</reg:FechaExceptua>
                    else ()
                }
                {
                    if ($ReglasEvaluacion/reg:Estado)
                    then 
                        <reg:Estado>
                            {
                                if ($ReglasEvaluacion/reg:Estado/cat:Id)
                                then <cat:Id>{fn:data($ReglasEvaluacion/reg:Estado/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($ReglasEvaluacion/reg:Estado/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($ReglasEvaluacion/reg:Estado/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($ReglasEvaluacion/reg:Estado/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($ReglasEvaluacion/reg:Estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($ReglasEvaluacion/reg:Estado/cat:estatus)
                                then <cat:estatus>{fn:data($ReglasEvaluacion/reg:Estado/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($ReglasEvaluacion/reg:Estado/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($ReglasEvaluacion/reg:Estado/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </reg:Estado>
                    else ()
                }
                {
                    if ($ReglasEvaluacion/reg:Estatus)
                    then <reg:Estatus>{fn:data($ReglasEvaluacion/reg:Estatus)}</reg:Estatus>
                    else ()
                }
                <reg1:EsAdvertencia>{fn:data($ReglasEvaluacion/reg1:EsAdvertencia)}</reg1:EsAdvertencia>
                <reg1:EsError>{fn:data($ReglasEvaluacion/reg1:EsError)}</reg1:EsError>
                <reg1:Mensaje>
                    {
                        if ($ReglasEvaluacion/reg1:Mensaje/cat:Id)
                        then <cat:Id>{fn:data($ReglasEvaluacion/reg1:Mensaje/cat:Id)}</cat:Id>
                        else ()
                    }
                    {
                        if ($ReglasEvaluacion/reg1:Mensaje/cat:Descripcion)
                        then <cat:Descripcion>{fn:data($ReglasEvaluacion/reg1:Mensaje/cat:Descripcion)}</cat:Descripcion>
                        else ()
                    }
                    {
                        if ($ReglasEvaluacion/reg1:Mensaje/cat:DescripcionCorta)
                        then <cat:DescripcionCorta>{fn:data($ReglasEvaluacion/reg1:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                        else ()
                    }
                    {
                        if ($ReglasEvaluacion/reg1:Mensaje/cat:estatus)
                        then <cat:estatus>{fn:data($ReglasEvaluacion/reg1:Mensaje/cat:estatus)}</cat:estatus>
                        else ()
                    }
                    {
                        if ($ReglasEvaluacion/reg1:Mensaje/cat:codigoExterno)
                        then <cat:codigoExterno>{fn:data($ReglasEvaluacion/reg1:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                        else ()
                    }
                </reg1:Mensaje>
            </des:ReglasEvaluacion>
        }
    </des:ConsultarReglasDesembolsoResponse>
};

local:funcXq_crearactualizarreglasdesembolsoresponse($consultarReglasDesembolsoAux.response, $outCrearActualizarReglasDesembolso.response)
