xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace functx = "http://www.functx.com";

declare variable $outConsultarDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::) external;

declare function local:funcXq_validarfechasplazo($outConsultarDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::)) as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::) {
    <des:ConsultarDesembolsosByIdResponse>
    {for $desembolso in $outConsultarDesembolsoById.response/des:ContratoDesembolso
        return
        <des:ContratoDesembolso>
            <des1:idDesembolso>{fn:data($desembolso/des1:idDesembolso)}</des1:idDesembolso>
            {
                if ($desembolso/des1:idFacturador)
                then <des1:idFacturador>{fn:data($desembolso/des1:idFacturador)}</des1:idFacturador>
                else ()
            }
            {
                if ($desembolso/des1:producto)
                then 
                    <des1:producto>
                        {
                        $desembolso/des1:producto/*
                        }
                    </des1:producto>
                else ()
            }
            {
                if ($desembolso/des1:referencia)
                then <des1:referencia>{fn:data($desembolso/des1:referencia)}</des1:referencia>
                else ()
            }
            {
                for $monto in $desembolso/des1:monto
                return 
                <des1:monto>
                {
                $monto/*
                }
                </des1:monto>
            }
            <des1:estado>
                {
                $desembolso/des1:estado/*
                }
            </des1:estado>
            {
                if ($desembolso/des1:programado)
                then <des1:programado>{fn:data($desembolso/des1:programado)}</des1:programado>
                else ()
            }
            {
                if ($desembolso/des1:fechaEstimadaDesembolsar)
                then <des1:fechaEstimadaDesembolsar>{fn:data($desembolso/des1:fechaEstimadaDesembolsar)}</des1:fechaEstimadaDesembolsar>
                else ()
            }
            {
                if ($desembolso/des1:fechaEfectiva)
                then <des1:fechaEfectiva>{fn:data($desembolso/des1:fechaEfectiva)}</des1:fechaEfectiva>
                else ()
            }
            {
                if ($desembolso/des1:fechaAsignacion)
                then <des1:fechaAsignacion>{fn:data($desembolso/des1:fechaAsignacion)}</des1:fechaAsignacion>
                else ()
            }
            {
                if ($desembolso/des1:fechaRegistro)
                then <des1:fechaRegistro>{fn:data($desembolso/des1:fechaRegistro)}</des1:fechaRegistro>
                else ()
            }
            {
                <des1:fechaVencimiento>
                { 
                    if (string-length($desembolso/des1:fechaDisponibilidadFondos/text()) = 0 )
                    then 
                    functx:add-period
                    (
                        fn:current-dateTime(),
                        xs:integer(fn:data($desembolso/des1:EstimadoDesembolso/des1:Plazo)),
                        fn:data($desembolso/des1:EstimadoDesembolso/des1:Frecuencia/cat:codigoExterno)
                    )
                    else if (string-length($desembolso/des1:fechaDisponibilidadFondos/text()) != 0 )
                    then
                    functx:add-period
                    (
                        fn:data($desembolso/des1:fechaDisponibilidadFondos),
                        xs:integer(fn:data($desembolso/des1:EstimadoDesembolso/des1:Plazo)),
                        fn:data($desembolso/des1:EstimadoDesembolso/des1:Frecuencia/cat:codigoExterno))
                    else fn:data($desembolso/des1:fechaVencimiento)
                }
                </des1:fechaVencimiento>
            }
            {
                if ($desembolso/des1:estatus)
                then <des1:estatus>{fn:data($desembolso/des1:estatus)}</des1:estatus>
                else ()
            }
            <des1:condicionesFinancieras>
            {
            $desembolso/des1:condicionesFinancieras/*
            }
            </des1:condicionesFinancieras>
            {
                if ($desembolso/des1:idLinea)
                then <des1:idLinea>{fn:data($desembolso/des1:idLinea)}</des1:idLinea>
                else ()
            }
            {
                if ($desembolso/des1:recursosExternos)
                then <des1:recursosExternos>{fn:data($desembolso/des1:recursosExternos)}</des1:recursosExternos>
                else ()
            }
            {
                if ($desembolso/des1:cuentaCliente)
                then <des1:cuentaCliente>{fn:data($desembolso/des1:cuentaCliente)}</des1:cuentaCliente>
                else ()
            }
            {
                if ($desembolso/des1:usuario)
                then <des1:usuario>{fn:data($desembolso/des1:usuario)}</des1:usuario>
                else ()
            }
                <des1:fechaDisponibilidadFondos>
                  { 
                    if (string-length($desembolso/des1:fechaDisponibilidadFondos/text()) = 0 )
                    then 
                    fn:current-dateTime()
                    else  
                    $desembolso/des1:fechaDisponibilidadFondos
                    
                  }
                </des1:fechaDisponibilidadFondos>
            {
                if ($desembolso/des1:origenTransferenciaCte)
                then <des1:origenTransferenciaCte>{fn:data($desembolso/des1:origenTransferenciaCte)}</des1:origenTransferenciaCte>
                else ()
            }
            {
                if ($desembolso/des1:Programa)
                then 
                    <des1:Programa>
                    {
                    $desembolso/des1:Programa/*
                    }
                    </des1:Programa>
                else ()
            }
            {
                if ($desembolso/des1:EstimadoDesembolso)
                then 
                    <des1:EstimadoDesembolso>
                    {
                    $desembolso/des1:EstimadoDesembolso/*
                    }
                    </des1:EstimadoDesembolso>
                else ()
            }
            {
                for $utilizacion in $desembolso/des1:Utilizacion
                return 
                    <des1:Utilizacion>
                    {$utilizacion/*}
                    </des1:Utilizacion>
            }
            {
                for $cargo in $desembolso/des1:Cargo
                return 
                  <des1:Cargo>
                  {$cargo/*}
                  </des1:Cargo>
            }
            {
                for $comision in $desembolso/des1:Comision
                return 
                  <des1:Comision>
                  {$comision/*}
                  </des1:Comision>
            }
            {
                for $transferencia in $desembolso/des1:Transferencia
                return 
                  <des1:Transferencia>
                  {$transferencia/*}
                  </des1:Transferencia>
            }
            <des1:HerramientaCE>
            {$desembolso/des1:HerramientaCE/*}
            </des1:HerramientaCE>
        </des:ContratoDesembolso>
        }
    </des:ConsultarDesembolsosByIdResponse>
};

declare function functx:add-period
  ( $date as xs:anyAtomicType? ,
    $value as xs:integer? ,
    $value-tipe as xs:string? )  as xs:dateTime? {
    if ($value-tipe = 'Y' or $value-tipe = 'A')
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

local:funcXq_validarfechasplazo($outConsultarDesembolsoById.response)
