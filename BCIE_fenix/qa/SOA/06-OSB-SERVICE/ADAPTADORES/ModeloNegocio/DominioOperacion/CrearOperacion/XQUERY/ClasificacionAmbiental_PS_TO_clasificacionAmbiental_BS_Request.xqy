xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearClasificacionAmbiental";
(:: import schema at "../../CrearClasificacionAmbiental/XSD/CrearClasificacionAmbiental_table.xsd" ::)

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $crearOperacion as element() (:: schema-element(ns1:CrearOperacionRequest) ::) external;
declare variable $idOperacion as xs:long external;

declare function local:clasificacionAmbiental($crearOperacion as element() (:: schema-element(ns1:CrearOperacionRequest) ::),
                                              $idOperacion as xs:long) 
                                              as element() (:: schema-element(ns2:ClasificacionAmbientalCollection) ::) {
    <ns2:ClasificacionAmbientalCollection>
        {
            for $componenteClasificacionAmbiental in $crearOperacion/ns1:Operacion/ope:insertClasificacionAmbiental/ope:componenteClasificacionAmbiental
            return 
            <ns2:ClasificacionAmbiental>
            <ns2:idOperacion>{fn:data($idOperacion)}</ns2:idOperacion>
                {
                    if ($componenteClasificacionAmbiental/ope:sectorAmbiental/cat:Id)
                    then <ns2:idTcaSectorAmbiental>{fn:data($componenteClasificacionAmbiental/ope:sectorAmbiental/cat:Id)}</ns2:idTcaSectorAmbiental>
                    else ()
                }
                {
                    if ($componenteClasificacionAmbiental/ope:aporteAmbiental/cat:Id)
                    then <ns2:idTcaAporteAmbiental>{fn:data($componenteClasificacionAmbiental/ope:aporteAmbiental/cat:Id)}</ns2:idTcaAporteAmbiental>
                    else ()
                }
                {
                    if ($componenteClasificacionAmbiental/ope:categoriaAmbiental/cat:Id)
                    then <ns2:idTcaCategoriaAmbiental>{fn:data($componenteClasificacionAmbiental/ope:categoriaAmbiental/cat:Id)}</ns2:idTcaCategoriaAmbiental>
                    else ()
                }
                {
                    if ($componenteClasificacionAmbiental/ope:subCategoriaAmbiental/cat:Id)
                    then <ns2:idTcaSubcategoriaAmbiental>{fn:data($componenteClasificacionAmbiental/ope:subCategoriaAmbiental/cat:Id)}</ns2:idTcaSubcategoriaAmbiental>
                    else ()
                }
                <ns2:banEstatus>1</ns2:banEstatus>
                <ns2:fechaRegistro>{fn:current-date()}</ns2:fechaRegistro>
                
                </ns2:ClasificacionAmbiental>
                
              
        }
    </ns2:ClasificacionAmbientalCollection>
};

local:clasificacionAmbiental($crearOperacion, $idOperacion)