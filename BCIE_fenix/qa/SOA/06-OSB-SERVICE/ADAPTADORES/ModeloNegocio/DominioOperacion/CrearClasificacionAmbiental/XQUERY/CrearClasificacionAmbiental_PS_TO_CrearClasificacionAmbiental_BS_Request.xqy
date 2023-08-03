xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearClasificacionAmbiental";
(:: import schema at "../XSD/CrearClasificacionAmbiental_table.xsd" ::)

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $requestClasificacionAmbiental as element() (:: schema-element(ns1:CrearClasificacionAmbientalRequest) ::) external;

declare function local:func($requestClasificacionAmbiental as element() (:: schema-element(ns1:CrearClasificacionAmbientalRequest) ::)) as element() (:: schema-element(ns2:ClasificacionAmbientalCollection) ::) {
    <ns2:ClasificacionAmbientalCollection>
    
        <ns2:ClasificacionAmbiental>
            {
                if ($requestClasificacionAmbiental/ns1:ClasificacionesAmbiental/ope:clasificacionAmbiental/ope:idOperacion)
                then <ns2:idOperacion>{fn:data($requestClasificacionAmbiental/ns1:ClasificacionesAmbiental/ope:clasificacionAmbiental/ope:idOperacion)}</ns2:idOperacion>
                else ()
            }
            {
                if ($requestClasificacionAmbiental/ns1:ClasificacionesAmbiental/ope:clasificacionAmbiental/ope:sectorAmbiental/cat:Id)
                then <ns2:idTcaSectorAmbiental>{fn:data($requestClasificacionAmbiental/ns1:ClasificacionesAmbiental/ope:clasificacionAmbiental/ope:sectorAmbiental/cat:Id)}</ns2:idTcaSectorAmbiental>
                else ()
            }
            {
                if ($requestClasificacionAmbiental/ns1:ClasificacionesAmbiental/ope:clasificacionAmbiental/ope:aporteAmbiental/cat:Id)
                then <ns2:idTcaAporteAmbiental>{fn:data($requestClasificacionAmbiental/ns1:ClasificacionesAmbiental/ope:clasificacionAmbiental/ope:aporteAmbiental/cat:Id)}</ns2:idTcaAporteAmbiental>
                else ()
            }
            {
                if ($requestClasificacionAmbiental/ns1:ClasificacionesAmbiental/ope:clasificacionAmbiental/ope:categoriaAmbiental/cat:Id)
                then <ns2:idTcaCategoriaAmbiental>{fn:data($requestClasificacionAmbiental/ns1:ClasificacionesAmbiental/ope:clasificacionAmbiental/ope:categoriaAmbiental/cat:Id)}</ns2:idTcaCategoriaAmbiental>
                else ()
            }
            {
                if ($requestClasificacionAmbiental/ns1:ClasificacionesAmbiental/ope:clasificacionAmbiental/ope:subCategoriaAmbiental/cat:Id)
                then <ns2:idTcaSubcategoriaAmbiental>{fn:data($requestClasificacionAmbiental/ns1:ClasificacionesAmbiental/ope:clasificacionAmbiental/ope:subCategoriaAmbiental/cat:Id)}</ns2:idTcaSubcategoriaAmbiental>
                else ()
            }
             
            <ns2:banEstatus>1</ns2:banEstatus>
            <ns2:fechaRegistro>{fn:current-dateTime()}</ns2:fechaRegistro>
            </ns2:ClasificacionAmbiental>
            
            </ns2:ClasificacionAmbientalCollection>
};

local:func($requestClasificacionAmbiental)