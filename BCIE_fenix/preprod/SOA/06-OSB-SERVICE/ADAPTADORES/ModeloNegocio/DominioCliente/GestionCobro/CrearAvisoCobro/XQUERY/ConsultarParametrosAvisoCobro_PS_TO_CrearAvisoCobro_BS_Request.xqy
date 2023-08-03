xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CrearAvisoCobro";
(:: import schema at "../XSD/CrearAvisoCobro_sp.xsd" ::)

declare namespace ges = "http://www.bcie.org/GestionCobroBO";

declare variable $ConsultarParametrosAvisoCobroResponse as element() (:: schema-element(ns1:ConsultarParametrosAvisoCobroResponse) ::) external;

declare variable $CrearAvisoCobroRequest as element() (:: schema-element(ns1:CrearAvisoCobroRequest) ::) external;

declare function local:func($ConsultarParametrosAvisoCobroResponse as element() (:: schema-element(ns1:ConsultarParametrosAvisoCobroResponse) ::),
$CrearAvisoCobroRequest as element() (:: schema-element(ns1:CrearAvisoCobroRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_FECHAINICIO>{fn:data($ConsultarParametrosAvisoCobroResponse/ns1:Parametros/ges:fechaInicio)}</ns2:P_FECHAINICIO>
        <ns2:P_FECHAFIN>{fn:data($ConsultarParametrosAvisoCobroResponse/ns1:Parametros/ges:fechaFin)}</ns2:P_FECHAFIN>
        <ns2:P_ESPUBLICO>{fn:data($ConsultarParametrosAvisoCobroResponse/ns1:Parametros/ges:esPublico)}</ns2:P_ESPUBLICO>
              <ns2:P_TIPOAVISO>{fn:data($ConsultarParametrosAvisoCobroResponse/ns1:Parametros/ges:tipoAviso)}</ns2:P_TIPOAVISO>
        <ns2:P_LINEA>{fn:data($ConsultarParametrosAvisoCobroResponse/ns1:Parametros/ges:linea)}</ns2:P_LINEA>
        <ns2:P_CLIENTE>{fn:data($ConsultarParametrosAvisoCobroResponse/ns1:Parametros/ges:cliente)}</ns2:P_CLIENTE>
        <ns2:P_MONEDA>{fn:data($ConsultarParametrosAvisoCobroResponse/ns1:Parametros/ges:moneda)}</ns2:P_MONEDA>
        <ns2:P_PAIS>{fn:data($ConsultarParametrosAvisoCobroResponse/ns1:Parametros/ges:pais)}</ns2:P_PAIS>
        <ns2:P_SECTORINSTITUCIONAL>{fn:data($ConsultarParametrosAvisoCobroResponse/ns1:Parametros/ges:sector)}</ns2:P_SECTORINSTITUCIONAL>
        <ns2:P_PERIODICIDAD>{fn:data($ConsultarParametrosAvisoCobroResponse/ns1:Parametros/ges:periodicidad)}</ns2:P_PERIODICIDAD>
        <ns2:P_TIPOSALDO>{fn:data($ConsultarParametrosAvisoCobroResponse/ns1:Parametros/ges:tipoSaldo)}</ns2:P_TIPOSALDO>
        <ns2:P_FONDOS>{fn:data($ConsultarParametrosAvisoCobroResponse/ns1:Parametros/ges:fondos)}</ns2:P_FONDOS>
        <ns2:P_CODIGOOPERACION>{fn:data($ConsultarParametrosAvisoCobroResponse/ns1:Parametros/ges:operacion)}</ns2:P_CODIGOOPERACION>
        <ns2:P_USUARIOCREADOR>{fn:data($ConsultarParametrosAvisoCobroResponse/ns1:Parametros/ges:usuarioCreador)}</ns2:P_USUARIOCREADOR>
    </ns2:InputParameters>
};

local:func($ConsultarParametrosAvisoCobroResponse, $CrearAvisoCobroRequest)
