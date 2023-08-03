xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CorreoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarPlantillaByProceso";
(:: import schema at "../XSD/ConsultarPlantillaByProceso.xsd" ::)

declare namespace cor = "http://www.bcie.org/CorreoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $ConsultarPlantillaByProcesoOutputCollection as element() (:: schema-element(ns1:ConsultarPlantillaByProcesoOutputCollection) ::) external;

declare function local:func($ConsultarPlantillaByProcesoOutputCollection as element() (:: schema-element(ns1:ConsultarPlantillaByProcesoOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarPlantillaByProcesoResponse) ::) {
    <ns2:ConsultarPlantillaByProcesoResponse>
       
        
        <!--ns2:listaUsuarios>
        {
        for $usuario in $ConsultarPlantillaByProcesoOutputCollection/ns1:ConsultarPlantillaByProcesoOutput
        return
            <cor:usuario>{fn:data($usuario/ns1:LOGIN_USUARIO)}</cor:usuario>
        }
        </ns2:listaUsuarios-->
        {
        if (string($ConsultarPlantillaByProcesoOutputCollection/ns1:ID)!='') then
        <ns2:ProcesoEventoPlantilla>
            <cor:id>{fn:data($ConsultarPlantillaByProcesoOutputCollection/ns1:ID)}</cor:id>
            <cor:descripcion>{fn:data($ConsultarPlantillaByProcesoOutputCollection/ns1:DESCRIPCION)}</cor:descripcion>
            <cor:remitente>{fn:data($ConsultarPlantillaByProcesoOutputCollection/ns1:REMITENTE)}</cor:remitente>
            <cor:mensaje>{fn:data($ConsultarPlantillaByProcesoOutputCollection/ns1:MENSAJE)}</cor:mensaje>
            <cor:asunto>{fn:data($ConsultarPlantillaByProcesoOutputCollection/ns1:ASUNTO)}</cor:asunto>
            <cor:destinatarios>{fn:data($ConsultarPlantillaByProcesoOutputCollection/ns1:DESTINATARIOS)}</cor:destinatarios>
            <cor:fecha_registro>{fn:data($ConsultarPlantillaByProcesoOutputCollection/ns1:FECHA_REGISTRO)}</cor:fecha_registro>
            <cor:estado>{fn:data($ConsultarPlantillaByProcesoOutputCollection/ns1:BAN_ESTATUS)}</cor:estado>
            <cor:cc_destinatarios>{fn:data($ConsultarPlantillaByProcesoOutputCollection/ns1:CC_DESTINATARIOS)}</cor:cc_destinatarios>
            <cor:categoriaEvento>
                <cat:Id>{fn:data($ConsultarPlantillaByProcesoOutputCollection/ns1:ID_TCA_CATEGORIA_EVENTO)}</cat:Id>
            </cor:categoriaEvento>
            <cor:banValidaTags>{fn:data($ConsultarPlantillaByProcesoOutputCollection/ns1:BAN_VALIDA_TAGS)}</cor:banValidaTags>
            <cor:banValidaET>{fn:data($ConsultarPlantillaByProcesoOutputCollection/ns1:BAN_VALIDA_ET)}</cor:banValidaET>
            <cor:banValidaMCC>{fn:data($ConsultarPlantillaByProcesoOutputCollection/ns1:BAN_VALIDA_MCC)}</cor:banValidaMCC>
            <cor:banValidaVA>{fn:data($ConsultarPlantillaByProcesoOutputCollection/ns1:BAN_VALIDA_VA)}</cor:banValidaVA>
            <cor:banValidaRA>{fn:data($ConsultarPlantillaByProcesoOutputCollection/ns1:BAN_VALIDA_RA)}</cor:banValidaRA>
            <cor:banValidaRO>{fn:data($ConsultarPlantillaByProcesoOutputCollection/ns1:BAN_VALIDA_RO)}</cor:banValidaRO>
        </ns2:ProcesoEventoPlantilla>
        else()
        }
        <ns2:Resultado>
        {
        if (string($ConsultarPlantillaByProcesoOutputCollection/ns1:ID)!='')
        then
            <res:result>OK</res:result>
           
        else 
            <res:result>Error</res:result>
        }
         {
        if (string($ConsultarPlantillaByProcesoOutputCollection/ns1:ID)!='')
        then
           <res:message>La consulta se ha realizado exitosamente</res:message>
           
        else 
           <res:message>No existen registros</res:message>
        }
        </ns2:Resultado>
    </ns2:ConsultarPlantillaByProcesoResponse>
};

local:func($ConsultarPlantillaByProcesoOutputCollection)
