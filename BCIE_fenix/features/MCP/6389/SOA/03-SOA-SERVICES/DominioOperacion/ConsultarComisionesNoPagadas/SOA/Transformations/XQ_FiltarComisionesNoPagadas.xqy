xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace com="http://www.bcie.org/ComisionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com2 = "http://www.bcie.org/CommonBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare variable $outConsultarComision.response as element() (:: schema-element(com:ConsultarComisionResponse) ::) external;

declare function local:funcXq_filtarcomisionesnopagadas($outConsultarComision.response as element() (:: schema-element(com:ConsultarComisionResponse) ::)) as element() (:: schema-element(com:ConsultarComisionResponse) ::) {
    <com:ConsultarComisionResponse>
        {
            for $Comision in $outConsultarComision.response/com:Comision
            where (xs:integer($Comision/com1:estadoTCC/atr:id) != 6 or xs:integer($Comision/com1:estadoTCC/atr:id) != 12) and (fn:string-length(fn:data($Comision/com1:codigoContrato)) != 0)
            return 
            <com:Comision>
                {
                $Comision/*
                }
            </com:Comision>
        }
    </com:ConsultarComisionResponse>
};

local:funcXq_filtarcomisionesnopagadas($outConsultarComision.response)
