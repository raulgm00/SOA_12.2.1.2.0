xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace functx = "http://www.functx.com";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $outConsultarDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::) external;
declare variable $ValidarConsultarPlazoGracia.response as element() (:: schema-element(des:ConsultarPlazoGraciaByLineaFinancieraResponse) ::) external;

declare function local:funcXq_validaplazogracia($outConsultarDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::), 
                                                $ValidarConsultarPlazoGracia.response as element() (:: schema-element(des:ConsultarPlazoGraciaByLineaFinancieraResponse) ::)) 
                                                as element() (:: schema-element(des:ConsultarPlazoGraciaByLineaFinancieraResponse) ::) {
    <des:ConsultarPlazoGraciaByLineaFinancieraResponse>
        {
            for $LimitePlazo in $ValidarConsultarPlazoGracia.response/des:LimitePlazo
            return 
            <des:LimitePlazo>
                <com:Tipo>
                  <cat:Id>
                    {
                        if (fn:string($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:fechaDisponibilidadFondos)!='')
                        then 
                        xs:date(fn:substring(xs:string(
                        functx:add-period(fn:data($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:fechaDisponibilidadFondos),
                        xs:integer(fn:data($LimitePlazo/com:Valor)),
                        fn:data($LimitePlazo/com:Tipo/cat:codigoExterno/text()))),0,11))
                        else ()
                    }
                  </cat:Id>
                  <cat:Descripcion>
                    {
                        if 
                        (
                          (
                            xs:string($LimitePlazo/des1:TipoLimite/cat:codigoExterno) = '1' 
                            or
                            xs:string($LimitePlazo/des1:TipoLimite/cat:codigoExterno) = '3'
                          )
                        and
                          (
                          fn:string( $outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:fechaVencimiento)!=''
                          )
                        ) 
                      then
                        xs:date(fn:substring($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:fechaVencimiento/text(),0,11))
                      else if (xs:string($LimitePlazo/des1:TipoLimite/cat:codigoExterno) = '2' and fn:string($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:fechaDisponibilidadFondos)!='')
                      then
                          xs:date
                          (
                            fn:substring
                            (
                                xs:string
                                (
                                    functx:add-period
                                    (
                                        fn:data($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:fechaDisponibilidadFondos),
                                        xs:integer(fn:data($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:periodoGracia/des1:Frecuencia/com:Valor)),
                                        fn:data($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:periodoGracia/des1:Frecuencia/com:Tipo/cat:codigoExterno)
                                    )
                                )
                            ,0,11)
                          ) 
                          
                      else ()
                    }
                  </cat:Descripcion>
                    {
                        if ($LimitePlazo/com:Tipo/cat:DescripcionCorta)
                        then <cat:DescripcionCorta>{fn:data($LimitePlazo/com:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                        else ()
                    }
                    <cat:estatus>
                   
                   
                    {
                    if 
                    (
                      (
                        xs:string($LimitePlazo/des1:TipoLimite/cat:codigoExterno) = '1' 
                      )
                    and
                      (
                      fn:string($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:fechaDisponibilidadFondos)!=''
                      )
                    ) 
                      
                      then
                        if (xs:date
                            (fn:substring
                            
                              (xs:string
                                (functx:add-period
                                  (fn:data($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:fechaDisponibilidadFondos),
                                  xs:integer(fn:data($LimitePlazo/com:Valor)),
                                  fn:data($LimitePlazo/com:Tipo/cat:codigoExterno/text()
                                  )
                              )
                            ),0,11)) >= xs:date(fn:substring($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:fechaVencimiento/text(),0,11)))
                        then 0
                        else 1
                      else if (xs:string($LimitePlazo/des1:TipoLimite/cat:codigoExterno) = '2' and 
                      fn:string($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:fechaDisponibilidadFondos)!='')
                      then
                            if 
                            (    
                              xs:date
                              (
                                fn:substring
                                (
                                  xs:string
                                  (
                                    functx:add-period
                                    (
                                      fn:data($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:fechaDisponibilidadFondos),
                                      xs:integer(fn:data($LimitePlazo/com:Valor)),
                                      fn:data($LimitePlazo/com:Tipo/cat:codigoExterno/text())
                                    )
                                  )
                                ,0,11)
                              ) 
                              >= 
                              xs:date
                              (
                                fn:substring
                                (
                                  xs:string
                                  (
                                    functx:add-period
                                    (
                                        fn:data($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:fechaDisponibilidadFondos),
                                        xs:integer(fn:data($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:periodoGracia/des1:Frecuencia/com:Valor)),
                                        fn:data($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:periodoGracia/des1:Frecuencia/com:Tipo/cat:codigoExterno)
                                    )
                                  )
                                ,0,11)
                              ) 
                            )
                            then 0
                            else 1
                      else if 
                        (
                          (
                            xs:string($LimitePlazo/des1:TipoLimite/cat:codigoExterno) = '3'
                          )
                        and
                          (
                          fn:string($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:fechaDisponibilidadFondos)!=''
                          )
                        ) 
                          
                          then
                            if (xs:date
                                (fn:substring
                                
                                  (xs:string
                                    (functx:add-period
                                      (fn:data($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:fechaDisponibilidadFondos),
                                      xs:integer(fn:data($LimitePlazo/com:Valor)),
                                      fn:data($LimitePlazo/com:Tipo/cat:codigoExterno/text()
                                      )
                                  )
                                ),0,11)) <= xs:date(fn:substring($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:fechaVencimiento/text(),0,11)))
                            then 0
                            else 1
                      else 0
                    }
                    
                    
                    </cat:estatus>
                    {
                        if ($LimitePlazo/com:Tipo/cat:codigoExterno)
                        then <cat:codigoExterno>{fn:data($LimitePlazo/com:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                        else ()
                    }
                </com:Tipo>
                <com:Valor>{fn:data($LimitePlazo/com:Valor)}</com:Valor>
                <des1:TipoLimite>
                    {
                        if ($LimitePlazo/des1:TipoLimite/cat:Id)
                        then <cat:Id>{fn:data($LimitePlazo/des1:TipoLimite/cat:Id)}</cat:Id>
                        else ()
                    }
                    {
                        if ($LimitePlazo/des1:TipoLimite/cat:Descripcion)
                        then <cat:Descripcion>{fn:data($LimitePlazo/des1:TipoLimite/cat:Descripcion)}</cat:Descripcion>
                        else ()
                    }
                    {
                        if ($LimitePlazo/des1:TipoLimite/cat:DescripcionCorta)
                        then <cat:DescripcionCorta>{fn:data($LimitePlazo/des1:TipoLimite/cat:DescripcionCorta)}</cat:DescripcionCorta>
                        else ()
                    }
                    {
                        if ($LimitePlazo/des1:TipoLimite/cat:estatus)
                        then <cat:estatus>{fn:data($LimitePlazo/des1:TipoLimite/cat:estatus)}</cat:estatus>
                        else ()
                    }
                    {
                        if ($LimitePlazo/des1:TipoLimite/cat:codigoExterno)
                        then <cat:codigoExterno>{fn:data($LimitePlazo/des1:TipoLimite/cat:codigoExterno)}</cat:codigoExterno>
                        else ()
                    }
                </des1:TipoLimite>
            </des:LimitePlazo>
        }
    </des:ConsultarPlazoGraciaByLineaFinancieraResponse>
};

declare function functx:add-period
  ( $date as xs:anyAtomicType? ,
    $value as xs:integer? ,
    $value-tipe as xs:string? )  as xs:dateTime? {
    if ($value-tipe = 'Y')
    then functx:add-months(xs:dateTime($date),xs:integer(12 * $value))
    else 
      if ($value-tipe = 'M')
      then functx:add-months(xs:dateTime($date),$value)
      else functx:add-day(xs:dateTime($date),$value)
 } ;
 
declare function functx:add-months
  ( $date as xs:anyAtomicType? ,
    $months as xs:integer )  as xs:dateTime? {
   xs:dateTime($date) + functx:yearMonthDuration(0,$months)
 } ;
 
 declare function functx:yearMonthDuration
  ( $years as xs:decimal? ,
    $months as xs:integer? )  as xs:yearMonthDuration {

    (xs:yearMonthDuration('P1M') * functx:if-empty($months,0)) +
    (xs:yearMonthDuration('P1Y') * functx:if-empty($years,0))
 } ;
 declare function functx:if-empty
  ( $arg as item()? ,
    $value as item()* )  as item()* {

  if (string($arg) != '')
  then data($arg)
  else $value
 } ;
 declare function functx:add-day
 ( $date as xs:anyAtomicType? ,
  $num-of-days as xs:integer) as xs:dateTime? {
  xs:dateTime($date) + xs:dayTimeDuration(string(concat('P', $num-of-days, 'D')))
 };
 
local:funcXq_validaplazogracia($outConsultarDesembolsoById.response, $ValidarConsultarPlazoGracia.response)
