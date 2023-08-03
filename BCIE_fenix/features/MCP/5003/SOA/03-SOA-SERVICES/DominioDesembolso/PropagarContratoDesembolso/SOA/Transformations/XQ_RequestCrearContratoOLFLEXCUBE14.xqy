xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V2/Schema/DesembolsoMOV2.xsd", 
                     "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace pre="http://www.bcie.org/FLEXCUBE14/PrestamoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Flexcube14/DominioPrestamo/Prestamo/V1/Schema/PrestamoMO.xsd" ::)
declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace com = "http://www.bcie.org/FLEXCUBE14/ComponenteBO";

declare namespace cal = "http://www.bcie.org/FLEXCUBE14/CalendarioBO";

declare namespace des1 = "http://www.bcie.org/DesembolsoBOV2";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace com1 = "http://www.bcie.org/CommonBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare variable $outConsultarInformacionDesembolso.response as element() (:: schema-element(des:ConsultarInformacionReglasByIdResponse) ::) external;
declare variable $outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse as element() (:: schema-element(lin:ConsultarLineaCreditoBPELResponse) ::) external;
declare variable $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response as element() (:: schema-element(des:ConsultarDesembolsosByIdV2Response) ::) external;

declare function local:funcXq_requestcrearcontratoolflexcube14($outConsultarInformacionDesembolso.response as element() (:: schema-element(des:ConsultarInformacionReglasByIdResponse) ::), 
                                                               $outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse as element() (:: schema-element(lin:ConsultarLineaCreditoBPELResponse) ::), 
                                                               $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response as element() (:: schema-element(des:ConsultarDesembolsosByIdV2Response) ::)) 
                                                               as element() (:: schema-element(pre:CreaContratoOLRequest) ::) {
    <pre:CreaContratoOLRequest>
        <pre:ContratoOL>
            <pre:Codigo_Desembolso>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:idDesembolso)}</pre:Codigo_Desembolso>
            <pre:Nombre_Negocio>{fn:data($outConsultarInformacionDesembolso.response/des:Operacion/ope:nombre)}</pre:Nombre_Negocio>
            <pre:Codigo_Cliente>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:idFacturador)}</pre:Codigo_Cliente>
            <pre:Sector_Institucional>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:sector/cat:codigoExterno)}</pre:Sector_Institucional>
            <pre:Actividad_Economica>{
            if(fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:ActividadEconomica/cat:codigoExterno) != '')
            then fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)
            else 'ND'
            }</pre:Actividad_Economica>
            <pre:Ejecutivo>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:usuario)}</pre:Ejecutivo>
            <pre:Pais>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:pais/cat:codigoExterno)}</pre:Pais>
            <pre:Eje_Estrategico>{
            if(fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:EjeEstrategico/cat:codigoExterno) != '')
            then fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)
            else 'ND'
            }</pre:Eje_Estrategico>
            <pre:Area_Focalizacion>{
            if(fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno) != '')
            then fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)
            else 'ND'
            }</pre:Area_Focalizacion>
            <pre:Objetivo_Estrategico></pre:Objetivo_Estrategico>
            <pre:Tipo_Recurso>ND</pre:Tipo_Recurso>
            <pre:Tipo_Financiamiento>D</pre:Tipo_Financiamiento>
            <pre:Numero_Tesoreria>{fn:concat(fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:idDesembolso),"/",fn:year-from-date(fn:current-date()))}</pre:Numero_Tesoreria>
            <pre:Linea_Financiera>{
         if(fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:LineaFinanciera/cat:codigoExterno) != '')
            then fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:LineaFinanciera/cat:codigoExterno)
            else 'ND'}
            </pre:Linea_Financiera>
            <pre:Fondo>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:fondo/cat:Id)}</pre:Fondo>
            <pre:Moneda>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:monto[com1:tipo/cat:DescripcionCorta='DESEMBOLSO']/com1:moneda/cat:codigoExterno)}</pre:Moneda>
            <pre:Monto>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:monto[com1:tipo/cat:DescripcionCorta='DESEMBOLSO']/com1:importe)}</pre:Monto>
            <pre:Fecha_Valor>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:fechaDisponibilidadFondos)}</pre:Fecha_Valor>
            <pre:Fecha_Vencimiento>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:fechaVencimiento)}</pre:Fecha_Vencimiento>
            <pre:Destino>{
             if (fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:DestinoFinanciamiento/cat:codigoExterno)!= '')
             then fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:DestinoFinanciamiento/cat:codigoExterno)
             else 'ND'}</pre:Destino>
            <pre:Numero_Cuenta_Cliente>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:cuentaCliente)}</pre:Numero_Cuenta_Cliente>
            <pre:Producto_Financiero>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:producto/pro:codExterno)}</pre:Producto_Financiero>
            <pre:Revolvencia>{
            if(fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:EsRevolvente)=true())
            then 'Y'
            else 'N'
            }</pre:Revolvencia>
            <pre:Considerar_Feridados>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:tratamientoDiasFeriados)}</pre:Considerar_Feridados>
            <pre:Mover_Entre_Meses>{
             if(fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:moverEntreMeses) = true())
              then 'Y'
              else 'N'
             }</pre:Mover_Entre_Meses>
            <pre:Numero_Contrato_Linea>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:NumeroLineaCredito)}</pre:Numero_Contrato_Linea>
            <pre:Plantilla_Limite></pre:Plantilla_Limite>
            <pre:Serial_Limite></pre:Serial_Limite>
            <pre:Lista_Componentes>
            {
              for $componente in $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:Componente
              order by if ($componente/des1:TipoComponente/cat:codigoExterno = 'INTORD') then (0) else if ($componente/des1:TipoComponente/cat:codigoExterno = 'DESCTO') then (0) else if ($componente/des1:TipoComponente/cat:codigoExterno = 'INTSPRD') then (1) else if ($componente/des1:TipoComponente/cat:codigoExterno = 'INTMOR') then (2) else  (3)
              return
                <com:componente>
                    {
                     if( $componente/des1:esDependiente/text() = '1' and $componente/des1:TipoComponente/cat:codigoExterno/text() = 'INTMOR')then
                     <com:Tipo_Tasa>X</com:Tipo_Tasa>
                     else <com:Tipo_Tasa>{fn:data($componente/des1:TipoTasa/cat:codigoExterno)}</com:Tipo_Tasa>
                     }
                    <com:Codigo_Tasa>{fn:data($componente/des1:CodigoTasaReferencia)}</com:Codigo_Tasa>
                    <com:Tipo_Comision>{fn:data($componente/des1:TipoComponente/cat:codigoExterno)}</com:Tipo_Comision>
                    <com:Base_Calculo>{fn:data($componente/des1:BaseCalculo/cat:codigoExterno)}</com:Base_Calculo>
                     {
                     if( 
                        (fn:data($componente/des1:TipoTasa/cat:codigoExterno)='F' or $componente/des1:esDependiente/text() = '1') 
                        and ($componente/des1:SpreadTasa/text() != '0' or  $componente/des1:TipoComponente/cat:codigoExterno/text() != 'INTMOR')
                        )then
                     <com:Spread>{fn:data($componente/des1:SpreadTasa)}</com:Spread>
                     else(<com:Spread xsi:nil="true"/>)
                     }
                     {
                     if ($componente/des1:esDependiente/text() = '1' and $componente/des1:SpreadTasa/text() != '' and $componente/des1:SpreadTasa/text() != '0')then
                     <com:Valor_Comision>{fn:data($componente/des1:SpreadTasa)}</com:Valor_Comision>
                     else if (fn:data($componente/des1:TipoTasa/cat:codigoExterno)='X')then
                     <com:Valor_Comision>{fn:data($componente/des1:ValorTasaReferencia)}</com:Valor_Comision>
                     else(<com:Valor_Comision xsi:nil="true"/>)}
                     {
                     if (fn:data($componente/des1:TipoTasa/cat:codigoExterno)='S')then
                     <com:Monto>{fn:data($componente/des1:MontoDescuento)}</com:Monto>
                     else(<com:Monto xsi:nil="true"/>)}
                    {
                     if (fn:data($componente/des1:TipoTasa/cat:codigoExterno)='F')then
                     <com:Tasa_Minima>{fn:data($componente/des1:LimiteTasaMinima)}</com:Tasa_Minima>
                     else(<com:Tasa_Minima xsi:nil="true"/>)}
                     {
                     if (fn:data($componente/des1:TipoTasa/cat:codigoExterno)='F')then
                     <com:Tasa_Maxima>{fn:data($componente/des1:LimiteTasaMaxima)}</com:Tasa_Maxima>
                     else(<com:Tasa_Maxima xsi:nil="true"/>)}
                    <com:Es_Dependiente>{
                     if($componente/des1:esDependiente/text() = '1')
                      then 'Y'
                      else 'N'
                     }</com:Es_Dependiente>
                    <com:Es_Factor>{
                     if($componente/des1:esFactor/text() = '1')
                      then 'Y'
                      else 'N'
                     }</com:Es_Factor>
                     {
                     if (fn:data($componente/des1:TipoTasa/cat:codigoExterno)='F')then
                     <com:Dias_Spot_Tasa>{fn:data($componente/des1:DiasSpotTasa)}</com:Dias_Spot_Tasa>
                     else(<com:Dias_Spot_Tasa xsi:nil="true"/>)}
                     {
                     if( $componente/des1:TipoRedondeo/cat:codigoExterno/text() != '')then
                     <com:Cantidad_Decimales>{fn:data($componente/des1:CantidadDecimales)}</com:Cantidad_Decimales>
                     else <com:Cantidad_Decimales></com:Cantidad_Decimales>
                     }
                    <com:Tipo_Redondeo>{fn:data($componente/des1:TipoRedondeo/cat:codigoExterno)}</com:Tipo_Redondeo>
                    {
                    if(($componente/des1:TipoComponente/cat:codigoExterno != 'INTMOR') or ($componente/des1:TipoComponente/cat:codigoExterno = 'INTMOR' and $componente/des1:TipoTasa/cat:codigoExterno != 'X')) then
                    <com:Lista_Calendario>
                        {
                            if(count($componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'])=1)then                    
                            <cal:calendario>
                                <cal:Tipo_Plan>{fn:data($componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:TipoPlan)}</cal:Tipo_Plan>
                                <cal:Tipo_Frecuencia>{fn:data($componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:codigoExterno)}</cal:Tipo_Frecuencia>
                                <cal:Frecuencia>{fn:data($componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:Frecuencia/des1:Frecuencia/com1:Valor)}</cal:Frecuencia>
                                <cal:Monto>{
                                if(fn:data($componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:Monto/com1:importe))
                                then fn:data($componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:Monto/com1:importe)
                                else fn:round-half-to-even(
                                      ( (if (fn:data($componente/des1:TipoTasa/cat:codigoExterno)='S') then fn:data($componente/des1:MontoDescuento) else 0) 
                                        div 
                                        fn:data($componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:NumeroCuotas) 
                                      ),2)
                                }</cal:Monto>
                                <cal:Fecha_Inicio>{fn:data($componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:Frecuencia/des1:FechaInicio)}</cal:Fecha_Inicio>
                                <cal:Numero_cuotas>{
                                  if (fn:data($componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:Id)<4)then
                                  fn:data($componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:NumeroCuotas) -1
                                  else fn:data($componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:NumeroCuotas)
                                }
                                </cal:Numero_cuotas>
                            </cal:calendario>
                            else
                            for $calendario in $componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P']
                            return
                            <cal:calendario>
                                <cal:Tipo_Plan>{fn:data($calendario/des1:TipoPlan/cat:codigoExterno)}</cal:Tipo_Plan>
                                <cal:Tipo_Frecuencia>{fn:data($calendario/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:codigoExterno)}</cal:Tipo_Frecuencia>
                                <cal:Frecuencia>{fn:data($calendario/des1:Frecuencia/des1:Frecuencia/com1:Valor)}</cal:Frecuencia>
                                <cal:Monto>{fn:data($calendario/des1:Monto/com1:importe)}</cal:Monto>
                                <cal:Fecha_Inicio>{fn:data($calendario/des1:Frecuencia/des1:FechaInicio)}</cal:Fecha_Inicio>
                                <cal:Numero_cuotas>{fn:data($calendario/des1:NumeroCuotas)}</cal:Numero_cuotas>
                            </cal:calendario>
                            }
                            
                            {
                            if(count($componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'])=1 
                                and fn:data($componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:Id)<4)then
                            <cal:calendario>
                                <cal:Tipo_Plan>{fn:data($componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:TipoPlan)}</cal:Tipo_Plan>
                                <cal:Tipo_Frecuencia>B</cal:Tipo_Frecuencia>
                                <cal:Frecuencia>1</cal:Frecuencia>
                                <cal:Monto>{fn:round-half-to-even(
                                ( (if (fn:data($componente/des1:TipoTasa/cat:codigoExterno)='S') then fn:data($componente/des1:MontoDescuento) else 0) -
                                  ( 
                                    ( fn:data($componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:NumeroCuotas) - 1 )
                                    * fn:round-half-to-even(
                                      ( (if (fn:data($componente/des1:TipoTasa/cat:codigoExterno)='S') then fn:data($componente/des1:MontoDescuento) else 0) 
                                        div 
                                        fn:data($componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:NumeroCuotas) 
                                      ),2)
                                  )
                                ),2)
                                }</cal:Monto>
                                <cal:Fecha_Inicio>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:fechaVencimiento)}</cal:Fecha_Inicio>
                                <cal:Numero_cuotas>1</cal:Numero_cuotas>
                              </cal:calendario>
                              else()
                            }
                            
                            {
                            if(count($componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'R'])=1 and $componente/des1:TipoTasa/cat:codigoExterno != 'X')then                    
                            <cal:calendario>
                                <cal:Tipo_Plan>{fn:data($componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'R'][1]/des1:TipoPlan)}</cal:Tipo_Plan>
                                <cal:Tipo_Frecuencia>{fn:data($componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'R'][1]/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:codigoExterno)}</cal:Tipo_Frecuencia>
                                <cal:Frecuencia>{fn:data($componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'R'][1]/des1:Frecuencia/des1:Frecuencia/com1:Valor)}</cal:Frecuencia>
                                <cal:Monto>{
                                if(fn:data($componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'R'][1]/des1:Monto/com1:importe))
                                then fn:data($componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'R'][1]/des1:Monto/com1:importe)
                                else fn:round-half-to-even(
                                      ( 0 
                                        div 
                                        fn:data($componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'R'][1]/des1:NumeroCuotas) 
                                      ),2)
                                }</cal:Monto>
                                <cal:Fecha_Inicio>{fn:data($componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'R'][1]/des1:Frecuencia/des1:FechaInicio)}</cal:Fecha_Inicio>
                                <cal:Numero_cuotas>{fn:data($componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'R'][1]/des1:NumeroCuotas)}</cal:Numero_cuotas>
                            </cal:calendario>
                            else if ($componente/des1:TipoTasa/cat:codigoExterno != 'X') then
                            for $calendario in $componente/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'R']
                            return
                            <cal:calendario>
                                <cal:Tipo_Plan>{fn:data($calendario/des1:TipoPlan)}</cal:Tipo_Plan>
                                <cal:Tipo_Frecuencia>{fn:data($calendario/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:codigoExterno)}</cal:Tipo_Frecuencia>
                                <cal:Frecuencia>{fn:data($calendario/des1:Frecuencia/des1:Frecuencia/com1:Valor)}</cal:Frecuencia>
                                <cal:Monto>{fn:data($calendario/des1:Monto/com1:importe)}</cal:Monto>
                                <cal:Fecha_Inicio>{fn:data($calendario/des1:Frecuencia/des1:FechaInicio)}</cal:Fecha_Inicio>
                                <cal:Numero_cuotas>{fn:data($calendario/des1:NumeroCuotas)}</cal:Numero_cuotas>
                            </cal:calendario>
                            else()
                            }
                    </com:Lista_Calendario>
                    else()
                    }
                </com:componente>
            }
            </pre:Lista_Componentes>
            <pre:Lista_Cargos>
            {
            for $cargo in $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Cargo
            return
                <pre:cargo>
                    <pre:Tipo_Cargo>{fn:data($cargo/cat:codigoExterno)}</pre:Tipo_Cargo>
                    <pre:MontoCargo>{fn:data($cargo/des1:Monto/com1:importe)}</pre:MontoCargo>
                </pre:cargo>
            }
            </pre:Lista_Cargos>
            <pre:Lista_Calendario_Capital>
                {
                if(count($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'])=1)then                    
                <cal:calendario>
                    <cal:Tipo_Plan>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:TipoPlan)}</cal:Tipo_Plan>
                    <cal:Tipo_Frecuencia>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:codigoExterno)}</cal:Tipo_Frecuencia>
                    <cal:Frecuencia>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:Frecuencia/des1:Frecuencia/com1:Valor)}</cal:Frecuencia>
                    <cal:Monto>{
                    if(fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:Monto/com1:importe))
                    then fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:Monto/com1:importe)
                    else fn:round-half-to-even(
                          ( fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:monto[com1:tipo/cat:DescripcionCorta='DESEMBOLSO']/com1:importe) 
                            div 
                            fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:NumeroCuotas) 
                          ),2) 
                    }</cal:Monto>
                    <cal:Fecha_Inicio>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:Frecuencia/des1:FechaInicio)}</cal:Fecha_Inicio>
                    <cal:Numero_cuotas>{
                      if (fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:Id)<4)then
                      fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:NumeroCuotas) -1
                      else fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:NumeroCuotas)
                    }
                    </cal:Numero_cuotas>
                </cal:calendario>
                else
                for $calendario in $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P']
                return
                <cal:calendario>
                    <cal:Tipo_Plan>{fn:data($calendario/des1:TipoPlan)}</cal:Tipo_Plan>
                    <cal:Tipo_Frecuencia>{fn:data($calendario/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:codigoExterno)}</cal:Tipo_Frecuencia>
                    <cal:Frecuencia>{fn:data($calendario/des1:Frecuencia/des1:Frecuencia/com1:Valor)}</cal:Frecuencia>
                    <cal:Monto>{fn:data($calendario/des1:Monto/com1:importe)}</cal:Monto>
                    <cal:Fecha_Inicio>{fn:data($calendario/des1:Frecuencia/des1:FechaInicio)}</cal:Fecha_Inicio>
                    <cal:Numero_cuotas>{fn:data($calendario/des1:NumeroCuotas)}</cal:Numero_cuotas>
                </cal:calendario>
                }                
                {
                if(count($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'])=1 
                    and fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:Id)<4)then
                <cal:calendario>
                    <cal:Tipo_Plan>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:TipoPlan)}</cal:Tipo_Plan>
                    <cal:Tipo_Frecuencia>B</cal:Tipo_Frecuencia>
                    <cal:Frecuencia>1</cal:Frecuencia>
                    <cal:Monto>{fn:round-half-to-even(
                    ( fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:monto[com1:tipo/cat:DescripcionCorta='DESEMBOLSO']/com1:importe) -
                      ( 
                        ( fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:NumeroCuotas) - 1 )
                        * fn:round-half-to-even(
                          ( fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:monto[com1:tipo/cat:DescripcionCorta='DESEMBOLSO']/com1:importe) 
                            div 
                            fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'P'][1]/des1:NumeroCuotas) 
                          ),2)
                      )
                    ),2)
                    }</cal:Monto>
                    <cal:Fecha_Inicio>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:fechaVencimiento)}</cal:Fecha_Inicio>
                    <cal:Numero_cuotas>1</cal:Numero_cuotas>
                  </cal:calendario>
                  else()
                }
                
                {
                if(count($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'R'])=1)then                    
                <cal:calendario>
                    <cal:Tipo_Plan>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'R'][1]/des1:TipoPlan)}</cal:Tipo_Plan>
                    <cal:Tipo_Frecuencia>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'R'][1]/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:codigoExterno)}</cal:Tipo_Frecuencia>
                    <cal:Frecuencia>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'R'][1]/des1:Frecuencia/des1:Frecuencia/com1:Valor)}</cal:Frecuencia>
                    <cal:Monto>{
                    if(fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'R'][1]/des1:Monto/com1:importe))
                    then fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'R'][1]/des1:Monto/com1:importe)
                    else fn:round-half-to-even(
                          ( fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:monto[com1:tipo/cat:DescripcionCorta='DESEMBOLSO']/com1:importe) 
                            div 
                            fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'R'][1]/des1:NumeroCuotas) 
                          ),2) 
                    }</cal:Monto>
                    <cal:Fecha_Inicio>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'R'][1]/des1:Frecuencia/des1:FechaInicio)}</cal:Fecha_Inicio>
                    <cal:Numero_cuotas>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'R'][1]/des1:NumeroCuotas)}</cal:Numero_cuotas>
                </cal:calendario>
                else
                for $calendario in $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario[des1:TipoPlan/cat:codigoExterno = 'R']
                return
                <cal:calendario>
                    <cal:Tipo_Plan>{fn:data($calendario/des1:TipoPlan)}</cal:Tipo_Plan>
                    <cal:Tipo_Frecuencia>{fn:data($calendario/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:codigoExterno)}</cal:Tipo_Frecuencia>
                    <cal:Frecuencia>{fn:data($calendario/des1:Frecuencia/des1:Frecuencia/com1:Valor)}</cal:Frecuencia>
                    <cal:Monto>{fn:data($calendario/des1:Monto/com1:importe)}</cal:Monto>
                    <cal:Fecha_Inicio>{fn:data($calendario/des1:Frecuencia/des1:FechaInicio)}</cal:Fecha_Inicio>
                    <cal:Numero_cuotas>{fn:data($calendario/des1:NumeroCuotas)}</cal:Numero_cuotas>
                </cal:calendario>
                }
            </pre:Lista_Calendario_Capital>
        </pre:ContratoOL>
        <pre:Usuario>SYSTEM</pre:Usuario>
    </pre:CreaContratoOLRequest>
};

local:funcXq_requestcrearcontratoolflexcube14($outConsultarInformacionDesembolso.response, $outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse, $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response)
