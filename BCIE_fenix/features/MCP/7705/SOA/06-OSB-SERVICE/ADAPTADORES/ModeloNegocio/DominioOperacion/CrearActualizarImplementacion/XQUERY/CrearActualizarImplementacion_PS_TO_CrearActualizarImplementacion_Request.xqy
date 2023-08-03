xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ImplementacionPctMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/implementacionPCT/V1/Schema/ImplementacionPctMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearActualizarImplementacion";
(:: import schema at "../XSD/CrearActualizarImplementacion_table.xsd" ::)

declare namespace imp = "http://www.bcie.org/ImplementacionPctBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $inCrearActualizarImplementacion as element() (:: schema-element(ns1:CrearActualizarImplementacionRequest) ::) external;

declare function local:func($inCrearActualizarImplementacion as element() (:: schema-element(ns1:CrearActualizarImplementacionRequest) ::)) as element() (:: schema-element(ns2:ImplementacionCollection) ::) {
    <ns2:ImplementacionCollection>
        <ns2:Implementacion>
            <ns2:id>{fn:data($inCrearActualizarImplementacion/ns1:Implementacion/imp:idLote)}</ns2:id>
            <ns2:idOperacion>{fn:data($inCrearActualizarImplementacion/ns1:Implementacion/imp:loteImpementacion/imp:idLote)}</ns2:idOperacion>
            {
                if ($inCrearActualizarImplementacion/ns1:Implementacion/imp:tipoProceso)
                then <ns2:tipoProceso>{fn:data($inCrearActualizarImplementacion/ns1:Implementacion/imp:tipoProceso)}</ns2:tipoProceso>
                else ()
            }
            {
                if ($inCrearActualizarImplementacion/ns1:Implementacion/imp:nombre)
                then <ns2:nombreAdquisicion>{fn:data($inCrearActualizarImplementacion/ns1:Implementacion/imp:nombre)}</ns2:nombreAdquisicion>
                else ()
            }
            {
                if ($inCrearActualizarImplementacion/ns1:Implementacion/imp:monto/com:importe)
                then <ns2:montoPresupuestado>{fn:data($inCrearActualizarImplementacion/ns1:Implementacion/imp:monto/com:importe)}</ns2:montoPresupuestado>
                else ()
            }
            {
                if ($inCrearActualizarImplementacion/ns1:Implementacion/imp:monto/com:tipo/cat:Id)
                then <ns2:idTcaTipoMoneda>{fn:data($inCrearActualizarImplementacion/ns1:Implementacion/imp:monto/com:tipo/cat:Id)}</ns2:idTcaTipoMoneda>
                else ()
            }
            {
                if ($inCrearActualizarImplementacion/ns1:Implementacion/imp:observacion)
                then <ns2:observacion>{fn:data($inCrearActualizarImplementacion/ns1:Implementacion/imp:observacion)}</ns2:observacion>
                else ()
            }
            {
                if ($inCrearActualizarImplementacion/ns1:Implementacion/imp:fechaPublicacion)
                then <ns2:fechaPublicacion>{fn:data($inCrearActualizarImplementacion/ns1:Implementacion/imp:fechaPublicacion)}</ns2:fechaPublicacion>
                else ()
            }
            {
                if ($inCrearActualizarImplementacion/ns1:Implementacion/imp:fechaLimiteRecepcion)
                then <ns2:fechaLimiteRecepcion>{fn:data($inCrearActualizarImplementacion/ns1:Implementacion/imp:fechaLimiteRecepcion)}</ns2:fechaLimiteRecepcion>
                else ()
            }
            {
                if ($inCrearActualizarImplementacion/ns1:Implementacion/imp:requiereLotes)
                then <ns2:requiereLotes>{if (fn:string( data($inCrearActualizarImplementacion/ns1:Implementacion/imp:requiereLotes))='true')then 1 else(0)}</ns2:requiereLotes>
                else ()
            }
            {
                if ($inCrearActualizarImplementacion/ns1:Implementacion/imp:fechaRegistro)
                then <ns2:fechaRegistro>{fn:data($inCrearActualizarImplementacion/ns1:Implementacion/imp:fechaRegistro)}</ns2:fechaRegistro>
                else ()
            }
            {
                if (fn:string($inCrearActualizarImplementacion/ns1:Implementacion/imp:monto/com:tipo/cat:estatus)!='')
                then <ns2:banEstatus>{if(fn:string(  fn:data($inCrearActualizarImplementacion/ns1:Implementacion/imp:estatus))='true' )then  1 else(0)}</ns2:banEstatus>
                else ()
            }
        </ns2:Implementacion>
    </ns2:ImplementacionCollection>
};

local:func($inCrearActualizarImplementacion)
