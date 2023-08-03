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

declare variable $CrearDesembolsoComplejoFLEXCUBERequest as element() (:: schema-element(ns1:CrearDesembolsoComplejoFLEXCUBERequest) ::) external;

declare function local:func($CrearDesembolsoComplejoFLEXCUBERequest as element() (:: schema-element(ns1:CrearDesembolsoComplejoFLEXCUBERequest) ::)) as element() (:: element(flex:crearLoanComplejo) ::) {
    <flex:crearLoanComplejo>
         <loan>
            <typ:codigoDesembolso>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:idDesembolso)}</typ:codigoDesembolso>
            <typ:nombreNegocio>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:Operacion/ope:nombre)}</typ:nombreNegocio>
            <typ:codigoCliente>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:Cliente/cli:idFacturador)}</typ:codigoCliente>
            <typ:moverEntreMeses>{
            if(fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:moverEntreMeses)= true())
              then 'Y'
              else 'N'
            }</typ:moverEntreMeses>
            <typ:sectorInstitucional>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:Cliente/cli:sector/cat:codigoExterno)}</typ:sectorInstitucional>
            <typ:actividadEconomica>{
            if($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)
            then fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)
            else('ND')}</typ:actividadEconomica>
            <typ:ejecutivo>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:usuario)}</typ:ejecutivo>
            <typ:pais>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:Cliente/cli:pais/cat:codigoExterno)}</typ:pais>
            <typ:ejeEstrategico>{
            if($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:EjeEstrategico/cat:codigoExterno != '')
            then fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)
            else 'ND'}</typ:ejeEstrategico>
            <typ:areaFocalizacion>{
            if ($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno != '')
            then fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)
            else 'ND'}</typ:areaFocalizacion>
            <typ:objetivoEstrategico></typ:objetivoEstrategico>
            <typ:tipoRecurso>ND</typ:tipoRecurso>
             {
             if (fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:TipoOperacion)=2)
             then <typ:tipoFinanciamiento>I</typ:tipoFinanciamiento>
             else 
                <typ:tipoFinanciamiento>D</typ:tipoFinanciamiento>
             } 
            <typ:numeroTesoreria>{fn:concat(fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:idDesembolso),"/",fn:year-from-date(fn:current-date()))}</typ:numeroTesoreria>
            <typ:lineaFinanciera>{
            if (fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)!= '')
            then fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)
            else ('ND')}</typ:lineaFinanciera>
            <typ:fondo>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:Id)}</typ:fondo>
            <typ:moneda>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:monto[com:tipo/cat:DescripcionCorta='DESEMBOLSO']/com:moneda/cat:codigoExterno)}</typ:moneda>
            <typ:monto>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:monto[com:tipo/cat:DescripcionCorta='DESEMBOLSO']/com:importe)}</typ:monto>
            <typ:fechaValor>{fn:substring-before($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:fechaDisponibilidadFondos/text(),'T')}</typ:fechaValor>
            <typ:fechaVencimiento>{fn:substring-before($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:fechaVencimiento/text(),'T')}</typ:fechaVencimiento>
            <typ:destino>{
            if(fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:codigoExterno)!= '')
            then fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:codigoExterno)
            else('ND')}</typ:destino>
            <typ:numeroCuentaCliente>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:cuentaCliente)}</typ:numeroCuentaCliente>
            <typ:productoFinanciero>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:producto/pro:codExterno)}</typ:productoFinanciero>
            <typ:revolvencia>{
            if(fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:EsRevolvente)= true())
            then 'Y' else 'N'}</typ:revolvencia>
            <typ:considerarFeridados>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tratamientoDiasFeriados)}</typ:considerarFeridados>
            <typ:numeroContratoLinea>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:NumeroLineaCredito)}</typ:numeroContratoLinea>
            <typ:plantillaLimite>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:CodigoPantallaLimite)}</typ:plantillaLimite>
            <typ:serialLimite>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:CodigoSerialLimite)}</typ:serialLimite>
            <typ:moverEntreMeses></typ:moverEntreMeses>
            <typ:listadoCargos>
            {
            for $cargo in  $CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:Cargo
            return
               <typ:array>
                  <typ:tipoCargo>{fn:data($cargo/cat:codigoExterno)}</typ:tipoCargo>
                  <typ:montocargo>{fn:data($cargo/des:Monto/com:importe)}</typ:montocargo>
               </typ:array>
            }
            </typ:listadoCargos>
            <typ:listaCalendario>
            {
            for $calendarioCapital at $position in $CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:calendarioComplejo[des:Monto/com:tipo/cat:DescripcionCorta = 'CAPITAL']
            let $tipoFrecuencia := if ($position = count($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:calendarioComplejo[des:Monto/com:tipo/cat:DescripcionCorta = 'CAPITAL'])) then 'B' else 'M'
            return
               <typ:array>
                  <typ:tipoPlan>P</typ:tipoPlan>
                  <typ:tipoFrecuenciaCapital>{fn:data($tipoFrecuencia)}</typ:tipoFrecuenciaCapital>
                  <typ:frecuenciaCapital>1</typ:frecuenciaCapital>
                  <typ:monto>{fn:data($calendarioCapital/des:Monto/com:importe)}</typ:monto>
                  <typ:fechaInicioCapital>{fn:substring-before($calendarioCapital/des:Frecuencia/des:FechaInicio/text(),'T')}</typ:fechaInicioCapital>
                  <typ:numeroCuotas>1</typ:numeroCuotas>
               </typ:array>
            }
            </typ:listaCalendario>
            <typ:listaComponentes>
            {
            if($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:producto/des:Componente[des:TipoTasa/cat:codigoExterno = $CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno/text() and fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:producto/des:Componente/des:esPrincipal = true()) ]/des:TipoComponente/cat:codigoExterno = 'INTERES')
            then
               <typ:array>
                  <typ:tipoTasa>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)}</typ:tipoTasa> 
                  {
                  if (fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)='F')then
                  <typ:codigoTasa>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:tasaReferencia/cat:Id)}</typ:codigoTasa>
                  else(<typ:codigoTasa xsi:nil="true"/>)}
                  <typ:tipoComision>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:producto/des:Componente[des:TipoComponente/cat:codigoExterno = 'INTERES' and des:TipoTasa/cat:codigoExterno = $CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno/text()]/cat:codigoExterno)}</typ:tipoComision>
                  <typ:baseCalculo>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:baseCalculo/cat:codigoExterno)}</typ:baseCalculo>
                  {
                   if (fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)='F')then
                   <typ:spread>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:spread/des:valor)}</typ:spread>
                   else(<typ:spread xsi:nil="true"/>)}
                  <typ:spreadMora>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:spreadMora/des:valor)}</typ:spreadMora>
                  {
                   if (fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)='X')then
                   <typ:valorComision>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:fija/des:valor)}</typ:valorComision>
                   else(<typ:valorComision xsi:nil="true"/>)
                   }
                   {
                   if (fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)='F')then
                   <typ:tasaMaxima>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:limite/com:maximo)}</typ:tasaMaxima>
                   else(<typ:tasaMaxima xsi:nil="true"/>)}
                   {
                   if (fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)='F')then
                   <typ:tasaMinima>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:limite/com:minimo)}</typ:tasaMinima>
                   else(<typ:tasaMinima xsi:nil="true"/>)}
                  {
                   if (fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)='S')then
                   <typ:monto>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:especial/des:valor)}</typ:monto>
                   else(<typ:monto xsi:nil="true"/>)}
                  <typ:listaCalendario>
                  {
                  for $calendarioInteres at $positionInteres in $CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:calendarioComplejo[des:Monto/com:tipo/cat:DescripcionCorta = 'INTERES']
                  let $tipoFrecuenciaInteres := if ($positionInteres = count($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:calendarioComplejo[des:Monto/com:tipo/cat:DescripcionCorta = 'INTERES'])) then 'B' else 'M'
                  return
                     <typ:array>
                        <typ:tipoPlan>P</typ:tipoPlan>
                        <typ:tipoFrecuenciaCapital>{fn:data($tipoFrecuenciaInteres)}</typ:tipoFrecuenciaCapital>
                        <typ:frecuenciaCapital>1</typ:frecuenciaCapital>
                        <typ:monto></typ:monto>
                        <typ:fechaInicioCapital>{fn:substring-before($calendarioInteres/des:Frecuencia/des:FechaInicio/text(),'T')}</typ:fechaInicioCapital>
                        <typ:numeroCuotas>1</typ:numeroCuotas>
                     </typ:array>
                  }
                  {
                  if (string($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:revision/des:Frecuencia/com:Valor) != '')
                  then
                        <typ:array>
                        <typ:tipoPlan>R</typ:tipoPlan>
                        <typ:tipoFrecuenciaCapital>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:revision/des:Frecuencia/com:Tipo/cat:codigoExterno)}</typ:tipoFrecuenciaCapital>
                        <typ:frecuenciaCapital>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:revision/des:Frecuencia/com:Valor)}</typ:frecuenciaCapital>
                        <typ:monto></typ:monto>
                        <typ:fechaInicioCapital>{fn:substring-before($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:revision/des:FechaInicio/text(),'T')}</typ:fechaInicioCapital>
                        <typ:numeroCuotas>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:calendarioComplejo[1]/des:NumeroCuotas)}</typ:numeroCuotas>
                     </typ:array>
                  else()}
                  </typ:listaCalendario>
               </typ:array>
               else()}
              {
              if($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:producto/des:Componente[des:TipoTasa/cat:codigoExterno = $CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno/text() and fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:producto/des:Componente/des:esPrincipal = true()) ]/des:TipoComponente/cat:codigoExterno = 'INTERES_MORA')
              then
               <typ:array>
                  <typ:tipoTasa>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)}</typ:tipoTasa>
                  <typ:codigoTasa>{
                  if (fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)='F')then
                  9999 else ()}</typ:codigoTasa>
                  <typ:tipoComision>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:producto/des:Componente[des:TipoComponente/cat:codigoExterno = 'INTERES_MORA' and des:TipoTasa/cat:codigoExterno = $CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno/text()]/cat:codigoExterno)}</typ:tipoComision>
                  <typ:baseCalculo>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:baseCalculo/cat:codigoExterno)}</typ:baseCalculo>
                  {
                   if (fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)='F')then
                   <typ:spread>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:tasaReferencia/des:valor)+fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:spread/des:valor)+fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:spreadMora/des:valor)}</typ:spread>
                   else(<typ:spread xsi:nil="true"/>)}
                  <typ:spreadMora xsi:nil="true"/>
                  {
                   if (fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)='X')then
                  <typ:valorComision>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:fija/des:valor) +fn:data( $CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:spreadMora/des:valor)}</typ:valorComision>
                  else (<typ:valorComision xsi:nil="true"/>)}
                  <typ:tasaMinima xsi:nil="true"/>
                  <typ:tasaMaxima xsi:nil="true"/>
                  <typ:monto xsi:nil="true"/>
                  <typ:listaCalendario/>
               </typ:array>
              else()}
            </typ:listaComponentes>
         </loan>
      </flex:crearLoanComplejo>
};

local:func($CrearDesembolsoComplejoFLEXCUBERequest)
