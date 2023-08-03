xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare variable $outConsultarInformacionDesembolso.response as element() (:: schema-element(des:ConsultarInformacionReglasByIdResponse) ::) external;
declare variable $outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse as element() (:: schema-element(lin:ConsultarLineaCreditoBPELResponse) ::) external;
declare variable $outConsultarDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::) external;

declare function local:funcXq_requestcreardesembolsoloanflexcube($outConsultarInformacionDesembolso.response as element() (:: schema-element(des:ConsultarInformacionReglasByIdResponse) ::), 
                                                                 $outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse as element() (:: schema-element(lin:ConsultarLineaCreditoBPELResponse) ::), 
                                                                 $outConsultarDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::)) 
                                                                 as element() (:: schema-element(des:CrearDesembolsoLoanFLEXCUBERequest) ::) {
    <des:CrearDesembolsoLoanFLEXCUBERequest>
        <des:Cliente>
            <cli:idCliente>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:idCliente)}</cli:idCliente>
            {
                if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:idFacturador)
                then <cli:idFacturador>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:idFacturador)}</cli:idFacturador>
                else ()
            }
            {
                if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:razonSocial)
                then <cli:razonSocial>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:razonSocial)}</cli:razonSocial>
                else ()
            }
            {
                if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:abreviatura)
                then <cli:abreviatura>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:abreviatura)}</cli:abreviatura>
                else ()
            }
            {
                if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoPersona)
                then 
                    <cli:tipoPersona>
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoPersona/cat:Id)
                            then <cat:Id>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoPersona/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoPersona/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoPersona/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoPersona/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoPersona/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoPersona/cat:estatus)
                            then <cat:estatus>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoPersona/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoPersona/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoPersona/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </cli:tipoPersona>
                else ()
            }
            {
                if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoCliente)
                then 
                    <cli:tipoCliente>
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoCliente/cat:Id)
                            then <cat:Id>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoCliente/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoCliente/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoCliente/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoCliente/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoCliente/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoCliente/cat:estatus)
                            then <cat:estatus>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoCliente/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoCliente/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoCliente/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </cli:tipoCliente>
                else ()
            }
            {
                if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:sector)
                then 
                    <cli:sector>
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:sector/cat:Id)
                            then <cat:Id>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:sector/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:sector/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:sector/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:sector/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:sector/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:sector/cat:estatus)
                            then <cat:estatus>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:sector/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:sector/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:sector/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </cli:sector>
                else ()
            }
            {
                if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoInstitucion)
                then 
                    <cli:tipoInstitucion>
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoInstitucion/cat:Id)
                            then <cat:Id>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoInstitucion/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoInstitucion/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoInstitucion/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoInstitucion/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoInstitucion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoInstitucion/cat:estatus)
                            then <cat:estatus>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoInstitucion/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoInstitucion/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoInstitucion/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </cli:tipoInstitucion>
                else ()
            }
            {
                if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:pais)
                then 
                    <cli:pais>
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:pais/cat:Id)
                            then <cat:Id>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:pais/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:pais/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:pais/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:pais/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:pais/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:pais/cat:estatus)
                            then <cat:estatus>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:pais/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:pais/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:pais/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </cli:pais>
                else ()
            }
            {
                if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:grupoEconomico)
                then 
                    <cli:grupoEconomico>
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:grupoEconomico/cat:Id)
                            then <cat:Id>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:grupoEconomico/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:grupoEconomico/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:grupoEconomico/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:grupoEconomico/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:grupoEconomico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:grupoEconomico/cat:estatus)
                            then <cat:estatus>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:grupoEconomico/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:grupoEconomico/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:grupoEconomico/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </cli:grupoEconomico>
                else ()
            }
            {
                if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoIdentificacion)
                then <cli:tipoIdentificacion>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:tipoIdentificacion)}</cli:tipoIdentificacion>
                else ()
            }
            {
                if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:numeroIdentificacion)
                then <cli:numeroIdentificacion>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:numeroIdentificacion)}</cli:numeroIdentificacion>
                else ()
            }
            {
                if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:direccion)
                then <cli:direccion>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:direccion)}</cli:direccion>
                else ()
            }
            {
                if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:telefono)
                then <cli:telefono>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:telefono)}</cli:telefono>
                else ()
            }
            {
                if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:fax)
                then <cli:fax>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:fax)}</cli:fax>
                else ()
            }
            {
                if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:email)
                then <cli:email>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:email)}</cli:email>
                else ()
            }
            {
                if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:ciudad)
                then <cli:ciudad>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:ciudad)}</cli:ciudad>
                else ()
            }
            {
                if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:oficina)
                then 
                    <cli:oficina>
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:oficina/cat:Id)
                            then <cat:Id>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:oficina/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:oficina/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:oficina/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:oficina/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:oficina/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:oficina/cat:estatus)
                            then <cat:estatus>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:oficina/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:oficina/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:oficina/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </cli:oficina>
                else ()
            }
            {
                if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:grupoEmpresarial)
                then <cli:grupoEmpresarial>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:grupoEmpresarial)}</cli:grupoEmpresarial>
                else ()
            }
            {
                if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:fechaRegistro)
                then <cli:fechaRegistro>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:fechaRegistro)}</cli:fechaRegistro>
                else ()
            }
            {
                if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:fechaAprobacion)
                then <cli:fechaAprobacion>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:fechaAprobacion)}</cli:fechaAprobacion>
                else ()
            }
            {
                if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:ejecutivo)
                then <cli:ejecutivo>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:ejecutivo)}</cli:ejecutivo>
                else ()
            }
            {
                if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:responsableCliente)
                then <cli:responsableCliente>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:responsableCliente)}</cli:responsableCliente>
                else ()
            }
        </des:Cliente>
        <des:Operacion>
            {
                if ($outConsultarInformacionDesembolso.response/des:Operacion/ope:idOperacion)
                then <ope:idOperacion>{fn:data($outConsultarInformacionDesembolso.response/des:Operacion/ope:idOperacion)}</ope:idOperacion>
                else ()
            }
            {
                if ($outConsultarInformacionDesembolso.response/des:Operacion/ope:responsable)
                then <ope:responsable>{fn:data($outConsultarInformacionDesembolso.response/des:Operacion/ope:responsable)}</ope:responsable>
                else ()
            }
            {
                if ($outConsultarInformacionDesembolso.response/des:Operacion/ope:oficina)
                then <ope:oficina>{fn:data($outConsultarInformacionDesembolso.response/des:Operacion/ope:oficina)}</ope:oficina>
                else ()
            }
            {
                if ($outConsultarInformacionDesembolso.response/des:Operacion/ope:nombre)
                then <ope:nombre>{fn:data($outConsultarInformacionDesembolso.response/des:Operacion/ope:nombre)}</ope:nombre>
                else ()
            }
        </des:Operacion>
        <des:LineaCredito>
            {
                if ($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:idLineaCredito)
                then <lin1:idLineaCredito>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:idLineaCredito)}</lin1:idLineaCredito>
                else ()
            }
            {
                if ($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:idContrato)
                then <lin1:idContrato>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:idContrato)}</lin1:idContrato>
                else ()
            }
            <lin1:NumeroLineaCredito>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:NumeroLineaCredito)}</lin1:NumeroLineaCredito>
            <lin1:Descripcion></lin1:Descripcion>
            <lin1:Flexcube>
                <lin1:id></lin1:id>
                <lin1:idProductoFlexcube></lin1:idProductoFlexcube>
                <lin1:idFlexcubePasivo></lin1:idFlexcubePasivo>
            </lin1:Flexcube>
            <lin1:Fondo></lin1:Fondo>
            <lin1:MontoLinea></lin1:MontoLinea>
            {
                if ($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:EsRevolvente)
                then <lin1:EsRevolvente>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:EsRevolvente)}</lin1:EsRevolvente>
                else ()
            }
            <lin1:TratamientoDiasFeriados>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:TratamientoDiasFeriados)}</lin1:TratamientoDiasFeriados>
            <lin1:FechaValor>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:FechaValor)}</lin1:FechaValor>
            <lin1:FechaVencimiento></lin1:FechaVencimiento>
            <lin1:CodigoPantallaLimite>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:CodigoPantallaLimite)}</lin1:CodigoPantallaLimite>
            <lin1:CodigoSerialLimite>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:CodigoSerialLimite)}</lin1:CodigoSerialLimite>
            <lin1:ContratoDesembolso>
            {$outConsultarDesembolsoById.response/des:ContratoDesembolso/*}
            </lin1:ContratoDesembolso>
        </des:LineaCredito>
    </des:CrearDesembolsoLoanFLEXCUBERequest>
};

local:funcXq_requestcreardesembolsoloanflexcube($outConsultarInformacionDesembolso.response, $outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse, $outConsultarDesembolsoById.response)
