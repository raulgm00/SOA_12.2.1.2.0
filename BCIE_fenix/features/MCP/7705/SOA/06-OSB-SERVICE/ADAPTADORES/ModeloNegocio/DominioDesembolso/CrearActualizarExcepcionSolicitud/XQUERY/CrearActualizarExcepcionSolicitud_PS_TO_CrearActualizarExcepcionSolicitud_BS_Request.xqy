xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CrearActualizarExcepcionSolicitud_DB";
(:: import schema at "../XSD/CrearActualizarExcepcionSolicitud_DB_sp.xsd" ::)

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare variable $CrearActualizarExcepcionSolicitudRequest as element() (:: schema-element(ns1:CrearActualizarExcepcionSolicitudRequest) ::) external;

declare function local:func($CrearActualizarExcepcionSolicitudRequest as element() (:: schema-element(ns1:CrearActualizarExcepcionSolicitudRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {


    <ns2:InputParameters>

        <ns2:P_LISTA_EXCEPCION_S>
            {
    for $excepcion in $CrearActualizarExcepcionSolicitudRequest/ns1:Excepcion
    return
            <ns2:P_LISTA_EXCEPCION_S_ITEM>
                <ns2:ID>{fn:data($excepcion/reg:Id)}</ns2:ID>
                {if($excepcion/reg:Exceptuado/cat:estatus)then
                <ns2:EXCEPTUADO>{
                if (fn:data($excepcion/reg:Exceptuado/cat:estatus) = true()) then 1 else 0}</ns2:EXCEPTUADO>
                else()
                }
                {
                if ($excepcion/des:instanciaProceso)then
                <ns2:INSTANCIA_PROCESO>{fn:data($excepcion/des:instanciaProceso)}</ns2:INSTANCIA_PROCESO>
                else()
                }
                {
                if($excepcion/des:enProcesoExcepcion)then
                <ns2:EN_PROCESO_EXCEPCION>{
                if (fn:data($excepcion/des:enProcesoExcepcion) = true())then 1 else 0}</ns2:EN_PROCESO_EXCEPCION>
                else()
                }
            </ns2:P_LISTA_EXCEPCION_S_ITEM>
            }
        </ns2:P_LISTA_EXCEPCION_S>
    </ns2:InputParameters>
    
};

local:func($CrearActualizarExcepcionSolicitudRequest)
