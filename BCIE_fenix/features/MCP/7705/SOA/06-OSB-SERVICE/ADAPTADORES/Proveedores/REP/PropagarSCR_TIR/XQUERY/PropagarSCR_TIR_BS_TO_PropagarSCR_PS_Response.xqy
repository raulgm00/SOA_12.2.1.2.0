xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/PropagarSCR_TIR";
(:: import schema at "../XSD/PropagarSCR_TIR.xsd" ::)

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $propagarOperacion as element() (:: schema-element(ns1:PropagarSCR_TIROutputCollection) ::) external;

declare function local:func($propagarOperacion as element() (:: schema-element(ns1:PropagarSCR_TIROutputCollection) ::)) as element() (:: schema-element(ns2:PropagarSCRyTIRResponse) ::) {
    <ns2:PropagarSCRyTIRResponse>
        {
            for $PropagarSCR_TIROutput in $propagarOperacion/ns1:PropagarSCR_TIROutput
            return 
            <ns2:Operacion>
                <ope:idOperacion>{fn:data($PropagarSCR_TIROutput/ns1:ID_OPERACION)}</ope:idOperacion>
                <ope:responsable>{fn:data($PropagarSCR_TIROutput/ns1:USUARIO)}</ope:responsable>
                <ope:nombre>{fn:data($PropagarSCR_TIROutput/ns1:USUARIOMODIFICO)}</ope:nombre>
                <ope:cliente>
                    <cli:idCliente></cli:idCliente>
                    <cli:idFacturador>{fn:data($PropagarSCR_TIROutput/ns1:ID_FLEXCUBE)}</cli:idFacturador>
                    <cli:razonSocial></cli:razonSocial>
                    <cli:abreviatura></cli:abreviatura>
                    <cli:tipoPersona>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </cli:tipoPersona>
                    <cli:tipoCliente></cli:tipoCliente>
                    <cli:sector>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </cli:sector>
                    <cli:tipoInstitucion>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </cli:tipoInstitucion>
                    <cli:pais>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </cli:pais>
                    <cli:grupoEconomico>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </cli:grupoEconomico>
                    <cli:tipoIdentificacion></cli:tipoIdentificacion>
                    <cli:numeroIdentificacion></cli:numeroIdentificacion>
                    <cli:direccion></cli:direccion>
                    <cli:telefono></cli:telefono>
                    <cli:fax></cli:fax>
                    <cli:email></cli:email>
                    <cli:ciudad></cli:ciudad>
                    <cli:oficina>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </cli:oficina>
                    <cli:grupoEmpresarial></cli:grupoEmpresarial>
                    <cli:fechaRegistro></cli:fechaRegistro>
                    <cli:fechaAprobacion></cli:fechaAprobacion>
                    <cli:ejecutivo></cli:ejecutivo>
                    <cli:Contactos>
                        <cli:Contacto>
                            <cli:idContacto></cli:idContacto>
                            <cli:idCliente></cli:idCliente>
                            <cli:nombre></cli:nombre>
                            <cli:telefono></cli:telefono>
                            <cli:correoElectronico></cli:correoElectronico>
                            <cli:cargo></cli:cargo>
                            <cli:fechaRegistro></cli:fechaRegistro>
                            <cli:idContactosClientes></cli:idContactosClientes>
                        </cli:Contacto>
                    </cli:Contactos>
                    <cli:InformacionCorrecta></cli:InformacionCorrecta>
                    <cli:ActualizacionPermitida></cli:ActualizacionPermitida>
                    <cli:comentarioAprobacion></cli:comentarioAprobacion>
                    <cli:revisadoAprobacion></cli:revisadoAprobacion>
                    <cli:status></cli:status>
                    <cli:fechaBaja></cli:fechaBaja>
                </ope:cliente>
                <ope:fechaBaja>{fn:data($PropagarSCR_TIROutput/ns1:FECHA)}</ope:fechaBaja>
                <ope:estado>
                    <cat:Id>{fn:data($PropagarSCR_TIROutput/ns1:ESTADO)}</cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </ope:estado>
                <ope:SRC>{fn:data($PropagarSCR_TIROutput/ns1:SCR)}</ope:SRC>
                <ope:RAROC>{fn:data($PropagarSCR_TIROutput/ns1:RAROC)}</ope:RAROC>
                <ope:TIR>{fn:data($PropagarSCR_TIROutput/ns1:TIR)}</ope:TIR></ns2:Operacion>
        }</ns2:PropagarSCRyTIRResponse>
};

local:func($propagarOperacion)