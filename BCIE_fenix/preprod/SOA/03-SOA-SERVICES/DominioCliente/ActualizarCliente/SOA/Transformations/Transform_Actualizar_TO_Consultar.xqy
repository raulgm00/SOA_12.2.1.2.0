xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace cli="http://www.bcie.org/ClienteMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd"::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli1 = "http://www.bcie.org/ClienteBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $consultarByIdCliente_OutputVariable.response as element() (:: schema-element(cli:ConsultarClienteResponse) ::) external;
declare variable $inputVariable.request as element() (:: schema-element(cli:ActualizarClienteRequest) ::) external;

declare function local:funcTransform_actualizar_to_consultar($consultarByIdCliente_OutputVariable.response as element() (:: schema-element(cli:ConsultarClienteResponse) ::), 
                                                             $inputVariable.request as element() (:: schema-element(cli:ActualizarClienteRequest) ::)) 
                                                             as element() (:: schema-element(cli:ConsultarClienteResponse) ::) {
    <cli:ConsultarClienteResponse>
        {
            for $Cliente in $consultarByIdCliente_OutputVariable.response/cli:Cliente
            return 
            <cli:Cliente>
                <cli1:idCliente>{fn:data($inputVariable.request/cli:Cliente/cli1:idCliente)}</cli1:idCliente>
                {
                    if ($inputVariable.request/cli:Cliente/cli1:idFacturador)
                    then <cli1:idFacturador>{fn:data($inputVariable.request/cli:Cliente/cli1:idFacturador)}</cli1:idFacturador>
                    else <cli1:idFacturador>{fn:data($Cliente/cli1:idFacturador)}</cli1:idFacturador> 
                    
                }
                {
                    if ($inputVariable.request/cli:Cliente/cli1:razonSocial)
                    then <cli1:razonSocial>{fn:data($inputVariable.request/cli:Cliente/cli1:razonSocial)}</cli1:razonSocial>
                    else  <cli1:razonSocial>{fn:data($Cliente/cli1:razonSocial)}</cli1:razonSocial>
                }
                {
                        if ($inputVariable.request/cli:Cliente/cli1:bicCode)
                        then <cli1:bicCode>{fn:data($inputVariable.request/cli:Cliente/cli1:bicCode)}</cli1:bicCode>
                        else  <cli1:bicCode>{fn:data($Cliente/cli1:bicCode)}</cli1:bicCode>
                }
               
                {
                    if ($inputVariable.request/cli:Cliente/cli1:abreviatura)
                    then <cli1:abreviatura>{fn:data($inputVariable.request/cli:Cliente/cli1:abreviatura)}</cli1:abreviatura>
                    else <cli1:abreviatura>{fn:data($Cliente/cli1:abreviatura)}</cli1:abreviatura>
                }
                {
                    if ($inputVariable.request/cli:Cliente/cli1:tipoPersona)
                    then 
                        <cli1:tipoPersona>
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:tipoPersona/cat:Id)
                                then <cat:Id>{fn:data($inputVariable.request/cli:Cliente/cli1:tipoPersona/cat:Id)}</cat:Id>
                                else <cat:Id>{fn:data($Cliente/cli1:tipoPersona/cat:Id)}</cat:Id>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:tipoPersona/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($inputVariable.request/cli:Cliente/cli1:tipoPersona/cat:Descripcion)}</cat:Descripcion>
                                else <cat:Descripcion>{fn:data($Cliente/cli1:tipoPersona/cat:Descripcion)}</cat:Descripcion>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:tipoPersona/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($inputVariable.request/cli:Cliente/cli1:tipoPersona/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else <cat:DescripcionCorta>{fn:data($Cliente/cli1:tipoPersona/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:tipoPersona/cat:estatus)
                                then <cat:estatus>{fn:data($inputVariable.request/cli:Cliente/cli1:tipoPersona/cat:estatus)}</cat:estatus>
                                else <cat:estatus>{fn:data($Cliente/cli1:tipoPersona/cat:estatus)}</cat:estatus>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:tipoPersona/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($inputVariable.request/cli:Cliente/cli1:tipoPersona/cat:codigoExterno)}</cat:codigoExterno>
                                else <cat:codigoExterno>{fn:data($Cliente/cli1:tipoPersona/cat:codigoExterno)}</cat:codigoExterno>
                            }
                        </cli1:tipoPersona>
                    else 
                     <cli1:tipoPersona>
                           <cat:Id>{fn:data($Cliente/cli1:tipoPersona/cat:Id)}</cat:Id>
                           <cat:Descripcion>{fn:data($Cliente/cli1:tipoPersona/cat:Descripcion)}</cat:Descripcion>
                           <cat:DescripcionCorta>{fn:data($Cliente/cli1:tipoPersona/cat:DescripcionCorta)}</cat:DescripcionCorta>
                           <cat:estatus>{fn:data($Cliente/cli1:tipoPersona/cat:estatus)}</cat:estatus>
                           <cat:codigoExterno>{fn:data($Cliente/cli1:tipoPersona/cat:codigoExterno)}</cat:codigoExterno>
                        </cli1:tipoPersona>
                }
                {
                    if ($inputVariable.request/cli:Cliente/cli1:tipoCliente)
                    then 
                        <cli1:tipoCliente>
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:tipoCliente/cat:Id)
                                then <cat:Id>{fn:data($inputVariable.request/cli:Cliente/cli1:tipoCliente/cat:Id)}</cat:Id>
                                else <cat:Id>{fn:data($Cliente/cli1:tipoCliente/cat:Id)}</cat:Id>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:tipoCliente/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($inputVariable.request/cli:Cliente/cli1:tipoCliente/cat:Descripcion)}</cat:Descripcion>
                                else <cat:Descripcion>{fn:data($Cliente/cli1:tipoCliente/cat:Descripcion)}</cat:Descripcion>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:tipoCliente/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($inputVariable.request/cli:Cliente/cli1:tipoCliente/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else <cat:DescripcionCorta>{fn:data($Cliente/cli1:tipoCliente/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:tipoCliente/cat:estatus)
                                then <cat:estatus>{fn:data($inputVariable.request/cli:Cliente/cli1:tipoCliente/cat:estatus)}</cat:estatus>
                                else <cat:estatus>{fn:data($Cliente/cli1:tipoCliente/cat:estatus)}</cat:estatus>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:tipoCliente/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($inputVariable.request/cli:Cliente/cli1:tipoCliente/cat:codigoExterno)}</cat:codigoExterno>
                                else <cat:codigoExterno>{fn:data($Cliente/cli1:tipoCliente/cat:codigoExterno)}</cat:codigoExterno>
                            }
                        </cli1:tipoCliente>
                    else 
                     <cli1:tipoCliente>
                           <cat:Id>{fn:data($Cliente/cli1:tipoCliente/cat:Id)}</cat:Id>
                           <cat:Descripcion>{fn:data($Cliente/cli1:tipoCliente/cat:Descripcion)}</cat:Descripcion>
                           <cat:DescripcionCorta>{fn:data($Cliente/cli1:tipoCliente/cat:DescripcionCorta)}</cat:DescripcionCorta>
                           <cat:estatus>{fn:data($Cliente/cli1:tipoCliente/cat:estatus)}</cat:estatus>
                           <cat:codigoExterno>{fn:data($Cliente/cli1:tipoCliente/cat:codigoExterno)}</cat:codigoExterno>
                        </cli1:tipoCliente>
                }
                {
                    if ($inputVariable.request/cli:Cliente/cli1:sector)
                    then 
                        <cli1:sector>
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:sector/cat:Id)
                                then <cat:Id>{fn:data($inputVariable.request/cli:Cliente/cli1:sector/cat:Id)}</cat:Id>
                                else <cat:Id>{fn:data($Cliente/cli1:sector/cat:Id)}</cat:Id>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:sector/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($inputVariable.request/cli:Cliente/cli1:sector/cat:Descripcion)}</cat:Descripcion>
                                else <cat:Descripcion>{fn:data($Cliente/cli1:sector/cat:Descripcion)}</cat:Descripcion>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:sector/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($inputVariable.request/cli:Cliente/cli1:sector/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else <cat:DescripcionCorta>{fn:data($Cliente/cli1:sector/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:sector/cat:estatus)
                                then <cat:estatus>{fn:data($inputVariable.request/cli:Cliente/cli1:sector/cat:estatus)}</cat:estatus>
                                else <cat:estatus>{fn:data($Cliente/cli1:sector/cat:estatus)}</cat:estatus>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:sector/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($inputVariable.request/cli:Cliente/cli1:sector/cat:codigoExterno)}</cat:codigoExterno>
                                else <cat:codigoExterno>{fn:data($Cliente/cli1:sector/cat:codigoExterno)}</cat:codigoExterno>
                            }
                        </cli1:sector>
                    else  <cli1:sector>
                              <cat:Id>{fn:data($Cliente/cli1:sector/cat:Id)}</cat:Id>
                              <cat:Descripcion>{fn:data($Cliente/cli1:sector/cat:Descripcion)}</cat:Descripcion>
                              <cat:DescripcionCorta>{fn:data($Cliente/cli1:sector/cat:DescripcionCorta)}</cat:DescripcionCorta>
                              <cat:estatus>{fn:data($Cliente/cli1:sector/cat:estatus)}</cat:estatus>
                              <cat:codigoExterno>{fn:data($Cliente/cli1:sector/cat:codigoExterno)}</cat:codigoExterno>
                        </cli1:sector>
                }
                {
                    if ($inputVariable.request/cli:Cliente/cli1:tipoInstitucion)
                    then 
                        <cli1:tipoInstitucion>
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:tipoInstitucion/cat:Id)
                                then <cat:Id>{fn:data($inputVariable.request/cli:Cliente/cli1:tipoInstitucion/cat:Id)}</cat:Id>
                                else <cat:Id>{fn:data($Cliente/cli1:tipoInstitucion/cat:Id)}</cat:Id>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:tipoInstitucion/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($inputVariable.request/cli:Cliente/cli1:tipoInstitucion/cat:Descripcion)}</cat:Descripcion>
                                else <cat:Descripcion>{fn:data($Cliente/cli1:tipoInstitucion/cat:Descripcion)}</cat:Descripcion>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:tipoInstitucion/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($inputVariable.request/cli:Cliente/cli1:tipoInstitucion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else <cat:DescripcionCorta>{fn:data($Cliente/cli1:tipoInstitucion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:tipoInstitucion/cat:estatus)
                                then <cat:estatus>{fn:data($inputVariable.request/cli:Cliente/cli1:tipoInstitucion/cat:estatus)}</cat:estatus>
                                else <cat:estatus>{fn:data($Cliente/cli1:tipoInstitucion/cat:estatus)}</cat:estatus>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:tipoInstitucion/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($inputVariable.request/cli:Cliente/cli1:tipoInstitucion/cat:codigoExterno)}</cat:codigoExterno>
                                else <cat:codigoExterno>{fn:data($Cliente/cli1:tipoInstitucion/cat:codigoExterno)}</cat:codigoExterno>
                            }
                        </cli1:tipoInstitucion>
                    else 
                      <cli1:tipoInstitucion>
                          <cat:Id>{fn:data($Cliente/cli1:tipoInstitucion/cat:Id)}</cat:Id>
                          <cat:Descripcion>{fn:data($Cliente/cli1:tipoInstitucion/cat:Descripcion)}</cat:Descripcion>
                          <cat:DescripcionCorta>{fn:data($Cliente/cli1:tipoInstitucion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                          <cat:estatus>{fn:data($Cliente/cli1:tipoInstitucion/cat:estatus)}</cat:estatus>
                          <cat:codigoExterno>{fn:data($Cliente/cli1:tipoInstitucion/cat:codigoExterno)}</cat:codigoExterno>                         
                        </cli1:tipoInstitucion>
                }
                {
                    if ($inputVariable.request/cli:Cliente/cli1:pais)
                    then 
                        <cli1:pais>
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:pais/cat:Id)
                                then <cat:Id>{fn:data($inputVariable.request/cli:Cliente/cli1:pais/cat:Id)}</cat:Id>
                                else <cat:Id>{fn:data($Cliente/cli1:pais/cat:Id)}</cat:Id>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:pais/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($inputVariable.request/cli:Cliente/cli1:pais/cat:Descripcion)}</cat:Descripcion>
                                else <cat:Descripcion>{fn:data($Cliente/cli1:pais/cat:Descripcion)}</cat:Descripcion>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:pais/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($inputVariable.request/cli:Cliente/cli1:pais/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else <cat:DescripcionCorta>{fn:data($Cliente/cli1:pais/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:pais/cat:estatus)
                                then <cat:estatus>{fn:data($inputVariable.request/cli:Cliente/cli1:pais/cat:estatus)}</cat:estatus>
                                else <cat:estatus>{fn:data($Cliente/cli1:pais/cat:estatus)}</cat:estatus>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:pais/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($inputVariable.request/cli:Cliente/cli1:pais/cat:codigoExterno)}</cat:codigoExterno>
                                else <cat:codigoExterno>{fn:data($Cliente/cli1:pais/cat:codigoExterno)}</cat:codigoExterno>
                            }
                        </cli1:pais>
                    else 
                      <cli1:pais>
                            <cat:Id>{fn:data($Cliente/cli1:pais/cat:Id)}</cat:Id>
                            <cat:Descripcion>{fn:data($Cliente/cli1:pais/cat:Descripcion)}</cat:Descripcion>
                            <cat:DescripcionCorta>{fn:data($Cliente/cli1:pais/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            <cat:estatus>{fn:data($Cliente/cli1:pais/cat:estatus)}</cat:estatus>
                            <cat:codigoExterno>{fn:data($Cliente/cli1:pais/cat:codigoExterno)}</cat:codigoExterno>                         
                        </cli1:pais>
                }
                {
                    if ($inputVariable.request/cli:Cliente/cli1:grupoEconomico)
                    then 
                        <cli1:grupoEconomico>
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:grupoEconomico/cat:Id)
                                then <cat:Id>{fn:data($inputVariable.request/cli:Cliente/cli1:grupoEconomico/cat:Id)}</cat:Id>
                                else <cat:Id>{fn:data($Cliente/cli1:grupoEconomico/cat:Id)}</cat:Id>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:grupoEconomico/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($inputVariable.request/cli:Cliente/cli1:grupoEconomico/cat:Descripcion)}</cat:Descripcion>
                                else <cat:Descripcion>{fn:data($Cliente/cli1:grupoEconomico/cat:Descripcion)}</cat:Descripcion>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:grupoEconomico/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($inputVariable.request/cli:Cliente/cli1:grupoEconomico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else <cat:DescripcionCorta>{fn:data($Cliente/cli1:grupoEconomico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:grupoEconomico/cat:estatus)
                                then <cat:estatus>{fn:data($inputVariable.request/cli:Cliente/cli1:grupoEconomico/cat:estatus)}</cat:estatus>
                                else <cat:estatus>{fn:data($Cliente/cli1:grupoEconomico/cat:estatus)}</cat:estatus>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:grupoEconomico/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($inputVariable.request/cli:Cliente/cli1:grupoEconomico/cat:codigoExterno)}</cat:codigoExterno>
                                else <cat:codigoExterno>{fn:data($Cliente/cli1:grupoEconomico/cat:codigoExterno)}</cat:codigoExterno>
                            }
                        </cli1:grupoEconomico>
                    else   <cli1:grupoEconomico>
                            <cat:Id>{fn:data($Cliente/cli1:grupoEconomico/cat:Id)}</cat:Id>
                           <cat:Descripcion>{fn:data($Cliente/cli1:grupoEconomico/cat:Descripcion)}</cat:Descripcion>
                            <cat:DescripcionCorta>{fn:data($Cliente/cli1:grupoEconomico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            <cat:estatus>{fn:data($Cliente/cli1:grupoEconomico/cat:estatus)}</cat:estatus>
                           <cat:codigoExterno>{fn:data($Cliente/cli1:grupoEconomico/cat:codigoExterno)}</cat:codigoExterno>                           
                        </cli1:grupoEconomico>
                }
                {
                    if ($inputVariable.request/cli:Cliente/cli1:tipoIdentificacion)
                    then <cli1:tipoIdentificacion>{fn:data($inputVariable.request/cli:Cliente/cli1:tipoIdentificacion)}</cli1:tipoIdentificacion>
                    else <cli1:tipoIdentificacion>{fn:data($Cliente/cli1:tipoIdentificacion)}</cli1:tipoIdentificacion>
                }
                {
                    if ($inputVariable.request/cli:Cliente/cli1:numeroIdentificacion)
                    then <cli1:numeroIdentificacion>{fn:data($inputVariable.request/cli:Cliente/cli1:numeroIdentificacion)}</cli1:numeroIdentificacion>
                    else <cli1:numeroIdentificacion>{fn:data($Cliente/cli1:numeroIdentificacion)}</cli1:numeroIdentificacion>
                }
                {
                    if ($inputVariable.request/cli:Cliente/cli1:direccion)
                    then <cli1:direccion>{fn:data($inputVariable.request/cli:Cliente/cli1:direccion)}</cli1:direccion>
                    else <cli1:direccion>{fn:data($Cliente/cli1:direccion)}</cli1:direccion>
                }
                {
                    if ($inputVariable.request/cli:Cliente/cli1:telefono)
                    then <cli1:telefono>{fn:data($inputVariable.request/cli:Cliente/cli1:telefono)}</cli1:telefono>
                    else  <cli1:telefono>{fn:data($Cliente/cli1:telefono)}</cli1:telefono>
                }
                {
                    if ($inputVariable.request/cli:Cliente/cli1:fax)
                    then <cli1:fax>{fn:data($inputVariable.request/cli:Cliente/cli1:fax)}</cli1:fax>
                    else <cli1:fax>{fn:data($Cliente/cli1:fax)}</cli1:fax>
                }
                {
                    if ($inputVariable.request/cli:Cliente/cli1:email)
                    then <cli1:email>{fn:data($inputVariable.request/cli:Cliente/cli1:email)}</cli1:email>
                    else <cli1:email>{fn:data($Cliente/cli1:email)}</cli1:email>
                }
                {
                    if ($inputVariable.request/cli:Cliente/cli1:ciudad)
                    then <cli1:ciudad>{fn:data($inputVariable.request/cli:Cliente/cli1:ciudad)}</cli1:ciudad>
                    else <cli1:ciudad>{fn:data($Cliente/cli1:ciudad)}</cli1:ciudad>
                }
                {
                    if ($inputVariable.request/cli:Cliente/cli1:oficina)
                    then 
                        <cli1:oficina>
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:oficina/cat:Id)
                                then <cat:Id>{fn:data($inputVariable.request/cli:Cliente/cli1:oficina/cat:Id)}</cat:Id>
                                else  <cat:Id>{fn:data($Cliente/cli1:oficina/cat:Id)}</cat:Id>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:oficina/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($inputVariable.request/cli:Cliente/cli1:oficina/cat:Descripcion)}</cat:Descripcion>
                                else <cat:Descripcion>{fn:data($Cliente/cli1:oficina/cat:Descripcion)}</cat:Descripcion>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:oficina/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($inputVariable.request/cli:Cliente/cli1:oficina/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else <cat:DescripcionCorta>{fn:data($Cliente/cli1:oficina/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:oficina/cat:estatus)
                                then <cat:estatus>{fn:data($inputVariable.request/cli:Cliente/cli1:oficina/cat:estatus)}</cat:estatus>
                                else <cat:estatus>{fn:data($Cliente/cli1:oficina/cat:estatus)}</cat:estatus>
                            }
                            {
                                if ($inputVariable.request/cli:Cliente/cli1:oficina/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($inputVariable.request/cli:Cliente/cli1:oficina/cat:codigoExterno)}</cat:codigoExterno>
                                else <cat:codigoExterno>{fn:data($Cliente/cli1:oficina/cat:codigoExterno)}</cat:codigoExterno>
                            }
                        </cli1:oficina>
                    else <cli1:oficina>
                            <cat:Id>{fn:data($Cliente/cli1:oficina/cat:Id)}</cat:Id>
                            <cat:Descripcion>{fn:data($Cliente/cli1:oficina/cat:Descripcion)}</cat:Descripcion>
                            <cat:DescripcionCorta>{fn:data($Cliente/cli1:oficina/cat:DescripcionCorta)}</cat:DescripcionCorta>
                             <cat:estatus>{fn:data($Cliente/cli1:oficina/cat:estatus)}</cat:estatus>
                            <cat:codigoExterno>{fn:data($Cliente/cli1:oficina/cat:codigoExterno)}</cat:codigoExterno>                         
                        </cli1:oficina>
                }
                {
                    if ($inputVariable.request/cli:Cliente/cli1:grupoEmpresarial)
                    then <cli1:grupoEmpresarial>{fn:data($inputVariable.request/cli:Cliente/cli1:grupoEmpresarial)}</cli1:grupoEmpresarial>
                    else <cli1:grupoEmpresarial>{fn:data($Cliente/cli1:grupoEmpresarial)}</cli1:grupoEmpresarial>
                }
                {
                    if ($inputVariable.request/cli:Cliente/cli1:fechaRegistro)
                    then <cli1:fechaRegistro>{fn:data($inputVariable.request/cli:Cliente/cli1:fechaRegistro)}</cli1:fechaRegistro>
                    else <cli1:fechaRegistro>{fn:data($Cliente/cli1:fechaRegistro)}</cli1:fechaRegistro>
                }
                {
                    if ($inputVariable.request/cli:Cliente/cli1:fechaAprobacion)
                    then <cli1:fechaAprobacion>{fn:data($inputVariable.request/cli:Cliente/cli1:fechaAprobacion)}</cli1:fechaAprobacion>
                    else <cli1:fechaAprobacion>{fn:data($Cliente/cli1:fechaAprobacion)}</cli1:fechaAprobacion>
                }
                {
                    if ($inputVariable.request/cli:Cliente/cli1:ejecutivo)
                    then <cli1:ejecutivo>{fn:data($inputVariable.request/cli:Cliente/cli1:ejecutivo)}</cli1:ejecutivo>
                    else <cli1:ejecutivo>{fn:data($Cliente/cli1:ejecutivo)}</cli1:ejecutivo>
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
                            </cli1:Contacto>
                        }
                    </cli1:Contactos>
                }
                <cli1:InformacionCorrecta>{fn:data($Cliente/cli1:InformacionCorrecta)}</cli1:InformacionCorrecta>
                <cli1:ActualizacionPermitida>{fn:data($Cliente/cli1:ActualizacionPermitida)}</cli1:ActualizacionPermitida>
                {
                    if ($inputVariable.request/cli:Cliente/cli1:comentarioAprobacion)
                    then <cli1:comentarioAprobacion>{fn:data($inputVariable.request/cli:Cliente/cli1:comentarioAprobacion)}</cli1:comentarioAprobacion>
                    else <cli1:comentarioAprobacion>{fn:data($Cliente/cli1:comentarioAprobacion)}</cli1:comentarioAprobacion>
                }
                {
                    if ($inputVariable.request/cli:Cliente/cli1:revisadoAprobacion)
                    then <cli1:revisadoAprobacion>{fn:data($inputVariable.request/cli:Cliente/cli1:revisadoAprobacion)}</cli1:revisadoAprobacion>
                    else <cli1:revisadoAprobacion>{fn:data($Cliente/cli1:revisadoAprobacion)}</cli1:revisadoAprobacion>
                }
                {
                    if ($inputVariable.request/cli:Cliente/cli1:status)
                    then <cli1:status>{fn:data($inputVariable.request/cli:Cliente/cli1:status)}</cli1:status>
                    else <cli1:status>{fn:data($Cliente/cli1:status)}</cli1:status>
                }
                {
                    if ($inputVariable.request/cli:Cliente/cli1:fechaBaja)
                    then <cli1:fechaBaja>{fn:data($inputVariable.request/cli:Cliente/cli1:fechaBaja)}</cli1:fechaBaja>
                    else <cli1:fechaBaja>{fn:data($Cliente/cli1:fechaBaja)}</cli1:fechaBaja>
                }
                {
                    if ($inputVariable.request/cli:Cliente/cli1:diaPago)
                    then <cli1:diaPago>{fn:data($inputVariable.request/cli:Cliente/cli1:diaPago)}</cli1:diaPago>
                    else <cli1:diaPago>{fn:data($Cliente/cli1:diaPago)}</cli1:diaPago>
                }
                {
                    if ($inputVariable.request/cli:Cliente/cli1:requiereEnvioAutomatico)
                    then <cli1:requiereEnvioAutomatico>{fn:data($inputVariable.request/cli:Cliente/cli1:requiereEnvioAutomatico)}</cli1:requiereEnvioAutomatico>
                    else <cli1:requiereEnvioAutomatico>{fn:data($Cliente/cli1:requiereEnvioAutomatico)}</cli1:requiereEnvioAutomatico>
                }
            </cli:Cliente>
        }
        {
            for $Operaciones in $consultarByIdCliente_OutputVariable.response/cli:Operaciones
            return 
            <cli:Operaciones>
                <cli1:idOperacion>{fn:data($Operaciones/cli1:idOperacion)}</cli1:idOperacion>
                <cli1:nombre>{fn:data($Operaciones/cli1:nombre)}</cli1:nombre>
                <cli1:cliente>{fn:data($Operaciones/cli1:cliente)}</cli1:cliente>
                <cli1:pais>{fn:data($Operaciones/cli1:pais)}</cli1:pais>
                <cli1:producto>{fn:data($Operaciones/cli1:producto)}</cli1:producto>
                <cli1:responsable>{fn:data($Operaciones/cli1:responsable)}</cli1:responsable>
                <cli1:etapa>{fn:data($Operaciones/cli1:etapa)}</cli1:etapa>
                <cli1:estado>{fn:data($Operaciones/cli1:estado)}</cli1:estado>
            </cli:Operaciones>
        }
        {
            if ($consultarByIdCliente_OutputVariable.response/cli:Resultado)
            then 
                <cli:Resultado>
                    <res:result>{fn:data($consultarByIdCliente_OutputVariable.response/cli:Resultado/res:result)}</res:result>
                    <res:message>{fn:data($consultarByIdCliente_OutputVariable.response/cli:Resultado/res:message)}</res:message>
                    {
                        if ($consultarByIdCliente_OutputVariable.response/cli:Resultado/res:error)
                        then 
                            <res:error>
                                <err:errorCode>{fn:data($consultarByIdCliente_OutputVariable.response/cli:Resultado/res:error/err:errorCode)}</err:errorCode>
                                <err:errorDescription>{fn:data($consultarByIdCliente_OutputVariable.response/cli:Resultado/res:error/err:errorDescription)}</err:errorDescription>
                                <err:errorType>{fn:data($consultarByIdCliente_OutputVariable.response/cli:Resultado/res:error/err:errorType)}</err:errorType>
                            </res:error>
                        else ()
                    }
                </cli:Resultado>
            else ()
        }
     
    </cli:ConsultarClienteResponse>
};

local:funcTransform_actualizar_to_consultar($consultarByIdCliente_OutputVariable.response, $inputVariable.request)
