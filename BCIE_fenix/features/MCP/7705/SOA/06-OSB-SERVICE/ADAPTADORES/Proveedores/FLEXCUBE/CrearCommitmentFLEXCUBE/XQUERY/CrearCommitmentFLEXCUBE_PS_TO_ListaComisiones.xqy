xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://org/bcie/ws/middle/FLEXCUBE.wsdl/types/";
(:: import schema at "../../WSDL/FLEXCUBE_WSDL.wsdl" ::)
declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare variable $CrearCommitmentFLEXCUBERequest as element() (:: schema-element(ns2:CrearCommitmentFLEXCUBERequest) ::) external;
declare variable $baseCalculo as xs:string external;
declare variable $fechaInicioRevision as xs:string external;
declare variable $tipoComision as xs:string external;
declare variable $codigoTasa as xs:string external;
declare variable $valorComision as xs:string external;
declare variable $frecuencia as xs:string external;
declare variable $tasaMaxima as xs:string external;
declare variable $tasaMinima as xs:string external;
declare variable $tipoFrecuenciaRevision as xs:string external;
declare variable $tipoTasa as xs:string external;
declare variable $tipoFrecuencia as xs:string external;
declare variable $spread as xs:string external;
declare variable $spreadMora as xs:string external;
declare variable $frecuenciaRevision as xs:string external;
declare variable $fechaInicio as xs:string external;
declare variable $monto as xs:string external;

declare function local:func($CrearCommitmentFLEXCUBERequest as element() (:: schema-element(ns2:CrearCommitmentFLEXCUBERequest) ::),
                
                            $fechaInicioRevision as xs:string, 
                  
                            $codigoTasa as xs:string, 
                        
                            $tasaMaxima as xs:string, 
                            $tasaMinima as xs:string, 
                            $tipoFrecuenciaRevision as xs:string, 
                        
                            $spread as xs:string, 
                            $spreadMora as xs:string, 
                            $frecuenciaRevision as xs:string )
                            as element() (:: element(*, ns1:ComisionestypeUserArray) ::) {
    <ns1:ComisionestypeUserArray>
     { for $comision in $CrearCommitmentFLEXCUBERequest/ns2:Contrato/con:LineaCredito/lin:Comision
            return 
        <ns1:ComisionestypeUser>
            <ns1:baseCalculo>{fn:data($comision/com:baseCalculo/cat:Id)}</ns1:baseCalculo>
            <ns1:fechaInicioRevision>{fn:data($fechaInicioRevision)}</ns1:fechaInicioRevision>
            <ns1:tipoComision>{fn:data($comision/com:tipoComision/com:idTipoComision/cat:Descripcion)}</ns1:tipoComision>
            <ns1:codigoTasa>{fn:data($codigoTasa)}</ns1:codigoTasa>
            <ns1:valorComision>{fn:data($comision/com:montoBase/com:porcentajeMontoBase)}</ns1:valorComision>
            <ns1:frecuencia>{fn:data($comision/com:frecuenciaPago)}</ns1:frecuencia>
            <ns1:tasaMaxima>{fn:data($tasaMaxima)}</ns1:tasaMaxima>
            <ns1:tasaMinima>{fn:data($tasaMinima)}</ns1:tasaMinima>
            <ns1:tipoFrecuenciaRevision>{fn:data($tipoFrecuenciaRevision)}</ns1:tipoFrecuenciaRevision>
            <ns1:tipoTasa>{fn:data($comision/com:tipoTasa/cat:Id)}</ns1:tipoTasa>
            <ns1:tipoFrecuencia>{fn:data($comision/com:tipoFrecuencia/cat:codigoExterno)}</ns1:tipoFrecuencia>
            <ns1:spread>{fn:data($spread)}</ns1:spread>
            <ns1:spreadMora>{fn:data($spreadMora)}</ns1:spreadMora>
            <ns1:frecuenciaRevision>{fn:data($frecuenciaRevision)}</ns1:frecuenciaRevision>
            <ns1:fechaInicio>{fn:data($comision/com:fechaInicioCapital)}</ns1:fechaInicio>
            <ns1:monto>{fn:data($comision/com:montoComision)}</ns1:monto>
        </ns1:ComisionestypeUser>
       }
    </ns1:ComisionestypeUserArray>
};

local:func($CrearCommitmentFLEXCUBERequest, $fechaInicioRevision, $codigoTasa, $tasaMaxima, $tasaMinima, $tipoFrecuenciaRevision, $spread, $spreadMora, $frecuenciaRevision)
