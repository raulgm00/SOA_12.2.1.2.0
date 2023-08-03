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

declare namespace com2 = "http://www.bcie.org/ComisionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare variable $outConsultarInformacionDesembolso.response as element() (:: schema-element(des:ConsultarInformacionReglasByIdResponse) ::) external;
declare variable $outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse as element() (:: schema-element(lin:ConsultarLineaCreditoBPELResponse) ::) external;
declare variable $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response as element() (:: schema-element(des:ConsultarDesembolsosByIdV2Response) ::) external;

declare function local:funcXq_requestcreadesembolsocomplejov2flexcube($outConsultarInformacionDesembolso.response as element() (:: schema-element(des:ConsultarInformacionReglasByIdResponse) ::), 
                                                                      $outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse as element() (:: schema-element(lin:ConsultarLineaCreditoBPELResponse) ::), 
                                                                      $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response as element() (:: schema-element(des:ConsultarDesembolsosByIdV2Response) ::)) 
                                                                      as element() (:: schema-element(des:CrearDesembolsoComplejoV2FLEXCUBERequest) ::) {
    <des:CrearDesembolsoComplejoV2FLEXCUBERequest>
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
            <lin1:idContrato></lin1:idContrato>
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
        </des:LineaCredito>
        <des:ContratoDesembolso>
            <des1:idDesembolso>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:idDesembolso)}</des1:idDesembolso>
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:idFacturador)
                then <des1:idFacturador>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:idFacturador)}</des1:idFacturador>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:producto)
                then 
                    <des1:producto>
                        <pro:idProducto>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:producto/pro:idProducto)}</pro:idProducto>
                        <pro:descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:producto/pro:descripcion)}</pro:descripcion>
                        <pro:descripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:producto/pro:descripcionCorta)}</pro:descripcionCorta>
                        <pro:fechaRegistro>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:producto/pro:fechaRegistro)}</pro:fechaRegistro>
                        <pro:codExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:producto/pro:codExterno)}</pro:codExterno>
                    </des1:producto>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:referencia)
                then <des1:referencia>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:referencia)}</des1:referencia>
                else ()
            }
            {
                for $monto in $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:monto
                return 
                <des1:monto>
                    <com1:tipo>
                        {
                            if ($monto/com1:tipo/cat:Id)
                            then <cat:Id>{fn:data($monto/com1:tipo/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($monto/com1:tipo/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($monto/com1:tipo/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($monto/com1:tipo/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($monto/com1:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($monto/com1:tipo/cat:estatus)
                            then <cat:estatus>{fn:data($monto/com1:tipo/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($monto/com1:tipo/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($monto/com1:tipo/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </com1:tipo>
                    {
                        if ($monto/com1:importe)
                        then <com1:importe>{fn:data($monto/com1:importe)}</com1:importe>
                        else ()
                    }
                    {
                        if ($monto/com1:moneda)
                        then 
                            <com1:moneda>
                                {
                                    if ($monto/com1:moneda/cat:Id)
                                    then <cat:Id>{fn:data($monto/com1:moneda/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($monto/com1:moneda/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($monto/com1:moneda/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($monto/com1:moneda/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($monto/com1:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($monto/com1:moneda/cat:estatus)
                                    then <cat:estatus>{fn:data($monto/com1:moneda/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($monto/com1:moneda/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($monto/com1:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com1:moneda>
                        else ()
                    }
                </des1:monto>
            }
            <des1:estado>
                {
                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:estado/cat:Id)
                    then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:estado/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:estado/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:estado/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
                {
                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:estado/cat:DescripcionCorta)
                    then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    else ()
                }
                {
                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:estado/cat:estatus)
                    then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:estado/cat:estatus)}</cat:estatus>
                    else ()
                }
                {
                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:estado/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:estado/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </des1:estado>
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:programado)
                then <des1:programado>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:programado)}</des1:programado>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:fechaEstimadaDesembolsar)
                then <des1:fechaEstimadaDesembolsar>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:fechaEstimadaDesembolsar)}</des1:fechaEstimadaDesembolsar>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:fechaEfectiva)
                then <des1:fechaEfectiva>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:fechaEfectiva)}</des1:fechaEfectiva>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:fechaAsignacion)
                then <des1:fechaAsignacion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:fechaAsignacion)}</des1:fechaAsignacion>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:fechaRegistro)
                then <des1:fechaRegistro>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:fechaRegistro)}</des1:fechaRegistro>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:fechaVencimiento)
                then <des1:fechaVencimiento>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:fechaVencimiento)}</des1:fechaVencimiento>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:estatus)
                then <des1:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:estatus)}</des1:estatus>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras)
                then 
                    <des1:condicionesFinancieras>
                        <des1:idCondicionFinanciera>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:idCondicionFinanciera)}</des1:idCondicionFinanciera>
                        <des1:fondo>
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:fondo/cat:Id)
                                then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:fondo/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:fondo/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:fondo/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:fondo/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:fondo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:fondo/cat:estatus)
                                then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:fondo/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:fondo/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:fondo/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                            {
                                for $Validador in $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:fondo/des1:Validador
                                return 
                                <des1:Validador>
                                    {
                                        if ($Validador/cat:Id)
                                        then <cat:Id>{fn:data($Validador/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($Validador/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($Validador/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($Validador/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($Validador/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($Validador/cat:estatus)
                                        then <cat:estatus>{fn:data($Validador/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($Validador/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($Validador/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </des1:Validador>
                            }
                        </des1:fondo>
                        <des1:baseCalculo>
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:baseCalculo/cat:Id)
                                then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:baseCalculo/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:baseCalculo/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:baseCalculo/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:baseCalculo/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:baseCalculo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:baseCalculo/cat:estatus)
                                then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:baseCalculo/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:baseCalculo/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:baseCalculo/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </des1:baseCalculo>
                        <des1:periodoGracia>
                            <des1:FechaInicio>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:periodoGracia/des1:FechaInicio)}</des1:FechaInicio>
                            <des1:Frecuencia>
                                <com1:Tipo>
                                    {
                                        if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:periodoGracia/des1:Frecuencia/com1:Tipo/cat:Id)
                                        then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:periodoGracia/des1:Frecuencia/com1:Tipo/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:periodoGracia/des1:Frecuencia/com1:Tipo/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:periodoGracia/des1:Frecuencia/com1:Tipo/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:periodoGracia/des1:Frecuencia/com1:Tipo/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:periodoGracia/des1:Frecuencia/com1:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:periodoGracia/des1:Frecuencia/com1:Tipo/cat:estatus)
                                        then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:periodoGracia/des1:Frecuencia/com1:Tipo/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:periodoGracia/des1:Frecuencia/com1:Tipo/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:periodoGracia/des1:Frecuencia/com1:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </com1:Tipo>
                                <com1:Valor>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:periodoGracia/des1:Frecuencia/com1:Valor)}</com1:Valor>
                            </des1:Frecuencia>
                        </des1:periodoGracia>
                        <des1:tratamientoDiasFeriados>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:tratamientoDiasFeriados)}</des1:tratamientoDiasFeriados>
                        <des1:tipoCalendario>
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:tipoCalendario/cat:Id)
                                then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:tipoCalendario/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:tipoCalendario/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:tipoCalendario/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:tipoCalendario/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:tipoCalendario/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:tipoCalendario/cat:estatus)
                                then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:tipoCalendario/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:tipoCalendario/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:tipoCalendario/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </des1:tipoCalendario>
                        <des1:moverEntreMeses>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:moverEntreMeses)}</des1:moverEntreMeses>
                        {
                            for $Componente in $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:Componente
                            return 
                            <des1:Componente>
                                {
                                    if ($Componente/cat:Id)
                                    then <cat:Id>{fn:data($Componente/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Componente/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Componente/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Componente/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Componente/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Componente/cat:estatus)
                                    then <cat:estatus>{fn:data($Componente/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Componente/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Componente/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                                <des1:TipoComponente>
                                    {
                                        if ($Componente/des1:TipoComponente/cat:Id)
                                        then <cat:Id>{fn:data($Componente/des1:TipoComponente/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des1:TipoComponente/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($Componente/des1:TipoComponente/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des1:TipoComponente/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($Componente/des1:TipoComponente/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des1:TipoComponente/cat:estatus)
                                        then <cat:estatus>{fn:data($Componente/des1:TipoComponente/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des1:TipoComponente/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($Componente/des1:TipoComponente/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </des1:TipoComponente>
                                <des1:TipoTasa>
                                    {
                                        if ($Componente/des1:TipoTasa/cat:Id)
                                        then <cat:Id>{fn:data($Componente/des1:TipoTasa/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des1:TipoTasa/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($Componente/des1:TipoTasa/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des1:TipoTasa/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($Componente/des1:TipoTasa/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des1:TipoTasa/cat:estatus)
                                        then <cat:estatus>{fn:data($Componente/des1:TipoTasa/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des1:TipoTasa/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($Componente/des1:TipoTasa/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </des1:TipoTasa>
                                <des1:BaseCalculo>
                                    {
                                        if ($Componente/des1:BaseCalculo/cat:Id)
                                        then <cat:Id>{fn:data($Componente/des1:BaseCalculo/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des1:BaseCalculo/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($Componente/des1:BaseCalculo/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des1:BaseCalculo/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($Componente/des1:BaseCalculo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des1:BaseCalculo/cat:estatus)
                                        then <cat:estatus>{fn:data($Componente/des1:BaseCalculo/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des1:BaseCalculo/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($Componente/des1:BaseCalculo/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </des1:BaseCalculo>
                                <des1:esDependiente>{fn:data($Componente/des1:esDependiente)}</des1:esDependiente>
                                <des1:esFactor>{fn:data($Componente/des1:esFactor)}</des1:esFactor>
                                <des1:CodigoTasaReferencia>{fn:data($Componente/des1:CodigoTasaReferencia)}</des1:CodigoTasaReferencia>
                                <des1:FechaEfectivaTasaReferencia>{fn:data($Componente/des1:FechaEfectivaTasaReferencia)}</des1:FechaEfectivaTasaReferencia>
                                <des1:ValorTasaReferencia>{fn:data($Componente/des1:ValorTasaReferencia)}</des1:ValorTasaReferencia>
                                <des1:Tasa>{fn:data($Componente/des1:Tasa)}</des1:Tasa>
                                <des1:SpreadTasa>{fn:data($Componente/des1:SpreadTasa)}</des1:SpreadTasa>
                                <des1:MontoDescuento>{fn:data($Componente/des1:MontoDescuento)}</des1:MontoDescuento>
                                <des1:DiasSpotTasa>{fn:data($Componente/des1:DiasSpotTasa)}</des1:DiasSpotTasa>
                                <des1:TipoRedondeo>
                                    {
                                        if ($Componente/des1:TipoRedondeo/cat:Id)
                                        then <cat:Id>{fn:data($Componente/des1:TipoRedondeo/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des1:TipoRedondeo/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($Componente/des1:TipoRedondeo/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des1:TipoRedondeo/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($Componente/des1:TipoRedondeo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des1:TipoRedondeo/cat:estatus)
                                        then <cat:estatus>{fn:data($Componente/des1:TipoRedondeo/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des1:TipoRedondeo/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($Componente/des1:TipoRedondeo/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </des1:TipoRedondeo>
                                <des1:CantidadDecimales>{fn:data($Componente/des1:CantidadDecimales)}</des1:CantidadDecimales>
                                <des1:LimiteTasaMinima>{fn:data($Componente/des1:LimiteTasaMinima)}</des1:LimiteTasaMinima>
                                <des1:LimiteTasaMaxima>{fn:data($Componente/des1:LimiteTasaMaxima)}</des1:LimiteTasaMaxima>
                                {
                                    for $calendario in $Componente/des1:calendario
                                    return 
                                    <des1:calendario>
                                        {
                                            if ($calendario/cat:Id)
                                            then <cat:Id>{fn:data($calendario/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($calendario/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($calendario/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($calendario/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($calendario/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($calendario/cat:estatus)
                                            then <cat:estatus>{fn:data($calendario/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($calendario/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($calendario/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                        <des1:TipoPlan>
                                            {
                                                if ($calendario/des1:TipoPlan/cat:Id)
                                                then <cat:Id>{fn:data($calendario/des1:TipoPlan/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($calendario/des1:TipoPlan/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($calendario/des1:TipoPlan/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($calendario/des1:TipoPlan/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($calendario/des1:TipoPlan/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($calendario/des1:TipoPlan/cat:estatus)
                                                then <cat:estatus>{fn:data($calendario/des1:TipoPlan/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($calendario/des1:TipoPlan/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($calendario/des1:TipoPlan/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </des1:TipoPlan>
                                        <des1:Frecuencia>
                                            <des1:FechaInicio>{fn:data($calendario/des1:Frecuencia/des1:FechaInicio)}</des1:FechaInicio>
                                            <des1:Frecuencia>
                                                <com1:Tipo>
                                                    {
                                                        if ($calendario/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:Id)
                                                        then <cat:Id>{fn:data($calendario/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:Id)}</cat:Id>
                                                        else ()
                                                    }
                                                    {
                                                        if ($calendario/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:Descripcion)
                                                        then <cat:Descripcion>{fn:data($calendario/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:Descripcion)}</cat:Descripcion>
                                                        else ()
                                                    }
                                                    {
                                                        if ($calendario/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:DescripcionCorta)
                                                        then <cat:DescripcionCorta>{fn:data($calendario/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                        else ()
                                                    }
                                                    {
                                                        if ($calendario/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:estatus)
                                                        then <cat:estatus>{fn:data($calendario/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:estatus)}</cat:estatus>
                                                        else ()
                                                    }
                                                    {
                                                        if ($calendario/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:codigoExterno)
                                                        then <cat:codigoExterno>{fn:data($calendario/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                                        else ()
                                                    }
                                                </com1:Tipo>
                                                <com1:Valor>{fn:data($calendario/des1:Frecuencia/des1:Frecuencia/com1:Valor)}</com1:Valor>
                                            </des1:Frecuencia>
                                        </des1:Frecuencia>
                                        <des1:Monto>
                                            <com1:tipo>
                                                {
                                                    if ($calendario/des1:Monto/com1:tipo/cat:Id)
                                                    then <cat:Id>{fn:data($calendario/des1:Monto/com1:tipo/cat:Id)}</cat:Id>
                                                    else ()
                                                }
                                                {
                                                    if ($calendario/des1:Monto/com1:tipo/cat:Descripcion)
                                                    then <cat:Descripcion>{fn:data($calendario/des1:Monto/com1:tipo/cat:Descripcion)}</cat:Descripcion>
                                                    else ()
                                                }
                                                {
                                                    if ($calendario/des1:Monto/com1:tipo/cat:DescripcionCorta)
                                                    then <cat:DescripcionCorta>{fn:data($calendario/des1:Monto/com1:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                    else ()
                                                }
                                                {
                                                    if ($calendario/des1:Monto/com1:tipo/cat:estatus)
                                                    then <cat:estatus>{fn:data($calendario/des1:Monto/com1:tipo/cat:estatus)}</cat:estatus>
                                                    else ()
                                                }
                                                {
                                                    if ($calendario/des1:Monto/com1:tipo/cat:codigoExterno)
                                                    then <cat:codigoExterno>{fn:data($calendario/des1:Monto/com1:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                                    else ()
                                                }
                                            </com1:tipo>
                                            {
                                                if ($calendario/des1:Monto/com1:importe)
                                                then <com1:importe>{fn:data($calendario/des1:Monto/com1:importe)}</com1:importe>
                                                else ()
                                            }
                                            {
                                                if ($calendario/des1:Monto/com1:moneda)
                                                then 
                                                    <com1:moneda>
                                                        {
                                                            if ($calendario/des1:Monto/com1:moneda/cat:Id)
                                                            then <cat:Id>{fn:data($calendario/des1:Monto/com1:moneda/cat:Id)}</cat:Id>
                                                            else ()
                                                        }
                                                        {
                                                            if ($calendario/des1:Monto/com1:moneda/cat:Descripcion)
                                                            then <cat:Descripcion>{fn:data($calendario/des1:Monto/com1:moneda/cat:Descripcion)}</cat:Descripcion>
                                                            else ()
                                                        }
                                                        {
                                                            if ($calendario/des1:Monto/com1:moneda/cat:DescripcionCorta)
                                                            then <cat:DescripcionCorta>{fn:data($calendario/des1:Monto/com1:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                            else ()
                                                        }
                                                        {
                                                            if ($calendario/des1:Monto/com1:moneda/cat:estatus)
                                                            then <cat:estatus>{fn:data($calendario/des1:Monto/com1:moneda/cat:estatus)}</cat:estatus>
                                                            else ()
                                                        }
                                                        {
                                                            if ($calendario/des1:Monto/com1:moneda/cat:codigoExterno)
                                                            then <cat:codigoExterno>{fn:data($calendario/des1:Monto/com1:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                                            else ()
                                                        }
                                                    </com1:moneda>
                                                else ()
                                            }
                                        </des1:Monto>
                                        <des1:NumeroCuotas>{fn:data($calendario/des1:NumeroCuotas)}</des1:NumeroCuotas>
                                    </des1:calendario>
                                }
                            </des1:Componente>
                        }
                        {
                            for $calendario in $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:calendario
                            return 
                            <des1:calendario>
                                {
                                    if ($calendario/cat:Id)
                                    then <cat:Id>{fn:data($calendario/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($calendario/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($calendario/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($calendario/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($calendario/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($calendario/cat:estatus)
                                    then <cat:estatus>{fn:data($calendario/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($calendario/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($calendario/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                                <des1:TipoPlan>
                                    {
                                        if ($calendario/des1:TipoPlan/cat:Id)
                                        then <cat:Id>{fn:data($calendario/des1:TipoPlan/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($calendario/des1:TipoPlan/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($calendario/des1:TipoPlan/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($calendario/des1:TipoPlan/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($calendario/des1:TipoPlan/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($calendario/des1:TipoPlan/cat:estatus)
                                        then <cat:estatus>{fn:data($calendario/des1:TipoPlan/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($calendario/des1:TipoPlan/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($calendario/des1:TipoPlan/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </des1:TipoPlan>
                                <des1:Frecuencia>
                                    <des1:FechaInicio>{fn:data($calendario/des1:Frecuencia/des1:FechaInicio)}</des1:FechaInicio>
                                    <des1:Frecuencia>
                                        <com1:Tipo>
                                            {
                                                if ($calendario/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:Id)
                                                then <cat:Id>{fn:data($calendario/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($calendario/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($calendario/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($calendario/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($calendario/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($calendario/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:estatus)
                                                then <cat:estatus>{fn:data($calendario/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($calendario/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($calendario/des1:Frecuencia/des1:Frecuencia/com1:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </com1:Tipo>
                                        <com1:Valor>{fn:data($calendario/des1:Frecuencia/des1:Frecuencia/com1:Valor)}</com1:Valor>
                                    </des1:Frecuencia>
                                </des1:Frecuencia>
                                <des1:Monto>
                                    <com1:tipo>
                                        {
                                            if ($calendario/des1:Monto/com1:tipo/cat:Id)
                                            then <cat:Id>{fn:data($calendario/des1:Monto/com1:tipo/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($calendario/des1:Monto/com1:tipo/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($calendario/des1:Monto/com1:tipo/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($calendario/des1:Monto/com1:tipo/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($calendario/des1:Monto/com1:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($calendario/des1:Monto/com1:tipo/cat:estatus)
                                            then <cat:estatus>{fn:data($calendario/des1:Monto/com1:tipo/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($calendario/des1:Monto/com1:tipo/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($calendario/des1:Monto/com1:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </com1:tipo>
                                    {
                                        if ($calendario/des1:Monto/com1:importe)
                                        then <com1:importe>{fn:data($calendario/des1:Monto/com1:importe)}</com1:importe>
                                        else ()
                                    }
                                    {
                                        if ($calendario/des1:Monto/com1:moneda)
                                        then 
                                            <com1:moneda>
                                                {
                                                    if ($calendario/des1:Monto/com1:moneda/cat:Id)
                                                    then <cat:Id>{fn:data($calendario/des1:Monto/com1:moneda/cat:Id)}</cat:Id>
                                                    else ()
                                                }
                                                {
                                                    if ($calendario/des1:Monto/com1:moneda/cat:Descripcion)
                                                    then <cat:Descripcion>{fn:data($calendario/des1:Monto/com1:moneda/cat:Descripcion)}</cat:Descripcion>
                                                    else ()
                                                }
                                                {
                                                    if ($calendario/des1:Monto/com1:moneda/cat:DescripcionCorta)
                                                    then <cat:DescripcionCorta>{fn:data($calendario/des1:Monto/com1:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                    else ()
                                                }
                                                {
                                                    if ($calendario/des1:Monto/com1:moneda/cat:estatus)
                                                    then <cat:estatus>{fn:data($calendario/des1:Monto/com1:moneda/cat:estatus)}</cat:estatus>
                                                    else ()
                                                }
                                                {
                                                    if ($calendario/des1:Monto/com1:moneda/cat:codigoExterno)
                                                    then <cat:codigoExterno>{fn:data($calendario/des1:Monto/com1:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                                    else ()
                                                }
                                            </com1:moneda>
                                        else ()
                                    }
                                </des1:Monto>
                                <des1:NumeroCuotas>{fn:data($calendario/des1:NumeroCuotas)}</des1:NumeroCuotas>
                            </des1:calendario>
                        }
                    </des1:condicionesFinancieras>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:idLinea)
                then <des1:idLinea>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:idLinea)}</des1:idLinea>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:recursosExternos)
                then <des1:recursosExternos>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:recursosExternos)}</des1:recursosExternos>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:cuentaCliente)
                then <des1:cuentaCliente>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:cuentaCliente)}</des1:cuentaCliente>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:usuario)
                then <des1:usuario>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:usuario)}</des1:usuario>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:fechaDisponibilidadFondos)
                then <des1:fechaDisponibilidadFondos>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:fechaDisponibilidadFondos)}</des1:fechaDisponibilidadFondos>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:origenTransferenciaCte)
                then <des1:origenTransferenciaCte>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:origenTransferenciaCte)}</des1:origenTransferenciaCte>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa)
                then 
                    <des1:Programa>
                        <her:LineaFinanciera>
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:LineaFinanciera/cat:Id)
                                then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:LineaFinanciera/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:LineaFinanciera/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:LineaFinanciera/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:LineaFinanciera/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:LineaFinanciera/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:LineaFinanciera/cat:estatus)
                                then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:LineaFinanciera/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:LineaFinanciera/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:LineaFinanciera/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:LineaFinanciera>
                        <her:DestinoFinanciamiento>
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:DestinoFinanciamiento/cat:Id)
                                then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:DestinoFinanciamiento/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:DestinoFinanciamiento/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:DestinoFinanciamiento/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:DestinoFinanciamiento/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:DestinoFinanciamiento/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:DestinoFinanciamiento/cat:estatus)
                                then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:DestinoFinanciamiento/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:DestinoFinanciamiento/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:DestinoFinanciamiento/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:DestinoFinanciamiento>
                        <her:ModalidadFinanciamiento>
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ModalidadFinanciamiento/cat:Id)
                                then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ModalidadFinanciamiento/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ModalidadFinanciamiento/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ModalidadFinanciamiento/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ModalidadFinanciamiento/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ModalidadFinanciamiento/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ModalidadFinanciamiento/cat:estatus)
                                then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ModalidadFinanciamiento/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ModalidadFinanciamiento/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ModalidadFinanciamiento/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:ModalidadFinanciamiento>
                        <her:HerramientaCE>
                            <her:ActividadEconomica>
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:ActividadEconomica/cat:Id)
                                    then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:ActividadEconomica/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:ActividadEconomica/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:ActividadEconomica/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:ActividadEconomica/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:ActividadEconomica/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:ActividadEconomica/cat:estatus)
                                    then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:ActividadEconomica/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </her:ActividadEconomica>
                            <her:EjeEstrategico>
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:EjeEstrategico/cat:Id)
                                    then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:EjeEstrategico/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:EjeEstrategico/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:EjeEstrategico/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:EjeEstrategico/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:EjeEstrategico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:EjeEstrategico/cat:estatus)
                                    then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:EjeEstrategico/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </her:EjeEstrategico>
                            <her:AreaFocalizacion>
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:Id)
                                    then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:estatus)
                                    then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </her:AreaFocalizacion>
                        </her:HerramientaCE>
                        <her:ClasificacionGeneral>
                            <her:SectorMercado>
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:Id)
                                    then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:estatus)
                                    then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </her:SectorMercado>
                            <her:SectorInstitucional>
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:Id)
                                    then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:estatus)
                                    then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </her:SectorInstitucional>
                        </her:ClasificacionGeneral>
                    </des1:Programa>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:EstimadoDesembolso)
                then 
                    <des1:EstimadoDesembolso>
                        <des1:Plazo>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:EstimadoDesembolso/des1:Plazo)}</des1:Plazo>
                        <des1:Frecuencia>
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:EstimadoDesembolso/des1:Frecuencia/cat:Id)
                                then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:EstimadoDesembolso/des1:Frecuencia/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:EstimadoDesembolso/des1:Frecuencia/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:EstimadoDesembolso/des1:Frecuencia/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:EstimadoDesembolso/des1:Frecuencia/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:EstimadoDesembolso/des1:Frecuencia/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:EstimadoDesembolso/des1:Frecuencia/cat:estatus)
                                then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:EstimadoDesembolso/des1:Frecuencia/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:EstimadoDesembolso/des1:Frecuencia/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:EstimadoDesembolso/des1:Frecuencia/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </des1:Frecuencia>
                    </des1:EstimadoDesembolso>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:fechaInicioProceso)
                then <des1:fechaInicioProceso>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:fechaInicioProceso)}</des1:fechaInicioProceso>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:fechaEstimadaDisponibilidad)
                then <des1:fechaEstimadaDisponibilidad>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:fechaEstimadaDisponibilidad)}</des1:fechaEstimadaDisponibilidad>
                else ()
            }
            {
                for $Utilizacion in $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Utilizacion
                return 
                <des1:Utilizacion>
                    <lin1:idFuente>{fn:data($Utilizacion/lin1:idFuente)}</lin1:idFuente>
                    <lin1:idLineaCredito>{fn:data($Utilizacion/lin1:idLineaCredito)}</lin1:idLineaCredito>
                    <lin1:idLineaPasiva>{fn:data($Utilizacion/lin1:idLineaPasiva)}</lin1:idLineaPasiva>
                    <lin1:codigoLineaPasiva>{fn:data($Utilizacion/lin1:codigoLineaPasiva)}</lin1:codigoLineaPasiva>
                    <lin1:idFacturadorLineaPasiva>{fn:data($Utilizacion/lin1:idFacturadorLineaPasiva)}</lin1:idFacturadorLineaPasiva>
                    <lin1:idFondo>{fn:data($Utilizacion/lin1:idFondo)}</lin1:idFondo>
                    <lin1:Descripcion>{fn:data($Utilizacion/lin1:Descripcion)}</lin1:Descripcion>
                    <lin1:FechaObtenido>{fn:data($Utilizacion/lin1:FechaObtenido)}</lin1:FechaObtenido>
                    <lin1:MontoAsignado>{fn:data($Utilizacion/lin1:MontoAsignado)}</lin1:MontoAsignado>
                    <lin1:montoDisponible>{fn:data($Utilizacion/lin1:montoDisponible)}</lin1:montoDisponible>
                    {
                        for $Monto in $Utilizacion/lin1:Monto
                        return 
                        <lin1:Monto>
                            <com1:tipo>
                                {
                                    if ($Monto/com1:tipo/cat:Id)
                                    then <cat:Id>{fn:data($Monto/com1:tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Monto/com1:tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Monto/com1:tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Monto/com1:tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Monto/com1:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Monto/com1:tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($Monto/com1:tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Monto/com1:tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Monto/com1:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com1:tipo>
                            {
                                if ($Monto/com1:importe)
                                then <com1:importe>{fn:data($Monto/com1:importe)}</com1:importe>
                                else ()
                            }
                            {
                                if ($Monto/com1:moneda)
                                then 
                                    <com1:moneda>
                                        {
                                            if ($Monto/com1:moneda/cat:Id)
                                            then <cat:Id>{fn:data($Monto/com1:moneda/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Monto/com1:moneda/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Monto/com1:moneda/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Monto/com1:moneda/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Monto/com1:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Monto/com1:moneda/cat:estatus)
                                            then <cat:estatus>{fn:data($Monto/com1:moneda/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Monto/com1:moneda/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Monto/com1:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </com1:moneda>
                                else ()
                            }
                        </lin1:Monto>
                    }
                    <lin1:NumeroAsignacion>{fn:data($Utilizacion/lin1:NumeroAsignacion)}</lin1:NumeroAsignacion>
                    <lin1:Comentario>{fn:data($Utilizacion/lin1:Comentario)}</lin1:Comentario>
                    <lin1:idContrato>{fn:data($Utilizacion/lin1:idContrato)}</lin1:idContrato>
                    <lin1:FechaRegistro>{fn:data($Utilizacion/lin1:FechaRegistro)}</lin1:FechaRegistro>
                    <lin1:Estado>{fn:data($Utilizacion/lin1:Estado)}</lin1:Estado>
                    <lin1:esExterna>{fn:data($Utilizacion/lin1:esExterna)}</lin1:esExterna>
                </des1:Utilizacion>
            }
            {
                for $Cargo in $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Cargo
                return 
                <des1:Cargo>
                    {
                        if ($Cargo/cat:Id)
                        then <cat:Id>{fn:data($Cargo/cat:Id)}</cat:Id>
                        else ()
                    }
                    {
                        if ($Cargo/cat:Descripcion)
                        then <cat:Descripcion>{fn:data($Cargo/cat:Descripcion)}</cat:Descripcion>
                        else ()
                    }
                    {
                        if ($Cargo/cat:DescripcionCorta)
                        then <cat:DescripcionCorta>{fn:data($Cargo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                        else ()
                    }
                    {
                        if ($Cargo/cat:estatus)
                        then <cat:estatus>{fn:data($Cargo/cat:estatus)}</cat:estatus>
                        else ()
                    }
                    {
                        if ($Cargo/cat:codigoExterno)
                        then <cat:codigoExterno>{fn:data($Cargo/cat:codigoExterno)}</cat:codigoExterno>
                        else ()
                    }
                    <des1:Monto>
                        <com1:tipo>
                            {
                                if ($Cargo/des1:Monto/com1:tipo/cat:Id)
                                then <cat:Id>{fn:data($Cargo/des1:Monto/com1:tipo/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Cargo/des1:Monto/com1:tipo/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Cargo/des1:Monto/com1:tipo/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Cargo/des1:Monto/com1:tipo/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Cargo/des1:Monto/com1:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Cargo/des1:Monto/com1:tipo/cat:estatus)
                                then <cat:estatus>{fn:data($Cargo/des1:Monto/com1:tipo/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Cargo/des1:Monto/com1:tipo/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Cargo/des1:Monto/com1:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </com1:tipo>
                        {
                            if ($Cargo/des1:Monto/com1:importe)
                            then <com1:importe>{fn:data($Cargo/des1:Monto/com1:importe)}</com1:importe>
                            else ()
                        }
                        {
                            if ($Cargo/des1:Monto/com1:moneda)
                            then 
                                <com1:moneda>
                                    {
                                        if ($Cargo/des1:Monto/com1:moneda/cat:Id)
                                        then <cat:Id>{fn:data($Cargo/des1:Monto/com1:moneda/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($Cargo/des1:Monto/com1:moneda/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($Cargo/des1:Monto/com1:moneda/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($Cargo/des1:Monto/com1:moneda/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($Cargo/des1:Monto/com1:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($Cargo/des1:Monto/com1:moneda/cat:estatus)
                                        then <cat:estatus>{fn:data($Cargo/des1:Monto/com1:moneda/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($Cargo/des1:Monto/com1:moneda/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($Cargo/des1:Monto/com1:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </com1:moneda>
                            else ()
                        }
                    </des1:Monto>
                    <des1:FechaRegistro>{fn:data($Cargo/des1:FechaRegistro)}</des1:FechaRegistro>
                    {
                        if ($Cargo/des1:Status)
                        then <des1:Status>{fn:data($Cargo/des1:Status)}</des1:Status>
                        else ()
                    }
                    {
                        if ($Cargo/des1:SoloLectura)
                        then <des1:SoloLectura>{fn:data($Cargo/des1:SoloLectura)}</des1:SoloLectura>
                        else ()
                    }
                    {
                        if ($Cargo/des1:TCC)
                        then 
                            <des1:TCC>
                                <mat:id>{fn:data($Cargo/des1:TCC/mat:id)}</mat:id>
                                <mat:tipo>{fn:data($Cargo/des1:TCC/mat:tipo)}</mat:tipo>
                                <mat:estado>{fn:data($Cargo/des1:TCC/mat:estado)}</mat:estado>
                                <mat:subEstado>{fn:data($Cargo/des1:TCC/mat:subEstado)}</mat:subEstado>
                            </des1:TCC>
                        else ()
                    }
                </des1:Cargo>
            }
            {
                for $Comision in $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Comision
                return 
                <des1:Comision>
                    <com2:idComision>{fn:data($Comision/com2:idComision)}</com2:idComision>
                    <com2:idOperacion>{fn:data($Comision/com2:idOperacion)}</com2:idOperacion>
                    {
                        if ($Comision/com2:nombre)
                        then <com2:nombre>{fn:data($Comision/com2:nombre)}</com2:nombre>
                        else ()
                    }
                    {
                        if ($Comision/com2:descripcion)
                        then <com2:descripcion>{fn:data($Comision/com2:descripcion)}</com2:descripcion>
                        else ()
                    }
                    {
                        if ($Comision/com2:tipoComision)
                        then 
                            <com2:tipoComision>
                                {
                                    if ($Comision/com2:tipoComision/com2:idCatComision)
                                    then <com2:idCatComision>{fn:data($Comision/com2:tipoComision/com2:idCatComision)}</com2:idCatComision>
                                    else ()
                                }
                                <com2:descripcion>{fn:data($Comision/com2:tipoComision/com2:descripcion)}</com2:descripcion>
                                <com2:descripcionCorta>{fn:data($Comision/com2:tipoComision/com2:descripcionCorta)}</com2:descripcionCorta>
                                {
                                    if ($Comision/com2:tipoComision/com2:idTipoComision)
                                    then 
                                        <com2:idTipoComision>
                                            {
                                                if ($Comision/com2:tipoComision/com2:idTipoComision/cat:Id)
                                                then <cat:Id>{fn:data($Comision/com2:tipoComision/com2:idTipoComision/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($Comision/com2:tipoComision/com2:idTipoComision/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($Comision/com2:tipoComision/com2:idTipoComision/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($Comision/com2:tipoComision/com2:idTipoComision/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($Comision/com2:tipoComision/com2:idTipoComision/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($Comision/com2:tipoComision/com2:idTipoComision/cat:estatus)
                                                then <cat:estatus>{fn:data($Comision/com2:tipoComision/com2:idTipoComision/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($Comision/com2:tipoComision/com2:idTipoComision/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($Comision/com2:tipoComision/com2:idTipoComision/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </com2:idTipoComision>
                                    else ()
                                }
                                <com2:esEditableEnFormalizacion>{fn:data($Comision/com2:tipoComision/com2:esEditableEnFormalizacion)}</com2:esEditableEnFormalizacion>
                                <com2:requiereValidarCOFI>{fn:data($Comision/com2:tipoComision/com2:requiereValidarCOFI)}</com2:requiereValidarCOFI>
                                <com2:sePuedeDispensar>{fn:data($Comision/com2:tipoComision/com2:sePuedeDispensar)}</com2:sePuedeDispensar>
                                <com2:seRegistraEnFlexCube>{fn:data($Comision/com2:tipoComision/com2:seRegistraEnFlexCube)}</com2:seRegistraEnFlexCube>
                                <com2:esPlantilla>{fn:data($Comision/com2:tipoComision/com2:esPlantilla)}</com2:esPlantilla>
                                <com2:idComisionPrecarga>{fn:data($Comision/com2:tipoComision/com2:idComisionPrecarga)}</com2:idComisionPrecarga>
                                <com2:fechaRegistro>{fn:data($Comision/com2:tipoComision/com2:fechaRegistro)}</com2:fechaRegistro>
                                <com2:estado>{fn:data($Comision/com2:tipoComision/com2:estado)}</com2:estado>
                                <com2:codigoExterno>{fn:data($Comision/com2:tipoComision/com2:codigoExterno)}</com2:codigoExterno>
                            </com2:tipoComision>
                        else ()
                    }
                    {
                        if ($Comision/com2:moneda)
                        then 
                            <com2:moneda>
                                {
                                    if ($Comision/com2:moneda/cat:Id)
                                    then <cat:Id>{fn:data($Comision/com2:moneda/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Comision/com2:moneda/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Comision/com2:moneda/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision/com2:moneda/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Comision/com2:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision/com2:moneda/cat:estatus)
                                    then <cat:estatus>{fn:data($Comision/com2:moneda/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision/com2:moneda/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Comision/com2:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com2:moneda>
                        else ()
                    }
                    {
                        if ($Comision/com2:montoComision)
                        then <com2:montoComision>{fn:data($Comision/com2:montoComision)}</com2:montoComision>
                        else ()
                    }
                    {
                        if ($Comision/com2:montoBase)
                        then 
                            <com2:montoBase>
                                {
                                    if ($Comision/com2:montoBase/com2:idMontoBase)
                                    then <com2:idMontoBase>{fn:data($Comision/com2:montoBase/com2:idMontoBase)}</com2:idMontoBase>
                                    else ()
                                }
                                {
                                    if ($Comision/com2:montoBase/com2:valorMontoBase)
                                    then <com2:valorMontoBase>{fn:data($Comision/com2:montoBase/com2:valorMontoBase)}</com2:valorMontoBase>
                                    else ()
                                }
                                {
                                    if ($Comision/com2:montoBase/com2:porcentajeMontoBase)
                                    then <com2:porcentajeMontoBase>{fn:data($Comision/com2:montoBase/com2:porcentajeMontoBase)}</com2:porcentajeMontoBase>
                                    else ()
                                }
                                {
                                    if ($Comision/com2:montoBase/com2:descripcionMontoBase)
                                    then <com2:descripcionMontoBase>{fn:data($Comision/com2:montoBase/com2:descripcionMontoBase)}</com2:descripcionMontoBase>
                                    else ()
                                }
                            </com2:montoBase>
                        else ()
                    }
                    {
                        if ($Comision/com2:fechaValor)
                        then <com2:fechaValor>{fn:data($Comision/com2:fechaValor)}</com2:fechaValor>
                        else ()
                    }
                    {
                        if ($Comision/com2:fechaVencimiento)
                        then <com2:fechaVencimiento>{fn:data($Comision/com2:fechaVencimiento)}</com2:fechaVencimiento>
                        else ()
                    }
                    {
                        if ($Comision/com2:fechaInicioCapital)
                        then <com2:fechaInicioCapital>{fn:data($Comision/com2:fechaInicioCapital)}</com2:fechaInicioCapital>
                        else ()
                    }
                    {
                        if ($Comision/com2:fechaInicioComision)
                        then <com2:fechaInicioComision>{fn:data($Comision/com2:fechaInicioComision)}</com2:fechaInicioComision>
                        else ()
                    }
                    {
                        if ($Comision/com2:tipoFrecuencia)
                        then 
                            <com2:tipoFrecuencia>
                                {
                                    if ($Comision/com2:tipoFrecuencia/cat:Id)
                                    then <cat:Id>{fn:data($Comision/com2:tipoFrecuencia/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Comision/com2:tipoFrecuencia/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Comision/com2:tipoFrecuencia/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision/com2:tipoFrecuencia/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Comision/com2:tipoFrecuencia/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision/com2:tipoFrecuencia/cat:estatus)
                                    then <cat:estatus>{fn:data($Comision/com2:tipoFrecuencia/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision/com2:tipoFrecuencia/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Comision/com2:tipoFrecuencia/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com2:tipoFrecuencia>
                        else ()
                    }
                    {
                        if ($Comision/com2:frecuenciaPago)
                        then <com2:frecuenciaPago>{fn:data($Comision/com2:frecuenciaPago)}</com2:frecuenciaPago>
                        else ()
                    }
                    {
                        if ($Comision/com2:fondo)
                        then <com2:fondo>{fn:data($Comision/com2:fondo)}</com2:fondo>
                        else ()
                    }
                    {
                        if ($Comision/com2:comisionCompartida)
                        then <com2:comisionCompartida>{fn:data($Comision/com2:comisionCompartida)}</com2:comisionCompartida>
                        else ()
                    }
                    {
                        if ($Comision/com2:codigoDesembolso)
                        then <com2:codigoDesembolso>{fn:data($Comision/com2:codigoDesembolso)}</com2:codigoDesembolso>
                        else ()
                    }
                    {
                        if ($Comision/com2:numeroTesoreria)
                        then <com2:numeroTesoreria>{fn:data($Comision/com2:numeroTesoreria)}</com2:numeroTesoreria>
                        else ()
                    }
                    {
                        if ($Comision/com2:codigoContrato)
                        then <com2:codigoContrato>{fn:data($Comision/com2:codigoContrato)}</com2:codigoContrato>
                        else ()
                    }
                    {
                        if ($Comision/com2:momentoCobro)
                        then 
                            <com2:momentoCobro>
                                {
                                    if ($Comision/com2:momentoCobro/cat:Id)
                                    then <cat:Id>{fn:data($Comision/com2:momentoCobro/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Comision/com2:momentoCobro/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Comision/com2:momentoCobro/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision/com2:momentoCobro/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Comision/com2:momentoCobro/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision/com2:momentoCobro/cat:estatus)
                                    then <cat:estatus>{fn:data($Comision/com2:momentoCobro/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision/com2:momentoCobro/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Comision/com2:momentoCobro/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com2:momentoCobro>
                        else ()
                    }
                    {
                        if ($Comision/com2:tipoTasa)
                        then 
                            <com2:tipoTasa>
                                {
                                    if ($Comision/com2:tipoTasa/cat:Id)
                                    then <cat:Id>{fn:data($Comision/com2:tipoTasa/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Comision/com2:tipoTasa/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Comision/com2:tipoTasa/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision/com2:tipoTasa/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Comision/com2:tipoTasa/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision/com2:tipoTasa/cat:estatus)
                                    then <cat:estatus>{fn:data($Comision/com2:tipoTasa/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision/com2:tipoTasa/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Comision/com2:tipoTasa/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com2:tipoTasa>
                        else ()
                    }
                    {
                        if ($Comision/com2:baseCalculo)
                        then 
                            <com2:baseCalculo>
                                {
                                    if ($Comision/com2:baseCalculo/cat:Id)
                                    then <cat:Id>{fn:data($Comision/com2:baseCalculo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Comision/com2:baseCalculo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Comision/com2:baseCalculo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision/com2:baseCalculo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Comision/com2:baseCalculo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision/com2:baseCalculo/cat:estatus)
                                    then <cat:estatus>{fn:data($Comision/com2:baseCalculo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision/com2:baseCalculo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Comision/com2:baseCalculo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com2:baseCalculo>
                        else ()
                    }
                    {
                        if ($Comision/com2:responsableComision)
                        then <com2:responsableComision>{fn:data($Comision/com2:responsableComision)}</com2:responsableComision>
                        else ()
                    }
                    <com2:estadoTCC>
                        {
                            if ($Comision/com2:estadoTCC/atr:id)
                            then <atr:id>{fn:data($Comision/com2:estadoTCC/atr:id)}</atr:id>
                            else ()
                        }
                        {
                            if ($Comision/com2:estadoTCC/atr:descripcion)
                            then <atr:descripcion>{fn:data($Comision/com2:estadoTCC/atr:descripcion)}</atr:descripcion>
                            else ()
                        }
                        {
                            if ($Comision/com2:estadoTCC/atr:descripcionCorta)
                            then <atr:descripcionCorta>{fn:data($Comision/com2:estadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                            else ()
                        }
                        {
                            if ($Comision/com2:estadoTCC/atr:estatus)
                            then <atr:estatus>{fn:data($Comision/com2:estadoTCC/atr:estatus)}</atr:estatus>
                            else ()
                        }
                        {
                            if ($Comision/com2:estadoTCC/atr:codigoExterno)
                            then <atr:codigoExterno>{fn:data($Comision/com2:estadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                            else ()
                        }
                        {
                            if ($Comision/com2:estadoTCC/atr:esSubEstado)
                            then <atr:esSubEstado>{fn:data($Comision/com2:estadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                            else ()
                        }
                    </com2:estadoTCC>
                    <com2:subEstadoTCC>
                        {
                            if ($Comision/com2:subEstadoTCC/atr:id)
                            then <atr:id>{fn:data($Comision/com2:subEstadoTCC/atr:id)}</atr:id>
                            else ()
                        }
                        {
                            if ($Comision/com2:subEstadoTCC/atr:descripcion)
                            then <atr:descripcion>{fn:data($Comision/com2:subEstadoTCC/atr:descripcion)}</atr:descripcion>
                            else ()
                        }
                        {
                            if ($Comision/com2:subEstadoTCC/atr:descripcionCorta)
                            then <atr:descripcionCorta>{fn:data($Comision/com2:subEstadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                            else ()
                        }
                        {
                            if ($Comision/com2:subEstadoTCC/atr:estatus)
                            then <atr:estatus>{fn:data($Comision/com2:subEstadoTCC/atr:estatus)}</atr:estatus>
                            else ()
                        }
                        {
                            if ($Comision/com2:subEstadoTCC/atr:codigoExterno)
                            then <atr:codigoExterno>{fn:data($Comision/com2:subEstadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                            else ()
                        }
                        {
                            if ($Comision/com2:subEstadoTCC/atr:esSubEstado)
                            then <atr:esSubEstado>{fn:data($Comision/com2:subEstadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                            else ()
                        }
                    </com2:subEstadoTCC>
                    <com2:fechaRegistro>{fn:data($Comision/com2:fechaRegistro)}</com2:fechaRegistro>
                    <com2:estado>{fn:data($Comision/com2:estado)}</com2:estado>
                    <com2:comisionEnmendada>{fn:data($Comision/com2:comisionEnmendada)}</com2:comisionEnmendada>
                    <com2:fechaEnmienda>{fn:data($Comision/com2:fechaEnmienda)}</com2:fechaEnmienda>
                    {
                        if ($Comision/com2:banSugerida)
                        then <com2:banSugerida>{fn:data($Comision/com2:banSugerida)}</com2:banSugerida>
                        else ()
                    }
                    {
                        if ($Comision/com2:numeroCuentaCliente)
                        then <com2:numeroCuentaCliente>{fn:data($Comision/com2:numeroCuentaCliente)}</com2:numeroCuentaCliente>
                        else ()
                    }
                    {
                        if ($Comision/com2:observaciones)
                        then <com2:observaciones>{fn:data($Comision/com2:observaciones)}</com2:observaciones>
                        else ()
                    }
                    {
                        for $configAtributo in $Comision/com2:configAtributo
                        return 
                        <com2:configAtributo>
                            {
                                if ($configAtributo/atr:id)
                                then <atr:id>{fn:data($configAtributo/atr:id)}</atr:id>
                                else ()
                            }
                            <atr:columna>{fn:data($configAtributo/atr:columna)}</atr:columna>
                            {
                                if ($configAtributo/atr:ordenamiento)
                                then <atr:ordenamiento>{fn:data($configAtributo/atr:ordenamiento)}</atr:ordenamiento>
                                else ()
                            }
                            {
                                if ($configAtributo/atr:soloLectura)
                                then <atr:soloLectura>{fn:data($configAtributo/atr:soloLectura)}</atr:soloLectura>
                                else ()
                            }
                            {
                                if ($configAtributo/atr:esObligatorio)
                                then <atr:esObligatorio>{fn:data($configAtributo/atr:esObligatorio)}</atr:esObligatorio>
                                else ()
                            }
                            {
                                if ($configAtributo/atr:etiqueta)
                                then <atr:etiqueta>{fn:data($configAtributo/atr:etiqueta)}</atr:etiqueta>
                                else ()
                            }
                            {
                                for $valor in $configAtributo/atr:valor
                                return 
                                <atr:valor>{fn:data($configAtributo/atr:valor)}</atr:valor>
                            }
                            {
                                if ($configAtributo/atr:tipoValor)
                                then <atr:tipoValor>{fn:data($configAtributo/atr:tipoValor)}</atr:tipoValor>
                                else ()
                            }
                            {
                                for $catalogo in $configAtributo/atr:catalogo
                                return 
                                <atr:catalogo>
                                    {
                                        if ($catalogo/cat:Id)
                                        then <cat:Id>{fn:data($catalogo/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($catalogo/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($catalogo/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($catalogo/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($catalogo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($catalogo/cat:estatus)
                                        then <cat:estatus>{fn:data($catalogo/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($catalogo/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($catalogo/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </atr:catalogo>
                            }
                        </com2:configAtributo>
                    }
                    {
                        if ($Comision/com2:Accion)
                        then <com2:Accion>{fn:data($Comision/com2:Accion)}</com2:Accion>
                        else ()
                    }
                </des1:Comision>
            }
            {
                for $Transferencia in $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:Transferencia
                return 
                <des1:Transferencia>
                    <des1:idTransferencia>{fn:data($Transferencia/des1:idTransferencia)}</des1:idTransferencia>
                    <des1:idDesembolso>{fn:data($Transferencia/des1:idDesembolso)}</des1:idDesembolso>
                    {
                        if ($Transferencia/des1:idFacturador)
                        then <des1:idFacturador>{fn:data($Transferencia/des1:idFacturador)}</des1:idFacturador>
                        else ()
                    }
                    <des1:Monto>
                        <com1:tipo>
                            {
                                if ($Transferencia/des1:Monto/com1:tipo/cat:Id)
                                then <cat:Id>{fn:data($Transferencia/des1:Monto/com1:tipo/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Transferencia/des1:Monto/com1:tipo/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Transferencia/des1:Monto/com1:tipo/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Transferencia/des1:Monto/com1:tipo/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Transferencia/des1:Monto/com1:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Transferencia/des1:Monto/com1:tipo/cat:estatus)
                                then <cat:estatus>{fn:data($Transferencia/des1:Monto/com1:tipo/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Transferencia/des1:Monto/com1:tipo/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Transferencia/des1:Monto/com1:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </com1:tipo>
                        {
                            if ($Transferencia/des1:Monto/com1:importe)
                            then <com1:importe>{fn:data($Transferencia/des1:Monto/com1:importe)}</com1:importe>
                            else ()
                        }
                        {
                            if ($Transferencia/des1:Monto/com1:moneda)
                            then 
                                <com1:moneda>
                                    {
                                        if ($Transferencia/des1:Monto/com1:moneda/cat:Id)
                                        then <cat:Id>{fn:data($Transferencia/des1:Monto/com1:moneda/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($Transferencia/des1:Monto/com1:moneda/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($Transferencia/des1:Monto/com1:moneda/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($Transferencia/des1:Monto/com1:moneda/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($Transferencia/des1:Monto/com1:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($Transferencia/des1:Monto/com1:moneda/cat:estatus)
                                        then <cat:estatus>{fn:data($Transferencia/des1:Monto/com1:moneda/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($Transferencia/des1:Monto/com1:moneda/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($Transferencia/des1:Monto/com1:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </com1:moneda>
                            else ()
                        }
                    </des1:Monto>
                    <des1:FormaTransferencia>
                        {
                            if ($Transferencia/des1:FormaTransferencia/cat:Id)
                            then <cat:Id>{fn:data($Transferencia/des1:FormaTransferencia/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($Transferencia/des1:FormaTransferencia/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($Transferencia/des1:FormaTransferencia/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($Transferencia/des1:FormaTransferencia/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($Transferencia/des1:FormaTransferencia/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($Transferencia/des1:FormaTransferencia/cat:estatus)
                            then <cat:estatus>{fn:data($Transferencia/des1:FormaTransferencia/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($Transferencia/des1:FormaTransferencia/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($Transferencia/des1:FormaTransferencia/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </des1:FormaTransferencia>
                    {
                        if ($Transferencia/des1:referenciaMensaje)
                        then <des1:referenciaMensaje>{fn:data($Transferencia/des1:referenciaMensaje)}</des1:referenciaMensaje>
                        else ()
                    }
                    {
                        if ($Transferencia/des1:esConsolidada)
                        then <des1:esConsolidada>{fn:data($Transferencia/des1:esConsolidada)}</des1:esConsolidada>
                        else ()
                    }
                    {
                        if ($Transferencia/des1:esConsolidacionPadre)
                        then <des1:esConsolidacionPadre>{fn:data($Transferencia/des1:esConsolidacionPadre)}</des1:esConsolidacionPadre>
                        else ()
                    }
                    {
                        if ($Transferencia/des1:idConsolidacionPadre)
                        then <des1:idConsolidacionPadre>{fn:data($Transferencia/des1:idConsolidacionPadre)}</des1:idConsolidacionPadre>
                        else ()
                    }
                    {
                        if ($Transferencia/des1:bhqTransferencia)
                        then <des1:bhqTransferencia>{fn:data($Transferencia/des1:bhqTransferencia)}</des1:bhqTransferencia>
                        else ()
                    }
                    {
                        if ($Transferencia/des1:NumeroReserva)
                        then <des1:NumeroReserva>{fn:data($Transferencia/des1:NumeroReserva)}</des1:NumeroReserva>
                        else ()
                    }
                    {
                        if ($Transferencia/des1:idBancoTransferencia)
                        then <des1:idBancoTransferencia>{fn:data($Transferencia/des1:idBancoTransferencia)}</des1:idBancoTransferencia>
                        else ()
                    }
                    {
                        if ($Transferencia/des1:numeroCuenta)
                        then <des1:numeroCuenta>{fn:data($Transferencia/des1:numeroCuenta)}</des1:numeroCuenta>
                        else ()
                    }
                    <des1:nombreCuenta>{fn:data($Transferencia/des1:nombreCuenta)}</des1:nombreCuenta>
                    {
                        if ($Transferencia/des1:nombreBanco)
                        then <des1:nombreBanco>{fn:data($Transferencia/des1:nombreBanco)}</des1:nombreBanco>
                        else ()
                    }
                    <des1:idOperacion>{fn:data($Transferencia/des1:idOperacion)}</des1:idOperacion>
                    <des1:tipoMensaje>{fn:data($Transferencia/des1:tipoMensaje)}</des1:tipoMensaje>
                    {
                        if ($Transferencia/des1:estado)
                        then <des1:estado>{fn:data($Transferencia/des1:estado)}</des1:estado>
                        else ()
                    }
                    <des1:fechaRegistro>{fn:data($Transferencia/des1:fechaRegistro)}</des1:fechaRegistro>
                    {
                        if ($Transferencia/des1:Beneficiario)
                        then 
                            <des1:Beneficiario>
                                {
                                    if ($Transferencia/des1:Beneficiario/des1:tipoOpcion)
                                    then <des1:tipoOpcion>{fn:data($Transferencia/des1:Beneficiario/des1:tipoOpcion)}</des1:tipoOpcion>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des1:Beneficiario/des1:numeroCta)
                                    then <des1:numeroCta>{fn:data($Transferencia/des1:Beneficiario/des1:numeroCta)}</des1:numeroCta>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des1:Beneficiario/des1:identificador)
                                    then <des1:identificador>{fn:data($Transferencia/des1:Beneficiario/des1:identificador)}</des1:identificador>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des1:Beneficiario/des1:beneficiario)
                                    then <des1:beneficiario>{fn:data($Transferencia/des1:Beneficiario/des1:beneficiario)}</des1:beneficiario>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des1:Beneficiario/des1:direccion)
                                    then <des1:direccion>{fn:data($Transferencia/des1:Beneficiario/des1:direccion)}</des1:direccion>
                                    else ()
                                }
                            </des1:Beneficiario>
                        else ()
                    }
                    {
                        if ($Transferencia/des1:Banco)
                        then 
                            <des1:Banco>
                                {
                                    if ($Transferencia/des1:Banco/des1:tipoOpcion)
                                    then <des1:tipoOpcion>{fn:data($Transferencia/des1:Banco/des1:tipoOpcion)}</des1:tipoOpcion>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des1:Banco/des1:numeroCta)
                                    then <des1:numeroCta>{fn:data($Transferencia/des1:Banco/des1:numeroCta)}</des1:numeroCta>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des1:Banco/des1:identificador)
                                    then <des1:identificador>{fn:data($Transferencia/des1:Banco/des1:identificador)}</des1:identificador>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des1:Banco/des1:beneficiario)
                                    then <des1:beneficiario>{fn:data($Transferencia/des1:Banco/des1:beneficiario)}</des1:beneficiario>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des1:Banco/des1:direccion)
                                    then <des1:direccion>{fn:data($Transferencia/des1:Banco/des1:direccion)}</des1:direccion>
                                    else ()
                                }
                            </des1:Banco>
                        else ()
                    }
                    {
                        if ($Transferencia/des1:Intermediario)
                        then 
                            <des1:Intermediario>
                                {
                                    if ($Transferencia/des1:Intermediario/des1:tipoOpcion)
                                    then <des1:tipoOpcion>{fn:data($Transferencia/des1:Intermediario/des1:tipoOpcion)}</des1:tipoOpcion>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des1:Intermediario/des1:numeroCta)
                                    then <des1:numeroCta>{fn:data($Transferencia/des1:Intermediario/des1:numeroCta)}</des1:numeroCta>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des1:Intermediario/des1:identificador)
                                    then <des1:identificador>{fn:data($Transferencia/des1:Intermediario/des1:identificador)}</des1:identificador>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des1:Intermediario/des1:beneficiario)
                                    then <des1:beneficiario>{fn:data($Transferencia/des1:Intermediario/des1:beneficiario)}</des1:beneficiario>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des1:Intermediario/des1:direccion)
                                    then <des1:direccion>{fn:data($Transferencia/des1:Intermediario/des1:direccion)}</des1:direccion>
                                    else ()
                                }
                            </des1:Intermediario>
                        else ()
                    }
                </des1:Transferencia>
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:TransferenciaFT05)
                then 
                    <des1:TransferenciaFT05>
                        <des1:idTransferenciaFT05>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:TransferenciaFT05/des1:idTransferenciaFT05)}</des1:idTransferenciaFT05>
                        <des1:idDesembolso>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:TransferenciaFT05/des1:idDesembolso)}</des1:idDesembolso>
                        {
                            if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:TransferenciaFT05/des1:idFacturador)
                            then <des1:idFacturador>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:TransferenciaFT05/des1:idFacturador)}</des1:idFacturador>
                            else ()
                        }
                        {
                            if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:TransferenciaFT05/des1:FechaEfectiva)
                            then <des1:FechaEfectiva>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:TransferenciaFT05/des1:FechaEfectiva)}</des1:FechaEfectiva>
                            else ()
                        }
                    </des1:TransferenciaFT05>
                else ()
            }
            {
                for $TransferenciaRecursos in $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:TransferenciaRecursos
                return 
                <des1:TransferenciaRecursos>
                    <des1:idTransferencia>{fn:data($TransferenciaRecursos/des1:idTransferencia)}</des1:idTransferencia>
                    <des1:idDesembolso>{fn:data($TransferenciaRecursos/des1:idDesembolso)}</des1:idDesembolso>
                    {
                        if ($TransferenciaRecursos/des1:idFacturador)
                        then <des1:idFacturador>{fn:data($TransferenciaRecursos/des1:idFacturador)}</des1:idFacturador>
                        else ()
                    }
                    <des1:idLineaPasiva>{fn:data($TransferenciaRecursos/des1:idLineaPasiva)}</des1:idLineaPasiva>
                    <des1:Monto>
                        <com1:tipo>
                            {
                                if ($TransferenciaRecursos/des1:Monto/com1:tipo/cat:Id)
                                then <cat:Id>{fn:data($TransferenciaRecursos/des1:Monto/com1:tipo/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($TransferenciaRecursos/des1:Monto/com1:tipo/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($TransferenciaRecursos/des1:Monto/com1:tipo/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($TransferenciaRecursos/des1:Monto/com1:tipo/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($TransferenciaRecursos/des1:Monto/com1:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($TransferenciaRecursos/des1:Monto/com1:tipo/cat:estatus)
                                then <cat:estatus>{fn:data($TransferenciaRecursos/des1:Monto/com1:tipo/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($TransferenciaRecursos/des1:Monto/com1:tipo/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($TransferenciaRecursos/des1:Monto/com1:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </com1:tipo>
                        {
                            if ($TransferenciaRecursos/des1:Monto/com1:importe)
                            then <com1:importe>{fn:data($TransferenciaRecursos/des1:Monto/com1:importe)}</com1:importe>
                            else ()
                        }
                        {
                            if ($TransferenciaRecursos/des1:Monto/com1:moneda)
                            then 
                                <com1:moneda>
                                    {
                                        if ($TransferenciaRecursos/des1:Monto/com1:moneda/cat:Id)
                                        then <cat:Id>{fn:data($TransferenciaRecursos/des1:Monto/com1:moneda/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($TransferenciaRecursos/des1:Monto/com1:moneda/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($TransferenciaRecursos/des1:Monto/com1:moneda/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($TransferenciaRecursos/des1:Monto/com1:moneda/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($TransferenciaRecursos/des1:Monto/com1:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($TransferenciaRecursos/des1:Monto/com1:moneda/cat:estatus)
                                        then <cat:estatus>{fn:data($TransferenciaRecursos/des1:Monto/com1:moneda/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($TransferenciaRecursos/des1:Monto/com1:moneda/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($TransferenciaRecursos/des1:Monto/com1:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </com1:moneda>
                            else ()
                        }
                    </des1:Monto>
                    <des1:fecha>{fn:data($TransferenciaRecursos/des1:fecha)}</des1:fecha>
                    <des1:fechaEfectiva>{fn:data($TransferenciaRecursos/des1:fechaEfectiva)}</des1:fechaEfectiva>
                    <des1:FormaTransferencia>
                        {
                            if ($TransferenciaRecursos/des1:FormaTransferencia/cat:Id)
                            then <cat:Id>{fn:data($TransferenciaRecursos/des1:FormaTransferencia/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($TransferenciaRecursos/des1:FormaTransferencia/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($TransferenciaRecursos/des1:FormaTransferencia/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($TransferenciaRecursos/des1:FormaTransferencia/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($TransferenciaRecursos/des1:FormaTransferencia/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($TransferenciaRecursos/des1:FormaTransferencia/cat:estatus)
                            then <cat:estatus>{fn:data($TransferenciaRecursos/des1:FormaTransferencia/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($TransferenciaRecursos/des1:FormaTransferencia/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($TransferenciaRecursos/des1:FormaTransferencia/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </des1:FormaTransferencia>
                    {
                        if ($TransferenciaRecursos/des1:idBanco)
                        then <des1:idBanco>{fn:data($TransferenciaRecursos/des1:idBanco)}</des1:idBanco>
                        else ()
                    }
                    {
                        if ($TransferenciaRecursos/des1:nombreBanco)
                        then <des1:nombreBanco>{fn:data($TransferenciaRecursos/des1:nombreBanco)}</des1:nombreBanco>
                        else ()
                    }
                    {
                        if ($TransferenciaRecursos/des1:numeroCuenta)
                        then <des1:numeroCuenta>{fn:data($TransferenciaRecursos/des1:numeroCuenta)}</des1:numeroCuenta>
                        else ()
                    }
                    {
                        if ($TransferenciaRecursos/des1:nombreCuenta)
                        then <des1:nombreCuenta>{fn:data($TransferenciaRecursos/des1:nombreCuenta)}</des1:nombreCuenta>
                        else ()
                    }
                    {
                        if ($TransferenciaRecursos/des1:estatus)
                        then <des1:estatus>{fn:data($TransferenciaRecursos/des1:estatus)}</des1:estatus>
                        else ()
                    }
                    {
                        if ($TransferenciaRecursos/des1:fechaRegistro)
                        then <des1:fechaRegistro>{fn:data($TransferenciaRecursos/des1:fechaRegistro)}</des1:fechaRegistro>
                        else ()
                    }
                </des1:TransferenciaRecursos>
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE)
                then 
                    <des1:HerramientaCE>
                        <her:ActividadEconomica>
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:ActividadEconomica/cat:Id)
                                then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:ActividadEconomica/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:ActividadEconomica/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:ActividadEconomica/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:ActividadEconomica/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:ActividadEconomica/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:ActividadEconomica/cat:estatus)
                                then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:ActividadEconomica/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:ActividadEconomica>
                        <her:EjeEstrategico>
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:EjeEstrategico/cat:Id)
                                then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:EjeEstrategico/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:EjeEstrategico/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:EjeEstrategico/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:EjeEstrategico/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:EjeEstrategico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:EjeEstrategico/cat:estatus)
                                then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:EjeEstrategico/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:EjeEstrategico>
                        <her:AreaFocalizacion>
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:AreaFocalizacion/cat:Id)
                                then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:AreaFocalizacion/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:AreaFocalizacion/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:AreaFocalizacion/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:AreaFocalizacion/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:AreaFocalizacion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:AreaFocalizacion/cat:estatus)
                                then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:AreaFocalizacion/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:AreaFocalizacion>
                    </des1:HerramientaCE>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:modalidadFinan)
                then 
                    <des1:modalidadFinan>
                        {
                            if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:modalidadFinan/cat:Id)
                            then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:modalidadFinan/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:modalidadFinan/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:modalidadFinan/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:modalidadFinan/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:modalidadFinan/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:modalidadFinan/cat:estatus)
                            then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:modalidadFinan/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:modalidadFinan/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:modalidadFinan/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </des1:modalidadFinan>
                else ()
            }
        </des:ContratoDesembolso>
        </des:CrearDesembolsoComplejoV2FLEXCUBERequest>
};

local:funcXq_requestcreadesembolsocomplejov2flexcube($outConsultarInformacionDesembolso.response, $outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse, $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response)
