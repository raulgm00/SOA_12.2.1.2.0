xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ActualiizarClientes";
(:: import schema at "../XSD/ActualiizarClientes_table.xsd" ::)

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $ActualizarClienteRequest as element() (:: schema-element(ns1:ActualizarClienteRequest) ::) external;

declare function local:func($ActualizarClienteRequest as element() (:: schema-element(ns1:ActualizarClienteRequest) ::)) as element() (:: schema-element(ns2:ClientesCollection) ::) {
    <ns2:ClientesCollection>
        <ns2:Clientes>
            <ns2:idCliente>{fn:data($ActualizarClienteRequest/ns1:Cliente/cli:idCliente)}</ns2:idCliente>
            {
                if ($ActualizarClienteRequest/ns1:Cliente/cli:idFacturador)
                then <ns2:idFlexcube>{fn:data($ActualizarClienteRequest/ns1:Cliente/cli:idFacturador)}</ns2:idFlexcube>
                else ()
            }
            {
                if ($ActualizarClienteRequest/ns1:Cliente/cli:razonSocial)
                then <ns2:razonSocial>{fn:data($ActualizarClienteRequest/ns1:Cliente/cli:razonSocial)}</ns2:razonSocial>
                else ()
            }
            {
                if ($ActualizarClienteRequest/ns1:Cliente/cli:abreviatura)
                then <ns2:abreviatura>{fn:data($ActualizarClienteRequest/ns1:Cliente/cli:abreviatura)}</ns2:abreviatura>
                else ()
            }
            {
                if ($ActualizarClienteRequest/ns1:Cliente/cli:tipoPersona/cat:Id)
                then <ns2:tipoPersona>{fn:data($ActualizarClienteRequest/ns1:Cliente/cli:tipoPersona/cat:Id)}</ns2:tipoPersona>
                else ()
            }
            {
                    if ($ActualizarClienteRequest/ns1:Cliente/cli:tipoCliente/cat:Id)
                    then <ns2:tipoCliente>{fn:data($ActualizarClienteRequest/ns1:Cliente/cli:tipoCliente/cat:Id)}</ns2:tipoCliente>
                    else ()

            }
            {
                if ($ActualizarClienteRequest/ns1:Cliente/cli:sector/cat:Id)
                then <ns2:sector>{fn:data($ActualizarClienteRequest/ns1:Cliente/cli:sector/cat:Id)}</ns2:sector>
                else ()
            }
            {
                if ($ActualizarClienteRequest/ns1:Cliente/cli:tipoInstitucion/cat:Id)
                then <ns2:tipoInstitucion>{fn:data($ActualizarClienteRequest/ns1:Cliente/cli:tipoInstitucion/cat:Id)}</ns2:tipoInstitucion>
                else ()
            }
            {
                if ($ActualizarClienteRequest/ns1:Cliente/cli:pais/cat:Id)
                then <ns2:pais>{fn:data($ActualizarClienteRequest/ns1:Cliente/cli:pais/cat:Id)}</ns2:pais>
                else ()
            }
            {
                if ($ActualizarClienteRequest/ns1:Cliente/cli:grupoEconomico/cat:Id)
                then <ns2:grupoEconomico>{fn:data($ActualizarClienteRequest/ns1:Cliente/cli:grupoEconomico/cat:Id)}</ns2:grupoEconomico>
                else ()
            }
            {
                if ($ActualizarClienteRequest/ns1:Cliente/cli:tipoIdentificacion)
                then <ns2:tipoIdentificacion>{fn:data($ActualizarClienteRequest/ns1:Cliente/cli:tipoIdentificacion)}</ns2:tipoIdentificacion>
                else ()
            }
            {
                if ($ActualizarClienteRequest/ns1:Cliente/cli:numeroIdentificacion)
                then <ns2:numeroIdentificacion>{fn:data($ActualizarClienteRequest/ns1:Cliente/cli:numeroIdentificacion)}</ns2:numeroIdentificacion>
                else ()
            }
            {
                if ($ActualizarClienteRequest/ns1:Cliente/cli:oficina/cat:Id)
                then <ns2:oficina>{fn:data($ActualizarClienteRequest/ns1:Cliente/cli:oficina/cat:Id)}</ns2:oficina>
                else ()
            }
            {
                if ($ActualizarClienteRequest/ns1:Cliente/cli:fechaRegistro)
                then <ns2:fechaRegistro>{fn:data($ActualizarClienteRequest/ns1:Cliente/cli:fechaRegistro)}</ns2:fechaRegistro>
                else ()
            }
            {
                if ($ActualizarClienteRequest/ns1:Cliente/cli:fechaAprobacion)
                then <ns2:fechaAprobacion>{fn:data($ActualizarClienteRequest/ns1:Cliente/cli:fechaAprobacion)}</ns2:fechaAprobacion>
                else ()
            }
            {
                if ($ActualizarClienteRequest/ns1:Cliente/cli:ejecutivo)
                then <ns2:ejecutivo>{fn:data($ActualizarClienteRequest/ns1:Cliente/cli:ejecutivo)}</ns2:ejecutivo>
                else ()
            }
            {
                if ($ActualizarClienteRequest/ns1:Cliente/cli:comentarioAprobacion)
                then <ns2:comentarioAprobacion>{fn:data($ActualizarClienteRequest/ns1:Cliente/cli:comentarioAprobacion)}</ns2:comentarioAprobacion>
                else ()
            }
            {
                if ($ActualizarClienteRequest/ns1:Cliente/cli:status)
                then <ns2:banEstatus>{fn:data($ActualizarClienteRequest/ns1:Cliente/cli:status)}</ns2:banEstatus>
                else ()
            }
            {
                if ($ActualizarClienteRequest/ns1:Cliente/cli:fechaBaja)
                then <ns2:fechaBaja>{fn:data($ActualizarClienteRequest/ns1:Cliente/cli:fechaBaja)}</ns2:fechaBaja>
                else ()
            }
            {
                if ($ActualizarClienteRequest/ns1:Cliente/cli:diaPago)
                then <ns2:diaPago>{fn:data($ActualizarClienteRequest/ns1:Cliente/cli:diaPago)}</ns2:diaPago>
                else ()
            }
            {
                if ($ActualizarClienteRequest/ns1:Cliente/cli:deteriorado)
                then <ns2:estaDeteriorado>{
                  if($ActualizarClienteRequest/ns1:Cliente/cli:deteriorado = true())
                  then (1)
                  else(0)}</ns2:estaDeteriorado>
                else ()
            }
            {
                if ($ActualizarClienteRequest/ns1:Cliente/cli:SCR/cat:Id)
                then <ns2:idTcaScr>{fn:data($ActualizarClienteRequest/ns1:Cliente/cli:SCR/cat:Id)}</ns2:idTcaScr>
                else ()
            }
            {
                if ($ActualizarClienteRequest/ns1:Cliente/cli:perspectiva/cat:Id)
                then <ns2:idTcaPerspectiva>{fn:data($ActualizarClienteRequest/ns1:Cliente/cli:perspectiva/cat:Id)}</ns2:idTcaPerspectiva>
                else ()
            }
            {
                if ($ActualizarClienteRequest/ns1:Cliente/cli:requiereEnvioAutomatico)
                then <ns2:requiereEnvioAutomatico>{fn:data($ActualizarClienteRequest/ns1:Cliente/cli:requiereEnvioAutomatico)}</ns2:requiereEnvioAutomatico>
                else ()
            }
            {
                if ($ActualizarClienteRequest/ns1:Cliente/cli:direccion)
                then <ns2:direccion>{fn:data($ActualizarClienteRequest/ns1:Cliente/cli:direccion)}</ns2:direccion>
                else ()
            }
            {
                if ($ActualizarClienteRequest/ns1:Cliente/cli:bicCode)
                then <ns2:bicCode>{fn:data($ActualizarClienteRequest/ns1:Cliente/cli:bicCode)}</ns2:bicCode>
                else ()
            }</ns2:Clientes>
    </ns2:ClientesCollection>
};

local:func($ActualizarClienteRequest)
