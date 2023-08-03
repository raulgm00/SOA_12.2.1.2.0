xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace flex="http://org/bcie/ws/middle/FLEXCUBE.wsdl";
declare namespace typ="http://org/bcie/ws/middle/FLEXCUBE.wsdl/types/";
(:: import schema at "../../WSDL/FLEXCUBE_WSDL.wsdl" ::)
declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V2/Schema/DesembolsoMOV2.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBOV2";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace xsi="http://www.w3.org/2001/XMLSchema-instance";

declare variable $CrearDesembolsoComplejoV2FLEXCUBERequest as element() (:: schema-element(ns1:CrearDesembolsoComplejoV2FLEXCUBERequest) ::) external;

declare function local:func($CrearDesembolsoComplejoV2FLEXCUBERequest as element() (:: schema-element(ns1:CrearDesembolsoComplejoV2FLEXCUBERequest) ::))  as element() (:: element(flex:crearLoanComplejo) ::) {
<flex:crearLoanComplejo>
         <loan>
            <typ:codigoDesembolso>{fn:data($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:ContratoDesembolso/des:idDesembolso)}</typ:codigoDesembolso>
            <typ:nombreNegocio>{fn:data($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:Operacion/ope:nombre)}</typ:nombreNegocio>
            <typ:codigoCliente>{fn:data($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:Cliente/cli:idFacturador)}</typ:codigoCliente>
            <typ:moverEntreMeses>{
            if(fn:data($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:moverEntreMeses)= true())
              then 'Y'
              else 'N'
            }</typ:moverEntreMeses>
            <typ:sectorInstitucional>{fn:data($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:Cliente/cli:sector/cat:codigoExterno)}</typ:sectorInstitucional>
            <typ:actividadEconomica>{
            if($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:ContratoDesembolso/des:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)
            then fn:data($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:ContratoDesembolso/des:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)
            else('ND')}</typ:actividadEconomica>
            <typ:ejecutivo>{fn:data($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:ContratoDesembolso/des:usuario)}</typ:ejecutivo>
            <typ:pais>{fn:data($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:Cliente/cli:pais/cat:codigoExterno)}</typ:pais>
            <typ:ejeEstrategico>{
            if($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:ContratoDesembolso/des:HerramientaCE/her:EjeEstrategico/cat:codigoExterno != '')
            then fn:data($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:ContratoDesembolso/des:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)
            else 'ND'}</typ:ejeEstrategico>
            <typ:areaFocalizacion>{
            if ($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:ContratoDesembolso/des:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno != '')
            then fn:data($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:ContratoDesembolso/des:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)
            else 'ND'}</typ:areaFocalizacion>
            <typ:objetivoEstrategico></typ:objetivoEstrategico>
            <typ:tipoRecurso>ND</typ:tipoRecurso>
             {
             if (fn:data($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:TipoOperacion)=2)
             then <typ:tipoFinanciamiento>I</typ:tipoFinanciamiento>
             else 
                <typ:tipoFinanciamiento>D</typ:tipoFinanciamiento>
             } 
            <typ:numeroTesoreria>{fn:concat(fn:data($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:ContratoDesembolso/des:idDesembolso),"/",fn:year-from-date(fn:current-date()))}</typ:numeroTesoreria>
            <typ:lineaFinanciera>{
            if (fn:data($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)!= '')
            then fn:data($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)
            else ('ND')}</typ:lineaFinanciera>
            <typ:fondo>{fn:data($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:Id)}</typ:fondo>
            <typ:moneda>{fn:data($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:ContratoDesembolso/des:monto[com:tipo/cat:DescripcionCorta='DESEMBOLSO']/com:moneda/cat:codigoExterno)}</typ:moneda>
            <typ:monto>{fn:data($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:ContratoDesembolso/des:monto[com:tipo/cat:DescripcionCorta='DESEMBOLSO']/com:importe)}</typ:monto>
            <typ:fechaValor>{$CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:ContratoDesembolso/des:fechaDisponibilidadFondos/text()}</typ:fechaValor>
            <typ:fechaVencimiento>{$CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:ContratoDesembolso/des:fechaVencimiento/text()}</typ:fechaVencimiento>
            <typ:destino>{
            if(fn:data($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:codigoExterno)!= '')
            then fn:data($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:codigoExterno)
            else('ND')}</typ:destino>
            <typ:numeroCuentaCliente>{fn:data($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:ContratoDesembolso/des:cuentaCliente)}</typ:numeroCuentaCliente>
            <typ:productoFinanciero>{fn:data($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:ContratoDesembolso/des:producto/pro:codExterno)}</typ:productoFinanciero>
            <typ:revolvencia>{
            if(fn:data($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:LineaCredito/lin:EsRevolvente)= true())
            then 'Y' else 'N'}</typ:revolvencia>
            <typ:considerarFeridados>{fn:data($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tratamientoDiasFeriados)}</typ:considerarFeridados>
            <typ:numeroContratoLinea>{fn:data($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:LineaCredito/lin:NumeroLineaCredito)}</typ:numeroContratoLinea>
            <typ:plantillaLimite>{fn:data($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:LineaCredito/lin:CodigoPantallaLimite)}</typ:plantillaLimite>
            <typ:serialLimite>{fn:data($CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:LineaCredito/lin:CodigoSerialLimite)}</typ:serialLimite>
            <typ:listadoCargos>
            {
            for $cargo in  $CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:ContratoDesembolso/des:Cargo
            return
               <typ:array>
                  <typ:tipoCargo>{fn:data($cargo/cat:codigoExterno)}</typ:tipoCargo>
                  <typ:montocargo>{fn:data($cargo/des:Monto/com:importe)}</typ:montocargo>
               </typ:array>
            }
            </typ:listadoCargos>
            <typ:listaCalendario>
            {
            for $calendarioCapital at $position in $CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario
            return
               <typ:array>
                  <typ:tipoPlan>{fn:data($calendarioCapital/des:TipoPlan/cat:codigoExterno)}</typ:tipoPlan>
                  <typ:tipoFrecuenciaCapital>{fn:data($calendarioCapital/des:Frecuencia/des:Frecuencia/com:Tipo/cat:codigoExterno)}</typ:tipoFrecuenciaCapital>
                  <typ:frecuenciaCapital>{fn:data($calendarioCapital/des:Frecuencia/des:Frecuencia/com:Valor)}</typ:frecuenciaCapital>
                  <typ:monto>{fn:data($calendarioCapital/des:Monto/com:importe)}</typ:monto>
                  <typ:fechaInicioCapital>{$calendarioCapital/des:Frecuencia/des:FechaInicio/text()}</typ:fechaInicioCapital>
                  <typ:numeroCuotas>{fn:data($calendarioCapital/des:NumeroCuotas)}</typ:numeroCuotas>
               </typ:array>
            }
            </typ:listaCalendario>
            <typ:listaComponentes>
            {
            for $componente in $CrearDesembolsoComplejoV2FLEXCUBERequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:Componente
            return
               <typ:array>
                  <typ:tipoTasa>{fn:data($componente/des:TipoTasa/cat:codigoExterno)}</typ:tipoTasa> 
                  {
                  if (fn:data($componente/des:TipoTasa/cat:codigoExterno)='F')then
                  <typ:codigoTasa>{fn:data($componente/des:CodigoTasaReferencia)}</typ:codigoTasa>
                  else(<typ:codigoTasa xsi:nil="true"/>)
                  }
                  <typ:tipoComision>{fn:data($componente/des:TipoComponente/cat:codigoExterno)}</typ:tipoComision>
                  <typ:baseCalculo>{fn:data($componente/des:BaseCalculo/cat:codigoExterno)}</typ:baseCalculo>
                  {
                   if (fn:data($componente/des:TipoTasa/cat:codigoExterno)='F')then
                   <typ:spread>{fn:data($componente/des:SpreadTasa)}</typ:spread>
                   else(<typ:spread xsi:nil="true"/>)
                   
                   }
                   {
                    if (fn:data($componente/des:TipoComponente/cat:codigoExterno)='INTERES')then
                      <typ:spreadMora>{fn:data($componente[des:TipoComponente/cat:codigoExterno = 'INTERES_MORA']/des:SpreadTasa)}</typ:spreadMora>
                      else(<typ:spreadMora xsi:nil="true"/>)
                   }
                  {
                   if (fn:data($componente/des:TipoTasa/cat:codigoExterno)='X')then
                   <typ:valorComision>{fn:data($componente/des:Tasa)}</typ:valorComision>
                   else(<typ:valorComision xsi:nil="true"/>)
                   }
                   {
                   if (fn:data($componente/des:TipoTasa/cat:codigoExterno)='F')then
                   <typ:tasaMaxima>{fn:data($componente/des:LimiteTasaMaxima)}</typ:tasaMaxima>
                   else(<typ:tasaMaxima xsi:nil="true"/>)}
                   {
                   if (fn:data($componente/des:TipoTasa/cat:codigoExterno)='F')then
                   <typ:tasaMinima>{fn:data($componente/des:LimiteTasaMinima)}</typ:tasaMinima>
                   else(<typ:tasaMinima xsi:nil="true"/>)}
                  {
                   if (fn:data($componente/des:TipoTasa/cat:codigoExterno)='S')then
                   <typ:monto>{fn:data($componente/des:MontoDescuento)}</typ:monto>
                   else(<typ:monto xsi:nil="true"/>)}
                  <typ:listaCalendario>
                  {
                  for $calendarioInteres at $position in $componente/des:calendario
                  return
                     <typ:array>
                        <typ:tipoPlan>{fn:data($calendarioInteres/des:TipoPlan/cat:codigoExterno)}</typ:tipoPlan>
                        <typ:tipoFrecuenciaCapital>{fn:data($calendarioInteres/des:Frecuencia/des:Frecuencia/com:Tipo/cat:codigoExterno)}</typ:tipoFrecuenciaCapital>
                        <typ:frecuenciaCapital>{fn:data($calendarioInteres/des:Frecuencia/des:Frecuencia/com:Valor)}</typ:frecuenciaCapital>
                        <typ:monto>{fn:data($calendarioInteres/des:Monto/com:importe)}</typ:monto>
                        <typ:fechaInicioCapital>{$calendarioInteres/des:Frecuencia/des:FechaInicio/text()}</typ:fechaInicioCapital>
                        <typ:numeroCuotas>{fn:data($calendarioInteres/des:NumeroCuotas)}</typ:numeroCuotas>
                     </typ:array>
                  }
                  </typ:listaCalendario>
               </typ:array>
               }
            </typ:listaComponentes>
         </loan>
      </flex:crearLoanComplejo>
};

local:func($CrearDesembolsoComplejoV2FLEXCUBERequest)
