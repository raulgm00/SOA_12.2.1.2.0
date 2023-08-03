xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace cli="http://www.bcie.org/ClienteMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace reg = "http://www.bcie.org/ReglaTareaBO";

declare namespace reg1 = "http://www.bcie.org/ReglaBO";

declare namespace cli1 = "http://www.bcie.org/ClienteBO";

declare variable $inputVariable.request as element() (:: schema-element(des:ValidarReglasDesembolsoRequest) ::) external;
declare variable $outValidarSCR.response as element() (:: schema-element(cli:ValidarSCRResponse) ::) external;

declare function local:funcXq_validarscr($inputVariable.request as element() (:: schema-element(des:ValidarReglasDesembolsoRequest) ::), 
                                         $outValidarSCR.response as element() (:: schema-element(cli:ValidarSCRResponse) ::)) 
                                         as element() (:: schema-element(des:ValidarReglasDesembolsoRequest) ::) {
    <des:ValidarReglasDesembolsoRequest>
        <des:idDesembolso>{fn:data($inputVariable.request/des:idDesembolso)}</des:idDesembolso>
        <des:instanciaProceso>{fn:data($inputVariable.request/des:instanciaProceso)}</des:instanciaProceso>
        <des:TareaReglas>
        
        
            <reg:IdTarea>{fn:data($inputVariable.request/des:TareaReglas/reg:IdTarea)}</reg:IdTarea>
            {
                if ($inputVariable.request/des:TareaReglas/reg:Proceso)
                then <reg:Proceso>{fn:data($inputVariable.request/des:TareaReglas/reg:Proceso)}</reg:Proceso>
                else ()
            }
            {
                if ($inputVariable.request/des:TareaReglas/reg:Descripcion)
                then <reg:Descripcion>{fn:data($inputVariable.request/des:TareaReglas/reg:Descripcion)}</reg:Descripcion>
                else ()
            }
            {
                if ($inputVariable.request/des:TareaReglas/reg:Estatus)
                then <reg:Estatus>{fn:data($inputVariable.request/des:TareaReglas/reg:Estatus)}</reg:Estatus>
                else ()
            }
            {
                for $ReglasEvaluacion in $inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]
                let $estatusComparacion := fn:substring-before($outValidarSCR.response/cli:Cliente/cli1:detalleSCR/cli1:estatusComparacion,'_')
                let $reglaSCR1 := if($ReglasEvaluacion/reg1:Exceptuado/cat:estatus = true())then ('EXCEPTUADA')
                                    else(if($ReglasEvaluacion/reg1:PosibleExceptuar = true())then (
                                  if($estatusComparacion = 'MENOR') then 'CUMPLIDA' else 'NO_CUMPLIDA')
                                  else(if($estatusComparacion = 'MENOR' or $estatusComparacion = 'IGUAL')then
                                  'CUMPLIDA' else 'NO_CUMPLIDA'))
                let $reglaSCR2 := if(count($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'])>1)then(if($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][2]/reg1:Exceptuado/cat:estatus = true())then('EXCEPTUADA')
                                    else(if($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][2]/reg1:PosibleExceptuar = true())then(if($estatusComparacion = 'MENOR') then('CUMPLIDA') 
                                        else('NO_CUMPLIDA'))
                                          else(if($estatusComparacion = 'MENOR' or $estatusComparacion = 'IGUAL') then('CUMPLIDA') 
                                              else('NO_CUMPLIDA'))))
                                  else()
                let $estadoSCR := if (fn:string($outValidarSCR.response/cli:Cliente/cli1:detalleSCR/cli1:estatusComparacion)='NO_EVALUADA')then 'CUMPLIDA' else( if($reglaSCR2 != '')then(local:funcXq_validarEstado($reglaSCR1,$reglaSCR2))else $reglaSCR1)
                
                let $mensajeSCR := fn:concat("SCR_FUENTE: ",fn:data($outValidarSCR.response/cli:Cliente/cli1:detalleSCR/cli1:scrFuente/cli1:observacion)
                                              ," - ",fn:data($outValidarSCR.response/cli:Cliente/cli1:detalleSCR/cli1:scrFuente/cli1:scr)," SCR_REFERENCIA: "
                                              ,fn:data($outValidarSCR.response/cli:Cliente/cli1:detalleSCR/cli1:scrReferencia/cli1:scr)," ESTATUS_COMPARACION: "
                                              ,fn:data($outValidarSCR.response/cli:Cliente/cli1:detalleSCR/cli1:estatusComparacion))
                return 
                <reg:ReglasEvaluacion>
                    <reg1:Id>
                    {
                      if ($reglaSCR1 = 'NO_CUMPLIDA') 
                        then
                          fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Id)                      
                      else if ($reglaSCR2 = 'NO_CUMPLIDA')
                        then
                          fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][2]/reg1:Id)
                      else ()
                    }
                    </reg1:Id>
                    {
                        if ($ReglasEvaluacion/reg1:Descripcion)
                        then <reg1:Descripcion>{fn:data($ReglasEvaluacion/reg1:Descripcion)}</reg1:Descripcion>
                        else ()
                    }
                    {
                        if ($ReglasEvaluacion/reg1:Transaccion)
                        then <reg1:Transaccion>{fn:data($ReglasEvaluacion/reg1:Transaccion)}</reg1:Transaccion>
                        else ()
                    }
                    {
                        if ($ReglasEvaluacion/reg1:Tipo)
                        then 
                            <reg1:Tipo>
                                {
                                    if ($ReglasEvaluacion/reg1:Tipo/cat:Id)
                                    then <cat:Id>{fn:data($ReglasEvaluacion/reg1:Tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasEvaluacion/reg1:Tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasEvaluacion/reg1:Tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasEvaluacion/reg1:Tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasEvaluacion/reg1:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasEvaluacion/reg1:Tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasEvaluacion/reg1:Tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasEvaluacion/reg1:Tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasEvaluacion/reg1:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Tipo>
                        else ()
                    }
                    {
                        if ($ReglasEvaluacion/reg1:PosibleExceptuar)
                        then <reg1:PosibleExceptuar>{fn:data($ReglasEvaluacion/reg1:PosibleExceptuar)}</reg1:PosibleExceptuar>
                        else ()
                    }
                    {
                        if ($ReglasEvaluacion/reg1:Exceptuado)
                        then 
                            <reg1:Exceptuado>
                                {
                                    if ($ReglasEvaluacion/reg1:Exceptuado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasEvaluacion/reg1:Exceptuado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasEvaluacion/reg1:Exceptuado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasEvaluacion/reg1:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasEvaluacion/reg1:Exceptuado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasEvaluacion/reg1:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasEvaluacion/reg1:Exceptuado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasEvaluacion/reg1:Exceptuado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasEvaluacion/reg1:Exceptuado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasEvaluacion/reg1:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Exceptuado>
                        else ()
                    }
                    {
                        if ($ReglasEvaluacion/reg1:UsuarioExceptua)
                        then <reg1:UsuarioExceptua>{fn:data($ReglasEvaluacion/reg1:UsuarioExceptua)}</reg1:UsuarioExceptua>
                        else ()
                    }
                    {
                        if ($ReglasEvaluacion/reg1:FechaExcepcion)
                        then <reg1:FechaExcepcion>{fn:data($ReglasEvaluacion/reg1:FechaExcepcion)}</reg1:FechaExcepcion>
                        else ()
                    }
                    {
                        if ($ReglasEvaluacion/reg1:Estado)
                        then 
                            <reg1:Estado>
                                {
                                    if ($ReglasEvaluacion/reg1:Estado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasEvaluacion/reg1:Estado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasEvaluacion/reg1:Estado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasEvaluacion/reg1:Estado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                <cat:DescripcionCorta>{$estadoSCR}</cat:DescripcionCorta>
                                {
                                    if ($ReglasEvaluacion/reg1:Estado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasEvaluacion/reg1:Estado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasEvaluacion/reg1:Estado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasEvaluacion/reg1:Estado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Estado>
                        else ()
                    }
                    {
                        if ($ReglasEvaluacion/reg1:Estatus)
                        then <reg1:Estatus>{fn:data($ReglasEvaluacion/reg1:Estatus)}</reg1:Estatus>
                        else ()
                    }
                    <reg:EsAdvertencia>{fn:data($ReglasEvaluacion/reg:EsAdvertencia)}</reg:EsAdvertencia>
                    <reg:EsError>{fn:data($ReglasEvaluacion/reg:EsError)}</reg:EsError>
                    <reg:Mensaje>
                        {
                            if ($ReglasEvaluacion/reg:Mensaje/cat:Id)
                            then <cat:Id>{fn:data($ReglasEvaluacion/reg:Mensaje/cat:Id)}</cat:Id>
                            else ()
                        }
                                <cat:Descripcion>{$mensajeSCR}</cat:Descripcion>
                        {
                            if ($ReglasEvaluacion/reg:Mensaje/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ReglasEvaluacion/reg:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ReglasEvaluacion/reg:Mensaje/cat:estatus)
                            then <cat:estatus>{fn:data($ReglasEvaluacion/reg:Mensaje/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ReglasEvaluacion/reg:Mensaje/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ReglasEvaluacion/reg:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg:Mensaje>
                </reg:ReglasEvaluacion>
            }
      
        </des:TareaReglas>
    </des:ValidarReglasDesembolsoRequest>
};

declare function local:funcXq_validarEstado($reglaSCR1 as xs:string, 
                                            $reglaSCR2 as xs:string) 
                                            as xs:string {
    if ($reglaSCR1 = 'CUMPLIDA' and $reglaSCR2 = 'CUMPLIDA') then
                ('CUMPLIDA')
                else
                (if($reglaSCR1 = 'NO_CUMPLIDA' and $reglaSCR2 = 'NO_CUMPLIDA')then ('NO_CUMPLIDA')
                else
                (if($reglaSCR1 = 'EXCEPTUADA' and $reglaSCR2 = 'EXCEPTUADA')then('EXCEPTUADA')
                else
                (if (($reglaSCR1 = 'CUMPLIDA' or $reglaSCR1 = 'EXCEPTUADA') and ($reglaSCR2 = 'EXCEPTUADA' or $reglaSCR2 = 'CUMPLIDA'))then ('EXCEPTUADA')
                else
                (if(($reglaSCR1 = 'EXCEPTUADA' or $reglaSCR1 = 'NO_CUMPLIDA') and ($reglaSCR2 = 'NO_CUMPLIDA' or $reglaSCR2 = 'EXCEPTUADA'))then('NO_CUMPLIDA')
                else
                (if (($reglaSCR1 = 'CUMPLIDA' or $reglaSCR1 = 'NO_CUMPLIDA') and ($reglaSCR2 = 'NO_CUMPLIDA' or $reglaSCR2 = 'CUMPLIDA'))then ('NO_CUMPLIDA')
                else
                ())))))
       
};

local:funcXq_validarscr($inputVariable.request, $outValidarSCR.response)
