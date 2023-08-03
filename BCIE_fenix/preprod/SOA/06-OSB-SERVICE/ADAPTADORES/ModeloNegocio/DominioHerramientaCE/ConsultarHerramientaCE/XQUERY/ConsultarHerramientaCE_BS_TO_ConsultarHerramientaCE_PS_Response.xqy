xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/HerramientaCEMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioHerramientaCE/HerramientaCE/V1/Schema/HerramientaCEMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarHerramientaCE_DB";
(:: import schema at "../XSD/ConsultarHerramientaCE_DB_sp.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ConsultarHerramientaCEResponse) ::) {
    <ns2:ConsultarHerramientaCEResponse>
        <ns2:HerramientaCE>
        {
        if (count($OutputParameters/ns1:RECORDSET_ACTIVIDAD/ns1:Row)>0)then
                for $actividad in $OutputParameters/ns1:RECORDSET_ACTIVIDAD/ns1:Row
                return  
                <her:ActividadEconomica>
                    <cat:Id>{fn:data($actividad/ns1:Column[@name='ID_ACTIVIDAD'])}</cat:Id>
                    <cat:Descripcion>{fn:data($actividad/ns1:Column[@name='DESCRIPCION_ACTIVIDAD'])}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($actividad/ns1:Column[@name='DESCRIPCION_CORTA_ACTIVIDAD'])}</cat:DescripcionCorta>
                    <cat:estatus>{
                    if(fn:data($actividad/ns1:Column[@name='BAN_ESTATUS_ACTIVIDAD']) = '1')then true()
                    else false()}</cat:estatus>
                    <cat:codigoExterno>{fn:data($actividad/ns1:Column[@name='COD_EXTERNO_ACTIVIDAD'])}</cat:codigoExterno>
                </her:ActividadEconomica>
            else()
            }
            {
            if (count($OutputParameters/ns1:RECORDSET_EJE/ns1:Row)>0)then
                for $eje in $OutputParameters/ns1:RECORDSET_EJE/ns1:Row
                return 
                <her:EjeEstrategico>
                    <cat:Id>{fn:data($eje/ns1:Column[@name='ID_EJE'])}</cat:Id>
                    <cat:Descripcion>{fn:data($eje/ns1:Column[@name='DESCRIPCION_EJE'])}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($eje/ns1:Column[@name='DESCRIPCION_CORTA_EJE'])}</cat:DescripcionCorta>
                    <cat:estatus>{
                    if (fn:data($eje/ns1:Column[@name='BAN_ESTATUS_EJE'])= '1') then true()
                    else false()}</cat:estatus>
                    <cat:codigoExterno>{fn:data($eje/ns1:Column[@name='COD_EXTERNO_EJE'])}</cat:codigoExterno>
                </her:EjeEstrategico>
            else()}
            {
            if (count($OutputParameters/ns1:RECORDSET_AREA/ns1:Row)>0)then
                for $area in $OutputParameters/ns1:RECORDSET_AREA/ns1:Row
                return 
                <her:AreaFocalizacion>
                    <cat:Id>{fn:data($area/ns1:Column[@name='ID_AREA'])}</cat:Id>
                    <cat:Descripcion>{fn:data($area/ns1:Column[@name='DESCRIPCION_AREA'])}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($area/ns1:Column[@name='DESCRIPCION_CORTA_AREA'])}</cat:DescripcionCorta>
                    <cat:estatus>{
                    if (fn:data($area/ns1:Column[@name='BAN_ESTATUS_AREA'])='1') then true()
                    else false()}</cat:estatus>
                    <cat:codigoExterno>{fn:data($area/ns1:Column[@name='COD_EXTERNO_AREA'])}</cat:codigoExterno>
                </her:AreaFocalizacion>
            else()
          }
        </ns2:HerramientaCE>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>{if (count($OutputParameters/ns1:RECORDSET_ACTIVIDAD/ns1:Row)= 0 and count($OutputParameters/ns1:RECORDSET_EJE/ns1:Row)=0
            and count($OutputParameters/ns1:RECORDSET_AREA/ns1:Row)= 0) then 'No hay registros' else 'Consulta exitosa'}</res:message>
        </ns2:Resultado>
    </ns2:ConsultarHerramientaCEResponse>
};

local:func($OutputParameters)
