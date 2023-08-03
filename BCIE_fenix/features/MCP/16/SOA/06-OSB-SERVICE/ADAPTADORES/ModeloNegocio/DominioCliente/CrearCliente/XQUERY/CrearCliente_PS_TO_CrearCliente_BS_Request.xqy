xquery version "1.0" encoding "UTF-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearCliente";
(:: import schema at "../XSD/CrearCliente_table.xsd" ::)

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace catBO = "http://www.bcie.org/CatalogoBO";

declare variable $ClienteRequest as element() (:: schema-element(ns1:CrearClienteRequest) ::) external;

declare function local:func($ClienteRequest as element() (:: schema-element(ns1:CrearClienteRequest) ::)) as element() (:: schema-element(ns2:ClientesCollection) ::) {
    <ns2:ClientesCollection>
        <ns2:Clientes>
            <ns2:idCliente> </ns2:idCliente>
            {
                if ($ClienteRequest/ns1:Cliente/cli:idFacturador)
                then <ns2:idFlexcube>{fn:data($ClienteRequest/ns1:Cliente/cli:idFacturador)}</ns2:idFlexcube>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/cli:razonSocial)
                then <ns2:razonSocial>{fn:data($ClienteRequest/ns1:Cliente/cli:razonSocial)}</ns2:razonSocial>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/cli:abreviatura)
                then <ns2:abreviatura>{fn:data($ClienteRequest/ns1:Cliente/cli:abreviatura)}</ns2:abreviatura>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/cli:tipoPersona/catBO:Id)
                then <ns2:tipoPersona>{fn:data($ClienteRequest/ns1:Cliente/cli:tipoPersona/catBO:Id)}</ns2:tipoPersona>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/cli:tipoCliente)
                then 
                    if ($ClienteRequest/ns1:Cliente/cli:tipoCliente/catBO:Id)
                    then <ns2:tipoCliente>{fn:data($ClienteRequest/ns1:Cliente/cli:tipoCliente/catBO:Id)}</ns2:tipoCliente>
                    else ()
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/cli:sector/catBO:Id)
                then <ns2:sector>{fn:data($ClienteRequest/ns1:Cliente/cli:sector/catBO:Id)}</ns2:sector>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/cli:tipoInstitucion/catBO:Id)
                then <ns2:tipoInstitucion>{fn:data($ClienteRequest/ns1:Cliente/cli:tipoInstitucion/catBO:Id)}</ns2:tipoInstitucion>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/cli:pais/catBO:Id)
                then <ns2:pais>{fn:data($ClienteRequest/ns1:Cliente/cli:pais/catBO:Id)}</ns2:pais>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/cli:grupoEconomico/catBO:Id)
                then <ns2:grupoEconomico>{fn:data($ClienteRequest/ns1:Cliente/cli:grupoEconomico/catBO:Id)}</ns2:grupoEconomico>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/cli:tipoIdentificacion)
                then <ns2:tipoIdentificacion>{fn:data($ClienteRequest/ns1:Cliente/cli:tipoIdentificacion)}</ns2:tipoIdentificacion>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/cli:numeroIdentificacion)
                then <ns2:numeroIdentificacion>{fn:data($ClienteRequest/ns1:Cliente/cli:numeroIdentificacion)}</ns2:numeroIdentificacion>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/cli:oficina/catBO:Id)
                then <ns2:oficina>{fn:data($ClienteRequest/ns1:Cliente/cli:oficina/catBO:Id)}</ns2:oficina>
                else ()
            }
            <ns2:fechaRegistro>{fn:current-date()}</ns2:fechaRegistro>
            {
                if ($ClienteRequest/ns1:Cliente/cli:ejecutivo)
                then <ns2:ejecutivo>{fn:data($ClienteRequest/ns1:Cliente/cli:ejecutivo)}</ns2:ejecutivo>
                else ()
            }
            <ns2:autorizo></ns2:autorizo>
            <ns2:banEstatus>1</ns2:banEstatus>
            {
                if ($ClienteRequest/ns1:Cliente/cli:diaPago)
                then <ns2:diaPago>{fn:data($ClienteRequest/ns1:Cliente/cli:diaPago)}</ns2:diaPago>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/cli:deteriorado)
                then <ns2:estaDeteriorado>{
                if ($ClienteRequest/ns1:Cliente/cli:deteriorado = true())
                then (1)
                else (0)
                }</ns2:estaDeteriorado>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/cli:SCR/catBO:Id)
                then <ns2:idTcaScr>{fn:data($ClienteRequest/ns1:Cliente/cli:SCR/catBO:Id)}</ns2:idTcaScr>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/cli:perspectiva/catBO:Id)
                then <ns2:idTcaPerspectiva>{fn:data($ClienteRequest/ns1:Cliente/cli:perspectiva/catBO:Id)}</ns2:idTcaPerspectiva>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/cli:requiereEnvioAutomatico)
                then <ns2:requiereEnvioAutomatico>{fn:data($ClienteRequest/ns1:Cliente/cli:requiereEnvioAutomatico)}</ns2:requiereEnvioAutomatico>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/cli:direccion)
                then <ns2:direccion>{fn:data($ClienteRequest/ns1:Cliente/cli:direccion)}</ns2:direccion>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/cli:bicCode)
                then <ns2:bicCode>{fn:data($ClienteRequest/ns1:Cliente/cli:bicCode)}</ns2:bicCode>
                else ()
            }
        </ns2:Clientes>
    </ns2:ClientesCollection>
};

local:func($ClienteRequest)