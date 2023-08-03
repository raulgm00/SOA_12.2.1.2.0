xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarOperacionByIdOperacion";
(:: import schema at "../XSD/ConsultarOperacionByIdOperacion.xsd" ::)
declare namespace ns3="http://www.bcie.org/ValidarOperacionProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ValidarOperacionProceso/V1/Schema/ValidarOperacionProcesoMO.xsd" ::)

declare namespace ns11="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarMontoOperacion_db";
(:: import schema at "../../ConsultarMontoOperacion/XSD/ConsultarMontoOperacion_db.xsd" ::)
declare namespace ns4="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarClasificacionAmbiental_BS";
(:: import schema at "../../ConsultarClasificacionAmbiental/XSD/ConsultarClasificacionAmbiental_BS.xsd" ::)
declare namespace pre = "http://www.bcie.org/PreguntaBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace cue = "http://www.bcie.org/CuestionarioBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace com1 = "http://www.bcie.org/CommonBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare variable $ClienteResponse as element() (:: schema-element(ns1:ConsultarOperacionByIdOperacionOutputCollection) ::) external;
declare variable $inRequiereLaft as element() (:: schema-element(ns3:responseValidarOperacionProceso) ::) external;
declare variable $ResponseConsultarMontoOperacion as element() (:: schema-element(ns11:ConsultarMontoOperacion_dbOutputCollection) ::) external;
declare variable $ResponseConsultarClasificacionAmbiental as element() (:: schema-element(ns4:ConsultarClasificacionAmbiental_BSOutputCollection) ::) external;

declare function local:func($ClienteResponse as element() (:: schema-element(ns1:ConsultarOperacionByIdOperacionOutputCollection) ::),
                             $inRequiereLaft as element() (:: schema-element(ns3:responseValidarOperacionProceso) ::),
                             $ResponseConsultarMontoOperacion as element() (:: schema-element(ns11:ConsultarMontoOperacion_dbOutputCollection) ::),
                             $ResponseConsultarClasificacionAmbiental as element() (:: schema-element(ns4:ConsultarClasificacionAmbiental_BSOutputCollection) ::)) 
as element() (:: schema-element(ns2:ConsultarOperacionResponse) ::) {

    
    <ns2:ConsultarOperacionResponse>
        <ns2:Operacion>                           
            <ope:idOperacion>{if (not(fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_OPERACION)))
                                then (fn:empty($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_OPERACION))
                                  else(fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_OPERACION))
                                  }
            </ope:idOperacion>
            <ope:responsable>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:USUARIO_OPERACION)}</ope:responsable>
            
            <ope:nombre>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:NOMBRE)}
            </ope:nombre>
            <ope:cliente>
                <cli:idCliente>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_CLIENTE)}</cli:idCliente>
                <cli:idFacturador>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_FLEXCUBE)}</cli:idFacturador>
                <cli:razonSocial>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:RAZON_SOCIAL)}</cli:razonSocial>
                <cli:abreviatura>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ABREVIATURA)}</cli:abreviatura>
                <cli:tipoPersona>
                    <cat:Id>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_TIPO_PERSONA)}</cat:Id>
                    <cat:Descripcion>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:TIPO_PERSONA)}</cat:Descripcion>
                    <cat:codigoExterno>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:TIPO_PERSONA_COD_EXTERNO)}</cat:codigoExterno>
                </cli:tipoPersona>
                <cli:sector>
                <cat:Id>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_SECTOR)}</cat:Id>
                    <cat:Descripcion>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:SECTOR)}</cat:Descripcion>
                    <cat:codigoExterno>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:SECTOR_COD_EXTERNO)}</cat:codigoExterno>
                </cli:sector>
                <cli:tipoInstitucion>
                    <cat:Descripcion>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:TIPO_INSTITUCION)}</cat:Descripcion>
                </cli:tipoInstitucion>
                <cli:pais>
                    <cat:Descripcion>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:PAIS)}</cat:Descripcion>
                    <cat:codigoExterno>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:CODIGO_PAIS)}</cat:codigoExterno>
                </cli:pais>
                <cli:grupoEconomico>
                    <cat:Id>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_GRUPO_ECONOMICO)}</cat:Id>
                    <cat:Descripcion>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:GRUPO_ECONOMICO)}</cat:Descripcion>
                    <cat:codigoExterno>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:COD_EXTERNO_GE)}</cat:codigoExterno>
                </cli:grupoEconomico>
                <cli:tipoIdentificacion>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:TIPO_IDENTIFICACION)}</cli:tipoIdentificacion>
                <cli:numeroIdentificacion>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:NUMERO_IDENTIFICACION)}</cli:numeroIdentificacion>
                <cli:oficina>
                    <cat:Descripcion>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:OFICINA)}</cat:Descripcion>
                    <cat:codigoExterno>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:CODIGO_OFICINA)}</cat:codigoExterno>
                </cli:oficina>
                <cli:ejecutivo>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:CLIENTE_EJECUTIVO)}</cli:ejecutivo>
            </ope:cliente>
            <ope:producto>
                <pro:idProducto>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_PRODUCTO)}</pro:idProducto>
                <pro:descripcion>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:PRODUCTO)}</pro:descripcion>
            </ope:producto>            
            <ope:descripcion>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:DESCRIPCION)}</ope:descripcion>
            <ope:sectorMercado>
                <cat:Id>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_SECTOR_MERCADO)}</cat:Id>
                <cat:Descripcion>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:SECTOR_MERCADO)}</cat:Descripcion>
            </ope:sectorMercado>
            <ope:programadoPOA>{
              if(xs:string($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:PROGRAMADO_POA)!='') then
                (
                  if(fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:PROGRAMADO_POA)=1) then
                    true()
                  else
                    false()
                )
              else
                false()
            }</ope:programadoPOA>
            <ope:ejercicioPOA>
                <cat:Id>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_EJERCICIO_POA)}</cat:Id>
                <cat:Descripcion>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:EJERCICIO_POA)}</cat:Descripcion>
            </ope:ejercicioPOA>
            <ope:tipoGarantia>
                <cat:Id>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_TIPO_GARANTIA)}</cat:Id>
                <cat:Descripcion>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:TIPO_GARANTIA)}</cat:Descripcion>
            </ope:tipoGarantia>
            <ope:componenteConcesionalidad>{if(fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:COMPONENTE_CONCECIONALIDAD)=1) then true() else false()}</ope:componenteConcesionalidad>
            <ope:estructurador>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ESTRUCTURADOR)}</ope:estructurador>
            <ope:beneficiario>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:BENEFICIARIO)}</ope:beneficiario>
            <ope:unidadEjecutora>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:UNIDAD_EJECUTORA)}</ope:unidadEjecutora>
            <ope:programa>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:PROGRAMA)}</ope:programa>
            <ope:etapa>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:Operacion_ETAPA)}</ope:etapa>
            <ope:status>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:STATUS)}</ope:status>
            <ope:fechaBaja>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:FECHA_BAJA)}</ope:fechaBaja>
            <ope:calificacionRiesgo></ope:calificacionRiesgo>
            <ope:perspectiva>
                <cat:Id>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_PERSPECTIVA)}</cat:Id>
                <cat:DescripcionCorta>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:DESCRIPCION_PERSPECTIVA)}</cat:DescripcionCorta></ope:perspectiva>
            <ope:estado>
                <cat:Id>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ESTADO)}</cat:Id>
                <cat:Descripcion>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:DESCRIPCION_ESTADO)}</cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:DESCRIPCION_CORTA_ESTADO)}</cat:DescripcionCorta>
                <cat:estatus></cat:estatus>
                <cat:codigoExterno></cat:codigoExterno>
            </ope:estado>
			
            {
                if(fn:string-length($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_SECTOR/text())> 0 and 
                   fn:string-length($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_TIPO_GARANTIA/text())> 0) then 
                if (fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_SECTOR)=1 
                  and fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_TIPO_GARANTIA) = 2) then
                    <ope:SRC>
                        <cat:Id>0</cat:Id>
                        <cat:Descripcion>No aplica</cat:Descripcion>
                        <cat:DescripcionCorta>No aplica</cat:DescripcionCorta>
                        <cat:codigoExterno>No aplica</cat:codigoExterno>
                    </ope:SRC>
                else
                    <ope:SRC>
                        <cat:Id>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_SCR)}</cat:Id>
                        <cat:Descripcion>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:SCR_ESTATUS)}</cat:Descripcion>
                        <cat:DescripcionCorta>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:SCR_DESC_CORTA)}</cat:DescripcionCorta>
                        <cat:codigoExterno>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:SCR_COD_EXTERNO)}</cat:codigoExterno>
                    </ope:SRC>
                else()
            }
                
            
            {
            if(fn:string-length($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_SECTOR/text())> 0 and 
                   fn:string-length($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_TIPO_GARANTIA/text())> 0) then 
              if (fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_SECTOR)=1 
                and fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_TIPO_GARANTIA) = 2) then
                <ope:scrClienteDesembolsos>
                  <cat:Id>0</cat:Id>
                  <cat:Descripcion>No aplica</cat:Descripcion>
                  <cat:DescripcionCorta>No aplica</cat:DescripcionCorta>
                  <cat:estatus>true</cat:estatus>
                  <cat:codigoExterno>No aplica</cat:codigoExterno>
                </ope:scrClienteDesembolsos>
                
              else
                <ope:scrClienteDesembolsos>
                  <cat:Id>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_SCR_CLIENTE)}</cat:Id>
                  <cat:Descripcion>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:DESCRIPCION_SCR_CLIENTE)}</cat:Descripcion>
                  <cat:DescripcionCorta>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:DESCRIPCION_CORTA_SCR_CLIENTE)}</cat:DescripcionCorta>
                  <cat:estatus>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ESTADO_SCR_CLIENTE)}</cat:estatus>
                  <cat:codigoExterno>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:CODIGO_EXTERNO_SCR_CLIENTE)}</cat:codigoExterno>
                </ope:scrClienteDesembolsos>
            else()
            }
            
            <ope:RAROC>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:RAROC)}</ope:RAROC>
            
      
            {
              if(xs:string($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:TIR)!='') then
                (
                 
               <ope:TIR> {fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:TIR)}</ope:TIR>
          )
              else
              ()
               
            }
           
            <ope:TIREstatus>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:TIR_ESTATUS)}</ope:TIREstatus>
            <ope:requiereRecursosExternos>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:REQUIERE_RECURSOS_EXT)}</ope:requiereRecursosExternos>
            <ope:enProcesoLaft>{fn:data($inRequiereLaft/ns3:EnProceso)}</ope:enProcesoLaft>
            <ope:aplicaLaft>{if(fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:APLICA_LAFT)=1) then true() else false()}</ope:aplicaLaft>
            <ope:montoOperacion>
                {
                    for $ConsultarMontoOperacion_dbOutput in $ResponseConsultarMontoOperacion/ns11:ConsultarMontoOperacion_dbOutput
                    return 
                    <ope:montoOperacion>
                        <ope:id>{fn:data($ConsultarMontoOperacion_dbOutput/ns11:ID)}</ope:id>
                        <ope:idOperacion>{fn:data($ConsultarMontoOperacion_dbOutput/ns11:ID_OPERACION)}</ope:idOperacion>
                        <ope:IdTcaTipoMonto>{fn:data($ConsultarMontoOperacion_dbOutput/ns11:ID_TCA_TIPO_MONTO)}</ope:IdTcaTipoMonto>
                        <ope:monto>{fn:data($ConsultarMontoOperacion_dbOutput/ns11:MONTO)}</ope:monto>
<ope:idTcaTipoMonedaMontoOperacion>{fn:data($ConsultarMontoOperacion_dbOutput/ns11:ID_TCA_TIPO_MONEDA)}</ope:idTcaTipoMonedaMontoOperacion>
                        <ope:Descripcion>{fn:data($ConsultarMontoOperacion_dbOutput/ns11:DESCRIPCION)}</ope:Descripcion>
                    </ope:montoOperacion>
                }
            </ope:montoOperacion>
            
            <ope:clasificacionAmbiental>
              {
                 for $ConsultarClasificacionAmbiental_BSOutput in $ResponseConsultarClasificacionAmbiental/ns4:ConsultarClasificacionAmbiental_BSOutput
                 return
                 <ope:clasificacionAmbiental>
                     <ope:id>{fn:data($ConsultarClasificacionAmbiental_BSOutput/ns4:ID)}</ope:id>
                     <ope:idOperacion>{fn:data($ConsultarClasificacionAmbiental_BSOutput/ns4:ID_OPERACION)}</ope:idOperacion>
                     
                     <ope:sectorAmbiental>
                         <cat:Id>{fn:data($ConsultarClasificacionAmbiental_BSOutput/ns4:ID_SA)}</cat:Id>
                         <cat:Descripcion>{fn:data($ConsultarClasificacionAmbiental_BSOutput/ns4:DESCRIPCION_SA)}</cat:Descripcion></ope:sectorAmbiental>
                     <ope:aporteAmbiental>
                         <cat:Id>{fn:data($ConsultarClasificacionAmbiental_BSOutput/ns4:ID_AA)}</cat:Id>
                         <cat:Descripcion>{fn:data($ConsultarClasificacionAmbiental_BSOutput/ns4:DESCRIPCION_AA)}</cat:Descripcion></ope:aporteAmbiental>
                     <ope:categoriaAmbiental>
                         <cat:Id>{fn:data($ConsultarClasificacionAmbiental_BSOutput/ns4:ID_CA)}</cat:Id>
                         <cat:Descripcion>{fn:data($ConsultarClasificacionAmbiental_BSOutput/ns4:DESCRIPCION_CA)}</cat:Descripcion></ope:categoriaAmbiental>
                     <ope:subCategoriaAmbiental>
                         <cat:Id>{fn:data($ConsultarClasificacionAmbiental_BSOutput/ns4:ID_SCA)}</cat:Id>
                         <cat:Descripcion>{fn:data($ConsultarClasificacionAmbiental_BSOutput/ns4:DESCRIPCION_SCA)}</cat:Descripcion></ope:subCategoriaAmbiental>
                     <ope:banStatus>{fn:data($ConsultarClasificacionAmbiental_BSOutput/ns4:BAN_ESTATUS)}</ope:banStatus>
                     <ope:fechaRegistro>{fn:data($ConsultarClasificacionAmbiental_BSOutput/ns4:FECHA_REGISTRO)}</ope:fechaRegistro>
                     
                     
                 </ope:clasificacionAmbiental>
              }
            </ope:clasificacionAmbiental>
            
            <ope:tipoMoneda>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:MONEDA)}</ope:tipoMoneda>
            <ope:justificacionEnvio>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:JUSTIFICACION_ENVIO)}</ope:justificacionEnvio>
            <ope:Factibilidad>
                <des:tipo>
                    <cat:Id>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_TCA_TIPO_TASA)}</cat:Id>
                    <cat:Descripcion>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:TASA_DESEM_DESCRIPCION)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:TASA_DESEM_DESCRIPCION_CORTA)}</cat:DescripcionCorta>
                    <cat:codigoExterno>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:TASA_DESEM_COD_EXTERNO)}</cat:codigoExterno>
                </des:tipo>
                {
                if($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:TASA_DESEM_COD_EXTERNO/text() ='F') then
                  <des:variable>
                        <des:tasaReferencia>
                            <cat:id>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:CODIGO_TASA_REFERENCIA)}</cat:id>
                            <cat:codigoExterno></cat:codigoExterno>
                        <des:valor>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:VALOR_TASA)}</des:valor>
                        </des:tasaReferencia>
                        <des:spread>
                          <des:valor>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:VALOR_SPREAD)}</des:valor>
                          <des:codigo></des:codigo>
                        </des:spread>
                    </des:variable>
                else if($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:TASA_DESEM_COD_EXTERNO/text() ='X') then
                  <des:fija>
                    <des:valor>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:VALOR_TASA)}</des:valor>
                  </des:fija>
                else if($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:TASA_DESEM_COD_EXTERNO/text() ='S') then
                  <des:especial>
                    <des:valor>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:VALOR_TASA)}</des:valor>
                  </des:especial>
                else
                ()
                }
                <ope:esFactible>
                  { if(xs:string(fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ES_FACTIBLE)) = '1')
                    then true()
                    else false()
                  }
                </ope:esFactible>
                <ope:fechaFinalizacionEstudios>{substring-before($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:FECHA_FINALIZACION_ESTUDIOS/text(),'T')}</ope:fechaFinalizacionEstudios>
                <ope:fechaCalculoInteres>{substring-before($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:FECHA_CALCULO_INTERES/text(),'T')}</ope:fechaCalculoInteres>
            </ope:Factibilidad>
            <ope:esMultisectorial>
               { if(xs:string(fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ES_MULTISECTORIAL)) = '1')
                    then true()
                    else false()
              }
            </ope:esMultisectorial>
            <ope:clasificacionEstrategicaMultisectorial>
            {
              let $distinctClasificacionEstrategica := distinct-values($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput/ns1:ID_CLASIFICACION_ESTRATEGICA)
              for $id in $distinctClasificacionEstrategica
              let $ConsultarOperacionByIdOperacionOutput := $ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[ns1:ID_CLASIFICACION_ESTRATEGICA = $id][1]
                 return
                     <ope:componenteClasificacionEstrategica>
                         <ope:nombreComponente>{fn:data($ConsultarOperacionByIdOperacionOutput/ns1:NOMBRE_DEL_COMPONENTE)}</ope:nombreComponente>
                        <ope:descripcion>{fn:data($ConsultarOperacionByIdOperacionOutput/ns1:DESCRIPCION_DEL_COMPONENTE)}</ope:descripcion>
                        <ope:porcentaje>{fn:data($ConsultarOperacionByIdOperacionOutput/ns1:PORCENTAJE)}</ope:porcentaje>
                        <ope:actividadEconomica>
                            <cat:Id>{fn:data($ConsultarOperacionByIdOperacionOutput/ns1:ID_ACTIVIDAD_ECONOMICA_CE)}</cat:Id>
                            <cat:Descripcion>{fn:data($ConsultarOperacionByIdOperacionOutput/ns1:ACTIVIDAD_ECONOMICA_CE)}</cat:Descripcion>
                            <cat:codigoExterno>{fn:data($ConsultarOperacionByIdOperacionOutput/ns1:COD_EXT_ACTIVIDAD_ECONOMICA_CE)}</cat:codigoExterno>
                        </ope:actividadEconomica>
                        <ope:actividadEconomicaAsociada>
                            <cat:Id>{fn:data($ConsultarOperacionByIdOperacionOutput/ns1:ID_ACTIVIDAD_ECONOMICA_ASO_CE)}</cat:Id>
                                <cat:Descripcion>{fn:data($ConsultarOperacionByIdOperacionOutput/ns1:ACTIVIDAD_ECONOMICA_ASO_CE)}</cat:Descripcion>
                                <cat:codigoExterno>{fn:data($ConsultarOperacionByIdOperacionOutput/ns1:CODEXT_ACT_ECONOMIC_ASO_CE)}</cat:codigoExterno>
                        </ope:actividadEconomicaAsociada>
                        <ope:areaFocalizacion>
                            <cat:Id>{fn:data($ConsultarOperacionByIdOperacionOutput/ns1:ID_AREA_FOCALIZACION_CE)}</cat:Id>
                            <cat:Descripcion>{fn:data($ConsultarOperacionByIdOperacionOutput/ns1:AREA_FOCALIZACION_CE)}</cat:Descripcion>
                        </ope:areaFocalizacion>
                        <ope:ejeEstrategico>
                            <cat:Id>{fn:data($ConsultarOperacionByIdOperacionOutput/ns1:ID_EJE_ESTRATEGICO_CE)}</cat:Id>
                            <cat:Descripcion>{fn:data($ConsultarOperacionByIdOperacionOutput/ns1:EJE_ESTRATEGICO_CE)}</cat:Descripcion>
                            <cat:codigoExterno>{fn:data($ConsultarOperacionByIdOperacionOutput/ns1:COD_EJE_ESTRATEGICO_CE)}</cat:codigoExterno>
                        </ope:ejeEstrategico>
                        <ope:iniciativaEstrategica>
                            <cat:Id>{fn:data($ConsultarOperacionByIdOperacionOutput/ns1:ID_INICIATIVA_ESTRATEGICA_CE)}</cat:Id>
                            <cat:Descripcion>{fn:data($ConsultarOperacionByIdOperacionOutput/ns1:INICIATIVA_ESTRATEGICA_CE)}</cat:Descripcion>
                        </ope:iniciativaEstrategica>
                        <ope:cantidadPaises>
                            <cat:Id>{fn:data($ConsultarOperacionByIdOperacionOutput/ns1:ID_CANTIDAD_PAISES_CE)}</cat:Id>
                            <cat:Descripcion>{fn:data($ConsultarOperacionByIdOperacionOutput/ns1:CANTIDAD_PAISES_CE)}</cat:Descripcion>
                        </ope:cantidadPaises>
                        <ope:sectorIbcie>
                            <cat:Id>{fn:data($ConsultarOperacionByIdOperacionOutput/ns1:ID_SECTOR_IBCIE)}</cat:Id>
                            <cat:Descripcion>{fn:data($ConsultarOperacionByIdOperacionOutput/ns1:SECTOR_IBCIE_DESCRIPCION)}</cat:Descripcion>
                        </ope:sectorIbcie>
                        <ope:subSectorIbcie>
                            <cat:Id>{fn:data($ConsultarOperacionByIdOperacionOutput/ns1:ID_SUBSECTOR_IBCIE)}</cat:Id>
                            <cat:Descripcion>{fn:data($ConsultarOperacionByIdOperacionOutput/ns1:SUBSECTOR_IBCIE_DESCRIPCION)}</cat:Descripcion>
                        </ope:subSectorIbcie>
                </ope:componenteClasificacionEstrategica>       
            }
			 
            </ope:clasificacionEstrategicaMultisectorial>
             <ope:idSectorInstitucional>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_SECTOR_INSTITUCIONAL)}</ope:idSectorInstitucional>
             <ope:idEncargado>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_ENCARGADO)}</ope:idEncargado>
            <ope:TipoMoneda>
                <cat:Id>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_TCA_TIPO_MONEDA)}</cat:Id>
                <cat:codigoExterno>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:TIPO_MONEDA)}</cat:codigoExterno>
            </ope:TipoMoneda>
            
          {
              if(xs:string($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:CSI_ID)!='')
			  
			  then
                (
                 
               <ope:sectorInstitucional>
                <cat:Id>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:CSI_ID)}</cat:Id>
                <cat:Descripcion>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:CSI_DESCRIPCION)}</cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:CSI_DESCRIPCION_CORTA)}</cat:DescripcionCorta>
                <cat:estatus>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:CSI_BAN_ESTATUS)}</cat:estatus>
                <cat:codigoExterno>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:CSI_COD_EXTERNO)}</cat:codigoExterno>
            </ope:sectorInstitucional>
          )
              else
              ()
               
     }
   {
              if(xs:string($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:TCA_ENC_ID)!='')
			  
			  then
                (
                 
            <ope:encargado>
                <cat:Id>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:TCA_ENC_ID)}</cat:Id>
                <cat:Descripcion>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:TCA_ENC_DESCRIPCION)}</cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:TCA_ENC_DES_CORTA)}</cat:DescripcionCorta>
                <cat:estatus>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:TCA_ENC_BAN_ESTATUS)}</cat:estatus>
                <cat:codigoExterno>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:TCA_ENC_CODIGO_EXTERNO)}</cat:codigoExterno>
            </ope:encargado>
          )
              else
              ()
               
     }
   <ope:idRol>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_TCA_ROL_ENCARGADO)}</ope:idRol>
   <ope:descripcionRol>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:DESCRIPCION_ROL_ENCARGADO)}</ope:descripcionRol>
   <ope:ubicacionProyecto>{fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:UBICACION_PROYECTO)}</ope:ubicacionProyecto>
            <ope:idCatPais>{            
            if(xs:string($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_CAT_PAIS)!='') then
               (xs:decimal(fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_CAT_PAIS)))
            else
             ()                
            }</ope:idCatPais>
            <ope:Pais>
                <cat:Id>{
                if(xs:string($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_CAT_PAIS)!='') then
                    (fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:ID_CAT_PAIS))
                else
                    ()   
                }</cat:Id>
                <cat:Descripcion>{
                if(xs:string($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:DESCRIPCION_PAIS)!='') then
                    (xs:string(fn:data($ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput[1]/ns1:DESCRIPCION_PAIS)))
                else
                    ()   
                }</cat:Descripcion>
            </ope:Pais>           
            {for $i in $ClienteResponse/ns1:ConsultarOperacionByIdOperacionOutput
              where string($i/ns1:ID_CONTRATO) != ''
              return
            <ope:contrato>
                <con:idContrato>{fn:data($i/ns1:ID_CONTRATO)}</con:idContrato>
                <con:idOperacion>{fn:data($i/ns1:ID_OPERACION)}</con:idOperacion>
                <con:fechaFirma>{fn:data($i/ns1:FECHA_FIRMA)}</con:fechaFirma>
                <con:fechaVigencia>{fn:data($i/ns1:FECHA_VIGENCIA)}</con:fechaVigencia>
                <con:MontoEscriturado>{fn:data($i/ns1:MONTO_ESCRITURADO)}</con:MontoEscriturado>
                <con:cuentaCliente>
                    <con:cuentaCliente>{fn:data($i/ns1:CUENTA_CLIENTE)}</con:cuentaCliente>
                </con:cuentaCliente>
                <con:instanciaProceso>{fn:data($i/ns1:INSTANCIA_PROCESO)}</con:instanciaProceso>
            </ope:contrato>
           }           
        </ns2:Operacion>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta realizada existosamente</res:message>
        </ns2:Resultado>
    </ns2:ConsultarOperacionResponse>
};

local:func($ClienteResponse, $inRequiereLaft, $ResponseConsultarMontoOperacion, $ResponseConsultarClasificacionAmbiental)
