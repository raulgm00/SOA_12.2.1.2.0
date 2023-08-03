xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/MatrizTCCBO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCBO.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare variable $tcc as element() (:: element(*, ns1:TCC) ::) external;

declare function local:func($tcc as element() (:: element(*, ns1:TCC) ::)) as element() (:: schema-element(ns2:ActualizarCondicionRequest) ::) {
    <ns2:ActualizarCondicionRequest>
        <ns2:Condicion>
            <con:idCondicion>{fn:data($tcc/ns1:id)}</con:idCondicion>
            <con:estadoTCC>
                <atr:id>{fn:data($tcc/ns1:estado)}</atr:id>
            </con:estadoTCC>
            <con:subEstadoTCC>
                <atr:id>{fn:data($tcc/ns1:subEstado)}</atr:id>
            </con:subEstadoTCC>  
             {
            if ($tcc/ns1:banEstatus)
            then(
             <con:estado>{
              if (xs:string($tcc/ns1:banEstatus) = '')
                then(1)
                  else(
                    if (fn:data($tcc/ns1:banEstatus) = 1)
                      then(true())
                        else(false()))
            }
            </con:estado>)
            else()
            }
        </ns2:Condicion>
    </ns2:ActualizarCondicionRequest>
};

local:func($tcc)
