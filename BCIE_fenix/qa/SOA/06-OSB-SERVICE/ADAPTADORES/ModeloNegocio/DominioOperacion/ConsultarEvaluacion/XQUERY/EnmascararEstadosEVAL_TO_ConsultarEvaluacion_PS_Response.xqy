

xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/EvaluacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace eva = "http://www.bcie.org/EvaluacionBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace dvm= " http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue";




declare variable $ConsultarEvaluacionResponse as element() (:: schema-element(ns1:ConsultarEvaluacionResponse) ::) external;
declare variable $ConsultarEvaluacionRequest as element() (:: schema-element(ns1:ConsultarEvaluacionRequest) ::) external;


declare function local:funcXq_estadoEvaluacion($idTarea as xs:string, 
                                            $estadoEVAL as xs:string,
                                            $avance as xs:string) 
                                            as xs:string {

    if ($idTarea = '170' or $idTarea = '171' or $idTarea = '175') then
        if ($estadoEVAL = 'Validado por AED' or $estadoEVAL = 'Validado por ODE')then
          'Validado'
        else 
         'Por validar'
    
    else if ($idTarea = '35' or $idTarea = '168' or $idTarea = '169' or $idTarea = '173' or $idTarea = '174') then
       if ($avance = '1')then
          'Completado'
        else 
         'Por completar'

    else
    ''
};

declare function local:func($ConsultarEvaluacionResponse as element() (:: schema-element(ns1:ConsultarEvaluacionResponse) ::),
                            $ConsultarEvaluacionRequest as element() (:: schema-element(ns1:ConsultarEvaluacionRequest) ::)) as element() (:: schema-element(ns1:ConsultarEvaluacionResponse) ::) {
  let $todasExAnte := if(count($ConsultarEvaluacionResponse/ns1:Evaluaciones/eva:evaluacion[eva:catEvaluacion/cat:Id = 1]) = count($ConsultarEvaluacionResponse/ns1:Evaluaciones/eva:evaluacion))then 1 else 0
  let $medioTermino := count($ConsultarEvaluacionResponse/ns1:Evaluaciones/eva:evaluacion[eva:catEvaluacion/cat:Id = 2])
  let $exPost := count($ConsultarEvaluacionResponse/ns1:Evaluaciones/eva:evaluacion[eva:catEvaluacion/cat:Id = 3])
  return
    <ns1:ConsultarEvaluacionResponse>
        <ns1:Evaluaciones>
            {
              for $evaluacion in $ConsultarEvaluacionResponse/ns1:Evaluaciones/eva:evaluacion
               return
                <eva:evaluacion>
                    <eva:id>{fn:data($evaluacion/eva:id)}</eva:id>
                    <eva:idOperacion>{fn:data($evaluacion/eva:idOperacion)}</eva:idOperacion>
                    <eva:idEvaluacion>{fn:data($evaluacion/eva:idEvaluacion)}</eva:idEvaluacion>
                    <eva:tituloEvaluacion>{fn:data($evaluacion/eva:tituloEvaluacion)}</eva:tituloEvaluacion>
                    <eva:idSectorIBCIE>{fn:data($evaluacion/eva:idSectorIBCIE)}</eva:idSectorIBCIE>
                    <eva:SectorIBCIE>{fn:data($evaluacion/eva:SectorIBCIE)}</eva:SectorIBCIE>
                    <eva:idSubSectorIBCIE>{fn:data($evaluacion/eva:idSubSectorIBCIE)}</eva:idSubSectorIBCIE>
                    <eva:subSectorIBCIE>{fn:data($evaluacion/eva:subSectorIBCIE)}</eva:subSectorIBCIE>	
                    <eva:cuestionarios>
			 {
                          for $cuestionario in $evaluacion/eva:cuestionarios/eva:cuestionario
                          let $estado:= local:funcXq_estadoEvaluacion(string($ConsultarEvaluacionRequest/ns1:Tarea/cat:Id), $cuestionario/eva:descripcionEstado, string($cuestionario/eva:porcentaje))
                          return 
                          <eva:cuestionario>
                             <eva:idCuestionario>{fn:data($cuestionario/eva:idCuestionario)}</eva:idCuestionario>
                             <eva:cuestionario>{fn:data($cuestionario/eva:cuestionario)}</eva:cuestionario>
                             <eva:porcentaje>{fn:data($cuestionario/eva:porcentaje)}</eva:porcentaje>
                             <eva:porcentajeObligatorio>{fn:data($cuestionario/eva:porcentajeObligatorio)}</eva:porcentajeObligatorio>
                             <eva:codigoExterno>{fn:data($cuestionario/eva:codigoExterno)}</eva:codigoExterno>
                             <eva:estado>{fn:data($cuestionario/eva:estado)}</eva:estado>
                             {
                             if (string($ConsultarEvaluacionRequest/ns1:Tarea/cat:Id) = '166')
                             then
                                if ($todasExAnte = 1)then
                                <eva:descripcionEstado>Completar en prepación</eva:descripcionEstado>
                                else
                                if ($exPost >0) then
                                  if ($evaluacion/eva:catEvaluacion/cat:Id = 3) then
                                  <eva:descripcionEstado>Completar en prepación</eva:descripcionEstado>
                                  else
                                  <eva:descripcionEstado>Completado</eva:descripcionEstado>
                                else
                                if ($exPost =0 and $medioTermino>0)then
                                  if ($evaluacion/eva:catEvaluacion/cat:Id = 2)then
                                  <eva:descripcionEstado>Completar en prepación</eva:descripcionEstado>
                                  else 
                                  <eva:descripcionEstado>Completado</eva:descripcionEstado>
                                else
                                ()
                             else
                            <eva:descripcionEstado>{if($estado !='')then $estado else fn:data($cuestionario/eva:descripcionEstado)}</eva:descripcionEstado>
                            }
                          </eva:cuestionario>	  
                          }
                     </eva:cuestionarios>
                     <eva:enAnalisis>{fn:data($evaluacion/eva:enAnalisis)}</eva:enAnalisis>
                     <eva:fechaRegistro>{fn:data($evaluacion/eva:fechaRegistro)}</eva:fechaRegistro>
                     <eva:banEstatus>{fn:data($evaluacion/eva:banEstatus)}</eva:banEstatus>
                     <eva:tipoEvaluacion>{fn:data($evaluacion/eva:tipoEvaluacion)}</eva:tipoEvaluacion>
                     <eva:descripcionTipoEvaluacion>{fn:data($evaluacion/eva:descripcionTipoEvaluacion)}</eva:descripcionTipoEvaluacion>
                     <eva:version>{fn:data($evaluacion/eva:version)}</eva:version>
                     <eva:codigoUsuario>{fn:data($evaluacion/eva:codigoUsuario)}</eva:codigoUsuario>
                     <eva:nombreUsuario>{fn:data($evaluacion/eva:nombreUsuario)}</eva:nombreUsuario>
                     <eva:Porcentaje>{fn:data($evaluacion/eva:Porcentaje)}</eva:Porcentaje>
                     <eva:PorcentajeObligatorio>{fn:data($evaluacion/eva:PorcentajeObligatorio)}</eva:PorcentajeObligatorio>
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
                    <eva:valor>{fn:data($evaluacion/eva:valor)}</eva:valor>
                      </eva:evaluacion>
            }
        </ns1:Evaluaciones>
        <ns1:Resultado>
            <res:result>OK</res:result>
              <res:message>Consulta exitosa</res:message>
        </ns1:Resultado>
    </ns1:ConsultarEvaluacionResponse>
};

local:func($ConsultarEvaluacionResponse, $ConsultarEvaluacionRequest)
