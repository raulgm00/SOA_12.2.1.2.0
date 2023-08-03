xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/EvaluacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearEvaluacionDB";
(:: import schema at "../XSD/CrearEvaluacionDB_table.xsd" ::)

declare namespace eva = "http://www.bcie.org/EvaluacionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $CrearEvaluacionRequest as element() (:: schema-element(ns1:CrearEvaluacionRequest) ::) external;

declare function local:func($CrearEvaluacionRequest as element() (:: schema-element(ns1:CrearEvaluacionRequest) ::)) as element() (:: schema-element(ns2:EvaluacionCollection) ::) {
    <ns2:EvaluacionCollection>
        { for $eval in $CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:cuestionarios/eva:cuestionario
            return 
        <ns2:Evaluacion>
            <ns2:id>{fn:data($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:id)}</ns2:id>
            <ns2:idOperacion>{fn:data($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:idOperacion)}</ns2:idOperacion>
            {
                if ($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:idEvaluacion)
                then <ns2:idEvaluacion>{fn:data($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:idEvaluacion)}</ns2:idEvaluacion>
                else ()
            }
            {
                if ($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:idSectorIBCIE)
                then <ns2:idSectorIbcie>{fn:data($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:idSectorIBCIE)}</ns2:idSectorIbcie>
                else ()
            }
            {
                if ($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:SectorIBCIE)
                then <ns2:sectorIbcie>{fn:data($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:SectorIBCIE)}</ns2:sectorIbcie>
                else ()
            }
            {
                if ($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:idSubSectorIBCIE)
                then <ns2:idSubSectorIbcie>{fn:data($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:idSubSectorIBCIE)}</ns2:idSubSectorIbcie>
                else ()
            }
            {
                if ($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:subSectorIBCIE)
                then <ns2:subSectorIbcie>{fn:data($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:subSectorIBCIE)}</ns2:subSectorIbcie>
                else ()
            }
            {
                if ($eval/eva:codigoExterno)
                then <ns2:idCuestionario>{fn:data($eval/eva:codigoExterno)}</ns2:idCuestionario>
                else ()
            }
                      
            {
                if ($eval/eva:cuestionario)
                then <ns2:cuestionario>{fn:data($eval/eva:cuestionario)}</ns2:cuestionario>
                else ()
            }
            {
                if ($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:enAnalisis)
                then <ns2:enAnalisis>{fn:data($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:enAnalisis)}</ns2:enAnalisis>
                else ()
            }
            {
                if ($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:tipoEvaluacion)
                then <ns2:tipoEvaluacion>{fn:data($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:tipoEvaluacion)}</ns2:tipoEvaluacion>
                else ()
            }
            {
                if ($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:version)
                then <ns2:version>{fn:data($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:version)}</ns2:version>
                else ()
            }
               <ns2:fechaRegistro>{fn:current-date()}</ns2:fechaRegistro>
            {
                if ($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:banEstatus)
                then <ns2:banEstatus>{fn:data($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:banEstatus)}</ns2:banEstatus>
                else ()
            }
            {
                if ($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:catEvaluacion/cat:Id)
                then <ns2:idTcaTipoEvaluacion>{fn:data($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:catEvaluacion/cat:Id)}</ns2:idTcaTipoEvaluacion>
                else ()
            }
            {
                if ($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:idEvaluacionOrigen)
                then <ns2:idEvaluacionOrigen>{fn:data($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:idEvaluacionOrigen)}</ns2:idEvaluacionOrigen>
                else ()
            }
            {
                if ($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:calificacionIBCIE)
                then <ns2:calificacionIbcie>{fn:data($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:calificacionIBCIE)}</ns2:calificacionIbcie>
                else ()
            }
            {
                if ($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:instanciaProceso)
                then <ns2:instanciaProceso>{fn:data($CrearEvaluacionRequest/ns1:Evaluaciones/eva:evaluacion/eva:instanciaProceso)}</ns2:instanciaProceso>
                else ()
            }
           
          
        </ns2:Evaluacion>
        }
    </ns2:EvaluacionCollection>
};

local:func($CrearEvaluacionRequest)
