xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V2/Schema/DesembolsoMOV2.xsd", 
                     "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

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

declare namespace des2 = "http://www.bcie.org/DesembolsoBOV2";

declare namespace xsi="http://www.w3.org/2001/XMLSchema-instance";

declare variable $outConsultarInformacionDesembolso.response as element() (:: schema-element(des:ConsultarInformacionReglasByIdResponse) ::) external;
declare variable $outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse as element() (:: schema-element(lin:ConsultarLineaCreditoBPELResponse) ::) external;
declare variable $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response as element() (:: schema-element(des:ConsultarDesembolsosByIdV2Response) ::) external;

declare function local:funcXq_requestvalidarloancomplejo($outConsultarInformacionDesembolso.response as element() (:: schema-element(des:ConsultarInformacionReglasByIdResponse) ::), 
                                                         $outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse as element() (:: schema-element(lin:ConsultarLineaCreditoBPELResponse) ::), 
                                                         $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response as element() (:: schema-element(des:ConsultarDesembolsosByIdV2Response) ::)) 
                                                         as element() (:: schema-element(des:ValidarLoanComplejoRequest) ::) {
    <des:ValidarLoanComplejoRequest>
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
                if ($outConsultarInformacionDesembolso.response/des:Cliente/cli:bicCode)
                then <cli:bicCode>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:bicCode)}</cli:bicCode>
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
            {
                <lin1:idOperacion>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:idOperacion)}</lin1:idOperacion>
            }
            <lin1:NumeroLineaCredito>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:NumeroLineaCredito)}</lin1:NumeroLineaCredito>
            <lin1:Descripcion>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:Descripcion)}</lin1:Descripcion>
            {
                <lin1:Flexcube>
                        {
                            if ($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:Flexcube/lin1:id)
                            then <lin1:id>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:Flexcube/lin1:id)}</lin1:id>
                            else ()
                        }
                        {
                            if ($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:Flexcube/lin1:idProductoFlexcube)
                            then <lin1:idProductoFlexcube>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:Flexcube/lin1:idProductoFlexcube)}</lin1:idProductoFlexcube>
                            else ()
                        }
                        {
                            if ($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:Flexcube/lin1:idFlexcubePasivo)
                            then <lin1:idFlexcubePasivo>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:Flexcube/lin1:idFlexcubePasivo)}</lin1:idFlexcubePasivo>
                            else ()
                        }
                    </lin1:Flexcube>
            }
            {
                <lin1:Fondo>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:Fondo)}</lin1:Fondo>
            }
            {
                <lin1:IdTipoMonedaMontoLinea>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:IdTipoMonedaMontoLinea)}</lin1:IdTipoMonedaMontoLinea>
            }
            {
                <lin1:MontoLinea>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:MontoLinea)}</lin1:MontoLinea>
            }
            {
                <lin1:saldo>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:saldo)}</lin1:saldo>
            }
            {
                <lin1:Moneda>
                        {
                            if ($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:Moneda/cat:Id)
                            then <cat:Id>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:Moneda/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:Moneda/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:Moneda/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:Moneda/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:Moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:Moneda/cat:estatus)
                            then <cat:estatus>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:Moneda/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:Moneda/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:Moneda/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </lin1:Moneda>
            }
            {
                <lin1:EsRevolvente>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:EsRevolvente)}</lin1:EsRevolvente>
            }
            <lin1:TratamientoDiasFeriados>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:TratamientoDiasFeriados)}</lin1:TratamientoDiasFeriados>
            <lin1:FechaValor>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:FechaValor)}</lin1:FechaValor>
            <lin1:FechaVencimiento>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:FechaVencimiento)}</lin1:FechaVencimiento>
            <lin1:CodigoPantallaLimite>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:CodigoPantallaLimite)}</lin1:CodigoPantallaLimite>
            <lin1:CodigoSerialLimite>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:CodigoSerialLimite)}</lin1:CodigoSerialLimite>
            {
                <lin1:CodigoAsignacion>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:CodigoAsignacion)}</lin1:CodigoAsignacion>
            }
            <lin1:DescripcionAsignacion>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:DescripcionAsignacion)}</lin1:DescripcionAsignacion>
            {
                <lin1:CondicionEspecial>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:CondicionEspecial)}</lin1:CondicionEspecial>
            }
            <lin1:FechaRegistro>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:FechaRegistro)}</lin1:FechaRegistro>
            <lin1:FechaMaximaDesembolso>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:FechaMaximaDesembolso)}</lin1:FechaMaximaDesembolso>
            {
                <lin1:Estado>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:Estado)}</lin1:Estado>
            }
            {
                for $Termino in $outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:Termino
                return 
                <lin1:Termino>
                    <ter:idTermino>{fn:data($Termino/ter:idTermino)}</ter:idTermino>
                    <ter:operacion>{fn:data($Termino/ter:operacion)}</ter:operacion>
                    <ter:nombre>{fn:data($Termino/ter:nombre)}</ter:nombre>
                    <ter:descripcion>{fn:data($Termino/ter:descripcion)}</ter:descripcion>
                    <ter:tipoTermino>
                        {
                            if ($Termino/ter:tipoTermino/ter:idCatTermino)
                            then <ter:idCatTermino>{fn:data($Termino/ter:tipoTermino/ter:idCatTermino)}</ter:idCatTermino>
                            else ()
                        }
                        <ter:descripcion>{fn:data($Termino/ter:tipoTermino/ter:descripcion)}</ter:descripcion>
                        <ter:descripcionCorta>{fn:data($Termino/ter:tipoTermino/ter:descripcionCorta)}</ter:descripcionCorta>
                        {
                            if ($Termino/ter:tipoTermino/ter:idTipoTermino)
                            then <ter:idTipoTermino>{fn:data($Termino/ter:tipoTermino/ter:idTipoTermino)}</ter:idTipoTermino>
                            else ()
                        }
                        <ter:esEditableEnFormalizacion>{fn:data($Termino/ter:tipoTermino/ter:esEditableEnFormalizacion)}</ter:esEditableEnFormalizacion>
                        <ter:requiereValidarCOFI>{fn:data($Termino/ter:tipoTermino/ter:requiereValidarCOFI)}</ter:requiereValidarCOFI>
                        <ter:sePuedeDispensar>{fn:data($Termino/ter:tipoTermino/ter:sePuedeDispensar)}</ter:sePuedeDispensar>
                        <ter:esPlantilla>{fn:data($Termino/ter:tipoTermino/ter:esPlantilla)}</ter:esPlantilla>
                        <ter:requiereOGC>{fn:data($Termino/ter:tipoTermino/ter:requiereOGC)}</ter:requiereOGC>
                        <ter:idTerminoPrecarga>{fn:data($Termino/ter:tipoTermino/ter:idTerminoPrecarga)}</ter:idTerminoPrecarga>
                        <ter:fechaRegistro>{fn:data($Termino/ter:tipoTermino/ter:fechaRegistro)}</ter:fechaRegistro>
                        <ter:estado>{fn:data($Termino/ter:tipoTermino/ter:estado)}</ter:estado>
                        <ter:codigoExterno>{fn:data($Termino/ter:tipoTermino/ter:codigoExterno)}</ter:codigoExterno>
                    </ter:tipoTermino>
                    <ter:tipoFechaInicio>
                        {
                            if ($Termino/ter:tipoFechaInicio/cat:Id)
                            then <cat:Id>{fn:data($Termino/ter:tipoFechaInicio/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFechaInicio/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($Termino/ter:tipoFechaInicio/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFechaInicio/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($Termino/ter:tipoFechaInicio/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFechaInicio/cat:estatus)
                            then <cat:estatus>{fn:data($Termino/ter:tipoFechaInicio/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFechaInicio/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($Termino/ter:tipoFechaInicio/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ter:tipoFechaInicio>
                    <ter:fechaInicio>{fn:data($Termino/ter:fechaInicio)}</ter:fechaInicio>
                    <ter:plazo>{fn:data($Termino/ter:plazo)}</ter:plazo>
                    <ter:frecuenciaPlazo>
                        {
                            if ($Termino/ter:frecuenciaPlazo/cat:Id)
                            then <cat:Id>{fn:data($Termino/ter:frecuenciaPlazo/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($Termino/ter:frecuenciaPlazo/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($Termino/ter:frecuenciaPlazo/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($Termino/ter:frecuenciaPlazo/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($Termino/ter:frecuenciaPlazo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($Termino/ter:frecuenciaPlazo/cat:estatus)
                            then <cat:estatus>{fn:data($Termino/ter:frecuenciaPlazo/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($Termino/ter:frecuenciaPlazo/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($Termino/ter:frecuenciaPlazo/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ter:frecuenciaPlazo>
                    <ter:fechaVencimiento>{fn:data($Termino/ter:fechaVencimiento)}</ter:fechaVencimiento>
                    <ter:moneda>
                        {
                            if ($Termino/ter:moneda/cat:Id)
                            then <cat:Id>{fn:data($Termino/ter:moneda/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($Termino/ter:moneda/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($Termino/ter:moneda/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($Termino/ter:moneda/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($Termino/ter:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($Termino/ter:moneda/cat:estatus)
                            then <cat:estatus>{fn:data($Termino/ter:moneda/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($Termino/ter:moneda/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($Termino/ter:moneda/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ter:moneda>
                    <ter:monto>{fn:data($Termino/ter:monto)}</ter:monto>
                    <ter:tasa>{fn:data($Termino/ter:tasa)}</ter:tasa>
                    <ter:tipoTasa>
                        {
                            if ($Termino/ter:tipoTasa/cat:Id)
                            then <cat:Id>{fn:data($Termino/ter:tipoTasa/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoTasa/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($Termino/ter:tipoTasa/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoTasa/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($Termino/ter:tipoTasa/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoTasa/cat:estatus)
                            then <cat:estatus>{fn:data($Termino/ter:tipoTasa/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoTasa/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($Termino/ter:tipoTasa/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ter:tipoTasa>
                    <ter:fecha>{fn:data($Termino/ter:fecha)}</ter:fecha>
                    <ter:frecuenciaRevision>{fn:data($Termino/ter:frecuenciaRevision)}</ter:frecuenciaRevision>
                    <ter:tipoFrecuenciaRevision>
                        {
                            if ($Termino/ter:tipoFrecuenciaRevision/cat:Id)
                            then <cat:Id>{fn:data($Termino/ter:tipoFrecuenciaRevision/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFrecuenciaRevision/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($Termino/ter:tipoFrecuenciaRevision/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFrecuenciaRevision/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($Termino/ter:tipoFrecuenciaRevision/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFrecuenciaRevision/cat:estatus)
                            then <cat:estatus>{fn:data($Termino/ter:tipoFrecuenciaRevision/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFrecuenciaRevision/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($Termino/ter:tipoFrecuenciaRevision/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ter:tipoFrecuenciaRevision>
                    <ter:fechaInicioRevision>{fn:data($Termino/ter:fechaInicioRevision)}</ter:fechaInicioRevision>
                    <ter:frecuenciaPagoInteres>{fn:data($Termino/ter:frecuenciaPagoInteres)}</ter:frecuenciaPagoInteres>
                    <ter:tipoFrecuenciaPagoInteres>
                        {
                            if ($Termino/ter:tipoFrecuenciaPagoInteres/cat:Id)
                            then <cat:Id>{fn:data($Termino/ter:tipoFrecuenciaPagoInteres/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFrecuenciaPagoInteres/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($Termino/ter:tipoFrecuenciaPagoInteres/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFrecuenciaPagoInteres/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($Termino/ter:tipoFrecuenciaPagoInteres/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFrecuenciaPagoInteres/cat:estatus)
                            then <cat:estatus>{fn:data($Termino/ter:tipoFrecuenciaPagoInteres/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFrecuenciaPagoInteres/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($Termino/ter:tipoFrecuenciaPagoInteres/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ter:tipoFrecuenciaPagoInteres>
                    <ter:fechaInicioPagoInteres>{fn:data($Termino/ter:fechaInicioPagoInteres)}</ter:fechaInicioPagoInteres>
                    <ter:frecuenciaAmortizacion>{fn:data($Termino/ter:frecuenciaAmortizacion)}</ter:frecuenciaAmortizacion>
                    <ter:tipoFrecuenciaAmortizacion>
                        {
                            if ($Termino/ter:tipoFrecuenciaAmortizacion/cat:Id)
                            then <cat:Id>{fn:data($Termino/ter:tipoFrecuenciaAmortizacion/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFrecuenciaAmortizacion/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($Termino/ter:tipoFrecuenciaAmortizacion/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFrecuenciaAmortizacion/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($Termino/ter:tipoFrecuenciaAmortizacion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFrecuenciaAmortizacion/cat:estatus)
                            then <cat:estatus>{fn:data($Termino/ter:tipoFrecuenciaAmortizacion/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFrecuenciaAmortizacion/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($Termino/ter:tipoFrecuenciaAmortizacion/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ter:tipoFrecuenciaAmortizacion>
                    <ter:mora>{fn:data($Termino/ter:mora)}</ter:mora>
                    <ter:porcentajeCobertura>{fn:data($Termino/ter:porcentajeCobertura)}</ter:porcentajeCobertura>
                    <ter:seAplicanRecursosConcesion>{fn:data($Termino/ter:seAplicanRecursosConcesion)}</ter:seAplicanRecursosConcesion>
                    <ter:seAplicanRecursosExternos>{fn:data($Termino/ter:seAplicanRecursosExternos)}</ter:seAplicanRecursosExternos>
                    <ter:tipoContraparte>{fn:data($Termino/ter:tipoContraparte)}</ter:tipoContraparte>
                    <ter:montoMinimoDesembolso>{fn:data($Termino/ter:montoMinimoDesembolso)}</ter:montoMinimoDesembolso>
                    <ter:montoMaximoDesembolso>{fn:data($Termino/ter:montoMaximoDesembolso)}</ter:montoMaximoDesembolso>
                    <ter:tasaMinimaDesembolso>{fn:data($Termino/ter:tasaMinimaDesembolso)}</ter:tasaMinimaDesembolso>
                    <ter:tasaMaximaDesembolso>{fn:data($Termino/ter:tasaMaximaDesembolso)}</ter:tasaMaximaDesembolso>
                    <ter:estadoTCC>
                        {
                            if ($Termino/ter:estadoTCC/atr:id)
                            then <atr:id>{fn:data($Termino/ter:estadoTCC/atr:id)}</atr:id>
                            else ()
                        }
                        {
                            if ($Termino/ter:estadoTCC/atr:descripcion)
                            then <atr:descripcion>{fn:data($Termino/ter:estadoTCC/atr:descripcion)}</atr:descripcion>
                            else ()
                        }
                        {
                            if ($Termino/ter:estadoTCC/atr:descripcionCorta)
                            then <atr:descripcionCorta>{fn:data($Termino/ter:estadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                            else ()
                        }
                        {
                            if ($Termino/ter:estadoTCC/atr:estatus)
                            then <atr:estatus>{fn:data($Termino/ter:estadoTCC/atr:estatus)}</atr:estatus>
                            else ()
                        }
                        {
                            if ($Termino/ter:estadoTCC/atr:codigoExterno)
                            then <atr:codigoExterno>{fn:data($Termino/ter:estadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                            else ()
                        }
                        {
                            if ($Termino/ter:estadoTCC/atr:esSubEstado)
                            then <atr:esSubEstado>{fn:data($Termino/ter:estadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                            else ()
                        }
                    </ter:estadoTCC>
                    <ter:subEstadoTCC>
                        {
                            if ($Termino/ter:subEstadoTCC/atr:id)
                            then <atr:id>{fn:data($Termino/ter:subEstadoTCC/atr:id)}</atr:id>
                            else ()
                        }
                        {
                            if ($Termino/ter:subEstadoTCC/atr:descripcion)
                            then <atr:descripcion>{fn:data($Termino/ter:subEstadoTCC/atr:descripcion)}</atr:descripcion>
                            else ()
                        }
                        {
                            if ($Termino/ter:subEstadoTCC/atr:descripcionCorta)
                            then <atr:descripcionCorta>{fn:data($Termino/ter:subEstadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                            else ()
                        }
                        {
                            if ($Termino/ter:subEstadoTCC/atr:estatus)
                            then <atr:estatus>{fn:data($Termino/ter:subEstadoTCC/atr:estatus)}</atr:estatus>
                            else ()
                        }
                        {
                            if ($Termino/ter:subEstadoTCC/atr:codigoExterno)
                            then <atr:codigoExterno>{fn:data($Termino/ter:subEstadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                            else ()
                        }
                        {
                            if ($Termino/ter:subEstadoTCC/atr:esSubEstado)
                            then <atr:esSubEstado>{fn:data($Termino/ter:subEstadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                            else ()
                        }
                    </ter:subEstadoTCC>
                    <ter:fechaRegistro>{fn:data($Termino/ter:fechaRegistro)}</ter:fechaRegistro>
                    <ter:estado>{fn:data($Termino/ter:estado)}</ter:estado>
                    <ter:terminoEnmendado>{fn:data($Termino/ter:terminoEnmendado)}</ter:terminoEnmendado>
                    <ter:fechaEnmienda>{fn:data($Termino/ter:fechaEnmienda)}</ter:fechaEnmienda>
                    <ter:porcentajeModificacion>{fn:data($Termino/ter:porcentajeModificacion)}</ter:porcentajeModificacion>
                    <ter:idTcaTipoAvance>{fn:data($Termino/ter:idTcaTipoAvance)}</ter:idTcaTipoAvance>
                    <ter:idTcaTipoPorcentaje>{fn:data($Termino/ter:idTcaTipoPorcentaje)}</ter:idTcaTipoPorcentaje>
                    <ter:porcentaje>{fn:data($Termino/ter:porcentaje)}</ter:porcentaje>
                    <ter:porcentajeInicial>{fn:data($Termino/ter:porcentajeInicial)}</ter:porcentajeInicial>
                    <ter:porcentajeFinal>{fn:data($Termino/ter:porcentajeFinal)}</ter:porcentajeFinal>
                    {
                        if ($Termino/ter:loginUsuario)
                        then <ter:loginUsuario>{fn:data($Termino/ter:loginUsuario)}</ter:loginUsuario>
                        else ()
                    }
                    {
                        if ($Termino/ter:loginUsuarioUltimoCambio)
                        then <ter:loginUsuarioUltimoCambio>{fn:data($Termino/ter:loginUsuarioUltimoCambio)}</ter:loginUsuarioUltimoCambio>
                        else ()
                    }
                    {
                        if ($Termino/ter:nombreUsuario)
                        then <ter:nombreUsuario>{fn:data($Termino/ter:nombreUsuario)}</ter:nombreUsuario>
                        else ()
                    }
                    {
                        if ($Termino/ter:nombreUsuarioUltimoCambio)
                        then <ter:nombreUsuarioUltimoCambio>{fn:data($Termino/ter:nombreUsuarioUltimoCambio)}</ter:nombreUsuarioUltimoCambio>
                        else ()
                    }
                    {
                        if ($Termino/ter:fechaUltimoCambio)
                        then <ter:fechaUltimoCambio>{fn:data($Termino/ter:fechaUltimoCambio)}</ter:fechaUltimoCambio>
                        else ()
                    }
                    {
                        if ($Termino/ter:requiereFondoPresupuestario)
                        then <ter:requiereFondoPresupuestario>{fn:data($Termino/ter:requiereFondoPresupuestario)}</ter:requiereFondoPresupuestario>
                        else ()
                    }
                    {
                        for $configAtributo1 in $Termino/ter:configAtributo
                        return 
                        <ter:configAtributo>
                            {
                                if ($configAtributo1/atr:id)
                                then <atr:id>{fn:data($configAtributo1/atr:id)}</atr:id>
                                else ()
                            }
                            <atr:columna>{fn:data($configAtributo1/atr:columna)}</atr:columna>
                            {
                                if ($configAtributo1/atr:ordenamiento)
                                then <atr:ordenamiento>{fn:data($configAtributo1/atr:ordenamiento)}</atr:ordenamiento>
                                else ()
                            }
                            {
                                if ($configAtributo1/atr:soloLectura)
                                then <atr:soloLectura>{fn:data($configAtributo1/atr:soloLectura)}</atr:soloLectura>
                                else ()
                            }
                            {
                                if ($configAtributo1/atr:esObligatorio)
                                then <atr:esObligatorio>{fn:data($configAtributo1/atr:esObligatorio)}</atr:esObligatorio>
                                else ()
                            }
                            {
                                if ($configAtributo1/atr:etiqueta)
                                then <atr:etiqueta>{fn:data($configAtributo1/atr:etiqueta)}</atr:etiqueta>
                                else ()
                            }
                            {
                                for $valor1 in $configAtributo1/atr:valor
                                return 
                                <atr:valor>{fn:data($configAtributo1/atr:valor)}</atr:valor>
                            }
                            {
                                if ($configAtributo1/atr:tipoValor)
                                then <atr:tipoValor>{fn:data($configAtributo1/atr:tipoValor)}</atr:tipoValor>
                                else ()
                            }
                            {
                                for $catalogo1 in $configAtributo1/atr:catalogo
                                return 
                                <atr:catalogo>
                                    {
                                        if ($catalogo1/cat:Id)
                                        then <cat:Id>{fn:data($catalogo1/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($catalogo1/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($catalogo1/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($catalogo1/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($catalogo1/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($catalogo1/cat:estatus)
                                        then <cat:estatus>{fn:data($catalogo1/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($catalogo1/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($catalogo1/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </atr:catalogo>
                            }
                        </ter:configAtributo>
                    }
                    {
                        if ($Termino/ter:Accion)
                        then <ter:Accion>{fn:data($Termino/ter:Accion)}</ter:Accion>
                        else ()
                    }
                    {
                        for $Contraparte in $Termino/ter:Contraparte
                        return 
                        <ter:Contraparte>
                            {
                                if ($Contraparte/atr:id)
                                then <atr:id>{fn:data($Contraparte/atr:id)}</atr:id>
                                else ()
                            }
                            {
                                if ($Contraparte/atr:descripcion)
                                then <atr:descripcion>{fn:data($Contraparte/atr:descripcion)}</atr:descripcion>
                                else ()
                            }
                            {
                                if ($Contraparte/atr:estatus)
                                then <atr:estatus>{fn:data($Contraparte/atr:estatus)}</atr:estatus>
                                else ()
                            }
                            {
                                if ($Contraparte/atr:esPorcentaje)
                                then <atr:esPorcentaje>{fn:data($Contraparte/atr:esPorcentaje)}</atr:esPorcentaje>
                                else ()
                            }
                            {
                                if ($Contraparte/atr:difValor)
                                then <atr:difValor>{fn:data($Contraparte/atr:difValor)}</atr:difValor>
                                else ()
                            }
                            {
                                if ($Contraparte/atr:difPorcentaje)
                                then <atr:difPorcentaje>{fn:data($Contraparte/atr:difPorcentaje)}</atr:difPorcentaje>
                                else ()
                            }
                        </ter:Contraparte>
                    }
                </lin1:Termino>
            }
            {
                for $Comision in $outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:Comision
                return 
                <lin1:Comision>
                    <com1:idComision>{fn:data($Comision/com1:idComision)}</com1:idComision>
                    <com1:idOperacion>{fn:data($Comision/com1:idOperacion)}</com1:idOperacion>
                    {
                        if ($Comision/com1:nombre)
                        then <com1:nombre>{fn:data($Comision/com1:nombre)}</com1:nombre>
                        else ()
                    }
                    {
                        if ($Comision/com1:descripcion)
                        then <com1:descripcion>{fn:data($Comision/com1:descripcion)}</com1:descripcion>
                        else ()
                    }
                    {
                        if ($Comision/com1:tipoComision)
                        then 
                            <com1:tipoComision>
                                {
                                    if ($Comision/com1:tipoComision/com1:idCatComision)
                                    then <com1:idCatComision>{fn:data($Comision/com1:tipoComision/com1:idCatComision)}</com1:idCatComision>
                                    else ()
                                }
                                <com1:descripcion>{fn:data($Comision/com1:tipoComision/com1:descripcion)}</com1:descripcion>
                                <com1:descripcionCorta>{fn:data($Comision/com1:tipoComision/com1:descripcionCorta)}</com1:descripcionCorta>
                                {
                                    if ($Comision/com1:tipoComision/com1:idTipoComision)
                                    then 
                                        <com1:idTipoComision>
                                            {
                                                if ($Comision/com1:tipoComision/com1:idTipoComision/cat:Id)
                                                then <cat:Id>{fn:data($Comision/com1:tipoComision/com1:idTipoComision/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($Comision/com1:tipoComision/com1:idTipoComision/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($Comision/com1:tipoComision/com1:idTipoComision/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($Comision/com1:tipoComision/com1:idTipoComision/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($Comision/com1:tipoComision/com1:idTipoComision/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($Comision/com1:tipoComision/com1:idTipoComision/cat:estatus)
                                                then <cat:estatus>{fn:data($Comision/com1:tipoComision/com1:idTipoComision/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($Comision/com1:tipoComision/com1:idTipoComision/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($Comision/com1:tipoComision/com1:idTipoComision/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </com1:idTipoComision>
                                    else ()
                                }
                                <com1:esEditableEnFormalizacion>{fn:data($Comision/com1:tipoComision/com1:esEditableEnFormalizacion)}</com1:esEditableEnFormalizacion>
                                <com1:requiereValidarCOFI>{fn:data($Comision/com1:tipoComision/com1:requiereValidarCOFI)}</com1:requiereValidarCOFI>
                                <com1:sePuedeDispensar>{fn:data($Comision/com1:tipoComision/com1:sePuedeDispensar)}</com1:sePuedeDispensar>
                                <com1:seRegistraEnFlexCube>{fn:data($Comision/com1:tipoComision/com1:seRegistraEnFlexCube)}</com1:seRegistraEnFlexCube>
                                <com1:esPlantilla>{fn:data($Comision/com1:tipoComision/com1:esPlantilla)}</com1:esPlantilla>
                                <com1:idComisionPrecarga>{fn:data($Comision/com1:tipoComision/com1:idComisionPrecarga)}</com1:idComisionPrecarga>
                                <com1:fechaRegistro>{fn:data($Comision/com1:tipoComision/com1:fechaRegistro)}</com1:fechaRegistro>
                                <com1:estado>{fn:data($Comision/com1:tipoComision/com1:estado)}</com1:estado>
                                <com1:codigoExterno>{fn:data($Comision/com1:tipoComision/com1:codigoExterno)}</com1:codigoExterno>
                            </com1:tipoComision>
                        else ()
                    }
                    {
                        if ($Comision/com1:moneda)
                        then 
                            <com1:moneda>
                                {
                                    if ($Comision/com1:moneda/cat:Id)
                                    then <cat:Id>{fn:data($Comision/com1:moneda/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:moneda/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Comision/com1:moneda/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:moneda/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Comision/com1:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:moneda/cat:estatus)
                                    then <cat:estatus>{fn:data($Comision/com1:moneda/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:moneda/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Comision/com1:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com1:moneda>
                        else ()
                    }
                    {
                        if ($Comision/com1:montoComision)
                        then <com1:montoComision>{fn:data($Comision/com1:montoComision)}</com1:montoComision>
                        else ()
                    }
                    {
                        if ($Comision/com1:montoBase)
                        then 
                            <com1:montoBase>
                                {
                                    if ($Comision/com1:montoBase/com1:idMontoBase)
                                    then <com1:idMontoBase>{fn:data($Comision/com1:montoBase/com1:idMontoBase)}</com1:idMontoBase>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:montoBase/com1:valorMontoBase)
                                    then <com1:valorMontoBase>{fn:data($Comision/com1:montoBase/com1:valorMontoBase)}</com1:valorMontoBase>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:montoBase/com1:porcentajeMontoBase)
                                    then <com1:porcentajeMontoBase>{fn:data($Comision/com1:montoBase/com1:porcentajeMontoBase)}</com1:porcentajeMontoBase>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:montoBase/com1:descripcionMontoBase)
                                    then <com1:descripcionMontoBase>{fn:data($Comision/com1:montoBase/com1:descripcionMontoBase)}</com1:descripcionMontoBase>
                                    else ()
                                }
                            </com1:montoBase>
                        else ()
                    }
                    {
                        if ($Comision/com1:fechaValor)
                        then <com1:fechaValor>{fn:data($Comision/com1:fechaValor)}</com1:fechaValor>
                        else ()
                    }
                    {
                        if ($Comision/com1:fechaVencimiento)
                        then <com1:fechaVencimiento>{fn:data($Comision/com1:fechaVencimiento)}</com1:fechaVencimiento>
                        else ()
                    }
                    {
                        if ($Comision/com1:fechaInicioCapital)
                        then <com1:fechaInicioCapital>{fn:data($Comision/com1:fechaInicioCapital)}</com1:fechaInicioCapital>
                        else ()
                    }
                    {
                        if ($Comision/com1:fechaInicioComision)
                        then <com1:fechaInicioComision>{fn:data($Comision/com1:fechaInicioComision)}</com1:fechaInicioComision>
                        else ()
                    }
                    {
                        if ($Comision/com1:tipoFrecuencia)
                        then 
                            <com1:tipoFrecuencia>
                                {
                                    if ($Comision/com1:tipoFrecuencia/cat:Id)
                                    then <cat:Id>{fn:data($Comision/com1:tipoFrecuencia/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:tipoFrecuencia/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Comision/com1:tipoFrecuencia/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:tipoFrecuencia/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Comision/com1:tipoFrecuencia/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:tipoFrecuencia/cat:estatus)
                                    then <cat:estatus>{fn:data($Comision/com1:tipoFrecuencia/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:tipoFrecuencia/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Comision/com1:tipoFrecuencia/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com1:tipoFrecuencia>
                        else ()
                    }
                    {
                        if ($Comision/com1:frecuenciaPago)
                        then <com1:frecuenciaPago>{fn:data($Comision/com1:frecuenciaPago)}</com1:frecuenciaPago>
                        else ()
                    }
                    {
                        if ($Comision/com1:fondo)
                        then <com1:fondo>{fn:data($Comision/com1:fondo)}</com1:fondo>
                        else ()
                    }
                    {
                        if ($Comision/com1:comisionCompartida)
                        then <com1:comisionCompartida>{fn:data($Comision/com1:comisionCompartida)}</com1:comisionCompartida>
                        else ()
                    }
                    {
                        if ($Comision/com1:codigoDesembolso)
                        then <com1:codigoDesembolso>{fn:data($Comision/com1:codigoDesembolso)}</com1:codigoDesembolso>
                        else ()
                    }
                    {
                        if ($Comision/com1:numeroTesoreria)
                        then <com1:numeroTesoreria>{fn:data($Comision/com1:numeroTesoreria)}</com1:numeroTesoreria>
                        else ()
                    }
                    {
                        if ($Comision/com1:codigoContrato)
                        then <com1:codigoContrato>{fn:data($Comision/com1:codigoContrato)}</com1:codigoContrato>
                        else ()
                    }
                    {
                        if ($Comision/com1:momentoCobro)
                        then 
                            <com1:momentoCobro>
                                {
                                    if ($Comision/com1:momentoCobro/cat:Id)
                                    then <cat:Id>{fn:data($Comision/com1:momentoCobro/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:momentoCobro/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Comision/com1:momentoCobro/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:momentoCobro/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Comision/com1:momentoCobro/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:momentoCobro/cat:estatus)
                                    then <cat:estatus>{fn:data($Comision/com1:momentoCobro/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:momentoCobro/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Comision/com1:momentoCobro/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com1:momentoCobro>
                        else ()
                    }
                    {
                        if ($Comision/com1:tipoTasa)
                        then 
                            <com1:tipoTasa>
                                {
                                    if ($Comision/com1:tipoTasa/cat:Id)
                                    then <cat:Id>{fn:data($Comision/com1:tipoTasa/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:tipoTasa/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Comision/com1:tipoTasa/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:tipoTasa/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Comision/com1:tipoTasa/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:tipoTasa/cat:estatus)
                                    then <cat:estatus>{fn:data($Comision/com1:tipoTasa/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:tipoTasa/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Comision/com1:tipoTasa/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com1:tipoTasa>
                        else ()
                    }
                    {
                        if ($Comision/com1:baseCalculo)
                        then 
                            <com1:baseCalculo>
                                {
                                    if ($Comision/com1:baseCalculo/cat:Id)
                                    then <cat:Id>{fn:data($Comision/com1:baseCalculo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:baseCalculo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Comision/com1:baseCalculo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:baseCalculo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Comision/com1:baseCalculo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:baseCalculo/cat:estatus)
                                    then <cat:estatus>{fn:data($Comision/com1:baseCalculo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:baseCalculo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Comision/com1:baseCalculo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com1:baseCalculo>
                        else ()
                    }
                    {
                        if ($Comision/com1:responsableComision)
                        then <com1:responsableComision>{fn:data($Comision/com1:responsableComision)}</com1:responsableComision>
                        else ()
                    }
                    <com1:estadoTCC>
                        {
                            if ($Comision/com1:estadoTCC/atr:id)
                            then <atr:id>{fn:data($Comision/com1:estadoTCC/atr:id)}</atr:id>
                            else ()
                        }
                        {
                            if ($Comision/com1:estadoTCC/atr:descripcion)
                            then <atr:descripcion>{fn:data($Comision/com1:estadoTCC/atr:descripcion)}</atr:descripcion>
                            else ()
                        }
                        {
                            if ($Comision/com1:estadoTCC/atr:descripcionCorta)
                            then <atr:descripcionCorta>{fn:data($Comision/com1:estadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                            else ()
                        }
                        {
                            if ($Comision/com1:estadoTCC/atr:estatus)
                            then <atr:estatus>{fn:data($Comision/com1:estadoTCC/atr:estatus)}</atr:estatus>
                            else ()
                        }
                        {
                            if ($Comision/com1:estadoTCC/atr:codigoExterno)
                            then <atr:codigoExterno>{fn:data($Comision/com1:estadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                            else ()
                        }
                        {
                            if ($Comision/com1:estadoTCC/atr:esSubEstado)
                            then <atr:esSubEstado>{fn:data($Comision/com1:estadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                            else ()
                        }
                    </com1:estadoTCC>
                    <com1:subEstadoTCC>
                        {
                            if ($Comision/com1:subEstadoTCC/atr:id)
                            then <atr:id>{fn:data($Comision/com1:subEstadoTCC/atr:id)}</atr:id>
                            else ()
                        }
                        {
                            if ($Comision/com1:subEstadoTCC/atr:descripcion)
                            then <atr:descripcion>{fn:data($Comision/com1:subEstadoTCC/atr:descripcion)}</atr:descripcion>
                            else ()
                        }
                        {
                            if ($Comision/com1:subEstadoTCC/atr:descripcionCorta)
                            then <atr:descripcionCorta>{fn:data($Comision/com1:subEstadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                            else ()
                        }
                        {
                            if ($Comision/com1:subEstadoTCC/atr:estatus)
                            then <atr:estatus>{fn:data($Comision/com1:subEstadoTCC/atr:estatus)}</atr:estatus>
                            else ()
                        }
                        {
                            if ($Comision/com1:subEstadoTCC/atr:codigoExterno)
                            then <atr:codigoExterno>{fn:data($Comision/com1:subEstadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                            else ()
                        }
                        {
                            if ($Comision/com1:subEstadoTCC/atr:esSubEstado)
                            then <atr:esSubEstado>{fn:data($Comision/com1:subEstadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                            else ()
                        }
                    </com1:subEstadoTCC>
                    <com1:fechaRegistro>{fn:data($Comision/com1:fechaRegistro)}</com1:fechaRegistro>
                    <com1:estado>{fn:data($Comision/com1:estado)}</com1:estado>
                    <com1:comisionEnmendada>{fn:data($Comision/com1:comisionEnmendada)}</com1:comisionEnmendada>
                    <com1:fechaEnmienda>{fn:data($Comision/com1:fechaEnmienda)}</com1:fechaEnmienda>
                    {
                        if ($Comision/com1:banSugerida)
                        then <com1:banSugerida>{fn:data($Comision/com1:banSugerida)}</com1:banSugerida>
                        else ()
                    }
                    {
                        if ($Comision/com1:numeroCuentaCliente)
                        then <com1:numeroCuentaCliente>{fn:data($Comision/com1:numeroCuentaCliente)}</com1:numeroCuentaCliente>
                        else ()
                    }
                    {
                        if ($Comision/com1:observaciones)
                        then <com1:observaciones>{fn:data($Comision/com1:observaciones)}</com1:observaciones>
                        else ()
                    }
                    {
                        for $configAtributo in $Comision/com1:configAtributo
                        return 
                        <com1:configAtributo>
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
                        </com1:configAtributo>
                    }
                    {
                        if ($Comision/com1:Accion)
                        then <com1:Accion>{fn:data($Comision/com1:Accion)}</com1:Accion>
                        else ()
                    }
                </lin1:Comision>
            }
            {
                for $Fuente in $outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:Fuente
                return 
                <lin1:Fuente>
                    <lin1:idFuente>{fn:data($Fuente/lin1:idFuente)}</lin1:idFuente>
                    <lin1:idLineaCredito>{fn:data($Fuente/lin1:idLineaCredito)}</lin1:idLineaCredito>
                    <lin1:idLineaPasiva>{fn:data($Fuente/lin1:idLineaPasiva)}</lin1:idLineaPasiva>
                    <lin1:codigoLineaPasiva>{fn:data($Fuente/lin1:codigoLineaPasiva)}</lin1:codigoLineaPasiva>
                    <lin1:idFacturadorLineaPasiva>{fn:data($Fuente/lin1:idFacturadorLineaPasiva)}</lin1:idFacturadorLineaPasiva>
                    <lin1:idFondo>{fn:data($Fuente/lin1:idFondo)}</lin1:idFondo>
                    <lin1:Descripcion>{fn:data($Fuente/lin1:Descripcion)}</lin1:Descripcion>
                    <lin1:FechaObtenido>{fn:data($Fuente/lin1:FechaObtenido)}</lin1:FechaObtenido>
                    <lin1:MontoAsignado>{fn:data($Fuente/lin1:MontoAsignado)}</lin1:MontoAsignado>
                    <lin1:montoDisponible>{fn:data($Fuente/lin1:montoDisponible)}</lin1:montoDisponible>
                    {
                        for $Monto in $Fuente/lin1:Monto
                        return 
                        <lin1:Monto>
                            <com:tipo>
                                {
                                    if ($Monto/com:tipo/cat:Id)
                                    then <cat:Id>{fn:data($Monto/com:tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Monto/com:tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Monto/com:tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Monto/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Monto/com:tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($Monto/com:tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Monto/com:tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com:tipo>
                            {
                                if ($Monto/com:importe)
                                then <com:importe>{fn:data($Monto/com:importe)}</com:importe>
                                else ()
                            }
                            {
                                if ($Monto/com:moneda)
                                then 
                                    <com:moneda>
                                        {
                                            if ($Monto/com:moneda/cat:Id)
                                            then <cat:Id>{fn:data($Monto/com:moneda/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Monto/com:moneda/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Monto/com:moneda/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Monto/com:moneda/cat:estatus)
                                            then <cat:estatus>{fn:data($Monto/com:moneda/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Monto/com:moneda/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </com:moneda>
                                else ()
                            }
                        </lin1:Monto>
                    }
                    <lin1:NumeroAsignacion>{fn:data($Fuente/lin1:NumeroAsignacion)}</lin1:NumeroAsignacion>
                    <lin1:Comentario>{fn:data($Fuente/lin1:Comentario)}</lin1:Comentario>
                    <lin1:idContrato>{fn:data($Fuente/lin1:idContrato)}</lin1:idContrato>
                    <lin1:FechaRegistro>{fn:data($Fuente/lin1:FechaRegistro)}</lin1:FechaRegistro>
                    <lin1:Estado>{fn:data($Fuente/lin1:Estado)}</lin1:Estado>
                    <lin1:esExterna>{fn:data($Fuente/lin1:esExterna)}</lin1:esExterna>
                </lin1:Fuente>
            }
            {
                for $atributos in $outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:atributos
                return 
                <lin1:atributos>
                    <com:name>{fn:data($atributos/com:name)}</com:name>
                    <com:value>{fn:data($atributos/com:value)}</com:value>
                </lin1:atributos>
            }
            {
                <lin1:LineaCreditoFlexcube>
                        <lin1:numeroLineaCreditoResp_out>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:numeroLineaCreditoResp_out)}</lin1:numeroLineaCreditoResp_out>
                        <lin1:NumeroContrato>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:NumeroContrato)}</lin1:NumeroContrato>
                        <lin1:DescripcionLinea>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:DescripcionLinea)}</lin1:DescripcionLinea>
                        <lin1:Moneda>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:Moneda)}</lin1:Moneda>
                        <lin1:MontoAprobado>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:MontoAprobado)}</lin1:MontoAprobado>
                        <lin1:CodigoCliente>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:CodigoCliente)}</lin1:CodigoCliente>
                        <lin1:Revolvencia>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:Revolvencia)}</lin1:Revolvencia>
                        <lin1:Fondo>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:Fondo)}</lin1:Fondo>
                        <lin1:LineaFinanciera>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:LineaFinanciera)}</lin1:LineaFinanciera>
                        <lin1:Destino>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:Destino)}</lin1:Destino>
                        <lin1:TipoRecurso>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:TipoRecurso)}</lin1:TipoRecurso>
                        <lin1:Pais>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:Pais)}</lin1:Pais>
                        <lin1:ActividadEconomica>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:ActividadEconomica)}</lin1:ActividadEconomica>
                        <lin1:SectorInstitucional>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:SectorInstitucional)}</lin1:SectorInstitucional>
                        <lin1:Ejecutivo>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:Ejecutivo)}</lin1:Ejecutivo>
                        <lin1:EjeEstrategico>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:EjeEstrategico)}</lin1:EjeEstrategico>
                        <lin1:AreaFocalizacion>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:AreaFocalizacion)}</lin1:AreaFocalizacion>
                        <lin1:ObjeticoEstrategico>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:ObjeticoEstrategico)}</lin1:ObjeticoEstrategico>
                        <lin1:PlantillaLimite>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:PlantillaLimite)}</lin1:PlantillaLimite>
                        <lin1:SerialLimite>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:SerialLimite)}</lin1:SerialLimite>
                        <lin1:Saldo>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:Saldo)}</lin1:Saldo>
                        <lin1:Disponibilidad>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:Disponibilidad)}</lin1:Disponibilidad>
                        <lin1:DisponibilidadIfacil>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:DisponibilidadIfacil)}</lin1:DisponibilidadIfacil>
                        <lin1:TieneCondEspeciales>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:TieneCondEspeciales)}</lin1:TieneCondEspeciales>
                        <lin1:DescCondEspeciales>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:DescCondEspeciales)}</lin1:DescCondEspeciales>
                        <lin1:FechaMaxDesembolsar>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:FechaMaxDesembolsar)}</lin1:FechaMaxDesembolsar>
                        <lin1:NumeroDesembolsos>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:LineaCreditoFlexcube/lin1:NumeroDesembolsos)}</lin1:NumeroDesembolsos>
                    </lin1:LineaCreditoFlexcube>
            }
            {
                <lin1:HerramientaCE>
                        <her:ActividadEconomica>
                            {
                                if ($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:ActividadEconomica/cat:Id)
                                then <cat:Id>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:ActividadEconomica/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:ActividadEconomica/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:ActividadEconomica/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:ActividadEconomica/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:ActividadEconomica/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:ActividadEconomica/cat:estatus)
                                then <cat:estatus>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:ActividadEconomica/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:ActividadEconomica>
                        <her:EjeEstrategico>
                            {
                                if ($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:EjeEstrategico/cat:Id)
                                then <cat:Id>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:EjeEstrategico/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:EjeEstrategico/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:EjeEstrategico/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:EjeEstrategico/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:EjeEstrategico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:EjeEstrategico/cat:estatus)
                                then <cat:estatus>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:EjeEstrategico/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:EjeEstrategico>
                        <her:AreaFocalizacion>
                            {
                                if ($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:AreaFocalizacion/cat:Id)
                                then <cat:Id>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:AreaFocalizacion/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:AreaFocalizacion/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:AreaFocalizacion/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:AreaFocalizacion/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:AreaFocalizacion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:AreaFocalizacion/cat:estatus)
                                then <cat:estatus>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:AreaFocalizacion/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse/lin:LineaCredito/lin1:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:AreaFocalizacion>
                    </lin1:HerramientaCE>
            }
        </des:LineaCredito>
        <des:ContratoDesembolso>
            <des2:idDesembolso>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:idDesembolso)}</des2:idDesembolso>
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:idFacturador)
                then <des2:idFacturador>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:idFacturador)}</des2:idFacturador>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:producto)
                then 
                    <des2:producto>
                        <pro:idProducto>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:producto/pro:idProducto)}</pro:idProducto>
                        <pro:descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:producto/pro:descripcion)}</pro:descripcion>
                        <pro:descripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:producto/pro:descripcionCorta)}</pro:descripcionCorta>
                        <pro:fechaRegistro>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:producto/pro:fechaRegistro)}</pro:fechaRegistro>
                        <pro:codExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:producto/pro:codExterno)}</pro:codExterno>
                    </des2:producto>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:referencia)
                then <des2:referencia>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:referencia)}</des2:referencia>
                else ()
            }
            {
                for $monto in $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:monto
                return 
                <des2:monto>
                    <com:tipo>
                        {
                            if ($monto/com:tipo/cat:Id)
                            then <cat:Id>{fn:data($monto/com:tipo/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($monto/com:tipo/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($monto/com:tipo/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($monto/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($monto/com:tipo/cat:estatus)
                            then <cat:estatus>{fn:data($monto/com:tipo/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($monto/com:tipo/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </com:tipo>
                    {
                        if ($monto/com:importe)
                        then <com:importe>{fn:data($monto/com:importe)}</com:importe>
                        else ()
                    }
                    {
                        if ($monto/com:moneda)
                        then 
                            <com:moneda>
                                {
                                    if ($monto/com:moneda/cat:Id)
                                    then <cat:Id>{fn:data($monto/com:moneda/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($monto/com:moneda/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($monto/com:moneda/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($monto/com:moneda/cat:estatus)
                                    then <cat:estatus>{fn:data($monto/com:moneda/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($monto/com:moneda/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com:moneda>
                        else ()
                    }
                </des2:monto>
            }
            <des2:estado>
                {
                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:estado/cat:Id)
                    then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:estado/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:estado/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:estado/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
                {
                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:estado/cat:DescripcionCorta)
                    then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    else ()
                }
                {
                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:estado/cat:estatus)
                    then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:estado/cat:estatus)}</cat:estatus>
                    else ()
                }
                {
                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:estado/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:estado/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </des2:estado>
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:programado)
                then <des2:programado>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:programado)}</des2:programado>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:fechaEstimadaDesembolsar)
                then <des2:fechaEstimadaDesembolsar>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:fechaEstimadaDesembolsar)}</des2:fechaEstimadaDesembolsar>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:fechaEfectiva)
                then <des2:fechaEfectiva>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:fechaEfectiva)}</des2:fechaEfectiva>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:fechaAsignacion)
                then <des2:fechaAsignacion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:fechaAsignacion)}</des2:fechaAsignacion>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:fechaRegistro)
                then <des2:fechaRegistro>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:fechaRegistro)}</des2:fechaRegistro>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:fechaVencimiento)
                then <des2:fechaVencimiento>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:fechaVencimiento)}</des2:fechaVencimiento>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:estatus)
                then <des2:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:estatus)}</des2:estatus>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras)
                then 
                    <des2:condicionesFinancieras>
                        <des2:idCondicionFinanciera>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:idCondicionFinanciera)}</des2:idCondicionFinanciera>
                        <des2:fondo>
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:fondo/cat:Id)
                                then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:fondo/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:fondo/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:fondo/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:fondo/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:fondo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:fondo/cat:estatus)
                                then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:fondo/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:fondo/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:fondo/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                            {
                                for $Validador in $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:fondo/des2:Validador
                                return 
                                <des2:Validador>
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
                                </des2:Validador>
                            }
                        </des2:fondo>
                        <des2:baseCalculo>
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:baseCalculo/cat:Id)
                                then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:baseCalculo/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:baseCalculo/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:baseCalculo/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:baseCalculo/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:baseCalculo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:baseCalculo/cat:estatus)
                                then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:baseCalculo/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:baseCalculo/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:baseCalculo/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </des2:baseCalculo>
                        <des2:periodoGracia>
                            <des2:FechaInicio>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:periodoGracia/des2:FechaInicio)}</des2:FechaInicio>
                            <des2:Frecuencia>
                                <com:Tipo>
                                    {
                                        if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:periodoGracia/des2:Frecuencia/com:Tipo/cat:Id)
                                        then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:periodoGracia/des2:Frecuencia/com:Tipo/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:periodoGracia/des2:Frecuencia/com:Tipo/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:periodoGracia/des2:Frecuencia/com:Tipo/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:periodoGracia/des2:Frecuencia/com:Tipo/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:periodoGracia/des2:Frecuencia/com:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:periodoGracia/des2:Frecuencia/com:Tipo/cat:estatus)
                                        then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:periodoGracia/des2:Frecuencia/com:Tipo/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:periodoGracia/des2:Frecuencia/com:Tipo/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:periodoGracia/des2:Frecuencia/com:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </com:Tipo>
                                <com:Valor>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:periodoGracia/des2:Frecuencia/com:Valor)}</com:Valor>
                            </des2:Frecuencia>
                        </des2:periodoGracia>
                        <des2:tratamientoDiasFeriados>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:tratamientoDiasFeriados)}</des2:tratamientoDiasFeriados>
                        <des2:tipoCalendario>
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:tipoCalendario/cat:Id)
                                then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:tipoCalendario/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:tipoCalendario/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:tipoCalendario/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:tipoCalendario/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:tipoCalendario/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:tipoCalendario/cat:estatus)
                                then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:tipoCalendario/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:tipoCalendario/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:tipoCalendario/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </des2:tipoCalendario>
                        <des2:moverEntreMeses>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:moverEntreMeses)}</des2:moverEntreMeses>
                        {
                            for $Componente in $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:Componente
                            return 
                            <des2:Componente>
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
                                <des2:TipoComponente>
                                    {
                                        if ($Componente/des2:TipoComponente/cat:Id)
                                        then <cat:Id>{fn:data($Componente/des2:TipoComponente/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des2:TipoComponente/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($Componente/des2:TipoComponente/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des2:TipoComponente/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($Componente/des2:TipoComponente/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des2:TipoComponente/cat:estatus)
                                        then <cat:estatus>{fn:data($Componente/des2:TipoComponente/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des2:TipoComponente/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($Componente/des2:TipoComponente/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </des2:TipoComponente>
                                <des2:TipoTasa>
                                    {
                                        if ($Componente/des2:TipoTasa/cat:Id)
                                        then <cat:Id>{fn:data($Componente/des2:TipoTasa/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des2:TipoTasa/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($Componente/des2:TipoTasa/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des2:TipoTasa/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($Componente/des2:TipoTasa/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des2:TipoTasa/cat:estatus)
                                        then <cat:estatus>{fn:data($Componente/des2:TipoTasa/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des2:TipoTasa/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($Componente/des2:TipoTasa/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </des2:TipoTasa>
                                <des2:BaseCalculo>
                                    {
                                        if ($Componente/des2:BaseCalculo/cat:Id)
                                        then <cat:Id>{fn:data($Componente/des2:BaseCalculo/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des2:BaseCalculo/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($Componente/des2:BaseCalculo/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des2:BaseCalculo/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($Componente/des2:BaseCalculo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des2:BaseCalculo/cat:estatus)
                                        then <cat:estatus>{fn:data($Componente/des2:BaseCalculo/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des2:BaseCalculo/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($Componente/des2:BaseCalculo/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </des2:BaseCalculo>
                                <des2:esDependiente>{fn:data($Componente/des2:esDependiente)}</des2:esDependiente>
                                <des2:esFactor>{fn:data($Componente/des2:esFactor)}</des2:esFactor>
                                <des2:CodigoTasaReferencia>{fn:data($Componente/des2:CodigoTasaReferencia)}</des2:CodigoTasaReferencia>
                                <des2:FechaEfectivaTasaReferencia>{fn:data($Componente/des2:FechaEfectivaTasaReferencia)}</des2:FechaEfectivaTasaReferencia>
                                <des2:ValorTasaReferencia>{fn:data($Componente/des2:ValorTasaReferencia)}</des2:ValorTasaReferencia>
                                <des2:Tasa>{fn:data($Componente/des2:Tasa)}</des2:Tasa>
                                <des2:SpreadTasa>{fn:data($Componente/des2:SpreadTasa)}</des2:SpreadTasa>
                                <des2:MontoDescuento>{fn:data($Componente/des2:MontoDescuento)}</des2:MontoDescuento>
                                <des2:DiasSpotTasa>{fn:data($Componente/des2:DiasSpotTasa)}</des2:DiasSpotTasa>
                                <des2:TipoRedondeo>
                                    {
                                        if ($Componente/des2:TipoRedondeo/cat:Id)
                                        then <cat:Id>{fn:data($Componente/des2:TipoRedondeo/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des2:TipoRedondeo/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($Componente/des2:TipoRedondeo/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des2:TipoRedondeo/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($Componente/des2:TipoRedondeo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des2:TipoRedondeo/cat:estatus)
                                        then <cat:estatus>{fn:data($Componente/des2:TipoRedondeo/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($Componente/des2:TipoRedondeo/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($Componente/des2:TipoRedondeo/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </des2:TipoRedondeo>
                                <des2:CantidadDecimales>{fn:data($Componente/des2:CantidadDecimales)}</des2:CantidadDecimales>
                                <des2:LimiteTasaMinima>{fn:data($Componente/des2:LimiteTasaMinima)}</des2:LimiteTasaMinima>
                                <des2:LimiteTasaMaxima>{fn:data($Componente/des2:LimiteTasaMaxima)}</des2:LimiteTasaMaxima>
                                {
                                    for $calendario in $Componente/des2:calendario
                                    return 
                                    <des2:calendario>
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
                                        <des2:TipoPlan>
                                            {
                                                if ($calendario/des2:TipoPlan/cat:Id)
                                                then <cat:Id>{fn:data($calendario/des2:TipoPlan/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($calendario/des2:TipoPlan/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($calendario/des2:TipoPlan/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($calendario/des2:TipoPlan/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($calendario/des2:TipoPlan/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($calendario/des2:TipoPlan/cat:estatus)
                                                then <cat:estatus>{fn:data($calendario/des2:TipoPlan/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($calendario/des2:TipoPlan/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($calendario/des2:TipoPlan/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </des2:TipoPlan>
                                        <des2:Frecuencia>
                                            <des2:FechaInicio>{fn:data($calendario/des2:Frecuencia/des2:FechaInicio)}</des2:FechaInicio>
                                            <des2:Frecuencia>
                                                <com:Tipo>
                                                    {
                                                        if ($calendario/des2:Frecuencia/des2:Frecuencia/com:Tipo/cat:Id)
                                                        then <cat:Id>{fn:data($calendario/des2:Frecuencia/des2:Frecuencia/com:Tipo/cat:Id)}</cat:Id>
                                                        else ()
                                                    }
                                                    {
                                                        if ($calendario/des2:Frecuencia/des2:Frecuencia/com:Tipo/cat:Descripcion)
                                                        then <cat:Descripcion>{fn:data($calendario/des2:Frecuencia/des2:Frecuencia/com:Tipo/cat:Descripcion)}</cat:Descripcion>
                                                        else ()
                                                    }
                                                    {
                                                        if ($calendario/des2:Frecuencia/des2:Frecuencia/com:Tipo/cat:DescripcionCorta)
                                                        then <cat:DescripcionCorta>{fn:data($calendario/des2:Frecuencia/des2:Frecuencia/com:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                        else ()
                                                    }
                                                    {
                                                        if ($calendario/des2:Frecuencia/des2:Frecuencia/com:Tipo/cat:estatus)
                                                        then <cat:estatus>{fn:data($calendario/des2:Frecuencia/des2:Frecuencia/com:Tipo/cat:estatus)}</cat:estatus>
                                                        else ()
                                                    }
                                                    {
                                                        if ($calendario/des2:Frecuencia/des2:Frecuencia/com:Tipo/cat:codigoExterno)
                                                        then <cat:codigoExterno>{fn:data($calendario/des2:Frecuencia/des2:Frecuencia/com:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                                        else ()
                                                    }
                                                </com:Tipo>
                                                <com:Valor>{fn:data($calendario/des2:Frecuencia/des2:Frecuencia/com:Valor)}</com:Valor>
                                            </des2:Frecuencia>
                                        </des2:Frecuencia>
                                        <des2:Monto>
                                            <com:tipo>
                                                {
                                                    if ($calendario/des2:Monto/com:tipo/cat:Id)
                                                    then <cat:Id>{fn:data($calendario/des2:Monto/com:tipo/cat:Id)}</cat:Id>
                                                    else ()
                                                }
                                                {
                                                    if ($calendario/des2:Monto/com:tipo/cat:Descripcion)
                                                    then <cat:Descripcion>{fn:data($calendario/des2:Monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                                    else ()
                                                }
                                                {
                                                    if ($calendario/des2:Monto/com:tipo/cat:DescripcionCorta)
                                                    then <cat:DescripcionCorta>{fn:data($calendario/des2:Monto/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                    else ()
                                                }
                                                {
                                                    if ($calendario/des2:Monto/com:tipo/cat:estatus)
                                                    then <cat:estatus>{fn:data($calendario/des2:Monto/com:tipo/cat:estatus)}</cat:estatus>
                                                    else ()
                                                }
                                                {
                                                    if ($calendario/des2:Monto/com:tipo/cat:codigoExterno)
                                                    then <cat:codigoExterno>{fn:data($calendario/des2:Monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                                    else ()
                                                }
                                            </com:tipo>
                                            {
                                                if ($calendario/des2:Monto/com:importe)
                                                then <com:importe>{fn:data($calendario/des2:Monto/com:importe)}</com:importe>
                                                else ()
                                            }
                                            {
                                                if ($calendario/des2:Monto/com:moneda)
                                                then 
                                                    <com:moneda>
                                                        {
                                                            if ($calendario/des2:Monto/com:moneda/cat:Id)
                                                            then <cat:Id>{fn:data($calendario/des2:Monto/com:moneda/cat:Id)}</cat:Id>
                                                            else ()
                                                        }
                                                        {
                                                            if ($calendario/des2:Monto/com:moneda/cat:Descripcion)
                                                            then <cat:Descripcion>{fn:data($calendario/des2:Monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                                            else ()
                                                        }
                                                        {
                                                            if ($calendario/des2:Monto/com:moneda/cat:DescripcionCorta)
                                                            then <cat:DescripcionCorta>{fn:data($calendario/des2:Monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                            else ()
                                                        }
                                                        {
                                                            if ($calendario/des2:Monto/com:moneda/cat:estatus)
                                                            then <cat:estatus>{fn:data($calendario/des2:Monto/com:moneda/cat:estatus)}</cat:estatus>
                                                            else ()
                                                        }
                                                        {
                                                            if ($calendario/des2:Monto/com:moneda/cat:codigoExterno)
                                                            then <cat:codigoExterno>{fn:data($calendario/des2:Monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                                            else ()
                                                        }
                                                    </com:moneda>
                                                else ()
                                            }
                                        </des2:Monto>
                                        <des2:NumeroCuotas>{fn:data($calendario/des2:NumeroCuotas)}</des2:NumeroCuotas>
                                    </des2:calendario>
                                }
                            </des2:Componente>
                        }
                        {
                            for $calendario in $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:condicionesFinancieras/des2:calendario
                            return 
                            <des2:calendario>
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
                                <des2:TipoPlan>
                                    {
                                        if ($calendario/des2:TipoPlan/cat:Id)
                                        then <cat:Id>{fn:data($calendario/des2:TipoPlan/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($calendario/des2:TipoPlan/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($calendario/des2:TipoPlan/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($calendario/des2:TipoPlan/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($calendario/des2:TipoPlan/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($calendario/des2:TipoPlan/cat:estatus)
                                        then <cat:estatus>{fn:data($calendario/des2:TipoPlan/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($calendario/des2:TipoPlan/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($calendario/des2:TipoPlan/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </des2:TipoPlan>
                                <des2:Frecuencia>
                                    <des2:FechaInicio>{fn:data($calendario/des2:Frecuencia/des2:FechaInicio)}</des2:FechaInicio>
                                    <des2:Frecuencia>
                                        <com:Tipo>
                                            {
                                                if ($calendario/des2:Frecuencia/des2:Frecuencia/com:Tipo/cat:Id)
                                                then <cat:Id>{fn:data($calendario/des2:Frecuencia/des2:Frecuencia/com:Tipo/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($calendario/des2:Frecuencia/des2:Frecuencia/com:Tipo/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($calendario/des2:Frecuencia/des2:Frecuencia/com:Tipo/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($calendario/des2:Frecuencia/des2:Frecuencia/com:Tipo/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($calendario/des2:Frecuencia/des2:Frecuencia/com:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($calendario/des2:Frecuencia/des2:Frecuencia/com:Tipo/cat:estatus)
                                                then <cat:estatus>{fn:data($calendario/des2:Frecuencia/des2:Frecuencia/com:Tipo/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($calendario/des2:Frecuencia/des2:Frecuencia/com:Tipo/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($calendario/des2:Frecuencia/des2:Frecuencia/com:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </com:Tipo>
                                        <com:Valor>{fn:data($calendario/des2:Frecuencia/des2:Frecuencia/com:Valor)}</com:Valor>
                                    </des2:Frecuencia>
                                </des2:Frecuencia>
                                <des2:Monto>
                                    <com:tipo>
                                        {
                                            if ($calendario/des2:Monto/com:tipo/cat:Id)
                                            then <cat:Id>{fn:data($calendario/des2:Monto/com:tipo/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($calendario/des2:Monto/com:tipo/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($calendario/des2:Monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($calendario/des2:Monto/com:tipo/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($calendario/des2:Monto/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($calendario/des2:Monto/com:tipo/cat:estatus)
                                            then <cat:estatus>{fn:data($calendario/des2:Monto/com:tipo/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($calendario/des2:Monto/com:tipo/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($calendario/des2:Monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </com:tipo>
                                    {
                                        if ($calendario/des2:Monto/com:importe)
                                        then <com:importe>{fn:data($calendario/des2:Monto/com:importe)}</com:importe>
                                        else ()
                                    }
                                    {
                                        if ($calendario/des2:Monto/com:moneda)
                                        then 
                                            <com:moneda>
                                                {
                                                    if ($calendario/des2:Monto/com:moneda/cat:Id)
                                                    then <cat:Id>{fn:data($calendario/des2:Monto/com:moneda/cat:Id)}</cat:Id>
                                                    else ()
                                                }
                                                {
                                                    if ($calendario/des2:Monto/com:moneda/cat:Descripcion)
                                                    then <cat:Descripcion>{fn:data($calendario/des2:Monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                                    else ()
                                                }
                                                {
                                                    if ($calendario/des2:Monto/com:moneda/cat:DescripcionCorta)
                                                    then <cat:DescripcionCorta>{fn:data($calendario/des2:Monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                    else ()
                                                }
                                                {
                                                    if ($calendario/des2:Monto/com:moneda/cat:estatus)
                                                    then <cat:estatus>{fn:data($calendario/des2:Monto/com:moneda/cat:estatus)}</cat:estatus>
                                                    else ()
                                                }
                                                {
                                                    if ($calendario/des2:Monto/com:moneda/cat:codigoExterno)
                                                    then <cat:codigoExterno>{fn:data($calendario/des2:Monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                                    else ()
                                                }
                                            </com:moneda>
                                        else ()
                                    }
                                </des2:Monto>
                                <des2:NumeroCuotas>{fn:data($calendario/des2:NumeroCuotas)}</des2:NumeroCuotas>
                            </des2:calendario>
                        }
                    </des2:condicionesFinancieras>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:idLinea)
                then <des2:idLinea>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:idLinea)}</des2:idLinea>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:recursosExternos)
                then <des2:recursosExternos>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:recursosExternos)}</des2:recursosExternos>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:cuentaCliente)
                then <des2:cuentaCliente>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:cuentaCliente)}</des2:cuentaCliente>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:usuario)
                then <des2:usuario>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:usuario)}</des2:usuario>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:fechaDisponibilidadFondos)
                then <des2:fechaDisponibilidadFondos>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:fechaDisponibilidadFondos)}</des2:fechaDisponibilidadFondos>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:origenTransferenciaCte)
                then <des2:origenTransferenciaCte>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:origenTransferenciaCte)}</des2:origenTransferenciaCte>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa)
                then 
                    <des2:Programa>
                        <her:LineaFinanciera>
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:LineaFinanciera/cat:Id)
                                then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:LineaFinanciera/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:LineaFinanciera/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:LineaFinanciera/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:LineaFinanciera/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:LineaFinanciera/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:LineaFinanciera/cat:estatus)
                                then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:LineaFinanciera/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:LineaFinanciera/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:LineaFinanciera/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:LineaFinanciera>
                        <her:DestinoFinanciamiento>
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:DestinoFinanciamiento/cat:Id)
                                then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:DestinoFinanciamiento/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:DestinoFinanciamiento/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:DestinoFinanciamiento/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:DestinoFinanciamiento/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:DestinoFinanciamiento/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:DestinoFinanciamiento/cat:estatus)
                                then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:DestinoFinanciamiento/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:DestinoFinanciamiento/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:DestinoFinanciamiento/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:DestinoFinanciamiento>
                        <her:ModalidadFinanciamiento>
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ModalidadFinanciamiento/cat:Id)
                                then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ModalidadFinanciamiento/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ModalidadFinanciamiento/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ModalidadFinanciamiento/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ModalidadFinanciamiento/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ModalidadFinanciamiento/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ModalidadFinanciamiento/cat:estatus)
                                then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ModalidadFinanciamiento/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ModalidadFinanciamiento/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ModalidadFinanciamiento/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:ModalidadFinanciamiento>
                        <her:HerramientaCE>
                            <her:ActividadEconomica>
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:ActividadEconomica/cat:Id)
                                    then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:ActividadEconomica/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:ActividadEconomica/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:ActividadEconomica/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:ActividadEconomica/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:ActividadEconomica/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:ActividadEconomica/cat:estatus)
                                    then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:ActividadEconomica/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </her:ActividadEconomica>
                            <her:EjeEstrategico>
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:EjeEstrategico/cat:Id)
                                    then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:EjeEstrategico/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:EjeEstrategico/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:EjeEstrategico/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:EjeEstrategico/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:EjeEstrategico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:EjeEstrategico/cat:estatus)
                                    then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:EjeEstrategico/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </her:EjeEstrategico>
                            <her:AreaFocalizacion>
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:Id)
                                    then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:estatus)
                                    then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </her:AreaFocalizacion>
                        </her:HerramientaCE>
                        <her:ClasificacionGeneral>
                            <her:SectorMercado>
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:Id)
                                    then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:estatus)
                                    then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </her:SectorMercado>
                            <her:SectorInstitucional>
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:Id)
                                    then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:estatus)
                                    then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </her:SectorInstitucional>
                        </her:ClasificacionGeneral>
                    </des2:Programa>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:EstimadoDesembolso)
                then 
                    <des2:EstimadoDesembolso>
                        <des2:Plazo>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:EstimadoDesembolso/des2:Plazo)}</des2:Plazo>
                        <des2:Frecuencia>
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:EstimadoDesembolso/des2:Frecuencia/cat:Id)
                                then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:EstimadoDesembolso/des2:Frecuencia/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:EstimadoDesembolso/des2:Frecuencia/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:EstimadoDesembolso/des2:Frecuencia/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:EstimadoDesembolso/des2:Frecuencia/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:EstimadoDesembolso/des2:Frecuencia/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:EstimadoDesembolso/des2:Frecuencia/cat:estatus)
                                then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:EstimadoDesembolso/des2:Frecuencia/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:EstimadoDesembolso/des2:Frecuencia/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:EstimadoDesembolso/des2:Frecuencia/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </des2:Frecuencia>
                    </des2:EstimadoDesembolso>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:fechaInicioProceso)
                then <des2:fechaInicioProceso>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:fechaInicioProceso)}</des2:fechaInicioProceso>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:fechaEstimadaDisponibilidad)
                then <des2:fechaEstimadaDisponibilidad>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:fechaEstimadaDisponibilidad)}</des2:fechaEstimadaDisponibilidad>
                else ()
            }
            {
                for $Utilizacion in $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Utilizacion
                return 
                <des2:Utilizacion>
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
                        for $Monto1 in $Utilizacion/lin1:Monto
                        return 
                        <lin1:Monto>
                            <com:tipo>
                                {
                                    if ($Monto1/com:tipo/cat:Id)
                                    then <cat:Id>{fn:data($Monto1/com:tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Monto1/com:tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Monto1/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Monto1/com:tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Monto1/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Monto1/com:tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($Monto1/com:tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Monto1/com:tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Monto1/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com:tipo>
                            {
                                if ($Monto1/com:importe)
                                then <com:importe>{fn:data($Monto1/com:importe)}</com:importe>
                                else ()
                            }
                            {
                                if ($Monto1/com:moneda)
                                then 
                                    <com:moneda>
                                        {
                                            if ($Monto1/com:moneda/cat:Id)
                                            then <cat:Id>{fn:data($Monto1/com:moneda/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Monto1/com:moneda/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Monto1/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Monto1/com:moneda/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Monto1/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Monto1/com:moneda/cat:estatus)
                                            then <cat:estatus>{fn:data($Monto1/com:moneda/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Monto1/com:moneda/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Monto1/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </com:moneda>
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
                </des2:Utilizacion>
            }
            {
                for $Cargo in $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Cargo
                return 
                <des2:Cargo>
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
                    <des2:Monto>
                        <com:tipo>
                            {
                                if ($Cargo/des2:Monto/com:tipo/cat:Id)
                                then <cat:Id>{fn:data($Cargo/des2:Monto/com:tipo/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Cargo/des2:Monto/com:tipo/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Cargo/des2:Monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Cargo/des2:Monto/com:tipo/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Cargo/des2:Monto/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Cargo/des2:Monto/com:tipo/cat:estatus)
                                then <cat:estatus>{fn:data($Cargo/des2:Monto/com:tipo/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Cargo/des2:Monto/com:tipo/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Cargo/des2:Monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </com:tipo>
                        {
                            if ($Cargo/des2:Monto/com:importe)
                            then <com:importe>{fn:data($Cargo/des2:Monto/com:importe)}</com:importe>
                            else ()
                        }
                        {
                            if ($Cargo/des2:Monto/com:moneda)
                            then 
                                <com:moneda>
                                    {
                                        if ($Cargo/des2:Monto/com:moneda/cat:Id)
                                        then <cat:Id>{fn:data($Cargo/des2:Monto/com:moneda/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($Cargo/des2:Monto/com:moneda/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($Cargo/des2:Monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($Cargo/des2:Monto/com:moneda/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($Cargo/des2:Monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($Cargo/des2:Monto/com:moneda/cat:estatus)
                                        then <cat:estatus>{fn:data($Cargo/des2:Monto/com:moneda/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($Cargo/des2:Monto/com:moneda/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($Cargo/des2:Monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </com:moneda>
                            else ()
                        }
                    </des2:Monto>
                    <des2:FechaRegistro>{fn:data($Cargo/des2:FechaRegistro)}</des2:FechaRegistro>
                    {
                        if ($Cargo/des2:Status)
                        then <des2:Status>{fn:data($Cargo/des2:Status)}</des2:Status>
                        else ()
                    }
                    {
                        if ($Cargo/des2:SoloLectura)
                        then <des2:SoloLectura>{fn:data($Cargo/des2:SoloLectura)}</des2:SoloLectura>
                        else ()
                    }
                    {
                        if ($Cargo/des2:TCC)
                        then 
                            <des2:TCC>
                                <mat:id>{fn:data($Cargo/des2:TCC/mat:id)}</mat:id>
                                <mat:tipo>{fn:data($Cargo/des2:TCC/mat:tipo)}</mat:tipo>
                                <mat:estado>{fn:data($Cargo/des2:TCC/mat:estado)}</mat:estado>
                                <mat:subEstado>{fn:data($Cargo/des2:TCC/mat:subEstado)}</mat:subEstado>
                                <mat:banEstatus>{fn:data($Cargo/des2:TCC/mat:banEstatus)}</mat:banEstatus>
                            </des2:TCC>
                        else ()
                    }
                </des2:Cargo>
            }
            {
                for $Comision1 in $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Comision
                return 
                <des2:Comision>
                    <com1:idComision>{fn:data($Comision1/com1:idComision)}</com1:idComision>
                    <com1:idOperacion>{fn:data($Comision1/com1:idOperacion)}</com1:idOperacion>
                    {
                        if ($Comision1/com1:nombre)
                        then <com1:nombre>{fn:data($Comision1/com1:nombre)}</com1:nombre>
                        else ()
                    }
                    {
                        if ($Comision1/com1:descripcion)
                        then <com1:descripcion>{fn:data($Comision1/com1:descripcion)}</com1:descripcion>
                        else ()
                    }
                    {
                        if ($Comision1/com1:tipoComision)
                        then 
                            <com1:tipoComision>
                                {
                                    if ($Comision1/com1:tipoComision/com1:idCatComision)
                                    then <com1:idCatComision>{fn:data($Comision1/com1:tipoComision/com1:idCatComision)}</com1:idCatComision>
                                    else ()
                                }
                                <com1:descripcion>{fn:data($Comision1/com1:tipoComision/com1:descripcion)}</com1:descripcion>
                                <com1:descripcionCorta>{fn:data($Comision1/com1:tipoComision/com1:descripcionCorta)}</com1:descripcionCorta>
                                {
                                    if ($Comision1/com1:tipoComision/com1:idTipoComision)
                                    then 
                                        <com1:idTipoComision>
                                            {
                                                if ($Comision1/com1:tipoComision/com1:idTipoComision/cat:Id)
                                                then <cat:Id>{fn:data($Comision1/com1:tipoComision/com1:idTipoComision/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($Comision1/com1:tipoComision/com1:idTipoComision/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($Comision1/com1:tipoComision/com1:idTipoComision/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($Comision1/com1:tipoComision/com1:idTipoComision/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($Comision1/com1:tipoComision/com1:idTipoComision/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($Comision1/com1:tipoComision/com1:idTipoComision/cat:estatus)
                                                then <cat:estatus>{fn:data($Comision1/com1:tipoComision/com1:idTipoComision/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($Comision1/com1:tipoComision/com1:idTipoComision/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($Comision1/com1:tipoComision/com1:idTipoComision/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </com1:idTipoComision>
                                    else ()
                                }
                                <com1:esEditableEnFormalizacion>{fn:data($Comision1/com1:tipoComision/com1:esEditableEnFormalizacion)}</com1:esEditableEnFormalizacion>
                                <com1:requiereValidarCOFI>{fn:data($Comision1/com1:tipoComision/com1:requiereValidarCOFI)}</com1:requiereValidarCOFI>
                                <com1:sePuedeDispensar>{fn:data($Comision1/com1:tipoComision/com1:sePuedeDispensar)}</com1:sePuedeDispensar>
                                <com1:seRegistraEnFlexCube>{fn:data($Comision1/com1:tipoComision/com1:seRegistraEnFlexCube)}</com1:seRegistraEnFlexCube>
                                <com1:esPlantilla>{fn:data($Comision1/com1:tipoComision/com1:esPlantilla)}</com1:esPlantilla>
                                <com1:idComisionPrecarga>{fn:data($Comision1/com1:tipoComision/com1:idComisionPrecarga)}</com1:idComisionPrecarga>
                                <com1:fechaRegistro>{fn:data($Comision1/com1:tipoComision/com1:fechaRegistro)}</com1:fechaRegistro>
                                <com1:estado>{fn:data($Comision1/com1:tipoComision/com1:estado)}</com1:estado>
                                <com1:codigoExterno>{fn:data($Comision1/com1:tipoComision/com1:codigoExterno)}</com1:codigoExterno>
                            </com1:tipoComision>
                        else ()
                    }
                    {
                        if ($Comision1/com1:moneda)
                        then 
                            <com1:moneda>
                                {
                                    if ($Comision1/com1:moneda/cat:Id)
                                    then <cat:Id>{fn:data($Comision1/com1:moneda/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Comision1/com1:moneda/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Comision1/com1:moneda/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision1/com1:moneda/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Comision1/com1:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision1/com1:moneda/cat:estatus)
                                    then <cat:estatus>{fn:data($Comision1/com1:moneda/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision1/com1:moneda/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Comision1/com1:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com1:moneda>
                        else ()
                    }
                    {
                        if ($Comision1/com1:montoComision)
                        then <com1:montoComision>{fn:data($Comision1/com1:montoComision)}</com1:montoComision>
                        else ()
                    }
                    {
                        if ($Comision1/com1:montoBase)
                        then 
                            <com1:montoBase>
                                {
                                    if ($Comision1/com1:montoBase/com1:idMontoBase)
                                    then <com1:idMontoBase>{fn:data($Comision1/com1:montoBase/com1:idMontoBase)}</com1:idMontoBase>
                                    else ()
                                }
                                {
                                    if ($Comision1/com1:montoBase/com1:valorMontoBase)
                                    then <com1:valorMontoBase>{fn:data($Comision1/com1:montoBase/com1:valorMontoBase)}</com1:valorMontoBase>
                                    else ()
                                }
                                {
                                    if ($Comision1/com1:montoBase/com1:porcentajeMontoBase)
                                    then <com1:porcentajeMontoBase>{fn:data($Comision1/com1:montoBase/com1:porcentajeMontoBase)}</com1:porcentajeMontoBase>
                                    else ()
                                }
                                {
                                    if ($Comision1/com1:montoBase/com1:descripcionMontoBase)
                                    then <com1:descripcionMontoBase>{fn:data($Comision1/com1:montoBase/com1:descripcionMontoBase)}</com1:descripcionMontoBase>
                                    else ()
                                }
                            </com1:montoBase>
                        else ()
                    }
                    {
                        if ($Comision1/com1:fechaValor)
                        then <com1:fechaValor>{fn:data($Comision1/com1:fechaValor)}</com1:fechaValor>
                        else ()
                    }
                    {
                        if ($Comision1/com1:fechaVencimiento)
                        then <com1:fechaVencimiento>{fn:data($Comision1/com1:fechaVencimiento)}</com1:fechaVencimiento>
                        else ()
                    }
                    {
                        if ($Comision1/com1:fechaInicioCapital)
                        then <com1:fechaInicioCapital>{fn:data($Comision1/com1:fechaInicioCapital)}</com1:fechaInicioCapital>
                        else ()
                    }
                    {
                        if ($Comision1/com1:fechaInicioComision)
                        then <com1:fechaInicioComision>{fn:data($Comision1/com1:fechaInicioComision)}</com1:fechaInicioComision>
                        else ()
                    }
                    {
                        if ($Comision1/com1:tipoFrecuencia)
                        then 
                            <com1:tipoFrecuencia>
                                {
                                    if ($Comision1/com1:tipoFrecuencia/cat:Id)
                                    then <cat:Id>{fn:data($Comision1/com1:tipoFrecuencia/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Comision1/com1:tipoFrecuencia/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Comision1/com1:tipoFrecuencia/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision1/com1:tipoFrecuencia/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Comision1/com1:tipoFrecuencia/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision1/com1:tipoFrecuencia/cat:estatus)
                                    then <cat:estatus>{fn:data($Comision1/com1:tipoFrecuencia/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision1/com1:tipoFrecuencia/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Comision1/com1:tipoFrecuencia/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com1:tipoFrecuencia>
                        else ()
                    }
                    {
                        if ($Comision1/com1:frecuenciaPago)
                        then <com1:frecuenciaPago>{fn:data($Comision1/com1:frecuenciaPago)}</com1:frecuenciaPago>
                        else ()
                    }
                    {
                        if ($Comision1/com1:fondo)
                        then <com1:fondo>{fn:data($Comision1/com1:fondo)}</com1:fondo>
                        else ()
                    }
                    {
                        if ($Comision1/com1:comisionCompartida)
                        then <com1:comisionCompartida>{fn:data($Comision1/com1:comisionCompartida)}</com1:comisionCompartida>
                        else ()
                    }
                    {
                        if ($Comision1/com1:codigoDesembolso)
                        then <com1:codigoDesembolso>{fn:data($Comision1/com1:codigoDesembolso)}</com1:codigoDesembolso>
                        else ()
                    }
                    {
                        if ($Comision1/com1:numeroTesoreria)
                        then <com1:numeroTesoreria>{fn:data($Comision1/com1:numeroTesoreria)}</com1:numeroTesoreria>
                        else ()
                    }
                    {
                        if ($Comision1/com1:codigoContrato)
                        then <com1:codigoContrato>{fn:data($Comision1/com1:codigoContrato)}</com1:codigoContrato>
                        else ()
                    }
                    {
                        if ($Comision1/com1:momentoCobro)
                        then 
                            <com1:momentoCobro>
                                {
                                    if ($Comision1/com1:momentoCobro/cat:Id)
                                    then <cat:Id>{fn:data($Comision1/com1:momentoCobro/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Comision1/com1:momentoCobro/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Comision1/com1:momentoCobro/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision1/com1:momentoCobro/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Comision1/com1:momentoCobro/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision1/com1:momentoCobro/cat:estatus)
                                    then <cat:estatus>{fn:data($Comision1/com1:momentoCobro/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision1/com1:momentoCobro/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Comision1/com1:momentoCobro/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com1:momentoCobro>
                        else ()
                    }
                    {
                        if ($Comision1/com1:tipoTasa)
                        then 
                            <com1:tipoTasa>
                                {
                                    if ($Comision1/com1:tipoTasa/cat:Id)
                                    then <cat:Id>{fn:data($Comision1/com1:tipoTasa/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Comision1/com1:tipoTasa/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Comision1/com1:tipoTasa/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision1/com1:tipoTasa/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Comision1/com1:tipoTasa/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision1/com1:tipoTasa/cat:estatus)
                                    then <cat:estatus>{fn:data($Comision1/com1:tipoTasa/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision1/com1:tipoTasa/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Comision1/com1:tipoTasa/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com1:tipoTasa>
                        else ()
                    }
                    {
                        if ($Comision1/com1:baseCalculo)
                        then 
                            <com1:baseCalculo>
                                {
                                    if ($Comision1/com1:baseCalculo/cat:Id)
                                    then <cat:Id>{fn:data($Comision1/com1:baseCalculo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Comision1/com1:baseCalculo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Comision1/com1:baseCalculo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision1/com1:baseCalculo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Comision1/com1:baseCalculo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision1/com1:baseCalculo/cat:estatus)
                                    then <cat:estatus>{fn:data($Comision1/com1:baseCalculo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision1/com1:baseCalculo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Comision1/com1:baseCalculo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com1:baseCalculo>
                        else ()
                    }
                    {
                        if ($Comision1/com1:responsableComision)
                        then <com1:responsableComision>{fn:data($Comision1/com1:responsableComision)}</com1:responsableComision>
                        else ()
                    }
                    <com1:estadoTCC>
                        {
                            if ($Comision1/com1:estadoTCC/atr:id)
                            then <atr:id>{fn:data($Comision1/com1:estadoTCC/atr:id)}</atr:id>
                            else ()
                        }
                        {
                            if ($Comision1/com1:estadoTCC/atr:descripcion)
                            then <atr:descripcion>{fn:data($Comision1/com1:estadoTCC/atr:descripcion)}</atr:descripcion>
                            else ()
                        }
                        {
                            if ($Comision1/com1:estadoTCC/atr:descripcionCorta)
                            then <atr:descripcionCorta>{fn:data($Comision1/com1:estadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                            else ()
                        }
                        {
                            if ($Comision1/com1:estadoTCC/atr:estatus)
                            then <atr:estatus>{fn:data($Comision1/com1:estadoTCC/atr:estatus)}</atr:estatus>
                            else ()
                        }
                        {
                            if ($Comision1/com1:estadoTCC/atr:codigoExterno)
                            then <atr:codigoExterno>{fn:data($Comision1/com1:estadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                            else ()
                        }
                        {
                            if ($Comision1/com1:estadoTCC/atr:esSubEstado)
                            then <atr:esSubEstado>{fn:data($Comision1/com1:estadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                            else ()
                        }
                    </com1:estadoTCC>
                    <com1:subEstadoTCC>
                        {
                            if ($Comision1/com1:subEstadoTCC/atr:id)
                            then <atr:id>{fn:data($Comision1/com1:subEstadoTCC/atr:id)}</atr:id>
                            else ()
                        }
                        {
                            if ($Comision1/com1:subEstadoTCC/atr:descripcion)
                            then <atr:descripcion>{fn:data($Comision1/com1:subEstadoTCC/atr:descripcion)}</atr:descripcion>
                            else ()
                        }
                        {
                            if ($Comision1/com1:subEstadoTCC/atr:descripcionCorta)
                            then <atr:descripcionCorta>{fn:data($Comision1/com1:subEstadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                            else ()
                        }
                        {
                            if ($Comision1/com1:subEstadoTCC/atr:estatus)
                            then <atr:estatus>{fn:data($Comision1/com1:subEstadoTCC/atr:estatus)}</atr:estatus>
                            else ()
                        }
                        {
                            if ($Comision1/com1:subEstadoTCC/atr:codigoExterno)
                            then <atr:codigoExterno>{fn:data($Comision1/com1:subEstadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                            else ()
                        }
                        {
                            if ($Comision1/com1:subEstadoTCC/atr:esSubEstado)
                            then <atr:esSubEstado>{fn:data($Comision1/com1:subEstadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                            else ()
                        }
                    </com1:subEstadoTCC>
                    <com1:fechaRegistro>{fn:data($Comision1/com1:fechaRegistro)}</com1:fechaRegistro>
                    <com1:estado>{fn:data($Comision1/com1:estado)}</com1:estado>
                    <com1:comisionEnmendada>{fn:data($Comision1/com1:comisionEnmendada)}</com1:comisionEnmendada>
                    <com1:fechaEnmienda>{fn:data($Comision1/com1:fechaEnmienda)}</com1:fechaEnmienda>
                    {
                        if ($Comision1/com1:banSugerida)
                        then <com1:banSugerida>{fn:data($Comision1/com1:banSugerida)}</com1:banSugerida>
                        else ()
                    }
                    {
                        if ($Comision1/com1:numeroCuentaCliente)
                        then <com1:numeroCuentaCliente>{fn:data($Comision1/com1:numeroCuentaCliente)}</com1:numeroCuentaCliente>
                        else ()
                    }
                    {
                        if ($Comision1/com1:observaciones)
                        then <com1:observaciones>{fn:data($Comision1/com1:observaciones)}</com1:observaciones>
                        else ()
                    }
                    {
                        for $configAtributo2 in $Comision1/com1:configAtributo
                        return 
                        <com1:configAtributo>
                            {
                                if ($configAtributo2/atr:id)
                                then <atr:id>{fn:data($configAtributo2/atr:id)}</atr:id>
                                else ()
                            }
                            <atr:columna>{fn:data($configAtributo2/atr:columna)}</atr:columna>
                            {
                                if ($configAtributo2/atr:ordenamiento)
                                then <atr:ordenamiento>{fn:data($configAtributo2/atr:ordenamiento)}</atr:ordenamiento>
                                else ()
                            }
                            {
                                if ($configAtributo2/atr:soloLectura)
                                then <atr:soloLectura>{fn:data($configAtributo2/atr:soloLectura)}</atr:soloLectura>
                                else ()
                            }
                            {
                                if ($configAtributo2/atr:esObligatorio)
                                then <atr:esObligatorio>{fn:data($configAtributo2/atr:esObligatorio)}</atr:esObligatorio>
                                else ()
                            }
                            {
                                if ($configAtributo2/atr:etiqueta)
                                then <atr:etiqueta>{fn:data($configAtributo2/atr:etiqueta)}</atr:etiqueta>
                                else ()
                            }
                            {
                                for $valor2 in $configAtributo2/atr:valor
                                return 
                                <atr:valor>{fn:data($configAtributo2/atr:valor)}</atr:valor>
                            }
                            {
                                if ($configAtributo2/atr:tipoValor)
                                then <atr:tipoValor>{fn:data($configAtributo2/atr:tipoValor)}</atr:tipoValor>
                                else ()
                            }
                            {
                                for $catalogo2 in $configAtributo2/atr:catalogo
                                return 
                                <atr:catalogo>
                                    {
                                        if ($catalogo2/cat:Id)
                                        then <cat:Id>{fn:data($catalogo2/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($catalogo2/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($catalogo2/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($catalogo2/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($catalogo2/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($catalogo2/cat:estatus)
                                        then <cat:estatus>{fn:data($catalogo2/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($catalogo2/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($catalogo2/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </atr:catalogo>
                            }
                        </com1:configAtributo>
                    }
                    {
                        if ($Comision1/com1:Accion)
                        then <com1:Accion>{fn:data($Comision1/com1:Accion)}</com1:Accion>
                        else ()
                    }
                </des2:Comision>
            }
            {
                for $Transferencia in $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:Transferencia
                return 
                <des2:Transferencia>
                    <des2:idTransferencia>{fn:data($Transferencia/des2:idTransferencia)}</des2:idTransferencia>
                    <des2:idDesembolso>{fn:data($Transferencia/des2:idDesembolso)}</des2:idDesembolso>
                    {
                        if ($Transferencia/des2:idFacturador)
                        then <des2:idFacturador>{fn:data($Transferencia/des2:idFacturador)}</des2:idFacturador>
                        else ()
                    }
                    <des2:Monto>
                        <com:tipo>
                            {
                                if ($Transferencia/des2:Monto/com:tipo/cat:Id)
                                then <cat:Id>{fn:data($Transferencia/des2:Monto/com:tipo/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Transferencia/des2:Monto/com:tipo/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Transferencia/des2:Monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Transferencia/des2:Monto/com:tipo/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Transferencia/des2:Monto/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Transferencia/des2:Monto/com:tipo/cat:estatus)
                                then <cat:estatus>{fn:data($Transferencia/des2:Monto/com:tipo/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Transferencia/des2:Monto/com:tipo/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Transferencia/des2:Monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </com:tipo>
                        {
                            if ($Transferencia/des2:Monto/com:importe)
                            then <com:importe>{fn:data($Transferencia/des2:Monto/com:importe)}</com:importe>
                            else ()
                        }
                        {
                            if ($Transferencia/des2:Monto/com:moneda)
                            then 
                                <com:moneda>
                                    {
                                        if ($Transferencia/des2:Monto/com:moneda/cat:Id)
                                        then <cat:Id>{fn:data($Transferencia/des2:Monto/com:moneda/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($Transferencia/des2:Monto/com:moneda/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($Transferencia/des2:Monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($Transferencia/des2:Monto/com:moneda/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($Transferencia/des2:Monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($Transferencia/des2:Monto/com:moneda/cat:estatus)
                                        then <cat:estatus>{fn:data($Transferencia/des2:Monto/com:moneda/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($Transferencia/des2:Monto/com:moneda/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($Transferencia/des2:Monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </com:moneda>
                            else ()
                        }
                    </des2:Monto>
                    <des2:FormaTransferencia>
                        {
                            if ($Transferencia/des2:FormaTransferencia/cat:Id)
                            then <cat:Id>{fn:data($Transferencia/des2:FormaTransferencia/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($Transferencia/des2:FormaTransferencia/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($Transferencia/des2:FormaTransferencia/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($Transferencia/des2:FormaTransferencia/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($Transferencia/des2:FormaTransferencia/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($Transferencia/des2:FormaTransferencia/cat:estatus)
                            then <cat:estatus>{fn:data($Transferencia/des2:FormaTransferencia/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($Transferencia/des2:FormaTransferencia/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($Transferencia/des2:FormaTransferencia/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </des2:FormaTransferencia>
                    {
                        if ($Transferencia/des2:referenciaMensaje)
                        then <des2:referenciaMensaje>{fn:data($Transferencia/des2:referenciaMensaje)}</des2:referenciaMensaje>
                        else ()
                    }
                    {
                        if ($Transferencia/des2:esConsolidada)
                        then <des2:esConsolidada>{fn:data($Transferencia/des2:esConsolidada)}</des2:esConsolidada>
                        else ()
                    }
                    {
                        if ($Transferencia/des2:esConsolidacionPadre)
                        then <des2:esConsolidacionPadre>{fn:data($Transferencia/des2:esConsolidacionPadre)}</des2:esConsolidacionPadre>
                        else ()
                    }
                    {
                        if ($Transferencia/des2:idConsolidacionPadre)
                        then <des2:idConsolidacionPadre>{fn:data($Transferencia/des2:idConsolidacionPadre)}</des2:idConsolidacionPadre>
                        else ()
                    }
                    {
                        if ($Transferencia/des2:bhqTransferencia)
                        then <des2:bhqTransferencia>{fn:data($Transferencia/des2:bhqTransferencia)}</des2:bhqTransferencia>
                        else ()
                    }
                    {
                        if ($Transferencia/des2:NumeroReserva)
                        then <des2:NumeroReserva>{fn:data($Transferencia/des2:NumeroReserva)}</des2:NumeroReserva>
                        else ()
                    }
                    {
                        if ($Transferencia/des2:idBancoTransferencia)
                        then <des2:idBancoTransferencia>{fn:data($Transferencia/des2:idBancoTransferencia)}</des2:idBancoTransferencia>
                        else ()
                    }
                    {
                        if ($Transferencia/des2:numeroCuenta)
                        then <des2:numeroCuenta>{fn:data($Transferencia/des2:numeroCuenta)}</des2:numeroCuenta>
                        else ()
                    }
                    <des2:nombreCuenta>{fn:data($Transferencia/des2:nombreCuenta)}</des2:nombreCuenta>
                    {
                        if ($Transferencia/des2:nombreBanco)
                        then <des2:nombreBanco>{fn:data($Transferencia/des2:nombreBanco)}</des2:nombreBanco>
                        else ()
                    }
                    <des2:idOperacion>{fn:data($Transferencia/des2:idOperacion)}</des2:idOperacion>
                    <des2:tipoMensaje>{fn:data($Transferencia/des2:tipoMensaje)}</des2:tipoMensaje>
                    {
                        if ($Transferencia/des2:estado)
                        then <des2:estado>{fn:data($Transferencia/des2:estado)}</des2:estado>
                        else ()
                    }
                    <des2:fechaRegistro>{fn:data($Transferencia/des2:fechaRegistro)}</des2:fechaRegistro>
                    {
                        if ($Transferencia/des2:Beneficiario)
                        then 
                            <des2:Beneficiario>
                                {
                                    if ($Transferencia/des2:Beneficiario/des2:tipoOpcion)
                                    then <des2:tipoOpcion>{fn:data($Transferencia/des2:Beneficiario/des2:tipoOpcion)}</des2:tipoOpcion>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des2:Beneficiario/des2:numeroCta)
                                    then <des2:numeroCta>{fn:data($Transferencia/des2:Beneficiario/des2:numeroCta)}</des2:numeroCta>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des2:Beneficiario/des2:identificador)
                                    then <des2:identificador>{fn:data($Transferencia/des2:Beneficiario/des2:identificador)}</des2:identificador>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des2:Beneficiario/des2:beneficiario)
                                    then <des2:beneficiario>{fn:data($Transferencia/des2:Beneficiario/des2:beneficiario)}</des2:beneficiario>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des2:Beneficiario/des2:direccion)
                                    then <des2:direccion>{fn:data($Transferencia/des2:Beneficiario/des2:direccion)}</des2:direccion>
                                    else ()
                                }
                            </des2:Beneficiario>
                        else ()
                    }
                    {
                        if ($Transferencia/des2:Banco)
                        then 
                            <des2:Banco>
                                {
                                    if ($Transferencia/des2:Banco/des2:tipoOpcion)
                                    then <des2:tipoOpcion>{fn:data($Transferencia/des2:Banco/des2:tipoOpcion)}</des2:tipoOpcion>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des2:Banco/des2:numeroCta)
                                    then <des2:numeroCta>{fn:data($Transferencia/des2:Banco/des2:numeroCta)}</des2:numeroCta>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des2:Banco/des2:identificador)
                                    then <des2:identificador>{fn:data($Transferencia/des2:Banco/des2:identificador)}</des2:identificador>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des2:Banco/des2:beneficiario)
                                    then <des2:beneficiario>{fn:data($Transferencia/des2:Banco/des2:beneficiario)}</des2:beneficiario>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des2:Banco/des2:direccion)
                                    then <des2:direccion>{fn:data($Transferencia/des2:Banco/des2:direccion)}</des2:direccion>
                                    else ()
                                }
                            </des2:Banco>
                        else ()
                    }
                    {
                        if ($Transferencia/des2:Intermediario)
                        then 
                            <des2:Intermediario>
                                {
                                    if ($Transferencia/des2:Intermediario/des2:tipoOpcion)
                                    then <des2:tipoOpcion>{fn:data($Transferencia/des2:Intermediario/des2:tipoOpcion)}</des2:tipoOpcion>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des2:Intermediario/des2:numeroCta)
                                    then <des2:numeroCta>{fn:data($Transferencia/des2:Intermediario/des2:numeroCta)}</des2:numeroCta>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des2:Intermediario/des2:identificador)
                                    then <des2:identificador>{fn:data($Transferencia/des2:Intermediario/des2:identificador)}</des2:identificador>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des2:Intermediario/des2:beneficiario)
                                    then <des2:beneficiario>{fn:data($Transferencia/des2:Intermediario/des2:beneficiario)}</des2:beneficiario>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des2:Intermediario/des2:direccion)
                                    then <des2:direccion>{fn:data($Transferencia/des2:Intermediario/des2:direccion)}</des2:direccion>
                                    else ()
                                }
                            </des2:Intermediario>
                        else ()
                    }
                </des2:Transferencia>
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:TransferenciaFT05)
                then 
                    <des2:TransferenciaFT05>
                        <des2:idTransferenciaFT05>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:TransferenciaFT05/des2:idTransferenciaFT05)}</des2:idTransferenciaFT05>
                        <des2:idDesembolso>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:TransferenciaFT05/des2:idDesembolso)}</des2:idDesembolso>
                        {
                            if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:TransferenciaFT05/des2:idFacturador)
                            then <des2:idFacturador>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:TransferenciaFT05/des2:idFacturador)}</des2:idFacturador>
                            else ()
                        }
                        {
                            if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:TransferenciaFT05/des2:FechaEfectiva)
                            then <des2:FechaEfectiva>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:TransferenciaFT05/des2:FechaEfectiva)}</des2:FechaEfectiva>
                            else ()
                        }
                    </des2:TransferenciaFT05>
                else ()
            }
            {
                for $TransferenciaRecursos in $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:TransferenciaRecursos
                return 
                <des2:TransferenciaRecursos>
                    <des2:idTransferencia>{fn:data($TransferenciaRecursos/des2:idTransferencia)}</des2:idTransferencia>
                    <des2:idDesembolso>{fn:data($TransferenciaRecursos/des2:idDesembolso)}</des2:idDesembolso>
                    {
                        if ($TransferenciaRecursos/des2:idFacturador)
                        then <des2:idFacturador>{fn:data($TransferenciaRecursos/des2:idFacturador)}</des2:idFacturador>
                        else ()
                    }
                    <des2:idLineaPasiva>{fn:data($TransferenciaRecursos/des2:idLineaPasiva)}</des2:idLineaPasiva>
                    <des2:Monto>
                        <com:tipo>
                            {
                                if ($TransferenciaRecursos/des2:Monto/com:tipo/cat:Id)
                                then <cat:Id>{fn:data($TransferenciaRecursos/des2:Monto/com:tipo/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($TransferenciaRecursos/des2:Monto/com:tipo/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($TransferenciaRecursos/des2:Monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($TransferenciaRecursos/des2:Monto/com:tipo/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($TransferenciaRecursos/des2:Monto/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($TransferenciaRecursos/des2:Monto/com:tipo/cat:estatus)
                                then <cat:estatus>{fn:data($TransferenciaRecursos/des2:Monto/com:tipo/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($TransferenciaRecursos/des2:Monto/com:tipo/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($TransferenciaRecursos/des2:Monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </com:tipo>
                        {
                            if ($TransferenciaRecursos/des2:Monto/com:importe)
                            then <com:importe>{fn:data($TransferenciaRecursos/des2:Monto/com:importe)}</com:importe>
                            else ()
                        }
                        {
                            if ($TransferenciaRecursos/des2:Monto/com:moneda)
                            then 
                                <com:moneda>
                                    {
                                        if ($TransferenciaRecursos/des2:Monto/com:moneda/cat:Id)
                                        then <cat:Id>{fn:data($TransferenciaRecursos/des2:Monto/com:moneda/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($TransferenciaRecursos/des2:Monto/com:moneda/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($TransferenciaRecursos/des2:Monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($TransferenciaRecursos/des2:Monto/com:moneda/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($TransferenciaRecursos/des2:Monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($TransferenciaRecursos/des2:Monto/com:moneda/cat:estatus)
                                        then <cat:estatus>{fn:data($TransferenciaRecursos/des2:Monto/com:moneda/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($TransferenciaRecursos/des2:Monto/com:moneda/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($TransferenciaRecursos/des2:Monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </com:moneda>
                            else ()
                        }
                    </des2:Monto>
                    <des2:fecha>{fn:data($TransferenciaRecursos/des2:fecha)}</des2:fecha>
                    <des2:fechaEfectiva>{fn:data($TransferenciaRecursos/des2:fechaEfectiva)}</des2:fechaEfectiva>
                    <des2:FormaTransferencia>
                        {
                            if ($TransferenciaRecursos/des2:FormaTransferencia/cat:Id)
                            then <cat:Id>{fn:data($TransferenciaRecursos/des2:FormaTransferencia/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($TransferenciaRecursos/des2:FormaTransferencia/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($TransferenciaRecursos/des2:FormaTransferencia/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($TransferenciaRecursos/des2:FormaTransferencia/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($TransferenciaRecursos/des2:FormaTransferencia/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($TransferenciaRecursos/des2:FormaTransferencia/cat:estatus)
                            then <cat:estatus>{fn:data($TransferenciaRecursos/des2:FormaTransferencia/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($TransferenciaRecursos/des2:FormaTransferencia/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($TransferenciaRecursos/des2:FormaTransferencia/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </des2:FormaTransferencia>
                    {
                        if ($TransferenciaRecursos/des2:idBanco)
                        then <des2:idBanco>{fn:data($TransferenciaRecursos/des2:idBanco)}</des2:idBanco>
                        else ()
                    }
                    {
                        if ($TransferenciaRecursos/des2:nombreBanco)
                        then <des2:nombreBanco>{fn:data($TransferenciaRecursos/des2:nombreBanco)}</des2:nombreBanco>
                        else ()
                    }
                    {
                        if ($TransferenciaRecursos/des2:numeroCuenta)
                        then <des2:numeroCuenta>{fn:data($TransferenciaRecursos/des2:numeroCuenta)}</des2:numeroCuenta>
                        else ()
                    }
                    {
                        if ($TransferenciaRecursos/des2:nombreCuenta)
                        then <des2:nombreCuenta>{fn:data($TransferenciaRecursos/des2:nombreCuenta)}</des2:nombreCuenta>
                        else ()
                    }
                    {
                        if ($TransferenciaRecursos/des2:estatus)
                        then <des2:estatus>{fn:data($TransferenciaRecursos/des2:estatus)}</des2:estatus>
                        else ()
                    }
                    {
                        if ($TransferenciaRecursos/des2:fechaRegistro)
                        then <des2:fechaRegistro>{fn:data($TransferenciaRecursos/des2:fechaRegistro)}</des2:fechaRegistro>
                        else ()
                    }
                </des2:TransferenciaRecursos>
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE)
                then 
                    <des2:HerramientaCE>
                        <her:ActividadEconomica>
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:ActividadEconomica/cat:Id)
                                then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:ActividadEconomica/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:ActividadEconomica/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:ActividadEconomica/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:ActividadEconomica/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:ActividadEconomica/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:ActividadEconomica/cat:estatus)
                                then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:ActividadEconomica/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:ActividadEconomica>
                        <her:EjeEstrategico>
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:EjeEstrategico/cat:Id)
                                then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:EjeEstrategico/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:EjeEstrategico/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:EjeEstrategico/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:EjeEstrategico/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:EjeEstrategico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:EjeEstrategico/cat:estatus)
                                then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:EjeEstrategico/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:EjeEstrategico>
                        <her:AreaFocalizacion>
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:AreaFocalizacion/cat:Id)
                                then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:AreaFocalizacion/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:AreaFocalizacion/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:AreaFocalizacion/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:AreaFocalizacion/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:AreaFocalizacion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:AreaFocalizacion/cat:estatus)
                                then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:AreaFocalizacion/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:AreaFocalizacion>
                    </des2:HerramientaCE>
                else ()
            }
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:modalidadFinan)
                then 
                    <des2:modalidadFinan>
                        {
                            if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:modalidadFinan/cat:Id)
                            then <cat:Id>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:modalidadFinan/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:modalidadFinan/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:modalidadFinan/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:modalidadFinan/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:modalidadFinan/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:modalidadFinan/cat:estatus)
                            then <cat:estatus>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:modalidadFinan/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:modalidadFinan/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:modalidadFinan/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </des2:modalidadFinan>
                else ()
            }
        </des:ContratoDesembolso>
    </des:ValidarLoanComplejoRequest>
};

local:funcXq_requestvalidarloancomplejo($outConsultarInformacionDesembolso.response, $outConsultarLineaCreditoById.ConsultarLineaCreditoByIdResponse, $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response)
