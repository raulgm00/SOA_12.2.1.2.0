xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace cli14="http://www.bcie.org/FLEXCUBE14/ClienteMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Flexcube14/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace lin14="http://www.bcie.org/FLEXCUBE14/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Flexcube14/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace com = "http://www.bcie.org/FLEXCUBE14/ComisionBO";

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace apr = "http://www.bcie.org/AprobacionBO";

declare namespace functx = "http://www.functx.com";

declare variable $crearCommitmentFLEXCUBE_InputVariable.request as element() (:: schema-element(lin:CrearCommitmentFLEXCUBERequest) ::) external;

declare function local:funcCrearcommitmentflexcube_to_crearcommitmentflexcube14($crearCommitmentFLEXCUBE_InputVariable.request as element() (:: schema-element(lin:CrearCommitmentFLEXCUBERequest) ::)) 
                                                                                as element() (:: schema-element(lin14:CrearCommitmentRequest) ::) {
    let $tasaMinima := $crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:LineaCredito/lin1:Termino[1]/ter:tasaMinimaDesembolso
    let $tasaMaxima := $crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:LineaCredito/lin1:Termino[1]/ter:tasaMaximaDesembolso
    return
    <lin14:CrearCommitmentRequest>
        <lin14:Commitment>
            <lin14:Codigo_Intervencion>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Operacion/ope:idOperacion)}</lin14:Codigo_Intervencion>
            <lin14:Nombre_Negocio>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Operacion/ope:nombre)}</lin14:Nombre_Negocio>
            <lin14:Codigo_Cliente>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Operacion/ope:cliente/cli:idFacturador)}</lin14:Codigo_Cliente>
            <lin14:Sector_Institucional>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Operacion/ope:cliente/cli:sector/cat:Id)}</lin14:Sector_Institucional>
            <lin14:Actividad_Economica>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Operacion/ope:actividadEconomicaAsociada/cat:codigoExterno)}</lin14:Actividad_Economica>
            <lin14:Ejecutivo>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Operacion/ope:responsable)}</lin14:Ejecutivo>
            <lin14:Pais>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Operacion/ope:cliente/cli:pais/cat:codigoExterno)}</lin14:Pais>
            <lin14:Eje_Estrategico>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Operacion/ope:ejeEstrategico/cat:codigoExterno)}</lin14:Eje_Estrategico>
            <lin14:Area_Focalizacion>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Operacion/ope:areaFocalizacion/cat:codigoExterno)}</lin14:Area_Focalizacion>
            <lin14:Objetivo_Estrategico></lin14:Objetivo_Estrategico>
            <lin14:Tipo_Recurso>ND</lin14:Tipo_Recurso>
            <lin14:Tipo_Financiamiento>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Operacion/ope:cliente/cli:tipoPersona/cat:codigoExterno)}</lin14:Tipo_Financiamiento>
            <lin14:Numero_Resolucion>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Aprobacion/apr:numeroResolucion)}</lin14:Numero_Resolucion>
            <lin14:Fecha_Aprobacion>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Aprobacion/apr:fechaAprobacion)}</lin14:Fecha_Aprobacion>
            <lin14:Porcentaje_Cobertura>1</lin14:Porcentaje_Cobertura>
            <lin14:Numero_Linea_Credito>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:LineaCredito/lin1:NumeroLineaCredito)}</lin14:Numero_Linea_Credito>
            <lin14:Linea_Financiera>ND</lin14:Linea_Financiera>
            <lin14:Fondo>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:LineaCredito/lin1:Fondo)}</lin14:Fondo>
            <lin14:Moneda>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:LineaCredito/lin1:Moneda/cat:codigoExterno)}</lin14:Moneda>
            <lin14:Monto_Aprobado>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:montoAprobacion)}</lin14:Monto_Aprobado>
            <lin14:Fecha_Valor>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:LineaCredito/lin1:FechaValor)}</lin14:Fecha_Valor>
            <lin14:Monto_Escriturado>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:LineaCredito/lin1:MontoLinea)}</lin14:Monto_Escriturado>
            <lin14:Fecha_Escrituracion>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:fechaFirma)}</lin14:Fecha_Escrituracion>
            <lin14:Monto_Max_Desem>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:LineaCredito/lin1:Termino[1]/ter:montoMaximoDesembolso)}</lin14:Monto_Max_Desem>
            <lin14:Monto_Min_Desem>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:LineaCredito/lin1:Termino[1]/ter:montoMinimoDesembolso)}</lin14:Monto_Min_Desem>
            <lin14:Monto_Catalizar></lin14:Monto_Catalizar>
            <lin14:Tipo_Catalizar></lin14:Tipo_Catalizar>
            <lin14:Recursos_Ordinarios>
            {
                if($crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:LineaCredito/lin1:Termino/ter:seAplicanRecursosExternos/text()='1')
                then 'N'
                else 'S'
            }
            </lin14:Recursos_Ordinarios>
            <lin14:Codigo_Asignacion>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:LineaCredito/lin1:CodigoAsignacion)}</lin14:Codigo_Asignacion>
            <lin14:Plazo_Linea_Credito>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:LineaCredito/lin1:Termino[1]/ter:plazo)}</lin14:Plazo_Linea_Credito>
            <lin14:Tipo_Plazo_Linea>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:LineaCredito/lin1:Termino[1]/ter:frecuenciaPlazo/cat:codigoExterno)}</lin14:Tipo_Plazo_Linea>
            <lin14:Periodo_Gracia>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:LineaCredito/lin1:Termino[2]/ter:plazo)}</lin14:Periodo_Gracia>
            <lin14:Tipo_Plazo_Gracia>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:LineaCredito/lin1:Termino[2]/ter:frecuenciaPlazo/cat:codigoExterno)}</lin14:Tipo_Plazo_Gracia>
            <lin14:Destino>ND</lin14:Destino>
            <lin14:Numero_Cuenta_Cliente/>
            <lin14:Producto_Financiero>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:LineaCredito/lin1:Flexcube/lin1:idProductoFlexcube)}</lin14:Producto_Financiero>
            <!--<lin14:Producto_Financiero>CL02</lin14:Producto_Financiero>-->
            <lin14:Revolvencia>
            {
                if($crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:LineaCredito/lin1:EsRevolvente/text()='true')
                then 'S'
                else 'N'
            }
            </lin14:Revolvencia>
            <lin14:Fecha_Maxima_Escriturar>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:fechaVigencia)}</lin14:Fecha_Maxima_Escriturar>
            <lin14:Fecha_Minima_Iniciar>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:LineaCredito/lin1:FechaRegistro)}</lin14:Fecha_Minima_Iniciar>
            <lin14:Fecha_Maxima_Totalidad>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:LineaCredito/lin1:FechaVencimiento)}</lin14:Fecha_Maxima_Totalidad>
            <lin14:Tiene_Cond_Especiales>
            {
                if($crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:LineaCredito/lin1:CondicionEspecial/text()='1')
                then 'S'
                else 'N'
            }
            </lin14:Tiene_Cond_Especiales>
            <lin14:Desc_Cond_Especiales>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:LineaCredito/lin1:descCondEspecial)}</lin14:Desc_Cond_Especiales>
            <lin14:Considerar_Feriados>{fn:data($crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:LineaCredito/lin1:TratamientoDiasFeriados)}</lin14:Considerar_Feriados>
            <lin14:Mover_Entre_Meses>
            {
                if($crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:LineaCredito/lin1:MoverEntreMeses/text()='1')
                then 'Y'
                else 'N'
            }
            </lin14:Mover_Entre_Meses>
            <lin14:ListaComisiones>
            {
                if(count($crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:LineaCredito/lin1:Comision)>0)
                then for $comision in $crearCommitmentFLEXCUBE_InputVariable.request/lin:Contrato/con:LineaCredito/lin1:Comision
                     return 
                     <com:comision>
                        <com:Tipo_Tasa>X</com:Tipo_Tasa> <!-- Se encuentra que por defecto a flex7 se enviaba en duro X -->
                        <com:Codigo_Tasa></com:Codigo_Tasa>
                        <com:Tipo_Comision>{fn:data($comision/com1:tipoComision/com1:idTipoComision/cat:Descripcion)}</com:Tipo_Comision>
                        <!--<com:Tipo_Comision>INTCOM</com:Tipo_Comision>-->
                        <com:Base_Calculo>{fn:data($comision/com1:baseCalculo/cat:codigoExterno)}</com:Base_Calculo>
                        <com:Fecha_Inicio>{fn:data($comision/com1:fechaInicioCapital)}</com:Fecha_Inicio>
                        <com:Frecuencia>{fn:data($comision/com1:frecuenciaPago)}</com:Frecuencia>
                        <com:Tipo_Frecuencia>{fn:data($comision/com1:tipoFrecuencia/cat:codigoExterno)}</com:Tipo_Frecuencia>
                        <com:Spread></com:Spread>
                        <com:Spread_Mora></com:Spread_Mora>
                        <com:Valor_Comision>{fn:data($comision/com1:montoBase/com1:porcentajeMontoBase)}</com:Valor_Comision>
                        <com:Monto>{fn:data($comision/com1:montoComision)}</com:Monto>
                        <com:Tasa_Minima>{fn:data($tasaMinima)}</com:Tasa_Minima>
                        <com:Tasa_Maxima>{fn:data($tasaMaxima)}</com:Tasa_Maxima>
                        <com:Fecha_Inicio_Revision></com:Fecha_Inicio_Revision>
                        <com:Frecuencia_Revision></com:Frecuencia_Revision>
                        <com:Tipo_Frecuencia_Revision></com:Tipo_Frecuencia_Revision>
                     </com:comision>
                else
                     <com:comision>
                        <com:Tipo_Tasa>X</com:Tipo_Tasa>
                        <com:Codigo_Tasa></com:Codigo_Tasa>
                        <com:Tipo_Comision>INTCOM</com:Tipo_Comision>
                        <com:Base_Calculo>3</com:Base_Calculo>
                        <com:Fecha_Inicio>
                        {
                            let $fechaActual := fn:current-date()
                            let $anio := fn:year-from-date($fechaActual)
                            let $mes := functx:pad-integer-to-length(fn:month-from-date($fechaActual), 2)
                            let $dia := functx:pad-integer-to-length(fn:day-from-date($fechaActual), 2)
                            return fn:concat($anio,"-",$mes,"-",$dia)
                        }
                        </com:Fecha_Inicio>
                        <com:Frecuencia>6</com:Frecuencia>
                        <com:Tipo_Frecuencia>M</com:Tipo_Frecuencia>
                        <com:Spread></com:Spread>
                        <com:Spread_Mora></com:Spread_Mora>
                        <com:Valor_Comision>0</com:Valor_Comision>
                        <com:Monto></com:Monto>
                        <com:Tasa_Minima></com:Tasa_Minima>
                        <com:Tasa_Maxima></com:Tasa_Maxima>
                        <com:Fecha_Inicio_Revision></com:Fecha_Inicio_Revision>
                        <com:Frecuencia_Revision></com:Frecuencia_Revision>
                        <com:Tipo_Frecuencia_Revision></com:Tipo_Frecuencia_Revision>
                     </com:comision>
            }
            </lin14:ListaComisiones>
        </lin14:Commitment>
        <lin14:Usuario>SYSTEM</lin14:Usuario>
    </lin14:CrearCommitmentRequest>
};

declare function functx:repeat-string
  ( $stringToRepeat as xs:string? ,
    $count as xs:integer )  as xs:string {

   fn:string-join((for $i in 1 to $count return $stringToRepeat),
                        '')
 } ;

declare function functx:pad-integer-to-length
  ( $integerToPad as xs:anyAtomicType? ,
    $length as xs:integer )  as xs:string {

   if ($length < string-length(string($integerToPad)))
   then fn:error(xs:QName('functx:Integer_Longer_Than_Length'))
   else concat
         (functx:repeat-string(
            '0',$length - fn:string-length(fn:string($integerToPad))),
          string($integerToPad))
 } ;

local:funcCrearcommitmentflexcube_to_crearcommitmentflexcube14($crearCommitmentFLEXCUBE_InputVariable.request)

