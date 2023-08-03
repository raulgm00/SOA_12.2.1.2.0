xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://service.org.bcie.www/";
(:: import schema at "../XSD/ReporteElegibilidad.xsd" ::)
declare namespace ns1="http://xmlns.bcie.com/CuestionarioMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Cuestionario/V1/Schema/CuestionarioMO.xsd" ::)

declare variable $CrearReporteElegibilidadRequest as element() (:: schema-element(ns1:CrearReporteElegibilidadRequest) ::) external;
declare variable $NoObjecion as element() external;
declare variable $idDeclaracion as element() external;

declare function local:func($CrearReporteElegibilidadRequest as element() (:: schema-element(ns1:CrearReporteElegibilidadRequest) ::),
                            $NoObjecion as element(),
                            $idDeclaracion as element()) as element() (:: schema-element(ns2:Reporte) ::) {
    <ns2:Reporte>
        <idOperacion>{fn:data($CrearReporteElegibilidadRequest/ns1:idOperacion)}</idOperacion>
        <idDeclaracion>{fn:data($idDeclaracion)}</idDeclaracion>
        <NoObjecion>{fn:data($NoObjecion)}</NoObjecion>
        {
          if ($CrearReporteElegibilidadRequest/ns1:ResponsableOperacion)
          then <ResponsableOperacion>{fn:data($CrearReporteElegibilidadRequest/ns1:ResponsableOperacion)}</ResponsableOperacion>
          else()
        }
        {
          if ($CrearReporteElegibilidadRequest/ns1:TipoGeneracion)
          then <TipoGeneracion>{fn:data($CrearReporteElegibilidadRequest/ns1:TipoGeneracion)}</TipoGeneracion>
          else()
        }
    </ns2:Reporte>
};

local:func($CrearReporteElegibilidadRequest,$NoObjecion, $idDeclaracion)
