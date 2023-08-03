xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/PrepagoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Prepago/V1/Schema/PrepagoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/DetallePenalidadPrepago";
(:: import schema at "../XSD/DetallePenalidadPrepago_sp.xsd" ::)

declare variable $response as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($response as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:DetallePenalidadPrepagoResponse) ::) {
    <ns2:DetallePenalidadPrepagoResponse>
        {
            if ($response/ns1:P_TASAPRIME)
            then <ns2:P_TASAPRIME>{fn:data($response/ns1:P_TASAPRIME)}</ns2:P_TASAPRIME>
            else ()
        }
        {
            if ($response/ns1:P_TASALIBOR)
            then <ns2:P_TASALIBOR>{fn:data($response/ns1:P_TASALIBOR)}</ns2:P_TASALIBOR>
            else ()
        }
        <ns2:P_IDTCATIPOMONEDATRAMITE>{fn:data($response/ns1:P_IDTCATIPOMONEDATRAMITE)}</ns2:P_IDTCATIPOMONEDATRAMITE>
        {
            if ($response/ns1:P_CARGOTRAMITEVALOR)
            then <ns2:P_CARGOTRAMITEVALOR>{fn:data($response/ns1:P_CARGOTRAMITEVALOR)}</ns2:P_CARGOTRAMITEVALOR>
            else ()
        }
        {
            if ($response/ns1:P_CARGOTRAMITEDESCRIPCION)
            then <ns2:P_CARGOTRAMITEDESCRIPCION>{fn:data($response/ns1:P_CARGOTRAMITEDESCRIPCION)}</ns2:P_CARGOTRAMITEDESCRIPCION>
            else ()
        }
        {
            if ($response/ns1:P_FECHACALCULODEFINITIVO)
            then <ns2:P_FECHACALCULODEFINITIVO>{fn:data($response/ns1:P_FECHACALCULODEFINITIVO)}</ns2:P_FECHACALCULODEFINITIVO>
            else ()
        }
        <ns2:P_FECHAREFERENCIA>{fn:data($response/ns1:P_FECHAREFERENCIA)}</ns2:P_FECHAREFERENCIA>
        {
            if ($response/ns1:P_TBLPENALIDAD)
            then <ns2:P_TBLPENALIDAD>
                {
                    for $P_TBLPENALIDAD_ITEM in $response/ns1:P_TBLPENALIDAD/ns1:P_TBLPENALIDAD_ITEM
                    return 
                    <ns2:P_TBLPENALIDAD_ITEM>
                        {
                            if ($P_TBLPENALIDAD_ITEM/ns1:FECHAINICIO)
                            then <ns2:FECHAINICIO>{fn:data($P_TBLPENALIDAD_ITEM/ns1:FECHAINICIO)}</ns2:FECHAINICIO>
                            else ()
                        }
                        {
                            if ($P_TBLPENALIDAD_ITEM/ns1:FECHAFIN)
                            then <ns2:FECHAFIN>{fn:data($P_TBLPENALIDAD_ITEM/ns1:FECHAFIN)}</ns2:FECHAFIN>
                            else ()
                        }
                        {
                            if ($P_TBLPENALIDAD_ITEM/ns1:PLAZO)
                            then <ns2:PLAZO>{fn:data($P_TBLPENALIDAD_ITEM/ns1:PLAZO)}</ns2:PLAZO>
                            else ()
                        }
                        {
                            if ($P_TBLPENALIDAD_ITEM/ns1:SPREAD)
                            then <ns2:SPREAD>{fn:data($P_TBLPENALIDAD_ITEM/ns1:SPREAD)}</ns2:SPREAD>
                            else ()
                        }
                        {
                            if ($P_TBLPENALIDAD_ITEM/ns1:FRACCIONLIBOR)
                            then <ns2:FRACCIONLIBOR>{fn:data($P_TBLPENALIDAD_ITEM/ns1:FRACCIONLIBOR)}</ns2:FRACCIONLIBOR>
                            else ()
                        }
                        {
                            if ($P_TBLPENALIDAD_ITEM/ns1:TASAPREPAGO)
                            then <ns2:TASAPREPAGO>{fn:data($P_TBLPENALIDAD_ITEM/ns1:TASAPREPAGO)}</ns2:TASAPREPAGO>
                            else ()
                        }
                        {
                            if ($P_TBLPENALIDAD_ITEM/ns1:MONTOPENALIDAD)
                            then <ns2:MONTOPENALIDAD>{fn:data($P_TBLPENALIDAD_ITEM/ns1:MONTOPENALIDAD)}</ns2:MONTOPENALIDAD>
                            else ()
                        }
                        {
                            if ($P_TBLPENALIDAD_ITEM/ns1:INTERESES)
                            then <ns2:INTERESES>{fn:data($P_TBLPENALIDAD_ITEM/ns1:INTERESES)}</ns2:INTERESES>
                            else ()
                        }
                        {
                            if ($P_TBLPENALIDAD_ITEM/ns1:IDTREPREPAGOCONTRATO)
                            then <ns2:IDTREPREPAGOCONTRATO>{fn:data($P_TBLPENALIDAD_ITEM/ns1:IDTREPREPAGOCONTRATO)}</ns2:IDTREPREPAGOCONTRATO>
                            else ()
                        }
                        {
                            if ($P_TBLPENALIDAD_ITEM/ns1:CONTRATODESEMBOLSO)
                            then <ns2:CONTRATODESEMBOLSO>{fn:data($P_TBLPENALIDAD_ITEM/ns1:CONTRATODESEMBOLSO)}</ns2:CONTRATODESEMBOLSO>
                            else ()
                        }
                        {
                            if ($P_TBLPENALIDAD_ITEM/ns1:MONEDA)
                            then <ns2:MONEDA>{fn:data($P_TBLPENALIDAD_ITEM/ns1:MONEDA)}</ns2:MONEDA>
                            else ()
                        }
                        {
                            if ($P_TBLPENALIDAD_ITEM/ns1:RESOLUCION)
                            then <ns2:RESOLUCION>{fn:data($P_TBLPENALIDAD_ITEM/ns1:RESOLUCION)}</ns2:RESOLUCION>
                            else ()
                        }
                        {
                            if ($P_TBLPENALIDAD_ITEM/ns1:MONTOPREPAGO)
                            then <ns2:MONTOPREPAGO>{fn:data($P_TBLPENALIDAD_ITEM/ns1:MONTOPREPAGO)}</ns2:MONTOPREPAGO>
                            else ()
                        }
                        {
                            if ($P_TBLPENALIDAD_ITEM/ns1:PROXIMOPAGO)
                            then <ns2:PROXIMOPAGO>{fn:data($P_TBLPENALIDAD_ITEM/ns1:PROXIMOPAGO)}</ns2:PROXIMOPAGO>
                            else ()
                        }</ns2:P_TBLPENALIDAD_ITEM>
                }
            </ns2:P_TBLPENALIDAD>
            else ()
        } 
    </ns2:DetallePenalidadPrepagoResponse>
};

local:func($response)