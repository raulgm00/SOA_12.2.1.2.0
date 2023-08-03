

	CREATE OR REPLACE VIEW VCA_FUENTE AS 
		Select LIN.Codigo_Linea_Pasiva as ID 
		, LIN.Descrip_Linea_Pasiva || ' (' || LIN.Codigo_Linea_Pasiva || ')' as DESCRIPCION 
		, LIN.Monto_Disponible as MONTO_DISPONIBLE
		, LIN.Fecha_Vencimiento as FECHA_VENCIMIENTO
		, LIN.Moneda as MONEDA
		, LIN.Codigo_Fondo as CODIGO_FONDO
		, LIN.Codigo_Cliente as CODIGO_CLIENTE
		, LIN.Es_Externo
		From middle.ar_v_Linea_Pasiva LIN
		Where LIN.Fecha_Vencimiento >= SYSDATE;
	/