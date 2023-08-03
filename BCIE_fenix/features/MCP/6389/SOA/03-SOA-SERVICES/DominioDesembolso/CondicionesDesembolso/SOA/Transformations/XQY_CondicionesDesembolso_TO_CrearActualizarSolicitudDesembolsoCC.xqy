xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare variable $inputVariable.request as element() (:: schema-element(des:CondicionesDesembolsoRequest) ::) external;

declare function local:funcXqy_condicionesdesembolso_to_crearactualizarsolicituddesembolso($inputVariable.request as element() (:: schema-element(des:CondicionesDesembolsoRequest) ::)) 
                                                                                           as element() (:: schema-element(des:CrearActualizarSolicitudDesembolsoRequest) ::) {
    <des:CrearActualizarSolicitudDesembolsoRequest>
        <des:SolicitudDesembolso>
            <des1:idDesembolso>{fn:data($inputVariable.request/des:idSolicitudDesembolso)}</des1:idDesembolso>
            <des1:estado>
                    <cat:Id>7</cat:Id>
            </des1:estado>
        </des:SolicitudDesembolso>
    </des:CrearActualizarSolicitudDesembolsoRequest>
};

local:funcXqy_condicionesdesembolso_to_crearactualizarsolicituddesembolso($inputVariable.request)
