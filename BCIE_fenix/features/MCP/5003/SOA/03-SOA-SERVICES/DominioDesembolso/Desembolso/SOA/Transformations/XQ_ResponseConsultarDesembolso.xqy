xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace obt="http://xmlns.oracle.com/ObtenerDisponibilidadRecursos/ObtenerDisponibilidadRecursos_DecisionService_1";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/BusinessRules/ObtenerDisponibilidadRecursos/V1/Wsdl/ObtenerDisponibilidadRecursos_DecisionService.wsdl" ::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace www = "http://www.bcie.org";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $outputVariable.response as element() (:: schema-element(des:ConsultarDesembolsoBPELResponse) ::) external;
declare variable $outObtenerDisponibilidad.payload as element() (:: schema-element(obt:callFunctionStatelessDecision) ::) external;
declare variable $outValidarProgramacion.response as element() (:: schema-element(des:ValidarProgramacionResponse) ::) external;

declare function local:funcXq_responseconsultardesembolso($outputVariable.response as element() (:: schema-element(des:ConsultarDesembolsoBPELResponse) ::), 
                                                          $outObtenerDisponibilidad.payload as element() (:: schema-element(obt:callFunctionStatelessDecision) ::), 
                                                          $outValidarProgramacion.response as element() (:: schema-element(des:ValidarProgramacionResponse) ::)) 
                                                          as element() (:: schema-element(des:ConsultarDesembolsoBPELResponse) ::) {
    <des:ConsultarDesembolsoBPELResponse>
        <des:ContratoDesembolso>
            <des1:idDesembolso>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:idDesembolso)}</des1:idDesembolso>
            {
                if ($outputVariable.response/des:ContratoDesembolso/des1:idFacturador)
                then <des1:idFacturador>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:idFacturador)}</des1:idFacturador>
                else ()
            }
            {
                if ($outputVariable.response/des:ContratoDesembolso/des1:producto)
                then 
                    <des1:producto>
                        <pro:idProducto>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:producto/pro:idProducto)}</pro:idProducto>
                        <pro:descripcion>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:producto/pro:descripcion)}</pro:descripcion>
                        <pro:descripcionCorta>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:producto/pro:descripcionCorta)}</pro:descripcionCorta>
                        <pro:fechaRegistro>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:producto/pro:fechaRegistro)}</pro:fechaRegistro>
                        <pro:codExterno>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:producto/pro:codExterno)}</pro:codExterno>
                    </des1:producto>
                else ()
            }
            {
                if ($outputVariable.response/des:ContratoDesembolso/des1:referencia)
                then <des1:referencia>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:referencia)}</des1:referencia>
                else ()
            }
            {
                for $monto in $outputVariable.response/des:ContratoDesembolso/des1:monto
                return 
                <des1:monto>{$monto/*}</des1:monto>
            }
            {    
                for $monto in $outValidarProgramacion.response/des:ContratoDesembolso/des1:monto[com:tipo/cat:DescripcionCorta = 'PROGRAMADO' or com:tipo/cat:DescripcionCorta = 'EJECUTADO']
                return 
                <des1:monto>{$monto/*}</des1:monto>
            }
            <des1:estado>
                {
                    if ($outputVariable.response/des:ContratoDesembolso/des1:estado/cat:Id)
                    then <cat:Id>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:estado/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($outputVariable.response/des:ContratoDesembolso/des1:estado/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:estado/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
                {
                    if ($outputVariable.response/des:ContratoDesembolso/des1:estado/cat:DescripcionCorta)
                    then <cat:DescripcionCorta>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    else ()
                }
                {
                    if ($outputVariable.response/des:ContratoDesembolso/des1:estado/cat:estatus)
                    then <cat:estatus>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:estado/cat:estatus)}</cat:estatus>
                    else ()
                }
                {
                    if ($outputVariable.response/des:ContratoDesembolso/des1:estado/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:estado/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </des1:estado>
             {
             if(string(fn:data($outValidarProgramacion.response/des:ContratoDesembolso/des1:programado))!="")then
            <des1:programado>{fn:data($outValidarProgramacion.response/des:ContratoDesembolso/des1:programado)}</des1:programado>
            else()}
            {
                if ($outputVariable.response/des:ContratoDesembolso/des1:fechaEstimadaDesembolsar)
                then <des1:fechaEstimadaDesembolsar>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:fechaEstimadaDesembolsar)}</des1:fechaEstimadaDesembolsar>
                else ()
            }
            {
                if ($outputVariable.response/des:ContratoDesembolso/des1:fechaEfectiva)
                then <des1:fechaEfectiva>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:fechaEfectiva)}</des1:fechaEfectiva>
                else ()
            }
            {
                if ($outputVariable.response/des:ContratoDesembolso/des1:fechaAsignacion)
                then <des1:fechaAsignacion>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:fechaAsignacion)}</des1:fechaAsignacion>
                else ()
            }
            {
                if ($outputVariable.response/des:ContratoDesembolso/des1:fechaRegistro)
                then <des1:fechaRegistro>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:fechaRegistro)}</des1:fechaRegistro>
                else ()
            }
            {
                if ($outputVariable.response/des:ContratoDesembolso/des1:estatus)
                then <des1:estatus>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:estatus)}</des1:estatus>
                else ()
            }
            {
                if ($outputVariable.response/des:ContratoDesembolso/des1:idLinea)
                then <des1:idLinea>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:idLinea)}</des1:idLinea>
                else ()
            }
            {
                if ($outputVariable.response/des:ContratoDesembolso/des1:recursosExternos)
                then <des1:recursosExternos>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:recursosExternos)}</des1:recursosExternos>
                else ()
            }
            {
                if ($outputVariable.response/des:ContratoDesembolso/des1:Programa)
                then 
                    <des1:Programa>
                        <her:LineaFinanciera>
                            {
                                if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:LineaFinanciera/cat:Id)
                                then <cat:Id>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:LineaFinanciera/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:LineaFinanciera/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:LineaFinanciera/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:LineaFinanciera/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:LineaFinanciera/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:LineaFinanciera/cat:estatus)
                                then <cat:estatus>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:LineaFinanciera/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:LineaFinanciera/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:LineaFinanciera/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:LineaFinanciera>
                        <her:DestinoFinanciamiento>
                            {
                                if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:DestinoFinanciamiento/cat:Id)
                                then <cat:Id>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:DestinoFinanciamiento/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:DestinoFinanciamiento/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:DestinoFinanciamiento/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:DestinoFinanciamiento/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:DestinoFinanciamiento/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:DestinoFinanciamiento/cat:estatus)
                                then <cat:estatus>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:DestinoFinanciamiento/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:DestinoFinanciamiento/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:DestinoFinanciamiento/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:DestinoFinanciamiento>
                        <her:ModalidadFinanciamiento>
                            {
                                if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ModalidadFinanciamiento/cat:Id)
                                then <cat:Id>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ModalidadFinanciamiento/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ModalidadFinanciamiento/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ModalidadFinanciamiento/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ModalidadFinanciamiento/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ModalidadFinanciamiento/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ModalidadFinanciamiento/cat:estatus)
                                then <cat:estatus>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ModalidadFinanciamiento/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ModalidadFinanciamiento/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ModalidadFinanciamiento/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:ModalidadFinanciamiento>
                        <her:HerramientaCE>
                            <her:ActividadEconomica>
                                {
                                    if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:ActividadEconomica/cat:Id)
                                    then <cat:Id>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:ActividadEconomica/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:ActividadEconomica/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:ActividadEconomica/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:ActividadEconomica/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:ActividadEconomica/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:ActividadEconomica/cat:estatus)
                                    then <cat:estatus>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:ActividadEconomica/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </her:ActividadEconomica>
                            <her:EjeEstrategico>
                                {
                                    if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:EjeEstrategico/cat:Id)
                                    then <cat:Id>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:EjeEstrategico/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:EjeEstrategico/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:EjeEstrategico/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:EjeEstrategico/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:EjeEstrategico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:EjeEstrategico/cat:estatus)
                                    then <cat:estatus>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:EjeEstrategico/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </her:EjeEstrategico>
                            <her:AreaFocalizacion>
                                {
                                    if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:Id)
                                    then <cat:Id>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:estatus)
                                    then <cat:estatus>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </her:AreaFocalizacion>
                        </her:HerramientaCE>
                        <her:ClasificacionGeneral>
                            <her:SectorMercado>
                                {
                                    if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:Id)
                                    then <cat:Id>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:estatus)
                                    then <cat:estatus>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </her:SectorMercado>
                            <her:SectorInstitucional>
                                {
                                    if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:Id)
                                    then <cat:Id>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:estatus)
                                    then <cat:estatus>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </her:SectorInstitucional>
                        </her:ClasificacionGeneral>
                    </des1:Programa>
                else ()
            }
            <des1:EstimadoDesembolso>
                {
                if($outObtenerDisponibilidad.payload/obt:resultList/www:ObtenerDisponibilidadRecursosResponse/www:dias!=0)then
                <des1:Plazo>{fn:data($outObtenerDisponibilidad.payload/obt:resultList/www:ObtenerDisponibilidadRecursosResponse/www:dias)}</des1:Plazo>
                else
                <des1:Plazo></des1:Plazo>
                }
                <des1:Frecuencia>
                    <cat:Id>1</cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta>Dias</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>D</cat:codigoExterno>
                </des1:Frecuencia>
            </des1:EstimadoDesembolso>
            {
                for $Utilizacion in $outputVariable.response/des:ContratoDesembolso/des1:Utilizacion
                return 
                    <des1:Utilizacion>
                        <lin:idFuente>{fn:data($Utilizacion/lin:idFuente)}</lin:idFuente>
                        <lin:idLineaCredito>{fn:data($Utilizacion/lin:idLineaCredito)}</lin:idLineaCredito>
                        <lin:idLineaPasiva>{fn:data($Utilizacion/lin:idLineaPasiva)}</lin:idLineaPasiva>
                        <lin:codigoLineaPasiva>{fn:data($Utilizacion/lin:codigoLineaPasiva)}</lin:codigoLineaPasiva>
                        <lin:idFacturadorLineaPasiva>{fn:data($Utilizacion/lin:idFacturadorLineaPasiva)}</lin:idFacturadorLineaPasiva>
                        <lin:idFondo>{fn:data($Utilizacion/lin:idFondo)}</lin:idFondo>
                        <lin:Descripcion>{fn:data($Utilizacion/lin:Descripcion)}</lin:Descripcion>
                        <lin:FechaObtenido>{fn:data($Utilizacion/lin:FechaObtenido)}</lin:FechaObtenido>
                        <lin:MontoAsignado>{fn:data($Utilizacion/lin:MontoAsignado)}</lin:MontoAsignado>
                        <lin:montoDisponible>{fn:data($Utilizacion/lin:montoDisponible)}</lin:montoDisponible>
                        {
                            for $Monto in $Utilizacion/lin:Monto
                            return 
                            <lin:Monto>
                                <com:tipo>
                                    {
                                        if ($Monto/com:tipo/cat:Id)
                                        then <cat:Id>{fn:data($Monto/com:tipo/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($Monto/com:tipo/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($Monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($Monto/com:tipo/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($Monto/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($Monto/com:tipo/cat:estatus)
                                        then <cat:estatus>{fn:data($Monto/com:tipo/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($Monto/com:tipo/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($Monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </com:tipo>
                                {
                                    if ($Monto/com:importe)
                                    then <com:importe>{fn:data($Monto/com:importe)}</com:importe>
                                    else ()
                                }
                                {
                                    if ($Monto/com:moneda)
                                    then 
                                        <com:moneda>
                                            {
                                                if ($Monto/com:moneda/cat:Id)
                                                then <cat:Id>{fn:data($Monto/com:moneda/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($Monto/com:moneda/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($Monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($Monto/com:moneda/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($Monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($Monto/com:moneda/cat:estatus)
                                                then <cat:estatus>{fn:data($Monto/com:moneda/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($Monto/com:moneda/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($Monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </com:moneda>
                                    else ()
                                }
                            </lin:Monto>
                        }
                        <lin:NumeroAsignacion>{fn:data($Utilizacion/lin:NumeroAsignacion)}</lin:NumeroAsignacion>
                        <lin:Comentario>{fn:data($Utilizacion/lin:Comentario)}</lin:Comentario>
                        <lin:idContrato>{fn:data($Utilizacion/lin:idContrato)}</lin:idContrato>
                        <lin:FechaRegistro>{fn:data($Utilizacion/lin:FechaRegistro)}</lin:FechaRegistro>
                        <lin:Estado>{fn:data($Utilizacion/lin:Estado)}</lin:Estado>
                    </des1:Utilizacion>
            }
        </des:ContratoDesembolso>
        <des:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Exitosa</res:message>
        </des:Resultado>
    </des:ConsultarDesembolsoBPELResponse>
};

local:funcXq_responseconsultardesembolso($outputVariable.response, $outObtenerDisponibilidad.payload, $outValidarProgramacion.response)
