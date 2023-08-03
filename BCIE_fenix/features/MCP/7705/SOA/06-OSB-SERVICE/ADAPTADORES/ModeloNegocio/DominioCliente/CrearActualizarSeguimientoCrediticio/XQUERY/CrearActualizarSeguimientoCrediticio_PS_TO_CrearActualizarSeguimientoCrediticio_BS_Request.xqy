xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearActualizarSeguimientoCrediticio_BD";
(:: import schema at "../XSD/CrearActualizarSeguimientoCrediticio_BD_table.xsd" ::)

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $CrearActualizarSeguimientoCrediticioRequest as element() (:: schema-element(ns1:CrearActualizarSeguimientoCrediticioRequest) ::) external;

declare function local:func($CrearActualizarSeguimientoCrediticioRequest as element() (:: schema-element(ns1:CrearActualizarSeguimientoCrediticioRequest) ::)) as element() (:: schema-element(ns2:SeguimientoCrediticioCollection) ::) {
    <ns2:SeguimientoCrediticioCollection>
        <ns2:SeguimientoCrediticio>
            <ns2:id>{fn:data($CrearActualizarSeguimientoCrediticioRequest/ns1:SeguimientoCrediticio/cli:id)}</ns2:id>
            <ns2:idCliente>{fn:data($CrearActualizarSeguimientoCrediticioRequest/ns1:SeguimientoCrediticio/cli:idCliente)}</ns2:idCliente>
            <ns2:tipoInicio>{fn:data($CrearActualizarSeguimientoCrediticioRequest/ns1:SeguimientoCrediticio/cli:tipoInicio)}</ns2:tipoInicio>
            <ns2:tipoRevision>{fn:data($CrearActualizarSeguimientoCrediticioRequest/ns1:SeguimientoCrediticio/cli:tipoRevision)}</ns2:tipoRevision>
            {
                if ($CrearActualizarSeguimientoCrediticioRequest/ns1:SeguimientoCrediticio/cli:scr/cat:Id)
                then <ns2:idTcaScr>{fn:data($CrearActualizarSeguimientoCrediticioRequest/ns1:SeguimientoCrediticio/cli:scr/cat:Id)}</ns2:idTcaScr>
                else ()
            }
            {
                if ($CrearActualizarSeguimientoCrediticioRequest/ns1:SeguimientoCrediticio/cli:estadoSCR/cat:Id)
                then <ns2:idTcaEstadoScr>{fn:data($CrearActualizarSeguimientoCrediticioRequest/ns1:SeguimientoCrediticio/cli:estadoSCR/cat:Id)}</ns2:idTcaEstadoScr>
                else ()
            }
            {
                if ($CrearActualizarSeguimientoCrediticioRequest/ns1:SeguimientoCrediticio/cli:perspectiva/cat:Id)
                then <ns2:idTcaPerspectiva>{fn:data($CrearActualizarSeguimientoCrediticioRequest/ns1:SeguimientoCrediticio/cli:perspectiva/cat:Id)}</ns2:idTcaPerspectiva>
                else ()
            }
            {
            if ($CrearActualizarSeguimientoCrediticioRequest/ns1:SeguimientoCrediticio/cli:esCalificacionDividida)then
            <ns2:esCalificacionDividida>{
            if ($CrearActualizarSeguimientoCrediticioRequest/ns1:SeguimientoCrediticio/cli:esCalificacionDividida = true()) 
            then (1)
            else(0)}</ns2:esCalificacionDividida>
            else()}
            <ns2:fechaAprobado>{fn:data($CrearActualizarSeguimientoCrediticioRequest/ns1:SeguimientoCrediticio/cli:fechaAprobado)}</ns2:fechaAprobado>
            <ns2:instanciaProceso>{fn:data($CrearActualizarSeguimientoCrediticioRequest/ns1:SeguimientoCrediticio/cli:instanciaProceso)}</ns2:instanciaProceso>
            <ns2:loginUsuario>{fn:data($CrearActualizarSeguimientoCrediticioRequest/ns1:SeguimientoCrediticio/cli:loginUsuario)}</ns2:loginUsuario>
            <ns2:nombreUsuario>{fn:data($CrearActualizarSeguimientoCrediticioRequest/ns1:SeguimientoCrediticio/cli:nombreUsuario)}</ns2:nombreUsuario>
            <ns2:fechaRegistro>{fn:data($CrearActualizarSeguimientoCrediticioRequest/ns1:SeguimientoCrediticio/cli:fechaRegistro)}</ns2:fechaRegistro>
            {
            if ($CrearActualizarSeguimientoCrediticioRequest/ns1:SeguimientoCrediticio/cli:banEstatus)then
            <ns2:banEstatus>{
            if($CrearActualizarSeguimientoCrediticioRequest/ns1:SeguimientoCrediticio/cli:banEstatus = true())
            then (1)
            else(0)}</ns2:banEstatus>
            else()}
        </ns2:SeguimientoCrediticio>
    </ns2:SeguimientoCrediticioCollection>
};

local:func($CrearActualizarSeguimientoCrediticioRequest)
