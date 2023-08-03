xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarLineaCreditoByIdLineaCreditoResponse as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdLineaCreditoResponse) ::) external;
declare variable $ConsultarSaldoLineaResponse as element() (:: schema-element(ns1:ConsultarSaldoLineaResponse) ::) external;
declare variable $Resultado as xs:string external;

declare function local:func($ConsultarLineaCreditoByIdLineaCreditoResponse as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdLineaCreditoResponse) ::), 
                            $ConsultarSaldoLineaResponse as element() (:: schema-element(ns1:ConsultarSaldoLineaResponse) ::)
                            ,$Resultado) 
                            as element() (:: schema-element(ns1:ConsultarSaldoLineaResponse) ::) {
    <ns1:ConsultarSaldoLineaResponse>
    {
        if($Resultado = 'OK') then  
        <ns1:LineaCredito>
            {
                if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:idLineaCredito)
                then <lin:idLineaCredito>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:idLineaCredito)}</lin:idLineaCredito>
                else ()
            }
            {
                if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:idContrato)
                then <lin:idContrato>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:idContrato)}</lin:idContrato>
                else ()
            }
            <lin:NumeroLineaCredito>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:NumeroLineaCredito)}</lin:NumeroLineaCredito>
            <lin:Descripcion>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:Descripcion)}</lin:Descripcion>
            {
                if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:Flexcube)
                then 
                    <lin:Flexcube>
                        {
                            if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:Flexcube/lin:id)
                            then <lin:id>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:Flexcube/lin:id)}</lin:id>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:Flexcube/lin:idProductoFlexcube)
                            then <lin:idProductoFlexcube>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:Flexcube/lin:idProductoFlexcube)}</lin:idProductoFlexcube>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:Flexcube/lin:idFlexcubePasivo)
                            then <lin:idFlexcubePasivo>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:Flexcube/lin:idFlexcubePasivo)}</lin:idFlexcubePasivo>
                            else ()
                        }
                    </lin:Flexcube>
                else ()
            }
            {
                if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:Fondo)
                then <lin:Fondo>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:Fondo)}</lin:Fondo>
                else ()
            }
            {
                if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:MontoLinea)
                then <lin:MontoLinea>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:MontoLinea)}</lin:MontoLinea>
                else ()
            }
            {
                if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:EsRevolvente)
                then <lin:EsRevolvente>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:EsRevolvente)}</lin:EsRevolvente>
                else ()
            }
            <lin:TratamientoDiasFeriados>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:TratamientoDiasFeriados)}</lin:TratamientoDiasFeriados>
            <lin:FechaValor>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:FechaValor)}</lin:FechaValor>
            <lin:FechaVencimiento>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:FechaVencimiento)}</lin:FechaVencimiento>
            <lin:CodigoPantallaLimite>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:CodigoPantallaLimite)}</lin:CodigoPantallaLimite>
            <lin:CodigoSerialLimite>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:CodigoSerialLimite)}</lin:CodigoSerialLimite>
            <lin:CodigoAsignacion>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:CodigoAsignacion)}</lin:CodigoAsignacion>
            <lin:DescripcionAsignacion>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:DescripcionAsignacion)}</lin:DescripcionAsignacion>
            {
                if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:CondicionEspecial)
                then <lin:CondicionEspecial>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:CondicionEspecial)}</lin:CondicionEspecial>
                else ()
            }
            <lin:FechaRegistro>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:FechaRegistro)}</lin:FechaRegistro>
            <lin:FechaMaximaDesembolso>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:FechaMaximaDesembolso)}</lin:FechaMaximaDesembolso>
            {
                if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:Estado)
                then <lin:Estado>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:Estado)}</lin:Estado>
                else ()
            }
            <lin:descCondEspecial>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:descCondEspecial)}</lin:descCondEspecial>
            <lin:frecuenciaGracia>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:frecuenciaGracia)}</lin:frecuenciaGracia>
            <lin:plazoGracia>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:plazoGracia)}</lin:plazoGracia>
            <lin:frecuenciaFinanciamiento>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:frecuenciaFinanciamiento)}</lin:frecuenciaFinanciamiento>
            <lin:plazoFinanciamiento>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:plazoFinanciamiento)}</lin:plazoFinanciamiento>
            <lin:recursosExternos>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:recursosExternos)}</lin:recursosExternos>
            <lin:tasaMinima>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:tasaMinima)}</lin:tasaMinima>
            <lin:tasaMaxima>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:tasaMaxima)}</lin:tasaMaxima>
            <lin:montoMinimo>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:montoMinimo)}</lin:montoMinimo>
            <lin:montoMaximo>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:montoMaximo)}</lin:montoMaximo>
            {
                for $Monto in $ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:Monto
                return 
                <lin:Monto>
                    {$Monto/*}
                </lin:Monto>
            }
            {
                for $condicion in $ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:Condicion
                return
                <lin:Condicion>
                    {$condicion/*}
                </lin:Condicion>
            }
            {
                for $Termino in $ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:Termino
                return 
                <lin:Termino>
                {$Termino/*}
                </lin:Termino>
            }
            {
                for $Comision in $ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:Comision
                return 
                <lin:Comision>
                {$Comision/*}
                </lin:Comision>
            }
            {
                for $Fuente in $ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:Fuente
                return 
                <lin:Fuente>
                {$Fuente/*}
                </lin:Fuente>
            }
            {
                for $contratoDesembolso in $ConsultarSaldoLineaResponse/ns1:LineaCredito/lin:ContratoDesembolso
                where string-length($contratoDesembolso/des:idFacturador) > 0
                return 
                <lin:ContratoDesembolso>
                {$contratoDesembolso/*}
                </lin:ContratoDesembolso>
            }
            {
                for $atributos in $ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:atributos
                return 
                <lin:atributos>
                {$atributos/*}
                </lin:atributos>
            }
            {
                if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE)
                then 
                    <lin:HerramientaCE>
                        <her:ActividadEconomica>
                            {
                                if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:ActividadEconomica/cat:Id)
                                then <cat:Id>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:ActividadEconomica/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:ActividadEconomica/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:ActividadEconomica/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:ActividadEconomica/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:ActividadEconomica/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:ActividadEconomica/cat:estatus)
                                then <cat:estatus>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:ActividadEconomica/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:ActividadEconomica>
                        <her:EjeEstrategico>
                            {
                                if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:EjeEstrategico/cat:Id)
                                then <cat:Id>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:EjeEstrategico/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:EjeEstrategico/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:EjeEstrategico/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:EjeEstrategico/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:EjeEstrategico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:EjeEstrategico/cat:estatus)
                                then <cat:estatus>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:EjeEstrategico/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:EjeEstrategico>
                        <her:AreaFocalizacion>
                            {
                                if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:AreaFocalizacion/cat:Id)
                                then <cat:Id>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:AreaFocalizacion/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:AreaFocalizacion/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:AreaFocalizacion/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:AreaFocalizacion/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:AreaFocalizacion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:AreaFocalizacion/cat:estatus)
                                then <cat:estatus>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:AreaFocalizacion/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito/lin:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:AreaFocalizacion>
                    </lin:HerramientaCE>
                else ()
            }
        </ns1:LineaCredito>
        else ()
      }
      {
        if($Resultado = 'OK') then
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Existosa</res:message>
        </ns1:Resultado>
        else
        <ns1:Resultado>
            <res:result>ERROR</res:result>
            <res:message>{$Resultado}</res:message>
        </ns1:Resultado>
    }
    </ns1:ConsultarSaldoLineaResponse>
};

local:func($ConsultarLineaCreditoByIdLineaCreditoResponse, $ConsultarSaldoLineaResponse, $Resultado)
