xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ProductoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioProducto/Producto/V1/Schema/ProductoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ObtenerTareasProducto_BS";
(:: import schema at "../XSD/ObtenerTareasProducto_BS_sp.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ObtenerTareasProductoResponse as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($ObtenerTareasProductoResponse as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ObtenerTareasProductoResponse) ::) {
    <ns2:ObtenerTareasProductoResponse>
        <ns2:ListadoTareas>
        {
          for $VarObtenerTareasProducto in $ObtenerTareasProductoResponse/ns1:RS_TAREAS/ns1:Row
          return
            <pro:listadoTareas>
                <cat:idTarea>{fn:data($VarObtenerTareasProducto/ns1:Column[@name='ID_TAREA'])}</cat:idTarea>
                <cat:descripcion>{fn:data($VarObtenerTareasProducto/ns1:Column[@name='DESCRIPCION'])}</cat:descripcion>
                <cat:descripcionCorta>{fn:data($VarObtenerTareasProducto/ns1:Column[@name='DESCRIPCION_CORTA'])}</cat:descripcionCorta>
                <cat:idProcesoBpm>{fn:data($VarObtenerTareasProducto/ns1:Column[@name='ID_PROCESO_BPM'])}</cat:idProcesoBpm>
            </pro:listadoTareas>
        }
        </ns2:ListadoTareas>
        <ns2:Result>
            <res:result>{fn:data($ObtenerTareasProductoResponse/ns1:P_CODIGO_RES)}</res:result>
            <res:message>{fn:data($ObtenerTareasProductoResponse/ns1:P_CODIGO_RES)}</res:message>
        </ns2:Result>
    </ns2:ObtenerTareasProductoResponse>
};

local:func($ObtenerTareasProductoResponse)
