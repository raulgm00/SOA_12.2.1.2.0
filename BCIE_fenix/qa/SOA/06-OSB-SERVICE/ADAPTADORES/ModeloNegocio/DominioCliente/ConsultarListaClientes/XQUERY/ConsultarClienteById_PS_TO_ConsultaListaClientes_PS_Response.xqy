xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarListaClientesResponse as element() (:: schema-element(ns1:ConsultarListaClientesResponse) ::) external;

declare function local:func($ConsultarListaClientesResponse as element() (:: schema-element(ns1:ConsultarListaClientesResponse) ::)) as element() (:: schema-element(ns1:ConsultarListaClientesResponse) ::) {
    <ns1:ConsultarListaClientesResponse>
        {
            for $Cliente in $ConsultarListaClientesResponse/ns1:Cliente
            return 
            <ns1:Cliente>
                <cli:idCliente>{fn:data($Cliente/cli:idCliente)}</cli:idCliente>
                {
                    if ($Cliente/cli:idFacturador)
                    then <cli:idFacturador>{fn:data($Cliente/cli:idFacturador)}</cli:idFacturador>
                    else ()
                }
                {
                    if ($Cliente/cli:razonSocial)
                    then <cli:razonSocial>{fn:data($Cliente/cli:razonSocial)}</cli:razonSocial>
                    else ()
                }
                {
                    if ($Cliente/cli:abreviatura)
                    then <cli:abreviatura>{fn:data($Cliente/cli:abreviatura)}</cli:abreviatura>
                    else ()
                }
                {
                    if ($Cliente/cli:tipoPersona)
                    then 
                        <cli:tipoPersona>
                            {
                                if ($Cliente/cli:tipoPersona/cat:Id)
                                then <cat:Id>{fn:data($Cliente/cli:tipoPersona/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Cliente/cli:tipoPersona/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Cliente/cli:tipoPersona/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Cliente/cli:tipoPersona/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Cliente/cli:tipoPersona/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Cliente/cli:tipoPersona/cat:estatus)
                                then <cat:estatus>{fn:data($Cliente/cli:tipoPersona/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Cliente/cli:tipoPersona/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Cliente/cli:tipoPersona/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </cli:tipoPersona>
                    else ()
                }
                {
                    if ($Cliente/cli:tipoCliente)
                    then 
                        <cli:tipoCliente>
                            {
                                if ($Cliente/cli:tipoCliente/cat:Id)
                                then <cat:Id>{fn:data($Cliente/cli:tipoCliente/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Cliente/cli:tipoCliente/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Cliente/cli:tipoCliente/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Cliente/cli:tipoCliente/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Cliente/cli:tipoCliente/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Cliente/cli:tipoCliente/cat:estatus)
                                then <cat:estatus>{fn:data($Cliente/cli:tipoCliente/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Cliente/cli:tipoCliente/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Cliente/cli:tipoCliente/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </cli:tipoCliente>
                    else ()
                }
                {
                    if ($Cliente/cli:sector)
                    then 
                        <cli:sector>
                            {
                                if ($Cliente/cli:sector/cat:Id)
                                then <cat:Id>{fn:data($Cliente/cli:sector/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Cliente/cli:sector/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Cliente/cli:sector/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Cliente/cli:sector/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Cliente/cli:sector/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Cliente/cli:sector/cat:estatus)
                                then <cat:estatus>{fn:data($Cliente/cli:sector/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Cliente/cli:sector/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Cliente/cli:sector/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </cli:sector>
                    else ()
                }
                {
                    if ($Cliente/cli:tipoInstitucion)
                    then 
                        <cli:tipoInstitucion>
                            {
                                if ($Cliente/cli:tipoInstitucion/cat:Id)
                                then <cat:Id>{fn:data($Cliente/cli:tipoInstitucion/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Cliente/cli:tipoInstitucion/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Cliente/cli:tipoInstitucion/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Cliente/cli:tipoInstitucion/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Cliente/cli:tipoInstitucion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Cliente/cli:tipoInstitucion/cat:estatus)
                                then <cat:estatus>{fn:data($Cliente/cli:tipoInstitucion/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Cliente/cli:tipoInstitucion/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Cliente/cli:tipoInstitucion/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </cli:tipoInstitucion>
                    else ()
                }
                {
                    if ($Cliente/cli:pais)
                    then 
                        <cli:pais>
                            {
                                if ($Cliente/cli:pais/cat:Id)
                                then <cat:Id>{fn:data($Cliente/cli:pais/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Cliente/cli:pais/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Cliente/cli:pais/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Cliente/cli:pais/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Cliente/cli:pais/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Cliente/cli:pais/cat:estatus)
                                then <cat:estatus>{fn:data($Cliente/cli:pais/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Cliente/cli:pais/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Cliente/cli:pais/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </cli:pais>
                    else ()
                }
                {
                    if ($Cliente/cli:grupoEconomico)
                    then 
                        <cli:grupoEconomico>
                            {
                                if ($Cliente/cli:grupoEconomico/cat:Id)
                                then <cat:Id>{fn:data($Cliente/cli:grupoEconomico/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Cliente/cli:grupoEconomico/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Cliente/cli:grupoEconomico/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Cliente/cli:grupoEconomico/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Cliente/cli:grupoEconomico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Cliente/cli:grupoEconomico/cat:estatus)
                                then <cat:estatus>{fn:data($Cliente/cli:grupoEconomico/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Cliente/cli:grupoEconomico/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Cliente/cli:grupoEconomico/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </cli:grupoEconomico>
                    else ()
                }
                {
                    if ($Cliente/cli:tipoIdentificacion)
                    then <cli:tipoIdentificacion>{fn:data($Cliente/cli:tipoIdentificacion)}</cli:tipoIdentificacion>
                    else ()
                }
                {
                    if ($Cliente/cli:numeroIdentificacion)
                    then <cli:numeroIdentificacion>{fn:data($Cliente/cli:numeroIdentificacion)}</cli:numeroIdentificacion>
                    else ()
                }
                {
                    if ($Cliente/cli:direccion)
                    then <cli:direccion>{fn:data($Cliente/cli:direccion)}</cli:direccion>
                    else ()
                }
                {
                    if ($Cliente/cli:telefono)
                    then <cli:telefono>{fn:data($Cliente/cli:telefono)}</cli:telefono>
                    else ()
                }
                {
                    if ($Cliente/cli:fax)
                    then <cli:fax>{fn:data($Cliente/cli:fax)}</cli:fax>
                    else ()
                }
                {
                    if ($Cliente/cli:email)
                    then <cli:email>{fn:data($Cliente/cli:email)}</cli:email>
                    else ()
                }
                {
                    if ($Cliente/cli:ciudad)
                    then <cli:ciudad>{fn:data($Cliente/cli:ciudad)}</cli:ciudad>
                    else ()
                }
                {
                    if ($Cliente/cli:oficina)
                    then 
                        <cli:oficina>
                            {
                                if ($Cliente/cli:oficina/cat:Id)
                                then <cat:Id>{fn:data($Cliente/cli:oficina/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Cliente/cli:oficina/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Cliente/cli:oficina/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Cliente/cli:oficina/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Cliente/cli:oficina/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Cliente/cli:oficina/cat:estatus)
                                then <cat:estatus>{fn:data($Cliente/cli:oficina/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Cliente/cli:oficina/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Cliente/cli:oficina/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </cli:oficina>
                    else ()
                }
                {
                    if ($Cliente/cli:grupoEmpresarial)
                    then <cli:grupoEmpresarial>{fn:data($Cliente/cli:grupoEmpresarial)}</cli:grupoEmpresarial>
                    else ()
                }
                {
                    if ($Cliente/cli:fechaRegistro)
                    then <cli:fechaRegistro>{fn:data($Cliente/cli:fechaRegistro)}</cli:fechaRegistro>
                    else ()
                }
                {
                    if ($Cliente/cli:fechaAprobacion)
                    then <cli:fechaAprobacion>{fn:data($Cliente/cli:fechaAprobacion)}</cli:fechaAprobacion>
                    else ()
                }
                {
                    if ($Cliente/cli:ejecutivo)
                    then <cli:ejecutivo>{fn:data($Cliente/cli:ejecutivo)}</cli:ejecutivo>
                    else ()
                }
                {
                    for $Contactos in $Cliente/cli:Contactos
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
                <cli:InformacionCorrecta>{fn:data($Cliente/cli:InformacionCorrecta)}</cli:InformacionCorrecta>
                <cli:ActualizacionPermitida>{fn:data($Cliente/cli:ActualizacionPermitida)}</cli:ActualizacionPermitida>
                {
                    if ($Cliente/cli:comentarioAprobacion)
                    then <cli:comentarioAprobacion>{fn:data($Cliente/cli:comentarioAprobacion)}</cli:comentarioAprobacion>
                    else ()
                }
                {
                    if ($Cliente/cli:revisadoAprobacion)
                    then <cli:revisadoAprobacion>{fn:data($Cliente/cli:revisadoAprobacion)}</cli:revisadoAprobacion>
                    else ()
                }
                {
                    if ($Cliente/cli:status)
                    then <cli:status>{fn:data($Cliente/cli:status)}</cli:status>
                    else ()
                }
                {
                    if ($Cliente/cli:fechaBaja)
                    then <cli:fechaBaja>{fn:data($Cliente/cli:fechaBaja)}</cli:fechaBaja>
                    else ()
                }
                {
                    if ($Cliente/cli:diaPago)
                    then <cli:diaPago>{fn:data($Cliente/cli:diaPago)}</cli:diaPago>
                    else ()
                }
                {
                    if ($Cliente/cli:SRC)
                    then 
                        <cli:SRC>
                            {
                                if ($Cliente/cli:SRC/cat:Id)
                                then <cat:Id>{fn:data($Cliente/cli:SRC/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Cliente/cli:SRC/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Cliente/cli:SRC/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Cliente/cli:SRC/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Cliente/cli:SRC/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Cliente/cli:SRC/cat:estatus)
                                then <cat:estatus>{fn:data($Cliente/cli:SRC/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Cliente/cli:SRC/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Cliente/cli:SRC/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </cli:SRC>
                    else ()
                }
                {
                    if ($Cliente/cli:perspectiva)
                    then 
                        <cli:perspectiva>
                            {
                                if ($Cliente/cli:perspectiva/cat:Id)
                                then <cat:Id>{fn:data($Cliente/cli:perspectiva/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Cliente/cli:perspectiva/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Cliente/cli:perspectiva/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Cliente/cli:perspectiva/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Cliente/cli:perspectiva/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Cliente/cli:perspectiva/cat:estatus)
                                then <cat:estatus>{fn:data($Cliente/cli:perspectiva/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Cliente/cli:perspectiva/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Cliente/cli:perspectiva/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </cli:perspectiva>
                    else ()
                }
                {
                    if ($Cliente/cli:enMora)
                    then <cli:enMora>{fn:data($Cliente/cli:enMora)}</cli:enMora>
                    else ()
                }
                {
                    if ($Cliente/cli:mora)
                    then 
                        <cli:mora>
                            <cli:dias>{fn:data($Cliente/cli:mora/cli:dias)}</cli:dias>
                            <cli:monto>{fn:data($Cliente/cli:mora/cli:monto)}</cli:monto>
                            {
                                if ($Cliente/cli:mora/cli:tipo)
                                then 
                                    <cli:tipo>
                                        {
                                            if ($Cliente/cli:mora/cli:tipo/cat:Id)
                                            then <cat:Id>{fn:data($Cliente/cli:mora/cli:tipo/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Cliente/cli:mora/cli:tipo/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Cliente/cli:mora/cli:tipo/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Cliente/cli:mora/cli:tipo/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Cliente/cli:mora/cli:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Cliente/cli:mora/cli:tipo/cat:estatus)
                                            then <cat:estatus>{fn:data($Cliente/cli:mora/cli:tipo/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Cliente/cli:mora/cli:tipo/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Cliente/cli:mora/cli:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </cli:tipo>
                                else ()
                            }
                        </cli:mora>
                    else ()
                }
                {
                    if ($Cliente/cli:deteriorado)
                    then <cli:deteriorado>{fn:data($Cliente/cli:deteriorado)}</cli:deteriorado>
                    else ()
                }
                {
                    if ($Cliente/cli:reserva)
                    then 
                        <cli:reserva>
                            <cli:importeReserva>{fn:data($Cliente/cli:reserva/cli:importeReserva)}</cli:importeReserva>
                            <cli:tipoReserva>{fn:data($Cliente/cli:reserva/cli:tipoReserva)}</cli:tipoReserva>
                        </cli:reserva>
                    else ()
                }
                {
                    if ($Cliente/cli:responsableCliente)
                    then <cli:responsableCliente>{fn:data($Cliente/cli:responsableCliente)}</cli:responsableCliente>
                    else ()
                }
                {
                    if ($Cliente/cli:requiereEnvioAutomatico)
                    then <cli:requiereEnvioAutomatico>{fn:data($Cliente/cli:requiereEnvioAutomatico)}</cli:requiereEnvioAutomatico>
                    else ()
                }
            </ns1:Cliente>
        }

        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Exitosa</res:message>
        </ns1:Resultado>
    </ns1:ConsultarListaClientesResponse>
};

local:func($ConsultarListaClientesResponse)
