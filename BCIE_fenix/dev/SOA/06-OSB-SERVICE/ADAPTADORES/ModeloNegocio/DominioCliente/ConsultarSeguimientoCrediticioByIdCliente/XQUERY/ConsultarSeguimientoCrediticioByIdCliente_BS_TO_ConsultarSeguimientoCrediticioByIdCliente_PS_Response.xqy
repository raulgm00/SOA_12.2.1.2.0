xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarSeguimientoCrediticioByIdClienteDB";
(:: import schema at "../XSD/ConsultarSeguimientoCrediticioByIdClienteDB.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarSeguimientoCrediticioByIdClienteDBOutputCollection as element() (:: schema-element(ns1:ConsultarSeguimientoCrediticioByIdClienteDBOutputCollection) ::) external;

declare function local:func($ConsultarSeguimientoCrediticioByIdClienteDBOutputCollection as element() (:: schema-element(ns1:ConsultarSeguimientoCrediticioByIdClienteDBOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarSeguimientoCrediticioByIdClienteResponse) ::) {
    <ns2:ConsultarSeguimientoCrediticioByIdClienteResponse>
        <ns2:SeguimientoCrediticio>
            <cli:id>{fn:data($ConsultarSeguimientoCrediticioByIdClienteDBOutputCollection/ns1:ConsultarSeguimientoCrediticioByIdClienteDBOutput[1]/ns1:ID)}</cli:id>
            <cli:idCliente>{fn:data($ConsultarSeguimientoCrediticioByIdClienteDBOutputCollection/ns1:ConsultarSeguimientoCrediticioByIdClienteDBOutput[1]/ns1:ID_CLIENTE)}</cli:idCliente>
            <cli:tipoInicio>{fn:data($ConsultarSeguimientoCrediticioByIdClienteDBOutputCollection/ns1:ConsultarSeguimientoCrediticioByIdClienteDBOutput[1]/ns1:TIPO_INICIO)}</cli:tipoInicio>
            <cli:tipoRevision>{fn:data($ConsultarSeguimientoCrediticioByIdClienteDBOutputCollection/ns1:ConsultarSeguimientoCrediticioByIdClienteDBOutput[1]/ns1:TIPO_REVISION)}</cli:tipoRevision>
            <cli:scr>
                <cat:Id>{fn:data($ConsultarSeguimientoCrediticioByIdClienteDBOutputCollection/ns1:ConsultarSeguimientoCrediticioByIdClienteDBOutput[1]/ns1:ID_TCA_SCR)}</cat:Id>
                <cat:Descripcion></cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($ConsultarSeguimientoCrediticioByIdClienteDBOutputCollection/ns1:ConsultarSeguimientoCrediticioByIdClienteDBOutput[1]/ns1:SCR)}</cat:DescripcionCorta>
                <cat:estatus></cat:estatus>
                <cat:codigoExterno></cat:codigoExterno>
            </cli:scr>
            <cli:estadoSCR>
                <cat:Id>{fn:data($ConsultarSeguimientoCrediticioByIdClienteDBOutputCollection/ns1:ConsultarSeguimientoCrediticioByIdClienteDBOutput[1]/ns1:ID_TCA_ESTADO_SCR)}</cat:Id>
                <cat:Descripcion></cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($ConsultarSeguimientoCrediticioByIdClienteDBOutputCollection/ns1:ConsultarSeguimientoCrediticioByIdClienteDBOutput[1]/ns1:ESTADO_SCR)}</cat:DescripcionCorta>
                <cat:estatus></cat:estatus>
                <cat:codigoExterno></cat:codigoExterno>
            </cli:estadoSCR>
            <cli:perspectiva>
                <cat:Id>{fn:data($ConsultarSeguimientoCrediticioByIdClienteDBOutputCollection/ns1:ConsultarSeguimientoCrediticioByIdClienteDBOutput[1]/ns1:ID_TCA_PERSPECTIVA)}</cat:Id>
                <cat:Descripcion></cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($ConsultarSeguimientoCrediticioByIdClienteDBOutputCollection/ns1:ConsultarSeguimientoCrediticioByIdClienteDBOutput[1]/ns1:PERSPECTIVA)}</cat:DescripcionCorta>
                <cat:estatus></cat:estatus>
                <cat:codigoExterno></cat:codigoExterno>
            </cli:perspectiva>
            <cli:esCalificacionDividida>{
            if (string(fn:data($ConsultarSeguimientoCrediticioByIdClienteDBOutputCollection/ns1:ConsultarSeguimientoCrediticioByIdClienteDBOutput[1]/ns1:ES_CALIFICACION_DIVIDIDA)) = '1')
            then ('true')
            else ('false')}</cli:esCalificacionDividida>
            <cli:fechaAprobado>{fn:data($ConsultarSeguimientoCrediticioByIdClienteDBOutputCollection/ns1:ConsultarSeguimientoCrediticioByIdClienteDBOutput[1]/ns1:FECHA_APROBADO)}</cli:fechaAprobado>
            <cli:instanciaProceso>{fn:data($ConsultarSeguimientoCrediticioByIdClienteDBOutputCollection/ns1:ConsultarSeguimientoCrediticioByIdClienteDBOutput[1]/ns1:INSTANCIA_PROCESO)}</cli:instanciaProceso>
            <cli:loginUsuario>{fn:data($ConsultarSeguimientoCrediticioByIdClienteDBOutputCollection/ns1:ConsultarSeguimientoCrediticioByIdClienteDBOutput[1]/ns1:LOGIN_USUARIO)}</cli:loginUsuario>
            <cli:nombreUsuario>{fn:data($ConsultarSeguimientoCrediticioByIdClienteDBOutputCollection/ns1:ConsultarSeguimientoCrediticioByIdClienteDBOutput[1]/ns1:NOMBRE_USUARIO)}</cli:nombreUsuario>
            <cli:fechaRegistro>{fn:data($ConsultarSeguimientoCrediticioByIdClienteDBOutputCollection/ns1:ConsultarSeguimientoCrediticioByIdClienteDBOutput[1]/ns1:FECHA_REGISTRO)}</cli:fechaRegistro>
            <cli:banEstatus>{
            if (fn:data($ConsultarSeguimientoCrediticioByIdClienteDBOutputCollection/ns1:ConsultarSeguimientoCrediticioByIdClienteDBOutput[1]/ns1:BAN_ESTATUS) = 1)
            then ('true')
            else ('false')}</cli:banEstatus>
        </ns2:SeguimientoCrediticio>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>{
            if (fn:count($ConsultarSeguimientoCrediticioByIdClienteDBOutputCollection/ns1:ConsultarSeguimientoCrediticioByIdClienteDBOutput) > 0)
            then ("Consulta exitosa")
            else("No existen registros")}</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:ConsultarSeguimientoCrediticioByIdClienteResponse>
};

local:func($ConsultarSeguimientoCrediticioByIdClienteDBOutputCollection)
