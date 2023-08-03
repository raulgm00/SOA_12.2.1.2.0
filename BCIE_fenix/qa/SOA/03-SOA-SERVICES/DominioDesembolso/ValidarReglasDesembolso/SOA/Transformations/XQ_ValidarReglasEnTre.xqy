xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace reg1 = "http://www.bcie.org/ReglaTareaBO";

declare variable $inputVariable.request as element() (:: schema-element(des:ValidarReglasDesembolsoRequest) ::) external;
declare variable $outConsultarReglasDesembolso.response as element() (:: schema-element(des:ConsultarReglasDesembolsoResponse) ::) external;

declare function local:funcXq_validarreglasentre($inputVariable.request as element() (:: schema-element(des:ValidarReglasDesembolsoRequest) ::), 
                                                 $outConsultarReglasDesembolso.response as element() (:: schema-element(des:ConsultarReglasDesembolsoResponse) ::)) 
                                                 as element() (:: schema-element(des:ConsultarReglasDesembolsoResponse) ::) {
    <des:ConsultarReglasDesembolsoResponse>
    
    {
    for $reglas in $inputVariable.request/des:TareaReglas/reg1:ReglasEvaluacion
    let $idRegla := if($outConsultarReglasDesembolso.response/des:ReglasEvaluacion[reg:Id/text() != $reglas/reg:Id/text()])then ''
                    else $reglas/reg:Id/text()
    return
       if($idRegla != '') then
        <des:ReglasEvaluacion>
            <reg:Id>{fn:data($reglas/reg:Id)}</reg:Id>
            {
                if ($reglas/reg:Descripcion)
                then <reg:Descripcion>{fn:data($reglas/reg:Descripcion)}</reg:Descripcion>
                else ()
            }
            {
                if ($reglas/reg:Transaccion)
                then <reg:Transaccion>{fn:data($reglas/reg:Transaccion)}</reg:Transaccion>
                else ()
            }
            {
                if ($reglas/reg:Tipo)
                then 
                    <reg:Tipo>
                        {
                            if ($reglas/reg:Tipo/cat:Id)
                            then <cat:Id>{fn:data($reglas/reg:Tipo/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($reglas/reg:Tipo/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($reglas/reg:Tipo/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($reglas/reg:Tipo/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($reglas/reg:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($reglas/reg:Tipo/cat:estatus)
                            then <cat:estatus>{fn:data($reglas/reg:Tipo/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($reglas/reg:Tipo/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($reglas/reg:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg:Tipo>
                else ()
            }
            {
                if ($reglas/reg:PosibleExceptuar)
                then <reg:PosibleExceptuar>{fn:data($reglas/reg:PosibleExceptuar)}</reg:PosibleExceptuar>
                else ()
            }
            {
                if ($reglas/reg:Exceptuado)
                then 
                    <reg:Exceptuado>
                        {
                            if ($reglas/reg:Exceptuado/cat:Id)
                            then <cat:Id>{fn:data($reglas/reg:Exceptuado/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($reglas/reg:Exceptuado/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($reglas/reg:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($reglas/reg:Exceptuado/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($reglas/reg:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($reglas/reg:Exceptuado/cat:estatus)
                            then <cat:estatus>{fn:data($reglas/reg:Exceptuado/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($reglas/reg:Exceptuado/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($reglas/reg:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg:Exceptuado>
                else ()
            }
            {
                if ($reglas/reg:UsuarioExceptua)
                then <reg:UsuarioExceptua>{fn:data($reglas/reg:UsuarioExceptua)}</reg:UsuarioExceptua>
                else ()
            }
            {
                if ($reglas/reg:FechaExcepcion)
                then <reg:FechaExcepcion>{fn:data($reglas/reg:FechaExcepcion)}</reg:FechaExcepcion>
                else ()
            }
            {
                if ($reglas/reg:Estado)
                then 
                    <reg:Estado>
                        {
                            if ($reglas/reg:Estado/cat:Id)
                            then <cat:Id>{fn:data($reglas/reg:Estado/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($reglas/reg:Estado/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($reglas/reg:Estado/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($reglas/reg:Estado/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($reglas/reg:Estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($reglas/reg:Estado/cat:estatus)
                            then <cat:estatus>{fn:data($reglas/reg:Estado/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($reglas/reg:Estado/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($reglas/reg:Estado/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg:Estado>
                else ()
            }
            {
                if ($reglas/reg:Estatus)
                then <reg:Estatus>{fn:data($reglas/reg:Estatus)}</reg:Estatus>
                else ()
            }
            <reg1:EsAdvertencia>{fn:data($reglas/reg1:EsAdvertencia)}</reg1:EsAdvertencia>
            <reg1:EsError>{fn:data($reglas/reg1:EsError)}</reg1:EsError>
            <reg1:Mensaje>
                {
                    if ($reglas/reg1:Mensaje/cat:Id)
                    then <cat:Id>{fn:data($reglas/reg1:Mensaje/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($reglas/reg1:Mensaje/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($reglas/reg1:Mensaje/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
                {
                    if ($reglas/reg1:Mensaje/cat:DescripcionCorta)
                    then <cat:DescripcionCorta>{fn:data($reglas/reg1:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    else ()
                }
                {
                    if ($reglas/reg1:Mensaje/cat:estatus)
                    then <cat:estatus>{fn:data($reglas/reg1:Mensaje/cat:estatus)}</cat:estatus>
                    else ()
                }
                {
                    if ($reglas/reg1:Mensaje/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($reglas/reg1:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </reg1:Mensaje>
        </des:ReglasEvaluacion>
        else()
        }
    </des:ConsultarReglasDesembolsoResponse>
};

local:funcXq_validarreglasentre($inputVariable.request, $outConsultarReglasDesembolso.response)
