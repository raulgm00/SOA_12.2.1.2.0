xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/clasificacionEstrategicaBD_BS";
(:: import schema at "../V2/clasificacionEstrategicaBD_BS_table.xsd" ::)

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $crearOperacion as element() (:: schema-element(ns1:CrearOperacionRequest) ::) external;
declare variable $idOperacion as xs:long external;

declare function local:clasificacionEstrategica($crearOperacion as element() (:: schema-element(ns1:CrearOperacionRequest) ::), 
                                                $idOperacion as xs:long) 
                                                as element() (:: schema-element(ns2:ClasificacionEstrategicaCollection) ::) {
    <ns2:ClasificacionEstrategicaCollection>
        {
            for $clasificacionEstrategicaMultisectorial in $crearOperacion/ns1:Operacion/ope:clasificacionEstrategicaMultisectorial/ope:componenteClasificacionEstrategica
            return 
            <ns2:ClasificacionEstrategica>
                <ns2:idOperacion>{fn:data($idOperacion)}</ns2:idOperacion>
                {
                    if ($clasificacionEstrategicaMultisectorial/ope:actividadEconomica/cat:Id)
                    then <ns2:idCatActividadEconomica>{fn:data($clasificacionEstrategicaMultisectorial/ope:actividadEconomica/cat:Id)}</ns2:idCatActividadEconomica>
                    else ()
                }
                {
                    if ($clasificacionEstrategicaMultisectorial/ope:areaFocalizacion/cat:Id)
                    then <ns2:idCatAreaFocalizacion>{fn:data($clasificacionEstrategicaMultisectorial/ope:areaFocalizacion/cat:Id)}</ns2:idCatAreaFocalizacion>
                    else ()
                }
                {
                    if ($clasificacionEstrategicaMultisectorial/ope:ejeEstrategico/cat:Id)
                    then <ns2:idCatEjeEstrategico>{fn:data($clasificacionEstrategicaMultisectorial/ope:ejeEstrategico/cat:Id)}</ns2:idCatEjeEstrategico>
                    else ()
                }
                {
                    if ($clasificacionEstrategicaMultisectorial/ope:iniciativaEstrategica/cat:Id)
                    then <ns2:idIniciativaEstrategica>{fn:data($clasificacionEstrategicaMultisectorial/ope:iniciativaEstrategica/cat:Id)}</ns2:idIniciativaEstrategica>
                    else ()
                }
                {
                    if ($clasificacionEstrategicaMultisectorial/ope:cantidadPaises/cat:Id)
                    then <ns2:idTcaRangoPais>{fn:data($clasificacionEstrategicaMultisectorial/ope:cantidadPaises/cat:Id)}</ns2:idTcaRangoPais>
                    else ()
                }
                {
                    if ($clasificacionEstrategicaMultisectorial/ope:actividadEconomicaAsociada/cat:Id)
                    then <ns2:idActividadEconomicaAsoc>{fn:data($clasificacionEstrategicaMultisectorial/ope:actividadEconomicaAsociada/cat:Id)}</ns2:idActividadEconomicaAsoc>
                    else ()
                }
                {
                    if ($clasificacionEstrategicaMultisectorial/ope:nombreComponente)
                    then <ns2:nombreDelComponente>{fn:data($clasificacionEstrategicaMultisectorial/ope:nombreComponente)}</ns2:nombreDelComponente>
                    else ()
                }
                
                {
                    if ($clasificacionEstrategicaMultisectorial/ope:descripcion)
                    then <ns2:descripcion>{fn:data($clasificacionEstrategicaMultisectorial/ope:descripcion)}</ns2:descripcion>
                    else ()
                }
                {
                    if ($clasificacionEstrategicaMultisectorial/ope:porcentaje)
                    then <ns2:porcentaje>{fn:data($clasificacionEstrategicaMultisectorial/ope:porcentaje)}</ns2:porcentaje>
                    else ()
                }			

                <ns2:fechaRegistro>{fn:current-date()}</ns2:fechaRegistro>
                {
                    if ($clasificacionEstrategicaMultisectorial/ope:sectorIbcie/cat:Id)
                    then <ns2:idSectorIbcie>{fn:data($clasificacionEstrategicaMultisectorial/ope:sectorIbcie/cat:Id)}</ns2:idSectorIbcie>
                    else ()
                }
                {
                    if ($clasificacionEstrategicaMultisectorial/ope:subSectorIbcie/cat:Id)
                    then <ns2:idSubSectorIbcie>{fn:data($clasificacionEstrategicaMultisectorial/ope:subSectorIbcie/cat:Id)}</ns2:idSubSectorIbcie>
                    else ()
                }
                </ns2:ClasificacionEstrategica>
        }
    </ns2:ClasificacionEstrategicaCollection>
};

local:clasificacionEstrategica($crearOperacion, $idOperacion)