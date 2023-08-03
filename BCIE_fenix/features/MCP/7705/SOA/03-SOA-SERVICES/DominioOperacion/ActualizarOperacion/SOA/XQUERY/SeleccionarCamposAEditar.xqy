xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $ActualizarOperacionRequest as element() (:: schema-element(ns1:ActualizarOperacionRequest) ::) external;
declare variable $ActualizarOperacionRequestTempo as element() (:: schema-element(ns1:ActualizarOperacionRequest) ::) external;

declare function local:func($ActualizarOperacionRequest as element() (:: schema-element(ns1:ActualizarOperacionRequest) ::),
                          $ActualizarOperacionRequestTempo as element() (:: schema-element(ns1:ActualizarOperacionRequest) ::)) as element() (:: schema-element(ns1:ActualizarOperacionRequest) ::) {
    <ns1:ActualizarOperaccionRequest>
        <ns1:Operacion>
            {
                if (count($ActualizarOperacionRequest/ns1:Operacion/ope:idOperacion)>0)
                then <ope:idOperacion>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:idOperacion)}</ope:idOperacion>
                else <ope:idOperacion>$ActualizarOperacionRequestTempo/ns1:Operacion/ope:idOperacion)</ope:idOperacion>
            }

                
            
            {
                if ($ActualizarOperacionRequest/ns1:Operacion/ope:oficina)
                then <ope:oficina>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:oficina)}</ope:oficina>
                else <ope:oficina>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:oficina)}</ope:oficina>
            }
            {
                if (count($ActualizarOperacionRequest/ns1:Operacion/ope:nombre)>0)
                then <ope:nombre>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:nombre)}</ope:nombre>
                else <ope:nombre>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:nombre)}</ope:nombre>
            }
            <ope:cliente>
				{
					if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:idCliente)
					then <cli:idCliente>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:idCliente)}</cli:idCliente>
					else <cli:idCliente>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:idCliente)}</cli:idCliente>
				}
                {
                    if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:idFacturador)
                    then <cli:idFacturador>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:idFacturador)}</cli:idFacturador>
                    else <cli:idFacturador>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:idFacturador)}</cli:idFacturador>
                }
                {
                    if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:razonSocial)
                    then <cli:razonSocial>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:razonSocial)}</cli:razonSocial>
                    else <cli:razonSocial>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:razonSocial)}</cli:razonSocial>
                }
                {
                    if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:abreviatura)
                    then <cli:abreviatura>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:abreviatura)}</cli:abreviatura>
                    else <cli:abreviatura>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:abreviatura)}</cli:abreviatura>
                }
                {
					<cli:tipoPersona>
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:Id)
							then <cat:Id>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:Id)}</cat:Id>
							else <cat:Id>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:Id)}</cat:Id>
						}
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:Descripcion)
							then <cat:Descripcion>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:Descripcion)}</cat:Descripcion>
							else <cat:Descripcion>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:Descripcion)}</cat:Descripcion>
						}
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:DescripcionCorta)
							then <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:DescripcionCorta)}</cat:DescripcionCorta>
							else <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:DescripcionCorta)}</cat:DescripcionCorta>
						}
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:estatus)
							then <cat:estatus>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:estatus)}</cat:estatus>
							else <cat:estatus>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:estatus)}</cat:estatus>
						}
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:codigoExterno)
							then <cat:codigoExterno>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:codigoExterno)}</cat:codigoExterno>
							else <cat:codigoExterno>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:codigoExterno)}</cat:codigoExterno>
						}
					</cli:tipoPersona>
                }
                {
                    if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:tipoCliente)
                    then <cli:tipoCliente>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:tipoCliente)}</cli:tipoCliente>
                    else <cli:tipoCliente>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:tipoCliente)}</cli:tipoCliente>
                }
                {
					<cli:sector>
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:sector/cat:Id)
							then <cat:Id>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:sector/cat:Id)}</cat:Id>
							else <cat:Id>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:sector/cat:Id)}</cat:Id>
						}
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:sector/cat:Descripcion)
							then <cat:Descripcion>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:sector/cat:Descripcion)}</cat:Descripcion>
							else <cat:Descripcion>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:sector/cat:Descripcion)}</cat:Descripcion>
						}
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:sector/cat:DescripcionCorta)
							then <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:sector/cat:DescripcionCorta)}</cat:DescripcionCorta>
							else <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:sector/cat:DescripcionCorta)}</cat:DescripcionCorta>
						}
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:sector/cat:estatus)
							then <cat:estatus>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:sector/cat:estatus)}</cat:estatus>
							else <cat:estatus>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:sector/cat:estatus)}</cat:estatus>
						}
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:sector/cat:codigoExterno)
							then <cat:codigoExterno>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:sector/cat:codigoExterno)}</cat:codigoExterno>
							else <cat:codigoExterno>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:sector/cat:codigoExterno)}</cat:codigoExterno>
						}
					</cli:sector>
                }
                {
					<cli:tipoInstitucion>
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:tipoInstitucion/cat:Id)
							then <cat:Id>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:tipoInstitucion/cat:Id)}</cat:Id>
							else <cat:Id>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:tipoInstitucion/cat:Id)}</cat:Id>
						}
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:tipoInstitucion/cat:Descripcion)
							then <cat:Descripcion>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:tipoInstitucion/cat:Descripcion)}</cat:Descripcion>
							else <cat:Descripcion>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:tipoInstitucion/cat:Descripcion)}</cat:Descripcion>
						}
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:tipoInstitucion/cat:DescripcionCorta)
							then <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:tipoInstitucion/cat:DescripcionCorta)}</cat:DescripcionCorta>
							else <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:tipoInstitucion/cat:DescripcionCorta)}</cat:DescripcionCorta>
						}
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:tipoInstitucion/cat:estatus)
							then <cat:estatus>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:tipoInstitucion/cat:estatus)}</cat:estatus>
							else <cat:estatus>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:tipoInstitucion/cat:estatus)}</cat:estatus>
						}
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:tipoInstitucion/cat:codigoExterno)
							then <cat:codigoExterno>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:tipoInstitucion/cat:codigoExterno)}</cat:codigoExterno>
							else <cat:codigoExterno>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:tipoInstitucion/cat:codigoExterno)}</cat:codigoExterno>
						}
					</cli:tipoInstitucion>
                }
                {
					<cli:pais>
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:pais/cat:Id)
							then <cat:Id>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:pais/cat:Id)}</cat:Id>
							else <cat:Id>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:pais/cat:Id)}</cat:Id>
						}
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:pais/cat:Descripcion)
							then <cat:Descripcion>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:pais/cat:Descripcion)}</cat:Descripcion>
							else <cat:Descripcion>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:pais/cat:Descripcion)}</cat:Descripcion>
						}
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:pais/cat:DescripcionCorta)
							then <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:pais/cat:DescripcionCorta)}</cat:DescripcionCorta>
							else <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:pais/cat:DescripcionCorta)}</cat:DescripcionCorta>
						}
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:pais/cat:estatus)
							then <cat:estatus>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:pais/cat:estatus)}</cat:estatus>
							else <cat:estatus>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:pais/cat:estatus)}</cat:estatus>
						}
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:pais/cat:codigoExterno)
							then <cat:codigoExterno>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:pais/cat:codigoExterno)}</cat:codigoExterno>
							else <cat:codigoExterno>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:pais/cat:codigoExterno)}</cat:codigoExterno>
						}
					</cli:pais>
                }
                {
					<cli:grupoEconomico>
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:Id)
							then <cat:Id>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:Id)}</cat:Id>
							else <cat:Id>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:Id)}</cat:Id>
						}
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:Descripcion)
							then <cat:Descripcion>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:Descripcion)}</cat:Descripcion>
							else <cat:Descripcion>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:Descripcion)}</cat:Descripcion>
						}
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:DescripcionCorta)
							then <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:DescripcionCorta)}</cat:DescripcionCorta>
							else <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:DescripcionCorta)}</cat:DescripcionCorta>
						}
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:estatus)
							then <cat:estatus>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:estatus)}</cat:estatus>
							else <cat:estatus>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:estatus)}</cat:estatus>
						}
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:codigoExterno)
							then <cat:codigoExterno>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:codigoExterno)}</cat:codigoExterno>
							else <cat:codigoExterno>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:codigoExterno)}</cat:codigoExterno>
						}
					</cli:grupoEconomico>
                }
                {
                    if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:tipoIdentificacion)
                    then <cli:tipoIdentificacion>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:tipoIdentificacion)}</cli:tipoIdentificacion>
                    else <cli:tipoIdentificacion>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:tipoIdentificacion)}</cli:tipoIdentificacion>
                }
                {
                    if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:numeroIdentificacion)
                    then <cli:numeroIdentificacion>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:numeroIdentificacion)}</cli:numeroIdentificacion>
                    else  <cli:numeroIdentificacion>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:numeroIdentificacion)}</cli:numeroIdentificacion>
                }
                {
                    if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:direccion)
                    then <cli:direccion>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:direccion)}</cli:direccion>
                    else <cli:direccion>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:direccion)}</cli:direccion>
                }
                {
                    if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:telefono)
                    then <cli:telefono>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:telefono)}</cli:telefono>
                    else <cli:telefono>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:telefono)}</cli:telefono>
                }
                {
                    if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:fax)
                    then <cli:fax>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:fax)}</cli:fax>
                    else <cli:fax>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:fax)}</cli:fax>
                }
                {
                    if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:email)
                    then <cli:email>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:email)}</cli:email>
                    else <cli:email>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:email)}</cli:email>
                }
                {
                    if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:ciudad)
                    then <cli:ciudad>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:ciudad)}</cli:ciudad>
                    else <cli:ciudad>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:ciudad)}</cli:ciudad>
                }
                {
					<cli:oficina>
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:oficina/cat:Id)
							then <cat:Id>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:oficina/cat:Id)}</cat:Id>
							else <cat:Id>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:oficina/cat:Id)}</cat:Id>
						}
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:oficina/cat:Descripcion)
							then <cat:Descripcion>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:oficina/cat:Descripcion)}</cat:Descripcion>
							else <cat:Descripcion>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:oficina/cat:Descripcion)}</cat:Descripcion>
						}
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:oficina/cat:DescripcionCorta)
							then <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:oficina/cat:DescripcionCorta)}</cat:DescripcionCorta>
							else <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:oficina/cat:DescripcionCorta)}</cat:DescripcionCorta>
						}
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:oficina/cat:estatus)
							then <cat:estatus>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:oficina/cat:estatus)}</cat:estatus>
							else <cat:estatus>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:oficina/cat:estatus)}</cat:estatus>
						}
						{
							if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:oficina/cat:codigoExterno)
							then <cat:codigoExterno>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:oficina/cat:codigoExterno)}</cat:codigoExterno>
							else <cat:codigoExterno>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:oficina/cat:codigoExterno)}</cat:codigoExterno>
						}
					</cli:oficina>
                }
                {
                    if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:grupoEmpresarial)
                    then <cli:grupoEmpresarial>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:grupoEmpresarial)}</cli:grupoEmpresarial>
                    else <cli:grupoEmpresarial>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:grupoEmpresarial)}</cli:grupoEmpresarial>
                }
                {
                    if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:fechaRegistro)
                    then <cli:fechaRegistro>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:fechaRegistro)}</cli:fechaRegistro>
                    else <cli:fechaRegistro>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:fechaRegistro)}</cli:fechaRegistro>
                }
                {
                    if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:fechaAprobacion)
                    then <cli:fechaAprobacion>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:fechaAprobacion)}</cli:fechaAprobacion>
                    else <cli:fechaAprobacion>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:fechaAprobacion)}</cli:fechaAprobacion>
                }
                {
                    if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:ejecutivo)
                    then <cli:ejecutivo>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:ejecutivo)}</cli:ejecutivo>
                    else <cli:ejecutivo>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:ejecutivo)}</cli:ejecutivo>
                }
                {
                    for $Contactos in $ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:Contactos
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
                                <cli:fechaRegistro>{fn:data($Contacto/cli:fechaRegistro)}</cli:fechaRegistro>
                                <cli:idContactosClientes>{fn:data($Contacto/cli:idContactosClientes)}</cli:idContactosClientes>
                            </cli:Contacto>
                        }
                    </cli:Contactos>
                }
                <cli:InformacionCorrecta>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:InformacionCorrecta)}</cli:InformacionCorrecta>
                <cli:ActualizacionPermitida>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:ActualizacionPermitida)}</cli:ActualizacionPermitida>
                {
                    if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:comentarioAprobacion)
                    then <cli:comentarioAprobacion>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:comentarioAprobacion)}</cli:comentarioAprobacion>
                    else <cli:comentarioAprobacion>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:comentarioAprobacion)}</cli:comentarioAprobacion>
                }
                {
                    if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:revisadoAprobacion)
                    then <cli:revisadoAprobacion>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:revisadoAprobacion)}</cli:revisadoAprobacion>
                    else <cli:revisadoAprobacion>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:revisadoAprobacion)}</cli:revisadoAprobacion>
                }
                {
                    if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:status)
                    then <cli:status>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:status)}</cli:status>
                    else <cli:status>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:status)}</cli:status>
                }
                {
                    if ($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:fechaBaja)
                    then <cli:fechaBaja>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cliente/cli:fechaBaja)}</cli:fechaBaja>
                    else <cli:fechaBaja>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cliente/cli:fechaBaja)}</cli:fechaBaja>
                }
            </ope:cliente>
            {
                if ($ActualizarOperacionRequest/ns1:Operacion/ope:descripcion)
                then <ope:descripcion>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:descripcion)}</ope:descripcion>
                else <ope:descripcion>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:descripcion)}</ope:descripcion>
            }
            {
                if ($ActualizarOperacionRequest/ns1:Operacion/ope:montoTotal)
                then <ope:montoTotal>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:montoTotal)}</ope:montoTotal>
                else <ope:montoTotal>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:montoTotal)}</ope:montoTotal>
            }
            {
                if ($ActualizarOperacionRequest/ns1:Operacion/ope:montoSolicitado)
                then <ope:montoSolicitado>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:montoSolicitado)}</ope:montoSolicitado>
                else <ope:montoSolicitado>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:montoSolicitado)}</ope:montoSolicitado>
            }
            {
                if ($ActualizarOperacionRequest/ns1:Operacion/ope:lineaCredito)
                then <ope:lineaCredito>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:lineaCredito)}</ope:lineaCredito>
                else <ope:lineaCredito>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:lineaCredito)}</ope:lineaCredito>
            }
            {
				<ope:actividadEconomica>
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:actividadEconomica/cat:Id)
						then <cat:Id>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:actividadEconomica/cat:Id)}</cat:Id>
						else <cat:Id>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:actividadEconomica/cat:Id)}</cat:Id>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:actividadEconomica/cat:Descripcion)
						then <cat:Descripcion>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:actividadEconomica/cat:Descripcion)}</cat:Descripcion>
						else <cat:Descripcion>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:actividadEconomica/cat:Descripcion)}</cat:Descripcion>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:actividadEconomica/cat:DescripcionCorta)
						then <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:actividadEconomica/cat:DescripcionCorta)}</cat:DescripcionCorta>
						else <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:actividadEconomica/cat:DescripcionCorta)}</cat:DescripcionCorta>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:actividadEconomica/cat:estatus)
						then <cat:estatus>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:actividadEconomica/cat:estatus)}</cat:estatus>
						else <cat:estatus>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:actividadEconomica/cat:estatus)}</cat:estatus>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:actividadEconomica/cat:codigoExterno)
						then <cat:codigoExterno>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:actividadEconomica/cat:codigoExterno)}</cat:codigoExterno>
						else <cat:codigoExterno>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:actividadEconomica/cat:codigoExterno)}</cat:codigoExterno>
					}
				</ope:actividadEconomica>
            }
            {
				<ope:actividadEconomicaAsociada>
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:actividadEconomicaAsociada/cat:Id)
						then <cat:Id>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:actividadEconomicaAsociada/cat:Id)}</cat:Id>
						else <cat:Id>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:actividadEconomicaAsociada/cat:Id)}</cat:Id>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:actividadEconomicaAsociada/cat:Descripcion)
						then <cat:Descripcion>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:actividadEconomicaAsociada/cat:Descripcion)}</cat:Descripcion>
						else <cat:Descripcion>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:actividadEconomicaAsociada/cat:Descripcion)}</cat:Descripcion>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:actividadEconomicaAsociada/cat:DescripcionCorta)
						then <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:actividadEconomicaAsociada/cat:DescripcionCorta)}</cat:DescripcionCorta>
						else <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:actividadEconomicaAsociada/cat:DescripcionCorta)}</cat:DescripcionCorta>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:actividadEconomicaAsociada/cat:estatus)
						then <cat:estatus>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:actividadEconomicaAsociada/cat:estatus)}</cat:estatus>
						else <cat:estatus>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:actividadEconomicaAsociada/cat:estatus)}</cat:estatus>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:actividadEconomicaAsociada/cat:codigoExterno)
						then <cat:codigoExterno>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:actividadEconomicaAsociada/cat:codigoExterno)}</cat:codigoExterno>
						else <cat:codigoExterno>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:actividadEconomicaAsociada/cat:codigoExterno)}</cat:codigoExterno>
					}
				</ope:actividadEconomicaAsociada>
            }
            {
                if ($ActualizarOperacionRequest/ns1:Operacion/ope:objetivosEstrategicos)
                then <ope:objetivosEstrategicos>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:objetivosEstrategicos)}</ope:objetivosEstrategicos>
                else <ope:objetivosEstrategicos>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:objetivosEstrategicos)}</ope:objetivosEstrategicos>
            }
            {
				<ope:areaFocalizacion>
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:areaFocalizacion/cat:Id)
						then <cat:Id>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:areaFocalizacion/cat:Id)}</cat:Id>
						else <cat:Id>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:areaFocalizacion/cat:Id)}</cat:Id>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:areaFocalizacion/cat:Descripcion)
						then <cat:Descripcion>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:areaFocalizacion/cat:Descripcion)}</cat:Descripcion>
						else <cat:Descripcion>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:areaFocalizacion/cat:Descripcion)}</cat:Descripcion>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:areaFocalizacion/cat:DescripcionCorta)
						then <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:areaFocalizacion/cat:DescripcionCorta)}</cat:DescripcionCorta>
						else <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:areaFocalizacion/cat:DescripcionCorta)}</cat:DescripcionCorta>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:areaFocalizacion/cat:estatus)
						then <cat:estatus>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:areaFocalizacion/cat:estatus)}</cat:estatus>
						else <cat:estatus>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:areaFocalizacion/cat:estatus)}</cat:estatus>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:areaFocalizacion/cat:codigoExterno)
						then <cat:codigoExterno>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:areaFocalizacion/cat:codigoExterno)}</cat:codigoExterno>
						else <cat:codigoExterno>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:areaFocalizacion/cat:codigoExterno)}</cat:codigoExterno>
					}
				</ope:areaFocalizacion>
            }
            {
				<ope:ejeEstrategico>
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:ejeEstrategico/cat:Id)
						then <cat:Id>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:ejeEstrategico/cat:Id)}</cat:Id>
						else <cat:Id>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:ejeEstrategico/cat:Id)}</cat:Id>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:ejeEstrategico/cat:Descripcion)
						then <cat:Descripcion>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:ejeEstrategico/cat:Descripcion)}</cat:Descripcion>
						else <cat:Descripcion>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:ejeEstrategico/cat:Descripcion)}</cat:Descripcion>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:ejeEstrategico/cat:DescripcionCorta)
						then <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:ejeEstrategico/cat:DescripcionCorta)}</cat:DescripcionCorta>
						else <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:ejeEstrategico/cat:DescripcionCorta)}</cat:DescripcionCorta>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:ejeEstrategico/cat:estatus)
						then <cat:estatus>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:ejeEstrategico/cat:estatus)}</cat:estatus>
						else <cat:estatus>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:ejeEstrategico/cat:estatus)}</cat:estatus>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:ejeEstrategico/cat:codigoExterno)
						then <cat:codigoExterno>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:ejeEstrategico/cat:codigoExterno)}</cat:codigoExterno>
						else <cat:codigoExterno>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:ejeEstrategico/cat:codigoExterno)}</cat:codigoExterno>
					}
				</ope:ejeEstrategico>
            }
            {
				<ope:iniciativaEstrategica>
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:iniciativaEstrategica/cat:Id)
						then <cat:Id>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:iniciativaEstrategica/cat:Id)}</cat:Id>
						else <cat:Id>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:iniciativaEstrategica/cat:Id)}</cat:Id>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:iniciativaEstrategica/cat:Descripcion)
						then <cat:Descripcion>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:iniciativaEstrategica/cat:Descripcion)}</cat:Descripcion>
						else <cat:Descripcion>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:iniciativaEstrategica/cat:Descripcion)}</cat:Descripcion>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:iniciativaEstrategica/cat:DescripcionCorta)
						then <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:iniciativaEstrategica/cat:DescripcionCorta)}</cat:DescripcionCorta>
						else <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:iniciativaEstrategica/cat:DescripcionCorta)}</cat:DescripcionCorta>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:iniciativaEstrategica/cat:estatus)
						then <cat:estatus>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:iniciativaEstrategica/cat:estatus)}</cat:estatus>
						else <cat:estatus>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:iniciativaEstrategica/cat:estatus)}</cat:estatus>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:iniciativaEstrategica/cat:codigoExterno)
						then <cat:codigoExterno>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:iniciativaEstrategica/cat:codigoExterno)}</cat:codigoExterno>
						else <cat:codigoExterno>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:iniciativaEstrategica/cat:codigoExterno)}</cat:codigoExterno>
					}
				</ope:iniciativaEstrategica>
            }
            {
				<ope:cantidadPaises>
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:cantidadPaises/cat:Id)
						then <cat:Id>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cantidadPaises/cat:Id)}</cat:Id>
						else <cat:Id>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cantidadPaises/cat:Id)}</cat:Id>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:cantidadPaises/cat:Descripcion)
						then <cat:Descripcion>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cantidadPaises/cat:Descripcion)}</cat:Descripcion>
						else <cat:Descripcion>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cantidadPaises/cat:Descripcion)}</cat:Descripcion>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:cantidadPaises/cat:DescripcionCorta)
						then <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cantidadPaises/cat:DescripcionCorta)}</cat:DescripcionCorta>
						else <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cantidadPaises/cat:DescripcionCorta)}</cat:DescripcionCorta>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:cantidadPaises/cat:estatus)
						then <cat:estatus>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cantidadPaises/cat:estatus)}</cat:estatus>
						else <cat:estatus>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cantidadPaises/cat:estatus)}</cat:estatus>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:cantidadPaises/cat:codigoExterno)
						then <cat:codigoExterno>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:cantidadPaises/cat:codigoExterno)}</cat:codigoExterno>
						else <cat:codigoExterno>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:cantidadPaises/cat:codigoExterno)}</cat:codigoExterno>
					}
				</ope:cantidadPaises>
            }
            {
				<ope:sectorMercado>
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:sectorMercado/cat:Id)
						then <cat:Id>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:sectorMercado/cat:Id)}</cat:Id>
						else <cat:Id>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:sectorMercado/cat:Id)}</cat:Id>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:sectorMercado/cat:Descripcion)
						then <cat:Descripcion>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:sectorMercado/cat:Descripcion)}</cat:Descripcion>
						else  <cat:Descripcion>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:sectorMercado/cat:Descripcion)}</cat:Descripcion>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:sectorMercado/cat:DescripcionCorta)
						then <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:sectorMercado/cat:DescripcionCorta)}</cat:DescripcionCorta>
						else  <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:sectorMercado/cat:DescripcionCorta)}</cat:DescripcionCorta>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:sectorMercado/cat:estatus)
						then <cat:estatus>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:sectorMercado/cat:estatus)}</cat:estatus>
						else <cat:estatus>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:sectorMercado/cat:estatus)}</cat:estatus>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:sectorMercado/cat:codigoExterno)
						then <cat:codigoExterno>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:sectorMercado/cat:codigoExterno)}</cat:codigoExterno>
						else <cat:codigoExterno>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:sectorMercado/cat:codigoExterno)}</cat:codigoExterno>
					}
				</ope:sectorMercado>
            }
            {
                if ($ActualizarOperacionRequest/ns1:Operacion/ope:fechaInicio)
                then <ope:fechaInicio>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:fechaInicio)}</ope:fechaInicio>
                else <ope:fechaInicio>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:fechaInicio)}</ope:fechaInicio>
            }
            {
                if ($ActualizarOperacionRequest/ns1:Operacion/ope:programadoPOA)
                then <ope:programadoPOA>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:programadoPOA)}</ope:programadoPOA>
                else <ope:programadoPOA>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:programadoPOA)}</ope:programadoPOA>
            }
            {
                if ($ActualizarOperacionRequest/ns1:Operacion/ope:ejercicioPOA)
                then <ope:ejercicioPOA>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:ejercicioPOA)}</ope:ejercicioPOA>
                else <ope:ejercicioPOA>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:ejercicioPOA)}</ope:ejercicioPOA>
            }
            {
				<ope:tipoGarantia>
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:tipoGarantia/cat:Id)
						then <cat:Id>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:tipoGarantia/cat:Id)}</cat:Id>
						else <cat:Id>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:tipoGarantia/cat:Id)}</cat:Id>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:tipoGarantia/cat:Descripcion)
						then <cat:Descripcion>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:tipoGarantia/cat:Descripcion)}</cat:Descripcion>
						else <cat:Descripcion>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:tipoGarantia/cat:Descripcion)}</cat:Descripcion>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:tipoGarantia/cat:DescripcionCorta)
						then <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:tipoGarantia/cat:DescripcionCorta)}</cat:DescripcionCorta>
						else <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:tipoGarantia/cat:DescripcionCorta)}</cat:DescripcionCorta>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:tipoGarantia/cat:estatus)
						then <cat:estatus>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:tipoGarantia/cat:estatus)}</cat:estatus>
						else <cat:estatus>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:tipoGarantia/cat:estatus)}</cat:estatus>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:tipoGarantia/cat:codigoExterno)
						then <cat:codigoExterno>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:tipoGarantia/cat:codigoExterno)}</cat:codigoExterno>
						else <cat:codigoExterno>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:tipoGarantia/cat:codigoExterno)}</cat:codigoExterno>
					}
				</ope:tipoGarantia>
            }
            {
                if ($ActualizarOperacionRequest/ns1:Operacion/ope:componenteConcesionalidad)
                then <ope:componenteConcecionalidad>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:componenteConcesionalidad)}</ope:componenteConcecionalidad>
                else <ope:componenteConcecionalidad>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:componenteConcesionalidad)}</ope:componenteConcecionalidad>
            }
            {
                if ($ActualizarOperacionRequest/ns1:Operacion/ope:estructurador)
                then <ope:estructurador>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:estructurador)}</ope:estructurador>
                else <ope:estructurador>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:estructurador)}</ope:estructurador>
            }
            {
                if ($ActualizarOperacionRequest/ns1:Operacion/ope:beneficiario)
                then <ope:beneficiario>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:beneficiario)}</ope:beneficiario>
                else <ope:beneficiario>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:beneficiario)}</ope:beneficiario>
            }
            {
                if ($ActualizarOperacionRequest/ns1:Operacion/ope:unidadEjecutora)
                then <ope:unidadEjecutora>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:unidadEjecutora)}</ope:unidadEjecutora>
                else <ope:unidadEjecutora>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:unidadEjecutora)}</ope:unidadEjecutora>
            }
            {
                if ($ActualizarOperacionRequest/ns1:Operacion/ope:programa)
                then <ope:programa>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:programa)}</ope:programa>
                else <ope:programa>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:programa)}</ope:programa>
            }
            <ope:asociadas>
                <ope:Operacion></ope:Operacion>
            </ope:asociadas>
            <ope:declaracionJurada>
                <dec:idDeclaracion></dec:idDeclaracion>
                <dec:codigoExternoDeclaracion></dec:codigoExternoDeclaracion>
                <dec:idOperacion></dec:idOperacion>
                <dec:sectorInstitucional></dec:sectorInstitucional>
                <dec:tipoPersona></dec:tipoPersona>
                <dec:tipoSolicitud></dec:tipoSolicitud>
                <dec:ejecutivo></dec:ejecutivo>
                <dec:titulo></dec:titulo>
                <dec:fechaRecepcion></dec:fechaRecepcion>
                <dec:fechaFirmaDeclaracion></dec:fechaFirmaDeclaracion>
                <dec:fechaVencimiento></dec:fechaVencimiento>
                <dec:estadoDeclaracion>
                    <dec:codigoEstadoDeclaracion></dec:codigoEstadoDeclaracion>
                    <dec:nombreEstadoDeclaracion></dec:nombreEstadoDeclaracion>
                    <dec:EstadoNoObjecion></dec:EstadoNoObjecion>
                </dec:estadoDeclaracion>
                <dec:estadoBusqueda>
                    <dec:codigoEstadoBusqueda></dec:codigoEstadoBusqueda>
                    <dec:nombreEstadoBusqueda></dec:nombreEstadoBusqueda>
                </dec:estadoBusqueda>
                <dec:calificacionDeRiesgo>
                    <dec:codigoCalificacionDeRiesgo></dec:codigoCalificacionDeRiesgo>
                    <dec:nombreCalificacionDeRiesgo></dec:nombreCalificacionDeRiesgo>
                </dec:calificacionDeRiesgo>
                <dec:conclusionAnalisisDeclaracion></dec:conclusionAnalisisDeclaracion>
                <dec:conclusionAnalisisBusqueda></dec:conclusionAnalisisBusqueda>
            </ope:declaracionJurada>
            {
                if ($ActualizarOperacionRequest/ns1:Operacion/ope:masInformacionRiesgo)
                then <ope:masInformacionRiesgo>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:masInformacionRiesgo)}</ope:masInformacionRiesgo>
                else <ope:masInformacionRiesgo>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:masInformacionRiesgo)}</ope:masInformacionRiesgo>
            }
            {
                if ($ActualizarOperacionRequest/ns1:Operacion/ope:informacionAdicionalRiesgo)
                then <ope:informacionAdicionalRiesgo>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:informacionAdicionalRiesgo)}</ope:informacionAdicionalRiesgo>
                else <ope:informacionAdicionalRiesgo>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:informacionAdicionalRiesgo)}</ope:informacionAdicionalRiesgo>
            }
            {
                if ($ActualizarOperacionRequest/ns1:Operacion/ope:masInformacionJuridico)
                then <ope:masInformacionJuridico>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:masInformacionJuridico)}</ope:masInformacionJuridico>
                else <ope:masInformacionJuridico>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:masInformacionJuridico)}</ope:masInformacionJuridico>
            }
            <ope:informacionAdicionalJuridico></ope:informacionAdicionalJuridico>
            <ope:contrapartesProhibidas></ope:contrapartesProhibidas>
            <ope:comentarioContrapartes></ope:comentarioContrapartes>
            <ope:limitesAnalisis></ope:limitesAnalisis>
            <ope:opinionElegibilidad></ope:opinionElegibilidad>
            {
                if ($ActualizarOperacionRequest/ns1:Operacion/ope:comentarioElegibilidad)
                then <ope:comentarioElegibilidad>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:comentarioElegibilidad)}</ope:comentarioElegibilidad>
                else <ope:comentarioElegibilidad>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:comentarioElegibilidad)}</ope:comentarioElegibilidad>
            }
            {
                if ($ActualizarOperacionRequest/ns1:Operacion/ope:limitesDeterminar)
                then <ope:limitesDeterminar>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:limitesDeterminar)}</ope:limitesDeterminar>
                else <ope:limitesDeterminar>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:limitesDeterminar)}</ope:limitesDeterminar>
            }
            <ope:opinionDeterminar></ope:opinionDeterminar>
            <ope:comentarioDeterminar></ope:comentarioDeterminar>
            {
                  if ($ActualizarOperacionRequest/ns1:Operacion/ope:etapa)
                  then <ope:etapa>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:etapa)}</ope:etapa>
                  else <ope:etapa>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:etapa)}</ope:etapa>
            }
            <ope:status></ope:status>
            <ope:fechaBaja></ope:fechaBaja>
            <ope:comentarioLaft></ope:comentarioLaft>
            <ope:cumpleLaft></ope:cumpleLaft>
            {
                if ($ActualizarOperacionRequest/ns1:Operacion/ope:calificacionRiesgo)
                then <ope:calificacionRiesgo>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:calificacionRiesgo)}</ope:calificacionRiesgo>
                else <ope:calificacionRiesgo>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:calificacionRiesgo)}</ope:calificacionRiesgo>
            }
            <ope:perspectiva>
                {
                    if ($ActualizarOperacionRequest/ns1:Operacion/ope:perspectiva/cat:Id)
                    then <cat:Id>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:perspectiva/cat:Id)}</cat:Id>
                    else <cat:Id>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:perspectiva/cat:Id)}</cat:Id>
                }
            </ope:perspectiva>
            {
				<ope:estado>
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:estado/cat:Id)
						then <cat:Id>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:estado/cat:Id)}</cat:Id>
						else <cat:Id>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:estado/cat:Id)}</cat:Id>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:estado/cat:Descripcion)
						then <cat:Descripcion>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:estado/cat:Descripcion)}</cat:Descripcion>
						else <cat:Descripcion>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:estado/cat:Descripcion)}</cat:Descripcion>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:estado/cat:DescripcionCorta)
						then <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
						else <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:estado/cat:estatus)
						then <cat:estatus>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:estado/cat:estatus)}</cat:estatus>
						else <cat:estatus>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:estado/cat:estatus)}</cat:estatus>
					}
					{
						if ($ActualizarOperacionRequest/ns1:Operacion/ope:estado/cat:codigoExterno)
						then <cat:codigoExterno>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:estado/cat:codigoExterno)}</cat:codigoExterno>
						else <cat:codigoExterno>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:estado/cat:codigoExterno)}</cat:codigoExterno>
					}
				</ope:estado>
            }
            {
                <ope:SRC>
                    {
                        if ($ActualizarOperacionRequest/ns1:Operacion/ope:SRC/cat:Id)
                        then <cat:Id>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:SRC/cat:Id)}</cat:Id>
                        else <cat:Id>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:SRC/cat:Id)}</cat:Id>
                    }
                    {
                        if ($ActualizarOperacionRequest/ns1:Operacion/ope:SRC/cat:Descripcion)
                        then <cat:Descripcion>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:SRC/cat:Descripcion)}</cat:Descripcion>
                        else <cat:Descripcion>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:SRC/cat:Descripcion)}</cat:Descripcion>
                    }
                    {
                        if ($ActualizarOperacionRequest/ns1:Operacion/ope:SRC/cat:DescripcionCorta)
                        then <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:SRC/cat:DescripcionCorta)}</cat:DescripcionCorta>
                        else <cat:DescripcionCorta>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:SRC/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    }
                    {
                        if ($ActualizarOperacionRequest/ns1:Operacion/ope:SRC/cat:estatus)
                        then <cat:estatus>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:SRC/cat:estatus)}</cat:estatus>
                        else <cat:estatus>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:SRC/cat:estatus)}</cat:estatus>
                    }
                    {
                        if ($ActualizarOperacionRequest/ns1:Operacion/ope:SRC/cat:codigoExterno)
                        then <cat:codigoExterno>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:SRC/cat:codigoExterno)}</cat:codigoExterno>
                        else <cat:codigoExterno>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:SRC/cat:codigoExterno)}</cat:codigoExterno>
                    }
                </ope:SRC>
            }
            {
                if ($ActualizarOperacionRequest/ns1:Operacion/ope:RAROC)
                then <ope:RAROC>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:RAROC)}</ope:RAROC>
                else <ope:RAROC>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:RAROC)}</ope:RAROC>
            }
            {
                if ($ActualizarOperacionRequest/ns1:Operacion/ope:TIR)
                then <ope:TIR>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:TIR)}</ope:TIR>
                else <ope:TIR>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:TIR)}</ope:TIR>
            }
            {
                if ($ActualizarOperacionRequest/ns1:Operacion/ope:TIREstatus)
                then <ope:TIREstatus>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:TIREstatus)}</ope:TIREstatus>
                else <ope:TIREstatus>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:TIREstatus)}</ope:TIREstatus>
            }
            <ope:requiereRecursosExternos></ope:requiereRecursosExternos>
            {
                if ($ActualizarOperacionRequest/ns1:Operacion/ope:aplicaLaft)
                then <ope:aplicaLaft>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:aplicaLaft)}</ope:aplicaLaft>
                else <ope:aplicaLaft>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:aplicaLaft)}</ope:aplicaLaft>
            }
            <ope:montoOperacion>
                <ope:montoOperacion>
                    <ope:id></ope:id>
                    <ope:idOperacion></ope:idOperacion>
                    <ope:IdTcaTipoMonto></ope:IdTcaTipoMonto>
                    <ope:monto></ope:monto>
                    <ope:Descripcion></ope:Descripcion>
                </ope:montoOperacion>
            </ope:montoOperacion>
            {
                if ($ActualizarOperacionRequest/ns1:Operacion/ope:idCatPais)
                then <ope:idCatPais>{fn:data($ActualizarOperacionRequest/ns1:Operacion/ope:idCatPais)}</ope:idCatPais>
                else <ope:idCatPais>{fn:data($ActualizarOperacionRequestTempo/ns1:Operacion/ope:idCatPais)}</ope:idCatPais>
            }
 
           
            
        </ns1:Operacion>
        <ns1:reasignar></ns1:reasignar>
    </ns1:ActualizarOperaccionRequest>
};

local:func($ActualizarOperacionRequest, $ActualizarOperacionRequestTempo)
