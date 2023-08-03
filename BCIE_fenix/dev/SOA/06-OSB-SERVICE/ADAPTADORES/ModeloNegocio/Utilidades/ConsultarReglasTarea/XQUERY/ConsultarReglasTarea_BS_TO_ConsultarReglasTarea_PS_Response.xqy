xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ReglaTareaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ReglaTarea/V1/Schema/ReglaTareaMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarReglasTarea_DB";
(:: import schema at "../XSD/ConsultarReglasTarea_DB.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace reg = "http://www.bcie.org/ReglaTareaBO";

declare namespace reg1 = "http://www.bcie.org/ReglaBO";

declare variable $ConsultarReglasTarea_DBOutputCollection as element() (:: schema-element(ns1:ConsultarReglasTarea_DBOutputCollection) ::) external;

declare function local:func($ConsultarReglasTarea_DBOutputCollection as element() (:: schema-element(ns1:ConsultarReglasTarea_DBOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarReglasTareaResponse) ::) {
    <ns2:ConsultarReglasTareaResponse>
    {
    for $idTarea in fn:distinct-values($ConsultarReglasTarea_DBOutputCollection/ns1:ConsultarReglasTarea_DBOutput/ns1:ID_TAREA)
    let $tarea := $ConsultarReglasTarea_DBOutputCollection/ns1:ConsultarReglasTarea_DBOutput[ns1:ID_TAREA = $idTarea][1]
    return
    
        <ns2:TareaReglas>
            <reg:IdTarea>{fn:data($tarea/ns1:ID_TAREA)}</reg:IdTarea>
            <reg:Proceso>{fn:data($tarea/ns1:ID_PROCESO_BPM)}</reg:Proceso>
            <reg:Descripcion>{fn:data($tarea/ns1:DESCRIPCION_TAREA)}</reg:Descripcion>
            <reg:Estatus>{
            if (fn:data($tarea/ns1:BAN_ESTATUS_TAREA)= 1) then true()
            else false()}</reg:Estatus>
            {
            for $reglas in $ConsultarReglasTarea_DBOutputCollection/ns1:ConsultarReglasTarea_DBOutput
            where $reglas/ns1:TIPO eq $reglas/ns1:TIPO
            return
            <reg:ReglasEvaluacion>
                <reg1:Id>{fn:data($reglas/ns1:ID_REGLA)}</reg1:Id>
                <reg1:Descripcion>{fn:data($reglas/ns1:DESCRIPCION_REGLA)}</reg1:Descripcion>
                <reg1:Transaccion>{fn:data($reglas/ns1:TRANSACCION)}</reg1:Transaccion>
                <reg1:Tipo>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($reglas/ns1:TIPO)}</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </reg1:Tipo>
                <reg1:PosibleExceptuar>{
                if (fn:data($reglas/ns1:SE_PUEDE_EXCEPTUAR)= 1) then true()
                else false()}</reg1:PosibleExceptuar>
                <reg1:Exceptuado></reg1:Exceptuado>
                <reg1:Estado>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </reg1:Estado>
                <reg1:Estatus>{
                if (fn:data($reglas/ns1:BAN_ESTATUS_REGLA) = 1) then true()
                else false()}</reg1:Estatus>
                <reg:EsAdvertencia>{
                if (fn:data($reglas/ns1:ES_ADVERTENCIA) = 1) then true()
                else false()}</reg:EsAdvertencia>
                <reg:EsError>{
                if (fn:data($reglas/ns1:ES_ERROR) = 1)then true()
                else false()}</reg:EsError>
                <reg:Mensaje>
                    <cat:Id>{fn:data($reglas/ns1:ID_TCA_MENSAJE)}</cat:Id>
                    <cat:Descripcion>{fn:data($reglas/ns1:DESCRIPCION_MENSAJE)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($reglas/ns1:DESCRIPCION_CORTA_MENSAJE)}</cat:DescripcionCorta>
                    <cat:estatus>{
                    if (fn:data($reglas/ns1:BAN_ESTATUS_MENSAJE)=1) then true()
                    else false()}</cat:estatus>
                    <cat:codigoExterno>{fn:data($reglas/ns1:COD_EXTERNO_MENSAJE)}</cat:codigoExterno>
                </reg:Mensaje>
            </reg:ReglasEvaluacion>
            }
        </ns2:TareaReglas>
        }
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>{
            if (count($ConsultarReglasTarea_DBOutputCollection/ns1:ConsultarReglasTarea_DBOutput)> 0)
            then 'Consulta Exitosa'
            else 'No hay registros'
            }</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:ConsultarReglasTareaResponse>
};

local:func($ConsultarReglasTarea_DBOutputCollection)