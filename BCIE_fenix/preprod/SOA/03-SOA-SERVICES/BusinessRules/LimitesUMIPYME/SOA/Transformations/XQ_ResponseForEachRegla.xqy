xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ValidarConsultarPlazoGracia.response as element() (:: schema-element(des:ConsultarPlazoGraciaByLineaFinancieraResponse) ::) external;

declare function local:funcXq_responseforeachregla($ValidarConsultarPlazoGracia.response as element() (:: schema-element(des:ConsultarPlazoGraciaByLineaFinancieraResponse) ::)) as element() (:: schema-element(des:ValidarPlazoResponse) ::) {
    <des:ValidarPlazoResponse>
        {
            for $LimitePlazo in $ValidarConsultarPlazoGracia.response/des:LimitePlazo
            return 
            <des:Regla>
                <reg:Transaccion>
                    { 
                    if (xs:string(fn:data($LimitePlazo/des1:TipoLimite/cat:codigoExterno)) = '1')
                    then 'PLAZO_CAPITAL_MAXIMO'
                    else if (xs:string(fn:data($LimitePlazo/des1:TipoLimite/cat:codigoExterno)) = '2')
                    then 'PERIODO_GRACIA'
                    else if (xs:string(fn:data($LimitePlazo/des1:TipoLimite/cat:codigoExterno)) = '3')
                    then 'PLAZO_CAPITAL_MINIMO'
                    else ()
                    }
                </reg:Transaccion>
                <reg:Estado>
                    <cat:Descripcion>
                      {concat('Fecha Vencimiento: ',fn:data($LimitePlazo/com:Tipo/cat:Descripcion/text()), ' Fecha Disponibilidad: ',fn:data($LimitePlazo/com:Tipo/cat:Id/text()))}
                    </cat:Descripcion>
                    <cat:DescripcionCorta>
                    {
                    if (xs:string(fn:data($LimitePlazo/com:Tipo/cat:estatus)) = '0')
                    then 'CUMPLIDA'
                    else 'NO_CUMPLIDA'
                    }
                    </cat:DescripcionCorta>
                </reg:Estado>
            </des:Regla>
        }
        <des:Resultado>
            <res:result>OK</res:result>
            <res:message>Validacion Exitosa</res:message>
        </des:Resultado>
    </des:ValidarPlazoResponse>
};

local:funcXq_responseforeachregla($ValidarConsultarPlazoGracia.response)
