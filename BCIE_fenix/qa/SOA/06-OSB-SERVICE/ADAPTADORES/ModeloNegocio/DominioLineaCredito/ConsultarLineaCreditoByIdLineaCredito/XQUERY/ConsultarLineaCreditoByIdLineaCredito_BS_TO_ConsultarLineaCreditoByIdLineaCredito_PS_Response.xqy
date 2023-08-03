xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarLineaCreditoByIdLineaCredito";
(:: import schema at "../XSD/ConsultarLineaCreditoByIdLineaCredito.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace cat1 = "http://www.bcie.org/CatalogoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare namespace apr = "http://www.bcie.org/AprobacionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarLineaCreditoByIdLineaCreditoOutputCollection as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdLineaCreditoOutputCollection) ::) external;
declare variable $responseTerminoTemp as element() (:: schema-element(ns2:ConsultarLineaCreditoByIdLineaCreditoResponse) ::) external;

declare function local:func($ConsultarLineaCreditoByIdLineaCreditoOutputCollection as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdLineaCreditoOutputCollection) ::), 
                            $responseTerminoTemp as element() (:: schema-element(ns2:ConsultarLineaCreditoByIdLineaCreditoResponse) ::)) 
                            as element() (:: schema-element(ns2:ConsultarLineaCreditoByIdLineaCreditoResponse) ::) {
    <ns2:ConsultarLineaCreditoByIdLineaCreditoResponse>
{
   for $idOperacion in distinct-values($ConsultarLineaCreditoByIdLineaCreditoOutputCollection/ns1:ConsultarLineaCreditoByIdLineaCreditoOutput/ns1:ID_OPERACION)
      let $operacion:= $ConsultarLineaCreditoByIdLineaCreditoOutputCollection/ns1:ConsultarLineaCreditoByIdLineaCreditoOutput[ns1:ID_OPERACION = $idOperacion ][1]
      return
        <ns2:Operacion>
            <ope:idOperacion>{fn:data($operacion/ns1:ID_OPERACION)}</ope:idOperacion>
            <ope:responsable>{fn:data($operacion/ns1:EJECUTIVO)}</ope:responsable>
            <ope:nombre>{fn:data($operacion/ns1:NOMBRE)}</ope:nombre>
            <ope:cliente>
                <cli:idCliente>{fn:data($operacion/ns1:ID_CLIENTE)}</cli:idCliente>
                <cli:idFacturador>{fn:data($operacion/ns1:ID_FLEXCUBE)}</cli:idFacturador>
                <cli:razonSocial>{fn:data($operacion/ns1:RAZON_SOCIAL)}</cli:razonSocial>
                <cli:tipoPersona>
                    <cat1:Id>{fn:data($operacion/ns1:TIPO_PERSONA)}</cat1:Id>
                </cli:tipoPersona>
                <cli:sector>
                    <cat1:Id>{fn:data($operacion/ns1:SECTOR_INSTITUCIONAL)}</cat1:Id>
                </cli:sector>
                <cli:pais>
                    <cat1:codigoExterno>{fn:data($operacion/ns1:COD_EXTERNO_PAIS)}</cat1:codigoExterno>
                </cli:pais>
                <cli:responsableCliente>{fn:data($operacion/ns1:RESPONSABLE_CLIENTE)}</cli:responsableCliente>
            </ope:cliente>
            <ope:producto>
                <pro:idProducto>{fn:data($operacion/ns1:ID_PRODUCTO )}</pro:idProducto>
               
            </ope:producto>
            <ope:actividadEconomica>
                <cat1:codigoExterno>{fn:data($operacion/ns1:COD_EXTERNO_ACTIVIDAD)}</cat1:codigoExterno>
            </ope:actividadEconomica>
            <ope:actividadEconomicaAsociada>
                <cat1:codigoExterno>{fn:data($operacion/ns1:COD_EXTERNO_ACT_ASOC)}</cat1:codigoExterno>
            </ope:actividadEconomicaAsociada>
            <ope:areaFocalizacion>
                <cat1:codigoExterno>{fn:data($operacion/ns1:COD_EXTERNO_AREA)}</cat1:codigoExterno>
            </ope:areaFocalizacion>
            <ope:ejeEstrategico>
                <cat1:codigoExterno>{fn:data($operacion/ns1:COD_EXTERNO_EJE)}</cat1:codigoExterno>
            </ope:ejeEstrategico>
            {for $idContrato in distinct-values($ConsultarLineaCreditoByIdLineaCreditoOutputCollection/ns1:ConsultarLineaCreditoByIdLineaCreditoOutput/ns1:ID_CONTRATO)
            let $contrato:= $ConsultarLineaCreditoByIdLineaCreditoOutputCollection/ns1:ConsultarLineaCreditoByIdLineaCreditoOutput[ns1:ID_CONTRATO = $idContrato ][1]
            where $contrato/ns1:ID_OPERACION = $idOperacion
            return
            <ope:contrato>
                <con:idContrato>{fn:data($contrato/ns1:ID_CONTRATO)}</con:idContrato>
                <con:idOperacion>{fn:data($contrato/ns1:ID_OPERACION)}</con:idOperacion>
                <con:fechaFirma>{fn:substring-before(string($contrato/ns1:FECHA_ESCRITURACION),'T')}</con:fechaFirma>
                <con:fechaVigencia>{fn:substring-before(string($responseTerminoTemp/ns2:Contrato/con:LineaCredito[lin:idLineaCredito = $contrato/ns1:ID_LINEA_CREDITO]/lin:Termino[ter:tipoTermino/ter:descripcionCorta ='T101']/ter:fechaVencimiento),'T')}</con:fechaVigencia>
                <con:MontoEscriturado>{fn:data($contrato/ns1:MONTO_ESCRITURADO)}</con:MontoEscriturado>
                <con:cuentaCliente>
                    <con:cuentaCliente>{fn:data($contrato/ns1:CUST_AC_NO)}</con:cuentaCliente>
                </con:cuentaCliente>
                {for $idLineaCredito in distinct-values($ConsultarLineaCreditoByIdLineaCreditoOutputCollection/ns1:ConsultarLineaCreditoByIdLineaCreditoOutput/ns1:ID_LINEA_CREDITO)
                let $lineaCredito := $ConsultarLineaCreditoByIdLineaCreditoOutputCollection/ns1:ConsultarLineaCreditoByIdLineaCreditoOutput[ns1:ID_LINEA_CREDITO = $idLineaCredito ][1]
                let $montoAmpliacion := (if($lineaCredito/ns1:MONTO_AMPLIACION/text() != "")then data($lineaCredito/ns1:MONTO_AMPLIACION) else 0)
                where $lineaCredito/ns1:ID_CONTRATO = $idContrato
                return
                <con:LineaCredito>
                    <lin:idLineaCredito>{fn:data($lineaCredito/ns1:ID_LINEA_CREDITO)}</lin:idLineaCredito>
                    <lin:idContrato>{fn:data($lineaCredito/ns1:ID_CONTRATO)}</lin:idContrato>
                    <lin:NumeroLineaCredito>{fn:data($lineaCredito/ns1:NUMERO_LINEA_CREDITO)}</lin:NumeroLineaCredito>
                    <lin:Flexcube>
                        <lin:id>{fn:data($lineaCredito/ns1:ID_FLEXCUBE_LINEA)}</lin:id>
                        <lin:idProductoFlexcube>{fn:data($lineaCredito/ns1:PRODUCTO_FINANCIERO)}</lin:idProductoFlexcube>
                        <lin:idFlexcubePasivo></lin:idFlexcubePasivo>
                    </lin:Flexcube>
                    <lin:Fondo>{fn:data($lineaCredito/ns1:FONDO)}</lin:Fondo>
                    <lin:MontoLinea>{fn:data($lineaCredito/ns1:MONTO_LINEA)}</lin:MontoLinea>
                    <lin:Moneda>
                        <cat:Id>{fn:data($lineaCredito/ns1:ID_TCA_TIPO_MONEDA)}</cat:Id>
                        <cat:Descripcion>{fn:data($lineaCredito/ns1:DESCRIPCION_MONEDA)}</cat:Descripcion>
                        <cat:DescripcionCorta>{fn:data($lineaCredito/ns1:DESCRIPCION_CORTA_MONEDA)}</cat:DescripcionCorta>
                        <cat:codigoExterno>{fn:data($lineaCredito/ns1:COD_EXTERNO_MONEDA)}</cat:codigoExterno>
                    </lin:Moneda>
                    {
                      if ($lineaCredito/ns1:REVOLVENTE/text()='1') 
                      then
                        <lin:EsRevolvente>true</lin:EsRevolvente>
                      else
                        <lin:EsRevolvente>false</lin:EsRevolvente>
                    }
                    <lin:TratamientoDiasFeriados>{fn:data($lineaCredito/ns1:TRATAMIENTO_DIAS_FERIADOS)}</lin:TratamientoDiasFeriados>
                    <lin:FechaValor>{fn:substring-before(string($lineaCredito/ns1:FECHA_VALOR),'T')}</lin:FechaValor>
                    <lin:FechaVencimiento>{fn:substring-before(string($responseTerminoTemp/ns2:Contrato/con:LineaCredito[lin:idLineaCredito = $idLineaCredito]/lin:Termino[ter:tipoTermino/ter:descripcionCorta ='T103'][1]/ter:fechaVencimiento),'T')}</lin:FechaVencimiento>
                    <lin:CodigoAsignacion>{fn:data($lineaCredito/ns1:CODIGO_ASIGNACION)}</lin:CodigoAsignacion>
                    <lin:CondicionEspecial>{fn:data($lineaCredito/ns1:TIENE_CONDICIONES_ESPECIALES)}</lin:CondicionEspecial>
                    <lin:FechaRegistro>{fn:substring-before(string($responseTerminoTemp/ns2:Contrato/con:LineaCredito[lin:idLineaCredito = $idLineaCredito]/lin:Termino[ter:tipoTermino/ter:descripcionCorta ='T102'][1]/ter:fechaVencimiento),'T')}</lin:FechaRegistro>
                    <lin:descCondEspecial>{fn:data($lineaCredito/ns1:DESCRIPCION_COND_ESPECIAL)}</lin:descCondEspecial>
                    <lin:Monto>
                        <com:tipo>
                            <cat1:DescripcionCorta>MONTO_AMPLIACION</cat1:DescripcionCorta>
                        </com:tipo>
                        <com:importe>{$montoAmpliacion}</com:importe>
                    </lin:Monto>
                    <lin:MoverEntreMeses>{fn:data($lineaCredito/ns1:SE_PUEDE_MOVER_ENTRE_MESES)}</lin:MoverEntreMeses>
                    <lin:Termino>
                        <ter:plazo>{fn:data($responseTerminoTemp/ns2:Contrato/con:LineaCredito[lin:idLineaCredito = $idLineaCredito]/lin:Termino[ter:tipoTermino/ter:descripcionCorta ='T106']/ter:plazo)}</ter:plazo>
                        <ter:frecuenciaPlazo>
                            <cat:codigoExterno>{fn:data($responseTerminoTemp/ns2:Contrato/con:LineaCredito[lin:idLineaCredito = $idLineaCredito]/lin:Termino[ter:tipoTermino/ter:descripcionCorta ='T106']/ter:frecuenciaPlazo/cat:codigoExterno)}</cat:codigoExterno>
                        </ter:frecuenciaPlazo>
                        <ter:seAplicanRecursosExternos>{fn:data($responseTerminoTemp/ns2:Contrato/con:LineaCredito[lin:idLineaCredito = $idLineaCredito]/lin:Termino[ter:tipoTermino/ter:descripcionCorta ='T305']/ter:seAplicanRecursosExternos)}</ter:seAplicanRecursosExternos>
                        <ter:montoMinimoDesembolso>{fn:data($responseTerminoTemp/ns2:Contrato/con:LineaCredito[lin:idLineaCredito = $idLineaCredito]/lin:Termino[ter:tipoTermino/ter:descripcionCorta ='T501']/ter:montoMinimoDesembolso)}</ter:montoMinimoDesembolso>
                        <ter:montoMaximoDesembolso>{fn:data($responseTerminoTemp/ns2:Contrato/con:LineaCredito[lin:idLineaCredito = $idLineaCredito]/lin:Termino[ter:tipoTermino/ter:descripcionCorta ='T501']/ter:montoMaximoDesembolso)}</ter:montoMaximoDesembolso>
                        <ter:tasaMinimaDesembolso>{fn:data($responseTerminoTemp/ns2:Contrato/con:LineaCredito[lin:idLineaCredito = $idLineaCredito]/lin:Termino[ter:tipoTermino/ter:descripcionCorta ='T501']/ter:tasaMinimaDesembolso)}</ter:tasaMinimaDesembolso>
                        <ter:tasaMaximaDesembolso>{fn:data($responseTerminoTemp/ns2:Contrato/con:LineaCredito[lin:idLineaCredito = $idLineaCredito]/lin:Termino[ter:tipoTermino/ter:descripcionCorta ='T501']/ter:tasaMaximaDesembolso)}</ter:tasaMaximaDesembolso>
                    </lin:Termino>
                    <lin:Termino>
                        <ter:plazo>{fn:data($responseTerminoTemp/ns2:Contrato/con:LineaCredito[lin:idLineaCredito = $idLineaCredito]/lin:Termino[ter:tipoTermino/ter:descripcionCorta ='T108']/ter:plazo)}</ter:plazo>
                        <ter:frecuenciaPlazo>
                            <cat:codigoExterno>{fn:data($responseTerminoTemp/ns2:Contrato/con:LineaCredito[lin:idLineaCredito = $idLineaCredito]/lin:Termino[ter:tipoTermino/ter:descripcionCorta ='T108']/ter:frecuenciaPlazo/cat:codigoExterno)}</cat:codigoExterno>
                        </ter:frecuenciaPlazo>
                        <ter:seAplicanRecursosExternos>{fn:data($responseTerminoTemp/ns2:Contrato/con:LineaCredito[lin:idLineaCredito = $idLineaCredito]/lin:Termino[ter:tipoTermino/ter:descripcionCorta ='T501']/ter:seAplicanRecursosExternos)}</ter:seAplicanRecursosExternos>
                        <ter:montoMinimoDesembolso>{fn:data($responseTerminoTemp/ns2:Contrato/con:LineaCredito[lin:idLineaCredito = $idLineaCredito]/lin:Termino[ter:tipoTermino/ter:descripcionCorta ='T501']/ter:montoMinimoDesembolso)}</ter:montoMinimoDesembolso>
                        <ter:montoMaximoDesembolso>{fn:data($responseTerminoTemp/ns2:Contrato/con:LineaCredito[lin:idLineaCredito = $idLineaCredito]/lin:Termino[ter:tipoTermino/ter:descripcionCorta ='T501']/ter:montoMaximoDesembolso)}</ter:montoMaximoDesembolso>
                        <ter:tasaMinimaDesembolso>{fn:data($responseTerminoTemp/ns2:Contrato/con:LineaCredito[lin:idLineaCredito = $idLineaCredito]/lin:Termino[ter:tipoTermino/ter:descripcionCorta ='T501']/ter:tasaMinimaDesembolso)}</ter:tasaMinimaDesembolso>
                        <ter:tasaMaximaDesembolso>{fn:data($responseTerminoTemp/ns2:Contrato/con:LineaCredito[lin:idLineaCredito = $idLineaCredito]/lin:Termino[ter:tipoTermino/ter:descripcionCorta ='T501']/ter:tasaMaximaDesembolso)}</ter:tasaMaximaDesembolso>
                    </lin:Termino>
                    <lin:Termino>
                        <ter:tipoTermino>
                            <ter:descripcionCorta>{fn:data($responseTerminoTemp/ns2:Contrato/con:LineaCredito[lin:idLineaCredito = $idLineaCredito]/lin:Termino/ter:tipoTermino[ter:descripcionCorta ='T702']/ter:descripcionCorta)}</ter:descripcionCorta>
                        </ter:tipoTermino>
                        <ter:requiereFondoPresupuestario>{fn:data($responseTerminoTemp/ns2:Contrato/con:LineaCredito[lin:idLineaCredito = $idLineaCredito]/lin:Termino[ter:tipoTermino/ter:descripcionCorta ='T702']/ter:requiereFondoPresupuestario)}</ter:requiereFondoPresupuestario>
                    </lin:Termino>
                    {
                    for $idDesembolso in distinct-values($ConsultarLineaCreditoByIdLineaCreditoOutputCollection/ns1:ConsultarLineaCreditoByIdLineaCreditoOutput/ns1:ID_DESEMBOLSO)
                    let $desembolso:= $ConsultarLineaCreditoByIdLineaCreditoOutputCollection/ns1:ConsultarLineaCreditoByIdLineaCreditoOutput[ns1:ID_DESEMBOLSO = $idDesembolso ][1]
                    where $desembolso/ns1:ID_LINEA_CREDITO = $idLineaCredito
                    return
                    <lin:ContratoDesembolso>
                        <des:idDesembolso>{fn:data($desembolso/ns1:ID_DESEMBOLSO)}</des:idDesembolso>
                        <des:estado>
                            <cat:Id>{fn:data($desembolso/ns1:ESTADO_DESEMBOLSO)}</cat:Id>
                        </des:estado>
                        <des:fechaEfectiva>{fn:data($desembolso/ns1:FECHA_EFECTIVA)}</des:fechaEfectiva>
                    </lin:ContratoDesembolso>
                    } 
                </con:LineaCredito>
                }
            </ope:contrato>
            }
        </ns2:Operacion>
    }
    <ns2:Aprobacion>
        <apr:fechaAprobacion>{fn:substring-before(string($ConsultarLineaCreditoByIdLineaCreditoOutputCollection/ns1:ConsultarLineaCreditoByIdLineaCreditoOutput[1]/ns1:FECHA_APROBACION),'T') [1]}</apr:fechaAprobacion>
        <apr:numeroResolucion>{fn:data($ConsultarLineaCreditoByIdLineaCreditoOutputCollection/ns1:ConsultarLineaCreditoByIdLineaCreditoOutput[1]/ns1:NUMERO_RESOLUCION)[1]}</apr:numeroResolucion>
    </ns2:Aprobacion>
    <ns2:montoAprobacion>{fn:data($ConsultarLineaCreditoByIdLineaCreditoOutputCollection/ns1:ConsultarLineaCreditoByIdLineaCreditoOutput[1]/ns1:MONTO_APROBACION[1])}</ns2:montoAprobacion>
     {if (count($ConsultarLineaCreditoByIdLineaCreditoOutputCollection/ns1:ConsultarLineaCreditoByIdLineaCreditoOutput)>0)then
            <ns2:Resultado>
                  <res:result>OK</res:result>
                  <res:message>Consulta Exitosa</res:message>              
            </ns2:Resultado>
     else(
            <ns2:Resultado>
                  <res:result>ERROR</res:result>
                  <res:message>Consulta sin Resultado</res:message>
            </ns2:Resultado>)
    }
       </ns2:ConsultarLineaCreditoByIdLineaCreditoResponse>
};

local:func($ConsultarLineaCreditoByIdLineaCreditoOutputCollection, $responseTerminoTemp)
