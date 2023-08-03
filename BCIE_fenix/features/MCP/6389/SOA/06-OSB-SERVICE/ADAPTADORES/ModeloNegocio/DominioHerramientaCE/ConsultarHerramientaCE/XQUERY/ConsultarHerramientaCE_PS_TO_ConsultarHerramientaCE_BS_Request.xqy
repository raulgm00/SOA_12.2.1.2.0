xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/HerramientaCEMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioHerramientaCE/HerramientaCE/V1/Schema/HerramientaCEMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarHerramientaCE_DB";
(:: import schema at "../XSD/ConsultarHerramientaCE_DB_sp.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $ConsultarHerramientaCERequest as element() (:: schema-element(ns1:ConsultarHerramientaCERequest) ::) external;

declare function local:func($ConsultarHerramientaCERequest as element() (:: schema-element(ns1:ConsultarHerramientaCERequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        {
            if ($ConsultarHerramientaCERequest/ns1:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)
            then <ns2:P_COD_EXTERNO_ACTIVIDAD>{fn:data($ConsultarHerramientaCERequest/ns1:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)}</ns2:P_COD_EXTERNO_ACTIVIDAD>
            else ()
        }
        {
            if ($ConsultarHerramientaCERequest/ns1:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)
            then <ns2:P_COD_EXTERNO_AREA>{fn:data($ConsultarHerramientaCERequest/ns1:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)}</ns2:P_COD_EXTERNO_AREA>
            else ()
        }
        {
            if ($ConsultarHerramientaCERequest/ns1:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)
            then <ns2:P_COD_EXTERNO_EJE>{fn:data($ConsultarHerramientaCERequest/ns1:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)}</ns2:P_COD_EXTERNO_EJE>
            else ()
        }

    </ns2:InputParameters>
};

local:func($ConsultarHerramientaCERequest)
