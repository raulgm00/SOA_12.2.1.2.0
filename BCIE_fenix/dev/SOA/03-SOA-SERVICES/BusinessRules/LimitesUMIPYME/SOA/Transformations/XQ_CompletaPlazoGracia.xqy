xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $outConsultarDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::) external;
declare variable $outConsultarPlazoGraciaByLineaFinanciera.response as element() (:: schema-element(des:ConsultarPlazoGraciaByLineaFinancieraResponse) ::) external;

declare function local:funcXq_validaplazogracia($outConsultarDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::), 
                                                $outConsultarPlazoGraciaByLineaFinanciera.response as element() (:: schema-element(des:ConsultarPlazoGraciaByLineaFinancieraResponse) ::)) 
                                                as element() (:: schema-element(des:ConsultarPlazoGraciaByLineaFinancieraResponse) ::) {
    <des:ConsultarPlazoGraciaByLineaFinancieraResponse>
        {
            for $LimitePlazo in $outConsultarPlazoGraciaByLineaFinanciera.response/des:LimitePlazo
            where $LimitePlazo/com:Tipo/cat:DescripcionCorta = $outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:Programa/her:DestinoFinanciamiento/cat:codigoExterno
            return 
            <des:LimitePlazo>
            {
              if ($LimitePlazo)
              then $LimitePlazo/* 
              else ()
            }
            </des:LimitePlazo>
        }
    </des:ConsultarPlazoGraciaByLineaFinancieraResponse>
};

local:funcXq_validaplazogracia($outConsultarDesembolsoById.response, $outConsultarPlazoGraciaByLineaFinanciera.response)
