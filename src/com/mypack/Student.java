package com.mypack;

import java.util.Date;

public class Student
{
	private String SNO;
	private String SNAME;
	private String SSEX;
	private Date SBIRTHDAY;
	private String CLASS;
	public String getSNO()
	{
		return SNO;
	}
	public void setSNO(String sNO)
	{
		SNO = sNO;
	}
	public String getSNAME()
	{
		return SNAME;
	}
	public void setSNAME(String sNAME)
	{
		SNAME = sNAME;
	}
	public String getSSEX()
	{
		return SSEX;
	}
	public void setSSEX(String sSEX)
	{
		SSEX = sSEX;
	}
	public Date getSBIRTHDAY()
	{
		return SBIRTHDAY;
	}
	public void setSBIRTHDAY(Date sBIRTHDAY)
	{
		SBIRTHDAY = sBIRTHDAY;
	}
	public String getCLASS()
	{
		return CLASS;
	}
	public void setCLASS(String cLASS)
	{
		CLASS = cLASS;
	}
}
