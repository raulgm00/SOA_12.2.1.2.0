xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ObtenerCuestionaroElegibilidadV2db";
(:: import schema at "../XSD/ObtenerCuestionaroElegibilidadV2db_sp.xsd" ::)

declare variable $reporteElegibilidad as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($reporteElegibilidad as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns1:any) ::) {
  

    <ns1:any>
         {            
            for $row in $reporteElegibilidad/ns1:P_LISTA_CUESTIONARIO/ns1:Row
            return
                fn-bea:inlinedXML(fn:data($row/ns1:Column[@name ='XML']))
         }
    </ns1:any>
};

local:func($reporteElegibilidad)
