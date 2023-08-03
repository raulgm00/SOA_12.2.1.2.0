xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace cli="http://www.bcie.org/ClienteMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli1 = "http://www.bcie.org/ClienteBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $outConsultarClienteById.response as element() (:: schema-element(cli:ConsultarClienteResponse) ::) external;
declare variable $outValidarMora.response as element() (:: schema-element(cli:ValidarMoraResponse) ::) external;
declare variable $outValidarReserva.response as element() (:: schema-element(cli:ValidarReservaResponse) ::) external;
                                                            
declare function local:funcXq_consultarclientebyid_response($outConsultarClienteById.response as element() (:: schema-element(cli:ConsultarClienteResponse) ::), 
                                            $outValidarMora.response as element() (:: schema-element(cli:ValidarMoraResponse) ::), 
                                            $outValidarReserva.response as element() (:: schema-element(cli:ValidarReservaResponse) ::)) 
                                            as element() (:: schema-element(cli:ConsultarClienteResponse) ::) {
    <cli:ConsultarClienteResponse>
        {
            for $Cliente in $outConsultarClienteById.response/cli:Cliente
            return 
            <cli:Cliente>
                <cli1:idCliente>{fn:data($Cliente/cli1:idCliente)}</cli1:idCliente>
                {
                    if ($Cliente/cli1:idFacturador)
                    then <cli1:idFacturador>{fn:data($Cliente/cli1:idFacturador)}</cli1:idFacturador>
                    else ()
                }
                {
                    if ($Cliente/cli1:razonSocial)
                    then <cli1:razonSocial>{fn:data($Cliente/cli1:razonSocial)}</cli1:razonSocial>
                    else ()
                }
                {
                    if ($Cliente/cli1:bicCode)
                    then <cli1:bicCode>{fn:data($Cliente/cli1:bicCode)}</cli1:bicCode>
                    else ()
                }
                {
                    if ($Cliente/cli1:abreviatura)
                    then <cli1:abreviatura>{fn:data($Cliente/cli1:abreviatura)}</cli1:abreviatura>
                    else ()
                }
                {
                    if ($Cliente/cli1:tipoPersona)
                    then 
                        <cli1:tipoPersona>
                            {
                                if ($Cliente/cli1:tipoPersona/cat:Id)
                                then <cat:Id>{fn:data($Cliente/cli1:tipoPersona/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:tipoPersona/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Cliente/cli1:tipoPersona/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:tipoPersona/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Cliente/cli1:tipoPersona/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:tipoPersona/cat:estatus)
                                then <cat:estatus>{fn:data($Cliente/cli1:tipoPersona/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:tipoPersona/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Cliente/cli1:tipoPersona/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </cli1:tipoPersona>
                    else ()
                }
                {
                    if ($Cliente/cli1:tipoCliente)
                    then <cli1:tipoCliente>{$Cliente/cli1:tipoCliente/*}</cli1:tipoCliente>
                    else ()
                }
                {
                    if ($Cliente/cli1:sector)
                    then 
                        <cli1:sector>
                            {
                                if ($Cliente/cli1:sector/cat:Id)
                                then <cat:Id>{fn:data($Cliente/cli1:sector/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:sector/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Cliente/cli1:sector/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:sector/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Cliente/cli1:sector/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:sector/cat:estatus)
                                then <cat:estatus>{fn:data($Cliente/cli1:sector/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:sector/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Cliente/cli1:sector/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </cli1:sector>
                    else ()
                }
                {
                    if ($Cliente/cli1:tipoInstitucion)
                    then 
                        <cli1:tipoInstitucion>
                            {
                                if ($Cliente/cli1:tipoInstitucion/cat:Id)
                                then <cat:Id>{fn:data($Cliente/cli1:tipoInstitucion/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:tipoInstitucion/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Cliente/cli1:tipoInstitucion/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:tipoInstitucion/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Cliente/cli1:tipoInstitucion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:tipoInstitucion/cat:estatus)
                                then <cat:estatus>{fn:data($Cliente/cli1:tipoInstitucion/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:tipoInstitucion/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Cliente/cli1:tipoInstitucion/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </cli1:tipoInstitucion>
                    else ()
                }
                {
                    if ($Cliente/cli1:pais)
                    then 
                        <cli1:pais>
                            {
                                if ($Cliente/cli1:pais/cat:Id)
                                then <cat:Id>{fn:data($Cliente/cli1:pais/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:pais/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Cliente/cli1:pais/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:pais/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Cliente/cli1:pais/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:pais/cat:estatus)
                                then <cat:estatus>{fn:data($Cliente/cli1:pais/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:pais/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Cliente/cli1:pais/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </cli1:pais>
                    else ()
                }
                {
                    if ($Cliente/cli1:grupoEconomico)
                    then 
                        <cli1:grupoEconomico>
                            {
                                if ($Cliente/cli1:grupoEconomico/cat:Id)
                                then <cat:Id>{fn:data($Cliente/cli1:grupoEconomico/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:grupoEconomico/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Cliente/cli1:grupoEconomico/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:grupoEconomico/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Cliente/cli1:grupoEconomico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:grupoEconomico/cat:estatus)
                                then <cat:estatus>{fn:data($Cliente/cli1:grupoEconomico/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:grupoEconomico/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Cliente/cli1:grupoEconomico/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </cli1:grupoEconomico>
                    else ()
                }
                {
                    if ($Cliente/cli1:tipoIdentificacion)
                    then <cli1:tipoIdentificacion>{fn:data($Cliente/cli1:tipoIdentificacion)}</cli1:tipoIdentificacion>
                    else ()
                }
                {
                    if ($Cliente/cli1:numeroIdentificacion)
                    then <cli1:numeroIdentificacion>{fn:data($Cliente/cli1:numeroIdentificacion)}</cli1:numeroIdentificacion>
                    else ()
                }
                {
                    if ($Cliente/cli1:direccion)
                    then <cli1:direccion>{fn:data($Cliente/cli1:direccion)}</cli1:direccion>
                    else ()
                }
                {
                    if ($Cliente/cli1:telefono)
                    then <cli1:telefono>{fn:data($Cliente/cli1:telefono)}</cli1:telefono>
                    else ()
                }
                {
                    if ($Cliente/cli1:fax)
                    then <cli1:fax>{fn:data($Cliente/cli1:fax)}</cli1:fax>
                    else ()
                }
                {
                    if ($Cliente/cli1:email)
                    then <cli1:email>{fn:data($Cliente/cli1:email)}</cli1:email>
                    else ()
                }
                {
                    if ($Cliente/cli1:ciudad)
                    then <cli1:ciudad>{fn:data($Cliente/cli1:ciudad)}</cli1:ciudad>
                    else ()
                }
                {
                    if ($Cliente/cli1:oficina)
                    then 
                        <cli1:oficina>
                            {
                                if ($Cliente/cli1:oficina/cat:Id)
                                then <cat:Id>{fn:data($Cliente/cli1:oficina/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:oficina/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Cliente/cli1:oficina/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:oficina/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Cliente/cli1:oficina/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:oficina/cat:estatus)
                                then <cat:estatus>{fn:data($Cliente/cli1:oficina/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:oficina/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Cliente/cli1:oficina/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </cli1:oficina>
                    else ()
                }
                {
                    if ($Cliente/cli1:grupoEmpresarial)
                    then <cli1:grupoEmpresarial>{fn:data($Cliente/cli1:grupoEmpresarial)}</cli1:grupoEmpresarial>
                    else ()
                }
                {
                    if ($Cliente/cli1:fechaRegistro)
                    then <cli1:fechaRegistro>{fn:data($Cliente/cli1:fechaRegistro)}</cli1:fechaRegistro>
                    else ()
                }
                {
                    if ($Cliente/cli1:fechaAprobacion)
                    then <cli1:fechaAprobacion>{fn:data($Cliente/cli1:fechaAprobacion)}</cli1:fechaAprobacion>
                    else ()
                }
                {
                    if ($Cliente/cli1:ejecutivo)
                    then <cli1:ejecutivo>{fn:data($Cliente/cli1:ejecutivo)}</cli1:ejecutivo>
                    else ()
                }
                {
                    for $Contactos in $Cliente/cli1:Contactos
                    return 
                    <cli1:Contactos>
                        {
                            for $Contacto in $Contactos/cli1:Contacto
                            return 
                            <cli1:Contacto>
                                <cli1:idContacto>{fn:data($Contacto/cli1:idContacto)}</cli1:idContacto>
                                <cli1:idCliente>{fn:data($Contacto/cli1:idCliente)}</cli1:idCliente>
                                <cli1:nombre>{fn:data($Contacto/cli1:nombre)}</cli1:nombre>
                                <cli1:telefono>{fn:data($Contacto/cli1:telefono)}</cli1:telefono>
                                <cli1:correoElectronico>{fn:data($Contacto/cli1:correoElectronico)}</cli1:correoElectronico>
                                <cli1:cargo>{fn:data($Contacto/cli1:cargo)}</cli1:cargo>
                                <cli1:fechaRegistro>{fn:data($Contacto/cli1:fechaRegistro)}</cli1:fechaRegistro>
                                <cli1:idContactosClientes>{fn:data($Contacto/cli1:idContactosClientes)}</cli1:idContactosClientes>
                                <cli1:recibeAvisoCobro>{fn:data($Contacto/cli1:recibeAvisoCobro)}</cli1:recibeAvisoCobro>
                            </cli1:Contacto>
                        }
                    </cli1:Contactos>
                }
                <cli1:InformacionCorrecta>{fn:data($Cliente/cli1:InformacionCorrecta)}</cli1:InformacionCorrecta>
                <cli1:ActualizacionPermitida>{fn:data($Cliente/cli1:ActualizacionPermitida)}</cli1:ActualizacionPermitida>
                {
                    if ($Cliente/cli1:comentarioAprobacion)
                    then <cli1:comentarioAprobacion>{fn:data($Cliente/cli1:comentarioAprobacion)}</cli1:comentarioAprobacion>
                    else ()
                }
                {
                    if ($Cliente/cli1:revisadoAprobacion)
                    then <cli1:revisadoAprobacion>{fn:data($Cliente/cli1:revisadoAprobacion)}</cli1:revisadoAprobacion>
                    else ()
                }
                {
                    if ($Cliente/cli1:status)
                    then <cli1:status>{fn:data($Cliente/cli1:status)}</cli1:status>
                    else ()
                }
                {
                    if ($Cliente/cli1:fechaBaja)
                    then <cli1:fechaBaja>{fn:data($Cliente/cli1:fechaBaja)}</cli1:fechaBaja>
                    else ()
                }
                {
                    if ($Cliente/cli1:diaPago)
                    then <cli1:diaPago>{fn:data($Cliente/cli1:diaPago)}</cli1:diaPago>
                    else ()
                }
                {
                    if ($Cliente/cli1:SCR)
                    then 
                        <cli1:SCR>{$Cliente/cli1:SCR/*}</cli1:SCR>
                    else ()
                }
                {
                    if ($Cliente/cli1:perspectiva)
                    then 
                        <cli1:perspectiva>
                            {
                                if ($Cliente/cli1:perspectiva/cat:Id)
                                then <cat:Id>{fn:data($Cliente/cli1:perspectiva/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:perspectiva/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Cliente/cli1:perspectiva/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:perspectiva/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Cliente/cli1:perspectiva/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:perspectiva/cat:estatus)
                                then <cat:estatus>{fn:data($Cliente/cli1:perspectiva/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Cliente/cli1:perspectiva/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Cliente/cli1:perspectiva/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </cli1:perspectiva>
                    else ()
                }
                {
                    if ($outValidarMora.response/cli:Cliente/cli1:enMora)then
                        <cli1:enMora>{fn:data($outValidarMora.response/cli:Cliente/cli1:enMora)}</cli1:enMora>
                          else ()
                    }      
                {
                if (fn:count($outValidarMora.response/cli:Cliente/cli1:mora) > 0)then
                        <cli1:mora>
                            <cli1:dias>{fn:data($outValidarMora.response/cli:Cliente/cli1:mora/cli1:dias)}</cli1:dias>
                            <cli1:monto>{fn:data($outValidarMora.response/cli:Cliente/cli1:mora/cli1:monto)}</cli1:monto>
                            <cli1:tipo>
                                <cat:Id></cat:Id>
                                {
                                    if ($outValidarMora.response/cli:Cliente/cli1:mora/cli1:tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($outValidarMora.response/cli:Cliente/cli1:mora/cli1:tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                <cat:DescripcionCorta></cat:DescripcionCorta>
                                <cat:estatus></cat:estatus>
                                <cat:codigoExterno></cat:codigoExterno>
                            </cli1:tipo>
                        </cli1:mora>
                else()        
                }
                {
                    if ($outValidarReserva.response/cli:Cliente/cli1:deteriorado)
                    then <cli1:deteriorado>{fn:data($outValidarReserva.response/cli:Cliente/cli1:deteriorado)}</cli1:deteriorado>
                    else ()
                }
                {
                if (fn:count($outValidarReserva.response/cli:Cliente/cli1:reserva) > 0)then
                  <cli1:reserva>
                      <cli1:importeReserva>{fn:data($outValidarReserva.response/cli:Cliente/cli1:reserva/cli1:importeReserva)}</cli1:importeReserva>
                      <cli1:tipoReserva>{fn:data($outValidarReserva.response/cli:Cliente/cli1:reserva/cli1:tipoReserva)}</cli1:tipoReserva>
                  </cli1:reserva> 
                  
                  else()
                }
                {
                    if ($Cliente/cli1:requiereEnvioAutomatico)
                    then <cli1:requiereEnvioAutomatico>{fn:data($Cliente/cli1:requiereEnvioAutomatico)}</cli1:requiereEnvioAutomatico>
                    else ()
                }

            </cli:Cliente>
        }
        {
            for $Operaciones in $outConsultarClienteById.response/cli:Operaciones
            return 
            <cli:Operaciones>{$Operaciones/*}</cli:Operaciones>
        }
        <cli:Resultado>
            <res:result>OK</res:result>
            <res:message>{
            if (fn:string-length(string($outConsultarClienteById.response/cli:Cliente/cli1:idCliente)) > 0) 
            then("Consulta exitosa")
            else("No existen registros")}</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </cli:Resultado>
    </cli:ConsultarClienteResponse>
};

local:funcXq_consultarclientebyid_response($outConsultarClienteById.response, $outValidarMora.response,$outValidarReserva.response)
