xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $ObtenerInformeCondicionesResponse as element() (:: schema-element(ns1:ObtenerInformeCondicionesResponse) ::) external;

declare function local:func($ObtenerInformeCondicionesResponse as element() (:: schema-element(ns1:ObtenerInformeCondicionesResponse) ::)) as element() (:: schema-element(ns1:CrearReporteCumplimientoCondicionRequest) ::) {
    <ns1:CrearReporteCumplimientoCondicionRequest>
        <ns1:ListaCondicion>
        {for $condicion in $ObtenerInformeCondicionesResponse/ns1:InformeEstado/con:Condicion
          return
            <con:Condicion>
                {$condicion/*}
            </con:Condicion>
            }
        </ns1:ListaCondicion>
    </ns1:CrearReporteCumplimientoCondicionRequest>
};

local:func($ObtenerInformeCondicionesResponse)
