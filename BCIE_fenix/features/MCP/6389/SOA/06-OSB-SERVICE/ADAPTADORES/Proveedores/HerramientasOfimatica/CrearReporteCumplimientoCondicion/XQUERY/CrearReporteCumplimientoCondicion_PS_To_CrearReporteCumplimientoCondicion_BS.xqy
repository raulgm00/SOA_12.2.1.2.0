xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://service.org.bcie.www/";
(:: import schema at "../XSD/GenerarCumplimientoCondicion.xsd" ::)
declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare namespace cum = "http://www.bcie.org/herramientaOfismatica/cumplimientocondiciones";

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare variable $CrearReporteCumplimientoCondicionRequest as element() (:: schema-element(ns1:CrearReporteCumplimientoCondicionRequest) ::) external;

declare function local:func($CrearReporteCumplimientoCondicionRequest as element() (:: schema-element(ns1:CrearReporteCumplimientoCondicionRequest) ::)) as element() (:: schema-element(ns2:generarCumplimientoCondicion) ::) {
    <ns2:generarCumplimientoCondicion>
        <cumplimiento>
            <cum:lstCondicion>
            {for $condicion in $CrearReporteCumplimientoCondicionRequest/ns1:ListaCondicion/con:Condicion
            return
                <cum:condicion>
                    <cum:tipo>{fn:data($condicion/con:tipoCondicion/con:tipoCondicion/cat:Descripcion)}</cum:tipo>
                    <cum:nombre>{fn:data($condicion/con:nombre)}</cum:nombre>
                    <cum:descripcion>{fn:data($condicion/con:descripcion)}</cum:descripcion>
                    <cum:estado>{fn:data($condicion/con:estadoTCC/atr:descripcion)}</cum:estado>
                    <cum:fechaVigenciaValidacion>{fn:data($condicion/con:fechaVigencia)}</cum:fechaVigenciaValidacion>
                    {if (count($condicion/con:categoriaCondicion) = 1) 
                    then
                        <cum:priCategoria>{fn:data($condicion/con:categoriaCondicion/con:descripcion)}</cum:priCategoria>
                        else ()
                    }
                    {if (count($condicion/con:categoriaCondicion) = 1) 
                    then
                        <cum:priValidacion>{fn:data($condicion/con:categoriaCondicion/con:validadores/cat:Descripcion)}</cum:priValidacion>
                    else ()
                    }
                    {if (count($condicion/con:categoriaCondicion) > 1) 
                    then
                        for $categoriaValidacion in $condicion/con:categoriaCondicion
                        return
                        <cum:categoriaValidacion>
                            <cum:categoria>{fn:data($categoriaValidacion/con:descripcion)}</cum:categoria>
                            <cum:validacion>{fn:data($categoriaValidacion/con:validadores/cat:Descripcion)}</cum:validacion>
                        </cum:categoriaValidacion>
                        else ()                    
                    }
                </cum:condicion>
              }
            </cum:lstCondicion>
        </cumplimiento>
    </ns2:generarCumplimientoCondicion>
};

local:func($CrearReporteCumplimientoCondicionRequest)
