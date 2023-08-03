xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V2/Schema/DesembolsoMOV2.xsd" ::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBOV2";

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

declare variable $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response as element() (:: schema-element(des:ConsultarDesembolsosByIdV2Response) ::) external;
declare variable $ComponentesCounter as xs:integer external;

declare function local:funcXq_ajustartasacomponente($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response as element() (:: schema-element(des:ConsultarDesembolsosByIdV2Response) ::), $ComponentesCounter as xs:integer) as element() (:: schema-element(des:ConsultarDesembolsosByIdV2Response) ::) 
{
    <des:ConsultarDesembolsosByIdV2Response>
    {
    let $tasaMinima := if($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:Componente[$ComponentesCounter]/des1:LimiteTasaMinima) then fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:Componente[$ComponentesCounter]/des1:LimiteTasaMinima) else(0)
    let $tasaMaxima := if($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:Componente[$ComponentesCounter]/des1:LimiteTasaMaxima)then fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:Componente[$ComponentesCounter]/des1:LimiteTasaMaxima)else (0)
    let $tipoTasa := fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:Componente[$ComponentesCounter]/des1:TipoTasa/cat:codigoExterno)
    let $valorTasaFija := if($tipoTasa='X' and $outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:Componente[$ComponentesCounter]/des1:Tasa/text() = '')then fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:Componente[$ComponentesCounter]/des1:Tasa) else(0)
    let $valorTasaMax:= if($valorTasaFija != 0 and $tasaMaxima != 0)then if($valorTasaFija > $tasaMaxima)then $tasaMaxima else 0 else (0)
    let $valorTasaMin:= if($valorTasaFija != 0 and $tasaMinima != 0)then if($valorTasaFija < $tasaMinima)then $tasaMinima else 0 else (0)
    let $tasaReferencia := if($tipoTasa = 'F') then 
                                  if($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:Componente[$ComponentesCounter]/des1:ValorTasaReferencia) then
                                      fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:Componente[$ComponentesCounter]/des1:ValorTasaReferencia)
                                  else 0
                            else (0)
    let $spread := if($tipoTasa = 'F') then 
                   $tasaReferencia + fn:data($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response/des:ContratoDesembolso/des1:condicionesFinancieras/des1:Componente[$ComponentesCounter]/des1:SpreadTasa) 
                   else (0)
    let $spreadMin := if($tipoTasa = 'F' and $tasaMinima != 0)then if($spread < $tasaMinima)then $tasaMinima - $tasaReferencia else (0) else (0)
    let $spreadMax := if($tipoTasa = 'F' and $tasaMaxima != 0) then if($spread > $tasaMaxima) then  $tasaMaxima - $tasaReferencia else(0) else(0)
    let $ajusteTasa := if($valorTasaMax != 0 or $valorTasaMin != 0 or $spreadMin != 0 or $spreadMax != 0)then true() else false()
    return 
    if($ajusteTasa = true())then
        <des:ContratoDesembolso>
            <des1:condicionesFinancieras>
                <des1:Componente>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                    <des1:TipoTasa>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </des1:TipoTasa>
                    <des1:esDependiente></des1:esDependiente>
                    <des1:esFactor></des1:esFactor>
                    <des1:CodigoTasaReferencia></des1:CodigoTasaReferencia>
                    <des1:FechaEfectivaTasaReferencia></des1:FechaEfectivaTasaReferencia>
                    <des1:ValorTasaReferencia></des1:ValorTasaReferencia>
                    <des1:Tasa>
                    {
                      if($valorTasaMax != 0)then $valorTasaMax
                      else if($valorTasaMin != 0)then $valorTasaMin
                           else $valorTasaFija
                    }
                    </des1:Tasa>
                    <des1:SpreadTasa>
                    {
                      if($tipoTasa='F')then
                      if($spreadMin!=0)then $spreadMin
                      else if($spreadMax != 0)then $spreadMax
                      else($spread) else()
                    }
                    </des1:SpreadTasa>
                    <des1:MontoDescuento></des1:MontoDescuento>
                    <des1:DiasSpotTasa></des1:DiasSpotTasa>
                    <des1:CantidadDecimales></des1:CantidadDecimales>
                    <des1:LimiteTasaMinima></des1:LimiteTasaMinima>
                    <des1:LimiteTasaMaxima></des1:LimiteTasaMaxima>
                </des1:Componente>
            </des1:condicionesFinancieras>
        </des:ContratoDesembolso>
        else()}
    </des:ConsultarDesembolsosByIdV2Response>
};

local:funcXq_ajustartasacomponente($outConsultarDesembolsoByIdV2.ConsultarDesembolsoByIdV2Response,$ComponentesCounter)
