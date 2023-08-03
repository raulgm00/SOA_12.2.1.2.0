xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTCCConfiguracion";
(:: import schema at "../XSD/ConsultarTCCConfiguracion.xsd" ::)

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $consultarOutputCollection as element() (:: schema-element(ns1:ConsultarTCCConfiguracionOutputCollection) ::) external;

declare function local:func($consultarOutputCollection as element() (:: schema-element(ns1:ConsultarTCCConfiguracionOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarTCCConfiguracionResponse) ::) {
    <ns2:ConsultarTCCConfiguracionResponse>
        <ns2:ListaTCCConfiguracion>
        { for $config in $consultarOutputCollection/ns1:ConsultarTCCConfiguracionOutput
            return 
            if(fn:string-length(fn:string($config/ns1:ID_TCA_TIPO_COMISION))>0) then (
            <mat:TCCConfiguracion>
                <mat:idTCCConfig>{fn:data($config/ns1:ID_TCCCONFIG)}</mat:idTCCConfig>
                <mat:tipo>COMISION</mat:tipo>
                <mat:idPrecarga>{fn:data($config/ns1:ID_COMISION_PRECARGA)}</mat:idPrecarga>
                <mat:nombre>{fn:data($config/ns1:NOMBRE_COMISION)}</mat:nombre>
                <mat:idTcaTipo>{fn:data($config/ns1:ID_TCA_TIPO_COMISION)}</mat:idTcaTipo>
                <mat:idTca>{fn:data($config/ns1:ID_TCA_COMISION)}</mat:idTca>
               <mat:esMandatorio>{fn:data($config/ns1:ES_MANDATORIO)}</mat:esMandatorio>
            </mat:TCCConfiguracion>)
            else if(fn:string-length(fn:string($config/ns1:ID_TCA_TIPO_TERMINO))>0) then (
             <mat:TCCConfiguracion>
                <mat:idTCCConfig>{fn:data($config/ns1:ID_TCCCONFIG)}</mat:idTCCConfig>
                <mat:tipo>TERMINO</mat:tipo>
                <mat:idPrecarga>{fn:data($config/ns1:ID_TERMINO_PRECARGA)}</mat:idPrecarga>
                 <mat:nombre>{fn:data($config/ns1:NOMBRE_TERMINO)}</mat:nombre>
                <mat:idTcaTipo>{fn:data($config/ns1:ID_TCA_TIPO_TERMINO)}</mat:idTcaTipo>
                <mat:idTca>{fn:data($config/ns1:ID_TCA_TERMINO)}</mat:idTca>
               <mat:esMandatorio>{fn:data($config/ns1:ES_MANDATORIO)}</mat:esMandatorio>
            </mat:TCCConfiguracion>)
             else if(fn:string-length(fn:string($config/ns1:ID_TCA_TIPO_CONDICION))>0) then (
              <mat:TCCConfiguracion>
                <mat:idTCCConfig>{fn:data($config/ns1:ID_TCCCONFIG)}</mat:idTCCConfig>
                <mat:tipo>CONDICION</mat:tipo>
                <mat:idPrecarga>{fn:data($config/ns1:ID_CONDICION_PRECARGA)}</mat:idPrecarga>
                  <mat:nombre>{fn:data($config/ns1:NOMBRE_CONDICION)}</mat:nombre>
                <mat:idTcaTipo>{fn:data($config/ns1:ID_TCA_TIPO_CONDICION)}</mat:idTcaTipo>
                <mat:idTca>{fn:data($config/ns1:ID_TCA_CONDICION)}</mat:idTca>
               <mat:esMandatorio>{fn:data($config/ns1:ES_MANDATORIO)}</mat:esMandatorio>
            </mat:TCCConfiguracion>)
             else()
         
              }
        </ns2:ListaTCCConfiguracion>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Realizada Exitosamente</res:message>
           
        </ns2:Resultado>
    </ns2:ConsultarTCCConfiguracionResponse>
};

local:func($consultarOutputCollection)
