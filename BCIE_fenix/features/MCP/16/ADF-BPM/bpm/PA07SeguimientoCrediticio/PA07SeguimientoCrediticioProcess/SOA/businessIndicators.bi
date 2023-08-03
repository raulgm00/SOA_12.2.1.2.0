<?xml version="1.0" encoding="UTF-8"?>
<ns0:businessIndicators xmlns:ns0="http://xmlns.oracle.com/bpm/analytics/businessIndicator" fileVersion="20130625">
   <ns0:businessIndicator name="totalDesperdicio">
      <ns0:id>b9298fc2-8574-4efd-862b-b2fdd4cada28</ns0:id>
      <ns0:type>COUNTER</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:mappings>
         <ns0:mapping componentName="PA07SegCrediticioProcess" componentType="BPMN">bpmn:getDataObject('projectDataObjectCounter2')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="totalReprocesosPA07">
      <ns0:id>7e8c8b39-ade7-4e18-bc0d-012e932f155d</ns0:id>
      <ns0:type>COUNTER</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:mappings>
         <ns0:mapping componentName="PA07SegCrediticioProcess" componentType="BPMN">bpmn:getDataObject('projectDataObjectCounter4')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="totalDesperdicioPA07">
      <ns0:id>9bbbb6fe-d344-4e17-9394-f0b77f690d87</ns0:id>
      <ns0:type>COUNTER</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:mappings>
         <ns0:mapping componentName="PA07SegCrediticioProcess" componentType="BPMN">bpmn:getDataObject('projectDataObjectCounter1')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="totalReprocesos">
      <ns0:id>dc5acb77-b5b4-4b92-b33e-e84ca0c43432</ns0:id>
      <ns0:type>COUNTER</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:mappings>
         <ns0:mapping componentName="PA07SegCrediticioProcess" componentType="BPMN">bpmn:getDataObject('projectDataObjectCounter3')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="accionTarea">
      <ns0:id>8a00d82c-6042-473f-bc39-d0127e35939f</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>String</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="PA07SegCrediticioProcess" componentType="BPMN">bpmn:getDataObject('accionTarea')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="idCliente">
      <ns0:id>7b74d936-27bb-4329-a767-3ce4df7543ae</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="PA07SegCrediticioProcess" componentType="BPMN">bpmn:getDataObject('idCliente')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="idClientePA07">
      <ns0:id>b8e61faa-daf5-418f-a5ac-778aa988e403</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="PA07SegCrediticioProcess" componentType="BPMN">bpmn:getDataObject('idClientePA07')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="razonSocial">
      <ns0:id>529ed1ce-f38a-4537-abfe-b7aba96b68d0</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>String</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="PA07SegCrediticioProcess" componentType="BPMN">bpmn:getDataObject('razonSocial')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="accionTareaPA07">
      <ns0:id>1fc7ed90-54f5-4c6f-b4a0-9f67199b20f1</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>String</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="PA07SegCrediticioProcess" componentType="BPMN">bpmn:getDataObject('accionTareaPA07')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="razonSocialPA07">
      <ns0:id>ca1fe754-72ce-458d-8ae2-bbaffe98272e</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>String</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="PA07SegCrediticioProcess" componentType="BPMN">bpmn:getDataObject('razonSocialPA07')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
</ns0:businessIndicators>
