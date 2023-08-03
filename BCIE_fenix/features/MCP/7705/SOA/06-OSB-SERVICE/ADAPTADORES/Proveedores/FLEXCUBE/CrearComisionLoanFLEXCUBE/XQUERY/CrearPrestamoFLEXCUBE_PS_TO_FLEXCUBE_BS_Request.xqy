xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace flex="http://org/bcie/ws/middle/FLEXCUBE.wsdl";

declare namespace typ="http://org/bcie/ws/middle/FLEXCUBE.wsdl/types/";
(:: import schema at "../../WSDL/FLEXCUBE_WSDL.wsdl" ::)
declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace functx = "http://www.functx.com";

declare variable $CrearPrestamoFLEXCUBERequest as element() (:: schema-element(ns1:CrearPrestamoFLEXCUBERequest) ::) external;

declare function local:func($CrearPrestamoFLEXCUBERequest as element() (:: schema-element(ns1:CrearPrestamoFLEXCUBERequest) ::)) as element() (:: element(flex:crearLoan) ::) {
      <flex:crearLoan>
         <codigoDesembolso>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:idComision)}</codigoDesembolso>
         <nombreNegocio>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Operacion/ope:nombre)}</nombreNegocio>
         <codigoCliente>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Operacion/ope:cliente/cli:idFacturador)}</codigoCliente>
         <sectorInstitucional>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Operacion/ope:cliente/cli:sector/cat:Id)}</sectorInstitucional>
         <actividadEconomica>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Operacion/ope:actividadEconomica/cat:codigoExterno)}</actividadEconomica>
         <ejecutivo>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Operacion/ope:cliente/cli:ejecutivo)}</ejecutivo>
         <pais>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Operacion/ope:cliente/cli:pais/cat:DescripcionCorta)}</pais>
         <ejeEstrategico>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Operacion/ope:ejeEstrategico/cat:codigoExterno)}</ejeEstrategico>
         <areaFocalizacion>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Operacion/ope:areaFocalizacion/cat:codigoExterno)}</areaFocalizacion>
         <objetivoEstrategico>ND</objetivoEstrategico>
         <tipoRecurso>ND</tipoRecurso>
         <tipoFinanciamiento>C</tipoFinanciamiento>
         <numeroTesoreria>{fn:concat($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:idComision,'.',$CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:idOperacion,'.', fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:tipoComision/com:idTipoComision/cat:Id))}</numeroTesoreria>
         <lineaFinanciera>ND</lineaFinanciera>
         <fondo>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fondo)}</fondo>
         <moneda>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:moneda/cat:DescripcionCorta)}</moneda>
         <monto>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:montoComision)}</monto>
         <fechaValor>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fechaValor)}</fechaValor>
         <fechaVencimiento>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fechaVencimiento)}</fechaVencimiento>
         <fechaInicioCapital>{
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
          else fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fechaInicioCapital)}</fechaInicioCapital>
         <frecuenciaCapital>{
         if($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:tipoFrecuencia/cat:codigoExterno/text() = 'B')
         then 1
         else fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:frecuenciaPago)}</frecuenciaCapital>
         <tipoFrecuenciaCapital>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:tipoFrecuencia/cat:codigoExterno)}</tipoFrecuenciaCapital>
         <destino>ND</destino>
         <numeroCuentaCliente>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:numeroCuentaCliente)}</numeroCuentaCliente>
         <productoFinanciero>{fn:substring-before($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:tipoComision/com:idTipoComision/cat:codigoExterno,',')}</productoFinanciero>
         <revolvencia>N</revolvencia>
         <considerarFeridados>IGN</considerarFeridados>
         <numeroContratoLinea/>
         <plantillaLimite/>
         <serialLimite/>
         <listacomisiones>
            <typ:ComisionestypeUser>
               <typ:baseCalculo>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:baseCalculo/cat:codigoExterno)}</typ:baseCalculo> 
               <typ:fechaInicioRevision></typ:fechaInicioRevision>
               <typ:tipoComision>{fn:substring-after($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:tipoComision/com:idTipoComision/cat:codigoExterno,',')}</typ:tipoComision>
               <typ:codigoTasa/>
               <typ:valorComision></typ:valorComision>
               <typ:frecuencia>1</typ:frecuencia>
               <typ:tasaMaxima></typ:tasaMaxima>
               <typ:tasaMinima></typ:tasaMinima>
               <typ:tipoFrecuenciaRevision></typ:tipoFrecuenciaRevision>
               <typ:tipoTasa>S</typ:tipoTasa>
               <typ:tipoFrecuencia>B</typ:tipoFrecuencia>
               <typ:spread></typ:spread>
               <typ:spreadMora></typ:spreadMora>
               <typ:frecuenciaRevision></typ:frecuenciaRevision>
               <typ:fechaInicio>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:fechaInicioComision)}</typ:fechaInicio>
               <typ:monto>{fn:data($CrearPrestamoFLEXCUBERequest/ns1:Prestamo/lin:Comision/com:montoComision)}</typ:monto>
            </typ:ComisionestypeUser>
         </listacomisiones>
         <listadocargos/>
         <moverEntreMeses>N</moverEntreMeses>
      </flex:crearLoan>
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