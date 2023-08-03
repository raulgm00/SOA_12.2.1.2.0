xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace com="http://www.bcie.org/ComisionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace apr = "http://www.bcie.org/AprobacionBO";

declare variable $consultarComisionByIdLineaCredito_OutputVariable.response as element() (:: schema-element(com:ConsultarComisionByIdLineaCreditoResponse) ::) external;
declare variable $consultarLineaCreditoByIdLineaCredito_OutputVariable.response as element() (:: schema-element(lin:ConsultarLineaCreditoByIdLineaCreditoResponse) ::) external;

declare function local:funcConsultarcomision_to_crearcommitment($consultarComisionByIdLineaCredito_OutputVariable.response as element() (:: schema-element(com:ConsultarComisionByIdLineaCreditoResponse) ::), 
                                                                $consultarLineaCreditoByIdLineaCredito_OutputVariable.response as element() (:: schema-element(lin:ConsultarLineaCreditoByIdLineaCreditoResponse) ::)) 
                                                                as element() (:: schema-element(lin:CrearCommitmentFLEXCUBERequest) ::) {
    <lin:CrearCommitmentFLEXCUBERequest>
        {
            if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion)
            then 
                <lin:Operacion>
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:idOperacion)
                        then <ope:idOperacion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:idOperacion)}</ope:idOperacion>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:responsable)
                        then <ope:responsable>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:responsable)}</ope:responsable>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:oficina)
                        then <ope:oficina>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:oficina)}</ope:oficina>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:nombre)
                        then <ope:nombre>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:nombre)}</ope:nombre>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente)
                        then 
                            <ope:cliente>
                                <cli:idCliente>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:idCliente)}</cli:idCliente>
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:idFacturador)
                                    then <cli:idFacturador>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:idFacturador)}</cli:idFacturador>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:razonSocial)
                                    then <cli:razonSocial>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:razonSocial)}</cli:razonSocial>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:abreviatura)
                                    then <cli:abreviatura>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:abreviatura)}</cli:abreviatura>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoPersona)
                                    then 
                                        <cli:tipoPersona>
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoPersona/cat:Id)
                                                then <cat:Id>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoPersona/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoPersona/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoPersona/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoPersona/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoPersona/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoPersona/cat:estatus)
                                                then <cat:estatus>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoPersona/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoPersona/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoPersona/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </cli:tipoPersona>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoCliente)
                                    then 
                                        <cli:tipoCliente>
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoCliente/cat:Id)
                                                then <cat:Id>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoCliente/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoCliente/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoCliente/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoCliente/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoCliente/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoCliente/cat:estatus)
                                                then <cat:estatus>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoCliente/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoCliente/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoCliente/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </cli:tipoCliente>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:sector)
                                    then 
                                        <cli:sector>
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:sector/cat:Id)
                                                then <cat:Id>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:sector/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:sector/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:sector/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:sector/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:sector/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:sector/cat:estatus)
                                                then <cat:estatus>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:sector/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:sector/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:sector/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </cli:sector>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoInstitucion)
                                    then 
                                        <cli:tipoInstitucion>
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoInstitucion/cat:Id)
                                                then <cat:Id>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoInstitucion/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoInstitucion/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoInstitucion/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoInstitucion/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoInstitucion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoInstitucion/cat:estatus)
                                                then <cat:estatus>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoInstitucion/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoInstitucion/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoInstitucion/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </cli:tipoInstitucion>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:pais)
                                    then 
                                        <cli:pais>
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:pais/cat:Id)
                                                then <cat:Id>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:pais/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:pais/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:pais/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:pais/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:pais/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:pais/cat:estatus)
                                                then <cat:estatus>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:pais/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:pais/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:pais/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </cli:pais>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:grupoEconomico)
                                    then 
                                        <cli:grupoEconomico>
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:grupoEconomico/cat:Id)
                                                then <cat:Id>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:grupoEconomico/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:grupoEconomico/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:grupoEconomico/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:grupoEconomico/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:grupoEconomico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:grupoEconomico/cat:estatus)
                                                then <cat:estatus>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:grupoEconomico/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:grupoEconomico/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:grupoEconomico/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </cli:grupoEconomico>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoIdentificacion)
                                    then <cli:tipoIdentificacion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:tipoIdentificacion)}</cli:tipoIdentificacion>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:numeroIdentificacion)
                                    then <cli:numeroIdentificacion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:numeroIdentificacion)}</cli:numeroIdentificacion>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:direccion)
                                    then <cli:direccion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:direccion)}</cli:direccion>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:telefono)
                                    then <cli:telefono>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:telefono)}</cli:telefono>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:fax)
                                    then <cli:fax>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:fax)}</cli:fax>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:email)
                                    then <cli:email>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:email)}</cli:email>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:ciudad)
                                    then <cli:ciudad>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:ciudad)}</cli:ciudad>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:oficina)
                                    then 
                                        <cli:oficina>
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:oficina/cat:Id)
                                                then <cat:Id>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:oficina/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:oficina/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:oficina/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:oficina/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:oficina/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:oficina/cat:estatus)
                                                then <cat:estatus>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:oficina/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:oficina/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:oficina/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </cli:oficina>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:grupoEmpresarial)
                                    then <cli:grupoEmpresarial>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:grupoEmpresarial)}</cli:grupoEmpresarial>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:fechaRegistro)
                                    then <cli:fechaRegistro>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:fechaRegistro)}</cli:fechaRegistro>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:fechaAprobacion)
                                    then <cli:fechaAprobacion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:fechaAprobacion)}</cli:fechaAprobacion>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:ejecutivo)
                                    then <cli:ejecutivo>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:ejecutivo)}</cli:ejecutivo>
                                    else ()
                                }
                                {
                                    for $Contactos in $consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:Contactos
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
                                <cli:InformacionCorrecta>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:InformacionCorrecta)}</cli:InformacionCorrecta>
                                <cli:ActualizacionPermitida>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:ActualizacionPermitida)}</cli:ActualizacionPermitida>
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:comentarioAprobacion)
                                    then <cli:comentarioAprobacion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:comentarioAprobacion)}</cli:comentarioAprobacion>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:revisadoAprobacion)
                                    then <cli:revisadoAprobacion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:revisadoAprobacion)}</cli:revisadoAprobacion>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:status)
                                    then <cli:status>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:status)}</cli:status>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:fechaBaja)
                                    then <cli:fechaBaja>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:fechaBaja)}</cli:fechaBaja>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:diaPago)
                                    then <cli:diaPago>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:diaPago)}</cli:diaPago>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:SCR)
                                    then 
                                        <cli:SRC>
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:SCR/cat:Id)
                                                then <cat:Id>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:SCR/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:SCR/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:SCR/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:SCR/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:SCR/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:SCR/cat:estatus)
                                                then <cat:estatus>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:SCR/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:SCR/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:SCR/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </cli:SRC>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:perspectiva)
                                    then 
                                        <cli:perspectiva>
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:perspectiva/cat:Id)
                                                then <cat:Id>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:perspectiva/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:perspectiva/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:perspectiva/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:perspectiva/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:perspectiva/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:perspectiva/cat:estatus)
                                                then <cat:estatus>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:perspectiva/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:perspectiva/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:perspectiva/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </cli:perspectiva>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:enMora)
                                    then <cli:enMora>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:enMora)}</cli:enMora>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:mora)
                                    then 
                                        <cli:mora>
                                            <cli:dias>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:mora/cli:dias)}</cli:dias>
                                            <cli:monto>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:mora/cli:monto)}</cli:monto>
                                        </cli:mora>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:deteriorado)
                                    then <cli:deteriorado>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:deteriorado)}</cli:deteriorado>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:reserva)
                                    then 
                                        <cli:reserva>
                                            <cli:importeReserva>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:reserva/cli:importeReserva)}</cli:importeReserva>
                                            <cli:tipoReserva>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:reserva/cli:tipoReserva)}</cli:tipoReserva>
                                        </cli:reserva>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:responsableCliente)
                                    then <cli:responsableCliente>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cliente/cli:responsableCliente)}</cli:responsableCliente>
                                    else ()
                                }
                            </ope:cliente>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto)
                        then 
                            <ope:producto>
                                <pro:idProducto>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:idProducto)}</pro:idProducto>
                                <pro:descripcion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:descripcion)}</pro:descripcion>
                                <pro:descripcionCorta>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:descripcionCorta)}</pro:descripcionCorta>
                                <pro:fechaRegistro>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:fechaRegistro)}</pro:fechaRegistro>
                                <pro:codExterno>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:codExterno)}</pro:codExterno>
                                <pro:idInstrumentoFinanciero>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:idInstrumentoFinanciero)}</pro:idInstrumentoFinanciero>
                                <pro:idTipoOperacion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:idTipoOperacion)}</pro:idTipoOperacion>
                                <pro:reglas>
                                    <pro:banStatus>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:banStatus)}</pro:banStatus>
                                    <pro:requiereElegibilidad>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:requiereElegibilidad)}</pro:requiereElegibilidad>
                                    <pro:requiereLaft>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:requiereLaft)}</pro:requiereLaft>
                                    <pro:OFICrealizaAnalisisLAFT>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:OFICrealizaAnalisisLAFT)}</pro:OFICrealizaAnalisisLAFT>
                                    <pro:requiereEvaluacionExAnte>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:requiereEvaluacionExAnte)}</pro:requiereEvaluacionExAnte>
                                    <pro:requiereFormularProgramas>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:requiereFormularProgramas)}</pro:requiereFormularProgramas>
                                    <pro:elaboraAnalizarAdquisiciones>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:elaboraAnalizarAdquisiciones)}</pro:elaboraAnalizarAdquisiciones>
                                    <pro:elaboraHojaTerminosPCT>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:elaboraHojaTerminosPCT)}</pro:elaboraHojaTerminosPCT>
                                    <pro:elaboraHojaTerminosSEPRI>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:elaboraHojaTerminosSEPRI)}</pro:elaboraHojaTerminosSEPRI>
                                    <pro:analizarModelo>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:analizarModelo)}</pro:analizarModelo>
                                    <pro:realizarPreanalisis>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:realizarPreanalisis)}</pro:realizarPreanalisis>
                                    <pro:participaSupervision>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:participaSupervision)}</pro:participaSupervision>
                                    <pro:participaSeguimiento>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:participaSeguimiento)}</pro:participaSeguimiento>
                                    <pro:participaFINAM>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:participaFINAM)}</pro:participaFINAM>
                                    <pro:SRC>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:SRC)}</pro:SRC>
                                    <pro:OpinionJuridica>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:OpinionJuridica)}</pro:OpinionJuridica>
                                    <pro:firmaContrato>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:firmaContrato)}</pro:firmaContrato>
                                    <pro:condicionesPreviasFormalizar>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:condicionesPreviasFormalizar)}</pro:condicionesPreviasFormalizar>
                                    <pro:condicionesPreviasDesembolso>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:condicionesPreviasDesembolso)}</pro:condicionesPreviasDesembolso>
                                    <pro:programacionDesembolsos>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:programacionDesembolsos)}</pro:programacionDesembolsos>
                                    <pro:reglaLAFT>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:reglaLAFT)}</pro:reglaLAFT>
                                    <pro:adquisiciones2>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:adquisiciones2)}</pro:adquisiciones2>
                                    <pro:supervicionTecnica>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:supervicionTecnica)}</pro:supervicionTecnica>
                                    <pro:seguimientoCrediticio>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:seguimientoCrediticio)}</pro:seguimientoCrediticio>
                                    <pro:enmiendas>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:enmiendas)}</pro:enmiendas>
                                    <pro:recuperacion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:recuperacion)}</pro:recuperacion>
                                    <pro:cierreOperativo>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:cierreOperativo)}</pro:cierreOperativo>
                                    <pro:rendicionCuentas>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:rendicionCuentas)}</pro:rendicionCuentas>
                                    <pro:evaluacionMedioTermino>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:evaluacionMedioTermino)}</pro:evaluacionMedioTermino>
                                    <pro:evaluacionExPost>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:evaluacionExPost)}</pro:evaluacionExPost>
                                    <pro:requiereAdquisiciones>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:requiereAdquisiciones)}</pro:requiereAdquisiciones>
                                    <pro:requiereRAROC>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:requiereRAROC)}</pro:requiereRAROC>
                                    <pro:requiereIBICIE>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:requiereIBICIE)}</pro:requiereIBICIE>
                                    <pro:requiereSIEMAS>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:requiereSIEMAS)}</pro:requiereSIEMAS>
                                    <pro:requiereGERIESElegibilidad>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:requiereGERIESElegibilidad)}</pro:requiereGERIESElegibilidad>
                                    <pro:requiereOpinionTecnica>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:requiereOpinionTecnica)}</pro:requiereOpinionTecnica>
                                    <pro:requiereAsjurAnalisis>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:requiereAsjurAnalisis)}</pro:requiereAsjurAnalisis>
                                    <pro:requiereAsjurElegibilidad>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:requiereAsjurElegibilidad)}</pro:requiereAsjurElegibilidad>
                                    <pro:requierePreparacion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:requierePreparacion)}</pro:requierePreparacion>
                                    <pro:tieneRiesgoCredito>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:tieneRiesgoCredito)}</pro:tieneRiesgoCredito>
                                    <pro:responsableAnalisis>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:responsableAnalisis)}</pro:responsableAnalisis>
                                    <pro:nombreResponsableAnalisis>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:nombreResponsableAnalisis)}</pro:nombreResponsableAnalisis>
                                    <pro:requiereFirmacontrato>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:requiereFirmacontrato)}</pro:requiereFirmacontrato>
                                    <pro:requiereRegistroLinea>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:requiereRegistroLinea)}</pro:requiereRegistroLinea>
                                    <pro:requiereCore>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:reglas/pro:requiereCore)}</pro:requiereCore>
                                </pro:reglas>
                                <pro:instrumentoFinanciero>
                                    <pro:descripcion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:instrumentoFinanciero/pro:descripcion)}</pro:descripcion>
                                    <pro:descripcionCorta>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:instrumentoFinanciero/pro:descripcionCorta)}</pro:descripcionCorta>
                                    <pro:estado>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:instrumentoFinanciero/pro:estado)}</pro:estado>
                                    <pro:fechaRegistro>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:instrumentoFinanciero/pro:fechaRegistro)}</pro:fechaRegistro>
                                    <pro:codigoExterno>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:instrumentoFinanciero/pro:codigoExterno)}</pro:codigoExterno>
                                </pro:instrumentoFinanciero>
                                <pro:estatus>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:producto/pro:estatus)}</pro:estatus>
                            </ope:producto>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:descripcion)
                        then <ope:descripcion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:descripcion)}</ope:descripcion>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:actividadEconomica)
                        then 
                            <ope:actividadEconomica>
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:actividadEconomica/cat:Id)
                                    then <cat:Id>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:actividadEconomica/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:actividadEconomica/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:actividadEconomica/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:actividadEconomica/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:actividadEconomica/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:actividadEconomica/cat:estatus)
                                    then <cat:estatus>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:actividadEconomica/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:actividadEconomica/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:actividadEconomica/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </ope:actividadEconomica>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:actividadEconomicaAsociada)
                        then 
                            <ope:actividadEconomicaAsociada>
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:actividadEconomicaAsociada/cat:Id)
                                    then <cat:Id>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:actividadEconomicaAsociada/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:actividadEconomicaAsociada/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:actividadEconomicaAsociada/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:actividadEconomicaAsociada/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:actividadEconomicaAsociada/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:actividadEconomicaAsociada/cat:estatus)
                                    then <cat:estatus>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:actividadEconomicaAsociada/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:actividadEconomicaAsociada/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:actividadEconomicaAsociada/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </ope:actividadEconomicaAsociada>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:objetivosEstrategicos)
                        then <ope:objetivosEstrategicos>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:objetivosEstrategicos)}</ope:objetivosEstrategicos>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:areaFocalizacion)
                        then 
                            <ope:areaFocalizacion>
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:areaFocalizacion/cat:Id)
                                    then <cat:Id>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:areaFocalizacion/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:areaFocalizacion/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:areaFocalizacion/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:areaFocalizacion/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:areaFocalizacion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:areaFocalizacion/cat:estatus)
                                    then <cat:estatus>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:areaFocalizacion/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:areaFocalizacion/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:areaFocalizacion/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </ope:areaFocalizacion>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:ejeEstrategico)
                        then 
                            <ope:ejeEstrategico>
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:ejeEstrategico/cat:Id)
                                    then <cat:Id>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:ejeEstrategico/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:ejeEstrategico/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:ejeEstrategico/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:ejeEstrategico/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:ejeEstrategico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:ejeEstrategico/cat:estatus)
                                    then <cat:estatus>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:ejeEstrategico/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:ejeEstrategico/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:ejeEstrategico/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </ope:ejeEstrategico>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:iniciativaEstrategica)
                        then 
                            <ope:iniciativaEstrategica>
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:iniciativaEstrategica/cat:Id)
                                    then <cat:Id>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:iniciativaEstrategica/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:iniciativaEstrategica/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:iniciativaEstrategica/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:iniciativaEstrategica/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:iniciativaEstrategica/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:iniciativaEstrategica/cat:estatus)
                                    then <cat:estatus>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:iniciativaEstrategica/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:iniciativaEstrategica/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:iniciativaEstrategica/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </ope:iniciativaEstrategica>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cantidadPaises)
                        then 
                            <ope:cantidadPaises>
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cantidadPaises/cat:Id)
                                    then <cat:Id>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cantidadPaises/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cantidadPaises/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cantidadPaises/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cantidadPaises/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cantidadPaises/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cantidadPaises/cat:estatus)
                                    then <cat:estatus>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cantidadPaises/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cantidadPaises/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cantidadPaises/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </ope:cantidadPaises>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:sectorMercado)
                        then 
                            <ope:sectorMercado>
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:sectorMercado/cat:Id)
                                    then <cat:Id>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:sectorMercado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:sectorMercado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:sectorMercado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:sectorMercado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:sectorMercado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:sectorMercado/cat:estatus)
                                    then <cat:estatus>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:sectorMercado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:sectorMercado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:sectorMercado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </ope:sectorMercado>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:fechaInicio)
                        then <ope:fechaInicio>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:fechaInicio)}</ope:fechaInicio>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:programadoPOA)
                        then <ope:programadoPOA>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:programadoPOA)}</ope:programadoPOA>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:ejercicioPOA)
                        then 
                            <ope:ejercicioPOA>
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:ejercicioPOA/cat:Id)
                                    then <cat:Id>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:ejercicioPOA/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:ejercicioPOA/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:ejercicioPOA/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:ejercicioPOA/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:ejercicioPOA/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:ejercicioPOA/cat:estatus)
                                    then <cat:estatus>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:ejercicioPOA/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:ejercicioPOA/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:ejercicioPOA/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </ope:ejercicioPOA>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:tipoGarantia)
                        then 
                            <ope:tipoGarantia>
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:tipoGarantia/cat:Id)
                                    then <cat:Id>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:tipoGarantia/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:tipoGarantia/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:tipoGarantia/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:tipoGarantia/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:tipoGarantia/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:tipoGarantia/cat:estatus)
                                    then <cat:estatus>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:tipoGarantia/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:tipoGarantia/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:tipoGarantia/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </ope:tipoGarantia>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:componenteConcesionalidad)
                        then <ope:componenteConcesionalidad>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:componenteConcesionalidad)}</ope:componenteConcesionalidad>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:estructurador)
                        then <ope:estructurador>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:estructurador)}</ope:estructurador>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:beneficiario)
                        then <ope:beneficiario>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:beneficiario)}</ope:beneficiario>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:unidadEjecutora)
                        then <ope:unidadEjecutora>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:unidadEjecutora)}</ope:unidadEjecutora>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:programa)
                        then <ope:programa>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:programa)}</ope:programa>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:asociadas)
                        then 
                            <ope:asociadas>
                                {
                                    for $Operacion in $consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:asociadas/ope:Operacion
                                    return 
                                    <ope:Operacion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:asociadas/ope:Operacion)}</ope:Operacion>
                                }
                            </ope:asociadas>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada)
                        then 
                            <ope:declaracionJurada>
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:idDeclaracion)
                                    then <dec:idDeclaracion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:idDeclaracion)}</dec:idDeclaracion>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:codigoExternoDeclaracion)
                                    then <dec:codigoExternoDeclaracion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:codigoExternoDeclaracion)}</dec:codigoExternoDeclaracion>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:titulo)
                                    then <dec:titulo>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:titulo)}</dec:titulo>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:estadoDeclaracion)
                                    then 
                                        <dec:estadoDeclaracion>
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:estadoDeclaracion/dec:codigoEstadoDeclaracion)
                                                then <dec:codigoEstadoDeclaracion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:estadoDeclaracion/dec:codigoEstadoDeclaracion)}</dec:codigoEstadoDeclaracion>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:estadoDeclaracion/dec:nombreEstadoDeclaracion)
                                                then <dec:nombreEstadoDeclaracion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:estadoDeclaracion/dec:nombreEstadoDeclaracion)}</dec:nombreEstadoDeclaracion>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:estadoDeclaracion/dec:EstadoNoObjecion)
                                                then <dec:EstadoNoObjecion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:estadoDeclaracion/dec:EstadoNoObjecion)}</dec:EstadoNoObjecion>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:estadoDeclaracion/dec:nombreEstadoNoObjecion)
                                                then <dec:nombreEstadoNoObjecion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:estadoDeclaracion/dec:nombreEstadoNoObjecion)}</dec:nombreEstadoNoObjecion>
                                                else ()
                                            }
                                        </dec:estadoDeclaracion>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:estadoBusqueda)
                                    then 
                                        <dec:estadoBusqueda>
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:estadoBusqueda/dec:codigoEstadoBusqueda)
                                                then <dec:codigoEstadoBusqueda>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:estadoBusqueda/dec:codigoEstadoBusqueda)}</dec:codigoEstadoBusqueda>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:estadoBusqueda/dec:nombreEstadoBusqueda)
                                                then <dec:nombreEstadoBusqueda>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:estadoBusqueda/dec:nombreEstadoBusqueda)}</dec:nombreEstadoBusqueda>
                                                else ()
                                            }
                                        </dec:estadoBusqueda>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:calificacionDeRiesgo)
                                    then 
                                        <dec:calificacionDeRiesgo>
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:calificacionDeRiesgo/dec:codigoCalificacionDeRiesgo)
                                                then <dec:codigoCalificacionDeRiesgo>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:calificacionDeRiesgo/dec:codigoCalificacionDeRiesgo)}</dec:codigoCalificacionDeRiesgo>
                                                else ()
                                            }
                                            {
                                                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:calificacionDeRiesgo/dec:nombreCalificacionDeRiesgo)
                                                then <dec:nombreCalificacionDeRiesgo>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:calificacionDeRiesgo/dec:nombreCalificacionDeRiesgo)}</dec:nombreCalificacionDeRiesgo>
                                                else ()
                                            }
                                        </dec:calificacionDeRiesgo>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:fechaRegistro)
                                    then <dec:fechaRegistro>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:fechaRegistro)}</dec:fechaRegistro>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:fechaFirmaDeclaracion)
                                    then <dec:fechaFirmaDeclaracion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:fechaFirmaDeclaracion)}</dec:fechaFirmaDeclaracion>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:fechaVencimiento)
                                    then <dec:fechaVencimiento>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:fechaVencimiento)}</dec:fechaVencimiento>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:comentarioDeclaracion)
                                    then <dec:comentarioDeclaracion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:comentarioDeclaracion)}</dec:comentarioDeclaracion>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:comentarioBusqueda)
                                    then <dec:comentarioBusqueda>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:comentarioBusqueda)}</dec:comentarioBusqueda>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:elevarAOtraInstancia)
                                    then <dec:elevarAOtraInstancia>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:elevarAOtraInstancia)}</dec:elevarAOtraInstancia>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:tipoOrigen)
                                    then <dec:tipoOrigen>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:tipoOrigen)}</dec:tipoOrigen>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:tipoSeguimiento)
                                    then <dec:tipoSeguimiento>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:tipoSeguimiento)}</dec:tipoSeguimiento>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:instanciaProceso)
                                    then <dec:instanciaProceso>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:instanciaProceso)}</dec:instanciaProceso>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:esSoloLectura)
                                    then <dec:esSoloLectura>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:esSoloLectura)}</dec:esSoloLectura>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:status)
                                    then <dec:status>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:declaracionJurada/dec:status)}</dec:status>
                                    else ()
                                }
                            </ope:declaracionJurada>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:masInformacionRiesgo)
                        then <ope:masInformacionRiesgo>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:masInformacionRiesgo)}</ope:masInformacionRiesgo>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:informacionAdicionalRiesgo)
                        then <ope:informacionAdicionalRiesgo>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:informacionAdicionalRiesgo)}</ope:informacionAdicionalRiesgo>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:masInformacionJuridico)
                        then <ope:masInformacionJuridico>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:masInformacionJuridico)}</ope:masInformacionJuridico>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:informacionAdicionalJuridico)
                        then <ope:informacionAdicionalJuridico>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:informacionAdicionalJuridico)}</ope:informacionAdicionalJuridico>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:contrapartesProhibidas)
                        then <ope:contrapartesProhibidas>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:contrapartesProhibidas)}</ope:contrapartesProhibidas>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:comentarioContrapartes)
                        then <ope:comentarioContrapartes>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:comentarioContrapartes)}</ope:comentarioContrapartes>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:limitesAnalisis)
                        then <ope:limitesAnalisis>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:limitesAnalisis)}</ope:limitesAnalisis>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:limitesDeterminar)
                        then <ope:limitesDeterminar>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:limitesDeterminar)}</ope:limitesDeterminar>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:etapa)
                        then <ope:etapa>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:etapa)}</ope:etapa>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:status)
                        then <ope:status>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:status)}</ope:status>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:fechaBaja)
                        then <ope:fechaBaja>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:fechaBaja)}</ope:fechaBaja>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:comentarioLaft)
                        then <ope:comentarioLaft>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:comentarioLaft)}</ope:comentarioLaft>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cumpleLaft)
                        then <ope:cumpleLaft>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:cumpleLaft)}</ope:cumpleLaft>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:calificacionRiesgo)
                        then <ope:calificacionRiesgo>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:calificacionRiesgo)}</ope:calificacionRiesgo>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:perspectiva)
                        then 
                            <ope:perspectiva>
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:perspectiva/cat:Id)
                                    then <cat:Id>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:perspectiva/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:perspectiva/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:perspectiva/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:perspectiva/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:perspectiva/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:perspectiva/cat:estatus)
                                    then <cat:estatus>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:perspectiva/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:perspectiva/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:perspectiva/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </ope:perspectiva>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:estado)
                        then 
                            <ope:estado>
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:estado/cat:Id)
                                    then <cat:Id>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:estado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:estado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:estado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:estado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:estado/cat:estatus)
                                    then <cat:estatus>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:estado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:estado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:estado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </ope:estado>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:lineaCredito)
                        then <ope:lineaCredito>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:lineaCredito)}</ope:lineaCredito>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:SRC)
                        then 
                            <ope:SRC>
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:SRC/cat:Id)
                                    then <cat:Id>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:SRC/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:SRC/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:SRC/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:SRC/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:SRC/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:SRC/cat:estatus)
                                    then <cat:estatus>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:SRC/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:SRC/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:SRC/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </ope:SRC>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:RAROC)
                        then <ope:RAROC>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:RAROC)}</ope:RAROC>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:TIR)
                        then <ope:TIR>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:TIR)}</ope:TIR>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:TIREstatus)
                        then <ope:TIREstatus>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:TIREstatus)}</ope:TIREstatus>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:requiereRecursosExternos)
                        then <ope:requiereRecursosExternos>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:requiereRecursosExternos)}</ope:requiereRecursosExternos>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:enProcesoLaft)
                        then <ope:enProcesoLaft>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:enProcesoLaft)}</ope:enProcesoLaft>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:aplicaLaft)
                        then <ope:aplicaLaft>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:aplicaLaft)}</ope:aplicaLaft>
                        else ()
                    }
                    <ope:montoOperacion>
                        {
                            for $montoOperacion in $consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:montoOperacion/ope:montoOperacion
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
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:justificacionCancela)
                        then <ope:justificacionCancela>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:justificacionCancela)}</ope:justificacionCancela>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:observacionCancela)
                        then <ope:observacionCancela>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:observacionCancela)}</ope:observacionCancela>
                        else ()
                    }
                </lin:Operacion>
            else ()
        }
        {
            if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:contrato)
            then 
                <lin:Contrato>
                    <con:idContrato>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:contrato/con:idContrato)}</con:idContrato>
                    <con:idOperacion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:contrato/con:idOperacion)}</con:idOperacion>
                    <con:fechaFirma>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:contrato/con:fechaFirma)}</con:fechaFirma>
                    <con:fechaVigencia>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:contrato/con:fechaVigencia)}</con:fechaVigencia>
                    <con:MontoEscriturado>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:contrato/con:MontoEscriturado)}</con:MontoEscriturado>
                    <con:cuentaCliente>
                        {
                            for $cuentaCliente in $consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:contrato/con:cuentaCliente/con:cuentaCliente
                            return 
                            <con:cuentaCliente>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:contrato/con:cuentaCliente/con:cuentaCliente)}</con:cuentaCliente>
                        }
                    </con:cuentaCliente>
                    <con:instanciaProceso>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:contrato/con:instanciaProceso)}</con:instanciaProceso>
                    {
                        for $LineaCredito in $consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:contrato/con:LineaCredito
                        return 
                        <con:LineaCredito>
                            {
                                if ($LineaCredito/lin1:idLineaCredito)
                                then <lin1:idLineaCredito>{fn:data($LineaCredito/lin1:idLineaCredito)}</lin1:idLineaCredito>
                                else ()
                            }
                            {
                                if ($LineaCredito/lin1:idContrato)
                                then <lin1:idContrato>{fn:data($LineaCredito/lin1:idContrato)}</lin1:idContrato>
                                else ()
                            }
                            <lin1:NumeroLineaCredito>{fn:data($LineaCredito/lin1:NumeroLineaCredito)}</lin1:NumeroLineaCredito>
                            <lin1:Descripcion>{fn:data($LineaCredito/lin1:Descripcion)}</lin1:Descripcion>
                            {
                                if ($LineaCredito/lin1:Flexcube)
                                then 
                                    <lin1:Flexcube>
                                        {
                                            if ($LineaCredito/lin1:Flexcube/lin1:id)
                                            then <lin1:id>{fn:data($LineaCredito/lin1:Flexcube/lin1:id)}</lin1:id>
                                            else ()
                                        }
                                        {
                                            if ($LineaCredito/lin1:Flexcube/lin1:idProductoFlexcube)
                                            then <lin1:idProductoFlexcube>{fn:data($LineaCredito/lin1:Flexcube/lin1:idProductoFlexcube)}</lin1:idProductoFlexcube>
                                            else ()
                                        }
                                        {
                                            if ($LineaCredito/lin1:Flexcube/lin1:idFlexcubePasivo)
                                            then <lin1:idFlexcubePasivo>{fn:data($LineaCredito/lin1:Flexcube/lin1:idFlexcubePasivo)}</lin1:idFlexcubePasivo>
                                            else ()
                                        }
                                    </lin1:Flexcube>
                                else ()
                            }
                            <lin1:Fondo>{fn:data($LineaCredito/lin1:Fondo)}</lin1:Fondo>
                            <lin1:MontoLinea>{fn:data($LineaCredito/lin1:MontoLinea)}</lin1:MontoLinea>
                            {
                                if ($LineaCredito/lin1:Moneda)
                                then 
                                    <lin1:Moneda>
                                        {
                                            if ($LineaCredito/lin1:Moneda/cat:Id)
                                            then <cat:Id>{fn:data($LineaCredito/lin1:Moneda/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($LineaCredito/lin1:Moneda/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($LineaCredito/lin1:Moneda/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($LineaCredito/lin1:Moneda/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($LineaCredito/lin1:Moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($LineaCredito/lin1:Moneda/cat:estatus)
                                            then <cat:estatus>{fn:data($LineaCredito/lin1:Moneda/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($LineaCredito/lin1:Moneda/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($LineaCredito/lin1:Moneda/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </lin1:Moneda>
                                else ()
                            }
                            {
                                if ($LineaCredito/lin1:EsRevolvente)
                                then <lin1:EsRevolvente>{fn:data($LineaCredito/lin1:EsRevolvente)}</lin1:EsRevolvente>
                                else ()
                            }
                            <lin1:TratamientoDiasFeriados>{fn:data($LineaCredito/lin1:TratamientoDiasFeriados)}</lin1:TratamientoDiasFeriados>
                            <lin1:FechaValor>{fn:data($LineaCredito/lin1:FechaValor)}</lin1:FechaValor>
                            <lin1:FechaVencimiento>{fn:data($LineaCredito/lin1:FechaVencimiento)}</lin1:FechaVencimiento>
                            <lin1:CodigoPantallaLimite>{fn:data($LineaCredito/lin1:CodigoPantallaLimite)}</lin1:CodigoPantallaLimite>
                            <lin1:CodigoSerialLimite>{fn:data($LineaCredito/lin1:CodigoSerialLimite)}</lin1:CodigoSerialLimite>
                            <lin1:CodigoAsignacion>{fn:data($LineaCredito/lin1:CodigoAsignacion)}</lin1:CodigoAsignacion>
                            <lin1:DescripcionAsignacion>{fn:data($LineaCredito/lin1:DescripcionAsignacion)}</lin1:DescripcionAsignacion>
                            {
                                if ($LineaCredito/lin1:CondicionEspecial)
                                then <lin1:CondicionEspecial>{fn:data($LineaCredito/lin1:CondicionEspecial)}</lin1:CondicionEspecial>
                                else ()
                            }
                            <lin1:FechaRegistro>{fn:data($LineaCredito/lin1:FechaRegistro)}</lin1:FechaRegistro>
                            {
                                if ($LineaCredito/lin1:Estado)
                                then <lin1:Estado>{fn:data($LineaCredito/lin1:Estado)}</lin1:Estado>
                                else ()
                            }
                            <lin1:descCondEspecial>{fn:data($LineaCredito/lin1:descCondEspecial)}</lin1:descCondEspecial>
                            <lin1:frecuenciaGracia>{fn:data($LineaCredito/lin1:frecuenciaGracia)}</lin1:frecuenciaGracia>
                            <lin1:plazoGracia>{fn:data($LineaCredito/lin1:plazoGracia)}</lin1:plazoGracia>
                            <lin1:frecuenciaFinanciamiento>{fn:data($LineaCredito/lin1:frecuenciaFinanciamiento)}</lin1:frecuenciaFinanciamiento>
                            <lin1:plazoFinanciamiento>{fn:data($LineaCredito/lin1:plazoFinanciamiento)}</lin1:plazoFinanciamiento>
                            <lin1:recursosExternos>{fn:data($LineaCredito/lin1:recursosExternos)}</lin1:recursosExternos>
                            <lin1:tasaMinima>{fn:data($LineaCredito/lin1:tasaMinima)}</lin1:tasaMinima>
                            <lin1:tasaMaxima>{fn:data($LineaCredito/lin1:tasaMaxima)}</lin1:tasaMaxima>
                            <lin1:montoMinimo>{fn:data($LineaCredito/lin1:montoMinimo)}</lin1:montoMinimo>
                            <lin1:montoMaximo>{fn:data($LineaCredito/lin1:montoMaximo)}</lin1:montoMaximo>
                            {

                                    if (xs:decimal(fn:data($LineaCredito/lin1:MoverEntreMeses))=1)
                                    then
                                      <lin1:MoverEntreMeses>Y</lin1:MoverEntreMeses>
                                    else
                                      <lin1:MoverEntreMeses>N</lin1:MoverEntreMeses>

                            }
                            {
                                for $Condicion in $LineaCredito/lin1:Condicion
                                return 
                                <lin1:Condicion>
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
                                </lin1:Condicion>
                            }
                            {
                                for $Termino in $LineaCredito/lin1:Termino
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
                                </lin1:Termino>
                            }
                            {
                                for $Comision in $consultarComisionByIdLineaCredito_OutputVariable.response/com:Comision
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
                                        then <com1:fechaInicioCapital>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:contrato/con:LineaCredito/lin1:FechaVencimiento)}</com1:fechaInicioCapital>
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
                                            <com1:tipoTasa>
                                                <cat:codigoExterno>X</cat:codigoExterno>
                                            </com1:tipoTasa>
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
                                        for $configAtributo2 in $Comision/com1:configAtributo
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
                                        if ($Comision/com1:Accion)
                                        then <com1:Accion>{fn:data($Comision/com1:Accion)}</com1:Accion>
                                        else ()
                                    }
                                </lin1:Comision>
                            }

                            {
                                for $Fuente in $LineaCredito/lin1:Fuente
                                return 
                                <lin1:Fuente>
                                    <lin1:idFuente>{fn:data($Fuente/lin1:idFuente)}</lin1:idFuente>
                                    <lin1:idLineaCredito>{fn:data($Fuente/lin1:idLineaCredito)}</lin1:idLineaCredito>
                                    <lin1:idLineaPasiva>{fn:data($Fuente/lin1:idLineaPasiva)}</lin1:idLineaPasiva>
                                    <lin1:codigoLineaPasiva>{fn:data($Fuente/lin1:codigoLineaPasiva)}</lin1:codigoLineaPasiva>
                                    <lin1:Descripcion>{fn:data($Fuente/lin1:Descripcion)}</lin1:Descripcion>
                                    <lin1:FechaObtenido>{fn:data($Fuente/lin1:FechaObtenido)}</lin1:FechaObtenido>
                                    <lin1:MontoAsignado>{fn:data($Fuente/lin1:MontoAsignado)}</lin1:MontoAsignado>
                                    <lin1:NumeroAsignacion>{fn:data($Fuente/lin1:NumeroAsignacion)}</lin1:NumeroAsignacion>
                                    <lin1:Comentario>{fn:data($Fuente/lin1:Comentario)}</lin1:Comentario>
                                    <lin1:idContrato>{fn:data($Fuente/lin1:idContrato)}</lin1:idContrato>
                                    <lin1:FechaRegistro>{fn:data($Fuente/lin1:FechaRegistro)}</lin1:FechaRegistro>
                                    <lin1:Estado>{fn:data($Fuente/lin1:Estado)}</lin1:Estado>
                                </lin1:Fuente>
                            }
                        </con:LineaCredito>
                    }
                </lin:Contrato>
            else ()
        }
        {
            if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion)
            then 
                <lin:Aprobacion>
                    <apr:idAprobacion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:idAprobacion)}</apr:idAprobacion>
                    <apr:idOperacion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:idOperacion)}</apr:idOperacion>
                    <apr:idReunionAprobacion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:idReunionAprobacion)}</apr:idReunionAprobacion>
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:fechaAprobacion)
                        then <apr:fechaAprobacion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:fechaAprobacion)}</apr:fechaAprobacion>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:tipoAprobacion)
                        then 
                            <apr:tipoAprobacion>
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:tipoAprobacion/cat:Id)
                                    then <cat:Id>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:tipoAprobacion/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:tipoAprobacion/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:tipoAprobacion/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:tipoAprobacion/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:tipoAprobacion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:tipoAprobacion/cat:estatus)
                                    then <cat:estatus>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:tipoAprobacion/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:tipoAprobacion/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:tipoAprobacion/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </apr:tipoAprobacion>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:numeroResolucion)
                        then <apr:numeroResolucion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:numeroResolucion)}</apr:numeroResolucion>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:resumen)
                        then <apr:resumen>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:resumen)}</apr:resumen>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:idMontoAprobacion)
                        then <apr:idMontoAprobacion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:idMontoAprobacion)}</apr:idMontoAprobacion>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:MontoAprobacion)
                        then <apr:MontoAprobacion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:MontoAprobacion)}</apr:MontoAprobacion>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:loginUsuario)
                        then 
                            <apr:loginUsuario>
                                <apr:usuario>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:loginUsuario/apr:usuario)}</apr:usuario>
                                <apr:nombreUsuario>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:loginUsuario/apr:nombreUsuario)}</apr:nombreUsuario>
                                <apr:propietario>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:loginUsuario/apr:propietario)}</apr:propietario>
                                <apr:dependecia>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:loginUsuario/apr:dependecia)}</apr:dependecia>
                            </apr:loginUsuario>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:fechaRegistro)
                        then <apr:fechaRegistro>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:fechaRegistro)}</apr:fechaRegistro>
                        else ()
                    }
                    {
                        if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:idContrato)
                        then <apr:idContrato>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:idContrato)}</apr:idContrato>
                        else ()
                    }
                    {
                        for $LineaCredito1 in $consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Aprobacion/apr:LineaCredito
                        return 
                        <apr:LineaCredito>
                            {
                                if ($LineaCredito1/lin1:idLineaCredito)
                                then <lin1:idLineaCredito>{fn:data($LineaCredito1/lin1:idLineaCredito)}</lin1:idLineaCredito>
                                else ()
                            }
                            {
                                if ($LineaCredito1/lin1:idContrato)
                                then <lin1:idContrato>{fn:data($LineaCredito1/lin1:idContrato)}</lin1:idContrato>
                                else ()
                            }
                            <lin1:NumeroLineaCredito>{fn:data($LineaCredito1/lin1:NumeroLineaCredito)}</lin1:NumeroLineaCredito>
                            <lin1:Descripcion>{fn:data($LineaCredito1/lin1:Descripcion)}</lin1:Descripcion>
                            {
                                if ($LineaCredito1/lin1:Flexcube)
                                then 
                                    <lin1:Flexcube>
                                        {
                                            if ($LineaCredito1/lin1:Flexcube/lin1:id)
                                            then <lin1:id>{fn:data($LineaCredito1/lin1:Flexcube/lin1:id)}</lin1:id>
                                            else ()
                                        }
                                        {
                                            if ($LineaCredito1/lin1:Flexcube/lin1:idProductoFlexcube)
                                            then <lin1:idProductoFlexcube>{fn:data($LineaCredito1/lin1:Flexcube/lin1:idProductoFlexcube)}</lin1:idProductoFlexcube>
                                            else ()
                                        }
                                        {
                                            if ($LineaCredito1/lin1:Flexcube/lin1:idFlexcubePasivo)
                                            then <lin1:idFlexcubePasivo>{fn:data($LineaCredito1/lin1:Flexcube/lin1:idFlexcubePasivo)}</lin1:idFlexcubePasivo>
                                            else ()
                                        }
                                    </lin1:Flexcube>
                                else ()
                            }
                            <lin1:Fondo>{fn:data($LineaCredito1/lin1:Fondo)}</lin1:Fondo>
                            <lin1:MontoLinea>{fn:data($LineaCredito1/lin1:MontoLinea)}</lin1:MontoLinea>
                            {
                                if ($LineaCredito1/lin1:Moneda)
                                then 
                                    <lin1:Moneda>
                                        {
                                            if ($LineaCredito1/lin1:Moneda/cat:Id)
                                            then <cat:Id>{fn:data($LineaCredito1/lin1:Moneda/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($LineaCredito1/lin1:Moneda/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($LineaCredito1/lin1:Moneda/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($LineaCredito1/lin1:Moneda/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($LineaCredito1/lin1:Moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($LineaCredito1/lin1:Moneda/cat:estatus)
                                            then <cat:estatus>{fn:data($LineaCredito1/lin1:Moneda/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($LineaCredito1/lin1:Moneda/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($LineaCredito1/lin1:Moneda/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </lin1:Moneda>
                                else ()
                            }
                            {
                                if ($LineaCredito1/lin1:EsRevolvente)
                                then <lin1:EsRevolvente>{fn:data($LineaCredito1/lin1:EsRevolvente)}</lin1:EsRevolvente>
                                else ()
                            }
                            <lin1:TratamientoDiasFeriados>{fn:data($LineaCredito1/lin1:TratamientoDiasFeriados)}</lin1:TratamientoDiasFeriados>
                            <lin1:FechaValor>{fn:data($LineaCredito1/lin1:FechaValor)}</lin1:FechaValor>
                            <lin1:FechaVencimiento>{fn:data($LineaCredito1/lin1:FechaVencimiento)}</lin1:FechaVencimiento>
                            <lin1:CodigoPantallaLimite>{fn:data($LineaCredito1/lin1:CodigoPantallaLimite)}</lin1:CodigoPantallaLimite>
                            <lin1:CodigoSerialLimite>{fn:data($LineaCredito1/lin1:CodigoSerialLimite)}</lin1:CodigoSerialLimite>
                            <lin1:CodigoAsignacion>{fn:data($LineaCredito1/lin1:CodigoAsignacion)}</lin1:CodigoAsignacion>
                            <lin1:DescripcionAsignacion>{fn:data($LineaCredito1/lin1:DescripcionAsignacion)}</lin1:DescripcionAsignacion>
                            {
                                if ($LineaCredito1/lin1:CondicionEspecial)
                                then <lin1:CondicionEspecial>{fn:data($LineaCredito1/lin1:CondicionEspecial)}</lin1:CondicionEspecial>
                                else ()
                            }
                            <lin1:FechaRegistro>{fn:data($LineaCredito1/lin1:FechaRegistro)}</lin1:FechaRegistro>
                            {
                                if ($LineaCredito1/lin1:Estado)
                                then <lin1:Estado>{fn:data($LineaCredito1/lin1:Estado)}</lin1:Estado>
                                else ()
                            }
                            <lin1:descCondEspecial>{fn:data($LineaCredito1/lin1:descCondEspecial)}</lin1:descCondEspecial>
                            <lin1:frecuenciaGracia>{fn:data($LineaCredito1/lin1:frecuenciaGracia)}</lin1:frecuenciaGracia>
                            <lin1:plazoGracia>{fn:data($LineaCredito1/lin1:plazoGracia)}</lin1:plazoGracia>
                            <lin1:frecuenciaFinanciamiento>{fn:data($LineaCredito1/lin1:frecuenciaFinanciamiento)}</lin1:frecuenciaFinanciamiento>
                            <lin1:plazoFinanciamiento>{fn:data($LineaCredito1/lin1:plazoFinanciamiento)}</lin1:plazoFinanciamiento>
                            <lin1:recursosExternos>{fn:data($LineaCredito1/lin1:recursosExternos)}</lin1:recursosExternos>
                            <lin1:tasaMinima>{fn:data($LineaCredito1/lin1:tasaMinima)}</lin1:tasaMinima>
                            <lin1:tasaMaxima>{fn:data($LineaCredito1/lin1:tasaMaxima)}</lin1:tasaMaxima>
                            <lin1:montoMinimo>{fn:data($LineaCredito1/lin1:montoMinimo)}</lin1:montoMinimo>
                            <lin1:montoMaximo>{fn:data($LineaCredito1/lin1:montoMaximo)}</lin1:montoMaximo>
                            {
                                for $Condicion1 in $LineaCredito1/lin1:Condicion
                                return 
                                <lin1:Condicion>
                                    <con1:idCondicion>{fn:data($Condicion1/con1:idCondicion)}</con1:idCondicion>
                                    <con1:operacion>{fn:data($Condicion1/con1:operacion)}</con1:operacion>
                                    {
                                        if ($Condicion1/con1:nombre)
                                        then <con1:nombre>{fn:data($Condicion1/con1:nombre)}</con1:nombre>
                                        else ()
                                    }
                                    {
                                        if ($Condicion1/con1:descripcion)
                                        then <con1:descripcion>{fn:data($Condicion1/con1:descripcion)}</con1:descripcion>
                                        else ()
                                    }
                                    {
                                        if ($Condicion1/con1:tipoCondicion)
                                        then 
                                            <con1:tipoCondicion>
                                                {
                                                    if ($Condicion1/con1:tipoCondicion/con1:idCatCondicion)
                                                    then <con1:idCatCondicion>{fn:data($Condicion1/con1:tipoCondicion/con1:idCatCondicion)}</con1:idCatCondicion>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:tipoCondicion/con1:descripcion)
                                                    then <con1:descripcion>{fn:data($Condicion1/con1:tipoCondicion/con1:descripcion)}</con1:descripcion>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:tipoCondicion/con1:descripcionCorta)
                                                    then <con1:descripcionCorta>{fn:data($Condicion1/con1:tipoCondicion/con1:descripcionCorta)}</con1:descripcionCorta>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:tipoCondicion/con1:idTipoCondicion)
                                                    then <con1:idTipoCondicion>{fn:data($Condicion1/con1:tipoCondicion/con1:idTipoCondicion)}</con1:idTipoCondicion>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:tipoCondicion/con1:esEditableEnFormalizacion)
                                                    then <con1:esEditableEnFormalizacion>{fn:data($Condicion1/con1:tipoCondicion/con1:esEditableEnFormalizacion)}</con1:esEditableEnFormalizacion>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:tipoCondicion/con1:requiereValidarCOFI)
                                                    then <con1:requiereValidarCOFI>{fn:data($Condicion1/con1:tipoCondicion/con1:requiereValidarCOFI)}</con1:requiereValidarCOFI>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:tipoCondicion/con1:sePuedeDispensar)
                                                    then <con1:sePuedeDispensar>{fn:data($Condicion1/con1:tipoCondicion/con1:sePuedeDispensar)}</con1:sePuedeDispensar>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:tipoCondicion/con1:esPlantilla)
                                                    then <con1:esPlantilla>{fn:data($Condicion1/con1:tipoCondicion/con1:esPlantilla)}</con1:esPlantilla>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:tipoCondicion/con1:idCondicionPrecarga)
                                                    then <con1:idCondicionPrecarga>{fn:data($Condicion1/con1:tipoCondicion/con1:idCondicionPrecarga)}</con1:idCondicionPrecarga>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:tipoCondicion/con1:fechaRegistro)
                                                    then <con1:fechaRegistro>{fn:data($Condicion1/con1:tipoCondicion/con1:fechaRegistro)}</con1:fechaRegistro>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:tipoCondicion/con1:estado)
                                                    then <con1:estado>{fn:data($Condicion1/con1:tipoCondicion/con1:estado)}</con1:estado>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:tipoCondicion/con1:codigoExterno)
                                                    then <con1:codigoExterno>{fn:data($Condicion1/con1:tipoCondicion/con1:codigoExterno)}</con1:codigoExterno>
                                                    else ()
                                                }
                                            </con1:tipoCondicion>
                                        else ()
                                    }
                                    {
                                        for $categoriaCondicion1 in $Condicion1/con1:categoriaCondicion
                                        return 
                                        <con1:categoriaCondicion>
                                            <con1:id>{fn:data($categoriaCondicion1/con1:id)}</con1:id>
                                            <con1:descripcion>{fn:data($categoriaCondicion1/con1:descripcion)}</con1:descripcion>
                                            {
                                                if ($categoriaCondicion1/con1:descripcionCorta)
                                                then <con1:descripcionCorta>{fn:data($categoriaCondicion1/con1:descripcionCorta)}</con1:descripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($categoriaCondicion1/con1:estatus)
                                                then <con1:estatus>{fn:data($categoriaCondicion1/con1:estatus)}</con1:estatus>
                                                else ()
                                            }
                                            {
                                                if ($categoriaCondicion1/con1:codigoExterno)
                                                then <con1:codigoExterno>{fn:data($categoriaCondicion1/con1:codigoExterno)}</con1:codigoExterno>
                                                else ()
                                            }
                                            {
                                                for $validadores1 in $categoriaCondicion1/con1:validadores
                                                return 
                                                <con1:validadores>
                                                    {
                                                        if ($validadores1/cat:Id)
                                                        then <cat:Id>{fn:data($validadores1/cat:Id)}</cat:Id>
                                                        else ()
                                                    }
                                                    {
                                                        if ($validadores1/cat:Descripcion)
                                                        then <cat:Descripcion>{fn:data($validadores1/cat:Descripcion)}</cat:Descripcion>
                                                        else ()
                                                    }
                                                    {
                                                        if ($validadores1/cat:DescripcionCorta)
                                                        then <cat:DescripcionCorta>{fn:data($validadores1/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                        else ()
                                                    }
                                                    {
                                                        if ($validadores1/cat:estatus)
                                                        then <cat:estatus>{fn:data($validadores1/cat:estatus)}</cat:estatus>
                                                        else ()
                                                    }
                                                    {
                                                        if ($validadores1/cat:codigoExterno)
                                                        then <cat:codigoExterno>{fn:data($validadores1/cat:codigoExterno)}</cat:codigoExterno>
                                                        else ()
                                                    }
                                                </con1:validadores>
                                            }
                                        </con1:categoriaCondicion>
                                    }
                                    {
                                        if ($Condicion1/con1:controlCondicion)
                                        then 
                                            <con1:controlCondicion>
                                                {
                                                    if ($Condicion1/con1:controlCondicion/cat:Id)
                                                    then <cat:Id>{fn:data($Condicion1/con1:controlCondicion/cat:Id)}</cat:Id>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:controlCondicion/cat:Descripcion)
                                                    then <cat:Descripcion>{fn:data($Condicion1/con1:controlCondicion/cat:Descripcion)}</cat:Descripcion>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:controlCondicion/cat:DescripcionCorta)
                                                    then <cat:DescripcionCorta>{fn:data($Condicion1/con1:controlCondicion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:controlCondicion/cat:estatus)
                                                    then <cat:estatus>{fn:data($Condicion1/con1:controlCondicion/cat:estatus)}</cat:estatus>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:controlCondicion/cat:codigoExterno)
                                                    then <cat:codigoExterno>{fn:data($Condicion1/con1:controlCondicion/cat:codigoExterno)}</cat:codigoExterno>
                                                    else ()
                                                }
                                            </con1:controlCondicion>
                                        else ()
                                    }
                                    {
                                        for $eventoCondicion1 in $Condicion1/con1:eventoCondicion
                                        return 
                                        <con1:eventoCondicion>
                                            {
                                                if ($eventoCondicion1/cat:Id)
                                                then <cat:Id>{fn:data($eventoCondicion1/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($eventoCondicion1/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($eventoCondicion1/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($eventoCondicion1/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($eventoCondicion1/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($eventoCondicion1/cat:estatus)
                                                then <cat:estatus>{fn:data($eventoCondicion1/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($eventoCondicion1/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($eventoCondicion1/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </con1:eventoCondicion>
                                    }
                                    {
                                        if ($Condicion1/con1:tipoFechaInicio)
                                        then 
                                            <con1:tipoFechaInicio>
                                                {
                                                    if ($Condicion1/con1:tipoFechaInicio/cat:Id)
                                                    then <cat:Id>{fn:data($Condicion1/con1:tipoFechaInicio/cat:Id)}</cat:Id>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:tipoFechaInicio/cat:Descripcion)
                                                    then <cat:Descripcion>{fn:data($Condicion1/con1:tipoFechaInicio/cat:Descripcion)}</cat:Descripcion>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:tipoFechaInicio/cat:DescripcionCorta)
                                                    then <cat:DescripcionCorta>{fn:data($Condicion1/con1:tipoFechaInicio/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:tipoFechaInicio/cat:estatus)
                                                    then <cat:estatus>{fn:data($Condicion1/con1:tipoFechaInicio/cat:estatus)}</cat:estatus>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:tipoFechaInicio/cat:codigoExterno)
                                                    then <cat:codigoExterno>{fn:data($Condicion1/con1:tipoFechaInicio/cat:codigoExterno)}</cat:codigoExterno>
                                                    else ()
                                                }
                                            </con1:tipoFechaInicio>
                                        else ()
                                    }
                                    {
                                        if ($Condicion1/con1:fechaInicio)
                                        then <con1:fechaInicio>{fn:data($Condicion1/con1:fechaInicio)}</con1:fechaInicio>
                                        else ()
                                    }
                                    {
                                        if ($Condicion1/con1:plazo)
                                        then <con1:plazo>{fn:data($Condicion1/con1:plazo)}</con1:plazo>
                                        else ()
                                    }
                                    {
                                        if ($Condicion1/con1:frecuenciaPlazo)
                                        then 
                                            <con1:frecuenciaPlazo>
                                                {
                                                    if ($Condicion1/con1:frecuenciaPlazo/cat:Id)
                                                    then <cat:Id>{fn:data($Condicion1/con1:frecuenciaPlazo/cat:Id)}</cat:Id>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:frecuenciaPlazo/cat:Descripcion)
                                                    then <cat:Descripcion>{fn:data($Condicion1/con1:frecuenciaPlazo/cat:Descripcion)}</cat:Descripcion>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:frecuenciaPlazo/cat:DescripcionCorta)
                                                    then <cat:DescripcionCorta>{fn:data($Condicion1/con1:frecuenciaPlazo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:frecuenciaPlazo/cat:estatus)
                                                    then <cat:estatus>{fn:data($Condicion1/con1:frecuenciaPlazo/cat:estatus)}</cat:estatus>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:frecuenciaPlazo/cat:codigoExterno)
                                                    then <cat:codigoExterno>{fn:data($Condicion1/con1:frecuenciaPlazo/cat:codigoExterno)}</cat:codigoExterno>
                                                    else ()
                                                }
                                            </con1:frecuenciaPlazo>
                                        else ()
                                    }
                                    {
                                        if ($Condicion1/con1:fechaFinal)
                                        then <con1:fechaFinal>{fn:data($Condicion1/con1:fechaFinal)}</con1:fechaFinal>
                                        else ()
                                    }
                                    {
                                        if ($Condicion1/con1:estadoTCC)
                                        then 
                                            <con1:estadoTCC>
                                                {
                                                    if ($Condicion1/con1:estadoTCC/atr:id)
                                                    then <atr:id>{fn:data($Condicion1/con1:estadoTCC/atr:id)}</atr:id>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:estadoTCC/atr:descripcion)
                                                    then <atr:descripcion>{fn:data($Condicion1/con1:estadoTCC/atr:descripcion)}</atr:descripcion>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:estadoTCC/atr:descripcionCorta)
                                                    then <atr:descripcionCorta>{fn:data($Condicion1/con1:estadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:estadoTCC/atr:estatus)
                                                    then <atr:estatus>{fn:data($Condicion1/con1:estadoTCC/atr:estatus)}</atr:estatus>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:estadoTCC/atr:codigoExterno)
                                                    then <atr:codigoExterno>{fn:data($Condicion1/con1:estadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:estadoTCC/atr:esSubEstado)
                                                    then <atr:esSubEstado>{fn:data($Condicion1/con1:estadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                                                    else ()
                                                }
                                            </con1:estadoTCC>
                                        else ()
                                    }
                                    {
                                        if ($Condicion1/con1:subEstadoTCC)
                                        then 
                                            <con1:subEstadoTCC>
                                                {
                                                    if ($Condicion1/con1:subEstadoTCC/atr:id)
                                                    then <atr:id>{fn:data($Condicion1/con1:subEstadoTCC/atr:id)}</atr:id>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:subEstadoTCC/atr:descripcion)
                                                    then <atr:descripcion>{fn:data($Condicion1/con1:subEstadoTCC/atr:descripcion)}</atr:descripcion>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:subEstadoTCC/atr:descripcionCorta)
                                                    then <atr:descripcionCorta>{fn:data($Condicion1/con1:subEstadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:subEstadoTCC/atr:estatus)
                                                    then <atr:estatus>{fn:data($Condicion1/con1:subEstadoTCC/atr:estatus)}</atr:estatus>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:subEstadoTCC/atr:codigoExterno)
                                                    then <atr:codigoExterno>{fn:data($Condicion1/con1:subEstadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                                                    else ()
                                                }
                                                {
                                                    if ($Condicion1/con1:subEstadoTCC/atr:esSubEstado)
                                                    then <atr:esSubEstado>{fn:data($Condicion1/con1:subEstadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                                                    else ()
                                                }
                                            </con1:subEstadoTCC>
                                        else ()
                                    }
                                    {
                                        if ($Condicion1/con1:fechaRegistro)
                                        then <con1:fechaRegistro>{fn:data($Condicion1/con1:fechaRegistro)}</con1:fechaRegistro>
                                        else ()
                                    }
                                    {
                                        if ($Condicion1/con1:estado)
                                        then <con1:estado>{fn:data($Condicion1/con1:estado)}</con1:estado>
                                        else ()
                                    }
                                    {
                                        if ($Condicion1/con1:condicionEnmendada)
                                        then <con1:condicionEnmendada>{fn:data($Condicion1/con1:condicionEnmendada)}</con1:condicionEnmendada>
                                        else ()
                                    }
                                    {
                                        if ($Condicion1/con1:fechaEnmienda)
                                        then <con1:fechaEnmienda>{fn:data($Condicion1/con1:fechaEnmienda)}</con1:fechaEnmienda>
                                        else ()
                                    }
                                    {
                                        for $configAtributo1 in $Condicion1/con1:configAtributo
                                        return 
                                        <con1:configAtributo>
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
                                        </con1:configAtributo>
                                    }
                                    {
                                        if ($Condicion1/con1:evidencia)
                                        then 
                                            <con1:evidencia>
                                                {
                                                    for $Documento1 in $Condicion1/con1:evidencia/doc:Documento
                                                    return 
                                                    <doc:Documento>
                                                        {
                                                            if ($Documento1/doc:idDocumento)
                                                            then <doc:idDocumento>{fn:data($Documento1/doc:idDocumento)}</doc:idDocumento>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:idTipoDocumento)
                                                            then <doc:idTipoDocumento>{fn:data($Documento1/doc:idTipoDocumento)}</doc:idTipoDocumento>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:idTipoDocumentoExterno)
                                                            then <doc:idTipoDocumentoExterno>{fn:data($Documento1/doc:idTipoDocumentoExterno)}</doc:idTipoDocumentoExterno>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:nombreTipoDocumento)
                                                            then <doc:nombreTipoDocumento>{fn:data($Documento1/doc:nombreTipoDocumento)}</doc:nombreTipoDocumento>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:etapa)
                                                            then <doc:etapa>{fn:data($Documento1/doc:etapa)}</doc:etapa>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:codigoDocumento)
                                                            then <doc:codigoDocumento>{fn:data($Documento1/doc:codigoDocumento)}</doc:codigoDocumento>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:idExterno)
                                                            then <doc:idExterno>{fn:data($Documento1/doc:idExterno)}</doc:idExterno>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:idDocAreaTipo)
                                                            then <doc:idDocAreaTipo>{fn:data($Documento1/doc:idDocAreaTipo)}</doc:idDocAreaTipo>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:idOperacion)
                                                            then <doc:idOperacion>{fn:data($Documento1/doc:idOperacion)}</doc:idOperacion>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:idProducto)
                                                            then <doc:idProducto>{fn:data($Documento1/doc:idProducto)}</doc:idProducto>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:idPais)
                                                            then <doc:idPais>{fn:data($Documento1/doc:idPais)}</doc:idPais>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:nombre)
                                                            then <doc:nombre>{fn:data($Documento1/doc:nombre)}</doc:nombre>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:filename)
                                                            then <doc:filename>{fn:data($Documento1/doc:filename)}</doc:filename>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:mime_type)
                                                            then <doc:mime_type>{fn:data($Documento1/doc:mime_type)}</doc:mime_type>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:tamanio)
                                                            then <doc:tamanio>{fn:data($Documento1/doc:tamanio)}</doc:tamanio>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:idAdjunto)
                                                            then <doc:idAdjunto>{fn:data($Documento1/doc:idAdjunto)}</doc:idAdjunto>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:esJustificacion)
                                                            then <doc:esJustificacion>{fn:data($Documento1/doc:esJustificacion)}</doc:esJustificacion>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:comentario)
                                                            then <doc:comentario>{fn:data($Documento1/doc:comentario)}</doc:comentario>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:fechaDocumento)
                                                            then <doc:fechaDocumento>{fn:data($Documento1/doc:fechaDocumento)}</doc:fechaDocumento>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:fechaRegistro)
                                                            then <doc:fechaRegistro>{fn:data($Documento1/doc:fechaRegistro)}</doc:fechaRegistro>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:status)
                                                            then <doc:status>{fn:data($Documento1/doc:status)}</doc:status>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:estatusTipoDoc)
                                                            then <doc:estatusTipoDoc>{fn:data($Documento1/doc:estatusTipoDoc)}</doc:estatusTipoDoc>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:codExtTipoDoc)
                                                            then <doc:codExtTipoDoc>{fn:data($Documento1/doc:codExtTipoDoc)}</doc:codExtTipoDoc>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:tarea)
                                                            then <doc:tarea>{fn:data($Documento1/doc:tarea)}</doc:tarea>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:idtarea)
                                                            then <doc:idtarea>{fn:data($Documento1/doc:idtarea)}</doc:idtarea>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:content)
                                                            then <doc:content>{fn:data($Documento1/doc:content)}</doc:content>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:PuedeModificar)
                                                            then <doc:PuedeModificar>{fn:data($Documento1/doc:PuedeModificar)}</doc:PuedeModificar>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:PuedeBorrar)
                                                            then <doc:PuedeBorrar>{fn:data($Documento1/doc:PuedeBorrar)}</doc:PuedeBorrar>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:usuarioAgrega)
                                                            then <doc:usuarioAgrega>{fn:data($Documento1/doc:usuarioAgrega)}</doc:usuarioAgrega>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:usuarioUltimaActualizacion)
                                                            then <doc:usuarioUltimaActualizacion>{fn:data($Documento1/doc:usuarioUltimaActualizacion)}</doc:usuarioUltimaActualizacion>
                                                            else ()
                                                        }
                                                        {
                                                            if ($Documento1/doc:accionARealizar)
                                                            then <doc:accionARealizar>{fn:data($Documento1/doc:accionARealizar)}</doc:accionARealizar>
                                                            else ()
                                                        }
                                                    </doc:Documento>
                                                }
                                            </con1:evidencia>
                                        else ()
                                    }
                                    {
                                        for $observaciones1 in $Condicion1/con1:observaciones
                                        return 
                                        <con1:observaciones>
                                            <con1:id>{fn:data($observaciones1/con1:id)}</con1:id>
                                            {
                                                if ($observaciones1/con1:observacion)
                                                then <con1:observacion>{fn:data($observaciones1/con1:observacion)}</con1:observacion>
                                                else ()
                                            }
                                            {
                                                if ($observaciones1/con1:loginUsuario)
                                                then <con1:loginUsuario>{fn:data($observaciones1/con1:loginUsuario)}</con1:loginUsuario>
                                                else ()
                                            }
                                            {
                                                if ($observaciones1/con1:nombreUsuario)
                                                then <con1:nombreUsuario>{fn:data($observaciones1/con1:nombreUsuario)}</con1:nombreUsuario>
                                                else ()
                                            }
                                            {
                                                if ($observaciones1/con1:fechaRegistro)
                                                then <con1:fechaRegistro>{fn:data($observaciones1/con1:fechaRegistro)}</con1:fechaRegistro>
                                                else ()
                                            }
                                            {
                                                if ($observaciones1/con1:estatus)
                                                then <con1:estatus>{fn:data($observaciones1/con1:estatus)}</con1:estatus>
                                                else ()
                                            }
                                            {
                                                if ($observaciones1/con1:esPrincipal)
                                                then <con1:esPrincipal>{fn:data($observaciones1/con1:esPrincipal)}</con1:esPrincipal>
                                                else ()
                                            }
                                            {
                                                if ($observaciones1/con1:tareaBPM)
                                                then 
                                                    <con1:tareaBPM>
                                                        {
                                                            if ($observaciones1/con1:tareaBPM/cat:Id)
                                                            then <cat:Id>{fn:data($observaciones1/con1:tareaBPM/cat:Id)}</cat:Id>
                                                            else ()
                                                        }
                                                        {
                                                            if ($observaciones1/con1:tareaBPM/cat:Descripcion)
                                                            then <cat:Descripcion>{fn:data($observaciones1/con1:tareaBPM/cat:Descripcion)}</cat:Descripcion>
                                                            else ()
                                                        }
                                                        {
                                                            if ($observaciones1/con1:tareaBPM/cat:DescripcionCorta)
                                                            then <cat:DescripcionCorta>{fn:data($observaciones1/con1:tareaBPM/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                            else ()
                                                        }
                                                        {
                                                            if ($observaciones1/con1:tareaBPM/cat:estatus)
                                                            then <cat:estatus>{fn:data($observaciones1/con1:tareaBPM/cat:estatus)}</cat:estatus>
                                                            else ()
                                                        }
                                                        {
                                                            if ($observaciones1/con1:tareaBPM/cat:codigoExterno)
                                                            then <cat:codigoExterno>{fn:data($observaciones1/con1:tareaBPM/cat:codigoExterno)}</cat:codigoExterno>
                                                            else ()
                                                        }
                                                    </con1:tareaBPM>
                                                else ()
                                            }
                                        </con1:observaciones>
                                    }
                                    {
                                        for $lineaCredito1 in $Condicion1/con1:lineaCredito
                                        return 
                                        <con1:lineaCredito>
                                            {
                                                if ($lineaCredito1/atr:id)
                                                then <atr:id>{fn:data($lineaCredito1/atr:id)}</atr:id>
                                                else ()
                                            }
                                            {
                                                if ($lineaCredito1/atr:descripcion)
                                                then <atr:descripcion>{fn:data($lineaCredito1/atr:descripcion)}</atr:descripcion>
                                                else ()
                                            }
                                            {
                                                if ($lineaCredito1/atr:estatus)
                                                then <atr:estatus>{fn:data($lineaCredito1/atr:estatus)}</atr:estatus>
                                                else ()
                                            }
                                        </con1:lineaCredito>
                                    }
                                    {
                                        for $fuente1 in $Condicion1/con1:fuente
                                        return 
                                        <con1:fuente>
                                            {
                                                if ($fuente1/atr:id)
                                                then <atr:id>{fn:data($fuente1/atr:id)}</atr:id>
                                                else ()
                                            }
                                            {
                                                if ($fuente1/atr:descripcion)
                                                then <atr:descripcion>{fn:data($fuente1/atr:descripcion)}</atr:descripcion>
                                                else ()
                                            }
                                            {
                                                if ($fuente1/atr:estatus)
                                                then <atr:estatus>{fn:data($fuente1/atr:estatus)}</atr:estatus>
                                                else ()
                                            }
                                        </con1:fuente>
                                    }
                                    {
                                        if ($Condicion1/con1:Accion)
                                        then <con1:Accion>{fn:data($Condicion1/con1:Accion)}</con1:Accion>
                                        else ()
                                    }
                                </lin1:Condicion>
                            }
                            {
                                for $Termino1 in $LineaCredito1/lin1:Termino
                                return 
                                <lin1:Termino>
                                    <ter:idTermino>{fn:data($Termino1/ter:idTermino)}</ter:idTermino>
                                    <ter:operacion>{fn:data($Termino1/ter:operacion)}</ter:operacion>
                                    <ter:nombre>{fn:data($Termino1/ter:nombre)}</ter:nombre>
                                    <ter:descripcion>{fn:data($Termino1/ter:descripcion)}</ter:descripcion>
                                    <ter:tipoTermino>
                                        {
                                            if ($Termino1/ter:tipoTermino/ter:idCatTermino)
                                            then <ter:idCatTermino>{fn:data($Termino1/ter:tipoTermino/ter:idCatTermino)}</ter:idCatTermino>
                                            else ()
                                        }
                                        <ter:descripcion>{fn:data($Termino1/ter:tipoTermino/ter:descripcion)}</ter:descripcion>
                                        <ter:descripcionCorta>{fn:data($Termino1/ter:tipoTermino/ter:descripcionCorta)}</ter:descripcionCorta>
                                        {
                                            if ($Termino1/ter:tipoTermino/ter:idTipoTermino)
                                            then <ter:idTipoTermino>{fn:data($Termino1/ter:tipoTermino/ter:idTipoTermino)}</ter:idTipoTermino>
                                            else ()
                                        }
                                        <ter:esEditableEnFormalizacion>{fn:data($Termino1/ter:tipoTermino/ter:esEditableEnFormalizacion)}</ter:esEditableEnFormalizacion>
                                        <ter:requiereValidarCOFI>{fn:data($Termino1/ter:tipoTermino/ter:requiereValidarCOFI)}</ter:requiereValidarCOFI>
                                        <ter:sePuedeDispensar>{fn:data($Termino1/ter:tipoTermino/ter:sePuedeDispensar)}</ter:sePuedeDispensar>
                                        <ter:esPlantilla>{fn:data($Termino1/ter:tipoTermino/ter:esPlantilla)}</ter:esPlantilla>
                                        <ter:requiereOGC>{fn:data($Termino1/ter:tipoTermino/ter:requiereOGC)}</ter:requiereOGC>
                                        <ter:idTerminoPrecarga>{fn:data($Termino1/ter:tipoTermino/ter:idTerminoPrecarga)}</ter:idTerminoPrecarga>
                                        <ter:fechaRegistro>{fn:data($Termino1/ter:tipoTermino/ter:fechaRegistro)}</ter:fechaRegistro>
                                        <ter:estado>{fn:data($Termino1/ter:tipoTermino/ter:estado)}</ter:estado>
                                        <ter:codigoExterno>{fn:data($Termino1/ter:tipoTermino/ter:codigoExterno)}</ter:codigoExterno>
                                    </ter:tipoTermino>
                                    <ter:tipoFechaInicio>
                                        {
                                            if ($Termino1/ter:tipoFechaInicio/cat:Id)
                                            then <cat:Id>{fn:data($Termino1/ter:tipoFechaInicio/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:tipoFechaInicio/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Termino1/ter:tipoFechaInicio/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:tipoFechaInicio/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Termino1/ter:tipoFechaInicio/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:tipoFechaInicio/cat:estatus)
                                            then <cat:estatus>{fn:data($Termino1/ter:tipoFechaInicio/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:tipoFechaInicio/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Termino1/ter:tipoFechaInicio/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </ter:tipoFechaInicio>
                                    <ter:fechaInicio>{fn:data($Termino1/ter:fechaInicio)}</ter:fechaInicio>
                                    <ter:plazo>{fn:data($Termino1/ter:plazo)}</ter:plazo>
                                    <ter:frecuenciaPlazo>
                                        {
                                            if ($Termino1/ter:frecuenciaPlazo/cat:Id)
                                            then <cat:Id>{fn:data($Termino1/ter:frecuenciaPlazo/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:frecuenciaPlazo/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Termino1/ter:frecuenciaPlazo/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:frecuenciaPlazo/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Termino1/ter:frecuenciaPlazo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:frecuenciaPlazo/cat:estatus)
                                            then <cat:estatus>{fn:data($Termino1/ter:frecuenciaPlazo/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:frecuenciaPlazo/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Termino1/ter:frecuenciaPlazo/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </ter:frecuenciaPlazo>
                                    <ter:fechaVencimiento>{fn:data($Termino1/ter:fechaVencimiento)}</ter:fechaVencimiento>
                                    <ter:moneda>
                                        {
                                            if ($Termino1/ter:moneda/cat:Id)
                                            then <cat:Id>{fn:data($Termino1/ter:moneda/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:moneda/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Termino1/ter:moneda/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:moneda/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Termino1/ter:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:moneda/cat:estatus)
                                            then <cat:estatus>{fn:data($Termino1/ter:moneda/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:moneda/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Termino1/ter:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </ter:moneda>
                                    <ter:monto>{fn:data($Termino1/ter:monto)}</ter:monto>
                                    <ter:tasa>{fn:data($Termino1/ter:tasa)}</ter:tasa>
                                    <ter:tipoTasa>
                                        {
                                            if ($Termino1/ter:tipoTasa/cat:Id)
                                            then <cat:Id>{fn:data($Termino1/ter:tipoTasa/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:tipoTasa/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Termino1/ter:tipoTasa/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:tipoTasa/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Termino1/ter:tipoTasa/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:tipoTasa/cat:estatus)
                                            then <cat:estatus>{fn:data($Termino1/ter:tipoTasa/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:tipoTasa/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Termino1/ter:tipoTasa/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </ter:tipoTasa>
                                    <ter:fecha>{fn:data($Termino1/ter:fecha)}</ter:fecha>
                                    <ter:frecuenciaRevision>{fn:data($Termino1/ter:frecuenciaRevision)}</ter:frecuenciaRevision>
                                    <ter:tipoFrecuenciaRevision>
                                        {
                                            if ($Termino1/ter:tipoFrecuenciaRevision/cat:Id)
                                            then <cat:Id>{fn:data($Termino1/ter:tipoFrecuenciaRevision/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:tipoFrecuenciaRevision/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Termino1/ter:tipoFrecuenciaRevision/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:tipoFrecuenciaRevision/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Termino1/ter:tipoFrecuenciaRevision/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:tipoFrecuenciaRevision/cat:estatus)
                                            then <cat:estatus>{fn:data($Termino1/ter:tipoFrecuenciaRevision/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:tipoFrecuenciaRevision/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Termino1/ter:tipoFrecuenciaRevision/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </ter:tipoFrecuenciaRevision>
                                    <ter:fechaInicioRevision>{fn:data($Termino1/ter:fechaInicioRevision)}</ter:fechaInicioRevision>
                                    <ter:frecuenciaPagoInteres>{fn:data($Termino1/ter:frecuenciaPagoInteres)}</ter:frecuenciaPagoInteres>
                                    <ter:tipoFrecuenciaPagoInteres>
                                        {
                                            if ($Termino1/ter:tipoFrecuenciaPagoInteres/cat:Id)
                                            then <cat:Id>{fn:data($Termino1/ter:tipoFrecuenciaPagoInteres/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:tipoFrecuenciaPagoInteres/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Termino1/ter:tipoFrecuenciaPagoInteres/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:tipoFrecuenciaPagoInteres/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Termino1/ter:tipoFrecuenciaPagoInteres/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:tipoFrecuenciaPagoInteres/cat:estatus)
                                            then <cat:estatus>{fn:data($Termino1/ter:tipoFrecuenciaPagoInteres/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:tipoFrecuenciaPagoInteres/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Termino1/ter:tipoFrecuenciaPagoInteres/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </ter:tipoFrecuenciaPagoInteres>
                                    <ter:fechaInicioPagoInteres>{fn:data($Termino1/ter:fechaInicioPagoInteres)}</ter:fechaInicioPagoInteres>
                                    <ter:frecuenciaAmortizacion>{fn:data($Termino1/ter:frecuenciaAmortizacion)}</ter:frecuenciaAmortizacion>
                                    <ter:tipoFrecuenciaAmortizacion>
                                        {
                                            if ($Termino1/ter:tipoFrecuenciaAmortizacion/cat:Id)
                                            then <cat:Id>{fn:data($Termino1/ter:tipoFrecuenciaAmortizacion/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:tipoFrecuenciaAmortizacion/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Termino1/ter:tipoFrecuenciaAmortizacion/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:tipoFrecuenciaAmortizacion/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Termino1/ter:tipoFrecuenciaAmortizacion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:tipoFrecuenciaAmortizacion/cat:estatus)
                                            then <cat:estatus>{fn:data($Termino1/ter:tipoFrecuenciaAmortizacion/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:tipoFrecuenciaAmortizacion/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Termino1/ter:tipoFrecuenciaAmortizacion/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </ter:tipoFrecuenciaAmortizacion>
                                    <ter:mora>{fn:data($Termino1/ter:mora)}</ter:mora>
                                    <ter:porcentajeCobertura>{fn:data($Termino1/ter:porcentajeCobertura)}</ter:porcentajeCobertura>
                                    <ter:seAplicanRecursosConcesion>{fn:data($Termino1/ter:seAplicanRecursosConcesion)}</ter:seAplicanRecursosConcesion>
                                    <ter:seAplicanRecursosExternos>{fn:data($Termino1/ter:seAplicanRecursosExternos)}</ter:seAplicanRecursosExternos>
                                    <ter:tipoContraparte>{fn:data($Termino1/ter:tipoContraparte)}</ter:tipoContraparte>
                                    <ter:montoMinimoDesembolso>{fn:data($Termino1/ter:montoMinimoDesembolso)}</ter:montoMinimoDesembolso>
                                    <ter:montoMaximoDesembolso>{fn:data($Termino1/ter:montoMaximoDesembolso)}</ter:montoMaximoDesembolso>
                                    <ter:tasaMinimaDesembolso>{fn:data($Termino1/ter:tasaMinimaDesembolso)}</ter:tasaMinimaDesembolso>
                                    <ter:tasaMaximaDesembolso>{fn:data($Termino1/ter:tasaMaximaDesembolso)}</ter:tasaMaximaDesembolso>
                                    <ter:estadoTCC>
                                        {
                                            if ($Termino1/ter:estadoTCC/atr:id)
                                            then <atr:id>{fn:data($Termino1/ter:estadoTCC/atr:id)}</atr:id>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:estadoTCC/atr:descripcion)
                                            then <atr:descripcion>{fn:data($Termino1/ter:estadoTCC/atr:descripcion)}</atr:descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:estadoTCC/atr:descripcionCorta)
                                            then <atr:descripcionCorta>{fn:data($Termino1/ter:estadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:estadoTCC/atr:estatus)
                                            then <atr:estatus>{fn:data($Termino1/ter:estadoTCC/atr:estatus)}</atr:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:estadoTCC/atr:codigoExterno)
                                            then <atr:codigoExterno>{fn:data($Termino1/ter:estadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:estadoTCC/atr:esSubEstado)
                                            then <atr:esSubEstado>{fn:data($Termino1/ter:estadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                                            else ()
                                        }
                                    </ter:estadoTCC>
                                    <ter:subEstadoTCC>
                                        {
                                            if ($Termino1/ter:subEstadoTCC/atr:id)
                                            then <atr:id>{fn:data($Termino1/ter:subEstadoTCC/atr:id)}</atr:id>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:subEstadoTCC/atr:descripcion)
                                            then <atr:descripcion>{fn:data($Termino1/ter:subEstadoTCC/atr:descripcion)}</atr:descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:subEstadoTCC/atr:descripcionCorta)
                                            then <atr:descripcionCorta>{fn:data($Termino1/ter:subEstadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:subEstadoTCC/atr:estatus)
                                            then <atr:estatus>{fn:data($Termino1/ter:subEstadoTCC/atr:estatus)}</atr:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:subEstadoTCC/atr:codigoExterno)
                                            then <atr:codigoExterno>{fn:data($Termino1/ter:subEstadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                                            else ()
                                        }
                                        {
                                            if ($Termino1/ter:subEstadoTCC/atr:esSubEstado)
                                            then <atr:esSubEstado>{fn:data($Termino1/ter:subEstadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                                            else ()
                                        }
                                    </ter:subEstadoTCC>
                                    <ter:fechaRegistro>{fn:data($Termino1/ter:fechaRegistro)}</ter:fechaRegistro>
                                    <ter:estado>{fn:data($Termino1/ter:estado)}</ter:estado>
                                    <ter:terminoEnmendado>{fn:data($Termino1/ter:terminoEnmendado)}</ter:terminoEnmendado>
                                    <ter:fechaEnmienda>{fn:data($Termino1/ter:fechaEnmienda)}</ter:fechaEnmienda>
                                    {
                                        for $configAtributo1 in $Termino1/ter:configAtributo
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
                                        if ($Termino1/ter:Accion)
                                        then <ter:Accion>{fn:data($Termino1/ter:Accion)}</ter:Accion>
                                        else ()
                                    }
                                    {
                                        for $Contraparte1 in $Termino1/ter:Contraparte
                                        return 
                                        <ter:Contraparte>
                                            {
                                                if ($Contraparte1/atr:id)
                                                then <atr:id>{fn:data($Contraparte1/atr:id)}</atr:id>
                                                else ()
                                            }
                                            {
                                                if ($Contraparte1/atr:descripcion)
                                                then <atr:descripcion>{fn:data($Contraparte1/atr:descripcion)}</atr:descripcion>
                                                else ()
                                            }
                                            {
                                                if ($Contraparte1/atr:estatus)
                                                then <atr:estatus>{fn:data($Contraparte1/atr:estatus)}</atr:estatus>
                                                else ()
                                            }
                                        </ter:Contraparte>
                                    }
                                </lin1:Termino>
                            }
                            {
                                for $Comision1 in $LineaCredito1/lin1:Comision
                                return 
                                <lin1:Comision>
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
                                            <com1:tipoTasa>
                                                <cat:codigoExterno>X</cat:codigoExterno>
                                            </com1:tipoTasa>
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
                                        for $configAtributo1 in $Comision1/com1:configAtributo
                                        return 
                                        <com1:configAtributo>
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
                                        </com1:configAtributo>
                                    }
                                    {
                                        if ($Comision1/com1:Accion)
                                        then <com1:Accion>{fn:data($Comision1/com1:Accion)}</com1:Accion>
                                        else ()
                                    }
                                </lin1:Comision>
                            }
                            {
                                for $Fuente1 in $LineaCredito1/lin1:Fuente
                                return 
                                <lin1:Fuente>
                                    <lin1:idFuente>{fn:data($Fuente1/lin1:idFuente)}</lin1:idFuente>
                                    <lin1:idLineaCredito>{fn:data($Fuente1/lin1:idLineaCredito)}</lin1:idLineaCredito>
                                    <lin1:idLineaPasiva>{fn:data($Fuente1/lin1:idLineaPasiva)}</lin1:idLineaPasiva>
                                    <lin1:codigoLineaPasiva>{fn:data($Fuente1/lin1:codigoLineaPasiva)}</lin1:codigoLineaPasiva>
                                    <lin1:Descripcion>{fn:data($Fuente1/lin1:Descripcion)}</lin1:Descripcion>
                                    <lin1:FechaObtenido>{fn:data($Fuente1/lin1:FechaObtenido)}</lin1:FechaObtenido>
                                    <lin1:MontoAsignado>{fn:data($Fuente1/lin1:MontoAsignado)}</lin1:MontoAsignado>
                                    <lin1:NumeroAsignacion>{fn:data($Fuente1/lin1:NumeroAsignacion)}</lin1:NumeroAsignacion>
                                    <lin1:Comentario>{fn:data($Fuente1/lin1:Comentario)}</lin1:Comentario>
                                    <lin1:idContrato>{fn:data($Fuente1/lin1:idContrato)}</lin1:idContrato>
                                    <lin1:FechaRegistro>{fn:data($Fuente1/lin1:FechaRegistro)}</lin1:FechaRegistro>
                                    <lin1:Estado>{fn:data($Fuente1/lin1:Estado)}</lin1:Estado>
                                </lin1:Fuente>
                            }
                        </apr:LineaCredito>
                    }
                </lin:Aprobacion>
            else ()
        }
        {
            if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:montoAprobacion)
            then <lin:montoAprobacion>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:montoAprobacion)}</lin:montoAprobacion>
            else ()
        }    </lin:CrearCommitmentFLEXCUBERequest>
};

local:funcConsultarcomision_to_crearcommitment($consultarComisionByIdLineaCredito_OutputVariable.response, $consultarLineaCreditoByIdLineaCredito_OutputVariable.response)
