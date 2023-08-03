xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace cli="http://www.bcie.org/ClienteMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace val="http://xmlns.oracle.com/ValidarMora_BR/ValidarMora_BR_DecisionService_1";
(:: import schema at "../Schemas/ValidarMora_BR_DecisionService_1MessageTypes.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli1 = "http://www.bcie.org/ClienteBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutConsultarClienteFLEXCUBE.response as element() (:: schema-element(cli:ConsultarClienteFLEXCUBEResponse) ::) external;
declare variable $OutValidarMora_BR.payload as element() (:: schema-element(val:callFunctionStatelessDecision) ::) external;
declare variable $OutConsultarClienteById.response as element() (:: schema-element(cli:ConsultarClienteResponse) ::) external;

declare function local:funcValidarmoraresponse($OutConsultarClienteFLEXCUBE.response as element() (:: schema-element(cli:ConsultarClienteFLEXCUBEResponse) ::), 
                                               $OutValidarMora_BR.payload as element() (:: schema-element(val:callFunctionStatelessDecision) ::),
                                               $OutConsultarClienteById.response as element() (:: schema-element(cli:ConsultarClienteResponse) ::)) 
                                               as element() (:: schema-element(cli:ValidarMoraResponse) ::) {
    <cli:ValidarMoraResponse>
        <cli:Cliente>
            <cli1:idCliente>{fn:data($OutConsultarClienteById.response/cli:Cliente/cli1:idCliente)}</cli1:idCliente>
            {
                if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:idFacturador)
                then <cli1:idFacturador>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:idFacturador)}</cli1:idFacturador>
                else ()
            }
            {
                if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:razonSocial)
                then <cli1:razonSocial>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:razonSocial)}</cli1:razonSocial>
                else ()
            }
            {
                if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:abreviatura)
                then <cli1:abreviatura>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:abreviatura)}</cli1:abreviatura>
                else ()
            }
            {
                if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:tipoPersona)
                then 
                    <cli1:tipoPersona>
                        {
                            if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:tipoPersona/cat:Id)
                            then <cat:Id>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:tipoPersona/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:tipoPersona/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:tipoPersona/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:tipoPersona/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:tipoPersona/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:tipoPersona/cat:estatus)
                            then <cat:estatus>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:tipoPersona/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:tipoPersona/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:tipoPersona/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </cli1:tipoPersona>
                else ()
            }
            {
                if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:tipoCliente)
                then <cli1:tipoCliente>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:tipoCliente)}</cli1:tipoCliente>
                else ()
            }
            {
                if ($OutConsultarClienteById.response/cli:Cliente/cli1:sector)
                then 
                    <cli1:sector>
                        {
                            if ($OutConsultarClienteById.response/cli:Cliente/cli1:sector/cat:Id)
                            then <cat:Id>{fn:data($OutConsultarClienteById.response/cli:Cliente/cli1:sector/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($OutConsultarClienteById.response/cli:Cliente/cli1:sector/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($OutConsultarClienteById.response/cli:Cliente/cli1:sector/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($OutConsultarClienteById.response/cli:Cliente/cli1:sector/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($OutConsultarClienteById.response/cli:Cliente/cli1:sector/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($OutConsultarClienteById.response/cli:Cliente/cli1:sector/cat:estatus)
                            then <cat:estatus>{fn:data($OutConsultarClienteById.response/cli:Cliente/cli1:sector/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($OutConsultarClienteById.response/cli:Cliente/cli1:sector/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($OutConsultarClienteById.response/cli:Cliente/cli1:sector/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </cli1:sector>
                else ()
            }
            
           
            
            
           
            {
                if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:tipoInstitucion)
                then 
                    <cli1:tipoInstitucion>
                        {
                            if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:tipoInstitucion/cat:Id)
                            then <cat:Id>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:tipoInstitucion/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:tipoInstitucion/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:tipoInstitucion/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:tipoInstitucion/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:tipoInstitucion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:tipoInstitucion/cat:estatus)
                            then <cat:estatus>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:tipoInstitucion/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:tipoInstitucion/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:tipoInstitucion/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </cli1:tipoInstitucion>
                else ()
            }
            {
                if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:pais)
                then 
                    <cli1:pais>
                        {
                            if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:pais/cat:Id)
                            then <cat:Id>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:pais/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:pais/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:pais/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:pais/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:pais/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:pais/cat:estatus)
                            then <cat:estatus>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:pais/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:pais/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:pais/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </cli1:pais>
                else ()
            }
            {
                if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:grupoEconomico)
                then 
                    <cli1:grupoEconomico>
                        {
                            if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:grupoEconomico/cat:Id)
                            then <cat:Id>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:grupoEconomico/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:grupoEconomico/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:grupoEconomico/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:grupoEconomico/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:grupoEconomico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:grupoEconomico/cat:estatus)
                            then <cat:estatus>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:grupoEconomico/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:grupoEconomico/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:grupoEconomico/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </cli1:grupoEconomico>
                else ()
            }
            {
                if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:tipoIdentificacion)
                then <cli1:tipoIdentificacion>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:tipoIdentificacion)}</cli1:tipoIdentificacion>
                else ()
            }
            {
                if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:numeroIdentificacion)
                then <cli1:numeroIdentificacion>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:numeroIdentificacion)}</cli1:numeroIdentificacion>
                else ()
            }
            {
                if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:direccion)
                then <cli1:direccion>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:direccion)}</cli1:direccion>
                else ()
            }
            {
                if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:telefono)
                then <cli1:telefono>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:telefono)}</cli1:telefono>
                else ()
            }
            {
                if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:fax)
                then <cli1:fax>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:fax)}</cli1:fax>
                else ()
            }
            {
                if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:email)
                then <cli1:email>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:email)}</cli1:email>
                else ()
            }
            {
                if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:ciudad)
                then <cli1:ciudad>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:ciudad)}</cli1:ciudad>
                else ()
            }
            {
                if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:oficina)
                then 
                    <cli1:oficina>
                        {
                            if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:oficina/cat:Id)
                            then <cat:Id>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:oficina/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:oficina/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:oficina/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:oficina/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:oficina/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:oficina/cat:estatus)
                            then <cat:estatus>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:oficina/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:oficina/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:oficina/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </cli1:oficina>
                else ()
            }
            {
                if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:grupoEmpresarial)
                then <cli1:grupoEmpresarial>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:grupoEmpresarial)}</cli1:grupoEmpresarial>
                else ()
            }
            {
                if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:fechaRegistro)
                then <cli1:fechaRegistro>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:fechaRegistro)}</cli1:fechaRegistro>
                else ()
            }
            {
                if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:fechaAprobacion)
                then <cli1:fechaAprobacion>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:fechaAprobacion)}</cli1:fechaAprobacion>
                else ()
            }
            {
                if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:ejecutivo)
                then <cli1:ejecutivo>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:ejecutivo)}</cli1:ejecutivo>
                else ()
            }
            {
                for $Contactos in $OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:Contactos
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
                        </cli1:Contacto>
                    }
                </cli1:Contactos>
            }
            <cli1:InformacionCorrecta>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:InformacionCorrecta)}</cli1:InformacionCorrecta>
            <cli1:ActualizacionPermitida>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:ActualizacionPermitida)}</cli1:ActualizacionPermitida>
            {
                if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:comentarioAprobacion)
                then <cli1:comentarioAprobacion>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:comentarioAprobacion)}</cli1:comentarioAprobacion>
                else ()
            }
            {
                if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:revisadoAprobacion)
                then <cli1:revisadoAprobacion>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:revisadoAprobacion)}</cli1:revisadoAprobacion>
                else ()
            }
            {
                if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:status)
                then <cli1:status>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:status)}</cli1:status>
                else ()
            }
            {
                if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:fechaBaja)
                then <cli1:fechaBaja>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:fechaBaja)}</cli1:fechaBaja>
                else ()
            }
            {
                if ($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:diaPago)
                then <cli1:diaPago>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:diaPago)}</cli1:diaPago>
                else ()
            }
            
            {
              if($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:mora/cli1:dias)then 
                  if($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:mora/cli1:dias <= 0)then 
                    <cli1:enMora>false</cli1:enMora>
                  else
                    <cli1:enMora>true</cli1:enMora>
              else ()
            }
            
            {
                if ($OutValidarMora_BR.payload/val:resultList/cli:ValidarMoraBRResponse/cli:isMora and xs:double($OutValidarMora_BR.payload/val:resultList/cli:ValidarMoraBRResponse/cli:isMora) != xs:double(0))
                then 
                    <cli1:mora>
                        <cli1:dias>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:mora/cli1:dias)}</cli1:dias>
                        <cli1:monto>{fn:data($OutConsultarClienteFLEXCUBE.response/cli:Cliente/cli1:mora/cli1:monto)}</cli1:monto>
                        <cli1:tipo>
                        {
                            if($OutValidarMora_BR.payload/val:resultList/cli:ValidarMoraBRResponse/cli:isMora = 1)then
                                    <cat:Descripcion>TECNICA</cat:Descripcion>
                            else if($OutValidarMora_BR.payload/val:resultList/cli:ValidarMoraBRResponse/cli:isMora = 2)then
                                    <cat:Descripcion>NORMAL</cat:Descripcion>
                            else if($OutValidarMora_BR.payload/val:resultList/cli:ValidarMoraBRResponse/cli:isMora = 3)then
                                    <cat:Descripcion>SECTOR PUBLICO</cat:Descripcion>
                            else
                                    ()
                        }
                        </cli1:tipo>
                    </cli1:mora>
                else ()
            }
        </cli:Cliente>
        <cli:Resultado>
            <res:result>OK</res:result>
            <res:message>Operación Exitosa</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </cli:Resultado>
    </cli:ValidarMoraResponse>
};

local:funcValidarmoraresponse($OutConsultarClienteFLEXCUBE.response, $OutValidarMora_BR.payload,$OutConsultarClienteById.response)
