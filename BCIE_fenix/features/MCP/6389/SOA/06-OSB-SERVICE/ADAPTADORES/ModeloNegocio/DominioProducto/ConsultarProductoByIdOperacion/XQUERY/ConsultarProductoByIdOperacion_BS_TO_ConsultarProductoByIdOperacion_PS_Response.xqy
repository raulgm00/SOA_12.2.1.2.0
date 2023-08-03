xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ProductoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioProducto/Producto/V1/Schema/ProductoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarProductoByIdOperacion";
(:: import schema at "../XSD/ConsultarProductoByIdOperacion.xsd" ::)

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarProductoResponse as element() (:: schema-element(ns1:ConsultarProductoByIdOperacionOutputCollection) ::) external;

declare function local:func($ConsultarProductoResponse as element() (:: schema-element(ns1:ConsultarProductoByIdOperacionOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarProductoByIdOperacionResponse) ::) {
    <ns2:ConsultarProductoByIdOperacionResponse>
        <ns2:Producto>
            <pro:idProducto>{if (not(fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:ID)))
                                  then(fn:empty(fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:ID)))
                                    else(fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:ID))}</pro:idProducto>
            <pro:descripcion>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:DESCRIPCION)}</pro:descripcion>
            <pro:descripcionCorta>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:DESCRIPCION_CORTA)}</pro:descripcionCorta>
            <pro:fechaRegistro></pro:fechaRegistro>
            <pro:codExterno>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:COD_EXTERNO)}</pro:codExterno>
            <pro:idInstrumentoFinanciero>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:ID_INSTRUMENTO_FINANCIERO)}</pro:idInstrumentoFinanciero>
            <pro:idTipoOperacion>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:ID_TIPO_OPERACION)}</pro:idTipoOperacion>
            <pro:reglas>
                <pro:requiereElegibilidad>{
                  if(fn:string($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:REQUIERE_ELEGIBILIDAD) = '1') then
                    true()
                  else
                    false()
                }</pro:requiereElegibilidad>
                <pro:requiereLaft>{
                if( fn:string($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:REQUIERE_LAFT) = '1') then
                    'true'
                  else
                    'false'
                }</pro:requiereLaft>
                <pro:OFICrealizaAnalisisLAFT>{
                  if(fn:string($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:OFIC_REALIZA_ANALISIS_LAFT) = '1') then
                    'true'
                  else
                    'false'
                  }</pro:OFICrealizaAnalisisLAFT>
                <pro:elaboraHojaTerminosPCT>{
                  if(fn:string($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:REQUIERE_HOJA_TERMINOS_PCT) = '1') then
                    'true'
                  else
                    'false'
                  }</pro:elaboraHojaTerminosPCT>
                <pro:elaboraHojaTerminosSEPRI>{
                  if(fn:string($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:REQUIERE_HOJA_TERMINOS_SEPRI) = '1') then
                    'true'
                  else
                    'false'
                  }</pro:elaboraHojaTerminosSEPRI>
                <pro:participaFINAM>{
                 if(fn:string($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:ES_IFI) = '1') then
                    'true'
                  else
                    'false'
                }</pro:participaFINAM>
                <pro:requiereAdquisiciones>{
                  if(fn:string($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:REQUIERE_ADQUISICIONES)='1' )then
                    'true' else 'false'
                }</pro:requiereAdquisiciones>
                <pro:requiereRAROC>{
                  if(fn:string($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:REQUIERE_RAROC)='1')then
                    'true' else 'false'
                }</pro:requiereRAROC>
                <pro:requiereIBICIE>{
                  if(fn:string($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:REQUIERE_IBCIE)='1')then
                    'true' else 'false'
                }</pro:requiereIBICIE>
                <pro:requiereSIEMAS>{
                  if(fn:string($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:REQUIERE_SIEMAS)='1')then
                    'true' else 'false'
                  }</pro:requiereSIEMAS>
                <pro:requiereGERIESElegibilidad>{
                  if(fn:string($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:REQUIERE_GERIES_ELEGIBILIDAD)='1')then
                    'true' else 'false'
                  }</pro:requiereGERIESElegibilidad>
                <pro:requiereOpinionTecnica>{
                  if(fn:string($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:REQUIERE_OPINION_TECNICA)='1')then
                    'true' else 'false'
                  }</pro:requiereOpinionTecnica>
                <pro:requiereAsjurAnalisis>{
                    if(xs:string($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:CODIGO_TIPO_INSTITUCION)='4') then false() else
                      if(fn:string($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:REQUIERE_ASJUR_ANALISIS)='1') then
                        true() else false()
                  }
                </pro:requiereAsjurAnalisis>
                <pro:requiereAsjurElegibilidad>{
                  if(fn:string($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:REQUIERE_ASJUR_ELEGIBILIDAD)='1') then
                    'true' else 'false'
                  }</pro:requiereAsjurElegibilidad>
                <pro:requierePreparacion>{
                  if(fn:string($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:REQUIERE_PREPARACION)='1')then
                    true()
                  else
                    false()
                }</pro:requierePreparacion>
                <pro:tieneRiesgoCredito>{
                  if(fn:string($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:TIENE_RIESGO_CREDITO)='1')then
                    true()
                  else
                    false()
                }</pro:tieneRiesgoCredito>
                <pro:responsableAnalisis>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:RESPONSABLE_ANALISIS)}</pro:responsableAnalisis>
                <pro:nombreResponsableAnalisis>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:NOM_RESPONSABLE_ANALISIS)}</pro:nombreResponsableAnalisis>
                <pro:requiereFirmacontrato>{
                  if(fn:string($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:REQUIERE_FIRMA_CONTRATO)='1')then
                    true()
                  else
                    false()
                }</pro:requiereFirmacontrato>
                <pro:requiereRegistroLinea>{
                  if(fn:string($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:REQUIERE_REGISTRO_LINEA)='1')then
                    true()
                  else
                    false()
                }</pro:requiereRegistroLinea>
                <pro:requiereCore>{
                  if(fn:string(($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:REQUIERE_CORE))='1')then
                    true()
                  else
                    false()
                }
             </pro:requiereCore>
             {
             if(string($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:REQUIERE_VALIDAR_MATRIZ/text()) != '1')then
            <pro:requiereValidarMatriz>false</pro:requiereValidarMatriz>
            else
            (<pro:requiereValidarMatriz>true</pro:requiereValidarMatriz>)
            }
            </pro:reglas>
            <pro:Requiere_Cierre>
                <pro:Requiere_Cierre>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:REQUIERE_CIERRE)}</pro:Requiere_Cierre>
                
                {
                if((string($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:REQUIERE_CIERRE)) eq 'true' or '1')
                then
                     <pro:Tipo_Cierre>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:TIPO_CIERRE)}</pro:Tipo_Cierre>
                else(<pro:Tipo_Cierre>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:TIPO_CIERRE)}</pro:Tipo_Cierre>)
                }
            </pro:Requiere_Cierre>
            
        </ns2:Producto>
        <ns2:Operacion>
            <ope:idOperacion>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:ID_OPERACION)}</ope:idOperacion>
            <ope:responsable>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:USUARIO)}</ope:responsable>            
            <ope:oficina>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:OFICINA)}</ope:oficina>
            <ope:nombre>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:NOMBRE)}</ope:nombre>
            <ope:cliente>
                <cli:idCliente>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:ID_CLIENTE)}</cli:idCliente>
                <cli:idFacturador>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:ID_FLEXCUBE)}</cli:idFacturador>
                <cli:razonSocial>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:RAZON_SOCIAL)}</cli:razonSocial>
                <cli:abreviatura>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:ABREVIATURA)}</cli:abreviatura>
                <cli:tipoPersona>
                    <cat:Id>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:ID_TIPO_PERSONA)}</cat:Id>
                    <cat:Descripcion>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:TIPO_PERSONA)}</cat:Descripcion>
                </cli:tipoPersona>
                <cli:tipoCliente></cli:tipoCliente>
                <cli:sector>
                    <cat:Id>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:ID_SECTOR)}</cat:Id>
                    <cat:Descripcion>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:SECTOR)}</cat:Descripcion>
                </cli:sector>
                <cli:tipoInstitucion>
                    <cat:Id>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:CODIGO_TIPO_INSTITUCION)}</cat:Id>
                    <cat:Descripcion>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:TIPO_INSTITUCION)}</cat:Descripcion>
                </cli:tipoInstitucion>
                <cli:pais>
                    <cat:Id> </cat:Id>
                    <cat:Descripcion>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:PAIS)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:CODIGO_PAIS)}</cat:DescripcionCorta>
                </cli:pais>                
                <cli:tipoIdentificacion>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:TIPO_IDENTIFICACION)}</cli:tipoIdentificacion>
                <cli:numeroIdentificacion>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:NUMERO_IDENTIFICACION)}</cli:numeroIdentificacion>                
            </ope:cliente>
            <ope:producto></ope:producto>
            <ope:descripcion>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:DESCRIPCION_OP)}</ope:descripcion>
            <ope:montoTotal>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:MONTO_TOTAL)}</ope:montoTotal>
            <ope:montoSolicitado>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:MONTO_SOLICITADO)}</ope:montoSolicitado>            
            <ope:actividadEconomica>
                <cat:Descripcion>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:ACTIVIDAD_ECONOMICA)}</cat:Descripcion>
            </ope:actividadEconomica>            
            <ope:areaFocalizacion>
                <cat:Descripcion>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:AREA_FOCALIZACION)}</cat:Descripcion>
            </ope:areaFocalizacion>
            <ope:ejeEstrategico>
                <cat:Descripcion>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:EJE_ESTRATEGICO)}</cat:Descripcion>
            </ope:ejeEstrategico>
            <ope:iniciativaEstrategica>
                <cat:Descripcion>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:INICIATIVA_ESTRATEGICA)}</cat:Descripcion>
            </ope:iniciativaEstrategica>
            <ope:cantidadPaises>
                <cat:Descripcion>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:CANTIDAD_PAISES)}</cat:Descripcion>
            </ope:cantidadPaises>
            <ope:sectorMercado>
                <cat:Descripcion>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:SECTOR_MERCADO)}</cat:Descripcion>
            </ope:sectorMercado>
            <ope:programadoPOA>{
              if(xs:string($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:PROGRAMADO_POA)!='') then
              (
                if(fn:string($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:PROGRAMADO_POA)='1') then
                  true()
                else
                  false()
              )
              else 
                false()
            }</ope:programadoPOA>
            <ope:ejercicioPOA>
                <cat:Descripcion>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:EJERCICIO_POA)}</cat:Descripcion>
            </ope:ejercicioPOA>
            <ope:tipoGarantia>
                <cat:Id>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:ID_TIPO_GARANTIA)}</cat:Id>
                <cat:Descripcion>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:TIPO_GARANTIA)}</cat:Descripcion>
            </ope:tipoGarantia>
            <ope:componenteConcesionalidad>{if(fn:string($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:COMPONENTE_CONCECIONALIDAD)='1') then true() else false()}</ope:componenteConcesionalidad>
            <ope:unidadEjecutora>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:UNIDAD_EJECUTORA)}</ope:unidadEjecutora>
            <ope:calificacionRiesgo>SCR-1</ope:calificacionRiesgo>
            <ope:perspectiva>
                <cat:Id>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:ID_PERSPECTIVA)}</cat:Id>
                <cat:DescripcionCorta>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:DESCRIPCION_PERSPECTIVA)}</cat:DescripcionCorta>
            </ope:perspectiva>
            <ope:montoOperacion>
                <ope:montoOperacion>
                    <ope:id>1</ope:id>
                    <ope:idOperacion></ope:idOperacion>
                    <ope:IdTcaTipoMonto></ope:IdTcaTipoMonto>
                    <ope:monto>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:MONTO_TOTAL)}</ope:monto>
                    <ope:Descripcion></ope:Descripcion>
                </ope:montoOperacion>
            </ope:montoOperacion>
            <ope:justificacionCancela>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:JUSTIFICACION_CANCELA)}</ope:justificacionCancela>
            <ope:idSectorInstitucional>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:ID_SECTOR_INSTITUCIONAL)}</ope:idSectorInstitucional>
            <ope:idEncargado>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:ID_ENCARGADO)}</ope:idEncargado>
            <ope:idRol>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:TCA_ID)}</ope:idRol>
            <ope:descripcionRol>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:TCA_DESCRIPCION_CORTA)}</ope:descripcionRol>
               <ope:montoOperacion>
                <ope:montoOperacion>
                    <ope:id>2</ope:id>
                    <ope:idOperacion></ope:idOperacion>
                    <ope:IdTcaTipoMonto></ope:IdTcaTipoMonto>
                    <ope:monto>{fn:data($ConsultarProductoResponse/ns1:ConsultarProductoByIdOperacionOutput/ns1:MONTO_SOLICITADO)}</ope:monto>
                    <ope:Descripcion></ope:Descripcion>
                </ope:montoOperacion>
            </ope:montoOperacion>
        </ns2:Operacion>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta realizada correctamente</res:message>
        </ns2:Resultado>
    </ns2:ConsultarProductoByIdOperacionResponse>
};

local:func($ConsultarProductoResponse)
