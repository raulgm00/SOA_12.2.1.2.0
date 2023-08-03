xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ObtenerCumplimientoCondiciones";
(:: import schema at "../XSD/ObtenerCumplimientoCondiciones_sp.xsd" ::)

declare variable $ObtenerCumplimientoCondiciones as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($ObtenerCumplimientoCondiciones as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns1:any) ::) {
    <ns1:any>
         {            
            for $row in $ObtenerCumplimientoCondiciones/ns1:P_CUMPLIMIENTO_CONDICIONES/ns1:Row
            return
                fn-bea:inlinedXML(fn:data($row/ns1:Column[@name ='XML']))
         }
    </ns1:any>
};

local:func($ObtenerCumplimientoCondiciones)
