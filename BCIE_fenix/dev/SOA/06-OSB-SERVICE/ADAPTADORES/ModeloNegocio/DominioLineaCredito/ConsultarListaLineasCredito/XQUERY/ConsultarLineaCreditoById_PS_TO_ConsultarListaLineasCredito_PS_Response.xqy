xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace apr = "http://www.bcie.org/AprobacionBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare variable $ConsultarLineaCreditoByIdLineaCreditoResponse as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdLineaCreditoResponse) ::) external;

declare function local:func($ConsultarLineaCreditoByIdLineaCreditoResponse as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdLineaCreditoResponse) ::)) as element() (:: schema-element(ns1:ConsultarListaLineasCreditoResponse) ::) {
    <ns1:ConsultarListaLineasCreditoResponse>
        {for $linea at $pos in (1 to fn:count($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion))
     
          return
        <ns1:LineaCredito>
            <ns1:Operacion>
                {
                    if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion[$pos]/ope:idOperacion)
                    then <ope:idOperacion>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion[$pos]/ope:idOperacion)}</ope:idOperacion>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion[$pos]/ope:responsable)
                    then <ope:responsable>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion[$pos]/ope:responsable)}</ope:responsable>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion[$pos]/ope:nombre)
                    then <ope:nombre>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion[$pos]/ope:nombre)}</ope:nombre>
                    else ()
                }
                <ope:cliente>
                    <cli:idCliente>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion[$pos]/ope:cliente/cli:idCliente)}</cli:idCliente>
                    {
                        if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion[$pos]/ope:cliente/cli:idFacturador)
                        then <cli:idFacturador>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion[$pos]/ope:cliente/cli:idFacturador)}</cli:idFacturador>
                        else ()
                    }
                    <cli:razonSocial>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion[$pos]/ope:cliente/cli:razonSocial)}</cli:razonSocial>
                    <cli:tipoPersona>
                        {
                            if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion[$pos]/ope:cliente/cli:tipoPersona/cat:Id)
                            then <cat:Id>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion[$pos]/ope:cliente/cli:tipoPersona/cat:Id)}</cat:Id>
                            else ()
                        }
                    </cli:tipoPersona>
                    <cli:sector>
                        <cat:Id>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion[$pos]/ope:cliente/cli:sector/cat:Id)}</cat:Id>
                    </cli:sector>
                    <cli:pais>
                        <cat:codigoExterno>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion[$pos]/ope:cliente/cli:pais/cat:codigoExterno)}</cat:codigoExterno>
                    </cli:pais>
                    <cli:responsableCliente>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion[$pos]/ope:cliente/cli:responsableCliente)}</cli:responsableCliente>
                </ope:cliente>
                <ope:producto>
                    <pro:idProducto>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion[$pos]/ope:producto/pro:idProducto)}</pro:idProducto>
                   
                </ope:producto>
                <ope:actividadEconomica>
                    <cat:codigoExterno>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion[$pos]/ope:actividadEconomica/cat:codigoExterno)}</cat:codigoExterno>
                </ope:actividadEconomica>
                <ope:actividadEconomicaAsociada>
                    <cat:codigoExterno>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion[$pos]/ope:actividadEconomicaAsociada/cat:codigoExterno)}</cat:codigoExterno>
                </ope:actividadEconomicaAsociada>
                <ope:areaFocalizacion>
                    <cat:codigoExterno>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion[$pos]/ope:areaFocalizacion/cat:codigoExterno)}</cat:codigoExterno>
                </ope:areaFocalizacion>
                <ope:ejeEstrategico>
                    <cat:codigoExterno>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion[$pos]/ope:ejeEstrategico/cat:codigoExterno)}</cat:codigoExterno>
                </ope:ejeEstrategico>
                {for $contrato in $ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion[$pos]/ope:contrato
                return
                <ope:contrato>
                    <con:idContrato>{fn:data($contrato/con:idContrato)}</con:idContrato>
                    <con:idOperacion>{fn:data($contrato/con:idOperacion)}</con:idOperacion>
                    <con:fechaFirma>{fn:data($contrato/con:fechaFirma)}</con:fechaFirma>
                    <con:fechaVigencia>{fn:data($contrato/con:fechaVigencia)}</con:fechaVigencia>
                    <con:MontoEscriturado>{fn:data($contrato/con:MontoEscriturado)}</con:MontoEscriturado>
                    <con:cuentaCliente>
                        <con:cuentaCliente>{fn:data($contrato/con:cuentaCliente/con:cuentaCliente)}</con:cuentaCliente>
                    </con:cuentaCliente>
                    {for $lineaCredito in $contrato/con:LineaCredito
                    return
                    <con:LineaCredito>
                        <lin:idLineaCredito>{fn:data($lineaCredito/lin:idLineaCredito)}</lin:idLineaCredito>
                        <lin:idContrato>{fn:data($lineaCredito/lin:idContrato)}</lin:idContrato>
                        <lin:NumeroLineaCredito>{fn:data($lineaCredito/lin:NumeroLineaCredito)}</lin:NumeroLineaCredito>
                        <lin:Flexcube>
                            <lin:id>{fn:data($lineaCredito/lin:Flexcube/lin:id)}</lin:id>
                            <lin:idProductoFlexcube>{fn:data($lineaCredito/lin:Flexcube/lin:idProductoFlexcube)}</lin:idProductoFlexcube>
                            <lin:idFlexcubePasivo>{fn:data($lineaCredito/lin:Flexcube/lin:idFlexcubePasivo)}</lin:idFlexcubePasivo>
                        </lin:Flexcube>
                        <lin:Fondo>{fn:data($lineaCredito/lin:Fondo)}</lin:Fondo>
                        <lin:EsRevolvente>{fn:data($lineaCredito/lin:EsRevolvente)}</lin:EsRevolvente>
                        <lin:FechaValor>{fn:data($lineaCredito/lin:FechaValor)}</lin:FechaValor>
                        <lin:FechaVencimiento>{fn:data($lineaCredito/lin:FechaVencimiento)}</lin:FechaVencimiento>
                        <lin:CodigoAsignacion>{fn:data($lineaCredito/lin:CodigoAsignacion)}</lin:CodigoAsignacion>
                        <lin:DescripcionAsignacion>{fn:data($lineaCredito/lin:DescripcionAsignacion)}</lin:DescripcionAsignacion>
                        <lin:CondicionEspecial>{fn:data($lineaCredito/lin:CondicionEspecial)}</lin:CondicionEspecial>
                        <lin:FechaRegistro>{fn:data($lineaCredito/lin:FechaRegistro)}</lin:FechaRegistro>
                        <lin:FechaMaximaDesembolso>{fn:data($lineaCredito/lin:FechaMaximaDesembolso)}</lin:FechaMaximaDesembolso>
                        <lin:Estado>{fn:data($lineaCredito/lin:Estado)}</lin:Estado>
                        <lin:descCondEspecial>{fn:data($lineaCredito/lin:descCondEspecial)}</lin:descCondEspecial>
                        {for $termino in $lineaCredito/lin:Termino
                        return
                        <lin:Termino>
                            <ter:frecuenciaPlazo>
                                {
                                    if ($termino/ter:frecuenciaPlazo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($termino/ter:frecuenciaPlazo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </ter:frecuenciaPlazo>
                        </lin:Termino>
                        }
                        {$lineaCredito/lin:ContratoDesembolso}
                    </con:LineaCredito>
                    }
                </ope:contrato>
                }
            </ns1:Operacion>
            <ns1:Aprobacion>
                <apr:fechaAprobacion>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Aprobacion[$pos]/apr:fechaAprobacion)}</apr:fechaAprobacion>
                <apr:numeroResolucion>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Aprobacion[$pos]/apr:numeroResolucion)}</apr:numeroResolucion>
            </ns1:Aprobacion>
            <ns1:montoAprobacion>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:montoAprobacion[$pos])}</ns1:montoAprobacion>
        </ns1:LineaCredito>
    }
        <ns1:Resultado>
            <res:result>OK</res:result>
            {if(fn:count($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion) > 0)then
              <res:message>Consulta Exitosa</res:message>
            else
              <res:message>Consulta sin resultados</res:message>
            }
        </ns1:Resultado>    
    </ns1:ConsultarListaLineasCreditoResponse>
};

local:func($ConsultarLineaCreditoByIdLineaCreditoResponse)
