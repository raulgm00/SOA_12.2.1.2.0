xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace cli="http://www.bcie.org/ClienteMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace cli1="http://www.bcie.org/FLEXCUBE14/ClienteMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Flexcube14/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ope="http://www.bcie.org/OperacionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace cli2 = "http://www.bcie.org/ClienteBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $inputVariable.request as element() (:: schema-element(cli:ActualizarClienteRequest) ::) external;
declare variable $ConsultarOperacionById_OutputVariable.response as element() (:: schema-element(ope:ConsultarOperacionResponse) ::) external;

declare function local:funcXqactualizarcliente_to_actualizarclienteflx14($inputVariable.request as element() (:: schema-element(cli:ActualizarClienteRequest) ::), 
                                                                         $ConsultarOperacionById_OutputVariable.response as element() (:: schema-element(ope:ConsultarOperacionResponse) ::)) 
                                                                         as element() (:: schema-element(cli1:ActualizarClienteRequest) ::) {
    <cli1:ActualizarClienteRequest>
        <cli1:Cliente>
            <cli1:Ejecutivo>{fn:data($inputVariable.request/cli:Cliente/cli2:ejecutivo)}</cli1:Ejecutivo>
            <cli1:Razon_Social>{fn:data($inputVariable.request/cli:Cliente/cli2:razonSocial)}</cli1:Razon_Social>
            <cli1:Abreviatura>{fn:data($inputVariable.request/cli:Cliente/cli2:abreviatura)}</cli1:Abreviatura>
            <cli1:Tipo_Identificacion>{fn:data($inputVariable.request/cli:Cliente/cli2:tipoIdentificacion)}</cli1:Tipo_Identificacion>
            <cli1:Identificacion>{fn:data($inputVariable.request/cli:Cliente/cli2:numeroIdentificacion)}</cli1:Identificacion>
            <cli1:Grupo_Empresarial>
            {
                if( fn:data(fn:data($inputVariable.request/cli:Cliente/cli2:grupoEconomico/cat:Id)) != 0 )
                then fn:data(fn:data($inputVariable.request/cli:Cliente/cli2:grupoEconomico/cat:Id))
                else ()
            }
            </cli1:Grupo_Empresarial>
            <cli1:Tipo_Persona></cli1:Tipo_Persona>
            <cli1:Tipo_Institucion>{fn:data($inputVariable.request/cli:Cliente/cli2:tipoInstitucion/cat:Id)}</cli1:Tipo_Institucion>
            <cli1:Sector_Institucional>{fn:data($inputVariable.request/cli:Cliente/cli2:sector/cat:Id)}</cli1:Sector_Institucional>
            <cli1:Actividad_Economica>ND</cli1:Actividad_Economica>
            <cli1:Eje_Estrategico>ND</cli1:Eje_Estrategico>
            <cli1:Direccion>{fn:data($inputVariable.request/cli:Cliente/cli2:direccion)}</cli1:Direccion>
            <cli1:Pais>{fn:data($inputVariable.request/cli:Cliente/cli2:pais/cat:codigoExterno)}</cli1:Pais>
            <cli1:Ciudad>{fn:data($inputVariable.request/cli:Cliente/cli2:ciudad)}</cli1:Ciudad>
            <cli1:Telefono>{fn:data($inputVariable.request/cli:Cliente/cli2:Contactos[1]/cli2:Contacto[1]/cli2:telefono)}</cli1:Telefono>
            <cli1:Fax></cli1:Fax>
            <cli1:Email>{fn:data($inputVariable.request/cli:Cliente/cli2:Contactos[1]/cli2:Contacto[1]/cli2:correoElectronico)}</cli1:Email>
            <cli1:Dia_De_Pago>
            {
                if( string-length($inputVariable.request/cli:Cliente/cli2:diaPago/text()) = 0)
                then 1
                else fn:data(fn:data($inputVariable.request/cli:Cliente/cli2:diaPago))
            }
            </cli1:Dia_De_Pago>
            <cli1:Cliente_Padre/>
            <cli1:Puesto/>
            <cli1:Tipo_Relacion/>
            <cli1:BIC_Code>{fn:data($inputVariable.request/cli:Cliente/cli2:bicCode)}</cli1:BIC_Code>
            <cli1:Codigo_Cliente>{fn:data($inputVariable.request/cli:Cliente/cli2:idFacturador)}</cli1:Codigo_Cliente>
            <cli1:Overall_Limit></cli1:Overall_Limit>
            <cli1:Rating></cli1:Rating>
            <cli1:Autorizado></cli1:Autorizado>
        </cli1:Cliente>
        <cli1:Usuario>SYSTEM</cli1:Usuario>
    </cli1:ActualizarClienteRequest>
};

local:funcXqactualizarcliente_to_actualizarclienteflx14($inputVariable.request, $ConsultarOperacionById_OutputVariable.response)
