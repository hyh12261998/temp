package com.hyh.www.ExcelDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Workbook;

//��Excel�ж�ȡ���ݲ����͵���Ϣ�м��

public class SendDemo {
	
	public static void main(String[] args) throws Exception {
		try {	
			File excelFile=new File("C:\\Users\\17905\\Desktop\\j2ee\\Software17_Student_JavaEE.xlsx");//�����ļ�����
			FileInputStream in=new FileInputStream(excelFile);//�ļ���
			ExcelUtil.checkExcelVaild(excelFile);//�ж��ļ��Ƿ���Excel�ļ�
			Workbook workbook=ExcelUtil.getWorkbook(in,excelFile);
			ExcelUtil.disPlayRow(workbook);
			ProducerDemo cd=new ProducerDemo();
			cd.sendMessageExcel(ExcelUtil.getCellNum()*ExcelUtil.getRowNum(), ExcelUtil.getExcelCell());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
