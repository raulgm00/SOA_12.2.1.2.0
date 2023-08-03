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

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $outConsultarDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::) external;

declare function local:funcXq_ajustartasa($outConsultarDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::)) as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::) {
    <des:ConsultarDesembolsosByIdResponse>
    {
    let $tasaMinima := if($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:tasa/des1:variable/des1:limite/com:minimo) then fn:data($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:tasa/des1:variable/des1:limite/com:minimo) else(0)
    let $tasaMaxima := if($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:tasa/des1:variable/des1:limite/com:maximo)then fn:data($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:tasa/des1:variable/des1:limite/com:maximo)else (0)
    let $tipoTasa := fn:data($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:tasa/des1:tipo/cat:codigoExterno)
    let $valorTasaFija := if($tipoTasa='X')then fn:data($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:tasa/des1:fija/des1:valor)else(0)
    let $valorTasaMax:= if($valorTasaFija != 0 and $tasaMaxima != 0)then if($valorTasaFija > $tasaMaxima)then $tasaMaxima else 0 else (0)
    let $valorTasaMin:= if($valorTasaFija != 0 and $tasaMinima != 0)then if($valorTasaFija < $tasaMinima)then $tasaMinima else 0 else (0)
    let $tasaReferencia := if($tipoTasa = 'F') then fn:data($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:tasa/des1:variable/des1:tasaReferencia/des1:valor) else (0)
    let $spread := if($tipoTasa = 'F') then $tasaReferencia + fn:data($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:tasa/des1:variable/des1:spread/des1:valor) else (0)
    let $spreadMin := if($tipoTasa = 'F' and $tasaMinima != 0)then if($spread < $tasaMinima)then $tasaMinima + $tasaReferencia else (0) else (0)
    let $spreadMax := if($tipoTasa = 'F' and $tasaMaxima != 0) then if($spread > $tasaMaxima) then  $tasaMaxima - $tasaReferencia else(0) else(0)
    let $ajusteTasa := if($valorTasaMax != 0 or $valorTasaMin != 0 or $spreadMin != 0 or $spreadMax != 0)then true() else false()
    return 
    if($ajusteTasa = true())then
        <des:ContratoDesembolso>
            <des1:condicionesFinancieras>
                <des1:tasa>
                    <des1:tipo>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </des1:tipo>
                            <des1:fija>
                                <des1:valor>{
                                if($valorTasaMax != 0)then $valorTasaMax
                                else if($valorTasaMin != 0)then $valorTasaMin
                                     else $valorTasaFija}</des1:valor>
                            </des1:fija>
                                <des1:variable>
                                    <des1:tasaReferencia>
                                        <cat:Id></cat:Id>
                                        <cat:Descripcion></cat:Descripcion>
                                        <cat:DescripcionCorta></cat:DescripcionCorta>
                                        <cat:estatus></cat:estatus>
                                        <cat:codigoExterno></cat:codigoExterno>
                                        <des1:valor></des1:valor>
                                    </des1:tasaReferencia>
                                    <des1:spread>
                                        <des1:valor>{if($tipoTasa='F')then
                                        if($spreadMin!=0)then $spreadMin
                                        else if($spreadMax != 0)then $spreadMax
                                        else($spread) else()
                                        }</des1:valor>
                                        <des1:codigo></des1:codigo>
                                        <des1:revision>
                                            <des1:FechaInicio></des1:FechaInicio>
                                            <des1:Frecuencia>
                                                <com:Tipo>
                                                    <cat:Id></cat:Id>
                                                    <cat:Descripcion></cat:Descripcion>
                                                    <cat:DescripcionCorta></cat:DescripcionCorta>
                                                    <cat:estatus></cat:estatus>
                                                    <cat:codigoExterno></cat:codigoExterno>
                                                </com:Tipo>
                                                <com:Valor></com:Valor>
                                            </des1:Frecuencia>
                                        </des1:revision>
                                    </des1:spread>
                                    <des1:revision>
                                        <des1:FechaInicio></des1:FechaInicio>
                                        <des1:Frecuencia>
                                            <com:Tipo>
                                                <cat:Id></cat:Id>
                                                <cat:Descripcion></cat:Descripcion>
                                                <cat:DescripcionCorta></cat:DescripcionCorta>
                                                <cat:estatus></cat:estatus>
                                                <cat:codigoExterno></cat:codigoExterno>
                                            </com:Tipo>
                                            <com:Valor></com:Valor>
                                        </des1:Frecuencia>
                                    </des1:revision>
                                    <des1:limite>
                                        <com:maximo></com:maximo>
                                        <com:minimo></com:minimo>
                                    </des1:limite>
                                </des1:variable>
                                <des1:especial>
                                    <des1:valor></des1:valor>
                                </des1:especial>
                    <des1:spreadMora>
                        <des1:valor></des1:valor>
                        <des1:codigo></des1:codigo>
                        <des1:revision>
                            <des1:FechaInicio></des1:FechaInicio>
                            <des1:Frecuencia>
                                <com:Tipo>
                                    <cat:Id></cat:Id>
                                    <cat:Descripcion></cat:Descripcion>
                                    <cat:DescripcionCorta></cat:DescripcionCorta>
                                    <cat:estatus></cat:estatus>
                                    <cat:codigoExterno></cat:codigoExterno>
                                </com:Tipo>
                                <com:Valor></com:Valor>
                            </des1:Frecuencia>
                        </des1:revision>
                    </des1:spreadMora>
                </des1:tasa>
            </des1:condicionesFinancieras>
        </des:ContratoDesembolso>
        else()}
    </des:ConsultarDesembolsosByIdResponse>
};

local:funcXq_ajustartasa($outConsultarDesembolsoById.response)
