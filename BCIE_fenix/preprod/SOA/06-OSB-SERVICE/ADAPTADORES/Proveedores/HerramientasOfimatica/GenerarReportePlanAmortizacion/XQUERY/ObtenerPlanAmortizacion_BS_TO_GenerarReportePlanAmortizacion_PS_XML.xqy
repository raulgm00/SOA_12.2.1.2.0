xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ObtenerPlanAmortizacion";
(:: import schema at "../XSD/ObtenerPlanAmortizacion_sp.xsd" ::)

declare variable $planAmortizacion as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($planAmortizacion as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns1:any) ::) {
    <ns1:any>
         {            
            for $row in $planAmortizacion/ns1:P_PLAN_AMORTIZACION/ns1:Row
            return
                fn-bea:inlinedXML(fn:data($row/ns1:Column[@name ='XML']))
         }
    </ns1:any>
};

local:func($planAmortizacion)
