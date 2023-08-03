<?xml version="1.0" encoding="UTF-8"?>
<ns0:businessIndicators xmlns:ns0="http://xmlns.oracle.com/bpm/analytics/businessIndicator" fileVersion="20130625">
   <ns0:businessIndicator name="totalReprocesosPA02">
      <ns0:id>628c7e43-2f78-41f1-b065-5085632f1868</ns0:id>
      <ns0:type>COUNTER</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:mappings>
         <ns0:mapping componentName="PA02AdministracionComisionesProcess" componentType="BPMN">bpmn:getDataObject('projectDataObjectCounter4')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="totalDesperdicio">
      <ns0:id>8ea84267-8626-4fdc-8aa1-30e47ceeb678</ns0:id>
      <ns0:type>COUNTER</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:mappings>
         <ns0:mapping componentName="PA02AdministracionComisionesProcess" componentType="BPMN">bpmn:getDataObject('projectDataObjectCounter1')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="totalReprocesos">
      <ns0:id>c7c66c01-045b-4eab-96f4-2121f8fdb186</ns0:id>
      <ns0:type>COUNTER</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:mappings>
         <ns0:mapping componentName="PA02AdministracionComisionesProcess" componentType="BPMN">bpmn:getDataObject('projectDataObjectCounter2')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="totalDesperdicioPA02">
      <ns0:id>cc38299d-70f1-4a9f-8c93-dccc80bddc0b</ns0:id>
      <ns0:type>COUNTER</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:mappings>
         <ns0:mapping componentName="PA02AdministracionComisionesProcess" componentType="BPMN">bpmn:getDataObject('projectDataObjectCounter3')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="idEstadoOperacion">
      <ns0:id>da72c1e0-2738-4659-968f-17c87ba5ffdf</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="PA02AdministracionComisionesProcess" componentType="BPMN">bpmn:getDataObject('idEstadoOperacion')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="idOperacion">
      <ns0:id>6cbd6588-fee8-4ed6-b153-4a140b1c843a</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="PA02AdministracionComisionesProcess" componentType="BPMN">bpmn:getDataObject('idOperacion')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="accionTareaPA02">
      <ns0:id>eca6c847-68c4-478a-9c2e-271069a06d58</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>String</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="PA02AdministracionComisionesProcess" componentType="BPMN">bpmn:getDataObject('accionTareaPA02')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="idEstadoOperacionPA02">
      <ns0:id>3dc31a0c-4d4c-48fa-96de-1bc5ee1056d2</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="PA02AdministracionComisionesProcess" componentType="BPMN">bpmn:getDataObject('idEstadoOperacionPA02')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="nombreOperacion">
      <ns0:id>08dfaeda-a0d7-4dba-9522-0fb88b5c91fc</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>String</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="PA02AdministracionComisionesProcess" componentType="BPMN">bpmn:getDataObject('nombreOperacion')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="accionTarea">
      <ns0:id>3a494788-755b-45cc-8cd6-568d65731c69</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>String</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="PA02AdministracionComisionesProcess" componentType="BPMN">bpmn:getDataObject('accionTarea')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="idOperacionPA02">
      <ns0:id>b0602a2f-5c68-40de-823a-c50131378c5e</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="PA02AdministracionComisionesProcess" componentType="BPMN">bpmn:getDataObject('idOperacionPA02')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="nombreOperacionPA02">
      <ns0:id>a6dc1379-85fc-4b0e-aa13-4fa21b01480b</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>String</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="PA02AdministracionComisionesProcess" componentType="BPMN">bpmn:getDataObject('nombreOperacionPA02')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
</ns0:businessIndicators>
