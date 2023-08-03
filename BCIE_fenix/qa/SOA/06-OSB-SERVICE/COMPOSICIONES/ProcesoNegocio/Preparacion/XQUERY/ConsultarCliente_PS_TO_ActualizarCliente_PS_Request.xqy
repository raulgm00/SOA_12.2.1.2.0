xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $ConsultarClienteResponse as element() (:: schema-element(ns1:ConsultarClienteResponse) ::) external;
declare variable $idOperacion as element() external;

declare function local:func($ConsultarClienteResponse as element() (:: schema-element(ns1:ConsultarClienteResponse) ::), 
                            $idOperacion as element()) 
                            as element() (:: schema-element(ns1:ActualizarClienteRequest) ::) {
    <ns1:ActualizarClienteRequest>
        <ns1:Cliente>
            <cli:idCliente>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:idCliente)}</cli:idCliente>
            {
                if ($ConsultarClienteResponse/ns1:Cliente/cli:idFacturador)
                then <cli:idFacturador>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:idFacturador)}</cli:idFacturador>
                else ()
            }
            {
                if ($ConsultarClienteResponse/ns1:Cliente/cli:razonSocial)
                then <cli:razonSocial>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:razonSocial)}</cli:razonSocial>
                else ()
            }
            {
                if ($ConsultarClienteResponse/ns1:Cliente/cli:abreviatura)
                then <cli:abreviatura>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:abreviatura)}</cli:abreviatura>
                else ()
            }
            <cli:tipoPersona>
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:tipoPersona/cat:Id)
                    then <cat:Id>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:tipoPersona/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:tipoPersona/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:tipoPersona/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:tipoPersona/cat:DescripcionCorta)
                    then <cat:DescripcionCorta>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:tipoPersona/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    else ()
                }
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:tipoPersona/cat:estatus)
                    then <cat:estatus>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:tipoPersona/cat:estatus)}</cat:estatus>
                    else ()
                }
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:tipoPersona/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:tipoPersona/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </cli:tipoPersona>
            {
                if ($ConsultarClienteResponse/ns1:Cliente/cli:tipoCliente)
                then <cli:tipoCliente>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:tipoCliente)}</cli:tipoCliente>
                else ()
            }
            <cli:sector>
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:sector/cat:Id)
                    then <cat:Id>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:sector/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:sector/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:sector/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:sector/cat:DescripcionCorta)
                    then <cat:DescripcionCorta>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:sector/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    else ()
                }
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:sector/cat:estatus)
                    then <cat:estatus>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:sector/cat:estatus)}</cat:estatus>
                    else ()
                }
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:sector/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:sector/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </cli:sector>
            <cli:tipoInstitucion>
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:tipoInstitucion/cat:Id)
                    then <cat:Id>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:tipoInstitucion/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:tipoInstitucion/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:tipoInstitucion/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:tipoInstitucion/cat:DescripcionCorta)
                    then <cat:DescripcionCorta>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:tipoInstitucion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    else ()
                }
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:tipoInstitucion/cat:estatus)
                    then <cat:estatus>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:tipoInstitucion/cat:estatus)}</cat:estatus>
                    else ()
                }
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:tipoInstitucion/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:tipoInstitucion/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </cli:tipoInstitucion>
            <cli:pais>
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:pais/cat:Id)
                    then <cat:Id>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:pais/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:pais/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:pais/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:pais/cat:DescripcionCorta)
                    then <cat:DescripcionCorta>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:pais/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    else ()
                }
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:pais/cat:estatus)
                    then <cat:estatus>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:pais/cat:estatus)}</cat:estatus>
                    else ()
                }
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:pais/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:pais/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </cli:pais>
            <cli:grupoEconomico>
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:grupoEconomico/cat:Id)
                    then <cat:Id>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:grupoEconomico/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:grupoEconomico/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:grupoEconomico/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:grupoEconomico/cat:DescripcionCorta)
                    then <cat:DescripcionCorta>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:grupoEconomico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    else ()
                }
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:grupoEconomico/cat:estatus)
                    then <cat:estatus>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:grupoEconomico/cat:estatus)}</cat:estatus>
                    else ()
                }
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:grupoEconomico/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:grupoEconomico/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </cli:grupoEconomico>
            {
                if ($ConsultarClienteResponse/ns1:Cliente/cli:tipoIdentificacion)
                then <cli:tipoIdentificacion>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:tipoIdentificacion)}</cli:tipoIdentificacion>
                else ()
            }
            {
                if ($ConsultarClienteResponse/ns1:Cliente/cli:numeroIdentificacion)
                then <cli:numeroIdentificacion>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:numeroIdentificacion)}</cli:numeroIdentificacion>
                else ()
            }
            {
                if ($ConsultarClienteResponse/ns1:Cliente/cli:direccion)
                then <cli:direccion>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:direccion)}</cli:direccion>
                else ()
            }
            {
                if ($ConsultarClienteResponse/ns1:Cliente/cli:telefono)
                then <cli:telefono>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:telefono)}</cli:telefono>
                else ()
            }
            {
                if ($ConsultarClienteResponse/ns1:Cliente/cli:fax)
                then <cli:fax>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:fax)}</cli:fax>
                else ()
            }
            {
                if ($ConsultarClienteResponse/ns1:Cliente/cli:email)
                then <cli:email>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:email)}</cli:email>
                else ()
            }
            {
                if ($ConsultarClienteResponse/ns1:Cliente/cli:ciudad)
                then <cli:ciudad>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:ciudad)}</cli:ciudad>
                else ()
            }
            <cli:oficina>
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:oficina/cat:Id)
                    then <cat:Id>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:oficina/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:oficina/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:oficina/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:oficina/cat:DescripcionCorta)
                    then <cat:DescripcionCorta>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:oficina/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    else ()
                }
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:oficina/cat:estatus)
                    then <cat:estatus>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:oficina/cat:estatus)}</cat:estatus>
                    else ()
                }
                {
                    if ($ConsultarClienteResponse/ns1:Cliente/cli:oficina/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:oficina/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </cli:oficina>
            {
                if ($ConsultarClienteResponse/ns1:Cliente/cli:grupoEmpresarial)
                then <cli:grupoEmpresarial>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:grupoEmpresarial)}</cli:grupoEmpresarial>
                else ()
            }
            {
                if ($ConsultarClienteResponse/ns1:Cliente/cli:fechaRegistro)
                then <cli:fechaRegistro>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:fechaRegistro)}</cli:fechaRegistro>
                else ()
            }
            {
                if ($ConsultarClienteResponse/ns1:Cliente/cli:fechaAprobacion)
                then <cli:fechaAprobacion>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:fechaAprobacion)}</cli:fechaAprobacion>
                else ()
            }
            {
                if ($ConsultarClienteResponse/ns1:Cliente/cli:ejecutivo)
                then <cli:ejecutivo>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:ejecutivo)}</cli:ejecutivo>
                else ()
            }
            <cli:Contactos>
                <cli:Contacto>
                    <cli:idContacto>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:Contactos/cli:Contacto/cli:idContacto)}</cli:idContacto>
                    <cli:idCliente>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:Contactos/cli:Contacto/cli:idCliente)}</cli:idCliente>
                    <cli:nombre>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:Contactos/cli:Contacto/cli:nombre)}</cli:nombre>
                    <cli:telefono>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:Contactos/cli:Contacto/cli:telefono)}</cli:telefono>
                    <cli:correoElectronico>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:Contactos/cli:Contacto/cli:correoElectronico)}</cli:correoElectronico>
                    <cli:cargo>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:Contactos/cli:Contacto/cli:cargo)}</cli:cargo>
                    <cli:fechaRegistro>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:Contactos/cli:Contacto/cli:fechaRegistro)}</cli:fechaRegistro>
                    <cli:idContactosClientes>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:Contactos/cli:Contacto/cli:idContactosClientes)}</cli:idContactosClientes>
                </cli:Contacto>
            </cli:Contactos>
            <cli:InformacionCorrecta>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:InformacionCorrecta)}</cli:InformacionCorrecta>
            <cli:ActualizacionPermitida>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:ActualizacionPermitida)}</cli:ActualizacionPermitida>
            {
                if ($ConsultarClienteResponse/ns1:Cliente/cli:comentarioAprobacion)
                then <cli:comentarioAprobacion>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:comentarioAprobacion)}</cli:comentarioAprobacion>
                else ()
            }
            {
                if ($ConsultarClienteResponse/ns1:Cliente/cli:revisadoAprobacion)
                then <cli:revisadoAprobacion>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:revisadoAprobacion)}</cli:revisadoAprobacion>
                else ()
            }
            {
                if ($ConsultarClienteResponse/ns1:Cliente/cli:status)
                then <cli:status>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:status)}</cli:status>
                else ()
            }
            {
                if ($ConsultarClienteResponse/ns1:Cliente/cli:fechaBaja)
                then <cli:fechaBaja>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:fechaBaja)}</cli:fechaBaja>
                else ()
            }
        </ns1:Cliente>
        <ns1:idOperacion>{fn:data($idOperacion)}</ns1:idOperacion></ns1:ActualizarClienteRequest>
};

local:func($ConsultarClienteResponse, $idOperacion)
