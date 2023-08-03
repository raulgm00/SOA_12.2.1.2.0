xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd"::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace apr = "http://www.bcie.org/AprobacionBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare variable $AplicarAmpliacion_InputVariable.AplicarAmpliacionRequestMessage as element() (:: schema-element(lin:AplicarAmpliacionRequest) ::) external;
declare variable $ConsultarListaLineasCredito_OutputVariable.response as element() (:: schema-element(lin:ConsultarListaLineasCreditoResponse) ::) external;

declare function local:funcXq_excluircontratosnuevos($AplicarAmpliacion_InputVariable.AplicarAmpliacionRequestMessage as element() (:: schema-element(lin:AplicarAmpliacionRequest) ::), 
                                                     $ConsultarListaLineasCredito_OutputVariable.response as element() (:: schema-element(lin:ConsultarListaLineasCreditoResponse) ::)) 
                                                     as element() (:: schema-element(lin:ConsultarListaLineasCreditoResponse) ::) {
    <lin:ConsultarListaLineasCreditoResponse>
      {
       for $LineasCredito in $ConsultarListaLineasCredito_OutputVariable.response/lin:LineaCredito
       return 
        <lin:LineaCredito>
            <lin:Operacion>
               {$LineasCredito/lin:Operacion/*}
            </lin:Operacion>
            <lin:Contrato>
                <con:idContrato>{fn:data($LineasCredito/lin:Contrato/con:idContrato)}</con:idContrato>
                <con:idOperacion>{fn:data($LineasCredito/lin:Contrato/con:idOperacion)}</con:idOperacion>
                <con:fechaFirma>{fn:data($LineasCredito/lin:Contrato/con:fechaFirma)}</con:fechaFirma>
                <con:fechaVigencia>{fn:data($LineasCredito/lin:Contrato/con:fechaVigencia)}</con:fechaVigencia>
                <con:MontoEscriturado>{fn:data($LineasCredito/lin:Contrato/con:MontoEscriturado)}</con:MontoEscriturado>
                <con:cuentaCliente>
                   {$LineasCredito/lin:Contrato/con:cuentaCliente/*}
                </con:cuentaCliente>
                <con:instanciaProceso>{fn:data($LineasCredito/lin:Contrato/con:instanciaProceso)}</con:instanciaProceso>
                {
                for $ContratoLineasCredito in $ConsultarListaLineasCredito_OutputVariable.response/lin:LineaCredito/lin:Contrato/con:LineaCredito
                return 
                <con:LineaCredito>
                    <lin1:idLineaCredito>{fn:data($ContratoLineasCredito/lin1:idLineaCredito)}</lin1:idLineaCredito>
                    <lin1:idContrato>{fn:data($ContratoLineasCredito/lin1:idContrato)}</lin1:idContrato>
                    <lin1:NumeroLineaCredito>{fn:data($ContratoLineasCredito/lin1:NumeroLineaCredito)}</lin1:NumeroLineaCredito>
                    <lin1:Descripcion>{fn:data($ContratoLineasCredito/lin1:Descripcion)}</lin1:Descripcion>
                    <lin1:Flexcube>
                        {$ContratoLineasCredito/lin1:Flexcube/*}
                    </lin1:Flexcube>
                    <lin1:Fondo>{fn:data($ContratoLineasCredito/lin1:Fondo)}</lin1:Fondo>
                    <lin1:MontoLinea>{fn:data($ContratoLineasCredito/lin1:MontoLinea)}</lin1:MontoLinea>
                    <lin1:EsRevolvente>{fn:data($ContratoLineasCredito/lin1:EsRevolvente)}</lin1:EsRevolvente>
                    <lin1:TratamientoDiasFeriados>{fn:data($ContratoLineasCredito/lin1:TratamientoDiasFeriados)}</lin1:TratamientoDiasFeriados>
                    <lin1:FechaValor>{fn:data($ContratoLineasCredito/lin1:FechaValor)}</lin1:FechaValor>
                    <lin1:FechaVencimiento>{fn:data($ContratoLineasCredito/lin1:FechaVencimiento)}</lin1:FechaVencimiento>
                    <lin1:CodigoPantallaLimite>{fn:data($ContratoLineasCredito/lin1:CodigoPantallaLimite)}</lin1:CodigoPantallaLimite>
                    <lin1:CodigoSerialLimite>{fn:data($ContratoLineasCredito/lin1:CodigoSerialLimite)}</lin1:CodigoSerialLimite>
                    <lin1:CodigoAsignacion>{fn:data($ContratoLineasCredito/lin1:CodigoAsignacion)}</lin1:CodigoAsignacion>
                    <lin1:DescripcionAsignacion>{fn:data($ContratoLineasCredito/lin1:DescripcionAsignacion)}</lin1:DescripcionAsignacion>
                    <lin1:CondicionEspecial>{fn:data($ContratoLineasCredito/lin1:CondicionEspecial)}</lin1:CondicionEspecial>
                    <lin1:FechaRegistro>{fn:data($ContratoLineasCredito/lin1:FechaRegistro)}</lin1:FechaRegistro>
                    <lin1:FechaMaximaDesembolso>{fn:data($ContratoLineasCredito/lin1:FechaMaximaDesembolso)}</lin1:FechaMaximaDesembolso>
                    <lin1:Estado>{fn:data($ContratoLineasCredito/lin1:Estado)}</lin1:Estado>
                    <lin1:descCondEspecial>{fn:data($ContratoLineasCredito/lin1:descCondEspecial)}</lin1:descCondEspecial>
                    <lin1:frecuenciaGracia>{fn:data($ContratoLineasCredito/lin1:frecuenciaGracia)}</lin1:frecuenciaGracia>
                    <lin1:plazoGracia>{fn:data($ContratoLineasCredito/lin1:plazoGracia)}</lin1:plazoGracia>
                    <lin1:frecuenciaFinanciamiento>{fn:data($ContratoLineasCredito/lin1:frecuenciaFinanciamiento)}</lin1:frecuenciaFinanciamiento>
                    <lin1:plazoFinanciamiento>{fn:data($ContratoLineasCredito/lin1:plazoFinanciamiento)}</lin1:plazoFinanciamiento>
                    <lin1:recursosExternos>{fn:data($ContratoLineasCredito/lin1:recursosExternos)}</lin1:recursosExternos>
                    <lin1:tasaMinima>{fn:data($ContratoLineasCredito/lin1:tasaMinima)}</lin1:tasaMinima>
                    <lin1:tasaMaxima>{fn:data($ContratoLineasCredito/lin1:tasaMaxima)}</lin1:tasaMaxima>
                    <lin1:montoMinimo>{fn:data($ContratoLineasCredito/lin1:montoMinimo)}</lin1:montoMinimo>
                    <lin1:montoMaximo>{fn:data($ContratoLineasCredito/lin1:montoMaximo)}</lin1:montoMaximo>
                    {
                        for $Monto in $ContratoLineasCredito/lin1:Monto
                        return 
                        $Monto
                    }
                    {
                        for $Condicion in $ContratoLineasCredito/lin1:Condicion
                        return 
                        $Condicion
                    }
                    {
                        for $Termino in $ContratoLineasCredito/lin1:Termino
                        return 
                        $Termino
                    }
                    {
                        for $Comision in $ContratoLineasCredito/lin1:Comision
                        return 
                        $Comision
                    }
                    {
                        for $Fuente in $ContratoLineasCredito/lin1:Fuente
                        return 
                        $Fuente
                    }
                    {
                      for $ContratoDesembolso in $ContratoLineasCredito/lin1:ContratoDesembolso
                      let $ContratoDes := $AplicarAmpliacion_InputVariable.AplicarAmpliacionRequestMessage/lin:SolicitudDesembolso/des:ContratoDesembolso[des:idDesembolso = $ContratoDesembolso/des:idDesembolso]/des:idDesembolso
                      return 
                       if(string-length($ContratoDes/text())<=0)
                      then
                          $ContratoDesembolso
                      else()                        
                    }
                    {
                        for $atributos in $ContratoLineasCredito/lin1:atributos
                        return 
                        $atributos
                    }
                    {
                        for $HerramientaCE in $ContratoLineasCredito/lin1:HerramientaCE
                        return 
                      $HerramientaCE
                    }
                </con:LineaCredito>
            }
            
            
            </lin:Contrato>
        </lin:LineaCredito>
        }
    </lin:ConsultarListaLineasCreditoResponse>
};

local:funcXq_excluircontratosnuevos($AplicarAmpliacion_InputVariable.AplicarAmpliacionRequestMessage, $ConsultarListaLineasCredito_OutputVariable.response)
