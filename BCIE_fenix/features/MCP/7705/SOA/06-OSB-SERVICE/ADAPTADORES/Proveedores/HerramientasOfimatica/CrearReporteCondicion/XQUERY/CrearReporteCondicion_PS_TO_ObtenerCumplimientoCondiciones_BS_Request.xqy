xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ObtenerCumplimientoCondiciones";
(:: import schema at "../XSD/ObtenerCumplimientoCondiciones_sp.xsd" ::)

declare variable $CrearReporteCondicionRequest as element() (:: schema-element(ns1:CrearReporteCondicionRequest) ::) external;

declare function local:func($CrearReporteCondicionRequest as element() (:: schema-element(ns1:CrearReporteCondicionRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_ID_OPERACION>{fn:data($CrearReporteCondicionRequest/ns1:idOperacion)}</ns2:P_ID_OPERACION>
        {
            if ($CrearReporteCondicionRequest/ns1:idAgrupador)
            then <ns2:P_AGRUPADOR>{fn:data($CrearReporteCondicionRequest/ns1:idAgrupador)}</ns2:P_AGRUPADOR>
            else ()
        }
        {
            if ($CrearReporteCondicionRequest/ns1:idCondicion)
            then <ns2:P_ID_CONDICION>{fn:data($CrearReporteCondicionRequest/ns1:idCondicion)}</ns2:P_ID_CONDICION>
            else ()
        }
        {
            if ($CrearReporteCondicionRequest/ns1:idTipoControl)
            then <ns2:P_ID_TIPO_CONTROL>{fn:data($CrearReporteCondicionRequest/ns1:idTipoControl)}</ns2:P_ID_TIPO_CONTROL>
            else ()
        }
        {
            if ($CrearReporteCondicionRequest/ns1:banEstatus)
            then <ns2:P_BAN_ESTATUS>{fn:data($CrearReporteCondicionRequest/ns1:banEstatus)}</ns2:P_BAN_ESTATUS>
            else ()
        }
        {
            if ($CrearReporteCondicionRequest/ns1:listaEventos)
            then <ns2:P_LISTA_EVENTOS>{fn:data($CrearReporteCondicionRequest/ns1:listaEventos)}</ns2:P_LISTA_EVENTOS>
            else ()
        }
    </ns2:InputParameters>
};

local:func($CrearReporteCondicionRequest)
