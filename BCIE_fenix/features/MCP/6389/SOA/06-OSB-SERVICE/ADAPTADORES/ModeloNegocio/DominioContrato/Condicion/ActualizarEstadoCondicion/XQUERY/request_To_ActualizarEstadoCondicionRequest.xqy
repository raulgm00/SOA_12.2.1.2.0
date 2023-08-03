xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $ActualizarEstadoCondicionRequest as element() (:: schema-element(ns1:ActualizarEstadoCondicionRequest) ::) external;

declare function local:func($ActualizarEstadoCondicionRequest as element() (:: schema-element(ns1:ActualizarEstadoCondicionRequest) ::)) as element() (:: schema-element(ns1:ActualizarEstadoCondicionRequest) ::) {
    <ns1:ActualizarEstadoCondicionRequest>
        {
            for $idCondicion in $ActualizarEstadoCondicionRequest/ns1:idCondicion
            return 
            <ns1:idCondicion>{fn:data($idCondicion)}</ns1:idCondicion>
        }
        <ns1:EstadoTCC>
            {
                if ($ActualizarEstadoCondicionRequest/ns1:EstadoTCC/cat:Id)
                then <cat:Id>{fn:data($ActualizarEstadoCondicionRequest/ns1:EstadoTCC/cat:Id)}</cat:Id>
                else ()
            }
            {
                if ($ActualizarEstadoCondicionRequest/ns1:EstadoTCC/cat:Descripcion)
                then <cat:Descripcion>{fn:data($ActualizarEstadoCondicionRequest/ns1:EstadoTCC/cat:Descripcion)}</cat:Descripcion>
                else ()
            }
            {
                if ($ActualizarEstadoCondicionRequest/ns1:EstadoTCC/cat:DescripcionCorta)
                then <cat:DescripcionCorta>{fn:data($ActualizarEstadoCondicionRequest/ns1:EstadoTCC/cat:DescripcionCorta)}</cat:DescripcionCorta>
                else ()
            }
            {
                if ($ActualizarEstadoCondicionRequest/ns1:EstadoTCC/cat:estatus)
                then <cat:estatus>{fn:data($ActualizarEstadoCondicionRequest/ns1:EstadoTCC/cat:estatus)}</cat:estatus>
                else ()
            }
            {
                if ($ActualizarEstadoCondicionRequest/ns1:EstadoTCC/cat:codigoExterno)
                then <cat:codigoExterno>{fn:data($ActualizarEstadoCondicionRequest/ns1:EstadoTCC/cat:codigoExterno)}</cat:codigoExterno>
                else ()
            }
        </ns1:EstadoTCC>
        <ns1:SubEstadoTCC>
            {
                if ($ActualizarEstadoCondicionRequest/ns1:SubEstadoTCC/cat:Id)
                then <cat:Id>{fn:data($ActualizarEstadoCondicionRequest/ns1:SubEstadoTCC/cat:Id)}</cat:Id>
                else ()
            }
            {
                if ($ActualizarEstadoCondicionRequest/ns1:SubEstadoTCC/cat:Descripcion)
                then <cat:Descripcion>{fn:data($ActualizarEstadoCondicionRequest/ns1:SubEstadoTCC/cat:Descripcion)}</cat:Descripcion>
                else ()
            }
            {
                if ($ActualizarEstadoCondicionRequest/ns1:SubEstadoTCC/cat:DescripcionCorta)
                then <cat:DescripcionCorta>{fn:data($ActualizarEstadoCondicionRequest/ns1:SubEstadoTCC/cat:DescripcionCorta)}</cat:DescripcionCorta>
                else ()
            }
            {
                if ($ActualizarEstadoCondicionRequest/ns1:SubEstadoTCC/cat:estatus)
                then <cat:estatus>{fn:data($ActualizarEstadoCondicionRequest/ns1:SubEstadoTCC/cat:estatus)}</cat:estatus>
                else ()
            }
            {
                if ($ActualizarEstadoCondicionRequest/ns1:SubEstadoTCC/cat:codigoExterno)
                then <cat:codigoExterno>{fn:data($ActualizarEstadoCondicionRequest/ns1:SubEstadoTCC/cat:codigoExterno)}</cat:codigoExterno>
                else ()
            }
        </ns1:SubEstadoTCC>
        {
            if ($ActualizarEstadoCondicionRequest/ns1:Agrupador)
            then <ns1:Agrupador>{fn:data($ActualizarEstadoCondicionRequest/ns1:Agrupador)}</ns1:Agrupador>
            else ()
        }
    </ns1:ActualizarEstadoCondicionRequest>
};

local:func($ActualizarEstadoCondicionRequest)
