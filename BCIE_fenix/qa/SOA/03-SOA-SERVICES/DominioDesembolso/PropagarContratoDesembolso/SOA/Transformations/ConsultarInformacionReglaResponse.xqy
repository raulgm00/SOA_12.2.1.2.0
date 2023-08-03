xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns3="http://www.bcie.org/OperacionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse as element() (:: schema-element(ns1:ConsultarDesembolsosConfiguracionByIdResponse) ::) external;
declare variable $outConsultarLineaCreditoById.response as element() (:: schema-element(ns2:ConsultarLineaCreditoByIdLineaCreditoResponse) ::) external;
declare variable $outConsultarOperacionById.response as element() (:: schema-element(ns3:ConsultarOperacionResponse) ::) external;

declare function local:funcConsultarinformacionreglaresponse($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse as element() (:: schema-element(ns1:ConsultarDesembolsosConfiguracionByIdResponse) ::), 
                                                             $outConsultarLineaCreditoById.response as element() (:: schema-element(ns2:ConsultarLineaCreditoByIdLineaCreditoResponse) ::), 
                                                             $outConsultarOperacionById.response as element() (:: schema-element(ns3:ConsultarOperacionResponse) ::)) 
                                                             as element() (:: schema-element(ns1:ConsultarInformacionReglasByIdResponse) ::) {
    <ns1:ConsultarInformacionReglasByIdResponse>
        <ns1:Cliente>
            <cli:idCliente>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:idCliente)}</cli:idCliente>
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:idFacturador)
                then <cli:idFacturador>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:idFacturador)}</cli:idFacturador>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:razonSocial)
                then <cli:razonSocial>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:razonSocial)}</cli:razonSocial>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:abreviatura)
                then <cli:abreviatura>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:abreviatura)}</cli:abreviatura>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoPersona)
                then 
                    <cli:tipoPersona>
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoPersona/cat:Id)
                            then <cat:Id>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoPersona/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoPersona/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoPersona/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoPersona/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoPersona/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoPersona/cat:estatus)
                            then <cat:estatus>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoPersona/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoPersona/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoPersona/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </cli:tipoPersona>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoCliente)
                then 
                    <cli:tipoCliente>
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoCliente/cat:Id)
                            then <cat:Id>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoCliente/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoCliente/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoCliente/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoCliente/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoCliente/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoCliente/cat:estatus)
                            then <cat:estatus>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoCliente/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoCliente/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoCliente/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </cli:tipoCliente>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:sector)
                then 
                    <cli:sector>
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:sector/cat:Id)
                            then <cat:Id>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:sector/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:sector/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:sector/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:sector/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:sector/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:sector/cat:estatus)
                            then <cat:estatus>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:sector/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:sector/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:sector/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </cli:sector>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoInstitucion)
                then 
                    <cli:tipoInstitucion>
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoInstitucion/cat:Id)
                            then <cat:Id>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoInstitucion/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoInstitucion/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoInstitucion/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoInstitucion/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoInstitucion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoInstitucion/cat:estatus)
                            then <cat:estatus>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoInstitucion/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoInstitucion/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoInstitucion/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </cli:tipoInstitucion>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:pais)
                then 
                    <cli:pais>
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:pais/cat:Id)
                            then <cat:Id>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:pais/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:pais/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:pais/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:pais/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:pais/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:pais/cat:estatus)
                            then <cat:estatus>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:pais/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:pais/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:pais/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </cli:pais>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:grupoEconomico)
                then 
                    <cli:grupoEconomico>
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:grupoEconomico/cat:Id)
                            then <cat:Id>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:grupoEconomico/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:grupoEconomico/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:grupoEconomico/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:grupoEconomico/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:grupoEconomico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:grupoEconomico/cat:estatus)
                            then <cat:estatus>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:grupoEconomico/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:grupoEconomico/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:grupoEconomico/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </cli:grupoEconomico>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoIdentificacion)
                then <cli:tipoIdentificacion>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:tipoIdentificacion)}</cli:tipoIdentificacion>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:numeroIdentificacion)
                then <cli:numeroIdentificacion>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:numeroIdentificacion)}</cli:numeroIdentificacion>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:direccion)
                then <cli:direccion>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:direccion)}</cli:direccion>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:telefono)
                then <cli:telefono>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:telefono)}</cli:telefono>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:fax)
                then <cli:fax>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:fax)}</cli:fax>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:email)
                then <cli:email>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:email)}</cli:email>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:ciudad)
                then <cli:ciudad>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:ciudad)}</cli:ciudad>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:oficina)
                then 
                    <cli:oficina>
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:oficina/cat:Id)
                            then <cat:Id>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:oficina/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:oficina/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:oficina/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:oficina/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:oficina/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:oficina/cat:estatus)
                            then <cat:estatus>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:oficina/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:oficina/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:oficina/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </cli:oficina>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:grupoEmpresarial)
                then <cli:grupoEmpresarial>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:grupoEmpresarial)}</cli:grupoEmpresarial>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:fechaRegistro)
                then <cli:fechaRegistro>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:fechaRegistro)}</cli:fechaRegistro>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:fechaAprobacion)
                then <cli:fechaAprobacion>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:fechaAprobacion)}</cli:fechaAprobacion>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:ejecutivo)
                then <cli:ejecutivo>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:ejecutivo)}</cli:ejecutivo>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:responsableCliente)
                then <cli:responsableCliente>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:responsableCliente)}</cli:responsableCliente>
                else ()
            }
            {
                for $Contactos in $outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:Contactos
                return 
                <cli:Contactos>
                    {
                        for $Contacto in $Contactos/cli:Contacto
                        return 
                        <cli:Contacto>
                            <cli:idContacto>{fn:data($Contacto/cli:idContacto)}</cli:idContacto>
                            <cli:idCliente>{fn:data($Contacto/cli:idCliente)}</cli:idCliente>
                            <cli:nombre>{fn:data($Contacto/cli:nombre)}</cli:nombre>
                            <cli:telefono>{fn:data($Contacto/cli:telefono)}</cli:telefono>
                            <cli:correoElectronico>{fn:data($Contacto/cli:correoElectronico)}</cli:correoElectronico>
                            <cli:cargo>{fn:data($Contacto/cli:cargo)}</cli:cargo>
                            <cli:tipo>{fn:data($Contacto/cli:tipo)}</cli:tipo>
                            <cli:fechaRegistro>{fn:data($Contacto/cli:fechaRegistro)}</cli:fechaRegistro>
                            <cli:idContactosClientes>{fn:data($Contacto/cli:idContactosClientes)}</cli:idContactosClientes>
                        </cli:Contacto>
                    }
                </cli:Contactos>
            }
            <cli:InformacionCorrecta>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:InformacionCorrecta)}</cli:InformacionCorrecta>
            <cli:ActualizacionPermitida>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:ActualizacionPermitida)}</cli:ActualizacionPermitida>
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:comentarioAprobacion)
                then <cli:comentarioAprobacion>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:comentarioAprobacion)}</cli:comentarioAprobacion>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:revisadoAprobacion)
                then <cli:revisadoAprobacion>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:revisadoAprobacion)}</cli:revisadoAprobacion>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:status)
                then <cli:status>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:status)}</cli:status>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:fechaBaja)
                then <cli:fechaBaja>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:fechaBaja)}</cli:fechaBaja>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:diaPago)
                then <cli:diaPago>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:diaPago)}</cli:diaPago>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:SCR)
                then 
                    <cli:SRC>
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:SCR/cat:Id)
                            then <cat:Id>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:SCR/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:SCR/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:SCR/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:SCR/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:SCR/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:SCR/cat:estatus)
                            then <cat:estatus>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:SCR/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:SCR/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:SCR/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </cli:SRC>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:perspectiva)
                then 
                    <cli:perspectiva>
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:perspectiva/cat:Id)
                            then <cat:Id>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:perspectiva/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:perspectiva/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:perspectiva/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:perspectiva/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:perspectiva/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:perspectiva/cat:estatus)
                            then <cat:estatus>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:perspectiva/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:perspectiva/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:perspectiva/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </cli:perspectiva>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:enMora)
                then <cli:enMora>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:enMora)}</cli:enMora>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:mora)
                then 
                    <cli:mora>
                        <cli:dias>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:mora/cli:dias)}</cli:dias>
                        <cli:monto>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:mora/cli:monto)}</cli:monto>
                        {
                            if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:mora/cli:tipo)
                            then 
                                <cli:tipo>
                                    {
                                        if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:mora/cli:tipo/cat:Id)
                                        then <cat:Id>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:mora/cli:tipo/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:mora/cli:tipo/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:mora/cli:tipo/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:mora/cli:tipo/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:mora/cli:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:mora/cli:tipo/cat:estatus)
                                        then <cat:estatus>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:mora/cli:tipo/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:mora/cli:tipo/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:mora/cli:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </cli:tipo>
                            else ()
                        }
                    </cli:mora>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:deteriorado)
                then <cli:deteriorado>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:deteriorado)}</cli:deteriorado>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:reserva)
                then 
                    <cli:reserva>
                        <cli:importeReserva>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:reserva/cli:importeReserva)}</cli:importeReserva>
                        <cli:tipoReserva>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:reserva/cli:tipoReserva)}</cli:tipoReserva>
                    </cli:reserva>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:requiereEnvioAutomatico)
                then <cli:requiereEnvioAutomatico>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:requiereEnvioAutomatico)}</cli:requiereEnvioAutomatico>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR)
                then 
                    <cli:detalleSCR>
                        <cli:scrFuente>
                            <cli:idCliente>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:idCliente)}</cli:idCliente>
                            {
                                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:idFacturador)
                                then <cli:idFacturador>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:idFacturador)}</cli:idFacturador>
                                else ()
                            }
                            {
                                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:scr)
                                then <cli:scr>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:scr)}</cli:scr>
                                else ()
                            }
                            {
                                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:estadoScr)
                                then <cli:estadoScr>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:estadoScr)}</cli:estadoScr>
                                else ()
                            }
                            {
                                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:observacion)
                                then <cli:observacion>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:observacion)}</cli:observacion>
                                else ()
                            }
                            {
                                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:fecha)
                                then <cli:fecha>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:fecha)}</cli:fecha>
                                else ()
                            }
                            {
                                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:usuarioModifico)
                                then <cli:usuarioModifico>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:usuarioModifico)}</cli:usuarioModifico>
                                else ()
                            }
                            {
                                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:usuarioValido)
                                then <cli:usuarioValido>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:usuarioValido)}</cli:usuarioValido>
                                else ()
                            }
                        </cli:scrFuente>
                        <cli:scrReferencia>
                            <cli:idCliente>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:idCliente)}</cli:idCliente>
                            {
                                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:idFacturador)
                                then <cli:idFacturador>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:idFacturador)}</cli:idFacturador>
                                else ()
                            }
                            {
                                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:scr)
                                then <cli:scr>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:scr)}</cli:scr>
                                else ()
                            }
                            {
                                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:estadoScr)
                                then <cli:estadoScr>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:estadoScr)}</cli:estadoScr>
                                else ()
                            }
                            {
                                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:observacion)
                                then <cli:observacion>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:observacion)}</cli:observacion>
                                else ()
                            }
                            {
                                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:fecha)
                                then <cli:fecha>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:fecha)}</cli:fecha>
                                else ()
                            }
                            {
                                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:usuarioModifico)
                                then <cli:usuarioModifico>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:usuarioModifico)}</cli:usuarioModifico>
                                else ()
                            }
                            {
                                if ($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:usuarioValido)
                                then <cli:usuarioValido>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:usuarioValido)}</cli:usuarioValido>
                                else ()
                            }
                        </cli:scrReferencia>
                        <cli:estatusComparacion>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:cliente/cli:detalleSCR/cli:estatusComparacion)}</cli:estatusComparacion>
                    </cli:detalleSCR>
                else ()
            }
        </ns1:Cliente>
        <ns1:LineaCredito>
            {
                if ($outConsultarLineaCreditoById.response/ns2:Operacion/ope:contrato/con:LineaCredito/lin:idLineaCredito)
                then <lin:idLineaCredito>{fn:data($outConsultarLineaCreditoById.response/ns2:Operacion/ope:contrato/con:LineaCredito/lin:idLineaCredito)}</lin:idLineaCredito>
                else ()
            }
            {
                if ($outConsultarLineaCreditoById.response/ns2:Operacion/ope:contrato/con:LineaCredito/lin:idContrato)
                then <lin:idContrato>{fn:data($outConsultarLineaCreditoById.response/ns2:Operacion/ope:contrato/con:LineaCredito/lin:idContrato)}</lin:idContrato>
                else ()
            }
            <lin:NumeroLineaCredito>{fn:data($outConsultarLineaCreditoById.response/ns2:Operacion/ope:contrato/con:LineaCredito/lin:NumeroLineaCredito)}</lin:NumeroLineaCredito>
            <lin:Descripcion>{fn:data($outConsultarLineaCreditoById.response/ns2:Operacion/ope:contrato/con:LineaCredito/lin:Descripcion)}</lin:Descripcion>
            {
                if ($outConsultarLineaCreditoById.response/ns2:Operacion/ope:contrato/con:LineaCredito/lin:Flexcube)
                then 
                    <lin:Flexcube>
                        {
                            if ($outConsultarLineaCreditoById.response/ns2:Operacion/ope:contrato/con:LineaCredito/lin:Flexcube/lin:id)
                            then <lin:id>{fn:data($outConsultarLineaCreditoById.response/ns2:Operacion/ope:contrato/con:LineaCredito/lin:Flexcube/lin:id)}</lin:id>
                            else ()
                        }
                        {
                            if ($outConsultarLineaCreditoById.response/ns2:Operacion/ope:contrato/con:LineaCredito/lin:Flexcube/lin:idProductoFlexcube)
                            then <lin:idProductoFlexcube>{fn:data($outConsultarLineaCreditoById.response/ns2:Operacion/ope:contrato/con:LineaCredito/lin:Flexcube/lin:idProductoFlexcube)}</lin:idProductoFlexcube>
                            else ()
                        }
                        {
                            if ($outConsultarLineaCreditoById.response/ns2:Operacion/ope:contrato/con:LineaCredito/lin:Flexcube/lin:idFlexcubePasivo)
                            then <lin:idFlexcubePasivo>{fn:data($outConsultarLineaCreditoById.response/ns2:Operacion/ope:contrato/con:LineaCredito/lin:Flexcube/lin:idFlexcubePasivo)}</lin:idFlexcubePasivo>
                            else ()
                        }
                    </lin:Flexcube>
                else ()
            }
            <lin:Fondo>{fn:data($outConsultarLineaCreditoById.response/ns2:Operacion/ope:contrato/con:LineaCredito/lin:Fondo)}</lin:Fondo>
            <lin:MontoLinea>{fn:data($outConsultarLineaCreditoById.response/ns2:Operacion/ope:contrato/con:LineaCredito/lin:MontoLinea)}</lin:MontoLinea>
            {
                if ($outConsultarLineaCreditoById.response/ns2:Operacion/ope:contrato/con:LineaCredito/lin:EsRevolvente)
                then <lin:EsRevolvente>{fn:data($outConsultarLineaCreditoById.response/ns2:Operacion/ope:contrato/con:LineaCredito/lin:EsRevolvente)}</lin:EsRevolvente>
                else ()
            }
            <lin:TratamientoDiasFeriados></lin:TratamientoDiasFeriados>
            <lin:FechaValor>{fn:data($outConsultarLineaCreditoById.response/ns2:Operacion/ope:contrato/con:LineaCredito/lin:FechaValor)}</lin:FechaValor>
            <lin:CodigoAsignacion>{fn:data($outConsultarLineaCreditoById.response/ns2:Operacion/ope:contrato/con:LineaCredito/lin:CodigoAsignacion)}</lin:CodigoAsignacion>
            <lin:DescripcionAsignacion></lin:DescripcionAsignacion>
            {
                if ($outConsultarLineaCreditoById.response/ns2:Operacion/ope:contrato/con:LineaCredito/lin:CondicionEspecial)
                then <lin:CondicionEspecial>{fn:data($outConsultarLineaCreditoById.response/ns2:Operacion/ope:contrato/con:LineaCredito/lin:CondicionEspecial)}</lin:CondicionEspecial>
                else ()
            }
        </ns1:LineaCredito>
        <ns1:Operacion>
            {
                if ($outConsultarLineaCreditoById.response/ns2:Operacion/ope:idOperacion)
                then <ope:idOperacion>{fn:data($outConsultarLineaCreditoById.response/ns2:Operacion/ope:idOperacion)}</ope:idOperacion>
                else ()
            }
            {
                if ($outConsultarLineaCreditoById.response/ns2:Operacion/ope:responsable)
                then <ope:responsable>{fn:data($outConsultarLineaCreditoById.response/ns2:Operacion/ope:responsable)}</ope:responsable>
                else ()
            }
            {
                if ($outConsultarOperacionById.response/ns3:Operacion/ope:nombre)
                then <ope:nombre>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:nombre)}</ope:nombre>
                else ()
            }
            <ope:producto>
                <pro:idProducto>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:producto/pro:idProducto)}</pro:idProducto>
                <pro:descripcion>{fn:data($outConsultarOperacionById.response/ns3:Operacion/ope:producto/pro:descripcion)}</pro:descripcion>
            </ope:producto>
            <ope:actividadEconomica>
                {
                    if ($outConsultarLineaCreditoById.response/ns2:Operacion/ope:actividadEconomica/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($outConsultarLineaCreditoById.response/ns2:Operacion/ope:actividadEconomica/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </ope:actividadEconomica>
            <ope:actividadEconomicaAsociada>
                {
                    if ($outConsultarLineaCreditoById.response/ns2:Operacion/ope:actividadEconomicaAsociada/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($outConsultarLineaCreditoById.response/ns2:Operacion/ope:actividadEconomicaAsociada/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </ope:actividadEconomicaAsociada>
            <ope:areaFocalizacion>
                {
                    if ($outConsultarLineaCreditoById.response/ns2:Operacion/ope:areaFocalizacion/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($outConsultarLineaCreditoById.response/ns2:Operacion/ope:areaFocalizacion/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </ope:areaFocalizacion>
            <ope:ejeEstrategico>
                {
                    if ($outConsultarLineaCreditoById.response/ns2:Operacion/ope:ejeEstrategico/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($outConsultarLineaCreditoById.response/ns2:Operacion/ope:ejeEstrategico/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </ope:ejeEstrategico>
        </ns1:Operacion>
        <ns1:Desembolso>
            <des:idDesembolso>{fn:data($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:idDesembolso)}</des:idDesembolso>
            {
                if ($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:idFacturador)
                then <des:idFacturador>{fn:data($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:idFacturador)}</des:idFacturador>
                else ()
            }
            {
                for $monto in $outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:monto
                return 
                <des:monto>
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
                </des:monto>
            }
            <des:estado>
                {
                    if ($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:estado/cat:Id)
                    then <cat:Id>{fn:data($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:estado/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:estado/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:estado/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
                {
                    if ($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:estado/cat:DescripcionCorta)
                    then <cat:DescripcionCorta>{fn:data($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    else ()
                }
                {
                    if ($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:estado/cat:estatus)
                    then <cat:estatus>{fn:data($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:estado/cat:estatus)}</cat:estatus>
                    else ()
                }
                {
                    if ($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:estado/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:estado/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </des:estado>
            <des:programado>{fn:data($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:programado)}</des:programado>
            <des:fechaEstimadaDesembolsar>{fn:data($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:fechaEstimadaDesembolsar)}</des:fechaEstimadaDesembolsar>
            <des:fechaEfectiva>{fn:data($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:fechaEfectiva)}</des:fechaEfectiva>
            <des:fechaAsignacion>{fn:data($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:fechaAsignacion)}</des:fechaAsignacion>
            <des:fechaRegistro>{fn:data($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:fechaRegistro)}</des:fechaRegistro>
            <des:estatus>{fn:data($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:estatus)}</des:estatus>
            <des:idLinea>{fn:data($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:idLinea)}</des:idLinea>
            {
                if ($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:recursosExternos)
                then <des:recursosExternos>{fn:data($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:recursosExternos)}</des:recursosExternos>
                else ()
            }
            <des:Programa>
              {$outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:Programa/*}
            </des:Programa>
            {
            for $utilizacion in $outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:Utilizacion
            return
            <des:Utilizacion>
                <lin:idFuente>{fn:data($utilizacion/lin:idFuente)}</lin:idFuente>
                <lin:idLineaCredito>{fn:data($utilizacion/lin:idLineaCredito)}</lin:idLineaCredito>
                <lin:idLineaPasiva>{fn:data($utilizacion/lin:idLineaPasiva)}</lin:idLineaPasiva>
                <lin:codigoLineaPasiva>{fn:data($utilizacion/lin:codigoLineaPasiva)}</lin:codigoLineaPasiva>
                <lin:idFacturadorLineaPasiva>{fn:data($utilizacion/lin:idFacturadorLineaPasiva)}</lin:idFacturadorLineaPasiva>
                <lin:idFondo>{fn:data($utilizacion/lin:idFondo)}</lin:idFondo>
                <lin:Descripcion>{fn:data($utilizacion/lin:Descripcion)}</lin:Descripcion>
                <lin:FechaObtenido>{fn:data($utilizacion/lin:FechaObtenido)}</lin:FechaObtenido>
                <lin:MontoAsignado>{fn:data($utilizacion/lin:MontoAsignado)}</lin:MontoAsignado>
                <lin:montoDisponible>{fn:data($utilizacion/lin:montoDisponible)}</lin:montoDisponible>
                {
                    for $Monto in $utilizacion/lin:Monto
                    return 
                    <lin:Monto>
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
                    </lin:Monto>
                }
                <lin:NumeroAsignacion>{fn:data($utilizacion/lin:NumeroAsignacion)}</lin:NumeroAsignacion>
                <lin:Comentario>{fn:data($utilizacion/lin:Comentario)}</lin:Comentario>
                <lin:idContrato>{fn:data($utilizacion/lin:idContrato)}</lin:idContrato>
                <lin:FechaRegistro>{fn:data($utilizacion/lin:FechaRegistro)}</lin:FechaRegistro>
                <lin:Estado>{fn:data($utilizacion/lin:Estado)}</lin:Estado>
            </des:Utilizacion>
            }
            <des:HerramientaCE>{$outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:HerramientaCE/*}</des:HerramientaCE>
            {
                if ($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:modalidadFinan)
                then 
                    <des:modalidadFinan>
                        {
                            if ($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:modalidadFinan/cat:Id)
                            then <cat:Id>{fn:data($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:modalidadFinan/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:modalidadFinan/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:modalidadFinan/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:modalidadFinan/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:modalidadFinan/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:modalidadFinan/cat:estatus)
                            then <cat:estatus>{fn:data($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:modalidadFinan/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:modalidadFinan/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso/des:modalidadFinan/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </des:modalidadFinan>
                else ()
            }
        </ns1:Desembolso>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>{
            if(count($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/ns1:ContratoDesembolso)> 0)then ('Consulta Exitosa')
            else ('No hay resultados')
            }</res:message>
        </ns1:Resultado>
    </ns1:ConsultarInformacionReglasByIdResponse>
};

local:funcConsultarinformacionreglaresponse($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse, $outConsultarLineaCreditoById.response, $outConsultarOperacionById.response)
