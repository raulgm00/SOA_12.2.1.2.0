xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace flex="http://org/bcie/ws/middle/FLEXCUBE.wsdl";
declare namespace typ="http://org/bcie/ws/middle/FLEXCUBE.wsdl/types/";
(:: import schema at "../../WSDL/FLEXCUBE_WSDL.wsdl" ::)
declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";


declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace xsi="http://www.w3.org/2001/XMLSchema-instance";


declare variable $CrearDesembolsoLoanFLEXCUBERequest as element() (:: schema-element(ns1:CrearDesembolsoLoanFLEXCUBERequest) ::) external;

declare function local:func($CrearDesembolsoLoanFLEXCUBERequest as element() (:: schema-element(ns1:CrearDesembolsoLoanFLEXCUBERequest) ::)) as element() (:: element(flex:crearLoan) ::) {
         <flex:crearLoan>
         <codigoDesembolso>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:idDesembolso)}</codigoDesembolso>
         <nombreNegocio>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:Operacion/ope:nombre)}</nombreNegocio>
         <codigoCliente>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:Cliente/cli:idFacturador)}</codigoCliente>
        
         <sectorInstitucional>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:Cliente/cli:sector/cat:codigoExterno)}</sectorInstitucional>
         <actividadEconomica>{
         if (fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)!= '')
         then fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)
         else 'ND'}</actividadEconomica>
         <ejecutivo>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:usuario)}</ejecutivo>
         <pais>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:Cliente/cli:pais/cat:codigoExterno)}</pais>
         <ejeEstrategico>{
         if (fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)!= '')
         then fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)
         else 'ND'}</ejeEstrategico>
         <areaFocalizacion>{
         if (fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)!= '')
         then fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)
         else 'ND'}</areaFocalizacion>
         <objetivoEstrategico></objetivoEstrategico>
         <tipoRecurso>ND</tipoRecurso>
         {
         if (fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:TipoOperacion)=2)
         then <tipoFinanciamiento>I</tipoFinanciamiento>
         else 
            <tipoFinanciamiento>D</tipoFinanciamiento>
         } 
         <numeroTesoreria>{fn:concat($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:idDesembolso,"/",fn:year-from-date(fn:current-date()))}</numeroTesoreria>
         <lineaFinanciera>{
         if(fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno) != '')
            then fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)
            else 'ND'}</lineaFinanciera>
         <fondo>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:Id)}</fondo>
         <moneda>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:monto[com:tipo/cat:DescripcionCorta='DESEMBOLSO']/com:moneda/cat:codigoExterno)}</moneda>
         <monto>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:monto[com:tipo/cat:DescripcionCorta='DESEMBOLSO']/com:importe)}</monto>
         <fechaValor>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:fechaDisponibilidadFondos)}</fechaValor>
         <fechaVencimiento>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:fechaVencimiento)}</fechaVencimiento>
         <fechaInicioCapital>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:FechaInicio)}</fechaInicioCapital>
         <frecuenciaCapital>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:Frecuencia/com:Valor)}</frecuenciaCapital>
         <tipoFrecuenciaCapital>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:Frecuencia/com:Tipo/cat:codigoExterno)}</tipoFrecuenciaCapital>
         <destino>{
         if (fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:codigoExterno)!= '')
         then fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:codigoExterno)
         else 'ND'}</destino>
         <numeroCuentaCliente>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:cuentaCliente)}</numeroCuentaCliente>
         <productoFinanciero>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:producto/pro:codExterno)}</productoFinanciero>
         <revolvencia>{
         if (fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:EsRevolvente)= true())
         then 'Y'
         else 'N'}</revolvencia>
         <considerarFeridados>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tratamientoDiasFeriados)}</considerarFeridados>
         <numeroContratoLinea>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:NumeroLineaCredito)}</numeroContratoLinea>
         <plantillaLimite>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:CodigoPantallaLimite)}</plantillaLimite>
         <serialLimite>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:CodigoSerialLimite)}</serialLimite>
         <listacomisiones>
            {
            if($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:producto/des:Componente[des:TipoTasa/cat:codigoExterno = $CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno/text() and fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:producto/des:Componente/des:esPrincipal = true()) ]/des:TipoComponente/cat:codigoExterno = 'INTERES')
            then
            <typ:ComisionestypeUser>
               <typ:baseCalculo>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:baseCalculo/cat:codigoExterno)}</typ:baseCalculo>
               {
               if (fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)='F')then
               <typ:fechaInicioRevision>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:revision/des:FechaInicio)}</typ:fechaInicioRevision>
               else(<typ:fechaInicioRevision xsi:nil="true"/>)}
               <typ:tipoComision>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:producto/des:Componente[des:TipoComponente/cat:codigoExterno = 'INTERES' and des:TipoTasa/cat:codigoExterno = $CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno/text()]/cat:codigoExterno)}</typ:tipoComision>
               {
               if (fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)='F')then
               <typ:codigoTasa>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:tasaReferencia/cat:Id)}</typ:codigoTasa>
               else(<typ:codigoTasa xsi:nil="true"/>)}
               {
               if (fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)='X')then
               <typ:valorComision>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:fija/des:valor)}</typ:valorComision>
               else(<typ:valorComision xsi:nil="true"/>)
               }
               <typ:frecuencia>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Valor)}</typ:frecuencia>
               {
               if (fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)='F')then
               <typ:tasaMaxima>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:limite/com:maximo)}</typ:tasaMaxima>
               else(<typ:tasaMaxima xsi:nil="true"/>)}
               {
               if (fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)='F')then
               <typ:tasaMinima>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:limite/com:minimo)}</typ:tasaMinima>
               else(<typ:tasaMinima xsi:nil="true"/>)}
               {
               if (fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)='F')then
               <typ:tipoFrecuenciaRevision>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:revision/des:Frecuencia/com:Tipo/cat:codigoExterno)}</typ:tipoFrecuenciaRevision>
               else(<typ:tipoFrecuenciaRevision xsi:nil="true"/>)}
               <typ:tipoTasa>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)}</typ:tipoTasa>
               <typ:tipoFrecuencia>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Tipo/cat:codigoExterno)}</typ:tipoFrecuencia>
               {
               if (fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)='F')then
               <typ:spread>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:spread/des:valor)}</typ:spread>
               else(<typ:spread xsi:nil="true"/>)}
               <typ:spreadMora>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:spreadMora/des:valor)}</typ:spreadMora>
               {
               if (fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)='F')then
               <typ:frecuenciaRevision>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:revision/des:Frecuencia/com:Valor)}</typ:frecuenciaRevision>
               else(<typ:frecuenciaRevision xsi:nil="true"/>)}
               <typ:fechaInicio>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:FechaInicio)}</typ:fechaInicio>
               {
               if (fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)='S')then
               <typ:monto>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:especial/des:valor)}</typ:monto>
               else(<typ:monto xsi:nil="true"/>)}
            </typ:ComisionestypeUser>
            else()
          }
          {
          if($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:producto/des:Componente[des:TipoTasa/cat:codigoExterno = $CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno/text() 
          and fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:producto/des:Componente/des:esPrincipal = true()) ]/des:TipoComponente/cat:codigoExterno = 'INTERES_MORA')
          then
            <typ:ComisionestypeUser>
               <typ:baseCalculo>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:baseCalculo/cat:codigoExterno)}</typ:baseCalculo>
               <typ:fechaInicioRevision xsi:nil="true"/>
               <typ:tipoComision>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:producto/des:Componente[des:TipoComponente/cat:codigoExterno = 'INTERES_MORA' and des:TipoTasa/cat:codigoExterno = $CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno/text()]/cat:codigoExterno)}</typ:tipoComision>
               <typ:codigoTasa>9999</typ:codigoTasa>
               {
               if (fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)='X')then
               <typ:valorComision>{$CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:fija/des:valor+$CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:spreadMora/des:valor}</typ:valorComision>
               else if(fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)='S')then
               <typ:valorComision>3</typ:valorComision>
               else(<typ:valorComision xsi:nil="true"/>)}
               <typ:frecuencia xsi:nil="true"/>
               <typ:tasaMaxima xsi:nil="true"/>
               <typ:tasaMinima xsi:nil="true"/>
               <typ:tipoFrecuenciaRevision xsi:nil="true"/>
               <typ:tipoTasa>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)}</typ:tipoTasa>
               <typ:tipoFrecuencia xsi:nil="true"/>
               {
               if (fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)='F')then
               <typ:spread>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:tasaReferencia/des:valor)+fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:spread/des:valor)+fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:spreadMora/des:valor)}</typ:spread>
               else(<typ:spread xsi:nil="true"/>)}
               <typ:spreadMora xsi:nil="true"/>
               <typ:frecuenciaRevision xsi:nil="true"/>
               <typ:fechaInicio xsi:nil="true"/>
               <typ:monto xsi:nil="true"/>
            </typ:ComisionestypeUser>
            else()}
            
                 {
          if($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:producto/des:Componente[des:TipoTasa/cat:codigoExterno =
          $CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno/text() 
          and fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:producto/des:Componente/des:esPrincipal = true()) ]/des:TipoComponente/cat:codigoExterno = 'SPREAD_VARIABLE')
          then
            <typ:ComisionestypeUser>
               <typ:baseCalculo>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:baseCalculo/cat:codigoExterno)}</typ:baseCalculo>
               <typ:fechaInicioRevision>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:spread/des:revision/des:FechaInicio)}</typ:fechaInicioRevision> 
               <typ:tipoComision>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:producto/des:Componente[des:TipoComponente/cat:codigoExterno = 'SPREAD_VARIABLE'
               and des:TipoTasa/cat:codigoExterno = $CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno/text()]/cat:codigoExterno)}</typ:tipoComision>
               <typ:codigoTasa>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:spread/des:codigo)}</typ:codigoTasa>
              <typ:valorComision xsi:nil="true"/>
               <typ:frecuencia>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Valor)}</typ:frecuencia> 
               <typ:tasaMaxima xsi:nil="true"/>
               <typ:tasaMinima xsi:nil="true"/>
               <typ:tipoFrecuenciaRevision>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:spread/des:revision/des:Frecuencia/com:Tipo/cat:codigoExterno)}</typ:tipoFrecuenciaRevision>
               <typ:tipoTasa>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)}</typ:tipoTasa>
               <typ:tipoFrecuencia>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Tipo/cat:codigoExterno)}</typ:tipoFrecuencia> 
                  <typ:spread>0</typ:spread>
               <typ:spreadMora xsi:nil="true"/>
               <typ:frecuenciaRevision>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:spread/des:revision/des:Frecuencia/com:Valor)}</typ:frecuenciaRevision> 
               <typ:fechaInicio>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:FechaInicio)}</typ:fechaInicio> 
               <typ:monto xsi:nil="true"/>
            </typ:ComisionestypeUser>
            else()}
            
         </listacomisiones>
         <listadocargos>
            {
            for $cargo in $CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:Cargo
            return
            <typ:CargostypeUser>
               <typ:tipoCargo>{fn:data($cargo/cat:codigoExterno)}</typ:tipoCargo>
               <typ:montocargo>{fn:data($cargo/des:Monto/com:importe)}</typ:montocargo>
            </typ:CargostypeUser>
            }
         </listadocargos>
          <moverEntreMeses>{
         if(fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:moverEntreMeses) = true())
          then 'Y'
          else 'N'
         }</moverEntreMeses>
      </flex:crearLoan>


};

local:func($CrearDesembolsoLoanFLEXCUBERequest)
