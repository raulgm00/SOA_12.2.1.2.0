xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ReglaTareaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ReglaTarea/V1/Schema/ReglaTareaMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarReglas_DB";
(:: import schema at "../XSD/ConsultarReglas_DB.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarReglas_DBOutputCollection as element() (:: schema-element(ns1:ConsultarReglas_DBOutputCollection) ::) external;

declare function local:func($ConsultarReglas_DBOutputCollection as element() (:: schema-element(ns1:ConsultarReglas_DBOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarReglasResponse) ::) {
    <ns2:ConsultarReglasResponse>
    {
    for $reglas in $ConsultarReglas_DBOutputCollection/ns1:ConsultarReglas_DBOutput
    return
        <ns2:Reglas>
            <reg:Id>{fn:data($reglas/ns1:ID)}</reg:Id>
            <reg:Descripcion>{fn:data($reglas/ns1:DESCRIPCION)}</reg:Descripcion>
            <reg:Transaccion>{fn:data($reglas/ns1:TRANSACCION)}</reg:Transaccion>
            <reg:Tipo>
                <cat:Id></cat:Id>
                <cat:Descripcion></cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($reglas/ns1:TIPO)}</cat:DescripcionCorta>
                <cat:estatus></cat:estatus>
                <cat:codigoExterno></cat:codigoExterno>
            </reg:Tipo>
            <reg:PosibleExceptuar>{
            if (fn:data($reglas/ns1:SE_PUEDE_EXCEPTUAR)= 1)then true()
            else false()}</reg:PosibleExceptuar>
            <reg:Exceptuado></reg:Exceptuado>
            <reg:Estado>
                <cat:Id></cat:Id>
                <cat:Descripcion></cat:Descripcion>
                <cat:DescripcionCorta></cat:DescripcionCorta>
                <cat:estatus></cat:estatus>
                <cat:codigoExterno></cat:codigoExterno>
            </reg:Estado>
            <reg:Estatus>{
            if (fn:data($reglas/ns1:BAN_ESTATUS) = 1) then true()
            else false()}</reg:Estatus>
        </ns2:Reglas>
        }
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>{
            if (count($ConsultarReglas_DBOutputCollection/ns1:ConsultarReglas_DBOutput) > 0)
            then 'Consulta Exitosa'
            else 'No existen registros'
            }</res:message>
        </ns2:Resultado>
    </ns2:ConsultarReglasResponse>
};

local:func($ConsultarReglas_DBOutputCollection)