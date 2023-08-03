xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ObtenerURLMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/Utilidades/ObtenerURL/V1/Schema/ObtenerURLMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/PrepagoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Prepago/V1/Schema/PrepagoMO.xsd" ::)

declare namespace cor = "http://www.bcie.org/CorreoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace pre = "http://www.bcie.org/PrepagoBO";

declare variable $PlanArmotizacionRequest as element() (:: schema-element(ns1:PlanArmotizacionRequest) ::) external;
declare variable $ExtraerReportePrepagoResponse as element() (:: schema-element(ns1:ExtraerReportePrepagoResponse) ::) external;

declare function local:func($PlanArmotizacionRequest as element() (:: schema-element(ns1:PlanArmotizacionRequest) ::), 
                            $ExtraerReportePrepagoResponse as element() (:: schema-element(ns1:ExtraerReportePrepagoResponse) ::)) 
                            as element() (:: schema-element(ns2:ObtenerURLRequest) ::) {
    <ns2:ObtenerURLRequest>
        <ns2:URL>{fn:data($PlanArmotizacionRequest/ns1:URL)}</ns2:URL>
        {for $lineas in $ExtraerReportePrepagoResponse/ns1:Reporte/pre:CondicionesEspeciales
        return
        <ns2:Tags>
            <cor:tag>{'Linea'}</cor:tag>
            <cor:valor>{fn:data($lineas/pre:Linea/lin:NumeroLineaCredito)}</cor:valor>
        </ns2:Tags>
        }
    </ns2:ObtenerURLRequest>
};

local:func($PlanArmotizacionRequest, $ExtraerReportePrepagoResponse)
