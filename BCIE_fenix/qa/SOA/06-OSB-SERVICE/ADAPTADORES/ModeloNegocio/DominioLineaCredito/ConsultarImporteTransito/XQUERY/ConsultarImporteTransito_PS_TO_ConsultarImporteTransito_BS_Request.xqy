xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/Consultar_Importe_Transito_db";
(:: import schema at "../XSD/Consultar_Importe_Transito_db_sp.xsd" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $ConsultarImporteTransitoRequest as element() (:: schema-element(ns1:ConsultarImporteTransitoRequest) ::) external;

declare function local:func($ConsultarImporteTransitoRequest as element() (:: schema-element(ns1:ConsultarImporteTransitoRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
            <ns2:P_IDLINEA>{fn:data($ConsultarImporteTransitoRequest/ns1:LineaCredito/lin:idLineaCredito)}</ns2:P_IDLINEA>
        {
            if ($ConsultarImporteTransitoRequest/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)
            then <ns2:P_LINEA_FINANCIERA>{fn:data($ConsultarImporteTransitoRequest/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)}</ns2:P_LINEA_FINANCIERA>
            else ()
        }
        {
            if ($ConsultarImporteTransitoRequest/ns1:LineaCredito/lin:atributos/com:value)
            then <ns2:P_PAIS>{fn:data($ConsultarImporteTransitoRequest/ns1:LineaCredito/lin:atributos/com:value)}</ns2:P_PAIS>
            else ()
        }
        {
            if ($ConsultarImporteTransitoRequest/ns1:LineaCredito/lin:Monto/com:moneda/cat:codigoExterno)
            then <ns2:P_MONEDA>{fn:data($ConsultarImporteTransitoRequest/ns1:LineaCredito/lin:Monto/com:moneda/cat:codigoExterno)}</ns2:P_MONEDA>
            else ()
        }
        {
            if($ConsultarImporteTransitoRequest/ns1:LineaCredito/lin:FechaValor) then
            <ns2:P_FECHA_ESTIMADA>{fn:data($ConsultarImporteTransitoRequest/ns1:LineaCredito/lin:FechaValor)}</ns2:P_FECHA_ESTIMADA>
            else ()
        }  
    </ns2:InputParameters>
};

local:func($ConsultarImporteTransitoRequest)
