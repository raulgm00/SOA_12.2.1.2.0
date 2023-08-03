xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $outConsultarDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::) external;

declare function local:funcXq_requestcrearactualizardesembolso($outConsultarDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::)) 
                                                               as element() (:: schema-element(des:CrearActualizarDesembolsosRequest) ::) {
    <des:CrearActualizarDesembolsosRequest>
        <des:ContratoDesembolso>
            <des1:idDesembolso>{fn:data($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:idDesembolso)}</des1:idDesembolso>
            {
                if ($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:idFacturador)
                then <des1:idFacturador>{fn:data($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:idFacturador)}</des1:idFacturador>
                else ()
            }
            {
                if ($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:referencia)
                then <des1:referencia>{fn:data($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:referencia)}</des1:referencia>
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
                if ($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:fechaDisponibilidadFondos)
                then <des1:fechaEfectiva>{fn:data($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:fechaDisponibilidadFondos)}</des1:fechaEfectiva>
                else ()
            }
            <des1:condicionesFinancieras>
                <des1:tasa>
                    <des1:tipo>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </des1:tipo>
                    {
                        if (fn:data($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:tasa/des1:tipo/cat:codigoExterno)= 'X') then 
                            <des1:fija>
                                <des1:valor>{fn:data($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:tasa/des1:fija/des1:valor)}</des1:valor>
                            </des1:fija>
                        else
                            if (fn:data($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:tasa/des1:tipo/cat:codigoExterno)= 'F') then 
                                <des1:variable>
                                    <des1:spread>
                                        <des1:valor>{fn:data($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:tasa/des1:variable/des1:spread/des1:valor)}</des1:valor>
                                    </des1:spread>
                                </des1:variable>
                            else()
                        }
                </des1:tasa>
            </des1:condicionesFinancieras>
            </des:ContratoDesembolso>
    </des:CrearActualizarDesembolsosRequest>
};

local:funcXq_requestcrearactualizardesembolso($outConsultarDesembolsoById.response)
