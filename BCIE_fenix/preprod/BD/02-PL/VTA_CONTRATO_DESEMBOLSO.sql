
	-- 20180213 Se crea la vista VTA_CONTRATO_DESEMBOLSO, en base a una vista materializada  MIDDLE.DW_MV_CONTRACTS, 
	-- Como esta vista solo afeta a prepago, se modifica la vista base, como lo indica Gustavo en  su correo del 08 de Feb de 2018 
	-- Re: Revisi�n de la vista MIDDLE.DW_MV_CONTRACTS

  CREATE OR REPLACE VIEW VTA_CONTRATO_DESEMBOLSO AS 
  SELECT CONTRACT_REF_NO AS NUMERO_CONTRATO,
	CODIGO_LINEA_CREDITO,
	NUMERO_TESORERIA,
	SECTOR_INSTITUCIONAL,
	CODIGO_PROGRAMA,
	CODIGO_DESTINO,
	CODIGO_EJE,
	CODIGO_CLIENTE,
	NOMBRE_CLIENTE,
	CODIGO_TIPO_PRESTAMO,
	CODIGO_PAIS,
	CODIGO_ACTIVIDAD_ECONOMICA,
	CODIGO_MONEDA,
	CODIGO_FONDO,
	CODIGO_EJECUTIVO,
	TIPO_FINANCIAMIENTO,
	FECHA_VENCIMIENTO,
	FECHA_APERTURA,
	FECHA_RECIBIDO,
	FECHA_ESCRITURACION,
	MONTO_APROBADO,
	MONTO_APROBADO_LCY,
	MONTO_INICIAL,
	OBSERVACIONES,
	CODIGO_GRUPO_EMPRESARIAL,
	CONTRACT_REF_NO,
	LATEST_VERSION_NO,
	MODULE_CODE,
	PRODUCT,
	PRODUCT_TYPE,
	CONTRACT_STATUS,
	MAIN_COMP,
	CONTRACT_REF_NO_L,
	LATEST_VERSION_NO_L,
	BRANCH,
	USER_REF_NO,
	EXTERNAL_REF_NO,
	NUMERO_TESORERIA_UDF,
	NUMERO_CONTRATO AS NUMERO_CONTRATO_DW,
	OPERACION
	FROM MIDDLE.DW_MV_CONTRACTS;

   COMMENT ON TABLE "FENIX"."VTA_CONTRATO_DESEMBOLSO"  IS 'En base al correo del cliente del 12 de Enero de 2017, se indica que el campo CONTRACT_REF_NO de la vista MIDDLE.DW_V_CONTRACTS, corresponde al N�mero BHQ del desembolso,
	mientas que el campo NUMERO_CONTRATO de la misma vista, representa el n�mero del contrato dentro del DW, para no impactar la construcci�n ya realizada,
	en la vista (VTA_CONTRATO_DESEMBOLSO) se asignan los alias a los campos correspondientes, quedando como sigue: CONTRACT_REF_NO AS NUMERO_CONTRATO y NUMERO_CONTRATO AS NUMERO_CONTRATO_DW
	ya que en la definici�n inicial se habia considerado el campo NUMERO_CONTRATO como el N�mero BHQ del desembolso.';


