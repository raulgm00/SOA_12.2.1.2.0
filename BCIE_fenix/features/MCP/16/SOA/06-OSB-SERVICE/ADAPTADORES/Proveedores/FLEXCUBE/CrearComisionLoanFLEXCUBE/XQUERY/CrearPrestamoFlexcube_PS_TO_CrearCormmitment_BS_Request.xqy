xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CrearCommitment";
(:: import schema at "../XSD/CrearCommitment_sp.xsd" ::)

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace functx = "http://www.functx.com";

declare variable $CrearPrestamoFLEXCUBERequest as element() (:: schema-element(ns1:CrearPrestamoFLEXCUBERequest) ::) external;

declare function local:func($CrearPrestamoFLEXCUBERequest as element() (:: schema-element(ns1:CrearPrestamoFLEXCUBERequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:PRECCOMMITMENT>
            <ns2:CODIGO_INTERVENCION>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:idOperacion)}</ns2:CODIGO_INTERVENCION>
            <ns2:NOMBRE_NEGOCIO>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Operacion/ope:nombre)}</ns2:NOMBRE_NEGOCIO>
            <ns2:CODIGO_CLIENTE>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Operacion/ope:cliente/cli:idFacturador)}</ns2:CODIGO_CLIENTE>
            <ns2:SECTOR_INSTITUCIONAL>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Operacion/ope:cliente/cli:sector/cat:Id)}</ns2:SECTOR_INSTITUCIONAL>
            <ns2:ACTIVIDAD_ECONOMICA>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Operacion/ope:actividadEconomica/cat:codigoExterno)}</ns2:ACTIVIDAD_ECONOMICA>
            <ns2:EJECUTIVO>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Operacion/ope:cliente/cli:ejecutivo)}</ns2:EJECUTIVO>
            <ns2:PAIS>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Operacion/ope:cliente/cli:pais/cat:DescripcionCorta)}</ns2:PAIS>
            <ns2:EJE_ESTRATEGICO>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Operacion/ope:ejeEstrategico/cat:codigoExterno)}</ns2:EJE_ESTRATEGICO>
            <ns2:AREA_FOCALIZACION>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Operacion/ope:areaFocalizacion/cat:codigoExterno)}</ns2:AREA_FOCALIZACION>
            <ns2:OBJETIVO_ESTRATEGICO>ND</ns2:OBJETIVO_ESTRATEGICO>
            <ns2:TIPO_RECURSO>ND</ns2:TIPO_RECURSO>
            <ns2:TIPO_FINANCIAMIENTO>C</ns2:TIPO_FINANCIAMIENTO>
            <ns2:NUMERO_RESOLUCION>{fn:concat($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:idComision,'.',$CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:idOperacion,'.', fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:tipoComision/com:idTipoComision/cat:Id))}</ns2:NUMERO_RESOLUCION>
            <ns2:FECHA_APROBACION>{
               if($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:tipoFrecuencia/cat:codigoExterno/text() = 'B')
               then if(string($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fechaInicioCapital) != '')
                      then fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fechaInicioCapital)
                        else fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fechaVencimiento)
                else if(string($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fechaInicioCapital) = '')
                then if($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:tipoFrecuencia/cat:codigoExterno/text() = 'Y')
                      then functx:add-months(fn:substring-before(string($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fechaValor),"T"),xs:integer(12*$CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:frecuenciaPago))
                      else if($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:tipoFrecuencia/cat:codigoExterno/text() = 'M')
                            then functx:add-months(fn:substring-before(string($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fechaValor),"T"),$CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:frecuenciaPago)
                          else if($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:tipoFrecuencia/cat:codigoExterno/text() = 'D')
                                then functx:add-day (fn:substring-before(string($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fechaValor),"T"),$CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:frecuenciaPago)
                               else()
                else fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fechaInicioCapital)}
            </ns2:FECHA_APROBACION>
            <ns2:PORCENTAJE_COBERTURA>1</ns2:PORCENTAJE_COBERTURA>
            <ns2:NUMERO_LINEA_CREDITO>{fn:concat($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:idComision,'.',$CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:idOperacion,'.', fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:tipoComision/com:idTipoComision/cat:Id))}</ns2:NUMERO_LINEA_CREDITO>
            <ns2:LINEA_FINANCIERA>ND</ns2:LINEA_FINANCIERA>
            <ns2:FONDO>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fondo)}</ns2:FONDO>
            <ns2:MONEDA>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:moneda/cat:DescripcionCorta)}</ns2:MONEDA>
            <ns2:MONTO_APROBADO>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:montoComision)}</ns2:MONTO_APROBADO>
            <ns2:FECHA_VALOR>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fechaValor)}</ns2:FECHA_VALOR>
            <ns2:MONTO_ESCRITURADO>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:montoComision)}</ns2:MONTO_ESCRITURADO>
            <ns2:FECHA_ESCRITURACION>{
               if($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:tipoFrecuencia/cat:codigoExterno/text() = 'B')
               then if(string($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fechaInicioCapital) != '')
                      then fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fechaInicioCapital)
                        else fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fechaVencimiento)
                else if(string($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fechaInicioCapital) = '')
                then if($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:tipoFrecuencia/cat:codigoExterno/text() = 'Y')
                      then functx:add-months(fn:substring-before(string($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fechaValor),"T"),xs:integer(12*$CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:frecuenciaPago))
                      else if($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:tipoFrecuencia/cat:codigoExterno/text() = 'M')
                            then functx:add-months(fn:substring-before(string($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fechaValor),"T"),$CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:frecuenciaPago)
                          else if($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:tipoFrecuencia/cat:codigoExterno/text() = 'D')
                                then functx:add-day (fn:substring-before(string($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fechaValor),"T"),$CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:frecuenciaPago)
                               else()
                else fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fechaInicioCapital)}
            </ns2:FECHA_ESCRITURACION>
            <ns2:MONTO_MAX_DESEM/>
            <ns2:MONTO_MIN_DESEM/>
            <ns2:MONTO_CATALIZAR/>
            <ns2:TIPO_CATALIZAR/>
            <ns2:RECURSOS_ORDINARIOS>S</ns2:RECURSOS_ORDINARIOS>
            <ns2:CODIGO_ASIGNACION/>
            <ns2:DIA_PAGO/>
            <ns2:PLAZO_LINEA_CREDITO>{
               if($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:tipoFrecuencia/cat:codigoExterno/text() = 'B')
               then 1
               else fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:frecuenciaPago)
            }</ns2:PLAZO_LINEA_CREDITO>
            <ns2:TIPO_PLAZO_LINEA>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:tipoFrecuencia/cat:codigoExterno)}</ns2:TIPO_PLAZO_LINEA>
            <ns2:PERIODO_GRACIA/>
            <ns2:TIPO_PLAZO_GRACIA/>
            <ns2:DESTINO>ND</ns2:DESTINO>
            <ns2:NUMERO_CUENTA_CLIENTE>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:numeroCuentaCliente)}</ns2:NUMERO_CUENTA_CLIENTE>
            <ns2:PRODUCTO_FINANCIERO>{fn:substring-before($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:tipoComision/com:idTipoComision/cat:codigoExterno,',')}</ns2:PRODUCTO_FINANCIERO>
            <ns2:REVOLVENCIA>N</ns2:REVOLVENCIA>
            <ns2:FECHA_MAXIMA_ESCRITURAR>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fechaVencimiento)}</ns2:FECHA_MAXIMA_ESCRITURAR>
            <ns2:FECHA_MINIMA_INICIAR>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fechaVencimiento)}</ns2:FECHA_MINIMA_INICIAR>
            <ns2:FECHA_MAXIMA_TOTALIDAD>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fechaVencimiento)}</ns2:FECHA_MAXIMA_TOTALIDAD>
            <ns2:TIENE_COND_ESPECIALES>N</ns2:TIENE_COND_ESPECIALES>
            <ns2:DESC_COND_ESPECIALES/>
            <ns2:CONSIDERAR_FERIDADOS>IGN</ns2:CONSIDERAR_FERIDADOS>
            <ns2:MOVER_ENTRE_MESES>N</ns2:MOVER_ENTRE_MESES>
            <ns2:FECHA_VENCIMIENTO>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fechaVencimiento)}</ns2:FECHA_VENCIMIENTO>
            <ns2:LISTACOMISIONES>
                <ns2:LISTACOMISIONES_ITEM>
                    <ns2:TIPO_TASA>S</ns2:TIPO_TASA>
                    <ns2:CODIGO_TASA/>
                    <ns2:TIPO_COMISION>{fn:substring-after($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:tipoComision/com:idTipoComision/cat:codigoExterno,',')}</ns2:TIPO_COMISION>
                    <ns2:BASE_CALCULO>{
                      if($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:baseCalculo/cat:codigoExterno/text()) 
                      then fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:baseCalculo/cat:codigoExterno) 
                      else 3 (: Se usa por defecto Actual/360 si no tiene base de calculo, en fenix es id 7 y cod externo 3 :)
                    }</ns2:BASE_CALCULO>
                    <ns2:FECHA_INICIO>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fechaInicioComision)}</ns2:FECHA_INICIO>
                    <ns2:FRECUENCIA>1</ns2:FRECUENCIA>
                    <ns2:TIPO_FRECUENCIA>B</ns2:TIPO_FRECUENCIA>
                    <ns2:SPREAD></ns2:SPREAD>
                    <ns2:SPREAD_MORA></ns2:SPREAD_MORA>
                    <ns2:VALOR_COMISION>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:montoComision)}</ns2:VALOR_COMISION>
                    <ns2:MONTO>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:montoComision)}</ns2:MONTO>
                    <ns2:TASA_MINIMA></ns2:TASA_MINIMA>
                    <ns2:TASA_MAXIMA></ns2:TASA_MAXIMA>
                    <ns2:FECHA_INICIO_REVISION></ns2:FECHA_INICIO_REVISION>
                    <ns2:FRECUENCIA_REVISION></ns2:FRECUENCIA_REVISION>
                    <ns2:TIPO_FRECUENCIA_REVISION></ns2:TIPO_FRECUENCIA_REVISION>
                </ns2:LISTACOMISIONES_ITEM>
            </ns2:LISTACOMISIONES>
        </ns2:PRECCOMMITMENT>
        <ns2:PVUSUARIO>SYSTEM</ns2:PVUSUARIO>
    </ns2:InputParameters>
};

declare function functx:add-months
  ( $date as xs:anyAtomicType? ,
    $months as xs:integer )  as xs:date? {
   xs:date($date) + functx:yearMonthDuration(0,$months)
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
  $num-of-days as xs:integer) as xs:date? {
  xs:date($date) + xs:dayTimeDuration(string(concat('P', $num-of-days, 'D')))
 };

local:func($CrearPrestamoFLEXCUBERequest)