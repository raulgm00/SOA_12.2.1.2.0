xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/EvaluacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarEvaluacion";
(:: import schema at "../XSD/ConsultarEvaluacion_sp.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace eva = "http://www.bcie.org/EvaluacionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarEvaluacionRequest as element() (:: schema-element(ns1:ConsultarEvaluacionRequest) ::) external;
declare variable $OutputParameters as element() (:: schema-element(ns2:OutputParameters) ::) external;
declare variable $ConsultarEvaluacionFenixResponse as element() (:: schema-element(ns1:ConsultarEvaluacionFenixResponse) ::) external;

declare function local:func($ConsultarEvaluacionRequest as element() (:: schema-element(ns1:ConsultarEvaluacionRequest) ::), 
                            $OutputParameters as element() (:: schema-element(ns2:OutputParameters) ::), 
                            $ConsultarEvaluacionFenixResponse as element() (:: schema-element(ns1:ConsultarEvaluacionFenixResponse) ::)) 
                            as element() (:: schema-element(ns1:ConsultarEvaluacionResponse) ::) {

    <ns1:ConsultarEvaluacionResponse>
        <ns1:Evaluaciones>
          {
          let $evaluacion := 
                for $row in $OutputParameters/ns2:P_RECORDSET/ns2:Row
                let $evaluacion := $ConsultarEvaluacionFenixResponse/ns1:Evaluaciones/eva:evaluacion[eva:idEvaluacion/text() = $row/ns2:Column[@name = 'CODIGO_EVALUACION'] and 
                eva:cuestionarios/eva:cuestionario/eva:codigoExterno = $row/ns2:Column[@name = 'CODIGO_MODELO'] and eva:tipoEvaluacion/text() = $row/ns2:Column[@name = 'CODIGO_TIPO_EVALUACION']][1]
                return
                <eva:evaluacion>
                <eva:id>{fn:data($evaluacion/eva:id)}</eva:id>
                <eva:idOperacion>{fn:data($row/ns2:Column[@name = 'CODIGO_NEGOCIO'])}</eva:idOperacion>
                <eva:idEvaluacion>{fn:data($row/ns2:Column[@name = 'CODIGO_EVALUACION'])}</eva:idEvaluacion>
                <eva:tituloEvaluacion>{fn:data($row/ns2:Column[@name = 'TITULO_MODELO'])}</eva:tituloEvaluacion>
                <eva:idSectorIBCIE>{fn:data($evaluacion/eva:idSectorIBCIE)}</eva:idSectorIBCIE>
                <eva:SectorIBCIE>{fn:data($evaluacion/eva:SectorIBCIE)}</eva:SectorIBCIE>
                <eva:idSubSectorIBCIE>{fn:data($evaluacion/eva:idSubSectorIBCIE)}</eva:idSubSectorIBCIE>
                <eva:subSectorIBCIE>{fn:data($evaluacion/eva:subSectorIBCIE)}</eva:subSectorIBCIE>
                <eva:cuestionarios>
                    <eva:cuestionario>
                        <eva:idCuestionario>{fn:data($evaluacion/eva:id)}</eva:idCuestionario>
                        <eva:cuestionario>{fn:data($row/ns2:Column[@name = 'TITULO_MODELO'])}</eva:cuestionario>
                        <eva:porcentaje>{fn:data($row/ns2:Column[@name = 'PORCENTAJE_GRUPO'])}</eva:porcentaje>
                        <eva:porcentajeObligatorio>{fn:data($row/ns2:Column[@name = 'PORCENTAJE_OBLIGATORIO'])}</eva:porcentajeObligatorio>
                        <eva:codigoExterno>{fn:data($row/ns2:Column[@name = 'CODIGO_MODELO'])}</eva:codigoExterno>
                        <eva:estado>{
                        if($row/ns2:Column[@name = 'ESTADO']/text() = 'B') then 'V' else data($row/ns2:Column[@name = 'ESTADO']/text())
                        }</eva:estado>
                        <eva:descripcionEstado>{fn:data($row/ns2:Column[@name = 'DESCRIPCION_ESTADO'])}</eva:descripcionEstado>
                    </eva:cuestionario>
                </eva:cuestionarios>
                <eva:enAnalisis>{fn:data($evaluacion/eva:enAnalisis)}</eva:enAnalisis>
                <eva:fechaRegistro>{fn:data($evaluacion/eva:fechaRegistro)}</eva:fechaRegistro>
                <eva:banEstatus>{fn:data($evaluacion/eva:banEstatus)}</eva:banEstatus>
                <eva:tipoEvaluacion>{fn:data($row/ns2:Column[@name = 'CODIGO_TIPO_EVALUACION'])}</eva:tipoEvaluacion>
                <eva:descripcionTipoEvaluacion>{fn:data($row/ns2:Column[@name = 'ABREVIATURA_MODELO'])}</eva:descripcionTipoEvaluacion>
                <eva:version>{fn:data($row/ns2:Column[@name = 'VERSION_EVALUACION'])}</eva:version>
                <eva:codigoUsuario></eva:codigoUsuario>
                <eva:nombreUsuario></eva:nombreUsuario>
                <eva:Porcentaje>{fn:data($row/ns2:Column[@name = 'PORCENTAJE_GRUPO'])}</eva:Porcentaje>
                <eva:PorcentajeObligatorio>{fn:data($row/ns2:Column[@name = 'PORCENTAJE_OBLIGATORIO'])}</eva:PorcentajeObligatorio>
                <eva:codigoExterno></eva:codigoExterno>
                <eva:catEvaluacion>
                     <cat:Id>{fn:data($evaluacion/eva:catEvaluacion/cat:Id)}</cat:Id>
                     <cat:Descripcion>{fn:data($evaluacion/eva:catEvaluacion/cat:Descripcion)}</cat:Descripcion>
                     <cat:DescripcionCorta>{fn:data($evaluacion/eva:catEvaluacion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                     <cat:estatus>{fn:data($evaluacion/eva:catEvaluacion/cat:estatus)}</cat:estatus>
                     <cat:codigoExterno>{fn:data($evaluacion/eva:catEvaluacion/cat:codigoExterno)}</cat:codigoExterno>
                </eva:catEvaluacion>
                <eva:idEvaluacionOrigen>{fn:data($evaluacion/eva:idEvaluacionOrigen)}</eva:idEvaluacionOrigen>
                <eva:calificacionIBCIE>{fn:data($evaluacion/eva:calificacionIBCIE)}</eva:calificacionIBCIE>
                <eva:instanciaProceso>{fn:data($evaluacion/eva:instanciaProceso)}</eva:instanciaProceso>
                <eva:valor>{fn:data($row/ns2:Column[@name ='VALOR'])}</eva:valor>
          </eva:evaluacion>
          return 
            let $evaluaciones := if(fn:string-length($ConsultarEvaluacionRequest/ns1:Evaluacion/eva:descripcionTipoEvaluacion) > 0)
                                     then
                                      for $evaluaciones in $evaluacion[eva:descripcionTipoEvaluacion = $ConsultarEvaluacionRequest/ns1:Evaluacion/eva:descripcionTipoEvaluacion]
                                      return
                                        $evaluaciones
                                     else
                                      $evaluacion
            
            let $evaluaciones := if(fn:string-length(xs:string($ConsultarEvaluacionRequest/ns1:Evaluacion/eva:catEvaluacion/cat:Id)) > 0)
                                     then
                                      for $evaluaciones in $evaluaciones[eva:catEvaluacion/cat:Id = $ConsultarEvaluacionRequest/ns1:Evaluacion/eva:catEvaluacion/cat:Id]
                                      return
                                        $evaluaciones
                                     else
                                      $evaluaciones
            
            let $evaluaciones := if(fn:string-length($ConsultarEvaluacionRequest/ns1:Evaluacion/eva:instanciaProceso) > 0)
                                     then
                                      for $evaluaciones in $evaluaciones[eva:instanciaProceso = $ConsultarEvaluacionRequest/ns1:Evaluacion/eva:instanciaProceso]
                                      return
                                        $evaluaciones
                                     else
                                      $evaluaciones
            let $agregadas := if (dvmtr:lookup('MDS/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/DVM/TiposEvaluacion','TAREA',$ConsultarEvaluacionRequest/ns1:Tarea/cat:Id/text(),'OPERADOR', '0') = 'ALL') 
            then                                                                               
                for $agregadas in $evaluacion[eva:catEvaluacion/cat:Id/text() != '']
                return 
                  $agregadas
                else ()
        
        
            return 
              $evaluaciones union $agregadas
          }
        </ns1:Evaluaciones>
    </ns1:ConsultarEvaluacionResponse>
};

local:func($ConsultarEvaluacionRequest, $OutputParameters, $ConsultarEvaluacionFenixResponse)
