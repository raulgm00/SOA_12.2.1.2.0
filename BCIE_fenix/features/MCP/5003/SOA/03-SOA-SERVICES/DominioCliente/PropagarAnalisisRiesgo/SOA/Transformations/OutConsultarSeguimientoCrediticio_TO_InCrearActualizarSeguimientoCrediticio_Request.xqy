xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace cli="http://www.bcie.org/ClienteMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd"::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli1 = "http://www.bcie.org/ClienteBO";

declare variable $outConsultarSeguimientoCrediticio.response as element() (:: schema-element(cli:ConsultarSeguimientoCrediticioByIdClienteResponse) ::) external;

declare function local:funcOutconsultarseguimientocrediticio_to_increaractualizarseguimientocrediticio_request($outConsultarSeguimientoCrediticio.response as element() (:: schema-element(cli:ConsultarSeguimientoCrediticioByIdClienteResponse) ::)) as element() (:: schema-element(cli:CrearActualizarSeguimientoCrediticioRequest) ::) {
    <cli:CrearActualizarSeguimientoCrediticioRequest>
        <cli:SeguimientoCrediticio>
            <cli1:id>{fn:data($outConsultarSeguimientoCrediticio.response/cli:SeguimientoCrediticio/cli1:id)}</cli1:id>
            <cli1:idCliente>{fn:data($outConsultarSeguimientoCrediticio.response/cli:SeguimientoCrediticio/cli1:idCliente)}</cli1:idCliente>
            <cli1:estadoSCR>
                <cat:Id>2</cat:Id>
            </cli1:estadoSCR>    
        </cli:SeguimientoCrediticio>
    </cli:CrearActualizarSeguimientoCrediticioRequest>
};

local:funcOutconsultarseguimientocrediticio_to_increaractualizarseguimientocrediticio_request($outConsultarSeguimientoCrediticio.response)
