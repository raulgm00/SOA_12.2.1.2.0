xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/MapeoErrorMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/MapeoError/V1/Schema/MapeoErrorMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/ResultBO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Common/V1/Schema/ResultBO.xsd" ::)

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $Error as element() (:: schema-element(ns1:requestMapeoErrorMessage) ::) external;

declare function local:func($Error as element() (:: schema-element(ns1:requestMapeoErrorMessage) ::)) as element() (:: element(*, ns2:Resultado) ::) {
    <ns2:Resultado>
      <ns2:error>
          <err:errorCode>{fn:data($Error/ns1:ErrorInput/err:errorCode)}</err:errorCode>
          <err:errorDescription>{fn:data($Error/ns1:ErrorInput/err:errorDescription)}</err:errorDescription>
          <err:errorType>{fn:data($Error/ns1:ErrorInput/err:errorType)}</err:errorType>        
      </ns2:error>
    </ns2:Resultado>
};

local:func($Error)