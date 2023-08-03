xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CrearActualizarFuentes_BD";
(:: import schema at "../XSD/CrearActualizarFuentes_BD_sp.xsd" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare variable $CrearActualizarFuentesRequest as element() (:: schema-element(ns1:CrearActualizarFuentesRequest) ::) external;

declare function local:func($CrearActualizarFuentesRequest as element() (:: schema-element(ns1:CrearActualizarFuentesRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_FUENTES>
        {
        for $fuente in $CrearActualizarFuentesRequest/ns1:Fuentes
        return
            <ns2:P_FUENTES_ITEM>
                <ns2:ID>{fn:data($fuente/lin:idFuente)}</ns2:ID>
                {
                if ($fuente/lin:idLineaCredito)then
                <ns2:ID_LINEA_CREDITO>{fn:data($fuente/lin:idLineaCredito)}</ns2:ID_LINEA_CREDITO>
                else()}
                {
                if ($fuente/lin:idContrato)then
                <ns2:ID_CONTRATO>{fn:data($fuente/lin:idContrato)}</ns2:ID_CONTRATO>
                else()}
                {
                if ($fuente/lin:idLineaPasiva)then
                <ns2:ID_LINEA_PASIVA>{fn:data($fuente/lin:idLineaPasiva)}</ns2:ID_LINEA_PASIVA>
                else()}
                {
                if ($fuente/lin:Descripcion) then
                <ns2:DESCRIPCION>{fn:data($fuente/lin:Descripcion)}</ns2:DESCRIPCION>
                else()}
                {
                if ($fuente/lin:FechaObtenido)then
                <ns2:FECHA_OBTENIDO>{fn:data($fuente/lin:FechaObtenido)}</ns2:FECHA_OBTENIDO>
                else()}
                {
                if ($fuente/lin:MontoAsignado)then
                <ns2:MONTO_ASIGNADO>{fn:data($fuente/lin:MontoAsignado)}</ns2:MONTO_ASIGNADO>
                else()}
                {
                if ($fuente/lin:NumeroAsignacion)then
                <ns2:NUMERO_ASIGNACION>{fn:data($fuente/lin:NumeroAsignacion)}</ns2:NUMERO_ASIGNACION>
                else()}
                {
                if ($fuente/lin:Comentario) then
                <ns2:COMENTARIO>{fn:data($fuente/lin:Comentario)}</ns2:COMENTARIO>
                else()}
                {
                if($fuente/lin:Estado)then
                <ns2:BAN_ESTATUS>{
                if ($fuente/lin:Estado = true() )
                then 1
                else 0}</ns2:BAN_ESTATUS>
                else()}
            </ns2:P_FUENTES_ITEM>
        }
        </ns2:P_FUENTES>
    </ns2:InputParameters>
};

local:func($CrearActualizarFuentesRequest)
