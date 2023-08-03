xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)


declare namespace ns8="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns3="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarAtributoByCondicion";
(:: import schema at "../../ConsultarAtributoByCondicion/XSD/ConsultarAtributoByCondicion.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarCategoriaByCondicion";
(:: import schema at "../../ConsultarCategoriaByCondicion/XSD/ConsultarCategoriaByCondicion.xsd" ::)
declare namespace ns5="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarFuenteByCondicion";
(:: import schema at "../../../../DominioLineaCredito/ConsultarFuenteByCondicion/XSD/ConsultarFuenteByCondicion.xsd" ::)
declare namespace ns4="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarLineaCreditoByCondicion";
(:: import schema at "../../../../DominioLineaCredito/ConsultarLineaCreditoByCondicion/XSD/ConsultarLineaCreditoByCondicion.xsd" ::)
declare namespace ns6="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarValidadoresbyCategoria";
(:: import schema at "../../../../Utilidades/ConsultarValidadoresByCategoria/XSD/ConsultarValidadoresbyCategoria.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/consultarCondicionByRol";
(:: import schema at "../XSD/consultarCondicionByRol.xsd" ::)
declare namespace ns7="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarValoresdeAtributosCondicion";
(:: import schema at "../../ConsultarValoresAtributosCondicion/XSD/ConsultarValoresdeAtributosCondicion_sp.xsd" ::)
declare namespace ns11="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarEventoCondicion";
(:: import schema at "../../ConsultarEventoByCondicion/XSD/ConsultarEventoCondicion.xsd" ::)


declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $consultarCondicionByRolOutputCollection as element() (:: schema-element(ns1:consultarCondicionByRolOutputCollection) ::) external;
declare variable $ConsultarCategoriaByCondicionOutputCollection as element() (:: schema-element(ns2:ConsultarCategoriaByCondicionOutputCollection) ::) external;
declare variable $ConsultarAtributoByCondicionOutputCollection as element() (:: schema-element(ns3:ConsultarAtributoByCondicionOutputCollection) ::) external;
declare variable $ConsultarLineaCreditoByCondicionOutputCollection as element() (:: schema-element(ns4:ConsultarLineaCreditoByCondicionOutputCollection) ::) external;
declare variable $ConsultarFuenteByCondicionOutputCollection as element() (:: schema-element(ns5:ConsultarFuenteByCondicionOutputCollection) ::) external;
declare variable $ConsultarValidadoresbyCategoriaOutputCollection as element() (:: schema-element(ns6:ConsultarValidadoresbyCategoriaOutputCollection) ::) external;
declare variable $valoresOutputParameters as element() (:: schema-element(ns7:OutputParameters) ::) external;
declare variable $ConsultarEventoCondicionOutputCollection as element() (:: schema-element(ns11:ConsultarEventoCondicionOutputCollection) ::) external;

declare function local:func($consultarCondicionByRolOutputCollection as element() (:: schema-element(ns1:consultarCondicionByRolOutputCollection) ::), 
                            $ConsultarCategoriaByCondicionOutputCollection as element() (:: schema-element(ns2:ConsultarCategoriaByCondicionOutputCollection) ::), 
                            $ConsultarAtributoByCondicionOutputCollection as element() (:: schema-element(ns3:ConsultarAtributoByCondicionOutputCollection) ::), 
                            $ConsultarLineaCreditoByCondicionOutputCollection as element() (:: schema-element(ns4:ConsultarLineaCreditoByCondicionOutputCollection) ::), 
                            $ConsultarFuenteByCondicionOutputCollection as element() (:: schema-element(ns5:ConsultarFuenteByCondicionOutputCollection) ::), 
                            $ConsultarValidadoresbyCategoriaOutputCollection as element() (:: schema-element(ns6:ConsultarValidadoresbyCategoriaOutputCollection) ::), 
                                                       $valoresOutputParameters as element() (:: schema-element(ns7:OutputParameters) ::),
                                                       $ConsultarEventoCondicionOutputCollection as element() (:: schema-element(ns11:ConsultarEventoCondicionOutputCollection) ::)) 
                            as element() (:: schema-element(ns8:ConsultarCondicionByRolResponse) ::) {
    <ns8:ConsultarCondicionByRolResponse>
        {
            for $consultarCondicionByRolOutput in $consultarCondicionByRolOutputCollection/ns1:consultarCondicionByRolOutput
            return 
            <ns8:Condicion>
                <con:idCondicion>{fn:data($consultarCondicionByRolOutput/ns1:ID)}</con:idCondicion>
                <con:operacion>{fn:data($consultarCondicionByRolOutput/ns1:ID_OPERACION)}</con:operacion>
                <con:nombre>{fn:data($consultarCondicionByRolOutput/ns1:NOMBRE)}</con:nombre>
                <con:descripcion>{fn:data($consultarCondicionByRolOutput/ns1:DESCRIPCION)}</con:descripcion>
                <con:tipoCondicion>
                    <con:idCatCondicion>{fn:data($consultarCondicionByRolOutput/ns1:ID_TCA_CONDICION)}</con:idCatCondicion>
                    <con:descripcion>{fn:data($consultarCondicionByRolOutput/ns1:DESCRIPCION_TCA)}</con:descripcion>
                    <con:descripcionCorta>{fn:data($consultarCondicionByRolOutput/ns1:DESCRIPCION_CORTA)}</con:descripcionCorta>
                    <con:idTipoCondicion>{fn:data($consultarCondicionByRolOutput/ns1:ID_TCA_TIPO_CONDICION)}</con:idTipoCondicion>
                    <con:esEditableEnFormalizacion>{if(string($consultarCondicionByRolOutput/ns1:ES_EDITABLE_EN_FORMALIZACION)='') then false()
                    else if (fn:data($consultarCondicionByRolOutput/ns1:ES_EDITABLE_EN_FORMALIZACION)=1) then true() else false()
                    }</con:esEditableEnFormalizacion>
                    <con:requiereValidarCOFI>{ if(string ($consultarCondicionByRolOutput/ns1:REQUIERE_VALIDAR_COFI)='')then false() else
                    if (fn:data($consultarCondicionByRolOutput/ns1:REQUIERE_VALIDAR_COFI)=1)then true() else false()
                    }</con:requiereValidarCOFI>
                    <con:sePuedeDispensar>{if (string ($consultarCondicionByRolOutput/ns1:SE_PUEDE_DISPENSAR) ='')then false() else 
                   if( fn:data($consultarCondicionByRolOutput/ns1:SE_PUEDE_DISPENSAR)=1) then true() else false()
                    }</con:sePuedeDispensar>
                    <con:esPlantilla>{if(string ($consultarCondicionByRolOutput/ns1:ES_PLANTILLA)='') then false() else
                    if(    fn:data($consultarCondicionByRolOutput/ns1:ES_PLANTILLA)=1) then true() else false()
                    }</con:esPlantilla>
                    <con:idCondicionPrecarga>{fn:data($consultarCondicionByRolOutput/ns1:ID_CONDICION_PRECARGA)}</con:idCondicionPrecarga>
                    <con:fechaRegistro>{fn:data($consultarCondicionByRolOutput/ns1:FECHA_REGISTRO)}</con:fechaRegistro>
                    <con:estado>{if (string($consultarCondicionByRolOutput/ns1:BAN_ESTATUS)='') then false()   else                 
                    if(fn:data($consultarCondicionByRolOutput/ns1:BAN_ESTATUS)=1) then true() else false ()
                    }</con:estado>
                    <con:codigoExterno>{fn:data($consultarCondicionByRolOutput/ns1:COD_EXTERNO)}</con:codigoExterno>
                </con:tipoCondicion>
                {
                    for $ConsultarCategoriaByCondicionOutput in $ConsultarCategoriaByCondicionOutputCollection/ns2:ConsultarCategoriaByCondicionOutput
                  where fn:data($consultarCondicionByRolOutput/ns1:ID)=fn:data($ConsultarCategoriaByCondicionOutput/ns2:ID_CONDICION)
                    return 
                    <con:categoriaCondicion>
                        <con:id>{fn:data($ConsultarCategoriaByCondicionOutput/ns2:ID)}</con:id>
                        <con:descripcion>{fn:data($ConsultarCategoriaByCondicionOutput/ns2:DESCRIPCION)}</con:descripcion>
                        <con:descripcionCorta>{fn:data($ConsultarCategoriaByCondicionOutput/ns2:DESCRIPCION_CORTA)}</con:descripcionCorta>
                        <con:estatus>{if (string($ConsultarCategoriaByCondicionOutput/ns2:BAN_ESTATUS)='') then false() else
                        if(fn:data($ConsultarCategoriaByCondicionOutput/ns2:BAN_ESTATUS)=1) then true() else false()
                        }</con:estatus>
                        <con:codigoExterno>{fn:data($ConsultarCategoriaByCondicionOutput/ns2:COD_EXTERNO)}</con:codigoExterno>
                        {
                            for $ConsultarValidadoresbyCategoriaOutput in $ConsultarValidadoresbyCategoriaOutputCollection/ns6:ConsultarValidadoresbyCategoriaOutput
                            where fn:data($ConsultarValidadoresbyCategoriaOutput/ns6:ID_CAT_PRODUCTO)=fn:data($consultarCondicionByRolOutput/ns1:ID_PRODUCTO)
                            and fn:data($ConsultarValidadoresbyCategoriaOutput/ns6:ID_TCA_CATEGORIA)=fn:data($ConsultarCategoriaByCondicionOutput/ns2:ID)
                            return 
                            <con:validadores>
                                <cat:Id>{fn:data($ConsultarValidadoresbyCategoriaOutput/ns6:ID)}</cat:Id>
                                <cat:Descripcion>{fn:data($ConsultarValidadoresbyCategoriaOutput/ns6:DESCRIPCION)}</cat:Descripcion>
                                <cat:DescripcionCorta>{fn:data($ConsultarValidadoresbyCategoriaOutput/ns6:DESCRIPCION_CORTA)}</cat:DescripcionCorta>
                                <cat:estatus>{if(string($ConsultarValidadoresbyCategoriaOutput/ns6:BAN_ESTATUS)='') then false() else
                                if(fn:data($ConsultarValidadoresbyCategoriaOutput/ns6:BAN_ESTATUS)=1)then true() else false()
                                }</cat:estatus>
                                <cat:codigoExterno>{fn:data($ConsultarValidadoresbyCategoriaOutput/ns6:COD_EXTERNO)}</cat:codigoExterno></con:validadores>
                        }</con:categoriaCondicion>
                }
                <con:controlCondicion>
                    <cat:Id>{fn:data($consultarCondicionByRolOutput/ns1:ID_TCA_CONTROL_CONDICION)}</cat:Id>
                    <cat:Descripcion>{fn:data($consultarCondicionByRolOutput/ns1:DESCRIPCION_T)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($consultarCondicionByRolOutput/ns1:DESCRIPCION_CORTA_TC)}</cat:DescripcionCorta>
                    <cat:estatus>{ if(string($consultarCondicionByRolOutput/ns1:BAN_ESTATUS_TC)='') then false () else
                   if( fn:data($consultarCondicionByRolOutput/ns1:BAN_ESTATUS_TC)=1) then true() else false()
                    }</cat:estatus>
                    <cat:codigoExterno>{fn:data($consultarCondicionByRolOutput/ns1:COD_EXTERNO_TC)}</cat:codigoExterno>
                </con:controlCondicion>
                {
                    for $ConsultarEventoCondicionOutput in $ConsultarEventoCondicionOutputCollection/ns11:ConsultarEventoCondicionOutput
                     where fn:data($consultarCondicionByRolOutput/ns1:ID)=fn:data($ConsultarEventoCondicionOutput/ns11:ID_CONDICION)
                    return 
                    <con:eventoCondicion>
                        <cat:Id>{fn:data($ConsultarEventoCondicionOutput/ns11:ID)}</cat:Id>
                        <cat:Descripcion>{fn:data($ConsultarEventoCondicionOutput/ns11:DESCRIPCION)}</cat:Descripcion>
                        <cat:DescripcionCorta>{fn:data($ConsultarEventoCondicionOutput/ns11:DESCRIPCION_CORTA)}</cat:DescripcionCorta>
                        <cat:estatus>{ if(string($ConsultarEventoCondicionOutput/ns11:BAN_ESTATUS)='') then false() else
                        if(fn:data($ConsultarEventoCondicionOutput/ns11:BAN_ESTATUS)=1) then true() else false()
                        }</cat:estatus>
                        <cat:codigoExterno>{fn:data($ConsultarEventoCondicionOutput/ns11:COD_EXTERNO)}</cat:codigoExterno></con:eventoCondicion>
                }
             
                <con:tipoFechaInicio>
                    <cat:Id>{fn:data($consultarCondicionByRolOutput/ns1:ID_TCA_TIPO_FECHA_INICIO)}</cat:Id>
                    <cat:Descripcion>{fn:data($consultarCondicionByRolOutput/ns1:DESCRIPCION_FI)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($consultarCondicionByRolOutput/ns1:DESCRIPCION_CORTA_FI)}</cat:DescripcionCorta>
                    <cat:estatus>{if(string($consultarCondicionByRolOutput/ns1:BAN_ESTATUS_FI)='') then false() else 
                   if( fn:data($consultarCondicionByRolOutput/ns1:BAN_ESTATUS_FI)=1) then true() else false()
                    }</cat:estatus>
                    <cat:codigoExterno>{fn:data($consultarCondicionByRolOutput/ns1:COD_EXTERNO_FI)}</cat:codigoExterno>
                </con:tipoFechaInicio>
                <con:fechaInicio>{fn:data($consultarCondicionByRolOutput/ns1:FECHA_INICIO)}</con:fechaInicio>
                <con:plazo>{fn:data($consultarCondicionByRolOutput/ns1:PLAZO)}</con:plazo>
                <con:frecuenciaPlazo>
                    <cat:Id>{fn:data($consultarCondicionByRolOutput/ns1:ID_TCA_FRECUENCIA_PLAZO)}</cat:Id>
                    <cat:Descripcion>{fn:data($consultarCondicionByRolOutput/ns1:DESCRIPCION_TF)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($consultarCondicionByRolOutput/ns1:DESCRIPCION_CORTA_TF)}</cat:DescripcionCorta>
                    <cat:estatus>{ if(string($consultarCondicionByRolOutput/ns1:BAN_ESTATUS_TF)='') then false () else
                   if( fn:data($consultarCondicionByRolOutput/ns1:BAN_ESTATUS_TF)=1) then true() else false()
                    }</cat:estatus>
                    <cat:codigoExterno>{fn:data($consultarCondicionByRolOutput/ns1:COD_EXTERNO_TF)}</cat:codigoExterno>
                </con:frecuenciaPlazo>
                <con:fechaFinal>{fn:data($consultarCondicionByRolOutput/ns1:FECHA_FINAL)}</con:fechaFinal>
                <con:estadoTCC>
                    <atr:id>{fn:data($consultarCondicionByRolOutput/ns1:ID_TCA_ESTADO_TCC)}</atr:id>
                    <atr:descripcion>{fn:data($consultarCondicionByRolOutput/ns1:DESCRIPCION_EST)}</atr:descripcion>
                    <atr:descripcionCorta>{fn:data($consultarCondicionByRolOutput/ns1:DESCRIPCION_CORTA_EST)}</atr:descripcionCorta>
                    <atr:estatus>{ if(string($consultarCondicionByRolOutput/ns1:BAN_ESTATUS_EST)='') then false() else
                    if(fn:data($consultarCondicionByRolOutput/ns1:BAN_ESTATUS_EST)=1) then true() else false()
                    }</atr:estatus>
                    <atr:codigoExterno>{fn:data($consultarCondicionByRolOutput/ns1:COD_EXTERNO_EST)}</atr:codigoExterno>
                    <atr:esSubEstado>{
                    if (string($consultarCondicionByRolOutput/ns1:ES_SUBESTADO_EST)='') then false() else                     
                  if(  fn:data($consultarCondicionByRolOutput/ns1:ES_SUBESTADO_EST)=1) then true() else false ()
                    
                    }</atr:esSubEstado>
                </con:estadoTCC>
                <con:fechaRegistro>{fn:data($consultarCondicionByRolOutput/ns1:CD_FECHA_REGISTRO)}</con:fechaRegistro>
                <con:estado>{ if(string($consultarCondicionByRolOutput/ns1:CD_BAN_ESTATUS)='') then false() else 
                if(fn:data($consultarCondicionByRolOutput/ns1:CD_BAN_ESTATUS)=1) then true() else false()
                }</con:estado>
                <con:condicionEnmendada>{fn:data($consultarCondicionByRolOutput/ns1:ID_CONDICION_ENMENDADA)}</con:condicionEnmendada>
                <con:fechaEnmienda>{fn:data($consultarCondicionByRolOutput/ns1:FECHA_ENMIENDA)}</con:fechaEnmienda>
                {
                    for $ConsultarAtributoByCondicionOutput in $ConsultarAtributoByCondicionOutputCollection/ns3:ConsultarAtributoByCondicionOutput
                    where  fn:data($ConsultarAtributoByCondicionOutput/ns3:ID_TCA_CONDICION)=fn:data($consultarCondicionByRolOutput/ns1:ID_TCA_CONDICION)
                    return 
                    <con:configAtributo>
                        <atr:id>{fn:data($ConsultarAtributoByCondicionOutput/ns3:ID)}</atr:id>
                        <atr:columna>{fn:data($ConsultarAtributoByCondicionOutput/ns3:COLUMNA)}</atr:columna>
                        <atr:ordenamiento>{fn:data($ConsultarAtributoByCondicionOutput/ns3:ORDENAMIENTO)}</atr:ordenamiento>
                        <atr:soloLectura>{ if (string($ConsultarAtributoByCondicionOutput/ns3:SOLO_LECTURA)='') then false () else 
                       if( fn:data($ConsultarAtributoByCondicionOutput/ns3:SOLO_LECTURA)=1)then  true() else false()
                       }</atr:soloLectura>
                        <atr:esObligatorio>{fn:data($ConsultarAtributoByCondicionOutput/ns3:ES_OBLIGATORIO)}</atr:esObligatorio>
                        <atr:etiqueta>{fn:data($ConsultarAtributoByCondicionOutput/ns3:ETIQUETA)}</atr:etiqueta>
                        {
                         for $Row in $valoresOutputParameters/ns7:RECORDSET/ns7:Row
                         where string($Row/ns7:Column[@name="ID"])=string($consultarCondicionByRolOutput/ns1:ID)
                            and fn:data($Row/ns7:Column[@name="COLUMNA"])=fn:data($ConsultarAtributoByCondicionOutput/ns3:COLUMNA)
                         return
                         
                        <atr:valor>{fn:data($Row/ns7:Column[@name="VALOR"])}</atr:valor>
                        }
                        <atr:tipoValor>{fn:data($ConsultarAtributoByCondicionOutput/ns3:TIPO_VALOR)}</atr:tipoValor>
                        {
                            for $Row in $valoresOutputParameters/ns7:RECORDSET/ns7:Row
                            where string($Row/ns7:Column[@name="ID"])=string($consultarCondicionByRolOutput/ns1:ID)
                            and fn:data($Row/ns7:Column[@name="COLUMNA"])=fn:data($ConsultarAtributoByCondicionOutput/ns3:COLUMNA)
                            and string($Row/ns7:Column[@name="ID_CAT"])!=''
                            return 
                            <atr:catalogo>
                                <cat:Id>{fn:data($Row/ns7:Column[@name="ID_CAT"])}</cat:Id>
                                <cat:Descripcion>{fn:data($Row/ns7:Column[@name="DESCRIPCION"])}</cat:Descripcion>
                                <cat:DescripcionCorta>{fn:data($Row/ns7:Column[@name="DESCRIPCION_CORTA"])}</cat:DescripcionCorta>
                                <cat:codigoExterno></cat:codigoExterno>
                            </atr:catalogo>
                        }</con:configAtributo>
                }
                {
                    for $ConsultarLineaCreditoByCondicionOutput in $ConsultarLineaCreditoByCondicionOutputCollection/ns4:ConsultarLineaCreditoByCondicionOutput
                   where fn:data($consultarCondicionByRolOutput/ns1:ID)=fn:data($ConsultarLineaCreditoByCondicionOutput/ns4:ID_CONDICION)
                    return 
                    <con:lineaCredito>
                        <atr:id>{fn:data($ConsultarLineaCreditoByCondicionOutput/ns4:ID)}</atr:id>
                        <atr:descripcion>{fn:data($ConsultarLineaCreditoByCondicionOutput/ns4:DESCRIPCION_LINEA)}</atr:descripcion>
                        <atr:estatus>{ if(string($ConsultarLineaCreditoByCondicionOutput/ns4:BAN_ESTATUS)='')then false() else 
                        if(fn:data($ConsultarLineaCreditoByCondicionOutput/ns4:BAN_ESTATUS)=1) then true() else false()
                        }</atr:estatus></con:lineaCredito>
                }
                {
                    for $ConsultarFuenteByCondicionOutput in $ConsultarFuenteByCondicionOutputCollection/ns5:ConsultarFuenteByCondicionOutput
                   where fn:data($consultarCondicionByRolOutput/ns1:ID)=fn:data($ConsultarFuenteByCondicionOutput/ns5:ID_CONDICION)
                    return 
                    <con:fuente>
                        <atr:id>{fn:data($ConsultarFuenteByCondicionOutput/ns5:ID)}</atr:id>
                        <atr:descripcion>{fn:data($ConsultarFuenteByCondicionOutput/ns5:DESCRIPCION)}</atr:descripcion>
                        <atr:estatus>{if (string ($ConsultarFuenteByCondicionOutput/ns5:BAN_ESTATUS)='') then false () else
                       if( fn:data($ConsultarFuenteByCondicionOutput/ns5:BAN_ESTATUS)=1) then true() else false()
                        }</atr:estatus></con:fuente>
                }</ns8:Condicion>
        }
        <ns8:Resultado>
            <res:result>OK</res:result>
            {
            if (string($consultarCondicionByRolOutputCollection/ns1:consultarCondicionByRolOutput/ns1:ID)='') then
            <res:message>No existen registros</res:message>
            else
            <res:message>Consulta realizada existosamente</res:message>
           }
        </ns8:Resultado>
    </ns8:ConsultarCondicionByRolResponse>
};

local:func($consultarCondicionByRolOutputCollection, $ConsultarCategoriaByCondicionOutputCollection, $ConsultarAtributoByCondicionOutputCollection, $ConsultarLineaCreditoByCondicionOutputCollection, $ConsultarFuenteByCondicionOutputCollection, $ConsultarValidadoresbyCategoriaOutputCollection, $valoresOutputParameters, $ConsultarEventoCondicionOutputCollection)