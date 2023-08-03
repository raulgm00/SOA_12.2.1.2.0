xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V2/Schema/DesembolsoMOV2.xsd", 
                     "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace des2 = "http://www.bcie.org/DesembolsoBOV2";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response as element() (:: schema-element(des:ConsultarDesembolsosByIdV2Response) ::) external;

declare function local:funcXq_requestcrearactualizardesembolsov2($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response as element() (:: schema-element(des:ConsultarDesembolsosByIdV2Response) ::)) as element() (:: schema-element(des:CrearActualizarDesembolsosRequest) ::) {
    <des:CrearActualizarDesembolsosRequest>
        <des:ContratoDesembolso>
            <des1:idDesembolso>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:idDesembolso)}</des1:idDesembolso>
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:idFacturador)
                then <des1:idFacturador>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:idFacturador)}</des1:idFacturador>
                else ()
            }
            <des1:estado>
                <cat:Id>17</cat:Id>
                <cat:Descripcion></cat:Descripcion>
                <cat:DescripcionCorta></cat:DescripcionCorta>
                <cat:estatus></cat:estatus>
                <cat:codigoExterno></cat:codigoExterno>
            </des1:estado>
            <des1:fechaEstimadaDesembolsar></des1:fechaEstimadaDesembolsar>
            {
                if ($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:fechaDisponibilidadFondos)
                then <des1:fechaEfectiva>{fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des2:fechaDisponibilidadFondos)}</des1:fechaEfectiva>
                else ()
            }
        </des:ContratoDesembolso>
    </des:CrearActualizarDesembolsosRequest>
};

local:funcXq_requestcrearactualizardesembolsov2($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response)
