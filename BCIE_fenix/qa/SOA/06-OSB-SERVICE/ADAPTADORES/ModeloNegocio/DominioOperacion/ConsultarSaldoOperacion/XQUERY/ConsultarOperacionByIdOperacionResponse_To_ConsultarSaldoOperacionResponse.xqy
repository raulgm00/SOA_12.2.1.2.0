xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

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

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarOperacionResponse as element() (:: schema-element(ns1:ConsultarOperacionResponse) ::) external;
declare variable $ConsultarSaldoOperacionResponse as element() (:: schema-element(ns1:ConsultarSaldoOperacionResponse) ::) external;
declare variable $Resultado as xs:string external;

declare function local:func($ConsultarOperacionResponse as element() (:: schema-element(ns1:ConsultarOperacionResponse) ::), 
                            $ConsultarSaldoOperacionResponse as element() (:: schema-element(ns1:ConsultarSaldoOperacionResponse) ::)
                            ,$Resultado) 
                            as element() (:: schema-element(ns1:ConsultarSaldoOperacionResponse) ::) {
    <ns1:ConsultarSaldoOperacionResponse>
    {
    if($Resultado = 'OK') then
        <ns1:Operacion>
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:idOperacion)
                then <ope:idOperacion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:idOperacion)}</ope:idOperacion>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:responsable)
                then <ope:responsable>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:responsable)}</ope:responsable>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:oficina)
                then <ope:oficina>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:oficina)}</ope:oficina>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:nombre)
                then <ope:nombre>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:nombre)}</ope:nombre>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente)
                then 
                    <ope:cliente>
                        <cli:idCliente>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:idCliente)}</cli:idCliente>
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:idFacturador)
                            then <cli:idFacturador>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:idFacturador)}</cli:idFacturador>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:razonSocial)
                            then <cli:razonSocial>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:razonSocial)}</cli:razonSocial>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:abreviatura)
                            then <cli:abreviatura>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:abreviatura)}</cli:abreviatura>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoPersona)
                            then 
                                <cli:tipoPersona>
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:Id)
                                        then <cat:Id>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:estatus)
                                        then <cat:estatus>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </cli:tipoPersona>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoCliente)
                            then 
                                <cli:tipoCliente>
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoCliente/cat:Id)
                                        then <cat:Id>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoCliente/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoCliente/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoCliente/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoCliente/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoCliente/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoCliente/cat:estatus)
                                        then <cat:estatus>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoCliente/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoCliente/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoCliente/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </cli:tipoCliente>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:sector)
                            then 
                                <cli:sector>
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:sector/cat:Id)
                                        then <cat:Id>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:sector/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:sector/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:sector/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:sector/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:sector/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:sector/cat:estatus)
                                        then <cat:estatus>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:sector/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:sector/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:sector/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </cli:sector>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoInstitucion)
                            then 
                                <cli:tipoInstitucion>
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoInstitucion/cat:Id)
                                        then <cat:Id>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoInstitucion/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoInstitucion/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoInstitucion/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoInstitucion/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoInstitucion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoInstitucion/cat:estatus)
                                        then <cat:estatus>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoInstitucion/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoInstitucion/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoInstitucion/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </cli:tipoInstitucion>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:pais)
                            then 
                                <cli:pais>
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:pais/cat:Id)
                                        then <cat:Id>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:pais/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:pais/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:pais/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:pais/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:pais/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:pais/cat:estatus)
                                        then <cat:estatus>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:pais/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:pais/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:pais/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </cli:pais>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:grupoEconomico)
                            then 
                                <cli:grupoEconomico>
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:Id)
                                        then <cat:Id>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:estatus)
                                        then <cat:estatus>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </cli:grupoEconomico>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoIdentificacion)
                            then <cli:tipoIdentificacion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:tipoIdentificacion)}</cli:tipoIdentificacion>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:numeroIdentificacion)
                            then <cli:numeroIdentificacion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:numeroIdentificacion)}</cli:numeroIdentificacion>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:direccion)
                            then <cli:direccion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:direccion)}</cli:direccion>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:telefono)
                            then <cli:telefono>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:telefono)}</cli:telefono>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:fax)
                            then <cli:fax>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:fax)}</cli:fax>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:email)
                            then <cli:email>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:email)}</cli:email>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:ciudad)
                            then <cli:ciudad>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:ciudad)}</cli:ciudad>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:oficina)
                            then 
                                <cli:oficina>
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:oficina/cat:Id)
                                        then <cat:Id>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:oficina/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:oficina/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:oficina/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:oficina/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:oficina/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:oficina/cat:estatus)
                                        then <cat:estatus>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:oficina/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:oficina/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:oficina/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </cli:oficina>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:grupoEmpresarial)
                            then <cli:grupoEmpresarial>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:grupoEmpresarial)}</cli:grupoEmpresarial>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:fechaRegistro)
                            then <cli:fechaRegistro>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:fechaRegistro)}</cli:fechaRegistro>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:fechaAprobacion)
                            then <cli:fechaAprobacion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:fechaAprobacion)}</cli:fechaAprobacion>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:ejecutivo)
                            then <cli:ejecutivo>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:ejecutivo)}</cli:ejecutivo>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:responsableCliente)
                            then <cli:responsableCliente>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:responsableCliente)}</cli:responsableCliente>
                            else ()
                        }
                        {
                            for $Contactos in $ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:Contactos
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
                        <cli:InformacionCorrecta>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:InformacionCorrecta)}</cli:InformacionCorrecta>
                        <cli:ActualizacionPermitida>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:ActualizacionPermitida)}</cli:ActualizacionPermitida>
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:comentarioAprobacion)
                            then <cli:comentarioAprobacion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:comentarioAprobacion)}</cli:comentarioAprobacion>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:revisadoAprobacion)
                            then <cli:revisadoAprobacion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:revisadoAprobacion)}</cli:revisadoAprobacion>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:status)
                            then <cli:status>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:status)}</cli:status>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:fechaBaja)
                            then <cli:fechaBaja>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:fechaBaja)}</cli:fechaBaja>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:diaPago)
                            then <cli:diaPago>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:diaPago)}</cli:diaPago>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:SCR)
                            then 
                                <cli:SCR>
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:SCR/cat:Id)
                                        then <cat:Id>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:SCR/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:SCR/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:SCR/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:SCR/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:SCR/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:SCR/cat:estatus)
                                        then <cat:estatus>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:SCR/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:SCR/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:SCR/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </cli:SCR>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:perspectiva)
                            then 
                                <cli:perspectiva>
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:perspectiva/cat:Id)
                                        then <cat:Id>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:perspectiva/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:perspectiva/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:perspectiva/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:perspectiva/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:perspectiva/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:perspectiva/cat:estatus)
                                        then <cat:estatus>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:perspectiva/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:perspectiva/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:perspectiva/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </cli:perspectiva>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:enMora)
                            then <cli:enMora>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:enMora)}</cli:enMora>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:mora)
                            then 
                                <cli:mora>
                                    <cli:dias>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:mora/cli:dias)}</cli:dias>
                                    <cli:monto>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:mora/cli:monto)}</cli:monto>
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:mora/cli:tipo)
                                        then 
                                            <cli:tipo>
                                                {
                                                    if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:mora/cli:tipo/cat:Id)
                                                    then <cat:Id>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:mora/cli:tipo/cat:Id)}</cat:Id>
                                                    else ()
                                                }
                                                {
                                                    if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:mora/cli:tipo/cat:Descripcion)
                                                    then <cat:Descripcion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:mora/cli:tipo/cat:Descripcion)}</cat:Descripcion>
                                                    else ()
                                                }
                                                {
                                                    if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:mora/cli:tipo/cat:DescripcionCorta)
                                                    then <cat:DescripcionCorta>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:mora/cli:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                    else ()
                                                }
                                                {
                                                    if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:mora/cli:tipo/cat:estatus)
                                                    then <cat:estatus>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:mora/cli:tipo/cat:estatus)}</cat:estatus>
                                                    else ()
                                                }
                                                {
                                                    if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:mora/cli:tipo/cat:codigoExterno)
                                                    then <cat:codigoExterno>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:mora/cli:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                                    else ()
                                                }
                                            </cli:tipo>
                                        else ()
                                    }
                                </cli:mora>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:deteriorado)
                            then <cli:deteriorado>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:deteriorado)}</cli:deteriorado>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:reserva)
                            then 
                                <cli:reserva>
                                    <cli:importeReserva>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:reserva/cli:importeReserva)}</cli:importeReserva>
                                    <cli:tipoReserva>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:reserva/cli:tipoReserva)}</cli:tipoReserva>
                                </cli:reserva>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:requiereEnvioAutomatico)
                            then <cli:requiereEnvioAutomatico>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:requiereEnvioAutomatico)}</cli:requiereEnvioAutomatico>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR)
                            then 
                                <cli:detalleSCR>
                                    <cli:scrFuente>
                                        <cli:idCliente>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:idCliente)}</cli:idCliente>
                                        {
                                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:idFacturador)
                                            then <cli:idFacturador>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:idFacturador)}</cli:idFacturador>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:scr)
                                            then <cli:scr>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:scr)}</cli:scr>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:estadoScr)
                                            then <cli:estadoScr>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:estadoScr)}</cli:estadoScr>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:observacion)
                                            then <cli:observacion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:observacion)}</cli:observacion>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:fecha)
                                            then <cli:fecha>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:fecha)}</cli:fecha>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:usuarioModifico)
                                            then <cli:usuarioModifico>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:usuarioModifico)}</cli:usuarioModifico>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:usuarioValido)
                                            then <cli:usuarioValido>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrFuente/cli:usuarioValido)}</cli:usuarioValido>
                                            else ()
                                        }
                                    </cli:scrFuente>
                                    <cli:scrReferencia>
                                        <cli:idCliente>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:idCliente)}</cli:idCliente>
                                        {
                                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:idFacturador)
                                            then <cli:idFacturador>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:idFacturador)}</cli:idFacturador>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:scr)
                                            then <cli:scr>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:scr)}</cli:scr>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:estadoScr)
                                            then <cli:estadoScr>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:estadoScr)}</cli:estadoScr>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:observacion)
                                            then <cli:observacion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:observacion)}</cli:observacion>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:fecha)
                                            then <cli:fecha>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:fecha)}</cli:fecha>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:usuarioModifico)
                                            then <cli:usuarioModifico>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:usuarioModifico)}</cli:usuarioModifico>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:usuarioValido)
                                            then <cli:usuarioValido>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:scrReferencia/cli:usuarioValido)}</cli:usuarioValido>
                                            else ()
                                        }
                                    </cli:scrReferencia>
                                    <cli:estatusComparacion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:detalleSCR/cli:estatusComparacion)}</cli:estatusComparacion>
                                </cli:detalleSCR>
                            else ()
                        }
                    </ope:cliente>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:producto)
                then 
                    <ope:producto>
                        <pro:idProducto>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:idProducto)}</pro:idProducto>
                        <pro:descripcion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:descripcion)}</pro:descripcion>
                        <pro:descripcionCorta>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:descripcionCorta)}</pro:descripcionCorta>
                        <pro:fechaRegistro>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:fechaRegistro)}</pro:fechaRegistro>
                        <pro:codExterno>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:codExterno)}</pro:codExterno>
                        <pro:idInstrumentoFinanciero>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:idInstrumentoFinanciero)}</pro:idInstrumentoFinanciero>
                        <pro:idTipoOperacion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:idTipoOperacion)}</pro:idTipoOperacion>
                        {if  ($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas)
                        then
                        <pro:reglas>
                            <pro:banStatus>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:banStatus)}</pro:banStatus>
                            <pro:requiereElegibilidad>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:requiereElegibilidad)}</pro:requiereElegibilidad>
                            <pro:requiereLaft>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:requiereLaft)}</pro:requiereLaft>
                            <pro:OFICrealizaAnalisisLAFT>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:OFICrealizaAnalisisLAFT)}</pro:OFICrealizaAnalisisLAFT>
                            <pro:requiereEvaluacionExAnte>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:requiereEvaluacionExAnte)}</pro:requiereEvaluacionExAnte>
                            <pro:requiereFormularProgramas>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:requiereFormularProgramas)}</pro:requiereFormularProgramas>
                            <pro:elaboraAnalizarAdquisiciones>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:elaboraAnalizarAdquisiciones)}</pro:elaboraAnalizarAdquisiciones>
                            <pro:elaboraHojaTerminosPCT>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:elaboraHojaTerminosPCT)}</pro:elaboraHojaTerminosPCT>
                            <pro:elaboraHojaTerminosSEPRI>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:elaboraHojaTerminosSEPRI)}</pro:elaboraHojaTerminosSEPRI>
                            <pro:analizarModelo>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:analizarModelo)}</pro:analizarModelo>
                            <pro:realizarPreanalisis>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:realizarPreanalisis)}</pro:realizarPreanalisis>
                            <pro:participaSupervision>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:participaSupervision)}</pro:participaSupervision>
                            <pro:participaSeguimiento>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:participaSeguimiento)}</pro:participaSeguimiento>
                            <pro:participaFINAM>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:participaFINAM)}</pro:participaFINAM>
                            <pro:SRC>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:SRC)}</pro:SRC>
                            <pro:OpinionJuridica>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:OpinionJuridica)}</pro:OpinionJuridica>
                            <pro:firmaContrato>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:firmaContrato)}</pro:firmaContrato>
                            <pro:condicionesPreviasFormalizar>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:condicionesPreviasFormalizar)}</pro:condicionesPreviasFormalizar>
                            <pro:condicionesPreviasDesembolso>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:condicionesPreviasDesembolso)}</pro:condicionesPreviasDesembolso>
                            <pro:programacionDesembolsos>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:programacionDesembolsos)}</pro:programacionDesembolsos>
                            <pro:reglaLAFT>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:reglaLAFT)}</pro:reglaLAFT>
                            <pro:adquisiciones2>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:adquisiciones2)}</pro:adquisiciones2>
                            <pro:supervicionTecnica>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:supervicionTecnica)}</pro:supervicionTecnica>
                            <pro:seguimientoCrediticio>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:seguimientoCrediticio)}</pro:seguimientoCrediticio>
                            <pro:enmiendas>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:enmiendas)}</pro:enmiendas>
                            <pro:recuperacion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:recuperacion)}</pro:recuperacion>
                            <pro:cierreOperativo>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:cierreOperativo)}</pro:cierreOperativo>
                            <pro:rendicionCuentas>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:rendicionCuentas)}</pro:rendicionCuentas>
                            <pro:evaluacionMedioTermino>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:evaluacionMedioTermino)}</pro:evaluacionMedioTermino>
                            <pro:evaluacionExPost>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:evaluacionExPost)}</pro:evaluacionExPost>
                            <pro:requiereAdquisiciones>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:requiereAdquisiciones)}</pro:requiereAdquisiciones>
                            <pro:requiereRAROC>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:requiereRAROC)}</pro:requiereRAROC>
                            <pro:requiereIBICIE>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:requiereIBICIE)}</pro:requiereIBICIE>
                            <pro:requiereSIEMAS>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:requiereSIEMAS)}</pro:requiereSIEMAS>
                            <pro:requiereGERIESElegibilidad>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:requiereGERIESElegibilidad)}</pro:requiereGERIESElegibilidad>
                            <pro:requiereOpinionTecnica>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:requiereOpinionTecnica)}</pro:requiereOpinionTecnica>
                            <pro:requiereAsjurAnalisis>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:requiereAsjurAnalisis)}</pro:requiereAsjurAnalisis>
                            <pro:requiereAsjurElegibilidad>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:requiereAsjurElegibilidad)}</pro:requiereAsjurElegibilidad>
                            <pro:requierePreparacion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:requierePreparacion)}</pro:requierePreparacion>
                            <pro:tieneRiesgoCredito>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:tieneRiesgoCredito)}</pro:tieneRiesgoCredito>
                            <pro:responsableAnalisis>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:responsableAnalisis)}</pro:responsableAnalisis>
                            <pro:nombreResponsableAnalisis>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:nombreResponsableAnalisis)}</pro:nombreResponsableAnalisis>
                            <pro:requiereFirmacontrato>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:requiereFirmacontrato)}</pro:requiereFirmacontrato>
                            <pro:requiereRegistroLinea>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:requiereRegistroLinea)}</pro:requiereRegistroLinea>
                            <pro:requiereCore>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:reglas/pro:requiereCore)}</pro:requiereCore>
                        </pro:reglas>
                        else ()
                        }
                        <pro:instrumentoFinanciero>
                            <pro:descripcion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:instrumentoFinanciero/pro:descripcion)}</pro:descripcion>
                            <pro:descripcionCorta>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:instrumentoFinanciero/pro:descripcionCorta)}</pro:descripcionCorta>
                            <pro:estado>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:instrumentoFinanciero/pro:estado)}</pro:estado>
                            <pro:fechaRegistro>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:instrumentoFinanciero/pro:fechaRegistro)}</pro:fechaRegistro>
                            <pro:codigoExterno>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:instrumentoFinanciero/pro:codigoExterno)}</pro:codigoExterno>
                        </pro:instrumentoFinanciero>
                        <pro:estatus>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:estatus)}</pro:estatus>
                        <pro:Requiere_Cierre>
                            <pro:Requiere_Cierre>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:Requiere_Cierre/pro:Requiere_Cierre)}</pro:Requiere_Cierre>
                            {
                                if ($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:Requiere_Cierre/pro:Tipo_Cierre)
                                then <pro:Tipo_Cierre>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:Requiere_Cierre/pro:Tipo_Cierre)}</pro:Tipo_Cierre>
                                else ()
                            }
                        </pro:Requiere_Cierre>
                    </ope:producto>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:descripcion)
                then <ope:descripcion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:descripcion)}</ope:descripcion>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:actividadEconomica)
                then 
                    <ope:actividadEconomica>
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:actividadEconomica/cat:Id)
                            then <cat:Id>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:actividadEconomica/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:actividadEconomica/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:actividadEconomica/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:actividadEconomica/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:actividadEconomica/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:actividadEconomica/cat:estatus)
                            then <cat:estatus>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:actividadEconomica/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:actividadEconomica/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:actividadEconomica/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ope:actividadEconomica>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:actividadEconomicaAsociada)
                then 
                    <ope:actividadEconomicaAsociada>
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:actividadEconomicaAsociada/cat:Id)
                            then <cat:Id>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:actividadEconomicaAsociada/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:actividadEconomicaAsociada/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:actividadEconomicaAsociada/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:actividadEconomicaAsociada/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:actividadEconomicaAsociada/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:actividadEconomicaAsociada/cat:estatus)
                            then <cat:estatus>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:actividadEconomicaAsociada/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:actividadEconomicaAsociada/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:actividadEconomicaAsociada/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ope:actividadEconomicaAsociada>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:objetivosEstrategicos)
                then <ope:objetivosEstrategicos>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:objetivosEstrategicos)}</ope:objetivosEstrategicos>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:areaFocalizacion)
                then 
                    <ope:areaFocalizacion>
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:areaFocalizacion/cat:Id)
                            then <cat:Id>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:areaFocalizacion/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:areaFocalizacion/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:areaFocalizacion/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:areaFocalizacion/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:areaFocalizacion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:areaFocalizacion/cat:estatus)
                            then <cat:estatus>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:areaFocalizacion/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:areaFocalizacion/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:areaFocalizacion/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ope:areaFocalizacion>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:ejeEstrategico)
                then 
                    <ope:ejeEstrategico>
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:ejeEstrategico/cat:Id)
                            then <cat:Id>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:ejeEstrategico/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:ejeEstrategico/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:ejeEstrategico/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:ejeEstrategico/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:ejeEstrategico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:ejeEstrategico/cat:estatus)
                            then <cat:estatus>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:ejeEstrategico/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:ejeEstrategico/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:ejeEstrategico/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ope:ejeEstrategico>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:iniciativaEstrategica)
                then 
                    <ope:iniciativaEstrategica>
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:iniciativaEstrategica/cat:Id)
                            then <cat:Id>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:iniciativaEstrategica/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:iniciativaEstrategica/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:iniciativaEstrategica/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:iniciativaEstrategica/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:iniciativaEstrategica/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:iniciativaEstrategica/cat:estatus)
                            then <cat:estatus>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:iniciativaEstrategica/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:iniciativaEstrategica/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:iniciativaEstrategica/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ope:iniciativaEstrategica>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:cantidadPaises)
                then 
                    <ope:cantidadPaises>
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cantidadPaises/cat:Id)
                            then <cat:Id>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cantidadPaises/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cantidadPaises/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cantidadPaises/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cantidadPaises/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cantidadPaises/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cantidadPaises/cat:estatus)
                            then <cat:estatus>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cantidadPaises/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:cantidadPaises/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cantidadPaises/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ope:cantidadPaises>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:sectorMercado)
                then 
                    <ope:sectorMercado>
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:sectorMercado/cat:Id)
                            then <cat:Id>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:sectorMercado/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:sectorMercado/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:sectorMercado/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:sectorMercado/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:sectorMercado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:sectorMercado/cat:estatus)
                            then <cat:estatus>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:sectorMercado/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:sectorMercado/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:sectorMercado/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ope:sectorMercado>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:fechaInicio)
                then <ope:fechaInicio>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:fechaInicio)}</ope:fechaInicio>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:programadoPOA)
                then <ope:programadoPOA>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:programadoPOA)}</ope:programadoPOA>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:ejercicioPOA)
                then 
                    <ope:ejercicioPOA>
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:ejercicioPOA/cat:Id)
                            then <cat:Id>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:ejercicioPOA/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:ejercicioPOA/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:ejercicioPOA/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:ejercicioPOA/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:ejercicioPOA/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:ejercicioPOA/cat:estatus)
                            then <cat:estatus>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:ejercicioPOA/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:ejercicioPOA/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:ejercicioPOA/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ope:ejercicioPOA>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:tipoGarantia)
                then 
                    <ope:tipoGarantia>
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:tipoGarantia/cat:Id)
                            then <cat:Id>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:tipoGarantia/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:tipoGarantia/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:tipoGarantia/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:tipoGarantia/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:tipoGarantia/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:tipoGarantia/cat:estatus)
                            then <cat:estatus>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:tipoGarantia/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:tipoGarantia/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:tipoGarantia/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ope:tipoGarantia>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:componenteConcesionalidad)
                then <ope:componenteConcesionalidad>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:componenteConcesionalidad)}</ope:componenteConcesionalidad>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:estructurador)
                then <ope:estructurador>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:estructurador)}</ope:estructurador>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:beneficiario)
                then <ope:beneficiario>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:beneficiario)}</ope:beneficiario>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:unidadEjecutora)
                then <ope:unidadEjecutora>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:unidadEjecutora)}</ope:unidadEjecutora>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:programa)
                then <ope:programa>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:programa)}</ope:programa>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:asociadas)
                then 
                    <ope:asociadas>
                        {
                            for $Operacion in $ConsultarOperacionResponse/ns1:Operacion/ope:asociadas/ope:Operacion
                            return 
                            <ope:Operacion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:asociadas/ope:Operacion)}</ope:Operacion>
                        }
                    </ope:asociadas>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada)
                then 
                    <ope:declaracionJurada>
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:idDeclaracion)
                            then <dec:idDeclaracion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:idDeclaracion)}</dec:idDeclaracion>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:codigoExternoDeclaracion)
                            then <dec:codigoExternoDeclaracion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:codigoExternoDeclaracion)}</dec:codigoExternoDeclaracion>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:titulo)
                            then <dec:titulo>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:titulo)}</dec:titulo>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:estadoDeclaracion)
                            then 
                                <dec:estadoDeclaracion>
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:estadoDeclaracion/dec:codigoEstadoDeclaracion)
                                        then <dec:codigoEstadoDeclaracion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:estadoDeclaracion/dec:codigoEstadoDeclaracion)}</dec:codigoEstadoDeclaracion>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:estadoDeclaracion/dec:nombreEstadoDeclaracion)
                                        then <dec:nombreEstadoDeclaracion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:estadoDeclaracion/dec:nombreEstadoDeclaracion)}</dec:nombreEstadoDeclaracion>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:estadoDeclaracion/dec:EstadoNoObjecion)
                                        then <dec:EstadoNoObjecion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:estadoDeclaracion/dec:EstadoNoObjecion)}</dec:EstadoNoObjecion>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:estadoDeclaracion/dec:nombreEstadoNoObjecion)
                                        then <dec:nombreEstadoNoObjecion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:estadoDeclaracion/dec:nombreEstadoNoObjecion)}</dec:nombreEstadoNoObjecion>
                                        else ()
                                    }
                                </dec:estadoDeclaracion>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:estadoBusqueda)
                            then 
                                <dec:estadoBusqueda>
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:estadoBusqueda/dec:codigoEstadoBusqueda)
                                        then <dec:codigoEstadoBusqueda>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:estadoBusqueda/dec:codigoEstadoBusqueda)}</dec:codigoEstadoBusqueda>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:estadoBusqueda/dec:nombreEstadoBusqueda)
                                        then <dec:nombreEstadoBusqueda>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:estadoBusqueda/dec:nombreEstadoBusqueda)}</dec:nombreEstadoBusqueda>
                                        else ()
                                    }
                                </dec:estadoBusqueda>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:calificacionDeRiesgo)
                            then 
                                <dec:calificacionDeRiesgo>
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:calificacionDeRiesgo/dec:codigoCalificacionDeRiesgo)
                                        then <dec:codigoCalificacionDeRiesgo>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:calificacionDeRiesgo/dec:codigoCalificacionDeRiesgo)}</dec:codigoCalificacionDeRiesgo>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:calificacionDeRiesgo/dec:nombreCalificacionDeRiesgo)
                                        then <dec:nombreCalificacionDeRiesgo>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:calificacionDeRiesgo/dec:nombreCalificacionDeRiesgo)}</dec:nombreCalificacionDeRiesgo>
                                        else ()
                                    }
                                </dec:calificacionDeRiesgo>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:fechaRegistro)
                            then <dec:fechaRegistro>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:fechaRegistro)}</dec:fechaRegistro>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:fechaFirmaDeclaracion)
                            then <dec:fechaFirmaDeclaracion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:fechaFirmaDeclaracion)}</dec:fechaFirmaDeclaracion>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:fechaVencimiento)
                            then <dec:fechaVencimiento>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:fechaVencimiento)}</dec:fechaVencimiento>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:comentarioDeclaracion)
                            then <dec:comentarioDeclaracion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:comentarioDeclaracion)}</dec:comentarioDeclaracion>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:comentarioBusqueda)
                            then <dec:comentarioBusqueda>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:comentarioBusqueda)}</dec:comentarioBusqueda>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:elevarAOtraInstancia)
                            then <dec:elevarAOtraInstancia>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:elevarAOtraInstancia)}</dec:elevarAOtraInstancia>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:tipoOrigen)
                            then <dec:tipoOrigen>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:tipoOrigen)}</dec:tipoOrigen>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:tipoSeguimiento)
                            then <dec:tipoSeguimiento>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:tipoSeguimiento)}</dec:tipoSeguimiento>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:instanciaProceso)
                            then <dec:instanciaProceso>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:instanciaProceso)}</dec:instanciaProceso>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:esSoloLectura)
                            then <dec:esSoloLectura>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:esSoloLectura)}</dec:esSoloLectura>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:status)
                            then <dec:status>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:declaracionJurada/dec:status)}</dec:status>
                            else ()
                        }
                    </ope:declaracionJurada>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:masInformacionRiesgo)
                then <ope:masInformacionRiesgo>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:masInformacionRiesgo)}</ope:masInformacionRiesgo>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:informacionAdicionalRiesgo)
                then <ope:informacionAdicionalRiesgo>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:informacionAdicionalRiesgo)}</ope:informacionAdicionalRiesgo>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:masInformacionJuridico)
                then <ope:masInformacionJuridico>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:masInformacionJuridico)}</ope:masInformacionJuridico>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:informacionAdicionalJuridico)
                then <ope:informacionAdicionalJuridico>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:informacionAdicionalJuridico)}</ope:informacionAdicionalJuridico>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:contrapartesProhibidas)
                then <ope:contrapartesProhibidas>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:contrapartesProhibidas)}</ope:contrapartesProhibidas>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:comentarioContrapartes)
                then <ope:comentarioContrapartes>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:comentarioContrapartes)}</ope:comentarioContrapartes>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:limitesAnalisis)
                then <ope:limitesAnalisis>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:limitesAnalisis)}</ope:limitesAnalisis>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:limitesDeterminar)
                then <ope:limitesDeterminar>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:limitesDeterminar)}</ope:limitesDeterminar>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:etapa)
                then <ope:etapa>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:etapa)}</ope:etapa>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:status)
                then <ope:status>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:status)}</ope:status>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:fechaBaja)
                then <ope:fechaBaja>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:fechaBaja)}</ope:fechaBaja>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:comentarioLaft)
                then <ope:comentarioLaft>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:comentarioLaft)}</ope:comentarioLaft>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:cumpleLaft)
                then <ope:cumpleLaft>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cumpleLaft)}</ope:cumpleLaft>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:calificacionRiesgo)
                then <ope:calificacionRiesgo>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:calificacionRiesgo)}</ope:calificacionRiesgo>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:perspectiva)
                then 
                    <ope:perspectiva>
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:perspectiva/cat:Id)
                            then <cat:Id>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:perspectiva/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:perspectiva/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:perspectiva/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:perspectiva/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:perspectiva/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:perspectiva/cat:estatus)
                            then <cat:estatus>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:perspectiva/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:perspectiva/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:perspectiva/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ope:perspectiva>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:estado)
                then 
                    <ope:estado>
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:estado/cat:Id)
                            then <cat:Id>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:estado/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:estado/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:estado/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:estado/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:estado/cat:estatus)
                            then <cat:estatus>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:estado/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:estado/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:estado/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ope:estado>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:lineaCredito)
                then <ope:lineaCredito>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:lineaCredito)}</ope:lineaCredito>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:SRC)
                then 
                    <ope:SRC>
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:SRC/cat:Id)
                            then <cat:Id>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:SRC/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:SRC/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:SRC/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:SRC/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:SRC/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:SRC/cat:estatus)
                            then <cat:estatus>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:SRC/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns1:Operacion/ope:SRC/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:SRC/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ope:SRC>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:RAROC)
                then <ope:RAROC>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:RAROC)}</ope:RAROC>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:TIR)
                then <ope:TIR>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:TIR)}</ope:TIR>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:TIREstatus)
                then <ope:TIREstatus>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:TIREstatus)}</ope:TIREstatus>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:requiereRecursosExternos)
                then <ope:requiereRecursosExternos>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:requiereRecursosExternos)}</ope:requiereRecursosExternos>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:enProcesoLaft)
                then <ope:enProcesoLaft>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:enProcesoLaft)}</ope:enProcesoLaft>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:aplicaLaft)
                then <ope:aplicaLaft>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:aplicaLaft)}</ope:aplicaLaft>
                else ()
            }
            <ope:montoOperacion>
                {
                    for $montoOperacion in $ConsultarOperacionResponse/ns1:Operacion/ope:montoOperacion/ope:montoOperacion
                    return 
                    <ope:montoOperacion>
                        {
                            if ($montoOperacion/ope:id)
                            then <ope:id>{fn:data($montoOperacion/ope:id)}</ope:id>
                            else ()
                        }
                        {
                            if ($montoOperacion/ope:idOperacion)
                            then <ope:idOperacion>{fn:data($montoOperacion/ope:idOperacion)}</ope:idOperacion>
                            else ()
                        }
                        {
                            if ($montoOperacion/ope:IdTcaTipoMonto)
                            then <ope:IdTcaTipoMonto>{fn:data($montoOperacion/ope:IdTcaTipoMonto)}</ope:IdTcaTipoMonto>
                            else ()
                        }
                        {
                            if ($montoOperacion/ope:monto)
                            then <ope:monto>{fn:data($montoOperacion/ope:monto)}</ope:monto>
                            else ()
                        }
                        {
                            if ($montoOperacion/ope:Descripcion)
                            then <ope:Descripcion>{fn:data($montoOperacion/ope:Descripcion)}</ope:Descripcion>
                            else ()
                        }
                        {
                            if ($montoOperacion/ope:fechaRegistro)
                            then <ope:fechaRegistro>{fn:data($montoOperacion/ope:fechaRegistro)}</ope:fechaRegistro>
                            else ()
                        }
                        {
                            if ($montoOperacion/ope:estado)
                            then <ope:estado>{fn:data($montoOperacion/ope:estado)}</ope:estado>
                            else ()
                        }
                    </ope:montoOperacion>
                }
            </ope:montoOperacion>
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:justificacionCancela)
                then <ope:justificacionCancela>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:justificacionCancela)}</ope:justificacionCancela>
                else ()
            }
            {
                if ($ConsultarOperacionResponse/ns1:Operacion/ope:observacionCancela)
                then <ope:observacionCancela>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:observacionCancela)}</ope:observacionCancela>
                else ()
            }
            {for $contrato in $ConsultarSaldoOperacionResponse/ns1:Operacion/ope:contrato
            where count($contrato/con:LineaCredito) > 0
            return
            <ope:contrato>
                {$contrato/*}
            </ope:contrato>
            }
        </ns1:Operacion>
    else()
    }
    {
      if($Resultado = 'OK') then
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Exitosa</res:message>
        </ns1:Resultado>
      else
         <ns1:Resultado>
            <res:result>ERROR</res:result>
            <res:message>{$Resultado}</res:message>
        </ns1:Resultado>
    }
    </ns1:ConsultarSaldoOperacionResponse>
};

local:func($ConsultarOperacionResponse, $ConsultarSaldoOperacionResponse, $Resultado)
