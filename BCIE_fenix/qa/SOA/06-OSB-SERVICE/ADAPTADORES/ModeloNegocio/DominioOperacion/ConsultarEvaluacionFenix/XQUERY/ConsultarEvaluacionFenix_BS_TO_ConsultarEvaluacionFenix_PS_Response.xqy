xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/EvaluacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarEvaluacionFenix";
(:: import schema at "../XSD/ConsultarEvaluacionFenix.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace eva = "http://www.bcie.org/EvaluacionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarEvaluacionFenixOutputCollection as element() (:: schema-element(ns1:ConsultarEvaluacionFenixOutputCollection) ::) external;

declare function local:func($ConsultarEvaluacionFenixOutputCollection as element() (:: schema-element(ns1:ConsultarEvaluacionFenixOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarEvaluacionFenixResponse) ::) {
    <ns2:ConsultarEvaluacionFenixResponse>
        <ns2:Evaluaciones>
          {for $evaluacion in $ConsultarEvaluacionFenixOutputCollection/ns1:ConsultarEvaluacionFenixOutput
          return
            <eva:evaluacion>
                <eva:id>{fn:data($evaluacion/ns1:ID)}</eva:id>
                <eva:idOperacion>{fn:data($evaluacion/ns1:ID_OPERACION)}</eva:idOperacion>
                <eva:idEvaluacion>{fn:data($evaluacion/ns1:ID_EVALUACION)}</eva:idEvaluacion>
                <eva:idSectorIBCIE>{fn:data($evaluacion/ns1:ID_SECTOR_IBCIE)}</eva:idSectorIBCIE>
                <eva:SectorIBCIE>{fn:data($evaluacion/ns1:SECTOR_IBCIE)}</eva:SectorIBCIE>
                <eva:idSubSectorIBCIE>{fn:data($evaluacion/ns1:ID_SUB_SECTOR_IBCIE)}</eva:idSubSectorIBCIE>
                <eva:subSectorIBCIE>{fn:data($evaluacion/ns1:SUB_SECTOR_IBCIE)}</eva:subSectorIBCIE>
                <eva:cuestionarios>
                    <eva:cuestionario>
                        <eva:cuestionario>{fn:data($evaluacion/ns1:CUESTIONARIO)}</eva:cuestionario>
                        <eva:codigoExterno>{fn:data($evaluacion/ns1:ID_CUESTIONARIO)}</eva:codigoExterno>
                    </eva:cuestionario>
                </eva:cuestionarios>
                <eva:enAnalisis>{fn:data($evaluacion/ns1:EN_ANALISIS)}</eva:enAnalisis>
                <eva:fechaRegistro>{fn:data($evaluacion/ns1:FECHA_REGISTRO)}</eva:fechaRegistro>
                <eva:banEstatus>{fn:data($evaluacion/ns1:BAN_ESTATUS)}</eva:banEstatus>
                <eva:tipoEvaluacion>{fn:data($evaluacion/ns1:TIPO_EVALUACION)}</eva:tipoEvaluacion>
                <eva:descripcionTipoEvaluacion></eva:descripcionTipoEvaluacion>
                <eva:version>{fn:data($evaluacion/ns1:VERSION)}</eva:version>
                <eva:catEvaluacion>
                    <cat:Id>{fn:data($evaluacion/ns1:ID_TCA_TIPO_EVALUACION)}</cat:Id>
                    <cat:Descripcion>{fn:data($evaluacion/ns1:DESCRIPCION)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($evaluacion/ns1:DESCRIPCION_CORTA)}</cat:DescripcionCorta>
                    <cat:estatus>{fn:data($evaluacion/ns1:TTE_BAN_ESTATUS)}</cat:estatus>
                    <cat:codigoExterno>{fn:data($evaluacion/ns1:COD_EXTERNO)}</cat:codigoExterno>
                </eva:catEvaluacion>
                <eva:idEvaluacionOrigen>{fn:data($evaluacion/ns1:ID_EVALUACION_ORIGEN)}</eva:idEvaluacionOrigen>
                <eva:calificacionIBCIE>{fn:data($evaluacion/ns1:CALIFICACION_IBCIE)}</eva:calificacionIBCIE>
                <eva:instanciaProceso>{fn:data($evaluacion/ns1:INSTANCIA_PROCESO)}</eva:instanciaProceso>
            </eva:evaluacion>
           } 
        </ns2:Evaluaciones>
        <ns2:Resultado>
            <res:result>OK</res:result>
            {if(fn:count($ConsultarEvaluacionFenixOutputCollection/ns1:ConsultarEvaluacionFenixOutput) > 0)then
              <res:message>Consulta exitosa</res:message>
            else
              <res:message>Consulta sin resultados</res:message>
            }
        </ns2:Resultado>
    </ns2:ConsultarEvaluacionFenixResponse>
};

local:func($ConsultarEvaluacionFenixOutputCollection)
