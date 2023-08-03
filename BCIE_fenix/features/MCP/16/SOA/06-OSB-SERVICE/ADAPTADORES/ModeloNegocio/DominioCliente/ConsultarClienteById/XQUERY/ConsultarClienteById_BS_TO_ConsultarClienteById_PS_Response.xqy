xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarClienteByIdCliente";
(:: import schema at "../XSD/ConsultarClienteByIdCliente.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarClienteByIdClienteOutputCollection as element() (:: schema-element(ns1:ConsultarClienteByIdClienteOutputCollection) ::) external;
declare variable $ConsultarOperacionesByIdClienteResponse as element() (:: schema-element(ns2:ConsultarOperacionesByIdClienteResponse) ::) external;

declare function local:func($ConsultarClienteByIdClienteOutputCollection as element() (:: schema-element(ns1:ConsultarClienteByIdClienteOutputCollection) ::), 
                            $ConsultarOperacionesByIdClienteResponse as element() (:: schema-element(ns2:ConsultarOperacionesByIdClienteResponse) ::)) 
                            as element() (:: schema-element(ns2:ConsultarClienteResponse) ::) {
    <ns2:ConsultarClienteResponse>
       
        <ns2:Cliente>
            <cli:idCliente>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:ID_CLIENTE)}</cli:idCliente>
            <cli:idFacturador>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:ID_FLEXCUBE)}</cli:idFacturador>
            <cli:razonSocial>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:RAZON_SOCIAL)}</cli:razonSocial>
            <cli:bicCode>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:BIC_CODE)}</cli:bicCode>
            <cli:abreviatura>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:ABREVIATURA)}</cli:abreviatura>
            <cli:tipoPersona>
                <cat:Id>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:TIPO_PERSONA)}</cat:Id>
                <cat:Descripcion>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:DESCRIPCION_TIPO_PERSONA)}</cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:DESCRIPCION_CORTA_TIPO_PERSONA)}</cat:DescripcionCorta>
                <cat:estatus>{
                if ($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:ESTATUS_TIPO_PERSONA = '1')
                then (fn:true())
                else(fn:false())
                }</cat:estatus>
                <cat:codigoExterno>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:COD_EXTERNO_TIPO_PERSONA)}</cat:codigoExterno>
            </cli:tipoPersona>
            <cli:tipoCliente>
                <cat:Id>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:TIPO_CLIENTE)}</cat:Id>
                <cat:Descripcion>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:DESCRIPCION_TIPO_CLIENTE)}</cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:DESCRIPCION_CORTA_TIPO_CLIENTE)}</cat:DescripcionCorta>
                <cat:estatus>{
                if ($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:ESTATUS_TIPO_CLIENTE = '1')
                then (fn:true())
                else(fn:false())
                }
                </cat:estatus>
                <cat:codigoExterno>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:COD_EXTERNO_TIPO_CLIENTE)}</cat:codigoExterno>
            </cli:tipoCliente>
            <cli:sector>
                <cat:Id>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:SECTOR)}</cat:Id>
                <cat:Descripcion>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:DESCRIPCION_SECTOR)}</cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:DESCRIPCION_CORTA_SECTOR)}</cat:DescripcionCorta>
                <cat:estatus>{
                if ($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:ESTATUS_SECTOR = '1')
                then (fn:true())
                else(fn:false())
                }</cat:estatus>
                <cat:codigoExterno>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:COD_EXTERNO_SECTOR)}</cat:codigoExterno>
            </cli:sector>
            <cli:tipoInstitucion>
                <cat:Id>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:TIPO_INSTITUCION)}</cat:Id>
                <cat:Descripcion>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:DESCRIPCION_TIPO_INSTITUCION)}</cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:DESCRIPCION_CORTA_TIPO_INST)}</cat:DescripcionCorta>
                <cat:estatus>{
                if($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:ESTATUS_TIPO_INSTITUCION = '1')
                then (fn:true())
                else(fn:false())
                }</cat:estatus>
                <cat:codigoExterno>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:COD_EXTERNO_TIPO_INSTITUCION)}</cat:codigoExterno>
            </cli:tipoInstitucion>
            <cli:pais>
                <cat:Id>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:PAIS)}</cat:Id>
                <cat:Descripcion>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:DESCRIPCION_PAIS)}</cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:DESCRIPCION_CORTA_PAIS)}</cat:DescripcionCorta>
                <cat:estatus>{
                if($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:ESTADO_PAIS = '1')
                then (fn:true())
                else(fn:false())
                }</cat:estatus>
                <cat:codigoExterno>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:COD_EXTERNO_PAIS)}</cat:codigoExterno>
            </cli:pais>
            <cli:grupoEconomico>
                <cat:Id>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:GRUPO_ECONOMICO)}</cat:Id>
                <cat:Descripcion>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:DESCRIPCION_GRUPO)}</cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:DESCRIPCION_CORTA_GRUPO)}</cat:DescripcionCorta>
                <cat:estatus>{
                if($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:ESTATUS_GRUPO = '1')
                then (fn:true())
                else(fn:false())
                }</cat:estatus>
                <cat:codigoExterno>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:COD_EXTERNO_GRUPO)}</cat:codigoExterno>
            </cli:grupoEconomico>
            <cli:tipoIdentificacion>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:TIPO_IDENTIFICACION)}</cli:tipoIdentificacion>
            <cli:numeroIdentificacion>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:NUMERO_IDENTIFICACION)}</cli:numeroIdentificacion>
            <cli:direccion>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:DIRECCION)}</cli:direccion>
            <cli:telefono> </cli:telefono>
            <cli:fax></cli:fax>
            <cli:email></cli:email>
            <cli:ciudad></cli:ciudad>
            <cli:oficina>
                <cat:Id>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:OFICINA)}</cat:Id>
                <cat:Descripcion>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:DESCRIPCION_OFICINA)}</cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:DESCRIPCION_CORTA_OFICINA)}</cat:DescripcionCorta>
                <cat:estatus>{
                if($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:ESTATUS_OFICINA = '1')
                then (fn:true())
                else(fn:false())
                }</cat:estatus>
                <cat:codigoExterno>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:COD_EXTERNO_OFICINA)}</cat:codigoExterno>
            </cli:oficina>
            <cli:grupoEmpresarial></cli:grupoEmpresarial>
            <cli:fechaRegistro>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:FECHA_REGISTRO)}</cli:fechaRegistro>
            <cli:fechaAprobacion>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:FECHA_APROBACION)}</cli:fechaAprobacion>
            <cli:ejecutivo>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:EJECUTIVO)}</cli:ejecutivo>
           
           {
           for $contacto in $ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput
         return
         
                    if ( fn:string-length($contacto/ns1:ID_CONTACTO/text()) > 0 )
                    then  
            <cli:Contactos>
                <cli:Contacto>
                    <cli:idContacto>{fn:data($contacto/ns1:ID_CONTACTO)}</cli:idContacto>
                    <cli:idCliente>{fn:data($contacto/ns1:ID_CLIENTE)}</cli:idCliente>
                    <cli:nombre>{fn:data($contacto/ns1:NOMBRE)}</cli:nombre>
                    <cli:telefono>{fn:data($contacto/ns1:TELEFONO)}</cli:telefono>
                    <cli:correoElectronico>{fn:data($contacto/ns1:CORREO_ELECTRONICO)}</cli:correoElectronico>
                    <cli:cargo>{fn:data($contacto/ns1:CARGO)}</cli:cargo>
                    <cli:fechaRegistro>{fn:data($contacto/ns1:FECHA_REGISTRO_CONTACTO)}</cli:fechaRegistro>
                    <cli:idContactosClientes></cli:idContactosClientes>
                    <cli:recibeAvisoCobro>
                    {if (xs:string(fn:data($contacto/ns1:RECIBE_AVISO_COBRO)) = '1')
                      then true()
                      else false()
                    }
                    </cli:recibeAvisoCobro>
                </cli:Contacto>
            </cli:Contactos>
                else ()
                }
           
            <cli:InformacionCorrecta></cli:InformacionCorrecta>
            <cli:ActualizacionPermitida></cli:ActualizacionPermitida>
            <cli:comentarioAprobacion>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:COMENTARIO_APROBACION)}</cli:comentarioAprobacion>
            <cli:revisadoAprobacion>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:AUTORIZO)}</cli:revisadoAprobacion>
            <cli:status></cli:status>
            <cli:fechaBaja>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:FECHA_BAJA)}</cli:fechaBaja>
            <cli:diaPago>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:DIA_PAGO)}</cli:diaPago>
            <cli:SCR>
                <cat:Id>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:SCR)}</cat:Id>
                <cat:Descripcion>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:DESCRIPCION_SCR)}</cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:DESCRIPCION_CORTA_SCR)}</cat:DescripcionCorta>
                <cat:estatus>{
                if($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:ESTATUS_SCR = '1')
                then (fn:true())
                else(fn:false())
                }</cat:estatus>
                <cat:codigoExterno>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:COD_EXTERNO_SCR)}</cat:codigoExterno>
            </cli:SCR>
            <cli:perspectiva>
                <cat:Id>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:PERSPECTIVA)}</cat:Id>
                <cat:Descripcion>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:DESCRIPCION_PERSPECTIVA)}</cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:DESCRIPCION_CORTA_PERSPECTIVA)}</cat:DescripcionCorta>
                <cat:estatus>{
                if($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:ESTATUS_PERSPECTIVA = '1')
                then (fn:true())
                else(fn:false())
                }</cat:estatus>
                <cat:codigoExterno>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:COD_EXTERNO_PERSPECTIVA)}</cat:codigoExterno>
            </cli:perspectiva>
            <cli:responsableCliente>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:EJECUTIVO)}</cli:responsableCliente>
            <cli:requiereEnvioAutomatico>{fn:data($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1]/ns1:REQUIERE_ENVIO_AUTOMATICO)}</cli:requiereEnvioAutomatico>
        </ns2:Cliente>
        {
        for $Operacion in $ConsultarOperacionesByIdClienteResponse/ns2:operaciones
        return
        <ns2:Operaciones>
            <cli:idOperacion>{fn:data($Operacion/cli:idOperacion)}</cli:idOperacion>
            <cli:nombre>{fn:data($Operacion/cli:nombre)}</cli:nombre>
            <cli:cliente>{fn:data($Operacion/cli:cliente)}</cli:cliente>
            <cli:pais>{fn:data($Operacion/cli:pais)}</cli:pais>
            <cli:producto>{fn:data($Operacion/cli:producto)}</cli:producto>
            <cli:responsable>{fn:data($Operacion/cli:responsable)}</cli:responsable>
            <cli:etapa>{fn:data($Operacion/cli:etapa)}</cli:etapa>
            <cli:estado>{fn:data($Operacion/cli:estado)}</cli:estado>
            <cli:SCR>
                {
                    if ($Operacion/cli:SCR/cat:Id)
                    then <cat:Id>{fn:data($Operacion/cli:SCR/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($Operacion/cli:SCR/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($Operacion/cli:SCR/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
                {
                    if ($Operacion/cli:SCR/cat:DescripcionCorta)
                    then <cat:DescripcionCorta>{fn:data($Operacion/cli:SCR/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    else ()
                }
                {
                    if ($Operacion/cli:SCR/cat:estatus)
                    then <cat:estatus>{fn:data($Operacion/cli:SCR/cat:estatus)}</cat:estatus>
                    else ()
                }
                {
                    if ($Operacion/cli:SCR/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($Operacion/cli:SCR/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </cli:SCR>
            <cli:perspectiva>
                {
                    if ($Operacion/cli:perspectiva/cat:Id)
                    then <cat:Id>{fn:data($Operacion/cli:perspectiva/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($Operacion/cli:perspectiva/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($Operacion/cli:perspectiva/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
                {
                    if ($Operacion/cli:perspectiva/cat:DescripcionCorta)
                    then <cat:DescripcionCorta>{fn:data($Operacion/cli:perspectiva/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    else ()
                }
                {
                    if ($Operacion/cli:perspectiva/cat:estatus)
                    then <cat:estatus>{fn:data($Operacion/cli:perspectiva/cat:estatus)}</cat:estatus>
                    else ()
                }
                {
                    if ($Operacion/cli:perspectiva/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($Operacion/cli:perspectiva/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </cli:perspectiva>
        </ns2:Operaciones>
        }
         {
        if (fn:count($ConsultarClienteByIdClienteOutputCollection/ns1:ConsultarClienteByIdClienteOutput[1])>0)
        then
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta realizada exitosamente</res:message>
        </ns2:Resultado>
        else
        <ns2:Resultado>
            <res:result>ERROR</res:result>
            <res:message>Consulta sin resultados</res:message>
        </ns2:Resultado>
        }
    </ns2:ConsultarClienteResponse>
};

local:func($ConsultarClienteByIdClienteOutputCollection, $ConsultarOperacionesByIdClienteResponse)
