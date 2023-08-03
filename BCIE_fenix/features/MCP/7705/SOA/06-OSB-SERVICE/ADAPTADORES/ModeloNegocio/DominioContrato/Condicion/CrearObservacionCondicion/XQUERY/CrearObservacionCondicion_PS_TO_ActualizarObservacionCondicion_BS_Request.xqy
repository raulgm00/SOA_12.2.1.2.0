xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ActualizarObservacionCondicion";
(:: import schema at "../XSD/ActualizarObservacionCondicion_table.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $CrearObservacionCondicionRequest as element() (:: schema-element(ns1:CrearObservacionCondicionRequest) ::) external;

declare function local:func($CrearObservacionCondicionRequest as element() (:: schema-element(ns1:CrearObservacionCondicionRequest) ::)) as element() (:: schema-element(ns2:ObservacionCondicionCollection) ::) {
    <ns2:ObservacionCondicionCollection>
        <ns2:ObservacionCondicion>
            <ns2:id>{fn:data($CrearObservacionCondicionRequest/ns1:ObservacionCondicion/con:id)}</ns2:id>
            <ns2:idCondicion>{fn:data($CrearObservacionCondicionRequest/ns1:idCondicion)}</ns2:idCondicion>
            {
                if ($CrearObservacionCondicionRequest/ns1:ObservacionCondicion/con:observacion)
                then <ns2:observacion>{fn:data($CrearObservacionCondicionRequest/ns1:ObservacionCondicion/con:observacion)}</ns2:observacion>
                else ()
            }
            {
                if ($CrearObservacionCondicionRequest/ns1:ObservacionCondicion/con:loginUsuario)
                then <ns2:loginUsuario>{fn:data($CrearObservacionCondicionRequest/ns1:ObservacionCondicion/con:loginUsuario)}</ns2:loginUsuario>
                else ()
            }
            {
                if ($CrearObservacionCondicionRequest/ns1:ObservacionCondicion/con:nombreUsuario)
                then <ns2:nombreUsuario>{fn:data($CrearObservacionCondicionRequest/ns1:ObservacionCondicion/con:nombreUsuario)}</ns2:nombreUsuario>
                else ()
            }
            <ns2:fechaRegistro></ns2:fechaRegistro>
            <ns2:banEstatus></ns2:banEstatus>
              {
                if ($CrearObservacionCondicionRequest/ns1:ObservacionCondicion/con:tareaBPM/cat:Id)
                then <ns2:idTcaTareaBpm>{fn:data($CrearObservacionCondicionRequest/ns1:ObservacionCondicion/con:tareaBPM/cat:Id)}</ns2:idTcaTareaBpm>
                else ()
            }
            <ns2:esPrincipal>1</ns2:esPrincipal>
        </ns2:ObservacionCondicion>
    </ns2:ObservacionCondicionCollection>
};

local:func($CrearObservacionCondicionRequest)
