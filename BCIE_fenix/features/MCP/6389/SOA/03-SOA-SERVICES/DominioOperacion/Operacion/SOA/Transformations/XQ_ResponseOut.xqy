xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ope="http://www.bcie.org/OperacionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace ope1 = "http://www.bcie.org/OperacionBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $outputVariable.response as element() (:: schema-element(ope:ConsultarOperacionResponse) ::) external;

declare function local:funcTransformation_2($outputVariable.response as element() (:: schema-element(ope:ConsultarOperacionResponse) ::)) as element() (:: schema-element(ope:ConsultarOperacionResponse) ::) {
    <ope:ConsultarOperacionResponse>
        {
            for $Operacion in $outputVariable.response/ope:Operacion
            return 
            <ope:Operacion>
                {
                    if ($Operacion/ope1:idOperacion)
                    then <ope1:idOperacion>{fn:data($Operacion/ope1:idOperacion)}</ope1:idOperacion>
                    else ()
                }
                {
                    if ($Operacion/ope1:responsable)
                    then <ope1:responsable>{fn:data($Operacion/ope1:responsable)}</ope1:responsable>
                    else ()
                }
                {
                    if ($Operacion/ope1:nombre)
                    then <ope1:nombre>{fn:data($Operacion/ope1:nombre)}</ope1:nombre>
                    else ()
                }
                {
                    if ($Operacion/ope1:cliente)
                    then 
                        <ope1:cliente>
                            <cli:idCliente>{fn:data($Operacion/ope1:cliente/cli:idCliente)}</cli:idCliente>
                            {
                                if ($Operacion/ope1:cliente/cli:idFacturador)
                                then <cli:idFacturador>{fn:data($Operacion/ope1:cliente/cli:idFacturador)}</cli:idFacturador>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:razonSocial)
                                then <cli:razonSocial>{fn:data($Operacion/ope1:cliente/cli:razonSocial)}</cli:razonSocial>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:abreviatura)
                                then <cli:abreviatura>{fn:data($Operacion/ope1:cliente/cli:abreviatura)}</cli:abreviatura>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:tipoPersona)
                                then 
                                    <cli:tipoPersona>
                                        {
                                            if ($Operacion/ope1:cliente/cli:tipoPersona/cat:Id)
                                            then <cat:Id>{fn:data($Operacion/ope1:cliente/cli:tipoPersona/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:tipoPersona/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Operacion/ope1:cliente/cli:tipoPersona/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:tipoPersona/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Operacion/ope1:cliente/cli:tipoPersona/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:tipoPersona/cat:estatus)
                                            then <cat:estatus>{fn:data($Operacion/ope1:cliente/cli:tipoPersona/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:tipoPersona/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Operacion/ope1:cliente/cli:tipoPersona/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </cli:tipoPersona>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:tipoCliente)
                                then 
                                    <cli:tipoCliente>
                                        {
                                            if ($Operacion/ope1:cliente/cli:tipoCliente/cat:Id)
                                            then <cat:Id>{fn:data($Operacion/ope1:cliente/cli:tipoCliente/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:tipoCliente/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Operacion/ope1:cliente/cli:tipoCliente/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:tipoCliente/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Operacion/ope1:cliente/cli:tipoCliente/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:tipoCliente/cat:estatus)
                                            then <cat:estatus>{fn:data($Operacion/ope1:cliente/cli:tipoCliente/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:tipoCliente/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Operacion/ope1:cliente/cli:tipoCliente/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </cli:tipoCliente>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:sector)
                                then 
                                    <cli:sector>
                                        {
                                            if ($Operacion/ope1:cliente/cli:sector/cat:Id)
                                            then <cat:Id>{fn:data($Operacion/ope1:cliente/cli:sector/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:sector/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Operacion/ope1:cliente/cli:sector/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:sector/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Operacion/ope1:cliente/cli:sector/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:sector/cat:estatus)
                                            then <cat:estatus>{fn:data($Operacion/ope1:cliente/cli:sector/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:sector/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Operacion/ope1:cliente/cli:sector/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </cli:sector>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:tipoInstitucion)
                                then 
                                    <cli:tipoInstitucion>
                                        {
                                            if ($Operacion/ope1:cliente/cli:tipoInstitucion/cat:Id)
                                            then <cat:Id>{fn:data($Operacion/ope1:cliente/cli:tipoInstitucion/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:tipoInstitucion/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Operacion/ope1:cliente/cli:tipoInstitucion/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:tipoInstitucion/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Operacion/ope1:cliente/cli:tipoInstitucion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:tipoInstitucion/cat:estatus)
                                            then <cat:estatus>{fn:data($Operacion/ope1:cliente/cli:tipoInstitucion/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:tipoInstitucion/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Operacion/ope1:cliente/cli:tipoInstitucion/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </cli:tipoInstitucion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:pais)
                                then 
                                    <cli:pais>
                                        {
                                            if ($Operacion/ope1:cliente/cli:pais/cat:Id)
                                            then <cat:Id>{fn:data($Operacion/ope1:cliente/cli:pais/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:pais/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Operacion/ope1:cliente/cli:pais/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:pais/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Operacion/ope1:cliente/cli:pais/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:pais/cat:estatus)
                                            then <cat:estatus>{fn:data($Operacion/ope1:cliente/cli:pais/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:pais/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Operacion/ope1:cliente/cli:pais/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </cli:pais>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:grupoEconomico)
                                then 
                                    <cli:grupoEconomico>
                                        {
                                            if ($Operacion/ope1:cliente/cli:grupoEconomico/cat:Id)
                                            then <cat:Id>{fn:data($Operacion/ope1:cliente/cli:grupoEconomico/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:grupoEconomico/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Operacion/ope1:cliente/cli:grupoEconomico/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:grupoEconomico/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Operacion/ope1:cliente/cli:grupoEconomico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:grupoEconomico/cat:estatus)
                                            then <cat:estatus>{fn:data($Operacion/ope1:cliente/cli:grupoEconomico/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:grupoEconomico/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Operacion/ope1:cliente/cli:grupoEconomico/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </cli:grupoEconomico>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:tipoIdentificacion)
                                then <cli:tipoIdentificacion>{fn:data($Operacion/ope1:cliente/cli:tipoIdentificacion)}</cli:tipoIdentificacion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:numeroIdentificacion)
                                then <cli:numeroIdentificacion>{fn:data($Operacion/ope1:cliente/cli:numeroIdentificacion)}</cli:numeroIdentificacion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:direccion)
                                then <cli:direccion>{fn:data($Operacion/ope1:cliente/cli:direccion)}</cli:direccion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:telefono)
                                then <cli:telefono>{fn:data($Operacion/ope1:cliente/cli:telefono)}</cli:telefono>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:fax)
                                then <cli:fax>{fn:data($Operacion/ope1:cliente/cli:fax)}</cli:fax>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:email)
                                then <cli:email>{fn:data($Operacion/ope1:cliente/cli:email)}</cli:email>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:ciudad)
                                then <cli:ciudad>{fn:data($Operacion/ope1:cliente/cli:ciudad)}</cli:ciudad>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:oficina)
                                then 
                                    <cli:oficina>
                                        {
                                            if ($Operacion/ope1:cliente/cli:oficina/cat:Id)
                                            then <cat:Id>{fn:data($Operacion/ope1:cliente/cli:oficina/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:oficina/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Operacion/ope1:cliente/cli:oficina/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:oficina/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Operacion/ope1:cliente/cli:oficina/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:oficina/cat:estatus)
                                            then <cat:estatus>{fn:data($Operacion/ope1:cliente/cli:oficina/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:oficina/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Operacion/ope1:cliente/cli:oficina/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </cli:oficina>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:grupoEmpresarial)
                                then <cli:grupoEmpresarial>{fn:data($Operacion/ope1:cliente/cli:grupoEmpresarial)}</cli:grupoEmpresarial>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:fechaRegistro)
                                then <cli:fechaRegistro>{fn:data($Operacion/ope1:cliente/cli:fechaRegistro)}</cli:fechaRegistro>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:fechaAprobacion)
                                then <cli:fechaAprobacion>{fn:data($Operacion/ope1:cliente/cli:fechaAprobacion)}</cli:fechaAprobacion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:ejecutivo)
                                then <cli:ejecutivo>{fn:data($Operacion/ope1:cliente/cli:ejecutivo)}</cli:ejecutivo>
                                else ()
                            }
                            {
                                for $Contactos in $Operacion/ope1:cliente/cli:Contactos
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
                            <cli:InformacionCorrecta>{fn:data($Operacion/ope1:cliente/cli:InformacionCorrecta)}</cli:InformacionCorrecta>
                            <cli:ActualizacionPermitida>{fn:data($Operacion/ope1:cliente/cli:ActualizacionPermitida)}</cli:ActualizacionPermitida>
                            {
                                if ($Operacion/ope1:cliente/cli:comentarioAprobacion)
                                then <cli:comentarioAprobacion>{fn:data($Operacion/ope1:cliente/cli:comentarioAprobacion)}</cli:comentarioAprobacion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:revisadoAprobacion)
                                then <cli:revisadoAprobacion>{fn:data($Operacion/ope1:cliente/cli:revisadoAprobacion)}</cli:revisadoAprobacion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:status)
                                then <cli:status>{fn:data($Operacion/ope1:cliente/cli:status)}</cli:status>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:fechaBaja)
                                then <cli:fechaBaja>{fn:data($Operacion/ope1:cliente/cli:fechaBaja)}</cli:fechaBaja>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:diaPago)
                                then <cli:diaPago>{fn:data($Operacion/ope1:cliente/cli:diaPago)}</cli:diaPago>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:SCR)
                                then 
                                    <cli:SCR>
                                        {
                                            if ($Operacion/ope1:cliente/cli:SCR/cat:Id)
                                            then <cat:Id>{fn:data($Operacion/ope1:cliente/cli:SCR/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:SCR/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Operacion/ope1:cliente/cli:SCR/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:SCR/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Operacion/ope1:cliente/cli:SCR/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:SCR/cat:estatus)
                                            then <cat:estatus>{fn:data($Operacion/ope1:cliente/cli:SCR/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:SCR/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Operacion/ope1:cliente/cli:SCR/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </cli:SCR>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:perspectiva)
                                then 
                                    <cli:perspectiva>
                                        {
                                            if ($Operacion/ope1:cliente/cli:perspectiva/cat:Id)
                                            then <cat:Id>{fn:data($Operacion/ope1:cliente/cli:perspectiva/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:perspectiva/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Operacion/ope1:cliente/cli:perspectiva/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:perspectiva/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Operacion/ope1:cliente/cli:perspectiva/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:perspectiva/cat:estatus)
                                            then <cat:estatus>{fn:data($Operacion/ope1:cliente/cli:perspectiva/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:cliente/cli:perspectiva/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Operacion/ope1:cliente/cli:perspectiva/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </cli:perspectiva>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:enMora)
                                then <cli:enMora>{fn:data($Operacion/ope1:cliente/cli:enMora)}</cli:enMora>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:mora)
                                then 
                                    <cli:mora>
                                        <cli:dias>{fn:data($Operacion/ope1:cliente/cli:mora/cli:dias)}</cli:dias>
                                        <cli:monto>{fn:data($Operacion/ope1:cliente/cli:mora/cli:monto)}</cli:monto>
                                        {
                                            if ($Operacion/ope1:cliente/cli:mora/cli:tipo)
                                            then 
                                                <cli:tipo>
                                                    {
                                                        if ($Operacion/ope1:cliente/cli:mora/cli:tipo/cat:Id)
                                                        then <cat:Id>{fn:data($Operacion/ope1:cliente/cli:mora/cli:tipo/cat:Id)}</cat:Id>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Operacion/ope1:cliente/cli:mora/cli:tipo/cat:Descripcion)
                                                        then <cat:Descripcion>{fn:data($Operacion/ope1:cliente/cli:mora/cli:tipo/cat:Descripcion)}</cat:Descripcion>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Operacion/ope1:cliente/cli:mora/cli:tipo/cat:DescripcionCorta)
                                                        then <cat:DescripcionCorta>{fn:data($Operacion/ope1:cliente/cli:mora/cli:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Operacion/ope1:cliente/cli:mora/cli:tipo/cat:estatus)
                                                        then <cat:estatus>{fn:data($Operacion/ope1:cliente/cli:mora/cli:tipo/cat:estatus)}</cat:estatus>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Operacion/ope1:cliente/cli:mora/cli:tipo/cat:codigoExterno)
                                                        then <cat:codigoExterno>{fn:data($Operacion/ope1:cliente/cli:mora/cli:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                                        else ()
                                                    }
                                                </cli:tipo>
                                            else ()
                                        }
                                    </cli:mora>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:deteriorado)
                                then <cli:deteriorado>{fn:data($Operacion/ope1:cliente/cli:deteriorado)}</cli:deteriorado>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:reserva)
                                then 
                                    <cli:reserva>
                                        <cli:importeReserva>{fn:data($Operacion/ope1:cliente/cli:reserva/cli:importeReserva)}</cli:importeReserva>
                                        <cli:tipoReserva>{fn:data($Operacion/ope1:cliente/cli:reserva/cli:tipoReserva)}</cli:tipoReserva>
                                    </cli:reserva>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:responsableCliente)
                                then <cli:responsableCliente>{fn:data($Operacion/ope1:cliente/cli:responsableCliente)}</cli:responsableCliente>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cliente/cli:requiereEnvioAutomatico)
                                then <cli:requiereEnvioAutomatico>{fn:data($Operacion/ope1:cliente/cli:requiereEnvioAutomatico)}</cli:requiereEnvioAutomatico>
                                else ()
                            }
                        </ope1:cliente>
                    else ()
                }
                {
                    if ($Operacion/ope1:producto)
                    then 
                        <ope1:producto>
                            <pro:idProducto>{fn:data($Operacion/ope1:producto/pro:idProducto)}</pro:idProducto>
                            <pro:descripcion>{fn:data($Operacion/ope1:producto/pro:descripcion)}</pro:descripcion>
                        </ope1:producto>
                    else ()
                }
                {
                    if ($Operacion/ope1:descripcion)
                    then <ope1:descripcion>{fn:data($Operacion/ope1:descripcion)}</ope1:descripcion>
                    else ()
                }
                {
                    if ($Operacion/ope1:actividadEconomica)
                    then 
                        <ope1:actividadEconomica>
                            {
                                if ($Operacion/ope1:actividadEconomica/cat:Id)
                                then <cat:Id>{fn:data($Operacion/ope1:actividadEconomica/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:actividadEconomica/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Operacion/ope1:actividadEconomica/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:actividadEconomica/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Operacion/ope1:actividadEconomica/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:actividadEconomica/cat:estatus)
                                then <cat:estatus>{fn:data($Operacion/ope1:actividadEconomica/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:actividadEconomica/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Operacion/ope1:actividadEconomica/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </ope1:actividadEconomica>
                    else ()
                }
                {
                    if ($Operacion/ope1:actividadEconomicaAsociada)
                    then 
                        <ope1:actividadEconomicaAsociada>
                            {
                                if ($Operacion/ope1:actividadEconomicaAsociada/cat:Id)
                                then <cat:Id>{fn:data($Operacion/ope1:actividadEconomicaAsociada/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:actividadEconomicaAsociada/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Operacion/ope1:actividadEconomicaAsociada/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:actividadEconomicaAsociada/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Operacion/ope1:actividadEconomicaAsociada/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:actividadEconomicaAsociada/cat:estatus)
                                then <cat:estatus>{fn:data($Operacion/ope1:actividadEconomicaAsociada/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:actividadEconomicaAsociada/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Operacion/ope1:actividadEconomicaAsociada/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </ope1:actividadEconomicaAsociada>
                    else ()
                }
                {
                    if ($Operacion/ope1:areaFocalizacion)
                    then 
                        <ope1:areaFocalizacion>
                            {
                                if ($Operacion/ope1:areaFocalizacion/cat:Id)
                                then <cat:Id>{fn:data($Operacion/ope1:areaFocalizacion/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:areaFocalizacion/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Operacion/ope1:areaFocalizacion/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:areaFocalizacion/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Operacion/ope1:areaFocalizacion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:areaFocalizacion/cat:estatus)
                                then <cat:estatus>{fn:data($Operacion/ope1:areaFocalizacion/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:areaFocalizacion/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Operacion/ope1:areaFocalizacion/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </ope1:areaFocalizacion>
                    else ()
                }
                {
                    if ($Operacion/ope1:ejeEstrategico)
                    then 
                        <ope1:ejeEstrategico>
                            {
                                if ($Operacion/ope1:ejeEstrategico/cat:Id)
                                then <cat:Id>{fn:data($Operacion/ope1:ejeEstrategico/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:ejeEstrategico/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Operacion/ope1:ejeEstrategico/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:ejeEstrategico/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Operacion/ope1:ejeEstrategico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:ejeEstrategico/cat:estatus)
                                then <cat:estatus>{fn:data($Operacion/ope1:ejeEstrategico/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:ejeEstrategico/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Operacion/ope1:ejeEstrategico/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </ope1:ejeEstrategico>
                    else ()
                }
                {
                    if ($Operacion/ope1:iniciativaEstrategica)
                    then 
                        <ope1:iniciativaEstrategica>
                            {
                                if ($Operacion/ope1:iniciativaEstrategica/cat:Id)
                                then <cat:Id>{fn:data($Operacion/ope1:iniciativaEstrategica/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:iniciativaEstrategica/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Operacion/ope1:iniciativaEstrategica/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:iniciativaEstrategica/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Operacion/ope1:iniciativaEstrategica/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:iniciativaEstrategica/cat:estatus)
                                then <cat:estatus>{fn:data($Operacion/ope1:iniciativaEstrategica/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:iniciativaEstrategica/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Operacion/ope1:iniciativaEstrategica/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </ope1:iniciativaEstrategica>
                    else ()
                }
                {
                    if ($Operacion/ope1:cantidadPaises)
                    then 
                        <ope1:cantidadPaises>
                            {
                                if ($Operacion/ope1:cantidadPaises/cat:Id)
                                then <cat:Id>{fn:data($Operacion/ope1:cantidadPaises/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cantidadPaises/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Operacion/ope1:cantidadPaises/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cantidadPaises/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Operacion/ope1:cantidadPaises/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cantidadPaises/cat:estatus)
                                then <cat:estatus>{fn:data($Operacion/ope1:cantidadPaises/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:cantidadPaises/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Operacion/ope1:cantidadPaises/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </ope1:cantidadPaises>
                    else ()
                }
                {
                    if ($Operacion/ope1:sectorMercado)
                    then 
                        <ope1:sectorMercado>
                            {
                                if ($Operacion/ope1:sectorMercado/cat:Id)
                                then <cat:Id>{fn:data($Operacion/ope1:sectorMercado/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:sectorMercado/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Operacion/ope1:sectorMercado/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:sectorMercado/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Operacion/ope1:sectorMercado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:sectorMercado/cat:estatus)
                                then <cat:estatus>{fn:data($Operacion/ope1:sectorMercado/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:sectorMercado/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Operacion/ope1:sectorMercado/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </ope1:sectorMercado>
                    else ()
                }
                {
                    if ($Operacion/ope1:programadoPOA)
                    then <ope1:programadoPOA>{fn:data($Operacion/ope1:programadoPOA)}</ope1:programadoPOA>
                    else ()
                }
                {
                    if ($Operacion/ope1:ejercicioPOA)
                    then 
                        <ope1:ejercicioPOA>
                            {
                                if ($Operacion/ope1:ejercicioPOA/cat:Id)
                                then <cat:Id>{fn:data($Operacion/ope1:ejercicioPOA/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:ejercicioPOA/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Operacion/ope1:ejercicioPOA/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:ejercicioPOA/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Operacion/ope1:ejercicioPOA/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:ejercicioPOA/cat:estatus)
                                then <cat:estatus>{fn:data($Operacion/ope1:ejercicioPOA/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:ejercicioPOA/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Operacion/ope1:ejercicioPOA/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </ope1:ejercicioPOA>
                    else ()
                }
                {
                    if ($Operacion/ope1:tipoGarantia)
                    then 
                        <ope1:tipoGarantia>
                            {
                                if ($Operacion/ope1:tipoGarantia/cat:Id)
                                then <cat:Id>{fn:data($Operacion/ope1:tipoGarantia/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:tipoGarantia/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Operacion/ope1:tipoGarantia/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:tipoGarantia/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Operacion/ope1:tipoGarantia/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:tipoGarantia/cat:estatus)
                                then <cat:estatus>{fn:data($Operacion/ope1:tipoGarantia/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:tipoGarantia/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Operacion/ope1:tipoGarantia/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </ope1:tipoGarantia>
                    else ()
                }
                {
                    if ($Operacion/ope1:componenteConcesionalidad)
                    then <ope1:componenteConcesionalidad>{fn:data($Operacion/ope1:componenteConcesionalidad)}</ope1:componenteConcesionalidad>
                    else ()
                }
                {
                    if ($Operacion/ope1:estructurador)
                    then <ope1:estructurador>{fn:data($Operacion/ope1:estructurador)}</ope1:estructurador>
                    else ()
                }
                {
                    if ($Operacion/ope1:beneficiario)
                    then <ope1:beneficiario>{fn:data($Operacion/ope1:beneficiario)}</ope1:beneficiario>
                    else ()
                }
                {
                    if ($Operacion/ope1:unidadEjecutora)
                    then <ope1:unidadEjecutora>{fn:data($Operacion/ope1:unidadEjecutora)}</ope1:unidadEjecutora>
                    else ()
                }
                {
                    if ($Operacion/ope1:programa)
                    then <ope1:programa>{fn:data($Operacion/ope1:programa)}</ope1:programa>
                    else ()
                }
                {
                    if ($Operacion/ope1:declaracionJurada)
                    then 
                        <ope1:declaracionJurada>
                            {
                                if ($Operacion/ope1:declaracionJurada/dec:idDeclaracion)
                                then <dec:idDeclaracion>{fn:data($Operacion/ope1:declaracionJurada/dec:idDeclaracion)}</dec:idDeclaracion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:declaracionJurada/dec:codigoExternoDeclaracion)
                                then <dec:codigoExternoDeclaracion>{fn:data($Operacion/ope1:declaracionJurada/dec:codigoExternoDeclaracion)}</dec:codigoExternoDeclaracion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:declaracionJurada/dec:titulo)
                                then <dec:titulo>{fn:data($Operacion/ope1:declaracionJurada/dec:titulo)}</dec:titulo>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:declaracionJurada/dec:estadoDeclaracion)
                                then 
                                    <dec:estadoDeclaracion>
                                        {
                                            if ($Operacion/ope1:declaracionJurada/dec:estadoDeclaracion/dec:codigoEstadoDeclaracion)
                                            then <dec:codigoEstadoDeclaracion>{fn:data($Operacion/ope1:declaracionJurada/dec:estadoDeclaracion/dec:codigoEstadoDeclaracion)}</dec:codigoEstadoDeclaracion>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:declaracionJurada/dec:estadoDeclaracion/dec:nombreEstadoDeclaracion)
                                            then <dec:nombreEstadoDeclaracion>{fn:data($Operacion/ope1:declaracionJurada/dec:estadoDeclaracion/dec:nombreEstadoDeclaracion)}</dec:nombreEstadoDeclaracion>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:declaracionJurada/dec:estadoDeclaracion/dec:EstadoNoObjecion)
                                            then <dec:EstadoNoObjecion>{fn:data($Operacion/ope1:declaracionJurada/dec:estadoDeclaracion/dec:EstadoNoObjecion)}</dec:EstadoNoObjecion>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:declaracionJurada/dec:estadoDeclaracion/dec:nombreEstadoNoObjecion)
                                            then <dec:nombreEstadoNoObjecion>{fn:data($Operacion/ope1:declaracionJurada/dec:estadoDeclaracion/dec:nombreEstadoNoObjecion)}</dec:nombreEstadoNoObjecion>
                                            else ()
                                        }
                                    </dec:estadoDeclaracion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:declaracionJurada/dec:estadoBusqueda)
                                then 
                                    <dec:estadoBusqueda>
                                        {
                                            if ($Operacion/ope1:declaracionJurada/dec:estadoBusqueda/dec:codigoEstadoBusqueda)
                                            then <dec:codigoEstadoBusqueda>{fn:data($Operacion/ope1:declaracionJurada/dec:estadoBusqueda/dec:codigoEstadoBusqueda)}</dec:codigoEstadoBusqueda>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:declaracionJurada/dec:estadoBusqueda/dec:nombreEstadoBusqueda)
                                            then <dec:nombreEstadoBusqueda>{fn:data($Operacion/ope1:declaracionJurada/dec:estadoBusqueda/dec:nombreEstadoBusqueda)}</dec:nombreEstadoBusqueda>
                                            else ()
                                        }
                                    </dec:estadoBusqueda>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:declaracionJurada/dec:calificacionDeRiesgo)
                                then 
                                    <dec:calificacionDeRiesgo>
                                        {
                                            if ($Operacion/ope1:declaracionJurada/dec:calificacionDeRiesgo/dec:codigoCalificacionDeRiesgo)
                                            then <dec:codigoCalificacionDeRiesgo>{fn:data($Operacion/ope1:declaracionJurada/dec:calificacionDeRiesgo/dec:codigoCalificacionDeRiesgo)}</dec:codigoCalificacionDeRiesgo>
                                            else ()
                                        }
                                        {
                                            if ($Operacion/ope1:declaracionJurada/dec:calificacionDeRiesgo/dec:nombreCalificacionDeRiesgo)
                                            then <dec:nombreCalificacionDeRiesgo>{fn:data($Operacion/ope1:declaracionJurada/dec:calificacionDeRiesgo/dec:nombreCalificacionDeRiesgo)}</dec:nombreCalificacionDeRiesgo>
                                            else ()
                                        }
                                    </dec:calificacionDeRiesgo>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:declaracionJurada/dec:fechaRegistro)
                                then <dec:fechaRegistro>{fn:data($Operacion/ope1:declaracionJurada/dec:fechaRegistro)}</dec:fechaRegistro>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:declaracionJurada/dec:fechaFirmaDeclaracion)
                                then <dec:fechaFirmaDeclaracion>{fn:data($Operacion/ope1:declaracionJurada/dec:fechaFirmaDeclaracion)}</dec:fechaFirmaDeclaracion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:declaracionJurada/dec:fechaVencimiento)
                                then <dec:fechaVencimiento>{fn:data($Operacion/ope1:declaracionJurada/dec:fechaVencimiento)}</dec:fechaVencimiento>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:declaracionJurada/dec:comentarioDeclaracion)
                                then <dec:comentarioDeclaracion>{fn:data($Operacion/ope1:declaracionJurada/dec:comentarioDeclaracion)}</dec:comentarioDeclaracion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:declaracionJurada/dec:comentarioBusqueda)
                                then <dec:comentarioBusqueda>{fn:data($Operacion/ope1:declaracionJurada/dec:comentarioBusqueda)}</dec:comentarioBusqueda>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:declaracionJurada/dec:elevarAOtraInstancia)
                                then <dec:elevarAOtraInstancia>{fn:data($Operacion/ope1:declaracionJurada/dec:elevarAOtraInstancia)}</dec:elevarAOtraInstancia>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:declaracionJurada/dec:tipoOrigen)
                                then <dec:tipoOrigen>{fn:data($Operacion/ope1:declaracionJurada/dec:tipoOrigen)}</dec:tipoOrigen>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:declaracionJurada/dec:tipoSeguimiento)
                                then <dec:tipoSeguimiento>{fn:data($Operacion/ope1:declaracionJurada/dec:tipoSeguimiento)}</dec:tipoSeguimiento>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:declaracionJurada/dec:instanciaProceso)
                                then <dec:instanciaProceso>{fn:data($Operacion/ope1:declaracionJurada/dec:instanciaProceso)}</dec:instanciaProceso>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:declaracionJurada/dec:esSoloLectura)
                                then <dec:esSoloLectura>{fn:data($Operacion/ope1:declaracionJurada/dec:esSoloLectura)}</dec:esSoloLectura>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:declaracionJurada/dec:status)
                                then <dec:status>{fn:data($Operacion/ope1:declaracionJurada/dec:status)}</dec:status>
                                else ()
                            }
                        </ope1:declaracionJurada>
                    else ()
                }
                {
                    if ($Operacion/ope1:etapa)
                    then <ope1:etapa>{fn:data($Operacion/ope1:etapa)}</ope1:etapa>
                    else ()
                }
                {
                    if ($Operacion/ope1:status)
                    then <ope1:status>{fn:data($Operacion/ope1:status)}</ope1:status>
                    else ()
                }
                {
                    if ($Operacion/ope1:fechaBaja)
                    then <ope1:fechaBaja>{fn:data($Operacion/ope1:fechaBaja)}</ope1:fechaBaja>
                    else ()
                }
                {
                    if ($Operacion/ope1:calificacionRiesgo)
                    then <ope1:calificacionRiesgo>{fn:data($Operacion/ope1:calificacionRiesgo)}</ope1:calificacionRiesgo>
                    else ()
                }
                {
                    if ($Operacion/ope1:perspectiva)
                    then 
                        <ope1:perspectiva>
                            {
                                if ($Operacion/ope1:perspectiva/cat:Id)
                                then <cat:Id>{fn:data($Operacion/ope1:perspectiva/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:perspectiva/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Operacion/ope1:perspectiva/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:perspectiva/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Operacion/ope1:perspectiva/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:perspectiva/cat:estatus)
                                then <cat:estatus>{fn:data($Operacion/ope1:perspectiva/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:perspectiva/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Operacion/ope1:perspectiva/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </ope1:perspectiva>
                    else ()
                }
                {
                    if ($Operacion/ope1:estado)
                    then 
                        <ope1:estado>
                            {
                                if ($Operacion/ope1:estado/cat:Id)
                                then <cat:Id>{fn:data($Operacion/ope1:estado/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:estado/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Operacion/ope1:estado/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:estado/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Operacion/ope1:estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:estado/cat:estatus)
                                then <cat:estatus>{fn:data($Operacion/ope1:estado/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:estado/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Operacion/ope1:estado/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </ope1:estado>
                    else ()
                }
                {
                    if ($Operacion/ope1:SRC)
                    then 
                        <ope1:SRC>
                            {
                                if ($Operacion/ope1:SRC/cat:Id)
                                then <cat:Id>{fn:data($Operacion/ope1:SRC/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:SRC/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Operacion/ope1:SRC/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:SRC/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Operacion/ope1:SRC/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:SRC/cat:estatus)
                                then <cat:estatus>{fn:data($Operacion/ope1:SRC/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:SRC/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Operacion/ope1:SRC/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </ope1:SRC>
                    else ()
                }
                {
                    if ($Operacion/ope1:scrClienteDesembolsos)
                    then 
                        
                        if((fn:string-length($Operacion/ope1:tipoGarantia/cat:Id/text()) > 0)  and  (fn:string-length($Operacion/ope1:cliente/cli:sector/cat:Id/text())>0 )) then
                        if(fn:data($Operacion/ope1:tipoGarantia/cat:Id)=2 and fn:data($Operacion/ope1:cliente/cli:sector/cat:Id)=1)  then
                          <ope1:scrClienteDesembolsos>
                            <cat:Id>0</cat:Id>
                            <cat:Descripcion>APROBADO</cat:Descripcion>
                            <cat:DescripcionCorta>APROBADO</cat:DescripcionCorta>
                            <cat:estatus>No aplica</cat:estatus>
                            <cat:codigoExterno>No aplica</cat:codigoExterno>
                          </ope1:scrClienteDesembolsos> 
                        else
                        
                          <ope1:scrClienteDesembolsos>
                            <cat:Id>{fn:data($Operacion/ope1:scrClienteDesembolsos/cat:Id)}</cat:Id>
                            <cat:Descripcion>{fn:data($Operacion/ope1:scrClienteDesembolsos/cat:Descripcion)}</cat:Descripcion>
                            <cat:DescripcionCorta>{fn:data($Operacion/ope1:scrClienteDesembolsos/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            <cat:estatus>{fn:data($Operacion/ope1:scrClienteDesembolsos/cat:estatus)}</cat:estatus>
                            <cat:codigoExterno>{fn:data($Operacion/ope1:scrClienteDesembolsos/cat:codigoExterno)}</cat:codigoExterno>
                          </ope1:scrClienteDesembolsos>
                        
                        else()
                    else ()
                }
                {
                    if ($Operacion/ope1:RAROC)
                    then <ope1:RAROC>{fn:data($Operacion/ope1:RAROC)}</ope1:RAROC>
                    else ()
                }
                {
                    if ($Operacion/ope1:TIR)
                    then <ope1:TIR>{fn:data($Operacion/ope1:TIR)}</ope1:TIR>
                    else ()
                }
                {
                    if ($Operacion/ope1:TIREstatus)
                    then <ope1:TIREstatus>{fn:data($Operacion/ope1:TIREstatus)}</ope1:TIREstatus>
                    else ()
                }
                {
                    if ($Operacion/ope1:requiereRecursosExternos)
                    then <ope1:requiereRecursosExternos>{fn:data($Operacion/ope1:requiereRecursosExternos)}</ope1:requiereRecursosExternos>
                    else ()
                }
                {
                    if ($Operacion/ope1:enProcesoLaft)
                    then <ope1:enProcesoLaft>{fn:data($Operacion/ope1:enProcesoLaft)}</ope1:enProcesoLaft>
                    else ()
                }
                {
                    if ($Operacion/ope1:aplicaLaft)
                    then <ope1:aplicaLaft>{fn:data($Operacion/ope1:aplicaLaft)}</ope1:aplicaLaft>
                    else ()
                }
                <ope1:montoOperacion>
                    {
                        for $montoOperacion in $Operacion/ope1:montoOperacion/ope1:montoOperacion
                        return 
                        <ope1:montoOperacion>
                            {
                                if ($montoOperacion/ope1:id)
                                then <ope1:id>{fn:data($montoOperacion/ope1:id)}</ope1:id>
                                else ()
                            }
                            {
                                if ($montoOperacion/ope1:idOperacion)
                                then <ope1:idOperacion>{fn:data($montoOperacion/ope1:idOperacion)}</ope1:idOperacion>
                                else ()
                            }
                            {
                                if ($montoOperacion/ope1:IdTcaTipoMonto)
                                then <ope1:IdTcaTipoMonto>{fn:data($montoOperacion/ope1:IdTcaTipoMonto)}</ope1:IdTcaTipoMonto>
                                else ()
                            }
                            {
                                if ($montoOperacion/ope1:monto)
                                then <ope1:monto>{fn:data($montoOperacion/ope1:monto)}</ope1:monto>
                                else ()
                            }
                            {
                                if ($montoOperacion/ope1:Descripcion)
                                then <ope1:Descripcion>{fn:data($montoOperacion/ope1:Descripcion)}</ope1:Descripcion>
                                else ()
                            }
                            {
                                if ($montoOperacion/ope1:fechaRegistro)
                                then <ope1:fechaRegistro>{fn:data($montoOperacion/ope1:fechaRegistro)}</ope1:fechaRegistro>
                                else ()
                            }
                            {
                                if ($montoOperacion/ope1:estado)
                                then <ope1:estado>{fn:data($montoOperacion/ope1:estado)}</ope1:estado>
                                else ()
                            }
                        </ope1:montoOperacion>
                    }
                  <ope1:montoOperacion>
                    <ope1:id></ope1:id>
                    <ope1:idOperacion>{fn:data($outputVariable.response/ope:Operacion/ope1:idOperacion)}</ope1:idOperacion>
                    <ope1:IdTcaTipoMonto>5</ope1:IdTcaTipoMonto>
                    <ope1:monto>{
                      sum(for $linea in $outputVariable.response/ope:Operacion/ope1:contrato/con:LineaCredito
                      let $importe := if(string($linea/lin:Flexcube/lin:id)!= '') then fn:data($linea/lin:MontoLinea) else(0)
                        return $importe)
                    }</ope1:monto>
                    <ope1:Descripcion>FORMALIZADO</ope1:Descripcion>
                  </ope1:montoOperacion> 
                  <ope1:montoOperacion>
                    <ope1:id>6</ope1:id>
                    <ope1:idOperacion>{fn:data($outputVariable.response/ope:Operacion/ope1:idOperacion)}</ope1:idOperacion>
                    <ope1:IdTcaTipoMonto>6</ope1:IdTcaTipoMonto>
                    <ope1:monto>{sum(for $linea at $posLinea in $outputVariable.response/ope:Operacion/ope1:contrato/con:LineaCredito
                                      let $montoDisponible := 0.0
                                      where string($linea/lin:Flexcube/lin:id) != ''
                                      return sum(for $monto at $posMonto in $linea/lin:Monto
                                              let $montoDisponible := if($monto/com:tipo/cat:DescripcionCorta = 'DISPONIBLE_DESEMBOLSO')then fn:data($monto/com:importe) else(0)
                                              return $montoDisponible)
                      )}</ope1:monto>
                    <ope1:Descripcion>DISPONIBLE_DESEMBOLSO</ope1:Descripcion>
                  </ope1:montoOperacion>
                </ope1:montoOperacion>
                {
                    if ($Operacion/ope1:TipoMoneda)
                    then 
                        <ope1:TipoMoneda>
                            {
                                if ($Operacion/ope1:TipoMoneda/cat:Id)
                                then <cat:Id>{fn:data($Operacion/ope1:TipoMoneda/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:TipoMoneda/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Operacion/ope1:TipoMoneda/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:TipoMoneda/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Operacion/ope1:TipoMoneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:TipoMoneda/cat:estatus)
                                then <cat:estatus>{fn:data($Operacion/ope1:TipoMoneda/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:TipoMoneda/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Operacion/ope1:TipoMoneda/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </ope1:TipoMoneda>
                    else ()
                }
                  {
                    if ($Operacion/ope1:esMultisectorial)
                    then <ope1:esMultisectorial>{fn:data($Operacion/ope1:esMultisectorial)}</ope1:esMultisectorial>
                    else ()
                }
                 <ope1:clasificacionEstrategicaMultisectorial>
                {
                    for $clasificacionEstrategicaMultisectorial in $Operacion/ope1:clasificacionEstrategicaMultisectorial/ope1:componenteClasificacionEstrategica
                    return 
                    <ope1:componenteClasificacionEstrategica>
                        {
                            if ($clasificacionEstrategicaMultisectorial/ope1:nombreComponente)
                            then <ope1:nombreComponente>{fn:data($clasificacionEstrategicaMultisectorial/ope1:nombreComponente)}</ope1:nombreComponente>
                            else ()
                        }
                        {
                            if ($clasificacionEstrategicaMultisectorial/ope1:descripcion)
                            then <ope1:descripcion>{fn:data($clasificacionEstrategicaMultisectorial/ope1:descripcion)}</ope1:descripcion>
                            else ()
                        }
                        {
                            if ($clasificacionEstrategicaMultisectorial/ope1:porcentaje)
                            then <ope1:porcentaje>{fn:data($clasificacionEstrategicaMultisectorial/ope1:porcentaje)}</ope1:porcentaje>
                            else ()
                        }
                        {
                            if ($clasificacionEstrategicaMultisectorial/ope1:actividadEconomica)
                            then 
                                <ope1:actividadEconomica>
                                    {
                                        if ($clasificacionEstrategicaMultisectorial/ope1:actividadEconomica/cat:Id)
                                        then <cat:Id>{fn:data($clasificacionEstrategicaMultisectorial/ope1:actividadEconomica/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($clasificacionEstrategicaMultisectorial/ope1:actividadEconomica/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($clasificacionEstrategicaMultisectorial/ope1:actividadEconomica/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($clasificacionEstrategicaMultisectorial/ope1:actividadEconomica/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($clasificacionEstrategicaMultisectorial/ope1:actividadEconomica/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </ope1:actividadEconomica>
                            else ()
                        }
                        {
                            if ($clasificacionEstrategicaMultisectorial/ope1:actividadEconomicaAsociada)
                            then 
                                <ope1:actividadEconomicaAsociada>
                                    {
                                        if ($clasificacionEstrategicaMultisectorial/ope1:actividadEconomicaAsociada/cat:Id)
                                        then <cat:Id>{fn:data($clasificacionEstrategicaMultisectorial/ope1:actividadEconomicaAsociada/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($clasificacionEstrategicaMultisectorial/ope1:actividadEconomicaAsociada/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($clasificacionEstrategicaMultisectorial/ope1:actividadEconomicaAsociada/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($clasificacionEstrategicaMultisectorial/ope1:actividadEconomicaAsociada/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($clasificacionEstrategicaMultisectorial/ope1:actividadEconomicaAsociada/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </ope1:actividadEconomicaAsociada>
                            else ()
                        }
                        {
                            if ($clasificacionEstrategicaMultisectorial/ope1:areaFocalizacion)
                            then 
                                <ope1:areaFocalizacion>
                                    {
                                        if ($clasificacionEstrategicaMultisectorial/ope1:areaFocalizacion/cat:Id)
                                        then <cat:Id>{fn:data($clasificacionEstrategicaMultisectorial/ope1:areaFocalizacion/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($clasificacionEstrategicaMultisectorial/ope1:areaFocalizacion/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($clasificacionEstrategicaMultisectorial/ope1:areaFocalizacion/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                </ope1:areaFocalizacion>
                            else ()
                        }
                        {
                            if ($clasificacionEstrategicaMultisectorial/ope1:ejeEstrategico)
                            then 
                                <ope1:ejeEstrategico>
                                    {
                                        if ($clasificacionEstrategicaMultisectorial/ope1:ejeEstrategico/cat:Id)
                                        then <cat:Id>{fn:data($clasificacionEstrategicaMultisectorial/ope1:ejeEstrategico/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($clasificacionEstrategicaMultisectorial/ope1:ejeEstrategico/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($clasificacionEstrategicaMultisectorial/ope1:ejeEstrategico/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($clasificacionEstrategicaMultisectorial/ope1:ejeEstrategico/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($clasificacionEstrategicaMultisectorial/ope1:ejeEstrategico/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </ope1:ejeEstrategico>
                            else ()
                        }
                        {
                            if ($clasificacionEstrategicaMultisectorial/ope1:iniciativaEstrategica)
                            then 
                                <ope1:iniciativaEstrategica>
                                    {
                                        if ($clasificacionEstrategicaMultisectorial/ope1:iniciativaEstrategica/cat:Id)
                                        then <cat:Id>{fn:data($clasificacionEstrategicaMultisectorial/ope1:iniciativaEstrategica/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($clasificacionEstrategicaMultisectorial/ope1:iniciativaEstrategica/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($clasificacionEstrategicaMultisectorial/ope1:iniciativaEstrategica/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                </ope1:iniciativaEstrategica>
                            else ()
                        }
                        {
                            if ($clasificacionEstrategicaMultisectorial/ope1:cantidadPaises)
                            then 
                                <ope1:cantidadPaises>
                                    {
                                        if ($clasificacionEstrategicaMultisectorial/ope1:cantidadPaises/cat:Id)
                                        then <cat:Id>{fn:data($clasificacionEstrategicaMultisectorial/ope1:cantidadPaises/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($clasificacionEstrategicaMultisectorial/ope1:cantidadPaises/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($clasificacionEstrategicaMultisectorial/ope1:cantidadPaises/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                </ope1:cantidadPaises>
                            else ()
                        }
                        {
                            if ($clasificacionEstrategicaMultisectorial/ope1:sectorIbcie)
                            then 
                                <ope1:sectorIbcie>
                                    {
                                        if ($clasificacionEstrategicaMultisectorial/ope1:sectorIbcie/cat:Id)
                                        then <cat:Id>{fn:data($clasificacionEstrategicaMultisectorial/ope1:sectorIbcie/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($clasificacionEstrategicaMultisectorial/ope1:sectorIbcie/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($clasificacionEstrategicaMultisectorial/ope1:sectorIbcie/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($clasificacionEstrategicaMultisectorial/ope1:sectorIbcie/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($clasificacionEstrategicaMultisectorial/ope1:sectorIbcie/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($clasificacionEstrategicaMultisectorial/ope1:sectorIbcie/cat:estatus)
                                        then <cat:estatus>{fn:data($clasificacionEstrategicaMultisectorial/ope1:sectorIbcie/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($clasificacionEstrategicaMultisectorial/ope1:sectorIbcie/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($clasificacionEstrategicaMultisectorial/ope1:sectorIbcie/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </ope1:sectorIbcie>
                            else ()
                        }
                        {
                            if ($clasificacionEstrategicaMultisectorial/ope1:subSectorIbcie)
                            then 
                                <ope1:subSectorIbcie>
                                    {
                                        if ($clasificacionEstrategicaMultisectorial/ope1:subSectorIbcie/cat:Id)
                                        then <cat:Id>{fn:data($clasificacionEstrategicaMultisectorial/ope1:subSectorIbcie/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($clasificacionEstrategicaMultisectorial/ope1:subSectorIbcie/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($clasificacionEstrategicaMultisectorial/ope1:subSectorIbcie/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($clasificacionEstrategicaMultisectorial/ope1:subSectorIbcie/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($clasificacionEstrategicaMultisectorial/ope1:subSectorIbcie/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($clasificacionEstrategicaMultisectorial/ope1:subSectorIbcie/cat:estatus)
                                        then <cat:estatus>{fn:data($clasificacionEstrategicaMultisectorial/ope1:subSectorIbcie/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($clasificacionEstrategicaMultisectorial/ope1:subSectorIbcie/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($clasificacionEstrategicaMultisectorial/ope1:subSectorIbcie/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </ope1:subSectorIbcie>
                            else ()
                        }
                                               
                    </ope1:componenteClasificacionEstrategica>
                }
                </ope1:clasificacionEstrategicaMultisectorial>
                {
                    if ($Operacion/ope1:sectorInstitucional)
                    then 
                        <ope1:sectorInstitucional>
                            {
                                if ($Operacion/ope1:sectorInstitucional/cat:Id)
                                then <cat:Id>{fn:data($Operacion/ope1:sectorInstitucional/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:sectorInstitucional/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Operacion/ope1:sectorInstitucional/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:sectorInstitucional/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Operacion/ope1:sectorInstitucional/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:sectorInstitucional/cat:estatus)
                                then <cat:estatus>{fn:data($Operacion/ope1:sectorInstitucional/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:sectorInstitucional/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Operacion/ope1:sectorInstitucional/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </ope1:sectorInstitucional>
                    else ()
                }
                {
                    if ($Operacion/ope1:encargado)
                    then 
                        <ope1:encargado>
                            {
                                if ($Operacion/ope1:encargado/cat:Id)
                                then <cat:Id>{fn:data($Operacion/ope1:encargado/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:encargado/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Operacion/ope1:encargado/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:encargado/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Operacion/ope1:encargado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:encargado/cat:estatus)
                                then <cat:estatus>{fn:data($Operacion/ope1:encargado/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Operacion/ope1:encargado/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Operacion/ope1:encargado/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </ope1:encargado>
                    else ()
                }
                {
                    if ($Operacion/ope1:ubicacionProyecto)
                    then <ope1:ubicacionProyecto>{fn:data($Operacion/ope1:ubicacionProyecto)}</ope1:ubicacionProyecto>
                    else ()
                }
                {
                    if ($Operacion/ope1:idRol)
                    then <ope1:idRol>{fn:data($Operacion/ope1:idRol)}</ope1:idRol>
                    else ()
                }
                {
                    if ($Operacion/ope1:descripcionRol)
                    then <ope1:descripcionRol>{fn:data($Operacion/ope1:descripcionRol)}</ope1:descripcionRol>
                    else ()
                }
                {
                    if ($Operacion/ope1:idCatPais)
                    then <ope1:idCatPais>{fn:data($Operacion/ope1:idCatPais)}</ope1:idCatPais>
                    else ()
                }
                {
                  if(string($Operacion/ope1:contrato[1]) != '')then
                    for $contrato in $Operacion/ope1:contrato
                    return 
                    <ope1:contrato>
                        <con:idContrato>{fn:data($contrato/con:idContrato)}</con:idContrato>
                        <con:idOperacion>{fn:data($contrato/con:idOperacion)}</con:idOperacion>
                        <con:fechaFirma>{fn:data($contrato/con:fechaFirma)}</con:fechaFirma>
                        <con:fechaVigencia>{fn:data($contrato/con:fechaVigencia)}</con:fechaVigencia>
                        <con:MontoEscriturado>{fn:data($contrato/con:MontoEscriturado)}</con:MontoEscriturado>
                       
                        <con:cuentaCliente>
                            {
                                for $cuentaCliente in $contrato/con:cuentaCliente/con:cuentaCliente
                                return 
                                <con:cuentaCliente>{fn:data($contrato/con:cuentaCliente/con:cuentaCliente)}</con:cuentaCliente>
                            }
                        </con:cuentaCliente>
                        <con:instanciaProceso>{fn:data($contrato/con:instanciaProceso)}</con:instanciaProceso>
                        {
                          if(string($contrato/con:LineaCredito[1]) != '')then
                            for $LineaCredito in $contrato/con:LineaCredito
                            return 
                            <con:LineaCredito>
                                {
                                    if ($LineaCredito/lin:idLineaCredito)
                                    then <lin:idLineaCredito>{fn:data($LineaCredito/lin:idLineaCredito)}</lin:idLineaCredito>
                                    else ()
                                }
                                {
                                    if ($LineaCredito/lin:idContrato)
                                    then <lin:idContrato>{fn:data($LineaCredito/lin:idContrato)}</lin:idContrato>
                                    else ()
                                }
                                <lin:NumeroLineaCredito>{fn:data($LineaCredito/lin:NumeroLineaCredito)}</lin:NumeroLineaCredito>
                                <lin:Descripcion>{fn:data($LineaCredito/lin:Descripcion)}</lin:Descripcion>
                                {
                                    if ($LineaCredito/lin:Flexcube)
                                    then 
                                        <lin:Flexcube>
                                            {
                                                if ($LineaCredito/lin:Flexcube/lin:id)
                                                then <lin:id>{fn:data($LineaCredito/lin:Flexcube/lin:id)}</lin:id>
                                                else ()
                                            }
                                            {
                                                if ($LineaCredito/lin:Flexcube/lin:idProductoFlexcube)
                                                then <lin:idProductoFlexcube>{fn:data($LineaCredito/lin:Flexcube/lin:idProductoFlexcube)}</lin:idProductoFlexcube>
                                                else ()
                                            }
                                            {
                                                if ($LineaCredito/lin:Flexcube/lin:idFlexcubePasivo)
                                                then <lin:idFlexcubePasivo>{fn:data($LineaCredito/lin:Flexcube/lin:idFlexcubePasivo)}</lin:idFlexcubePasivo>
                                                else ()
                                            }
                                        </lin:Flexcube>
                                    else ()
                                }
                                <lin:Fondo>{fn:data($LineaCredito/lin:Fondo)}</lin:Fondo>
                                <lin:MontoLinea>{fn:data($LineaCredito/lin:MontoLinea)}</lin:MontoLinea>
                                {
                                    if ($LineaCredito/lin:EsRevolvente)
                                    then <lin:EsRevolvente>{fn:data($LineaCredito/lin:EsRevolvente)}</lin:EsRevolvente>
                                    else ()
                                }
                                <lin:TratamientoDiasFeriados>{fn:data($LineaCredito/lin:TratamientoDiasFeriados)}</lin:TratamientoDiasFeriados>
                                <lin:FechaValor>{fn:data($LineaCredito/lin:FechaValor)}</lin:FechaValor>
                                <lin:FechaVencimiento>{fn:data($LineaCredito/lin:FechaVencimiento)}</lin:FechaVencimiento>
                                <lin:CodigoPantallaLimite>{fn:data($LineaCredito/lin:CodigoPantallaLimite)}</lin:CodigoPantallaLimite>
                                <lin:CodigoSerialLimite>{fn:data($LineaCredito/lin:CodigoSerialLimite)}</lin:CodigoSerialLimite>
                                <lin:CodigoAsignacion>{fn:data($LineaCredito/lin:CodigoAsignacion)}</lin:CodigoAsignacion>
                                <lin:DescripcionAsignacion>{fn:data($LineaCredito/lin:DescripcionAsignacion)}</lin:DescripcionAsignacion>
                                {
                                    if ($LineaCredito/lin:CondicionEspecial)
                                    then <lin:CondicionEspecial>{fn:data($LineaCredito/lin:CondicionEspecial)}</lin:CondicionEspecial>
                                    else ()
                                }
                                <lin:FechaRegistro>{fn:data($LineaCredito/lin:FechaRegistro)}</lin:FechaRegistro>
                                <lin:FechaMaximaDesembolso>{fn:data($LineaCredito/lin:FechaMaximaDesembolso)}</lin:FechaMaximaDesembolso>
                                {
                                    if ($LineaCredito/lin:Estado)
                                    then <lin:Estado>{fn:data($LineaCredito/lin:Estado)}</lin:Estado>
                                    else ()
                                }
                                <lin:descCondEspecial>{fn:data($LineaCredito/lin:descCondEspecial)}</lin:descCondEspecial>
                                <lin:frecuenciaGracia>{fn:data($LineaCredito/lin:frecuenciaGracia)}</lin:frecuenciaGracia>
                                <lin:plazoGracia>{fn:data($LineaCredito/lin:plazoGracia)}</lin:plazoGracia>
                                <lin:frecuenciaFinanciamiento>{fn:data($LineaCredito/lin:frecuenciaFinanciamiento)}</lin:frecuenciaFinanciamiento>
                                <lin:plazoFinanciamiento>{fn:data($LineaCredito/lin:plazoFinanciamiento)}</lin:plazoFinanciamiento>
                                <lin:recursosExternos>{fn:data($LineaCredito/lin:recursosExternos)}</lin:recursosExternos>
                                <lin:tasaMinima>{fn:data($LineaCredito/lin:tasaMinima)}</lin:tasaMinima>
                                <lin:tasaMaxima>{fn:data($LineaCredito/lin:tasaMaxima)}</lin:tasaMaxima>
                                <lin:montoMinimo>{fn:data($LineaCredito/lin:montoMinimo)}</lin:montoMinimo>
                                <lin:montoMaximo>{fn:data($LineaCredito/lin:montoMaximo)}</lin:montoMaximo>
                                {
                                    for $Monto in $LineaCredito/lin:Monto
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
                                {
                                    for $Condicion in $LineaCredito/lin:Condicion
                                    return 
                                    <lin:Condicion>
                                        <con1:idCondicion>{fn:data($Condicion/con1:idCondicion)}</con1:idCondicion>
                                        <con1:operacion>{fn:data($Condicion/con1:operacion)}</con1:operacion>
                                        {
                                            if ($Condicion/con1:nombre)
                                            then <con1:nombre>{fn:data($Condicion/con1:nombre)}</con1:nombre>
                                            else ()
                                        }
                                        {
                                            if ($Condicion/con1:descripcion)
                                            then <con1:descripcion>{fn:data($Condicion/con1:descripcion)}</con1:descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Condicion/con1:tipoCondicion)
                                            then 
                                                <con1:tipoCondicion>
                                                    {
                                                        if ($Condicion/con1:tipoCondicion/con1:idCatCondicion)
                                                        then <con1:idCatCondicion>{fn:data($Condicion/con1:tipoCondicion/con1:idCatCondicion)}</con1:idCatCondicion>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:tipoCondicion/con1:descripcion)
                                                        then <con1:descripcion>{fn:data($Condicion/con1:tipoCondicion/con1:descripcion)}</con1:descripcion>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:tipoCondicion/con1:descripcionCorta)
                                                        then <con1:descripcionCorta>{fn:data($Condicion/con1:tipoCondicion/con1:descripcionCorta)}</con1:descripcionCorta>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:tipoCondicion/con1:idTipoCondicion)
                                                        then <con1:idTipoCondicion>{fn:data($Condicion/con1:tipoCondicion/con1:idTipoCondicion)}</con1:idTipoCondicion>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:tipoCondicion/con1:esEditableEnFormalizacion)
                                                        then <con1:esEditableEnFormalizacion>{fn:data($Condicion/con1:tipoCondicion/con1:esEditableEnFormalizacion)}</con1:esEditableEnFormalizacion>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:tipoCondicion/con1:requiereValidarCOFI)
                                                        then <con1:requiereValidarCOFI>{fn:data($Condicion/con1:tipoCondicion/con1:requiereValidarCOFI)}</con1:requiereValidarCOFI>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:tipoCondicion/con1:sePuedeDispensar)
                                                        then <con1:sePuedeDispensar>{fn:data($Condicion/con1:tipoCondicion/con1:sePuedeDispensar)}</con1:sePuedeDispensar>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:tipoCondicion/con1:esPlantilla)
                                                        then <con1:esPlantilla>{fn:data($Condicion/con1:tipoCondicion/con1:esPlantilla)}</con1:esPlantilla>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:tipoCondicion/con1:idCondicionPrecarga)
                                                        then <con1:idCondicionPrecarga>{fn:data($Condicion/con1:tipoCondicion/con1:idCondicionPrecarga)}</con1:idCondicionPrecarga>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:tipoCondicion/con1:fechaRegistro)
                                                        then <con1:fechaRegistro>{fn:data($Condicion/con1:tipoCondicion/con1:fechaRegistro)}</con1:fechaRegistro>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:tipoCondicion/con1:estado)
                                                        then <con1:estado>{fn:data($Condicion/con1:tipoCondicion/con1:estado)}</con1:estado>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:tipoCondicion/con1:codigoExterno)
                                                        then <con1:codigoExterno>{fn:data($Condicion/con1:tipoCondicion/con1:codigoExterno)}</con1:codigoExterno>
                                                        else ()
                                                    }
                                                </con1:tipoCondicion>
                                            else ()
                                        }
                                        {
                                            for $categoriaCondicion in $Condicion/con1:categoriaCondicion
                                            return 
                                            <con1:categoriaCondicion>
                                                <con1:id>{fn:data($categoriaCondicion/con1:id)}</con1:id>
                                                <con1:descripcion>{fn:data($categoriaCondicion/con1:descripcion)}</con1:descripcion>
                                                {
                                                    if ($categoriaCondicion/con1:descripcionCorta)
                                                    then <con1:descripcionCorta>{fn:data($categoriaCondicion/con1:descripcionCorta)}</con1:descripcionCorta>
                                                    else ()
                                                }
                                                {
                                                    if ($categoriaCondicion/con1:estatus)
                                                    then <con1:estatus>{fn:data($categoriaCondicion/con1:estatus)}</con1:estatus>
                                                    else ()
                                                }
                                                {
                                                    if ($categoriaCondicion/con1:codigoExterno)
                                                    then <con1:codigoExterno>{fn:data($categoriaCondicion/con1:codigoExterno)}</con1:codigoExterno>
                                                    else ()
                                                }
                                                {
                                                    for $validadores in $categoriaCondicion/con1:validadores
                                                    return 
                                                    <con1:validadores>
                                                        {
                                                            if ($validadores/cat:Id)
                                                            then <cat:Id>{fn:data($validadores/cat:Id)}</cat:Id>
                                                            else ()
                                                        }
                                                        {
                                                            if ($validadores/cat:Descripcion)
                                                            then <cat:Descripcion>{fn:data($validadores/cat:Descripcion)}</cat:Descripcion>
                                                            else ()
                                                        }
                                                        {
                                                            if ($validadores/cat:DescripcionCorta)
                                                            then <cat:DescripcionCorta>{fn:data($validadores/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                            else ()
                                                        }
                                                        {
                                                            if ($validadores/cat:estatus)
                                                            then <cat:estatus>{fn:data($validadores/cat:estatus)}</cat:estatus>
                                                            else ()
                                                        }
                                                        {
                                                            if ($validadores/cat:codigoExterno)
                                                            then <cat:codigoExterno>{fn:data($validadores/cat:codigoExterno)}</cat:codigoExterno>
                                                            else ()
                                                        }
                                                    </con1:validadores>
                                                }
                                            </con1:categoriaCondicion>
                                        }
                                        {
                                            if ($Condicion/con1:controlCondicion)
                                            then 
                                                <con1:controlCondicion>
                                                    {
                                                        if ($Condicion/con1:controlCondicion/cat:Id)
                                                        then <cat:Id>{fn:data($Condicion/con1:controlCondicion/cat:Id)}</cat:Id>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:controlCondicion/cat:Descripcion)
                                                        then <cat:Descripcion>{fn:data($Condicion/con1:controlCondicion/cat:Descripcion)}</cat:Descripcion>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:controlCondicion/cat:DescripcionCorta)
                                                        then <cat:DescripcionCorta>{fn:data($Condicion/con1:controlCondicion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:controlCondicion/cat:estatus)
                                                        then <cat:estatus>{fn:data($Condicion/con1:controlCondicion/cat:estatus)}</cat:estatus>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:controlCondicion/cat:codigoExterno)
                                                        then <cat:codigoExterno>{fn:data($Condicion/con1:controlCondicion/cat:codigoExterno)}</cat:codigoExterno>
                                                        else ()
                                                    }
                                                </con1:controlCondicion>
                                            else ()
                                        }
                                        {
                                            for $eventoCondicion in $Condicion/con1:eventoCondicion
                                            return 
                                            <con1:eventoCondicion>
                                                {
                                                    if ($eventoCondicion/cat:Id)
                                                    then <cat:Id>{fn:data($eventoCondicion/cat:Id)}</cat:Id>
                                                    else ()
                                                }
                                                {
                                                    if ($eventoCondicion/cat:Descripcion)
                                                    then <cat:Descripcion>{fn:data($eventoCondicion/cat:Descripcion)}</cat:Descripcion>
                                                    else ()
                                                }
                                                {
                                                    if ($eventoCondicion/cat:DescripcionCorta)
                                                    then <cat:DescripcionCorta>{fn:data($eventoCondicion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                    else ()
                                                }
                                                {
                                                    if ($eventoCondicion/cat:estatus)
                                                    then <cat:estatus>{fn:data($eventoCondicion/cat:estatus)}</cat:estatus>
                                                    else ()
                                                }
                                                {
                                                    if ($eventoCondicion/cat:codigoExterno)
                                                    then <cat:codigoExterno>{fn:data($eventoCondicion/cat:codigoExterno)}</cat:codigoExterno>
                                                    else ()
                                                }
                                            </con1:eventoCondicion>
                                        }
                                        {
                                            if ($Condicion/con1:tipoFechaInicio)
                                            then 
                                                <con1:tipoFechaInicio>
                                                    {
                                                        if ($Condicion/con1:tipoFechaInicio/cat:Id)
                                                        then <cat:Id>{fn:data($Condicion/con1:tipoFechaInicio/cat:Id)}</cat:Id>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:tipoFechaInicio/cat:Descripcion)
                                                        then <cat:Descripcion>{fn:data($Condicion/con1:tipoFechaInicio/cat:Descripcion)}</cat:Descripcion>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:tipoFechaInicio/cat:DescripcionCorta)
                                                        then <cat:DescripcionCorta>{fn:data($Condicion/con1:tipoFechaInicio/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:tipoFechaInicio/cat:estatus)
                                                        then <cat:estatus>{fn:data($Condicion/con1:tipoFechaInicio/cat:estatus)}</cat:estatus>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:tipoFechaInicio/cat:codigoExterno)
                                                        then <cat:codigoExterno>{fn:data($Condicion/con1:tipoFechaInicio/cat:codigoExterno)}</cat:codigoExterno>
                                                        else ()
                                                    }
                                                </con1:tipoFechaInicio>
                                            else ()
                                        }
                                        {
                                            if ($Condicion/con1:fechaInicio)
                                            then <con1:fechaInicio>{fn:data($Condicion/con1:fechaInicio)}</con1:fechaInicio>
                                            else ()
                                        }
                                        {
                                            if ($Condicion/con1:plazo)
                                            then <con1:plazo>{fn:data($Condicion/con1:plazo)}</con1:plazo>
                                            else ()
                                        }
                                        {
                                            if ($Condicion/con1:frecuenciaPlazo)
                                            then 
                                                <con1:frecuenciaPlazo>
                                                    {
                                                        if ($Condicion/con1:frecuenciaPlazo/cat:Id)
                                                        then <cat:Id>{fn:data($Condicion/con1:frecuenciaPlazo/cat:Id)}</cat:Id>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:frecuenciaPlazo/cat:Descripcion)
                                                        then <cat:Descripcion>{fn:data($Condicion/con1:frecuenciaPlazo/cat:Descripcion)}</cat:Descripcion>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:frecuenciaPlazo/cat:DescripcionCorta)
                                                        then <cat:DescripcionCorta>{fn:data($Condicion/con1:frecuenciaPlazo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:frecuenciaPlazo/cat:estatus)
                                                        then <cat:estatus>{fn:data($Condicion/con1:frecuenciaPlazo/cat:estatus)}</cat:estatus>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:frecuenciaPlazo/cat:codigoExterno)
                                                        then <cat:codigoExterno>{fn:data($Condicion/con1:frecuenciaPlazo/cat:codigoExterno)}</cat:codigoExterno>
                                                        else ()
                                                    }
                                                </con1:frecuenciaPlazo>
                                            else ()
                                        }
                                        {
                                            if ($Condicion/con1:fechaFinal)
                                            then <con1:fechaFinal>{fn:data($Condicion/con1:fechaFinal)}</con1:fechaFinal>
                                            else ()
                                        }
                                        {
                                            if ($Condicion/con1:estadoTCC)
                                            then 
                                                <con1:estadoTCC>
                                                    {
                                                        if ($Condicion/con1:estadoTCC/atr:id)
                                                        then <atr:id>{fn:data($Condicion/con1:estadoTCC/atr:id)}</atr:id>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:estadoTCC/atr:descripcion)
                                                        then <atr:descripcion>{fn:data($Condicion/con1:estadoTCC/atr:descripcion)}</atr:descripcion>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:estadoTCC/atr:descripcionCorta)
                                                        then <atr:descripcionCorta>{fn:data($Condicion/con1:estadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:estadoTCC/atr:estatus)
                                                        then <atr:estatus>{fn:data($Condicion/con1:estadoTCC/atr:estatus)}</atr:estatus>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:estadoTCC/atr:codigoExterno)
                                                        then <atr:codigoExterno>{fn:data($Condicion/con1:estadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:estadoTCC/atr:esSubEstado)
                                                        then <atr:esSubEstado>{fn:data($Condicion/con1:estadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                                                        else ()
                                                    }
                                                </con1:estadoTCC>
                                            else ()
                                        }
                                        {
                                            if ($Condicion/con1:subEstadoTCC)
                                            then 
                                                <con1:subEstadoTCC>
                                                    {
                                                        if ($Condicion/con1:subEstadoTCC/atr:id)
                                                        then <atr:id>{fn:data($Condicion/con1:subEstadoTCC/atr:id)}</atr:id>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:subEstadoTCC/atr:descripcion)
                                                        then <atr:descripcion>{fn:data($Condicion/con1:subEstadoTCC/atr:descripcion)}</atr:descripcion>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:subEstadoTCC/atr:descripcionCorta)
                                                        then <atr:descripcionCorta>{fn:data($Condicion/con1:subEstadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:subEstadoTCC/atr:estatus)
                                                        then <atr:estatus>{fn:data($Condicion/con1:subEstadoTCC/atr:estatus)}</atr:estatus>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:subEstadoTCC/atr:codigoExterno)
                                                        then <atr:codigoExterno>{fn:data($Condicion/con1:subEstadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Condicion/con1:subEstadoTCC/atr:esSubEstado)
                                                        then <atr:esSubEstado>{fn:data($Condicion/con1:subEstadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                                                        else ()
                                                    }
                                                </con1:subEstadoTCC>
                                            else ()
                                        }
                                        {
                                            if ($Condicion/con1:fechaRegistro)
                                            then <con1:fechaRegistro>{fn:data($Condicion/con1:fechaRegistro)}</con1:fechaRegistro>
                                            else ()
                                        }
                                        {
                                            if ($Condicion/con1:estado)
                                            then <con1:estado>{fn:data($Condicion/con1:estado)}</con1:estado>
                                            else ()
                                        }
                                        {
                                            if ($Condicion/con1:condicionEnmendada)
                                            then <con1:condicionEnmendada>{fn:data($Condicion/con1:condicionEnmendada)}</con1:condicionEnmendada>
                                            else ()
                                        }
                                        {
                                            if ($Condicion/con1:fechaEnmienda)
                                            then <con1:fechaEnmienda>{fn:data($Condicion/con1:fechaEnmienda)}</con1:fechaEnmienda>
                                            else ()
                                        }
                                        {
                                            for $configAtributo in $Condicion/con1:configAtributo
                                            return 
                                            <con1:configAtributo>
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
                                            </con1:configAtributo>
                                        }
                                        {
                                            if ($Condicion/con1:evidencia)
                                            then 
                                                <con1:evidencia>
                                                    {
                                                        for $Documento in $Condicion/con1:evidencia/doc:Documento
                                                        return 
                                                        <doc:Documento>
                                                            {
                                                                if ($Documento/doc:idDocumento)
                                                                then <doc:idDocumento>{fn:data($Documento/doc:idDocumento)}</doc:idDocumento>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:idTipoDocumento)
                                                                then <doc:idTipoDocumento>{fn:data($Documento/doc:idTipoDocumento)}</doc:idTipoDocumento>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:idTipoDocumentoExterno)
                                                                then <doc:idTipoDocumentoExterno>{fn:data($Documento/doc:idTipoDocumentoExterno)}</doc:idTipoDocumentoExterno>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:nombreTipoDocumento)
                                                                then <doc:nombreTipoDocumento>{fn:data($Documento/doc:nombreTipoDocumento)}</doc:nombreTipoDocumento>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:etapa)
                                                                then <doc:etapa>{fn:data($Documento/doc:etapa)}</doc:etapa>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:codigoDocumento)
                                                                then <doc:codigoDocumento>{fn:data($Documento/doc:codigoDocumento)}</doc:codigoDocumento>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:idExterno)
                                                                then <doc:idExterno>{fn:data($Documento/doc:idExterno)}</doc:idExterno>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:idDocAreaTipo)
                                                                then <doc:idDocAreaTipo>{fn:data($Documento/doc:idDocAreaTipo)}</doc:idDocAreaTipo>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:idOperacion)
                                                                then <doc:idOperacion>{fn:data($Documento/doc:idOperacion)}</doc:idOperacion>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:idProducto)
                                                                then <doc:idProducto>{fn:data($Documento/doc:idProducto)}</doc:idProducto>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:idPais)
                                                                then <doc:idPais>{fn:data($Documento/doc:idPais)}</doc:idPais>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:nombre)
                                                                then <doc:nombre>{fn:data($Documento/doc:nombre)}</doc:nombre>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:filename)
                                                                then <doc:filename>{fn:data($Documento/doc:filename)}</doc:filename>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:mime_type)
                                                                then <doc:mime_type>{fn:data($Documento/doc:mime_type)}</doc:mime_type>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:tamanio)
                                                                then <doc:tamanio>{fn:data($Documento/doc:tamanio)}</doc:tamanio>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:idAdjunto)
                                                                then <doc:idAdjunto>{fn:data($Documento/doc:idAdjunto)}</doc:idAdjunto>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:esJustificacion)
                                                                then <doc:esJustificacion>{fn:data($Documento/doc:esJustificacion)}</doc:esJustificacion>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:comentario)
                                                                then <doc:comentario>{fn:data($Documento/doc:comentario)}</doc:comentario>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:fechaDocumento)
                                                                then <doc:fechaDocumento>{fn:data($Documento/doc:fechaDocumento)}</doc:fechaDocumento>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:fechaRegistro)
                                                                then <doc:fechaRegistro>{fn:data($Documento/doc:fechaRegistro)}</doc:fechaRegistro>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:status)
                                                                then <doc:status>{fn:data($Documento/doc:status)}</doc:status>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:estatusTipoDoc)
                                                                then <doc:estatusTipoDoc>{fn:data($Documento/doc:estatusTipoDoc)}</doc:estatusTipoDoc>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:codExtTipoDoc)
                                                                then <doc:codExtTipoDoc>{fn:data($Documento/doc:codExtTipoDoc)}</doc:codExtTipoDoc>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:tarea)
                                                                then <doc:tarea>{fn:data($Documento/doc:tarea)}</doc:tarea>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:idtarea)
                                                                then <doc:idtarea>{fn:data($Documento/doc:idtarea)}</doc:idtarea>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:content)
                                                                then <doc:content>{fn:data($Documento/doc:content)}</doc:content>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:PuedeModificar)
                                                                then <doc:PuedeModificar>{fn:data($Documento/doc:PuedeModificar)}</doc:PuedeModificar>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:PuedeBorrar)
                                                                then <doc:PuedeBorrar>{fn:data($Documento/doc:PuedeBorrar)}</doc:PuedeBorrar>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:usuarioAgrega)
                                                                then <doc:usuarioAgrega>{fn:data($Documento/doc:usuarioAgrega)}</doc:usuarioAgrega>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:usuarioUltimaActualizacion)
                                                                then <doc:usuarioUltimaActualizacion>{fn:data($Documento/doc:usuarioUltimaActualizacion)}</doc:usuarioUltimaActualizacion>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:accionARealizar)
                                                                then <doc:accionARealizar>{fn:data($Documento/doc:accionARealizar)}</doc:accionARealizar>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Documento/doc:idFlujoNegocio)
                                                                then <doc:idFlujoNegocio>{fn:data($Documento/doc:idFlujoNegocio)}</doc:idFlujoNegocio>
                                                                else ()
                                                            }
                                                        </doc:Documento>
                                                    }
                                                </con1:evidencia>
                                            else ()
                                        }
                                        {
                                            for $observaciones in $Condicion/con1:observaciones
                                            return 
                                            <con1:observaciones>
                                                <con1:id>{fn:data($observaciones/con1:id)}</con1:id>
                                                {
                                                    if ($observaciones/con1:observacion)
                                                    then <con1:observacion>{fn:data($observaciones/con1:observacion)}</con1:observacion>
                                                    else ()
                                                }
                                                {
                                                    if ($observaciones/con1:loginUsuario)
                                                    then <con1:loginUsuario>{fn:data($observaciones/con1:loginUsuario)}</con1:loginUsuario>
                                                    else ()
                                                }
                                                {
                                                    if ($observaciones/con1:nombreUsuario)
                                                    then <con1:nombreUsuario>{fn:data($observaciones/con1:nombreUsuario)}</con1:nombreUsuario>
                                                    else ()
                                                }
                                                {
                                                    if ($observaciones/con1:fechaRegistro)
                                                    then <con1:fechaRegistro>{fn:data($observaciones/con1:fechaRegistro)}</con1:fechaRegistro>
                                                    else ()
                                                }
                                                {
                                                    if ($observaciones/con1:estatus)
                                                    then <con1:estatus>{fn:data($observaciones/con1:estatus)}</con1:estatus>
                                                    else ()
                                                }
                                                {
                                                    if ($observaciones/con1:esPrincipal)
                                                    then <con1:esPrincipal>{fn:data($observaciones/con1:esPrincipal)}</con1:esPrincipal>
                                                    else ()
                                                }
                                                {
                                                    if ($observaciones/con1:tareaBPM)
                                                    then 
                                                        <con1:tareaBPM>
                                                            {
                                                                if ($observaciones/con1:tareaBPM/cat:Id)
                                                                then <cat:Id>{fn:data($observaciones/con1:tareaBPM/cat:Id)}</cat:Id>
                                                                else ()
                                                            }
                                                            {
                                                                if ($observaciones/con1:tareaBPM/cat:Descripcion)
                                                                then <cat:Descripcion>{fn:data($observaciones/con1:tareaBPM/cat:Descripcion)}</cat:Descripcion>
                                                                else ()
                                                            }
                                                            {
                                                                if ($observaciones/con1:tareaBPM/cat:DescripcionCorta)
                                                                then <cat:DescripcionCorta>{fn:data($observaciones/con1:tareaBPM/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                                else ()
                                                            }
                                                            {
                                                                if ($observaciones/con1:tareaBPM/cat:estatus)
                                                                then <cat:estatus>{fn:data($observaciones/con1:tareaBPM/cat:estatus)}</cat:estatus>
                                                                else ()
                                                            }
                                                            {
                                                                if ($observaciones/con1:tareaBPM/cat:codigoExterno)
                                                                then <cat:codigoExterno>{fn:data($observaciones/con1:tareaBPM/cat:codigoExterno)}</cat:codigoExterno>
                                                                else ()
                                                            }
                                                        </con1:tareaBPM>
                                                    else ()
                                                }
                                            </con1:observaciones>
                                        }
                                        {
                                            for $lineaCredito in $Condicion/con1:lineaCredito
                                            return 
                                            <con1:lineaCredito>
                                                {
                                                    if ($lineaCredito/atr:id)
                                                    then <atr:id>{fn:data($lineaCredito/atr:id)}</atr:id>
                                                    else ()
                                                }
                                                {
                                                    if ($lineaCredito/atr:descripcion)
                                                    then <atr:descripcion>{fn:data($lineaCredito/atr:descripcion)}</atr:descripcion>
                                                    else ()
                                                }
                                                {
                                                    if ($lineaCredito/atr:estatus)
                                                    then <atr:estatus>{fn:data($lineaCredito/atr:estatus)}</atr:estatus>
                                                    else ()
                                                }
                                            </con1:lineaCredito>
                                        }
                                        {
                                            for $fuente in $Condicion/con1:fuente
                                            return 
                                            <con1:fuente>
                                                {
                                                    if ($fuente/atr:id)
                                                    then <atr:id>{fn:data($fuente/atr:id)}</atr:id>
                                                    else ()
                                                }
                                                {
                                                    if ($fuente/atr:descripcion)
                                                    then <atr:descripcion>{fn:data($fuente/atr:descripcion)}</atr:descripcion>
                                                    else ()
                                                }
                                                {
                                                    if ($fuente/atr:estatus)
                                                    then <atr:estatus>{fn:data($fuente/atr:estatus)}</atr:estatus>
                                                    else ()
                                                }
                                            </con1:fuente>
                                        }
                                        {
                                            if ($Condicion/con1:Accion)
                                            then <con1:Accion>{fn:data($Condicion/con1:Accion)}</con1:Accion>
                                            else ()
                                        }
                                    </lin:Condicion>
                                }
                                {
                                    for $Termino in $LineaCredito/lin:Termino
                                    return 
                                    <lin:Termino>
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
                                        {
                                            for $configAtributo in $Termino/ter:configAtributo
                                            return 
                                            <ter:configAtributo>
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
                                            </ter:Contraparte>
                                        }
                                    </lin:Termino>
                                }
                                {
                                    for $Comision in $LineaCredito/lin:Comision
                                    return 
                                    <lin:Comision>
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
                                    </lin:Comision>
                                }
                                {
                                    for $Fuente in $LineaCredito/lin:Fuente
                                    return 
                                    <lin:Fuente>
                                        <lin:idFuente>{fn:data($Fuente/lin:idFuente)}</lin:idFuente>
                                        <lin:idLineaCredito>{fn:data($Fuente/lin:idLineaCredito)}</lin:idLineaCredito>
                                        <lin:idLineaPasiva>{fn:data($Fuente/lin:idLineaPasiva)}</lin:idLineaPasiva>
                                        <lin:codigoLineaPasiva>{fn:data($Fuente/lin:codigoLineaPasiva)}</lin:codigoLineaPasiva>
                                        <lin:Descripcion>{fn:data($Fuente/lin:Descripcion)}</lin:Descripcion>
                                        <lin:FechaObtenido>{fn:data($Fuente/lin:FechaObtenido)}</lin:FechaObtenido>
                                        <lin:MontoAsignado>{fn:data($Fuente/lin:MontoAsignado)}</lin:MontoAsignado>
                                        <lin:montoDisponible>{fn:data($Fuente/lin:montoDisponible)}</lin:montoDisponible>
                                        {
                                            for $Monto in $Fuente/lin:Monto
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
                                        <lin:NumeroAsignacion>{fn:data($Fuente/lin:NumeroAsignacion)}</lin:NumeroAsignacion>
                                        <lin:Comentario>{fn:data($Fuente/lin:Comentario)}</lin:Comentario>
                                        <lin:idContrato>{fn:data($Fuente/lin:idContrato)}</lin:idContrato>
                                        <lin:FechaRegistro>{fn:data($Fuente/lin:FechaRegistro)}</lin:FechaRegistro>
                                        <lin:Estado>{fn:data($Fuente/lin:Estado)}</lin:Estado>
                                    </lin:Fuente>
                                }
                                {
                                    for $atributos in $LineaCredito/lin:atributos
                                    return 
                                    <lin:atributos>
                                        <com:name>{fn:data($atributos/com:name)}</com:name>
                                        <com:value>{fn:data($atributos/com:value)}</com:value>
                                    </lin:atributos>
                                }
                            </con:LineaCredito>
                          else()  
                        }
                    </ope1:contrato>
                  else()
                }
            </ope:Operacion>
        }
        <ope:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Exitosa</res:message>
        </ope:Resultado>
    </ope:ConsultarOperacionResponse>
};
 
local:funcTransformation_2($outputVariable.response)
