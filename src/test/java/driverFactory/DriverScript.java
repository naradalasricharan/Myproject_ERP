package driverFactory;

import commonFunctions.FunctionLibrary;
import utilities.ExcellFileUtil;

public class DriverScript extends FunctionLibrary
{
	String inputpath="./FileInput/DataEngine.xlsx";
	String outputpath="./FileOutput/HybridResults.xlsx";
	String Module_Status="";
	public  void StartTest()throws Throwable
	{
		ExcellFileUtil xl=new ExcellFileUtil(inputpath);
		for(int i=1;i<=xl.rowCount("MasterTestCase");i++)
		{
			if(xl.getCellData("MasterTestCase", i,2).equalsIgnoreCase("y"))
			{
				String TCmodule=xl.getCellData("MasterTestCase", i,1);
				for(int j=1;j<=xl.rowCount(TCmodule);j++)
				{
					String Describtion=xl.getCellData(TCmodule, j,0);
					String ObjectType=xl.getCellData(TCmodule, j,1);
					String LocatorType=xl.getCellData(TCmodule, j,2);
					String LocatorValue=xl.getCellData(TCmodule, j,3);
					String TestData=xl.getCellData(TCmodule, j,4);
					
					try {
						if(ObjectType.equalsIgnoreCase("startBrowser"))
						{
							driver=FunctionLibrary.startBrowser();
						}else if(ObjectType.equalsIgnoreCase("openUrl"))
						{
							FunctionLibrary.openUrl(driver);
						}else if(ObjectType.equalsIgnoreCase("waitForElement"))
						{
							FunctionLibrary.waitForElement(driver, LocatorType, LocatorValue, TestData);
							
						}else if(ObjectType.equalsIgnoreCase("typeAction"))
						{
							FunctionLibrary.typeAction(driver, LocatorType, LocatorValue, TestData);
						}else if(ObjectType.equalsIgnoreCase("validateTitle"))
						{
							FunctionLibrary.validateTitle(driver, TestData);
						}else if(ObjectType.equalsIgnoreCase("closeBrowser"))
						{
							FunctionLibrary.closeBrowser(driver);
						}
						//Write As Pass In TCmodule 
						xl.setCelldata(TCmodule, j, 5, "Pass",outputpath);
						Module_Status="True";
						
					} catch (Exception e)
					{
						System.out.println(e.getMessage() );
						xl.setCelldata(TCmodule, j, 5, "Fail",outputpath);
						Module_Status="Fail";
					}
					if(Module_Status.equalsIgnoreCase("True"))
					{
						xl.setCelldata("MasterTestCase", i, 3,"Pass",outputpath);
					}else
					{
						xl.setCelldata("MasterTestCase", i, 3,"Fail",outputpath);
					}
					
				}
			}
			else
			{
				xl.setCelldata("MasterTestCase", i, 3,"Blocked",outputpath);
			}
			
		}
	}

}
