xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare variable $CrearLineaCredito_Input.CrearLineaCreditoIn as element() (:: schema-element(lin:CrearLineaCreditoRequest) ::) external;

declare function local:funcCrearlineascreditoinput_to_crearlineascreditorquest($CrearLineaCredito_Input.CrearLineaCreditoIn as element() (:: schema-element(lin:CrearLineaCreditoRequest) ::)) as element() (:: schema-element(lin:CrearLineaCreditoRequest) ::) {
    <lin:CrearLineaCreditoRequest>
        {
    for $LineaCredito in $CrearLineaCredito_Input.CrearLineaCreditoIn/lin:LineaCredito
    return
        <lin:LineaCredito>{$LineaCredito/*}</lin:LineaCredito>
        }
    </lin:CrearLineaCreditoRequest>
};

local:funcCrearlineascreditoinput_to_crearlineascreditorquest($CrearLineaCredito_Input.CrearLineaCreditoIn)
