xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)
declare namespace flex="http://org/bcie/ws/middle/FLEXCUBE.wsdl";
declare namespace typ="http://org/bcie/ws/middle/FLEXCUBE.wsdl/types/";
(:: import schema at "../../WSDL/FLEXCUBE_WSDL.wsdl" ::)
declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace oper="http://www.bcie.org/OperacionBO";
declare namespace con="http://www.bcie.org/ContratoBO";
declare namespace cli="http://www.bcie.org/ClienteBO";
declare namespace cat="http://www.bcie.org/CatalogoBO";
declare namespace apr="http://www.bcie.org/AprobacionBO";
declare namespace lin1="http://www.bcie.org/LineaCreditoBO";
declare namespace ter="http://www.bcie.org/TerminoBO";
declare namespace com = "http://www.bcie.org/ComisionBO";
declare namespace functx = "http://www.functx.com";

declare variable $CrearCommitmentRequest as element() (:: schema-element(lin:CrearCommitmentFLEXCUBERequest) ::) external;
  
declare function local:func($CrearCommitmentRequest as element() (:: schema-element(lin:CrearCommitmentFLEXCUBERequest) ::)) {
     <flex:crearCommitment>
         <codigoIntervencion>{data($CrearCommitmentRequest/lin:Operacion/oper:idOperacion)}</codigoIntervencion>
         <nombreNegocio>{data($CrearCommitmentRequest/lin:Operacion/oper:nombre)}</nombreNegocio>
         <codigoCliente>{data($CrearCommitmentRequest/lin:Operacion/oper:cliente/cli:idFacturador)}</codigoCliente>
         <sectorInstitucional>{data($CrearCommitmentRequest/lin:Operacion/oper:cliente/cli:sector/cat:Id)}</sectorInstitucional>
         <actividadEconomica>{data($CrearCommitmentRequest/lin:Operacion/oper:actividadEconomicaAsociada/cat:codigoExterno)}</actividadEconomica>
         <ejecutivo>{data($CrearCommitmentRequest/lin:Operacion/oper:responsable) }</ejecutivo>
         <pais>{data($CrearCommitmentRequest/lin:Operacion/oper:cliente/cli:pais/cat:codigoExterno)}</pais>
         <ejeEstrategico>{data($CrearCommitmentRequest/lin:Operacion/oper:ejeEstrategico/cat:codigoExterno)}</ejeEstrategico>
         <areaFocalizacion>{data($CrearCommitmentRequest/lin:Operacion/oper:areaFocalizacion/cat:codigoExterno)}</areaFocalizacion>
         <objetivoEstrategico></objetivoEstrategico>
         <tipoRecurso>ND</tipoRecurso>
         <tipoFinanciamiento>{data($CrearCommitmentRequest/lin:Operacion/oper:cliente/cli:tipoPersona/cat:codigoExterno)}</tipoFinanciamiento>
         <numeroResolucion>{data($CrearCommitmentRequest/lin:Aprobacion/apr:numeroResolucion)}</numeroResolucion>
         <fechaAprobacion>{data($CrearCommitmentRequest/lin:Aprobacion/apr:fechaAprobacion)}</fechaAprobacion>
         <porcentajeCobertura>1</porcentajeCobertura>
         <numeroLineaCredito>{data($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:NumeroLineaCredito)}</numeroLineaCredito>
         <lineaFinanciera>ND</lineaFinanciera>
         <fondo>{data($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:Fondo)}</fondo>
         <moneda>{fn:data($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:Moneda/cat:codigoExterno)}</moneda>
         <montoAprobado>{data($CrearCommitmentRequest/lin:montoAprobacion)}</montoAprobado>
         <fechaValor>{data($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:FechaValor)}</fechaValor>
          {
         if( fn:string(data($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:MontoLinea))='')then
             <montoEscriturado>0</montoEscriturado>
         else
             <montoEscriturado>{data($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:MontoLinea)}</montoEscriturado>
         }
         {
         if( fn:string(data($CrearCommitmentRequest/lin:Contrato/con:fechaFirma))='')then
             <fechaEscrituracion></fechaEscrituracion>
         else
             <fechaEscrituracion>{fn:data($CrearCommitmentRequest/lin:Contrato/con:fechaFirma)}</fechaEscrituracion>
         }
         <montoMaxDesem>{data($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:Termino[1]/ter:montoMaximoDesembolso)}</montoMaxDesem>
         <montoMinDesem>{data($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:Termino[1]/ter:montoMinimoDesembolso)}</montoMinDesem>
         <montoCatalizar></montoCatalizar>
        <tipoCatalizar></tipoCatalizar>
        
         {
            if (boolean(fn:empty(data($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:Termino/ter:seAplicanRecursosExternos)) and $CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:Termino/ter:seAplicanRecursosExternos = true())) then 
              <recursosOrdinarios>N</recursosOrdinarios>
            else
              <recursosOrdinarios>S</recursosOrdinarios>
         }
         
         <codigoAsignacion>{data($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:CodigoAsignacion)}</codigoAsignacion>
         <diaPago></diaPago>
         <plazoLineaCredito>{data($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:Termino[1]/ter:plazo)}</plazoLineaCredito>
         <tipoPlazoLinea>{data($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:Termino[1]/ter:frecuenciaPlazo/cat:codigoExterno)}</tipoPlazoLinea>
         <periodoGracia>{data($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:Termino[2]/ter:plazo)}</periodoGracia>
         <tipoPlazoGracia>{data($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:Termino[2]/ter:frecuenciaPlazo/cat:codigoExterno)}</tipoPlazoGracia>
         <destino>ND</destino>   
         <numeroCuentaCliente>{data($CrearCommitmentRequest/lin:Contrato/con:cuentaCliente/con:cuentaCliente)}</numeroCuentaCliente>
         <productoFinanciero>{data($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:Flexcube/lin1:idProductoFlexcube)}</productoFinanciero>
         {
            if (fn:string-length(string($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:EsRevolvente))>0 and $CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:EsRevolvente = true()) then 
              <revolvencia>S</revolvencia>
            else
              <revolvencia>N</revolvencia>
         }
         <fechaMaximaEscriturar>{data($CrearCommitmentRequest/lin:Contrato/con:fechaVigencia)}</fechaMaximaEscriturar>
         <fechaMinimaIniciar>{data($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:FechaRegistro)}</fechaMinimaIniciar>
         <fechaMaximaTotalidad>{data($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:FechaVencimiento)}</fechaMaximaTotalidad>
         {
            if($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:CondicionEspecial = true()) then
              <tieneCondEspeciales>S</tieneCondEspeciales>
            else
              <tieneCondEspeciales>N</tieneCondEspeciales>
         }
         <descCondEspeciales>{data($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:descCondEspecial)}</descCondEspeciales>
         <considerarFeridados>{data($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:TratamientoDiasFeriados)}</considerarFeridados> 
         <moverEntreMeses>{data($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:MoverEntreMeses)}</moverEntreMeses>
         <listacomisiones>
            {
            if(count($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:Comision) > 0 ) then
              for $comision in $CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:Comision
              let $fechaInicioRevision:= ''
              let $codigoTasa:=''
              let $tasaMaxima:=data($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:Termino[1]/ter:tasaMaximaDesembolso)
              let $tasaMinima:=data($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:Termino[1]/ter:tasaMinimaDesembolso)
              let $tipoFrecuenciaRevision:=''
              let $spread:=''
              let $spreadMora:=''
              let $frecuenciaRevision:=''
              let $plazoLineaCredito:= $CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:Termino[1]/ter:plazo
              return
                <typ:ComisionestypeUser>
                    <typ:baseCalculo>{data($comision/com:baseCalculo/cat:codigoExterno)}</typ:baseCalculo>
                    <typ:fechaInicioRevision>{data($fechaInicioRevision)}</typ:fechaInicioRevision>
                    <typ:tipoComision>{data($comision/com:tipoComision/com:idTipoComision/cat:Descripcion)}</typ:tipoComision>
                    <typ:codigoTasa>{data($codigoTasa)}</typ:codigoTasa>
                    <typ:valorComision>{data($comision/com:montoBase/com:porcentajeMontoBase)}</typ:valorComision>
                    <typ:frecuencia>{data($comision/com:frecuenciaPago)}</typ:frecuencia>
                    <typ:tasaMaxima>{data($tasaMaxima)}</typ:tasaMaxima>
                    <typ:tasaMinima>{data($tasaMinima)}</typ:tasaMinima>
                    <typ:tipoFrecuenciaRevision>{data($tipoFrecuenciaRevision)}</typ:tipoFrecuenciaRevision>
                    <typ:tipoTasa>{data($comision/com:tipoTasa/cat:codigoExterno)}</typ:tipoTasa>
                    <typ:tipoFrecuencia>{data($comision/com:tipoFrecuencia/cat:codigoExterno)}</typ:tipoFrecuencia>
                    <typ:spread>{data($spread)}</typ:spread>
                    <typ:spreadMora>{data($spreadMora)}</typ:spreadMora>
                    <typ:frecuenciaRevision>{data($frecuenciaRevision)}</typ:frecuenciaRevision>
                    <typ:fechaInicio>{
                    if($comision/com:tipoFrecuencia/cat:codigoExterno/text() = 'B') then
                      if($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:Termino[1]/ter:frecuenciaPlazo/cat:codigoExterno/text() = 'Y' ) then
                        functx:add-months($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:FechaValor, xs:integer(12 * $plazoLineaCredito) )
                      else
                        if($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:Termino[1]/ter:frecuenciaPlazo/cat:codigoExterno/text() = 'M') then
                          functx:add-months($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:FechaValor, $plazoLineaCredito )
                        else
                          functx:add-day($CrearCommitmentRequest/lin:Contrato/con:LineaCredito/lin1:FechaValor,$plazoLineaCredito )
                    else
                    data($comision/com:fechaInicioCapital)
                    }</typ:fechaInicio>
                    <typ:monto>{data($comision/com:montoComision)}</typ:monto>
                </typ:ComisionestypeUser>
              else
                 <typ:ComisionestypeUser>
                    <typ:baseCalculo>1</typ:baseCalculo>
                    <typ:fechaInicioRevision></typ:fechaInicioRevision>
                    <typ:tipoComision>L30INTCOMP</typ:tipoComision>
                    <typ:codigoTasa></typ:codigoTasa>
                    <typ:valorComision>0</typ:valorComision>
                    <typ:frecuencia>1</typ:frecuencia>
                    <typ:tasaMaxima></typ:tasaMaxima>
                    <typ:tasaMinima></typ:tasaMinima>
                    <typ:tipoFrecuenciaRevision></typ:tipoFrecuenciaRevision>
                    <typ:tipoTasa>X</typ:tipoTasa>
                    <typ:tipoFrecuencia>B</typ:tipoFrecuencia>
                    <typ:spread></typ:spread>
                    <typ:spreadMora></typ:spreadMora>
                    <typ:frecuenciaRevision></typ:frecuenciaRevision>
                    <typ:fechaInicio>{fn-bea:date-to-string-with-format("yyyy-MM-dd",fn:current-date())}</typ:fechaInicio>
                    <typ:monto></typ:monto>
                </typ:ComisionestypeUser>
            }
         </listacomisiones>  
      </flex:crearCommitment>
      
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
 
local:func($CrearCommitmentRequest)
