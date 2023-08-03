xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V2/Schema/DesembolsoMOV2.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ValidarLoanComplejo";
(:: import schema at "../XSD/ValidarLoanComplejo_sp.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBOV2";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare variable $ValidarLoanComplejoRequest as element() (:: schema-element(ns1:ValidarLoanComplejoRequest) ::) external;

declare function local:func($ValidarLoanComplejoRequest as element() (:: schema-element(ns1:ValidarLoanComplejoRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:LOAN>
            <ns2:CODIGO_DESEMBOLSO>{fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:idDesembolso)}</ns2:CODIGO_DESEMBOLSO>
            <ns2:NOMBRE_NEGOCIO>{fn:data($ValidarLoanComplejoRequest/ns1:Operacion/ope:nombre)}</ns2:NOMBRE_NEGOCIO>
            <ns2:CODIGO_CLIENTE>{fn:data($ValidarLoanComplejoRequest/ns1:Cliente/cli:idFacturador)}</ns2:CODIGO_CLIENTE>
            <ns2:SECTOR_INSTITUCIONAL>{fn:data($ValidarLoanComplejoRequest/ns1:Cliente/cli:sector/cat:codigoExterno)}</ns2:SECTOR_INSTITUCIONAL>
            <ns2:ACTIVIDAD_ECONOMICA>{
            if (fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:HerramientaCE/her:ActividadEconomica/cat:codigoExterno) != '')
            then fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)
            else 'ND'
            }</ns2:ACTIVIDAD_ECONOMICA>
            <ns2:EJECUTIVO>{fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:usuario)}</ns2:EJECUTIVO>
            <ns2:PAIS>{fn:data($ValidarLoanComplejoRequest/ns1:Cliente/cli:pais/cat:codigoExterno)}</ns2:PAIS>
            <ns2:EJE_ESTRATEGICO>{
            if (fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:HerramientaCE/her:EjeEstrategico/cat:codigoExterno) != '')
            then fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)
            else 'ND'
            }</ns2:EJE_ESTRATEGICO>
            <ns2:AREA_FOCALIZACION>{
            if (fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno) != '')
            then fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)
            else 'ND'
            }</ns2:AREA_FOCALIZACION>
            <ns2:OBJETIVO_ESTRATEGICO></ns2:OBJETIVO_ESTRATEGICO>
            <ns2:TIPO_RECURSO>ND</ns2:TIPO_RECURSO>
            <ns2:TIPO_FINANCIAMIENTO>D</ns2:TIPO_FINANCIAMIENTO>
            <ns2:NUMERO_TESORERIA>{fn:concat(fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:idDesembolso),"/",fn:year-from-date(fn:current-date()))}</ns2:NUMERO_TESORERIA>
            <ns2:LINEA_FINANCIERA>{
            if (fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno) != '')
            then fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)
            else 'ND'
            }</ns2:LINEA_FINANCIERA>
            <ns2:FONDO>{fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:Id)}</ns2:FONDO>
            <ns2:MONEDA>{fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:monto[com:tipo/cat:DescripcionCorta='DESEMBOLSO']/com:moneda/cat:codigoExterno)}</ns2:MONEDA>
            <ns2:MONTO>{fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:monto[com:tipo/cat:DescripcionCorta='DESEMBOLSO']/com:importe)}</ns2:MONTO>
            <ns2:FECHA_VALOR>{
            if ($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:fechaDisponibilidadFondos/text() != '')
            then fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:fechaDisponibilidadFondos)
            else fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:fechaEstimadaDesembolsar)
            }</ns2:FECHA_VALOR>
            <ns2:FECHA_VENCIMIENTO>{fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:fechaVencimiento)}</ns2:FECHA_VENCIMIENTO>
            <ns2:DESTINO>{
            if (fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:codigoExterno) != '')
            then fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:codigoExterno)
            else 'ND'
            }</ns2:DESTINO>
            <ns2:NUMERO_CUENTA_CLIENTE>{fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:cuentaCliente)}</ns2:NUMERO_CUENTA_CLIENTE>
            <ns2:PRODUCTO_FINANCIERO>{fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:producto/pro:codExterno)}</ns2:PRODUCTO_FINANCIERO>
            <ns2:REVOLVENCIA>{
            if (fn:data($ValidarLoanComplejoRequest/ns1:LineaCredito/lin:EsRevolvente)= true())
            then 'Y'
            else 'N'
            }</ns2:REVOLVENCIA>
            <ns2:CONSIDERAR_FERIDADOS>{
            if (fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tratamientoDiasFeriados) != '')
            then fn:substring($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tratamientoDiasFeriados,1,1)
            else ''
            }</ns2:CONSIDERAR_FERIDADOS>
            <ns2:MOVER_ENTRE_MESES>
            {
            if (fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:moverEntreMeses)= true())
            then 'Y'
            else 'N'
            }</ns2:MOVER_ENTRE_MESES>
            <ns2:NUMERO_CONTRATO_LINEA>{fn:data($ValidarLoanComplejoRequest/ns1:LineaCredito/lin:NumeroLineaCredito)}</ns2:NUMERO_CONTRATO_LINEA>
            <ns2:PLANTILLA_LIMITE></ns2:PLANTILLA_LIMITE>
            <ns2:SERIAL_LIMITE></ns2:SERIAL_LIMITE>
            <ns2:LISTA_COMPONENTES>
                {
                    for $componente in $ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:Componente
                    order by if ($componente/des:TipoComponente/cat:codigoExterno = 'INTORD') then (0) else if ($componente/des:TipoComponente/cat:codigoExterno = 'DESCTO') then (0) else if ($componente/des:TipoComponente/cat:codigoExterno = 'INTSPRD') then (1) else if ($componente/des:TipoComponente/cat:codigoExterno = 'INTMOR') then (2) else  (3)
                    return
                    <ns2:LISTA_COMPONENTES_ITEM>
                        {
                         if( $componente/des:esDependiente/text() = '1' and $componente/des:TipoComponente/cat:codigoExterno/text() = 'INTMOR')then
                         <ns2:TIPO_TASA>X</ns2:TIPO_TASA>
                         else <ns2:TIPO_TASA>{fn:data($componente/des:TipoTasa/cat:codigoExterno)}</ns2:TIPO_TASA>
                         }
                        <ns2:CODIGO_TASA>{fn:data($componente/des:CodigoTasaReferencia)}</ns2:CODIGO_TASA>
                        <ns2:TIPO_COMISION>{fn:data($componente/des:TipoComponente/cat:codigoExterno)}</ns2:TIPO_COMISION>
                        <ns2:BASE_CALCULO>{fn:data($componente/des:BaseCalculo/cat:codigoExterno)}</ns2:BASE_CALCULO>
                        {
                         if( 
                            (fn:data($componente/des:TipoTasa/cat:codigoExterno)='F' or $componente/des:esDependiente/text() = '1') 
                            and ($componente/des:SpreadTasa/text() != '0' or  $componente/des:TipoComponente/cat:codigoExterno/text() != 'INTMOR')
                            )then
                         <ns2:SPREAD>{fn:data($componente/des:SpreadTasa)}</ns2:SPREAD>
                         else(<ns2:SPREAD xsi:nil="true"/>)
                         }
                         {
                         if ($componente/des:esDependiente/text() = '1' and $componente/des:SpreadTasa/text() != '' and $componente/des:SpreadTasa/text() != '0')then
                         <ns2:VALOR_COMISION>{fn:data($componente/des:SpreadTasa)}</ns2:VALOR_COMISION>
                         else if (fn:data($componente/des:TipoTasa/cat:codigoExterno)='X')then
                         <ns2:VALOR_COMISION>{fn:data($componente/des:ValorTasaReferencia)}</ns2:VALOR_COMISION>
                         else(<ns2:VALOR_COMISION xsi:nil="true"/>)}
                         {
                         if (fn:data($componente/des:TipoTasa/cat:codigoExterno)='S')then
                         <ns2:MONTO>{fn:data($componente/des:MontoDescuento)}</ns2:MONTO>
                         else(<ns2:MONTO xsi:nil="true"/>)}
                         {
                         if (fn:data($componente/des:TipoTasa/cat:codigoExterno)='F')then
                         <ns2:TASA_MINIMA>{fn:data($componente/des:LimiteTasaMinima)}</ns2:TASA_MINIMA>
                         else(<ns2:TASA_MINIMA xsi:nil="true"/>)}
                         {
                         if (fn:data($componente/des:TipoTasa/cat:codigoExterno)='F')then
                         <ns2:TASA_MAXIMA>{fn:data($componente/des:LimiteTasaMaxima)}</ns2:TASA_MAXIMA>
                         else(<ns2:TASA_MAXIMA xsi:nil="true"/>)}
                        <ns2:ES_DEPENDIENTE>{
                         if($componente/des:esDependiente/text() = '1')
                          then 'Y'
                          else 'N'
                         }</ns2:ES_DEPENDIENTE>
                        <ns2:ES_FACTOR>{
                         if($componente/des:esFactor/text() = '1')
                          then 'Y'
                          else 'N'
                         }</ns2:ES_FACTOR>
                         {
                         if (fn:data($componente/des:TipoTasa/cat:codigoExterno)='F')then
                         <ns2:DIAS_SPOT_TASA>{fn:data($componente/des:DiasSpotTasa)}</ns2:DIAS_SPOT_TASA>
                         else(<ns2:DIAS_SPOT_TASA xsi:nil="true"/>)}
                         {
                         if( $componente/des:TipoRedondeo/cat:codigoExterno/text() != '')then
                         <ns2:CANTIDAD_DECIMALES>{fn:data($componente/des:CantidadDecimales)}</ns2:CANTIDAD_DECIMALES>
                         else <ns2:CANTIDAD_DECIMALES></ns2:CANTIDAD_DECIMALES>
                         }
                        <ns2:TIPO_REDONDEO>{fn:data($componente/des:TipoRedondeo/cat:codigoExterno)}</ns2:TIPO_REDONDEO>
                        {
                        if(($componente/des:TipoComponente/cat:codigoExterno != 'INTMOR') or ($componente/des:TipoComponente/cat:codigoExterno = 'INTMOR' and $componente/des:TipoTasa/cat:codigoExterno != 'X')) then
                        <ns2:LISTA_CALENDARIO>
                          {
                            if(count($componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'])=1)then                    
                            <ns2:LISTA_CALENDARIO_ITEM>
                                <ns2:TIPO_PLAN>{fn:data($componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:TipoPlan/cat:codigoExterno)}</ns2:TIPO_PLAN>
                                <ns2:TIPO_FRECUENCIA>{fn:data($componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:Frecuencia/des:Frecuencia/com:Tipo/cat:codigoExterno)}</ns2:TIPO_FRECUENCIA>
                                <ns2:FRECUENCIA>{fn:data($componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:Frecuencia/des:Frecuencia/com:Valor)}</ns2:FRECUENCIA>
                                <ns2:MONTO>{
                                if(fn:data($componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:Monto/com:importe))
                                then fn:data($componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:Monto/com:importe)
                                else fn:round-half-to-even(
                                      ( (if (fn:data($componente/des:TipoTasa/cat:codigoExterno)='S') then fn:data($componente/des:MontoDescuento) else 0) 
                                        div 
                                        fn:data($componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:NumeroCuotas) 
                                      ),2)
                                }</ns2:MONTO>
                                <ns2:FECHA_INICIO>{fn:data($componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:Frecuencia/des:FechaInicio)}</ns2:FECHA_INICIO>
                                <ns2:NUMERO_CUOTAS>{
                                  if (fn:data($componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:Frecuencia/des:Frecuencia/com:Tipo/cat:Id)<4)then
                                  fn:data($componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:NumeroCuotas) -1
                                  else fn:data($componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:NumeroCuotas)
                                }</ns2:NUMERO_CUOTAS>
                            </ns2:LISTA_CALENDARIO_ITEM>
                            else
                            for $calendario in $componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'P']
                            return
                            <ns2:LISTA_CALENDARIO_ITEM>
                                <ns2:TIPO_PLAN>{fn:data($calendario/des:TipoPlan/cat:codigoExterno)}</ns2:TIPO_PLAN>
                                <ns2:TIPO_FRECUENCIA>{fn:data($calendario/des:Frecuencia/des:Frecuencia/com:Tipo/cat:codigoExterno)}</ns2:TIPO_FRECUENCIA>
                                <ns2:FRECUENCIA>{fn:data($calendario/des:Frecuencia/des:Frecuencia/com:Valor)}</ns2:FRECUENCIA>
                                <ns2:MONTO>{fn:data($calendario/des:Monto/com:importe)}</ns2:MONTO>
                                <ns2:FECHA_INICIO>{fn:data($calendario/des:Frecuencia/des:FechaInicio)}</ns2:FECHA_INICIO>
                                <ns2:NUMERO_CUOTAS>{fn:data($calendario/des:NumeroCuotas)}</ns2:NUMERO_CUOTAS>
                            </ns2:LISTA_CALENDARIO_ITEM>
                            }
                            {
                              if(count($componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'])=1 
                                  and fn:data($componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:Frecuencia/des:Frecuencia/com:Tipo/cat:Id)<4)then
                              <ns2:LISTA_CALENDARIO_ITEM>
                                  <ns2:TIPO_PLAN>{fn:data($componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:TipoPlan/cat:codigoExterno)}</ns2:TIPO_PLAN>
                                  <ns2:TIPO_FRECUENCIA>B</ns2:TIPO_FRECUENCIA>
                                  <ns2:FRECUENCIA>1</ns2:FRECUENCIA>
                                  <ns2:MONTO>{fn:round-half-to-even(
                                  ( (if (fn:data($componente/des:TipoTasa/cat:codigoExterno)='S') then fn:data($componente/des:MontoDescuento) else 0) -
                                    ( 
                                      ( fn:data($componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:NumeroCuotas) - 1 )
                                      * fn:round-half-to-even(
                                        ( (if (fn:data($componente/des:TipoTasa/cat:codigoExterno)='S') then fn:data($componente/des:MontoDescuento) else 0) 
                                          div 
                                          fn:data($componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:NumeroCuotas) 
                                        ),2)
                                    )
                                  ),2)
                                  }</ns2:MONTO>
                                  <ns2:FECHA_INICIO>{fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:fechaVencimiento)}</ns2:FECHA_INICIO>
                                  <ns2:NUMERO_CUOTAS>1</ns2:NUMERO_CUOTAS>
                              </ns2:LISTA_CALENDARIO_ITEM>
                                else()
                              }
                              {
                                if(count($componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'R'])=1 and $componente/des:TipoTasa/cat:codigoExterno != 'X')then                    
                                <ns2:LISTA_CALENDARIO_ITEM>
                                    <ns2:TIPO_PLAN>{fn:data($componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'R'][1]/des:TipoPlan/cat:codigoExterno)}</ns2:TIPO_PLAN>
                                    <ns2:TIPO_FRECUENCIA>{fn:data($componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'R'][1]/des:Frecuencia/des:Frecuencia/com:Tipo/cat:codigoExterno)}</ns2:TIPO_FRECUENCIA>
                                    <ns2:FRECUENCIA>{fn:data($componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'R'][1]/des:Frecuencia/des:Frecuencia/com:Valor)}</ns2:FRECUENCIA>
                                    <ns2:MONTO>{
                                    if(fn:data($componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'R'][1]/des:Monto/com:importe))
                                    then fn:data($componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'R'][1]/des:Monto/com:importe)
                                    else fn:round-half-to-even(
                                          ( 0 
                                            div 
                                            fn:data($componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'R'][1]/des:NumeroCuotas) 
                                          ),2)
                                    }</ns2:MONTO>
                                    <ns2:FECHA_INICIO>{fn:data($componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'R'][1]/des:Frecuencia/des:FechaInicio)}</ns2:FECHA_INICIO>
                                    <ns2:NUMERO_CUOTAS>{fn:data($componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'R'][1]/des:NumeroCuotas)}</ns2:NUMERO_CUOTAS>
                                </ns2:LISTA_CALENDARIO_ITEM>
                                else if ($componente/des:TipoTasa/cat:codigoExterno != 'X') then
                                for $calendario in $componente/des:calendario[des:TipoPlan/cat:codigoExterno = 'R']
                                return
                                <ns2:LISTA_CALENDARIO_ITEM>
                                    <ns2:TIPO_PLAN>{fn:data($calendario/des:TipoPlan/cat:codigoExterno)}</ns2:TIPO_PLAN>
                                    <ns2:TIPO_FRECUENCIA>{fn:data($calendario/des:Frecuencia/des:Frecuencia/com:Tipo/cat:codigoExterno)}</ns2:TIPO_FRECUENCIA>
                                    <ns2:FRECUENCIA>{fn:data($calendario/des:Frecuencia/des:Frecuencia/com:Valor)}</ns2:FRECUENCIA>
                                    <ns2:MONTO>{fn:data($calendario/des:Monto/com:importe)}</ns2:MONTO>
                                    <ns2:FECHA_INICIO>{fn:data($calendario/des:Frecuencia/des:FechaInicio)}</ns2:FECHA_INICIO>
                                    <ns2:NUMERO_CUOTAS>{fn:data($calendario/des:NumeroCuotas)}</ns2:NUMERO_CUOTAS>
                                </ns2:LISTA_CALENDARIO_ITEM>
                                else()
                                }
                        </ns2:LISTA_CALENDARIO>
                        else()
                        }
                    </ns2:LISTA_COMPONENTES_ITEM>
                }
            </ns2:LISTA_COMPONENTES>
            <ns2:LISTA_CARGOS>
                {
                    for $Cargo in $ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:Cargo
                    return 
                    <ns2:LISTA_CARGOS_ITEM>
                        <ns2:TIPO_CARGO>{fn:data($Cargo/cat:codigoExterno)}</ns2:TIPO_CARGO>
                        <ns2:MONTOCARGO>{fn:data($Cargo/des:Monto/com:importe)}</ns2:MONTOCARGO>
                    </ns2:LISTA_CARGOS_ITEM>
                }
            </ns2:LISTA_CARGOS>
            <ns2:LISTA_CALENDARIO_CAPITAL>
                {
                    if(count($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'])=1)then
                      <ns2:LISTA_CALENDARIO_ITEM>
                          <ns2:TIPO_PLAN>{fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:TipoPlan/cat:codigoExterno)}</ns2:TIPO_PLAN>
                          <ns2:TIPO_FRECUENCIA>{fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:Frecuencia/des:Frecuencia/com:Tipo/cat:codigoExterno)}</ns2:TIPO_FRECUENCIA>
                          <ns2:FRECUENCIA>{fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:Frecuencia/des:Frecuencia/com:Valor)}</ns2:FRECUENCIA>
                          <ns2:MONTO>{
                          if(fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:Monto/com:importe))
                          then fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:Monto/com:importe)
                          else fn:round-half-to-even(
                                ( fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:monto[com:tipo/cat:DescripcionCorta='DESEMBOLSO']/com:importe) 
                                  div 
                                  fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:NumeroCuotas) 
                                ),2) 
                          }</ns2:MONTO>
                          <ns2:FECHA_INICIO>{fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:Frecuencia/des:FechaInicio)}</ns2:FECHA_INICIO>
                          <ns2:NUMERO_CUOTAS>{
                            if (fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:Frecuencia/des:Frecuencia/com:Tipo/cat:Id)<4)then
                            fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:NumeroCuotas) -1
                            else fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:NumeroCuotas)
                          }</ns2:NUMERO_CUOTAS>
                      </ns2:LISTA_CALENDARIO_ITEM> 
                    else
                    for $calendario in $ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'P']
                    return
                      <ns2:LISTA_CALENDARIO_ITEM>
                          <ns2:TIPO_PLAN>{fn:data($calendario/des:TipoPlan/cat:codigoExterno)}</ns2:TIPO_PLAN>
                          <ns2:TIPO_FRECUENCIA>{fn:data($calendario/des:Frecuencia/des:Frecuencia/com:Tipo/cat:codigoExterno)}</ns2:TIPO_FRECUENCIA>
                          <ns2:FRECUENCIA>{fn:data($calendario/des:Frecuencia/des:Frecuencia/com:Valor)}</ns2:FRECUENCIA>
                          <ns2:MONTO>{fn:data($calendario/des:Monto/com:importe)}</ns2:MONTO>
                          <ns2:FECHA_INICIO>{fn:data($calendario/des:Frecuencia/des:FechaInicio)}</ns2:FECHA_INICIO>
                          <ns2:NUMERO_CUOTAS>{fn:data($calendario/des:NumeroCuotas)}</ns2:NUMERO_CUOTAS>
                      </ns2:LISTA_CALENDARIO_ITEM>
                }
                {
                if(count($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'])=1 
                    and fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:Frecuencia/des:Frecuencia/com:Tipo/cat:Id)<4)then
                    <ns2:LISTA_CALENDARIO_ITEM>
                          <ns2:TIPO_PLAN>{fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:TipoPlan/cat:codigoExterno)}</ns2:TIPO_PLAN>
                          <ns2:TIPO_FRECUENCIA>B</ns2:TIPO_FRECUENCIA>
                          <ns2:FRECUENCIA>1</ns2:FRECUENCIA>
                          <ns2:MONTO>{fn:round-half-to-even(
                          ( fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:monto[com:tipo/cat:DescripcionCorta='DESEMBOLSO']/com:importe) -
                            ( 
                              ( fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:NumeroCuotas) - 1 )
                              * fn:round-half-to-even(
                                ( fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:monto[com:tipo/cat:DescripcionCorta='DESEMBOLSO']/com:importe) 
                                  div 
                                  fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'P'][1]/des:NumeroCuotas) 
                                ),2)
                            )
                          ),2)
                          }</ns2:MONTO>
                          <ns2:FECHA_INICIO>{fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:fechaVencimiento)}</ns2:FECHA_INICIO>
                          <ns2:NUMERO_CUOTAS>1</ns2:NUMERO_CUOTAS>
                      </ns2:LISTA_CALENDARIO_ITEM>
                  else()
                }
                {
                    if(count($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'R'])=1)then
                      <ns2:LISTA_CALENDARIO_ITEM>
                          <ns2:TIPO_PLAN>{fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'R'][1]/des:TipoPlan/cat:codigoExterno)}</ns2:TIPO_PLAN>
                          <ns2:TIPO_FRECUENCIA>{fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'R'][1]/des:Frecuencia/des:Frecuencia/com:Tipo/cat:codigoExterno)}</ns2:TIPO_FRECUENCIA>
                          <ns2:FRECUENCIA>{fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'R'][1]/des:Frecuencia/des:Frecuencia/com:Valor)}</ns2:FRECUENCIA>
                          <ns2:MONTO>{
                          if(fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'R'][1]/des:Monto/com:importe))
                          then fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'R'][1]/des:Monto/com:importe)
                          else fn:round-half-to-even(
                                ( fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:monto[com:tipo/cat:DescripcionCorta='DESEMBOLSO']/com:importe) 
                                  div 
                                  fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'R'][1]/des:NumeroCuotas) 
                                ),2) 
                          }</ns2:MONTO>
                          <ns2:FECHA_INICIO>{fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'R'][1]/des:Frecuencia/des:FechaInicio)}</ns2:FECHA_INICIO>
                          <ns2:NUMERO_CUOTAS>{fn:data($ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'R'][1]/des:NumeroCuotas)}</ns2:NUMERO_CUOTAS>
                      </ns2:LISTA_CALENDARIO_ITEM> 
                    else
                    for $calendario in $ValidarLoanComplejoRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:calendario[des:TipoPlan/cat:codigoExterno = 'R']
                    return
                      <ns2:LISTA_CALENDARIO_ITEM>
                          <ns2:TIPO_PLAN>{fn:data($calendario/des:TipoPlan/cat:codigoExterno)}</ns2:TIPO_PLAN>
                          <ns2:TIPO_FRECUENCIA>{fn:data($calendario/des:Frecuencia/des:Frecuencia/com:Tipo/cat:codigoExterno)}</ns2:TIPO_FRECUENCIA>
                          <ns2:FRECUENCIA>{fn:data($calendario/des:Frecuencia/des:Frecuencia/com:Valor)}</ns2:FRECUENCIA>
                          <ns2:MONTO>{fn:data($calendario/des:Monto/com:importe)}</ns2:MONTO>
                          <ns2:FECHA_INICIO>{fn:data($calendario/des:Frecuencia/des:FechaInicio)}</ns2:FECHA_INICIO>
                          <ns2:NUMERO_CUOTAS>{fn:data($calendario/des:NumeroCuotas)}</ns2:NUMERO_CUOTAS>
                      </ns2:LISTA_CALENDARIO_ITEM>
                }
            </ns2:LISTA_CALENDARIO_CAPITAL>
        </ns2:LOAN>
    </ns2:InputParameters>
};

local:func($ValidarLoanComplejoRequest)