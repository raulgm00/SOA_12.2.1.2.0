xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarReglasDesembolso_DB";
(:: import schema at "../XSD/ConsultarReglasDesembolso_DB.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace reg1 = "http://www.bcie.org/ReglaTareaBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarReglasDesembolso_DBOutputCollection as element() (:: schema-element(ns1:ConsultarReglasDesembolso_DBOutputCollection) ::) external;

declare function local:func($ConsultarReglasDesembolso_DBOutputCollection as element() (:: schema-element(ns1:ConsultarReglasDesembolso_DBOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarReglasDesembolsoResponse) ::) {
    <ns2:ConsultarReglasDesembolsoResponse>
    {
    for $treReglasDesembolso in $ConsultarReglasDesembolso_DBOutputCollection/ns1:ConsultarReglasDesembolso_DBOutput
    return
        <ns2:ReglasEvaluacion>
            <reg:Id>{fn:data($treReglasDesembolso/ns1:ID_REGLA)}</reg:Id>
            <reg:Descripcion>{fn:data($treReglasDesembolso/ns1:DESCRIPCION_REGLA)}</reg:Descripcion>
            <reg:Transaccion>{fn:data($treReglasDesembolso/ns1:TRANSACCION)}</reg:Transaccion>
            <reg:Tipo>
                <cat:Id></cat:Id>
                <cat:Descripcion>{fn:data($treReglasDesembolso/ns1:TIPO)}</cat:Descripcion>
                <cat:DescripcionCorta></cat:DescripcionCorta>
                <cat:estatus></cat:estatus>
                <cat:codigoExterno></cat:codigoExterno>
            </reg:Tipo>
            <reg:PosibleExceptuar>{
            if (fn:data($treReglasDesembolso/ns1:SE_PUEDE_EXCEPTUAR) = 1) then true()
            else false()}</reg:PosibleExceptuar>
            <reg:Exceptuado>
                <cat:Id>{fn:data($treReglasDesembolso/ns1:ID_TRE_REGLA_DESEMBOLSO)}</cat:Id>
                <cat:Descripcion>TRE_REGLA_DESEMBOLSO</cat:Descripcion>
                <cat:DescripcionCorta></cat:DescripcionCorta>
                <cat:estatus>{
                if (fn:data($treReglasDesembolso/ns1:EXCEPTUADO)= 1) then true()
                else false()}</cat:estatus>
                <cat:codigoExterno>{fn:data($treReglasDesembolso/ns1:USUARIOEXCEPTUA)}</cat:codigoExterno>
            </reg:Exceptuado>
            <reg:UsuarioExceptua>{fn:data($treReglasDesembolso/ns1:USUARIOEXCEPTUA)}</reg:UsuarioExceptua>
            <reg:FechaExceptua>{fn:data($treReglasDesembolso/ns1:FECHA_EXCEPCION)}</reg:FechaExceptua>  
        </ns2:ReglasEvaluacion>
         }
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>{
            if (count($ConsultarReglasDesembolso_DBOutputCollection/ns1:ConsultarReglasDesembolso_DBOutput)>0)
            then 'Consulta exitosa'
            else 'No hay registros'
            }</res:message>
        </ns2:Resultado>
    </ns2:ConsultarReglasDesembolsoResponse>
};

local:func($ConsultarReglasDesembolso_DBOutputCollection)
