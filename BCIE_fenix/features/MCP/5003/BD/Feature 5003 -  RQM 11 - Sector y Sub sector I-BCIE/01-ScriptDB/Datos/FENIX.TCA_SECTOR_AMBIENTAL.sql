-- INSERT de Catalogo SECTOR_AMBIENTAL
-- Tabla No.2 FENIX.TCA_SECTOR_AMBIENTAL
INSERT INTO FENIX.TCA_SECTOR_AMBIENTAL (DESCRIPCION, DESCRIPCION_CORTA, BAN_ESTATUS, CODIGO_EXTERNO, FECHA_REGISTRO) VALUES ('S1.Gestión integral de los recursos hídricos y contaminación','S1.Gestión integral de los recursos hídricos y contaminación',1,'SA-1013',SYSDATE);
INSERT INTO FENIX.TCA_SECTOR_AMBIENTAL (DESCRIPCION, DESCRIPCION_CORTA, BAN_ESTATUS, CODIGO_EXTERNO, FECHA_REGISTRO) VALUES ('S2.Gestión de los recursos naturales y turismo sostenible','S2.Gestión de los recursos naturales y turismo sostenible',1,'SA-1014',SYSDATE);
INSERT INTO FENIX.TCA_SECTOR_AMBIENTAL (DESCRIPCION, DESCRIPCION_CORTA, BAN_ESTATUS, CODIGO_EXTERNO, FECHA_REGISTRO) VALUES ('S3.Gestión integrada de riesgo ante desastres naturales','S3.Gestión integrada de riesgo ante desastres naturales',1,'SA-1015',SYSDATE);
INSERT INTO FENIX.TCA_SECTOR_AMBIENTAL (DESCRIPCION, DESCRIPCION_CORTA, BAN_ESTATUS, CODIGO_EXTERNO, FECHA_REGISTRO) VALUES ('S4.Ordenamiento','S4.Ordenamiento',1,'SA-1016',SYSDATE);
INSERT INTO FENIX.TCA_SECTOR_AMBIENTAL (DESCRIPCION, DESCRIPCION_CORTA, BAN_ESTATUS, CODIGO_EXTERNO, FECHA_REGISTRO) VALUES ('S5.Sistemas de infraestructura agropecuaria y seguridad alimentaria.','S5.Sistemas de infraestructura agropecuaria y seguridad alimentaria.',1,'SA-1017',SYSDATE);
INSERT INTO FENIX.TCA_SECTOR_AMBIENTAL (DESCRIPCION, DESCRIPCION_CORTA, BAN_ESTATUS, CODIGO_EXTERNO, FECHA_REGISTRO) VALUES ('S6.Energía, eficiencia energética y reducción de GEI','S6.Energía, eficiencia energética y reducción de GEI',1,'SA-1018',SYSDATE);
INSERT INTO FENIX.TCA_SECTOR_AMBIENTAL (DESCRIPCION, DESCRIPCION_CORTA, BAN_ESTATUS, CODIGO_EXTERNO, FECHA_REGISTRO) VALUES ('S7.Salud e infraestructura sanitaria','S7.Salud e infraestructura sanitaria',1,'SA-1019',SYSDATE);
INSERT INTO FENIX.TCA_SECTOR_AMBIENTAL (DESCRIPCION, DESCRIPCION_CORTA, BAN_ESTATUS, CODIGO_EXTERNO, FECHA_REGISTRO) VALUES ('S8.Generación de oportunidades de inclusión socio-económica para la población pobre y en extrema pobreza y las pequeñas y medianas unidades productivas','S8.Generación de oportunidades de inclusión socio-económica para la población pobre y en extrema pobreza y las pequeñas y medianas unidades productivas',1,'SA-1020',SYSDATE);
INSERT INTO FENIX.TCA_SECTOR_AMBIENTAL (DESCRIPCION, DESCRIPCION_CORTA, BAN_ESTATUS, CODIGO_EXTERNO, FECHA_REGISTRO) VALUES ('S9.Vivienda y entorno urbano','S9.Vivienda y entorno urbano',1,'SA-1021',SYSDATE);
INSERT INTO FENIX.TCA_SECTOR_AMBIENTAL (DESCRIPCION, DESCRIPCION_CORTA, BAN_ESTATUS, CODIGO_EXTERNO, FECHA_REGISTRO) VALUES ('S10.Infraestructura de transporte','S10.Infraestructura de transporte',1,'SA-1022',SYSDATE);COMMIT;

-- UPDATE Catalogo SECTOR_AMBIENTAL
-- Tabla No.3 FENIX.TCA_SECTOR_AMBIENTAL
UPDATE FENIX.TCA_SECTOR_AMBIENTAL SET BAN_ESTATUS = 0 WHERE ID <= 11;
COMMIT;
