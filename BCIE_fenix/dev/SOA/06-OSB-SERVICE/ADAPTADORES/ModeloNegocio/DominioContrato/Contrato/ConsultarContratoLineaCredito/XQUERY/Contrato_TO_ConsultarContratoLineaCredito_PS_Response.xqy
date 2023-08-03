xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns6="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns4="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarContrato";
(:: import schema at "../XSD/ConsultarContrato.xsd" ::)
declare namespace ns3="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarCuentas";
(:: import schema at "../../ConsultarCuentas/XSD/ConsultarCuentas.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTcaComision";
(:: import schema at "../../../../DominioLineaCredito/ConsultarComisionByContrato/XSD/ConsultarTcaComision.xsd" ::)
declare namespace ns5="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTcaTermino";
(:: import schema at "../../../Termino/ConsultarTerminoByContrato/XSD/ConsultarTcaTermino.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/CosultarLineaCredito";
(:: import schema at "../../../../DominioLineaCredito/ConsultarLineaCreditoByContrato/XSD/CosultarLineaCredito.xsd" ::)
declare namespace ns21="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarAprobacionByOperacion";
(:: import schema at "../../../../DominioOperacion/Aprobacion/ConsultarAprobacionByOperacion/XSD/ConsultarAprobacionByOperacion.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace cat1 = "http://www.bcie.org/CatalogoTerminoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare variable $responselineacredito as element() (:: schema-element(ns1:CosultarLineaCreditoOutputCollection) ::) external;
declare variable $reponseComision as element() (:: schema-element(ns2:ConsultarTcaComisionOutputCollection) ::) external;
declare variable $responseCuentas as element() (:: schema-element(ns3:ConsultarCuentasOutputCollection) ::) external;
declare variable $responseContrato as element() (:: schema-element(ns4:ConsultarContratoOutputCollection) ::) external;
declare variable $reponseTermino as element() (:: schema-element(ns5:ConsultarTcaTerminoOutputCollection) ::) external;
declare variable $ConsultarAprobacionByOperacionOutputCollection as element() (:: schema-element(ns21:ConsultarAprobacionByOperacionOutputCollection) ::) external;
declare variable $index as xs:int external;

declare function local:func($responselineacredito as element() (:: schema-element(ns1:CosultarLineaCreditoOutputCollection) ::), 
                            $reponseComision as element() (:: schema-element(ns2:ConsultarTcaComisionOutputCollection) ::), 
                            $responseCuentas as element() (:: schema-element(ns3:ConsultarCuentasOutputCollection) ::), 
                            $responseContrato as element() (:: schema-element(ns4:ConsultarContratoOutputCollection) ::), 
                            $reponseTermino as element() (:: schema-element(ns5:ConsultarTcaTerminoOutputCollection) ::),
                            $ConsultarAprobacionByOperacionOutputCollection as element() (:: schema-element(ns21:ConsultarAprobacionByOperacionOutputCollection) ::),
                            $index as xs:int
                            ) 
                            as element() (:: schema-element(ns6:ConsultarLineaCreditoResponse) ::) {
    <ns6:ConsultarLineaCreditoResponse>
        <ns6:clienteContrato>
            <con:idContrato>{fn:data($responseContrato/ns4:ConsultarContratoOutput[$index]/ns4:ID)}</con:idContrato>
            <con:idOperacion>{fn:data($responseContrato/ns4:ConsultarContratoOutput[$index]/ns4:ID_OPERACION)}</con:idOperacion>
            <con:fechaFirma>{ if ( fn:data($responseContrato/ns4:ConsultarContratoOutput[$index]/ns4:REQUIERE_FIRMA_CONTRATO)=0 and string($responseContrato/ns4:ConsultarContratoOutput[$index]/ns4:FECHA_FIRMA)='')
            then
           fn:data($ConsultarAprobacionByOperacionOutputCollection/ns21:ConsultarAprobacionByOperacionOutput/ns21:FECHA_APROBACION)
            else
            fn:data($responseContrato/ns4:ConsultarContratoOutput[$index]/ns4:FECHA_FIRMA)
           
            }</con:fechaFirma>
            <con:fechaVigencia>{fn:data($responseContrato/ns4:ConsultarContratoOutput[$index]/ns4:FECHA_VIGENCIA)}</con:fechaVigencia>
            <con:idTipoMonedaMontoEscriturado>{ if ( fn:data($responseContrato/ns4:ConsultarContratoOutput[$index]/ns4:REQUIERE_FIRMA_CONTRATO)=0 and string($responseContrato/ns4:ConsultarContratoOutput[$index]/ns4:MONTO)='')
              then
                fn:data($ConsultarAprobacionByOperacionOutputCollection/ns21:ConsultarAprobacionByOperacionOutput/ns21:ID_TCA_TIPO_MONEDA)
              else
                fn:data($responseContrato/ns4:ConsultarContratoOutput[$index]/ns4:ID_TCA_TIPO_MONEDA)        
              }
            </con:idTipoMonedaMontoEscriturado>
            <con:MontoEscriturado>{ if ( fn:data($responseContrato/ns4:ConsultarContratoOutput[$index]/ns4:REQUIERE_FIRMA_CONTRATO)=0 and string($responseContrato/ns4:ConsultarContratoOutput[$index]/ns4:MONTO)='')
           then
           fn:data($ConsultarAprobacionByOperacionOutputCollection/ns21:ConsultarAprobacionByOperacionOutput/ns21:MONTO)
           else
            fn:data($responseContrato/ns4:ConsultarContratoOutput[$index]/ns4:MONTO)
            
            }</con:MontoEscriturado>
           
            <con:cuentaCliente>
            {
              for $ConsultarcuentaOutput in $responseCuentas/ns3:ConsultarCuentasOutput
              return
                <con:cuentaCliente>{fn:data($ConsultarcuentaOutput/ns3:CUST_AC_NO)}</con:cuentaCliente>
                }
               

            </con:cuentaCliente>
<con:fechaValor>{fn:data($responseContrato/ns4:ConsultarContratoOutput[$index]/ns4:FECHA_VALOR)}</con:fechaValor>
            <con:instanciaProceso>{fn:data($responseContrato/ns4:ConsultarContratoOutput[$index]/ns4:INSTANCIA_PROCESO)}</con:instanciaProceso>
            {
                for $CosultarLineaCreditoOutput in $responselineacredito/ns1:CosultarLineaCreditoOutput
                return 
                <con:LineaCredito>
                    <lin:idLineaCredito>{fn:data($CosultarLineaCreditoOutput/ns1:ID)}</lin:idLineaCredito>
                    <lin:idContrato>{fn:data($CosultarLineaCreditoOutput/ns1:ID_CONTRATO)}</lin:idContrato>
                    <lin:NumeroLineaCredito>{fn:data($CosultarLineaCreditoOutput/ns1:NUMERO_LINEA_CREDITO)}</lin:NumeroLineaCredito>
                    <lin:Descripcion>{fn:data($CosultarLineaCreditoOutput/ns1:DESCRIPCION_LINEA)}</lin:Descripcion>
                    <lin:Flexcube>
                        <lin:id>{fn:data($CosultarLineaCreditoOutput/ns1:ID_FLEXCUBE)}</lin:id>
                        <lin:idProductoFlexcube>{fn:data($CosultarLineaCreditoOutput/ns1:ID_PRODUCTO_FLEXCUBE)}</lin:idProductoFlexcube>
                        <lin:idFlexcubePasivo>{fn:data($CosultarLineaCreditoOutput/ns1:ID_FLEXCUBE_PASIVO)}</lin:idFlexcubePasivo>
                    </lin:Flexcube>
                    <lin:Fondo>{fn:data($CosultarLineaCreditoOutput/ns1:FONDO)}</lin:Fondo>
                    <lin:IdTipoMonedaMontoLinea>{fn:data($CosultarLineaCreditoOutput/ns1:ID_TIPO_MONEDA_MONTO_LINEA)}</lin:IdTipoMonedaMontoLinea>
                    <lin:MontoLinea>{fn:data($CosultarLineaCreditoOutput/ns1:MONTO_LINEA)}</lin:MontoLinea>
                    <lin:EsRevolvente>{ if(string($CosultarLineaCreditoOutput/ns1:ES_REVOLVENTE)='') then false() else
                    if (fn:data($CosultarLineaCreditoOutput/ns1:ES_REVOLVENTE)=1) then true () else  false ()
                    
                    
                    }</lin:EsRevolvente>
                    <lin:TratamientoDiasFeriados>{fn:data($CosultarLineaCreditoOutput/ns1:TRATAMIENTO_DIAS_FERIADOS)}</lin:TratamientoDiasFeriados>
                    <lin:FechaValor>{fn:data($CosultarLineaCreditoOutput/ns1:FECHA_VALOR)}</lin:FechaValor>
                    <lin:FechaVencimiento>{fn:data($CosultarLineaCreditoOutput/ns1:FECHA_VENCIMIENTO)}</lin:FechaVencimiento>
                    <lin:CodigoPantallaLimite>{fn:data($CosultarLineaCreditoOutput/ns1:CODIGO_PLANTILLA_LIMITE)}</lin:CodigoPantallaLimite>
                    <lin:CodigoSerialLimite>{fn:data($CosultarLineaCreditoOutput/ns1:CODIGO_SERIAL_LIMITE)}</lin:CodigoSerialLimite>
                    <lin:CodigoAsignacion>{fn:data($CosultarLineaCreditoOutput/ns1:CODIGO_ASIGNACION)}</lin:CodigoAsignacion>
                    <lin:DescripcionAsignacion>{fn:data($CosultarLineaCreditoOutput/ns1:DESCRIPCION_ASIGNACION)}</lin:DescripcionAsignacion>
                    <lin:CondicionEspecial>{
                     if(string($CosultarLineaCreditoOutput/ns1:CONDICION_ESPECIAL)='')then () else
                    if(fn:data($CosultarLineaCreditoOutput/ns1:CONDICION_ESPECIAL)=1) then true() else false()
                    }</lin:CondicionEspecial>
                    <lin:FechaRegistro>{fn:data($CosultarLineaCreditoOutput/ns1:FECHA_REGISTRO)}</lin:FechaRegistro>
                    <lin:Estado>{
                     if(string($CosultarLineaCreditoOutput/ns1:BAN_ESTATUS)='')then () else
                    if(fn:data($CosultarLineaCreditoOutput/ns1:BAN_ESTATUS)=1) then true() else false()
                    }</lin:Estado>
                    <lin:descCondEspecial>{fn:data($CosultarLineaCreditoOutput/ns1:DESCRIPCION_COND_ESPECIAL)}</lin:descCondEspecial>
                   
                 
                   <lin:frecuenciaGracia>
                    {
                        for $ConsultarTcaTerminoOutput in $reponseTermino/ns5:ConsultarTcaTerminoOutput
                        where fn:data($CosultarLineaCreditoOutput/ns1:ID)=fn:data($ConsultarTcaTerminoOutput/ns5:ID_LINEA_CREDITO)
                        and ( fn:data($ConsultarTcaTerminoOutput/ns5:ID_TCA_ESTADO_TCC)=9 or fn:data($ConsultarTcaTerminoOutput/ns5:ID_TCA_ESTADO_TCC)=15)
                        return
                                                   
                   if ( string($ConsultarTcaTerminoOutput/ns5:DESCRIPCION_CORTA_TCA)='T108' )then
                   
                   fn:data($ConsultarTcaTerminoOutput/ns5:ID_TCA_FRECUENCIA_PLAZO)
                   else ()
                   
                        }
                   
                   </lin:frecuenciaGracia>
                    <lin:plazoGracia>
                     {
                        for $ConsultarTcaTerminoOutput in $reponseTermino/ns5:ConsultarTcaTerminoOutput
                        where fn:data($CosultarLineaCreditoOutput/ns1:ID)=fn:data($ConsultarTcaTerminoOutput/ns5:ID_LINEA_CREDITO)
                       and( fn:data($ConsultarTcaTerminoOutput/ns5:ID_TCA_ESTADO_TCC)=9 or fn:data($ConsultarTcaTerminoOutput/ns5:ID_TCA_ESTADO_TCC)=15)
                        return
                                                    
                   if ( string($ConsultarTcaTerminoOutput/ns5:DESCRIPCION_CORTA_TCA)='T108' )then
                   
                   fn:data($ConsultarTcaTerminoOutput/ns5:PLAZO)
                   
                   else ()
                   
                        }
                    </lin:plazoGracia>
                    <lin:frecuenciaFinanciamiento>
                     {
                       let $valor := for $ConsultarTcaTerminoOutput in $reponseTermino/ns5:ConsultarTcaTerminoOutput
                       where fn:data($CosultarLineaCreditoOutput/ns1:ID)=fn:data($ConsultarTcaTerminoOutput/ns5:ID_LINEA_CREDITO)
                       and ( fn:data($ConsultarTcaTerminoOutput/ns5:ID_TCA_ESTADO_TCC)=9 or fn:data($ConsultarTcaTerminoOutput/ns5:ID_TCA_ESTADO_TCC)=15)
                       and string($ConsultarTcaTerminoOutput/ns5:DESCRIPCION_CORTA_TCA)='T106'
                       and fn:data($ConsultarTcaTerminoOutput/ns5:TER_BAN_ESTATUS) = 1
                       order by $ConsultarTcaTerminoOutput/ns5:ID ascending 
                        return $ConsultarTcaTerminoOutput
                      
                       return fn:data($valor[1]/ns5:ID_TCA_FRECUENCIA_PLAZO)
                   
                        }
                    
                    </lin:frecuenciaFinanciamiento>
                    <lin:plazoFinanciamiento>
                     {
                     
                       let $valor := for $ConsultarTcaTerminoOutput in $reponseTermino/ns5:ConsultarTcaTerminoOutput                       
                       where fn:data($CosultarLineaCreditoOutput/ns1:ID)=fn:data($ConsultarTcaTerminoOutput/ns5:ID_LINEA_CREDITO)
                       and ( fn:data($ConsultarTcaTerminoOutput/ns5:ID_TCA_ESTADO_TCC)=9 or fn:data($ConsultarTcaTerminoOutput/ns5:ID_TCA_ESTADO_TCC)=15)
                       and string($ConsultarTcaTerminoOutput/ns5:DESCRIPCION_CORTA_TCA)='T106'
                       and fn:data($ConsultarTcaTerminoOutput/ns5:TER_BAN_ESTATUS) = 1
                       order by $ConsultarTcaTerminoOutput/ns5:ID ascending 
                        return $ConsultarTcaTerminoOutput
                                                    
                    return fn:data($valor[1]/ns5:PLAZO)
                    
                        }
                    </lin:plazoFinanciamiento>
                    <lin:recursosExternos>
                     {
                        for $ConsultarTcaTerminoOutput in $reponseTermino/ns5:ConsultarTcaTerminoOutput
                       where fn:data($CosultarLineaCreditoOutput/ns1:ID)=fn:data($ConsultarTcaTerminoOutput/ns5:ID_LINEA_CREDITO)
                        return
                                                    
                   if ( string($ConsultarTcaTerminoOutput/ns5:DESCRIPCION_CORTA_TCA)='T305' )then
                   
                   
                if( fn:data($ConsultarTcaTerminoOutput/ns5:SE_APLICAN_RECURSOS_EXTERNOS)=1) then true() else false()
                   
                   else ()
                   
                        }
                    
                    </lin:recursosExternos>
                 
                      {
                        for $ConsultarTcaTerminoOutput in $reponseTermino/ns5:ConsultarTcaTerminoOutput
                      where fn:data($CosultarLineaCreditoOutput/ns1:ID)=fn:data($ConsultarTcaTerminoOutput/ns5:ID_LINEA_CREDITO)
                        return
                                                    
                   if (( string($ConsultarTcaTerminoOutput/ns5:DESCRIPCION_CORTA_TCA)='T501' ) and ( string($ConsultarTcaTerminoOutput/ns5:TASA_MINIMA_DESEMBOLSO)!='' ))      then
                   
                  <lin:tasaMinima>{ fn:data($ConsultarTcaTerminoOutput/ns5:TASA_MINIMA_DESEMBOLSO)  } </lin:tasaMinima>
                   
                   else   if (( string($ConsultarTcaTerminoOutput/ns5:DESCRIPCION_CORTA_TCA)='T501' ) )    
                   then <lin:tasaMinima xsi:nil="true"></lin:tasaMinima>
                   else ()
                   
                        }
                 
                   {
                        for $ConsultarTcaTerminoOutput in $reponseTermino/ns5:ConsultarTcaTerminoOutput
                       where fn:data($CosultarLineaCreditoOutput/ns1:ID)=fn:data($ConsultarTcaTerminoOutput/ns5:ID_LINEA_CREDITO)
                        return
                                                    
                   if ( ( string($ConsultarTcaTerminoOutput/ns5:DESCRIPCION_CORTA_TCA)='T501' )and  (string($ConsultarTcaTerminoOutput/ns5:TASA_MAXIMA_DESEMBOLSO)!=''))   then
                   
                <lin:tasaMaxima>{fn:data($ConsultarTcaTerminoOutput/ns5:TASA_MAXIMA_DESEMBOLSO)}</lin:tasaMaxima>
                   
                   else  if ( ( string($ConsultarTcaTerminoOutput/ns5:DESCRIPCION_CORTA_TCA)='T501' ))   then
                   <lin:tasaMaxima xsi:nil="true"></lin:tasaMaxima> else ()
                        }
                    
                    
                    
                    
                    {
                        for $ConsultarTcaTerminoOutput in $reponseTermino/ns5:ConsultarTcaTerminoOutput
                       where fn:data($CosultarLineaCreditoOutput/ns1:ID)=fn:data($ConsultarTcaTerminoOutput/ns5:ID_LINEA_CREDITO)
                        return
                                                    
                   if (( string($ConsultarTcaTerminoOutput/ns5:DESCRIPCION_CORTA_TCA)='T501' ) and (string($ConsultarTcaTerminoOutput/ns5:MONTO_MINIMO_DESEMBOLSO)!='') )   then
                   
             <lin:montoMinimo>{ fn:data($ConsultarTcaTerminoOutput/ns5:MONTO_MINIMO_DESEMBOLSO)}</lin:montoMinimo>
                   
                   else if (( string($ConsultarTcaTerminoOutput/ns5:DESCRIPCION_CORTA_TCA)='T501' )  )  then  <lin:montoMinimo xsi:nil="true"></lin:montoMinimo>
                   else ()
                   
                        }
                    
                    
                    
                    {
                        for $ConsultarTcaTerminoOutput in $reponseTermino/ns5:ConsultarTcaTerminoOutput
                       where fn:data($CosultarLineaCreditoOutput/ns1:ID)=fn:data($ConsultarTcaTerminoOutput/ns5:ID_LINEA_CREDITO)
                        return
                                                    
                   if (( string($ConsultarTcaTerminoOutput/ns5:DESCRIPCION_CORTA_TCA)='T501' ) and string($ConsultarTcaTerminoOutput/ns5:MONTO_MAXIMO_DESEMBOLSO)!='' ) then
                   
               <lin:montoMaximo> {fn:data($ConsultarTcaTerminoOutput/ns5:MONTO_MAXIMO_DESEMBOLSO) }</lin:montoMaximo>
                   
                   else   if (( string($ConsultarTcaTerminoOutput/ns5:DESCRIPCION_CORTA_TCA)='T501' ) ) then <lin:montoMaximo xsi:nil="true">  </lin:montoMaximo>
                   else ()
                   
                        }
                    <lin:MoverEntreMeses>{fn:data($CosultarLineaCreditoOutput/ns1:SE_PUEDE_MOVER_ENTRE_MESES)}</lin:MoverEntreMeses>
                    {
                        for $ConsultarTcaTerminoOutput in $reponseTermino/ns5:ConsultarTcaTerminoOutput
                        where fn:data($CosultarLineaCreditoOutput/ns1:ID)=fn:data($ConsultarTcaTerminoOutput/ns5:ID_LINEA_CREDITO)  
                        and fn:data($ConsultarTcaTerminoOutput/ns5:ID_TCA_TIPO_TERMINO_TCA)=1
						and (fn:data($ConsultarTcaTerminoOutput/ns5:ID_TCA_ESTADO_TCC)=7 or 
                                                 (
                                                   fn:data($ConsultarTcaTerminoOutput/ns5:ID_TCA_ESTADO_TCC)=15 and fn:string($ConsultarTcaTerminoOutput/ns5:DES_FECHA_INICIO)!= '')
                                                 )

                     
                        return 
                     
                        <lin:Termino>
                        
                          
                            <ter:idTermino>{fn:data($ConsultarTcaTerminoOutput/ns5:ID)}</ter:idTermino>
                            <ter:operacion>{fn:data($ConsultarTcaTerminoOutput/ns5:ID_OPERACION)}</ter:operacion>
                            <ter:nombre>{fn:data($ConsultarTcaTerminoOutput/ns5:NOMBRE)}</ter:nombre>
                            <ter:descripcion>{fn:data($ConsultarTcaTerminoOutput/ns5:DESCRIPCION)}</ter:descripcion>
                            <ter:tipoTermino>
                                <ter:idCatTermino>{fn:data($ConsultarTcaTerminoOutput/ns5:ID_TCA)}</ter:idCatTermino>
                                <ter:descripcion>{fn:data($ConsultarTcaTerminoOutput/ns5:DESCRIPCION_TCA)}</ter:descripcion>
                                
                                
                                <ter:descripcionCorta>{fn:data($ConsultarTcaTerminoOutput/ns5:DESCRIPCION_CORTA_TCA)}</ter:descripcionCorta>
                                <ter:idTipoTermino>{fn:data($ConsultarTcaTerminoOutput/ns5:ID_TCA_TIPO_TERMINO_TCA)}</ter:idTipoTermino>
                                <ter:esEditableEnFormalizacion>{if(fn:data($ConsultarTcaTerminoOutput/ns5:ES_EDITABLE_EN_FORMALIZACION)=1) then true() else false()}</ter:esEditableEnFormalizacion>
                                <ter:requiereValidarCOFI>{if(fn:data($ConsultarTcaTerminoOutput/ns5:REQUIERE_VALIDAR_COFI)=1) then true() else false()}</ter:requiereValidarCOFI>
                                <ter:sePuedeDispensar>{if(fn:data($ConsultarTcaTerminoOutput/ns5:SE_PUEDE_DISPENSAR)=1) then true() else false()}</ter:sePuedeDispensar>
                                <ter:esPlantilla>{if(fn:data($ConsultarTcaTerminoOutput/ns5:ES_PLANTILLA)=1) then true() else false()}</ter:esPlantilla>
                                <ter:requiereOGC>{if(fn:data($ConsultarTcaTerminoOutput/ns5:REQUIERE_OCG)=1) then true() else false()}</ter:requiereOGC>
                                <ter:idTerminoPrecarga>{fn:data($ConsultarTcaTerminoOutput/ns5:ID_TERMINO_PRECARGA)}</ter:idTerminoPrecarga>
                                <ter:fechaRegistro>{fn:data($ConsultarTcaTerminoOutput/ns5:FECHA_REGISTRO_TCA)}</ter:fechaRegistro>
                                <ter:estado>{if(fn:data($ConsultarTcaTerminoOutput/ns5:BAN_ESTATUS)=1) then true() else false()}</ter:estado>
                                <ter:codigoExterno>{fn:data($ConsultarTcaTerminoOutput/ns5:COD_EXTERNO)}</ter:codigoExterno>
                            </ter:tipoTermino>
                            <ter:tipoFechaInicio>
                                <cat:Id>{fn:data($ConsultarTcaTerminoOutput/ns5:ID_TCA_TIPO_FECHA_INICIO)}</cat:Id>
                                <cat:Descripcion>{fn:data($ConsultarTcaTerminoOutput/ns5:DES_FECHA_INICIO)}</cat:Descripcion>
                                <cat:DescripcionCorta></cat:DescripcionCorta>
                                <cat:estatus></cat:estatus>
                                <cat:codigoExterno></cat:codigoExterno>
                            </ter:tipoFechaInicio>
                            <ter:fechaInicio>{fn:data($ConsultarTcaTerminoOutput/ns5:FECHA_INICIO)}</ter:fechaInicio>
                            <ter:plazo>{fn:data($ConsultarTcaTerminoOutput/ns5:PLAZO)}</ter:plazo>
                            <ter:frecuenciaPlazo>
                                <cat:Id>{fn:data($ConsultarTcaTerminoOutput/ns5:ID_TCA_FRECUENCIA_PLAZO)}</cat:Id>
                                <cat:Descripcion>{fn:data($ConsultarTcaTerminoOutput/ns5:DESCRIPCION_FRE)}</cat:Descripcion>
                               
                            </ter:frecuenciaPlazo>
                            <ter:fechaVencimiento>{fn:data($ConsultarTcaTerminoOutput/ns5:FECHA_VENCIMIENTO)}</ter:fechaVencimiento>
                            <ter:moneda>
                                <cat:Id>{fn:data($ConsultarTcaTerminoOutput/ns5:ID_TCA_MONEDA)}</cat:Id>
                           
                            </ter:moneda>
                            <ter:monto>{fn:data($ConsultarTcaTerminoOutput/ns5:MONTO)}</ter:monto>
                            <ter:tasa>{fn:data($ConsultarTcaTerminoOutput/ns5:TASA)}</ter:tasa>
                            <ter:tipoTasa>
                                <cat:Id>{fn:data($ConsultarTcaTerminoOutput/ns5:ID_TCA_TIPO_TASA)}</cat:Id>
                                
                            </ter:tipoTasa>
                            <ter:fecha>{fn:data($ConsultarTcaTerminoOutput/ns5:FECHA)}</ter:fecha>
                            <ter:frecuenciaRevision>{fn:data($ConsultarTcaTerminoOutput/ns5:FRECUENCIA_REVISION)}</ter:frecuenciaRevision>
                            <ter:tipoFrecuenciaRevision>
                                <cat:Id>{fn:data($ConsultarTcaTerminoOutput/ns5:ID_TCA_FRECUENCIA_REVISION)}</cat:Id>
                                
                            </ter:tipoFrecuenciaRevision>
                            <ter:fechaInicioRevision>{fn:data($ConsultarTcaTerminoOutput/ns5:FECHA_INICIO_REVISION)}</ter:fechaInicioRevision>
                            <ter:frecuenciaPagoInteres>{fn:data($ConsultarTcaTerminoOutput/ns5:FRECUENCIA_PAGO_INTERES)}</ter:frecuenciaPagoInteres>
                            <ter:tipoFrecuenciaPagoInteres>
                                <cat:Id>{fn:data($ConsultarTcaTerminoOutput/ns5:ID_TCA_FRECUENCIA_PAGO_INTERES)}</cat:Id>
                                
                            </ter:tipoFrecuenciaPagoInteres>
                            <ter:fechaInicioPagoInteres>{fn:data($ConsultarTcaTerminoOutput/ns5:FECHA_INICIO_PAGO_INTERES)}</ter:fechaInicioPagoInteres>
                            <ter:frecuenciaAmortizacion>{fn:data($ConsultarTcaTerminoOutput/ns5:FRECUENCIA_AMORTIZACION)}</ter:frecuenciaAmortizacion>
                            <ter:tipoFrecuenciaAmortizacion>
                                <cat:Id>{fn:data($ConsultarTcaTerminoOutput/ns5:ID_TCA_FRECUENCIA_AMORTIZACION)}</cat:Id>
                                
                            </ter:tipoFrecuenciaAmortizacion>
                            <ter:mora>{fn:data($ConsultarTcaTerminoOutput/ns5:MORA)}</ter:mora>
                            <ter:porcentajeCobertura>{fn:data($ConsultarTcaTerminoOutput/ns5:PORCENTAJE_COBERTURA)}</ter:porcentajeCobertura>
                            <ter:seAplicanRecursosConcesion>{    if(string($ConsultarTcaTerminoOutput/ns5:SE_APLICAN_RECURSOS_CONCESION)='')then () else
                            
                            if(fn:data($ConsultarTcaTerminoOutput/ns5:SE_APLICAN_RECURSOS_CONCESION)=1) then true() else false()}</ter:seAplicanRecursosConcesion>
                            <ter:seAplicanRecursosExternos>{if(string($ConsultarTcaTerminoOutput/ns5:SE_APLICAN_RECURSOS_EXTERNOS)='')then () else
                            
                            if(fn:data($ConsultarTcaTerminoOutput/ns5:SE_APLICAN_RECURSOS_EXTERNOS)=1) then true() else false()}</ter:seAplicanRecursosExternos>
                            <ter:tipoContraparte>{fn:data($ConsultarTcaTerminoOutput/ns5:TIPO_CONTRAPARTE)}</ter:tipoContraparte>
                            <ter:montoMinimoDesembolso>{fn:data($ConsultarTcaTerminoOutput/ns5:MONTO_MINIMO_DESEMBOLSO)}</ter:montoMinimoDesembolso>
                            <ter:montoMaximoDesembolso>{fn:data($ConsultarTcaTerminoOutput/ns5:MONTO_MAXIMO_DESEMBOLSO)}</ter:montoMaximoDesembolso>
                            <ter:tasaMinimaDesembolso>{fn:data($ConsultarTcaTerminoOutput/ns5:TASA_MINIMA_DESEMBOLSO)}</ter:tasaMinimaDesembolso>
                            <ter:tasaMaximaDesembolso>{fn:data($ConsultarTcaTerminoOutput/ns5:TASA_MAXIMA_DESEMBOLSO)}</ter:tasaMaximaDesembolso>
                            <ter:estadoTCC>
                                <atr:id>{fn:data($ConsultarTcaTerminoOutput/ns5:ID_TCA_ESTADO_TCC)}</atr:id>
                            
                                
                            </ter:estadoTCC>
                            <ter:fechaRegistro>{fn:data($ConsultarTcaTerminoOutput/ns5:FECHA_REGISTRO)}</ter:fechaRegistro>
                            <ter:estado>{if(fn:data($ConsultarTcaTerminoOutput/ns5:TER_BAN_ESTATUS)=1) then true() else false()}</ter:estado>
                            <ter:terminoEnmendado>{fn:data($ConsultarTcaTerminoOutput/ns5:ID_TERMINO_ENMENDADO)}</ter:terminoEnmendado>
                            <ter:fechaEnmienda>{fn:data($ConsultarTcaTerminoOutput/ns5:FECHA_ENMIENDA)}</ter:fechaEnmienda>
                            </lin:Termino>
                    }
                    
                    {
                        for $ConsultarTcaComisionOutput in $reponseComision/ns2:ConsultarTcaComisionOutput
                        where fn:data($CosultarLineaCreditoOutput/ns1:ID)=fn:data($ConsultarTcaComisionOutput/ns2:ID_LINEA_CREDITO)
                       
                        return 
                     
                         
                        <lin:Comision>
                            <com:idComision>{fn:data($ConsultarTcaComisionOutput/ns2:ID)}</com:idComision>
                            <com:idOperacion>{fn:data($ConsultarTcaComisionOutput/ns2:ID_OPERACION)}</com:idOperacion>
                            <com:nombre>{fn:data($ConsultarTcaComisionOutput/ns2:NOMBRE)}</com:nombre>
                            <com:descripcion>{fn:data($ConsultarTcaComisionOutput/ns2:DESCRIPCION)}</com:descripcion>
                            <com:tipoComision>
                                <com:idCatComision>{fn:data($ConsultarTcaComisionOutput/ns2:ID_CAT)}</com:idCatComision>
                                <com:descripcion>{fn:data($ConsultarTcaComisionOutput/ns2:DESCRIPCION_CAT)}</com:descripcion>
                                <com:descripcionCorta>{fn:data($ConsultarTcaComisionOutput/ns2:DESCRIPCION_CORTA_CAT)}</com:descripcionCorta>
                                <com:idTipoComision>
                                    <cat:Id>{fn:data($ConsultarTcaComisionOutput/ns2:ID_TCA_TIPO_COMISION)}</cat:Id>
                                   
                                </com:idTipoComision>
                                <com:esEditableEnFormalizacion>{if(fn:data($ConsultarTcaComisionOutput/ns2:ES_EDITABLE_EN_FORMALIZACION)=1 )then true()
                                else false()
                                
                                }</com:esEditableEnFormalizacion>
                                <com:requiereValidarCOFI>{ if(fn:data($ConsultarTcaComisionOutput/ns2:REQUIERE_VALIDAR_COFI)=1)then true()
                                else false()
                                }</com:requiereValidarCOFI>
                                <com:sePuedeDispensar>{if(fn:data($ConsultarTcaComisionOutput/ns2:SE_PUEDE_DISPENSAR)=1) then true() else false()}</com:sePuedeDispensar>
                                <com:seRegistraEnFlexCube>{if(fn:data($ConsultarTcaComisionOutput/ns2:SE_REGISTRA_FLEXCUBE)=1) then true() else false ()}</com:seRegistraEnFlexCube>
                                <com:esPlantilla>{if(fn:data($ConsultarTcaComisionOutput/ns2:ES_PLANTILLA)=1) then true() else false()}</com:esPlantilla>
                                <com:idComisionPrecarga>{fn:data($ConsultarTcaComisionOutput/ns2:ID_COMISION_PRECARGA)}</com:idComisionPrecarga>

                            </com:tipoComision>
                            <com:montoComision>{fn:data($ConsultarTcaComisionOutput/ns2:MONTO_COMISION)}</com:montoComision>
                            <com:montoBase>
                                <com:idMontoBase>{fn:data($ConsultarTcaComisionOutput/ns2:ID_TCA_MONTO_BASE)}</com:idMontoBase>
                                <com:valorMontoBase>{fn:data($ConsultarTcaComisionOutput/ns2:MONTO_BASE)}</com:valorMontoBase>
                                <com:porcentajeMontoBase>{fn:data($ConsultarTcaComisionOutput/ns2:PORCENTAJE_SOBRE_MONTO_BASE)}</com:porcentajeMontoBase>
                            </com:montoBase>
                            <com:fechaValor>{fn:data($ConsultarTcaComisionOutput/ns2:FECHA_VALOR)}</com:fechaValor>
                            <com:fechaVencimiento>{fn:data($ConsultarTcaComisionOutput/ns2:FECHA_VENCIMIENTO)}</com:fechaVencimiento>
                            <com:fechaInicioCapital>{fn:data($ConsultarTcaComisionOutput/ns2:FECHA_INICIO_CAPITAL)}</com:fechaInicioCapital>
                            <com:fechaInicioComision>{fn:data($ConsultarTcaComisionOutput/ns2:FECHA_INICIO_COMISION)}</com:fechaInicioComision>
                            <com:tipoFrecuencia>
                                <cat:Id>{fn:data($ConsultarTcaComisionOutput/ns2:ID_TCA_TIPO_FRECUENCIA)}</cat:Id>
                                <cat:Descripcion>{fn:data($ConsultarTcaComisionOutput/ns2:DESCRIPCION_FRE)}</cat:Descripcion>
                                
                            </com:tipoFrecuencia>
                            <com:frecuenciaPago>{fn:data($ConsultarTcaComisionOutput/ns2:FRECUENCIA_PAGO)}</com:frecuenciaPago>
                            <com:comisionCompartida>{ if(string($ConsultarTcaComisionOutput/ns2:COMISION_COMPARTIDA)='') then ()else
                            if( fn:data($ConsultarTcaComisionOutput/ns2:COMISION_COMPARTIDA)=1) then true() else false()
                            
                            }</com:comisionCompartida>
                            <com:tipoTasa>
                                <cat:Id>{fn:data($ConsultarTcaComisionOutput/ns2:ID_TCA_TIPO_TASA)}</cat:Id>
                               
                            </com:tipoTasa>
                            <com:baseCalculo>
                                <cat:Id>{fn:data($ConsultarTcaComisionOutput/ns2:ID_TCA_BASE_CALCULO)}</cat:Id>
                                <cat:Descripcion>{fn:data($ConsultarTcaComisionOutput/ns2:DES_BASE_CALCULO)}</cat:Descripcion>
                                
                            </com:baseCalculo>
                            <com:estadoTCC>
                                <atr:id>{fn:data($ConsultarTcaComisionOutput/ns2:ID_TCA_ESTADO_TCC)}</atr:id>
                      
                               
                            </com:estadoTCC>
                            <com:fechaRegistro>{fn:data($ConsultarTcaComisionOutput/ns2:FECHA_REGISTRO)}</com:fechaRegistro>
                            <com:estado>{if(fn:data($ConsultarTcaComisionOutput/ns2:BAN_ESTATUS)=1)then true() else
                            false()
                            
                            }</com:estado>
                            </lin:Comision>
                    }
                    
                    </con:LineaCredito>
            }
         
           </ns6:clienteContrato>
        
    </ns6:ConsultarLineaCreditoResponse>
};

local:func($responselineacredito, $reponseComision, $responseCuentas, $responseContrato, $reponseTermino, $ConsultarAprobacionByOperacionOutputCollection, $index)