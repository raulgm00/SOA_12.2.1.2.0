xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CrearAvisoCobro";
(:: import schema at "../../CrearAvisoCobro/XSD/CrearAvisoCobro_sp.xsd" ::)

declare namespace ges = "http://www.bcie.org/GestionCobroBO";

declare variable $CrearAvisoCobroDemandaRequest as element() (:: schema-element(ns1:CrearAvisoCobroDemandaRequest) ::) external;

declare function local:func($CrearAvisoCobroDemandaRequest as element() (:: schema-element(ns1:CrearAvisoCobroDemandaRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_FECHAINICIO>{fn:data($CrearAvisoCobroDemandaRequest/ns1:Aviso/ges:fechaInicio)}</ns2:P_FECHAINICIO>
        <ns2:P_FECHAFIN>{fn:data($CrearAvisoCobroDemandaRequest/ns1:Aviso/ges:fechaFin)}</ns2:P_FECHAFIN>
        <ns2:P_ESPUBLICO>{fn:data($CrearAvisoCobroDemandaRequest/ns1:Aviso/ges:esPublico)}</ns2:P_ESPUBLICO>
        <ns2:P_TIPOAVISO>{fn:data($CrearAvisoCobroDemandaRequest/ns1:Aviso/ges:tipoAviso)}</ns2:P_TIPOAVISO>
        <ns2:P_LINEA>{fn:data($CrearAvisoCobroDemandaRequest/ns1:Aviso/ges:linea)}</ns2:P_LINEA>
        <ns2:P_CLIENTE>{fn:data($CrearAvisoCobroDemandaRequest/ns1:Aviso/ges:cliente)}</ns2:P_CLIENTE>
        <ns2:P_MONEDA>{fn:data($CrearAvisoCobroDemandaRequest/ns1:Aviso/ges:moneda)}</ns2:P_MONEDA>
        <ns2:P_PAIS>{fn:data($CrearAvisoCobroDemandaRequest/ns1:Aviso/ges:pais)}</ns2:P_PAIS>
        <ns2:P_SECTORINSTITUCIONAL>{if(fn:lower-case($CrearAvisoCobroDemandaRequest/ns1:Aviso/ges:esPublico) = 's')then "1" else if (fn:lower-case($CrearAvisoCobroDemandaRequest/ns1:Aviso/ges:esPublico) = 'n')then ("2") else ("3")}</ns2:P_SECTORINSTITUCIONAL>
        <ns2:P_PERIODICIDAD>{fn:data($CrearAvisoCobroDemandaRequest/ns1:Aviso/ges:periodicidad)}</ns2:P_PERIODICIDAD>
        <ns2:P_TIPOSALDO>{fn:data($CrearAvisoCobroDemandaRequest/ns1:Aviso/ges:tipoSaldo)}</ns2:P_TIPOSALDO>
        <ns2:P_FONDOS>{fn:data($CrearAvisoCobroDemandaRequest/ns1:Aviso/ges:fondos)}</ns2:P_FONDOS>
        <ns2:P_CODIGOOPERACION>{fn:data($CrearAvisoCobroDemandaRequest/ns1:Aviso/ges:operacion)}</ns2:P_CODIGOOPERACION>
        <ns2:P_USUARIOCREADOR>{fn:data($CrearAvisoCobroDemandaRequest/ns1:Aviso/ges:usuarioCreador)}</ns2:P_USUARIOCREADOR>
    </ns2:InputParameters>
};

local:func($CrearAvisoCobroDemandaRequest)
