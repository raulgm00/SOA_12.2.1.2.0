xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearCondicion_DB";
(:: import schema at "../XSD/CrearCondicion_DB_table.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $CrearCondicionRequest as element() (:: schema-element(ns1:CrearCondicionRequest) ::) external;

declare function local:func($CrearCondicionRequest as element() (:: schema-element(ns1:CrearCondicionRequest) ::)) as element() (:: schema-element(ns2:CondicionCollection) ::) {
    <ns2:CondicionCollection>
        <ns2:Condicion>
             <ns2:idOperacion>{fn:data($CrearCondicionRequest/ns1:Condicion/con:operacion)}</ns2:idOperacion>
            {
                if ($CrearCondicionRequest/ns1:Condicion/con:nombre)
                then <ns2:nombre>{fn:data($CrearCondicionRequest/ns1:Condicion/con:nombre)}</ns2:nombre>
                else ()
            }
            {
                if ($CrearCondicionRequest/ns1:Condicion/con:descripcion)
                then <ns2:descripcion>{fn:data($CrearCondicionRequest/ns1:Condicion/con:descripcion)}</ns2:descripcion>
                else ()
            }
            {
                if ($CrearCondicionRequest/ns1:Condicion/con:tipoCondicion/con:idCatCondicion)
                then <ns2:idTcaCondicion>{fn:data($CrearCondicionRequest/ns1:Condicion/con:tipoCondicion/con:idCatCondicion)}</ns2:idTcaCondicion>
                else ()
            }
            {
                if ($CrearCondicionRequest/ns1:Condicion/con:controlCondicion/cat:Id)
                then <ns2:idTcaControlCondicion>{fn:data($CrearCondicionRequest/ns1:Condicion/con:controlCondicion/cat:Id)}</ns2:idTcaControlCondicion>
                else ()
            }
            {
                if ($CrearCondicionRequest/ns1:Condicion/con:tipoFechaInicio/cat:Id)
                then <ns2:idTcaTipoFechaInicio>{fn:data($CrearCondicionRequest/ns1:Condicion/con:tipoFechaInicio/cat:Id)}</ns2:idTcaTipoFechaInicio>
                else ()
            }
            {
                if ($CrearCondicionRequest/ns1:Condicion/con:fechaInicio)
                then <ns2:fechaInicio>{fn:data($CrearCondicionRequest/ns1:Condicion/con:fechaInicio)}</ns2:fechaInicio>
                else ()
            }
            {
                if ($CrearCondicionRequest/ns1:Condicion/con:plazo)
                then <ns2:plazo>{fn:data($CrearCondicionRequest/ns1:Condicion/con:plazo)}</ns2:plazo>
                else ()
            }
            {
                if ($CrearCondicionRequest/ns1:Condicion/con:frecuenciaPlazo/cat:Id)
                then <ns2:idTcaFrecuenciaPlazo>{fn:data($CrearCondicionRequest/ns1:Condicion/con:frecuenciaPlazo/cat:Id)}</ns2:idTcaFrecuenciaPlazo>
                else ()
            }
            {
                if ($CrearCondicionRequest/ns1:Condicion/con:fechaFinal)
                then <ns2:fechaFinal>{fn:data($CrearCondicionRequest/ns1:Condicion/con:fechaFinal)}</ns2:fechaFinal>
                else ()
            }
            {
                if ($CrearCondicionRequest/ns1:Condicion/con:estadoTCC/atr:id)
                then <ns2:idTcaEstadoTcc>{fn:data($CrearCondicionRequest/ns1:Condicion/con:estadoTCC/atr:id)}</ns2:idTcaEstadoTcc>
                else ()
            }
            {
                if ($CrearCondicionRequest/ns1:Condicion/con:subEstadoTCC/atr:id)
                then <ns2:idTcaSubEstadoTcc>{fn:data($CrearCondicionRequest/ns1:Condicion/con:subEstadoTCC/atr:id)}</ns2:idTcaSubEstadoTcc>
                else ()
            }
            <ns2:fechaRegistro>{fn:current-date()}</ns2:fechaRegistro>
            <ns2:banEstatus>1</ns2:banEstatus>
            {
                if ($CrearCondicionRequest/ns1:Condicion/con:fechaVigencia)
                then <ns2:fechaVigencia>{fn:data($CrearCondicionRequest/ns1:Condicion/con:fechaVigencia)}</ns2:fechaVigencia>
                else ()
            }
        </ns2:Condicion>
    </ns2:CondicionCollection>
};

local:func($CrearCondicionRequest)
