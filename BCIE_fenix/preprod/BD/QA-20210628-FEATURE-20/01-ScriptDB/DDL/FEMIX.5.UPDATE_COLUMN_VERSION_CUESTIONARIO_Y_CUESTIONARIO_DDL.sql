ALTER TABLE FENIX.TCA_VERSION_CUESTIONARIO 
    ADD (ID_TCA_TIPO_CUESTIONARIO NUMBER(5,0) NULL CONSTRAINT VERSION_CUESTIONARIO_TCA_TIPO_CUESTIONARIO_FK REFERENCES TCA_TIPO_CUESTIONARIO(ID));     

ALTER TABLE FENIX.CUESTIONARIO 
    ADD (ID_TCA_TIPO_CUESTIONARIO NUMBER(5,0) NULL CONSTRAINT CUESTIONARIO_TCA_TIPO_CUESTIONARIO_FK REFERENCES TCA_TIPO_CUESTIONARIO(ID));
